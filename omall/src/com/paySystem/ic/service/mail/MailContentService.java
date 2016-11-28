package com.paySystem.ic.service.mail;

import java.util.List;

import com.paySystem.ic.bean.mail.MailContent;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.mail.MailContentDTO;
import com.paySystem.ic.web.dto.mail.MailRecordDTO;
/***
 * 
 * @ClassName:MailContentService
 * @TODO:邮件内容记录表Service接口
 * @date: 2014-3-19下午02:11:18
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface MailContentService extends DAO<MailContent>{
	public static final String MAILCONTENTSERVICE="mailContentService";
	/**
	 * 通过DTO保存实体
	 *@Title:saveDTO
	 *@TODO:TODO
	 *@data:2014-3-19
	 *@param:@param contentDTO
	 *@return:void
	 *@author:孟凡岭
	 * @param mailRecordDTO 
	 *@thorws:
	 */
	public void saveDTO(MailContentDTO contentDTO, MailRecordDTO mailRecordDTO);
	/***
	 * 通过DTOList保存实体
	 *@Title:saveListDTO
	 *@TODO:TODO
	 *@data:2014-3-19
	 *@param:@param contentDTO
	 *@param:@param list
	 *@return:void
	 *@author:孟凡岭
	 *@thorws:
	 */
	public void saveListDTO(MailContentDTO contentDTO,List<MailRecordDTO> list);
}
