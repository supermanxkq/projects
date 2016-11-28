package com.paySystem.ic.service.base.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.dao.base.CodeDAO;
import com.paySystem.ic.service.base.CodeService;
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
	public CodeDTO findByTelNo(String telNo) {

		return codeDAOImpl.findByTelNo(telNo);
	}

}
