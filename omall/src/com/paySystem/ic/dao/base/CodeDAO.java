package com.paySystem.ic.dao.base;

import com.paySystem.ic.bean.app.Code;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.CodeDTO;

/***
 * 
 * @ClassName:CodeDAO
 * @Description:手机验证码DAO
 * @date: 2014-5-28下午09:39:35
 * @author: 井建国
 * @version: V1.0
 */
public interface CodeDAO extends DAO<Code> {
	
	
	public static final String CODEDAO = "CODEDAO";
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
	public CodeDTO findByTelNo(String telNo)throws Exception;
	
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
