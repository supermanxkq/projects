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
	/**查询所有资源*/
	function query(page) {
		var title = $.trim($("#title").val());
		var blogTypes = $("#blogTypes").val();
		var params = {
			"articleDTO.title" : title,
			"articleDTO.blogType.blogTypeId" : blogTypes,
			"orderProperty" : $("#orderProperty").val(),
			"orderDirection" : $("#orderDirection").val(),
			"articleDTO.page" : page
		};
		ajaxData("article/article!jsonPageList", params);
	}
	/**根据类型查找查询所有资源*/
	function queryByTypes(page,blogTypeId) {
			$("ul.sub li").removeClass("active");
			$("#"+blogTypeId+"s").addClass("active");
		var params = {
			"articleDTO.blogType.blogTypeId" : blogTypeId,
			"orderProperty" : $("#orderProperty").val(),
			"orderDirection" : $("#orderDirection").val(),
			"articleDTO.page" : page
		};
		ajaxData("article/article!jsonPageList", params);
	}
	function queryByDate(page,date) {
<%--			$("ul.sub li").removeClass("active");--%>
<%--			$("#"+blogTypeId+"s").addClass("active");--%>
		var params = {
			"articleDTO.contentDateString" : date,
			"orderProperty" : $("#orderProperty").val(),
			"orderDirection" : $("#orderDirection").val(),
			"articleDTO.page" : page
		};
		ajaxData("article/article!jsonPageList", params);
	}
</script>
		<!-- 内部js结束-->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY query(${articleDTO.page})-->
<body class="fixed-top" onload="queryByTypes(${articleDTO.page},-1)">
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
                                <span class="color-gray" data-style="gray"></span>
                                <span class="color-default" data-style="default"></span>
                                <span class="color-purple" data-style="purple"></span>
                                <span class="color-navy-blue" data-style="navy-blue"></span>
                            </span>
                        </span>
                   </div>
                   <!-- END THEME CUSTOMIZER-->
                  <!-- BEGIN PAGE TITLE & BREADCRUMB-->
							<h3 class="page-title">
								资源分享
								<small>项目源码、书籍、开发经验、软件、教程分享！</small>
							</h3>
							<ul class="breadcrumb">
								<li>
									<a href="article/article!list?columnCode=1"><i class="icon-home"></i>
									</a><span class="divider">&nbsp;</span>
								</li>
								<li>
									<a href="article/article!list?columnCode=1">资源分享</a><span class="divider-last">&nbsp;</span>
								</li>
								<li class="pull-right search-wrap">
	                                	<div class="search-input-area">
                                    <s:textfield id="title" name="articleDTO.title" placeholder="文章搜索" cssClass="search-query" maxlength="20" onkeyup="query(1);"></s:textfield> 
                                        <i class="icon-search"></i>
                                    </div>
								</li>
								<s:if test="#session.user_session!=null">
	                                	<a onclick=go('article/article!addUI') class="btn btn-success" style="color:#fff;"><i class='icon-plus'></i>添加新资源</a>
								</s:if>
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
	                           <h4><i class="icon-globe"></i>资源列表</h4>
	                           <span class="tools">
	                           <a href="javascript:;" class="icon-chevron-down"></a>
	                           <a href="javascript:;" class="icon-remove"></a>
	                           </span>                    
                        	</div>
								<div class="widget-body" id="content">
								</div>
								<div id="pageNav" class="pagination pagination-centered">
										<s:property value="pageHTML" escape="false" />
									</div>
									<br/><br/>
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