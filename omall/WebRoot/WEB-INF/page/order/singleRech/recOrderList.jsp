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
	<title>充值管理</title>
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
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
		function query(page){
			var recOrderId = $("#recOrderId").val();
			var cardNo = $("#cardNo").val();
			var orderAmt = $("#orderAmt").val();
			var params = {
					"rechargeDTO.recOrderId" : recOrderId,
			        "rechargeDTO.cardNo" : cardNo,
			        "rechargeDTO.orderAmt" : orderAmt,
			        "orderProperty" : $("#orderProperty").val(),
			        "orderDirection" : $("#orderDirection").val(),
			        "rechargeDTO.page" : page
			    }; 
			   ajaxData("order/recharge!jsonPageList",params);
			}
	</script> 
</head>
<body onload="query(${rechargeDTO.page});">
	
	<div class="Position">
		当前位置是：HOME &gt;&gt; 充值管理 &gt;&gt; 单卡充值管理
	</div>
	<jsp:include page="/WEB-INF/page/order/singleRech/rechDetialList.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>订单编号:</td>
				<td><s:textfield id="recOrderId" name="rechargeDTO.recOrderId" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>充值卡号:</td>
				<td>
					<s:textfield id="cardNo" name="rechargeDTO.cardNo" cssClass="formInput" maxlength="20" theme="simple"/>
				</td>
				<td>充值金额:</td>
				<td>
					<s:textfield id="orderAmt" name="rechargeDTO.orderAmt" cssClass="formInput" maxlength="20" theme="simple" onkeyup = "replaceToNum(this)"/>
				</td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr> 
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%"><a name="recOrderId" class="sort">订单编号</a></th>
			<th width="10%"><a name="orderAmt" class="sort">充值金额</a></th>
			<th width="10%"><a name="realityAmt" class="sort">实际金额</a></th>
			<th width="8%"><a name="cardNo" class="sort">充值卡号</a></th>
			<th width="8%"><a name="createTime" class="sort">充值时间</a></th>
			<th width="8%"><a name="operatorName" class="sort">操作人</a></th>
			<th width="8%"><a >相关操作</a></th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<my:permission key='sy-4202-02' value='充值订单添加'>
				<input type="button" class="formButton" value="添加" onclick="go('order/recharge!addUI')"/>
			</my:permission>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>