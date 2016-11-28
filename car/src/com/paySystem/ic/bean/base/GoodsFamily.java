package com.paySystem.ic.bean.base;

import java.io.Serializable;
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
 * @ClassName:GoodsFamily
 * @Description:商品分类表
 * @date: 2014-6-26下午03:03:53
 * @author: 张亚运
 * @version: V1.0
 */
@Entity
@Table(name = "b_goodsfamily")
public class GoodsFamily implements Serializable {

    /**
     *版本序列号
     */
    private static final long serialVersionUID = 1L;

    /**分类信息编号*/
    private Integer familyId;

    /**上级分类Id*/
    private Integer parentId;

    /**分类信息名称 */
    private String familyName;

    /**节点等级
     * 0、顶级
     * 1、一级分类
     * 2、二级分类 */
    private Integer nodeLevel;

    /**商品分类图片路径 */
    private String picPath;

    /**是否默认展开
     * 1、是
     * 2、否 */
    private Integer defaultShow;

    /**使用状态
     * 1：正常 2;禁用 9：删除 */
    private Integer status;

    /**关键字 */
    private String keyWords;

    /**
     *分类类型 0：手工分类 1：自动分类
     * */
    private Integer familyWay;

    /**创建时间 */
    private Date createTime;

    /**创建人 */
    private String operator;
    
    /** 是否是私有属性*/
    private Integer preFlag;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    @Column
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Column(length = 60)
    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @Column(columnDefinition = "int(2)")
    public Integer getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    @Column(length = 100)
    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Column(columnDefinition = "char(1)")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(length = 20)
    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    @Column(columnDefinition = "char(1)")
    public Integer getDefaultShow() {
        return defaultShow;
    }

    public void setDefaultShow(Integer defaultShow) {
        this.defaultShow = defaultShow;
    }

    @Column(columnDefinition = "char(1)")
    public Integer getFamilyWay() {
        return familyWay;
    }

    public void setFamilyWay(Integer familyWay) {
        this.familyWay = familyWay;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(length = 20)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Column(columnDefinition = "char(1)")
	public Integer getPreFlag() {
		return preFlag;
	}

	public void setPreFlag(Integer preFlag) {
		this.preFlag = preFlag;
	}
    
    

}
