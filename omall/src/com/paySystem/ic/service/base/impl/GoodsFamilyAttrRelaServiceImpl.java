/**  
 * @Title: GoodsFamilyAttrRelaServiceImpl.java
 * @Package: com.paySystem.ic.service.base.impl
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-12-3 上午09:35:33
 * @Version: V1.0  
 */
package com.paySystem.ic.service.base.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.dao.base.GoodsFamilyAttrRelaDao;
import com.paySystem.ic.dao.base.impl.GoodsFamilyAttrRelaDaoImpl;
import com.paySystem.ic.service.base.GoodsFamilyAttrRelaService;
import com.paySystem.ic.web.dto.base.GoodsFamilyAttrRelaDTO;

/**
 * @ProjectName:omallBackstage
 * @ClassName:GoodsFamilyAttrRelaServiceImpl
 * @Description:TODO
 * @date: 2014-12-3
 * @author: 孟凡岭
 * @version: V1.0
 */
@Service(GoodsFamilyAttrRelaService.GOODSFAMILYATTRRELASERVICE)
public class GoodsFamilyAttrRelaServiceImpl implements
		GoodsFamilyAttrRelaService {
	@Resource
	GoodsFamilyAttrRelaDao goodsFamilyAttrRelaDao=new GoodsFamilyAttrRelaDaoImpl();
	
	public GoodsFamilyAttrRelaDao getGoodsFamilyAttrRelaDao() {
		return goodsFamilyAttrRelaDao;
	}

	public void setGoodsFamilyAttrRelaDao(
			GoodsFamilyAttrRelaDao goodsFamilyAttrRelaDao) {
		this.goodsFamilyAttrRelaDao = goodsFamilyAttrRelaDao;
	}

	

}
