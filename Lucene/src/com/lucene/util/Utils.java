package com.lucene.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;

public class Utils {

	private static final Log log = LogFactory.getLog(Utils.class);

	/**
	 * 使用I/O流输出 json格式的数据
	 * 
	 * @param response
	 * @param object
	 */
	public static String printInfo(Object object) {
		HttpServletResponse response = ServletActionContext.getResponse();
		Gson gson = new Gson();
		String result = gson.toJson(object);
		response.setContentType("text/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache"); // 取消浏览器缓存
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			log.info("使用I/0输入出错" + e);
		}
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
}