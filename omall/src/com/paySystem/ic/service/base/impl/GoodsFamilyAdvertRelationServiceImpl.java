/**  
 * @Title: GoodsFamilyAdvertRelationServiceImpl.java
 * @Package: com.paySystem.ic.service.base.impl
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-12-3 上午10:11:27
 * @Version: V1.0  
 */
package com.paySystem.ic.service.base.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.dao.base.GoodsFamilyAdvertRelationDao;
import com.paySystem.ic.dao.base.impl.GoodsFamilyAdvertRelationDaoImpl;
import com.paySystem.ic.service.base.GoodsFamilyAdvertRelationService;
import com.paySystem.ic.web.dto.base.GoodsFamilyAdvertRelationDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyAttrRelaDTO;

/**
 * @ProjectName:omallBackstage
 * @ClassName:GoodsFamilyAdvertRelationServiceImpl
 * @Description:TODO
 * @date: 2014-12-3
 * @author: 孟凡岭
 * @version: V1.0
 */
@Service(GoodsFamilyAdvertRelationService.GOODSFAMILYADVERTRELATIONSERVICE)
public class GoodsFamilyAdvertRelationServiceImpl implements
		GoodsFamilyAdvertRelationService {
	@Resource
	private GoodsFamilyAdvertRelationDao advertRelationDao = new GoodsFamilyAdvertRelationDaoImpl();

	public GoodsFamilyAdvertRelationDao getAdvertRelationDao() {
		return advertRelationDao;
	}

	public void setAdvertRelationDao(
			GoodsFamilyAdvertRelationDao advertRelationDao) {
		this.advertRelationDao = advertRelationDao;
	}
	/**
	 *@MethodName:findByFamilyId
	 *@Description:通过商品分类ID查询
	 *@param familyId
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-12-3上午09:37:17
	 */
	public GoodsFamilyAdvertRelationDTO findByFamilyId(Integer familyId)
			throws Exception {
		// TODO Auto-generated method stub
		
		return advertRelationDao.findByFamilyId(familyId);
	}
}
