package com.paySystem.ic.web.dto.buss;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:PayParamDTO
 * @Description:活动管理信息DTO类
 * @date: 2014-8-21下午05:36:24
 * @author: 赵瑞群
 * @version: V1.0
 */
public class PromotionDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	/**活动编号*/
	private Integer proid;
	
	/**活动名称*/
	private String proname;
	
	/**活动主办方*/
	private String proHost;
	
	/**状态
		1：未招募
		2：招募中
		3：审核阶段
		4：活动中
		5：已结束
		6：已终止
		9：已删除
	*/
	private String status;
	
	/**活动性质
		1：折扣促销
		2：抽奖促销
		3：会员制促销
		4.赠品促销
	*/
	private String proType;
	
	/**活动名目
	1：季节性活动
	2：开业促销活动
	3：节庆促销活动
	4：新品上市促销
	5：庆典促销活动
	9：其它促销活动
	*/
	private String proItem;
	
	/**活动类型
	0：平台活动
	1：商户活动
	*/
	private String hostSign;
	
	/**开始时间*/
	private Date beginTime;
	
	/**结束时间*/
	private Date endTime;
	
	/**活动规则说明*/
	private String descr;
	
	/**活动图片*/
	private File proImg;
	
	/**活动图片名称*/
	private String proImgFileName;
	
	

	/**创建时间*/
	private Date createTime;
	
	/**更新时间*/
	private Date updateTime;
	
	/**操作人Id*/
	private String operatorId;
	
	/** 是否删除品牌Logo
	 *  0表示不删除
	 *  1表示删除
	 * **/
	private int deleteProImg = 0;
	
	

	public int getDeleteProImg() {
		return deleteProImg;
	}

	public void setDeleteProImg(int deleteProImg) {
		this.deleteProImg = deleteProImg;
	}

	public Integer getProid() {
		return proid;
	}

	public void setProid(Integer proid) {
		this.proid = proid;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public String getProHost() {
		return proHost;
	}

	public void setProHost(String proHost) {
		this.proHost = proHost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getProItem() {
		return proItem;
	}

	public void setProItem(String proItem) {
		this.proItem = proItem;
	}

	public String getHostSign() {
		return hostSign;
	}

	public void setHostSign(String hostSign) {
		this.hostSign = hostSign;
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

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public File getProImg() {
		return proImg;
	}

	public void setProImg(File proImg) {
		this.proImg = proImg;
	}
	
	public String getProImgFileName() {
		return proImgFileName;
	}

	public void setProImgFileName(String proImgFileName) {
		this.proImgFileName = proImgFileName;
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

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	
	
	
	
}
