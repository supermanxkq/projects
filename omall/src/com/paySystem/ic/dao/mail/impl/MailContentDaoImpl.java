package com.paySystem.ic.dao.mail.impl;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.mail.MailContent;
import com.paySystem.ic.dao.mail.MailContentDao;
import com.paySystem.ic.service.common.DaoSupport;

/***
 * 邮件记录DAO接口实现类
 * 
 * @ClassName:MailRecordDaoImpl
 * @TODO:TODO
 * @date: 2014-3-19上午10:48:38
 * @author: 孟凡岭
 * @version: V1.0
 */
@Repository(MailContentDao.MAILCONTENTDAO)
public class MailContentDaoImpl extends DaoSupport<MailContent> implements
		MailContentDao {

}
