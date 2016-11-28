package com.paySystem.ic.service.record;

import com.paySystem.ic.bean.record.OperRecord;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.system.UserSession;

public interface OperatorsService extends DAO<OperRecord> {
	public void saveOperatorLog(String moduleName,UserSession us);
}
