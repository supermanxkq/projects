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
	<title>短信模版设置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">

	function goHome() {
		parent.leftFrame.location.href='<%=basePath%>system/index!firstleft';
		parent.main.location.href='<%=basePath%>system/index!right';
		}
		
		var descrflag = false;
    
		//修改方法
		var check = function() {	              
			
			var stem = getEle("stemId");
            getValue(stem);
            
            validateDescr();
            
		    return descrflag;										
		}
		
		/**获取选中的模版的数据*/		
		var getStem = function(){
		
	            /**Ajax传输参数*/
	            var stemId = $("#stemId").val();          	            	            
		    	var params = {
		    		"smsTemDto.stemId" : stemId
				}; 
					
				var actionUrl = "message/smstemaction!getStem";   
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
				    		if(data.obj.stemTitle!=null){
					    		$("#stemTitle").val(data.obj.stemTitle);
					    	}else{
					    		$("#stemTitle").val("");					    	
					    	}
					    	if(data.obj.stemContent!=null){					    		
					    		$("#stemContent").val(data.obj.stemContent);
					    	}else{					    		
					    		$("#stemContent").val("");
					    	}					    	
					    	return true;
						}else {
					    	return false;						    	
					 	}
					}
				 });		    	
			}
			
		var validateDescr = function(){
			var textareaLength=$("#stemContent").val().length; 
			$("#descrMsg").html(""); 
			if(textareaLength>=350){ 
				$("#descrMsg").html("350以内字符！");
				descrflag = false;
			} else{
				descrflag = true;
			}
		}	
				
				
		function getValue(elem){
         	var optionVal= $(elem).find("option:selected").text();  // 取到选中的listValue(Option)的值
         	$("#stemName").val(optionVal);
     	}		
		
		
       //关闭输入法 
		function colseImeMode(obj){
			 obj.style.css = ("ime-mode","disabled");
			}
	
	
	</script> 
</head>
<body>
	
		<div class="Position">
			当前位置是：HOME &gt;&gt; 短信模版设置 &gt;&gt; 短信模版设置
		</div>
	<s:form action="message/smstemaction" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>
	<s:if test="#session.user_session.userLevel==0">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		   	<tr>
		   		<th width="34%" align="right">选择短信模版：</th>
		        <td>
			        <s:select name="smsTemDto.stemId" id="stemId" cssStyle="width:200px;" onchange="getStem();"
			        list="#request.smsTemName" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
			        <s:hidden name="smsTemDto.stemName" id="stemName"/>
		        </td>	      	
		   	</tr>
		   	<tr>
		      	<th align="right">短信模版主题：</th>
		        <td>
			        <s:textfield name="smsTemDto.stemTitle" id="stemTitle" cssStyle="width:195px;"  maxlength="60" cssClass="formInput"/> 
			        <label id="titleError" style="display: none;"></label>
			        <label id="titleMsg" style="display: none;"></label>			                
		        </td>		      	
		   	</tr>
		   	<tr>	
		   		<th align="right">短信模版内容：</th>
		        <td>
					<s:textarea name="smsTemDto.stemContent" id="stemContent" onblur="validateDescr();" style="font-size:12px;" rows="12" cols="80"/>
					<span id="descrMsg" class="Color5"></span>
				</td>
		    </tr>		    	
		</table>		    		    	 	
	</s:if>
	 
	 <div align="left" style="padding:20px; border:solid;border-color:#F2F2F2;border-style:none;margin-left:80px">
	    <font color="#ff9900" >
	                系统提示：
	                <br><br>
	                                          请勿修改  { } 中的内容，以免系统出现错误或不能正确发送短信内容；	               
	                <br>
	                                          如:{$user_name}表示会员名等，如若修改请尽量避免错误出现；
	    </font>	    
	 </div>			
	 <div class="formTableBottom">
	 	<s:if test="#session.user_session.userLevel==0">
			<my:permission key='sy-6500-01' value='短信模版添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('message/smstemaction!list')"/>
     </div>
	 </s:form>
</body>
</html>