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
import com.paySystem.ic.utils.DateTimeTool;
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

	/***
	 * 页面加载查询数据
	 * 
	 * @see com.paySystem.ic.dao.member.MemGroupsDAO#queryResult(int, int,
	 *      com.paySystem.ic.web.dto.member.MemGroupsDTO,
	 *      java.util.LinkedHashMap)
	 * @Description:TODO
	 * @date: 2014-3-28下午07:03:52
	 * @author: 解文侠
	 * @version: V1.0
	 * @param firstindex
	 * @param maxresult
	 * @param memGroupsDTO
	 * @param orderby
	 * @return
	 * @throws Exception
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
		if (memGroupsDTO.getStatus() != null
				&& Integer.parseInt(memGroupsDTO.getStatus()) != -1) {
			sql.append(" and o.status = ?").append(params.size() + 1);
			// 设置群组状态
			params.add(Integer.parseInt(memGroupsDTO.getStatus()));
		}
		/** 判断日期孟凡岭添加 **/
		if (memGroupsDTO.getCreateTime() != null) {
			sql.append(" and o.createTime=?").append(params.size() + 1);
			params.add(DateTimeTool.dateFormat("yyyy-MM-dd", DateTimeTool
					.dateFormat("yyyy-MM-dd", memGroupsDTO.getCreateTime())));
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
			if(us.getMerId()!=null){
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
			memGroups
					.setGroupName(Utils.getString(memGroupsDTO.getGroupName()));
			memGroups.setStatus(Integer.parseInt(memGroupsDTO.getStatus()));
			memGroups.setUpdateTime(this.getSysTime());
			memGroups
					.setGroupDesc(Utils.getString(memGroupsDTO.getGroupDesc()));
			memGroups.setOrgans(organsServiceImpl.find(memGroupsDTO
					.getOrganId()));
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
//		String jpl = "select * from m_member t left"
//			+ " join m_member_memgroups mgs on t.memid = mgs.memid"
//			+ " where" + " t.organid = '"
//			+ Utils.getUserSession().getOrganId() + "'" + " and t.memid"
//			+ " not in"
//			+ " (select memg.memid from m_Member_Memgroups memg)";
		String jpl = "select t.memrealname, t.memid from m_member t, m_member_memgroups s"
				+ " where"
				+ " t.organid = '"
				+ Utils.getUserSession().getOrganId()
				+ "'"
				+ " and t.memid = s.memid"
				+ " and s.groupid = '"
				+ groupId
				+ "'";
		List<Object[]> objList = em.createNativeQuery(jpl).getResultList();
		List<OptionsString> memList = new ArrayList<OptionsString>();
		for (int i = 0; i < objList.size(); i++) {
			Object[] obj = objList.get(i);
			memList.add(new OptionsString(obj[1].toString(),
					obj[0].toString()));
		}
		return memList;
	}
}
