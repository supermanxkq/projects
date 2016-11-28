package com.paySystem.ic.web.dto.goods;

import java.io.Serializable;
import java.util.List;
/**  
* @Title: GoodsTempFormatDTO.java
* @Package: com.paySystem.ic.web.dto.goods
* @Description: 商品零时规格DTO
* @Author: Jacky
* @Date: 2014-08-06
* @Version: V1.0  
*/
public class GoodsTempFormatDTO implements Serializable {
	private static final long serialVersionUID = -6733228584183515259L;
	
	/** 规格分组集合*/
	private List<GoodsFormatNameDTO> formatGroupList;

	public List<GoodsFormatNameDTO> getFormatGroupList() {
		return formatGroupList;
	}

	public void setFormatGroupList(List<GoodsFormatNameDTO> formatGroupList) {
		this.formatGroupList = formatGroupList;
	}
	
	
}
