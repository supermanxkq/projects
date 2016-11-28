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
		//修改订单
		function modifyOrder(obj) {
			$(obj).hide();
			$('#hidenName').val($('#orderNoId').html());
			$('#confirmId').show();
			$('#orderNoId').hide();
			$('#hiddenOrderNoId').show();
		}
		//确认订单
		function confirmOrder(obj) {
			if($.trim($('#hidenName').val()) == "") {
				alert("物流单号不能为空！");
				return;
			}
			if($.trim($('#hidenName').val()).length > 15) {
				alert('物流单号长度不能超过15个字！');
				return;
			}
			$.post("base/delivery!ajaxModifyWayBillNo",
				{"deliveryDTO.orderId":"${deliveryOrdersDTO.orderId}",
				 "deliveryDTO.waybillNo":$.trim($('#hidenName').val())},
				function(result){
		    		if(result.success) {
		    			$(obj).hide();
						$('#modifyId').show();
						$('#orderNoId').html($('#hidenName').val());
						$('#orderNoId').show();
						$('#hiddenOrderNoId').hide();
		    		} else {
		    			alert('修改物流单号失败！');
		    		}
		    	},
		    	'json'
			);
		}
		//检查订单编号
		function checkNumWord(obj) {
	    	obj.value = obj.value.replace(/[^\d\w]/g,"");
	    	obj.value = obj.value.replace(/[_]/g,"");
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
		当前位置是：基本信息 &gt;&gt; 订单管理 &gt;&gt; 发货管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<center>
		<div style="width:800px;">
			<div class="title-bar">
				<span>物流信息</span><br/>
			</div>
			<div>
				<table width="100%" id="listTable" class="listTable" style="padding-left:0px;" cellpadding="0" cellspacing="0" >
					<tr>
						<td width="40%" style="text-align: right;padding-right:20px;">发货方式：</td>
						<td width="60%" style="text-align: left;padding-left:20px;">
							<s:if test="#request.deliveryOrdersDTO.deliveryWay == '0'">
								无需物流
							</s:if>
							<s:else>
								自己联系
							</s:else>
						</td>
					</tr>
					<tr>
						<td width="40%" style="text-align: right;padding-right:20px;">物流公司：</td>
						<td width="60%" style="text-align: left;padding-left:20px;">${deliveryOrdersDTO.deliveryComp}</td>
					</tr>
					<tr>
						<td width="40%" style="text-align: right;padding-right:20px;">官方网址：</td>
						<td width="60%" style="text-align: left;padding-left:20px;"><a target="_blank" href="${deliveryOrdersDTO.compUrl}">${deliveryOrdersDTO.compUrl}</a></td>
					</tr>
					<tr>
						<td width="40%" style="text-align: right;padding-right:20px;">运单号码：</td>
						<td width="60%" style="text-align: left;padding-left:20px;"><span id="orderNoId">${deliveryOrdersDTO.waybillNo}</span><span id="hiddenOrderNoId" style="display:none;"><input type="text" id="hidenName" onkeyup="checkNumWord(this)" style="width:100px;" value="${deliveryOrdersDTO.waybillNo}" name="xx"/></span> 
							<s:if test="#request.deliveryOrdersDTO.waybillNo !=null && #request.deliveryOrdersDTO.waybillNo !=''">
								<span style="margin-left:50px;"><a href="javascript:void(0)" id="modifyId" onclick="modifyOrder(this)" style="color:blue;">修改</a><a href="javascript:void(0)" id="confirmId" onclick="confirmOrder(this)" style="color:blue;display:none;">确认修改</a></span>
							</s:if>
						</td>
					</tr>
				</table>
			</div>
			
			<div class="title-bar">
				<span>宝贝信息</span><br/>
			</div>
			<div>
				<table width="100%" id="listTable" class="listTable" style="padding-left:0px;" cellpadding="0" cellspacing="0" >
					<tr>
						<td width="40%" style="text-align: right;padding-right:20px;">${deliveryOrdersDTO.goodsName}</td>
						<td width="60%" style="text-align: left;padding-left:20px;">${deliveryOrdersDTO.price2Scale} * ${deliveryOrdersDTO.qty}</td>
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
						<td width="60%" style="text-align: left;padding-left:20px;">${deliveryOrdersDTO.address}</td>
					</tr>
					<tr>
						<td width="40%" style="text-align: right;padding-right:20px;">发货信息：</td>
						<td width="60%" style="text-align: left;padding-left:20px;">${deliveryOrdersDTO.sendAddress}</td>
					</tr>
					<tr>
						<td width="40%" style="text-align: right;padding-right:20px;">退货信息：</td>
						<td width="60%" style="text-align: left;padding-left:20px;">${deliveryOrdersDTO.returnAddress}</td>
					</tr>
				</table>
			</div>
		</div>
		<div>
			<s:if test="#request.close == null || #requset.close==''">
				<form action="base/delivery!list" method="POST">
					<input type="submit" class="formButton" value="返回"/>
					<input type="hidden" name="deliveryDTO.status" value="${deliveryOrdersDTO.status }"/>
					<input type="hidden" name="deliveryDTO.activeTab" value="${deliveryOrdersDTO.status }"/>
				</form>
			</s:if>
			<s:else>
				<input type="button" class="formButton" value="关闭" onclick="javascript:window.close()"/>
			</s:else>
		</div>
	</center>	
	
</body> 
</html>