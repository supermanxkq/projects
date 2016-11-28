package com.paySystem.ic.service.record.impl;

import java.lang.reflect.Field;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.record.Functions;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.system.UserSession;

@Service
public class FunctionsServiceBean extends DaoSupport<Functions> implements FunctionsService {
 
	public void saveFunctionLog(Object object1,Object object2){
		Field[] a = object1.getClass().getDeclaredFields();
		for (Field field : a) {
			System.out.println(field.getName());
		}
	}
	
	public static void main(String[] args) {
		Field[] a = Functions.class.getDeclaredFields();
		for (Field field : a) {
			System.out.println(field.getName()+field.isAccessible());
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveFunction(String moduleName,Integer operType,String relecontents){
		UserSession us = Utils.getUserSession();
		Functions functions = new Functions();
		functions.setModuleName(moduleName);
		functions.setOperType(operType);
		functions.setRelecontents(relecontents);
		functions.setOperId(us.getUserName());
		if(us.getUserLevel()==0||us.getUserLevel()==1){
			functions.setOrganId(us.getOrganId());
		}else{
			functions.setMerId(us.getMerId());
		}
		functions.setOperTime(new Date());
		this.save(functions);
	}
}
