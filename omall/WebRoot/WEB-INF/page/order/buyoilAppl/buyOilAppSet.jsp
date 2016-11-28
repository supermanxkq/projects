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

		<title>购油申请</title>
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
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script src="js/pubValiPattern.js"></script>
		<script type="text/javascript"><!--

			var oilFactFlag = false;
			var oilTypeFlag = false;
			var oilQtyFlag = true;
			var payMenFlag = true;			
			var descrFlag = false;					
			
			//根据油厂id查询油品
	    	function loadOilType(){
	    		 
				var oilFactId = $("#oilFactId").val();
				$("#oilFactIdValue").html("");
				if (oilFactId=='-1'){
			    	$("#oilFactIdValue").html("请选择油厂!");
			    	oilFactFlag = false;
			    	return false;
				}
			    else{
			      var oilFactId = $("#oilFactId").val();
	              actionUrl = "buyoilappl/buyoilapplaction!getOilTypes";
	              $.ajax({
	                   url:actionUrl,
	                   type:"POST",
	                   data:"buyOilDto.oilFactId="+oilFactId,
	                   dataType:"json",
	                   success:function(data){
	                    $("#oilTypeId").empty();
	 	            	$('#oilTypeId').append("<option id='' value='-1'>--请选择--</option>");
	 	            	if (data.length>0){
	 		        		for(var i=0;i<data.length;i++) {
	 		        			$('#oilTypeId').append("<option id='' value='"+data[i].key+"'>"+data[i].value+"</option>");
	 		        		}
	 		        		oilFactFlag = true;
	 		        	}
	 		        	
	                   },
	                   error:function(data){
	                       alert("系统ajax交互错误!");
	                   }
	                });
	               } 
		    }
		    var validateOilFact = function(){
			    var oilFactId = $("#oilFactId").val();
					$("#oilFactIdValue").html("");
					if (oilFactId=='-1'){
				    	$("#oilFactIdValue").html("请选择油厂!");
				    	oilFactFlag = false;
				    	return false;
					}else{
						oilFactFlag = true;
					}
			}
			
			
			var validateOilTypeId = function(){
			    var oilTypeId = $("#oilTypeId").val();
					$("#oilTypeIdValue").html("");
					if (oilTypeId=='-1'){
				    	$("#oilTypeIdValue").html("请选择油品!");
				    	oilTypeFlag = false;
				    	return false;
					}else{
						oilTypeFlag = true;
					}
			}
				
			//判断油品是否选择
			var validateOilType = function(){
			
				  
				var oilTypeId = $("#oilTypeId").val();
				var oilFactId = $("#oilFactId").val();
				$("#oilTypeIdValue").html("");
				if (oilTypeId=='-1'){
			    	$("#oilTypeIdValue").html("请选择油品!");
			    	oilTypeFlag = false;
			    	return false;
				}
			    else{
			    	var params = {
			    			"buyOilDto.oilFactId" : oilFactId,
					        "buyOilDto.oilTypeId" : oilTypeId
					    }; 
						
					var actionUrl = "buyoilappl/buyoilapplaction!getSalePrice";   
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
					    	if (data!=null) {
						    	$("#oilPrice").val(data);
						    	oilTypeFlag = true;
						    }else {
						    	$("#oilTypeIdValue").html("");
						    }
						}
					 });		    	
			    	}
				}
			
			//判断购油数量不为空	
			var validateOrderAmt= function(){
				var oilQty = $("#oilQty").val();
				var oilPrice = $("#oilPrice").val();
				var oilTypeId = $("#oilTypeId").val();
				$("#oilQtyValue").html("");
				if(oilTypeId=="-1"){
					$("#oilTypeIdValue").html("请选择油品");
					oilTypeFlag = false;
					return false;
				}
				if(oilQty==""||oilQty==null){
					$("#oilQtyValue").html("请填写购油数量");
					oilQtyFlag = false;
					return false;
				}else{
					$("#oilQtyValue").html("");
					var price = oilPrice					
					var quantity = oilQty;					
					var needPay = Math.floor(parseFloat(price*100 * quantity * 1378))/100;										
					$("#orderAmt").val(needPay);
					oilQtyFlag = true;
				}
			}
			
			//判断申请人不为空
			var validatePoser= function(){
				var payMenName =$("#payMenName").val();  
				$("#proposerValue").html("");
				if(payMenName==""||payMenName==null){ 					
					$("#proposerValue").html("请选择申请人！");
					payMenFlag = false;
					return false;
				} 
				else{
					$("#proposerValue").html("");
					payMenFlag = true;
				}
			}
				
			
			//保存调用方法
			var check = function(){
				validateOilFact();
				validateOilTypeId();
				validateOrderAmt();
				validatePoser();
				if(!(oilFactFlag&&oilTypeFlag&&oilQtyFlag&&payMenFlag)){
					alert("请检查输入信息，带*号的为必填项");
					return false;
				}else{
					return true;
				}
			 }	


		function replaceToNumPoint(obj){
		   if(isNaN(obj.value)){
			 	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
			 	obj.value = obj.value.replace(/^[.]/g,"");  //验证第一个字符是数字而不是.
			 	obj.value = obj.value.replace(/[.]{2,}/g,""); //清除第一个点以外的点			 	
			 }
	       }
	    function replaceToNum(obj){		   
		   if(isNaN(obj.value)){
			 	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
			 	obj.value = obj.value.replace(/[^.]/g,"");  //验证第一个字符是数字而不是.					 	
			 }
	   	   }

		--></script>
	</head>
	<body >

		<div class="Position">
			当前位置是：HOME &gt;&gt;购油申请 &gt;&gt; 购油申请添加
		</div>
		<jsp:include page="/WEB-INF/page/member/member/memberHelp.jsp"></jsp:include>
		<div class="List_tit">购油申请</div>
		<s:form action="buyoilappl/buyoilapplaction" method="post" theme="simple"
		onsubmit="document.getElementById('submitInput').disabled = true;return true;">
		<s:hidden name="method" />
			<div id="dialog-confirm">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formTable">											
					<s:hidden name="buyOilDto.orderId" id="orderId" />				 
					<s:if test="#session.user_session.userLevel!=2">
					<tr>
						<th align="right" width="6%" colspan="1">
							<span class="Color5">* </span>油厂名称：
						</th>
						<td>
							<s:select name="buyOilDto.oilFactId" id="oilFactId"
								list="#request.factList" headerKey="-1" headerValue="--请选择--" listKey="key" listValue="value"
								cssClass="formSelect" onchange = "loadOilType();" theme="simple" />
							<span class="Color5" id="oilFactIdValue"></span>
						</td>
					</tr>
					</s:if>
					<tr>
						<th align="right" width="6%">
							<span class="Color5">* </span>油品类型：
						</th>
						<td width="30%">
							<s:select name="buyOilDto.oilTypeId" id="oilTypeId"
								list="#request.typesList" headerKey="-1" headerValue="--请选择--" listKey="key" listValue="value"
								cssClass="formSelect" theme="simple" onchange="validateOilType();" />
							<span class="Color5" id="oilTypeIdValue"></span>
						</td>
						<th align="right" width="6%">油价(¥)：</th>
						<td width="30%">
							<s:textfield name="buyOilDto.oilPrice" id="oilPrice"
								maxlength="11" cssClass="formInput" theme="simple"
								readonly="true"/>
							<span class="Color3">(元/升)</span>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="Color5">* </span>购油数量：
						</th>
						<td>
							<s:textfield name="buyOilDto.oilQty" id="oilQty"
								maxlength="5" onkeyup="replaceToNumPoint(this);"
								cssClass="formInput" theme="simple"
								onblur="validateOrderAmt();" />
							<span class="Color3">(吨)</span>
							<span class="Color5" id="oilQtyValue"></span>
						</td>
						<th align="right">
							<span class="Color5"></span>订单金额：
						</th>
						<td>
							<s:textfield name="buyOilDto.orderAmt" id="orderAmt" maxlength="11" cssClass="formInput"
								theme="simple" readonly="true" />
							<span class="Color3">(元)</span>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="Color5">* </span>申请人：
						</th>
						<td>
			        		<s:textfield name="buyOilDto.proposerName" id="payMenName" readonly="true" 
			        		cssClass="formInput" theme="simple" onblur="validateDescr();" />
			        		<s:hidden name="buyOilDto.proposer" id="payMenId"/>
							<img alt="查找会员" src="images/search.gif" style="cursor:pointer;" onclick="ajaxParentMemId();"/>
							<span class="Color5" id="proposerValue"></span>
						</td>
						<th align="right">
							<span class="Color5"></span>联系电话：
						</th>
						<td>
							<s:textfield name="buyOilDto.telNo" id="telNo" cssClass="formInput"
								theme="simple" readonly="true" />
						</td>
					</tr>
					<s:if test="method=='editSave'">	
					<tr>
						<th align="right">描述：</th>
			        	<td colspan="4">
				        	
				        	<s:textarea name="buyOilDto.descR" id="descR" readonly="true" rows="3" cols="55"/>	
				        	<span class="Color3"></span>
		        		</td>
					</tr>
					</s:if>
				</table>
			</div>
			<div class="formTableBottom">
				<s:if test="method=='addSave'">					
						<input id="submitInput" type="submit" class="formButton"
							value="保 存" onclick="return check();" />					
				</s:if>
				<s:else>
						<input id="submitInput" type="submit" class="formButton"
							value="保 存" onclick="return check();" />
				</s:else>
				<input type="button" class="formButton" value="返 回"
					onclick="go('buyoilappl/buyoilapplaction!list')" />
			</div>
		</s:form>
	</body>
</html>