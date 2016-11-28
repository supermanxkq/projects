package com.paySystem.ic.service.mail.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.mail.MailContent;
import com.paySystem.ic.bean.mail.MailRecord;
import com.paySystem.ic.dao.mail.MailContentDao;
import com.paySystem.ic.dao.mail.MailRecordDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.mail.MailContentService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.web.dto.mail.MailContentDTO;
import com.paySystem.ic.web.dto.mail.MailRecordDTO;

/***
 * 
 * @ClassName:MailContentServiceImpl
 * @TODO:邮件内容记录表Service接口实现类
 * @date: 2014-3-19下午02:12:02
 * @author: 孟凡岭
 * @version: V1.0
 */
@Service(MailContentService.MAILCONTENTSERVICE)
public class MailContentServiceImpl extends DaoSupport<MailContent> implements
		MailContentService {
	@Resource
	private MailRecordDao mailRecordDao;
	@Resource
	private MailContentDao mailContentDao;
	/**
	 * 通过DTO保存实体
	 *@Title:saveDTO
	 *@param:@param contentDTO
	 *@author:孟凡岭
	 *@thorws:
	 */
	@Transactional
	public void saveDTO(MailContentDTO contentDTO, MailRecordDTO mailRecordDTO) {
		// TODO Auto-generated method stub
		MailContent m=getMailContent(contentDTO);
		mailContentDao.save(m);
		mailRecordDao.save(getMailRecord(m,mailRecordDTO));
	}
	/***
	 * 通过ListDTO保存实体
	 *@Title:saveListDTO
	 *@param:@param contentDTO
	 *@param:@param list
	 *@author:孟凡岭
	 *@thorws:
	 */
	@Transactional
	public void saveListDTO(MailContentDTO contentDTO,List<MailRecordDTO> list) {
		// TODO Auto-generated method stub
		MailContent m=getMailContent(contentDTO);
		mailContentDao.save(m);
		mailRecordDao.saves(getMailRecord(m,list));
	}
	public MailRecord getMailRecord(MailContent content,
			MailRecordDTO mailRecordDTO) {
		// TODO Auto-generated method stub
		MailRecord m=new MailRecord();
		m.setFromMail(mailRecordDTO.getFromMail());
		m.setSendDate(DateTimeTool.dateFormat("yyyy-MM-dd", mailRecordDTO.getSendDate()));
		
		m.setToMail(mailRecordDTO.getToMail());
		m.setType(mailRecordDTO.getType());
		m.setOrgId(mailRecordDTO.getOrgId());
		m.setMailContent(content);
		return m;
	}
	public MailContent getMailContent(MailContentDTO contentDTO){
		MailContent m=new MailContent();
		m.setContent(contentDTO.getContent());
		m.setCreateTime(contentDTO.getCreateTime());
		m.setOrgId(contentDTO.getOrgId());
		m.setSubject(contentDTO.getSubject());
		return m;
	}

	public List<MailRecord> getMailRecord(MailContent m,
			List<MailRecordDTO> list) {
		List<MailRecord> listt=new ArrayList<MailRecord>();
		for (int i = 0; i < list.size(); i++) {
			MailRecord record=new MailRecord();
			record.setFromMail(list.get(i).getFromMail());
			record.setSendDate(DateTimeTool.dateFormat("yyyy-MM-dd", list.get(i).getSendDate()));
			record.setToMail(list.get(i).getToMail());
			record.setType(list.get(i).getType());
			record.setOrgId(list.get(i).getOrgId());
			record.setMailContent(m);
			listt.add(record);
		}
		return listt;
	}
}
