package com.paySystem.ic.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.app.Code;
import com.paySystem.ic.dao.base.CodeDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.CodeDTO;

@Repository(CodeDAO.CODEDAO)
public class CodeDAOImpl extends DaoSupport<Code> implements CodeDAO,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 *@Title:findByTelNo
	 *@Description:根据手机号查询DTO
	 *@param:@param telNo
	 *@param:@return
	 *@return:CodeDAOImpl
	 *@author:井建国
	 * @throws NoSuchMethodException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 *@thorws:
	 *@father:@see com.paySystem.ic.dao.base.CodeDAO#findByTelNo(java.lang.String)
	 */
	public CodeDTO findByTelNo(String telNo) throws Exception {
		CodeDTO codeDTO = new CodeDTO();
		String sql = "select o from Code o where o.telephone = ?1" ;
		Query query = em.createQuery(sql);
		query.setParameter(1, telNo);
		List<Code> list = query.getResultList();
		Code code = list.get(0);
		codeDTO = (CodeDTO) EntityDtoConverter.bean2Dto(code, codeDTO);
		return codeDTO;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.CodeDAO#findByEamil(java.lang.String)
	 *@MethodName:findByEamil
	 *@Description:根据邮箱查找codeDTO
	 *@param email
	 *@return
	 *@throws Exception
	 *@Author:毛智东
	 *@Date:2014-10-9下午08:47:29
	 */
	public CodeDTO findByEmail(String email) throws Exception {
		CodeDTO codeDTO = new CodeDTO();
		String sql = "select o from Code o where o.email = ?1" ;
		Query query = em.createQuery(sql);
		query.setParameter(1, email);
		List<Code> list = query.getResultList();
		Code code = list.get(0);
		codeDTO = (CodeDTO) EntityDtoConverter.bean2Dto(code, codeDTO);
		return codeDTO;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.CodeDAO#saveCode(com.paySystem.ic.web.dto.base.CodeDTO)
	 *@MethodName:saveCode
	 *@Description:添加方法
	 *@param codeDTO
	 *@throws Exception
	 *@Author:毛智东
	 *@Date:2014-10-9下午08:47:29
	 */
	public ReturnDTO saveCode(CodeDTO codeDTO) throws Exception {
		ReturnDTO returnDTO = new ReturnDTO();
		Code code = new Code();
		code = (Code) EntityDtoConverter.dto2Bean(codeDTO, code);
		save(code);
		CodeDTO dto = new CodeDTO();
		dto = (CodeDTO) EntityDtoConverter.bean2Dto(code, dto);
		returnDTO.setFlag(true);
		returnDTO.setObj(dto);
		return returnDTO;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.CodeDAO#findByCodeId(int)
	 *@MethodName:findByCodeId
	 *@Description:根据codeId查找codeDTO对象
	 *@param codeId
	 *@return
	 *@throws Exception
	 *@Author:毛智东
	 *@Date:2014-10-11下午03:23:59
	 */
	public CodeDTO findByCodeId(int codeId) throws Exception {
		Code code = find(codeId);
		CodeDTO dto = new CodeDTO();
		dto = (CodeDTO) EntityDtoConverter.bean2Dto(code, dto);
		return dto;
	}

	
}
