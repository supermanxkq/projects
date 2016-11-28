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
<link rel="Shortcut Icon" href="images/shortcut.png" type="image/x-icon" />
<script src="js/code/codestyle.js"></script>
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
					<a class="navbar-brand" href="article/article">徐半仙儿</a>
				</div>

				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="article/article!list">博客</a></li>
						<li class="active"><a href="code/code!list">代码库</a></li>
						<!-- 						<li><a href="#">留言板</a></li> -->
						<li><a href="software/software!list">软件库</a></li>

						<!-- <!-- 						<li><a href="#">商城</a></li> -->
						<!-- 						<li><a href="#">时间轴</a></li> -->
						<!-- 						<li><a href="#">音乐盒</a></li> -->
						<!-- 						<li><a href="#">相册</a></li> -->
						<!-- 						<li><a href="#">友情链接</a></li> -->
						<!-- 						<li class="dropdown"><a href="#" class="dropdown-toggle" -->
						<!-- 							data-toggle="dropdown">资源下载<strong class="caret"></strong></a> -->
						<!-- 							<ul class="dropdown-menu"> -->
						<!-- 								<li><a href="#">Java书籍</a></li> -->
						<!-- 								<li><a href="#">Mac</a></li> -->
						<!-- 								<li><a href="#">Windows环境搭建</a></li> -->
						<!-- 								<li><a href="#">帮助文档</a></li> -->
						<!-- 								<li class="divider"></li> -->
						<!-- 								<li><a href="#">Separated link</a></li> -->
						<!-- 								<li class="divider"></li> -->
						<!-- 								<li><a href="#">One more separated link</a></li> -->
						<!-- 							</ul></li> -->
						<!-- 						<li><a href="#">关于我</a></li> -->
					</ul>
					<form class="navbar-form navbar-left" role="search" onsubmit="return false;">
						<div class="form-group">
							<input type="text" style="width:400px;" class="form-control" placeholder="搜索博客、软件、代码......"id="keyWord" />
						</div>
						<button type="button" class="btn btn-success"  id="lucene" onclick="luceneSearch();">搜索</button>
					</form>
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
					<div class="col-md-9" id="codelist"></div>
					<!-- 分类开始 -->
					<div class="col-md-3 "
						style="border: 1px solid #ddd; border-radius: 4px; margin-bottom: 20px;"
						id="codestylelist"></div>
				</div>
				<!-- 分页开始 -->
				<!-- 关键字开始 -->
				<!-- 				<div class="col-md-3-fluid thumbnail"> -->
				<!-- 					<h4>代码关键字</h4> -->
				<!-- 					<button type="button" class="btn btn-link">JavaWeb项目</button> -->
				<!-- 					<button type="button" class="btn btn-link">小例子</button> -->
				<!-- 					<button type="button" class="btn btn-link">Jquery</button> -->
				<!-- 					<button type="button" class="btn btn-link">BootStrap</button> -->
				<!-- 					<button type="button" class="btn btn-link">Struts2</button> -->
				<!-- 					<button type="button" class="btn btn-link">JavaScript</button> -->
				<!-- 					<button type="button" class="btn btn-link">Lucene</button> -->
				<!-- 					<button type="button" class="btn btn-link">Ajax</button> -->
				<!-- 					<button type="button" class="btn btn-link">SSI框架</button> -->
				<!-- 					<button type="button" class="btn btn-link">SpringMVC</button> -->
				<!-- 					<button type="button" class="btn btn-link">Java基础</button> -->
				<!-- 					<button type="button" class="btn btn-link">HTML</button> -->
				<!-- 					<button type="button" class="btn btn-link">EasyUI</button> -->
				<!-- 					<button type="button" class="btn btn-link">Maven</button> -->
				<!-- 				</div> -->
				<!-- 			</div> -->
				<!-- 分页开始 -->
				<nav style="text-align: center" id="pageNav"> </nav>
				<!-- 分页结束 -->
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
</body>
</html>