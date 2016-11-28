package com.paySystem.ic.dao.base;

import com.paySystem.ic.bean.app.Code;
import com.paySystem.ic.dao.common.DAO;
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
	public CodeDTO findByTelNo(String telNo);

	/***
	 * 
	 *@Title:getCodeDTO
	 *@Description:将实体转化为DTO
	 *@param:@param code
	 *@param:@return
	 *@return:CodeDTO
	 *@author:井建国
	 *@thorws:
	 */
	public CodeDTO getCodeDTO(Code code);
	
	/***
	 * 
	 *@Title:getCodeDTO
	 *@Description:将DTO转换为实体
	 *@param:@param codeDTO
	 *@param:@return
	 *@return:Code
	 *@author:井建国
	 *@thorws:
	 */
	public Code getCodeDTO(CodeDTO codeDTO);

}
