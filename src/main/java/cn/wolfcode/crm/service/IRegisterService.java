package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Register;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.RegisterQueryObject;
import jxl.write.WriteException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by joy on 2017/12/23.
 */
public interface IRegisterService {
    void deleteByPrimaryKey(Long id);

    void insert(Register record);

    Register selectByPrimaryKey(Long id);

    List<Register> selectAll();

    void updateByPrimaryKey(Register record);

    void changeState(Long id);

    void export(HttpServletResponse response) throws WriteException, Exception;

    PageResult query(RegisterQueryObject rqo);

    void testRegister(Register register);
}
