package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.PaymentManage;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IPaymentManageService {
    int deleteByPrimaryKey(Long id);

    int insert(PaymentManage record);

    PaymentManage selectByPrimaryKey(Long id);

    List<PaymentManage> selectAll();

    int updateByPrimaryKey(PaymentManage record);

    PageResult query(QueryObject qo);

    void export(HttpServletResponse response) throws Exception;


    void audit(Long id);

}
