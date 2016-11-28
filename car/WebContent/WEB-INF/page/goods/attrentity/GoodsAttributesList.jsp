<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
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
			var attrName = $("#attrName").find("option:selected").text(); 
			var params = {
		        "goodsAttributeDTO.attrName": attrName,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "goodsAttributeDTO.page" : page
		    }; 
		   ajaxData("base/attrEntity!jsonPageList",params);
		}
		
	</script>
	</head>
	<body onload="query(${goodsAttributeDTO.page});">
		<div class="Position">
			当前位置是：基本设置 &gt;&gt; 商品管理 &gt;&gt; 商品属性值管理
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<div class="search">
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
					<th align="right">
						商品属性名称:
					</th>
					<td>
						<s:select headerKey="-1" headerValue="全部" id="attrName"
							list="#request.goodsAttributeName" 
							listKey="key" listValue="value" theme="simple"
							name="goodsAttributeDTO.attrName"></s:select>
					</td>
					<td>
						<input type="button" id="button1" class="formButton" value="查 询"
							onclick="query();" />
					</td>
				</tr>
			</table>
		</div>
		<form action="base/goods" id="batchForm" method="POST">
			<input type="hidden" name="method" id="methodId" />
			<table width="96%" id="listTable" class="listTable" cellpadding="0"
				cellspacing="0">
				<tr>
					<th width="10%">
						<a name="attrId" class="sort">商品属性编号</a>
					</th>
					<th width="10%">
						<a name="attrName" class="sort">商品属性名称</a>
					</th>
					<th width="10%">
						<a name="isEn" class="sort">是否枚举</a>
					</th>
					<th width="5%">
						<a>相关操作</a>
					</th>
				</tr>
			</table>
		</form>
		<div class="listBottom">
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
		</div>
	</body>
</html>