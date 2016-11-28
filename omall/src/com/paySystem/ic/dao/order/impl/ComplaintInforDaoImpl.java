/**  
* @Title: ComplaintInforDaoImpl.java
* @Package: com.paySystem.ic.dao.order.impl
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-11-21 下午03:25:38
* @Version: V1.0  
*/
package com.paySystem.ic.dao.order.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.order.ComplaintInfor;
import com.paySystem.ic.dao.order.ComplaintInforDao;
import com.paySystem.ic.service.common.DaoSupport;

/**
 * @ProjectName:omallBackstage
 * @ClassName:ComplaintInforDaoImpl
 * @Description:TODO
 * @date: 2014-11-21
 * @author: 孟凡岭
 * @version: V1.0
 */
@Repository(ComplaintInforDao.COMPLAINTINFORDAO)
public class ComplaintInforDaoImpl extends DaoSupport<ComplaintInfor> implements ComplaintInforDao {

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.order.ComplaintInforDao#findByFiledId(java.lang.Integer)
	 *@MethodName:findByFiledId
	 *@Description:根据投诉编号查询
	 *@param filedId
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-11-21下午03:28:22
	 */
	@Override
	public ComplaintInfor findByFiledId(Integer filedId) throws Exception{
		// TODO Auto-generated method stub
		String jpl="from ComplaintInfor o where o.filedId="+filedId;
		List<ComplaintInfor> list=this.findByJpl(jpl);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
