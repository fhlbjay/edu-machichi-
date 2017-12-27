package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.ClassGrade;
import cn.wolfcode.crm.domain.ClassRoom;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Schedule;
import cn.wolfcode.crm.mapper.ClassGradeMapper;
import cn.wolfcode.crm.mapper.ClassRoomMapper;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import cn.wolfcode.crm.mapper.ScheduleMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.query.ScheduleQueryObject;
import cn.wolfcode.crm.service.IScheduleService;
import cn.wolfcode.crm.util.HandleDateUtil;
import cn.wolfcode.crm.util.ResponseContextUtil;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Service
public class ScheduleServiceImpl implements IScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;
    //班级
    @Autowired
    private ClassGradeMapper classGradeMapper;
    //老师/班主任
    @Autowired
    private EmployeeMapper employeeMapper;
    //教室
    @Autowired
    private ClassRoomMapper classRoomMapper;

    public int insert(Schedule record) {
        scheduleMapper.insert(record);
        return 0;
    }

    public int updateByPrimaryKey(Schedule record) {
        scheduleMapper.updateByPrimaryKey(record);
        return 0;
    }


    public int deleteByPrimaryKey(Long id) {
        scheduleMapper.deleteByPrimaryKey(id);
        return 0;
    }

    public Schedule selectByPrimaryKey(Long id) {
        Schedule schedule = scheduleMapper.selectByPrimaryKey(id);
        return schedule;
    }

    public List<Schedule> selectAll() {
        List<Schedule> schedules = scheduleMapper.selectAll();
        return schedules;
    }


    //分页
    public PageResult query(QueryObject qo) {
        //判断满足条件的总数
        int total = scheduleMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(total, Collections.emptyList());
        }
        List<Schedule> rows = scheduleMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    @Override
    public List<Schedule> selectcurrentDaySchedual(ScheduleQueryObject qo) {
        List<Schedule> schedules = scheduleMapper.selectcurrentDaySchedual(qo);
        return schedules;
    }

    @Override
    public void export() throws Exception {
        HttpServletResponse response = ResponseContextUtil.getResponse();
        //文件下载响应头
        String fileName = new String("课程表".getBytes("utf-8"), "ISO-8859-1");
        response.setHeader("Content-Disposition", (new StringBuilder()).append("attachment;filename=").append(fileName).append(".xls").toString());
        //创建xls文件
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        //创建工作簿
        WritableSheet sheet = workbook.createSheet("day01", 0);
        //给文件添加标题
        sheet.addCell(new Label(0, 0, "日期"));
        sheet.addCell(new Label(1, 0, "星期"));
        sheet.addCell(new Label(2, 0, "班级"));
        sheet.addCell(new Label(3, 0, "课程"));
        sheet.addCell(new Label(4, 0, "上课老师"));
        sheet.addCell(new Label(5, 0, "班主任"));
        sheet.addCell(new Label(6, 0, "教室"));
        sheet.addCell(new Label(7, 0, "备注"));
        sheet.addCell(new Label(8, 0, "课表状态"));
        //获取真实的课表数据
        List<Schedule> schedules = scheduleMapper.downLoadSchedule();
        //将日期进行格式化操作
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < schedules.size(); i++) {
            Schedule schedule = schedules.get(i);
            //往工作簿表格中添加数据
            if (schedule.getSubDate() != null) {
                sheet.addCell(new Label(0, i + 1, dateFormat.format(schedule.getSubDate())));
            } else {
                sheet.addCell(new Label(0, i + 1, null));
            }
            if (schedule.getSubDate() != null) {
                sheet.addCell(new Label(1, i + 1, HandleDateUtil.getWeek(schedule.getSubDate())));
            } else {
                sheet.addCell(new Label(1, i + 1, null));
            }
            if (schedule.getClassGrade() != null) {
                sheet.addCell(new Label(2, i + 1, schedule.getClassGrade().getName()));
            } else {
                sheet.addCell(new Label(2, i + 1, null));
            }
            if (schedule.getSubject() != null && schedule.getSubject() != "") {
                sheet.addCell(new Label(3, i + 1, schedule.getSubject()));
            } else {
                sheet.addCell(new Label(3, i + 1, null));
            }
            if (schedule.getTeacher() != null) {
                sheet.addCell(new Label(4, i + 1, schedule.getTeacher().getUsername()));
            } else {
                sheet.addCell(new Label(4, i + 1, null));
            }
            if (schedule.getClassTeacher() != null) {
                sheet.addCell(new Label(5, i + 1, schedule.getClassTeacher().getUsername()));
            } else {
                sheet.addCell(new Label(5, i + 1, null));
            }
            if (schedule.getClassRoom() != null) {
                sheet.addCell(new Label(6, i + 1, schedule.getClassRoom().getName()));
            } else {
                sheet.addCell(new Label(6, i + 1, null));
            }
            if (schedule.getRemark() != null && schedule.getRemark()  != "") {
                sheet.addCell(new Label(7, i + 1, schedule.getRemark()));
            } else {
                sheet.addCell(new Label(7, i + 1, null));
            }
            if (schedule.getSubDate() != null) {
                sheet.addCell(new Label(8, i + 1, HandleDateUtil.getState(schedule.getSubDate())));
            } else {
                sheet.addCell(new Label(8, i + 1, null));
            }
        }
        //写入数据
        workbook.write();
        //关闭资源
        workbook.close();
    }

    @Override
    public void downScheduleModal() throws Exception {
        HttpServletResponse response = ResponseContextUtil.getResponse();
        String fileName = new String("课程表模板".getBytes("utf-8"), "ISO-8859-1");
        response.setHeader("Content-Disposition", (new StringBuilder()).append("attachment;filename=").append(fileName).append(".xls").toString());
        WritableWorkbook workBook = Workbook.createWorkbook(response.getOutputStream());
        WritableSheet sheet = workBook.createSheet("工资表模板", 0);
        sheet.addCell(new Label(1, 0, "请注意:所有内容必须按照第一行提供的样式填写,不能有错别字,时间格式必须按照yyyy-MM-dd的格式填写!"));
        sheet.addCell(new Label(2, 1, "请严格遵守以上规范,否则会导致工资表上传失败!或者数据丢失!格式不能修改!"));
        sheet.addCell(new Label(1, 3, "填写模板如右:"));
        sheet.addCell(new Label(2, 3, "日期"));
        sheet.addCell(new Label(3, 3, "星期"));
        sheet.addCell(new Label(4, 3, "班级"));
        sheet.addCell(new Label(5, 3, "课程"));
        sheet.addCell(new Label(6, 3, "上课老师"));
        sheet.addCell(new Label(7, 3, "班主任"));
        sheet.addCell(new Label(8, 3, "教室"));
        sheet.addCell(new Label(9, 3, "备注"));
        sheet.addCell(new Label(10, 3, "课表状态"));

        sheet.addCell(new Label(2, 4, "2017-11-01"));
        sheet.addCell(new Label(3, 4, "星期一"));
        sheet.addCell(new Label(4, 4, "上海Java二期"));
        sheet.addCell(new Label(5, 4, "Java"));
        sheet.addCell(new Label(6, 4, "陈惠"));
        sheet.addCell(new Label(7, 4, "黄庆楠"));
        sheet.addCell(new Label(8, 4, "Java-2H"));
        sheet.addCell(new Label(9, 4, "叩叮狼"));
        sheet.addCell(new Label(10, 4, "已上/未上"));
        workBook.write();
        workBook.close();
    }

    //上传信息
    @Override
    public void input(MultipartFile file) throws Exception {
        //读取xls文件
        Workbook workbook = Workbook.getWorkbook(file.getInputStream());
        //读取某个工作簿
        Sheet sheet = workbook.getSheet(0);
        //获取总行数
        int rows = sheet.getRows();
        //将日期进行格式化操作
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 5; i < rows; i++) {
            //创建一个课表对象将上传文件的数据添加到数据库中
            Schedule schedule = new Schedule();
            //获取文件每一行内容
            schedule.setSubDate(dateFormat.parse(sheet.getCell(2, i).getContents()));
            //根据班级的名称去查询班级信息封装成班级对象返回
            String classGradeName = sheet.getCell(4, i).getContents();
            ClassGrade classGrade = classGradeMapper.selectclassGrade(classGradeName);
            if (classGrade != null) {
                schedule.setClassGrade(classGrade);
            }
            schedule.setSubject(sheet.getCell(5, i).getContents());
            //根据老师的名称去查询老师信息封装成老师对象返回
            String teacherName = sheet.getCell(6, i).getContents();
            Employee teacher = employeeMapper.selectTeacher(teacherName);
            if (teacher != null) {
                schedule.setTeacher(teacher);
            }
           /* //根据班主任的名称去查询班主任信息封装成班主任对象返回
            String classTeacherName = sheet.getCell(7, i).getContents();
            System.out.println(classTeacherName);
            Employee classTeacher = employeeMapper.selectClassTeacher(classTeacherName);
            if (classTeacher != null) {
                schedule.setClassTeacher(classTeacher);
            }*/
            //根据教室的名称去查询班教室信息封装成班教室对象返回
            String classRoomName = sheet.getCell(8, i).getContents();
            ClassRoom classRoom = classRoomMapper.selectClassRoom(classRoomName);
            if (classRoom != null) {
                schedule.setClassRoom(classRoom);
            }
            schedule.setRemark(sheet.getCell(9, i).getContents());
            scheduleMapper.insert(schedule);
        }
        workbook.close();
    }
}
