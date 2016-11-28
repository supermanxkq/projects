package com.paySystem.ic.dao.goods;

import java.util.List;

import com.paySystem.ic.bean.goods.VioRegul;
import com.paySystem.ic.dao.common.DAO;

/**
 * @Title: VioRegulDao.java
 * @Package: com.paySystem.ic.dao.base
 * @Description: 违规案例
 * @Author: yanwuyang
 * @Date: 2014-8-27 下午5:06:06
 * @Version: V1.0
 */

public interface VioRegulDao extends DAO<VioRegul> {

	public static final String VIOREGUL = "vioRegulDao";

	/**
	 * 
	 *@Title:getVioRegulByTypeId
	 *@Description:根据违规类型id获取违规案例
	 *@Params:@param typeId
	 *@Params:@return
	 *@Return:List<VioRegul>
	 *@author:yanwuyang
	 *@Date:2014-8-27下午5:07:58
	 */
	public List<VioRegul> getVioRegulByTypeId(Integer typeId);

	/**
	*@Title:findAll
	*@Description:TODO
	*@Params:@return
	*@Return:List<VioRegul>
	*@author:孟凡岭
	*@Date:2014-11-21下午04:20:57
	*/
	public List<VioRegul> findAll();

}
