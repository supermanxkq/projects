package com.paySystem.ic.bean.member;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.paySystem.ic.bean.base.Drivers;
import com.paySystem.ic.bean.base.Organs;

/***
 * 会员信息表
 * 
 * @author 井建国
 * 
 */
@Entity
@Table(name = "M_Member")
public class Member implements Serializable {
    private static final long serialVersionUID = 6075428406653446377L;

    /** 会员编号 */
    private String memId;

    /** 会员真实姓名 */
    private String memRealName;

    /** 会员证件类型 1： 身份证 2：军官证 3：护照 */
    private Integer perType;

    /** 会员证件号码 */
    private String personId;

    /** 会员电话 */
    private String teleNo;

    /** 会员性别 1：男 2：女 */
    private Integer sex;

    /** 会员所属区域 */
    private String areaId;

    /** 会员电子邮箱 */
    private String email;

    /** 会员群组 */
    private String groupId;
    
    /**会员等级实体*/
    private MemGrade memGrade;
    
    /** 会员信息修改时间 */
    private Date updateTime;

    /** 会员信息创建时间 */
    private Date createTime;

    /** 会员所属机构 */
    private Organs organs;

    /** 会员状态 1：正常 2;禁用 9：删除 */
    private Integer status;

    /*** 辅表映射 */
    private Amember amember;

    /**操作人*/
    private String operId;

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

    /**密码*/
    private String password;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "amemId")
    public Amember getAmember() {
        return amember;
    }

    public void setAmember(Amember amember) {
        this.amember = amember;
    }

    public Member() {
        super();
    }

    public Member(String memId) {
        this.memId = memId;
    }
    
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "gradeId")
    public MemGrade getMemGrade() {
		return memGrade;
	}

	public void setMemGrade(MemGrade memGrade) {
		this.memGrade = memGrade;
	}

	@Id
    @Column(length = 10)
    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    @Column(length = 30)
    public String getMemRealName() {
        return memRealName;
    }

    public void setMemRealName(String memRealName) {
        this.memRealName = memRealName;
    }

    @Column(length = 20)
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Column(length = 11)
    public String getTeleNo() {
        return teleNo;
    }

    public void setTeleNo(String teleNo) {
        this.teleNo = teleNo;
    }

    @Column(columnDefinition = "char(1)")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Column(length = 10)
    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @Column(length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(length = 8)
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Column
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
    @JoinColumn(name = "organId")
    public Organs getOrgans() {
        return organs;
    }

    public void setOrgans(Organs organs) {
        this.organs = organs;
    }

    @Column(columnDefinition = "char(1)")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setRecomId(String recomId) {
        RecomId = recomId;
    }

    @Column(length = 8)
    public String getRecomId() {
        return RecomId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    @Column(length = 30)
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setRecomType(Integer recomType) {
        RecomType = recomType;
    }

    @Column(columnDefinition = "char(1)")
    public Integer getRecomType() {
        return RecomType;
    }

    public void setDriverNo(String driverNo) {
        this.driverNo = driverNo;
    }

    @Column(length = 30)
    public String getDriverNo() {
        return driverNo;
    }

    public void setPerType(Integer perType) {
        this.perType = perType;
    }

    @Column(columnDefinition = "char(1)")
    public Integer getPerType() {
        return perType;
    }

    public void setDrivers(Drivers drivers) {
        this.drivers = drivers;
    }

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "memId")
    public Drivers getDrivers() {
        return drivers;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    @Column(length = 30)
    public String getOperId() {
        return operId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(length = 8)
    public String getPassword() {
        return password;
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

    @Column(columnDefinition = "char(1)")
    public Integer getCarType() {
        return carType;
    }

    /**public void setPerType(Integer perType) {
    	this.perType = perType;
    }
    @Column(columnDefinition = "char(1)")
    public Integer getPerType() {
    	return perType;
    }*/

}
