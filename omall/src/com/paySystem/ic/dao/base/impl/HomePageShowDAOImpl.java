package com.paySystem.ic.dao.base.impl;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.base.StoreInfo;
import com.paySystem.ic.bean.base.UndealServiceNum;
import com.paySystem.ic.dao.base.HomePageShowDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.base.HomePageShowDTO;
import com.paySystem.ic.web.dto.system.UserSession;

@Repository(HomePageShowDAO.HOMEPAGESHOWDAO)
public class HomePageShowDAOImpl extends DaoSupport<HomePageShowDTO>
                                  implements HomePageShowDAO{

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.HomePageShowDAO#findByOrgId(java.lang.String)
	 *@MethodName:findByOrgId
	 *@Description:根据机构号查询机构信息
	 *@param organId 机构编号
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-11-21上午11:47:25
	 */
	@Override
	public Organs findByOrgId(String organId) throws Exception {
		StringBuilder sql=new StringBuilder(
				" from Organs o where o.organId='"+organId+"'");
		Query query=em.createQuery(sql.toString());
		Organs org=(Organs) query.getSingleResult();
		return org;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.HomePageShowDAO#findByStoId(java.lang.String)
	 *@MethodName:findByStoId
	 *@Description:根据商户编号查询店铺信息
	 *@param merId 商户编号
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-11-21上午11:47:47
	 */
	@Override
	public StoreInfo findByStoId(String merId) throws Exception {
		StringBuilder sql=new StringBuilder(
				" from StoreInfo o where o.merId='"+merId+"'");
		Query query=em.createQuery(sql.toString());
		StoreInfo sto=(StoreInfo) query.getSingleResult();
		return sto;
	}

}
