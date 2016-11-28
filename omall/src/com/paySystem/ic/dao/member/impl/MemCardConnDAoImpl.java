package com.paySystem.ic.dao.member.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.member.MemCardConn;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.member.MemCardConnDAO;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.member.MemCardConnDTO;

/**
 * @ProjectName:omall
 * @ClassName:MemCardConnDAoImpl
 * @Description:会员与卡关联表DAO接口实现
 * @date: 2014-11-24
 * @author: 毛智东
 * @version: V1.0
 */
@Repository(MemCardConnDAO.MEMCARDCONNDAO)
public class MemCardConnDAoImpl extends DaoSupport<MemCardConn> implements
		MemCardConnDAO {

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.member.MemCardConnDAO#findByMemId
	 *                        (java.lang.Integer)
	 *@MethodName:findByMemId
	 *@Description:根据会员编号查找对象
	 *@param memId
	 *@return
	 *@throws Exception
	 *@Author:毛智东
	 *@Date:2014-11-24下午03:12:36
	 */
	@SuppressWarnings("unchecked")
	@Override
	public MemCardConnDTO findByMemId(Integer memId) throws Exception {
		MemCardConnDTO dto = new MemCardConnDTO();
		String sql = "select o from MemCardConn o where o.memId = ?1";
		Query query = em.createQuery(sql);
		query.setParameter(1, memId);
		List<MemCardConn> list = query.getResultList();
		for (MemCardConn bean : list) {
			dto = (MemCardConnDTO) EntityDtoConverter.bean2Dto(bean, dto);
		}
		return dto;
	}

}
