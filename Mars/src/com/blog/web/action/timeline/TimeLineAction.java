package com.blog.web.action.timeline;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.bean.timeline.HymArticle;
import com.blog.bean.timeline.HymYear;
import com.blog.service.timeline.TimeLineService;
import com.blog.web.action.BaseAction;

/** 文章处理控制器 */
@Controller("/timeLine/timeLine")
@Scope("prototype")
public class TimeLineAction extends BaseAction{
	/** */
	private static final long serialVersionUID = -3163959029212220736L;
	/** 通过依赖注入DAO组件实例 */
	@Resource
	private TimeLineService dao;	
	/** 文章管理所有请求中常用的参数值 */
	private String actionMsg;	//Action间传递的消息参数
	private List<HymArticle> articleList;		//文章列表
	private List<HymYear> hymYearList;
	/** 搜索栏中的条件 */
	private String keyWord = "";
	private String selType;
	private String newquery; //判断是不是新的查询
	
	/**列表分页的参数*/
	private int pageCount = 0; //总页数
	private int pageNo = 1; //当前页号
	private int count = 0; //总记录数
	private int pageSize = 20; //每页记录数	
	private static SimpleDateFormat pic_df = new SimpleDateFormat("yyyy");
	public static SimpleDateFormat df_ymdhms = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
	public static SimpleDateFormat df_md = new SimpleDateFormat("MM月dd日");
		
	//采用模型驱动
	private HymArticle model=new HymArticle();//用于封装文章属性模型
	public HymArticle getModel() {
		return model;
	}

	/**
	 *@MethodName:list 
	 *@Description:list方法
	 *@author:徐半仙儿
	 *@return String
	 *@date:2015-11-25下午10:49:54
	 */
	public String list() {
		return "list";
	}
	/** 处理浏览文章请求 */
	public String browseArticle(){
		//取得分页页码		
		if (ServletActionContext.getRequest().getParameter("page")!=null){
			//接受DisplayTag组件传递的页号参数page
			newquery = "yes"; //翻页也是一次新的查询
			pageNo = Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));
		}			
		HttpSession session = ServletActionContext.getRequest().getSession();
		if(newquery!=null){//来了一个新的查询
				
			//保存查询现场
			session.setAttribute("keyWord", keyWord);
			session.setAttribute("pageNo", pageNo);
			
		}else{//恢复查询现场
			keyWord = (String)session.getAttribute("keyWord");//关键字
			pageNo = (Integer)session.getAttribute("pageNo");//分页码
			

		}
		if(actionMsg!=null&&newquery==null){//不是新的查询
			actionMsg = java.net.URLDecoder.decode(actionMsg);
			addActionMessage(actionMsg);
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("from HymArticle as a where 1=1 ");
		
		String hql = null;
		String countHql=null; //统计记录条数的hql语句
		
		
			
		countHql="select count(*) "+sb.toString();
		sb.append( "  order by a.id desc" );
		hql = sb.toString();
		sb.delete(0, sb.length());//清空内存块中记录
		
		count = dao.countQuery(countHql);//统计
		articleList = dao.query(hql,pageNo,pageSize);//调用业务逻辑组件取得新闻栏目列表
		
		//封装分页数据
//		PageList pageList = new PageList();
//		pageList.setPageNumber(pageNo);//设置当前页数
//		pageList.setFullListSize(count);//设置总记录数
//		pageList.setList(articleList);//设置当前页列表
//		pageList.setObjectsPerPage(pageSize);//设置每页记录数
		//在request对象中保存分页数据
//		ServletActionContext.getRequest().setAttribute("pageList", pageList);		
		return SUCCESS;
	}
	
	/** 处理新增系统用户请求 */
	public String addArticle(){	
		try {			
				
			//设置默认值
			model.setCdate(new Date());//创建时间
			String year_str=pic_df.format(new Date());
			HymYear tempHymYear = (HymYear) dao.loadObject("from HymYear as a where a.year='"+year_str+"'");
			if(tempHymYear==null){
				tempHymYear = new HymYear();
				tempHymYear.setYear(year_str);
				dao.saveOrUpdate(tempHymYear);
			}
			model.setYearid(tempHymYear.getId());
		    dao.saveOrUpdate(model);
			addActionMessage("恭喜您：添加文章成功！");
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("对不起：添加文章失败！"));
		}
		
		return SUCCESS;
	}
	
	/** 处理删除文章请求 */
	public String delArticle(){
		if (model.getId()!=null){
			HymArticle tempHymArticle=(HymArticle)dao.loadById(HymArticle.class, model.getId());
			if(tempHymArticle!=null){
				dao.delById(HymArticle.class, tempHymArticle.getId());
			}
			actionMsg = getText("删除成功");
		}else{
			actionMsg = getText("admin_del_fail");
		}
		actionMsg = java.net.URLEncoder.encode(actionMsg);
		return "toBrowseArticle";
	}
	
	/** 处理查看文章请求 */
	public String viewArticle(){		
		if (model.getId() != null) {
			// 调用业务逻辑组件装载指定的角色
			HymArticle tempHymArticle = (HymArticle)dao.loadById(HymArticle.class, model.getId());
			if (tempHymArticle != null) {
				try {				
					BeanUtils.copyProperties(model, tempHymArticle);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return SUCCESS;
			} else {
				actionMsg = getText("admin_view_fail");
				actionMsg = java.net.URLEncoder.encode(actionMsg);
				return "toBrowseArticle";
			}
		} else {
			actionMsg = getText("admin_view_fail");
			actionMsg = java.net.URLEncoder.encode(actionMsg);
			return "toBrowseArticle";
		}
	}
	
	/** 处理装载文章请求 */
	public String editArticle(){		
		if (model.getId() != null) {
			// 调用业务逻辑组件装载指定的数据
			HymArticle tempHymArticle = (HymArticle)dao.loadById(HymArticle.class, model.getId());
			if (tempHymArticle != null) {
				try {
					// 快速复制源对象中的所有属性到目标对象中
					BeanUtils.copyProperties(model, tempHymArticle);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return SUCCESS;
			} else {
				actionMsg = getText("admin_edit_fail");
				actionMsg = java.net.URLEncoder.encode(actionMsg);
				return "toBrowseArticle";
			}
		} else {
			actionMsg = getText("admin_edit_fail");
			actionMsg = java.net.URLEncoder.encode(actionMsg);
			return "toBrowseArticle";
		}
	}
	
	/** 处理更新文章请求 */
	public String updateArticle(){
		//调用业务逻辑组件装载指定的文章
		HymArticle tempHymArticle= (HymArticle)dao.loadById(HymArticle.class, model.getId());		
		try {
			model.setCdate(tempHymArticle.getCdate());
			model.setYearid(tempHymArticle.getYearid());
			dao.saveOrUpdate(model);					
			addActionMessage(getText("admin_edit_succ"));			 

		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("对不起：修改文章失败！"));
		}
	
		return INPUT;		
	}
	
	//前台页面显示
	public String articleList(){		
		try {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				StringBuffer sb=new StringBuffer();
				sb.append(" <h1 class=\"title\">那些年走过的日子</h1>\n");
								
				hymYearList =  dao.query();
				if(hymYearList!=null&&hymYearList.size()>0){					
					for(HymYear temHymYear:hymYearList){
						sb.append(" <div class=\"year\">\n");
						 sb.append(" <h2><a href=\"#\">"+temHymYear.getYear()+"年<i></i></a></h2>\n");
						 sb.append(" <div class=\"list\">\n");
						 sb.append(" <ul>\n");
						 articleList =dao.queryArticle("select * from hym_article a where a.yearid="+temHymYear.getId()+" order by a.id desc");
						 if(articleList!=null&&articleList.size()>0){
							 for(HymArticle temArticle:articleList){
								 if(temArticle.getColor()==0){
									 sb.append(" <li class=\"cls highlight\">\n");
								 }else{
									 sb.append(" <li class=\"cls\">\n");
								 }
								
								 sb.append(" <p class=\"date\">"+df_md.format(temArticle.getCdate())+"</p>\n");
								 sb.append(" <p class=\"intro\">"+temArticle.getTitle()+"</p>\n");
								 sb.append(" <p class=\"version\">&nbsp;</p>\n");
								 sb.append(" <div class=\"more\">\n");
								 sb.append(" <p>"+temArticle.getContent()+"</p>\n");
								 sb.append(" </div>\n");
								 sb.append(" </li>\n");
							 }
							
						 }
						 sb.append(" </ul>\n");
						 sb.append(" </div>\n");
						 sb.append(" </div>\n");
					}
								
				}else{
					 sb.append(" <div class=\"year\">\n");
					 sb.append(" <h2><a href=\"#\">2015年<i></i></a></h2>\n");
					 sb.append(" <div class=\"list\">\n");
					 sb.append(" <ul>\n");
					 
					 sb.append(" <li class=\"cls\">\n");
					 sb.append(" <p class=\"date\">1月26日</p>\n");
					 sb.append(" <p class=\"intro\">没有数据</p>\n");
					 sb.append(" <p class=\"version\">&nbsp;</p>\n");
					 sb.append(" <div class=\"more\">\n");
					 sb.append(" <p>没有数据</p>\n");
					 sb.append(" </div>\n");
					 sb.append(" </li>\n");
					 sb.append(" </ul>\n");
					 sb.append(" </div>\n");
					 sb.append(" </div>\n");
					
				}
				out.println(sb);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;	
	}
	
	public String getActionMsg() {
		return actionMsg;
	}

	public void setActionMsg(String actionMsg) {
		this.actionMsg = actionMsg;
	}
	


	public List<HymArticle> getArticleList() {
		return articleList;
	}


	public void setArticleList(List<HymArticle> articleList) {
		this.articleList = articleList;
	}


	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getSelType() {
		return selType;
	}

	public void setSelType(String selType) {
		this.selType = selType;
	}
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	


	public String getNewquery() {
		return newquery;
	}

	public void setNewquery(String newquery) {
		this.newquery = newquery;
	}
}
