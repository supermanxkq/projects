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
    
    <title>充值信息管理</title>
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
	<script src="js/pubValidate.js"></script>
	<script src="js/base/member.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
	
	var cardNoFlag = false;
	var orderAmtFlag = false;
	var realityAmtFlag = false;
	function setCardNo(obj){
		var type = ["isNull","fullNumber"];
        var errorMsg = ["卡号不能为空!","卡号格式错误!"];
        cardNoFlag = showErrorMsg(obj,type,errorMsg,"cardNoAMsg","cardNoAError");
	}
	function setOrderAmt(obj){
		var type = ["isNull","isPlusAmt"];
        var errorMsg = ["充值金额不能为空!","充值金额格式错误!"];
        orderAmtFlag = showErrorMsg(obj,type,errorMsg,"orderAmtMsg","orderAmtError");
	}
	function setRealityAmt(obj){
		var type = ["isNull","isPlusAmt"];
        var errorMsg = ["支付金额不能为空!","支付金额格式错误!"];
        realityAmtFlag = showErrorMsg(obj,type,errorMsg,"realityMsg","realityError");
	}
	function check(){
		checkCardNo(document.getElementById("cardNo"));
		setRealityAmt(document.getElementById("realityAmt"));
		checkRechAmt(document.getElementById("orderAmt"));
		var descr = $("#descr").val();
		
		if(descr.length > 255){
			alert("描述信息不能超过255个字节！");
			return false;
			}
	if(!cardNoFlag&&!orderAmtFlag&&!realityAmtFlag){
		alert("填写信息有误，*号信息为必填项。请根据提示信息重新填写!");
		return false;
		}
};
	function checkCardNo(obj){
		setCardNo(obj);
		if(cardNoFlag){
		var cardNo = $.trim($("#cardNo").val());
		if(cardNo.length==6){
			//json数据传输
			var params = {
				"rechargeDTO.cardNoShow" : cardNo
		    }; 
		    var actionUrl = "order/recharge!checkParams";
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
		        	   $("#cardNoAError").html("该卡不能被充值!");
		        	   $("#cardNoAError").addClass("errorMsg");
		        	   $("#cardNoAError").show();
			           }
		        }
		    });
		}
		if(cardNo.length>0&&cardNo.length!=6){
			$("#cardNoAError").html("卡号长度不正确!");
			$("#cardNoAError").addClass("errorMsg");
			$("#cardNoAError").show();
		}
	}}
	function checkRechAmt(aa){
		setOrderAmt(aa);
		if(orderAmtFlag){
		var cardNo = $.trim($("#cardNo").val());
		var orderAmt = $("#orderAmt").val();
		//json数据传输
		var params = {
			"rechargeDTO.cardNoShow" : cardNo,
			"rechargeDTO.orderAmt" : orderAmt
	    }; 
	    var actionUrl = "order/recharge!checkRechAmt";
	    $.ajax({   
	        url : actionUrl,   
	        data : params,   
	        dataType : "json",
	        cache : false, 
	        type : "POST",
	        error : function(textStatus, errorThrown) {   
	    		alert("系统ajax交互错误!");  
	        },
	        success : function(data) {
	            if (data.obj>0) {
	            	 $("#orderAmtError").html("超过卡上限额度");
		             $("#orderAmtError").addClass("errorMsg");
		             $("#orderAmtError").show();
	               return false;
	           	}
	           	if($("#orderAmt").val()!=null){
		           	if(!data.flag){
		           		$("#orderAmtError").html("充值金额超过单笔充值额度限定");
						$("#orderAmtError").addClass("errorMsg");
						$("#orderAmtError").show();
						return false;
			           	}
	           	}else{
	           		$("#orderAmtError").html("充值金额不能为空");
	           	 	$("#orderAmtError").addClass("errorMsg");
	           	 	$("#orderAmtError").show();
					return false;
		           	}
	        }
	    });
	}
	}
	</script>
  </head>
  <body>

  <div class="Position">
		当前位置是：HOME &gt;&gt;订单信息管理 &gt;&gt; 充值订单管理
	</div>
	<s:form action="order/recharge" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method"/>
	<s:if test="#session.user_session.userLevel!=2">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>充值卡号：</th>
		        <td width="30%">
			        <s:textfield name="rechargeDTO.cardNoShow"  id="cardNo"  maxlength="20" cssClass="formInput"  theme="simple" onblur="checkCardNo(this)" onkeyup= "replaceToNum(this);"/>
			        <label id="cardNoAMsg" style="display: none;"></label> 
			 		<label id="cardNoAError" style="display: none;"></label>
		        </td>
		        <th align="right"><span class="Color5">* </span>充值金额：</th>
		 		<td>
		 		<s:textfield name="rechargeDTO.orderAmt"  id="orderAmt"  maxlength="20" cssClass="formInput"  theme="simple" onblur="checkRechAmt(this)" onkeyup= "replaceToNumPoint(this);"/>
		 		<label id="orderAmtMsg" style="display: none;"></label> 
			 	<label id="orderAmtError" style="display: none;"></label>
		 		</td>
			</tr>
		 	<tr>
		 		<th align="right" width="20%"><span class="Color5">* </span>实际支付金额：</th>
		        <td width="30%">
		        <s:textfield name="rechargeDTO.realityAmt"  id="realityAmt"   maxlength="20" cssClass="formInput"  theme="simple" onkeyup= "replaceToNumPoint(this);" onblur="setRealityAmt(this);"/>
		        <label id="realityMsg" style="display: none;"></label> 
			 	<label id="realityError" style="display: none;"></label>
		        </td>
		 		<th align="right" width="20%"><span class="Color5">* </span>操作人：</th>
		        <td width="30%"><s:property value="rechargeDTO.operatorName" /><s:hidden name="rechargeDTO.operatorName" id="operatorName"/></td>
		  	</tr>
		  	<tr>
		  		<th align="right">充值描述：</th>
		        <td colspan="3">
		        	<s:textarea name="rechargeDTO.descr" id="descr"  cssClass="formTextarea" theme="simple" maxlength="240" />
		        </td>
		  	</tr>
		</table>
	</s:if>

	 <div class="formTableBottom">
	 	
			<my:permission key='sy-4202-02' value='充值信息添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		<input type="button" class="formButton" value="返 回" onclick="go('order/recharge!list')"/>
	 </div>
	 </s:form>
</body>
</html>