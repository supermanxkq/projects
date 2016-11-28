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
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>徐半仙儿-资源列表</title>
   <base href="<%=basePath%>">
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
   <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
   <link href="assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
   <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
   <link href="css/style.css" rel="stylesheet" />
   <link href="css/style_responsive.css" rel="stylesheet" />
   <link href="css/style_gray.css" rel="stylesheet" id="style_color" />
		<link rel="Shortcut Icon"href="images/logo.png" type="image/x-icon" />
   <link href="assets/fancybox/source/jquery.fancybox.css" rel="stylesheet" />
   <link rel="stylesheet" type="text/css" href="assets/uniform/css/uniform.default.css" />
	<link rel="stylesheet" type="text/css" href="assets/chosen-bootstrap/chosen/chosen.css" />
   <!-- 引入js文件 -->
   <script src="js/jquery-1.8.3.min.js"></script>
		<script src="js/common.js"></script>
		<!-- 内部js结束-->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY query(${articleDTO.page})-->
<body class="fixed-top" >
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
									<a class="" href="blogtype/blogtype!list?columnCode=1">文章分类</a>
								</li>
							</s:if>
						</ul>
					</li>
					<li class="has-sub active">
						<a href="javascript:;" class=""> <span class="icon-box"><i
								class="icon-folder-open"></i> </span>文章归档 <span class="arrow"></span> </a>
						<ul class="sub">
							<s:iterator  value="result"  var="articleDTO">
							<li id="<s:property value="#articleDTO.blogType.blogTypeId"/>s" >
								<a class="" style="cursor:pointer;" onclick="queryByDate(1,'<s:property value="#articleDTO.contentDateString"/>')"><s:property value="#articleDTO.contentDateString"/>(<s:property value="#articleDTO.count"/>)</a>
							</li>
							</s:iterator>
						</ul>
					</li>
					<!-- 资源分享结束 -->
				</ul>
				<img alt="jay" src="images/jay.png">
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
                            <span class="settings">
                                <span class="text">Theme:</span>
                                <span class="colors">
                                    <span class="color-default" data-style="default"></span>
                                    <span class="color-gray" data-style="gray"></span>
                                    <span class="color-purple" data-style="purple"></span>
                                    <span class="color-navy-blue" data-style="navy-blue"></span>
                                </span>
                            </span>
                        </div>
                        <!-- END THEME CUSTOMIZER-->
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->				    			
						<h3 class="page-title">
							相册
							<small>相册照片</small>
						</h3>
                        <ul class="breadcrumb">
                            <li>
                                <a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span>
                            </li>
                            <li><a href="gallery/gallery!queryAll?columnCode=7">相册</a><span class="divider-last">&nbsp;</span></li>
                        </ul>
						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<!-- END PAGE CONTENT-->
				<!-- BEGIN PAGE CONTENT-->
				<div id="page">
					<!--BEGIN:BODY-->	
					<div class="row-fluid">
						<div class="span12">
							<!-- BEGIN GALLERY MANAGER PORTLET-->
							<div class="widget">
								<div class="widget-title">
									<h4><i class="icon-camera-retro"></i>相册</h4>
									<span class="tools">
									<a href="javascript:;" class="icon-chevron-down"></a>
									<a href="javascript:;" class="icon-remove"></a>
									</span>							
								</div>
								<div class="widget-body">
									<!-- BEGIN GALLERY MANAGER PANEL-->
<%--									<div class="row-fluid">--%>
<%--										<div class="span4">--%>
<%--											<h5> Category 1</h5>--%>
<%--										</div>--%>
<%--										<div class="span8">--%>
<%--											<div class="pull-right">--%>
<%--												<select data-placeholder="Select Category" class="chosen" tabindex="-1" id="inputCategory">--%>
<%--													<option value="0"></option>--%>
<%--													<option value="1">All</option>--%>
<%--													<option value="1">Category 1</option>--%>
<%--													<option value="1">Category 2</option>--%>
<%--													<option value="1">Category 3</option>--%>
<%--													<option value="1">Category 4</option>--%>
<%--												</select>--%>
<%--												<select data-placeholder="Sort By" class="chosen input-small" tabindex="-1" id="inputSort">--%>
<%--													<option value="0"></option>--%>
<%--													<option value="1">Date</option>--%>
<%--													<option value="1">Author</option>--%>
<%--													<option value="1">Title</option>--%>
<%--													<option value="1">Hits</option>--%>
<%--												</select>--%>
<%--												<div class="clearfix space5"></div>--%>
<%--											</div>--%>
<%--										</div>--%>
									</div>
									<!-- END GALLERY MANAGER PANEL-->
<%--									<hr class="clearfix" />--%>
									<!-- BEGIN GALLERY MANAGER LISTING-->
									<div class="row-fluid">
									<s:iterator value="photos" var="photo">
										<div class="span2" style="margin-left: 20px;">
											<div class="thumbnail">
												<div class="item" >
													<a class="fancybox-button" data-rel="fancybox-button" title="Photo" href="<s:property value="#photo.imgSrc"/>">
														<div class="zoom">
															<img src="<s:property value="#photo.imgSrc"/>" alt="Photo" />
															<div class="zoom-icon"></div>
														</div>
													</a>
												</div>
											</div>
										</div>
										</s:iterator>
									</div>
									<!-- END GALLERY MANAGER LISTING-->
									<!-- BEGIN GALLERY MANAGER PAGINATION-->
<%--									<div class="row-fluid">--%>
<%--										<div class="span12">--%>
<%--											<div class="pagination text-center">--%>
<%--												<ul>--%>
<%--													<li><a href="#">«</a></li>--%>
<%--													<li><a href="#">1</a></li>--%>
<%--													<li><a href="#">2</a></li>--%>
<%--													<li><a href="#">3</a></li>--%>
<%--													<li><a href="#">4</a></li>--%>
<%--													<li><a href="#">5</a></li>--%>
<%--													<li><a href="#">»</a></li>--%>
<%--												</ul>--%>
<%--											</div>--%>
<%--										</div>--%>
<%--									</div>--%>
									<!-- END GALLERY MANAGER PAGINATION-->
								</div>
							</div>
							<!-- END GALLERY MANAGER PORTLET-->
						</div>
					</div>
					<!--END:BODY-->
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
   <script type="text/javascript" src="assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
   <script type="text/javascript" src="assets/uniform/jquery.uniform.min.js"></script>
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