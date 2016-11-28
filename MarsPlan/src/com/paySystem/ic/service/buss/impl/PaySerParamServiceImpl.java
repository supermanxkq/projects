package com.paySystem.ic.service.buss.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.PaySerParam;
import com.paySystem.ic.dao.buss.PaySerParamDao;
import com.paySystem.ic.service.buss.PaySerParamService;
import com.paySystem.ic.web.dto.buss.PaySerParamDTO;

@Service(PaySerParamService.PAYPARAMSERV)
public class PaySerParamServiceImpl implements PaySerParamService{

	@Resource
	PaySerParamDao payParamDao;

	/**
	 *@Title:savePayParam
	 *@Description: 保存支付接口参数信息
	 *@param:@param paySerParam 支付接口参数DTO对象
	 *@Return:void  
	 *@author:      谢洪飞
	 *@Thorws:
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void savePayParam(PaySerParamDTO paySerParamDTO) {
		
		payParamDao.savePayParam(paySerParamDTO);
		
	}
	
	/**
	 *@Title:queryPayParamByCond
	 *@Description:根据条件查询支付接口信息列表
	 *@param:@param firstPage 开始条数
	 *@param:@param pageNum   每页显示调试
	 *@param:@param paySerParamDTO PaySerParamDTO对象
	 *@param:@param orderBy   排序方式
	 *@param:@return
	 *@return:List<PaySerParamDTO> 返回DTO集合
	 *@author:  谢洪飞
	 * @throws Exception 
	 *@Thorws:
	 */
	public QueryResult<PaySerParamDTO> queryPayParamByCond(int firstPage, int pageNum,
			PaySerParamDTO paySerParamDTO, LinkedHashMap<String, String> orderBy) throws Exception{
		
		QueryResult<PaySerParamDTO> dtoResult =
			payParamDao.queryPayParamByCond(firstPage, pageNum, paySerParamDTO, orderBy);
		
		return dtoResult;
	}

	
	/**
	 *  获取支付参数信息
	 *@Title:findById
	 *@Description:根据Id获取支付参数信息Dto对象
	 *@param:@param psId
	 *@param:@return
	 *@return:PaySerParamDTO
	 *@author:谢洪飞
	 *@Thorws:
	 */
	public PaySerParamDTO findById(Integer psId) {
		
		PaySerParamDTO payDto = payParamDao.findById(psId);
		
		return payDto;
	}

	/**
	 *   删除支付参数信息（逻辑删除）
	 *@Title:delPayParam
	 *@Description:
	 *@param:@param psId
	 *@Return:void
	 *@author: 谢洪飞
	 *@Thorws:
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public PaySerParamDTO delPayParam(Integer psId) throws Exception {
		PaySerParamDTO payDto = payParamDao.delPayParam(psId);
		return payDto;
	}

	
	/**
	 *  检查名称是否已存在
	 *@Title:checkSameName
	 *@Description:  检查支付方式名称是否已存在，如存在返回 false
	 *@param:@param psName 支付方式名称
	 *@param:@return
	 *@Return:boolean
	 *@author: 谢洪飞
	 *@Thorws:
	 */
	public boolean checkSameName(String psName) {
		
		return payParamDao.checkSameName(psName);
	}

	public PaySerParam getParam(String psName) {
		return payParamDao.getParam(psName);
	}
}
