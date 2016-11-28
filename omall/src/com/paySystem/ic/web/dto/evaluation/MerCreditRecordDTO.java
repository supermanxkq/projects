package com.paySystem.ic.web.dto.evaluation;

import java.io.Serializable;
import java.util.List;

import com.paySystem.ic.web.dto.BaseDTO;


/**  
 * @Title: MerCreditRecord.java
 * @Package: com.paySystem.ic.bean.evaluation
 * @Description: 评价记录
 * @Author: yanwuyang 
 * @Date: 2014-10-20 下午4:57:32
 * @Version: V1.0  
 */

public class MerCreditRecordDTO extends BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**信息ID */
	private Integer mcrId;
	/**商户编号 */
	private String merId;
	/**订单ID */
	private String orderId;
	/**商品ID */
	private Integer	goodsId;
	/**评价类型(好评/中评/差评)类型 */
	private Integer appType;
	/**好评分数 */
	private Integer goodsapprise;
	/**宝贝描述相符 */
	private Integer goodsMatch;
	/** 服务态度*/
	private Integer serviceAtt;
	/** 发货速度*/
	private Integer sendSpead;
	/**会员ID（评价人ID） */
	private String memId;
	/**创建日期 */
	private String createTIme;
	/**更新日期 */
	private String updateTIme;
	/** 评价方式自动/手动)类型*/
	private Integer mcrType;
	
	/** 商品名称*/
	private String goodsName;
	
	/** 用户类型*/
	private Integer memType;
	
	/**评论内容*/
	private String context;
	
	/**评论类型*/
	private Integer griType;
	
	/**评论ID*/
	private Integer griId;
	

	/**
	 * 公开/匿名：0，公开；1，匿名；
	 */
	private Integer disType;

	private String beginDate;
	
	private String endDate;
	
	
	/** 评价与被评价的标记 1 是给卖家的评价 2 是给买家的评价*/
	private Integer flag;
	
	/** 评论回复*/
	private List<CriticalContextDTO> contextDTOs;

	public Integer getMcrId() {
		return mcrId;
	}

	public void setMcrId(Integer mcrId) {
		this.mcrId = mcrId;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}
	
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	
	public Integer getAppType() {
		return appType;
	}
	
	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public Integer getGoodsapprise() {
		return goodsapprise;
	}

	public void setGoodsapprise(Integer goodsapprise) {
		this.goodsapprise = goodsapprise;
	}

	public Integer getGoodsMatch() {
		return goodsMatch;
	}

	public void setGoodsMatch(Integer goodsMatch) {
		this.goodsMatch = goodsMatch;
	}

	public Integer getServiceAtt() {
		return serviceAtt;
	}

	public void setServiceAtt(Integer serviceAtt) {
		this.serviceAtt = serviceAtt;
	}

	public Integer getSendSpead() {
		return sendSpead;
	}

	public void setSendSpead(Integer sendSpead) {
		this.sendSpead = sendSpead;
	}
	
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getCreateTIme() {
		return createTIme;
	}

	public void setCreateTIme(String createTIme) {
		this.createTIme = createTIme;
	}

	public String getUpdateTIme() {
		return updateTIme;
	}

	public void setUpdateTIme(String updateTIme) {
		this.updateTIme = updateTIme;
	}

	public Integer getMcrType() {
		return mcrType;
	}

	public void setMcrType(Integer mcrType) {
		this.mcrType = mcrType;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getMemType() {
		return memType;
	}

	public void setMemType(Integer memType) {
		this.memType = memType;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Integer getGriType() {
		return griType;
	}

	public void setGriType(Integer griType) {
		this.griType = griType;
	}

	public Integer getGriId() {
		return griId;
	}

	public void setGriId(Integer griId) {
		this.griId = griId;
	}

	public List<CriticalContextDTO> getContextDTOs() {
		return contextDTOs;
	}

	public void setContextDTOs(List<CriticalContextDTO> contextDTOs) {
		this.contextDTOs = contextDTOs;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getDisType() {
		return disType;
	}

	public void setDisType(Integer disType) {
		this.disType = disType;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
}
