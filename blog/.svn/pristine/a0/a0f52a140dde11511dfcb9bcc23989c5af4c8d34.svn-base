<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back/share/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<!-- saved from url=(0052)http://v2.bootcss.com/examples/starter-template.html -->
<html lang="zh-cn">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<title>导航菜单</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<base href="<%=basePath%>">
		<!-- 引入js文件 -->
		<script src="js/jquery-1.8.3.min.js"></script>
		<!-- Le styles -->
		<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>

		<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->

	</head>

	<body>

		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container-fluid">
					<button type="button" class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="brand" href="article/article!list?columnCode=1">徐半仙儿</a>
					<div class="nav-collapse collapse">
						<ul class="nav">
							<li id="1">
								<a href="article/article!list?columnCode=1">博客</a>
							</li>
							<li id="2">
								<a href="link/link!list?columnCode=2">好456</a>
							</li>
							<li id="3">
								<a href="leavemessage/leavemessage!list?columnCode=3">留言板</a>
							</li>
							<%--              <li><a href="#">音乐盒</a></li>--%>
							<%--              <li><a href="#">社区论坛</a></li>--%>
							<li id="4">
								<a href="help/help!toHelpPage?columnCode=4">友情捐助</a>
							</li>
							<li id="5">
								<a href="timeLine/timeLine!list?columnCode=5">时间轴</a>
							</li>
							<li id="6">
								<a href="shop/shop!toShopPage?columnCode=6">商城</a>
							</li>
							<li id="7">
								<a href="gallery/gallery!queryAll?columnCode=7">相册</a>
							</li>
							<li id="8">
								<a href="chat/chat!toChatRoom?columnCode=8">聊天室</a>
							</li>
						</ul>
						<div class="top-nav ">
						<ul class="nav pull-right top-menu">
							<s:if test="#session.user_session!=null">
									<!-- BEGIN USER LOGIN DROPDOWN -->
									<li class="dropdown">
										<a href="#" class="dropdown-toggle" data-toggle="dropdown">
											<img src="img/avatar1_small.jpg" alt=""> <span
											class="username">欢迎，${user_session.userName} </span> <b
											class="caret"></b> </a>
										<ul class="dropdown-menu">
											<li>
												<a href="goods/goods!list"><i class="icon-user"></i> 商品管理</a>
											</li>
											<li>
												<a href="order/order!queryAll"><i class="icon-tasks"></i>订单管理</a>
											</li>
												<li>
												<a href="gallery/gallery!list"><i class="icon-camera-retro"></i>相册管理</a>
											</li>
											<li class="divider"></li>
											<li>
												<a onclick="quit();" style="cursor: pointer;"
													class="navbar-link"><i class="icon-key"></i> 退出</a>
											</li>
										</ul>
									</li>
							</s:if>
							<s:else>
									<li>
										<a href="system/login!loginPage" class="navbar-link">登录</a>
									</li>
							</s:else>
							<li>
							<a target="_blank"
								href="http://shang.qq.com/wpa/qunwpa?idkey=b1f73cf6deb2eb1535e41f3e7f92db7f3ee8482b9cb8a6781d3bcb50a01aa53b"><img
									border="0" src="http://pub.idqqimg.com/wpa/images/group.png"
									alt="JAVA网站网络技术群" title="JAVA网站网络技术群">
							</a>
									</li>
										</ul>
							<!-- END USER LOGIN DROPDOWN -->
							<!-- END TOP NAVIGATION MENU -->
						</div>
					</div>
					<!--/.nav-collapse -->
				</div>
			</div>
		</div>
		<input type="hidden" id="columnCode" value="${columnCode}">
		<script type="text/javascript">
	//退出系统登录
	function quit() {
		if(confirm("确定要退出系统吗？")){
			parent.window.location.href="<%=basePath%>system/login!logout";
	}
}
	//导航栏active切换
<%--	$(document).ready(function () {--%>
<%--		$('ul.nav > li').click(function (e) {--%>
<%--		e.preventDefault();--%>
<%--		$('ul.nav > li').removeClass('active');--%>
<%--		$(this).addClass('active');--%>
<%--		});--%>
<%--		});--%>
<%--$('ul.nav  li a').click(function(e) {--%>
<%--	  var $this = $(this);--%>
<%--	  if (!$this.hasClass('active')) {--%>
<%--	    $this.addClass('active');--%>
<%--	  }--%>
<%--	  e.preventDefault();--%>
<%--	});--%>

$(function(){
  //获取栏目的值
 var columnCode = $("#columnCode").val();
 var len = $("ul.nav  li").size();//获取span标签的个数
 var arr = [];
 for(var index = 0; index < len; index++){//创建一个数字数组
     arr[index] = index;
  }
   $.each(arr, function(i){//循环得到不同的id的值
      var idValue = $("ul.nav  li").eq(i).attr("id");
      if(idValue != ''&&idValue==columnCode){
          $("ul.nav  li").eq(i).addClass('active');

      }
  }); 
 
});

    </script>
	</body>
</html>