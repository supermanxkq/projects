package com.paySystem.ic.dao.order.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.order.ComplaintFiled;
import com.paySystem.ic.bean.order.ComplaintHandle;
import com.paySystem.ic.dao.order.ComplaintFiledDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.order.ComplaintFiledDTO;
import com.paySystem.ic.web.dto.order.ComplaintHandleDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ProjectName:omallBackstage
 * @ClassName:ComplaintFiledDaoImpl
 * @Description:TODO
 * @date: 2014-11-19
 * @author: 孟凡岭
 * @version: V1.0
 */
@Repository(ComplaintFiledDao.COMPLAINTFILEDDAO)
public class ComplaintFiledDaoImpl extends DaoSupport<ComplaintFiled> implements
		ComplaintFiledDao {

	/**
	 *@MethodName:queryResult
	 *@Description:分页查询
	 *@param first
	 *@param pageNum
	 *@param complaintFiledDTO
	 *@param orderby
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-11-19下午12:00:18
	 */
	public QueryResult<ComplaintFiledDTO> queryResult(int first, int pageNum,
			ComplaintFiledDTO complaintFiledDTO,
			LinkedHashMap<String, String> orderby) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		UserSession us = Utils.getUserSession();
		List<Object> params = new ArrayList<Object>();
		if (us.getUserLevel() == 2) {
			sql.append(" and o.storeId=?");
			params.add(us.getMerId());
		}
		if (complaintFiledDTO.getCompType() != -1) {
			sql.append(" and o.compType=?");
			params.add(complaintFiledDTO.getCompType());
		}
		if (StringUtils.isNotBlank(complaintFiledDTO.getStoreId())) {
			sql.append(" and o.storeId like ?");
			params.add("%" + complaintFiledDTO.getStoreId() + "%");
		}
		if (complaintFiledDTO.getFiledId() != null) {
			sql.append(" and o.filedId=?");
			params.add(complaintFiledDTO.getFiledId());
		}
		QueryResult<ComplaintFiled> queryResult = this.getScrollData(first,
				pageNum, sql.toString(), params.toArray(), orderby);
		QueryResult<ComplaintFiledDTO> query = new QueryResult<ComplaintFiledDTO>();
		query.setTotalrecord(queryResult.getTotalrecord());
		query.setResultlist(getDTOList(queryResult.getResultlist()));
		return query;
	}

	/**
	 *@Title:getDTOList
	 *@Description:实体转dtoList
	 *@Params:@param resultlist
	 *@Params:@return
	 *@Return:List<ComplaintFiledDTO>
	 *@author:孟凡岭
	 *@Date:2014-11-19下午02:23:00
	 */
	public List<ComplaintFiledDTO> getDTOList(List<ComplaintFiled> resultlist)
			throws Exception {
		// TODO Auto-generated method stub
		List<ComplaintFiledDTO> list = new ArrayList<ComplaintFiledDTO>();
		if (resultlist != null) {
			for (int i = 0; i < resultlist.size(); i++) {
				list.add((ComplaintFiledDTO) EntityDtoConverter.bean2Dto(
						resultlist.get(i), new ComplaintFiledDTO()));
			}
		}
		return list;
	}

	/**
	 *@MethodName:loadData
	 *@Description:查询详情
	 *@param complaintFiledDTO
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-11-20上午11:58:07
	 */
	@SuppressWarnings("unchecked")
	public ComplaintHandleDTO loadComplaint(ComplaintFiledDTO complaintFiledDTO)
			throws Exception {
		// TODO Auto-generated method stub

		ComplaintHandleDTO c = null;
		StringBuilder sb = new StringBuilder();
		sb
				.append("select mem.userName,fileds.filedId,  fileds.filedId as 'id', fileds.compTime,fileds.compType,fileds.compDesc,fileds.memId, info.compSta,");
		sb
				.append("info.manComSta,info.storeComDesc,info.handlSug,info.operator,info.updateTime,info.deductScore,");
		sb
				.append("info.vioRugleId as 'vioId',info.handlWay,ogr.goodsId,ogr.goodsName,store.storeName");
		sb
				.append(" from m_members mem,  b_complaintinfor info,B_ComplaintFiled fileds,O_OrderGoodsRel ogr,b_storeinfo store where 1=1");
		sb.append(" and store.storeId='" + complaintFiledDTO.getStoreId()+"'");
		sb
				.append(" and info.orderId='" + complaintFiledDTO.getOrderId()
						+ "' ");
		sb.append(" and info.orderId=fileds.orderId and info.filedId= "
				+ complaintFiledDTO.getFiledId());
		sb.append(" and ogr.orderId=info.orderId and mem.memId="
				+ complaintFiledDTO.getMemId());
		sb.append(" and fileds.filedId="+complaintFiledDTO.getFiledId());
		sb.append(" order by info.updateTime desc");
		List<ComplaintHandle> list = this.em.createNativeQuery(sb.toString(),
				ComplaintHandle.class).getResultList();
		if (list.size() > 0) {
			c = (ComplaintHandleDTO) EntityDtoConverter.bean2Dto(list.get(0),
					new ComplaintHandleDTO());
			c.setAdminHandlSug(c.getCompSta());
			Query query = this.em
					.createNativeQuery("select p.picPath from b_comppic p where p.complaintId="
							+ complaintFiledDTO.getFiledId());
			List<String> arr = query.getResultList();
			if (arr.size() > 0) {
				if (arr.get(0) != null) {
					c.setPicPath(arr.get(0).split("\\*"));
				}
			}
		}
		return c;
	}

}
