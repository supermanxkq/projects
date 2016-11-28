
package com.paySystem.ic.bean.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName:AccountsRecord
 * @TODO:帐户变动记录表实体类
 * @date: 2014-4-4上午09:41:45
 * @author: 孟凡岭
 * @version: V1.0
 */
@Entity
@Table(name = "C_AccountsRecord")
public class AccountsRecord implements Serializable {
	/**
	 * @Fields serialVersionUID : 序列化
	 * @author 孟凡岭
	 * @date 2014-4-4 下午12:02:35
	 */
	private static final long serialVersionUID = 1L;
	/** 主键 **/
	private Integer id;
	/** 所属卡号 **/
	private String cardNo;
	/** 帐户类型 **/
	private Integer accTypeId;
	/** 调整金额，若是操作和金额无关可不添 **/
	private BigDecimal amount;
	/** 操作员ID **/
	private String operatorId;
	/** 所属机构编号 **/
	private String orgId;
	/** 帐户操作类型 0:调整 1:消费  2:消费  3:充值  4：换卡  5：退卡  **/
	private Integer operateType;
	/** 操作日期 **/
	private Date createTime;
	/***资金流向***/
	private Integer accFlow;
	
/*	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_AccountsRecord")
	@SequenceGenerator(name = "S_AccountsRecord", sequenceName = "S_AccountsRecord")*/
	/**
	 * 添加MySql对自增列的支持
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 19)
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Column(columnDefinition = "DECIMAL(18,2)")
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(length = 20)
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	@Column(length = 8)
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getAccTypeId() {
		return accTypeId;
	}

	public void setAccTypeId(Integer accTypeId) {
		this.accTypeId = accTypeId;
	}
	@Column(columnDefinition = "char(1)")
	public Integer getAccFlow() {
		return accFlow;
	}

	public void setAccFlow(Integer accFlow) {
		this.accFlow = accFlow;
	}
	
}
