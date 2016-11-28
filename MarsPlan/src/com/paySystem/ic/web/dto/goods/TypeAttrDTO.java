package com.paySystem.ic.web.dto.goods;

import java.io.Serializable;
import java.util.List;

import com.paySystem.ic.bean.goods.AttrEntity;
/**
 * 商品分类和属性映射表
 * @ClassName: StockPriMa
 * @Description: 商品分类和属性映射表
 * @date: 2014-08-06 上午02:29:36
 * @author: Jacky
 * @version: V1.0
 */
public class TypeAttrDTO implements Serializable {
	private static final long serialVersionUID = 507783491534459311L;
	
	/**属性编号 */
	private int attrId;
	
	/**属性名称 */
	private String attrName;
	
	/**是否是枚举 */
	private Integer isEn;
	
	/**属性值List */
	private List<AttrEntity> attValueList;
	
	
	public List<AttrEntity> getAttValueList() {
		return attValueList;
	}

	public void setAttValueList(List<AttrEntity> attValueList) {
		this.attValueList = attValueList;
	}

	public Integer getIsEn() {
		return isEn;
	}

	public void setIsEn(Integer isEn) {
		this.isEn = isEn;
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
