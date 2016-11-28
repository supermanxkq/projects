package com.paySystem.ic.service.system.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.system.Role;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.system.RoleService;

@Service
public class RoleServiceBean extends DaoSupport<Role> implements RoleService {

	public boolean validate(String code) {
		long count = (Long) em.createQuery("select count(o) from Role o where o.code=?1").setParameter(1, code).getSingleResult();
		return count > 0;
	}

	@SuppressWarnings("unchecked")
	public String getCode(){
		String code = "";
		List<String> string = em.createQuery("select o.code from Role o order by o.code desc").setMaxResults(1).getResultList();
		if(string==null||string.isEmpty()){
			code = "1001";
		}else{
			code = string.get(0);
			Integer newcode = Integer.valueOf(code)+1;
			code = newcode.toString();
			while(code.length()<4){
				code = "0" + code;
			}
		}
		
		while(this.validate(code)){
			Integer newcode = Integer.valueOf(code)+1;
			code = newcode.toString();
			while(code.length()<4){
				code = "0" + code;
			}
		}
		
		return code;
	}
	
	@SuppressWarnings("unchecked")
	public Role findBycode(String code) {
		List<Role> list = em.createQuery("from Role o where o.code like '" + code + "'").getResultList();
		if (list == null || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Role> getAll() {
		return em.createQuery("from Role").getResultList();
	}
}
