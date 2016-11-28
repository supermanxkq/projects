package com.paySystem.ic.dao.stock;

import com.paySystem.ic.bean.stock.BussParamsConfig;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.BussParamsConfigDTO;

/**
 * @ProjectName:omall
 * @ClassName:BussParamsConfigDAO
 * @Description:商城业务参数配置的DAO接口
 * @date: 2014-10-14
 * @author: 王楠
 * @version: V1.0
 */
public interface BussParamsConfigDAO extends DAO<BussParamsConfig>{
	
	public static final String BUSSPARAMSCONFIGDAO="bussParamsConfigDAO";
	
	/**
	*@Title:addBussParamsConfig
	*@Description:添加业务参数配置信息
	*@Params:@param bussParamsConfigDTO
	*@Return:void
	*@author:王楠
	*@Date:2014-10-14下午02:45:46
	*/
	public void addBussParamsConfig(BussParamsConfigDTO bussParamsConfigDTO)throws Exception;

	/**
	*@Title:findList
	*@Description:查找数据的方法
	*@Params:@return
	*@Params:@throws Exception
	*@Return:BussParamsConfigDTO
	*@author:王楠
	*@Date:2014-10-15下午03:37:03
	*/
	public BussParamsConfigDTO findList() throws Exception;
	
	/**
	*@Title:updateBussParamsConfig
	*@Description:修改业务参数配置信息
	*@Params:@param bussParamsConfigDTO
	*@Params:@return
	*@Params:@throws Exception
	*@Return:ReturnDTO 封装好的返回方法
	*@author:王楠
	*@Date:2014-10-15下午03:41:51
	*/
	public ReturnDTO updateBussParamsConfig(BussParamsConfigDTO bussParamsConfigDTO)throws Exception;

}
