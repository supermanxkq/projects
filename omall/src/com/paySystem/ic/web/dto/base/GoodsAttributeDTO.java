package com.paySystem.ic.web.dto.base;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;

/**  
 * @Title: GoodsAttribute.java
 * @Package: com.paySystem.ic.bean.base
 * @Description: 商品属性管理DTO
 * @Author: yanwuyang 
 * @Date: 2014-8-19 下午10:16:24
 * @Version: V1.0  
 */

public class GoodsAttributeDTO extends BaseDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6545922135142089619L;

    /**属性编号*/
    private Integer attrId;

    /**属性名称*/
    private String attrName;

    /**是否为枚举类型*/
    private Integer isEn;

    private String isParent;

    /**显示名称*/
    private String showlable;
    /**状态  0启用 1停用*/
    private Integer status;
    

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsEn() {
        return isEn;
    }

    public void setIsEn(Integer isEn) {
        this.isEn = isEn;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getShowlable() {
        return showlable;
    }

    public void setShowlable(String showlable) {
        this.showlable = showlable;
    }

}
