package com.paySystem.ic.bean.base;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @ClassName:Organs
 * @Description:机构实体Bean
 * @date: 2013-12-4下午05:12:35
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name = "b_organs")
public class Organs implements Serializable {
	private static final long serialVersionUID = 6693965150652975115L;
	/** 机构ID */
	private String organId;
	/** 机构名称 */
	private String name;
	/** 所属上一级机构号  0代表顶级发卡机构*/
	private String parentId;
	/** 联系人姓名 */
	private String conPerName;
	/** 地区ID */
	private Integer areaId;
	/** 电话 */
	private String teleNo;
	/** 状态：1为启用 0为禁用 9删除 */
	private Integer status;
	/** 地址 */
	private String address;
	/** 邮编 */
	private String zip;
	/** 上次结算时间 */
	private Date lastSettTime;
	/** 商户开户银行 */
	private String bankName;
	/** 商户开户账号 */
	private String bankAcctNo;
	/** 商户开户账号名称*/
	private String bankAccName;
	/** 联系人电话号码 */
	private String conPerTeleNo;
	/** 开户人 */
	private String bankUser;
	/** 创建时间 */
	private Date createTime;
	/** 紧急联系人电话号码 */
	private String emContPhone;
	/** 机构描述*/
	private String orgDesc;
	/** 机构卡*/
	private String orgCard;


	public Organs() {
		super();
	}

	public Organs(String orgId) {
		this.organId = orgId;
	}

	@Id
	@Column(length = 8)
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	@Column(length = 60)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length = 8)
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Column(length = 20)
	public String getConPerName() {
		return conPerName;
	}
	public void setConPerName(String conPerName) {
		this.conPerName = conPerName;
	}
	@Column
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	@Column(length = 20)
	public String getTeleNo() {
		return teleNo;
	}
	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}
	@Column(columnDefinition="char(1)")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(length = 100)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(length = 10)
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getLastSettTime() {
		return lastSettTime;
	}
	public void setLastSettTime(Date lastSettTime) {
		this.lastSettTime = lastSettTime;
	}
	@Column(length=40)
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	@Column(length = 40)
	public String getBankAcctNo() {
		return bankAcctNo;
	}
	public void setBankAcctNo(String bankAcctNo) {
		this.bankAcctNo = bankAcctNo;
	}
	@Column(length=40)
	public String getBankAccName() {
		return bankAccName;
	}
	public void setBankAccName(String bankAccName) {
		this.bankAccName = bankAccName;
	}
	@Column(length = 20)
	public String getConPerTeleNo() {
		return conPerTeleNo;
	}
	public void setConPerTeleNo(String conPerTeleNo) {
		this.conPerTeleNo = conPerTeleNo;
	}
	@Column(length = 30)
	public String getBankUser() {
		return bankUser;
	}
	public void setBankUser(String bankUser) {
		this.bankUser = bankUser;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(length = 200)
	public String getEmContPhone() {
		return emContPhone;
	}
	public void setEmContPhone(String emContPhone) {
		this.emContPhone = emContPhone;
	}
	@Column(length=255)
	public String getOrgDesc() {
		return orgDesc;
	}
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	@Column(length = 19)
	public String getOrgCard() {
		return orgCard;
	}

	public void setOrgCard(String orgCard) {
		this.orgCard = orgCard;
	}

}
