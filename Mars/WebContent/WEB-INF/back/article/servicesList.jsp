<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" +

			request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>" />
<title>文章列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
	type="image/x-icon" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.4.2.min.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript">
	/**查询方法*/
	function query(page) {
		var title = $("#title").val();
		var guest = $("#guest").val();
		var params = {
			"articleDTO.title" : title,
			"articleDTO.guest" : guest,
			"orderProperty" : $("#orderProperty").val(),
			"orderDirection" : $("#orderDirection").val(),
			"articleDTO.page" : page
		};
		ajaxData("article/article!jsonPageList", params);
	}
</script>
</head>
<body onload="query(${articleDTO.page})">
	<div class="Position">当前位置是：HOME &gt;&gt;文章管理 &gt;&gt; 文章管理</div>
	<jsp:include page="/WEB-INF/back/share/common.jsp"></jsp:include>
	<div class="search">
		<div class="Fl">
			<input type="button" class="formButton" value="添加"
				onclick="go('article/article!addUI')" />
		</div>
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
				<td>标题:</td>
				<td><s:textfield name="articleDTO.title" id="title"
						cssClass="formInput" theme="simple" maxlength="20" /></td>

				<td>邀请嘉宾:</td>
				<td><s:textfield id="guest" name="articleDTO.guest"
						cssClass="formInput" theme="simple" maxlength="20" /></td>
				<td><input type="button" class="formButton" onclick="query();"
					value="查询" /></td>
			</tr>
		</table>
	</div>


	<table width="96%" id="listTable" class="listTable" cellpadding="0"
		cellspacing="0">
		<tr>
			<th width="3%">序号</th>
			<th width="5%"><a name="title" class="sort">标题</a></th>
			<th width="8%">邀请嘉宾</th>
			<th width="8%">参与人</th>
			<th width="8%">相关媒体</th>
			<th width="8%">活动地址</th>
			<th width="8%">状态</th>
			<th width="8%"><a name="actionDate" class="sort">活动时间</a></th>
			<th width="8%"><a name="contentDate" class="sort">发表时间</a></th>
			<th width="8%">编辑者</th>
			<th width="8%">操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false" />
		</div>
		<div class="Fl">
			<input type="button" class="formButton" value="添加"
				onclick="go('article/article!addUI')" />
		</div>
	</div>
</body>
</html>