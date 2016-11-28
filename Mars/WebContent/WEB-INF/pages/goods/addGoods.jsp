<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<title>添加商品</title>
		<base href="<%=basePath%>" />
		<meta content="width=device-width, initial-scale=1.0" name="viewport" />
		<meta content="" name="description" />
		<meta content="" name="author" />
		<link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="assets/bootstrap/css/bootstrap-responsive.min.css"
		<link rel="Shortcut Icon"href="images/logo.png" type="image/x-icon" />
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
		<script src="js/jquery-1.4.2.min.js"></script>
		<script src="js/common.js"></script>
		<script src="js/datepicker/WdatePicker.js"></script>
		<script type="text/javascript" charset="utf-8"
			src="ueditor/ueditor.config.js"></script>
		<script type="text/javascript" charset="utf-8"
			src="ueditor/ueditor.all.min.js"> </script>
		<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
		<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
		<script type="text/javascript" charset="utf-8"
			src="ueditor/lang/zh-cn/zh-cn.js"></script>
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
		//提交
		function check(){
			//获取UEditor中的内容，然后进行表单的提交
				    if (!UE.getEditor('editor').hasContents()){
				    alert('请先填写内容!');
				    return false;
				    }else{
				    $("#content").val(UE.getEditor('editor').getContent());
				    $("#fm").submit();
				  }
		}
</script>
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
					<!-- 链接开始 -->
					<li class="has-sub">
					<a href="javascript:;" class=""> <span class="icon-box"><i
								class="icon-user"></i> </span> 好456<span class="arrow"></span> </a>
						<ul class="sub">
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
								<span class="settings"> <span class="text">主题:</span> <span
									class="colors"> <span class="color-default"
										data-style="default"></span> <span class="color-gray"
										data-style="gray"></span> <span class="color-purple"
										data-style="purple"></span> <span class="color-navy-blue"
										data-style="navy-blue"></span> </span> </span>
							</div>
							<!-- END THEME CUSTOMIZER-->
							<!-- BEGIN PAGE TITLE & BREADCRUMB-->
							<h3 class="page-title">
								添加商品
								<small>添加商品！</small>
							</h3>
							<ul class="breadcrumb">
								<li>
									<a href="#"><i class="icon-home"></i>
									</a><span class="divider">&nbsp;</span>
								</li>
								<li>
									<a href="goods/goods!list">添加商品</a>
									<span class="divider-last">&nbsp;</span>
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
										<i class="icon-globe"></i>添加商品
									</h4>
									<span class="tools"> <a href="javascript:;"
										class="icon-chevron-down"></a> <a href="javascript:;"
										class="icon-remove"></a> </span>
								</div>
								<div class="widget-body">
									<s:form action="goods/goods!add" method="post" id="fm"
										onsubmit="document.getElementById('submitInput').disabled = true;return true;"
										theme="simple" enctype="multipart/form-data">
										<s:hidden name="method" id="method" />
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="formTable">
											<tr>
												<td align="right">
													<span class="Color5">* </span>商品名称：
												</td>
												<td>
													<s:textfield name="goodsDTO.name" id="name"
														maxlength="50" cssClass="formInput" theme="simple"
														 />
													<label id="nameMsg" class="errorMsg" style="display: none;"></label>
												</td>
												<th align="right">
													<span class="Color5">* </span>价格：
												</th>
												<td>
													<s:textfield name="goodsDTO.price" id="price"
														maxlength="50" cssClass="formInput" theme="simple"
														 />
												</td>
											</tr>
											<tr>
												<td align="right">
													<span class="Color5">* </span>图片：
												</td>
												<td>
												<s:file name="goodsDTO.imageFile" id="file" cssClass="formInput" theme="simple"
														 />
												</td>
												<th align="right">
													<span class="Color5">* </span>简述：
												</th>
												<td>
													<s:textfield name="goodsDTO.content" 
														maxlength="50" cssClass="formInput" theme="simple"
														 />
												</td>
											</tr>
											<tr>
												<th align="right" width="10%">
													描述：
												</th>
												<td width="30%" colspan="3">
													<!-- 加载编辑器的容器 -->
													<script id="editor" type="text/plain"
														style="width:900px;height:500px;">
														</script>
													<s:hidden id="content" name="goodsDTO.description" />
												</td>
											</tr>
										</table>
										<div class="text-center">
												<input id="submitInput" type="submit"
													class="btn btn-success" value="保 存"
													onclick="return check();" />
											<input type="button" class="btn" value="返 回"
												onclick="go('article/article!list?columnCode=1')"/>
										</div>
									</s:form>
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
					<script>
			var ue = UE.getEditor('editor');
		jQuery(document).ready(function() {
			// initiate layout and plugins
				App.init();
			});
	</script>
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
			<!-- END JAVASCRIPTS -->
	</body>
	<!-- END BODY -->
</html>