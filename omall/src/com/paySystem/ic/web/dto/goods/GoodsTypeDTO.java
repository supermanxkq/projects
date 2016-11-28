package com.paySystem.ic.web.dto.goods;

import java.io.Serializable;
import java.util.List;

/**  
* @Title: GoodsTypeDTO.java
* @Package: com.paySystem.ic.web.dto.goods
* @Description: 商品分类DTO对象
* @Author: Jacky
* @Date: 2014-08-06
* @Version: V1.0  
*/
public class GoodsTypeDTO implements Serializable {
	private static final long serialVersionUID = -8037017105255251799L;
	
	/** 商品分类id **/
	private int id;
	
	/** 分类文案 **/
	private String text;
	
	/** 展开？ closed为不展开 **/
	private String state;
	
	/** 每一级分类孩子节点 **/
	private List<GoodsTypeDTO> children;

	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<GoodsTypeDTO> getChildren() {
		return children;
	}

	public void setChildren(List<GoodsTypeDTO> children) {
		this.children = children;
	}
	
}
