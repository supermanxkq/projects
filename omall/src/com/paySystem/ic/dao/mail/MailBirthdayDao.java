package com.paySystem.ic.dao.mail;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.mail.MailBirthday;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.mail.MailBirthdayDTO;

/***
 * 
 * @ClassName:MaiBirthdayDao
 * @TODO:生日邮件Dao
 * @date: 2014-3-24下午06:45:44
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface MailBirthdayDao extends DAO<MailBirthday> {
	public static final String MAILBIRTHDAYDAO = "maiBirthdayDao";

	/***
	 * 
	 *@Title:queryResult
	 *@TODO:查询数据
	 *@data:2014-3-24
	 *@param:@param page
	 *@param:@param pageNum
	 *@param:@param mailHolidayDTO
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
	 *@Title:queryAll
	 *@TODO:查询所有
	 *@data:2014-3-24
	 *@param:@return
	 *@return:List<MailBirthdayDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	List<MailBirthdayDTO> queryAll();

	/***
	 * 
	 *@Title:updateStatusOff
	 *@TODO:将启用状态关闭
	 *@data:2014-3-25
	 *@param:
	 *@return:void
	 *@author:孟凡岭
	 *@thorws:
	 */
	void updateStatusOff();
}
