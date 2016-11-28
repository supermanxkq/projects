package com.paySystem.ic.service.mail.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.mail.MailRecord;
import com.paySystem.ic.dao.mail.MailRecordDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.mail.MailRecordService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.web.dto.mail.MailRecordDTO;

/***
 * 邮件记录表Service接口实现类
 * 
 * @ClassName:MemberServiceImpl
 * @TODO:TODO
 * @date: 2014-3-19上午10:44:43
 * @author: 孟凡岭
 * @version: V1.0
 */
@Service(MailRecordService.MAILRECORDSERVICE)
public class MailRecordServiceImpl extends DaoSupport<MailRecord> implements
		MailRecordService {
	@Resource
	private MailRecordDao mailRecordDao;

	@Transactional
	public void saveDTO(MailRecordDTO recordDTO) {
		// TODO Auto-generated method stub
		mailRecordDao.save(getMailRecord(recordDTO));
	}

	@Transactional
	public void saveListDTO(List<MailRecordDTO> list) {
		// TODO Auto-generated method stub
		mailRecordDao.saves(getListMailRecord(list));
	}

	public MailRecord getMailRecord(MailRecordDTO recordDTO) {
		MailRecord record = new MailRecord();
		record.setFromMail(recordDTO.getFromMail());
		record.setSendDate(DateTimeTool.dateFormat("yyyy-MM-dd", recordDTO.getFromMail()));
		record.setToMail(recordDTO.getToMail());
		record.setType(recordDTO.getType());
		record.setOrgId(recordDTO.getOrgId());
		return record;
	}

	public List<MailRecord> getListMailRecord(List<MailRecordDTO> list) {
		List<MailRecord> listt = new ArrayList<MailRecord>();
		for (int i = 0; i < list.size(); i++) {
			MailRecord record = new MailRecord();
			record.setFromMail(list.get(i).getFromMail());
			record.setSendDate(DateTimeTool.dateFormat("yyyy-MM-dd", list.get(i).getSendDate()));
			record.setToMail(list.get(i).getToMail());
			record.setType(list.get(i).getType());
			record.setOrgId(list.get(i).getOrgId());
			listt.add(record);
		}
		return listt;
	}

	/***
	 * 查询结果
	 * 
	 * @Title:queryResult
	 *@param:@param i
	 *@param:@param pageNum
	 *@param:@param mailRecordDTO
	 *@param:@param orderby
	 *@param:@return
	 *@param:@throws Exception
	 *@author:孟凡岭
	 *@thorws:
	 */
	public QueryResult<MailRecordDTO> queryResult(int fristindex, int pageNum,
			MailRecordDTO mailRecordDTO, LinkedHashMap<String, String> orderby)
			throws Exception {
		// TODO Auto-generated method stub
		return mailRecordDao.queryResult(fristindex, pageNum,
				mailRecordDTO, orderby);
	}

}
