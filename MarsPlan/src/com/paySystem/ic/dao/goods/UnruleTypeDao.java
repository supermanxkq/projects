package com.paySystem.ic.dao.goods;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.goods.UnruleTypeDTO;


/**  
 * @Title: UnruleTypeDao.java
 * @Package: com.paySystem.ic.dao.goods
 * @Description: TODO
 * @author:Jacky
 * @Date: 2014-8-27 下午4:47:15
 * @Version: V1.0  
 */

public interface UnruleTypeDao {

	public static final String UNRULETYPE = "unruleTypeDao";
	
	/**
	 * 
	 *@Title:getAllUnruleTypes
	 *@Description:获取所有的违规类型
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:QueryResult<UnruleTypeDTO>
	 *@author:Jacky
	 *@Date:2014-8-27下午4:48:59
	 */
	public QueryResult<UnruleTypeDTO> getAllUnruleTypes() throws Exception;
}
