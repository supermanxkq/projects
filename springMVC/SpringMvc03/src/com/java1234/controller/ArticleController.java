package com.java1234.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java1234.model.Article;

@Controller
@RequestMapping("/article")
public class ArticleController {

	/**
	 * @param model
	 * @return
	 * 跳转到文章列表
	 */
	@RequestMapping("/list")
	public String list(Model model){
		return "article/list";
	}
	
	/**
	 * @param id
	 * @return
	 * 查看文章详情
	 */
	@RequestMapping("/details/{id}")//@PathVariable路径变量
	public ModelAndView details(@PathVariable("id") int id){
		ModelAndView mav=new ModelAndView();
		if(id==1){
			mav.addObject("article", new Article("文章一","文章一的内容"));
		}else if(id==2){
			mav.addObject("article", new Article("文章二","文章二的内容"));
		}
		mav.setViewName("article/details");
		return mav;
	}
}
