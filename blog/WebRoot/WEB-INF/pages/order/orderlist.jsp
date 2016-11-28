<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/WEB-INF/back/share/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'orderlist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <table cellpadding="0" cellspacing="0" bordercolor="green" width="100%" border="1px">
  	<thead>
  		<tr>
  			<td>商户订单号</td>
  			<td>订单名称</td>
  			<td>付款金额</td>
  			<td>订单状态</td>
  			<td>操作</td>
  		</tr>
  	</thead>
  	<s:iterator value="orderList" var="order">
  			<tr>
  				<td><s:property value="#order.out_trade_no"/></td>
  				<td><s:property value="#order.subject"/></td>
  				<td><s:property value="#order.price"/></td>
  				<td>
  				<s:if test="#order.status==0">
  						等待买家付款
  				</s:if>
  				<s:elseif test="#order.status==1">
  					买家已付款，等待卖家发货
  				</s:elseif>
  				<s:elseif test="#order.status==2">
  					卖家已发货，等待买家确认
  				</s:elseif>
  				<s:elseif test="#order.status==3">
  					交易成功结束
  				</s:elseif>
  				<s:else>
  					交易中途关闭（已结束，未成功完成）
  				</s:else> </td>
  				<td><s:if test="#order.status==0"><a href="#">付款</a>
  					</s:if>
  					<s:elseif test="#order.status==1"><a href="shop/shop!sendGoodsShop?order.out_trade_no=<s:property value="#order.out_trade_no"/>">发货</a></s:elseif>
  					<s:elseif test="#order.status==2"><a href="https://www.alipay.com/">支付宝确认收货</a></s:elseif>
  				</td>
  			</tr>
  	</s:iterator>
  	</table>
  </body>
</html>
