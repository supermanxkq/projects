package com.paySystem.ic.web.dto.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/***
 * @ClassName:AccountsRecordDTO
 * @TODO:帐户变动记录DTO
 * @date: 2014-4-4下午12:07:19
 * @author: 孟凡岭
 * @version: V1.0
 */
public class AccountsRecordDTO extends BaseDTO implements Serializable {
	/**
	 * @Fields serialVersionUID : 序列化
	 * @author 孟凡岭
	 * @date 2014-4-4 下午12:07:44
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
	/**结束日期**/
	private Date endTime;
	/***资金流向***/
	private Integer accFlow;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

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

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getAccTypeId() {
		return accTypeId;
	}

	public void setAccTypeId(Integer accTypeId) {
		this.accTypeId = accTypeId;
	}

	public Integer getAccFlow() {
		return accFlow;
	}

	public void setAccFlow(Integer accFlow) {
		this.accFlow = accFlow;
	}
	
}
