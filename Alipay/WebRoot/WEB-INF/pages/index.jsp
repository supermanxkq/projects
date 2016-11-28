<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
	<!--<![endif]-->
	<!-- BEGIN HEAD -->
	<head>
		<meta charset="utf-8" />
		<title>捐助</title>
		<base href="<%=basePath%>">
		<meta content="width=device-width, initial-scale=1.0" name="viewport" />
		<meta content="" name="description" />
		<meta content="" name="author" />
	</head>
	<body class="fixed-top">
	<h1>捐助实例</h1>
		<form name=alipayment action="help/help!createHelp" method=post
			target="_blank" class="form-horizontal">
			<input type="hidden" size="30" name="orderDTO.out_trade_no"
				value="${out_trade_no}" />
			<input type="hidden" size="30" name="orderDTO.subject" value="捐助" />
			捐助金额：
			<input name="orderDTO.price" type="text" placeholder="请输入捐助金额......">
			<button class="btn btn-success " type="submit">
				捐助
			</button>
		</form>
		<hr>
		<h1>购物实例</h1>
	<img src="images/a.png" width="400px" height="200px">
		<form name=alipayment action="help/help!createHelp2" method=post
			target="_blank" class="form-horizontal">
			<input type="hidden" size="30" name="orderDTO.out_trade_no"
				value="${out_trade_no}" />
			<input type="hidden" size="30" name="orderDTO.subject" value="捐助" />
			<input name="orderDTO.price" type="text" value="0.01">
			<button class="btn btn-success " type="submit">
				立即购买
			</button>
		</form>
	</body>
</html>
