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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="bootstrap3/css/font-awesome.min.css">
<script
	src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
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
					<a class="navbar-brand" href="#">徐半仙儿</a>
				</div>

				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="#">博客</a></li>
						<li class="active"><a href="#">代码库</a></li>
						<li><a href="#">留言板</a></li>
						<li><a href="#">商城</a></li>
						<li><a href="#">时间轴</a></li>
						<li><a href="#">音乐盒</a></li>
						<li><a href="#">相册</a></li>
						<li><a href="#">友情链接</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">资源下载<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="#">Java书籍</a></li>
								<li><a href="#">Mac</a></li>
								<li><a href="#">Windows环境搭建</a></li>
								<li><a href="#">帮助文档</a></li>
								<li class="divider"></li>
								<li><a href="#">Separated link</a></li>
								<li class="divider"></li>
								<li><a href="#">One more separated link</a></li>
							</ul></li>
						<li><a href="#">关于我</a></li>
					</ul>
					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="代码名称" />
						</div>
						<button type="submit" class="btn btn-default">提交</button>
					</form>
					<!-- 					<ul class="nav navbar-nav navbar-right"> -->
					<!-- 						<li><a href="#">Link</a></li> -->
					<!-- 						<li class="dropdown"><a href="#" class="dropdown-toggle" -->
					<!-- 							data-toggle="dropdown">Dropdown<strong class="caret"></strong></a> -->
					<!-- 							<ul class="dropdown-menu"> -->
					<!-- 								<li><a href="#">Action</a></li> -->
					<!-- 								<li><a href="#">Another action</a></li> -->
					<!-- 								<li><a href="#">Something else here</a></li> -->
					<!-- 								<li class="divider"></li> -->
					<!-- 								<li><a href="#">Separated link</a></li> -->
					<!-- 							</ul></li> -->
					<!-- 					</ul> -->
				</div>
				</nav>
				<!-- 导航条结束-->
				<!-- 引用开始 -->
				<blockquote>
					<p>Stay Hungry,Stay Fulish.</p>
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
								<h3 class="text-center text-success">${code.name}</h3>
								<ul class="list-unstyled list-inline">
									<li><span class="glyphicon glyphicon-eye-open"
										style="cursor: pointer;">阅读次数：1000次</span></li>
									<li><span class="glyphicon glyphicon-thumbs-up"
										style="cursor: pointer;">喜欢：500次</span></li>
									<li><span class="glyphicon glyphicon-thumbs-down"
										style="cursor: pointer;">喜欢：500次</span></li>
									<li><span class="glyphicon glyphicon-tags"
										style="cursor: pointer;">关键字：代码 项目</span></li>
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
					<!-- 					3布局开始 -->
					<div class="col-md-3">
						<h4>其他源码</h4>
						<div class="thumbnail">
							<img alt="Bootstrap Thumbnail First" src="images/haima.jpg" />
							<div class="caption">
								<h3>SSH实例Demo</h3>
								<p>Spring+Struts2+Hibernate增删改查分页搜索实例练习.</p>
								<p>
									<a class="btn btn-primary" href="#">获取源码</a>
								</p>
							</div>
						</div>
							<div class="thumbnail">
									<img alt="Bootstrap Thumbnail First" 
										src="images/1449928627939.jpg" />
									<div class="caption">
										<h3>SSH实例Demo</h3>
										<p>Spring+Struts2+Hibernate增删改查分页搜索实例练习.</p>
										<p>
											<a class="btn btn-primary" href="itemDetail.jsp">获取源码</a>
										</p>
									</div>
								</div>
					</div>
					<!-- 					3布局结束 -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>