
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<%
/* *
 功能：支付宝页面跳转同步通知页面
 版本：3.2
 日期：2011-03-17
 说明：
 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 //***********页面功能说明***********
 该页面可在本机电脑测试
 可放入HTML等美化页面的代码、商户业务逻辑程序代码
 TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
 TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
 //********************************
 * */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.blog.alipay.util.*"%>
<%@ page import="com.blog.alipay.config.*"%>
<html>
  <head>
  <base href="<%=basePath %>">
  <link href="css/style.css" rel="stylesheet" type="text/css" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝页面跳转同步通知页面</title>
  </head>
  <body>
  		捐助成功！感谢您的帮助。。。
<%--  		<dl class="H_return">--%>
<%--			<dt></dt>--%>
<%--			<dd>--%>
<%--				<span id="tiao" class="Color1 F16 B">3</span>--%>
<%--				<a href="javascript:countDown"></a> 秒后页面将自动跳转 <a href="javascript:go()">立即跳转</a>	--%>
<%--				<script language="javascript" >--%>
<%--					function countDown(secs){ --%>
<%--						tiao.innerText=secs;--%>
<%--						if(--secs>0){ --%>
<%--							setTimeout("countDown("+secs+")",1000);--%>
<%--						}else{ --%>
<%--							window.location.href='<%=basePath%>help/help!toHelpPage?columnCode=4';--%>
<%--						}--%>
<%--					}--%>
<%--					countDown(3);--%>
<%--					function go(){ --%>
<%--						window.location.href='<%=basePath%>help/help!toHelpPage?columnCode=4';--%>
<%--					}--%>
<%--				</script>	--%>
<%--			</dd>--%>
<%--		</dl>--%>
  </body>
</html>