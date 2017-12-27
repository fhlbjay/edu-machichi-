package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Register;
import cn.wolfcode.crm.query.RegisterQueryObject;

import java.util.List;

public interface RegisterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Register record);

    Register selectByPrimaryKey(Long id);

    List<Register> selectAll();

    int updateByPrimaryKey(Register record);

    void changeState(Long id);

    List<Register> queryForList(RegisterQueryObject rqo);

    Integer queryForCount(RegisterQueryObject rqo);

    void testRegister(Register register);
}