package com.paySystem.ic.service.evaluation.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.evaluation.MerCreditTotal;
import com.paySystem.ic.dao.evaluation.MerCreditTotalDao;
import com.paySystem.ic.service.evaluation.MerCreditTotalService;
import com.paySystem.ic.utils.DateTimeTool;

/**
 * @ProjectName:omproject
 * @ClassName:MerCreditTotalServiceImpl
 * @Description:商户评价累计Service接口实现类
 * @date: 2014-11-28
 * @author: 毛智东
 * @version: V1.0
 */
@Service(MerCreditTotalService.MERCREDITTOTALSERVICE)
public class MerCreditTotalServiceImpl implements MerCreditTotalService {

	@Resource
	MerCreditTotalDao merCreditTotalDao;

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.evaluation.MerCreditTotalService
	 *                        #initMerCreditTotal(java.lang.String)
	 *@MethodName:initMerCreditTotal
	 *@Description:根据商户编号初始化对象
	 *@param merId
	 *@throws Exception
	 *@Author:毛智东
	 *@Date:2014-11-28下午07:05:19
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void initMerCreditTotal(String merId) throws Exception {
		merCreditTotalDao.deleteByMerId(merId);
		MerCreditTotal merCreditTotal = new MerCreditTotal();
		merCreditTotal.setMerId(merId);

		/** 当前时间 **/
		String currentTime = DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",
				new Date());

		/** 创建时间 **/
		merCreditTotal.setCreateTime(DateTimeTool.dateFormat(
				"yyyy-MM-dd HH:mm:ss", currentTime));
		merCreditTotal.setPjcount(0);
		merCreditTotal.setCreditImgId(1);
		merCreditTotal.setGoodsappTotal(BigDecimal.ZERO);
		merCreditTotal.setGoodsMatchTotal(BigDecimal.ZERO);
		merCreditTotal.setSendSpeadTotal(BigDecimal.ZERO);
		merCreditTotal.setServiceAttTotal(BigDecimal.ZERO);
		merCreditTotal.setLogisticsTotal(BigDecimal.ZERO);
		merCreditTotal.setCourierTotal(BigDecimal.ZERO);
		merCreditTotalDao.save(merCreditTotal);
	}

}
