package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.ChangeClass;
import cn.wolfcode.crm.domain.OfficialStudent;
import cn.wolfcode.crm.domain.PotentitalStudent;
import cn.wolfcode.crm.mapper.ChangeClassMapper;
import cn.wolfcode.crm.mapper.OfficialStudentMapper;
import cn.wolfcode.crm.mapper.PotentitalStudentMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IChangeClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

@Service
public class ChangeClassServiceImpl implements IChangeClassService {
    @Autowired
    private ChangeClassMapper changeClassMapper;
    @Autowired
    private OfficialStudentMapper officialStudentMapper;
    @Autowired
    private PotentitalStudentMapper potentitalStudentMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        changeClassMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(Long id, Long afterClassId, String beforeClass) {
        changeClassMapper.insert(id, afterClassId, beforeClass);
        return 0;
    }

    @Override
    public int updateByPrimaryKey(ChangeClass record) {
        if (record.isAuditStatus()) {
            return 0;
        }
        return changeClassMapper.updateByPrimaryKey(record);
    }


    @Override
    public ChangeClass selectByPrimaryKey(Long id) {
        ChangeClass changeClass = changeClassMapper.selectByPrimaryKey(id);
        return changeClass;
    }

    @Override
    public List<ChangeClass> selectAll() {
        List<ChangeClass> changeClasss = changeClassMapper.selectAll();
        return changeClasss;
    }

    //分页
    @Override
    public PageResult query(QueryObject qo) {
        //判断满足条件的总数
        int total = changeClassMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(total, Collections.emptyList());
        }
        List<ChangeClass> rows = changeClassMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    //状态改变
    @Override
    public void changeClass(Long id) {
    }


    //    //文件下载
    @Override
    public void export(HttpServletResponse response) throws Exception {
//        //文件下载响应头
//        response.setHeader("Content-Disposition", "attachment;filename=changeClass.xls");
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
//        List<ChangeClass> changeClasss = changeClassMapper.selectAll();
//        //将日期进行格式化操作
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        for (int i = 0; i < changeClasss.size(); i++) {
//            ChangeClass changeClass = changeClasss.get(i);
//            //往工作簿表格中添加数据
//            if (changeClass.getUsername() != null) {
//                sheet.addCell(new Label(0, i + 1, changeClass.getUsername()));
//            } else {
//                sheet.addCell(new Label(0, i + 1, null));
//            }
//
//            if (changeClass.getRealname() != null) {
//                sheet.addCell(new Label(1, i + 1, changeClass.getRealname()));
//            } else {
//                sheet.addCell(new Label(1, i + 1, null));
//            }
//
//            if (changeClass.getTel() != null) {
//                sheet.addCell(new Label(2, i + 1, changeClass.getTel()));
//            } else {
//                sheet.addCell(new Label(2, i + 1, null));
//            }
//
//            if (changeClass.getEmail() != null) {
//                sheet.addCell(new Label(3, i + 1, changeClass.getEmail()));
//            } else {
//                sheet.addCell(new Label(3, i + 1, null));
//            }
//
//            if (changeClass.getDept() != null) {
//                sheet.addCell(new Label(4, i + 1, changeClass.getDept().getName()));
//            } else {
//                sheet.addCell(new Label(4, i + 1, null));
//            }
//
//            if (changeClass.getInputtime() != null) {
//                sheet.addCell(new Label(5, i + 1, dateFormat.format(changeClass.getInputtime())));
//            } else {
//                sheet.addCell(new Label(5, i + 1, null));
//            }
//
//            if (changeClass.getAdmin()) {
//                sheet.addCell(new Label(6, i + 1, "是"));
//            } else {
//                sheet.addCell(new Label(6, i + 1, "否"));
//            }
//
//            if (changeClass.isState()) {
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
        ChangeClass changeClass = changeClassMapper.selectByPrimaryKey(id);
        changeClassMapper.audit(id);
        //审核完毕需要修改正式学员表中的状态
        officialStudentMapper.changeStatusToNormal(changeClass.getOfficialStudent().getId());
        Long afterClassId = changeClass.getAfterClass().getId();
        System.out.println(afterClassId);
        officialStudentMapper.changepotentitalStudentClass(changeClass.getOfficialStudent().getPotentitalStudent().getId(), afterClassId);

    }
}
