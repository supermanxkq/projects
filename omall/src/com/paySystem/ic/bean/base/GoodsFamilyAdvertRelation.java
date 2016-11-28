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
 *  商品分类 & 楼层广告关联Bean
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name="b_goodsfamilyadvRel")
public class GoodsFamilyAdvertRelation implements Serializable {

	private static final long serialVersionUID = -861359250215612217L;
	
	/** 关联Id */
	private Integer relId;
	
	/** 分类Id */
	private Integer familyId;
	
    /**广告主体标志
     *  0:商品广告
     *  1:店铺广告
     *  2:品牌广告
     *  3:活动广告
     * */
    private Integer advertContentSign;
    
    /**
     * 内容Id
     */
    private Integer objectId;
    
    /**广告图片路径*/
    private String advertPicPath;
    
    /**创建时间*/
    private Date createTime;
    
    /**更新时间 */
    private Date updateTime;
    
    /**操作员Id*/
    private String operatorId;
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
	public Integer getRelId() {
		return relId;
	}

	public void setRelId(Integer relId) {
		this.relId = relId;
	}

	@Column
	public Integer getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}

	@Column(columnDefinition="char(1)")
	public Integer getAdvertContentSign() {
		return advertContentSign;
	}

	public void setAdvertContentSign(Integer advertContentSign) {
		this.advertContentSign = advertContentSign;
	}

	@Column
	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
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
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(length=20)
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	@Column(length=255)
	public String getAdvertPicPath() {
		return advertPicPath;
	}

	public void setAdvertPicPath(String advertPicPath) {
		this.advertPicPath = advertPicPath;
	}

	
	
}
