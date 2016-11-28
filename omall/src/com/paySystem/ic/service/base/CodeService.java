package com.paySystem.ic.service.base;

import com.paySystem.ic.web.dto.ReturnDTO;
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
	public CodeDTO findByTelNo(String telNo) throws Exception;

	/**
	 * 
	*@Title:findByEmail
	*@Description:根据邮箱查找CodeDTO
	*@Params:@param email
	*@Params:@return
	*@Params:@throws Exception
	*@Return:CodeDTO
	*@author:毛智东
	*@Date:2014-10-9下午08:45:37
	 */
	public CodeDTO findByEmail(String email)throws Exception;
	
	/**
	 * 
	*@Title:saveCode
	*@Description:保存codeDTO
	*@Params:@param codeDTO
	*@Params:@throws Exception
	*@Return:void
	*@author:毛智东
	*@Date:2014-10-9下午08:47:11
	 */
	public ReturnDTO saveCode(CodeDTO codeDTO)throws Exception;
	
	/**
	 * 
	*@Title:findByCodeId
	*@Description:根据id查找CodeDTO对象
	*@Params:@param codeId
	*@Params:@return
	*@Params:@throws Exception
	*@Return:CodeDTO
	*@author:毛智东
	*@Date:2014-10-11下午03:23:25
	 */
	public CodeDTO findByCodeId(int codeId) throws Exception;

}
