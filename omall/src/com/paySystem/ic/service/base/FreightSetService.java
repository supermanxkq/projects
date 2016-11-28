package com.paySystem.ic.service.base;

import com.paySystem.ic.web.dto.base.FreightSetDTO;

/**
 * @ProjectName: omall_back
 * @ClassName: FreightSetService
 * @Description: 运费设置
 * @date: 2014-11-14
 * @author: 廖晓远 
 * @version: V1.0
 */
public interface FreightSetService {
	
	public static final String FREIGHTSETSERVICE = "freightSetService";

	/**
	*@Title: updateOrSave
	*@Description: 保存运费设置
	*@Params: @param freightSetDTO
	*@Params: @throws Exception
	*@Return: void
	*@author: 廖晓远 
	*@Date: 2014-11-14下午03:44:27
	*/
	public void updateOrSave(FreightSetDTO freightSetDTO) throws Exception;
	
	/**
	*@Title: findFreightSet
	*@Description: 查询运费设置
	*@Params: @return
	*@Params: @throws Exception
	*@Return: FreightSetDTO
	*@author: 廖晓远 
	*@Date: 2014-11-14下午03:44:30
	*/
	public FreightSetDTO findFreightSet() throws Exception;

}
