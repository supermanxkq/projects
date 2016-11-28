package com.paySystem.ic.dao.mail.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.mail.MailHoliday;
import com.paySystem.ic.dao.mail.MailHolidayDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.mail.MailHolidayDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 
 * @ClassName:MailHolidayDaoImpl
 * @TODO:节日邮件Dao实现类
 * @date: 2014-3-21上午11:26:30
 * @author: 孟凡岭
 * @version: V1.0
 */
@Repository(MailHolidayDao.MAILHOLIDAYDAO)
public class MailHolidayDaoImpl extends DaoSupport<MailHoliday> implements
		MailHolidayDao {
	/***
	 * 
	 *@Title:queryResult
	 *@TODO:查询节日信息
	 *@param:@param page
	 *@param:@param pageNum
	 *@param:@param mailHolidayDTO
	 *@param:@param orderby
	 *@param:@return
	 *@author:孟凡岭
	 *@thorws:
	 */
	public QueryResult<MailHolidayDTO> queryResult(int page, int pageNum,
			MailHolidayDTO mailHolidayDTO, LinkedHashMap<String, String> orderby)
			throws Exception {
		/*** 封装查询where条件 */
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
		case 1:
			sql.append(" and o.orgId='" + us.getOrganId() + "'");
			break;
		}
		QueryResult<MailHoliday> queryResult = getScrollData(page, pageNum, sql
				.toString(), params.toArray(), orderby);
		List<MailHolidayDTO> list = getMailHolidayDTOList(queryResult
				.getResultlist());
		QueryResult<MailHolidayDTO> query = new QueryResult<MailHolidayDTO>();
		query.setResultlist(list);
		query.setTotalrecord(queryResult.getTotalrecord());
		return query;
	}

	/**
	 * 
	 *@Title:getMailHolidayDTOList
	 *@TODO:获取 MailHolidayDTOList集合
	 *@data:2014-3-21
	 *@param:@param resultlist
	 *@param:@return
	 *@return:List<MailHolidayDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<MailHolidayDTO> getMailHolidayDTOList(List<MailHoliday> list) {
		// TODO Auto-generated method stub
		List<MailHolidayDTO> listt = new ArrayList<MailHolidayDTO>();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				MailHolidayDTO m = new MailHolidayDTO();
				m.setHolidayDate(list.get(i).getHolidayDate());
				m.setHolidayName(list.get(i).getHolidayName());
				m.setSubject(list.get(i).getSubject());
				m.setId(list.get(i).getId());
				m.setOrgId(list.get(i).getOrgId());
				m.setStatus(list.get(i).getStatus());
				listt.add(m);
			}
		}

		return listt;
	}

	/**
	 * 
	 * @Title:queryAll
	 * @TODO:查询节日
	 * @date:2014-3-23
	 * @param:@return
	 * @author:孟凡岭
	 */
	@SuppressWarnings("unchecked")

	public List<MailHolidayDTO> queryAll() {

		String jpl = "select distinct t.* from m_mailholiday t,s_user s where t.status=0 and t.orgid=s.organid";
		List<Object[]> objList = em.createNativeQuery(jpl)
				.getResultList();
		
		List<MailHolidayDTO> list = new ArrayList<MailHolidayDTO>();
		for (int i = 0; i < objList.size(); i++) {
			MailHolidayDTO m = new MailHolidayDTO();
			Object[] obj = objList.get(i);
			m.setId(((BigDecimal)obj[0]).intValue());
			m.setContent((String) obj[1]);
			m.setHolidayDate((String) obj[2]);
			m.setHolidayName((String) obj[3]);
			m.setOrgId((String) obj[4]);
			m.setStatus(Integer.parseInt(obj[5].toString()));
			m.setSubject((String) obj[6]);
			list.add(m);
		}

		return list;
	}
}
