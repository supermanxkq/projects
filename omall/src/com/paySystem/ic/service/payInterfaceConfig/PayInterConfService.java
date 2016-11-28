package com.paySystem.ic.service.payInterfaceConfig;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.payInterfaceConfig.PayInterConf;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.payInterfaceConfig.PayInterConfDTO;

/**
 * @ProjectName:omall
 * @ClassName:PayInterConfService
 * @Description:支付接口配置的Service接口
 * @date: 2014-9-22
 * @author: 王楠
 * @version: V1.0
 */
public interface PayInterConfService extends DAO<PayInterConf>{

	public static final String PAYINTERCONFSERVICE="payInterConfService";
	
	/**
	*@Title:queryAll
	*@Description:查询支付接口配置信息
	*@Params:@param page 起始页
	*@Params:@param pageNum 页容量
	*@Params:@param payInterConfDTO 支付接口配置实体的DTO
	*@Params:@param orderBy 排序
	*@Params:@return
	*@Params:@throws Exception
	*@Return:QueryResult<PayInterConfDTO>
	*@author:王楠
	*@Date:2014-9-22上午11:55:00
	*/
	public QueryResult<PayInterConfDTO> queryAll(int page,int pageNum,
			PayInterConfDTO payInterConfDTO,
			LinkedHashMap<String,String> orderBy)throws Exception;
	
	/**
	*@Title:addSave
	*@Description:保存配置信息
	*@Params:@param payInterConfDTO 支付接口配置实体的DTO
	*@Return:void
	*@author:王楠
	*@Date:2014-9-23上午11:08:43
	*/
	public void addSave(PayInterConfDTO payInterConfDTO);
	
	/**
	*@Title:queryPsName
	*@Description:查询接口名称是否存在
	*@Params:@param psName 接口名称
	*@Params:@param psId 接口编号
	*@Params:@param method 方法
	*@Params:@return
	*@Return:boolean
	*@author:王楠
	*@Date:2014-9-23下午05:59:15
	*/
	public boolean queryPsName(String psName,Integer psId,String method);
	
	/**
	*@Title:updatePayInterConf
	*@Description:修改支付接口配置信息
	*@Params:@param payInterConfDTO 支付接口配置实体DTO
	*@Params:@return
	*@Return:ReturnDTO 封装好的返回DTO方法
	*@author:王楠
	*@Date:2014-9-24下午02:20:02
	*/
	public ReturnDTO updatePayInterConf(PayInterConfDTO payInterConfDTO);
	
	/**
	*@Title:findById
	*@Description:通过编号查找支付接口配置信息
	*@Params:@param psId 接口编号
	*@Params:@return
	*@Params:@throws Exception
	*@Return:PayInterConfDTO 支付接口配置实体的DTO
	*@author:王楠
	*@Date:2014-9-24下午02:32:05
	*/
	public PayInterConfDTO findById(Integer psId)throws Exception;
	
	/**
	*@Title:deletePayInterConf
	*@Description:删除接口信息
	*@Params:@param psId 接口编号
	*@Return:void
	*@author:王楠
	*@Date:2014-9-24下午03:06:24
	*/
	public void deletePayInterConf(Integer psId);
}
