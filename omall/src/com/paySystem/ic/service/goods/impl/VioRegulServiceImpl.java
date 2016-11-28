/**  
* @Title: VioRegulServiceImpl.java
* @Package: com.paySystem.ic.service.goods.impl
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-11-21 下午04:16:53
* @Version: V1.0  
*/
package com.paySystem.ic.service.goods.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.goods.VioRegul;
import com.paySystem.ic.dao.goods.VioRegulDao;
import com.paySystem.ic.dao.goods.impl.VioRegulDaoImpl;
import com.paySystem.ic.service.goods.VioRegulService;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ProjectName:omallBackstage
 * @ClassName:VioRegulServiceImpl
 * @Description:TODO
 * @date: 2014-11-21
 * @author: 孟凡岭
 * @version: V1.0
 */
@Service(VioRegulService.VIOREGULSERVICE)
public class VioRegulServiceImpl implements VioRegulService{
	@Resource
	private VioRegulDao vioRegulDao=new VioRegulDaoImpl();

	public VioRegulDao getVioRegulDao() {
		return vioRegulDao;
	}

	public void setVioRegulDao(VioRegulDao vioRegulDao) {
		this.vioRegulDao = vioRegulDao;
	}

	/**
	 *@MethodName:find
	 *@Description:TODO
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-11-21下午04:20:00
	 */
	public List<OptionsInteger> find() throws Exception {
		// TODO Auto-generated method stub
		List<VioRegul> list=vioRegulDao.findAll();
		List<OptionsInteger> opt=new ArrayList<OptionsInteger>();
		for (int i = 0; i < list.size(); i++) {
			VioRegul v=list.get(i);
			opt.add(new OptionsInteger(v.getUnruleId(), v.getUnruleWay()));
		}
		return opt;
	}
	
}
