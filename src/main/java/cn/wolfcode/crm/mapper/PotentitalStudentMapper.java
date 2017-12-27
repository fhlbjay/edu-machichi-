package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.PotentitalCustomerPool;
import cn.wolfcode.crm.domain.PotentitalStudent;
import cn.wolfcode.crm.query.QueryForPotentitalStudent;

import java.util.List;
import java.util.Map;

public interface PotentitalStudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PotentitalStudent record);

    PotentitalStudent selectByPrimaryKey(Long id);

    List<PotentitalStudent> selectAll();

    int updateByPrimaryKey(PotentitalStudent record);
    //更新正式学员页面修改的部分属性
    int updatebaseInfoByPrimaryKey(PotentitalStudent record);


    Integer queryForCount(QueryForPotentitalStudent qs);
    List<PotentitalStudent> queryForList(QueryForPotentitalStudent qs);
    int track(PotentitalStudent record);
    void changeEmployee(Long id);
    void insertPotentitalCustomerPool(PotentitalCustomerPool pcp);

    void insertTrackStudent(PotentitalStudent student);

    void change2official(Long id);

    List<Map<String,Object>> selectAllSn();
}
