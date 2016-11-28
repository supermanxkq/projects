package com.blog.web.action.blogtype;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.bean.PageView;
import com.blog.bean.QueryResult;
import com.blog.bean.blogtype.BlogType;
import com.blog.dto.ListInfoDTO;
import com.blog.dto.blogtype.BlogTypeDTO;
import com.blog.service.blogtype.BlogTypeService;
import com.blog.util.EntityDtoConverter;
import com.blog.util.OptionsValue;
import com.blog.util.Utils;
import com.blog.web.action.BaseAction;

/**
 * 登录Action类
 * 
 * @version 2013-9-1 上午11:12:59
 */

@Controller("/blogtype/blogtype")
@Scope("prototype")
public class BlogTypeAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;

	@Resource
	private BlogTypeService blogTypeService;

	private BlogTypeDTO blogTypeDTO = new BlogTypeDTO();

	public BlogTypeDTO getBlogTypeDTO() {
		return blogTypeDTO;
	}

	public void setBlogTypeDTO(BlogTypeDTO blogTypeDTO) {
		this.blogTypeDTO = blogTypeDTO;
	}

	public String list() {
		return "list";
	}

	/**
	 *@Title:jsonPageList
	 *@Description:异步加载数据
	 *@throws Exception
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-23下午05:08:26
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();

		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderby.put("id", "desc");
		}
		/** 返回结果 */
		QueryResult<BlogTypeDTO> queryResult = blogTypeService.queryAll(
				(blogTypeDTO.getPage() - 1) * pageNum, pageNum, blogTypeDTO,
				orderby);
		List<BlogTypeDTO> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			BlogTypeDTO blogTypeDTO = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(blogTypeDTO.getBlogTypeId().toString());
			strings.add(blogTypeDTO.getName());
			String operation = "";
			//如果是管理员的权限
			if(Utils.getUserSession().getUserLeavel().equals(1)){
			operation += "<a href=javascript:deleteData('blogtype/blogtype!delete','"
					+ blogTypeDTO.getBlogTypeId().toString()
					+ "') title='删除'>删除"
					+ "</a>&nbsp;";
			operation += "<a href=blogtype/blogtype!editUI?blogTypeDTO.id="
					+ blogTypeDTO.getBlogTypeId() + " title='修改'>修改" + "</a>&nbsp;";
			}
			strings.add(operation);
			lists.add(strings);
		}
		PageView pageView = new PageView(blogTypeDTO.getPage(), queryResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 *@Title:addSave
	 *@Description:保存服务清单实体类
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-23上午09:14:24
	 */
	public String addSave() {
		blogTypeService.addSaveDTO(blogTypeDTO);
		this.getRequest().setAttribute("url", "blogtype/blogtype!list");
		this.getRequest().setAttribute("result", "添加成功！");
		return "success";
	}

	/**
	 *@Title:updateData
	 *@Description:更新服务清单
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-23上午09:17:43
	 */
	public String updateData() {
		try {
			BlogType blogType = new BlogType();
			blogType = (BlogType) EntityDtoConverter.dto2Bean(blogTypeDTO,
					blogType);
			blogTypeService.update(blogType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getRequest().setAttribute("result", "修改成功!");
		this.getRequest().setAttribute("url", "blogtype/blogtype!list");
		return "success";
	}

	/**
	 *@Title:editUi
	 *@Description:跳转到编辑界面
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-24上午09:10:37
	 */
	public String editUI() {
		this.setMethod("updateData");
		this.getRequest().setAttribute("status", OptionsValue.Announ_STATUS);
		/** 传递服务器名称参数 */
		BlogType blogType = blogTypeService.find(blogTypeDTO.getBlogTypeId());
		try {
			blogTypeDTO = (BlogTypeDTO) EntityDtoConverter.bean2Dto(blogType,
					blogTypeDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	/**
	 *@Title:addUI
	 *@Description:跳转到添加界面
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-24上午09:10:37
	 */
	public String addUI() {
		this.getRequest().setAttribute("status", OptionsValue.Announ_STATUS);
		this.setMethod("addSave");
		return "input";
	}

	/**
	 *@Title:delete
	 *@Description:删除
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-31上午09:57:07
	 */
	public String delete() {
		try {
			blogTypeService.delete(Integer.parseInt(this.getId()));
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

	/**
	 *@Title:checkUI
	 *@Description:查看删除记录
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-31上午09:54:39
	 */
	public String checkUI() {
		this.getRequest().setAttribute("status", OptionsValue.Announ_STATUS);
		/** 传递服务器名称参数 */
		BlogType article = blogTypeService.find(blogTypeDTO.getBlogTypeId());
		try {
			blogTypeDTO = (BlogTypeDTO) EntityDtoConverter.bean2Dto(article,
					blogTypeDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "checkUI";
	}
}
