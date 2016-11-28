/**  
* @Title: GoodsFamilyRateRelServiceImpl.java
* @Package: com.paySystem.ic.dao.base.impl
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-12-10 上午09:10:07
* @Version: V1.0  
*/
package com.paySystem.ic.service.base.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.dao.base.GoodsFamilyRateRelDao;
import com.paySystem.ic.dao.base.impl.GoodsFamilyRateRelDaoImpl;
import com.paySystem.ic.service.base.GoodsFamilyRateRelService;

/**
 * @ProjectName:omallBackstage
 * @ClassName:GoodsFamilyRateRelServiceImpl
 * @Description:商品分类 & 手续费率Service实现
 * @date: 2014-12-10
 * @author: 孟凡岭
 * @version: V1.0
 */
@Service(GoodsFamilyRateRelService.GOODSFAMILYRATERELSERVICE)
public class GoodsFamilyRateRelServiceImpl implements GoodsFamilyRateRelService {
	@Resource
	private GoodsFamilyRateRelDao goodsFamilyRateRel=new GoodsFamilyRateRelDaoImpl();

	public GoodsFamilyRateRelDao getGoodsFamilyRateRel() {
		return goodsFamilyRateRel;
	}

	public void setGoodsFamilyRateRel(GoodsFamilyRateRelDao goodsFamilyRateRel) {
		this.goodsFamilyRateRel = goodsFamilyRateRel;
	}
	
}
