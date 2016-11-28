/**  
* @Title: ComplaintInforDao.java
* @Package: com.paySystem.ic.dao.order
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-11-21 下午03:25:02
* @Version: V1.0  
*/
package com.paySystem.ic.dao.order;

import com.paySystem.ic.bean.order.ComplaintInfor;
import com.paySystem.ic.dao.common.DAO;

/**
 * @ProjectName:omallBackstage
 * @ClassName:ComplaintInforDao
 * @Description:TODO
 * @date: 2014-11-21
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface ComplaintInforDao  extends DAO<ComplaintInfor>{
	public static final String COMPLAINTINFORDAO="complaintInforDao";

	/**
	*@Title:findByFiledId
	*@Description:根据投诉编号查询
	*@Params:@param filedId
	*@Params:@return
	*@Return:ComplaintInfor
	*@author:孟凡岭
	*@Date:2014-11-21下午03:28:05
	*/
	ComplaintInfor findByFiledId(Integer filedId) throws Exception;
}
