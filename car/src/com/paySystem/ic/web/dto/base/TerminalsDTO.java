package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Transient;

import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.web.dto.BaseDTO;

public class TerminalsDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 8302182693293426706L;
	/**	终端编号  */
	private String termId;
	/** 终端名称 */
	private String termName;
	/** 所属商户编号 */
	private String merId;
	/**	终端所属机构（收单机构） */
	private String organId;
	/** 消费折扣比率 */
	private  BigDecimal saleRate = new BigDecimal(0.00);
	/**终端启用状态:0-删除，1-启用，2-禁用*/
	private Integer state;
	/** 是否允许充值标志 :0-否，1-是 */
	private Integer allowRechSign;
	/** 是否允许退货标志 :0-否 ，1-允许*/
	private Integer allowRetSign;	
	/** 允许退货期限 ：0-否，1-允许 */
	private Integer retTimeLimit;
	/** 折扣率优先级 0-使用卡级别账户规则,1-使用自定义终端折扣率*/
	private Integer discPriority;
	/** 主管密码 */
	private String superPwd;
	/**	对终端的描述信息 */
	private String termDesc;
	
	/**查询条件：机构名称*/
	@Transient
	private String orgName;
	
	/**查询条件：商户名称*/
	@Transient
	private String merName;
	
	/** 更新时间 */
	private Date updateTime;
	
	/** 创建时间 */
	private Date createTime;
	
	/**确认密码*/
	private String rePwd;
	
	/**
	 * 所属商户，与终端存在一对多的关系，终端与它反之。
	 */
	private Merchants merchants;
	
	/**
	 * 所属机构，与终端存在一对多关系，终端与它反之。
	 */
	private Organs organs;
	
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public BigDecimal getSaleRate() {
		return saleRate;
	}
	public void setSaleRate(BigDecimal saleRate) {
		this.saleRate = saleRate;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getAllowRechSign() {
		return allowRechSign;
	}
	public void setAllowRechSign(Integer allowRechSign) {
		this.allowRechSign = allowRechSign;
	}
	public Integer getAllowRetSign() {
		return allowRetSign;
	}
	public void setAllowRetSign(Integer allowRetSign) {
		this.allowRetSign = allowRetSign;
	}
	public Integer getRetTimeLimit() {
		return retTimeLimit;
	}
	public void setRetTimeLimit(Integer retTimeLimit) {
		this.retTimeLimit = retTimeLimit;
	}
	public Integer getDiscPriority() {
		return discPriority;
	}
	public void setDiscPriority(Integer discPriority) {
		this.discPriority = discPriority;
	}
	public String getSuperPwd() {
		return superPwd;
	}
	public void setSuperPwd(String superPwd) {
		this.superPwd = superPwd;
	}
	public String getTermDesc() {
		return termDesc;
	}
	public void setTermDesc(String termDesc) {
		this.termDesc = termDesc;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Merchants getMerchants() {
		return merchants;
	}
	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}
	public Organs getOrgans() {
		return organs;
	}
	public void setOrgans(Organs organs) {
		this.organs = organs;
	}
	public String getRePwd() {
		return rePwd;
	}
	public void setRePwd(String rePwd) {
		this.rePwd = rePwd;
	}
    

}
