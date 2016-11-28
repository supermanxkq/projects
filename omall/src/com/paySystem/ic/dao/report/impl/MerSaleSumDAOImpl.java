package com.paySystem.ic.dao.report.impl;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.order.OrderGoodsRel;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.report.MerSaleSumDAO;
import com.paySystem.ic.web.dto.order.OrderGoodsRelDTO;

/**
 * @ProjectName:omall20140708DS
 * @ClassName:MerSaleSumDAOImpl
 * @Description:商户销量汇总排名
 * @date: 2014-7-24
 * @author: 赵巧鹤
 * @version: V1.0
 */
@Repository(MerSaleSumDAO.MERSALESUMDAO)
public class MerSaleSumDAOImpl extends DaoSupport<OrderGoodsRel> implements MerSaleSumDAO {

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.report.MerSaleSumDAO#queryAll(int, int, com.paySystem.ic.web.dto.order.OrderGoodsRelDTO, java.util.LinkedHashMap)
	 *@MethodName:queryAll
	 *@Description:查询出所有的商户的销量汇总数据
	 *@param firstIndex 起始页数
	 *@param pageNum 页码
	 *@param orderGoodsRelDTO 数据传输对象
	 *@param orderBy 排序
	 *@return
	 *@throws Exception
	 *@Author:赵巧鹤
	 *@Date:2014-7-24下午05:27:49
	 */
	public QueryResult<OrderGoodsRelDTO> queryAll(int firstIndex, int pageNum,
			OrderGoodsRelDTO orderGoodsRelDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		
		return null;
	}

}
