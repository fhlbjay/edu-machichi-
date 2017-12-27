package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Payment;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentMapper {
    int deleteByOfficialId(Long id);

    int insert(Payment record);

    Payment selectByOfficialId(Long id);

    int updateByOfficialId(Payment record);

    int queryForCount(QueryObject qo);

    List<Payment> queryForList(QueryObject qo);

    void updatePaidupFee(@Param("paymentAmount") BigDecimal paymentAmount, @Param("id") Long id);

    void updateFee2pay(Long id);

    void updatePaidStatus();

}