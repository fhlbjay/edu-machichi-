package cn.wolfcode.crm.service;

import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * 需求:
 * Created by khalil on 2017/12/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class ChangeClassServiceTest {
    @Autowired
    private IChangeClassService changeClassService;

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

        PageResult result = changeClassService.query(qo);
        System.out.println(result.getRows());
    }
}