package com.paySystem.ic.web.action.article;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.article.ArticleType;
import com.paySystem.ic.bean.code.CodeStyle;
import com.paySystem.ic.service.article.ArticleTypeService;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.article.ArticleTypeDTO;

/**
 * @ProjectName:MarsPlan
 * @ClassName:ArticleTypeAction
 * @Description:博客类型控制类
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:Mar 10, 20167:36:43 PM
 */
@Controller("/articletype/articletype")
@Scope("prototype")
public class ArticleTypeAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;

	@Resource
	private ArticleTypeService articleTypeService;

	private ArticleTypeDTO articleTypeDTO = new ArticleTypeDTO();

	public String list() {
		return "list";
	}

	/**
	 * @Title:jsonPageList
	 * @Description:异步加载数据
	 * @throws Exception
	 * @Return:String
	 * @author:徐凯强
	 * @Date:2014-7-23下午05:08:26
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();

		if (StringUtils.isNotBlank(this.getOrderProperty()) && StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderby.put("id", "desc");
		}
		/** 返回结果 */
		QueryResult<ArticleTypeDTO> queryResult = articleTypeService.queryAll((articleTypeDTO.getPage() - 1) * pageNum,
				pageNum, articleTypeDTO, orderby);
		List<ArticleTypeDTO> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			ArticleTypeDTO articleTypeDTO = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(articleTypeDTO.getId().toString());
			strings.add(articleTypeDTO.getName());
			String operation = "";
			// 如果是管理员的权限
			operation += "<a href=articletype/articletype!editUI?articleTypeDTO.id=" + articleTypeDTO.getId()
					+ " title='修改'>" + Globals.IMG_EDIT + "</a>&nbsp;";
			operation += "<a href=javascript:deleteData('articletype/articletype!delete','"
					+ articleTypeDTO.getId().toString() + "') title='删除'>" + Globals.IMG_DELETE + "</a>&nbsp;";
			strings.add(operation);
			lists.add(strings);
		}
		PageView pageView = new PageView(articleTypeDTO.getPage(), queryResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * @Title:addSave
	 * @Description:保存服务清单实体类
	 * @Return:String
	 * @author:徐凯强
	 * @Date:2014-7-23上午09:14:24
	 */
	public String addSave() {
		articleTypeService.addSaveDTO(articleTypeDTO);
		return "list";
	}

	/**
	 * @Title:updateData
	 * @Description:更新服务清单
	 * @Return:String
	 * @author:徐凯强
	 * @Date:2014-7-23上午09:17:43
	 */
	public String updateData() {
		try {
			ArticleType articleType = new ArticleType();
			articleType = (ArticleType) EntityDtoConverter.dto2Bean(articleTypeDTO, articleType);
			articleTypeService.update(articleType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	public String jsonPageListF() throws Exception {
		/**
		 * 查询结果排序参数设定
		 */
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		/** 判断排序参数是否有值 */
		if (StringUtils.isNotBlank(this.getOrderProperty()) && StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderBy.put("id", "desc");
		}
		/** 获取queryResult中的值 */
		QueryResult<ArticleType> result = articleTypeService.queryAll();
		/** 获取queryResult中的集合 */
		List<ArticleType> articleTypes = result.getResultlist();
		StringBuffer articleTypeList = new StringBuffer();
		articleTypeList.append("<h4>文章分类</h4>");
		for (int i = 0; i < articleTypes.size(); i++) {
			ArticleType articleType = articleTypes.get(i);
			articleTypeList.append("<button type=\"button\" class=\"btn btn-link\" onclick=\"queryByTypeId(1,"
					+ articleType.getId() + ")\">" + articleType.getName() + "</button>");
		}
		return Utils.printInfo(articleTypeList.toString());
	}
	
	/**
	 * @Title:editUi
	 * @Description:跳转到编辑界面
	 * @Return:String
	 * @author:徐凯强
	 * @Date:2014-7-24上午09:10:37
	 */
	public String editUI() {
		this.setMethod("updateData");
		this.getRequest().setAttribute("status", OptionsValue.Announ_STATUS);
		/** 传递服务器名称参数 */
		ArticleType articleType = articleTypeService.find(articleTypeDTO.getId());
		try {
			articleTypeDTO = (ArticleTypeDTO) EntityDtoConverter.bean2Dto(articleType, articleTypeDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	/**
	 * @Title:addUI
	 * @Description:跳转到添加界面
	 * @Return:String
	 * @author:徐凯强
	 * @Date:2014-7-24上午09:10:37
	 */
	public String addUI() {
		this.setMethod("addSave");
		return "input";
	}

	/**
	 * @Title:delete
	 * @Description:删除
	 * @Return:String
	 * @author:徐凯强
	 * @Date:2014-7-31上午09:57:07
	 */
	public String delete() {
		try {
			articleTypeService.delete(Integer.parseInt(this.getId()));
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

	/**
	 * @Title:checkUI
	 * @Description:查看删除记录
	 * @Return:String
	 * @author:徐凯强
	 * @Date:2014-7-31上午09:54:39
	 */
	public String checkUI() {
		this.getRequest().setAttribute("status", OptionsValue.Announ_STATUS);
		/** 传递服务器名称参数 */
		ArticleType articleType = articleTypeService.find(articleTypeDTO.getId());
		try {
			articleTypeDTO = (ArticleTypeDTO) EntityDtoConverter.bean2Dto(articleType, articleTypeDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "checkUI";
	}

	public ArticleTypeDTO getArticleTypeDTO() {
		return articleTypeDTO;
	}

	public void setArticleTypeDTO(ArticleTypeDTO articleTypeDTO) {
		this.articleTypeDTO = articleTypeDTO;
	}

}
