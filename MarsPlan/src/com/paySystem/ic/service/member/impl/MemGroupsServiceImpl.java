package com.paySystem.ic.service.member.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.MemGroups;
import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.bean.member.MemberGroupsDetail;
import com.paySystem.ic.dao.member.MemGroupsDAO;
import com.paySystem.ic.dao.member.MemberDAO;
import com.paySystem.ic.dao.member.MemberGroupsDetailDAO;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.member.MemGroupsService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemGroupsDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsString;

@Service(MemGroupsService.MEMGROUPSSERVICE)
public class MemGroupsServiceImpl extends DaoSupport<MemGroups> implements
		MemGroupsService {
	@Resource
	MemGroupsDAO memGroupsDAOImpl;
	@Resource
	MemberDAO memberDAO;
	@Resource
	OrgansService organsService;
	@Resource
	MemGroupsDAO memGroupsDAO;
	@Resource
	MemberGroupsDetailDAO memberGroupsDetailDAO;

	/***
	 * 删除群组会员
	 * 
	 * @see com.paySystem.ic.service.member.MemGroupsService#delete(java.lang.String[])
	 * @Description:TODO
	 * @date: 2014-3-28下午07:09:42
	 * @author: 解文侠
	 * @version: V1.0
	 * @param ids
	 */
	public void delete(String[] ids) {
		memGroupsDAOImpl.delete(ids);
	}

	/***
	 * 页面加载查询所有群组会员信息
	 * 
	 * @see com.paySystem.ic.service.member.MemGroupsService#queryResult(int,
	 *      int, com.paySystem.ic.web.dto.member.MemGroupsDTO,
	 *      java.util.LinkedHashMap)
	 * @Description:TODO
	 * @date: 2014-3-28下午07:09:46
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
		return memGroupsDAOImpl.queryResult(firstindex, maxresult,
				memGroupsDTO, orderby);
	}

	/***
	 * 添加群组会员
	 * 
	 * @see com.paySystem.ic.service.member.MemGroupsService#save(com.paySystem.ic.web.dto.member.MemGroupsDTO)
	 * @Description:TODO
	 * @date: 2014-3-28下午07:09:50
	 * @author: 解文侠
	 * @version: V1.0
	 * @param memGroupsDTO
	 */
	public void save(MemGroupsDTO memGroupsDTO) {
		memGroupsDAOImpl.save(memGroupsDTO);
	}

	/***
	 * 修改群组会员
	 * 
	 * @see com.paySystem.ic.service.member.MemGroupsService#update(com.paySystem.ic.web.dto.member.MemGroupsDTO)
	 * @Description:TODO
	 * @date: 2014-3-28下午07:09:56
	 * @author: 解文侠
	 * @version: V1.0
	 * @param memGroupsDTO
	 * @return
	 */
	@Transactional
	public ReturnDTO update(MemGroupsDTO memGroupsDTO, List<String> right) {
		ReturnDTO dto = new ReturnDTO();
		try {
			memGroupsDAOImpl.update(memGroupsDTO);
			memberGroupsDetailDAO.updateSave(memGroupsDTO, right);
			dto.setFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
			dto.setFlag(false);
		}
		return dto;
	}

	/***
	 * 查询所有群组会员信息
	 * 
	 * @see com.paySystem.ic.service.member.MemGroupsService#queryAll(java.lang.String)
	 * @Description:TODO
	 * @date: 2014-3-28下午07:10:33
	 * @author: 解文侠
	 * @version: V1.0
	 * @param orgId
	 * @return
	 */
	public List<MemGroupsDTO> queryAll(String orgId) {
		return memGroupsDAOImpl.queryAll(orgId);
	}

	/***
	 * 返回会员表与会员群组表不重复的数据
	 * 
	 * @see com.paySystem.ic.service.member.MemGroupsService#getMemberByGroupId(java.lang.Integer)
	 * @Description:TODO
	 * @date: 2014-3-28下午07:10:38
	 * @author: 解文侠
	 * @version: V1.0
	 * @param memId
	 * @return
	 */
	public List<OptionsString> getMemberByGroupId(Integer groupId) {
		return memGroupsDAOImpl.getMemberByGroupId(groupId);
	}

	/***
	 * 添加群组的会员
	 * 
	 * @see com.paySystem.ic.service.member.MemGroupsService#save(com.paySystem.ic.web.dto.member.MemGroupsDTO,
	 *      java.util.List)
	 * @Description:TODO
	 * @date: 2014-4-3下午06:08:51
	 * @author: 解文侠
	 * @version: V1.0
	 * @param memGroupsDTO
	 * @param rights
	 */
	public void save(MemGroupsDTO memGroupsDTO, List<String> rights) {
		MemGroups groups = getGroups(memGroupsDTO);
		memGroupsDAOImpl.save(groups);
		if(rights!=null){
			for (int i = 0; i < rights.size(); i++) {
				MemberGroupsDetail m = new MemberGroupsDetail();
				Member member = memberDAO.find(rights.get(i).trim());
				m.setMember(member);
				m.setMemGroups(groups);
				memGroupsDAO.save(m);
			}
		}
		
	}

	/**
	 * 获取实体
	 * 
	 * @Title:getGroupsDTO
	 *@param:@param memGroupsDTO
	 *@param:@return
	 *@return:MemGroupsDTO
	 *@author:解文侠
	 *@thorws:
	 */
	private MemGroups getGroups(MemGroupsDTO memGroupsDTO) {
		UserSession us = Utils.getUserSession();
		MemGroups m = new MemGroups();
		m.setCreateTime(this.getSysTime());
		if (memGroupsDTO.getGroupDesc() != null) {
			m.setGroupDesc(memGroupsDTO.getGroupDesc());
		}
		m.setUpdateTime(this.getSysTime());
		m.setGroupName(memGroupsDTO.getGroupName());
		m.setOrgans(organsService.find(us.getOrganId()));
		m.setStatus(Integer.valueOf(memGroupsDTO.getStatus()));
		m.setUserName(us.getUserName());
		return m;
	}
}
