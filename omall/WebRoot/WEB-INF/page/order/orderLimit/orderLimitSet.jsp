<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>订单审核限额管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
			type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.4.2.min.js"></script>
		<link href="js/jquery/css/jquery.ui.all.css" rel="stylesheet"
			type="text/css" />
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
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script type="text/javascript"><!--
		//更改级别
		var flag="1";
		function changeFlag(){
			//设定被选中的按钮的Key值
			flag = $('[name="orderLimitDTO.deptSign"]:checked').val();
			if(flag=="1"){
				$("#org1").show();
				$("#org2").show();
				$("#mer1").hide();	
				$("#mer2").hide();
			}
			if(flag=="2"){
				$("#org2").hide();
				$("#org1").hide();
				$("#mer1").show();
				$("#mer2").show();
			}
		};

	function setMerchants(){
		var merName = $("#merName").val();
		if(merName==""){
			$("#merchantsMsg").text("商户不能为空!");
			return false;
		}else{
			$("#merchantsMsg").text("");
			return true;
		}
		
	};
	function setOrderLName(){
		var orderLName = $("#orderLName").val();
		if(orderLName==""){
			$("#orderLNameError").text("");
			$("#orderLNameMsg").text("审核限额名称不能为空!");
			return false;
		}else{
			$("#orderLNameMsg").text("");
			$("#orderLNameError").text("");
			return true;
		}
	};
	function setOrderLimitAmt(){
	
		var orderLimitAmt = $("#orderLimitAmt").val();
		if(orderLimitAmt==""&&orderLimitAmt==0){
			$("#orderLNameError").text("");
			$("#orderLimitAmtMsg").text("审核额度不能为空!");
			return false;
		}else{
			$("#orderLNameError").text("");
			$("#orderLimitAmtMsg").text("");
			return true;
			
		}
	};
	function check(){
		if(flag=="1"){
			return setOrderLName()&&cardBin()&&setOrderLimitAmt();
		}else{		
			return setMerchants()&&setOrderLName()&&cardBin()&&setOrderLimitAmt();
		}
	};
	function cardBin(){
		var binId = $("#binId").val();
		if(binId==-1){
			$("#cardBinIdMsg").text("请选择卡BIN!");
			return false;
		}
		$("#cardBinIdMsg").text("");
		
		var flag = $('[name="orderLimitDTO.deptSign"]:checked').val();
		var params = {
				"orderLimitDTO.deptSign" : flag,
				"orderLimitDTO.binId"  : binId
				};
		var actionUrl = "order/orderLimit!checkBinId" ;
		$.ajax({
			 url : actionUrl,   
		        data : params,   
		        dataType : "json",
		        cache : false, 
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误!");  
		        },
		        success : function(data, textStatus) {
		        	if(data.flag==false){
		        		   $("#cardBinIdError").text("该卡的卡BIN已经被设定过订单审核额度!");
			        	   //alert("该卡的卡BIN已经被设定过订单审核额度，请查看列表信息!");
			        	   return false;
				    }$("#cardBinIdError").text("");
		        }
			});
		} ;
		function checkOrderLName(obj){
			setOrderLName(obj);
			var orderLimitId = $("#orderLimitId").val();
			var orderLName = $("#orderLName").val();
			if(orderLName.length!=0){
			var params = {
					"orderLimitDTO.orderLName" : orderLName,
					"orderLimitDTO.orderLimitId" : orderLimitId
			    }; 
			 var actionUrl = "order/orderLimit!checkOrderLName";
			 $.ajax({   
			        url : actionUrl,   
			        data : params,   
			        dataType : "json",
			        cache : false, 
			        type : "POST",
			        error : function(textStatus, errorThrown) {   
			    		alert("系统ajax交互错误!");  
			        },
			        success : function(data, textStatus) {
			        	if(data.flag==false){
				        	   $("#orderLNameError").text("该审核额度名称已经被使用!");
				        	   return false;
					    }
			        }
			    });
				}
			};
		function checkOrderLimit(obj){
			setOrderLimitAmt(obj);
			if(orderLimitFlag==true){
				var binId = $("#binId").val();
				var orderLimitAmt = $("#orderLimitAmt").val();
				var params = {
						"orderLimitDTO.binId" : binId,
						"orderLimitDTO.orderLimitAmt" : orderLimitAmt
				    }; 
				 var actionUrl = "order/orderLimit!checkOrderLimit";
				 $.ajax({   
				        url : actionUrl,   
				        data : params,   
				        dataType : "json",
				        cache : false, 
				        type : "POST",
				        error : function(textStatus, errorThrown) {   
				    		alert("系统ajax交互错误!");  
				        },
				        success : function(data, textStatus) {
				        	if(data.flag==false){
					        	   $("#orderLimitAmtError").text("该审核额度应小于卡内额度上限!");					        	   
					        	   return false;
						    }
				        }
				    });
			  }
		 };
				
		$(document).ready(function (){
		       var method = document.getElementById("method"); 
	            if(method.value=='editSave'){
	            	$("#binId").attr("disabled","disabled");
	                }
	            if(method.value=='checkUI'){
	            	$("#binId").attr("disabled","disabled");
	            	$("#status").attr("disabled","disabled");
	            	$("#descr").attr("disabled","disabled");
	            	$("#submitInput1").attr("disabled","disabled");
	            	 setInputDisabled();
	                }
		        });

--></script>

	</head>
	<body>

		<div class="Position">
			当前位置是：HOME &gt;&gt;订单管理 &gt;&gt; 订单审核限额管理
		</div>

		<!--机构、商户帮助页面的包含	-->
		<jsp:include page="/WEB-INF/page/base/terminals/mercHelps.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/page/base/organs/organHelp.jsp"></jsp:include>
		<s:form action="order/orderLimit" method="post"
			onsubmit="document.getElementById('submitInput').disabled = true;return true;">
			<s:hidden name="method" id="method" />
			<s:hidden id="updateTime" name="orderLimitDTO.updateTime" />
			<s:hidden id="orderLimitId" name="orderLimitDTO.orderLimitId" />
			<s:hidden id="operatorId" name="orderLimitDTO.operatorId" />
			<s:hidden id="createTime" name="orderLimitDTO.createTime" />
			<s:if test="method=='editSave'">
				<s:hidden id="binIdA" name="orderLimitDTO.binId" />
			</s:if>
			<div class="List_tit">
				订单审核限额设置
			</div>
			<div>
				<s:if test="#session.user_session.userLevel!=2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formTable">
						<s:if test="method=='addSave'">
							<tr>

								<th align="right" width="20%">
									<span class="Color5">* </span>审核限额标志：
								</th>
								<td width="30%">
									<s:radio name="orderLimitDTO.deptSign" id="deptSign"
										list="#request.deptSignValue" listKey="key" listValue="value"
										value="1" theme="simple" onclick="changeFlag()" />
								</td>
								<th align="right" width="20%">
									<div id="org1">
										所属机构：
									</div>
									<div id="mer1" style="display: none">
										<span class="Color5">* </span>所属商户：
									</div>
								</th>
								<td width="30%">
									<div id="org2">
										<s:textfield name="orderLimitDTO.name" id="name"
											maxlength="20" cssClass="formInput" theme="simple"
											readonly="true" />
										<s:hidden name="orderLimitDTO.organId" id="organId"></s:hidden>
									</div>
									<div id="mer2" style="display: none">
										
										<s:textfield name="orderLimitDTO.merName" id="merName"
											maxlength="20" cssClass="formInput" readonly="true" theme="simple"
											onkeyup="allowEnCnNu();" onblur="setMerchants()" />
										<s:hidden name="orderLimitDTO.merId" id="merId"></s:hidden>
										<img alt="查找商户" src="images/search.gif"
											style="cursor: pointer;" onclick="ajaxMerc();" />
										<span class="Color5"><label id="merchantsMsg" ></label></span>
										<span class="Color5"><label id="merchantsError" ></label></span>
									</div>
								</td>
							</tr>
						</s:if>
						<s:else>
							<s:hidden name="orderLimitDTO.deptSign" />
							<s:if test="orderLimitDTO.deptSign==1">
								<tr>
									<th align="right" width="20%">
										<span class="Color5">* </span>所属机构：
									</th>
									<td width="30%">
										<s:property value="orderLimitDTO.name" />
										<s:hidden name="orderLimitDTO.organId"></s:hidden>
									</td>
								</tr>
							</s:if>

							<s:if test="orderLimitDTO.deptSign==2">
								<tr>
									<th align="right" width="20%">
										<span class="Color5">* </span>所属商户：
									</th>
									<td width="30%">
										<s:property value="orderLimitDTO.merName" />
										<s:hidden name="orderLimitDTO.merId"></s:hidden>
									</td>
								</tr>
							</s:if>
						</s:else>
						<tr>
							<th align="right">
								<span class="Color5">* </span>审核限额名称：
							</th>
							<td>
								<s:textfield name="orderLimitDTO.orderLName" id="orderLName"
									maxlength="20" cssClass="formInput" theme="simple"
									onkeyup="allowEnCnNu();" onchange="checkOrderLName()"/>
								<span class="Color5"><label id="orderLNameMsg"></label></span>
								<span class="Color5"><label id="orderLNameError"></label></span>
							</td>
							<th align="right">
								<span class="Color5">* </span>卡BIN：
							</th>
							<td>
								<s:select name="orderLimitDTO.binId" id="binId"
									list="#request.binIdValues" listKey="key" listValue="value"
									headerKey="-1" headerValue="--请选择--" onchange="cardBin()"
									cssClass="formSelect" theme="simple" />
								<span class="Color5"><label id="cardBinIdMsg"></label></span>
								<span class="Color5"><label id="cardBinIdError"></label></span>
							</td>
						</tr>
						<tr>
							<th align="right">
								状 态：
							</th>
							<td>
								<s:select name="orderLimitDTO.status" id="status"
									list="#request.statusValues" listKey="key" listValue="value"
									cssClass="formSelect" theme="simple" />
							</td>
							<th align="right">
								<span class="Color5">* </span>审核限额：
							</th>
							<td>
								<s:textfield name="orderLimitDTO.orderLimitAmt"
									id="orderLimitAmt" maxlength="20" cssClass="formInput"
									theme="simple" onblur="checkOrderLimit()" />
								<span class="Color5"><label id="orderLimitAmtMsg"></label></span>
								<span class="Color5"><label id="orderLimitAmtError"></label></span>
							</td>
						</tr>
						<tr>
							<th align="right">
								描述：
							</th>
							<td>
								<s:textarea name="orderLimitDTO.descr" id="descr"
									cssClass="formTextarea" theme="simple" maxlength="255" />
							</td>
						</tr>
					</table>
				</s:if>
			</div>
			<div>
			</div>
			<div class="formTableBottom">
				<s:if test="method=='addSave'">
					<input id="submitInput" type="submit" class="formButton"
						value="保 存" onclick="return check();" />
				</s:if>
				<s:if test="method=='editSave'">
					<input id="submitInput" type="submit" class="formButton"
						value="保 存" onclick="return check();" />
				</s:if>
				<s:if test="method=='checkUI'">
					<input id="submitInput1" type="submit" class="formButton"
						value="保 存" onclick="return check();" />
				</s:if>
				<input type="button" class="formButton" value="返 回"
					onclick="go('order/orderLimit!list')" />
			</div>
		</s:form>
	</body>
</html>