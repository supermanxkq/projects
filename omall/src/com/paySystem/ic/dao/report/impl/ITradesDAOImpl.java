package com.paySystem.ic.dao.report.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.report.ITrades;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.report.ITradesDAO;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.report.ITradesDTO;

/**
 * @ProjectName:omallproject
 * @ClassName:ITradesDAOImpl
 * @Description:积分消费记录Dao接口
 * @date: 2014-11-12
 * @author: 毛智东
 * @version: V1.0
 */
@Repository(ITradesDAO.ITRADESDAO)
public class ITradesDAOImpl extends DaoSupport<ITrades> implements ITradesDAO {

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.report.ITradesDAO#queryAll(int
	 *                        , int, com.paySystem.ic.web.dto.report.BTradesDTO,
	 *                        java.util.LinkedHashMap)
	 *@MethodName:queryAll
	 *@Description:分页查询消费积分
	 *@param： firstindex 当前页索引
	 *@Params:@param maxresult 每页记录数
	 *@Params:@param bTradesDTO 参数保存dto
	 *@Params:@param orderby 排序
	 *@return
	 *@Params:@throws Exception 抛出异常
	 *@Author:毛智东
	 *@Date:2014-11-12上午10:33:53
	 */
	public QueryResult<ITradesDTO> queryAll(int firstindex, int maxresult,
			ITradesDTO iTradesDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {

		/** 封装sql语句 **/
		StringBuilder sb = new StringBuilder();

		/** 查询交易状态正常的记录 **/
		sb.append("and o.status=1");

		/** 参数设置 **/
		List<Object> params = new ArrayList<Object>();

		/** 判断过滤条件，如果无过滤条查询全部数据 */

		/** 交易流水号 **/
		if (StringUtils.isNotBlank(iTradesDTO.getParamTradesId())) {
			sb.append(" and o.tradesId like ?").append(params.size() + 1);
			params.add("%" + iTradesDTO.getParamTradesId().trim() + "%");
		}

		/** 会员编号 **/
		if (StringUtils.isNotBlank(iTradesDTO.getParamMemId())) {
			sb.append(" and o.omemId like ?").append(params.size() + 1);
			params.add("%" + iTradesDTO.getParamMemId().trim() + "%");
		}

		/** 判断交易起始时间 **/
		if (StringUtils.isNotBlank(iTradesDTO.getParamBeginTime())) {
			sb.append(" and str_to_date(o.tradesTime,'%Y-%m-%d') >= ?").append(
					params.size() + 1);
			params.add(iTradesDTO.getParamBeginTime().trim());
		}

		/** 判断交易结束时间 **/
		if (StringUtils.isNotBlank(iTradesDTO.getParamEndTime())) {
			sb.append(" and str_to_date(o.tradesTime,'%Y-%m-%d') <= ?").append(
					params.size() + 1);
			params.add(iTradesDTO.getParamEndTime().trim());
		}

		/** 得到queryResult对象 **/
		QueryResult<ITrades> queryResult = this.getScrollData(firstindex,
				maxresult, sb.toString(), params.toArray(), orderBy);

		/** 将Bean转换成DTO **/
		List<ITrades> list = queryResult.getResultlist();
		List<ITradesDTO> dtolist = new ArrayList<ITradesDTO>();
		for (ITrades bean : list) {
			ITradesDTO dto = new ITradesDTO();
			dto = (ITradesDTO) EntityDtoConverter.bean2Dto(bean, dto);
			dtolist.add(dto);
		}
		QueryResult<ITradesDTO> queryResultDTO = new QueryResult<ITradesDTO>();
		queryResultDTO.setResultlist(dtolist);
		queryResultDTO.setTotalrecord(queryResult.getTotalrecord());
		return queryResultDTO;
	}

}
