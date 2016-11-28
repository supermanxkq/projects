package com.ssi.stu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DownFile {
	protected HttpServletRequest m_request;
	protected HttpServletResponse m_response;
	protected ServletContext m_application;
	private String m_contentDisposition;

	public void setContentDisposition(String s) {
		m_contentDisposition = s;
	}

	public final void initialize(ServletContext servletContext,
			HttpSession httpSession, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ServletException {
		m_application = servletContext;
		m_request = httpServletRequest;
		m_response = httpServletResponse;
	}

	public void downloadFile(String sourceFile, String contentType,
			String toFile, int i) throws ServletException, IOException {
		if (sourceFile == null)
			throw new IllegalArgumentException((new StringBuilder("File '"))
					.append(sourceFile).append("' not found.").toString());
		if (sourceFile.equals(""))
			throw new IllegalArgumentException((new StringBuilder("File '"))
					.append(sourceFile).append("' not found.").toString());
		if (!isVirtual(sourceFile)) {
			System.out.println("文件路径有误。。");
			throw new SecurityException("Physical path is denied(文件路径有误)。");
		}
		File file = new File(sourceFile);
		FileInputStream fileInputStream = new FileInputStream(file);
		try {
			long fileLen = file.length();
			int k = 0;
			byte byteLen[] = new byte[i];
			if (contentType == null) {
				m_response.setContentType("application/x-msdownload");
			} else if (contentType.length() == 0)
				m_response.setContentType("application/x-msdownload");
			else
				m_response.setContentType(contentType);
			// 设置文件长度
			m_response.setContentLength((int) fileLen);
			m_contentDisposition = m_contentDisposition == null ? "attachment"
					: m_contentDisposition;
			if (toFile == null)
				m_response.setHeader("Content-Disposition", (new StringBuilder(
						String.valueOf(m_contentDisposition))).append(
						" filename=").append(
						toUtf8String(getFileName(sourceFile))).toString());
			else if (toFile.length() == 0)
				m_response.setHeader("Content-Disposition", (new StringBuilder(
						String.valueOf(m_contentDisposition))).append(
						" filename=").append(toUtf8String(getFileName(toFile)))
						.toString());
			while ((long) k < fileLen) {
				int j = fileInputStream.read(byteLen, 0, i);
				k += j;
				m_response.getOutputStream().write(byteLen, 0, j);
			}
		} catch (Exception e) {
			fileInputStream.close();
		}
		fileInputStream.close();
	}

	/**
	 *@MethodName:isVirtual
	 *@Description:判断文件是否存在
	 *@param filePath
	 *@author:半仙儿
	 *@return boolean
	 *@date:2015-10-30上午09:46:41
	 */
	private boolean isVirtual(String filePath) {
		if (filePath != null) {
			File file = new File(filePath);
			System.out.println(filePath + "存在.");
			return file.exists();
		} else {
			System.out.println(filePath + " 不存在。");
			return false;
		}
	}

	/**
	 *@MethodName:toUtf8String
	 *@Description:字符转换
	 *@param s
	 *@author:半仙儿
	 *@return String
	 *@date:2015-10-30上午09:51:03
	 */
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= '\377') {
				sb.append(c);
			} else {
				byte b[];
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception e) {
					System.out.println(e);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append((new StringBuilder("%")).append(
							Integer.toHexString(k).toUpperCase()).toString());
				}
			}
		}
		return sb.toString();
	}

	/**
	 *@MethodName:getFileName
	 *@Description:得到文件名称
	 *@param filePath
	 *@author:半仙儿
	 *@return String
	 *@date:2015-10-30上午09:53:37
	 */
	private String getFileName(String filePath) {
		int i = 0;
		i = filePath.lastIndexOf('/');
		if (i != -1)
			return filePath.substring(i + 1, filePath.length());
		i = filePath.lastIndexOf('\\');
		if (i != -1)
			return filePath.substring(i + 1, filePath.length());
		else
			return filePath;
	}
}
