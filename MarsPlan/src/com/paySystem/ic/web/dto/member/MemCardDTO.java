package com.paySystem.ic.web.dto.member;

import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class MemCardDTO extends BaseDTO {

	/** 会员关联*/
	private	String memId;

	/** 卡Bin 卡片类型*/
	private String binId;
	/** 卡Bin 卡片类型*/
	private String binName;
	/**会员真实姓名 */
	private String memRealName;
	/***会员昵称**/
	private String memName;
	/**卡表关联 */
	private String  cardNo;
	/**联系电话 */
	private String telNo;
	/**卡等级关联 */
	private	String levelId;
	/**卡等级名称 */
	private	String levelName;
	/**会员状态 */
	private Integer mstatus;
	/**卡状态 */
	private Integer cstatus;
	/**预存金额 */
	private BigDecimal balanceAnt;
	/**预存积分 */
	private BigDecimal  balancePnt;
	/**会员辅表流水Id*/
	private String amemId;
	/**电子邮箱 */
	private String email;
	/**会员群组 */
	private String groupId;
	/**会员证件类型*/
	private Integer perType;
	/**会员证件编号*/
	private String personId;
	/**证件有效期*/
	private Date certExTime;
	/**机构编号*/
	private String organId;
	/**机构编号*/
	private String orgName;
	/** 出生日期*/
	private Date birthday;
	/** 职业*/
	private String career;
	/**学历 */
	private String cultDegree;
	/**居住地址 */
	private String address;
	/**邮编 */
	private String residZip;
	/**区域Id */
	private String areaId;
	/**创建时间 */
	private Date createTime;
	/***性别*/
	private Integer sex ;
	/***显示卡号**/
	private String cardNoShow;
	/**车牌号*/
	private String carNumber;
	/**车型*/
	private Integer carType;
	/**推荐人*/
	private String recomId;
	/**推荐人类型*/
	private Integer recomType;
	/**司机驾驶证编号*/
	private String driverNo;
	/**会员卡类型*/
	private Integer cardType;
	/**银行*/
	private String bank;
	/**银行卡号*/
	private String bankAccount;
	/**联系人*/
	private String contacts;
	/**联系人电话*/
	private String contactsTel;
	/**合同编号*/
	private String contractNo;
	/**结算周期*/
	private String settleMent;
	
	
	
	
	
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getContactsTel() {
		return contactsTel;
	}
	public void setContactsTel(String contactsTel) {
		this.contactsTel = contactsTel;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getSettleMent() {
		return settleMent;
	}
	public void setSettleMent(String settleMent) {
		this.settleMent = settleMent;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public Integer getCarType() {
		return carType;
	}
	public void setCarType(Integer carType) {
		this.carType = carType;
	}
	public String getRecomId() {
		return recomId;
	}
	public void setRecomId(String recomId) {
		this.recomId = recomId;
	}
	public Integer getRecomType() {
		return recomType;
	}
	public void setRecomType(Integer recomType) {
		this.recomType = recomType;
	}
	public String getDriverNo() {
		return driverNo;
	}
	public void setDriverNo(String driverNo) {
		this.driverNo = driverNo;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getBinId() {
		return binId;
	}
	public void setBinId(String binId) {
		this.binId = binId;
	}
	public String getBinName() {
		return binName;
	}
	public void setBinName(String binName) {
		this.binName = binName;
	}
	public String getMemRealName() {
		return memRealName;
	}
	public void setMemRealName(String memRealName) {
		this.memRealName = memRealName;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public Integer getMstatus() {
		return mstatus;
	}
	public void setMstatus(Integer mstatus) {
		this.mstatus = mstatus;
	}
	public Integer getCstatus() {
		return cstatus;
	}
	public void setCstatus(Integer cstatus) {
		this.cstatus = cstatus;
	}
	public BigDecimal getBalanceAnt() {
		return balanceAnt;
	}
	public void setBalanceAnt(BigDecimal balanceAnt) {
		this.balanceAnt = balanceAnt;
	}
	public BigDecimal getBalancePnt() {
		return balancePnt;
	}
	public void setBalancePnt(BigDecimal balancePnt) {
		this.balancePnt = balancePnt;
	}
	public String getAmemId() {
		return amemId;
	}
	public void setAmemId(String amemId) {
		this.amemId = amemId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public Integer getPerType() {
		return perType;
	}
	public void setPerType(Integer perType) {
		this.perType = perType;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public Date getCertExTime() {
		return certExTime;
	}
	public void setCertExTime(Date certExTime) {
		this.certExTime = certExTime;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getCultDegree() {
		return cultDegree;
	}
	public void setCultDegree(String cultDegree) {
		this.cultDegree = cultDegree;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getResidZip() {
		return residZip;
	}
	public void setResidZip(String residZip) {
		this.residZip = residZip;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getCardNoShow() {
		return cardNoShow;
	}
	public void setCardNoShow(String cardNoShow) {
		this.cardNoShow = cardNoShow;
	}
	
}
