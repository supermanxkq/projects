package com.paySystem.ic.service.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.member.AccDisPnt;
import com.paySystem.ic.dao.member.AccDisPntDAO;
import com.paySystem.ic.service.member.AccDisPntService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.CardLevelDTO;
@Service(AccDisPntService.ACCDISPNTSERVICE)
public class AccDisPntServiceImpl implements AccDisPntService {
	@Resource AccDisPntDAO accDisPntDaoImpl;

	public AccDisPnt find(String accDisPntId) {
		return accDisPntDaoImpl.find(accDisPntId);
	}

	public String getAccDisPntId() {
		return accDisPntDaoImpl.getAccDisPntId();
	}

	public void save(CardLevelDTO cardLevelDTO) {
		accDisPntDaoImpl.save(cardLevelDTO);
		
	}

	public ReturnDTO update(CardLevelDTO cardLevelDTO) {
		return accDisPntDaoImpl.update(cardLevelDTO);
	}

	public List<AccDisPnt> query(String accDisPntId) {
		
		return accDisPntDaoImpl.query(accDisPntId);
	}

}
