package com.paySystem.ic.dao.card;

import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.CardNo;
import com.paySystem.ic.bean.card.Cards;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.card.CardsDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;

/**
 * @作者 赵巧鹤
 * @类名称 CardsDAO 根据卡号和卡状态进行查询
 * @项目名称 mciu
 * @创建时间 2013-12-17 上午11:49:00
 */
public interface CardsDAO extends DAO<Cards> {

	public CardsDTO findCards(String cardsNo);

	/**
	 *@Title:saveCardsInfo
	 *@Description:批量新增卡信息
	 *@param:@param cardNos 卡号集合
	 *@param:@return
	 *@param:@throws Exception
	 *@return:ReturnDTO
	 *@author:謝
	 *@thorws:
	 */
	ReturnDTO saveCardsInfo(List<CardNo> cardNos) throws Exception;

	public void saveCards(CardsDTO cardsDTO);

	public void updateCards(CardsDTO cardsDTO);

	/**
	 *@Title:loadBeginCardNo
	 *@Description:根据卡BIN号加载卡号 1.该卡号属于此卡BIN类型 2.该卡号为该卡BIN类型的最小卡号
	 *                          3.该卡号处于状态为：0入库（总部入库完成）
	 *@param:@param cardBinNo 卡BIN号
	 *@param:@return cardNo 卡号
	 *@author 谢洪飞
	 *@return:String
	 *@thorws:
	 */
	String loadBeginCardNo(String cardBinNo, String organId,Integer status);

	/**
	 *@Title:appendCardNo
	 *@Description:根据卡BIN号/加载的起始卡号/数量，查询要出库的卡信息
	 *@param:@param modStockDTO 库存变动信息
	 *@param:@return
	 *@return:Query
	 *@author: 謝
	 *@thorws:
	 */
	Query appendCardNo(ModStockDTO modStockDTO);

	/**
	 *@Title:updateCardsStatus
	 *@Description:批量更改卡状态:从0-总部入库到1-总部出库 / 从1：总部出库 到 2：发卡机构入库
	 *@Param:@param modStockDTO
	 *@param:@Param falg 更改状态标志
	 *@Return:void
	 *@author 谢
	 *@Throws:
	 */
	void updateCardsStatus(ModStock modStock, String flag);
	
	/**
	 *@Title:merStockUpdateStatus
	 *@Description:商户领卡确认，修改卡表中信息
	 *@param:@param modStock
	 *@return: void
	 *@author: 谢洪飞
	 */
	void merStockUpdateStatus(ModStock modStock);

	public CardsDTO checkCardNo(String cardNo);

	public QueryResult<Cards> queryResult(int fristindex, int pageNum,
			CardsDTO cardsDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;

	/****
	 * 
	 *@Title:query
	 *@Description:根据显示卡号返回cardsDTO
	 *@param:@param cardNoShow
	 *@param:@return
	 *@return:CardsDTO
	 *@author:井建国
	 *@thorws:
	 */
	public CardsDTO queryByCardNoShow(String cardNoShow);

	public Cards getCards(CardsDTO cardsDTO);

	public CardsDTO getCardsDTO(Cards cards);

	/**
	 * @Title: findByStatus
	 * @Description: 根据卡BIN和状态查询当前所属机构或商户的卡号
	 * @param @param status
	 * @param @return
	 * @return String
	 * @throws
	 * @author lily
	 * @date 2014-1-7 下午03:22:07
	 */
	public String findByStatus(String bin,Integer status);
	/***
	 * 
	 *@Title:findByBinId
	 *@Description:根据卡BIN查询可以出售卡信息
	 *@param:@param binId
	 *@param:@return
	 *@return:CardsDTO
	 *@author:井建国
	 *@thorws:
	 */
	public CardsDTO findByBinId(String binId,String organId,String merId);
	/**
	 * @Title:queryByCardNo
	 * @Descrition:TODO 根据卡号查询该条记录
	 * @param: @param cardNo
	 * @param: @return
	 * @return: CardsDTO
	 * @author: 张亚运
	 * @throws:
	 */
	public CardsDTO queryByCardNo(String cardNo);
}
