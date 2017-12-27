package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.DetailInfo;
import cn.wolfcode.crm.mapper.DetailInfoMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDetailInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 需求:
 * Created by khalil on 2017/12/22.
 */
@Service
public class DetailInfoServiceImpl implements IDetailInfoService {
    @Autowired
    private DetailInfoMapper detailInfoMapper;
    @Override
    public int deleteByOfficialId(Long id) {
        return detailInfoMapper.deleteByOfficialId(id);
    }

    @Override
    public int insert(DetailInfo record) {
        return 0;
    }

    @Override
    public DetailInfo selectByOfficialId(Long id) {
        return null;
    }

    @Override
    public int updateByOfficialId(DetailInfo record) {
        return 0;
    }

}
