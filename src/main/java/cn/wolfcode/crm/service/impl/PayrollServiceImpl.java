package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.*;
import cn.wolfcode.crm.mapper.DepartmentMapper;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import cn.wolfcode.crm.mapper.PayrollMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPayrollService;
import cn.wolfcode.crm.util.ResponseContextUtil;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class PayrollServiceImpl implements IPayrollService {
    @Autowired
    private PayrollMapper payrollMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    public int insert(Payroll record) {
        payrollMapper.insert(record);
        return 0;
    }

    public int updateByPrimaryKey(Payroll record) {
        payrollMapper.updateByPrimaryKey(record);
        return 0;
    }


    public int deleteByPrimaryKey(Long id) {
        payrollMapper.deleteByPrimaryKey(id);
        return 0;
    }

    public Payroll selectByPrimaryKey(Long id) {
        Payroll payroll = payrollMapper.selectByPrimaryKey(id);
        return payroll;
    }

    public List<Payroll> selectAll() {
        List<Payroll> payrolls = payrollMapper.selectAll();
        return payrolls;
    }


    //分页
    public PageResult query(QueryObject qo) {
        //判断满足条件的总数
        int total = payrollMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(total, Collections.emptyList());
        }
        List<Payroll> rows = payrollMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    @Override
    public void downPayrollModal() throws Exception {
        HttpServletResponse response = ResponseContextUtil.getResponse();
        String fileName = new String("工资表模板".getBytes("utf-8"), "ISO-8859-1");
        response.setHeader("Content-Disposition", (new StringBuilder()).append("attachment;filename=").append(fileName).append(".xls").toString());
        WritableWorkbook workBook = Workbook.createWorkbook(response.getOutputStream());
        WritableSheet sheet = workBook.createSheet("工资表模板", 0);
        sheet.addCell(new Label(2, 0, "请注意:所有内容必须按照第一行提供的样式填写,不能有错别字,月份天数格式必须按照yyyy-MM-dd的格式填写!支付天数格式必须是yyyy-MM-dd HH:mm:ss!!!"));
        sheet.addCell(new Label(3, 1, "请严格遵守以上规范,否则会导致工资表上传失败!或者数据丢失!格式不能进行修改!公司规章制度严厉!特别对时间!"));
        sheet.addCell(new Label(2, 2, "实际工作天数=(应工作天数-迟到天数-早退天数)   实际工资=(基本工资+奖金)   应发工资=(基本工资+奖金)-(迟到天数+早退天数)*50"));

        sheet.addCell(new Label(1, 4, "填写模板如右:"));

        sheet.addCell(new Label(2, 4, "员工编号"));
        sheet.addCell(new Label(3, 4, "员工名称"));
        sheet.addCell(new Label(4, 4, "所在部门"));
        sheet.addCell(new Label(5, 4, "工资月份"));
        sheet.addCell(new Label(6, 4, "应工作天数"));
        sheet.addCell(new Label(7, 4, "迟到天数"));
        sheet.addCell(new Label(8, 4, "早退天数"));
        sheet.addCell(new Label(9, 4, "实际工作天数"));
        sheet.addCell(new Label(10, 4, "基本薪水"));
        sheet.addCell(new Label(11, 4, "奖金"));
        sheet.addCell(new Label(12, 4, "实际薪水"));
        sheet.addCell(new Label(13, 4, "应发工资"));
        sheet.addCell(new Label(14, 4, "支付时间"));

        sheet.addCell(new Label(2, 5, "1"));
        sheet.addCell(new Label(3, 5, "小一"));
        sheet.addCell(new Label(4, 5, "开发部"));
        sheet.addCell(new Label(5, 5, "2017-12"));
        sheet.addCell(new Label(6, 5, "30"));
        sheet.addCell(new Label(7, 5, "0"));
        sheet.addCell(new Label(8, 5, "0"));
        sheet.addCell(new Label(9, 5, "30"));
        sheet.addCell(new Label(10, 5, "3000"));
        sheet.addCell(new Label(11, 5, "300"));
        sheet.addCell(new Label(12, 5, "15000"));
        sheet.addCell(new Label(13, 5, "15000"));
        sheet.addCell(new Label(14, 5, "2017-03-18 16:32:45"));
        workBook.write();
        workBook.close();
    }

    @Override
    public Defray checkAccount(Payroll payroll) {
        //获取到应工作天数,迟到天数,早退天数,实际工作天数,基本工资和奖金
        Integer workDay = payroll.getWorkDay();
        Integer afterDay = payroll.getAfterDay();
        Integer befterDay = payroll.getBefterDay();
        BigDecimal basicSalary = payroll.getBasicSalary();
        BigDecimal reword = payroll.getReword();
        //实际工作天数=(应工作天数-迟到天数-早退天数)
        Integer actualWorkDay = workDay - afterDay - befterDay;
        //计算实际工资和应发工资,将其设置到Payroll
        //将应工作时间转换
        BigDecimal workDays = new BigDecimal(workDay);
        //将实际工作时间转换(Integer类型转换为BigDecimal类型)
        BigDecimal actualWorkDays = new BigDecimal(actualWorkDay);
        //实际工资=(基本工资+奖金)
        BigDecimal salary = (basicSalary.add(reword));
        //System.out.println(salary.toString());
        //应发工资=(基本工资+奖金)-(迟到天数+早退天数)*50
        BigDecimal endSalary = salary.subtract(workDays.subtract(actualWorkDays).multiply(new BigDecimal(50)));
        // System.out.println(endSalary.toString());
        //为实际工资,应发工资,实际工作天数赋值
        payroll.setActualWorkDay(actualWorkDay);
        payroll.setSalary(salary);
        payroll.setEndSalary(endSalary);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        //String payTime = dateFormat.format(date);
        //设置核算时间
        payroll.setPayTime(date);
        payrollMapper.checkAccount(payroll);

        Defray defray = new Defray();
        defray.setDefrayTime(new Date());
        defray.setDefrayMoney(endSalary);
        defray.setRemark("员工工资支出");
        defray.setApplicant(payroll.getEmployee());
        Subject subject = SecurityUtils.getSubject();
        Employee operator = (Employee) subject.getPrincipal();
        defray.setHandMan(operator);
        SystemDictionaryItem paymentType = new SystemDictionaryItem();
        paymentType.setId(18L);
        defray.setPaymentType(paymentType);
        SystemDictionaryItem defrayType = new SystemDictionaryItem();
        defrayType.setId(110L);
        defray.setDefrayType(defrayType);
        return defray;
    }

    @Override
    public boolean reviseEmpSalaryInformation(String managerName, String managerPassword) {
        //对密码进行加密
        managerPassword = new Md5Hash(managerPassword, managerName, 2).toString();
        //根据角色sn去查看哪一个员工是超级管理员
        Employee superman = (Employee) employeeMapper.getMangerByRoleSn("SUPERMAN");
        if (!StringUtils.isEmpty(managerName) && !StringUtils.isEmpty(managerPassword) && superman != null) {
            if (superman.getUsername().equals(managerName) && superman.getPassword().equals(managerPassword)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //导出工资表
    public void export() throws Exception {
        HttpServletResponse response = ResponseContextUtil.getResponse();
        //文件下载响应头
        String fileName = new String("工资表".getBytes("utf-8"), "ISO-8859-1");
        response.setHeader("Content-Disposition", (new StringBuilder()).append("attachment;filename=").append(fileName).append(".xls").toString());
        //创建xls文件
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        //创建工作簿
        WritableSheet sheet = workbook.createSheet("day01", 0);
        sheet.addCell(new Label(5, 0, "叩叮狼教育科技公司工资表详情"));
        //给文件添加标题
        sheet.addCell(new Label(2, 2, "员工编号"));
        sheet.addCell(new Label(3, 2, "员工名称"));
        sheet.addCell(new Label(4, 2, "所在部门"));
        sheet.addCell(new Label(5, 2, "工资月份"));
        sheet.addCell(new Label(6, 2, "应工作天数"));
        sheet.addCell(new Label(7, 2, "迟到天数"));
        sheet.addCell(new Label(8, 2, "早退天数"));
        sheet.addCell(new Label(9, 2, "实际工作天数"));
        sheet.addCell(new Label(10, 2, "基本薪水"));
        sheet.addCell(new Label(11, 2, "奖金"));
        sheet.addCell(new Label(12, 2, "实际薪水"));
        sheet.addCell(new Label(13, 2, "应发工资"));
        sheet.addCell(new Label(14, 2, "支付时间"));
        //获取真实的课表数据
        List<Payroll> payrolls = payrollMapper.selectAll();
        //将日期进行格式化操作
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < payrolls.size(); i++) {
            Payroll payroll = payrolls.get(i);
            //部门查询
            Department department = payroll.getDepartment();
            Employee employee = payroll.getEmployee();
            if (employee != null) {
                //往工作簿表格中添加数据
                if (employee.getId() != null) {
                    sheet.addCell(new Label(2, i + 3, employee.getId().toString()));
                } else {
                    sheet.addCell(new Label(2, i + 3, null));
                }
                if (employee.getUsername() != null && employee.getUsername() != "") {
                    sheet.addCell(new Label(3, i + 3, employee.getUsername()));
                } else {
                    sheet.addCell(new Label(3, i + 3, null));
                }
            }
            if (department != null) {
                if (department.getName() != null && department.getName() != "") {
                    sheet.addCell(new Label(4, i + 3, department.getName()));
                } else {
                    sheet.addCell(new Label(4, i + 3, null));
                }
            }

            if (payroll.getMonth() != null) {
                sheet.addCell(new Label(5, i + 3, dateFormat.format(payroll.getMonth())));
            } else {
                sheet.addCell(new Label(5, i + 3, null));
            }

            if (payroll.getWorkDay() != null) {
                sheet.addCell(new Label(6, i + 3, payroll.getWorkDay().toString()));
            } else {
                sheet.addCell(new Label(6, i + 3, null));
            }
            if (payroll.getAfterDay() != null) {
                sheet.addCell(new Label(7, i + 3, payroll.getAfterDay().toString()));
            } else {
                sheet.addCell(new Label(7, i + 3, null));
            }
            if (payroll.getBefterDay() != null) {
                sheet.addCell(new Label(8, i + 3, payroll.getBefterDay().toString()));
            } else {
                sheet.addCell(new Label(8, i + 3, null));
            }
            if (payroll.getActualWorkDay() != null) {
                sheet.addCell(new Label(9, i + 3, payroll.getActualWorkDay().toString()));
            } else {
                sheet.addCell(new Label(9, i + 3, null));
            }
            if (payroll.getBasicSalary() != null) {
                sheet.addCell(new Label(10, i + 3, payroll.getBasicSalary().toString()));
            } else {
                sheet.addCell(new Label(10, i + 3, null));
            }

            if (payroll.getReword() != null) {
                sheet.addCell(new Label(11, i + 3, payroll.getReword().toString()));
            } else {
                sheet.addCell(new Label(11, i + 3, null));
            }
            if (payroll.getSalary() != null) {
                sheet.addCell(new Label(12, i + 3, payroll.getSalary().toString()));
            } else {
                sheet.addCell(new Label(12, i + 3, null));
            }
            if (payroll.getEndSalary() != null) {
                sheet.addCell(new Label(13, i + 3, payroll.getEndSalary().toString()));
            } else {
                sheet.addCell(new Label(13, i + 3, null));
            }
            if (payroll.getPayTime() != null) {
                sheet.addCell(new Label(14, i + 3, dateFormat1.format(payroll.getPayTime())));
            } else {
                sheet.addCell(new Label(14, i + 3, null));
            }
        }
        //写入数据
        workbook.write();
        //关闭资源
        workbook.close();
    }

    //上传工资表
    public void input(MultipartFile file) throws Exception {
        //读取xls文件
        Workbook workbook = Workbook.getWorkbook(file.getInputStream());
        //读取某个工作簿
        Sheet sheet = workbook.getSheet(0);
        //获取总行数
        int rows = sheet.getRows();
        //将日期进行格式化操作
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 6; i < rows; i++) {
            //创建一个工资表对象将上传文件的数据添加到数据库中
            Payroll payroll = new Payroll();
            //获取文件每一行内容
            String emp = sheet.getCell(2, i).getContents();
            if (emp != null) {
                Employee employee = employeeMapper.selectByPrimaryKey(Long.parseLong(emp));
                payroll.setEmployee(employee);
            }

            String deptName = sheet.getCell(4, i).getContents();
            if(deptName!=null){
                Department dept =  departmentMapper.getDepartmentByDeptName(deptName);
                payroll.setDepartment(dept);
            }

            String mon1 = sheet.getCell(5, i).getContents();
            if (!StringUtils.isEmpty(mon1)) {
                Date month = dateFormat.parse(mon1);
                payroll.setMonth(month);
            }


            String day4 = sheet.getCell(6, i).getContents();
            if (!StringUtils.isEmpty(day4)) {
                int workDay = Integer.valueOf(day4);
                payroll.setWorkDay(workDay);
            }


            String day3 = sheet.getCell(7, i).getContents();
            if (!StringUtils.isEmpty(day3)) {
                int afterDay = Integer.valueOf(day3);
                payroll.setAfterDay(afterDay);
            }


            String day2 = sheet.getCell(8, i).getContents();
            if (!StringUtils.isEmpty(day2)) {
                Integer befterDay = Integer.valueOf(day2);
                payroll.setBefterDay(befterDay);
            }


            String day1 = sheet.getCell(9, i).getContents();
            if (!StringUtils.isEmpty(day1)) {
                Integer actualWorkDay = Integer.valueOf(day1);
                payroll.setActualWorkDay(actualWorkDay);
            }

            String salary11 = sheet.getCell(10, i).getContents();
            if (!StringUtils.isEmpty(salary11)) {
                BigDecimal basicSalary = new BigDecimal(salary11);
                payroll.setBasicSalary(basicSalary);
            }

            String salary12 = sheet.getCell(11, i).getContents();
            if (!StringUtils.isEmpty(salary12)) {
                BigDecimal reword = new BigDecimal(salary12);
                payroll.setReword(reword);
            }

            String salary13 = sheet.getCell(12, i).getContents();
            if (!StringUtils.isEmpty(salary13)) {
                BigDecimal salary = new BigDecimal(salary13);
                payroll.setSalary(salary);
            }

            String salary14 = sheet.getCell(13, i).getContents();
            if (!StringUtils.isEmpty(salary14)) {
                BigDecimal endSalary = new BigDecimal(salary14);
                payroll.setEndSalary(endSalary);
            }

            String salary15 = sheet.getCell(14, i).getContents();
            System.out.println(salary15);
            if (!StringUtils.isEmpty(salary15)) {
                Date payTime = dateFormat1.parse(salary15);
                payroll.setPayTime(payTime);
            }
            payrollMapper.insert(payroll);
        }
        workbook.close();
    }
}
