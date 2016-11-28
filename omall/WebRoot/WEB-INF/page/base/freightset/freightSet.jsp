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
	<title>运费设置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />	
	<style type="text/css">
			.read { color:gray; }
	</style>
	<script src="js/jquery-1.4.2.min.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValidate.js"></script>
    <script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
    <script>

	    $(document).ready(function(){
	    	changeDisabled();
	    });

	    function changeDisabled(){
	    	if($("#type0").is(':checked')){
	    		$(getEle("tradeMoney")).removeAttr("readonly");
	    		$(getEle("tradeMoney")).removeClass("read");
	    		$(getEle("tradeFreightMoney")).removeAttr("readonly");
	    		$(getEle("tradeFreightMoney")).removeClass("read");
	    		$(getEle("freightMoney")).attr("readonly","readonly");
	    		$(getEle("freightMoney")).addClass("read");
	    		$("#freightErrorMsg").attr("display","none");
	    		$("#freightErrorMsg").html("");
			}else if($("#type1").is(':checked')){
				$(getEle("freightMoney")).removeAttr("readonly");
				$(getEle("freightMoney")).removeClass("read");
				$(getEle("tradeMoney")).attr("readonly","readonly");
				$(getEle("tradeMoney")).addClass("read");
	    		$(getEle("tradeFreightMoney")).attr("readonly","readonly");
	    		$(getEle("tradeFreightMoney")).addClass("read");
	    		$("#tradeErrorMsg").attr("display","none");
	    		$("#tradeErrorMsg").html("");
			}else{
				$(getEle("tradeMoney")).attr("readonly","readonly");
				$(getEle("tradeMoney")).addClass("read");
	    		$(getEle("tradeFreightMoney")).attr("readonly","readonly");
	    		$(getEle("tradeFreightMoney")).addClass("read");
				$(getEle("freightMoney")).attr("readonly","readonly");
				$(getEle("freightMoney")).addClass("read");
			}
	    }
		var tradeMoneyFlag = false;
		var tradeFreightMoneyFlag = false;
		var freightMoneyFlag = false;
		
		function tradeMoneyBlur(obj){
			if($("#type0").is(':checked')){
				var type = ["isNull"];
				var tradeMoneyErrorMsg = ["订单金额不能为空!"];
				tradeMoneyFlag = showErrorMsg(obj,type,tradeMoneyErrorMsg,"tradeErrorMsg","tradeErrorMsg");
				var reg = /^((\d{1,4}(\.\d{1,2})?))$/; 
				tradeMoneyFlag = reg.test(obj.value);
				if(!tradeMoneyFlag){
					$("#tradeErrorMsg").html("订单金额太大或格式错误！");
					$("#tradeErrorMsg").addClass("errorMsg");
					$("#tradeErrorMsg").show();
				}else{
					$("#tradeErrorMsg").html("");
					$("#tradeErrorMsg").hide();
				}
			}
		}

		function tradeFreightMoneyBlur(obj){
			if($("#type0").is(':checked')){
				var type = ["isNull"];
				var tradeMoneyErrorMsg = ["运费不能为空!"];
				tradeFreightMoneyFlag = showErrorMsg(obj,type,tradeMoneyErrorMsg,"tradeFreightErrorMsg","tradeFreightErrorMsg");
				var reg = /^((\d{1,3}(\.\d{1,2})?))$/; 
				tradeFreightMoneyFlag = reg.test(obj.value);
				if(!tradeFreightMoneyFlag){
					$("#tradeFreightErrorMsg").html("运费金额太大或格式错误！");
					$("#tradeFreightErrorMsg").addClass("errorMsg");
					$("#tradeFreightErrorMsg").show();
				}else{
					$("#tradeFreightErrorMsg").html("");
					$("#tradeFreightErrorMsg").hide();
				}
			}
		}

		function freightMoneyBlur(obj){
			if($("#type1").is(':checked')){
				var type = ["isNull"];
				var tradeMoneyErrorMsg = ["固定运费不能为空!"];
				freightMoneyFlag = showErrorMsg(obj,type,tradeMoneyErrorMsg,"freightErrorMsg","freightErrorMsg");
				var reg = /^((\d{1,3}(\.\d{1,2})?))$/; 
				freightMoneyFlag = reg.test(obj.value);
				if(!freightMoneyFlag){
					$("#freightErrorMsg").html("固定运费太大或格式错误！");
					$("#freightErrorMsg").addClass("errorMsg");
					$("#freightErrorMsg").show();
				}else{
					$("#freightErrorMsg").html("");
					$("#freightErrorMsg").hide();
				}
			}
		}
		
		function check(){
			if($("#type0").is(':checked')){
				freightMoneyFlag = true;
				tradeMoneyBlur(getEle("tradeMoney"));
				tradeFreightMoneyBlur(getEle("tradeFreightMoney"));
				$("#type").val("0");
			}else if($("#type1").is(':checked')){
				tradeMoneyFlag = true;
				tradeFreightMoneyFlag = true;
				freightMoneyBlur(getEle("freightMoney"));
				$("#type").val("1");
			}else{
				alert("请选择运费分类!");
				return false;
			}
			if(!(tradeMoneyFlag&&tradeFreightMoneyFlag&&freightMoneyFlag)){
			alert("信息填写有误，请按提示信息重新填写!");
			return false;
			}else{
				save();
			}
		}
		
		function getEle(ele){
			var element = document.getElementById(ele);
			return element;
		}

		function replaceToNumPoint1(obj){
			obj.value = obj.value.replace(/\s{1,}/g,"");
		    if(isNaN(obj.value)){
			    obj.value = obj.value.replace(/\s{1,}/g,"");
			    obj.value = obj.value.replace(/[.]{1,}$/g,"");
			 	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
			 	obj.value = obj.value.replace(/^[.]/g,"");  //验证第一个字符是数字而不是.
			 	obj.value = obj.value.replace(/[.]{2,}/g,""); //清除第一个点以外的点			 	
			}
		}

		function save(){
			document.getElementById('submitInput').disabled = true;
			var actionUrl = "base/freightset!editUI";
			$.ajax( {
			    url : actionUrl,
			    data : $("#form").serialize(),
			    cache : false,
			    type : "POST",
			    error : function(textStatus, errorThrown) {
			      	alert("系统ajax交互错误!");
			    },
			    success : function(data, textStatus) {
				    alert("保存成功");
				    window.location.href="base/freightset";
			    }
			});
		}
    </script>
</head>
<body>
    
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 运费设置
	</div>
	<s:form id="form" action="base/freightset!editUI" method="post" theme="simple">
		<s:hidden name="method" id="method"></s:hidden>
		<s:hidden name="freightSetDTO.fsId"></s:hidden>
		<s:hidden name="freightSetDTO.type" id="type"></s:hidden>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		   <tr>
		  		<th align="left" width="20%"><strong>&nbsp;运费设置</strong></th>
			</tr>
		    <tr>
		        <td><input id="type0" name="type" value="0" onchange="" onclick="changeDisabled();" type="radio" <s:if test="freightSetDTO.type==0">checked</s:if> />
		        <s:if test="#session.user_session.userLevel==0">
			本商城
			</s:if>
			<s:else>
			本店铺
			</s:else>   
		        ，每订单消费满<s:textfield id="tradeMoney" onkeyup="replaceToNumPoint1(this);" name="freightSetDTO.tradeMoney" maxlength="7"  cssClass="formInput" onblur="tradeMoneyBlur(this);" />元，免运费。未达到条件，收运费，
		        每订单运费<s:textfield id="tradeFreightMoney" onkeyup="replaceToNumPoint1(this);" name="freightSetDTO.tradeFreightMoney" maxlength="6" cssClass="formInput" onblur="tradeFreightMoneyBlur(this);"/>元。
		    <span class="Color3">正确格式:00.00</span>
		    <label id="tradeErrorMsg" style="display: none;"></label> 
		    <label id="tradeFreightErrorMsg" style="display: none;"></label></td>
		   	</tr>
		   	<tr>
		        <td><input id="type1" name="type" onclick="changeDisabled();" value="1" type="radio" <s:if test="freightSetDTO.type==1">checked</s:if>/>
		        <s:if test="#session.user_session.userLevel==0">
				本商城
				</s:if>
				<s:else>
				本店铺
				</s:else> 
			，每订单固定运费<s:textfield id="freightMoney" onkeyup="replaceToNumPoint1(this);" name="freightSetDTO.freightMoney" maxlength="6" onblur="freightMoneyBlur(this);" cssClass="formInput"/>元。
			<span class="Color3">正确格式:00.00</span>
			<label id="freightErrorMsg" style="display: none;"></label></td>
		   	</tr>
	 	</table>
	 	<div class="formTableBottom">
			<my:permission key='sy-1902-02' value='运费设置添加'>
		 		<input id="submitInput" type="button" class="formButton" value="保 存" onclick="check();"/>
		 	</my:permission>
		</div>
	 	</s:form>	 
	 </body> 
</html>