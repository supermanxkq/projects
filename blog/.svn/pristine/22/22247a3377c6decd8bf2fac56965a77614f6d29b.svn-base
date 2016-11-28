<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="/WEB-INF/back/share/taglib.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
	<!--<![endif]-->
	<!-- BEGIN HEAD -->
	<head>
		<meta charset="utf-8" />
		<title>好456</title>
		<base href="<%=basePath%>">
		<meta content="width=device-width, initial-scale=1.0" name="viewport" />
		<meta content="" name="description" />
		<meta content="" name="author" />
		<link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="assets/bootstrap/css/bootstrap-responsive.min.css"
			rel="stylesheet" />
		<link href="assets/bootstrap/css/bootstrap-fileupload.css"
			rel="stylesheet" />
			<link rel="Shortcut Icon"href="images/logo.png" type="image/x-icon" />
		<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
		<link href="css/style.css" rel="stylesheet" />
		<link href="css/style_responsive.css" rel="stylesheet" />
		<link href="css/style_gray.css" rel="stylesheet" id="style_color" />

		<link href="assets/fancybox/source/jquery.fancybox.css"
			rel="stylesheet" />
		<link rel="stylesheet" type="text/css"
			href="assets/uniform/css/uniform.default.css" />
		<!-- 引入js文件 -->
		<script src="js/jquery-1.8.3.min.js"></script>
		<script src="js/common.js"></script>
		<!-- 内部js开始 -->
		<script type="text/javascript">
	//退出系统登录
	/**
	 *ajax添加
	 */
	function ajaxAdd() {
		var linkName = $("#linkName").val();
		var href = $("#href").val();
		var logoHref = $("#logoHref").val();
		var linkTypeId=$("#linkTypes").val();
		var dataUrl = "link/link!addSave";
		var params = {
			"linkDTO.linkName" : linkName,
			"linkDTO.href" : href,
			"linkDTO.logoHref" : logoHref,
			"linkDTO.linkType.linkTypeId":linkTypeId
		};
		$.ajax( {
			url : dataUrl,
			data : params,
			dataType : "json",
			cache : false,
			type : "POST",
			error : function(textStatus, errorThrown) {
				alert("error");
			},
			beforeSend : function() {
			},
			success : function(data, textStatus) {
				window.location.href = "link/link";
			}
		});
	}
	/**
	 *ajax获取分类信息
	 */
	function ajaxLoadLinkTypes() {
		var dataUrl = "link/link!queryLinkTypes";
		var params = {
		};
		$.ajax( {
			url : dataUrl,
			data : params,
			dataType : "json",
			cache : false,
			type : "POST",
			error : function(textStatus, errorThrown) {
				alert("error");
			},
			beforeSend : function() {
			},
			success : function(data, textStatus) {
				$("#linkTypes").html(data);
			}
		});
	}
</script>
		<!-- 内部js结束-->
	</head>
	<!-- END HEAD -->
	<!-- BEGIN BODY -->
	<body class="fixed-top" onload="ajaxLoadLinkTypes();">
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
					<!-- 链接开始 -->
					<!-- 链接开始 -->
					<li class="has-sub active">
					<a href="javascript:;" class=""> <span class="icon-box"><i
								class="icon-user"></i> </span> 好456<span class="arrow"></span> </a>
						<ul  class="sub">
							<li>
								<a class="" href="link/link!list?columnCode=2">好456</a>
							</li>
							<s:if test="#session.user_session!=null">
							<li>
								<a class="" href="link/link!toEditPage">好456管理</a>
							</li>
							</s:if>
						</ul>
					</li>
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
								好456
								<small>网站链接分享！</small>
							</h3>
							<ul class="breadcrumb">
								<li>
									<a href="article/article!list?columnCode=1"><i class="icon-home"></i> </a><span
										class="divider">&nbsp;</span>
								</li>
								<li>
									<a href="article/article!list?columnCode=1">网站链接分享</a><span
										class="divider-last">&nbsp;</span>
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
										<i class="icon-reorder"></i>网站链接分享
									</h4>
									<span class="tools"> <a href="javascript:;"
										class="icon-chevron-down"></a> <a class="icon-remove"
										href="javascript:;"></a> </span>
								</div>
								<div class="widget-body">
									<div class="row-fluid">
										<div class="span12">
											<table>
												<tr>
													<td>
														链接名称：
													</td>
													<td>
														<input type="text" id="linkName" />
													</td>
												</tr>
												<tr>
													<td>
														链接地址：
													</td>
													<td>
														<input type="text" id="href" />
													</td>
												</tr>
												<tr>
													<td>
														logo地址：
													</td>
													<td>
														<input type="text" id="logoHref" />
													</td>
												</tr>
												<tr>
													<td>
														分类：
													</td>
													<td>
														<select id="linkTypes">
														</select>
													</td>
												</tr>
												<tr>
													<td colspan="2">

													</td>
													<td>
														<input type="button" name="" value="保存"
															class="success btn" onclick="ajaxAdd();"/>
													</td>
												</tr>
											</table>
										</div>
										<div class="space10 visible-phone"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- END PAGE CONTENT-->
				</div>
				<!-- END PAGE CONTAINER-->
			</div>
			<!-- END PAGE -->
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
	</body>
	<!-- END BODY -->
</html>