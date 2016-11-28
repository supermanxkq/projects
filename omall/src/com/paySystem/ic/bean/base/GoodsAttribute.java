package com.paySystem.ic.bean.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**  
 * @Title: GoodsAttribute.java
 * @Package: com.paySystem.ic.bean.base
 * @Description: 商品属性管理
 * @Author: yanwuyang 
 * @Date: 2014-8-19 下午10:16:24
 * @Version: V1.0  
 */

@Entity
@Table(name = "b_attribute")
public class GoodsAttribute implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3520102132313325290L;

    /**属性编号*/
    private Integer attrId;

    /**属性名称*/
    private String attrName;

    /**是否为枚举类型*/
    private Integer isEn;

    /**显示名称*/
    private String showlable;
    /**状态  0启用 1停用*/
    private Integer status;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }
    @Column(columnDefinition = "char(1)")
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(length = 60, nullable = false)
    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    @Column(nullable = false)
    public Integer getIsEn() {
        return isEn;
    }

    public void setIsEn(Integer isEn) {
        this.isEn = isEn;
    }

    @Column(length = 30, nullable = false)
    public String getShowlable() {
        return showlable;
    }

    public void setShowlable(String showlable) {
        this.showlable = showlable;
    }

}
