package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.DetailInfo;

import java.util.List;

public interface DetailInfoMapper {
    int deleteByOfficialId(Long id);

    int insert(DetailInfo record);

    DetailInfo selectByOfficialId(Long id);

    int updateByOfficialId(DetailInfo record);
}