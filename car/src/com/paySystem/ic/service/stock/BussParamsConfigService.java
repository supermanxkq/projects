package com.paySystem.ic.service.stock;

import com.paySystem.ic.bean.stock.BussParamsConfig;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.BussParamsConfigDTO;

/**
 * @ProjectName:omall
 * @ClassName:BussParamsConfigService
 * @Description:商城业务参数配置的Service接口
 * @date: 2014-10-14
 * @author: 王楠
 * @version: V1.0
 */
public interface BussParamsConfigService extends DAO<BussParamsConfig>{
	
	public static final String BUSSPARAMSCONFIGSERVICE="bussParamsConfigService";
	
	/**
	*@Title:addSave
	*@Description:保存配置信息
	*@Params:@param bussParamsConfigDTO 业务参数配置信息
	*@Return:void
	*@author:王楠
	*@Date:2014-10-14下午03:54:42
	*/
	public void addSave(BussParamsConfigDTO bussParamsConfigDTO);

	/**
	*@Title:findList
	*@Description:查找数据的方法
	*@Params:@return
	*@Params:@throws Exception
	*@Return:BussParamsConfigDTO
	*@author:王楠
	*@Date:2014-10-15下午03:37:52
	*/
	public BussParamsConfigDTO findList() throws Exception ;
	
	/**
	*@Title:updateBussParamsConfig
	*@Description:修改业务参数配置信息
	*@Params:@param bussParamsConfigDTO 业务参实体的DTO
	*@Params:@return
	*@Return:ReturnDTO 封装好的返回DTO 方法
	*@author:王楠
	*@Date:2014-10-15下午03:48:36
	*/
	public ReturnDTO updateBussParamsConfig(BussParamsConfigDTO bussParamsConfigDTO);

}
