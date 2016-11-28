package com.paySystem.ic.dao.mail;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.mail.MailHoliday;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.mail.MailHolidayDTO;

/**
 * 
 * @ClassName:MailHolidayDao
 * @TODO:节日邮件Dao
 * @date: 2014-3-21上午11:26:56
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface MailHolidayDao extends DAO<MailHoliday> {
	public static final String MAILHOLIDAYDAO = "mailHolidayDao";

	/***
	 * 
	 *@Title:queryResult
	 *@TODO:查询节日信息
	 *@data:2014-3-21
	 *@param:@param page
	 *@param:@param pageNum
	 *@param:@param mailHolidayDTO
	 *@param:@param orderby
	 *@param:@return
	 *@return:QueryResult<MailHolidayDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	QueryResult<MailHolidayDTO> queryResult(int page, int pageNum, MailHolidayDTO mailHolidayDTO, LinkedHashMap<String, String> orderby) throws Exception;

	/**
	 * 
	 * @Title:queryAll
	 * @TODO:查询节日
	 * @date:2014-3-23
	 * @param:@return
	 * @return:List<MailHolidayDTO>
	 * @author:孟凡岭
	 */
	List<MailHolidayDTO> queryAll();
}
