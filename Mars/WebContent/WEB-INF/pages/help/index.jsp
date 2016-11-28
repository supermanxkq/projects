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
		<title>友情捐助</title>
		<base href="<%=basePath%>">
		<meta content="width=device-width, initial-scale=1.0" name="viewport" />
		<meta content="" name="description" />
		<meta content="" name="author" />
		<link rel="Shortcut Icon" href="images/logo.png" type="image/x-icon" />
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
		<!-- 引入js文件 -->
		<script src="js/jquery-1.8.3.min.js"></script>
		<script src="js/common.js"></script>
		<!-- 内部js开始 -->
		<script type="text/javascript">
		//退出系统登录
		function quit() {
			if(confirm("确定要退出系统吗？")){
				parent.window.location.href="<%=basePath%>
	ystem/login!logout";
		}
	}
</script>
		<!-- 内部js结束-->

	</head>
	<!-- END HEAD -->
	<!-- BEGIN BODY -->
	<body class="fixed-top">
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
					<!-- 友情捐助开始-->
					<li class="has-sub active">
						<a href="javascript:;" class=""> <span class="icon-box"><i
								class="icon-heart-empty"></i> </span> 友情捐助<span class="arrow"></span> </a>
						<ul class="sub">
							<li class="active">
								<a class="" href="help/help!toHelpPage?columnCode=4">友情捐助</a>
							</li>
						</ul>
					</li>
					<!-- 友情捐助结束 -->
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
								友情捐助
								<small>献爱心</small>
							</h3>
							<ul class="breadcrumb">
								<li>
									<a href="#"><i class="icon-home"></i>
									</a><span class="divider">&nbsp;</span>
								</li>
								<li>
									<a href="#">友情捐助</a>
									<span class="divider">&nbsp;</span>
								</li>
								<li>
									<a href="#">献爱心</a><span class="divider-last">&nbsp;</span>
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
										<i class="icon-globe"></i>友情捐助
									</h4>
									<span class="tools"> <a href="javascript:;"
										class="icon-chevron-down"></a> <a href="javascript:;"
										class="icon-remove"></a> </span>
								</div>
								<div class="widget-body">
									<form name=alipayment action="shop/shop!createOrder"
										method="post" class="form-horizontal">
										<input type="hidden" name="goods.id" value="${goods.id}" />
										<div class="span12">
											<input type="hidden" name="order.body" value="友情捐助"></input>
											<input type="hidden" name="order.out_trade_no"
												value="${out_trade_no}" />
											<input type="hidden" name="order.subject" value="捐助"></input>
											<label class="control-label" style="text-align: right;">
												捐助金额：
											</label>
											<div class="input-prepend input-append">
												<span class="add-on">￥</span>
												<input class="form-control" name="order.price" type="number"
													placeholder="请输入捐助金额......" autofocus required>
												<span class="add-on">元</span>
												<button class="btn btn-success " type="submit">
													捐助
												</button>
											</div>
										</div>
									</form>

									<%--                                 <form name=alipayment action="help/help!createHelp" method=post target="_blank"  class="form-horizontal">--%>
									<%--						<input type="hidden" size="30" name="orderDTO.out_trade_no"  value="${out_trade_no}"/>--%>
									<%--						<input type="hidden" size="30" name="orderDTO.subject"  value="捐助"/>--%>
									<%--						<input type="text"  name="orderDTO.price" placeholder="请输入捐助金额......" class="input-large">--%>
									<%--						<span>必填 </span>--%>
									<%--						<div style="margin-left:250px;">--%>
									<%--                            <label class="control-label" style="text-align: right;">捐助金额：</label>--%>
									<%--                                 <div class="input-prepend input-append">--%>
									<%--                                    <span class="add-on">￥</span><input class="form-control" name="orderDTO.price"  type="number" placeholder="请输入捐助金额......"  autofocus required><span class="add-on">元</span>--%>
									<%--                              <button class="btn btn-success " type="submit">捐助</button>--%>
									<%--                                 </div>--%>
									<%--                         </form>--%>
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
