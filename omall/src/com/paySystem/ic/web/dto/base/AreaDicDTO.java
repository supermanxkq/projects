package com.paySystem.ic.web.dto.base;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall
 * @ClassName:AreaDic
 * @Description:省和市表数据传输对象
 * @date: 2014-11-3下午05:45:40
 * @author: 徐凯强
 * @version: V1.0
 */
public class AreaDicDTO extends BaseDTO implements Serializable {

	/***/
	private static final long serialVersionUID = -3325818591633503618L;
	/** 主键 **/
	private Integer Fcode;
	/** 名称 **/
	private String Fname;
	/** 类型 **/
	private String Ftype;

	public Integer getFcode() {
		return Fcode;
	}

	public void setFcode(Integer fcode) {
		Fcode = fcode;
	}

	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		Fname = fname;
	}

	public String getFtype() {
		return Ftype;
	}

	public void setFtype(String ftype) {
		Ftype = ftype;
	}

}
