package com.paySystem.ic.dao.base;

import com.paySystem.ic.bean.base.FreightSet;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.FreightSetDTO;

/**
 * @ProjectName: omall_back
 * @ClassName: FreightSetDAO
 * @Description: 运费设置
 * @date: 2014-11-14
 * @author: 廖晓远 
 * @version: V1.0
 */
public interface FreightSetDAO extends DAO<FreightSet> {
	
	/**常量**/
	public static final String FREIGHTSETDAO = "freightSetDAO";

	/**
	*@Title: updateOrSave
	*@Description: 保存运费设置
	*@Params: @param freightSetDTO
	*@Params: @throws Exception
	*@Return: void
	*@author: 廖晓远 
	*@Date: 2014-11-14下午03:35:55
	*/
	public void updateOrSave(FreightSetDTO freightSetDTO) throws Exception;

	/**
	*@Title: findFreightSet
	*@Description: 查询运费设置
	*@Params: @return
	*@Params: @throws Exception
	*@Return: FreightSetDTO
	*@author: 廖晓远 
	*@Date: 2014-11-14下午03:36:06
	*/
	public FreightSetDTO findFreightSet() throws Exception;
}
