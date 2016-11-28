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
	<title>卡管理</title>
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
	<script type='text/javascript' src='js/jquery.autocomplete.js'></script>
	<link rel="stylesheet" type="text/css" href="js/jquery.autocomplete.css" />
	<script src="js/common.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script language="javascript" type="text/javascript" defer="defer" src="<%=basePath%>js/datepicker/WdatePicker.js"></script>
	
	<script type="text/javascript">
		
		var queryCardNo = function(){
		   
			var cardNo = $("#cardNo").val();
			if (cardNo=='' || cardNo.length<6){
				$("#cardStatusValue").html("请正确输入卡号!");
				
		    	return false;
			}
			
			var params = {
		        "cardunlockDTO.cardNoView" : cardNo
		   
		    }; 
		   
			var actionUrl = "card/cardunlock!queryCardNo";   
			
		    $.ajax( {   
		        url : actionUrl,   
		        data : params,   
		        dataType : "json",
		        cache : false, 
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误!");  
		        },
		        success : function(data, textStatus) {   
		            if (data.flag) {
		                $("#cardStatusValue").html("挂失状态");
		                $("#flag").val("1");
		               flag=1;
		           	}else {
		           
		            	$("#cardStatusValue").html("状态异常!");
		            	$("#flag").val("0");
		            	
		            }
		        }
		    });
		}
		var validateDescr= function(){
			var textareaLength=$("#descr").val().length;  
				if(textareaLength>=200){ 
					var test=$("#descr").val().substr(0,200); 
					$("#descr").val(test); 
					$("#descrValue").html("200以内字符！");
					return false;
				} 
		}
		var check = function(){
			var cardNo = $("#cardNo").val();
			var flag = $("#flag").val();
			
			if (cardNo=='' || cardNo.length<6){
				alert("请检查您的卡号");
		    	$("#cardNo").focus();
		    	return false;
			}
			if(flag == "0"){
		
				alert("当前卡状态不正确!");
		    	$("#cardNo").focus();
		    	return false;
			}
			
		}
	</script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 卡信息管理 &gt;&gt; 解挂
	</div>
	
	<s:form action="card/cardunlock!save" theme="simple">
	<input type="hidden" id="flag"/>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		<tr>
			<th align="right" width="20%"><span class="Color5">* </span>卡号：</th>
			<td align="left" width="30%">
				<input type="text" name="cardunlockDTO.cardNoView" id="cardNo" maxlength="6" onkeypress="return onlyNum(this);" onkeyup="replaceToNum(this);" class="formInput2" onblur="queryCardNo() ;"/>
			</td>
			<th align="right" width="20%"><span class="Color5">* </span>当前卡状态：</th>
			<td align="left" width="30%" id="cardStatusValue"></td>
		</tr>
		<tr>
			<th align="right">描述：</th>
			<td align="left" colspan="3"><textarea id="descApp" name="cardunlockDTO.descApp" class="formTextarea" onblur="validateDescr();"  ></textarea>
			     <span id="descrValue" class="Color3">(200以内字符)</span>
			</td>
		</tr>
	</table>
	<div class="formTableBottom">
		<my:permission key='sy-2105-02' value='解挂添加'>
			<input type="submit" class="formButton" value="保 存" onclick="return check();"/>
		</my:permission>
		
		<input type="button" class="formButton" value="返 回" onclick="go('card/cardunlock!list')"/>
	</div>
	</s:form>
</body> 
</html>