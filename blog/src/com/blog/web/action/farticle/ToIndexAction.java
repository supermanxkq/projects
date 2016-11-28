package com.blog.web.action.farticle;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.dto.article.ArticleDTO;
import com.blog.service.article.ArticleService;
import com.blog.web.action.BaseAction;

/**
 * 登录Action类
 * 
 * @version 2013-9-1 上午11:12:59
 */

@Controller("/toIndex/toIndex")
@Scope("prototype")
public class ToIndexAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;

	@Resource
	private ArticleService articleService;

	/** 服务清单DTO */
	private ArticleDTO articleDTO=new ArticleDTO();

	public ArticleDTO getArticleDTO() {
		return articleDTO;
	}

	public void setArticleDTO(ArticleDTO articleDTO) {
		this.articleDTO = articleDTO;
	}

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
	 *@Title:jsonPageList
	 *@Description:异步加载数据
	 *@throws Exception
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-23下午05:08:26
	 */
//	@SuppressWarnings("unchecked")
//	public String jsonPageList() throws Exception {
//		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
//
//		if (StringUtils.isNotBlank(this.getOrderProperty())
//				&& StringUtils.isNotBlank(this.getOrderDirection())) {
//			orderby.put(this.getOrderProperty(), this.getOrderDirection());
//		} else {
//			orderby.put("contentDate", "desc");
//		}
//		/** 返回结果 */
//		QueryResult<ArticleDTO> queryResult = articleService.queryAll(
//				(articleDTO.getPage() - 1) * pageNum, pageNum, articleDTO,
//				orderby);
//		List<ArticleDTO> list = queryResult.getResultlist();
//		List<List<String>> lists = new ArrayList<List<String>>();
//		for (int i = 0; i < list.size(); i++) {
//			ArticleDTO articleDTO = list.get(i);
//			List<String> strings = new ArrayList<String>();
//			if (articleDTO.getStatus() != 9) {
//				strings.add(String.valueOf(i + 1));
//				/** 标题 */
//				strings.add(articleDTO.getTitle());
//				strings.add(articleDTO.getGuest());
//				strings.add(articleDTO.getSomePeople());
//				strings.add(articleDTO.getSomeMedia());
//				strings.add(articleDTO.getAddress());
//				strings.add(Utils.getOptionsIntegerName(
//						OptionsValue.Announ_STATUS, articleDTO.getStatus()));
//				strings.add(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm",
//						articleDTO.getActionDate()));
//				strings.add(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm",
//						articleDTO.getContentDate()));
//				// strings.add(articleDTO.getUser().getUserName());
//				strings.add("administrator");
//
//				String operation = "";
//				operation += "<a href=article/article!checkUI?serviceListDTO.servId="
//						+ articleDTO.getArticleId()
//						+ " title='查看'>"
//						+ Globals.IMG_VIEW + "</a>&nbsp;";
//				operation += "<a href=javascript:deleteData('article/article!delete','"
//						+ articleDTO.getArticleId().toString()
//						+ "') title='删除'>" + Globals.IMG_DELETE + "</a>&nbsp;";
//				operation += "<a href=article/article!editUI?articleDTO.articleId="
//						+ articleDTO.getArticleId()
//						+ " title='修改'>"
//						+ Globals.IMG_EDIT + "</a>&nbsp;";
//
//				strings.add(operation);
//				lists.add(strings);
//			}
//		}
//		PageView pageView = new PageView(articleDTO.getPage(), queryResult
//				.getTotalrecord());
//		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
//		return Utils.printInfo(listInfoDTO);
//	}

	/**
	 *@Title:addSave
	 *@Description:保存服务清单实体类
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-23上午09:14:24
	 */
//	public String addSave() {
//		articleService.addSaveDTO(articleDTO);
//		this.getRequest().setAttribute("url", "article/article!list");
//		this.getRequest().setAttribute("result", "添加成功！");
//		return "success";
//	}

	/**
	 *@Title:updateData
	 *@Description:更新服务清单
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-23上午09:17:43
	 */
//	public String updateData() {
//		try {
//			Article article = new Article();
//			article = (Article) EntityDtoConverter
//					.dto2Bean(articleDTO, article);
//			// 发表时间
//			article.setActionDate(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm",
//					articleDTO.getActionDateString()));
//			article.setContentDate(new Date());
//			articleService.update(article);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		this.getRequest().setAttribute("result", "修改成功!");
//		this.getRequest().setAttribute("url", "article/article!list");
//		return "success";
//	}

	/**
	 *@Title:editUi
	 *@Description:跳转到编辑界面
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-24上午09:10:37
	 */
//	public String editUI() {
//		this.setMethod("updateData");
//		this.getRequest().setAttribute("status", OptionsValue.Announ_STATUS);
//		/** 传递服务器名称参数 */
//		Article article = articleService.find(articleDTO.getArticleId());
//		try {
//			articleDTO = (ArticleDTO) EntityDtoConverter.bean2Dto(article,
//					articleDTO);
//			articleDTO.setActionDateString(DateTimeTool.dateFormat("yyyy-MM-dd HH:MM", articleDTO.getActionDate()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "input";
//	}

	/**
	 *@Title:addUI
	 *@Description:跳转到添加界面
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-24上午09:10:37
	 */
//	public String addUI() {
//		articleDTO.setActionDateString(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm",
//				new Date()));
//		this.getRequest().setAttribute("status", OptionsValue.Announ_STATUS);
//		this.setMethod("addSave");
//		return "input";
//	}

	/**
	 *@Title:delete
	 *@Description:删除
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-31上午09:57:07
	 */
//	public String delete() {
//		try {
//			Article article = articleService
//					.find(Integer.valueOf(this.getId()));
//			article.setStatus(9);
//			articleService.update(article);
//			this.ajaxResult = "ajaxsuccess";
//		} catch (Exception e) {
//			e.printStackTrace();
//			this.ajaxResult = "ajaxfailure";
//			this.msgResult = e.getMessage();
//		}
//		return this.ajaxResult;
//	}

	/**
	 *@Title:checkUI
	 *@Description:查看删除记录
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-31上午09:54:39
	 */
//	public String checkUI() {
//		this.setMethod("checkDetail");
//		Article article = articleService.find(articleDTO.getArticleId()
//				.toString());
//		try {
//			articleDTO = (ArticleDTO) EntityDtoConverter.bean2Dto(article,
//					articleDTO);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ERROR;
//	}
}
