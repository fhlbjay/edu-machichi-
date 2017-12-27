package cn.wolfcode.crm.service;


import cn.wolfcode.crm.domain.Universitytrace;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.UniversitytraceQueryObject;

import java.util.List;

public interface IUniversitytraceService {

	void deleteByPrimaryKey(Long id);

	void insert(Universitytrace record);

	Universitytrace selectByPrimaryKey(Long id);

	List<Universitytrace> selectAll();

	void updateByPrimaryKey(Universitytrace record);

	PageResult query(UniversitytraceQueryObject qo);

    void changeState(Long id);
}
