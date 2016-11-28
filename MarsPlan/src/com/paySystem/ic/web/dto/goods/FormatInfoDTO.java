package com.paySystem.ic.web.dto.goods;

/**
 * @ClassName: FormatInfoDTO
 * @Description: 规格信息DTO
 * @date: 2014-08-06 上午02:29:36
 * @author: Jacky
 * @version: V1.0
 */
public class FormatInfoDTO {
	
	/** 编号 **/
	private long fInfoId;
	
	/** 规格id**/
	private long formatId;
	
	/** 规格值**/
	private String formatValue;
	
	/** 商品id**/
	private long goodsId;
	
	/** 规格组名**/
	private String groupName;
	
	/** 规格名称**/
	private String formatName;
	
	public String getFormatName() {
		return formatName;
	}

	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public long getfInfoId() {
		return fInfoId;
	}

	public void setfInfoId(long fInfoId) {
		this.fInfoId = fInfoId;
	}

	public long getFormatId() {
		return formatId;
	}

	public void setFormatId(long formatId) {
		this.formatId = formatId;
	}

	public String getFormatValue() {
		return formatValue;
	}

	public void setFormatValue(String formatValue) {
		this.formatValue = formatValue;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}
	
}
