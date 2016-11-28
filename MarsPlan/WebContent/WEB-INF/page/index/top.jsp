<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>${title}</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="Shortcut Icon"href="images/time.png"type="image/x-icon" />
		<link href="css/css.css" rel="stylesheet" type="text/css" />
		<link href="css/style2.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript">
		     $(function(){
				$(".top ul li").click(function(){
					$(this).addClass("seleli").siblings().removeClass("seleli");
				});
			 });
			 function goHome() {
		 			parent.leftFrame.location.href='<%=basePath%>system/index!firstleft';
					parent.main.location.href='<%=basePath%>code/code!listB';
		 		}
			 
		</script>
	</head>
	<body>
		<!-- 修改后-->
		<div class="header">
			<div class="top">
				<img class="logo" src="images/logo.png" />
				<ul class="nav">
					<li class="seleli">
						<a href="javascript:goHome()">常用操作</a>
					</li>
					<s:iterator value="#request.modules">
						<li>
							<a href="system/index!left?pid=<s:property value="id" />"
								target="leftFrame"><s:property value="name" />
							</a>
						</li>
					</s:iterator>
				</ul>
			</div>
		</div>
	</body>
</html>