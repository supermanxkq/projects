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
	<title>支付接口配置</title>
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
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
	
	//查询方法
	function query(page) {
	var psName = $.trim($("#psName").val());
	var payOrgName = $.trim($("#payOrgName").val());
	var status = $.trim($("#status").val());
	var params = {
		"payInterConfDTO.psName" : psName,
        "payInterConfDTO.payOrgName" : payOrgName,
        "payInterConfDTO.status":status,
        "orderProperty" : $("#orderProperty").val(),
        "orderDirection" : $("#orderDirection").val(),
        "payInterConfDTO.page" : page
    }; 
   ajaxData("payInterface/payInterfaceConfig!jsonPageList",params);
}
	
	</script> 
</head>
<body onload="query(${payInterConfDTO.page});">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 支付接口配置
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
	    <div class="Fl">
			<my:permission key='sy-1801-02' value='支付接口配置添加'>
				<input type="button" class="formButton" value="添加" onclick="go('payInterface/payInterfaceConfig!addUI')"/>
			</my:permission>
		</div>
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
			    <td>接口名称:</td>
				<td><s:textfield id="psName" name="payInterConfDTO.psName" cssClass="formInput" maxlength="20" theme="simple" onkeyup="allowEnCnNu(this);" /></td>
				<td>支付机构:</td>
				<td><s:textfield id="payOrgName" name="payInterConfdto.payOrgName" cssClass="formInput" maxlength="20" theme="simple" onkeyup="allowEnCnNu(this);"/></td>
			    <td>状态:</td>
				<td><s:select name="payInterConfDTO.status" id="status"  list="#request.statusValues" listKey="key" listValue="value" headerKey="-1" headerValue="全部"
				 cssClass="formInput" theme="simple"/></td>
				 <td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="9%">接口名称</th>
			<th width="10%">支付机构</th>
			<th width="7%">启用状态</th>
			<th width="8%">接口类型</th>
			<th width="7%"><a name="psRate" class="sort">支付手续费率</a></th>
			<th width="8%">货到付款</th>
			<th width="7%">在线支付</th>
			<th width="8%"><a name="createTime" class="sort">创建时间</a></th>
			<th width="8%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
	    <div class="Fl">
			<my:permission key='sy-1801-02' value='支付接口配置添加'>
				<input type="button" class="formButton" value="添加" onclick="go('payInterface/payInterfaceConfig!addUI')"/>
			</my:permission>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>