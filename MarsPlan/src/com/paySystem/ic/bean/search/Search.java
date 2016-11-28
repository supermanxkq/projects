package com.paySystem.ic.bean.search;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 
 * @ProjectName:MarsPlan
 * @ClassName:Search
 * @Description:索引搜索数据传递类
 * @date: Apr 16, 20162:38:25 PM
 * @author: bruce
 * @version: V1.0
 */
public class Search implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3942074614349634183L;
	/** 编号 */
	private String id;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/** 要跳转的url */
	private String url;
	/** 分类 */
	private String type;
	/** 图片路径 */
	private String picPath;
	private String readTimes;
	/** 创建日期 */
	private String createDate;

	public String getReadTimes() {
		return readTimes;
	}

	public void setReadTimes(String readTimes) {
		this.readTimes = readTimes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
