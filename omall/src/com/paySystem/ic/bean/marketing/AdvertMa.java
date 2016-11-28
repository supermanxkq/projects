/**  
 * @Title: AdvertMa.java
 * @Package: com.paySystem.ic.bean.Marketing
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-9-16 下午04:52:34
 * @Version: V1.0  
 */
package com.paySystem.ic.bean.marketing;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @ProjectName:omall2014911
 * @ClassName:AdvertMa
 * @Description:TODO
 * @date: 2014-9-16
 * @author: 孙晓磊
 * @version: V1.0
 */

@Entity
@Table(name = "b_advertma")
public class AdvertMa {

	/** 序列号用于反序列化 **/
	private static final long serialVersionUID = -1781073735536614648L;
	/** 广告编号 **/
	private Integer advertId;
	/** 广告名称 **/
	private String advertName;
	/** 广告内容 **/
	private String advertContent;
	// 媒体类型 0：图片 1:文字 9：删除
	private Integer mediaType;
	/** 广告所在位置类型Id **/
	private Integer positionTypeId;
	/** 图片路径 **/
	private String imagePath;
	/** 开启状态 0：开启 1：不开启 **/
	private String openType;
	/** 开始时间 **/
	private Date beginTime;
	/** 结束时间 **/
	private Date endTime;
	/** 广告联系人 **/
	private String contract;
	/** 联系人email **/
	private String ContractEmail;
	/** 联系人电话 **/
	private String ContractTel;
	/** 广告链接 **/
	private String adverCot;
	/** 广告图片显示顺序 ***/
	private Integer showId;
    //广告类型0： 商品广告1： 店铺广告 2： 品牌广告 3, 活动广告
	private Integer advertType;
	//创建时间
	private Date createTime;
	//修改时间
	private Date updateTime;
	/** get set方法 **/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(length = 500)
	public String getAdvertContent() {
		return advertContent;
	}

	public void setAdvertContent(String advertContent) {
		this.advertContent = advertContent;
	}

	@Column(length = 1)
	public Integer getMediaType() {
		return mediaType;
	}

	public void setMediaType(Integer mediaType) {
		this.mediaType = mediaType;
	}
	@Column(length = 100)
	public String getImagePath() {
		return imagePath;
	}

	public Integer getPositionTypeId() {
		return positionTypeId;
	}
	@Column(length = 1)
	public void setPositionTypeId(Integer positionTypeId) {
		this.positionTypeId = positionTypeId;
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

	@Column(length = 30)
	public String getContractEmail() {
		return ContractEmail;
	}

	public void setContractEmail(String contractEmail) {
		ContractEmail = contractEmail;
	}

	@Column(length = 11)
	public String getContractTel() {
		return ContractTel;
	}

	public void setContractTel(String contractTel) {
		ContractTel = contractTel;
	}

	@Column(length = 30)
	public String getAdverCot() {
		return adverCot;
	}

	public void setAdverCot(String adverCot) {
		this.adverCot = adverCot;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getShowId() {
		return showId;
	}

	public void setShowId(Integer showId) {
		this.showId = showId;
	}
	@Column(columnDefinition = "char(1)")
	public Integer getAdvertType() {
		return advertType;
	}

	public void setAdvertType(Integer advertType) {
		this.advertType = advertType;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
