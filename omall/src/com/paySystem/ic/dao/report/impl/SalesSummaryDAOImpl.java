package com.paySystem.ic.dao.report.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.report.SalesSummary;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.report.SalesSummaryDAO;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.web.dto.report.SalesSummaryDTO;
/**
 * @ProjectName:MCIU_DS
 * @ClassName:SalesSummaryDAOImpl
 * @Description:销售额汇总DAO接口的实现
 * @date: 2014-7-16
 * @author: 王楠
 * @version: V1.0
 */
@Repository(SalesSummaryDAO.SALESSUMMARYDAO)
public class SalesSummaryDAOImpl extends DaoSupport<SalesSummary> implements SalesSummaryDAO {


	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.report.SalesSummaryDAO#queryAll(int, int, com.paySystem.ic.web.dto.report.SalesSummaryDTO, java.util.LinkedHashMap)
	 *@MethodName:queryAll
	 *@Description:查询销售额汇总信息
	 *@param page 页
	 *@param pageNum 页容量
	 *@param salesSummaryDTO 销售额汇总实体的DTO
	 *@param orderBy 排序
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-7-16下午02:23:44
	 */
	public QueryResult<SalesSummaryDTO> queryAll(int page, int pageNum,
			SalesSummaryDTO salesSummaryDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		List<Object> params=new ArrayList<Object>();/**参数设置*/
		StringBuilder sb=new StringBuilder();/**封装查询where条件*/
		QueryResult<SalesSummary> queryResult = null;
		QueryResult<SalesSummaryDTO> salesDTO=new QueryResult<SalesSummaryDTO>();
		if (StringUtils.isNotBlank(salesSummaryDTO.getMerName())) {
			/**拼接sql语句*/
			sb.append(" and o.merName like ?").append(params.size()+1);
			/**为where语句赋值*/
			params.add("%"+salesSummaryDTO.getMerName().trim()+"%");
		}
		if (StringUtils.isNotBlank(salesSummaryDTO.getBeginDate())) {
		    sb.append(" and str_to_date(o.lastTime,'%Y-%m-%d') >='"
				+salesSummaryDTO.getBeginDate()+"'");
		}
		if (StringUtils.isNotBlank(salesSummaryDTO.getEndDate())) {
		    sb.append(" and str_to_date(o.lastTime,'%Y-%m-%d') <='"
				+salesSummaryDTO.getEndDate()+"'");
		}
		/** 获取带有实体的QueryResult */
		queryResult=this.getScrollData(page,
				pageNum,sb.toString(),params.toArray(),orderBy);
		/**获取实体集合*/
		List<SalesSummary> list=queryResult.getResultlist();
		/**进行集合的转换*/
		List<SalesSummaryDTO> dtos=changeListToDTO(list);
		/**放置到SalesSummaryDTO集合中并设置集合的总记录*/
		salesDTO.setResultlist(dtos);
		salesDTO.setTotalrecord(queryResult.getTotalrecord());
		return salesDTO;
	}

	/**
	*@Title:changeListToDTO
	*@Description:实体集合转换成DTO集合
	*@Params:@param list
	*@Params:@return
	*@Return:List<SalesSummaryDTO>
	*@author:王楠
	*@Date:2014-7-17上午10:42:27
	*/
	private List<SalesSummaryDTO> changeListToDTO(List<SalesSummary> list) {
		/** 实例化要返回的QueryResult<SalesSummaryDTO>对象 */
		List<SalesSummaryDTO> queryResultDTO=new ArrayList<SalesSummaryDTO>();
		/** 迭代进行QueryResult<SalesSummary>到QueryResult<SalesSummaryDTO>的转换 */
        for (int i=0;i<list.size();i++) {
        	queryResultDTO.add(getSalesSummaryDTO(list.get(i)));
        }
		return queryResultDTO;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.report.SalesSummaryDAO#exportSalesSummary(com.paySystem.ic.web.dto.report.SalesSummaryDTO, java.util.LinkedHashMap)
	 *@MethodName:exportSalesSummary
	 *@Description:导出销售额汇总信息
	 *@param salesSummaryDTO 销售额汇总实体的DTO
	 *@param orderBy 排序
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-7-16下午02:25:02
	 */
	public QueryResult<SalesSummaryDTO> exportSalesSummary(
			SalesSummaryDTO salesSummaryDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return queryAll(-1, -1, salesSummaryDTO, orderBy);
	}
	
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.report.SalesSummaryService#getSalesSummaryDTO(com.paySystem.ic.bean.report.SalesSummary)
	 *@MethodName:getSalesSummaryDTO
	 *@Description:将实体转换成DTO
	 *@param salesSummary 实体
	 *@return
	 *@Author:王楠
	 *@Date:2014-7-16下午04:12:31
	 */
	public SalesSummaryDTO getSalesSummaryDTO(SalesSummary salesSummary) {
		SalesSummaryDTO salesSummaryDTO=new SalesSummaryDTO();
		salesSummaryDTO.setSaleId(salesSummary.getSaleId());
		salesSummaryDTO.setMerId(salesSummary.getMerId());
		salesSummaryDTO.setMerName(salesSummary.getMerName());
		salesSummaryDTO.setSalesQty(salesSummary.getSalesQty());
		salesSummaryDTO.setSalesAmt(salesSummary.getSalesAmt());
		salesSummaryDTO.setLastTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",salesSummary.getLastTime()));
		return salesSummaryDTO;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.report.SalesSummaryService#getSalesSummary(com.paySystem.ic.web.dto.report.SalesSummaryDTO)
	 *@MethodName:getSalesSummary
	 *@Description:将DTO转换成实体
	 *@param salesSummaryDTO  销售额汇总实体的DTO
	 *@return
	 *@Author:王楠
	 *@Date:2014-7-17上午10:00:45
	 */
	public SalesSummary getSalesSummary(SalesSummaryDTO salesSummaryDTO) {
		SalesSummary salesSummary=new SalesSummary();
		salesSummary.setSaleId(salesSummaryDTO.getSaleId());
		salesSummary.setMerId(salesSummaryDTO.getMerId());
		salesSummary.setMerName(salesSummaryDTO.getMerName());
		salesSummary.setSalesQty(salesSummaryDTO.getSalesQty());
		salesSummary.setSalesAmt(salesSummaryDTO.getSalesAmt());
		salesSummary.setLastTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",salesSummaryDTO.getLastTime()));
		return salesSummary;
	}

}
