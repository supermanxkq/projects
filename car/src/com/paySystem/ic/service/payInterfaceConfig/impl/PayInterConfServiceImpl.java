package com.paySystem.ic.service.payInterfaceConfig.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.payInterfaceConfig.PayInterConf;
import com.paySystem.ic.dao.payInterfaceConfig.PayInterConfDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.payInterfaceConfig.PayInterConfService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.payInterfaceConfig.PayInterConfDTO;

/**
 * @ProjectName:omall
 * @ClassName:PayInterConfServiceImpl
 * @Description:TODO
 * @date: 2014-9-22
 * @author: 王楠
 * @version: V1.0
 */
@Service(PayInterConfService.PAYINTERCONFSERVICE)
public class PayInterConfServiceImpl extends DaoSupport<PayInterConf>
                                             implements PayInterConfService{
	
	@Resource
	PayInterConfDAO payInterConfDAO;

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.payInterfaceConfig.PayInterConfService#queryAll(int, int, com.paySystem.ic.web.dto.payInterfaceConfig.PayInterConfDTO, java.util.LinkedHashMap)
	 *@MethodName:queryAll
	 *@Description:查询支付接口配置信息
	 *@param page 起始页
	 *@param pageNum 页容量
	 *@param payInterConfDTO 支付接口实体的DTO 
	 *@param orderBy 排序
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-9-22下午02:10:21
	 */
	public QueryResult<PayInterConfDTO> queryAll(int page, int pageNum,
			PayInterConfDTO payInterConfDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
            QueryResult<PayInterConfDTO> queryResult=payInterConfDAO.queryAll(page,
            		             pageNum, payInterConfDTO, orderBy);
		    return queryResult;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.payInterfaceConfig.PayInterConfService#addSave(com.paySystem.ic.web.dto.payInterfaceConfig.PayInterConfDTO)
	 *@MethodName:addSave
	 *@Description:保存
	 *@param payInterConfDTO 支付接口配置实体的DTO
	 *@Author:王楠
	 *@Date:2014-9-23上午11:09:24
	 */
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSave(PayInterConfDTO payInterConfDTO) {
		try {
			payInterConfDAO.savePayInterConf(payInterConfDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.payInterfaceConfig.PayInterConfService#queryPsName(java.lang.String, java.lang.Integer, java.lang.String)
	 *@MethodName:queryPsName
	 *@Description:查询接口名称是否存在
	 *@param psName 接口名称
	 *@param psId 接口编号
	 *@param method 方法
	 *@return
	 *@Author:王楠
	 *@Date:2014-9-23下午06:00:10
	 */
	public boolean queryPsName(String psName, Integer psId, String method) {
        @SuppressWarnings("unused")
		boolean falg=false;
        PayInterConf pay=payInterConfDAO.queryPsName(psName);
        if (method.equals("addSave")) {
        	if (pay==null) {
        		falg=true;
        	}
        }
        if (method.equals("editSave")) {
        	if (pay !=null) {
        		if (psId.equals(pay.getPsId())) {
        			falg=true;
        		} else {
        			falg=false;
        		}
        	} else {
        		  falg=true;
        	}
        }
		return falg;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.payInterfaceConfig.PayInterConfService#updatePayInterConf(com.paySystem.ic.web.dto.payInterfaceConfig.PayInterConfDTO)
	 *@MethodName:updatePayInterConf
	 *@Description:修改支付接口配置信息
	 *@param payInterConfDTO 支付接口配置实体的DTO
	 *@return
	 *@Author:王楠
	 *@Date:2014-9-24下午02:21:22
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO updatePayInterConf(PayInterConfDTO payInterConfDTO) {
        ReturnDTO returnDTO=new ReturnDTO();
        try {
			returnDTO=payInterConfDAO.updatePayInterConf(payInterConfDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return returnDTO;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.payInterfaceConfig.PayInterConfService#findById(java.lang.Integer)
	 *@MethodName:findById
	 *@Description:通过编号查找接口信息
	 *@param psId 接口编号
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-9-24下午02:33:04
	 */
	public PayInterConfDTO findById(Integer psId) throws Exception {
		return payInterConfDAO.findById(psId);
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.payInterfaceConfig.PayInterConfService#deletePayInterConf(java.lang.Integer)
	 *@MethodName:deletePayInterConf
	 *@Description:删除接口信息
	 *@param psId 接口编号
	 *@Author:王楠
	 *@Date:2014-9-24下午03:06:59
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deletePayInterConf(Integer psId) {
          PayInterConf payInterConf=payInterConfDAO.find(psId);		
          payInterConf.setUpdateTime(getSysTime());
          payInterConf.setStatus(9);
          this.update(payInterConf);
	}

}
