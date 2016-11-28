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
		<title>徐半仙儿-留言板</title>
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
		<%--		<script src="js/jquery-1.4.2.min.js"></script>--%>
		<link rel="stylesheet" href="kindeditor/themes/default/default.css" />
		<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
		<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
		<script>
		//退出系统登录
		function quit() {
			if(confirm("确定要退出系统吗？")){
				parent.window.location.href="<%=basePath%>system/login!logout";
		}
	}
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="leaveMessageDTO.leaveMessageContent"]',
				{
					newlineTag : this.value,
					resizeType : 1,
					allowPreviewEmoticons : false,
					allowImageUpload : false,
					items : [ 'fontname', 'fontsize', '|', 'forecolor',
							'hilitecolor', 'bold', 'italic', 'underline',
							'removeformat', '|', 'emoticons' ],
					afterChange : function() {
						//定义最大的字数限制
					var maxlimit = 500;
					if (this.count('text') > maxlimit)
						//截取
						editor.html(editor.html().substr(0, maxlimit));
					K('.word_count1').html(this.count());
					K('.word_count2').html(this.count('text'));
				}
				});
	});
	function query(page) {
		var params = {
			"orderProperty" : $("#orderProperty").val(),
			"orderDirection" : $("#orderDirection").val(),
			"leaveMessageDTO.page" : page
		};
		ajaxDataForPage("leavemessage/leavemessage!jsonPageList", params);
	}

	function addSave(dataUrl){
		editor.sync(); 
		var leaveMessageName=$("#leaveMessageName").val();
		var leaveMessageContent=$("#leaveMessageContent").val();
		var params = {
				"leaveMessageDTO.leaveMessageName":leaveMessageName,
				"leaveMessageDTO.leaveMessageContent":leaveMessageContent
			};
		$.ajax( {   
		      url : dataUrl, 
		      data : params,   
		      dataType : "json",
		      cache : false, 
		      type : "POST",
		      error : function(textStatus, errorThrown) {   
		      },   
		      beforeSend : function(){
				},
		      success : function(data, textStatus) {
					$("#leaveMessageName").val("");
					editor.html("");
					query(1);	 
		      }
		  });
	}
</script>
		<script src="js/jquery-1.8.3.min.js"></script>
		<script src="js/common.js"></script>
	</head>
	<!-- END HEAD -->
	<!-- BEGIN BODY -->
	<body class="fixed-top" onload="query(${leaveMessageDTO.page})">
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
					<!-- 留言板开始 -->
					<li class="has-sub active">
						<a href="javascript:;" class=""> <span class="icon-box"><i
								class="icon-comment"></i> </span> 留言板 <span class="arrow"></span> </a>
						<ul class="sub">
							<li class="active">
								<a class="" href="leavemessage/leavemessage!list?columnCode=3">留言板</a>
							</li>
						</ul>
					</li>
					<!-- 留言板结束 -->
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
								<span class="settings"> <span class="text">theme:</span>
									<span class="colors"> <span class="color-default"
										data-style="default"></span> <span class="color-gray"
										data-style="gray"></span> <span class="color-purple"
										data-style="purple"></span> <span class="color-navy-blue"
										data-style="navy-blue"></span> </span> </span>
							</div>
							<!-- END THEME CUSTOMIZER-->
							<!-- BEGIN PAGE TITLE & BREADCRUMB-->
							<h3 class="page-title">
								留言板
								<small>给半仙儿留言吧。</small>
							</h3>
							<ul class="breadcrumb">
								<li>
									<a href="javascript:;"><i class="icon-home"></i>留言板1.0版本</a><span
										class="divider">&nbsp;</span>
								</li>
								<li>
									<a href="javascript:;">留言板</a>
									<span class="divider">&nbsp;</span>
								</li>
								<li>
									<a href="javascript:;">留言板列表</a><span class="divider-last">&nbsp;</span>
								</li>
								<li class="pull-right search-wrap">
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
								<!--打分版，用户可以对当前新闻、游戏或者应用等进行1-5级的评分-->
<div id="SOHUCS" sid="994028591@qq.com"></div>
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
		
	<!-- END BODY -->
	<script charset="utf-8" type="text/javascript" src="http://changyan.sohu.com/upload/changyan.js" ></script>
<script type="text/javascript">
    window._config = { showScore: true };
    window.changyan.api.config({
        appid: 'cys5vNeQO',
        conf: 'prod_722e32af470624f7441b46e1922e6c3c'
    });
</script> 
	</body>

</html>