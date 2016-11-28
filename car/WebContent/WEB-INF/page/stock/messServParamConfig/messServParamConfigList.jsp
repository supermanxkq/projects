<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
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
		<title>短信服务器配置</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
			type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.4.2.min.js"></script>
		<link href="js/jquery/css/jquery.ui.all.css" rel="stylesheet"
			type="text/css" />
		<script src="js/jquery/jquery.ui.core.js"></script>
		<script src="js/jquery/jquery.ui.widget.js"></script>
		<script src="js/jquery/jquery.ui.mouse.js"></script>
		<script src="js/jquery/jquery.ui.button.js"></script>
		<script src="js/jquery/jquery.ui.draggable.js"></script>
		<script src="js/jquery/jquery.ui.position.js"></script>
		<script src="js/jquery/jquery.ui.resizable.js"></script>
		<script src="js/jquery/jquery.ui.dialog.js"></script>
		<script src="js/jquery/jquery.ui.tabs.js"></script>
		<script src="js/common.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script src="js/datepicker/WdatePicker.js"></script>
		<script type="text/javascript">
	$(function() {
		var $tabs = $("#tabs").tabs();
		$('#tabs-2').click(function() { // 绑定单击事件
					$tabs.tabs('select', 1);
					return true;
				});
	});
	/**查询方法*/
	function query(page) {
		var status = $("#status").val();
		var serviceId = $("#serviceId").val();
		var params = {
		"messServParamConfigDTO.status" : status,
			"messServParamConfigDTO.serviceId" : serviceId,
			"orderProperty" : $("#orderProperty").val(),
			"orderDirection" : $("#orderDirection").val(),
			"messServParamConfigDTO.page" : page
		};
		ajaxData("stock/messServParamConfig!jsonPageList", params);
	}
</script>
	</head>
	<body onload="query(${messServParamConfigDTO.page})">
		<div class="Position">
			当前位置是：HOME &gt;&gt;运营支持 &gt;&gt; 短信服务器配置
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<div class="search">
			<div class="Fl">
				<my:permission key='sy-6701-01' value='短信服务商添加'>
					<input type="button" class="formButton" value="添加"
						onclick="go('stock/messServParamConfig!addUI')" />
				</my:permission>
			</div>
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						状态:
					</td>
					<td>
						<s:select id="status" name="messServParamConfigDTO.status"
						list="#request.serverStatus" listKey="key" listValue="value"
						cssClass="formSelect"  theme="simple" />
					</td>
					<td>
						服务商:
					</td>
					<td>
						<s:select name="messServParamConfigDTO.serviceId" id="serviceId"
							list="#request.severNameValue" headerKey="-1" headerValue="全部"
							listKey="key" listValue="value" cssClass="formSelect"
							theme="simple" />
					</td>
					<td>
						<input type="button" class="formButton" onclick="query();"
							value="查询" />
					</td>
				</tr>
			</table>
		</div>


		<table width="96%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0" >
			<tr>
				<th width="3%">
					序号
				</th>
				<th width="5%">
					<a name="mspId" class="sort">编号</a>
				</th>
				<th width="8%">
					<a name="serviceId" class="sort">服务商名称</a>
				</th>
				<th width="8%">
					通道类型
				</th>
				<th width="8%">
					账户号
				</th>
				<th width="8%">
					状态
				</th>
				<th width="8%">
					操作
				</th>
			</tr>
		</table>
		<div class="listBottom">
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
			<div class="Fl">
				<my:permission key='sy-6701-01' value='短信服务商添加'>
					<input type="button" class="formButton" value="添加"
						onclick="go('stock/messServParamConfig!addUI')" />
				</my:permission>
			</div>
		</div>
	</body>
</html>