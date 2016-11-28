/**  
* @Title: AdvertMa.java
* @Package: com.paySystem.ic.bean.Marketing
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-16 下午04:52:34
* @Version: V1.0  
*/
package com.paySystem.ic.bean.Marketing;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:omall2014911
 * @ClassName:AdvertMa
 * @Description:TODO
 * @date: 2014-9-16
 * @author: 孙晓磊
 * @version: V1.0
 */

@Entity
@Table(name = "M_ADVERTMA")
public class AdvertMa {
	
	/**序列号用于反序列化**/
	private static final long serialVersionUID = -1781073735536614648L;
	/**广告编号**/
	private Integer advertId;
	/**广告名称**/
	private String  advertName;
	/**广告内容**/
	private String  advertContent;
	/**媒体类型**/
	private Integer mediaType;
	/**广告所在位置类型Id**/
	private Integer positionType;
	/**图片路径**/
	private String  imagePath;
	/**开启状态 0：开启 1：不开启**/
	private String  openType;
	/**开始时间**/
	private Date  beginTime;
	/**结束时间**/
	private Date  endTime;
	/**广告联系人**/
	private String contract;
	/**联系人email**/
	private String ContractEmail;
	/**联系人电话**/
	private String ContractTel;
	/**广告链接**/
	private String adverCot;
	
	/**get set方法**/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getAdvertId() {
		return advertId;
	}
	public void setAdvertId(Integer advertId) {
		this.advertId = advertId;
	}
	@Column(length = 60)
	public String getAdvertName() {
		return advertName;
	}
	public void setAdvertName(String advertName) {
		this.advertName = advertName;
	}
	@Column(length=500)
	public String getAdvertContent() {
		return advertContent;
	}
	public void setAdvertContent(String advertContent) {
		this.advertContent = advertContent;
	}
	@Column(length=1)
	public Integer getMediaType() {
		return mediaType;
	}
	public void setMediaType(Integer mediaType) {
		this.mediaType = mediaType;
	}
	@Column(length=1)
	public Integer getPositionType() {
		return positionType;
	}
	public void setPositionType(Integer positionType) {
		this.positionType = positionType;
	}
	@Column(length=100)
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Column(columnDefinition = "char(1)")
	public String getOpenType() {
		return openType;
	}
	public void setOpenType(String openType) {
		this.openType = openType;
	}
	@Column
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	@Column
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	@Column(length=30)
	public String getContractEmail() {
		return ContractEmail;
	}
	public void setContractEmail(String contractEmail) {
		ContractEmail = contractEmail;
	}
	@Column(length=11)
	public String getContractTel() {
		return ContractTel;
	}
	public void setContractTel(String contractTel) {
		ContractTel = contractTel;
	}
	@Column(length=30)
	public String getAdverCot() {
		return adverCot;
	}
	public void setAdverCot(String adverCot) {
		this.adverCot = adverCot;
	}
	
	
	
}
