package com.paySystem.ic.dao.report.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.report.VisitSlowRecord;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.report.VisitSlowRecordDAO;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.report.VisitSlowRecordDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ProjectName:omall20140708DS
 * @ClassName:VisitSlowRecordDAOImpl
 * @Description:访客流量统计DAO实现类
 * @date: 2014-7-17
 * @author: 赵巧鹤
 * @version: V1.0
 */
@Repository(VisitSlowRecordDAO.VISITSLOWRECORDDAO)
public class VisitSlowRecordDAOImpl extends DaoSupport<VisitSlowRecord>
		implements VisitSlowRecordDAO {

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.report.VisitSlowRecordDAO#queryAll(int, int, com.paySystem.ic.web.dto.report.VisitSlowRecordDTO, java.util.LinkedHashMap)
	 *@MethodName:queryAll
	 *@Description:TODO
	 *@param page
	 *@param pageNum
	 *@param visitSlowRecordDTO
	 *@param orderBy
	 *@return
	 *@Author:赵巧鹤
	 *@Date:2014-7-17下午03:33:24
	 */
	@SuppressWarnings("unchecked")
	public QueryResult<VisitSlowRecordDTO> queryAll(int page, int pageNum,
			VisitSlowRecordDTO visitSlowRecordDTO,
			LinkedHashMap<String, String> orderBy) {
		//
		StringBuilder sql = new StringBuilder("select area,visitIp,accessTime,visitSource,entrancePage,count(o.memId),count(o.visitIp) from R_VisitorSlow o");
		
		if (StringUtils.isNotBlank(visitSlowRecordDTO.getBeginDate())) {
			sql.append(" and str_to_date(o.accessTime,'%Y-%m-%d') >= '"
					+ visitSlowRecordDTO.getBeginDate()
					+ "'");
		}
		if (StringUtils.isNotBlank(visitSlowRecordDTO.getEndDate())) {
			sql.append(" and str_to_date(o.accessTime,'%Y-%m-%d') <= '"
					+ visitSlowRecordDTO.getEndDate()
					+ "'");
		}
		UserSession us = Utils.getUserSession();
		
		switch(us.getUserLevel()){
		case 0:
			break;
		case 1:
			sql.append("and o.organId = "+us.getOrganId());
			break;
		case 2:
			sql.append("and o.merId = "+us.getMerId());
			break;
		}
		QueryResult<VisitSlowRecordDTO> results = new QueryResult<VisitSlowRecordDTO>();
		Query query = em.createNativeQuery(sql.toString());
		results.setTotalrecord(query.getResultList().size());
		/** 分页 */
		if (page != -1 && pageNum != -1) {
			query.setFirstResult(page);
			query.setMaxResults(pageNum);
		}
		List<Object[]> objList =query.getResultList();
		List<VisitSlowRecordDTO> recordList = new ArrayList<VisitSlowRecordDTO>();
		for (int i = 0; i < objList.size(); i++) {
			VisitSlowRecordDTO visiRecordDTO = new VisitSlowRecordDTO();
			Object[] obj = objList.get(i);
			visiRecordDTO.setArea(obj[0].toString());
			visiRecordDTO.setVisitIp(obj[1].toString());
			visiRecordDTO.setAccessTime((Date) obj[2]);
			visiRecordDTO.setVisitSource(obj[3].toString());
			visiRecordDTO.setEntrancePage(obj[4].toString());
			visiRecordDTO.setTotalMember((BigInteger)obj[5]);
			visiRecordDTO.setTotalIp((BigInteger)obj[6]);
			recordList.add(visiRecordDTO);
		}
		 results.setResultlist(recordList);
		System.out.println(sql);
		return results;
	}

		
	
}
