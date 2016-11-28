package com.paySystem.ic.service.mail;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.mail.MailHoliday;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.mail.MailHolidayDTO;

/***
 * 
 * @ClassName:MailHolidayService
 * @TODO:节日邮件Service
 * @date: 2014-3-21上午11:27:16
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface MailHolidayService extends DAO<MailHoliday> {
	public static final String MAILHOLIDAYSERVICE = "mailHolidayService";

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
	QueryResult<MailHolidayDTO> queryResult(int page, int pageNum,
			MailHolidayDTO mailHolidayDTO, LinkedHashMap<String, String> orderby)
			throws Exception;

	/***
	 * 
	 * @Title:saveDTO
	 * @TODO:通过DTO保存数据
	 * @date:2014-3-21
	 * @param:@param mailHolidayDTO
	 * @return:void
	 * @author:孟凡岭
	 */
	void saveDTO(MailHolidayDTO mailHolidayDTO) throws Exception;

	/***
	 * 
	 * @Title:findById
	 * @TODO:根据ID查询数据
	 * @date:2014-3-22
	 * @param:@param id
	 * @param:@return
	 * @return:MailHolidayDTO
	 * @author:孟凡岭
	 */
	MailHolidayDTO findById(Integer id);

	/**
	 * 
	 * @Title:updateByDTO
	 * @TODO:通过DTO更新数据
	 * @date:2014-3-22
	 * @param:@param mailHolidayDTO
	 * @return:void
	 * @author:孟凡岭
	 */
	void updateByDTO(MailHolidayDTO mailHolidayDTO) throws Exception;

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

	/**
	 * 
	 *@Title:updateByStatus
	 *@TODO:更新状态
	 *@data:2014-3-27
	 *@param:@param m
	 *@return:void
	 *@author:孟凡岭
	 *@thorws:
	 */
	void updateStatus(MailHolidayDTO m);
}
