package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:GoodsFamilyDTO
 * @Description:商品分类DTO
 * @date: 2014-6-27下午07:11:44
 * @author: 张亚运
 * @version: V1.0
 */
public class GoodsFamilyDTO extends BaseDTO implements Serializable {

    /**
     *序列号
     */
    private static final long serialVersionUID = -8522185316993875460L;

    /** 分类信息编号 */
    private Integer familyId;

    /** 上级分类信息名称 */
    private String pFamilyName;

    /** 上级分类Id*/
    private Integer parentId;

    /** 分类信息名称 */
    private String familyName;

    /** 节点等级 */
    private Integer nodeLevel;

    /** 商品分类图片路径 */
    private String picPath;

    /** 关键字 */
    private String keyWords;

    /** 使用状态*/
    private Integer status;

    /** 是否默认展开 */
    private Integer defaultShow;

    /**
     * 分类类型 0：手工分类 1：自动分类
     * */
    private Integer familyWay;

    /** 创建时间 */
    private Date createTime;

    /** 创建人 */
    private String operator;
    
    /** 是否是私有属性*/
    private Integer preFlag;

    public Integer getPreFlag() {
		return preFlag;
	}

	public void setPreFlag(Integer preFlag) {
		this.preFlag = preFlag;
	}

	public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public String getpFamilyName() {
        return pFamilyName;
    }

    public void setpFamilyName(String pFamilyName) {
        this.pFamilyName = pFamilyName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Integer getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = getPicPath();
    }

    public Integer getDefaultShow() {
        return defaultShow;
    }

    public void setDefaultShow(Integer defaultShow) {
        this.defaultShow = defaultShow;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFamilyWay() {
        return familyWay;
    }

    public void setFamilyWay(Integer familyWay) {
        this.familyWay = familyWay;
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
