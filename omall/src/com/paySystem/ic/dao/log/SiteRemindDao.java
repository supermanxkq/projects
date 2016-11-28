package com.paySystem.ic.dao.log;

import com.paySystem.ic.bean.log.SiteRemind;
import com.paySystem.ic.service.common.DAO;



/**
 * @ProjectName: omall_back
 * @ClassName: SiteRemindDao
 * @Description: 网站提醒商户详细表接口
 * @date: 2014-11-10
 * @author: 廖晓远 
 * @version: V1.0
 */
public interface SiteRemindDao extends DAO<SiteRemind> {
	
	/**常量**/
	public static final String SITEREMINDDAO = "siteRemindDao";

}
