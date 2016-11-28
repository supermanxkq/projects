package com.blog.web.action;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 主体框架Action类
 * @version 2013-9-1 上午11:12:59
 */
@Controller("/system/index")
@Scope("prototype")
public class IndexAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;

//	@Resource UserService userService;
//	@Resource OrgansService organsService;
//	@Resource HomePageShowService homePageShowService;
//	@Resource UndealServiceNumService undealServiceNumService;
//	private HomePageShowDTO homePageShowDTO=new HomePageShowDTO();
//
//	public HomePageShowDTO getHomePageShowDTO() {
//		return homePageShowDTO;
//	}
//
//	public void setHomePageShowDTO(HomePageShowDTO homePageShowDTO) {
//		this.homePageShowDTO = homePageShowDTO;
//	}

	private String pid;
	private String id;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}


	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	/**
	 * 进入主页面页面
	 * 
	 * @version 2011-10-10 下午09:07:50
	 * @return
	 */
	@Override
	public String execute() {
		return "main";
	}

	/**
	 * 头部页面
	 * 
	 * @version 2011-10-10 下午09:07:50
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String top() {
//		List<Module> modules = null;
//		modules = (List) this.getRequest().getSession().getAttribute(Globals.FATHER_MODULES);
//		this.getRequest().setAttribute("modules", modules);
//		UserSession us = Utils.getUserSession();
//		Organs organs = organsService.find(us.getOrganId());
//		String logoImg = "images/logo.gif";
//		this.getRequest().setAttribute("logoImg", logoImg);
//		
		return "top";
	}

	public String right() {
		return "right";
	}

	public String tool() {
		return "tool";
	}

	public String firstleft() {
		// UserSession usersession = (UserSession)
		// this.getRequest().getSession().getAttribute(Globals.USER_SESSION);
		return "firstleft";
	}

	@SuppressWarnings("unchecked")
	public String left() {
		return "left";
	}

	public String separate() {
		return "separate";
	}

	public String footer() {
		return "footer";
	}

	public String myself(){
		return "myself";
	}
	
	public String saveMyself(){
		
//		User user = userService.find(userDTO.getUserName());
//		if(user!=null){
//			if(StringUtils.isNotBlank(userDTO.getPassWord())&&StringUtils.isNotBlank(userDTO.getNewPassWord())){
//				if(!user.getPassWord().equals(MD5.MD5Encode(userDTO.getPassWord()))){
//					this.getRequest().setAttribute("result","密码错误，请重新输入！");
//					this.getRequest().setAttribute("url","system/index!myself");
//					return SUCCESS;
//				}
//				user.setPassWord(MD5.MD5Encode(userDTO.getNewPassWord()));
//			}
//			user.setRealName(userDTO.getRealName());
//			user.setTeleNo(userDTO.getTeleNo());
//			userService.update(user);
//		}
//		
//		this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
//		this.getRequest().setAttribute("url","system/index!myself");
		return SUCCESS;
	}
	
	/**
	 * 小贴士
	 * @return
	 * @throws IOException 
	 */
//	public String tips() throws IOException{
//		StringBuffer sb = new StringBuffer();
//		User user = userService.find(userDTO.getUserName());
//		if(user!=null){
//			
//		}
//		
//		return Utils.printInfo(sb.toString());
//	}
	
	public String getTipsHtml(String typetext,String num,String text,String url){
		StringBuffer sb = new StringBuffer();
		String title = typetext+num+text;
		sb.append("<li><span class=\"Fl\">");
		sb.append(typetext);
		sb.append("<a href='"+url+"' target='main' title='"+title+"'><b class=\"Color1\">");
		sb.append(num);
		sb.append("</b></a>");
		
		sb.append(text);
		
		sb.append("</span><span class=\"Fr\"><a href='"+url+"' target='main' class='color_blue2' title='"+title+"' > ");
		sb.append("[&nbsp;查看&nbsp;]");
		sb.append("</a></span></li>");
		return sb.toString();
	}
}
