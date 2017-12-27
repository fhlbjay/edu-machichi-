package cn.wolfcode.crm.service.impl;


import cn.wolfcode.crm.domain.PotentitalCustomerPool;
import cn.wolfcode.crm.domain.PotentitalStudent;
import cn.wolfcode.crm.mapper.PotentitalCustomerPoolMapper;
import cn.wolfcode.crm.mapper.PotentitalStudentMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryForPotentitalStudent;
import cn.wolfcode.crm.service.IPotentitalStudentService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by joy on 2017/12/21.
 */
@Service
public class PotentitalStudentServiceImpl implements IPotentitalStudentService {
    @Autowired
    PotentitalStudentMapper mapper;
    @Autowired
    PotentitalCustomerPoolMapper customerPoolMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(PotentitalStudent record) {
        mapper.insert(record);
    }

    @Override
    public PotentitalStudent selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PotentitalStudent> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(PotentitalStudent record) {
        mapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryForPotentitalStudent qs) {
        Integer total = mapper.queryForCount(qs);
        if (total == 0) {
            return new PageResult(total, Collections.emptyList());
        }
        List<PotentitalStudent> list = mapper.queryForList(qs);
        PageResult result = new PageResult(total, list);
        return result;
    }

    public void export(HttpServletResponse response) throws Exception {
        //文件下载响应头
        response.setHeader("Content-Disposition", "attachment;filename=potentitalstudent.xls");
        //创建xls文件
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        //创建工作簿
        WritableSheet sheet = workbook.createSheet("day01", 0);
        //给文件添加标题
        sheet.addCell(new Label(0, 0, "姓名"));
        sheet.addCell(new Label(1, 0, "营销人员"));
        sheet.addCell(new Label(2, 0, "跟踪次数"));
        sheet.addCell(new Label(3, 0, "最后跟踪时间"));
        sheet.addCell(new Label(4, 0, "约访时间"));
        sheet.addCell(new Label(5, 0, "下次跟进时间"));
        sheet.addCell(new Label(6, 0, "QQ"));
        sheet.addCell(new Label(7, 0, "电话"));
        sheet.addCell(new Label(8, 0, "学校"));
        sheet.addCell(new Label(9, 0, "意向程度"));
        sheet.addCell(new Label(10, 0, "意向校区"));
        sheet.addCell(new Label(11, 0, "意向班级"));
        sheet.addCell(new Label(12, 0, "状态"));
        sheet.addCell(new Label(13, 0, "是否跟踪"));
        sheet.addCell(new Label(14, 0, "是否备注"));
        //获取真实的员工数据
        List<PotentitalStudent> potentitalStudents = mapper.selectAll();
        //将日期进行格式化操作
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < potentitalStudents.size(); i++) {
            PotentitalStudent potentitalStudent = potentitalStudents.get(i);
            //往工作簿表格中添加数据
            if (potentitalStudent.getName() != null) {
                sheet.addCell(new Label(0, i + 1, potentitalStudent.getName()));
            } else {
                sheet.addCell(new Label(0, i + 1, null));
            }

            if (potentitalStudent.getEmployee() != null) {
                sheet.addCell(new Label(1, i + 1, potentitalStudent.getEmployee().getUsername()));
            } else {
                sheet.addCell(new Label(1, i + 1, null));
            }

            if (potentitalStudent.getTrackTimes() != null) {
                sheet.addCell(new Label(2, i + 1, potentitalStudent.getTrackTimes().toString()));
            } else {
                sheet.addCell(new Label(2, i + 1, null));
            }

            if (potentitalStudent.getPrevDate() != null) {
                sheet.addCell(new Label(3, i + 1, dateFormat.format(potentitalStudent.getPrevDate())));
            } else {
                sheet.addCell(new Label(3, i + 1, null));
            }

            if (potentitalStudent.getVisitDate() != null) {
                sheet.addCell(new Label(4, i + 1, dateFormat.format(potentitalStudent.getVisitDate())));
            } else {
                sheet.addCell(new Label(4, i + 1, null));
            }

            if (potentitalStudent.getNextDate() != null) {
                sheet.addCell(new Label(5, i + 1, dateFormat.format(potentitalStudent.getNextDate())));
            } else {
                sheet.addCell(new Label(5, i + 1, null));
            }

            if (potentitalStudent.getQq() != null) {
                sheet.addCell(new Label(6, i + 1, potentitalStudent.getQq()));
            } else {
                sheet.addCell(new Label(6, i + 1, null));
            }

            if (potentitalStudent.getTel() != null) {
                sheet.addCell(new Label(7, i + 1, potentitalStudent.getTel()));
            } else {
                sheet.addCell(new Label(7, i + 1, null));
            }
            if (potentitalStudent.getSchool() != null) {
                sheet.addCell(new Label(8, i + 1, potentitalStudent.getSchool()));
            } else {
                sheet.addCell(new Label(8, i + 1, null));
            }
            if (potentitalStudent.getIntention() != null) {
                sheet.addCell(new Label(9, i + 1, potentitalStudent.getIntention().getName()));
            } else {
                sheet.addCell(new Label(9, i + 1, null));
            }
            if (potentitalStudent.getCampus() != null) {
                sheet.addCell(new Label(10, i + 1, potentitalStudent.getCampus().getName()));
            } else {
                sheet.addCell(new Label(10, i + 1, null));
            }
            if (potentitalStudent.getClassGrade() != null) {
                sheet.addCell(new Label(11, i + 1, potentitalStudent.getClassGrade().getName()));
            } else {
                sheet.addCell(new Label(11, i + 1, null));
            }
            if (potentitalStudent.getStatus() != null) {
                sheet.addCell(new Label(12, i + 1, potentitalStudent.getStatus().getName()));
            } else {
                sheet.addCell(new Label(12, i + 1, null));
            }
            if (potentitalStudent.getTrackState()!=null && potentitalStudent.getTrackState()) {
                sheet.addCell(new Label(13, i + 1, "已跟踪"));
            } else {
                sheet.addCell(new Label(13, i + 1, "未跟踪"));
            }
            if (potentitalStudent.getRemark() != null) {
                sheet.addCell(new Label(14, i + 1, potentitalStudent.getRemark()));
            } else {
                sheet.addCell(new Label(14, i + 1, "未跟踪"));
            }
        }
        //写入数据
        workbook.write();
        //关闭资源
        workbook.close();
    }

    @Override
    public void track(PotentitalStudent record) {
        mapper.track(record);
    }

    @Override
    public void changeEmployee(Long id) {
        mapper.changeEmployee(id);
    }

    @Override
    public void insertPotentitalCustomerPool(PotentitalCustomerPool pcp) {
        mapper.insertPotentitalCustomerPool(pcp);
    }

    @Override
    public void insertTrackStudent(PotentitalStudent student) {
        mapper.insertTrackStudent(student);
    }

    @Override
    public List<Map<String, Object>> selectAllSn() {
        List<Map<String, Object>> maps = mapper.selectAllSn();
        List<Map<String, Object>> maps1 = customerPoolMapper.selectAllSn();
        for (Map<String, Object> map : maps1) {
            maps.add(map);
        }
        return maps;
    }

}
