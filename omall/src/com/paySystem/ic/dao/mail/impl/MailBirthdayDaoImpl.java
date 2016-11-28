package com.paySystem.ic.dao.mail.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.mail.MailBirthday;
import com.paySystem.ic.dao.mail.MailBirthdayDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.mail.MailBirthdayDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 
 * @ClassName:MailHolidayDaoImpl
 * @TODO:节日邮件Dao实现类
 * @date: 2014-3-21上午11:26:30
 * @author: 孟凡岭
 * @version: V1.0
 */
@Repository(MailBirthdayDao.MAILBIRTHDAYDAO)
public class MailBirthdayDaoImpl extends DaoSupport<MailBirthday> implements
MailBirthdayDao {
	/***
	 * 
	 *@Title:queryAll
	 *@TODO:查询所有数据
	 *@param:@return
	 *@author:孟凡岭
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public List<MailBirthdayDTO> queryAll() {
		// TODO Auto-generated method stub
		String jpl = "select distinct t.* from m_mailbirthday t,s_user s where t.status=0 and t.orgid=s.organid and t.status=0";
		List<Object[]> objList = em.createNativeQuery(jpl)
				.getResultList();
		List<MailBirthdayDTO> list = new ArrayList<MailBirthdayDTO>();
		Date date = null;
		for (int i = 0; i < objList.size(); i++) {
			MailBirthdayDTO m = new MailBirthdayDTO();
			Object[] obj = objList.get(i);
			m.setId(((BigDecimal) obj[0]).intValue());
			m.setContent(obj[1].toString());
			date = DateTimeTool.dateFormat("yyyy-MM-dd", obj[2].toString());
			m.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd", date));
			m.setOrgId(obj[3].toString());
			m.setStatus(((BigDecimal) obj[4]).intValue());
			m.setSubject(obj[5].toString());
			list.add(m);
		}
		return list;
	}

	/***
	 * 
	 *@Title:queryResult
	 *@TODO:查询数据
	 *@param:@param page
	 *@param:@param pageNum
	 *@param:@param mailHolidayDTO
	 *@param:@param orderby
	 *@param:@return
	 *@param:@throws Exception
	 *@author:孟凡岭
	 *@thorws:
	 */
	public QueryResult<MailBirthdayDTO> queryResult(int page, int pageNum,
			MailBirthdayDTO birthdayDTO, LinkedHashMap<String, String> orderby)
			throws Exception {
		// TODO Auto-generated method stub
		/*** 封装查询where条件 */
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
		case 1:
			sql.append(" and o.orgId='" + us.getOrganId() + "'");
			break;
		}
		QueryResult<MailBirthday> queryResult = getScrollData(page, pageNum,
				sql.toString(), params.toArray(), orderby);
		List<MailBirthdayDTO> list = getMailHolidayDTOList(queryResult
				.getResultlist());
		QueryResult<MailBirthdayDTO> query = new QueryResult<MailBirthdayDTO>();
		query.setResultlist(list);
		query.setTotalrecord(queryResult.getTotalrecord());
		return query;
	}

	private List<MailBirthdayDTO> getMailHolidayDTOList(List<MailBirthday> list) {
		// TODO Auto-generated method stub
		List<MailBirthdayDTO> listt = new ArrayList<MailBirthdayDTO>();
		for (int i = 0; i < list.size(); i++) {
			MailBirthday m = list.get(i);
			MailBirthdayDTO dto = new MailBirthdayDTO();
			dto.setId(m.getId());
			dto.setContent(m.getContent());
			dto.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd", m
					.getCreateTime()));
			dto.setOrgId(m.getOrgId());
			dto.setSubject(m.getSubject());
			dto.setStatus(m.getStatus());
			listt.add(dto);

		}
		return listt;
	}

	/***
	 * 
	 *@Title:updateStatusOff
	 *@TODO:将启用状态关闭
	 *@param:
	 *@author:孟凡岭
	 *@thorws:
	 */
	public void updateStatusOff() {
		// TODO Auto-generated method stub
		Query query =  em.createQuery("update MailBirthday m set m.status=1 where m.orgId='"+Utils.getUserSession().getOrganId()+"'");
		query.executeUpdate();
		
	}

}
