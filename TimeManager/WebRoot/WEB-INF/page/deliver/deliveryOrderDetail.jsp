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
	<title>物流订单详情</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />	
	<script src="js/jquery-1.8.2.min.js"></script>
	<script src="js/jquery-easyui/jquery.easyui.min.js"></script>
	<script src="js/jquery.validate.js"></script>
	<script src="js/jquery.metadata.js"></script>
	<script src="js/additional-methods.min.js"></script>
	<script src="js/common.validate.js"></script>
	<link href="js/jquery/css/jquery.ui.all.css"  rel="stylesheet"  type="text/css" />	
	<link href="js/jquery-easyui/easyui.css"  rel="stylesheet"  type="text/css" />	
	<script src="js/jquery/jquery.ui.core.js"></script>
	<script src="js/jquery/jquery.ui.widget.js"></script>
	<script src="js/jquery/jquery.ui.mouse.js"></script>
	<script src="js/jquery/jquery.ui.button.js"></script>
	<script src="js/jquery/jquery.ui.draggable.js"></script>
	<script src="js/jquery/jquery.ui.position.js"></script>
	<script src="js/jquery/jquery.ui.resizable.js"></script>
	<script src="js/jquery/jquery.ui.dialog.js"></script>
	<script src="js/jquery/jquery.ui.tabs.js"></script>
	<script src="js/datepicker/WdatePicker.js"></script>
	<script src="js/common.js"></script>
	<script type="text/javascript">
		$().ready(function() {
			
		});
		
		function modifyOrder(obj) {
			$(obj).hide();
			$('#hidenName').val($('#orderNoId').html());
			$('#confirmId').show();
			$('#orderNoId').hide();
			$('#hiddenOrderNoId').show();
		}
		
		function confirmOrder(obj) {
			$(obj).hide();
			$('#modifyId').show();
			$('#orderNoId').html($('#hidenName').val());
			$('#orderNoId').show();
			$('#hiddenOrderNoId').hide();
		}
		
	</script> 
	<style>
		.title-bar {
			text-align: left;
			border: 1px solid #9DB5E3;
			margin:0 auto;
			padding-top:10px;
			padding-bottom:10px;
			padding-left:10px;
			background-color: #BEBEBE;
		}
	</style>
</head>
<body   >
	
	<div class="Position">
		当前位置是：基本设置 &gt;&gt; 发货管理 &gt;&gt; 物流订单详情
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<center>
		<div style="width:800px;">
			<div class="title-bar">
				<span>物流编号</span><br/>
				<span>LP00000000000256</span>
			</div>
			<div>
				<table width="100%" id="listTable" class="listTable" style="padding-left:0px;" cellpadding="0" cellspacing="0" >
					<tr>
						<td width="40%" style="text-align: right;padding-right:20px;">发货方式：</td>
						<td width="60%" style="text-align: left;padding-left:20px;">自己联系</td>
					</tr>
					<tr>
						<td width="40%" style="text-align: right;padding-right:20px;">物流公司：</td>
						<td width="60%" style="text-align: left;padding-left:20px;">圆通快递</td>
					</tr>
					<tr>
						<td width="40%" style="text-align: right;padding-right:20px;">官方网址：</td>
						<td width="60%" style="text-align: left;padding-left:20px;">http://www.baidu.com</td>
					</tr>
					<tr>
						<td width="40%" style="text-align: right;padding-right:20px;">运单号码：</td>
						<td width="60%" style="text-align: left;padding-left:20px;"><span id="orderNoId">65500215</span><span id="hiddenOrderNoId" style="display:none;"><input type="text" id="hidenName" style="width:100px;" name="xx"/></span> <span style="margin-left:50px;"><a href="javascript:void(0)" id="modifyId" onclick="modifyOrder(this)" style="color:blue;">修改</a><a href="javascript:void(0)" id="confirmId" onclick="confirmOrder(this)" style="color:blue;display:none;">确认修改</a></span></td>
					</tr>
				</table>
			</div>
			
			<div class="title-bar">
				<span>订单编号</span><br/>
				<span>25500015512</span>
			</div>
			<div>
				<table width="100%" id="listTable" class="listTable" style="padding-left:0px;" cellpadding="0" cellspacing="0" >
					<tr>
						<td width="40%" style="text-align: right;padding-right:20px;">木偶</td>
						<td width="60%" style="text-align: left;padding-left:20px;">50.00 * 1</td>
					</tr>
				</table>
			</div>
			
			<div class="title-bar">
				<span>收货地址</span>
			</div>
			<div>
				<table width="100%" id="listTable" class="listTable" style="padding-left:0px;" cellpadding="0" cellspacing="0" >
					<tr>
						<td width="40%" style="text-align: right;padding-right:20px;">收货信息：</td>
						<td width="60%" style="text-align: left;padding-left:20px;">北京市海淀区xxxxxxxxxxxxx 10090 xx 151xxx</td>
					</tr>
					<tr>
						<td width="40%" style="text-align: right;padding-right:20px;">发货信息：</td>
						<td width="60%" style="text-align: left;padding-left:20px;">北京市海淀区xxxxxxxxxxxxx 10090 xx 151xxx</td>
					</tr>
					<tr>
						<td width="40%" style="text-align: right;padding-right:20px;">退货信息：</td>
						<td width="60%" style="text-align: left;padding-left:20px;">北京市海淀区xxxxxxxxxxxxx 10090 xx 151xxx</td>
					</tr>
				</table>
			</div>
		</div>
	</center>	
	
</body> 
</html>