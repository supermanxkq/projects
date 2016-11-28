package com.java1234.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java1234.model.User;

@Controller
@RequestMapping("/user")
public class UserController {

	/**
	 * @param request
	 * @param response
	 * @return
	 * 登录
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response){
		System.out.println("----登录验证---");
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		Cookie cookie=new Cookie("user",userName+"-"+password);
		//设置cookie的有效时间，以秒为单位，这里为一周
		cookie.setMaxAge(1*60*60*24*7);
		User currentUser=new User(userName,password);
		response.addCookie(cookie);
		HttpSession session=request.getSession();
		session.setAttribute("currentUser", currentUser);
		return "redirect:/main.jsp";
	}
	
	/**
	 * @param request
	 * @return
	 * 只是用到了request
	 */
	@RequestMapping("/login2")
	public String login2(HttpServletRequest request){
		return "redirect:/main.jsp";
	}
	
	/**
	 * @param session
	 * @return
	 * 只是用到了session
	 */
	@RequestMapping("/login3")
	public String login3(HttpSession session){
		return "redirect:/main.jsp";
	}
	
	/**
	 * @return
	 * Ajax的支持
	 */
	@RequestMapping("/ajax")//@ResponseBody将user自动转换为json对象
	public @ResponseBody User ajax(){
		User user=new User("zhangsan","123");
		return user;
	}
}
