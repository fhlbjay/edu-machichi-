package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.*;
import cn.wolfcode.crm.mapper.*;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IOfficialStudentService;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.AfterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Service
public class OfficialStudentServiceImpl implements IOfficialStudentService {
    @Autowired
    private OfficialStudentMapper officialStudentMapper;
    @Autowired
    private PotentitalStudentMapper potentitalStudentMapper;
    @Autowired
    private DetailInfoMapper detailInfoMapper;
    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private PaymentManageMapper paymentManageMapper;
    @Autowired
    private ChangeClassMapper changeClassMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        officialStudentMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(OfficialStudent record) {
        //OfficialStudent表格相关信息插入
        officialStudentMapper.insert(record);
        //payment表格相关信息插入
        Payment payment = record.getPayment();
        payment.setOfficial_id(record.getId());
        paymentMapper.insert(payment);
        if (payment.getPaidupFee() != null && !BigDecimal.ZERO.equals(payment.getPaidupFee())) {
            //收款明细插入
            PaymentManage paymentManage = new PaymentManage();
            paymentManage.setOfficialStudent(record);
            paymentManage.setPayee(record.getPotentitalStudent().getEmployee());
            paymentManage.setPaymentAmount(payment.getPaidupFee());
            paymentManage.setPaymentType(payment.getPaymentType());
            paymentManageMapper.insert(paymentManage);

        }
        //detailInfo表格相关信息插入
        DetailInfo detailInfo = record.getDetailInfo();
        detailInfo.setOfficial_id(record.getId());
        detailInfoMapper.insert(detailInfo);
        //把潜在学员的状态设置为正式学员
        potentitalStudentMapper.change2official(record.getPotentitalStudent().getId());
        return 0;
    }

    @Override
    public int updateByPrimaryKey(OfficialStudent record) {
        //更新意向客户表格相关信息
        PotentitalStudent potentitalStudent = record.getPotentitalStudent();
        System.out.println(potentitalStudent);
        System.out.println("--------------------");
        potentitalStudentMapper.updatebaseInfoByPrimaryKey(potentitalStudent);
        //更新OfficialStudent表格相关信息
        officialStudentMapper.updateByPrimaryKey(record);
        //更新payment表格相关信息(此处不允许修改)
//        Payment payment = record.getPayment();
//        payment.setOfficial_id(record.getId());
//        paymentMapper.updateByOfficialId(payment);
        //更新detailInfo表格相关信息
        DetailInfo detailInfo = record.getDetailInfo();
        detailInfo.setOfficial_id(record.getId());
        detailInfoMapper.updateByOfficialId(detailInfo);
        return 0;
    }


    @Override
    public OfficialStudent selectByPrimaryKey(Long id) {
        OfficialStudent officialStudent = officialStudentMapper.selectByPrimaryKey(id);
        return officialStudent;
    }

    @Override
    public List<OfficialStudent> selectAll() {
        List<OfficialStudent> officialStudents = officialStudentMapper.selectAll();
        return officialStudents;
    }

    //分页
    @Override
    public PageResult query(QueryObject qo) {
        //判断满足条件的总数
        int total = officialStudentMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(total, Collections.emptyList());
        }
        List<OfficialStudent> rows = officialStudentMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    //状态改变
    @Override
    public void changeClass(Long id) {
        officialStudentMapper.changeClass(id);
    }


    @Override
    public void export(HttpServletResponse response) throws Exception {
    }

    @Override
    public void quitSchool(Long id, Long statusId) {
        officialStudentMapper.quitSchool(id, statusId);
    }

    @Override
    public void runOff(Long id) {
        officialStudentMapper.runOff(id);
    }

    @Override
    public List<OfficialStudent> selectUnpaid() {
        return officialStudentMapper.selectUnpaid();
    }

    @Override
    public OfficialStudent selectByPsId(Long id) {
        return officialStudentMapper.selectByPsId(id);
    }


}
