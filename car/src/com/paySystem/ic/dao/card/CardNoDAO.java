package com.paySystem.ic.dao.card;


import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.CardNo;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.card.CardNoDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;

/**
 * @作者 赵巧鹤
 * @类名称 CardNoDAO 卡号DAO 接口
 * @项目名称 mciu
 * @创建时间 2013-12-10 下午02:31:28
 */
public interface CardNoDAO extends DAO<CardNo>{
	
	public static final String CARDNODAO ="cardNoDAO";
	
	/**
	 * @Title:getCardNo
	 * @Descrition:TODO 按流水号获得卡号
	 * @param: @return
	 * @return: String
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public String getCardNo();
	
	
	/**
	 * @Title:queryByNo
	 * @Descrition:TODO 根据卡号进行查询
	 * @param: @param cardNo
	 * @param: @return
	 * @return: CardNo
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public CardNo queryByNo(String cardNo);

	
	/**
	 * @Title:queryCardNo
	 * @Descrition:TODO 查询卡号信息
	 * @param: @param fristindex
	 * @param: @param pageNum
	 * @param: @param cardNoDTO
	 * @param: @param orderBy
	 * @param: @return
	 * @param: @throws Exception
	 * @return: QueryResult<CardNo>
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public QueryResult<CardNo> queryCardNo(int fristindex,int pageNum,
			CardNoDTO cardNoDTO,LinkedHashMap<String,String> orderBy)throws Exception;
	


   /**
 * @Title:exportCardConsumeXls
 * @Descrition:TODO 导出
 * @param: @param cardNoDTO
 * @param: @param title
 * @param: @param response
 * @param: @return
 * @return: String
 * @author: 赵巧鹤
 * @throws:
 */
public String exportCardConsumeXls( CardNoDTO cardNoDTO,String title,HttpServletResponse response);
   
   /**
    *@Title:loadBeginCardNo
    *@Description:根据卡BIN号加载卡号
    *             1.该卡号属于此卡BIN类型
    *             2.该卡号为该卡BIN类型的最小卡号
    *             3.该卡号处于导出状态
    *@param:@param cardBinNo 卡BIN号
    *@param:@return cardNo   卡号
    *@author 谢洪飞
    *@return:String
    *@thorws:
    */
  String loadBeginCardNo(String cardBinNo,String organId);
  
  /**
   *@Title:appendCardNo
   *@Description:根据起始卡号/数量/卡BIN号 查询卡号信息
   *@param:@param beginCardNo ：起始卡号
   *@param:@param cardCount   ：添加数量
   *@param:@param cardBinNo   ：卡BIN号
   *@param:@return
   *@return:QueryResult<CardNo>
   *@author:谢洪飞
   *@thorws:
   */
  Query appendCardNo(String beginCardNo,int cardCount,String cardBinNo,int status);
  
  /**
   *@Title:updateCardStatus
   *@Description:更改卡号表中信息的状态：
   *             从"导出"状态改为"待确认"状态
   *@param:@param modStockDTO 库存变动信息DTO对象
   *@param:@return
   *@return:ReturnDTO 返回DTO对象
   *@author:谢洪飞
   *@thorws:
   */
  ReturnDTO updateCardStatus(ModStockDTO modStockDTO) throws Exception;
  /**
   *@Title:updateCardStatus
   *@Description: 更改卡号表中信息的状态：
   *             从"待确认"状态改为“已入库”状态
   *@param:@param modStockDTO 库存变动信息DTO对象
   *@param:@return
   *@return:ReturnDTO 返回DTO对象
   *@author:谢洪飞
   *@thorws:
   */
  ReturnDTO modfiyCardStatus(List<CardNo> cardNos) throws Exception;
  
  }
