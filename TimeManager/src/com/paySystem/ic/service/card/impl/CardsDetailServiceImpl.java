package com.paySystem.ic.service.card.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.card.Cards;
import com.paySystem.ic.dao.card.CardsDetailDAO;
import com.paySystem.ic.service.card.CardsDetailService;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.web.dto.card.CardsDetailDTO;

@Service(CardsDetailService.CARDSDETAILSERVICE)
public class CardsDetailServiceImpl extends DaoSupport<Cards> implements
		CardsDetailService {
	
	@Resource
	CardsDetailDAO cardsDetailDAO;
	
	public CardsDetailDTO findByCardNo(String cardNo) {
		CardsDetailDTO cardsDetailDTO = cardsDetailDAO.findByCardNo(cardNo);
		return cardsDetailDTO;
	}


}
