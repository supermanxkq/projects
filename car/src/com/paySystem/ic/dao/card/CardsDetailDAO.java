package com.paySystem.ic.dao.card;

import java.util.List;

import com.paySystem.ic.bean.card.Acards;
import com.paySystem.ic.bean.card.CardNo;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.card.CardsDetailDTO;

public interface CardsDetailDAO extends DAO<Acards>{
	public CardsDetailDTO findByCardNo(String cardNo);

	public CardsDetailDTO findCardsDetail(String beginCardNo);

	public void updateCardsDetail(CardsDetailDTO cardsDetailDTO);
	/**
	 *@Title:saveCardsDetail
	 *@Description:保存卡明细信息
	 *@param:@param cardNos 卡号集合
	 *@return:void
	 *@author: 謝
	 *@thorws:
	 */
	void saveCardsDetail(List<CardNo> cardNos) throws Exception;
}
