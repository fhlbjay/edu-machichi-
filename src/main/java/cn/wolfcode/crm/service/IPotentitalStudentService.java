package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.PotentitalCustomerPool;
import cn.wolfcode.crm.domain.PotentitalStudent;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryForPotentitalStudent;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by joy on 2017/12/21.
 */
public interface IPotentitalStudentService {
    void deleteByPrimaryKey(Long id);

    void insert(PotentitalStudent record);

    PotentitalStudent selectByPrimaryKey(Long id);

    List<PotentitalStudent> selectAll();

    void updateByPrimaryKey(PotentitalStudent record);

   PageResult query(QueryForPotentitalStudent qs);

    void export(HttpServletResponse response) throws Exception;
    void track(PotentitalStudent record);
    void changeEmployee(Long id);
    void insertPotentitalCustomerPool(PotentitalCustomerPool pcp);
    void insertTrackStudent(PotentitalStudent student);
    List<Map<String,Object>> selectAllSn();

}
