package com.blog.web.action.link;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.bean.QueryResult;
import com.blog.bean.links.Link;
import com.blog.bean.links.LinkType;
import com.blog.dto.link.LinkDTO;
import com.blog.dto.link.LinkTypeDTO;
import com.blog.service.link.LinkService;
import com.blog.service.linktype.LinkTypeService;
import com.blog.util.EntityDtoConverter;
import com.blog.util.Utils;
import com.blog.web.action.BaseAction;

/**
 * 登录Action类
 * 
 * @version 2013-9-1 上午11:12:59
 */

@Controller("/link/link")
@Scope("prototype")
public class LinkAction extends BaseAction {
	/** 序列化 */
	private static final long serialVersionUID = 6527405819743413855L;
	// 注入资源
	@Resource
	private LinkService linkService;
	@Resource
	private LinkTypeService linkTypeService;
	// 实例化数据传输对象
	private LinkDTO linkDTO = new LinkDTO();
	// 实例化传输对象集合
	private List<LinkDTO> linkDTOs = new ArrayList<LinkDTO>();
	private List<LinkType> linkTypes = new ArrayList<LinkType>();

	/**
	 *@Title:list
	 *@Description:list方法
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-22下午03:02:16
	 */
	public String list() {
		return "list";
	}

	/**
	 *@MethodName:jsonPageList
	 *@Description:异步加载链接信息
	 *@throws Exception
	 *@author:徐半仙儿
	 *@return String
	 *@date:2015-6-24下午09:27:55
	 */
	public String jsonPageList() throws Exception {
		StringBuffer strings = new StringBuffer();
		/** 排序参数 */
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		/** 设置默认的排序规则 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderby.put("linkId", "desc");
		}
		/** 返回结果 */
		QueryResult<LinkDTO> queryResult = linkService.queryAll();
		QueryResult<LinkTypeDTO> linQueryResult = linkTypeService.queryAll();
		strings
				.append("<div class='tabbable tabbable-custom'><ul class='nav nav-tabs'>");
		// 链接类型
		for (int i = 0; i < linQueryResult.getResultlist().size(); i++) {
			LinkTypeDTO linkTypeDTO = linQueryResult.getResultlist().get(i);
			if (i == 0) {
				strings.append("<li class='active'><a href='#tab_1_" + (i + 1)
						+ "' data-toggle='tab'>"
						+ linkTypeDTO.getLinkTypeName() + "</a></li>");
			} else {
				strings.append("<li><a href='#tab_1_" + (i + 1)
						+ "' data-toggle='tab'>"
						+ linkTypeDTO.getLinkTypeName() + "</a></li>");
			}
		}
		strings.append("</ul>");

		// 链接内容
		strings.append("<div class='tab-content'>");
		for (int i = 0; i < linQueryResult.getResultlist().size(); i++) {
			LinkTypeDTO linkTypeDTO = linQueryResult.getResultlist().get(i);
			if (i == 0) {
				strings.append("<div class='tab-pane active' id='tab_1_"
						+ (i + 1) + "'><ul style='list-style:none;display:block;padding-bottom: 0;padding-left: 12px;position: relative;padding-top: 10px;width: 834px;'>");
			} else {
				strings.append("<div class='tab-pane' id='tab_1_"
						+ (i + 1) + "'><ul style='list-style:none;display:block;padding-bottom: 0;padding-left: 12px;position: relative;padding-top: 10px;width: 834px;'>");
			}
			for (int j = 0; j < queryResult.getResultlist().size(); j++) {
				linkDTO = queryResult.getResultlist().get(j);
				if (linkDTO.getLinkType().getLinkTypeId().equals(
						linkTypeDTO.getLinkTypeId())) {
					strings.append("<li style='margin:0px;padding:0px;text-align:left;line-height: 20px;vertical-align: middle;height: 20px;font-size: 14px;color: #000;width: 158px;margin: 5px 0 5px 4px;float:left;'><img width='18px' height='18px' src="
							+ linkDTO.getLogoHref() + ">&nbsp;&nbsp;&nbsp;&nbsp;<a href="
							+ linkDTO.getHref() + " target='_blank'>"
							+ linkDTO.getLinkName() + "</a></li>");
				}
			}
			strings.append("</ul></div>");
		}
		strings.append("</div></div>");

		return Utils.printInfo(strings.toString());
	}

	/**
	 *@MethodName:toEditPage
	 *@Description:跳转到编辑页面
	 *@author:徐半仙儿
	 *@return String
	 *@date:2015-7-17下午10:31:54
	 */
	public String toEditPage() {
		return "input";
	}

	/**
	 *@MethodName:addSave
	 *@Description:添加
	 *@author:徐半仙儿
	 *@return void
	 *@date:2015-7-17下午10:44:52
	 */
	public void addSave() {
		try {
			Link link = new Link();
			link = (Link) EntityDtoConverter.dto2Bean(linkDTO, link);
			linkService.save(link);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *@MethodName:queryLinkTypes
	 *@Description:添加
	 *@author:徐半仙儿
	 *@return void
	 *@date:2015-7-17下午10:44:52
	 */
	public String queryLinkTypes() {
		StringBuffer strings = new StringBuffer();
		try {
			QueryResult<LinkTypeDTO> linQueryResult = linkTypeService
					.queryAll();
			for (int i = 0; i < linQueryResult.getResultlist().size(); i++) {
				LinkTypeDTO linkTypeDTO = linQueryResult.getResultlist().get(i);
				// 链接类型
				strings.append("<option value='" + linkTypeDTO.getLinkTypeId()
						+ "'>" + linkTypeDTO.getLinkTypeName() + "</option>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Utils.printInfo(strings.toString());
	}

	public LinkDTO getLinkDTO() {
		return linkDTO;
	}

	public void setLinkDTO(LinkDTO linkDTO) {
		this.linkDTO = linkDTO;
	}

	public List<LinkDTO> getLinkDTOs() {
		return linkDTOs;
	}

	public void setLinkDTOs(List<LinkDTO> linkDTOs) {
		this.linkDTOs = linkDTOs;
	}

	public List<LinkType> getLinkTypes() {
		return linkTypes;
	}

	public void setLinkTypes(List<LinkType> linkTypes) {
		this.linkTypes = linkTypes;
	}
}
