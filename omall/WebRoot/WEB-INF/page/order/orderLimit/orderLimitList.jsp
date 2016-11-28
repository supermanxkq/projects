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
	<title>订单审核额度限定管理</title>
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
	function query(page) {
		var orderLimitId = $("#orderLimitId").val();
		var orderLimitAmt = $("#orderLimitAmt").val();
		var status = $("#status").val();
	//json数据传输
	    var params = {
	    "orderLimitDTO.orderLimitId" :orderLimitId,
	    "orderLimitDTO.orderLimitAmt" :orderLimitAmt,
	    "orderLimitDTO.status":status,
	    "orderProperty" : $("#orderProperty").val(),
	    "orderDirection" : $("#orderDirection").val(),
	    "orderLimitDTO.page" : page
    	}; 
	   ajaxData("order/orderLimit!jsonPageList",params);
	}
	</script> 
</head>
<body onload="query(${orderLimitDTO.page});">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 订单管理 &gt;&gt; 订单审核额度管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>订单限额编号:</td>
				<td><s:textfield id="orderLimitId" name="orderLimitDTO.orderLimitId" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>订单额度:</td>
				<td><s:textfield id="orderLimitAmt" name="orderLimitDTO.orderLimitAmt" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>状态:</td>
				<td><s:select name="orderLimitDTO.status" id="status"  list="#request.statusValues" listKey="key" listValue="value" headerKey="-1" headerValue="全部"
				 cssClass="formInput" theme="simple"/></td>
	        	
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr> 
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%"><a name="orderLimitId" class="sort">审核限额编号</a></th>
			<th width="10%"><a name="orderLName" class="sort">审核限额名称</a></th>
			<th width="10%"><a name="orderLimit" >审核限额</a></th>
			<th width="10%"><a name="deptSign" >所属机构类型</a></th>
			<th width="10%"><a name="orgMerId" >所属机构</a></th>
			<th width="10%"><a name="binId" >卡BIN</a></th>
			<th width="5%"><a name="status" class="sort">状态</a></th>
			<th width="8%"><a name="updateTime" class="sort">更新时间</a></th>
			<th width="5%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
		
				<input type="button" class="formButton" value="添加" onclick="go('order/orderLimit!addUI')"/>
			
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>