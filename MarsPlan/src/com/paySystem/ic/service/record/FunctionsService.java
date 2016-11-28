package com.paySystem.ic.service.record;

import com.paySystem.ic.bean.record.Functions;
import com.paySystem.ic.service.common.DAO;

public interface FunctionsService extends DAO<Functions> {
	public void saveFunction(String moduleName,Integer operType,String relecontents);

}
