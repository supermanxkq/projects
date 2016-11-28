package com.paySystem.ic.service.stock;

import java.util.LinkedHashMap;

import javax.persistence.Query;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.CardBIN;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.card.CardBINDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;

/**
 * @ClassName:HeadQuinService
 * @Description:总部入库service接口
 * @date: 2013-12-11下午04:49:24
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface HeadQuinService {

	public static final String HEADQUINSERV = "headQuinService";
	
	/**
	 *@Title:queryStockByCond
	 *@Description:根据条件查询总部入库信息Service方法
	 *@param:@param firstindex
	 *@param:@param maxresult
	 *@param:@param modStockDTO
	 *@param:@param orderby
	 *@param:@return
	 *@param:@throws Exception
	 *@return:QueryResult<ModStock>
	 *@thorws:
	 */
	QueryResult<ModStock> queryStockByCond(int firstindex,
			int maxresult, ModStockDTO modStockDTO,
			LinkedHashMap<String, String> orderby) throws Exception;
	
	/**
	 *@Title:queryCardBinList
	 *@Description:查询CardBin列表
	 *@param:@param firstindex
	 *@param:@param maxresult
	 *@param:@param modStockDTO
	 *@param:@param orderby
	 *@param:@return
	 *@param:@throws Exception
	 *@return:QueryResult<CardBIN>
	 *@thorws:
	 */
	QueryResult<CardBIN> queryCardBinList(int firstindex,
			int maxresult, CardBINDTO cardBINDTO,
			LinkedHashMap<String, String> orderby) throws Exception;
	
	/**
	 *@Title:queryCardBinList
	 *@Description:查询所有CardBin列表信息
	 *@param:@return
	 *@return:Query
	 *@thorws:
	 */
	Query queryCardBinList(String organId);
	
	/**
	 *@Title:loadBeginCardNo
	 *@Description:根据卡BIN号加载属于该卡BIN且处于导出状态的最小卡号
	 *@param:@param cardBinNo 卡BIN号
	 *@param:@return
	 *@return:String
	 *@author: 謝
	 *@thorws:
	 */
	String loadBeginCardNo(String cardBinNo,String organId);
	
	/**
	 *@Title:appendCardNo
	 *@Description:根据起始卡号/数量/卡BIN号 查找要添加入库的卡号信息
	 *@param:@param beginCardNo
	 *@param:@param cardCount
	 *@param:@param cardBinNo
	 *@param:@return
	 *@return:Query
	 *@author: 謝
	 *@thorws:
	 */
	Query appendCardNo(String beginCardNo, int cardCount,
			String cardBinNo,int flag);
	
	/**
	 *@Title:addModStockInfo
	 *@Description:添加库存变动信息--总部入库
	 *@param:@param modStockDTO 库存变动DTO对象
	 *@param:@return 
	 *@return:ReturnDTO 返回结果DTO
	 *@author: 謝
	 *@thorws:
	 */
	ReturnDTO addModStockInfo(ModStockDTO modStockDTO,int flag) throws Exception;
	
	/**
	 *@Title:stockDetial
	 *@Description:入库审核/查看详情时，加载要查询的数据
	 *@param:@param modStockDTO
	 *@param:@return
	 *@return:ReturnDTO
	 *@author: 謝
	 *@thorws:
	 */
	ReturnDTO stockDetial(ModStockDTO modStockDTO);
	
	/**
	 *@Title:sureStockIn
	 *@Description:[确认入库]操作触发，将该批卡入库
	 *              1.更改入库变动信息表中的入库状态为：从0:未入库 → 1:已入库
	 *              2.更改卡号表中的入库状态为：从2:待确认 → 3:已入库
	 *              3.插入库存信息表
	 *              4.新增卡表信息
	 *              5.新增账户表信息
	 *@Param:@return
	 *@Return:String
	 *@author: 謝
	 * @throws Exception 
	 *@Throws:
	 */
	ReturnDTO sureStockIn(ModStockDTO modStockDTO) throws Exception;
}
