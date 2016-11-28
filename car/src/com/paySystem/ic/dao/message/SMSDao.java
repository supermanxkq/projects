package com.paySystem.ic.dao.message;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.app.ShortMesSend;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.message.ShortMesSendDTO;

/**
 * @ClassName:MessParamDAO
 * @Description:TODO
 * @date: 2014-3-19上午11:48:33
 * @author: 张亚运
 * @version: V1.0
 */
public interface SMSDao extends DAO<ShortMesSend> {
	
	public static final String SMSDAO = "smsDAO";
	/**
	 *@Title:queryStockByCond
	 *@Description:根据条件查询库存微调信息
	 */
	
	QueryResult<ShortMesSend> querySmsByCond(int firstindex, int maxresult,
			ShortMesSendDTO smsDTO,
			LinkedHashMap<String, String> orderby,int flag) throws Exception;
	
	public void addSMSInfo(ShortMesSendDTO smsDTO);
	
	public ReturnDTO update(ShortMesSendDTO smsDTO);
	
	public ShortMesSendDTO findShortMesSend(Integer stockId) ;
	
}
