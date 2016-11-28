package com.paySystem.ic.service.base;

import com.paySystem.ic.web.dto.base.CodeDTO;

public interface CodeService {
	
	public static final String CODESERVICE = "CODESERVICE";
	
	/**
	 * 
	 *@Title:findByTelNo
	 *@Description:根据电话号查询
	 *@param:@param telNo
	 *@param:@return
	 *@return:String
	 *@author:井建国
	 *@thorws:
	 */
	public CodeDTO findByTelNo(String telNo);

}
