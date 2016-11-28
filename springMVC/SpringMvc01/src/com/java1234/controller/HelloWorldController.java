package com.java1234.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
	// 请求地址
	@RequestMapping("/helloWorld")
	public String helloWorld(Model model) {
		//封装传递的参数
		model.addAttribute("message", "StringMvc他大爷");
		//返回到以helloWorld开头的suffix（后缀）为.jsp页面
		return "helloWorld";
	}
}
