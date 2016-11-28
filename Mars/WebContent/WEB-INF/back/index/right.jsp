<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back/share/taglib.jsp"%>
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
<link rel="Shortcut Icon" href="images/time.png" type="image/x-icon" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<style>
.R_ts_bg {
	width: 727px;
	height: 238px;
	background: url(images/ts_bg.gif) no-repeat;
	margin: 160px auto 0 auto;
	padding: 60px 0 0 50px;
}

.R_ts_bg_ul {
	
}

.R_ts_bg_ul li {
	float: left;
	width: 160px;
	height: 39px;
	line-height: 39px;
	border: 1px solid #ccc;
	padding-left: 40px;
	margin: 20px 0 0 15px;
	overflow: hidden;
	background: url(images/sy_li_bg.jpg) no-repeat
}

.R_ts_bg_ul li a {
	font-size: 14px;
	font-weight: bold;
	color: #333
}

.R_ts_bg_ul li a span {
	color: #f60
}
</style>
<script type="text/javascript">
		var sendTime = 3*60*1000;//弹出频率   1秒=1000
		var stayTime = 5*1000;//停留时间   1秒=1000
		function showmsg(){
			var userName = $("#userName").val();
			var params = {
		        "userDTO.userName" : userName
		  	};
			var actionUrl = "system/index!tips";
		    $.ajax( {
		        url : actionUrl,
		        data : params,   
		        dataType : "json",
		        cache : false, 
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		    		//var thestr = "系统ajax交互错误!";
			 		//$.messager.lays(280, 160);//设置宽高 
					//$.messager.show('<font color=red>系统提示</font>',thestr, stayTime);
		        },
		        success : function(data, textStatus) {
		            if(data!=""){
		            	var thetable = "<ul class='H_system' id='message_content'>";
		            	thetable = thetable + data;
		            	thetable = thetable + "</ul>";
		            	$.messager.lays(280, 160);//设置宽高 
						$.messager.show('<font color=red>系统提示</font>',thetable, stayTime);
		            }
		        }   
		    });
		}
		
		//window.setInterval("showmsg();",sendTime);//间隔循环执行showmsg()函数
		
	</script>
</head>
<body style="background: url(images/bg2.jpg) center top;"
	onload="showmsg()">
	<input type="hidden" id="userName" name="userName"
		value="${user_session.userName}" />

	<div class="H_header" style="padding-top: 10px;">

		<div id="userMenu"
			style="display: none; width: 10; height: 30; z-index: 101111">
			<%--
			   <iframe src="<%=basePath%>system/index!tool">
			   </iframe>
			   --%>
			<ul>
				<li style="line-height: 50px; height: 0px; backgroung-color: #fff;"><img
					src="images/quit.png" /><a href="javascript:quit()">&nbsp;&nbsp;&nbsp;&nbsp;安全退出</a></li>
				<li style="line-height: 10px; height: 0px; z-index: 100000"><img
					src="images/home.png" /><a href="javascript:goHome()">&nbsp;&nbsp;&nbsp;&nbsp;返回主页</a></li>
				<li style="line-height: 40px; height: 10px; z-index: 100000"><img
					src="images/u.png" /><a href="system/index!myself" target="main">&nbsp;&nbsp;&nbsp;&nbsp;用户设置</a></li>

			</ul>

		</div>
	</div>
	<!-- 
	<div class="R_ts_bg">
	<ul class="R_ts_bg_ul">
	<li><a href="#">待审核单据<span>(25)</span></a></li>
	<li><a href="#">待审核单据<span>(25)</span></a></li>
	<li><a href="#">待审核单据<span>(25)</span></a></li>
	<li><a href="#">待审核单据<span>(25)</span></a></li>
	<li><a href="#">待审核单据<span>(25)</span></a></li>
	<li><a href="#">待审核单据<span>(25)</span></a></li>
	</ul>
	</div>
	 -->
</body>
</html>

