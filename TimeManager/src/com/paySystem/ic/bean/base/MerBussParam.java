package com.paySystem.ic.bean.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName:MerBussParam
 * @Description:商户业务参数实体类
 * @date: 2014-6-26下午02:29:36
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name="b_merbussparam")
public class MerBussParam  implements Serializable{

	private static final long serialVersionUID = 1L;

	/**商户号*/
	private String merId;
	
	/**商品货号前缀*/
	private String gnoPrefix;
	
	/**客户下单是否给商户发送信息*/
	private Integer cosmSign;
	
	/**客户付款是否给商户发送信息*/
	private Integer cpsmSign;
	
	/**客户付款是否给客户发送信息*/
	private Integer cpscSign;
	
	/**商户发货是否给客户发送信息*/
	private Integer msscSign;
	
	/**店铺已有商品数量*/
	private Integer exitsCount;
	
	/**店铺已上架商品数量*/
	private Integer salingCount;
	
	/** icp证书号 */
	private String icpNo;
	
	/** icp证书文件路径 */
	private String icpFilePath;

	@Id
	@Column(length=15)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	@Column(length=20)
	public String getGnoPrefix() {
		return gnoPrefix;
	}

	public void setGnoPrefix(String gnoPrefix) {
		this.gnoPrefix = gnoPrefix;
	}

	@Column(columnDefinition="char(1)")
	public Integer getCosmSign() {
		return cosmSign;
	}

	public void setCosmSign(Integer cosmSign) {
		this.cosmSign = cosmSign;
	}

	@Column(columnDefinition="char(1)")
	public Integer getCpsmSign() {
		return cpsmSign;
	}

	public void setCpsmSign(Integer cpsmSign) {
		this.cpsmSign = cpsmSign;
	}

	@Column(columnDefinition="char(1)")
	public Integer getCpscSign() {
		return cpscSign;
	}

	public void setCpscSign(Integer cpscSign) {
		this.cpscSign = cpscSign;
	}

	@Column(columnDefinition="char(1)")
	public Integer getMsscSign() {
		return msscSign;
	}

	public void setMsscSign(Integer msscSign) {
		this.msscSign = msscSign;
	}

	@Column
	public Integer getExitsCount() {
		return exitsCount;
	}

	public void setExitsCount(Integer exitsCount) {
		this.exitsCount = exitsCount;
	}

	@Column
	public Integer getSalingCount() {
		return salingCount;
	}

	public void setSalingCount(Integer salingCount) {
		this.salingCount = salingCount;
	}

	@Column(length=40)
	public String getIcpNo() {
		return icpNo;
	}

	public void setIcpNo(String icpNo) {
		this.icpNo = icpNo;
	}

	@Column(length=100)
	public String getIcpFilePath() {
		return icpFilePath;
	}

	public void setIcpFilePath(String icpFilePath) {
		this.icpFilePath = icpFilePath;
	}
	
	
	
}
