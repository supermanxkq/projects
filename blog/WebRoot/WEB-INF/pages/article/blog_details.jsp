<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
	<!--<![endif]-->
	<!-- BEGIN HEAD -->
	<head>
		<meta charset="utf-8" />
		<title>${articleDTO.title}</title>
		<base href="<%=basePath%>">
		<meta content="width=device-width, initial-scale=1.0" name="viewport" />
		<meta content="" name="description" />
		<meta content="" name="author" />
		<link rel="Shortcut Icon"href="images/logo.png" type="image/x-icon" />
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
		<script type="text/javascript">
			//退出系统登录
			function quit() {
				if(confirm("确定要退出系统吗？")){
					parent.window.location.href="<%=basePath%>system/login!logout";
			}
		}

			/**根据类型查找查询所有资源*/
			function queryByTypes(page,blogTypeId) {
				var title = $.trim($("#title").val());
					$("ul.sub li").removeClass("active");
					$("#"+blogTypeId+"s").addClass("active");
				var params = {
					"articleDTO.title" : title,
					"articleDTO.blogType.blogTypeId" : blogTypeId,
					"orderProperty" : $("#orderProperty").val(),
					"orderDirection" : $("#orderDirection").val(),
					"articleDTO.page" : page
				};

				window.location.href="article/article!list";
<%--				ajaxData("article/article!list", params);--%>
			}
			</script>
	</head>
	<!-- END HEAD -->
	<!-- BEGIN BODY -->
	<body class="fixed-top"  >
		<!-- Begin header -->
		<jsp:include page="/WEB-INF/pages/common/top2.jsp"></jsp:include>
		<!-- end header -->
		<!-- BEGIN CONTAINER -->
		<div id="container" class="row-fluid">
			<!-- BEGIN SIDEBAR -->
			<div id="sidebar" class="nav-collapse collapse">
				<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				<div class="sidebar-toggler hidden-phone"></div>
				<!-- BEGIN SIDEBAR TOGGLER BUTTON -->

				<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
				<div class="navbar-inverse">
					<form class="navbar-search visible-phone">
						<input type="text" class="search-query" placeholder="Search" />
					</form>
				</div>
				<!-- END RESPONSIVE QUICK SEARCH FORM -->
				<!-- BEGIN SIDEBAR MENU -->
						<ul class="sidebar-menu">
					<!-- 资源分享开始 -->
					<li class="has-sub active">
						<a href="javascript:;" class=""> <span class="icon-box"><i
								class="icon-folder-open"></i> </span> 资源分类 <span class="arrow"></span> </a>
						<ul class="sub">
							<s:iterator  value="records"  var="articleDTO">
							<li id="<s:property value="#articleDTO.blogType.blogTypeId"/>s" >
								<a class="" style="cursor:pointer;" onclick="queryByTypes(1,<s:property value="#articleDTO.blogType.blogTypeId"/>)"><s:property value="#articleDTO.blogType.name"/>(<s:property value="#articleDTO.blogTypeCount"/>)</a>
							</li>
							</s:iterator>
							<s:if test="#session.user_session!=null">
								<li>
									<a class="" href="blogtype/blogtype!list">文章分类</a>
								</li>
							</s:if>
						</ul>
					</li>
					<!-- 资源分享结束 -->
				</ul>
				<!-- END SIDEBAR MENU -->
			</div>
			<!-- END SIDEBAR -->
			<!-- BEGIN PAGE -->
			<div id="main-content">
				<!-- BEGIN PAGE CONTAINER-->
				<div class="container-fluid">
					<!-- BEGIN PAGE HEADER-->
					<div class="row-fluid">
						<div class="span12">
							<!-- BEGIN THEME CUSTOMIZER-->
							<div id="theme-change" class="hidden-phone">
								<i class="icon-cogs"></i>
								<span class="settings"> <span class="text">Theme:</span>
									<span class="colors"> <span class="color-default"
										data-style="default"></span> <span class="color-gray"
										data-style="gray"></span> <span class="color-purple"
										data-style="purple"></span> <span class="color-navy-blue"
										data-style="navy-blue"></span> </span> </span>
							</div>
							<!-- END THEME CUSTOMIZER-->
							<!-- BEGIN PAGE TITLE & BREADCRUMB-->
							<h3 class="page-title">
								资源详情
								<small>查看资源</small>
							</h3>
							<ul class="breadcrumb">
								<li>
									<a href="javascript:;"><i class="icon-home"></i> </a><span
										class="divider">&nbsp;</span>
								</li>
								<li>
									<a href="article/article!list?columnCode=1">资源分享</a>
									<span class="divider">&nbsp;</span>
								</li>
								<li>
									<a href="javascript:;">查看资源详情</a><span class="divider-last">&nbsp;</span>
								</li>
							</ul>
							<!-- END PAGE TITLE & BREADCRUMB-->
						</div>
					</div>
					<!-- END PAGE HEADER-->

					<!-- BEGIN PAGE CONTENT-->
					<div class="row-fluid">
						<div class="span12">
							<div class="widget">
								<div class="widget-title">
									<h4>
										<i class="icon-globe"></i>查看资源详情
									</h4>
									<span class="tools"> <a href="javascript:;"
										class="icon-chevron-down"></a> <a href="javascript:;"
										class="icon-remove"></a> </span>
								</div>
								<div class="widget-body">
									<div class="row-fluid blog">

										<div class="span12">
											<h2>
												${articleDTO.title}
											</h2>
											<p>
												作者：
												<a href="javascript:;">${articleDTO.user.userName}</a>
											</p>
											<div>
												<s:property value="articleDTO.content" escape="false" />
											</div>
							<!--打分版，用户可以对当前新闻、游戏或者应用等进行1-5级的评分-->
							<div id="SOHUCS" sid="${articleDTO.articleId}"></div>
										</div>
										<%--                                    <!--end media-->--%>
										<!--end post comments-->
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- END PAGE CONTENT-->
					<!-- END PAGE CONTAINER-->
				</div>
				<!-- END PAGE -->
			</div>
		</div>
		<!-- END CONTAINER -->
		<!-- begin footer -->
		<jsp:include page="/WEB-INF/pages/common/footer.jsp"></jsp:include>
		<!-- end footer -->

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
	jQuery(document).ready(function() {
		// initiate layout and plugins
			App.init();
		});
</script>
		<!-- END JAVASCRIPTS -->

		<script charset="utf-8" type="text/javascript" src="http://changyan.sohu.com/upload/changyan.js" ></script>
<script type="text/javascript">
    window._config = { showScore: true };
    window.changyan.api.config({
        appid: 'cys5vNeQO',
        conf: 'prod_722e32af470624f7441b46e1922e6c3c'
    });
</script> 
	</body>
	<!-- END BODY -->
</html>