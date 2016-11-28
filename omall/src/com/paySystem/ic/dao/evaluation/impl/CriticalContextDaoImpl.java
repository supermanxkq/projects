package com.paySystem.ic.dao.evaluation.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.evaluation.CriticalContext;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.evaluation.CriticalContextDao;
import com.paySystem.ic.web.dto.evaluation.CriticalContextDTO;


/**  
 * @Title: CriticalContextDaoImpl.java
 * @Package: com.paySystem.ic.dao.evaluation.impl
 * @Description: 评论dao
 * @Author: yanwuyang 
 * @Date: 2014-10-20 下午6:12:43
 * @Version: V1.0  
 */

@Repository(CriticalContextDao.CRITICALCONTEXTDAO)
public class CriticalContextDaoImpl extends DaoSupport<CriticalContext>
		implements CriticalContextDao {

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.evaluation.CriticalContextDao#save(com.paySystem.ic.web.dto.evaluation.CriticalContextDTO)
	 *@MethodName:save
	 *@Description:保存
	 *@param contextDTO
	 *@Author:yanwuyang
	 *@Date:2014-10-20下午09:06:00
	 */
	public void save(CriticalContextDTO contextDTO) {
		CriticalContext context = new CriticalContext();
		BeanUtils.copyProperties(contextDTO, context);
		this.save(context);
	}

}
