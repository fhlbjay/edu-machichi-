package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.*;
import cn.wolfcode.crm.mapper.DefrayMapper;
import cn.wolfcode.crm.mapper.DepartmentMapper;
import cn.wolfcode.crm.mapper.OfficialStudentMapper;
import cn.wolfcode.crm.mapper.WastageStudentMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IWastageStudentService;
import cn.wolfcode.crm.util.BillNumberBuilder;
import cn.wolfcode.crm.util.DateCompareUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class WastageStudentServiceImpl implements IWastageStudentService {
    @Autowired
    private WastageStudentMapper wastageStudentMapper;
    @Autowired
    private OfficialStudentMapper officialStudentMapper;
    @Autowired
    private DefrayMapper defrayMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        wastageStudentMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(WastageStudent record) {

        Long studentId = record.getStudentId();
        OfficialStudent officialStudent = officialStudentMapper.selectByPrimaryKey(studentId);
        //设置姓名
        record.setName(officialStudent.getPotentitalStudent().getName());
        //设置电话
        record.setTel(officialStudent.getPotentitalStudent().getTel());
        //设置营销人员
        record.setSaleman(officialStudent.getPotentitalStudent().getEmployee());
        //流失原因
        //退款状态
        //班级
        record.setClassgrade(officialStudent.getPotentitalStudent().getClassGrade());
        //退款金额
        //审核状态
        record.setAuditState(false);
        //审核人
        record.setOperator(null);
        //入学时间
        record.setEnrolTime(officialStudent.getEnrolTime());
        //流失日期
        record.setWastageDate(new Date());
        //已上学天数
        Long day=((new Date().getTime()-officialStudent.getEnrolTime().getTime())/(24*60*60*1000))+1L;
        if (day<0L){
            day = 0L;
        }
        record.setStudyDaysNum(day);
        if(record.getRefundState()){
           record.setRefundMoney(BigDecimal.ZERO);
        }
        officialStudentMapper.runOff(studentId);

        return wastageStudentMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(WastageStudent record) {
        //在更新之前先删除之前的关系
        return wastageStudentMapper.updateByPrimaryKey(record);
    }

    @Override
    public WastageStudent selectByPrimaryKey(Long id) {
        WastageStudent wastageStudent = wastageStudentMapper.selectByPrimaryKey(id);
        return wastageStudent;
    }

    @Override
    public List<WastageStudent> selectAll() {
        List<WastageStudent> wastageStudents = wastageStudentMapper.selectAll();
        return wastageStudents;
    }

    //分页
    @Override
    public PageResult query(QueryObject qo) {
        //判断满足条件的总数
        int total = wastageStudentMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(total, Collections.emptyList());
        }
        List<WastageStudent> rows = wastageStudentMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    //审核
    @Override
    public void changeState(Long id) {
        Subject subject = SecurityUtils.getSubject();
        Employee operator = (Employee) subject.getPrincipal();
        WastageStudent wastageStudent = wastageStudentMapper.selectByPrimaryKey(id);
        wastageStudent.setOperator(operator);
        if (!wastageStudent.getRefundState()){
            //如果还未退款,就返回提示信息给前台
            throw new RuntimeException("尚未给该学员退款,无法审核,请再次核实");
        }
        wastageStudent.setAuditState(true);
        wastageStudentMapper.changeState(wastageStudent);
    }
    //退款
    @Override
    public void refund(Defray defray) {
        //退款
        defray.setDefrayTime(new Date());
        WastageStudent wastageStudent = wastageStudentMapper.selectByPrimaryKey(defray.getApplicant().getId());
        //未付款金额
        BigDecimal refundMoney = wastageStudent.getRefundMoney();
        BigDecimal cunrrentRefundMoney = refundMoney.subtract(defray.getDefrayMoney());
        wastageStudent.setRefundMoney(cunrrentRefundMoney);
        if (cunrrentRefundMoney.compareTo(BigDecimal.ZERO)<=0){
            wastageStudent.setRefundState(true);

        }else{
            wastageStudent.setRefundState(false);
        }
            wastageStudentMapper.refund(wastageStudent);
        //如果前台传过来的退款金额比0大,就新增一条支出
        if (refundMoney.compareTo(BigDecimal.ZERO)>0){
                 //给支出表中添加一条数据
            Subject subject = SecurityUtils.getSubject();
            Employee operator = (Employee) subject.getPrincipal();
            defray.setHandMan(operator);
            defray.setApplicant(operator);
            SystemDictionaryItem defrayType = new SystemDictionaryItem();
            defrayType.setId(100L);
            defray.setDefrayType(defrayType);

            //设置单据号
            //生成流水号
             Defray Maxdefray = defrayMapper.selectByMaxId();
            BillNumberBuilder.buildBillNumber(defray,Maxdefray);

            defrayMapper.insert(defray);
        }
    }




}
