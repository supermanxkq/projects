package com.paySystem.ic.web.dto.member;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.web.dto.BaseDTO;

public class MemGroupsDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 会员群组编号 */
	private Integer groupId;
	/** 会员群组名称 */
	private String groupName;
	/** 创建人 */
	private String userName;
	/** 群组所属机构 */
	private String organId;
	/** 群组所属机构 名称 */
	private String name;
	/** 所属商户 */
	private String merId;
	/** 群组启用状态 : 0 :删除 1：启用 2：禁用 */
	private String status;
	/** 群组创建时间 */
	private Date createTime;
	/** 群组修改时间 */
	private Date updateTime;
	/** 群组的描述信息 */
	private String groupDesc;
	/** 会员id */
	private List<String> memId;
	/** 会员集合 **/
	private List<Member> member;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public List<String> getMemId() {
		return memId;
	}

	public void setMemId(List<String> memId) {
		this.memId = memId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Member> getMember() {
		return member;
	}

	public void setMember(List<Member> member) {
		this.member = member;
	}

}
