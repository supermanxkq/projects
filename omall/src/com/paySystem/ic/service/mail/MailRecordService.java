package com.paySystem.ic.service.mail;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.mail.MailRecord;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.mail.MailRecordDTO;
/***
 * 
 * @ClassName:MailRecordService
 * @TODO:邮件记录表Service接口
 * @date: 2014-3-19上午10:44:01
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface MailRecordService extends DAO<MailRecord>{
	public static final String MAILRECORDSERVICE="mailRecordService";
	/**
	 * 通过DTO保存实体
	 *@Title:saveDTO
	 *@TODO:TODO
	 *@data:2014-3-19
	 *@param:@param recordDTO
	 *@return:void
	 *@author:孟凡岭
	 *@thorws:
	 */
	public void saveDTO(MailRecordDTO recordDTO);
	/***
	 * 通过DTO list保存实体
	 *@Title:saveListDTO
	 *@TODO:TODO
	 *@data:2014-3-19
	 *@param:@param list
	 *@return:void
	 *@author:孟凡岭
	 *@thorws:
	 */
	public void saveListDTO(List<MailRecordDTO> list);
	/***
	 * 
	 *@Title:queryResult
	 *@TODO:查询结果
	 *@data:2014-3-20
	 *@param:@param i
	 *@param:@param pageNum
	 *@param:@param mailRecordDTO
	 *@param:@param orderby
	 *@param:@return
	 *@param:@throws Exception
	 *@return:List<MailRecordDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	public QueryResult<MailRecordDTO> queryResult(int page, int pageNum,
			MailRecordDTO mailRecordDTO,
			LinkedHashMap<String, String> orderby) throws Exception;
}
