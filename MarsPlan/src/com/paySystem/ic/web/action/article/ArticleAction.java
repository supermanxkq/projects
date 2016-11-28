package com.paySystem.ic.web.action.article;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.PageViewBaidu;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.article.Article;
import com.paySystem.ic.bean.article.ReadTimes;
import com.paySystem.ic.service.article.ArticleService;
import com.paySystem.ic.service.article.ArticleTypeService;
import com.paySystem.ic.service.article.ReadTimesService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ListInfoDTOF;
import com.paySystem.ic.web.dto.article.ArticleDTO;

/**
 * 登录Action类
 * 
 * @version 2013-9-1 上午11:12:59
 */

@Controller("/article/article")
@Scope("prototype")
public class ArticleAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;

	@Resource
	private ArticleService articleService;
	@Resource 
	private ReadTimesService readtimeService;
	@Resource
	private ArticleTypeService articleTypeService;
	private Article article;
	private Integer blogTypeId;
	private Integer uv;

	private ArticleDTO articleDTO = new ArticleDTO();
	/** 文章分类名称和其下数量 */
	private List<ArticleDTO> records = new ArrayList<ArticleDTO>();
	private List<ArticleDTO> result;

	public ArticleDTO getArticleDTO() {
		return articleDTO;
	}

	public void setArticleDTO(ArticleDTO articleDTO) {
		this.articleDTO = articleDTO;
	}

	/**
	 * @MethodName:listB
	 * @Description:跳转到后台查询所有的博客信息
	 * @author:徐凯强
	 * @return String
	 * @date:Mar 10, 20165:16:41 PM
	 */
	public String listB() {
		return "listB";
	}

	/**
	 * @Title:list
	 * @Description:list方法
	 * @Return:String
	 * @author:徐凯强
	 * @Date:2014-7-22下午03:02:16
	 */
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
		QueryResult<ArticleDTO> queryResult = articleService.queryAll((articleDTO.getPage() - 1) * pageNum, pageNum,
				articleDTO, orderby);
		List<ArticleDTO> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			ArticleDTO articleDTO = list.get(i);
			List<String> strings = new ArrayList<String>();
			if (articleDTO.getStatus() != 9) {
				strings.add(articleDTO.getId().toString());
				/** 标题 */
				strings.add(articleDTO.getTitle());
				strings.add(articleDTO.getAuthor());
				strings.add(articleDTO.getTypeId().toString());
				strings.add(DateTimeTool.dateFormat("", articleDTO.getContentDate()));
				String operation = "";
				if (Utils.checkPermission("sy-3101-04")) {
					operation += "<a href='base/goods!checkUI?goodsDTO.goodsId=" + articleDTO.getId() + "' title='查看'>"
							+ Globals.IMG_VIEW + "</a>&nbsp;";
				}
				if (Utils.checkPermission("sy-3101-03")) {
					operation += "<a href='article/article!editUI?articleDTO.id=" + articleDTO.getId() + "' title='修改'>"
							+ Globals.IMG_EDIT + "</a>&nbsp;";
				}
				if (Utils.checkPermission("sy-3101-02")) {
					operation += "<a href='code/code!checkUI?goodsDTO.goodsId=" + articleDTO.getId() + "' title='删除'>"
							+ Globals.IMG_DELETE + "</a>&nbsp;";
				}
				strings.add(operation);
				lists.add(strings);
			}
		}
		PageView pageView = new PageView(articleDTO.getPage(), queryResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * @MethodName:findArticleById
	 * @Description:根据编号查询文章
	 * @return
	 * @author:徐凯强
	 * @return String
	 * @date:Mar 11, 20163:30:01 PM
	 */
	public String findArticleById() {
		//获取文章
		article = articleService.find(articleDTO.getId());
		//更新阅读次数
		ReadTimes readTimes=new ReadTimes(article.getId());
		readtimeService.save(readTimes);
		BigInteger count=readtimeService.queryCountByArticleId(article.getId());
		article.setReadTimes(count);
		articleTypeService.update(article);
		return "find";
	}

	/**
	 * @MethodName:jsonPageListF
	 * @Description：前台获取所有的文章列表
	 * @return
	 * @throws Exception
	 * @author:徐凯强
	 * @return String
	 * @date:Mar 11, 20162:21:18 AM
	 */
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
		QueryResult<ArticleDTO> result = articleService.queryAll((articleDTO.getPage() - 1) * pageNum, pageNum,
				articleDTO, orderBy);
		/** 获取queryResult中的集合 */
		List<ArticleDTO> articles = result.getResultlist();
		StringBuffer articleList = new StringBuffer();
		for (int i = 0; i < articles.size(); i++) {
			ArticleDTO articleDTO = articles.get(i);
			articleList.append("<div class=\"media\">\n");
			articleList.append("<div class=\"media-body\">");
			articleList.append("<h4 class=\"media-heading\"><a href=\"article/article!findArticleById?articleDTO.id="
					+ articleDTO.getId() + "\">" + articleDTO.getTitle() + "</a></h4>\n");
			articleList.append(articleDTO.getSummary());
			articleList.append("</div>\n");
			articleList.append("<p class=\"text-right text-muted\"><ul class=\"text-right list-inline list-unstyled\"><li><small>"
					+ DateTimeTool.dateFormat("", articleDTO.getContentDate()) + "</small></li><li><i class=\"glyphicon glyphicon-search\"></i><a href=\"article/article!findArticleById?articleDTO.id="
					+ articleDTO.getId() + "\"><font color=\"red\">阅读</font></a>("+articleDTO.getReadTimes()+")</li></uL></p></div><hr/>");
		}
		// /** 实例化PageView对象,获取分页的参数、总页数、总记录数 */
		//PageView pageView = new PageView(articleDTO.getPage(), result.getTotalrecord());
		PageViewBaidu pageView=new PageViewBaidu(articleDTO.getPage(), (int)result.getTotalrecord(), articleList.toString());
		/** 实例化ListInfoDTO */
//		ListInfoDTOF listInfoDTOF = new ListInfoDTOF(articleList.toString(), getPageHTMLF(pageView));
		return Utils.printInfo(pageView);
	}

	/**
	 * @Title:addSave
	 * @Description:保存服务清单实体类
	 * @Return:String
	 * @author:徐凯强
	 * @Date:2014-7-23上午09:14:24
	 */
	public String addSave() {
		try {
			articleDTO.setAuthor(Utils.getUserSession().getUserName());
			articleDTO.setContentDate(new Date());
			articleDTO.setStatus(1);
			articleDTO.setReadTimes(BigInteger.ONE);
			articleService.addSaveDTO(articleDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listB";
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
			articleService.updateData(articleDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listB";
	}

	/**
	 * @Title:editUi
	 * @Description:跳转到编辑界面
	 * @Return:String
	 * @author:徐凯强
	 * @Date:2014-7-24上午09:10:37
	 */
	public String editUI() {
		try {
			this.setMethod("updateData");
			this.getRequest().setAttribute("articleTypes", articleTypeService.getAllTypes());
			/** 传递服务器名称参数 */
			Article article = articleService.find(articleDTO.getId());
			articleDTO = (ArticleDTO) EntityDtoConverter.bean2Dto(article, articleDTO);
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
		this.getRequest().setAttribute("articleTypes", articleTypeService.getAllTypes());
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
			Article article = articleService.find(Integer.valueOf(this.getId()));
			article.setStatus(9);
			articleService.update(article);
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
		/** 获取文章分类及分类下面文章数量列表 */
		records = articleService.queryArticleTypesAndCounts();
		this.getRequest().setAttribute("status", OptionsValue.Announ_STATUS);
		/** 传递服务器名称参数 */
		Article article = articleService.find(articleDTO.getId());
		try {
			articleDTO = (ArticleDTO) EntityDtoConverter.bean2Dto(article, articleDTO);
			/** 添加访问次数 */
			articleService.addReadTimes(articleDTO);
			this.getRequest().setAttribute("url",
					"article/article!checkUI?columnCode=1&&articleDTO.articleId=" + articleDTO.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "checkUI";
	}
	
	
//	public String queryByLucene(){
//		List<ArticleDTO> articles=articleService.queryByLucene(articleDTO);
//		StringBuffer articleList = new StringBuffer();
//		for (int i = 0; i < articles.size(); i++) {
//			ArticleDTO articleDTO2 = articles.get(i);
//		articleList.append("<div class=\"media well\">\n");
//		articleList.append("<a href=\"article/article!findArticleById?articleDTO.id=" + articleDTO2.getId()
//				+ "\" class=\"pull-left\"><img alt=\"Bootstrap Media Preview\" src=\""
//				+ articleDTO2.getThumbnailPath()
//				+ "\" width=\"64px\" height=\"64px\" class=\"media-object\" /></a>\n");
//		articleList.append("<div class=\"media-body\">");
//		articleList.append("<h4 class=\"media-heading\"><a href=\"article/article!findArticleById?articleDTO.id="
//				+ articleDTO2.getId() + "\">" + articleDTO2.getTitle() + "</a></h4>\n");
//		articleList.append(articleDTO2.getContent());
//		articleList.append("</div>\n");
//		articleList.append("<p class=\"text-right text-danger\">"
//				+ articleDTO2.getContentDateString()+ " </p></div>");
//		}
//		return Utils.printInfo(articleList.toString());
//	}

	public List<ArticleDTO> getRecords() {
		return records;
	}

	public Integer getBlogTypeId() {
		return blogTypeId;
	}

	public void setBlogTypeId(Integer blogTypeId) {
		this.blogTypeId = blogTypeId;
	}

	public void setRecords(List<ArticleDTO> records) {
		this.records = records;
	}

	public List<ArticleDTO> getResult() {
		return result;
	}

	public void setResult(List<ArticleDTO> result) {
		this.result = result;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}
