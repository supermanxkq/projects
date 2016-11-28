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
			
	       	if(method.value=='checkUI'){
	             setInputDisabled();
	             $("#mfpDesc").attr("readonly","true");
                 $("#mfpDesc").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
	        }
	        

	      //初始化 "按条"、"按月"收费显示结果 
	       	var messType = $("#messType").val();
            var tiao = document.getElementById("tiao");
            var yue = document.getElementById("yue");
            if(messType=="0"){               	 
              tiao.style.display = "";
              yue.style.display = "none";                                                              
            }
            else{
          	  yue.style.display = "";
              tiao.style.display = "none";                 	
            }
        })
	
		
			
		//根据限制类型显示不同限制信息 ：messType下拉框onChange触发
		function changeDiv(){
                 var messType = $("#messType").val();
                 var tiao = document.getElementById("tiao");
                 var yue = document.getElementById("yue");
                 if(messType=="0"){               	 
                   tiao.style.display = "";
                   yue.style.display = "none";                                                              
                 }
                 else{
               	   yue.style.display = "";
                   tiao.style.display = "none";                 	
                 }
			}
		
		function replaceToNumPoint(obj){
		   if(isNaN(obj.value)){
			 	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
			 	obj.value = obj.value.replace(/^[.]/g,"");  //验证第一个字符是数字而不是.
			 	obj.value = obj.value.replace(/[.]{2,}/g,""); //清除第一个点以外的点			 	
			 }
	       }


	   	   
/********************************************************************************************************/

		messNameFlag = false;
		messPeriodFlag = false;
		messFeeFlag = false;
		descFlag = false;
			
		/*校验参数名称*/
		var validateName = function(){
			var messName = $.trim($("#messName").val());
			$("#messNameError").hide(); 
			if(messName==''){
				$("#messNameError").html("参数名称不能为空!");
				$("#messNameError").show(); 
				messNameFlag = false;
			} else{
				$("#messNameError").hide(); 
				messNameFlag = true;               
			}
			if(messNameFlag){
				/**Ajax传输参数*/
				var mfpId = $.trim($("#mfpId").val());  
	            var messName = $.trim($("#messName").val()); 
	                 	            	            
		    	var params = {
		    		"messDto.mfpId" : mfpId,
		    		"messDto.messName" : messName
				}; 					
				var actionUrl = "message/messageparam!validateName";   
				$.ajax({   
			        async:false,
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
				    		$("#messNameError").html("该参数名称已存在!");
							$("#messNameError").show(); 				    	
							messNameFlag = false;
						}else {
							$("#messNameError").hide(); 
							messNameFlag = true;					    	
					 	}
					}
				 });		   
				}			
		}

		/*校验短信条数*/
		var validatePeriod = function(){
			var messType =$.trim($("#messType").val());  //参数类型
			var messPeriod = $.trim($("#messPeriod").val());//使用条数
			var miniPeriod = $.trim($("#miniPeriod").val());//最低条数
			
		$("#miniPeriodError").hide();
		$("#messPeriodError").hide();
			if(messType==0){
				if(miniPeriod.length==0&&miniPeriod==""){
					$("#miniPeriodError").html("短信条数不能为空!");
					$("#miniPeriodError").show(); 
					messPeriodFlag = false;
				}else{
					$("#miniPeriodError").hide(); 
					messPeriodFlag = true;
				}
			}else{
				if(messType==1&&messPeriod.length==0&&messPeriod==""){
					$("#messPeriodError").html("短信条数不能为空!");
					$("#messPeriodError").show(); 
					messPeriodFlag = false;
				} 
				else{				
					$("#messPeriodError").hide(); 
					messPeriodFlag = true;               
				}
			}
		}

		/*校验短信费用*/
		var validateFee = function(){
			var messType =$.trim( $("#messType").val());  //参数类型
			var singleFee = $.trim($("#singleFee").val());  //单条费用
			var messFee = $.trim($("#messFee").val());  //费用
			
			$("#singleFeeError").hide();
			$("#messFeeError").hide(); 
			if(messType==0){ 
				if(singleFee==""){
					$("#singleFeeError").html("短信费用不能为空!");
					$("#singleFeeError").show(); 
					messFeeFlag = false;
				} 
				else if(singleFee > 5){
					$("#singleFeeError").html("单条费用设置太高!");
					$("#singleFeeError").show(); 
					messFeeFlag = false;
			    }
				else if(singleFee!=""){
				    var floatreg = /^\d{1}\.\d{1,3}$/;
				    if(!floatreg.test(singleFee)){
					    $("#singleFeeError").html("单条费用格式错误，请重新填写.形如:0.1/0.15");
						$("#singleFeeError").show(); 
						messFeeFlag = false;
		  			}else{
		  				$("#singleFeeError").html("");
						$("#singleFeeError").hide(); 
						messFeeFlag = true;
		  			}		   
			   	}				
			    else{
					$("#singleFeeError").hide(); 
					messFeeFlag = true;               
				}
			}else{
				if(messFee==""){
					$("#messFeeError").html("短信费用不能为空!");
					$("#messFeeError").show(); 
					messFeeFlag = false;
				}else{
					var feereg = /^\d{1,5}\.\d{1,3}$/;
			        if(!feereg.test(messFee)){
					   $("#messFeeError").html("费用格式错误，请重新填写.形如:1000.11/10.15");
					   $("#messFeeError").show(); 
					   messFeeFlag = false;
				  	}else{
				  		$("#messFeeError").html("");
						$("#messFeeError").hide(); 
						messFeeFlag = true;
				  	}		   
				}				
			}
		}


		/*校验参数描述*/
		var validateDesc = function(){
			var mfpDesc = $.trim($("#mfpDesc").val());  //参数描述
			$("#descError").hide(); 
			if(mfpDesc.length>255){
				$("#descError").html("描述输入字符过长，请重新输入!");
				$("#descError").show(); 
				descFlag = false;
			} else{
				$("#descError").hide(); 
				descFlag = true;               
			}
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

		function replaceToNumPoint2(obj){
		    if(isNaN(obj.value)){
			 	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
			 	obj.value = obj.value.replace(/^[.]/g,"");  //验证第一个字符是数字而不是.
			 	obj.value = obj.value.replace(/[.]{2,}/g,""); //清除第一个点以外的点			 	
			}
		}
		
		//修改方法
		var check = function() {
			validateName();
			validatePeriod();
			validateFee();
			validateDesc();
			if(!(messNameFlag&&messPeriodFlag&&messFeeFlag&&descFlag)){
				alert("页面信息填写有误，请根据提示信息填写！");
				return false;
			}
		}
    </script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 短信管理  &gt;&gt;  短信费参数管理
	</div>
	
	<s:form action="message/messageparam" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method" />	
		  <table width="96%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<s:if test="method=='addSave'">
			<tr>				
		  	    <th align="right" width="20%"><span class="Color5">* </span>参数名称：</th>
		  		<td width="30%"><s:textfield name="messDto.messName" id="messName" onblur="validateName();" maxlength="20" cssStyle="width:150px;" cssClass="formInput" theme="simple"/>
		  		<s:hidden name="messDto.mfpId" id="mfpId"></s:hidden>
		  		<label id="messNameError" style="display: none;" class="errorMsg"></label>
		  		</td>
		  		<th align="right" width="20%"></th>
		  		<td width="30%"></td>
			</tr>
			</s:if>
			<s:else>
			<tr>			
		  		<th align="right" width="20%">参数名称：</th>
		  	    <td width="30%"><s:property value="messDto.messName"/><s:hidden name="messDto.messName" id="messName"/>
		  		<s:hidden name="messDto.mfpId" id="mfpId"></s:hidden></td>
		  		<th align="right" width="20%"></th>
		  		<td width="30%"></td>
			</tr>
			</s:else>
		  <tr>
		 		<th align="right" width="20%"><span class="Color5">* </span>使用状态：</th>
				<td width="30%"><s:select name="messDto.useSign" id="useSign" list="#request.status" listKey="key" listValue="value" cssStyle="width:156px;" cssClass="formSelect" theme="simple"/></td>
				<th align="right" width="20%"><span class="Color5">* </span>套餐类型：</th>
				<td width="30%"><s:select name="messDto.messType" id="messType" onchange="changeDiv();" list="#request.type" listKey="key" listValue="value" cssStyle="width:156px;" cssClass="formSelect" theme="simple"/></td>	
		  </tr>
		  
		  <tr id="tiao" style="display:none;">
			  	<th align="right" width="20%"><span class="Color5">* </span>最低使用条数：</th>
			    <td width="30%"><s:textfield name="messDto.miniPeriod" id="miniPeriod" onblur="validatePeriod();" maxlength="5" cssClass="formInput"  cssStyle="width:150px;" theme="simple" onkeyup="replaceToNum(this);" />				    
				<label id="miniPeriodError" style="display: none;" class="errorMsg"></label>
				</td>
				<th align="right" width="20%"><span class="Color5">* </span>单条收费标准：</th>
		        <td width="30%"><s:textfield name="messDto.singleFee" id="singleFee" onblur="validateFee();" maxlength="5" onkeyup="replaceToNumPoint1(this);" cssClass="formInput" cssStyle="width:150px;"  theme="simple"/><span class="Color3">(如：0.1或0.15)</span>
		  		<label id="singleFeeError" style="display: none;" class="errorMsg"></label>
		  		</td>
		  </tr>		  
	      <tr id="yue" style="display: none;">
		  		<th align="right" width="20%"><span class="Color5">* </span>包月条数：</th>
		        <td width="30%"><s:textfield name="messDto.messPeriod" id="messPeriod" onblur="validatePeriod();" maxlength="7"  onkeyup="replaceToNum(this);" cssClass="formInput"  cssStyle="width:150px;" theme="simple"/>
		        <label id="messPeriodError" style="display: none;" class="errorMsg"></label>
		        </td>
		        <th align="right" width="20%"><span class="Color5">* </span>包月费用：</th>
		        <td width="30%"><s:textfield name="messDto.messFee" id="messFee" onblur="validateFee();" maxlength="8" onkeyup="replaceToNumPoint1(this);" cssClass="formInput" cssStyle="width:150px;" theme="simple"/>
		  		<label id="messFeeError" style="display: none;" class="errorMsg"></label>
		  		</td>
		  </tr>		    			
	  	  <tr>
		  		<th align="right" width="20%">参数描述：</th>		        
		 		<td>
					<s:textarea name="messDto.MfpDesc" id="mfpDesc" cssClass="formTextarea" onblur="validateDesc();" rows="4" cols="40"/>
					<label id="descError" style="display: none;" class="errorMsg"></label>
				</td>
	 	  </tr>
		  </table>
	 <div class="formTableBottom">
	 	<s:if test="method!='checkUI'">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1601-02' value='短信参数添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-1601-03' value='短信参数修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		</s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('message/messageparam!list')"/>
	 </div>
	 </s:form>
	
</body>
</html>