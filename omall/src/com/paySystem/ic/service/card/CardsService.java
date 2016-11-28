package com.paySystem.ic.service.card;

import java.util.LinkedHashMap;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.Cards;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.account.AccRecordDTO;
import com.paySystem.ic.web.dto.card.CardsDTO;

public interface CardsService{

	//设置static final 类型的依赖注入常量
	public static final String CARDSSERVICE = "cardsService";
	/**
	 * 查询所有的卡
	 * */
	public QueryResult<CardsDTO> queryAll(int firstindex, int maxresult,CardsDTO cardsDTO,LinkedHashMap<String, String> orderby);
	
	/**
	 * 查看卡详细信息
	 */
	
	public CardsDTO findByCardNo(String cardNo);
	
	public ReturnDTO update(CardsDTO cardsDTO);

	//public Cards find(String cardNo);
	/**
	 * 检验卡号是否否存在
	 * 
	 * 
	 * 井建国创建
	 */
	public boolean checkCardId(String cardNo);
	/**
	 * 赵巧鹤 判断该卡号是否属于该卡BIN
	 * */
	public boolean checkCardNo(AccRecordDTO accRecordDTO);
	public Cards checkRechAmt(String cardNo);
	
	public CardsDTO queryByCardsNoShow(String cardsNoShow);
	/**
	 * @Title:queryByCardsNoLock
	 * @Descrition:TODO 挂失专用
	 * @param: @param cardsNoShow
	 * @param: @return
	 * @return: CardsDTO
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public CardsDTO queryByCardsNoLock(String cardsNoShow);
}
