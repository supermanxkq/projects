<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title></title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" language="JavaScript">
		/**function toggleMenu()
		{
		
		  if (parent.$("#frmTitle").css("width") != "0px")
		  {
		    parent.$("#frmTitle").css({ width: "0px", border: "none" });
		    parent.$("#leftFrame").css("width", "0px");
		    $("div.H_sepa").toggleClass("H_exit");
		  }
		  else
		  {
		    parent.$("#frmTitle").css({ width: "180px", border:"1px solid #ccc","border-top": "none"});
		    parent.$("#leftFrame").css("width", "180px");
		    $("div.H_sepa").toggleClass("H_exit");
		  }
		}**/
		</script>
</head>
<body>
	<!--  <body onclick="toggleMenu();" >
	<div class="H_sepa" ></div>-->
</body>
</html>