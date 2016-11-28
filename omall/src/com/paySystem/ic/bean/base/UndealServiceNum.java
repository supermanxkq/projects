package com.paySystem.ic.bean.base;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:omall
 * @ClassName:UndealServiceNum
 * @Description:商城后台首界面待处理业务统计数实体
 * @date: 2014-10-22
 * @author: 王楠
 * @version: V1.0
 */
@Entity
@Table(name="B_UnDealServiceNum")
public class UndealServiceNum implements Serializable{

	private static final long serialVersionUID = 4088265737507256364L;
	
	/**编号*/
	private Integer dealId;
	/**机构商户号*/
	private String orgMerId;
	/**等待发货的订单数*/
    private Integer noDelivNum=0;
    /**已发货的订单数*/
    private Integer postedNum=0;
    /**收到的投诉*/
    private Integer complaintsNum=0;
    /**等待买家付款的订单数*/
    private Integer noPaidNum=0;
    /**已成功的订单数*/
    private Integer completedNum=0;
    /**退款中的订单数*/
    private Integer returnNum=0;
    /**未阅读的商品评价数*/
    private Integer unReadNum=0;
    /**用户申请为商户的审核 */
    private Integer merCheckNum=0;
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	public Integer getDealId() {
		return dealId;
	}
	public void setDealId(Integer dealId) {
		this.dealId = dealId;
	}
	@Column(length=15)
	public String getOrgMerId() {
		return orgMerId;
	}
	public void setOrgMerId(String orgMerId) {
		this.orgMerId = orgMerId;
	}
	@Column(length=8,columnDefinition="int default 0")
	public Integer getNoDelivNum() {
		return noDelivNum;
	}
	public void setNoDelivNum(Integer noDelivNum) {
		this.noDelivNum = noDelivNum;
	}
	@Column(length=8,columnDefinition="int default 0")
	public Integer getPostedNum() {
		return postedNum;
	}
	public void setPostedNum(Integer postedNum) {
		this.postedNum = postedNum;
	}
	@Column(length=8,columnDefinition="int default 0")
	public Integer getComplaintsNum() {
		return complaintsNum;
	}
	public void setComplaintsNum(Integer complaintsNum) {
		this.complaintsNum = complaintsNum;
	}
	@Column(length=8,columnDefinition="int default 0")
	public Integer getNoPaidNum() {
		return noPaidNum;
	}
	public void setNoPaidNum(Integer noPaidNum) {
		this.noPaidNum = noPaidNum;
	}
	@Column(length=8,columnDefinition="int default 0")
	public Integer getCompletedNum() {
		return completedNum;
	}
	public void setCompletedNum(Integer completedNum) {
		this.completedNum = completedNum;
	}
	@Column(length=8,columnDefinition="int default 0")
	public Integer getReturnNum() {
		return returnNum;
	}
	public void setReturnNum(Integer returnNum) {
		this.returnNum = returnNum;
	}
	@Column(length=8,columnDefinition="int default 0")
	public Integer getUnReadNum() {
		return unReadNum;
	}
	public void setUnReadNum(Integer unReadNum) {
		this.unReadNum = unReadNum;
	}
	@Column(length=8,columnDefinition="int default 0")
	public Integer getMerCheckNum() {
		return merCheckNum;
	}
	public void setMerCheckNum(Integer merCheckNum) {
		this.merCheckNum = merCheckNum;
	}
    
}
