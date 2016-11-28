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
<html lang="zh-cn">
	<!--<![endif]-->
	<!-- BEGIN HEAD -->
	<head>
		<meta charset="utf-8" />
		<title>徐半仙儿-注册新用户</title>
		<base href="<%=basePath%>" />
		<meta content="width=device-width, initial-scale=1.0" name="viewport" />
		<meta content="" name="description" />
		<meta content="" name="author" />
		<link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="assets/bootstrap/css/bootstrap-responsive.min.css"
			rel="stylesheet" />
		<link href="assets/bootstrap/css/bootstrap-fileupload.css"
			rel="stylesheet" />
		<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
		<link href="css/style.css" rel="stylesheet" />
		<link href="css/style_responsive.css" rel="stylesheet" />

		<link href="assets/fancybox/source/jquery.fancybox.css"
			rel="stylesheet" />
		<link rel="stylesheet" type="text/css"
			href="assets/uniform/css/uniform.default.css" />
		<script src="js/jquery-1.4.2.min.js"></script>
		<script src="js/common.js"></script>
		<script src="js/datepicker/WdatePicker.js"></script>
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
		<script src="js/pubValiPattern.js"></script>
		<script src="js/pubValidate.js"></script>
		<script type="text/javascript">
		/**用户名是否正确*/
		var userNameFlag = false;
		/**验证码是否正确*/
		var codeFlag=false;
		//验证用户名
		function checkUserName(){
			var userName=$("#userNameInput").val();
			if(userName==""){
				$("#userNameDiv").removeClass("success");
				$("#userNameDiv").addClass("error");
				$("#userNameMsg").html("起个名字吧！");
				userNameFlag=false;
			}else{
				//检查用户名是否存在
	        	checkUserNameExsit();	
			}
			}
		//验证验证码
		function  validateCode(){
			//获取输入框中的验证码
			var code=$("#rand").val();
			var dataUrl='system/user!validateCode';
			if(code==""){
				 $("#codeMsg").html("");
					$("#codeDiv").addClass("error");
					$("#codeMsg").html("请输入验证码！");
				}else{
			//Ajax获取Session的验证码
		var params = {
				"rand":code
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
				      if(data==1){
				    	  $("#codeMsg").html("");
							$("#codeDiv").addClass("success");
							$("#codeMsg").html("验证码正确！");
							codeFlag=true;
					  	}else{
					  		 $("#codeMsg").html("");
							$("#codeDiv").addClass("error");
							$("#codeMsg").html("验证码错误！");
						 }
		      }
		  });
		}
		}
		//重新产生验证码
		function changeValidateCode(obj) {
			// 获取当前的时间作为参数，无具体意义
			var timenow = new Date().getTime();
			// 每次请求需要一个不同的参数，否则可能会返回同样的验证码
			// 这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。
			obj.src = "system/randAction?d=" + timenow;
		}
		//检查用户名是否存在
		function checkUserNameExsit(){
			//获取输入框中的用户名
			var userName=$("#userNameInput").val();
			var dataUrl='system/user!validateUserName';
			//Ajax获取Session的验证码
		var params = {
				"userDTO.userName":userName
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
				      if(data==0){
				    	  $("#userNameDiv").removeClass("error");
				    	  $("#userNameMsg").html("");
							$("#userNameDiv").addClass("success");
							$("#userNameMsg").html("用户名可以使用！");
							userNameFlag=true;
					  	}else{
					  		$("#userNameDiv").removeClass("success");
						  	userNameFlag=false;
					  		 $("#userNameMsg").html("");
							$("#userNameDiv").addClass("error");
							$("#userNameMsg").html("用户名已被注册！");
						 }
		      }
		  });
		}
		//验证全部
		function check(){
			//验证用户名
			checkUserName();
			//验证验证码
			validateCode();
			if(codeFlag&&userNameFlag){
				return true;
			}else{
				return false;
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
					<!-- 资源分享开始 -->
					<li class="has-sub">
						<a href="javascript:;" class=""> <span class="icon-box"><i
								class="icon-folder-open"></i> </span> 资源 <span class="arrow"></span> </a>
						<ul class="sub">
							<li>
								<a class="" href="article/article!list?columnCode=1">资源分享</a>
							</li>
							<s:if test="#session.user_session!=null">
								<li class="active">
									<a class="" href="blogtype/blogtype!list">资源分类</a>
								</li>
							</s:if>
						</ul>
					</li>
					<!-- 资源分享结束 -->
					<!-- 留言板开始 -->
					<li class="has-sub">
						<a href="javascript:;" class=""> <span class="icon-box"><i
								class="icon-comment"></i> </span> 留言板 <span class="arrow"></span> </a>
						<ul class="sub">
							<li class="active">
								<a class="" href="leavemessage/leavemessage!list?columnCode=3">留言板</a>
							</li>
						</ul>
					</li>
					<!-- 留言板结束 -->
					<!-- 友情捐助开始-->
					<li class="has-sub">
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
								注册
								<small>欢迎注册半仙儿的网站。</small>
							</h3>
							<ul class="breadcrumb">
								<li>
									<a href="article/article!list?columnCode=1"><i class="icon-home"></i> </a><span
										class="divider">&nbsp;</span>
								</li>
								<li>
									<a href="system/user!addUI">注册新用户</a>
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
										<i class="icon-globe"></i>注册新用户
									</h4>
									<span class="tools"> <a href="javascript:;"
										class="icon-chevron-down"></a> <a href="javascript:;"
										class="icon-remove"></a> </span>
								</div>
								<div class="widget-body form">
									<!-- BEGIN FORM-->
									<div class="widget-body form">
										<!-- BEGIN FORM-->
										<form action="system/user!addSave" class="form-horizontal"
											method="post" onsubmit="return check();">
											<s:hidden name="method" id="method" />
											<div class="control-group" id="userNameDiv">
												<label class="control-label" for="inputError">
													用户名：
												</label>
												<div class="controls">
													<input type="email" name="userDTO.userName"  autofocus=""
														class="span6" id="userNameInput" placeholder="请输入用户名(邮箱)"
														value="${sessionScope.userDTO.userName}"
														onblur="checkUserName();" required=""></input>
													<span class="help-inline" id="userNameMsg"></span>
												</div>
											</div>
											<div class="control-group" id="codeDiv">
												<label class="control-label" for="inputError">
													验证码：
												</label>
												<div class="controls">
													<input class="span6" type="text" id="rand" name="rand"
														onblur="validateCode();" placeholder="验证码" required=""/>
													<img src="system/randAction" id="CreateCheckCode"
														onclick="changeValidateCode(this)" title="点击图片刷新验证码" />
													<span class="help-inline" id="codeMsg"></span>
												</div>
												<div class="form-actions">
													<button type="submit" class="btn btn-success">
														注册
													</button>
													<button type="button" class="btn" onclick="go('article/article!list?columnCode=1')">
														取消
													</button>
												</div>
											</div>
										</form>
										<!-- END FORM-->
									</div>
									<!-- END FORM-->
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
		
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"32"},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"32"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
	</body>
	<!-- END BODY -->
</html>