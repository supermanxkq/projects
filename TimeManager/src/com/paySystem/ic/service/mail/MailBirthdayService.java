package com.paySystem.ic.service.mail;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.mail.MailBirthday;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.mail.MailBirthdayDTO;

/***
 * 
 * @ClassName:MailHolidayService
 * @TODO:节日邮件Service
 * @date: 2014-3-21上午11:27:16
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface MailBirthdayService extends DAO<MailBirthday> {
	public static final String MAILBIRTHDAYSERVICE = "mailBirthdayService";

	/***
	 * 
	 *@Title:queryResult
	 *@TODO:查询生日邮件内容信息
	 *@data:2014-3-24
	 *@param:@param page
	 *@param:@param pageNum
	 *@param:@param mailBirthdayDTO
	 *@param:@param orderby
	 *@param:@return
	 *@param:@throws Exception
	 *@return:QueryResult<MailBirthdayDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	QueryResult<MailBirthdayDTO> queryResult(int page, int pageNum,
			MailBirthdayDTO birthdayDTO, LinkedHashMap<String, String> orderby)
			throws Exception;

	/***
	 * 
	 *@Title:saveDTO
	 *@TODO:通过DTO保存数据
	 *@data:2014-3-24
	 *@param:@param mailBirthday
	 *@param:@throws Exception
	 *@return:void
	 *@author:孟凡岭
	 *@thorws:
	 */
	void saveDTO(MailBirthdayDTO mailBirthday) throws Exception;

	/***
	 * 
	 *@Title:findById
	 *@TODO:根据ID查询数据
	 *@data:2014-3-24
	 *@param:@param id
	 *@param:@return
	 *@return:MailBirthdayDTO
	 *@author:孟凡岭
	 *@thorws:
	 */
	MailBirthdayDTO findById(Integer id);

	/**
	 * 
	 *@Title:updateByDTO
	 *@TODO:通过DTO更新数据
	 *@data:2014-3-24
	 *@param:@param mailBirthday
	 *@param:@throws Exception
	 *@return:void
	 *@author:孟凡岭
	 *@thorws:
	 */
	void updateByDTO(MailBirthdayDTO mailBirthday) throws Exception;

	/**
	 * 
	 *@Title:queryAll
	 *@TODO:查询所有生日邮件内容
	 *@data:2014-3-24
	 *@param:@return
	 *@return:List<MailBirthdayDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	List<MailBirthdayDTO> queryAll();
}
