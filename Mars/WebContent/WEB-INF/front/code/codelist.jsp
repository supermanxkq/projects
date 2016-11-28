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
<title>徐半仙儿</title>
<link rel="stylesheet" href="bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap3/css/bootstrap-theme.min.css">
<script src="bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="bootstrap3/js/bootstrap.min.js"></script>
<script src="js/code/code.js"></script>
<script src="js/code/codestyle.js"></script>
<script type="text/javascript">
function query(page) {
	var name=$("#name").val();
	var typeId=$("#").val();
	var params = {
		"codeDTO.typeId":typeId,
		"codeDTO.name":name,
		"codeDTO.page" : page
	};
	ajaxData("code/code!jsonPageList", params);
}

function queryByTypeId(page,typeId) {
	var params = {
		"codeDTO.typeId":typeId,
		"codeDTO.page" : page
	};
	ajaxData("code/code!jsonPageList", params);
}
	</script>
</head>
<body style="padding: 20px;" onload="query(${codeDTO.page})">
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
							<input type="text" class="form-control" placeholder="代码名称"
								name="codeDTO.name" id="name" />
						</div>
						<button type="button" class="btn btn-default" onclick="query(1);">提交</button>
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
					<div class="col-md-9" id="codelist"></div>
					<!-- 分类开始 -->
					<div class="col-md-3 "
						style="border: 1px solid #ddd; border-radius: 4px; margin-bottom: 20px;"
						id="codestylelist"></div>
				</div>
				<!-- 分页开始 -->
				<!-- 关键字开始 -->
				<div class="col-md-3-fluid thumbnail">
					<h4>代码关键字</h4>
					<button type="button" class="btn btn-link">JavaWeb项目</button>
					<button type="button" class="btn btn-link">小例子</button>
					<button type="button" class="btn btn-link">Jquery</button>
					<button type="button" class="btn btn-link">BootStrap</button>
					<button type="button" class="btn btn-link">Struts2</button>
					<button type="button" class="btn btn-link">JavaScript</button>
					<button type="button" class="btn btn-link">Lucene</button>
					<button type="button" class="btn btn-link">Ajax</button>
					<button type="button" class="btn btn-link">SSI框架</button>
					<button type="button" class="btn btn-link">SpringMVC</button>
					<button type="button" class="btn btn-link">Java基础</button>
					<button type="button" class="btn btn-link">HTML</button>
					<button type="button" class="btn btn-link">EasyUI</button>
					<button type="button" class="btn btn-link">Maven</button>
				</div>
			</div>
			<!-- 分页开始 -->
			<nav style="text-align: center">
			<ul class="pagination pagination-lg" id="pageNav">
				<!-- 				<li><a href="#">共100页</a></li> -->
				<!-- 				<li><a href="#">上一页</a></li> -->
				<!-- 				<li><a href="#">1</a></li> -->
				<!-- 				<li><a href="#">2</a></li> -->
			</ul>
			</nav>
			<!-- 分页结束 -->
			<!-- 其他说明信息开始 -->
			<div class="jumbotron well">
				<h2>Hello, world!</h2>
				<p>This is a template for a simple marketing or informational
					website. It includes a large callout called the hero unit and three
					supporting pieces of content. Use it as a starting point to create
					something more unique.</p>
				<p>
					<a class="btn btn-primary btn-large" href="#">Learn more</a>
				</p>
			</div>
			<!-- 其他说明信息结束 -->
		</div>
	</div>
</body>
</html>