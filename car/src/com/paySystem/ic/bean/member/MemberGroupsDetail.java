package com.paySystem.ic.bean.member;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/****
 * 群组会员中间实体表
 * 
 * @author 解文侠
 * 
 */
@Entity
@Table(name = "M_Member_MemGroups")
public class MemberGroupsDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 会员-群组-编号 */
	private Integer id;
	/** 群组会员信息 */
	private MemGroups memGroups;
	/** 会员信息 */
	private Member member;

/*	@Id
	@SequenceGenerator(name = "s_MemDetail_SEQ", sequenceName = "s_MemDetail_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_MemDetail_SEQ")*/
	/**
	 * 添加MySql自增列支持
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "groupId")
	public MemGroups getMemGroups() {
		return memGroups;
	}

	public void setMemGroups(MemGroups memGroups) {
		this.memGroups = memGroups;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "memId")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
