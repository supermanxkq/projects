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
<script src="js/article/article.js"></script>
<link rel="Shortcut Icon" href="images/shortcut.png" type="image/x-icon" />
<script src="js/article/articletype.js"></script>
<!-- 百度统计代码 -->
<script>
// var _hmt = _hmt || [];
// (function() {
//   var hm = document.createElement("script");
//   hm.src = "//hm.baidu.com/hm.js?12d39e2b2a66cd9ebf5dda986d3253a0";
//   var s = document.getElementsByTagName("script")[0]; 
//   s.parentNode.insertBefore(hm, s);
// })();
</script>

<script type="text/javascript">
function query(page) {
	var title=$("#title").val();
	var typeId=$("#").val();
	var params = {
		"articleDTO.typeId":typeId,
		"articleDTO.title":title,
		"articleDTO.page" : page
	};
	ajaxData("article/article!jsonPageListF", params);
}

function queryByTypeId(page,typeId) {
	var params = {
		"articleDTO.typeId":typeId,
		"articleDTO.page" : page
	};
	ajaxData("article/article!jsonPageListF", params);
}
	</script>
</head>
<body style="padding: 20px;" onload="query(${articleDTO.page})">
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
						<li class="active"><a href="article/article!list">博客</a></li>
						<li><a href="code/code!list">代码库</a></li>
						<li><a href="software/software!list">软件库</a></li>
					</ul>
					<form class="navbar-form navbar-left" role="search" onsubmit="return false;">
						<div class="form-group">
							<input type="text" style="width: 400px;" class="form-control"
								placeholder="搜索博客、软件、代码......" id="keyWord" />
						</div>
						<button type="button" class="btn btn-success" id="lucene" onclick="luceneSearch();">搜索</button>
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
					<div class="col-md-9" id="articlelist">
						<!-- 						<div class="media well"> -->
						<!-- 							<a href="#" class="pull-left"><img -->
						<!-- 								alt="Bootstrap Media Preview" src="http://lorempixel.com/64/64/" -->
						<!-- 								class="media-object" /></a> -->
						<!-- 							<div class="media-body"> -->
						<!-- 								<h4 class="media-heading">Nested media heading</h4> -->
						<!-- 								Cras sit amet nibh libero, in gravida nulla. Nulla vel metus -->
						<!-- 								scelerisque ante sollicitudin commodo. Cras purus odio, -->
						<!-- 								vestibulum in vulputate at, tempus viverra turpis. -->
						<!-- 							</div> -->
						<!-- 							<p class="text-right text-danger">2016-08-16 23:23:23  阅读(20)  评论(30)</p> -->
						<!-- 						</div> -->
						<!-- 						<div class="media well"> -->
						<!-- 							<a href="#" class="pull-left"><img -->
						<!-- 								alt="Bootstrap Media Preview" src="http://lorempixel.com/64/64/" -->
						<!-- 								class="media-object" /></a> -->
						<!-- 							<div class="media-body"> -->
						<!-- 								<h4 class="media-heading"><a href="#">Nested media heading</a></h4> -->
						<!-- 								Cras sit amet nibh libero, in gravida nulla. Nulla vel metus -->
						<!-- 								scelerisque ante sollicitudin commodo. Cras purus odio, -->
						<!-- 								vestibulum in vulputate at, tempus viverra turpis. -->
						<!-- 							</div> -->
						<!-- 							<p class="text-right text-danger">2016-08-16 23:23:23  阅读(20)  评论(30)</p> -->
						<!-- 						</div> -->
						<!-- 						<div class="media well"> -->
						<!-- 							<a href="#" class="pull-left"><img -->
						<!-- 								alt="Bootstrap Media Preview" src="http://lorempixel.com/64/64/" -->
						<!-- 								class="media-object" /></a> -->
						<!-- 							<div class="media-body"> -->
						<!-- 								<h4 class="media-heading">Nested media heading</h4> -->
						<!-- 								Cras sit amet nibh libero, in gravida nulla. Nulla vel metus -->
						<!-- 								scelerisque ante sollicitudin commodo. Cras purus odio, -->
						<!-- 								vestibulum in vulputate at, tempus viverra turpis. -->
						<!-- 							</div> -->
						<!-- 							<p class="text-right text-danger">2016-08-16 23:23:23  阅读(20)  评论(30)</p> -->
						<!-- 						</div> -->
					</div>
					<!-- 分类开始 -->
					<div class="col-md-3 "
						style="border: 1px solid #ddd; border-radius: 4px; margin-bottom: 20px;"
						id="articletypelist"></div>
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
							技术交流QQ群:java网站开发(469120527)(未满)</li>
						<li>免责声明：本站所有内容来源于互联网。如果本站部分内容侵犯您的权益，请您告知，站长会立即处理。蒙ICP备13002765号-2</li>
						<li>Copyright © 2015-2016 徐半仙儿 | xukaiqiang.com | 代码库 | 个人网站
							| 个人博客</li>
						<li>站内所有资源仅供学习与参考，请勿用于商业用途，否则产生的一切后果将由您自己承担！
					</ul>
				</div>
				<!-- 其他说明信息结束 -->
			</div>
		</div>
	</div>
</body>
</html>