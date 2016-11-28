package com.paySystem.ic.service.mail.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.mail.MailBirthday;
import com.paySystem.ic.dao.mail.MailBirthdayDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.mail.MailBirthdayService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.mail.MailBirthdayDTO;

/***
 * 
 * @ClassName:MailBirthdayServiceImpl
 * @TODO:生日邮件service实现类
 * @date: 2014-3-24下午06:45:58
 * @author: 孟凡岭
 * @version: V1.0
 */
@Service(MailBirthdayService.MAILBIRTHDAYSERVICE)
public class MailBirthdayServiceImpl extends DaoSupport<MailBirthday> implements MailBirthdayService {
	@Resource
	private MailBirthdayDao mailBirthdayDao;

	/***
	 * 
	 *@Title:findById
	 *@TODO:根据主键查询数据
	 *@param:@param id
	 *@param:@return
	 *@author:孟凡岭
	 *@thorws:
	 */
	public MailBirthdayDTO findById(Integer id) {
		MailBirthday m = mailBirthdayDao.find(id);
		return getMailBirthdayDTO(m);
		
	}

	/***
	 * 
	 *@Title:queryAll
	 *@TODO:查询所有数据
	 *@param:@return
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<MailBirthdayDTO> queryAll() {
		// TODO Auto-generated method stub
		return mailBirthdayDao.queryAll();
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
	public QueryResult<MailBirthdayDTO> queryResult(int page, int pageNum, MailBirthdayDTO birthdayDTO, LinkedHashMap<String, String> orderby) throws Exception {
		// TODO Auto-generated method stub
		return mailBirthdayDao.queryResult(page, pageNum, birthdayDTO, orderby);
	}

	/***
	 * 
	 *@Title:saveDTO
	 *@TODO:通过DTO保存实体
	 *@param:@param mailBirthday
	 *@param:@throws Exception
	 *@author:孟凡岭
	 *@thorws:
	 */
	@Transactional
	public void saveDTO(MailBirthdayDTO mailBirthday) throws Exception {
		// TODO Auto-generated method stub
		MailBirthday m=getMailBirthday(mailBirthday);
		if(m.getStatus()==0){
			mailBirthdayDao.updateStatusOff();
		}
		mailBirthdayDao.save(m);
	}

	/**
	 * 
	 * @Title:getMailBirthday
	 * @TODO:获取实体
	 * @date:2014-3-24
	 * @param:@param mailBirthday
	 * @param:@return
	 * @return:MailBirthday
	 * @author:孟凡岭
	 */
	public MailBirthday getMailBirthday(MailBirthdayDTO mailBirthday) {
		// TODO Auto-generated method stub
		MailBirthday m = new MailBirthday();
		m.setContent(mailBirthday.getContent());
		m.setCreateTime(this.getSysTime());
		m.setOrgId(Utils.getUserSession().getOrganId());
		m.setSubject(mailBirthday.getSubject());
		m.setStatus(mailBirthday.getStatus());
		return m;
	}
	/***
	 * 
	 * @Title:getMailBirthdayDTO
	 * @TODO:获取DTO
	 * @date:2014-3-24
	 * @param:@param m
	 * @param:@return
	 * @return:MailBirthdayDTO
	 * @author:孟凡岭
	 */
	public MailBirthdayDTO getMailBirthdayDTO(MailBirthday m) {
		// TODO Auto-generated method stub
		MailBirthdayDTO dto=new MailBirthdayDTO();
		dto.setId(m.getId());
		dto.setContent(m.getContent());
		dto.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd", m.getCreateTime()));
		dto.setOrgId(m.getOrgId());
		dto.setSubject(m.getSubject());
		dto.setStatus(m.getStatus());
		return dto;
	}
	/***
	 * 
	 *@Title:updateByDTO
	 *@TODO:通过DTO更新实体
	 *@param:@param mailBirthday
	 *@param:@throws Exception
	 *@author:孟凡岭
	 *@thorws:
	 */
	@Transactional
	public void updateByDTO(MailBirthdayDTO mailBirthday) throws Exception {
		MailBirthday m=new MailBirthday();
		m.setContent(mailBirthday.getContent());
		m.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd", mailBirthday.getCreateTime()));
		m.setId(mailBirthday.getId());
		m.setOrgId(Utils.getUserSession().getOrganId());
		m.setSubject(mailBirthday.getSubject());
		m.setStatus(mailBirthday.getStatus());
		if(m.getStatus()==0){
			mailBirthdayDao.updateStatusOff();
		}
		mailBirthdayDao.update(m);
	}

}
