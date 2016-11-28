package com.paySystem.ic.service.system.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.system.Area;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.system.AreaService;
import com.paySystem.ic.web.ui.OptionsString;

@Service
public class AreaServiceBean extends DaoSupport<Area> implements AreaService {

	@SuppressWarnings("unchecked")
	public List<Area> findAll(){
		return em.createQuery("from Area o ").getResultList();
	}
	/**
	 * 获取级数
	 * @param pid
	 * @param level
	 * @return
	 */
	public Integer getLevel(String pid,Integer level){
		if(pid.equals("0")){
			level = getLevel(this.find(pid).getParentId(),level+1);
		}
		return level;
	}
	
	@SuppressWarnings("unchecked")
	public List<Area> findByPid(String parentId) {
		List<Area> list = em.createQuery("select o from Area o where o.status = 1 and o.parentId = ?1").setParameter(1, parentId).getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<OptionsString> getOption(){
		List<Area> areasList = em.createQuery("from Area o where o.status = 1").getResultList();
		List<OptionsString> list = new ArrayList<OptionsString>();
		for (Area area : areasList) {
			list.add(new OptionsString(area.getId(), area.getDisplayName()));
		}
		return list;
	}
}
