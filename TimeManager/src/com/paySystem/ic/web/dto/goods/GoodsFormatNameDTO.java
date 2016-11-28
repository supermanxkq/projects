package com.paySystem.ic.web.dto.goods;

import java.util.List;

import com.paySystem.ic.web.dto.base.GoodsFormatDTO;

/**  
* @Title: GoodsFormatNameDTO.java
* @Package: com.paySystem.ic.web.dto.goods
* @Description: 商品规格名称DTO对象
* @Author: Jacky
* @Date: 2014-08-06
* @Version: V1.0  
*/
public class GoodsFormatNameDTO {
	
	/**分组名称 */
	private String groupName;
	
	/**规格集合*/
	List<GoodsFormatDTO> formatList;
	
	/**规格名称集合 */
	List<FormatInfoDTO> formatNameList;
	
	public GoodsFormatNameDTO(String groupName,List<GoodsFormatDTO> formatList,List<FormatInfoDTO> formatNameList) {
		this.groupName = groupName;
		this.formatList = formatList;
		this.formatNameList = formatNameList;
	}
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<GoodsFormatDTO> getFormatList() {
		return formatList;
	}

	public void setFormatList(List<GoodsFormatDTO> formatList) {
		this.formatList = formatList;
	}

	public List<FormatInfoDTO> getFormatNameList() {
		return formatNameList;
	}

	public void setFormatNameList(List<FormatInfoDTO> formatNameList) {
		this.formatNameList = formatNameList;
	}
	
}
