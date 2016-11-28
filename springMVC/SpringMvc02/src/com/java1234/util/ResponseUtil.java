package com.java1234.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator json数据打印
 */
public class ResponseUtil {

	public static void write(HttpServletResponse response, Object o)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(o.toString());
		out.flush();
		out.close();
	}
}
