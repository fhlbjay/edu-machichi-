package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.ChangeClass;
import cn.wolfcode.crm.domain.OfficialStudent;
import cn.wolfcode.crm.domain.Payment;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

/**
 * @author khalil
 */
public interface IPaymentService {
    int deleteByOfficialId(Long id);

    int insert(Payment record);

    Payment selectByOfficialId(Long id);

    int updateByOfficialId(Payment record);

    PageResult query(QueryObject qo);

    void audit(Long id);

}
