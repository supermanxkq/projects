package com.paySystem.ic.dao.report.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.report.BTrades;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.report.BTradesDAO;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.report.BTradesDTO;

/**
 * @ProjectName: omproject
 * @ClassName: BTradesDAOImpl
 * @Description: 业务交易报表DAO实现类
 * @date: 2014-11-7 下午06:29:06
 * @author: 王少辉
 * @version: V1.0
 */
@Repository(BTradesDAO.BTRADESDAO)
public class BTradesDAOImpl extends DaoSupport<BTrades> implements BTradesDAO {

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.report.BTradesDAO#findById(int
	 *                        )
	 *@MethodName: findById
	 *@Description: 根据id查询业务交易信息
	 *@Params: id 主键ID
	 *@Return: BTradesDTO 返回业务交易信息
	 *@throws: Exception 抛出异常
	 *@Author: 王少辉
	 *@Date: 2014-11-7 下午06:29:23
	 */
	@Override
	public BTradesDTO findById(int id) throws Exception {
		BTradesDTO dto = new BTradesDTO();

		BTrades bean = find(id);

		dto = (BTradesDTO) EntityDtoConverter.bean2Dto(bean, dto);

		return dto;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.report.BTradesDAO#queryAll(int
	 *                        , int, com.paySystem.ic.web.dto.report.BTradesDTO,
	 *                        java.util.LinkedHashMap)
	 *@MethodName: queryAll
	 *@Description: 分页查询业务交易报表
	 *@Params: firstindex 分页信息第一条记录索引
	 *@Params: maxresult 分页信息每页记录数
	 *@Params: bTradesDTO 保存分页信息
	 *@Params: orderby 排序条件
	 *@Return: QueryResult<BTradesDTO> 返回分页查询出的业务交易报表
	 *@throws: Exception 抛出异常
	 *@Author: 王少辉
	 *@Date: 2014-11-7 下午06:29:23
	 */
	@SuppressWarnings("unchecked")
	@Override
	public QueryResult<BTradesDTO> queryAll(int firstindex, int maxresult,
			BTradesDTO bTradesDTO, LinkedHashMap<String, String> orderby)
			throws Exception {

		// 查询业务交易报表
		List<Object> params = new ArrayList<Object>();
		String sql = "FROM BTrades o WHERE o.status = "
				+ bTradesDTO.getStatus();

		// 封装查询where条件
		StringBuilder sqlCond = new StringBuilder("");
		// 封装查询where条件一：交易流水号
		String tradesId = bTradesDTO.getTradesId();
		if (StringUtils.isNotBlank(tradesId)) {
			sqlCond.append(" and o.tradesId like ?").append(params.size() + 1);
			params.add("%" + tradesId + "%");
		}
		// 封装查询where条件二：会员编号
		String omemId = bTradesDTO.getOmemId();
		if (StringUtils.isNotBlank(omemId)) {
			sqlCond.append(" and o.omemId like ?").append(params.size() + 1);
			params.add("%" + omemId + "%");
		}
		// 封装查询where条件三：交易时间段
		String beginDate = bTradesDTO.getBeginDate();
		if (StringUtils.isNotBlank(beginDate)) {
			sqlCond.append(" and str_to_date(o.tradesTime,'%Y-%m-%d')"
					+ " >= '" + beginDate + "'");
		}
		String endDate = bTradesDTO.getEndDate();
		if (StringUtils.isNotBlank(endDate)) {
			sqlCond.append(" and str_to_date(o.tradesTime,'%Y-%m-%d')"
					+ " <= '" + endDate + "'");
		}
		sql = sql
				+ (null == sqlCond || "".equals(sqlCond.toString().trim()) ? ""
						: sqlCond.toString()) + buildOrderby(orderby);

		Query query = em.createQuery(sql);
		setQueryParams(query, params.toArray());
		// 设置分页信息
		if (firstindex != -1 && maxresult != -1) {
			query.setFirstResult(firstindex).setMaxResults(maxresult);
		}
		// 获取分页查询的数据
		List<BTrades> list = query.getResultList();

		// 封装返回结果
		QueryResult qr = new QueryResult<BTrades>();

		// 查询记录为空时直接返回
		if (CollectionUtils.isEmpty(list)) {
			return qr;
		}

		// 转换bean为dto
		BTradesDTO dto = null;
		List<BTradesDTO> dtoList = new ArrayList<BTradesDTO>();
		for (BTrades bt : list) {
			dto = new BTradesDTO();
			dto = (BTradesDTO) EntityDtoConverter.bean2Dto(bt, dto);
			dtoList.add(dto);
		}

		// 封装返回结果一：设置列表数据
		qr.setResultlist(dtoList);
		// 封装返回结果二：设置总记录数
		StringBuilder countSql = new StringBuilder(
				"SELECT count(o) FROM BTrades o  WHERE o.status = ");
		if (null != bTradesDTO && bTradesDTO.getStatus() != 1) {
			countSql.append(" 2 ");
		} else {
			countSql.append(" 1 ");
		}
		countSql.append((null == sqlCond
				|| "".equals(sqlCond.toString().trim()) ? "" : sqlCond
				.toString()));

		query = em.createQuery(countSql.toString());
		setQueryParams(query, params.toArray());
		qr.setTotalrecord((Long) query.getSingleResult());

		return qr;
	}

}
