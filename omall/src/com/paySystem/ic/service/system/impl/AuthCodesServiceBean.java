package com.paySystem.ic.service.system.impl;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.paySystem.ic.bean.system.AuthCodes;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.system.AuthCodesService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.RandomUtils;

@Service
public class AuthCodesServiceBean extends DaoSupport<AuthCodes> implements AuthCodesService {

	
	@SuppressWarnings("unchecked")
	public AuthCodes findAuthCodes(String flag,String type, String teleNo) {

		StringBuffer sql = new StringBuffer();
		sql.append("from AuthCodes o where o.flag = '"+flag+"'");
		sql.append(" and o.type = '"+type+"'");
		sql.append(" and o.teleNo = '"+teleNo+"'");
		sql.append(" and o.exTime >=to_timestamp('").append(DateTimeTool.dateFormat("", new Date())).append("','yyyy-mm-dd hh24:mi:ss:ff') ");
		sql.append(" order by o.createTime desc");
		List<AuthCodes> list = em.createQuery(sql.toString()).setMaxResults(1).getResultList();
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 发送验证码
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Boolean sendAuthCode(String flag,String type, String teleNo) throws Exception {
		
		AuthCodes authCodes = this.findAuthCodes(flag, type,teleNo);
		if(authCodes==null){
			authCodes = new AuthCodes();
			authCodes.setFlag(flag);
			authCodes.setType("3");
			authCodes.setTeleNo(teleNo);
			authCodes.setAuthCode(Globals.IS_SEND_MSG?RandomUtils.getRandomInt(4):"8888");
			authCodes.setExTime(DateTimeTool.nMinuteAgo(20, new Date()));
			authCodes.setCreateTime(new Date());
			this.save(authCodes);
		}
		

		return null;
	}
}
