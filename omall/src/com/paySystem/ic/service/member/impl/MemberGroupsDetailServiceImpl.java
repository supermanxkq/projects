package com.paySystem.ic.service.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.member.MemberGroupsDetail;
import com.paySystem.ic.dao.member.MemberGroupsDetailDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.member.MemberGroupsDetailService;

@Service(MemberGroupsDetailService.MEMBERGROUPSDETAILSERVICE)
public class MemberGroupsDetailServiceImpl extends
		DaoSupport<MemberGroupsDetail> implements MemberGroupsDetailService {
	@Resource
	MemberGroupsDetailDAO memberGroupsDetailDAO;

	public List<MemberGroupsDetail> findByGroupId(Integer groupId) {
		String sql = "from MemberGroupsDetail m where m.memGroups.groupId='"
				+ groupId + "'";
		return memberGroupsDetailDAO.findByJpl(sql);
	}

}
