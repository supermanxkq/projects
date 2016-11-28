package com.paySystem.ic.service.card;

import com.paySystem.ic.web.dto.card.CardsDetailDTO;

public interface CardsDetailService{

	//设置static final 类型的依赖注入常量
	public static final String CARDSDETAILSERVICE = "cardsDetailService";
	/**
	 * 查看卡详细信息
	 */
	public CardsDetailDTO findByCardNo(String cardNo);
}
