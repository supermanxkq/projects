<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>正在处理中,请稍候...</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script>
		function goHome() {
 			parent.leftFrame.location.href='<%=basePath%>system/index!firstleft';
			parent.main.location.href='<%=basePath%>system/index!right';
 		}
		</script>
		
	</head>
	<body>

		<dl class="H_return">
			<dt></dt>
			<dd>
				<p class="Color1 F16 B">
					您没有操作此模块的权限！
				</p>
				请选择
				<a href="javascript:goHome()">返回首页</a> 或 <a href="javascript:history.back();">返回上一页</a>
			</dd>
		</dl>

	</body>
</html>
