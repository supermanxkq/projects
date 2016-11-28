package com.blog.bean.links;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @ProjectName:blog
 * @ClassName:LinkType
 * @Description:链接类型
 * @date: 2015-6-24下午05:18:35
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-6-24下午05:18:35
 */
@Entity
@Table(name = "t_linktype")
public class LinkType {
	/** 链接类型编号 */
	private Integer linkTypeId;
	/** 类型名称 */
	private String linkTypeName;
	/** 链接类型对应详情href */
	private String linkTypeHref;
	/** 一对多链接信息 */
	private Set<Link> links;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getLinkTypeId() {
		return linkTypeId;
	}

	@OneToMany(mappedBy = "linkType", cascade = { CascadeType.PERSIST })
	public Set<Link> getLinks() {
		return links;
	}

	@Column
	public String getLinkTypeHref() {
		return linkTypeHref;
	}

	@Column
	public String getLinkTypeName() {
		return linkTypeName;
	}

	public void setLinkTypeId(Integer linkTypeId) {
		this.linkTypeId = linkTypeId;
	}

	public void setLinks(Set<Link> links) {
		this.links = links;
	}

	public void setLinkTypeHref(String linkTypeHref) {
		this.linkTypeHref = linkTypeHref;
	}

	public void setLinkTypeName(String linkTypeName) {
		this.linkTypeName = linkTypeName;
	}

}
