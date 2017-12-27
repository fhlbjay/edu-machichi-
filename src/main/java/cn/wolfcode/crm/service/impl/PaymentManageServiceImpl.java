package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.PaymentManage;
import cn.wolfcode.crm.mapper.PaymentManageMapper;
import cn.wolfcode.crm.mapper.OfficialStudentMapper;
import cn.wolfcode.crm.mapper.PaymentMapper;
import cn.wolfcode.crm.mapper.PotentitalStudentMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPaymentManageService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

@Service
public class PaymentManageServiceImpl implements IPaymentManageService {
    @Autowired
    private PaymentManageMapper paymentManageMapper;
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private OfficialStudentMapper officialStudentMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        paymentManageMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(PaymentManage record) {
        paymentManageMapper.insert(record);
        return 0;
    }

    @Override
    public int updateByPrimaryKey(PaymentManage record) {
        if (record.isAuditStatus()) {
            return 0;
        }
        return paymentManageMapper.updateByPrimaryKey(record);
    }


    @Override
    public PaymentManage selectByPrimaryKey(Long id) {
        PaymentManage paymentManage = paymentManageMapper.selectByPrimaryKey(id);
        return paymentManage;
    }

    @Override
    public List<PaymentManage> selectAll() {
        List<PaymentManage> paymentManages = paymentManageMapper.selectAll();
        return paymentManages;
    }

    //分页
    @Override
    public PageResult query(QueryObject qo) {
        //判断满足条件的总数
        int total = paymentManageMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(total, Collections.emptyList());
        }
        List<PaymentManage> rows = paymentManageMapper.queryForList(qo);
        return new PageResult(total, rows);
    }


    //    //文件下载
    @Override
    public void export(HttpServletResponse response) throws Exception {
//        //文件下载响应头
//        response.setHeader("Content-Disposition", "attachment;filename=paymentManage.xls");
//        //创建xls文件
//        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
//        //创建工作簿
//        WritableSheet sheet = workbook.createSheet("day01", 0);
//        //给文件添加标题
//        sheet.addCell(new Label(0, 0, "姓名"));
//        sheet.addCell(new Label(1, 0, "真实姓名"));
//        sheet.addCell(new Label(2, 0, "电话"));
//        sheet.addCell(new Label(3, 0, "邮箱"));
//        sheet.addCell(new Label(4, 0, "部门"));
//        sheet.addCell(new Label(5, 0, "入职时间"));
//        sheet.addCell(new Label(6, 0, "超级管理员"));
//        sheet.addCell(new Label(7, 0, "状态"));
//        //获取真实的员工数据
//        List<PaymentManage> paymentManages = paymentManageMapper.selectAll();
//        //将日期进行格式化操作
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        for (int i = 0; i < paymentManages.size(); i++) {
//            PaymentManage paymentManage = paymentManages.get(i);
//            //往工作簿表格中添加数据
//            if (paymentManage.getUsername() != null) {
//                sheet.addCell(new Label(0, i + 1, paymentManage.getUsername()));
//            } else {
//                sheet.addCell(new Label(0, i + 1, null));
//            }
//
//            if (paymentManage.getRealname() != null) {
//                sheet.addCell(new Label(1, i + 1, paymentManage.getRealname()));
//            } else {
//                sheet.addCell(new Label(1, i + 1, null));
//            }
//
//            if (paymentManage.getTel() != null) {
//                sheet.addCell(new Label(2, i + 1, paymentManage.getTel()));
//            } else {
//                sheet.addCell(new Label(2, i + 1, null));
//            }
//
//            if (paymentManage.getEmail() != null) {
//                sheet.addCell(new Label(3, i + 1, paymentManage.getEmail()));
//            } else {
//                sheet.addCell(new Label(3, i + 1, null));
//            }
//
//            if (paymentManage.getDept() != null) {
//                sheet.addCell(new Label(4, i + 1, paymentManage.getDept().getName()));
//            } else {
//                sheet.addCell(new Label(4, i + 1, null));
//            }
//
//            if (paymentManage.getInputtime() != null) {
//                sheet.addCell(new Label(5, i + 1, dateFormat.format(paymentManage.getInputtime())));
//            } else {
//                sheet.addCell(new Label(5, i + 1, null));
//            }
//
//            if (paymentManage.getAdmin()) {
//                sheet.addCell(new Label(6, i + 1, "是"));
//            } else {
//                sheet.addCell(new Label(6, i + 1, "否"));
//            }
//
//            if (paymentManage.isState()) {
//                sheet.addCell(new Label(7, i + 1, "在职"));
//            } else {
//                sheet.addCell(new Label(7, i + 1, "离职"));
//            }
//        }
//        //写入数据
//        workbook.write();
//        //关闭资源
//        workbook.close();
    }

    @Override
    public void audit(Long id) {
        //获得当前用户id
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        paymentManageMapper.audit(id, employee.getId());
        //审核完毕之后 跟新正式学员中的已付费用
        //1.拿到最新的待缴费用
        PaymentManage paymentManage = paymentManageMapper.selectByPrimaryKey(id);
        paymentMapper.updatePaidupFee(paymentManage.getPaymentAmount(), paymentManage.getOfficialStudent().getId());
        //2.根据已付费用更新正式学员中的待缴费用
        paymentMapper.updateFee2pay(paymentManage.getOfficialStudent().getId());
        //3.根据已付费用更新付款状态
        paymentMapper.updatePaidStatus();


    }
}
