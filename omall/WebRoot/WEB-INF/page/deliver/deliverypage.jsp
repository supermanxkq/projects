<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style='overflow:scroll;overflow-x:hidden'>

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
		
		//打开修改收货地址窗口
		function openDialog(containerId,memTelId,receNameId,typeId,orderIds,orderId) {
			document.getElementById('dialogForm').reset();
			$('label').hide();
			$.get("base/delivery!getProvinceAjax", {},
				  function(result){
				  		var htmlStr = "<option value='-1'>请选择...</option>";
				  		$.each(result.pList,function(i,item) {
				  			htmlStr += "<option value='"+item.code+"'>"+item.name+"</option>";
				  	 	});
				  	 	$('#provId').html(htmlStr);
				  },
				  'json'
		    );
			$("#defaultAddress").html($('#'+containerId).html());
			$('#changeDialog').dialog({
				autoOpen: true,
				modal: true,
				width:800,
				buttons: {
				  "取消": function() {
				  	$(this).dialog('close');
				  	
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
            		var values = $("#provId").find("option:selected").text() + $("#cityId").find("option:selected").text()  +$("#areaId").find("option:selected").text()+$('#addressId').val()+"，" +$('#postCodeId').val()+"，"+$('#recevierId').val()+"，";
            		if($.trim($('#telphone-qu').val())!="") {
            			values += $.trim($('#telphone-qu').val())+"-";
            		}
            		if($.trim($('#telphone-no').val())!="") {
            			values += $.trim($('#telphone-no').val());
            			if($.trim($('#telphone-bran').val())!="") {
            				values += "-";
            			}
            		}
            		if($.trim($('#telphone-bran').val())!="") {
            			values += $.trim($('#telphone-bran').val());
            		}
            		values = values +"，"+$('#cellphoneId').val();
            		
            		$('#'+containerId).html(values);
            		$('#'+containerId+"Hidden").val(values);
            		if(null != memTelId) {
	            		if($.trim($('#cellphoneId').val()) == "") {
	            			var htmlStr = "";
	            			if($.trim($('#telphone-qu').val())!="") {
	            				htmlStr += ($.trim($('#telphone-qu').val()));
	            				if($.trim($('#telphone-no').val())!="") {
	            					htmlStr += "-";
	            				}
	            			}
	            			if($.trim($('#telphone-no').val())!="") {
	            				htmlStr += ($.trim($('#telphone-no').val()))
	            				if($.trim($('#telphone-bran').val())!="") {
	            					htmlStr += "-";
	            				}
	            			}
	            			if($.trim($('#telphone-bran').val())!="") {
	            				htmlStr += ($.trim($('#telphone-bran').val()))
	            			}
	            			$('#'+memTelId).val(htmlStr);
	            		} else {
	            			$('#'+memTelId).val($.trim($('#cellphoneId').val()));
	            		}
	            		if($('#'+memTelId).val().length > 32) {
	            			alert('电话号码过长！');
	            			return;
	            		}
	            	}
	            	if(null != receNameId) {
	            		$('#'+receNameId).val($.trim($('#recevierId').val()));
	            	}
	            	// 修改发货地址
	            	if(typeId == 1) {
	            		$.post("base/delivery!ajaxModifyAddress", 
	            			{"deliveryDTO.type":1,
	            			 "deliveryDTO.address":$.trim($('#myaddressHidden').val()),
	            			 "deliveryDTO.orderIdStr":orderIds
	            			 },
							function(result){
					    		if(!result.success) {
					    			alert('修改发货地址失败！');
					    		}
					    	},
					    	'json'
						);
	            	} else if(typeId == 2) {
	            		$.post("base/delivery!ajaxModifyAddress", 
	            			{"deliveryDTO.type":2,
	            			 "deliveryDTO.address":$.trim($('#mybackaddressHidden').val()),
	            			 "deliveryDTO.orderIdStr":orderIds
	            			 },
							function(result){
					    		if(!result.success) {
					    			alert('修改退货地址失败！');
					    		}
					    	},
					    	'json'
						);
	            	} else if(typeId == 3) {
	            		$.post("base/delivery!ajaxModifyAddress", 
	            			{"deliveryDTO.type":3,
	            			 "deliveryDTO.address":values,
	            			 "deliveryDTO.orderId":orderId,
	            			 "deliveryDTO.memName":$.trim($('#recevierId').val()),
	            			 "deliveryDTO.memTele":$.trim($('#'+memTelId).val())
	            			 },
							function(result){
					    		if(!result.success) {
					    			alert('修改收货地址失败！');
					    		}
					    	},
					    	'json'
						);
	            	}
			      	$(this).dialog('close');
			      	$("#changeDialog").dialog("destroy");
			      }
			   },
			   close:function () {
			   		$("#changeDialog").dialog("destroy");
			   }
			});
		}
		
		// 异步加载省市区域
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
		//表单内容校验
		function yesIdo(orderId,yunOrderId,receAddressId,unRememberId,divId,memTeleId,receNameId) {
			if($.trim($("#"+receAddressId+"Hidden").val())=="") {
				alert('请填写收货地址！');
				return;
			}
			if($.trim($("#"+receAddressId+"Hidden").val()).length > 255) {
				alert('收货地址长度不能超过255个字！');
				return;
			}
			if($.trim($("#"+orderId).val())=="" ) {
				alert('请勿修改表单！');
				return;
			}
			if($.trim($('#myaddressHidden').val())=="" || $.trim($('#mybackaddressHidden').val()) == "") {
				alert('请填写发货/退货地址！');
				return;
			}
			if($.trim($('#myaddressHidden').val()).length > 255) {
				alert('发货地址长度不能超过255个字！');
				return;
			}
			if($.trim($('#mybackaddressHidden').val()).length > 255) {
				alert('发货地址长度不能超过255个字！');
				return;
			}
			if($.trim($('#'+unRememberId).val()).length > 150) {
				alert('备注不能超过150字！');
				return;
			}
			if($.trim($('#'+memTeleId).val()) == "") {
				alert('收货人电话必须填写！');
				return;
			}
			if($.trim($('#'+receNameId).val()) == "") {
				alert('收货人姓名必须填写！');
				return;
			}
			if($.trim($('#'+receNameId).val()).length > 15) {
				alert('收货人姓名长度不能超过15个字！');
				return;
			}
			$.post("base/delivery!ajaxSendSingle",
				{"deliveryDTO.orderId":$.trim($("#"+orderId).val()),
				 "deliveryDTO.waybillNo":$.trim($("#"+yunOrderId).val()),
				 "deliveryDTO.address":$.trim($('#'+receAddressId+"Hidden").val()),
				 "deliveryDTO.sendAddress":$.trim($('#myaddressHidden').val()),
				 "deliveryDTO.returnAddress":$.trim($('#mybackaddressHidden').val()),
				 "deliveryDTO.remarks":$.trim($('#'+unRememberId).val()),
				 "deliveryDTO.deliveryWay":$('input[name="deliveryDTO.deliveryWay"]:checked').val(),
				 "deliveryDTO.logiId":$('#logiIdSelect').val(),
				 "deliveryDTO.memTele":$.trim($('#'+memTeleId).val()),
				 "deliveryDTO.memName":$.trim($('#'+receNameId).val())},
				function(result){
		    		if(result.success) {
		    			alert('单独发货成功！');
		    			
		    			$('#'+divId).remove();
		    			
		    			var close = '<%=request.getParameter("close")%>'; 
		    			 if(close=='1'){
			    				
	                        	countDown(2);
			    			}else if($('#J-div-counter').children().length < 1) {
		    				returnBack();
		    			   }
		    		} else {
		    			
		    			
						//alert(window.parent.dialogArguments);
						 //window.parent.dialogArguments.updateBack();
						alert('单独发货失败！');
                       
		    			
		    		}
		    	},
		    	'json'
			);
		}
		//倒计时关闭
		function countDown(secs){ 
			
			if(--secs>0){ 
				
				setTimeout("countDown("+secs+")",1000);
			}else{ 
				window.close();
			}
		}
		//省份值改变事件
		function changePro(obj){
			if(obj.value != -1) {
				ajaxLoadData(obj.value,1,'cityId');
			} else {
				$('#cityId').val(-1);
				$('#areaId').val(-1);
			}
		}
		//城市值改变事件
		function changeCity(obj) {
			if(obj.value != -1) {
				ajaxLoadData(obj.value,2,'areaId');
			} else {
				$('#areaId').val(-1);
			}
		}
		//邮政编码校验
		function postCodeBlur(obj) {
			var type = ["isNull"];
            var postCodeErrorMsg=["邮政编码不能为空!"];
            var result = showErrorMsg(obj,type,postCodeErrorMsg,"postCodeErrorMsg","postCodeErrorMsg");
            if(!result) return;
            var type = ["isZipCode"];
            var postCodeErrorMsg=["邮政编码必须符合规范!"];
            showErrorMsg(obj,type,postCodeErrorMsg,"postCodeErrorMsg","postCodeErrorMsg");
		}
		//地址校验
		function addressBlur(obj) {
			var type = ["isNull"];
            var postCodeErrorMsg=["街道地址不能为空!"];
            showErrorMsg(obj,type,postCodeErrorMsg,"addressErrorMsg","addressErrorMsg");
		}
		//收货人校验
		function receiveBlur(obj) {
			var type = ["isNull"];
            var postCodeErrorMsg=["收货人姓名不能为空!"];
            showErrorMsg(obj,type,postCodeErrorMsg,"recevierErrorMsg","recevierErrorMsg");
		}
	    //提交
		function IamSure() {
			if($.trim($('#myaddressHidden').val()) == "") {
				alert('请修改我的发货信息！');
				return;
			}
			if($.trim($('#mybackaddressHidden').val()) == "") {
				alert('请修改我的退货信息！');
				return;
			}
			var flag = true;
			$.each($('input[name="orderId"]'),function(i,item) {
				if($.trim(item.value)=="") {
					flag=false;
					return;
				}
			});
			if(!flag) {
				alert('请不要篡改页面订单号！');
				return;
			}
			var backup = $('textarea');
			for(i=0;i<backup.length;i++) {
				if(backup[i].value.length > 150) {
					alert('备注长度不能超过150个字！');
					return;
				}
			}
			$('#mainFormId').submit();
			
		}
		//退回
		function returnBack() {
			if($('#returnUrlId').val() != "") {
				$('#otherForm').attr('action',$('#returnUrlId').val());
			}
			$('#otherForm').submit();
		}
		//检查值长度
		function checkNumWord(obj) {
	    	obj.value = obj.value.replace(/[^\d\w]/g,"");
	    	obj.value = obj.value.replace(/[_]/g,"");
	    }
	       function checklength(obj,len) {
	    	if($.trim(obj.value).length > 150) {
	    		pubErrorShow($("#showMsgId"), ["备忘录不能超过150个字!"]);
				return;
			} else {
				$("#showMsgId").hide();
			}
	    }
	    window.name="win_test"
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
			padding-left:10px;
			padding-top:10px;
			padding-bottom:10px;
			border:1px solid #E0E0E0;
			margin-top:10px;
			line-height:20px;
			height:40px;
		}
	</style>
	<base target="_self" />
</head>
<body >
	
	<div class="Position">
		当前位置是：基本信息 &gt;&gt; 订单管理 &gt;&gt; 发货管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<form action="base/delivery!batchConfirmSend" method="POST" id="mainFormId" target="win_test"  >
		<input type="hidden" id="returnUrlId" name="returnUrl" value="${returnUrl}"/>
		<input type="hidden" id="close" name="close" value="${close}"/>
		<div align="left" style="border:1px solid #E0E0E0;margin-left:50px;margin-right:50px;padding:20px;">
			<div >
				<div>
					<span class="orange-font">第一步</span> <span class="black-font">确认发货/退货信息</span>
				</div>
				<div class="ordinary-div">
					<div style="height:20px;vertical-align: top;">
						<div style="width:80%;float:left;">我的发货信息：
							<s:if test="#request.sendAddressOrgin != null && #request.sendAddressOrgin != ''">
								<span id="myaddress">${sendAddressOrgin}</span>
								<input type="hidden" id="myaddressHidden" name="deliveryList.sendAddress" value="${sendAddressOrgin}"/> 
							</s:if>
							<s:else>
								<span id="myaddress">${sendAddress.area}${sendAddress.address}，${sendAddress.zip }，${sendAddress.merName}，${sendAddress.teleNo}，${sendAddress.conPerTeleNo}</span>
								<input type="hidden" id="myaddressHidden" name="deliveryList.sendAddress" value="${sendAddress.area}${sendAddress.address}，${sendAddress.zip }，${sendAddress.merName}，${sendAddress.teleNo} ${sendAddress.conPerTeleNo}"/> 
							</s:else>
						</div>
						<div style="float:right;width:100px;">
							<a href="javascript:void(0)" onclick="openDialog('myaddress',null,null,1,'${orderStr}',null)" style="color:blue;">修改我的发货信息</a>
						</div>
					</div>
					<div style="height:20px;vertical-align: top;">
						<div style="width:80%;float:left;">我的退货信息：
							<s:if test="#request.returnAddressOrgin != null && #request.returnAddressOrgin != ''">
								<span id="mybackaddress">${returnAddressOrgin }</span>
								<input type="hidden" id="mybackaddressHidden" name="deliveryList.returnAddress" value="${returnAddressOrgin }"/> 
							</s:if>
							<s:else>
								<span id="mybackaddress">${returnAddress.area}${returnAddress.address}，${returnAddress.zip }，${returnAddress.merName}，${returnAddress.teleNo}，${sendAddress.conPerTeleNo}</span>
								<input type="hidden" id="mybackaddressHidden" name="deliveryList.returnAddress" value="${returnAddress.area}${returnAddress.address}，${returnAddress.zip }，${returnAddress.merName}，${returnAddress.teleNo} ${sendAddress.conPerTeleNo}"/> 
							</s:else>
						</div>
						<div style="float:right;width:100px;">
							<a href="javascript:void(0)" onclick="openDialog('mybackaddress',null,null,2,'${orderStr}',null)"  style="color:blue;">修改我的退货信息</a>
							</div>
					</div>
				</div>
				
				<div style="margin-top:10px;">
					<span class="orange-font">第二步</span> <span class="black-font">选择物流服务</span>
				</div>
				<div class="ordinary-div">
					<span>
						物流公司：<font color="red">*</font>
						<s:select list="#request.logiList" name="deliveryDTO.logiId" id="logiIdSelect" listKey="logistId" listValue="logistName">
						</s:select>
					</span>
					<span style="margin-left:100px;">物流方式：<font color="red">*</font><input type="radio" name="deliveryDTO.deliveryWay" value="0" />无需物流 &nbsp;<input type="radio" name="deliveryDTO.deliveryWay" value="1" checked="checked"/>自己联系 </span>
				</div>
				
				<div style="margin-top:10px;">
					<span class="orange-font">第三步</span> <span class="black-font">确认收货信息并填写运单号</span>
				</div>
				
				
				<div style="border:1px solid #E0E0E0;">
					<s:if test="#request.orderList.size > 0">
						<s:iterator value="#request.orderList" var="order" status="i">
							<div id="big-div_${i.index}" class="J-div-counter">
								<div style="padding:10px;line-height: 20px;background-color:#ACD6FF;border:1px solid #BEBEBE;">
									<input type="hidden" id="orderIdHidden_${i.index}" name="orderId" value="${order.orderId}">
									<div>
										<div style="float:left;width:80%;">
											收货信息：
											<span id="receAddress_${i.index}">${order.address } </span>
										</div>
										<input type="hidden" id="receAddress_${i.index}Hidden" name="deliveryList.address" value="${order.address }">
										<input type="hidden" id="receName_${i.index}Hidden" name="deliveryList.memName" value="${order.memName }">
										<input type="hidden" id="memTeleId_${i.index}" name="deliveryList.memTele" value="${order.memTele}" >
										
										<div style="float:right;margin-right:20px;width:100px;"><a href="javascript:void(0)" style="color:blue;" onclick="openDialog('receAddress_${i.index}','memTeleId_${i.index}','receName_${i.index}Hidden',3,null,'${order.orderId}')">修改收货信息</a></div>
									</div>
									<br/>
									<div>
										运单号码：<input type="text" id="yunOrderId_${i.index}" maxlength="15" onkeyup='checkNumWord(this)' name="deliveryList.waybillNo" value="" /> 
									</div>
								</div>
								<div>
									<div style="padding:10px;background-color:#D1E9E9;border:1px solid #BEBEBE;">
										<span>订单编号：${order.orderId}</span>
										<input type="hidden" name="deliveryList.orderId" value="${order.orderId}">
										<span style="float:right;margin-right:20px;margin-top:0px;">
											<input  type="button" class="formButton" yun-order-id="yunOrderId_${i.index}" order-id="orderIdHidden_${i.index}" rece-addr="receAddress_${i.index}Hidden" onclick="yesIdo('orderIdHidden_${i.index}','yunOrderId_${i.index}','receAddress_${i.index}','unRemember_${i.index}','big-div_${i.index}','memTeleId_${i.index}','receName_${i.index}Hidden')" value="单独发送"/>
										</span>
									</div>
									<div>
										<table class="listTable" style="padding-left:0px;width:100%" cellpadding="0" cellspacing="0" >
											<tr height="80px;">
												<td width="50%" >
													<span style="float:left;margin-left:50px;"><img src="${order.goodsUrl}" width="80" height="80"/><a href="javascript:void(0)" style="color: blue;">${order.goodsName}</a></span>
													<span style="float: right;margin-right:20px;">${order.price2Scale} x ${order.qty}</span>
												</td>
												<td></td>
											</tr>
										</table>
									</div>
									<div style="padding:10px;line-height:20px;height: 150px;">
										<span style="vertical-align: top">
											买家选择：快递<br/>
											物流公司：请选择一家物流公司
										</span>
										<span style="margin-left:300px;vertical-align: top">
											我的备忘录：
											<textarea rows="5" name="deliveryList.remarks" onblur="checklength(this,150)" id="unRemember_${i.index}" cols="40"></textarea>
										</span>
										<label id="showMsgId" style="display:none;"></label>
									</div>
								</div>
							</div>	
						</s:iterator>
					</s:if>
					<s:else>
						<center>
							<div>没有找到订单数据!</div>
						</center>
					</s:else>
				</div>
			</div>
		</div>
		<center>
			<div>
				<s:if test="#request.orderList.size > 0">
					<input type="button" class="formButton" id="sureButton" onclick="IamSure()" value="确认发货"/>
				</s:if>
				<s:if test="#request.close==1">
				<input type="button" class="formButton" onclick="javascript:window.close()" value="关闭"/>
				</s:if>
				<s:else>
				<input type="button" class="formButton" onclick="returnBack()" value="返回"/>
				</s:else>
			</div>	
		</center>
	</form>	
		<div id="changeDialog" style="display:none;" title="修改地址">
		<form id="dialogForm" >
				<table class="listTable" style="padding-left:0px;" cellpadding="0" cellspacing="0" width="700px;">
					<tr style="background-color:#D1E9E9;">
						<td width="30%">原地址：</td>
						<td width="70%" style="text-align: left;padding-left:20px;" id="defaultAddress"></td>
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
										<input type="text" name="address" id="addressId" onblur="addressBlur(this)" maxlength="100"/>
										<label id="addressErrorMsg" style="display: none;"></label>
									</td>
								</tr>
								<tr>
									<td>收货人姓名：<font color="red">*</font></td>
									<td>
										<input type="text" name="recevier" id="recevierId" onblur="receiveBlur(this)" maxlength="15"/>
										<label id="recevierErrorMsg" style="display: none;"></label>
									</td>
								</tr>
								<tr>
									<td>电话：</td>
									<td>
										<input type="text" name="qu" id="telphone-qu" style="width: 50px;" maxlength="4"/>-<input type="text" name="no" id="telphone-no"  style="width: 80px;" maxlength="12"/>-<input type="text" name="qu" id="telphone-bran" style="width: 50px;" maxlength="4"/>
										<label id="telPhoneErrorMsg" style="display: none;"></label>
									</td>
								</tr>
								<tr>
									<td>手机：</td>
									<td>
										<input type="text" name="cellphone" id="cellphoneId" maxlength="11"/> 电话和手机至少填一个
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				</form>
		</div>
	
	<form action="base/delivery!list" method="POST" id="otherForm">
		<input type="hidden" name="deliveryDTO.activeTab" value="0"/>
	</form>
</body> 
</html>