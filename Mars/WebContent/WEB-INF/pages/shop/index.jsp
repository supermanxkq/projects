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
		<title>商城</title>
		<base href="<%=basePath%>">
		<meta content="width=device-width, initial-scale=1.0" name="viewport" />
		<meta content="" name="description" />
		<meta content="" name="author" />
		<link rel="Shortcut Icon" href="images/logo.png" type="image/x-icon" />
		<link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="assets/bootstrap/css/bootstrap-responsive.min.css"
			rel="stylesheet" />
<%--		<link href="css/css1/swipebox.css" rel="stylesheet" />--%>
		<link href="css/style_responsive.css" rel="stylesheet" />
		   <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />

		<link href="css/style.css" rel="stylesheet" />
		<%--   <link href="css/css1/bootstrap.css" rel="stylesheet" />--%>
		<link href="css/style_gray.css" rel="stylesheet" id="style_color" />
<%--		   <link href="assets/fancybox/source/jquery.fancybox.css" rel="stylesheet" />--%>
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
				parent.window.location.href="<%=basePath%>system/login!logout";
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
					<!-- 资源分享开始 -->
					<li class="has-sub active">
						<a href="javascript:;" class=""> <span class="icon-box"><i
								class="icon-folder-open"></i> </span> 资源分类 <span class="arrow"></span>
						</a>
						<ul class="sub">
							<s:iterator value="records" var="articleDTO">
								<li id="<s:property value="#articleDTO.blogType.blogTypeId"/>s">
									<a class="" style="cursor: pointer;"
										onclick="queryByTypes(1,<s:property value="#articleDTO.blogType.blogTypeId"/>)"><s:property
											value="#articleDTO.blogType.name" />(<s:property
											value="#articleDTO.blogTypeCount" />)</a>
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
							<s:iterator value="result" var="articleDTO">
								<li id="<s:property value="#articleDTO.blogType.blogTypeId"/>s">
									<a class="" style="cursor: pointer;"
										onclick="queryByDate(1,'<s:property value="#articleDTO.contentDateString"/>')"><s:property
											value="#articleDTO.contentDateString" />(<s:property
											value="#articleDTO.count" />)</a>
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
								<span class="settings"> <span class="text">Theme:</span>
									<span class="colors"> <span class="color-gray"
										data-style="gray"></span> <span class="color-default"
										data-style="default"></span> <span class="color-purple"
										data-style="purple"></span> <span class="color-navy-blue"
										data-style="navy-blue"></span> </span> </span>
							</div>
							<!-- END THEME CUSTOMIZER-->
						</div>
					</div>
					<!-- END PAGE HEADER-->
					<h3 class="page-title">
								商城
								<small>P2P电子商城。</small>
							</h3>
							<ul class="breadcrumb">
								<li>
									<a href="article/article!list?columnCode=1"><i class="icon-home"></i>
									</a><span class="divider">&nbsp;</span>
								</li>
								<li>
									<a href="article/article!list?columnCode=1">电子商城</a><span class="divider-last">&nbsp;</span>
								</li>
							</ul>
					<!-- BEGIN PAGE CONTENT-->
					<div class="row-fluid">
						<div class="span12">
							<div class="widget">
							<div class="widget-title">
	                           <h4><i class="icon-globe"></i>商城</h4>
	                           <span class="tools">
	                           <a href="javascript:;" class="icon-chevron-down"></a>
	                           <a href="javascript:;" class="icon-remove"></a>
	                           </span>                    
                        	</div>
								<div class="widget-body" id="content">
									<div class="row-fluid">
									<s:iterator value="goodsList" var="goods" status="status">
										<div class="span3" style="margin-left: 20px;border:3px solid white;padding-left:20px;"onmouseover="this.style.borderColor='#A52A2A'"  onmouseout="this.style.borderColor='white'" >
											<a href="shop/shop!detail?goods.id=<s:property value="#goods.id"/>" ><img class="img-polaroid" style="width: 210px; height: 210px;" src="<s:property value="#goods.imgsrc"/>" class="img-responsive zoom-img" alt=""></a>
											<h4><s:property value="#goods.name"/></h4>  
		        							<h3 style="color: #C00;">￥<s:property value="#goods.price"/>.0元</h3>
											<p>
												<s:property value="#goods.content"/>
											</p>
										</div>
									</s:iterator>
<%--          <ul class="thumbnails bootstrap-examples">--%>
<%--            <li class="span3">--%>
<%--              <a class="thumbnail" href="examples/starter-template.html" target="_blank">--%>
<%--                <img src="assets/img/examples/bootstrap-example-starter.png" alt="">--%>
<%--              </a>--%>
<%--              <h4>最简模版</h4>--%>
<%--              <p>一个包含了Bootstrap的所有CSS和JavaScript文件的最简HTML文档。</p>--%>
<%--            </li>--%>
<%--            <li class="span3">--%>
<%--              <a class="thumbnail" href="examples/hero.html" target="_blank">--%>
<%--                <img src="assets/img/examples/bootstrap-example-marketing.png" alt="">--%>
<%--              </a>--%>
<%--              <h4>基本的营销类网站</h4>--%>
<%--              <p>具有一个主消息板块和三个辅助性元素。</p>--%>
<%--            </li>--%>
<%--            <li class="span3">--%>
<%--              <a class="thumbnail" href="examples/fluid.html" target="_blank">--%>
<%--                <img src="assets/img/examples/bootstrap-example-fluid.png" alt="">--%>
<%--              </a>--%>
<%--              <h4>流式布局</h4>--%>
<%--              <p>使用新的响应式布局, 流式栅格系统建立的一个流式布局。</p>--%>
<%--            </li>--%>
<%----%>
<%--            <li class="span3">--%>
<%--              <a class="thumbnail" href="examples/marketing-narrow.html" target="_blank">--%>
<%--                <img src="assets/img/examples/bootstrap-example-marketing-narrow.png" alt="">--%>
<%--              </a>--%>
<%--              <h4>精简版的营销类网站</h4>--%>
<%--              <p>适用于小项目或团队的简单、轻量级的营销类模板。</p>--%>
<%--            </li>--%>
<%--            <li class="span3">--%>
<%--              <a class="thumbnail" href="examples/justified-nav.html" target="_blank">--%>
<%--                <img src="assets/img/examples/bootstrap-example-justified-nav.png" alt="">--%>
<%--              </a>--%>
<%--              <h4>两端对齐的导航条</h4>--%>
<%--              <p>带有等宽的导航链接的市场营销类页面，导航条在原始样式的基础上进行了修改。</p>--%>
<%--            </li>--%>
<%--            <li class="span3">--%>
<%--              <a class="thumbnail" href="examples/signin.html" target="_blank">--%>
<%--                <img src="assets/img/examples/bootstrap-example-signin.png" alt="">--%>
<%--              </a>--%>
<%--              <h4>登录框</h4>--%>
<%--              <p>基本的登录表单，使用到了自定义的较大的表单组件和灵活的布局。</p>--%>
<%--            </li>--%>
<%----%>
<%--            <li class="span3">--%>
<%--              <a class="thumbnail" href="examples/sticky-footer.html" target="_blank">--%>
<%--                <img src="assets/img/examples/bootstrap-example-sticky-footer.png" alt="">--%>
<%--              </a>--%>
<%--              <h4>页脚固定在底部</h4>--%>
<%--              <p>将固定高度的页脚钉在页面可视区域的最下方。</p>--%>
<%--            </li>--%>
<%--            <li class="span3">--%>
<%--              <a class="thumbnail" href="examples/carousel.html" target="_blank">--%>
<%--                <img src="assets/img/examples/bootstrap-example-carousel.png" alt="">--%>
<%--              </a>--%>
<%--              <h4>大屏轮播</h4>--%>
<%--              <p>一个更具交互、突出轮播的基本营销类网站。</p>--%>
<%--            </li>--%>
<%--          </ul>--%>
<%----%>




<%--										<div class="span4">--%>
<%--											<h2>--%>
<%--												Heading--%>
<%--											</h2>--%>
<%--											<p>--%>
<%--												Donec id elit non mi porta gravida at eget metus. Fusce--%>
<%--												dapibus, tellus ac cursus commodo, tortor mauris condimentum--%>
<%--												nibh, ut fermentum massa justo sit amet risus. Etiam porta--%>
<%--												sem malesuada magna mollis euismod. Donec sed odio dui.--%>
<%--											</p>--%>
<%--											<p>--%>
<%--												<a class="btn" href="#">View details »</a>--%>
<%--											</p>--%>
<%--										</div>--%>
<%--										<div class="span4">--%>
<%--											<h2>--%>
<%--												Heading--%>
<%--											</h2>--%>
<%--											<p>--%>
<%--												Donec sed odio dui. Cras justo odio, dapibus ac facilisis--%>
<%--												in, egestas eget quam. Vestibulum id ligula porta felis--%>
<%--												euismod semper. Fusce dapibus, tellus ac cursus commodo,--%>
<%--												tortor mauris condimentum nibh, ut fermentum massa.--%>
<%--											</p>--%>
<%--											<p>--%>
<%--												<a class="btn" href="#">View details »</a>--%>
<%--											</p>--%>
<%--										</div>--%>
									</div>
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
















		<!-- BEGIN CONTAINER -->
		<%--	<div class="container">--%>
		<%--	<div class="Features-grids">--%>
		<%--	<s:iterator value="goodsList" var="goods">--%>
		<%--		<div class="col-md-3 Feature-grid">--%>
		<%--			<a href="shop/shop!detail?goods.id=<s:property value="#goods.id"/>"><img src="images1/img<s:property value="#goods.imgsrc"/>.jpg" class="img-responsive zoom-img" alt=""></a>--%>
		<%--			<h4><s:property value="#goods.name"/></h4>--%>
		<%--				<h5>￥<s:property value="#goods.price"/>.0元</h5>--%>
		<%--			<p><s:property value="#goods.content"/><a href="order/order!queryAll">查询所有订单</a></p>--%>
		<%--		</div>--%>
		<%--	</s:iterator>--%>
		<%--	<div class="clearfix"></div>--%>
		<%--	</div>--%>

		<%--   <!-- END CONTAINER -->--%>
		<%--   <!-- begin footer -->--%>
		<%--   <jsp:include page="/WEB-INF/pages/common/footer.jsp"></jsp:include>--%>
		<!-- end footer -->
		<!-- BEGIN JAVASCRIPTS -->
		<!-- Load javascripts at bottom, this will reduce page load time -->
		<%--   <script src="js/jquery-1.8.3.min.js"></script>--%>
		<%--   <script src="assets/bootstrap/js/bootstrap.min.js"></script>--%>
		<%--   <script src="js/jquery.blockui.js"></script>--%>
		<%--   <!-- ie8 fixes -->--%>
		<%--   <!--[if lt IE 9]>--%>
		<%--   <script src="js/excanvas.js"></script>--%>
		<%--   <script src="js/respond.js"></script>--%>
		<%--   <![endif]-->--%>
		<%--   <script type="text/javascript" src="assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>--%>
		<%--   <script type="text/javascript" src="assets/uniform/jquery.uniform.min.js"></script>--%>
		<%--   <script src="js/scripts.js"></script>--%>
		<%--   <script>--%>
		<%--      jQuery(document).ready(function() {       --%>
		<%--         // initiate layout and plugins--%>
		<%--         App.init();--%>
		<%--      });--%>
		<%--   </script>--%>
		<%--   <!-- END JAVASCRIPTS -->   --%>
	</body>
	<!-- END BODY -->
</html>
