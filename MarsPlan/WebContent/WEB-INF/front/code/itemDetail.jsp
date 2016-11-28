<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${code.name }</title>
<!-- pageContext.request.contextPath表示绝对路径 -->
<link rel="stylesheet" href="bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap3/css/bootstrap-theme.min.css">
<script src="bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="bootstrap3/js/bootstrap.min.js"></script>
<link rel="Shortcut Icon" href="images/shortcut.png" type="image/x-icon" />
<!-- 百度统计代码 -->
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?12d39e2b2a66cd9ebf5dda986d3253a0";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
</head>
<body style="padding: 20px;">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<!-- 导航条开始 -->
				<nav class="navbar navbar-default navbar-inverse " role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span><span
							class="icon-bar"></span><span class="icon-bar"></span><span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="article/article">徐半仙儿</a>
				</div>

				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="article/article">博客</a></li>
						<li class="active"><a href="code/code">代码库</a></li>
						<li><a href="software/software">软件库</a></li>
					</ul>
				</div>
				</nav>
				<!-- 导航条结束-->
				<!-- 引用开始 -->
				<blockquote>
					<p>stay hungry stay foolish.</p>
					<small>Steve <cite>Jobs</cite></small>
				</blockquote>
				<!-- 引用结束 -->
				<div class="row">
					<!-- 9布局开始 -->
					<div class="col-md-9">
						<div class="row well">
							<div class="col-md-6 thumbnail">
								<img alt="Bootstrap Image Preview img-responsive"
									src="${code.thumbnailPath }" />
							</div>
							<div class="col-md-6">
								<h3>${code.name}</h3>
								<ul class="list-unstyled list-inline">
									<li>
										<p>${code.summary}</p>
									</li>
								</ul>
								<a class="btn btn-success btn-large" href="${code.panHref }"
									target="_blank"><i class="glyphicon glyphicon-download-alt"></i>免费下载</a>
								<a class="btn btn-danger btn-large" href="${code.panHref }"
									target="_blank">源码下载</a>
							</div>
						</div>
						<div class="page-header">
							<h4>代码介绍</h4>
						</div>
						<p>${code.description}</p>
					</div>
					<!-- 										3布局开始 -->
					<!-- 					<div class="col-md-4"> -->
					<!-- 					</div> -->
					<!-- 										3布局结束 -->
				</div>

				<!-- 评论开始 -->
				<div class="row">
					<!-- 9布局开始 -->
					<div class="col-md-9">
						<!-- 评论开始 -->
						<div id="SOHUCS" sid="${code.id}"></div>
						<!-- 评论结束-->

					</div>
					<!-- 					3布局开始 -->
					<!-- 					<div class="col-md-3"> -->
					<!-- 						<h4>其他源码</h4> -->
					<!-- 							<img alt="Bootstrap Thumbnail First" src="images/haima.jpg" /> -->
					<!-- 							<div class="caption"> -->
					<!-- 								<h3>SSH实例Demo</h3> -->
					<!-- 								<p>Spring+Struts2+Hibernate增删改查分页搜索实例练习.</p> -->
					<!-- 								<p> -->
					<!-- 									<a class="btn btn-primary" href="#">获取源码</a> -->
					<!-- 								</p> -->
					<!-- 						</div> -->
					<!-- 					</div> -->
					<!-- 					3布局结束 -->
					<!-- 				</div> -->
					<!-- 评论结束 -->
				</div>
				<!-- 其他说明信息开始 -->
				<div class="jumbotron ">
					<ul class="list-unstyled">
						<li>友情链接申请：994028591(QQ) 站长信箱：994028591#QQ.COM.
							技术交流QQ群:java网站开发(469120527)(未满)
						</li>
						<li>免责声明：本站所有内容来源于互联网。如果本站部分内容侵犯您的权益，请您告知，站长会立即处理。蒙ICP备13002765号-2</li>
						<li>Copyright © 2015-2016 徐半仙儿 | xukaiqiang.com | 代码库 | 个人网站
							| 个人博客
						</li>
						<li>站内所有资源仅供学习与参考，请勿用于商业用途，否则产生的一切后果将由您自己承担！</li>
					</ul>
				</div>
				<!-- 其他说明信息结束 -->
			</div>
		</div>
	</div>
	<script charset="utf-8" type="text/javascript"
		src="http://changyan.sohu.com/upload/changyan.js"></script>
	<script type="text/javascript">
		window._config = {
			showScore : true
		};
		window.changyan.api.config({
			appid : 'cys5vNeQO',
			conf : 'prod_722e32af470624f7441b46e1922e6c3c'
		});
	</script>
</body>
</html>