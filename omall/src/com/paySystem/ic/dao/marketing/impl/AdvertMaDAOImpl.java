/**  
 * @Title: annountDAOImpl.java
 * @Package: com.paySystem.ic.dao.marketing.impl
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-9-9 下午02:02:37
 * @Version: V1.0  
 */
package com.paySystem.ic.dao.marketing.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.marketing.AdvertMa;
import com.paySystem.ic.dao.marketing.AdvertMaDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.marketing.AdvertMaDTO;

@Repository(AdvertMaDAO.ADVERTMA)
public class AdvertMaDAOImpl extends DaoSupport<AdvertMa> implements
		AdvertMaDAO {

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.marketing.AdvertMaDAO#queryResult
	 *                        (int, int,
	 *                        com.paySystem.ic.web.dto.marketing.AdvertMaDTO,
	 *                        java.util.LinkedHashMap)
	 *@MethodName:queryResult
	 *@Description:异步加载
	 *@param firstindex
	 *@param maxresult
	 *@param advertMaDTO
	 *@param orderby
	 *@return
	 *@Author:孙晓磊
	 *@Date:2014-9-21下午02:52:33
	 */
	public QueryResult<AdvertMaDTO> queryResult(int firstindex, int maxresult,
			AdvertMaDTO advertMaDTO, LinkedHashMap<String, String> orderby) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		if (!"-1".equals(advertMaDTO.getMediaType())
				&& -1 != advertMaDTO.getMediaType()) {
			sql.append(" and o.mediaType = ?").append(params.size() + 1);
			params.add(advertMaDTO.getMediaType());
		}
		if (StringUtils.isNotBlank(advertMaDTO.getAdvertName())) {
			sql.append(" and o.advertName like ?").append(params.size() + 1);
			params.add("%" + advertMaDTO.getAdvertName().trim() + "%");
		}
		QueryResult<AdvertMa> queryResult = null;
		try {
			queryResult = getScrollData(firstindex, maxresult, sql.toString(),
					params.toArray(), orderby);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<AdvertMaDTO> list = getAdvertMaList(queryResult.getResultlist());
		QueryResult<AdvertMaDTO> query = new QueryResult<AdvertMaDTO>();
		query.setResultlist(list);
		query.setTotalrecord(queryResult.getTotalrecord());
		return query;
	}

	/**
	 *@Title:getAdvertMaList
	 *@Description:实体转换
	 *@Params:@param advertMaList
	 *@Params:@return
	 *@Return:List<AdvertMaDTO>
	 *@author:孙晓磊
	 *@Date:2014-9-21下午02:52:48
	 */
	public List<AdvertMaDTO> getAdvertMaList(List<AdvertMa> advertMaList) {
		List<AdvertMaDTO> advertmadtolist = new ArrayList<AdvertMaDTO>();
		for (int i = 0; i < advertMaList.size(); i++) {
			AdvertMa advertma = advertMaList.get(i);
			AdvertMaDTO advertmadto = new AdvertMaDTO();
			if (advertma.getAdvertName() != null) {
				advertmadto.setAdvertName(advertma.getAdvertName());
			}
			if (advertma.getPositionTypeId() != null) {
				advertmadto.setPositionTypeId(advertma.getPositionTypeId());
			}
			if (advertma.getMediaType() != null) {
				advertmadto.setMediaType(advertma.getMediaType());
			}
			if (advertma.getBeginTime() != null) {
				advertmadto.setBeginTime(advertma.getBeginTime());
			}
			if (advertma.getEndTime() != null) {
				advertmadto.setEndTime(advertma.getEndTime());
			}
			if (advertma.getAdvertId() != null) {
				advertmadto.setAdvertId(advertma.getAdvertId());
			}
			advertmadtolist.add(advertmadto);

		}
		return advertmadtolist;

	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.marketing.AdvertMaDAO#addsaveAdver
	 *                        (com.paySystem.ic.web.dto.marketing.AdvertMaDTO)
	 *@MethodName:addsaveAdver
	 *@Description:添加方法
	 *@param adverMaDTO
	 *@Author:孙晓磊
	 *@Date:2014-9-21下午02:53:16
	 */
	public void addsaveAdver(AdvertMaDTO adverMaDTO)throws Exception {
		AdvertMa advertma=new AdvertMa();
		EntityDtoConverter.dto2Bean(adverMaDTO, advertma);
		this.save(advertma);
	}



	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.marketing.AdvertMaDAO#getAdvertMaDTO
	 *                        (java.lang.Integer)
	 *@MethodName:getAdvertMaDTO
	 *@Description:通过id获取dto
	 *@param annountId
	 *@return
	 *@Author:孙晓磊
	 *@Date:2014-9-21下午02:53:58
	 */
	public AdvertMaDTO getAdvertMaDTO(Integer annountId)throws Exception {
		AdvertMa advertMa = find(annountId);
		AdvertMaDTO advertMaDto=new AdvertMaDTO();
        EntityDtoConverter.bean2Dto(advertMa, advertMaDto);
		return advertMaDto;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.marketing.AdvertMaDAO#updateAdvertMa
	 *                        (com.paySystem.ic.web.dto.marketing.AdvertMaDTO)
	 *@MethodName:updateAdvertMa
	 *@Description:更新实体
	 *@param advertDTO
	 *@Author:孙晓磊
	 *@Date:2014-9-20下午05:00:54
	 */
	public void updateAdvertMa(AdvertMaDTO advertDTO)throws Exception {
		AdvertMa advertma=new AdvertMa();
		EntityDtoConverter.dto2Bean(advertDTO, advertma);
		this.update(advertma);
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.marketing.AdvertMaDAO#findAdvertManame
	 *                        (java.lang.String)
	 *@MethodName:findAdvertManame
	 *@Description:查询实体List
	 *@param AdvertManame
	 *@return
	 *@Author:孙晓磊
	 *@Date:2014-9-21下午03:22:50
	 */

	@SuppressWarnings("unchecked")
	public List<AdvertMa> findAdvertManame(String AdvertManame) {
		String jpl = null;
		jpl = "select o from AdvertMa o where o.advertName =?";
		Query query = em.createQuery(jpl);
		query.setParameter(1, AdvertManame);

		return query.getResultList();
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.marketing.AdvertMaDAO#findAdvertMaIsUse()
	 *@MethodName:findAdvertMaIsUse
	 *@Description:查询正在使用的广告信息
	 *@return
	 *@throws Exception
	 *@Author:张军磊
	 *@Date:2014-12-11上午11:23:45
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdvertMa> findAdvertMaIsUse() throws Exception {
		//今天的日期
		String currentTime = DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",
				new Date());
		DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", currentTime);
		String today = DateTimeTool
				.dateFormat(
						"yyyy-MM-dd HH:mm:ss",
						DateTimeTool
								.doDateFormatStringBeginningOfyyyymmdd000000(DateTimeTool
										.dateFormat("yyyy-MM-dd HH:mm:ss",
												currentTime)));
		StringBuilder sql = new StringBuilder(
				" select ad from AdvertMa ad where  ad.mediaType ='0' and ad.openType='0'  and ad.endTime >= '"+today+"'");
		//执行查询
		Query query = em.createQuery(sql.toString());		
		List<AdvertMa> advertMaList=query.getResultList();		
		return advertMaList;
	}
}
