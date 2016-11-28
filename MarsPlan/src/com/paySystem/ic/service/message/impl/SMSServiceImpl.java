package com.paySystem.ic.service.message.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.app.ShortMesSend;
import com.paySystem.ic.dao.message.SMSDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.message.SMSService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.message.ShortMesSendDTO;

/**
 * @ClassName:StockAdjustmentServiceImpl
 * @Description:库存微调信息Service实现类
 * @author: 张国法
 */
@Service(SMSService.SMSSERV)
public class SMSServiceImpl  extends DaoSupport<ShortMesSend> implements SMSService{
	//public static Logger log = Logger.getLogger(HeadQuinService.class);
	public static Logger log = Logger.getLogger(SMSService.class);
	//@Resource HeadQuinDao heDao;
	@Resource SMSDao smsDao;

	/**
	 * 根据条件查询库存变动信息
	 */
	public QueryResult<ShortMesSend> querySmsByCond(int firstindex,
			int maxresult, ShortMesSendDTO smsDTO,
			LinkedHashMap<String, String> orderby) throws Exception {
		return smsDao.querySmsByCond(firstindex, maxresult, smsDTO, orderby,0);
	}

	//添加
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSMSInfo(ShortMesSendDTO smsDTO) {
		smsDao.addSMSInfo(smsDTO);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO update(ShortMesSendDTO smsDTO){
		ReturnDTO dto = new ReturnDTO();
		try{
			smsDao.update(smsDTO);
			dto.setFlag(true);
		}catch(Exception e){
			e.printStackTrace();
			dto.setFlag(false);
		}
		return dto;
	}


	public ShortMesSend find(Integer smsId) {
		
		return smsDao.find(smsId);
	}
	
	public ShortMesSendDTO findShortMesSend(Integer smsId) {
		ShortMesSendDTO smsDto = null;
		smsDto = smsDao.findShortMesSend(smsId);
		return smsDto;
	}


}
