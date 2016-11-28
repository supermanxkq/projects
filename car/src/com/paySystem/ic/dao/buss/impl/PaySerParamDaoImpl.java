package com.paySystem.ic.dao.buss.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.PaySerParam;
import com.paySystem.ic.dao.buss.PaySerParamDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.buss.PaySerParamDTO;

/**
 * @ClassName:PaySerParamDaoImpl
 * @Description:支付接口参数配置Dao实现类
 * @date: 2014-7-10上午10:49:02
 * @author: 谢洪飞
 * @version: V1.0
 */
@Repository(PaySerParamDao.PAYSERPARAMDAO)
public class PaySerParamDaoImpl extends DaoSupport<PaySerParam> implements PaySerParamDao {

	
	/**
	 *@Title:savePayParam
	 *@Description: 保存支付接口参数信息
	 *@param:@param paySerParam 支付接口参数对象
	 *@Return:void  
	 *@author:      谢洪飞
	 *@Thorws:
	 */
	public void savePayParam(PaySerParamDTO paySerParamDTO) {
		
		PaySerParam payParam = this.DTO2Bean(paySerParamDTO);
		payParam.setIsEnable(1);
		
		if(paySerParamDTO.getPsId()==null){
			
			this.save(payParam);
		}else{
			
			this.update(payParam);
		}
		
	    
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
			PaySerParamDTO paySerParamDTO, LinkedHashMap<String, String> orderBy) throws Exception {
		
		QueryResult<PaySerParamDTO> dtoResult = new QueryResult<PaySerParamDTO>();
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>(); 
		
		if(StringUtils.isNotBlank(paySerParamDTO.getPsName())){
			sb.append(" and o.psName like ?").append(params.size()+1);
			params.add("'%"+paySerParamDTO.getPsName()+"%'");
		}
		
		QueryResult<PaySerParam> result =
			getScrollData(firstPage, pageNum, sb.toString(), params.toArray(),orderBy);
		
		dtoResult = this.dtoResult2beanResult(result);
		
		return dtoResult;
	}


	
	/**
	 *@Title:DTO2Bean
	 *@Description: Bean 转 DTO
	 *@param:@param payDto
	 *@param:@return
	 *@return:PaySerParam
	 *@author: 谢洪飞
	 *@Thorws:
	 */
	private PaySerParam DTO2Bean(PaySerParamDTO payDto){
		
		PaySerParam pay = new PaySerParam();
		
		if(payDto!=null){
		   pay.setPsId(payDto.getPsId());
		   pay.setPsName(payDto.getPsName());
		   pay.setAccountNo(payDto.getAccountNo());
		   pay.setCodSign(payDto.getCodSign());
		   pay.setCreateTime(this.getSysTime());
		   pay.setCurrency(payDto.getCurrency());
		   pay.setIsEnable(payDto.getIsEnable());
		   pay.setPayImgPath(payDto.getPayImgPath());
		   pay.setPayOnlineSign(payDto.getPayOnlineSign());
		   pay.setPayOrgId(payDto.getPayOrgId());
		   pay.setPsPwd(payDto.getPsPwd());
		   pay.setPsUrl(payDto.getPsUrl());
		   pay.setSecretKey(payDto.getSecretKey());
		   pay.setDescr(payDto.getDescr());
		   pay.setFeeRate(payDto.getFeeRate());
			
		}
		return pay;
	}
	
	
	/**
	 *@Title:bean2DTO
	 *@Description:实体Bean 转 DTO
	 *@param:@param pay
	 *@param:@return
	 *@return:PaySerParamDTO
	 *@author:
	 *@Thorws:
	 */
	private PaySerParamDTO bean2DTO(PaySerParam pay){
		
		PaySerParamDTO payDto = new PaySerParamDTO();
		
		if(pay!=null){
			
			payDto.setAccountNo(pay.getAccountNo());
			payDto.setCodSign(pay.getCodSign());
			payDto.setCreateTime(pay.getCreateTime());
			payDto.setCurrency(pay.getCurrency());
			payDto.setIsEnable(pay.getIsEnable());
			payDto.setPayImgPath(pay.getPayImgPath());
			payDto.setPayOnlineSign(pay.getPayOnlineSign());
			payDto.setPayOrgId(pay.getPayOrgId());
			payDto.setPayOrgName(Utils.getOptionsIntegerName(OptionsValue.PayOrgans, pay.getPayOrgId()));
			payDto.setPsId(pay.getPsId());
			payDto.setPsName(pay.getPsName());
			payDto.setPsPwd(pay.getPsPwd());
			payDto.setPsUrl(pay.getPsUrl());
			payDto.setSecretKey(pay.getSecretKey());
			payDto.setDescr(pay.getDescr());
			payDto.setFeeRate(pay.getFeeRate());
		
		}
		
		return payDto;
	}
	
	/**
	 *@Title:dtoResult2beanResult
	 *@Description:DTOResult 砖 实体BeanResult
	 *@param:@param result
	 *@param:@return
	 *@return:QueryResult<PaySerParamDTO>
	 *@author: 谢洪飞
	 *@Thorws:
	 */
	private QueryResult<PaySerParamDTO> dtoResult2beanResult(QueryResult<PaySerParam> result){
		
		QueryResult<PaySerParamDTO> dtoResult = new QueryResult<PaySerParamDTO>();
		List<PaySerParamDTO> dtoList = new ArrayList<PaySerParamDTO>();
		
		if(result!=null){
			
			for (PaySerParam payParam : result.getResultlist()){
				
				PaySerParamDTO payDto = new PaySerParamDTO();
			    payDto = bean2DTO(payParam); 
			    dtoList.add(payDto);
			}
		}
		
		dtoResult.setResultlist(dtoList);
		dtoResult.setTotalrecord(result.getTotalrecord());
		
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
		
		PaySerParam pay = new PaySerParam();
		PaySerParamDTO payDto = new PaySerParamDTO();
		
		pay = this.find(psId);
		payDto = this.bean2DTO(pay);
		
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
	public PaySerParamDTO delPayParam(Integer psId) throws Exception {
		
		PaySerParam payParam = this.find(psId);
		
		PaySerParamDTO payDto = this.bean2DTO(payParam);
		
		payParam.setIsEnable(0);
		
		this.update(payParam);
		
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
		
		
		
		String sql = "select pay.psId from s_payparam pay where pay.psName='"+psName+"'";
		Query query = em.createNativeQuery(sql);
		
		List<Object[]> payList = query.getResultList();
		
		return payList.size()<1;
	}



	public PaySerParam getParam(String psName) {
		StringBuilder sb = new StringBuilder();
		sb.append("select o from PaySerParam o where o.psName = '"+psName+"'");
		List<PaySerParam> payList= em.createQuery(sb.toString()).getResultList();
		
		return payList.size()<0?new PaySerParam():payList.get(0);
	}
}
