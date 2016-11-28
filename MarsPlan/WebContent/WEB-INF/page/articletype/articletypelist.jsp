<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>" />
<title>博客类型列表</title>
<script src="js/jquery-1.8.2.min.js"></script>
<script src="js/tips/jquery-1.2.6.pack.js"></script>
<script src="js/tips/jquery.messager.js"></script>
<link href="js/tips/animate.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
	type="image/x-icon" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="resources/css/style.css" rel="stylesheet" type="text/css" />
<script src="js/common.js"></script>
<script type="text/javascript">
	// 查询方法
	function query(page) {
		var params = {
			"orderProperty" : $("#orderProperty").val(),
			"orderDirection" : $("#orderDirection").val(),
			"articleTypeDTO.page" : page
		};
		ajaxData("articletype/articletype!jsonPageList", params);
	}
</script>
</head>
<body onload="query(${articleTypeDTO.page});">
	<div class="Position">当前位置是：博客 &gt;&gt; 分类管理</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<s:hidden name="method" id="method" />
	<s:hidden name="articleTypeDTO.id" id="id" />
	<div class="search">
		<div class="Fl">
			<my:permission key="sy-3102-01" value="分类添加">
				<input type="button" class="formButton" value="添加"
						onclick="go('articletype/articletype!addUI')" />
			</my:permission>
		</div>
	</div>
	<table width="96%" id="listTable" class="listTable" cellpadding="0"
		cellspacing="0">
		<tr>
			<th width="6%">编号</th>
			<th width="6%">名称</th>
			<th width="6%">操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<my:permission key="sy-3102-01" value="分类添加">
				<input type="button" class="formButton" value="添加"
					onclick="go('articletype/articletype!addUI')" />
			</my:permission>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false" />
		</div>
	</div>
</body>
</html>