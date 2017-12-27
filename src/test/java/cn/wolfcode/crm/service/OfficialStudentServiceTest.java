package cn.wolfcode.crm.service;

import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 需求:
 * Created by khalil on 2017/12/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class OfficialStudentServiceTest {
    @Autowired
    private IOfficialStudentService officialStudentService;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void query() {
        QueryObject qo = new QueryObject();
        qo.setPage(1);
        qo.setRows(10);

        PageResult result = officialStudentService.query(qo);
        System.out.println(result.getRows());

    }


    @Test
    public void changeState() {
    }

    @Test
    public void export() {
    }
}