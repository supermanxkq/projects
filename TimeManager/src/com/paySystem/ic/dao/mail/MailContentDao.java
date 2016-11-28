package com.paySystem.ic.dao.mail;

import com.paySystem.ic.bean.mail.MailContent;
import com.paySystem.ic.dao.common.DAO;

/***
 * 
 * @ClassName:MailRecordDao
 * @TODO:邮件记录DAO接口
 * @date: 2014-3-19上午10:47:11
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface MailContentDao extends DAO<MailContent> {
	public static final String MAILCONTENTDAO="mailContentDao";
}
