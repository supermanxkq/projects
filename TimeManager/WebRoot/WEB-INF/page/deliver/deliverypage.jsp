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
	<title>发货页面</title>
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
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript">
		$().ready(function() {
		});
		
		
		function openDialog(containerId) {
			$('#changeDialog').dialog({
				autoOpen: true,
				modal: true,
				width:800,
				buttons: {
				  "取消": function() {
				  	$(this).dialog('close');
				  	alert('hheeh');
				  	$("#changeDialog").dialog("destroy");
				  },
			      "确定": function() {
			      	
			      	if($('#provId').val() == -1) {
			      		alert('请选择所在省！');
			      		return;
			      	}
			      	if($('#cityId').val() == -1) {
			      		alert('请选择所在市！');
			      		return;
			      	}
			      	if($('#areaId').val() == -1) {
			      		alert('请选择所在区域！');
			      		return;
			      	}
			      	
			      	var type = ["isNull"];
		            var postCodeErrorMsg=["邮政编码不能为空!"];
		            var result = showErrorMsg($('#postCodeId')[0],type,postCodeErrorMsg,"postCodeErrorMsg","postCodeErrorMsg");
			      	if(!result)return;
			      	
			      	var type = ["isZipCode"];
		            var postCodeErrorMsg=["邮政编码必须符合规范!"];
		            var result2 = showErrorMsg($('#postCodeId')[0],type,postCodeErrorMsg,"postCodeErrorMsg","postCodeErrorMsg");
            		if(!result2) return;
            		
            		var type = ["isNull"];
		            var addressErrorMsg=["街道地址不能为空!"];
		            var result3 = showErrorMsg($('#addressId')[0],type,addressErrorMsg,"addressErrorMsg","addressErrorMsg");
            		if(!result3) return;
            		
            		var type = ["isNull"];
		            var receiverErrorMsg=["收货人姓名不能为空!"];
		            var result4 = showErrorMsg($('#recevierId')[0],type,receiverErrorMsg,"recevierErrorMsg","recevierErrorMsg");
            		if(!result4) return;
            		
            		if(($('#telphone-qu').val()!="" &&　isNaN($('#telphone-qu').val())) || ($.trim($('#telphone-qu').val())!="" && isNaN($('#telphone-no').val()))|| ( $('#telphone-bran').val() !="" && isNaN($('#telphone-bran').val()))) {
            			alert('电话号码必须是数字！');
            			return;
            		}
            		
            		if($.trim($('#cellphoneId').val())!="" && isNaN($('#cellphoneId').val())) {
            			alert('手机号码必须是数字！');
            			return;
            		}
            		if($.trim($('#cellphoneId').val()) == "" && $.trim($('#telphone-no').val()) == "") {
            			alert('手机号码和电话必须填写一个！');
            			return;
            		}
            		
            		$('#'+containerId).html($("#provId").find("option:selected").text()+" " + $("#cityId").find("option:selected").text() +" " +$("#areaId").find("option:selected").text() +","+$('#postCodeId').val()+","+$('#recevierId').val()+","+($.trim($('#cellphoneId').val()) == ""?($('#telphone-qu').val()+"-"+$('#telphone-no').val()+"-"+$('#telphone-bran').val()):$('#cellphoneId').val()));
            		
			      	$(this).dialog('close');
			      	$("#changeDialog").dialog("destroy");
			      }
			   },
			   close:function () {
			   		$("#changeDialog").dialog("destroy");
			   }
			});
		}
		
		function ajaxLoadData(code,types,selectId) {
			$.get("base/delivery!ajaxLoadData", 
				 {"deliveryDTO.parentCode":code,
				  "deliveryDTO.type":types},
				  function(result){
				  		var htmlStr = "<option value='-1'>请选择...</option>";
				  		$.each(result.dataMap,function(i,item) {
				  			htmlStr += "<option value='"+item.code+"'>"+item.name+"</option>";
				  	 	});
				  	 	$('#'+selectId).html(htmlStr);
				  },
				  'json'
		    );
		}
		
		function changePro(obj){
			if(obj.value != -1) {
				ajaxLoadData(obj.value,1,'cityId');
			} else {
				$('#cityId').val(-1);
				$('#areaId').val(-1);
			}
		}
		
		function changeCity(obj) {
			if(obj.value != -1) {
				ajaxLoadData(obj.value,2,'areaId');
			} else {
				$('#areaId').val(-1);
			}
		}
		
		function postCodeBlur(obj) {
			var type = ["isNull"];
            var postCodeErrorMsg=["邮政编码不能为空!"];
            var result = showErrorMsg(obj,type,postCodeErrorMsg,"postCodeErrorMsg","postCodeErrorMsg");
            if(!result) return;
            var type = ["isZipCode"];
            var postCodeErrorMsg=["邮政编码必须符合规范!"];
            showErrorMsg(obj,type,postCodeErrorMsg,"postCodeErrorMsg","postCodeErrorMsg");
		}
		
		function addressBlur(obj) {
			var type = ["isNull"];
            var postCodeErrorMsg=["街道地址不能为空!"];
            showErrorMsg(obj,type,postCodeErrorMsg,"addressErrorMsg","addressErrorMsg");
		}
		
		function receiveBlur(obj) {
			var type = ["isNull"];
            var postCodeErrorMsg=["收货人姓名不能为空!"];
            showErrorMsg(obj,type,postCodeErrorMsg,"recevierErrorMsg","recevierErrorMsg");
		}
		
	</script> 
	<style>
		.orange-font {
			font-weight: bold;
			color: #FF5809;
			font-size: small;
		}
		.black-font {
			font-weight: bold;
			color: black;
			font-size: small;
		}
		.ordinary-div {
			padding:10px;
			border:1px solid #E0E0E0;
			margin-top:10px;
			line-height:20px;
		}
	</style>
</head>
<body   >
	
	<div class="Position">
		当前位置是：基本设置 &gt;&gt; 发货管理 &gt;&gt; 发货
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<div align="left" style="border:1px solid #E0E0E0;margin-left:50px;margin-right:50px;padding:20px;">
		<div >
			<div>
				<span class="orange-font">第一步</span> <span class="black-font">确认发货/退货信息</span>
			</div>
			<div class="ordinary-div">
				<div>我的发货信息：<span id="myaddress">北京市海淀区xxxxxx 1001 xxx 151xxxx</span> <span style="float:right;"><a href="javascript:void(0)" onclick="openDialog('myaddress')" style="color:blue;">修改我的发货信息</a></span></div>
				<div>我的退货信息：<span id="mybackaddress">北京市海淀区xxxxxx 1001 xxx 151xxxx</span> <span style="float:right;"><a href="javascript:void(0)" onclick="openDialog('mybackaddress')"  style="color:blue;">修改我的退货信息</a></span></div>
			</div>
			
			<div style="margin-top:10px;">
				<span class="orange-font">第二步</span> <span class="black-font">选择物流服务</span>
			</div>
			<div class="ordinary-div">
				<span>
					物流公司：<font color="red">*</font>
					<s:select list="#request.logiList" listKey="logistId" listValue="logistName">
					</s:select>
				</span>
				<span style="margin-left:100px;">物流方式：<font color="red">*</font><input type="radio" name="detype" value=1/>在线下单 &nbsp;<input type="radio" name="detype" value=2 checked="checked"/>自己联系物流 </span>
			</div>
			
			<div style="margin-top:10px;">
				<span class="orange-font">第三步</span> <span class="black-font">确认收货信息并填写运单号</span>
			</div>
			
			
			<div style="border:1px solid #E0E0E0;">
				<div style="padding:10px;line-height: 20px;background-color:#ACD6FF;border:1px solid #BEBEBE;">
					<div>
						收货信息：<span id="receAddress">北京市海淀区xxxx xxx 10015xx </span><span style="float:right;margin-right:20px;"><a href="javascript:void(0)" style="color:blue;" onclick="openDialog('receAddress')">修改收货信息</a></span>
					</div>
					<div>
						运单号码：<input type="text" name="xxx" /> <a href="javascript:void(0)" style="color: blue;">范例</a>
					</div>
				</div>
				
				<s:iterator value="#request.orderList" var="order">
					<div>
						<div style="padding:10px;background-color:#D1E9E9;border:1px solid #BEBEBE;">
							<span>订单编号：${order.orderId}</span>
							<span style="float:right;margin-right:20px;margin-top:0px;">
								<input  type="button" class="formButton"   value="单独发送"/>
							</span>
						</div>
						<div>
							<table class="listTable" style="padding-left:0px;width:100%" cellpadding="0" cellspacing="0" >
								<tr height="80px;">
									<td width="50%" >
										<span style="float:left;margin-left:50px;"><img src="xxxx"/><a href="javascript:void(0)" style="color: blue;">${order.goodsName}</a></span>
										<span style="float: right;margin-right:20px;">${order.price} x ${order.qty}</span>
									</td>
									<td>111</td>
								</tr>
							</table>
						</div>
						<div style="padding:10px;line-height:20px;height: 100px;">
							<span style="vertical-align: top">
								买家选择：快递<br/>
								物流公司：请选择一家物流公司
							</span>
							<span style="margin:400px;vertical-align: top">
								我的备忘录：
								<textarea rows="5" cols="40"></textarea>
							</span>
						</div>
					</div>
				</s:iterator>
				
				<div>
					<div style="padding:10px;background-color:#D1E9E9;border:1px solid #BEBEBE;">
						<span>订单编号：25664566984521</span>
						<span style="float:right;margin-right:20px;margin-top:0px;"><input  type="button" class="formButton"   value="单独发送"/></span>
					</div>
					<div>
						<table class="listTable" style="padding-left:0px;width:100%" cellpadding="0" cellspacing="0" >
							<tr height="80px;">
								<td width="50%" >
									<span style="float:left;margin-left:50px;"><img src="xxxx"/><a href="javascript:void(0)" style="color: blue;">商品名称</a></span>
									<span style="float: right;margin-right:20px;">50.00 x 1</span>
								</td>
								<td>111</td>
							</tr>
						</table>
					</div>
					<div style="padding:10px;line-height:20px;height: 100px;">
						<span style="vertical-align: top">
							买家选择：快递<br/>
							物流公司：请选择一家物流公司
						</span>
						<span style="margin:400px;vertical-align: top">
							我的备忘录：
							<textarea rows="5" cols="40"></textarea>
						</span>
					</div>
				</div>
				
				
				<div style="padding:10px;line-height: 20px;background-color:#ACD6FF;border:1px solid #BEBEBE;">
					<div>
						收货信息：<span>北京市海淀区xxxx xxx 10015xx </span><span style="float:right;margin-right:20px;"><a href="javascript:void(0)" style="color:blue;">修改收货信息</a></span>
					</div>
					<div>
						运单号码：<input type="text" name="xxx" /> <a href="javascript:void(0)" style="color: blue;">范例</a>
					</div>
				</div>
				
				<s:iterator value="#request.orderList" var="order">
					<div>
						<div style="padding:10px;background-color:#D1E9E9;border:1px solid #BEBEBE;">
							<span>订单编号：${order.orderId}</span>
							<span style="float:right;margin-right:20px;margin-top:0px;">
								<input  type="button" class="formButton"   value="单独发送"/>
							</span>
						</div>
						<div>
							<table class="listTable" style="padding-left:0px;width:100%" cellpadding="0" cellspacing="0" >
								<tr height="80px;">
									<td width="50%" >
										<span style="float:left;margin-left:50px;"><img src="xxxx"/><a href="javascript:void(0)" style="color: blue;">${order.goodsName}</a></span>
										<span style="float: right;margin-right:20px;">${order.price} x ${order.qty}</span>
									</td>
									<td>111</td>
								</tr>
							</table>
						</div>
						<div style="padding:10px;line-height:20px;height: 100px;">
							<span style="vertical-align: top">
								买家选择：快递<br/>
								物流公司：请选择一家物流公司
							</span>
							<span style="margin:400px;vertical-align: top">
								我的备忘录：
								<textarea rows="5" cols="40"></textarea>
							</span>
						</div>
					</div>
				</s:iterator>
				
				<div>
					<div style="padding:10px;background-color:#D1E9E9;border:1px solid #BEBEBE;">
						<span>订单编号：25664566984521</span>
						<span style="float:right;margin-right:20px;margin-top:0px;"><input  type="button" class="formButton"   value="单独发送"/></span>
					</div>
					<div>
						<table class="listTable" style="padding-left:0px;width:100%" cellpadding="0" cellspacing="0" >
							<tr height="80px;">
								<td width="50%" >
									<span style="float:left;margin-left:50px;"><img src="xxxx"/><a href="javascript:void(0)" style="color: blue;">商品名称</a></span>
									<span style="float: right;margin-right:20px;">50.00 x 1</span>
								</td>
								<td>111</td>
							</tr>
						</table>
					</div>
					<div style="padding:10px;line-height:20px;height: 100px;">
						<span style="vertical-align: top">
							买家选择：快递<br/>
							物流公司：请选择一家物流公司
						</span>
						<span style="margin:400px;vertical-align: top">
							我的备忘录：
							<textarea rows="5" cols="40"></textarea>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<center>
		<div>
			<input type="submit" class="formButton" value="确认发货"/>
		</div>	
	</center>
	<div id="changeDialog" style="display:none;" title="修改地址">
		<table class="listTable" style="padding-left:0px;" cellpadding="0" cellspacing="0" width="700px;">
			<tr style="background-color:#D1E9E9;">
				<td width="30%">原地址：</td>
				<td width="70%" style="text-align: left;padding-left:20px;">北京 北京市 海淀区 清河效应东路，1000091，名字,手机</td>
			</tr>
			<tr>
				<td width="30%">新地址：</td>
				<td width="70%" style="text-align: left;padding-left:20px;">
					<table border="0">
						<tr>
							<td>选择所在地：</td>
							<td>
								<select id="provId" onchange="changePro(this)" >
									<option value="-1" >请选择..</option>
									<s:iterator value="#request.pList" var="province">
										<option value="${province.code}" >${province.name}</option>
									</s:iterator>
								</select>
								<select id="cityId" onchange="changeCity(this)">
									<option value="-1" >请选择..</option>
								</select>
								<select id="areaId">
									<option value="-1" >请选择..</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>邮政编码：<font color="red">*</font></td>
							<td>
								<input type="text" name="postCode" id="postCodeId" onblur="postCodeBlur(this)"/>
								<label id="postCodeErrorMsg" style="display: none;"></label>
							</td>
						</tr>
						<tr>
							<td>街道地址：<font color="red">*</font></td>
							<td>
								<input type="text" name="address" id="addressId" onblur="addressBlur(this)"/>
								<label id="addressErrorMsg" style="display: none;"></label>
							</td>
						</tr>
						<tr>
							<td>收货人姓名：<font color="red">*</font></td>
							<td>
								<input type="text" name="recevier" id="recevierId" onblur="receiveBlur(this)"/>
								<label id="recevierErrorMsg" style="display: none;"></label>
							</td>
						</tr>
						<tr>
							<td>电话：</td>
							<td>
								<input type="text" name="qu" id="telphone-qu" style="width: 50px;"/>-<input type="text" name="no" id="telphone-no"  style="width: 80px;"/>-<input type="text" name="qu" id="telphone-bran" style="width: 50px;"/>
								<label id="telPhoneErrorMsg" style="display: none;"></label>
							</td>
						</tr>
						<tr>
							<td>手机：</td>
							<td>
								<input type="text" name="cellphone" id="cellphoneId"/> 电话和手机至少填一个
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body> 
</html>