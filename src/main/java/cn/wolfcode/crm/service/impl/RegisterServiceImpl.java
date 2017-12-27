package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Register;
import cn.wolfcode.crm.mapper.RegisterMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.RegisterQueryObject;
import cn.wolfcode.crm.service.IRegisterService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by joy on 2017/12/23.
 */
@Service
public class RegisterServiceImpl implements IRegisterService {
    @Autowired
    RegisterMapper mapper;
    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Register record) {
        mapper.insert(record);
    }

    @Override
    public Register selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Register> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Register record) {
        mapper.updateByPrimaryKey(record);
    }

    @Override
    public void changeState(Long id) {
        mapper.changeState(id);
    }

    @Override
    public void export(HttpServletResponse response) throws Exception {
        //文件下载响应头
        response.setHeader("Content-Disposition", "attachment;filename=register.xls");
        //创建xls文件
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        //创建工作簿
        WritableSheet sheet = workbook.createSheet("day01", 0);
        //给文件添加标题
        sheet.addCell(new Label(0, 0, "姓名"));
        sheet.addCell(new Label(1, 0, "营销人员"));
        sheet.addCell(new Label(2, 0, "编号"));
        sheet.addCell(new Label(3, 0, "考试时间"));
        sheet.addCell(new Label(4, 0, "QQ"));
        sheet.addCell(new Label(5, 0, "电话"));
        sheet.addCell(new Label(7, 0, "意向班级"));
        sheet.addCell(new Label(8, 0, "审核结果"));
        sheet.addCell(new Label(9, 0, "考试结果"));
        sheet.addCell(new Label(10, 0, "备注"));
        //获取真实的员工数据
        List<Register>registers = mapper.selectAll();
        System.out.println(registers);
        //将日期进行格式化操作
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < registers.size(); i++) {
            Register register = registers.get(i);
            //往工作簿表格中添加数据
            if (register.getName()!= null) {
                sheet.addCell(new Label(0, i + 1, register.getName()));
            } else {
                sheet.addCell(new Label(0, i + 1, null));
            }

            if (register.getEmployee() != null) {
                sheet.addCell(new Label(1, i + 1, register.getEmployee().getUsername()));
            } else {
                sheet.addCell(new Label(1, i + 1, null));
            }

            if (register.getSn() != null) {
                sheet.addCell(new Label(2, i + 1,register.getSn().toString()));
            } else {
                sheet.addCell(new Label(2, i + 1, null));
            }

            if (register.getTestTime() != null) {
                sheet.addCell(new Label(3, i + 1, dateFormat.format(register.getTestTime())));
            } else {
                sheet.addCell(new Label(3, i + 1, null));
            }

            if (register.getQq()!=null) {
                sheet.addCell(new Label(4, i + 1,register.getQq()));
            } else {
                sheet.addCell(new Label(4, i + 1, null));
            }

            if (register.getTel()!=null) {
                sheet.addCell(new Label(5, i + 1, register.getTel()));
            } else {
                sheet.addCell(new Label(5, i + 1, null));
            }
            if (register.getClassGrade()!=null) {
                sheet.addCell(new Label(6, i + 1, register.getClassGrade().getName()));
            } else {
                sheet.addCell(new Label(6, i + 1, null));
            }
            if (register.getState()) {
                sheet.addCell(new Label(7, i + 1,"审核"));
            } else {
                sheet.addCell(new Label(7, i + 1, "未审核"));
            }
            if (register.getTestResult()) {
                sheet.addCell(new Label(8, i + 1, "通过"));
            } else {
                sheet.addCell(new Label(8, i + 1, "未通过"));
            }
            if (register.getRemark()!=null) {
                sheet.addCell(new Label(9, i + 1, register.getRemark()));
            } else {
                sheet.addCell(new Label(9, i + 1, "未跟踪"));
            }
        }
        //写入数据
        workbook.write();
        //关闭资源
        workbook.close();
    }

    @Override
    public PageResult query(RegisterQueryObject rqo) {
        Integer total = mapper.queryForCount(rqo);
        List<Register> list = mapper.queryForList(rqo);
        PageResult pageResult=new PageResult(total,list);
        return pageResult;
    }

    @Override
    public void testRegister(Register register) {
        mapper.testRegister(register);
    }
}
