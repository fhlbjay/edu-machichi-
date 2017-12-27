package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.DetailInfo;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author khalil
 */
public interface IDetailInfoService {
    int deleteByOfficialId(Long id);

    int insert(DetailInfo record);

    DetailInfo selectByOfficialId(Long id);

    int updateByOfficialId(DetailInfo record);

}
