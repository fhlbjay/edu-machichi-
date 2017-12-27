import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.WastageStudent;
import cn.wolfcode.crm.mapper.WastageStudentMapper;
import cn.wolfcode.crm.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class AllTest {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private WastageStudentMapper wastageStudentMapper;

    @Test
    public void employeeTest() throws Exception {
        Department dept = new Department();
        dept.setId(1l);
        Employee emp = new Employee();
        emp.setEmail("1111");
        emp.setPassword("1111");
        emp.setState(true);
        emp.setRealname("小一");
        emp.setUsername("追梦");
        emp.setDept(dept);
        emp.setTel("1111111111111111");
        emp.setInputtime(new Date());
        //employeeService.insert(emp);
    }
    @Test
    public void testDate() throws  Exception{
        /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String[] day = new String[] { "日", "一", "二", "三", "四", "五", "六" };
        Date d = dateFormat.parse("2017-12-23");// 把字符串转化成日期
        System.out.println("星期" + day[d.getDay()]);*/
        //String[] day = new String[]{"日", "一", "二", "三", "四", "五", "六"};
        //Date d = dateFormat.parse("2017-12-23");// 把字符串转化成日期
        Date date = new Date();
        System.out.println(date);
        //System.out.println("星期" + day[date1.getDay()]);
    }

    @Test
    public void testD2() {
        List<WastageStudent> wastageStudents = wastageStudentMapper.selectAll();
        System.out.println(wastageStudents);
    }
}
