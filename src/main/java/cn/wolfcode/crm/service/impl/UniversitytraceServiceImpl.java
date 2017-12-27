package cn.wolfcode.crm.service.impl;


import cn.wolfcode.crm.domain.Universitytrace;
import cn.wolfcode.crm.mapper.UniversitytraceMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.UniversitytraceQueryObject;
import cn.wolfcode.crm.service.IUniversitytraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UniversitytraceServiceImpl implements IUniversitytraceService {

	@Autowired
	private UniversitytraceMapper universitytraceMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		universitytraceMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(Universitytrace record) {
		universitytraceMapper.insert(record);
	}
	@Override
	public Universitytrace selectByPrimaryKey(Long id) {
		return universitytraceMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Universitytrace> selectAll() {
		return universitytraceMapper.selectAll();
	}
	@Override
	public void updateByPrimaryKey(Universitytrace record) {
		universitytraceMapper.updateByPrimaryKey(record);
	}
	@Override
	public PageResult query(UniversitytraceQueryObject qo) {
		int total = universitytraceMapper.queryForCount(qo);
		if (total==0){
		    return new PageResult(0, Collections.emptyList());
		}
		List<Universitytrace> rows = universitytraceMapper.queryForList(qo);
		return new PageResult(total,rows);
	}

	@Override
	public void changeState(Long id) {
		universitytraceMapper.changeState(id);
	}


}
