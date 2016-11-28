<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html lang="en-US">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title>ueditor demo</title>
	</head>

	<body>
		<!-- 加载编辑器的容器 -->
		<textarea id="container" name="content" type="text/plain"
			style="width: 800px;">
    </textarea>
		<!-- 配置文件 -->
		<script type="text/javascript" src="../mailEdit/ueditor.config.js"></script>
		<!-- 编辑器源码文件 -->
		<script type="text/javascript" src="../mailEdit/ueditor.all.js"></script>
		<script type="text/javascript" src="../mailEdit/ueditor.all.min.js"></script>
		<!-- 实例化编辑器 -->
		<script type="text/javascript" charset="UTF-8">
	var ue = UE.getEditor('container');
</script>
	</body>
</html>