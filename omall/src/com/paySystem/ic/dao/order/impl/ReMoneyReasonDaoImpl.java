package com.paySystem.ic.dao.order.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.order.ReMoReason;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.order.ReMoneyReasonDao;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.order.ReMoReasonDTO;

/**
 * @ProjectName: omproject
 * @ClassName: ReMoneyReasonDaoImpl
 * @Description: 退款Dao实现
 * @date: 2014-11-12 下午05:54:48
 * @author: 王少辉
 * @version: V1.0
 */
@Repository(ReMoneyReasonDao.REMONEYREASONDAO)
public class ReMoneyReasonDaoImpl extends DaoSupport<ReMoReason> implements
		ReMoneyReasonDao {

	/**
	 *@Title: findById
	 *@Description: 根据主键ID查询退款详细
	 *@Params: id 主键ID
	 *@Return: ReMoReasonDTO 返回退款详细
	 *@throws: Exception 抛出异常
	 *@author: 王少辉
	 *@Date: 2014-11-12 下午06:10:31
	 */
	@Override
	public ReMoReasonDTO findById(int id) throws Exception {
		ReMoReasonDTO dto = new ReMoReasonDTO();
		ReMoReason bean = this.find(id);

		if (null != bean) {
			dto = (ReMoReasonDTO) EntityDtoConverter.bean2Dto(bean, dto);
		}

		return dto;
	}

	/**
	 *@Title: queryAll
	 *@Description: 分页查询退款信息
	 *@Params: firstindex 分页信息第一条记录索引
	 *@Params: maxresult 分页信息每页记录数
	 *@Params: reMoReasonDTO 保存分页信息
	 *@Params: orderBy 排序条件
	 *@Return: QueryResult<ReMoReasonDTO> 返回分页退款信息
	 *@throws: Exception 抛出异常
	 *@author: 王少辉
	 *@Date: 2014-11-12 下午06:12:46
	 */
	@SuppressWarnings("unchecked")
	@Override
	public QueryResult<ReMoReasonDTO> queryAll(int firstindex, int maxresult,
			ReMoReasonDTO reMoReasonDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		// 封装查询SQL
		StringBuilder sqlCond = new StringBuilder("");
		List<Object> params = new ArrayList<Object>();
		// 封装查询where条件一：订单编号
		String orderId = reMoReasonDTO.getOrderId();
		if (StringUtils.isNotBlank(orderId)) {
			sqlCond.append(" and o.orderId like ?").append(params.size() + 1);
			params.add("%" + orderId + "%");
		}
		// 封装查询where条件二：会员编号
		String memId = reMoReasonDTO.getMemId();
		if (StringUtils.isNotBlank(memId)) {
			sqlCond.append(" and o.memId like ?").append(params.size() + 1);
			params.add("%" + memId + "%");
		}
		// 封装查询where条件三：退款申请时间
		String beginDate = reMoReasonDTO.getBeginDate();
		if (StringUtils.isNotBlank(beginDate)) {
			sqlCond.append(" and str_to_date(o.reMoTime,'%Y-%m-%d')" + " >= '"
					+ beginDate + "'");
		}
		String endDate = reMoReasonDTO.getEndDate();
		if (StringUtils.isNotBlank(endDate)) {
			sqlCond.append(" and str_to_date(o.reMoTime,'%Y-%m-%d')" + " <= '"
					+ endDate + "'");
		}

		String sql = "FROM ReMoReason o";
		sql = sql
				+ (StringUtils.isBlank(sqlCond.toString()) ? "" : " where 1=1"
						+ sqlCond.toString()) + buildOrderby(orderBy);

		// 执行查询
		Query query = em.createQuery(sql);
		setQueryParams(query, params.toArray());
		if (firstindex != -1 && maxresult != -1) {
			query.setFirstResult(firstindex).setMaxResults(maxresult);
		}
		List<ReMoReason> list = query.getResultList();

		// 封装返回结果
		QueryResult qr = new QueryResult<ReMoReason>();

		// 查询记录为空时直接返回
		if (CollectionUtils.isEmpty(list)) {
			return qr;
		}

		// 转换bean为dto
		ReMoReasonDTO dto = null;
		List<ReMoReasonDTO> dtoList = new ArrayList<ReMoReasonDTO>();
		for (ReMoReason bean : list) {
			dto = new ReMoReasonDTO();
			dto = (ReMoReasonDTO) EntityDtoConverter.bean2Dto(bean, dto);
			dtoList.add(dto);
		}

		// 封装返回结果一：设置列表数据
		qr.setResultlist(dtoList);
		// 封装返回结果二：设置总记录数
		String countSql = "SELECT count(o) FROM ReMoReason o "
				+ (StringUtils.isBlank(sqlCond.toString()) ? "" : " where 1=1"
						+ sqlCond.toString());
		query = em.createQuery(countSql);
		setQueryParams(query, params.toArray());
		qr.setTotalrecord((Long) query.getSingleResult());

		return qr;
	}
	
}
