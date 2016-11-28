package com.paySystem.ic.web.dto.log;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;

public class FunctionsDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -1324112849666702193L;
	/** 流水号 */
	private Integer id;
	/** 操作类型 1添加 2修改 3删除 4审核 */
	private Integer operType;
	/** 操作员 */
	private String operId;
	/** 关联内容 */
	private String relecontents;
	/** 模块名称 */
	private String moduleName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOperType() {
		return operType;
	}
	public void setOperType(Integer operType) {
		this.operType = operType;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getRelecontents() {
		return relecontents;
	}
	public void setRelecontents(String relecontents) {
		this.relecontents = relecontents;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
}
