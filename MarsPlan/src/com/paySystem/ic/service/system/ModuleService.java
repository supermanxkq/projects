package com.paySystem.ic.service.system;

import java.io.Serializable;
import java.util.List;

import com.paySystem.ic.bean.system.Module;
import com.paySystem.ic.service.common.DAO;

/**
 * @ClassName:ModuleService
 * @Description:模块管理Service接口
 * @date: 2013-1-10上午11:15:14
 * @author: 
 * @version: V1.0
 */
public interface ModuleService extends DAO<Module> {

	public List<Module> find(String jpl);

	public Module getModule(Serializable id);

	public void batchSave(List<Module> module);

	public List<Module> findByParentid(String parentid);

	void deleteModule(Serializable id);
}
