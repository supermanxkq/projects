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
	<title>短信参数管理</title>
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
	<script src="js/datepicker/WdatePicker.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
		$(document).ready(function (){
	        var method = document.getElementById("method"); 	        
	        if(method.value=='checkDetail'){
	             setInputDisabled();
	             $("#smsTitle").attr("disabled","disabled");
	             $("#smsType").attr("disabled","disabled"); 
	             $("#smsContent").attr("disabled","disabled");
	            }
	        });

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
	   	   
	   	   var smsTitleFlag = true;		
		
			//判断短信标题不为空
			var validatesmsTitle= function(){
				var smsTitle =$("#smsTitle").val();  
				$("#smsTitleValue").html("");
				if(smsTitle==""||smsTitle==null){ 					
					$("#smsTitleValue").html("输入短信标题！");
					smsTitleFlag = false;
					return false;
				}else if(smsTitle.length>=30){
				$("#smsTitleValue").html("输入短信标题长度超过限制长度！");
				}else{
					$("#smsTitleValue").html("");
					smsTitleFlag = true;
				}
			}
			
			var smsContentFlag = true;	
			//判断短信内容不为空
			var validatesmsContent= function(){
				var smsContent =$("#smsContent").val();  
				$("#smsContentValue").html("");
				if(smsContent==""||smsContent==null){ 					
					$("#smsContentValue").html("输入短信内容！");
					smsContentFlag = false;
					return false;
				}else if(smsTitle.length>=180){
					$("#smsContentValue").html("输入短信内容长度超过限制长度！");
				} else{
					$("#smsContentValue").html("");
					smsContentFlag = true;
				}
			}
		//修改方法
		var check = function() {
		
		   var smsId = $.trim($("#smsId").val());
		   var smsTitle = $.trim($("#smsTitle").val());
		   var smsType = $.trim($("#smsType").val());   //使用状态
		   var smsContent =$.trim( $("#smsContent").val());  //参数类型
			validatesmsTitle();
			validatesmsContent();
			//alert("smsTitleFlag"+smsTitleFlag);
			if(!(smsTitleFlag&&smsContentFlag)){
				alert("请检查输入信息，带*号的为必填项");
				return false;
			}else{
				return true;
			}

		}
    </script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 短信费管理&gt;&gt; 短信群发添加
	</div>
	
	<s:form action="message/shortMesSend" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">	
	<s:hidden name="method" id="method" />
		  <table class="formTable">

			<tr>			
		  		<th align="right" width="20%"><span class="Color5">* </span>短信标题：</th>
		  	    <td width="30%"><s:textfield name="smsDto.smsTitle" id="smsTitle" cssClass="formInput" onblur="validatesmsTitle()"  cssStyle="width:150px;" theme="simple" />
		  	    <span class="Color5" id="smsTitleValue"></span>
		  		<s:hidden name="smsDto.smsId" id="smsId"></s:hidden></td>
		  		<th align="right" width="20%"><span class="Color5">* </span>发送人群：</th>
				<td width="30%"><s:select name="smsDto.smsType" id="smsType"  list="#request.type" listKey="key" listValue="value" cssStyle="width:156px;" cssClass="formSelect" theme="simple"/></td>	

			</tr>

		  </table>
		  
		  <div id="tiao" style="display: block;">
		  <s:if test="method=='checkDetail'">
		  <table  class="formTable">	     
		    <tr>
			  	<th align="right" width="20%"><span class="Color5">* </span>本次发送条数：</th>
			    <td width="30%"><s:textfield name="smsDto.num" id="miniPeriod" maxlength="5"   cssClass="formInput"  cssStyle="width:150px;" theme="simple"/>（条）</td>				    
				<th align="right" width="20%"><span class="Color5">* </span>本次发送费用：</th>
		        <td width="30%"><s:textfield name="smsDto.totalPrice" id="singleFee" maxlength="5"  cssClass="formInput" cssStyle="width:150px;"  theme="simple"/>(元)</td>
			</tr>
		  </table>
		  </s:if>
		  </div>		  

		  <table class="formTable">
		  	<tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>短信内容：</th>		        
		 		<td>
					<s:textarea name="smsDto.smsContent" id="smsContent" rows="2" cols="78" onblur="validatesmsContent()"  />
					<span class="Color5" id="smsContentValue"></span>
				</td>
				
		 	</tr>
		  </table>
	 <div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1611-02' value='短信参数添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-1611-03' value='短信参数修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		 	<input type="button" class="formButton" value="返 回" onclick="go('message/shortMesSend!list')"/>
	 </div>
	 </s:form>
	
</body>
</html>