<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:wb="http://open.weibo.com/wb">
	<head>
		<base href="<%=basePath%>" />
		<title>${title}</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js"
			type="text/javascript" charset="utf-8"></script>
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
			type="image/x-icon" />
<%--		<!--[if ie]>--%>
<%--			<script type="text/javascript">--%>
<%--			$(function(){--%>
<%--	        function Hresize(){--%>
<%--	        var a = $(window).height();--%>
<%--	        $("table").height(a-100);}--%>
<%--	        $(window).resize(function() {Hresize()});--%>
<%--	        Hresize();--%>
<%--			});--%>
<%--	        </script>--%>
<%--	     <![endif]-->--%>
		<link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="assets/bootstrap/css/bootstrap-responsive.min.css"
			rel="stylesheet" />
		<link href="assets/bootstrap/css/bootstrap-fileupload.css"
			rel="stylesheet" />
		<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
		<link href="css/style.css" rel="stylesheet" />
		<link href="css/style_responsive.css" rel="stylesheet" />
		<link href="css/style_gray.css" rel="stylesheet" id="style_color" />

		<link href="assets/fancybox/source/jquery.fancybox.css"
			rel="stylesheet" />
		<link rel="stylesheet" type="text/css"
			href="assets/uniform/css/uniform.default.css" />
		<style type="text/css">
html {
	overflow-y: auto;
	overflow-x: hidden;
	height: 100%;
}

body {
	height: 100%;
	margin: 0px;
	padding: 0px;
}
</style>
		<script type="text/javascript">
function quit() {
	if(confirm("确定要退出系统吗？")){
		parent.window.location.href="<%=basePath%>system/login!logout";
		}
	}
function startTime(){ 
	$(".time").text(new Date().toLocaleString()); 
	setTimeout('startTime()',1000); 
	} 
</script>
	</head>
	<body style="overflow: auto;" onload="startTime();">
		<!-- BEGIN HEADER -->
		<div id="header" class="navbar navbar-inverse navbar-fixed-top">
			<!-- BEGIN TOP NAVIGATION BAR -->
			<div class="navbar-inner">
				<div class="container-fluid">
					<!-- BEGIN LOGO -->
					<a class="brand" href="system/index"> <img src="img/logo.png"
							title="徐半仙儿" /> </a>
					<!-- END LOGO -->
					<!-- BEGIN RESPONSIVE MENU TOGGLER -->
					<a class="btn btn-navbar collapsed" id="main_menu_trigger"
						data-toggle="collapse" data-target=".nav-collapse"> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="arrow"></span> </a>
					<!-- END RESPONSIVE MENU TOGGLER -->
					<div id="top_menu" class="nav notify-row">
						<!-- BEGIN NOTIFICATION -->
						<ul class="nav top-menu">
							<!-- BEGIN SETTINGS -->
							<li style="margin-top: 10px;">
								<wb:follow-button uid="2812670461" type="red_1" width="67"
									height="24"></wb:follow-button>
							</li>
							<li>
								<a target="_blank"
									href="http://shang.qq.com/wpa/qunwpa?idkey=fe632f87ba9ee37e8d3f1c7536803d42927bcb323bf496cee7d9dd4cf4b6585a"><img
										border="0" src="http://pub.idqqimg.com/wpa/images/group.png"
										alt="JAVA网站网络技术群" title="JAVA网站网络技术群"></img>
								</a>
							</li>
							<li>
								<a target="_blank"
									href="http://wpa.qq.com/msgrd?v=3&uin=994028591&site=qq&menu=yes"><img
										border="0" src="http://wpa.qq.com/pa?p=2:451295859:41"
										alt="点击这里给我发消息" title="点击这里给我发消息"/>
								</a>
							</li>
							<li >
								<p class="time" style="line-height:10px;"></p>
								<p class="words" >如果今天是我生命的最后一天，那些原本今天要做的事我还想去做吗？</p>
							</li>
							<!-- END NOTIFICATION DROPDOWN -->
						</ul>
					</div>
					<!-- END  NOTIFICATION -->
					<div class="top-nav">
						<ul class="nav pull-right top-menu">
							<!-- BEGIN SUPPORT -->
<%--							<s:if test="#session.user_session==null">--%>
<%--										<li class="dropdown mtop5">--%>
<%--								</li>--%>
<%--								<li class="dropdown mtop5">--%>
<%--									<a href="#">[注册]</a>--%>
<%--								</li>--%>
<%--							</s:if>--%>
							<!-- END SUPPORT -->
							<!-- BEGIN USER LOGIN DROPDOWN -->
							<s:if test="#session.user_session!=null">
								<li class="dropdown" style="display: ;">
									<a href="javascript:;" class="dropdown-toggle"
										data-toggle="dropdown"> <img src="img/avatar1_small.jpg"
											alt=""/> <span class="username">${user_session.userName}</span>
										<b class="caret"></b> </a>
									
									<ul class="dropdown-menu">
										<li>
											<a href="profile/profile!list" target="main"><i
												class="icon-user"></i>个人信息</a>
										</li>
										<li>
											<a href="javascript:;"><i class="icon-calendar"></i>
												Calendar</a>
										</li>
										<li class="divider"></li>
										<li>
											<a onclick="quit();"><i class="icon-key"></i>退出</a>
										</li>
									</ul>
								</li>
							</s:if>
							<!-- END USER LOGIN DROPDOWN -->
						</ul>
						<!-- END TOP NAVIGATION MENU -->
					</div>
				</div>
			</div>
			<!-- END TOP NAVIGATION BAR -->
		</div>
		<!-- END HEADER -->
		<iframe style="z-index: 0; width: 100%; height: 100%;" id="main"
			name="main" allowTransparency="true" marginheight="0"
			src="<%=basePath%>article/article!list?columnCode=1" frameborder="0" noResize></iframe>
		<iframe
			style="Z-INDEX: -1; VISIBILITY: inherit; width: 100%; height: 25px"
			name="Explorer_Tool" marginwidth="0" marginheight="0"
			src="<%=basePath%>system/index!footer" frameborder="0" noResize
			scrolling="no" bordercolor="threedface"></iframe>

		<!-- BEGIN JAVASCRIPTS -->
		<!-- Load javascripts at bottom, this will reduce page load time -->
		<script src="js/jquery-1.8.3.min.js"></script>
		<script src="assets/bootstrap/js/bootstrap.min.js"></script>
		<script src="js/jquery.blockui.js"></script>
		<!-- ie8 fixes -->
		<!--[if lt IE 9]>
   <script src="js/excanvas.js"></script>
   <script src="js/respond.js"></script>
   <![endif]-->
		<script type="text/javascript"
			src="assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
		<script type="text/javascript"
			src="assets/uniform/jquery.uniform.min.js"></script>
		<script src="js/scripts.js"></script>
		<script>
		$(window.parent.document).find("#main").load(function() {
			var main = $(window.parent.document).find("#main");
			var thisheight = $(document).height() + 30;
			main.height(thisheight);
		});
</script>
		<!-- END JAVASCRIPTS -->
		
	</body>

</html>
