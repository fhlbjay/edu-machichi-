package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Defray;
import cn.wolfcode.crm.domain.Defray;
import cn.wolfcode.crm.mapper.DepartmentMapper;
import cn.wolfcode.crm.mapper.OfficialStudentMapper;
import cn.wolfcode.crm.mapper.DefrayMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDefrayService;
import cn.wolfcode.crm.util.BillNumberBuilder;
import cn.wolfcode.crm.util.DateCompareUtil;
import cn.wolfcode.crm.util.HandleDateUtil;
import cn.wolfcode.crm.util.ResponseContextUtil;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class DefrayServiceImpl implements IDefrayService {
    @Autowired
    private DefrayMapper defrayMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        defrayMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(Defray record) {
        //新增时设置经手人
        Subject subject = SecurityUtils.getSubject();
        Employee operator = (Employee) subject.getPrincipal();
        record.setHandMan(operator);
        record.setDefrayTime(new Date());

        //生成流水号
        Defray Maxdefray = defrayMapper.selectByMaxId();
        BillNumberBuilder.buildBillNumber(record,Maxdefray);



        return defrayMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(Defray record) {
        //在更新之前先删除之前的关系
        return defrayMapper.updateByPrimaryKey(record);
    }


    @Override
    public Defray selectByPrimaryKey(Long id) {
        Defray defray = defrayMapper.selectByPrimaryKey(id);
        return defray;
    }

    @Override
    public List<Defray> selectAll() {
        List<Defray> defrays = defrayMapper.selectAll();
        return defrays;
    }

    //分页
    @Override
    public PageResult query(QueryObject qo) {
        //判断满足条件的总数
        int total = defrayMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(total, Collections.emptyList());
        }
        List<Defray> rows = defrayMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    //审核
    @Override
    public void changeState(Long id) {
        Subject subject = SecurityUtils.getSubject();
        Employee operator = (Employee) subject.getPrincipal();
        Defray defray = defrayMapper.selectByPrimaryKey(id);
        defray.setAuditor(operator);
        defrayMapper.changeState(defray);
    }

    //导出工资表
    @Override
    public void export() throws Exception {
        HttpServletResponse response = ResponseContextUtil.getResponse();
        //文件下载响应头
        String fileName = new String("支出表".getBytes("utf-8"), "ISO-8859-1");
        response.setHeader("Content-Disposition", (new StringBuilder()).append("attachment;filename=").append(fileName).append(".xls").toString());
        //创建xls文件
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        //创建工作簿
        WritableSheet sheet = workbook.createSheet("day01", 0);
        sheet.addCell(new Label(5, 0, "叩叮狼教育科技公司支出表详情"));
        //给文件添加标题
        sheet.addCell(new Label(2, 2, "支出时间"));
        sheet.addCell(new Label(3, 2, "支出金额"));
        sheet.addCell(new Label(4, 2, "支出摘要"));
        sheet.addCell(new Label(5, 2, "申请人"));
        sheet.addCell(new Label(6, 2, "经手人"));
        sheet.addCell(new Label(7, 2, "支付方式"));
        sheet.addCell(new Label(8, 2, "单据号"));
        sheet.addCell(new Label(9, 2, "支出类型"));
        sheet.addCell(new Label(10, 2, "所属班级"));
        /*sheet.addCell(new Label(11, 2, "审核人"));*/
        sheet.addCell(new Label(12, 2, "审核状态"));
        //获取真实的课表数据
        List<Defray> defrays = defrayMapper.selectAll();
        //将日期进行格式化操作
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < defrays.size(); i++) {
            Defray defray = defrays.get(i);
            if (defray != null) {
                //往工作簿表格中添加数据
                if (defray.getDefrayTime() != null) {
                    sheet.addCell(new Label(2, i + 3, dateFormat1.format(defray.getDefrayTime())));
                } else {
                    sheet.addCell(new Label(2, i + 3, null));
                }
                if (defray.getDefrayMoney() != null ) {
                    sheet.addCell(new Label(3, i + 3, defray.getDefrayMoney().toString()));
                } else {
                    sheet.addCell(new Label(3, i + 3, null));
                }
                if (defray.getRemark()!= null && defray.getRemark()!= "") {
                    sheet.addCell(new Label(4, i + 3, defray.getRemark()));
                } else {
                    sheet.addCell(new Label(4, i + 3, null));
                }
                if (defray.getApplicant()!=null){

                    if (defray.getApplicant().getUsername()!= null && defray.getApplicant().getUsername()!= "") {
                        sheet.addCell(new Label(5, i + 3, defray.getApplicant().getUsername()));
                    } else {
                        sheet.addCell(new Label(5, i + 3, null));
                    }
                }
                if (defray.getHandMan()!=null){

                    if (defray.getHandMan().getUsername()!= null && defray.getHandMan().getUsername()!= "") {
                        sheet.addCell(new Label(6, i + 3, defray.getHandMan().getUsername()));
                    } else {
                        sheet.addCell(new Label(6, i + 3, null));
                    }
                }
                if (defray.getPaymentType()!=null){

                    if (defray.getPaymentType().getName()!= null && defray.getPaymentType().getName()!= "") {
                        sheet.addCell(new Label(7, i + 3, defray.getPaymentType().getName()));
                    } else {
                        sheet.addCell(new Label(7, i + 3, null));
                    }
                }

                    if (defray.getBillnumber()!= null && defray.getBillnumber()!= "") {
                        sheet.addCell(new Label(8, i + 3, defray.getBillnumber()));
                    } else {
                        sheet.addCell(new Label(8, i + 3, null));
                    }
                if (defray.getDefrayType()!=null){

                    if (defray.getDefrayType().getName()!= null && defray.getDefrayType().getName()!= "") {
                        sheet.addCell(new Label(9, i + 3, defray.getDefrayType().getName()));
                    } else {
                        sheet.addCell(new Label(9, i + 3, null));
                    }
                }
                if (defray.getClassGrade()!=null){

                    if (defray.getClassGrade().getName()!= null && defray.getClassGrade().getName()!= "") {
                        sheet.addCell(new Label(10, i + 3, defray.getClassGrade().getName()));
                    } else {
                        sheet.addCell(new Label(10, i + 3, null));
                    }
                }
              /*  if (defray.getAuditor()!=null){
                    if (defray.getAuditor().getUsername()!= null && defray.getAuditor().getUsername()!= "") {
                        sheet.addCell(new Label(11, i + 3, defray.getClassGrade().getName()));
                    } else {
                        sheet.addCell(new Label(11, i + 3, null));
                    }
                }*/

                    if (defray.getAuditState()!= null ) {
                        sheet.addCell(new Label(12, i + 3, HandleDateUtil.getState(defray.getAuditState())));
                    } else {
                        sheet.addCell(new Label(12, i + 3, null));
                    }
            }
        }
        //写入数据
        workbook.write();
        //关闭资源
        workbook.close();
    }

}
