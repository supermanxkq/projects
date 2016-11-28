package com.paySystem.ic.dao.member.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.Members;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.member.MemberDAO;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.member.MembersDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsString;

/**
 * @ProjectName:omall
 * @ClassName:MemberDaoImpl
 * @Description:会员Dao实现类
 * @date: 2014-11-27上午09:49:59
 * @author: 徐凯强
 * @version: V1.0
 */
@Repository(MemberDAO.MEMBERDAO)
public class MemberDaoImpl extends DaoSupport<Members> implements MemberDAO {

	/**
	 *@MethodName:queryResult
	 *@Description:分页查询数据库中所有的会员信息
	 *@param fristindex首个分页参数
	 *@param pageNum每页含有多少个数据
	 *@param membersDTO会员数据传输对象
	 *@param orderBy排序参数
	 *@throws Exception抛出异常信息
	 *@Author:徐凯强
	 *@Date:2014-11-27上午09:29:11
	 */
	public QueryResult<MembersDTO> queryResult(int fristindex, int pageNum,
			MembersDTO membersDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		/** 封装查询where条件 **/
		StringBuilder sql = new StringBuilder();
		/** 查询参数 */
		List<Object> params = new ArrayList<Object>();
		/** 根据会员编号进行模糊查询 */
		if (membersDTO.getMemId() != null) {
			sql.append(" and o.memId= ?").append(params.size() + 1);
			/** 设置会员编号 **/
			params.add(membersDTO.getMemId());
		}

		/** 根据真实姓名 */
		if (StringUtils.isNotBlank(membersDTO.getRealName())) {
			sql.append(" and o.realName like ?").append(params.size() + 1);
			/** 设置会员名称 **/
			params.add("%" + membersDTO.getRealName().trim() + "%");
		}
		if (StringUtils.isNotBlank(membersDTO.getTeleNo())) {
			sql.append(" and o.teleNo like ?").append(params.size() + 1);
			/** 设置手机号码 **/
			params.add("%" + membersDTO.getTeleNo().trim() + "%");
		}
		/** 会员状态 1：正常 0;禁用 9：删除 */
		if (membersDTO.getStatus() != null && membersDTO.getStatus() != -1) {
			sql.append(" and o.status = ?").append(params.size() + 1);
			/** 设置会员状态 **/
			params.add(membersDTO.getStatus());
		}
		QueryResult<MembersDTO> membersDtos = new QueryResult<MembersDTO>();
		/** 获取数据库中所有的会员信息 */
		QueryResult<Members> queryResult = getScrollData(fristindex, pageNum,
				sql.toString(), params.toArray(), orderBy);
		/** 实例化list集合，将list集合中的数据进行处理 */
		List<Members> membersList = queryResult.getResultlist();
		/** 将实体list集合转换为DTOlist集合 */
		List<MembersDTO> membersDTOList = new ArrayList<MembersDTO>();
		/** 使用循环将list中的实体转换为DTO */
		for (Members members : membersList) {
			MembersDTO memberDTO = new MembersDTO();
			try {
				memberDTO = (MembersDTO) EntityDtoConverter.bean2Dto(members,
						memberDTO);
				membersDTOList.add(memberDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/** 设置queryResult对象 */
		membersDtos.setResultlist(membersDTOList);
		membersDtos.setTotalrecord(queryResult.getTotalrecord());
		/** 返回queryResultDTO集合 */
		return membersDtos;
	}

	/**
	 *@Title:updateMembers
	 *@Description:禁用会员
	 *@param membersDTO会员数据传输对象
	 *@Return:void空
	 *@author:徐凯强
	 *@Date:2014-11-26下午03:18:31
	 */
	public void updateMembers(MembersDTO membersDTO) {
		Members members = new Members();
		try {
			this.update(EntityDtoConverter.dto2Bean(membersDTO, members));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.member.MemberDAO#findMemOptionString
	 *                        (com.paySystem.ic.web.dto.member.MemberDTO,
	 *                        java.lang.Integer, java.lang.String)
	 *@MethodName:findMemOptionString
	 *@Description:获取不属于群组的会员和已经被选中的会员
	 *@param memDto
	 *@param groupId
	 *@param rightSelect
	 *@return
	 *@Author:张军磊
	 *@Date:2014-11-19下午07:00:36
	 */
	public List<OptionsString> findMemOptionString(MembersDTO memDto,
			Integer groupId, String rightSelect) {
		// TODO Auto-generated method stub
		UserSession us = Utils.getUserSession();
		List<Object> params = new ArrayList<Object>();// 参数设置
		StringBuilder sql = new StringBuilder(
				"select o.memid,o.userName,o.sex from m_members o where 1=1 ");
		switch (us.getUserLevel()) {
		case 1:
			sql.append(" and o.organId='" + us.getOrganId() + "'");
			break;
		}
		if (StringUtils.isNotBlank(memDto.getAreaId())
				&& !"-1".equals(memDto.getAreaId())) {
			sql.append(" and o.areaId = ?").append(params.size() + 1);
			params.add(memDto.getAreaId());
		}
		if (memDto.getSex() != null && memDto.getSex() != -1) {
			sql.append(" and o.sex = ?").append(params.size() + 1); // 设置会员性别
			params.add(memDto.getSex());
		}
		// if (StringUtils.isNotBlank(memDto.getMemId())) {
		// sql.append(" and o.memId like ?").append(params.size() + 1);
		// params.add("%" + memDto.getMemId() + "%");
		// }

		if (rightSelect != null && !rightSelect.equals("")) {
			sql.append(" and o.memid not in (" + rightSelect + ")");
		}
		Query query = em.createNativeQuery(sql.toString());
		DaoSupport.setQueryParams(query, params.toArray());
		List<Object[]> list = query.getResultList();
		List<OptionsString> listt = new ArrayList<OptionsString>();
		for (int i = 0; i < list.size(); i++) {
			Object[] o = list.get(i);
			listt.add(new OptionsString(o[0].toString(), o[1].toString()));
		}
		return listt;
	}

	/**
	 *@MethodName:findByOrgId
	 *@Description:根据机构号查询
	 *@param orgId
	 *@return
	 * @throws Exception 
	 *@Author:孟凡岭
	 *@Date:2014-11-27上午11:52:17
	 */
	public List<MembersDTO> findByOrgId(String orgId) throws Exception {
		// TODO Auto-generated method stub
		String sql = "from Members m where m.organs.organId='" + orgId + "'";
		List<Members> list = em.createQuery(sql).getResultList();
		List<MembersDTO> listt = getMemberDTOList(list);
		return listt;
	}

	/**
	*@Title:getMemberDTOList
	*@Description:TODO
	*@Params:@param list
	*@Params:@return
	*@Return:List<MembersDTO>
	*@author:孟凡岭
	*@Date:2014-11-27上午11:53:40
	*/
	private List<MembersDTO> getMemberDTOList(List<Members> list) throws Exception {
		// TODO Auto-generated method stub
		List<MembersDTO> dtoList=new ArrayList<MembersDTO>();
		if(list!=null){
			
			for (int i = 0; i < list.size(); i++) {
				Members m=list.get(i);
				dtoList.add((MembersDTO) EntityDtoConverter.bean2Dto(m, new MembersDTO()));
			}
		}
		return dtoList;
	}
}
