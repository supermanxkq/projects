package com.paySystem.ic.dao.payInterfaceConfig.Impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.payInterfaceConfig.PayInterConf;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.payInterfaceConfig.PayInterConfDAO;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.payInterfaceConfig.PayInterConfDTO;
@Repository(PayInterConfDAO.PAYINTERCONFDAO)
public class PayInterConfDAOImpl extends DaoSupport<PayInterConf>
                                           implements PayInterConfDAO{

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.payInterfaceConfig.PayInterConfDAO#queryAll(int, int, com.paySystem.ic.web.dto.payInterfaceConfig.PayInterConfDTO, java.util.LinkedHashMap)
	 *@MethodName:queryAll
	 *@Description:查询支付接口配置信息
	 *@param page 起始页
	 *@param pageNum 页容量
	 *@param payInterConfDTO 支付接口配置实体DTO
	 *@param orderBy 排序
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-9-19下午05:45:32
	 */
	public QueryResult<PayInterConfDTO> queryAll(int page, int pageNum,
			PayInterConfDTO payInterConfDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
            List<Object> params=new ArrayList<Object>();/**参数设置*/
            StringBuilder sql=new StringBuilder();
            /**判断页面条件*/
            if(StringUtils.isNotBlank(payInterConfDTO.getPsName())){
            	sql.append(" and o.psName like '%"+payInterConfDTO.getPsName()+"%'");
            }
            if(StringUtils.isNotBlank(payInterConfDTO.getPayOrgName())){
            	sql.append(" and o.payOrgName like '%"+payInterConfDTO.getPayOrgName()+"%'");
            }
           if(payInterConfDTO.getStatus()!=-1){
            	sql.append(" and o.status = "+payInterConfDTO.getStatus());
            }
            QueryResult<PayInterConf> queryResult=getScrollData(
            		page,pageNum,sql.toString(),params.toArray(),new LinkedHashMap<String,String>());
            List<PayInterConfDTO> listDTO=new ArrayList<PayInterConfDTO>();
            List<PayInterConf> list=queryResult.getResultlist();
            for(int i=0;i<list.size();i++){
            	/**遍历实体集合*/
            	PayInterConf payInterConf=list.get(i);
            	payInterConfDTO=getPayInterConfDTO(payInterConf);
            	listDTO.add(payInterConfDTO);
            }
            QueryResult<PayInterConfDTO> queryResultDTO=new QueryResult<PayInterConfDTO>();
    		queryResultDTO.setTotalrecord(queryResult.getTotalrecord());
    		queryResultDTO.setResultlist(listDTO);
    		return queryResultDTO;
	}
	
	/**
	*@Title:getPayInterConfDTO
	*@Description:实体转DTO
	*@Params:@param payInterConf 支付接口配置实体
	*@Params:@return
	*@Params:@throws Exception
	*@Return:PayInterConfDTO 支付接口配置DTO
	*@author:王楠
	*@Date:2014-9-19下午06:08:15
	*/
	@SuppressWarnings("unused")
	private PayInterConfDTO getPayInterConfDTO(PayInterConf payInterConf)throws Exception{
		PayInterConfDTO payInterConfDTO=new PayInterConfDTO();
		payInterConfDTO=(PayInterConfDTO)EntityDtoConverter.bean2Dto(payInterConf, payInterConfDTO);
		return payInterConfDTO;
	}
	
	/**
	*@Title:getPayInterConf
	*@Description:DTO转实体
	*@Params:@param payInterConfDTO 支付接口配置实体DTO
	*@Params:@return
	*@Params:@throws Exception
	*@Return:PayInterConf 支付接口配置实体
	*@author:王楠
	*@Date:2014-9-19下午06:11:44
	*/
	@SuppressWarnings("unused")
	private PayInterConf getPayInterConf(PayInterConfDTO payInterConfDTO) throws Exception{
		PayInterConf payInterConf=new PayInterConf();
		payInterConf=(PayInterConf)EntityDtoConverter.dto2Bean(payInterConfDTO, payInterConf);
		return payInterConf;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.payInterfaceConfig.PayInterConfDAO#savePayInterConf(com.paySystem.ic.web.dto.payInterfaceConfig.PayInterConfDTO)
	 *@MethodName:savePayInterConf
	 *@Description:保存接口配置信息
	 *@param payInterConfDTO 接口配置实体DTO
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-9-23上午11:02:16
	 */
	public void savePayInterConf(PayInterConfDTO payInterConfDTO)
			throws Exception {
		payInterConfDTO.setCreateTime(getSysTime());
		PayInterConf payInterConf=this.getPayInterConf(payInterConfDTO);
		payInterConf.setCreateTime(getSysTime());
		this.save(payInterConf);
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.payInterfaceConfig.PayInterConfDAO#queryPsName(java.lang.String)
	 *@MethodName:queryPsName
	 *@Description:查询接口名称是否存在
	 *@param psName 接口名称
	 *@return
	 *@Author:王楠
	 *@Date:2014-9-23下午05:53:43
	 */

	@SuppressWarnings("unchecked")
	public PayInterConf queryPsName(String psName) {
        StringBuilder sql=new StringBuilder(
        		"from PayInterConf o where o.psName='"+psName+"'");
        Query query=em.createQuery(sql.toString());
        List<PayInterConf> pay=query.getResultList();
        if(pay.size()==0){
        	return null;
        }
		return pay.get(0);
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.payInterfaceConfig.PayInterConfDAO#updatePayInterConf(com.paySystem.ic.web.dto.payInterfaceConfig.PayInterConfDTO)
	 *@MethodName:updatePayInterConf
	 *@Description:修改支付接口配置信息
	 *@param payInterConfDTO 支付接口配置实体的DTO
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-9-24下午12:05:37
	 */
	public ReturnDTO updatePayInterConf(PayInterConfDTO payInterConfDTO)
			throws Exception {
		PayInterConf payInterConf=this.getPayInterConf(payInterConfDTO);
		ReturnDTO returnDTO=new ReturnDTO();
		payInterConf.setUpdateTime(getSysTime());
		this.update(payInterConf);
		returnDTO.setFlag(true);
		return returnDTO;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.payInterfaceConfig.PayInterConfDAO#findById(java.lang.Integer)
	 *@MethodName:findById
	 *@Description:通过id查询接口信息
	 *@param psId 接口编号
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-9-24下午02:29:40
	 */
	public PayInterConfDTO findById(Integer psId) throws Exception {
        PayInterConf payInterConf=this.find(psId);
		return getPayInterConfDTO(payInterConf);
	}

}
