package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.PotentitalCustomerPool;
import cn.wolfcode.crm.domain.PotentitalStudent;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PotentitalCustomerPoolMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PotentitalCustomerPool record);
    void insertPotentitalStudent(PotentitalStudent ps);
    PotentitalCustomerPool selectByPrimaryKey(Long id);

    List<PotentitalCustomerPool> selectAll();

    int updateByPrimaryKey(PotentitalCustomerPool record);
    void changeEmployee(@Param("eId") Long eId,@Param("id") Long id);
    List<Map<String,Object>> selectAllSn();
    List<PotentitalCustomerPool> queryForList(QueryObject qo);
    Integer queryForCount();
}
