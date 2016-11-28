<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BootStrap Hello World</title>
<!-- pageContext.request.contextPath表示绝对路径 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
</head>
<body style="padding: 20px;">
<h1>你好，世界！，在Eclipse中可以服务器邮件进行clean操作。</h1>
	<!-- Indicates a dangerous or potentially negative action -->
	<button type="button" class="btn btn-danger">我是全局Css中的按钮</button>
</body>
</html>