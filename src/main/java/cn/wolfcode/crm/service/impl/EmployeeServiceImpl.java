package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.mapper.DepartmentMapper;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.util.ResponseContextUtil;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    public int deleteByPrimaryKey(Long id) {
        employeeMapper.deleteRelation(id);
        employeeMapper.deleteByPrimaryKey(id);
        return 0;
    }

    public int insert(Employee record) {
        //对密码进行加密操作(加密规则Md5Hash:密码+用户名+2次盐)
        record.setPassword(new Md5Hash(record.getPassword(), record.getRealname(), 2).toString());
        employeeMapper.insert(record);
        List<Role> roles = record.getRoles();
        if (roles.size() > 0 ) {
            employeeMapper.batchInsertRelation(record.getId(), roles);
        }
        return 0;
    }

    public int updateByPrimaryKey(Employee record) {
        //在更新之前先删除之前的关系
        employeeMapper.deleteRelation(record.getId());
        List<Role> roles = record.getRoles();
        if (roles != null) {

            employeeMapper.batchInsertRelation(record.getId(), roles);
        }
        employeeMapper.updateByPrimaryKey(record);
        return 0;
    }


    public Employee selectByPrimaryKey(Long id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    public List<Employee> selectAll() {
        List<Employee> employees = employeeMapper.selectAll();
        return employees;
    }

    //分页
    public PageResult query(QueryObject qo) {
        //判断满足条件的总数
        int total = employeeMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(total, Collections.emptyList());
        }
        List<Employee> rows = employeeMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    //状态改变
    public void changeState(Long id) {
        employeeMapper.changeState(id);
    }

    //根据员工id查角色
    public List<Long> getRolesByempId(Long empId) {
        return employeeMapper.getRolesByempId(empId);
    }

    //文件下载
    public void export() throws Exception {
        HttpServletResponse response = ResponseContextUtil.getResponse();
        //文件下载响应头
        response.setHeader("Content-Disposition", "attachment;filename=employee.xls");
        //创建xls文件
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        //创建工作簿
        WritableSheet sheet = workbook.createSheet("day01", 0);
        //给文件添加标题
        sheet.addCell(new Label(0, 0, "姓名"));
        sheet.addCell(new Label(1, 0, "真实姓名"));
        sheet.addCell(new Label(2, 0, "电话"));
        sheet.addCell(new Label(3, 0, "邮箱"));
        sheet.addCell(new Label(4, 0, "部门"));
        sheet.addCell(new Label(5, 0, "入职时间"));
        sheet.addCell(new Label(6, 0, "超级管理员"));
        sheet.addCell(new Label(7, 0, "状态"));
        //获取真实的员工数据
        List<Employee> employees = employeeMapper.selectAll();
        //将日期进行格式化操作
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            //往工作簿表格中添加数据
            if (employee.getUsername() != null && employee.getUsername() != "") {
                sheet.addCell(new Label(0, i + 1, employee.getUsername()));
            } else {
                sheet.addCell(new Label(0, i + 1, null));
            }

            if (employee.getRealname() != null && employee.getRealname() != "") {
                sheet.addCell(new Label(1, i + 1, employee.getRealname()));
            } else {
                sheet.addCell(new Label(1, i + 1, null));
            }

            if (employee.getTel() != null && employee.getTel() != "") {
                sheet.addCell(new Label(2, i + 1, employee.getTel()));
            } else {
                sheet.addCell(new Label(2, i + 1, null));
            }

            if (employee.getEmail() != null && employee.getEmail() != "") {
                sheet.addCell(new Label(3, i + 1, employee.getEmail()));
            } else {
                sheet.addCell(new Label(3, i + 1, null));
            }

            if (employee.getDept() != null) {
                sheet.addCell(new Label(4, i + 1, employee.getDept().getName()));
            } else {
                sheet.addCell(new Label(4, i + 1, null));
            }

            if (employee.getInputtime() != null) {
                sheet.addCell(new Label(5, i + 1, dateFormat.format(employee.getInputtime())));
            } else {
                sheet.addCell(new Label(5, i + 1, null));
            }

            if (employee.getAdmin()) {
                sheet.addCell(new Label(6, i + 1, "是"));
            } else {
                sheet.addCell(new Label(6, i + 1, "否"));
            }

            if (employee.isState()) {
                sheet.addCell(new Label(7, i + 1, "在职"));
            } else {
                sheet.addCell(new Label(7, i + 1, "离职"));
            }
        }
        //写入数据
        workbook.write();
        //关闭资源
        workbook.close();
    }

    //文件上传
    public void input(MultipartFile file) throws Exception {
        //读取xls文件
        Workbook workbook = Workbook.getWorkbook(file.getInputStream());
        //读取某个工作簿
        Sheet sheet = workbook.getSheet(0);
        //获取总行数
        int rows = sheet.getRows();
        //将日期进行格式化操作
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 1; i < rows; i++) {
            //创建一个员工对象将上传文件的数据添加到数据库中
            Employee employee = new Employee();
            //获取文件每一行内容
            employee.setUsername(sheet.getCell(0, i).getContents());
            employee.setRealname(sheet.getCell(1, i).getContents());
            employee.setTel(sheet.getCell(2, i).getContents());
            employee.setEmail(sheet.getCell(3, i).getContents());
            //根据部门的名称去查询部门信息封装成部门对象返回
            String deptName = sheet.getCell(4, i).getContents();
            Department dept = departmentMapper.selectDept(deptName);
            if (dept != null) {
                employee.setDept(dept);
            }
            employee.setInputtime(dateFormat.parse(sheet.getCell(5, i).getContents()));
            if (sheet.getCell(6, i).getContents().equals("是")) {
                employee.setAdmin(true);
            } else {
                employee.setAdmin(false);
            }
            if (sheet.getCell(7, i).getContents().equals("在职")) {
                employee.setState(true);
            } else {
                employee.setState(false);
            }
            employeeMapper.insert(employee);
        }
        workbook.close();
    }

    @Override
    public Employee getEmployeeByUsername(String principal) {
        return employeeMapper.getEmployeeByUsername(principal);
    }

    @Override
    public List<Employee> getTeacherByRoleId(String sn) {
        return employeeMapper.getTeacherByRoleId(sn);
    }
}
