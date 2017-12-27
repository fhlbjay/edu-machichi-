package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.ChangeClass;
import cn.wolfcode.crm.domain.OfficialStudent;
import cn.wolfcode.crm.domain.Payment;
import cn.wolfcode.crm.mapper.PaymentMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 需求:
 *
 * @author khalil
 * @date 2017/12/22
 */
@Service
public class PaymentServiceImpl implements IPaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public int deleteByOfficialId(Long id) {
        return paymentMapper.deleteByOfficialId(id);
    }

    @Override
    public int insert(Payment record) {
        return 0;
    }

    @Override
    public Payment selectByOfficialId(Long id) {
        return null;
    }

    @Override
    public int updateByOfficialId(Payment record) {
        return 0;
    }
    //分页
    @Override
    public PageResult query(QueryObject qo) {
        //判断满足条件的总数
        int total = paymentMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(total, Collections.emptyList());
        }
        List<Payment> rows = paymentMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    @Override
    public void audit(Long id) {

    }
}
