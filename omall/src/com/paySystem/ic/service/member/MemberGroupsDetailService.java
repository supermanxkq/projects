package com.paySystem.ic.service.member;

import java.util.List;

import com.paySystem.ic.bean.member.MemberGroupsDetail;
import com.paySystem.ic.service.common.DAO;

public interface MemberGroupsDetailService extends DAO<MemberGroupsDetail> {
	public static final String MEMBERGROUPSDETAILSERVICE = "memberGroupsDetailService";

	/**
	 * 根据群组ID查找中间表数据
	 * 
	 * @Title:findByGroupId
	 *@param:@param groupId
	 *@param:@return
	 *@return:List<MemberGroupsDetail>
	 *@author:解文侠
	 *@thorws:
	 */
	List<MemberGroupsDetail> findByGroupId(Integer groupId);
}
