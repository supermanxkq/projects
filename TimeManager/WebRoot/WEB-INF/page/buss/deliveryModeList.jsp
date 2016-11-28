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
	<title>配送方式管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />	
	<script src="js/jquery-1.4.2.min.js"></script>
	<link href="js/jquery/css/jquery.ui.all.css"  rel="stylesheet"  type="text/css" />	
	<script src="js/jquery/jquery.ui.core.js"></script>
	<script src="js/jquery/jquery.ui.widget.js"></script>
	<script src="js/jquery/jquery.ui.mouse.js"></script>
	<script src="js/jquery/jquery.ui.button.js"></script>
	<script src="js/jquery/jquery.ui.draggable.js"></script>
	<script src="js/jquery/jquery.ui.position.js"></script>
	<script src="js/jquery/jquery.ui.resizable.js"></script>
	<script src="js/jquery/jquery.ui.dialog.js"></script>
	<script src="js/common.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
		
		//查询方法
		function query(page) {
			var dmName = $.trim($("#dmName").val());
			var logisticsComName =$.trim($("#logisticsComName").val());
			var status =$.trim($("#status").val());
           
			var params = {
				"deliveryModeDTO.dmName" : dmName,
				"deliveryModeDTO.logisticsComName" : logisticsComName,
				"deliveryModeDTO.status" : status,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "deliveryModeDTO.page" : page
		    }; 
		   ajaxData("buss/deliverymode!jsonPageList",params);
		}
	</script> 
</head>
<body onload="query(${deliveryModeDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 业务参数 &gt;&gt; 配送方式设置
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">		<div class="Fl">
			<my:permission key='sy-6600-01' value='配送方式新增'>
				<input type="button" class="formButton" value="添加" onclick="go('buss/deliverymode!addUI')"/>
			</my:permission>
		</div>
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
			    <td>配送方式名称:</td>
				<td><s:textfield id="dmName" name="deliveryModeDTO.dmName" cssClass="formInput" maxlength="15" theme="simple"/></td>
				<td>物流单位名称:</td>
				<td><s:textfield id="logisticsComName" name="deliveryModeDTO.logisticsComName" cssClass="formInput" maxlength="30" theme="simple"/></td>
				<td>状态:</td>
				<td><s:select id="status" name="deliveryModeDTO.status" list="#request.status" headerKey="-1" headerValue="全部" listKey="key" listValue="value"  theme="simple" /></td>
				<td width="22px">&nbsp;</td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0">
		<tr>
			<th width="3%">序号</th>
			<th width="8%">方式名称</th>
			<th width="10%">物流单位</th>
			<th width="10%">启用状态</th>
			<th width="8%"><a name="enterCost" class="sort" >报价费用</a></th>
			<th width="8%">货到付款</th>
			<th width="8%">当前用户数</th>
			<th width="5%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<my:permission key='sy-6600-01' value='配送方式新增'>
				<input type="button" class="formButton" value="添加" onclick="go('buss/deliverymode!addUI')"/>
			</my:permission>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>