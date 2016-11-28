package com.blog.bean.links;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @ProjectName:blog
 * @ClassName:link
 * @Description:超链接实体
 * @date: 2015-6-24下午04:44:15
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-6-24下午04:44:15
 */
@Entity
@Table(name = "t_link")
public class Link {
	/** 链接编号 */
	private Integer linkId;
	/** 链接名称 */
	private String linkName;
	/** 链接地址 */
	private String href;
	/** 链接类型 */
	private LinkType linkType;
	/**logo地址*/
	private String logoHref;
	@Column
	public String getHref() {
		return href;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getLinkId() {
		return linkId;
	}

	@Column
	public String getLinkName() {
		return linkName;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "linkTypeId")
	public LinkType getLinkType() {
		return linkType;
	}
	@Column
	public String getLogoHref() {
		return logoHref;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public void setLinkType(LinkType linkType) {
		this.linkType = linkType;
	}

	public void setLogoHref(String logoHref) {
		this.logoHref = logoHref;
	}
	
}
