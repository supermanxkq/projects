package com.paySystem.ic.web.dto.member;

import java.io.Serializable;

import com.paySystem.ic.bean.member.MemGroups;
import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.web.dto.BaseDTO;

public class MemberGroupsDetailDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 会员-群组-编号 */
	private Integer id;
	/** 群组会员信息 */
	private MemGroups memGroups;
	/** 会员信息 */
	private Member member;
	/** 会员群组编号 */
	private Integer groupId;
	/** 会员编号 */
	private String memId;

	public Integer getId() {
		return id;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MemGroups getMemGroups() {
		return memGroups;
	}

	public void setMemGroups(MemGroups memGroups) {
		this.memGroups = memGroups;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}