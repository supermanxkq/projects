package com.paySystem.ic.service.mail.impl;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.mail.MailHoliday;
import com.paySystem.ic.dao.mail.MailHolidayDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.mail.MailHolidayService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.mail.MailHolidayDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 
 * @ClassName:MailHolidayServiceImpl
 * @TODO:节日邮件Service实现类
 * @date: 2014-3-21上午11:27:26
 * @author: 孟凡岭
 * @version: V1.0
 */
@Service(MailHolidayService.MAILHOLIDAYSERVICE)
public class MailHolidayServiceImpl extends DaoSupport<MailHoliday> implements
		MailHolidayService {
	@Resource
	private MailHolidayDao mailHolidayDao;

	/**
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

		return mailHolidayDao.queryResult(page, pageNum, mailHolidayDTO,
				orderby);
	}

	/**
	 * 
	 * @Title:saveDTO
	 * @TODO:通过DTO保存数据
	 * @date:2014-3-21
	 * @param:@param mailHolidayDTO
	 * @author:孟凡岭
	 */
	@Transactional
	public void saveDTO(MailHolidayDTO mailHolidayDTO) throws Exception {
		// TODO Auto-generated method stub
		mailHolidayDao.save(getMailHoliday(mailHolidayDTO));
	}

	/**
	 * 
	 * @Title:getMailHoliday
	 * @TODO:获取getMailHoliday实体
	 * @date:2014-3-21
	 * @param:@param mailHolidayDTO
	 * @param:@return
	 * @return:Object
	 * @author:孟凡岭
	 */
	public MailHoliday getMailHoliday(MailHolidayDTO mailHolidayDTO)
			throws Exception {
		UserSession us = Utils.getUserSession();
		MailHoliday m = new MailHoliday();
		if (mailHolidayDTO.getId() != null) {
			m.setId(mailHolidayDTO.getId());
		}
		m.setContent(mailHolidayDTO.getContent());
		m.setHolidayDate(mailHolidayDTO.getHolidayDate());
		m.setHolidayName(mailHolidayDTO.getHolidayName());
		m.setOrgId(us.getOrganId());
		m.setSubject(mailHolidayDTO.getSubject());
		boolean flag = judgeDate(mailHolidayDTO.getHolidayDate());
		if (flag) {
			m.setStatus(0);
		} else {
			m.setStatus(1);
		}
		return m;
	}

	/***
	 * 
	 * @Title:judgeDate
	 * @TODO:判断日期是否已过
	 * @date:2014-3-22
	 * @param:@param holidayDate
	 * @param:@return
	 * @return:boolean
	 * @author:孟凡岭
	 */
	public boolean judgeDate(String holidayDate) {
		String sysDate = new SimpleDateFormat("MM-dd").format(this.getSysTime());
		int sysMonth = Integer.parseInt(sysDate.substring(0, 2));
		int sysDay = Integer.parseInt(sysDate.substring(3, 5));
		int holidayMonth = Integer.parseInt(holidayDate.substring(0, 2));
		int holidayDay = Integer.parseInt(holidayDate.substring(3, 5));
		if (holidayMonth > sysMonth) {
			return true;
		} else if (holidayMonth == sysMonth
				&& (holidayDay > sysDay || holidayDate == sysDate)) {
			return true;
		} else if (holidayMonth == sysMonth && holidayDay < sysDay) {
			return false;
		} else if (holidayMonth < sysMonth) {
			return false;
		}
		return false;
	}

	/***
	 * 
	 * @Title:findById
	 * @TODO:根据ID查询数据
	 * @date:2014-3-22
	 * @param:@param id
	 * @param:@return
	 * @author:孟凡岭
	 */
	public MailHolidayDTO findById(Integer id) {

		return getMailHolidayDTO(mailHolidayDao.find(id));
	}

	public MailHolidayDTO getMailHolidayDTO(MailHoliday holiday) {
		// TODO Auto-generated method stub
		MailHolidayDTO m = new MailHolidayDTO();
		m.setContent(holiday.getContent());
		m.setHolidayDate(holiday.getHolidayDate());
		m.setHolidayName(holiday.getHolidayName());
		m.setId(holiday.getId());
		m.setOrgId(holiday.getOrgId());
		m.setSubject(holiday.getSubject());
		m.setStatus(m.getStatus());
		return m;
	}

	/**
	 * 
	 * @Title:updateByDTO
	 * @TODO:通过DTO更新数据
	 * @date:2014-3-22
	 * @param:@param mailHolidayDTO
	 * @author:孟凡岭
	 */
	@Transactional
	public void updateByDTO(MailHolidayDTO mailHolidayDTO) throws Exception {
		// TODO Auto-generated method stub
		mailHolidayDao.update(getMailHoliday(mailHolidayDTO));
	}

	/**
	 * 
	 * @Title:queryAll
	 * @TODO:查询节日
	 * @date:2014-3-23
	 * @param:
	 * @author:孟凡岭
	 */
	public List<MailHolidayDTO> queryAll() {

		return mailHolidayDao.queryAll();
	}

	/**
	 * 
	 *@Title:updateByStatus
	 *@TODO:更新状态
	 *@param:@param m
	 *@author:孟凡岭
	 *@thorws:
	 */
	public void updateStatus(MailHolidayDTO m) {
		// TODO Auto-generated method stub
		MailHoliday en=new MailHoliday();
		en.setContent(m.getContent());
		en.setHolidayDate(m.getHolidayDate());
		en.setHolidayName(m.getHolidayName());
		en.setId(m.getId());
		en.setOrgId(m.getOrgId());
		en.setSubject(m.getSubject());
		en.setStatus(1);
		mailHolidayDao.update(en);
	}
}
