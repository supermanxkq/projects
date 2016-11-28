package com.paySystem.ic.web.dto.goods;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * 
 * @ClassName: StockPriMa
 * @Description: 属性值枚举类
 * @date: 2014-08-06 上午02:29:36
 * @author: Jacky
 * @version: V1.0
 */
public class AttrEntityDTO extends BaseDTO implements Serializable {

    /***/
    private static final long serialVersionUID = -1537133702552784367L;

    /** 属性枚举ID */
    private int attrEnId;

    /** 属性枚举名称 */
    private String attrEnName;

    /** 属性编号 */
    private int attrId;

    /** 属性名称 */
    private String attrName;
    /**状态  0启用1停用*/
    private Integer status;

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public int getAttrEnId() {
        return attrEnId;
    }

    public void setAttrEnId(int attrEnId) {
        this.attrEnId = attrEnId;
    }

    public String getAttrEnName() {
        return attrEnName;
    }

    public void setAttrEnName(String attrEnName) {
        this.attrEnName = attrEnName;
    }

    public int getAttrId() {
        return attrId;
    }

    public void setAttrId(int attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

}
