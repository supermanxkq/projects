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
	<title>订单详情</title>
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
	<script src="js/jquery/jquery.ui.tabs.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValidate.js"></script>
    <script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
		
	$(function() {
		var $tabs = $("#tabs").tabs();	
		$('#tabs-2').click(function() { // 绑定单击事件
		    $tabs.tabs('select', 1);
		    return true;
		});		
	});
		
		 
	</script> 
</head>
<body >
	
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<br><br>
	<fieldset  style='margin-left:10px;margin-top:0px;padding-bottom:20px;padding-left:20px;'>
		<div>
		<s:if test="ordersDTO.isClose==1">
		<font color="red" size="+1">当前订单状态：交易关闭</font>
		</s:if>
		<s:else>
		<font color="red" size="+1">当前订单状态：
		<s:property value="#request.orderStatusStr"/>
		</font><br>
		</s:else>
		</div>
	</fieldset>
	
	<div class="search" id="tabs">
	     <ul>
		   <li><a href="#tabs-1">订单信息</a></li>
		  
	     </ul>
	 
	<div class="search" id="tabs-1">
	 <div>
	   收货地址：${ordersDTO.address}<br>
	   买家留言：${ordersDTO.leaveWord}<br>
	 </div>
	 <br><br>
	 <font color="red">买家信息</font> 
	   <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		   <tr>
		  		<th align="right" width="15%"><strong>昵称：</th>
		        <td width="15%" >${ordersDTO.memName}</td>
		        <th align="right" width="15%">真实姓名：</th>
		        <td width="15%">${ordersDTO.memRealName}</td>
		        <th align="right" width="15%">邮件：</th>
		        <td width="20%">${ordersDTO.mail}</td>
			</tr>
		     <tr>
		  		<th align="right" width="15%"><strong>地址：</th>
		        <td width="15%" >${ordersDTO.address}</td>
		        <th align="right" width="15%">联系电话：</th>
		        <td width="15%">${ordersDTO.memTele}</td>
		        <th align="right" width="15%">支付账号：</th>
		        <td width="20%">${ordersDTO.payAccount}</td>
			</tr>
			</table>
			<br><br>
			
			<font color="red">订单信息</font> 
			 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
		  		<th align="right" width="15%"><strong>订单编号：</th>
		        <td width="15%" >${ordersDTO.orderId}</td>
		        <th align="right" width="15%">支付宝交易号：</th>
		        <td width="15%">${ordersDTO.payTradeId}</td>
		        <th align="right" width="15%">成交时间：</th>
		        <td width="20%">${ordersDTO.orderTimeStr}</td>
			</tr>
		   	
	 	</table>
	 	<br><br><br>
	 	<table width="100%" id="listTable" class="listTable" cellpadding="0" cellspacing="0">
		<tr>
			
			<th width="20%">宝贝</th>
			
			<th width="10%">数量</th>
			<th width="10%">单价(元)</th>
			<th width="6%">原价</th>
			
		</tr>
		<s:if test="#request.orderGoodList.size > 0">
			<s:iterator value="#request.orderGoodList" var="orderGood">
		<tr>
		<td>${orderGood.goodsName }</td>	
		<td>${orderGood.qty }</td>
		<td>${orderGood.unitPrice }</td>
		<td>${orderGood.price }</td>
		</tr>
		</s:iterator>
		</s:if>
	</table>
	<div align="left">实收款：<s:property value="ordersDTO.paidAmt"/>&nbsp;(快递：<s:property value="ordersDTO.postAmt"/>)</div>
</div>
	 
		
		
	 	
		
	
	
</body> 
</html>