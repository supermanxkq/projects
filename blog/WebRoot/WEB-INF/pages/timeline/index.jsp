<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>那些年走过的日子</title>
<link rel="Shortcut Icon"href="images/logo.png" type="image/x-icon" />
<link href="css/timelinecss/style.css" rel="stylesheet" type="text/css" />
<script src="js/timelinejs/js/jquery.min.js" type="text/javascript"></script>
</head>
<body>
  <script type="text/javascript">
$(document).ready(function(){
  $.post("timeLine/timeLine!articleList", function(data){
        $(".main").html(data);
   });
   });

</script>

<div class="content">
	<div class="wrapper">
		<div class="light"><i></i></div>
		<hr class="line-left">
		<hr class="line-right">
		<div class="main">			
			
			
	
		</div>
	</div>
</div>



<script type="text/javascript">
<%--$(".main  .year .list").each(function(e, target){--%>
<%--	var $target=  $(target),--%>
<%--	$ul = $target.find("ul");--%>
<%--	$target.height($ul.outerHeight()), $ul.css("position", "absolute");--%>
<%--}); --%>
<%--$(".main .year>h2>a").click(function(e){--%>
<%--	e.preventDefault();--%>
<%--	$(this).parents(".year").toggleClass("close");--%>
<%--});--%>
</script>
	
</body>
</html>
