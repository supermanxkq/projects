package com.paySystem.ic.service.system.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.system.Module;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.system.ModuleService;

/**
 * @ClassName:ModuleServiceBean
 * @Description:模块管理Service实现
 * @date: 2013-1-10上午11:14:44
 * @author: 
 * @version: V1.0
 */
@Service
public class ModuleServiceBean extends DaoSupport<Module> implements
		ModuleService {
	@SuppressWarnings("unchecked")
	public List<Module> find(String jpl) {
		return em.createQuery(jpl).getResultList();
	}

	public Module getModule(Serializable id) {
		return this.find(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void batchSave(List<Module> module) {
		for (Module mod : module) {
			this.save(mod);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteModule(Serializable id) {
		Module module = this.find(id);
		if (module.getParent() != null) {
			module.setParent(null);
			this.update(module);
		}
		this.delete(module.getId());
	}

	@SuppressWarnings("unchecked")
	public List<Module> findByParentid(String parentid) {
		return em.createQuery("from Module o where o.parent.id=?1 ").setParameter(1, parentid).getResultList();
	}

}
