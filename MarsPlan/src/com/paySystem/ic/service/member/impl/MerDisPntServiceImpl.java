package com.paySystem.ic.service.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.member.MerDisPnt;
import com.paySystem.ic.dao.member.MerDisPntDAO;
import com.paySystem.ic.service.member.MerDisPntService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.CardLevelDTO;
@Service(MerDisPntService.MERDISPNTSERVICE)
public class MerDisPntServiceImpl implements MerDisPntService {
	@Resource MerDisPntDAO merDisPntDaoImpl;
	public MerDisPnt find(String merDisPntids) {
		return merDisPntDaoImpl.find(merDisPntids);
	}

	public String getMerDisPntId() {
		return merDisPntDaoImpl.getMerDisPntId();
	}

	public void save(CardLevelDTO cardLevelDTO) {
		merDisPntDaoImpl.save(cardLevelDTO);
	}

	public ReturnDTO update(CardLevelDTO cardLevelDTO) {
		return merDisPntDaoImpl.update(cardLevelDTO);
	}

	public List<MerDisPnt> query(String merDisPntId) {
		return merDisPntDaoImpl.query(merDisPntId);
	}

	public MerDisPnt search(String levelId) {
		return merDisPntDaoImpl.search(levelId);
	}
}
