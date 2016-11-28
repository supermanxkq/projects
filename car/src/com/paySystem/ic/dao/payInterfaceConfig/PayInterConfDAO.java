package com.paySystem.ic.dao.payInterfaceConfig;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.payInterfaceConfig.PayInterConf;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.payInterfaceConfig.PayInterConfDTO;
/**
 * @ProjectName:omall
 * @ClassName:PayInterConfDAO
 * @Description:支付接口配置的DAO接口
 * @date: 2014-9-19
 * @author: 王楠
 * @version: V1.0
 */
public interface PayInterConfDAO extends DAO<PayInterConf>{
	
	public static final String PAYINTERCONFDAO="payInterConfDAO";
	
	/**
	*@Title:queryAll
	*@Description:查询支付接口配置信息
	*@Params:@param page 起始页
	*@Params:@param pageNum 页容量
	*@Params:@param payInterConfDTO 支付接口配置实体DTO
	*@Params:@param orderBy 排序
	*@Params:@return
	*@Params:@throws Exception
	*@Return:QueryResult<PayInterConfDTO>
	*@author:王楠
	*@Date:2014-9-19下午05:39:09
	*/
	public QueryResult<PayInterConfDTO> queryAll(int page,int pageNum,
			PayInterConfDTO payInterConfDTO,
			LinkedHashMap<String,String> orderBy)throws Exception;

	/**
	*@Title:savePayInterConf
	*@Description:保存接口配置信息
	*@Params:@param payInterConfDTO 支付接口配置实体的DTO
	*@Params:@throws Exception
	*@Return:void
	*@author:王楠
	*@Date:2014-9-23上午11:00:26
	*/
	public void savePayInterConf(PayInterConfDTO payInterConfDTO)throws Exception;
	
	/**
	*@Title:queryPsName
	*@Description:查询接口名称是否存在
	*@Params:@param psName 接口名称
	*@Params:@return
	*@Return:PayInterConf 支付接口配置实体
	*@author:王楠
	*@Date:2014-9-23下午05:52:20
	*/
	public PayInterConf queryPsName(String psName);
	
	/**
	*@Title:updatePayInterConf
	*@Description:修改支付接口配置信息
	*@Params:@param payInterConfDTO 支付接口配置实体的DTO
	*@Params:@return
	*@Params:@throws Exception
	*@Return:ReturnDTO
	*@author:王楠
	*@Date:2014-9-24下午12:04:41
	*/
	public ReturnDTO updatePayInterConf(PayInterConfDTO payInterConfDTO)throws Exception;
	
	/**
	*@Title:findById
	*@Description:通过id查找支付接口配置信息
	*@Params:@param psId 接口编号
	*@Params:@return
	*@Params:@throws Exception
	*@Return:PayInterConfDTO 支付接口配置实体的DTO 
	*@author:王楠
	*@Date:2014-9-24下午02:28:39
	*/
	public PayInterConfDTO findById(Integer psId)throws Exception;
}
