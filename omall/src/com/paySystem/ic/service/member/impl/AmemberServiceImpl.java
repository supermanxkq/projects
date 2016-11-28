package com.paySystem.ic.service.member.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.paySystem.ic.bean.member.Amember;
import com.paySystem.ic.dao.member.AmemberDAO;
import com.paySystem.ic.service.member.AmemberService;
@Service(AmemberService.AMEMBERSERVICE)
public class AmemberServiceImpl implements AmemberService {
	@Resource AmemberDAO amemberDaoImpl;


	public Amember query(String memId) throws Exception {
		return amemberDaoImpl.query(memId);
	}

	public Amember find(String amemId) {
		return amemberDaoImpl.find(amemId);
	}
	

}
