package com.blog.web.action.article;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.bean.PageView;
import com.blog.bean.QueryResult;
import com.blog.bean.article.Article;
import com.blog.dto.ListInfoDTO;
import com.blog.dto.article.ArticleDTO;
import com.blog.dto.system.UserSession;
import com.blog.service.article.ArticleService;
import com.blog.service.blogtype.BlogTypeService;
import com.blog.service.log.LogService;
import com.blog.util.DateTimeTool;
import com.blog.util.EntityDtoConverter;
import com.blog.util.HtmlUtil;
import com.blog.util.OptionsValue;
import com.blog.util.Utils;
import com.blog.web.action.BaseAction;

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
	private BlogTypeService blogTypeService;
	@Autowired
	private LogService logService;
	private Integer blogTypeId;
	private Integer uv;

	/** 服务清单DTO */
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
	 *@Title:list
	 *@Description:list方法
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-22下午03:02:16
	 */
	public String list() {
		try {
			// 统计网站的PV（页面浏览量），UV（独立访客数）
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String format = df.format(new Date());
			Date parse = null;
			try {
				parse = df.parse(format);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}

			Timestamp startTime = new Timestamp(parse.getTime());
			Timestamp endTime = new Timestamp(
					parse.getTime() + 24 * 3600 * 1000);
			uv = logService.getUV(startTime, endTime);
			this.getRequest().getSession().setAttribute("uv", uv);
			/** 获取文章分类及分类下面文章数量列表 */
			records = articleService.queryArticleTypesAndCounts();
			/**获取文章归档*/
			result = articleService.queryArticleDateAndCounts();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			orderby.put("contentDate", "desc");
		}
		
		
		/** 返回结果 */
		QueryResult<ArticleDTO> queryResult = articleService.queryAll(
				(articleDTO.getPage() - 1) * pageNum, pageNum, articleDTO,
				orderby);
		List<ArticleDTO> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			ArticleDTO articleDTO = list.get(i);
			List<String> strings = new ArrayList<String>();
			if (articleDTO.getStatus() != 9) {
				strings.add(articleDTO.getArticleId().toString());
				/** 标题 */
				strings.add(articleDTO.getTitle());
				strings.add(Utils.getOptionsIntegerName(
						OptionsValue.Announ_STATUS, articleDTO.getStatus()));
				strings.add(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm",
						articleDTO.getContentDate()));
				strings.add(articleDTO.getUser().getUserName());
				strings.add(articleDTO.getBlogType().getName());
				strings.add(HtmlUtil.getTextFromHtml(articleDTO.getContent()));
				/** 阅读次数 */
				strings.add(articleDTO.getReadTimes() + "");
				/** 评论次数 */
				strings.add(articleDTO.getCommonts().size() + "");
				UserSession userSession = Utils.getUserSession();
				// 如果文章属于当前用户
				if (userSession != null) {
					// 如果是当前用户的资源
					if (articleDTO.getUser().getUserId().equals(
							userSession.getUserId())) {
						strings.add("1");
					}
				}
				// 如果是超级管理员
				if (userSession != null) {
					// 如果是当前用户的资源
					if (userSession.getUserLeavel().equals(1)) {
						strings.add("2");
					}
				}

				lists.add(strings);
			}
		}
		PageView pageView = new PageView(articleDTO.getPage(), queryResult
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
		try {
			articleService.addSaveDTO(articleDTO);
			this.getRequest().setAttribute("url", "article/article!list");
			this.getRequest().setAttribute("result", "添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			Article article = new Article();
			article = (Article) EntityDtoConverter
					.dto2Bean(articleDTO, article);
			// 发表时间
			// article.setActionDate(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm",
			// articleDTO.getActionDateString()));
			article.setContentDate(new Date());
			articleService.update(article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getRequest().setAttribute("result", "修改成功!");
		this.getRequest().setAttribute("url", "article/article!list");
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
		this.getRequest().setAttribute("blogTypes",
				blogTypeService.getAllTypes());
		this.getRequest().setAttribute("status", OptionsValue.Announ_STATUS);
		/** 传递服务器名称参数 */
		Article article = articleService.find(articleDTO.getArticleId());
		try {
			articleDTO = (ArticleDTO) EntityDtoConverter.bean2Dto(article,
					articleDTO);
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
		this.getRequest().setAttribute("blogTypes",
				blogTypeService.getAllTypes());
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
			Article article = articleService
					.find(Integer.valueOf(this.getId()));
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
	 *@Title:checkUI
	 *@Description:查看删除记录
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-7-31上午09:54:39
	 */
	public String checkUI() {
		/** 获取文章分类及分类下面文章数量列表 */
		records = articleService.queryArticleTypesAndCounts();
		this.getRequest().setAttribute("status", OptionsValue.Announ_STATUS);
		/** 传递服务器名称参数 */
		Article article = articleService.find(articleDTO.getArticleId());
		try {
			articleDTO = (ArticleDTO) EntityDtoConverter.bean2Dto(article,
					articleDTO);
			/** 添加访问次数 */
			articleService.addReadTimes(articleDTO);
			this.getRequest().setAttribute(
					"url",
					"article/article!checkUI?columnCode=1&&articleDTO.articleId="
							+ articleDTO.getArticleId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "checkUI";
	}

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
	
}
