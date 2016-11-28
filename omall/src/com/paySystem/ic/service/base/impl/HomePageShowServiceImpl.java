package com.paySystem.ic.service.base.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.base.StoreInfo;
import com.paySystem.ic.bean.base.UndealServiceNum;
import com.paySystem.ic.dao.base.HomePageShowDAO;
import com.paySystem.ic.service.base.HomePageShowService;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.web.dto.base.HomePageShowDTO;

/**
 * @ProjectName:omall
 * @ClassName:HomePageShowServiceImpl
 * @Description:首页内容展示的Service实现类
 * @date: 2014-11-21
 * @author: 王楠
 * @version: V1.0
 */
@Service(HomePageShowService.HOMEPAGESHOWSERVICE)
public class HomePageShowServiceImpl extends DaoSupport<HomePageShowDTO>
                                                   implements HomePageShowService{

	@Resource
	HomePageShowDAO homePageShowDAO;
	
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.base.HomePageShowService#findByOrgId(java.lang.String)
	 *@MethodName:findByOrgId
	 *@Description:根据机构编号查询机构信息
	 *@param organId 机构编号
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-11-21下午02:42:09
	 */
	@Override
	public Organs findByOrgId(String organId) throws Exception {
		Organs organs=homePageShowDAO.findByOrgId(organId);
		return organs;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.base.HomePageShowService#findByStoId(java.lang.String)
	 *@MethodName:findByStoId
	 *@Description:根据商户编号查询店铺信息
	 *@param merId 商户编号
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-11-21下午02:42:38
	 */
	@Override
	public StoreInfo findByStoId(String merId) throws Exception {
		StoreInfo storeInfo=homePageShowDAO.findByStoId(merId);
		return storeInfo;
	}

}
