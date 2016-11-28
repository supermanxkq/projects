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
	<title>邮件服务器设置</title>
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

	$(document).ready(function (){
	       var method = document.getElementById("method"); 
	       
	        if(method.value=='checkUI'){
	             setInputDisabled();
	            }
	        });
		
		
		/**验证除汉字外字符*/
		function replaceTocode(obj){
		   obj.value = obj.value.replace(/[^\a-\z\A-\Z0-9\@\.\,\?\;\:]/g,'');
	    }
		
		espUrlFlag = false;
		espPortFlag = false;
		//emaccNoFlag = false;
		emPwdFlag = false;
		replyUrlFlag = false;
		testEmailFlag = false;
		
		var validateEspUrl = function(obj){
             var type = ["isNull"];
             var errorMsg = ["服务器地址不能为空!"];
             espUrlFlag = showErrorMsg(obj,type,errorMsg,"espUrlError","espUrlError"); 
             return espUrlFlag;           
            }
            
       	//var validateEspPort = function(obj){
        //     var type = ["isNull"];
        //     var errorMsg = ["服务器端口不能为空!"];
        //     espPortFlag = showErrorMsg(obj,type,errorMsg,"espPortError","espPortError");            
        //    }
            
        var validateEmaccNo = function(obj){             
             var flag1 = false;
             var flag2 = false;
             var typeNull = ["isNull"];
             var errorNull = ["邮件发送帐号不能为空!"];
             var typeEmail = ["isEmail"];
             var errorEmail = ["邮箱格式不正确！"];
             var isNull = $("#emaccNo").val();
             pubErrorShow($("#emaccNoError"),"");
             pubErrorShow($("#emaccNoMsg"),"");
             flag1 = showErrorMsg(obj,typeNull,errorNull,"emaccNoError","emaccNoError");
             if(flag1){             	
             	flag2 = showErrorMsg(obj,typeEmail,errorEmail,"emaccNoMsg","emaccNoMsg"); 
             }
             if(flag1&&flag2){
               		emaccNoFlag = true; 
               		return emaccNoFlag; 
               }         
            }
            
        var validateEmPwd = function(obj){
             var type = ["isNull"];
             var errorMsg = ["邮箱密码不能为空!"];
             emPwdFlag = showErrorMsg(obj,type,errorMsg,"emPwdError","emPwdError");
             return emPwdFlag;            
            }
       
       	var validateReplyUrl = function(obj){
             var type = ["isNull"];
             var errorMsg = ["回复地址不能为空!"];
             replyUrlFlag = showErrorMsg(obj,type,errorMsg,"replyUrlError","replyUrlError");
             return replyUrlFlag;            
            }
    
		//修改方法
		var check = function() {
					    
		    var espUrl = getEle("espUrl");
            espUrlFlag = validateEspUrl(espUrl); 
            var emaccNo = getEle("emaccNo");
            emaccNoFlag = validateEmaccNo(emaccNo);
            var empwd = getEle("empwd");
            emPwdFlag = validateEmPwd(empwd);
            var replyUrl = getEle("replyUrl");
            replyUrlFlag = validateReplyUrl(replyUrl);
                       	     	              
			if(!(espUrlFlag&&emaccNoFlag&&emPwdFlag&&replyUrlFlag)){
	            alert("请按照提示信息正确填写.(*部分为必填项!)");
		        return false;					
			}			
		}
		
		/**发送测试邮件*/		
		var sendTestEmail = function(){
		        var obj = getEle("testEmail");
			    var flag1 = false;
			    var flag2 = false;
	            var type = ["isNull"];
	            var errorMsg = ["请填写邮箱!"];
	            var typeEmail = ["isEmail"];
	            var errorEmail = ["邮箱格式不正确！"];
	            /**Ajax传输参数*/
	            var espUrl = $("#espUrl").val();
	            var emaccNo = $("#emaccNo").val();
	            var empwd = $("#empwd").val();
	            var testEmail = $("#testEmail").val();
	            
	            pubErrorShow($("#testEmailError"),"");
             	pubErrorShow($("#testEmailMsg"),"");
	            
	            flag1 = showErrorMsg(obj,type,errorMsg,"testEmailError","testEmailError");
	            
	           	
	            if(flag1){
	             	flag2 = showErrorMsg(obj,typeEmail,errorEmail,"testEmailMsg","testEmailMsg"); 
	            }            	            	            
				if (!(flag1&&flag2)){
			    	return false;
				}
			    else{
			    	var params = {
			    		"mailServParamDto.espUrl" : espUrl,
			    		"mailServParamDto.emaccNo" : emaccNo,
			    		"mailServParamDto.empwd" : empwd,
			    		"mailServParamDto.testEmail" : testEmail
					}; 
						
					var actionUrl = "mail/mailserver!sendEmail";   
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
						    	alert("测试邮件发送成功！");
						    	return true;
							}else {
						    	alert("测试邮件未发送成功！");
						    	return false;						    	
						 	}
						}
					 });		    	
			    	}
				}
		
		
       //关闭输入法 
		function colseImeMode(obj){
			 obj.style.css = ("ime-mode","disabled");
			}
	
	
	</script> 
</head>
<body>
	<s:if test="method=='addSave'">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 邮件服务器设置 &gt;&gt; 添加邮件服务器
		</div>
	</s:if>
	<s:if test="method=='editSave'">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 邮件服务器设置 &gt;&gt; 修改邮件服务器
		</div>
	</s:if>
	<s:if test="method=='checkUI'">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 邮件服务器设置 &gt;&gt; 查看邮件服务器
		</div>
	</s:if>
	<s:form action="mail/mailserver" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>
	<s:if test="#session.user_session.userLevel==0">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		<%--
			<td><s:file name="icpFile" id="icpFile"></s:file>
		--%>
			<s:hidden name="mailServParamDto.espId"/>
		   	<tr>
		   		<th width="45%" align="right"><span class="Color5">* </span>发送邮件服务器地址(SMTP)：</th>
		        <td>
		        	<s:textfield name="mailServParamDto.espUrl" id="espUrl" onkeyup = "replaceTocode(this);" onblur="validateEspUrl(this);" maxlength="20" cssClass="formInput"/>
		        	<label id="espUrlError" style="display: none;"></label>
		        </td>		      	
		   	</tr>
			<!-- 
				<tr>
			      	<th align="right"><span class="Color5">* </span>服务器端口：</th>
			        <td>
				        <s:textfield name="mailServParamDto.espPort" id="espPort" onkeyup = "replaceToNum(this);" onblur="validateEspPort(this);" maxlength="20" cssClass="formInput"/> 
				        <label id="espPortError" style="display: none;"></label>
			        </td>		      	
			   	</tr>
		   	 -->
		   	<tr>
		      	<th align="right"><span class="Color5">* </span>邮件发送帐号：</th>
		        <td>
			        <s:textfield name="mailServParamDto.emaccNo" id="emaccNo" onkeyup = "replaceTocode(this);" onblur="validateEmaccNo(this);" maxlength="60" cssClass="formInput"/> 
			        <label id="emaccNoError" style="display: none;"></label>
			        <label id="emaccNoMsg" style="display: none;"></label>			                
		        </td>		      	
		   	</tr>
		   	<tr>
		      	<th align="right"><span class="Color5">* </span>帐号密码：</th>
		        <td>
			        <s:textfield name="mailServParamDto.empwd" id="empwd" onblur="validateEmPwd(this);" maxlength="30" cssClass = "formInput"/> 
			        <label id="emPwdError" style="display: none;"></label>
		        </td>		      	
		   	</tr>
		   	<tr>
		      	<th align="right"><span class="Color5">* </span>邮件回复地址：</th>
		        <td>
			        <s:textfield name="mailServParamDto.replyUrl" id="replyUrl" onkeyup = "replaceTocode(this);" onblur="validateReplyUrl(this);" maxlength="60" cssClass="formInput"/> 
			        <label id="replyUrlError" style="display: none;"></label>
		        </td>		      	
		   	</tr>
		   	<tr>	
		   		<th align="right"><span class="Color5">* </span>使用状态：</th>
		        <td>
			        <s:select name="mailServParamDto.status" id="status" cssStyle="width:156px;" 
			        list="#request.statusValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
		        </td>
		    </tr>
		    <s:if test="method=='checkUI'">
		    <tr>	
		   		<th align="right">创建时间：</th>
		        <td>
			      	<s:textfield name="mailServParamDto.createTime"  maxlength="60" cssClass="formInput"/> 
		        </td>  
		    </tr>
		    <tr>	
		   		<th align="right">操作人：</th>
		        <td>
			        <s:textfield name="mailServParamDto.operator"  maxlength="60" cssClass="formInput"/> 
		        </td>
		    </tr>
		    <tr>	
		   		<th align="right">更新时间：</th>
		        <td>
			        <s:textfield name="mailServParamDto.updateTime"  maxlength="60" cssClass="formInput"/> 			        
		        </td>
		    </tr>
		    </s:if> 
		   	<s:if test="method!='checkUI'">
		   	<tr>
		      	<th align="right">测试邮件地址：</th>
		        <td>
			        <s:textfield name="mailServParamDto.testEmail" id="testEmail" onkeyup = "replaceTocode(this);" maxlength="60" cssClass="formInput"/> 
			        <input type="button" onclick="sendTestEmail();" value="发送测试邮件"></input>
			        <label id="testEmailError" style="display: none;"></label>
			        <label id="testEmailMsg" style="display: none;"></label>
		        </td>		      	
		   	</tr>
		   	</s:if>
	 	</table>
	</s:if>
		
	 <div class="formTableBottom">
	 	<s:if test="#session.user_session.userLevel==0">
	 	<s:if test="method!='checkUI'">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-6400-01' value='邮件服务器添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-6400-03' value='邮件服务器修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		</s:if>
		</s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('mail/mailserver!list')"/>
     </div>
	 </s:form>
</body>
</html>