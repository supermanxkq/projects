package com.paySystem.ic.dao.evaluation;

import com.paySystem.ic.bean.evaluation.CriticalContext;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.evaluation.CriticalContextDTO;


/**  
 * @Title: CriticalContext.java
 * @Package: com.paySystem.ic.dao.evaluation
 * @Description: 评论DAO
 * @Author: yanwuyang 
 * @Date: 2014-10-20 下午6:11:46
 * @Version: V1.0  
 */

public interface CriticalContextDao extends DAO<CriticalContext> {
	
	public final static String CRITICALCONTEXTDAO = "criticalContextDao";
	
	/**
	 * 
	 *@Title:save
	 *@Description:保存
	 *@Params:@param contextDTO
	 *@Return:void
	 *@author:yanwuyang
	 *@Date:2014-10-20下午09:04:53
	 */
	public void save(CriticalContextDTO contextDTO);

}
