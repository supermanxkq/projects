package com.paySystem.ic.service.member.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.member.MemCardConn;
import com.paySystem.ic.dao.member.MemCardConnDAO;
import com.paySystem.ic.dao.member.impl.MemCardConnDAoImpl;
import com.paySystem.ic.service.member.MemCardConnService;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemCardConnDTO;

/**
 * @ProjectName:omall
 * @ClassName:MemCardConnServiceImpl
 * @Description:会员与卡关联表Service接口实现
 * @date: 2014-11-24
 * @author: 毛智东
 * @version: V1.0
 */
@Service(MemCardConnService.MEMCARDCONNSERVICE)
public class MemCardConnServiceImpl implements MemCardConnService {

	@Resource
	MemCardConnDAO memCardConnDAO=new MemCardConnDAoImpl();
	

	public MemCardConnDAO getMemCardConnDAO() {
		return memCardConnDAO;
	}

	public void setMemCardConnDAO(MemCardConnDAO memCardConnDAO) {
		this.memCardConnDAO = memCardConnDAO;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.service.member.MemCardConnService#
	 *                        add
	 *                        (com.paySystem.ic.web.dto.member.MemCardConnDTO)
	 *@MethodName:add
	 *@Description:添加方法
	 *@param memCardConnDTO
	 *@return
	 *@throws Exception
	 *@Author:毛智东
	 *@Date:2014-11-24下午03:27:56
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO add(MemCardConnDTO memCardConnDTO) throws Exception {
		ReturnDTO returnDTO = new ReturnDTO();
		MemCardConn bean = new MemCardConn();
		bean = (MemCardConn) EntityDtoConverter.dto2Bean(memCardConnDTO, bean);
		memCardConnDAO.save(bean);
		returnDTO.setFlag(true);
		return returnDTO;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.service.member.MemCardConnService#
	 *                        findById(java.lang.Integer)
	 *@MethodName:findById
	 *@Description:根据id查找对象
	 *@param connId
	 *@return
	 *@throws Exception
	 *@Author:毛智东
	 *@Date:2014-11-24下午03:27:56
	 */
	@Override
	public MemCardConnDTO findById(Integer connId) throws Exception {
		MemCardConn bean = memCardConnDAO.find(connId);
		MemCardConnDTO dto = new MemCardConnDTO();
		dto = (MemCardConnDTO) EntityDtoConverter.bean2Dto(bean, dto);
		return dto;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.service.member.MemCardConnService#
	 *                        findByMemId(java.lang.Integer)
	 *@MethodName:findByMemId
	 *@Description:根据会员编号查找对象
	 *@param memId
	 *@return
	 *@throws Exception
	 *@Author:毛智东
	 *@Date:2014-11-24下午03:27:56
	 */
	@Override
	public MemCardConnDTO findByMemId(Integer memId) throws Exception {
		return memCardConnDAO.findByMemId(memId);
	}




}
