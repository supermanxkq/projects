/**  
* @Title: AdvertMaDTO.java
* @Package: com.paySystem.ic.web.dto.marketing
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-16 下午05:16:01
* @Version: V1.0  
*/
package com.paySystem.ic.web.dto.marketing;


import java.io.File;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall2014911
 * @ClassName:AdvertMaDTO
 * @Description:TODO
 * @date: 2014-9-16
 * @author: 孙晓磊
 * @version: V1.0
 */
public class AdvertMaDTO extends BaseDTO{

	/** 广告编号 **/
	private Integer advertId;
	/** 广告名称 **/
	private String advertName;
	/** 广告内容 **/
	private String advertContent;
	/** 媒体类型 **/
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
	
	private File  imageFile;
	
	private String imageFileFileName;

	public Integer getAdvertId() {
		return advertId;
	}
	public void setAdvertId(Integer advertId) {
		this.advertId = advertId;
	}
	public String getAdvertName() {
		return advertName;
	}
	public void setAdvertName(String advertName) {
		this.advertName = advertName;
	}
	public String getAdvertContent() {
		return advertContent;
	}
	public void setAdvertContent(String advertContent) {
		this.advertContent = advertContent;
	}
	public Integer getMediaType() {
		return mediaType;
	}
	public void setMediaType(Integer mediaType) {
		this.mediaType = mediaType;
	}
	
	public Integer getPositionTypeId() {
		return positionTypeId;
	}
	public void setPositionTypeId(Integer positionTypeId) {
		this.positionTypeId = positionTypeId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getOpenType() {
		return openType;
	}
	public void setOpenType(String openType) {
		this.openType = openType;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getContractEmail() {
		return ContractEmail;
	}
	public void setContractEmail(String contractEmail) {
		ContractEmail = contractEmail;
	}
	public String getContractTel() {
		return ContractTel;
	}
	public void setContractTel(String contractTel) {
		ContractTel = contractTel;
	}
	public String getAdverCot() {
		return adverCot;
	}
	public void setAdverCot(String adverCot) {
		this.adverCot = adverCot;
	}
	public Integer getShowId() {
		return showId;
	}
	public void setShowId(Integer showId) {
		this.showId = showId;
	}
	public Integer getAdvertType() {
		return advertType;
	}
	public void setAdvertType(Integer advertType) {
		this.advertType = advertType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getImageFileFileName() {
		return imageFileFileName;
	}
	public void setImageFileFileName(String imageFileFileName) {
		this.imageFileFileName = imageFileFileName;
	}
	public File getImageFile() {
		return imageFile;
	}
	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}
	
	
	
}
