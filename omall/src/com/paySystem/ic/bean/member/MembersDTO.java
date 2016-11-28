package com.paySystem.ic.bean.member;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * 
 * @ProjectName: omall
 * @ClassName: Member
 * @Description: 电子商务平台会员信息
 * @date: 2014-10-8 下午04:27:31
 * @author: 郭营
 * @version: V1.0
 */

public class MembersDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 6075428406653446377L;
	/** 会员编号 */
	private Integer memId;
	/** 会员真实姓名 */
	private String realName;
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

	/** 会员信息修改时间 */
	private Date updateTime;
	/** 会员信息创建时间 */
	private Date createTime;
	/** 会员状态 1：正常 2;禁用 9：删除 */
	private Integer status;
	/** 家乡地址 */
	private String homeAdress;
	/** 图片地址 */
	private String pictureAdress;
	/** 会员昵称 */
	private String memName;
	/** 生日 */
	private String birthDay;
	/** 居住地址 */
	private String stayAdress;

	private String hs_province;
	private String hs_city;
	private String hs_county;
	private String s_province;
	private String s_city;
	private String s_county;

	/** 用户名 **/
	private String userName;

	/** 密码 **/
	private String pwd;

	/** 登录名 **/
	private String loginString;

	public String getLoginString() {
		return loginString;
	}

	public void setLoginString(String loginString) {
		this.loginString = loginString;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getHomeAdress() {
		return homeAdress;
	}

	public void setHomeAdress(String homeAdress) {
		this.homeAdress = homeAdress;
	}

	public String getPictureAdress() {
		return pictureAdress;
	}

	public void setPictureAdress(String pictureAdress) {
		this.pictureAdress = pictureAdress;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getStayAdress() {
		return stayAdress;
	}

	public void setStayAdress(String stayAdress) {
		this.stayAdress = stayAdress;
	}

	public String getHs_province() {
		return hs_province;
	}

	public void setHs_province(String hsProvince) {
		hs_province = hsProvince;
	}

	public String getHs_city() {
		return hs_city;
	}

	public void setHs_city(String hsCity) {
		hs_city = hsCity;
	}

	public String getHs_county() {
		return hs_county;
	}

	public void setHs_county(String hsCounty) {
		hs_county = hsCounty;
	}

	public String getS_province() {
		return s_province;
	}

	public void setS_province(String sProvince) {
		s_province = sProvince;
	}

	public String getS_city() {
		return s_city;
	}

	public void setS_city(String sCity) {
		s_city = sCity;
	}

	public String getS_county() {
		return s_county;
	}

	public void setS_county(String sCounty) {
		s_county = sCounty;
	}

}
