package com.paySystem.ic.service.base.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.dao.base.CodeDAO;
import com.paySystem.ic.service.base.CodeService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.CodeDTO;

@Repository(CodeService.CODESERVICE)
public class CodeServiceImpl implements CodeService,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource CodeDAO codeDAOImpl;
	
	/**
	 * 
	 *@Title:findByTelNo
	 *@Description:根据手机号查询手机验证码
	 *@param:@param telNo
	 *@param:@return
	 *@return:CodeServiceImpl
	 *@author:井建国
	 *@thorws:
	 *@father:@see com.paySystem.ic.service.base.CodeService#findByTelNo(java.lang.String)
	 */
	public CodeDTO findByTelNo(String telNo) throws Exception{

		return codeDAOImpl.findByTelNo(telNo);
	}
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.base.CodeService#findByEmail(java.lang.String)
	 *@MethodName:findByEmail
	 *@Description:根据邮箱查询验证码
	 *@param email
	 *@return
	 *@throws Exception
	 *@Author:毛智东
	 *@Date:2014-10-9下午09:03:31
	 */
	public CodeDTO findByEmail(String email) throws Exception {
		return codeDAOImpl.findByEmail(email);
	}
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.base.CodeService#saveCode(com.paySystem.ic.web.dto.base.CodeDTO)
	 *@MethodName:saveCode
	 *@Description:TODO
	 *@param codeDTO
	 *@throws Exception
	 *@Author:毛智东
	 *@Date:2014-10-9下午09:03:31
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO saveCode(CodeDTO codeDTO) throws Exception {
		return codeDAOImpl.saveCode(codeDTO);
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.base.CodeService#findByCodeId(int)
	 *@MethodName:findByCodeId
	 *@Description:TODO
	 *@param codeId
	 *@return
	 *@throws Exception
	 *@Author:毛智东
	 *@Date:2014-10-11下午03:26:28
	 */
	public CodeDTO findByCodeId(int codeId) throws Exception {
		return codeDAOImpl.findByCodeId(codeId);
	}


}
