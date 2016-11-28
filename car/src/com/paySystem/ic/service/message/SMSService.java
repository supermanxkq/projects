package com.paySystem.ic.service.message;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.app.ShortMesSend;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.message.ShortMesSendDTO;



/**
 * @Description:短信参数Service
 * @author: 张国法
 */
public interface SMSService {
	
	public static final String SMSSERV = "smsService";
	/**
	 *@Title:queryStockByCond
	 *@Description:根据条件查询总部入库信息Service方法
	 */
	
	QueryResult<ShortMesSend> querySmsByCond(int firstindex,
			int maxresult, ShortMesSendDTO smsDto,
			LinkedHashMap<String, String> orderby) throws Exception;
	
	public void addSMSInfo(ShortMesSendDTO smsDto
			);
	
	public ShortMesSend find(Integer  smsId);
	
	public ReturnDTO update(ShortMesSendDTO smsDTO);
	
	public ShortMesSendDTO findShortMesSend(Integer stockId) ;

}
