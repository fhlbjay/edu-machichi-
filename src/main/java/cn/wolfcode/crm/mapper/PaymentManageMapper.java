package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.PaymentManage;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentManageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaymentManage record);

    PaymentManage selectByPrimaryKey(Long id);

    List<PaymentManage> selectAll();

    int updateByPrimaryKey(PaymentManage record);

    int queryForCount(QueryObject qo);

    List<PaymentManage> queryForList(QueryObject qo);

    void audit(@Param("id") Long id, @Param("auditId") Long auditId);

}