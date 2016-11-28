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
	var basepath = "<%=basePath%>";
	$(function() {
		var $tabs = $("#tabs").tabs();	
		$('#tabs-2').click(function() { // 绑定单击事件
		    $tabs.tabs('select', 1);
		    return true;
		});		
	});
	//提交订单	
	function sendOrder(orderId) {
		window.location = basepath+"base/delivery!deliverPage?deliveryDTO.orderIdStr="+orderId+"&close=1";
		//window.open(basepath+"base/delivery!deliverPage?deliveryDTO.orderIdStr="+orderId+"&close=1","发货","dependent=no,location=no,height=200,width=1000");
	}
	//省份值改变
	function changePro(obj){
			if(obj.value != -1) {
				ajaxLoadData(obj.value,1,'cityId');
			} else {
				$('#cityId').val(-1);
				$('#areaId').val(-1);
			}
		}
	//城市值改变
	 function changeCity(obj) {
			if(obj.value != -1) {
				ajaxLoadData(obj.value,2,'areaId');
			} else {
				$('#areaId').val(-1);
			}
		}
		
	function ajaxLoadData(code,types,selectId) {
			$.get(basepath+"base/delivery!ajaxLoadData", 
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
	//打开修改收货地址窗口	
	function openDialog(containerId,memTelId,receNameId,typeId,orderIds,orderId) {
		document.getElementById('dialogForm').reset();
		$('label').hide();
			$.get(basepath+"base/delivery!getProvinceAjax", {},
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
            		var values = $("#provId").find("option:selected").text() + $("#cityId").find("option:selected").text()  +$("#areaId").find("option:selected").text() +"，"+$('#postCodeId').val()+"，"+$('#addressId').val()+"，"+$('#recevierId').val()+"，";
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
            		$('#addressinfo').html(values);
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
	            			$('#'+memTelId).html(htmlStr);
	            		} else {
	            			$('#'+memTelId).html($.trim($('#cellphoneId').val()));
	            		}
	            		if($('#'+memTelId).html().length > 32) {
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
	            			 "deliveryDTO.memTele":$.trim($('#'+memTelId).html())
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
	//检查单号	 
	function checkNumWord(obj) {
    	obj.value = obj.value.replace(/[^\d\w]/g,"");
    	obj.value = obj.value.replace(/[_]/g,"");
    }
	//邮政编码检查
	function postCodeBlur(obj) {
		var type = ["isNull"];
        var postCodeErrorMsg=["邮政编码不能为空!"];
        var result = showErrorMsg(obj,type,postCodeErrorMsg,"postCodeErrorMsg","postCodeErrorMsg");
        if(!result) return;
        var type = ["isZipCode"];
        var postCodeErrorMsg=["邮政编码必须符合规范!"];
        showErrorMsg(obj,type,postCodeErrorMsg,"postCodeErrorMsg","postCodeErrorMsg");
	}
	//地址检查
	function addressBlur(obj) {
		var type = ["isNull"];
        var postCodeErrorMsg=["街道地址不能为空!"];
        showErrorMsg(obj,type,postCodeErrorMsg,"addressErrorMsg","addressErrorMsg");
	}
	
	//收货人检查
	function receiveBlur(obj) {
		var type = ["isNull"];
        var postCodeErrorMsg=["收货人姓名不能为空!"];
        showErrorMsg(obj,type,postCodeErrorMsg,"recevierErrorMsg","recevierErrorMsg");
	}
	</script> 
</head>
<body >
	
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<br>
	<font  size="+1">当前订单状态：
		<font color="red"> 买家已付款，等待卖家发货</font>
	</font><br>
	<div>
	<br>
	<span align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" style="width:70px;height:30px;"  onclick="sendOrder('${ordersDTO.orderId}')" value="发货" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" style="width:100px;height:30px;"  onclick="openDialog('address','memTele','memName',3,null,'${ordersDTO.orderId}')" value="修改收货信息" />
	</span>
	<span></span>
	</div>
	<br>
	<fieldset  style='margin-left:10px;margin-top:0px;padding-bottom:20px;padding-left:20px;'>
		<div>
		<font color="red" size="+1">平台提醒您：</font><br><br>
		(1) &nbsp;买家已付款，请尽快发货，否则买家有权申请退款。<br>
		(2)&nbsp; 如果无法发货，请尽快与买家联系说明原因。<br>
		(3)&nbsp; 买家申请退款后需征得卖家同意后才能进行发货，否则买家有权拒收货物。<br>
		</div>
	</fieldset>
	
	<div class="search" id="tabs">
	     <ul>
		   <li><a href="#tabs-1">订单信息</a></li>
		   <li><a href="#tabs-2">收货和物流信息</a></li>
		  
	     </ul>
	 
	<div class="search" id="tabs-1">
	
	 <br><br>
	 <font color="red">买家信息</font> 
	   <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		   <tr>
		  		<th align="right" width="25%"><strong>昵称：</th>
		        <td width="25%"  id="memName">${ordersDTO.memName}</td>
		        <th align="right" width="25%">真实姓名：</th>
		        <td width="25%">${ordersDTO.memRealName}</td>
		        
			</tr>
		     <tr>
		  		<th align="right" width="25%"><strong>地址：</th>
		        <td width="25%" id="address" >${ordersDTO.address}</td>
		        <th align="right" width="25%" >联系电话：</th>
		        <td width="25%" id="memTele">${ordersDTO.memTele}</td>
		       
			</tr>
			<tr>
		  		<th align="right" width="25%">邮件：</th>
		        <td width="25%">${ordersDTO.mail}</td>
		         <th align="right" width="25%">支付账号：</th>
		        <td width="25%">${ordersDTO.payAccount}</td>
			</tr>
			</table>
			<br><br>
			
			<br>
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
	<div align="right">实收款：<font color="red" size="+1"><s:property value="ordersDTO.paidAmt"/></font>&nbsp;(快递：<s:property value="ordersDTO.postAmt"/>)</div>
</div>
	 
	<div class="search" id="tabs-2">
	<br><br>
	<font color="red">物流信息</font> 
			 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
		  		<th align="right" width="20%"><strong>收货地址：</th>
		        <td width="80%" id="addressinfo" >${ordersDTO.address}</td>
			</tr>
		   	
		   	<tr>
		  		<th align="right" width="20%"><strong>配送方式：</th>
		        <td width="80%" >
		        <s:if test="ordersDTO.deliveryWay=='0'">
		                无需物流
		        </s:if>
		        <s:else>
		                 自己联系
		        </s:else>
		        </td>
			</tr>
		   	
	 	</table>
	</div>	
		
	 	
		<div id="changeDialog" style="display:none;" title="修改地址">
			<form id='dialogForm'>
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

	
	
</body> 
</html>