package com.paySystem.ic.dao.mail.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.mail.MailRecord;
import com.paySystem.ic.dao.mail.MailRecordDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.mail.MailRecordDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/***
 * 邮件记录DAO接口实现类
 * 
 * @ClassName:MailRecordDaoImpl
 * @TODO:TODO
 * @date: 2014-3-19上午10:48:38
 * @author: 孟凡岭
 * @version: V1.0
 */
@Repository(MailRecordDao.MAILRECORDDAO)
public class MailRecordDaoImpl extends DaoSupport<MailRecord> implements
		MailRecordDao {
	/***
	 * 
	 *@Title:queryResult
	 *@TODO:查询结果
	 *@param:@param fristindex
	 *@param:@param pageNum
	 *@param:@param mailRecordDTO
	 *@param:@param orderby
	 *@param:@return
	 *@author:孟凡岭
	 *@thorws:
	 */
	public QueryResult<MailRecordDTO> queryResult(int fristindex, int pageNum,
			MailRecordDTO mailRecordDTO, LinkedHashMap<String, String> orderby)
			throws Exception {
		/*** 封装查询where条件 */
		
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		UserSession us=Utils.getUserSession();
		switch(us.getUserLevel()){
		case 1:
			sql.append(" and o.orgId='"+us.getOrganId()+"' ");
			break;
		}
		if(StringUtils.isNotBlank(mailRecordDTO.getSendDate())){
			sql.append("and to_date(to_char(o.sendDate,'yyyy-mm-dd'),'yyyy-mm-dd')= to_date('"+mailRecordDTO.getSendDate()+"','yyyy-mm-dd')");
		}
		QueryResult<MailRecord> queryResult = getScrollData(fristindex,
				pageNum, sql.toString(), params.toArray(), orderby);
		List<MailRecordDTO> list = getMailRecordList(queryResult
				.getResultlist());
		QueryResult<MailRecordDTO> query=new QueryResult<MailRecordDTO>();
		query.setResultlist(list);
		query.setTotalrecord(queryResult.getTotalrecord());
		return query;
	}

	/***
	 * 
	 *@Title:getMailRecordList
	 *@TODO:获取DTO List集合
	 *@data:2014-3-20
	 *@param:@param resultlist
	 *@param:@return
	 *@return:List<MailRecordDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<MailRecordDTO> getMailRecordList(List<MailRecord> list) {
		// TODO Auto-generated method stub
		List<MailRecordDTO> listt = new ArrayList<MailRecordDTO>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				MailRecordDTO m = new MailRecordDTO();
				m.setId(list.get(i).getId());
				m.setType(list.get(i).getType());
				m.setFromMail(list.get(i).getFromMail());
				m.setSendDate(DateTimeTool.dateFormat("yyyy-MM-dd", list.get(i).getSendDate()));
				m.setToMail(list.get(i).getToMail());
				listt.add(m);
			}
		}
		return listt != null && listt.size() > 0 ? listt : null;
	}

}
