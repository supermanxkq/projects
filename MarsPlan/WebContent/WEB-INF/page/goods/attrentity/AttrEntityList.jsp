<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<base href="<%=basePath%>" />
		<title>商品属性值管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
			type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.8.2.min.js"></script>
		<script src="js/jquery-easyui/jquery.easyui.min.js"></script>
		<script src="js/jquery.validate.js"></script>
		<script src="js/jquery.metadata.js"></script>
		<script src="js/additional-methods.min.js"></script>
		<script src="js/common.validate.js"></script>
		<link href="js/jquery/css/jquery.ui.all.css" rel="stylesheet"
			type="text/css" />
		<link href="js/jquery-easyui/easyui.css" rel="stylesheet"
			type="text/css" />
		<script src="js/jquery/jquery.ui.core.js"></script>
		<script src="js/jquery/jquery.ui.widget.js"></script>
		<script src="js/jquery/jquery.ui.mouse.js"></script>
		<script src="js/jquery/jquery.ui.button.js"></script>
		<script src="js/jquery/jquery.ui.draggable.js"></script>
		<script src="js/jquery/jquery.ui.position.js"></script>
		<script src="js/jquery/jquery.ui.resizable.js"></script>
		<script src="js/jquery/jquery.ui.dialog.js"></script>
		<script src="js/common.js"></script>
		<script type="text/javascript">
	// 查询方法
	function query(page) {
		var attrId = $("#attrId").val();
		var params = {
			"goodsAttributeDTO.attrId" : attrId,
			"orderProperty" : $("#orderProperty").val(),
			"orderDirection" : $("#orderDirection").val(),
			"attrEntityDTO.page" : page
		};
		ajaxData("base/attrEntity!jsonPageListForAttrEntities", params);
	}
</script>
	</head>
	<body onload="query(${attrEntityDTO.page});">

		<div class="Position">
			当前位置是：基本设置 &gt;&gt; 商品管理 &gt;&gt; 商品属性值管理
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<s:hidden name="#request.goodsAttributeDTO.attrId" id="attrId" />
		<input type="hidden" name="method" id="methodId" />
		<div class="search">
			<div class="Fl">
				<my:permission key='sy-1705-02' value='添加属性值'>
					<input type="button" class="formButton" value="添加属性值"
						onclick="go('base/attrEntity!addUI')" />
				</my:permission>
			</div>
		</div>
		<table width="96%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="10%">
					<a name="attrEnId" class="sort">属性值编号</a>
				</th>
				<th width="10%">
					<a name="attrEnName" class="sort">属性枚举名称</a>
				</th>
			</tr>
		</table>
		<div class="listBottom">
			<div class="Fl">
				<my:permission key='sy-1705-02' value='添加属性值'>
					<input type="button" class="formButton" value="添加属性值"
						onclick="go('base/attrEntity!addUI?attrEntityDTO.attrId=<s:property value="#request.goodsAttributeDTO.attrId" />')" />
				</my:permission>
				<input type="button" class="formButton" value="返回"
						onclick="go('base/attrEntity!list')" />
			</div>
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
		</div>
	</body>
</html>