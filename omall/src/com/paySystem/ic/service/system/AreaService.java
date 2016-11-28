package com.paySystem.ic.service.system;

import java.util.List;

import com.paySystem.ic.bean.system.Area;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.ui.OptionsString;

public interface AreaService extends DAO<Area> {
	public List<Area> findAll();
	
	public Integer getLevel(String pid,Integer level);
	
	public List<Area> findByPid(String parentId);
	
	public List<OptionsString> getOption();
}
