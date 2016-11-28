package com.paySystem.ic.dao.report.impl;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.report.MemShoppingSum;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.report.MemShopRankSumDAO;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.report.MemShoppingSumDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:MemShopRankSumDAOImpl
 * @Description:会员购物量汇总排名的DAO接口实现
 * @date: 2014-7-23
 * @author: 王楠
 * @version: V1.0
 */
@Repository(MemShopRankSumDAO.MEMSHOPRANKSUMDAO)
public class MemShopRankSumDAOImpl extends DaoSupport<MemShoppingSum> implements MemShopRankSumDAO{

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.report.MemShopRankSumDAO#queryAll(int, int, com.paySystem.ic.web.dto.report.MemShoppingSumDTO, java.util.LinkedHashMap)
	 *@MethodName:queryAll
	 *@Description:会员购物量汇总排名查询
	 *@param page 页
	 *@param pageNum 页容量
	 *@param memShoppingSumDTO 会员购物量汇总排名的DTO
	 *@param orderBy 排序
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-7-23下午05:54:45
	 */
	public QueryResult<MemShoppingSumDTO> queryAll(int page, int pageNum,
			MemShoppingSumDTO memShoppingSumDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		List<Object> params=new ArrayList<Object>();/**参数设置*/
		StringBuilder sql = new StringBuilder();	/**封装查询where条件*/
		QueryResult<MemShoppingSum> queryResult=null;
		QueryResult<MemShoppingSumDTO> memshopDTO=new QueryResult<MemShoppingSumDTO>();
		if (StringUtils.isNotBlank(memShoppingSumDTO.getMemId())) {
			/**拼接sql语句*/
			sql.append(" and o.memId like ?").append(params.size()+1);
			/**为where语句赋值*/
			params.add("%"+memShoppingSumDTO.getMemId().trim()+"%");
		}
		if (StringUtils.isNotBlank(memShoppingSumDTO.getMemName())) {
			/**拼接sql语句*/
			sql.append(" and o.memName like ?").append(params.size()+1);
			/**为where语句赋值*/
			params.add("%"+memShoppingSumDTO.getMemName().trim()+"%");
		}
		UserSession sess = Utils.getUserSession();
		/**
		 *设置权限
		 */
		switch (sess.getUserLevel()) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			sql.append(" and o.merId='" + sess.getMerId() + "'");
			break;
		}
		/** 获取带有实体的QueryResult */
		queryResult= getScrollData(page,
				pageNum,sql.toString(),params.toArray(),orderBy);
		/**获取实体集合*/
		List<MemShoppingSum> list = queryResult.getResultlist();
		/**进行集合的转换*/
		List<MemShoppingSumDTO> dtos=changeListToDTO(list);
		/**放置到MemShoppingSumDTO集合中并设置集合的总记录*/
		memshopDTO.setResultlist(dtos);
		memshopDTO.setTotalrecord(queryResult.getTotalrecord());
		return memshopDTO;
	}

	/**
	*@Title:changeListToDTO
	*@Description:实体集合转换成DTO集合
	*@Params:@param list 实体集合
	*@Params:@return
	*@Return:List<MemShoppingSumDTO>
	*@author:王楠
	 * @throws Exception 
	*@Date:2014-7-24上午11:51:15
	*/
	private List<MemShoppingSumDTO> changeListToDTO(List<MemShoppingSum> list) throws Exception {
		/** 实例化要返回的QueryResult<MemShoppingSumDTO>对象 */
		List<MemShoppingSumDTO> queryResultDTO=new ArrayList<MemShoppingSumDTO>();
		/** 迭代进行QueryResult<MemShoppingSum>到QueryResult<MemShoppingSum>的转换 */
        for (int i=0;i<list.size();i++) {
        	queryResultDTO.add(getSalesSummaryDTO(list.get(i)));
        }
		return queryResultDTO;
	}

	/**
	*@Title:getSalesSummaryDTO
	*@Description:实体转DTO 
	*@Params:@param memShoppingSum 实体
	*@Params:@return
	*@Return:MemShoppingSumDTO DTO
	*@author:王楠
	*@throws:Exception 
	*@Date:2014-7-24下午03:37:08
	*/
	private MemShoppingSumDTO getSalesSummaryDTO(MemShoppingSum memShoppingSum) throws Exception {
		MemShoppingSumDTO memShoppingSumDTO=new MemShoppingSumDTO();
		memShoppingSumDTO=
			(MemShoppingSumDTO)EntityDtoConverter.bean2Dto(memShoppingSum, memShoppingSumDTO);
		return memShoppingSumDTO;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.report.MemShopRankSumDAO#exportMemShopXls(com.paySystem.ic.web.dto.report.MemShoppingSumDTO, java.util.LinkedHashMap)
	 *@MethodName:exportMemShopXls
	 *@Description:会员购物量汇总排名查询信息导出
	 *@param memShoppingSumDTO 会员购物量汇总实体的DTO
	 *@param orderBy 排序
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-7-23下午06:03:35
	 */
	public QueryResult<MemShoppingSumDTO> exportMemShopXls(
			MemShoppingSumDTO memShoppingSumDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return queryAll(-1,-1,memShoppingSumDTO,orderBy);
	}
}
