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
		<meta name="Keywords" content="" />
		<meta name="Description" content="" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.soChange-min.js"></script>
		<script type="text/javascript">
	     	$(document).ready(function()
			{
				$("#firstpane p.menu_head").click(function()
			    {
					$(this).css({backgroundImage:"url(images/down.png)"}).next("div.menu_body").slideToggle(0).siblings("div.menu_body").slideUp("slow");
			       	$(this).siblings().css({backgroundImage:"url(images/left.png)"});
				});
			});
		</script>
	</head>
	<body>
	<div class="H_left">
		  <div id="firstpane" class="menu_list">
		    <s:iterator value="#request.modules" >
		    	<p class="menu_head"><s:property value="name" /></p>
		    	<div class="menu_body"> 
		    	<s:iterator value="childModules" >
		    		<a href="<s:property value="linkAddr" />" target="main"><s:property value="name" /></a>
		    	</s:iterator>
		    	</div>
		    </s:iterator>
		  </div>
   </div>
	</body>
</html>