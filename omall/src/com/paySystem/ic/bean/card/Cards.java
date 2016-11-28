package com.paySystem.ic.bean.card;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.paySystem.ic.bean.account.Accounts;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.member.CardLevel;
import com.paySystem.ic.bean.member.Member;

/**
 * 卡主表
 * 
 * @author lily
 * 
 */
@Entity
@Table(name = "C_Cards")
public class Cards implements Serializable {

private static final long serialVersionUID = -8856850169725586516L;
	/**
	 * 卡号 卡bin（6位）+ 标志(1位)+预留（2位 88）+9位流水+1位校验位
	 * */
	private String cardNo;

	/**
	 * 卡级别
	 * */
	private CardLevel cardLevel;
	/**
	 * 卡状态 0：入库；1：出库2：发卡机构入库； 3：发卡机构出库；4：商户入库；5：商户出库； 6：待激活；7：正常；8：挂失
	 * 9：已换卡；10：发卡机构退卡；11：商户退卡
	 * */
	private Integer status;
	/**
	 * 持卡人客户号
	 * */
	//private String holdMemId;
	private Member member;
	/**
	 * 售卡单位类型
	 */
	//private String deptType;
	private Organs organs;
	private Merchants merchants;
	/**
	 * 卡有效期
	 * */
	private Date validTime;

	/**
	 * 会员绑定状态 0：未绑定；1：绑定
	 * */
	private Integer memSign;
	/**
	 * 是否商圈卡 0：否；1：是
	 * */
	private Integer bcSign;

	/**
	 * 密码是否启用 0：不启用；1：启用
	 * */
	private Integer pwdSign;
	/**
	 * 密码错误次数
	 * */
	private Integer pwdErrorNum;

	/**
	 *密码偏移量
	 * */
	private String pinBlock;
	/**
	 * 2磁道信息（或m1卡2扇区校验位）
	 * */
	private String track2;
	
	/**
	 * 发卡机构编号
	 */
	//private String organId;
	/**
	 * 创建时间
	 * */
	private Date createTime;

   /**
    * 账户
    * */
	
	List<Accounts> listAcc = new ArrayList<Accounts>();
	/**
	 * 显示卡号
	 * */
	private String cardViewNo;
	/**
	 * 企业Id
	 * */
	private Integer companyId;
	/**
	 * 卡标志
	 * */
	private Integer cardSingn;
	/***密码**/
	private String pwd ;
	
	public Cards() {
		super();
	}

	public Cards(String cardViewNo,String cardNo ) {
		this.cardViewNo = cardViewNo;
		this.cardNo=cardNo;
	}

	@Id
	@Column(length = 19)
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "levelId")
	public CardLevel getCardLevel() {
		return cardLevel;
	}

	public void setCardLevel(CardLevel cardLevel) {
		this.cardLevel = cardLevel;
	}

	@Column
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

//	@Column(length = 8)
//	public String getHoldMemId() {
//		return holdMemId;
//	}
//
//	public void setHoldMemId(String holdMemId) {
//		this.holdMemId = holdMemId;
//	}
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "holdMemID")
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
//	@Column(length = 1)
//	public String getDeptType() {private String organId;
//		return deptType;
//	}
//
//	public void setDeptType(String deptType) {
//		this.deptType = deptType;
//	}
//	@Column(length = 8)
//	public String getOrgId() {
//		return organId;
//	}
//
//	public void setOrgId(String organId) {
//		this.organId = organId;
//	}
	
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "organId")
	
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getValidTime() {
		return validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	@Column(columnDefinition = "CHAR(1)")
	public Integer getMemSign() {
		return memSign;
	}

	public void setMemSign(Integer memSign) {
		this.memSign = memSign;
	}

	@Column(length = 1)
	public Integer getBcSign() {
		return bcSign;
	}

	public void setBcSign(Integer bcSign) {
		this.bcSign = bcSign;
	}

	@Column(columnDefinition = "CHAR(1)")
	public Integer getPwdSign() {
		return pwdSign;
	}

	public void setPwdSign(Integer pwdSign) {
		this.pwdSign = pwdSign;
	}

	@Column(length = 2)
	public Integer getPwdErrorNum() {
		return pwdErrorNum;
	}

	public void setPwdErrorNum(Integer pwdErrorNum) {
		this.pwdErrorNum = pwdErrorNum;
	}

	@Column(length = 16)
	public String getPinBlock() {
		return pinBlock;
	}

	public void setPinBlock(String pinBlock) {
		this.pinBlock = pinBlock;
	}

	@Column(length = 50)
	public String getTrack2() {
		return track2;
	}

	public void setTrack2(String track2) {
		this.track2 = track2;
	}
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Transient
	public List<Accounts> getListAcc() {
		return listAcc;
	}

	public void setListAcc(List<Accounts> listAcc) {
		this.listAcc = listAcc;
	}

	public String getCardViewNo() {
		return cardViewNo;
	}
    @Column(length =19)
	public void setCardViewNo(String cardViewNo) {
		this.cardViewNo = cardViewNo;
	}
    @Column
	public Integer getCompanyId() {
		return companyId;
	}
  
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	@Column(columnDefinition = "char(1)")
	public Integer getCardSingn() {
		return cardSingn;
	}

	public void setCardSingn(Integer cardSingn) {
		this.cardSingn = cardSingn;
	}
	@Column(length = 20)
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}

