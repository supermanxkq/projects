package com.paySystem.ic.web.dto.base;

/**
 * 注释后面加
 * @author Jacky
 */
public class GoodsFormatGroupInfoDTO {
	
	/** 分组名称**/
	private String fGroupName;
	
	/** 规格id **/
	private int formatId;

	/** 规格名称**/
	private String formatName;
	
	
	public String getFormatName() {
		return formatName;
	}

	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}

	public String getfGroupName() {
		return fGroupName;
	}

	public void setfGroupName(String fGroupName) {
		this.fGroupName = fGroupName;
	}

	public int getFormatId() {
		return formatId;
	}

	public void setFormatId(int formatId) {
		this.formatId = formatId;
	}
}
