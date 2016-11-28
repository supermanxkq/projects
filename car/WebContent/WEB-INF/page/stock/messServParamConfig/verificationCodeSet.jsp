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
		<title>商户管理</title>
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
		<script src="js/jquery/jquery.ui.tabs.js"></script>
		<script src="js/common.js"></script>
		<script src="js/pubValidate.js"></script>
		<script src="js/pubValiPattern.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script type="text/javascript">
		  /**服务器URL配置成功标志，key-value*/
		  var  servUrlValueFlag1=false;
		/**账户号*/		  
		  var  AccountFlag1=false;
		  var  AccountValueFlag1=false;
		/**口令*/		  
		  var  passwordErrorFlag1=false;
		  var  passwordValueErrorFlag1=false;
		/**手机号码*/
		var  mobileFlag1=false;
		 /**手机号值*/
        var mobileValueFlag1=false;
	        /**验证服务器URL值*/
            function checkServURLValue1(){
            	var servUrlValue1=$.trim($("#servUrlValue1").val());
        		if(servUrlValue1==0){
					$("#servUrlValueErrorMsg1").html("请输入服务器URL值!");
					$("#servUrlValueErrorMsg1").show();
					servUrlValueFlag1=false;
			  }else{
					$("#servUrlValueErrorMsg1").hide();
					servUrlValueFlag1=true;
				  }
                }
            /**验证账户号*/
	         function checkAccount1(){
	                var   account1=$.trim($("#account1").val());
	 				if(account1.length==0){
	 							$("#accountErrorMsg1").html("账户号参数不能为空！");
	 							$("#accountErrorMsg1").show();
	 							AccountFlag1=false;
	 	 			}else{
								$("#accountErrorMsg1").hide();
								AccountFlag1=true;
	 	 	 		}
	         }

	         /**验证账户号值*/
	         function checkAccountValue1(){
	                var   accountValue1=$.trim($("#accountValue1").val());
	 				if(accountValue1.length==0){
	 							$("#accountValueErrorMsg1").html("账户号值不能为空！");
	 							$("#accountValueErrorMsg1").show();
	 							AccountValueFlag1=false;
	 	 			}else{
								$("#accountValueErrorMsg1").hide();
								AccountValueFlag1=true;
	 	 	 		}
	         }
	         /**验证口令*/
	         function  checkPassword1(){
	 			var password1=$.trim($("#password1").val());
	 			if(password1.length==0){
	 					$("#passwordErrorMsg1").html("请输入口令参数!");
	 					$("#passwordErrorMsg1").show();
	 					passwordErrorFlag1=false;
	 			  }else{
	 					$("#passwordErrorMsg1").hide();
	 					passwordErrorFlag1=true;
	 				  }
	             }

             
	         /**验证口令值*/
	         function checkPasswordValue1(){
	                var   passwordValue1=$.trim($("#passwordValue1").val());
	 				if(passwordValue1.length==0){
	 							$("#passwordValueErrorMsg1").html("口令值不能为空！");
	 							$("#passwordValueErrorMsg1").show();
	 							passwordValueErrorFlag1=false;
	 	 			}else{
	 							$("#passwordValueErrorMsg1").hide();
	 							passwordValueErrorFlag1=true;
	 	 	 		}
	         }
	         /**验证手机号码*/
		        function  checkMobile1(){
					var mobile1=	$.trim($("#mobile1").val());
					if(mobile1.length==0){
							$("#mobileErrorMsg1").html("请输入手机号码参数!");
							$("#mobileErrorMsg1").show();
							mobileFlag1=false;
					  }else{
							$("#mobileErrorMsg1").hide();
							mobileFlag1=true;
						  }
		            }
		        /**验证手机号码值*/
		        function conPerTeleNoBlur1(){
		        	var obj=document.getElementById("mobileValue1");
		            var type = ["isMobile"];  
		            var errorMsg = ["手机号码格式错误!"];
		            if(conPerTeleNoFlag = showErrorMsg(obj,type,errorMsg,"conPerTeleNoErrorMsg1","conPerTeleNoWarnMsg1")){
			            mobileValueFlag1=true;
		                }else{
		            mobileValueFlag1=false;
		                }
		         }
       
		/**测试验证码短信服务器*/
		function testValidateMessSer(){
			conPerTeleNoBlur1();
			if(mobileValueFlag1){
				checkVerification();
				if(servUrlValueFlag1&&AccountFlag1&&AccountValueFlag1&&passwordErrorFlag1&&passwordValueErrorFlag1&&mobileFlag1){
			/**获取手机号码*/
				var mobile1 = $("#mobile1").val();
			/**获取手机号码值*/
				var mobileValue1 = $("#mobileValue1").val();
			/**用户名*/
				var account1 = $("#account1").val();
			/**用户名值*/
				var accountValue1 = $("#accountValue1").val();
			/**密码*/
				var password1 = $("#password1").val();
			/**密码*/
				var passwordValue1 = $("#passwordValue1").val();
			/**服务器Url*/
				var servUrl1 = $("#servUrl1").val();
			/**服务器Url值*/
				var servUrlValue1 = $("#servUrlValue1").val();

				/**json数据传输*/
				var params = {
					"messServParamConfigDTO.mobile1": mobile1,
					"messServParamConfigDTO.mobileValue1": mobileValue1,
					"messServParamConfigDTO.account1" : account1,
					"messServParamConfigDTO.accountValue1" : accountValue1,
					"messServParamConfigDTO.password1" : password1,
					"messServParamConfigDTO.passwordValue1" : passwordValue1,
					"messServParamConfigDTO.servUrlValue1" : servUrlValue1
			    };
			    var actionUrl = "stock/messServParamConfig!testValidateMessServCon";
			    $.ajax({
			        url : actionUrl,
			        data : params,   
			        dataType : "json",
			        cache : false, 
			        type : "POST",
			        error : function(textStatus, errorThrown) {
			       		 alert("短信发送失败......");
			        },
			        success : function(data, textStatus) {
				        alert ("验证码服务器测试短信已发送成功！请注意查收......");
			        }
			    });
		}
			}
		}
		  /**添加时，如果选择了启用，改变其他验证码服务器数据的状态*/
		function changeValidateMessStatus(){
				var status1=$("#status1").val();
				var method=$("#method").val();
 				/**判断是否选择的是启用*/
 				if(status1==1){
 				/**提示确认用户其他服务器状态设置为禁用*/
 						if(confirm("确认要停用其他验证码短息服务器吗？")){
 	 						if(method=="updateData"){
						/**如果确认选择的是启用，设置所有的服务器的状态为停用*/
 							  var actionUrl = "stock/messServParamConfig!changeValidateMessStatus";
 							    $.ajax({
 							        url : actionUrl,
 							        dataType : "json",
 							        cache : false, 
 							        type : "POST",
 							        error : function(textStatus, errorThrown) {
 							       		 alert("修改失败！");
 							       		$("#status1").val(0);
 							        },
 							        success : function(data, textStatus) {
 							       		 alert("修改成功！");
 							        }
 							    });

 	 						}
 	 	 				}else{
 	 	 					$("#status1").val(0);	
 	 	 	 			}
 				}
			}

		/**修改时候验证全部*/
		function checkVerification(){
			/**手机号码*/
			checkMobile1();
			/**口令值*/
			checkPasswordValue1();
			/**口令*/
			checkPassword1();
			/**账户号值*/
			checkAccountValue1();
			/**账户号*/
			checkAccount1();
			/**服务器url*/
			checkServURLValue1();
			if(servUrlValueFlag1&&AccountFlag1&&AccountValueFlag1&&passwordErrorFlag1&&passwordValueErrorFlag1&&mobileFlag1){
						return true;
			}
			return false;
		}
		</script>

	</head>
	<body>
		<s:hidden name="messServParamConfigDTO.mspId1" id="mspId1" />
		<s:hidden name="messServParamConfigDTO.paramType1" id="paramType1"
			value="1" />
		<div class="List_tit">
			验证码短信服务器参数设置
		</div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="formTable">
			<tr>
				<th align="right" width="20%">
					短信供应商：
				</th>
				<td width="30%">
					<s:select id="serviceId1" name="messServParamConfigDTO.serviceId1"
						list="#request.severNameValue" listKey="key" listValue="value"
						cssClass="formSelect" theme="simple" />
				</td>
				<th align="right" width="20%">
					服务器状态：
				</th>
				<td width="30%">
					<s:select id="status1" name="messServParamConfigDTO.status1"
						list="#request.serverStatus" listKey="key" listValue="value"
						cssClass="formSelect" onchange="changeValidateMessStatus()" />
					<label id="warnMsg" style="display: none;"></label>
					<label id="errorMsg" style="display: none;"></label>
				</td>
			</tr>
			<tr>
				<th align="right">
					<span class="Color5">* </span>账户号参数：
				</th>
				<td>
					<s:textfield name="messServParamConfigDTO.account1" id="account1"
						maxlength="20" cssClass="formInput" onblur="checkAccount1()" />
					<label id="accountErrorMsg1" style="display: none;"
						class="errorMsg"></label>
				</td>
				<th align="right">
					<span class="Color5">* </span>账户号值：
				</th>
				<td>
					<s:textfield name="messServParamConfigDTO.accountValue1"
						id="accountValue1" maxlength="11" cssClass="formInput"
						onblur="checkAccountValue1()" />
					<label id="accountValueErrorMsg1" style="display: none;"
						class="errorMsg"></label>
				</td>
			</tr>
			<tr>
				<th align="right">
					<span class="Color5">* </span>口令参数：
				</th>

				<td>
					<s:textfield name="messServParamConfigDTO.password1" id="password1"
						maxlength="13" cssClass="formInput" onblur="checkPassword1()" />
					<label id="passwordErrorMsg1" style="display: none;"
						class="errorMsg"></label>
				</td>
				<th align="right">
					<span class="Color5">* </span>口令值：
				</th>
				<td colspan="3">
					<s:password name="messServParamConfigDTO.passwordValue1"
						id="passwordValue1" maxlength="20" cssClass="formInput"
						onblur="checkPasswordValue1()" />
					<label id="passwordValueErrorMsg1" style="display: none;"
						class="errorMsg"></label>
				</td>
			</tr>
			<tr>
				<th align="right">
					<span class="Color5">* </span>手机号码参数：
				</th>
				<td>
					<s:textfield name="messServParamConfigDTO.mobile1" id="mobile1"
						maxlength="20" cssClass="formInput" onblur="checkMobile1();" />
					<label id="mobileErrorMsg1" style="display: none;" class="errorMsg"></label>
				</td>
				<th align="right">
					<span class="Color5">* </span>服务器URL值：
				</th>
				<td>
					<s:textfield name="messServParamConfigDTO.servUrlValue1"
						id="servUrlValue1" maxlength="50" cssClass="formInput"
						onblur="checkServURLValue1();" />
					<label id="servUrlValueErrorMsg1" style="display: none;"
						class="errorMsg"></label>
				</td>
			</tr>
			<tr>
				<th align="right" colspan="2">
					手机号码值：
					<s:textfield name="messServParamConfigDTO.mobileValue1"
						id="mobileValue1" maxlength="11"
						onkeypress="return onlyNum(this);" onkeyup="replaceToNum(this);"
						cssClass="formInput" onblur="conPerTeleNoBlur1();" />
				</th>
				<td colspan="2">
					<input id="submitInput" type="button" class="formButton"
						value="发测试短信" onclick="return testValidateMessSer();" />
					<label class="errorMsg" id="conPerTeleNoErrorMsg1"
						style="display: none;">
						手机号码格式错误!
					</label>
				</td>
			</tr>
		</table>
	</body>
</html>