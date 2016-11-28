package com.paySystem.ic.web.dto.base;

/**
 * @ClassName: KeyValue
 * @Description: key value保存对象
 * @date: 2014-8-26下午03:29:12
 * @author: Jacky
 * @version: V1.0
 */
public class KeyValue {
	
	/** 存储key **/
	private int key;
	
	/** 存储value **/
	private String value;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
