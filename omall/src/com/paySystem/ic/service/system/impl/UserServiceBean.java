package com.paySystem.ic.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.system.Module;
import com.paySystem.ic.bean.system.User;
import com.paySystem.ic.service.base.MerchantsService;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.system.UserService;
import com.paySystem.ic.utils.MD5;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsString;

/**
 * @ClassName:UserServiceBean
 * @Description:用户管理Service实现类
 * @date: 2013-9-20上午11:09:19
 * @author: 
 * @version: V1.0
 */
@Service
public class UserServiceBean extends DaoSupport<User> implements UserService {
	@Resource OrgansService organsService;
	@Resource MerchantsService merchantsService;
	
	public boolean exist(String username) {
		long count = (Long) em.createQuery("select count(o) from User o where o.userName=?1").setParameter(1, username).getSingleResult();
		return count > 0;
	}

	@SuppressWarnings("unchecked")
	public User validate(String username, String password) {
		List<User> list = em.createQuery("from User o where o.userName=?1 and o.passWord=?2").setParameter(1, username).setParameter(2,MD5.MD5Encode(password)).getResultList();
		if (list == null || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
	public UserSession getUserSession(User user,HashMap<String, Module> modulesMap){
		UserSession userSession = new UserSession();
		userSession.setUserName(user.getUserName());
		userSession.setPassWord(user.getPassWord());
		userSession.setRealName(user.getRealName());
		userSession.setUserLevel(user.getUserLevel());
		userSession.setDlsFlag(user.getDlsFlag());
		userSession.setCpyFlag(user.getCpyFlag());
		userSession.setCompanyId(user.getCompanyId());
		userSession.setModulesMap(modulesMap);
		if(user.getUserLevel()==0||user.getUserLevel()==1){
			Organs organs = organsService.find(user.getOrganId());
			if(organs!=null){
				userSession.setOrganId(organs.getOrganId());
				userSession.setOrganName(organs.getName());
				userSession.setPreOrganId(organs.getParentId());
			}
			
			
		}else if(user.getUserLevel()==2){
			Merchants merchants = merchantsService.find(user.getMerId());
			if(merchants!=null){
				userSession.setMerId(merchants.getMerId());
				userSession.setMerName(merchants.getMerName());
				if(merchants.getOrgans()!=null){
					Organs organs = merchants.getOrgans();
					userSession.setOrganId(organs.getOrganId());
					userSession.setOrganName(organs.getName());
					userSession.setPreOrganId(organs.getParentId());
				}
				
			}
		}
		return userSession;
	}
	
	@SuppressWarnings("unchecked")
	public List<OptionsString> getDlsOption() {
		List<User> organsList = em.createQuery("from User o where o.dlsFlag = 1 order by userName asc").getResultList();
		List<OptionsString> list = new ArrayList<OptionsString>();
		for (User user : organsList) {
			list.add(new OptionsString(user.getUserName(), user.getRealName()));
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<OptionsString> getDlsOptionByOrganId(String organId) {
		List<User> organsList = em.createQuery("from User o where o.dlsFlag = 1 and o.organId = '"+organId+"' order by userName asc").getResultList();
		
		List<OptionsString> list = new ArrayList<OptionsString>();
		for (User user : organsList) {
			list.add(new OptionsString(user.getUserName(), user.getRealName()));
		}
		return list;
	}
}
