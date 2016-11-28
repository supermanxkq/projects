package com.paySystem.ic.dao.mail;

import java.util.LinkedHashMap;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.mail.MailRecord;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.mail.MailRecordDTO;

/***
 * 
 * @ClassName:MailRecordDao
 * @TODO:邮件记录DAO接口
 * @date: 2014-3-19上午10:47:11
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface MailRecordDao extends DAO<MailRecord> {
	public static final String MAILRECORDDAO = "mailRecordDao";

	/***
	 *@Title:queryResult
	 *@TODO:查询结果
	 *@data:2014-3-20
	 *@param:@param fristindex
	 *@param:@param pageNum
	 *@param:@param mailRecordDTO
	 *@param:@param orderby
	 *@param:@return
	 *@return:List<MailRecordDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	QueryResult<MailRecordDTO> queryResult(int fristindex, int pageNum,
			MailRecordDTO mailRecordDTO, LinkedHashMap<String, String> orderby) throws Exception;
}
