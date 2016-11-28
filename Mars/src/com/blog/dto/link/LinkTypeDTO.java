package com.blog.dto.link;

import java.util.Set;

import com.blog.bean.links.Link;


/**
 * @ProjectName:blog
 * @ClassName:LinkType
 * @Description:链接类型
 * @date: 2015-6-24下午05:18:35
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-6-24下午05:18:35
 */
public class LinkTypeDTO {
	/** 链接类型编号 */
	private Integer linkTypeId;
	/** 类型名称 */
	private String linkTypeName;
	/** 链接类型对应详情href */
	private String linkTypeHref;
	/** 一对多链接信息 */
	private Set<Link> links;
	
	
	public Set<Link> getLinks() {
		return links;
	}

	public void setLinks(Set<Link> links) {
		this.links = links;
	}

	public Integer getLinkTypeId() {
		return linkTypeId;
	}

	public String getLinkTypeHref() {
		return linkTypeHref;
	}

	public String getLinkTypeName() {
		return linkTypeName;
	}

	public void setLinkTypeId(Integer linkTypeId) {
		this.linkTypeId = linkTypeId;
	}

	public void setLinkTypeHref(String linkTypeHref) {
		this.linkTypeHref = linkTypeHref;
	}

	public void setLinkTypeName(String linkTypeName) {
		this.linkTypeName = linkTypeName;
	}

}
