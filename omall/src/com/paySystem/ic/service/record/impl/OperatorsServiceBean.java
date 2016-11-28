package com.paySystem.ic.service.record.impl;

import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.record.OperRecord;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.record.OperatorsService;
import com.paySystem.ic.service.system.UserService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.system.UserSession;

@Service
public class OperatorsServiceBean extends DaoSupport<OperRecord> implements OperatorsService {

	@Resource UserService userService;
	 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOperatorLog(String moduleName,UserSession us){
		try {
			OperRecord operRecord = new OperRecord();
			operRecord.setOperId(us.getUserName());
			operRecord.setModuleName(moduleName);
			operRecord.setLoginIP(Utils.getIpAddr());
			operRecord.setOperTime(new Date());
			if(us.getUserLevel()==0||us.getUserLevel()==1){
				operRecord.setOrganId(us.getOrganId());
			}else{
				operRecord.setMerId(us.getMerId());
			}
			this.save(operRecord);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
