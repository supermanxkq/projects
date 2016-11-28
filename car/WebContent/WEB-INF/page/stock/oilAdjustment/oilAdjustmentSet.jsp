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
    
    <title>会员管理</title>
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
	<script src="js/common.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script src="js/base/member.js"></script>
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>

<script type="text/javascript">

	    $(document).ready(function (){
	     
	        var method = document.getElementById("method"); 	        
	        if(method.value=='checkDetail'){
	        //alert("method.value"+method.value);
	             setInputDisabled();
	             $("#merId").attr("disabled","disabled");
	             $("#merName").attr("disabled","disabled");
	             $("#stockId").attr("disabled","disabled");
	             $("#status").attr("disabled","disabled");
	             $("#teleNo").attr("disabled","disabled");
	             $("#oilType").attr("disabled","disabled"); 
	             $("#stockOilAmoUnt").attr("disabled","disabled");
	             $("#descR").attr("disabled","disabled");
	            }
	        });
	        
	        
<!--	 	var contactsflag=true;-->
	
	   	var setwarnProportFlag = true;	

        
			//判断短信标题不为空
			var setwarnProport= function(){
				var warnProportIon =$("#warnProportIon").val();  
				$("#warnProportIonValue").html("");
				if(warnProportIon==""||warnProportIon==null){ 					
					$("#warnProportIonValue").html("预警比例不能为空,输入预警比例！");
					setwarnProportFlag = false;
					return false;
				}else if(warnProportIon.length>=30){
				$("#warnProportIonValue").html("输入预警比例长度超过限制长度！");
				}else{
					$("#warnProportIonValue").html("");
					setwarnProportFlag = true;
				}
			}
        
        
        
		var stockOilAmoUntIDFlag = true;	
					//判断短信标题不为空
			var stockOilAmoUntID= function(){
				var stockOilAmoUnt =$("#stockOilAmoUnt").val();  
				$("#stockOilAmoUntValue").html("");
				if(stockOilAmoUnt==""||stockOilAmoUnt==null){ 					
					$("#stockOilAmoUntValue").html("预警比例不能为空,输入预警比例！");
					stockOilAmoUntIDFlag = false;
					return false;
				}else if(stockOilAmoUnt.length>=30){
				$("#stockOilAmoUntValue").html("输入预警比例长度超过限制长度！");
				}else{
					$("#stockOilAmoUntValue").html("");
					stockOilAmoUntIDFlag = true;
				}
			}
			
		
<!--		var stockOilAmoUntID = function(){-->
<!--			var stockOilAmoUnt=$("#stockOilAmoUnt").val().length; -->
<!--			 var reg= new RegExp("^[A-Za-z\\u4e00-\\u9fa5]+$");-->
<!--			$("#stockOilAmoUntMsg").html(""); -->
<!--			if(stockOilAmoUnt<1){ -->
<!--				$("#stockOilAmoUntMsg").html("库存不能为空！");-->
<!--				contactsflag = false;-->
<!--			} else{-->
<!--				contactsflag = true;-->
<!--			}-->
<!--			if(!reg.test(stockOilAmoUnt)){-->
<!--			$("#stockOilAmoUntError").text("库存格式错误!");-->
<!--			return false;-->
<!--		}-->
<!--		}-->
		
	    function replaceToNum(obj){		   
		   if(isNaN(obj.value)){
			 	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
			 	obj.value = obj.value.replace(/[^.]/g,"");  //验证第一个字符是数字而不是.					 	
			 }
	   	   }
	   	   
	   	function replaceToNumPoint(obj){
		   if(isNaN(obj.value)){
			 	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
			 	obj.value = obj.value.replace(/^[.]/g,"");  //验证第一个字符是数字而不是.
			 	obj.value = obj.value.replace(/[.]{2,}/g,""); //清除第一个点以外的点
			 	
			 }
	       }

	   	   
	    var check = function() {
	    var warnProportIon = $.trim($("#warnProportIon").val());
	    var stockOilAmoUnt = $.trim($("#stockOilAmoUnt").val());
	    	setwarnProport();
	    	stockOilAmoUntID();
		   if(!(setwarnProportFlag&&stockOilAmoUntIDFlag)){
				alert("请检查输入信息，带*号的为必填项");
				return false;
			}else{
				return true;
			}	   
		}

</script>
  </head>
  <body>
<!--  <jsp:include page="/WEB-INF/page/base/terminals/mercHelps.jsp"></jsp:include>-->
  <div class="Position">
		当前位置是：HOME &gt;&gt;库存微调 
	</div>
	<s:form action="stock/stockAdjustment"  method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method" />
	<s:if test="#session.user_session.userLevel!=1">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
			<s:if test="method=='editSave'">
			
		  		<th align="right" width="20%">库存编号：</th>
		        <td>
		        <s:property value="stockAdjustmentDTO.stockId"/>
		        <s:hidden name="stockAdjustmentDTO.stockId" id="stockId"></s:hidden>
<!--		 			<input type="text" name="stockAdjustmentDTO.stockId" id="stockId"-->
<!--							onkeyup="replaceToNum(this);" class="formInput"  />-->
		 		</td>
		 		</s:if>
		        <th align="right"><span class="Color5">* </span>预警比例：</th>
		 		<td>
		 			<s:textfield name="stockAdjustmentDTO.warnProportIon"  id="warnProportIon" onblur="setwarnProport();" onkeyup="replaceToNum(this);"  maxlength="80" cssClass="formInput"  theme="simple"   />
		 			<span id="warnProportIonValue" class="Color5"></span>
		 		</td>
			</tr>
		 	<tr>
		 		<th align="right"><span class="Color5">* </span>商户名称：</th>
		        <td><s:textfield name="stockAdjustmentDTO.merName" id="merName"  disabled="true"   maxlength="60" cssClass="formInput" theme="simple"/>
<!--		        <span id="contactsMsg" class="Color5"></span>-->
		        </td>

		 		<th align="right">手机号码：</th>
			 		<td>
			        	<s:textfield name="StockAdjustmentDTO.teleNo" id="teleNo"  disabled="true" maxlength="11" cssClass="formInput" theme="simple"  />
			        	<label id="telNoMsg" style="display: none;"></label> 
			        	<label id="telNoError" ></label> 
			        </td>
		        </tr>

		  	<tr>
		      	 <th align="right">油品类型：</th>
		       		<td>
		        	<s:select name="stockAdjustmentDTO.oilType" id="oilType"  list="#request.invChangeValues"  listKey="key" listValue="value"  cssClass="formInput" theme="simple" ></s:select>
		       		</td>
		         <th align="right"><span class="Color5">* </span>库存量：</th>
			         <td><s:textfield name="stockAdjustmentDTO.stockOilAmoUnt" id="stockOilAmoUnt" maxlength="60" onblur="stockOilAmoUntID();" onkeyup="replaceToNumPoint(this);"  cssClass="formInput" theme="simple"/>
			         <span id="stockOilAmoUntValue" class="Color5"></span>
			         </td>
		        </tr>
		  
		</table>
		<table class="formTable">
		 	<tr>
		      	<th align="right" width="20%">备注：</th>
		      	<td><s:textarea name="stockAdjustmentDTO.descR" id="descR" rows="3" cols="78"/></td>		  
		   	</tr>
		</table>
	</s:if>

	 <div class="formTableBottom">
	 <s:if test="key=='sy-2203-01'">
	 	<input type="button" class="formButton" value="返 回" onclick="go('stock/stockAdjustment!list')"/>
	 </s:if>
	 <s:else>
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-2203-02' value='添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-2203-03' value='修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		<input type="button" class="formButton" value="返 回" onclick="go('stock/stockAdjustment!list')"/>
		</s:else>
	 </div>
	 </s:form>
</body>
</html>