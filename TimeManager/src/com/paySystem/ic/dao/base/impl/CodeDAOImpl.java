package com.paySystem.ic.dao.base.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.app.Code;
import com.paySystem.ic.dao.base.CodeDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.web.dto.base.CodeDTO;

@Repository(CodeDAO.CODEDAO)
public class CodeDAOImpl extends DaoSupport<Code> implements CodeDAO,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 *@Title:getCodeDTO
	 *@Description:TODO
	 *@param:@param codeDTO
	 *@param:@return
	 *@return:CodeDAOImpl
	 *@author:井建国
	 *@thorws:
	 *@father:@see com.paySystem.ic.dao.base.CodeDAO#getCodeDTO(com.paySystem.ic.web.dto.base.CodeDTO)
	 */
	public Code getCodeDTO(CodeDTO codeDTO) {
		Code code = new Code();
		code.setTelephone(codeDTO.getTelephone());
		code.setCodeNum(codeDTO.getCodeNum());
		return code;
	}
	
	/**
	 * 
	 *@Title:getCodeDTO
	 *@Description:TODO
	 *@param:@param code
	 *@param:@return
	 *@return:CodeDAOImpl
	 *@author:井建国
	 *@thorws:
	 *@father:@see com.paySystem.ic.dao.base.CodeDAO#getCodeDTO(com.paySystem.ic.bean.app.Code)
	 */
	public CodeDTO getCodeDTO(Code code){
		CodeDTO codeDTO = new CodeDTO();
		codeDTO.setTelephone(code.getTelephone());
		codeDTO.setCodeNum(code.getCodeNum());
		return null;
	}
	
	/**
	 * 
	 *@Title:findByTelNo
	 *@Description:根据手机号查询DTO
	 *@param:@param telNo
	 *@param:@return
	 *@return:CodeDAOImpl
	 *@author:井建国
	 *@thorws:
	 *@father:@see com.paySystem.ic.dao.base.CodeDAO#findByTelNo(java.lang.String)
	 */
	public CodeDTO findByTelNo(String telNo) {
		CodeDTO codeDTO = new CodeDTO();
		String sql = "select o from Code o where o.telephone = ?1" ;
		Query query = em.createQuery(sql);
		query.setParameter(1, telNo);
		List<Code> list = query.getResultList();
		Code code = list.get(0);
		codeDTO = getCodeDTO(code);
		return codeDTO;
	}

	

}
