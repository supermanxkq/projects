package com.paySystem.ic.web.dto.member;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.paySystem.ic.bean.base.Drivers;
import com.paySystem.ic.bean.base.ReceivingInfo;
import com.paySystem.ic.bean.member.MemGrade;
import com.paySystem.ic.web.dto.BaseDTO;

public class MemberDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = -6833571816006801397L;
	/** 会员编号  */
	private String memId;
	/**  会员真实姓名  */
	private String memRealName;
	/**  会员证件类型  */
    private Integer perType;	
	/**  会员证件号码 */
	private String personId;
	/**  会员电话  */
	private String teleNo;
	/**  会员性别  */
	private Integer sex;
	/**  会员所属区域  */
	private String areaId;
	/**  会员电子邮箱  */
	private String email;
	/**会员群组Id*/
	private String groupId;
	/**  会员信息修改时间  */
	private String updateTime;
	/**会员信息创建时间  */
	private String createTime;
	/**会员所属机构 名称*/
	private String name;
	/**会员所属机构Id*/
	private String organId;
	/**所属机构名**/
	private String organName;
	/**会员状态*/
	private Integer status;
	/**会员生日**/
	private String birthday;
	/**会员职业 */
	private String career;
	/**操作人*/
	private String operId;
	 /**会员等级实体*/
    private MemGrade memGrade;
    /**会员等级名称*/
    private String gradeName;
    /**会员等级名称*/
    private String gradeId;
	/**会员学历 */
	private String cultDegree;
	/** 会员居住地址*/
	private String address;
	/**会员邮编 */
	private String residZip;
	/**新增的字段*/
	/**推荐人id*/
	private String RecomId;
	/**推荐人类型 1：加油站会员 2：个人会员*/
	private Integer RecomType;
	/**车型*/
	private Integer carType;
	/**车牌号*/
	private String carNumber;
	/**驾驶证编号*/
	private String driverNo;
	/**卡类型 1：个人会员卡  2：加油站卡*/
	private Integer cardType;
	/**司机附表映射*/
	private Drivers drivers;
	/**可用积分*/
	private BigDecimal availableAmt;
	/**收货信息集合*/
	private List<ReceivingInfo> receivingInfo;
	
	
	public List<ReceivingInfo> getReceivingInfo() {
		return receivingInfo;
	}
	public void setReceivingInfo(List<ReceivingInfo> receivingInfo) {
		this.receivingInfo = receivingInfo;
	}
	
	public BigDecimal getAvailableAmt() {
		return availableAmt;
	}
	public void setAvailableAmt(BigDecimal availableAmt) {
		this.availableAmt = availableAmt;
	}
	public MemGrade getMemGrade() {
		return memGrade;
	}
	public void setMemGrade(MemGrade memGrade) {
		this.memGrade = memGrade;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
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
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemRealName() {
		return memRealName;
	}
	public void setMemRealName(String memRealName) {
		this.memRealName = memRealName;
	}

	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getTeleNo() {
		return teleNo;
	}
	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
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
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	/**public void setPerType(Integer perType) {
		this.perType = perType;
	}
	public Integer getPerType() {
		return perType;
	}*/
	public void setRecomId(String recomId) {
		RecomId = recomId;
	}
	public String getRecomId() {
		return RecomId;
	}
	public void setRecomType(Integer recomType) {
		RecomType = recomType;
	}
	public Integer getRecomType() {
		return RecomType;
	}
	
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setDriverNo(String driverNo) {
		this.driverNo = driverNo;
	}
	public String getDriverNo() {
		return driverNo;
	}
	public void setPerType(Integer perType) {
		this.perType = perType;
	}
	public Integer getPerType() {
		return perType;
	}
	
	public void setDrivers(Drivers drivers) {
		this.drivers = drivers;
	}
	public Drivers getDrivers() {
		return drivers;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getOperId() {
		return operId;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCarType(Integer carType) {
		this.carType = carType;
	}
	public Integer getCarType() {
		return carType;
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
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
	
}