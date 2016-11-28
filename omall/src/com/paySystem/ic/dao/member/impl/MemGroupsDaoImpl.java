package com.paySystem.ic.dao.member.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.member.MemGroups;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.member.MemGroupsDAO;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.member.MemGroupsService;
import com.paySystem.ic.service.member.MemberService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemGroupsDTO;
import com.paySystem.ic.web.dto.member.MemberGroupsDetailDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsString;

@Component
public class MemGroupsDaoImpl extends DaoSupport<MemGroups> implements
		MemGroupsDAO {
	MemberGroupsDetailDTO memberGroupsDetailDTO = new MemberGroupsDetailDTO();
	MemGroupsDTO memGroupsDTO = new MemGroupsDTO();
	@Resource
	MemGroupsService memGroupsService;
	@Resource
	OrgansService organsServiceImpl;
	@Resource
	MemberService memberService;

	/***
	 * 删除会员群组信息
	 * 
	 * @see com.paySystem.ic.dao.member.MemGroupsDAO#delete(java.lang.String[])
	 * @Description:TODO
	 * @date: 2014-3-28下午07:03:39
	 * @author: 解文侠
	 * @version: V1.0
	 * @param ids
	 */
	public void delete(String[] ids) {
		try {
			for (int i = 0; i < ids.length; i++) {
				ids[i] = Utils.getString(memGroupsDTO.getGroupId());
			}
			super.delete(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 *@MethodName:queryResult
	 *@Description:查询数据库中的群组的信息，用于群组管理查看和群组邮件发送查看,根据页面上是否传过来状态值进行区分
	 *@param firstindex分页的首个参数
	 *@param maxresult每页显示多少条数据
	 *@param memGroupsDTO会员群组数据传输对象
	 *@param orderby排序参数
	 *@throws Exception抛出异常信息
	 *@Author:解文侠、徐凯强
	 *@Date:2014-3-28下午07:03:52
	 *@Date:2014-9-15下午02:18:09
	 */
	public QueryResult<MemGroups> queryResult(int firstindex, int maxresult,
			MemGroupsDTO memGroupsDTO, LinkedHashMap<String, String> orderby)
			throws Exception {
		StringBuilder sql = new StringBuilder(); // 封装查询where条件
		List<Object> params = new ArrayList<Object>();
		UserSession us = Utils.getUserSession();
		/**
		 *设置权限
		 */
		switch (us.getUserLevel()) {
		case 0:
			break;
		case 1:
			sql.append(" and o.organs.organId = '" + us.getOrganId() + "' ");
			break;
		case 2:
			sql.append(" and o.merId = '" + us.getMerId() + "' ");
		default:
			break;
		}
		/** 判断过滤条件，如果无过滤条查询全部数据 */
		if (StringUtils.isNotBlank(memGroupsDTO.getGroupName())) {
			sql.append(" and o.groupName like ?").append(params.size() + 1);
			// 设置会员群组编号
			params.add("%" + memGroupsDTO.getGroupName().trim() + "%");
		}
		if (StringUtils.isNotBlank(memGroupsDTO.getName())) {
			sql.append(" and o.organs.name like ?").append(params.size() + 1);
			// 设置机构名称
			params.add("%" + memGroupsDTO.getName().trim() + "%");
		}
		/** 状态查询要进行分情况考虑，因为群组邮件发送（只查询出所有启用状态的数据）和群组管理查询群组信息都是这个方法，所以要进行区分 */
		if (memGroupsDTO.getStatus() != null){
			/** 用于群组管理的模糊查询，拼接根据状态查询语句 */
			if(memGroupsDTO.getStatus().equals("-1") ){
				
			}else {
				/** 用于会员群组邮件发送，查询出来的都是启用状态的群组，其他状态的群组不可以进行发送邮件 */
				sql.append(" and o.status=" + memGroupsDTO.getStatus());
			}
			
		} 
		/** 根据创建时间进行查询 */
		// if (memGroupsDTO.getBeginDate() != null
		// && !memGroupsDTO.getBeginDate().equals("")) {
		// sql
		// .append(" and o.createTime>="
		// + memGroupsDTO.getBeginDate());
		// }
		// if (memGroupsDTO.getEndDate() != null
		// && !memGroupsDTO.getEndDate().equals("")) {
		// sql
		// .append(" and o.createTime<="
		// + memGroupsDTO.getEndDate());
		// }
		if (StringUtils.isNotBlank(memGroupsDTO.getBeginDate())) {
			sql.append(" and o.createTime >='" + memGroupsDTO.getBeginDate()+"'");
		}
		if (StringUtils.isNotBlank(memGroupsDTO.getEndDate())) {
			sql.append(" and o.createTime <='" + memGroupsDTO.getEndDate()+"'");
		}
		QueryResult<MemGroups> queryResult = getScrollData(firstindex,
				maxresult, sql.toString(), params.toArray(), orderby);
		return queryResult;
	}

	/***
	 * 添加群组会员
	 * 
	 * @see com.paySystem.ic.dao.member.MemGroupsDAO#save(com.paySystem.ic.web.dto.member.MemGroupsDTO)
	 * @Description:TODO
	 * @date: 2014-3-28下午07:05:29
	 * @author: 解文侠
	 * @version: V1.0
	 * @param memGroupsDTO
	 */
	public void save(MemGroupsDTO memGroupsDTO) {
		UserSession us = Utils.getUserSession();
		Organs o = new Organs();
		o.setOrganId(us.getOrganId());
		MemGroups g = new MemGroups();
		try {
			g.setGroupName(memGroupsDTO.getGroupName());
			g.setStatus(Integer.parseInt(memGroupsDTO.getStatus()));
			if (us.getMerId() != null) {
				g.setMerId(us.getMerId());
			}
			g.setOrgans(o);
			g.setCreateTime(this.getSysTime());
			g.setUserName(us.getUserName());
			g.setGroupDesc(memGroupsDTO.getGroupDesc());
			super.save(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 修改群组会员
	 * 
	 * @see com.paySystem.ic.dao.member.MemGroupsDAO#update(com.paySystem.ic.web.dto.member.MemGroupsDTO)
	 * @Description:TODO
	 * @date: 2014-3-28下午07:05:47
	 * @author: 解文侠
	 * @version: V1.0
	 * @param memGroupsDTO
	 * @return
	 */
	public ReturnDTO update(MemGroupsDTO memGroupsDTO) {
		ReturnDTO dto = new ReturnDTO();
		MemGroups memGroups = new MemGroups();
		if (memGroupsDTO != null) {
			memGroups.setGroupId(memGroupsDTO.getGroupId());
			memGroups.setGroupName(Utils.getString(memGroupsDTO.getGroupName()));
			memGroups.setStatus(Integer.parseInt(memGroupsDTO.getStatus()));
			memGroups.setUpdateTime(this.getSysTime());
			memGroups.setGroupDesc(Utils.getString(memGroupsDTO.getGroupDesc()));
			memGroups.setOrgans(organsServiceImpl.find(memGroupsDTO.getOrganId()));
			memGroups.setCreateTime(memGroupsDTO.getCreateTime());
			memGroups.setUserName(memGroupsDTO.getUserName());
			super.update(memGroups);
			dto.setFlag(true);
		}
		return dto;
	}

	/***
	 * 查询群组会员id
	 * 
	 * @see com.paySystem.ic.dao.member.MemGroupsDAO#queryAll(java.lang.String)
	 * @Description:TODO
	 * @date: 2014-3-28下午07:06:00
	 * @author: 解文侠
	 * @version: V1.0
	 * @param orgId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MemGroupsDTO> queryAll(String orgId) {
		String jpl = "select o from MemGroups o where o.organs='" + orgId + "'";
		Query query = em.createQuery(jpl);
		List<MemGroups> list = query.getResultList();
		List<MemGroupsDTO> listt = getMemGroupsDTOList(list);
		return listt;
	}

	/***
	 * 返回会员表与会员群组表不重复的数据
	 * 
	 * @Title:getMemGroupsDTOList
	 *@param:@param list
	 *@param:@return
	 *@return:List<MemGroupsDTO>
	 *@author:解文侠
	 *@thorws:
	 */
	public List<MemGroupsDTO> getMemGroupsDTOList(List<MemGroups> list) {
		List<MemGroupsDTO> listt = new ArrayList<MemGroupsDTO>();
		for (int i = 0; i < list.size(); i++) {
			MemGroupsDTO m = new MemGroupsDTO();
			m.setCreateTime(list.get(i).getCreateTime());
			m.setGroupDesc(list.get(i).getGroupDesc());
			m.setGroupId(list.get(i).getGroupId());
			m.setGroupName(list.get(i).getGroupName());
			m.setMerId(list.get(i).getMerId());
			m.setOrganId(list.get(i).getOrgans().getOrganId());
			m.setStatus(list.get(i).getStatus().toString());
			m.setUpdateTime(list.get(i).getUpdateTime());
			m.setUserName(list.get(i).getUserName());
			listt.add(m);
		}
		return listt;
	}

	/***
	 * 
	 * @see com.paySystem.ic.dao.member.MemGroupsDAO#getMemberByGroupId(java.lang.String)
	 * @Description:查询当前机构下的所有会员
	 * @date: 2014-3-25下午05:51:24
	 * @author: 解文侠
	 * @version: V1.0
	 * @param memId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OptionsString> getMemberByGroupId(Integer groupId) {
		// String jpl = "select * from m_member t left"
		// + " join m_member_memgroups mgs on t.memid = mgs.memid"
		// + " where" + " t.organid = '"
		// + Utils.getUserSession().getOrganId() + "'" + " and t.memid"
		// + " not in"
		// + " (select memg.memid from m_Member_Memgroups memg)";
		String jpl = "select t.userName, t.memid from m_members t, m_member_memgroups s"
				+ " where"
				+ "  t.memid = s.memid"
				+ " and s.groupid = '"
				+ groupId
				+ "'";
		System.out.println("jpl---------------------------------->"+jpl);
		List<Object[]> objList = em.createNativeQuery(jpl).getResultList();
		List<OptionsString> memList = new ArrayList<OptionsString>();
		for (int i = 0; i < objList.size(); i++) {
			Object[] obj = objList.get(i);
			memList
					.add(new OptionsString(obj[1].toString(), obj[0].toString()));
		}
		return memList;
	}

	/**
	 *@Title:checkGroupsNameExsis
	 *@Description:检测群组名称是否存在
	 *@param memGroupsDTO会员群组数据传输对象
	 *@Return:boolean返回布尔类型值，存在返回true,不存在返回false
	 *@author:徐凯强
	 *@Date:2014-8-26下午08:00:12
	 */
	@SuppressWarnings("unchecked")
	public boolean checkGroupsNameExsis(MemGroupsDTO memGroupsDTO, String method) {
		
		String sql = "select o from MemGroups o where  o.groupName =?";

		Query query = em.createQuery(sql);
		query.setParameter(1, memGroupsDTO.getGroupName().trim());

		boolean flag = false;
		/** 查找数据库中是否有这个名称 **/
		
		List<MemGroups> memList = query.getResultList();
		/** 如果添加方法，有一条数据，表示重复,true表示重复，false表示不重复 */
		if (method.equals("addSave")) {
			if (memList.size() > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} else {
			/** 如果是修改方法，编号也相同表示不重复，编号不同不重复 */
			if (memList.size() > 0) {
				if (memList.get(0).getGroupId().equals(
						memGroupsDTO.getGroupId())) {
					flag = false;
				} else {
					flag = true;
				}
			} else {
				flag = false;
			}
		}
		return flag;
	}
}
