package com.paySystem.ic.bean.account;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;

/**
 * 
* @ClassName: AccKinds
* @Description: 账户类型实体映射类
* @author lily
* @date 2013-12-14 下午03:16:30
* @version V1.0
 */
@Entity
@Table(name = "C_Acc_Kinds")
public class AccKinds implements Serializable {
	/**
	* @Fields serialVersionUID
	*/
	private static final long serialVersionUID = 3552995468677095819L;
	private Integer kindId; //类型编号
	private String kindName; //类型名称
	//private Integer OrgId; //机构编号
	private Organs organs;
	//private Integer MerId; //商户号	
	private Merchants merchants;
	private Integer accSign; //消费类型    0：金额；1：按次数
	private Integer saleTimesLimit; //当日消费次数显示(0)表示无限制
	private Date beginDate; //有效起始日期
	private Date endDate; //有效终止日期  
	private Integer status; //类型启用状态  0：删除；1：启用；2： 禁用
	private Date updateTime; //更新时间
	private String descr; //类型描述
	private Integer withDraType;//Withdrawals是否可提现： 0：否  1：是
	private Integer transAccType;//Transfer accounts是否可转账： 0：否    1：是
	private Integer consType;//Consumption是否允许消费 0：否    1：是
	private Integer rechargeType;//Recharge是否可充值  0：否    1：是
	
	@Id
	@Column
	public Integer getKindId() {
		return kindId;
	}
	public void setKindId(Integer kindId) {
		this.kindId = kindId;
	}
	
	@Column(columnDefinition = "Varchar(20)")
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "orgId")
	
	public Organs getOrgans() {
		return organs;
	}
	public void setOrgans(Organs organs) {
		this.organs = organs;
	}
	
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "MerId")
	public Merchants getMerchants() {
		return merchants;
	}
	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}
	
	@Column(columnDefinition = "CHAR(1)")
	public Integer getAccSign() {
		return accSign;
	}
	public void setAccSign(Integer accSign) {
		this.accSign = accSign;
	}
	
	@Column
	public Integer getSaleTimesLimit() {
		return saleTimesLimit;
	}
	public void setSaleTimesLimit(Integer saleTimesLimit) {
		this.saleTimesLimit = saleTimesLimit;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Column(columnDefinition = "CHAR(1)")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(columnDefinition = "Varchar(200)")
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	@Override
	public String toString() {
		return "AccKinds [accSign=" + accSign + ", beginDate=" + beginDate
				+ ", descr=" + descr + ", endDate=" + endDate + ", kindId="
				+ kindId + ", kindName=" + kindName + ", merchants="
				+ merchants + ", organs=" + organs + ", saleTimesLimit="
				+ saleTimesLimit + ", status=" + status + ", updateTime="
				+ updateTime + ",withDraType=" + withDraType + ",transAccType="
				+ transAccType + ",consType=" + consType + ",rechargeType="
				+ rechargeType + " ]";
	}
	
	public void setWithDraType(Integer withDraType) {
		this.withDraType = withDraType;
	}
	@Column(columnDefinition = "CHAR(1)")
	public Integer getWithDraType() {
		return withDraType;
	}
	
	public void setTransAccType(Integer transAccType) {
		this.transAccType = transAccType;
	}
	@Column(columnDefinition = "CHAR(1)")
	public Integer getTransAccType() {
		return transAccType;
	}
	
	public void setRechargeType(Integer rechargeType) {
		this.rechargeType = rechargeType;
	}
	@Column(columnDefinition = "CHAR(1)")
	public Integer getRechargeType() {
		return rechargeType;
	}
	
	public void setConsType(Integer consType) {
		this.consType = consType;
	}
	@Column(columnDefinition = "CHAR(1)")
	public Integer getConsType() {
		return consType;
	}	
}
