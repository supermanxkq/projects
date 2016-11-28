package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class CompanyInfoDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = -1324112849666702193L;
	/** 企业编号 */
	private Integer companyId;
	/** 企业名称 */
	private String companyName;
	/** 营业执照 */
	private String businLicen;
	/** 状态：1为启用 0为禁用 9删除 */
	private Integer status;
	/** 企业电话 */
	private String comTele;
	/** 企业Email地址 */
	private String comEmail;
	/** 企业联系人 */
	private String comConPer;
	/** 企业人数 */
	private Integer compNum;
	/** 备注 */
	private String descr;
	/** 创建时间 */
	private Date createTime;
	/** 企业联系人电话 */
	private String comConPerTele;
	/** 企业地址 */
	private String compAddress;

	public String getCompAddress() {
		return compAddress;
	}

	public void setCompAddress(String compAddress) {
		this.compAddress = compAddress;
	}

	public String getComConPerTele() {
		return comConPerTele;
	}

	public void setComConPerTele(String comConPerTele) {
		this.comConPerTele = comConPerTele;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBusinLicen() {
		return businLicen;
	}

	public void setBusinLicen(String businLicen) {
		this.businLicen = businLicen;
	}

	public String getComTele() {
		return comTele;
	}

	public void setComTele(String comTele) {
		this.comTele = comTele;
	}

	public String getComEmail() {
		return comEmail;
	}

	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}

	public String getComConPer() {
		return comConPer;
	}

	public void setComConPer(String comConPer) {
		this.comConPer = comConPer;
	}

	public Integer getCompNum() {
		return compNum;
	}

	public void setCompNum(Integer compNum) {
		this.compNum = compNum;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
