package com.paySystem.ic.dao.report;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.order.OrderGoodsRel;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.order.OrderGoodsRelDTO;

/**
 * @ProjectName:omall20140708DS
 * @ClassName:MerSaleSumDAO
 * @Description:商户销量汇总排名
 * @date: 2014-7-24
 * @author: 赵巧鹤
 * @version: V1.0
 */
public interface MerSaleSumDAO extends DAO<OrderGoodsRel>{
	
public static final String MERSALESUMDAO ="merSaleSumDAO";
/**
*@Title:queryAll
*@Description:查询所有
*@Params:@param firstIndex 起始页数
*@Params:@param pageNum 页码
*@Params:@param orderGoodsRelDTO 数据传输对象
*@Params:@param orderBy 排序
*@Params:@return
*@Params:@throws Exception
*@Return:QueryResult<OrderGoodsRelDTO>
*@author:赵巧鹤
*@Date:2014-7-24下午05:26:14
*/
public QueryResult<OrderGoodsRelDTO> queryAll(int firstIndex,int pageNum,OrderGoodsRelDTO orderGoodsRelDTO,LinkedHashMap<String,String> orderBy)throws Exception;


}
