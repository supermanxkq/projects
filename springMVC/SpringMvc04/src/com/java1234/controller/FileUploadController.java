package com.java1234.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	/**
	 * @param file1
	 * @param request
	 * @return文件上传单个文件
	 * @throws Exception
	 */
	@RequestMapping("/upload")
	public String uploadFile(@RequestParam("file1") MultipartFile file1,HttpServletRequest request)throws Exception{
		//获取项目路径
		String filePath=request.getServletContext().getRealPath("/");
		System.out.println(filePath);
		//file1.getOriginalFilename()获取文件名，把文件输出到指定目录
		file1.transferTo(new File(filePath+"upload/"+file1.getOriginalFilename()));
		return "redirect:success.html";
	}
	
	@RequestMapping("/upload2")
	public String uploadFiles(@RequestParam("file") MultipartFile[] files,HttpServletRequest request)throws Exception{
		String filePath=request.getServletContext().getRealPath("/");
		System.out.println(filePath);
		for(MultipartFile file:files){
			file.transferTo(new File(filePath+"upload/"+file.getOriginalFilename()));			
		}
		return "redirect:success.html";
	}
}
