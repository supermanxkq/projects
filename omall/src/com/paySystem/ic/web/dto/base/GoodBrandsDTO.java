package com.paySystem.ic.web.dto.base;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**  
 * 
* @Title: GoodBrandsDTO.java
* @Package: com.paySystem.ic.web.dto.base
* @Description: 品牌管理DTO对象
* @Author: Jacky
* @Date: 2014-08-01
* @Version: V1.0  
*/
public class GoodBrandsDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = -3069387422137960472L;

	/** 品牌信息编号 **/
	private int brandId;
	
	/** 品牌名称 **/
	private String brandName;
	
	/** 品牌网址  **/
	private String brandUrl;
	
	/** 品牌Logo文件**/
	private File brandLogo;
	
	/** 是否删除品牌Logo
	 *  0表示不删除
	 *  1表示删除
	 * **/
	private int deleteLogo = 0;
	
	/** 品牌文件名**/
	private String brandLogoFileName;
	
	/** 品牌描述  **/
	private String brandDesc;
	
	/** 是否显示 0是 1否 **/
	private short isShow;
	
	/** 创建时间  **/
	private Date createTime;
	
	/** 操作人 **/
	private String operator;
	
	/** 是否删除
	 * 1表示删除
	 * 0表示正常**/
	
	private short isDeleted;

	
	public short getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(short isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getDeleteLogo() {
		return deleteLogo;
	}

	public void setDeleteLogo(int deleteLogo) {
		this.deleteLogo = deleteLogo;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public File getBrandLogo() {
		return brandLogo;
	}

	public void setBrandLogo(File brandLogo) {
		this.brandLogo = brandLogo;
	}

	public String getBrandLogoFileName() {
		return brandLogoFileName;
	}

	public void setBrandLogoFileName(String brandLogoFileName) {
		this.brandLogoFileName = brandLogoFileName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandUrl() {
		return brandUrl;
	}

	public void setBrandUrl(String brandUrl) {
		this.brandUrl = brandUrl;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	public short getIsShow() {
		return isShow;
	}

	public void setIsShow(short isShow) {
		this.isShow = isShow;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
}
