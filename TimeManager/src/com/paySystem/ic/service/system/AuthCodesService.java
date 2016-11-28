package com.paySystem.ic.service.system;

import com.paySystem.ic.bean.system.AuthCodes;
import com.paySystem.ic.service.common.DAO;

public interface AuthCodesService extends DAO<AuthCodes> {

	public AuthCodes findAuthCodes(String flag,String type, String teleNo);
	
	public Boolean sendAuthCode(String flag,String type, String teleNo) throws Exception ;

}
