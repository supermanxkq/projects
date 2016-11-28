<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<base href="<%=basePath%>" />
		<title>短信服务商参数配置</title>
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
		<script src="js/pubValidate.js"></script>
		<script src="js/pubValiPattern.js"></script>
		<script src="js/jquery/jquery.ui.tabs.js"></script>
		<script src="js/base/member.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script type="text/javascript">

       /**服务器URL配置成功标志，key-value*/
		  var  servUrlValueFlag=false;
		/**账户号*/		  
		  var  AccountFlag=false;
		  var  AccountValueFlag=false;
		/**口令*/		  
		  var  passwordErrorFlag=false;
		  var  passwordValueErrorFlag=false;
		/**手机号*/
          var  mobileFlag=false;
         /**手机号值*/
         var mobileValueFlag=false;
         /**内容参数标志*/
         var contentFlag=false;
            /**验证服务器URL值*/
            function checkServURLValue(){
            	var servUrlValue=	$.trim($("#servUrlValue").val());
        		if(servUrlValue==0){
					$("#servUrlValueErrorMsg").html("请输入服务器URL值!");
					$("#servUrlValueErrorMsg").show();
					servUrlValueFlag=false;
			  }else{
					$("#servUrlValueErrorMsg").hide();
					servUrlValueFlag=true;
				  }
                }
	        /**验证账户号*/
	         function checkAccount(){
	                var   account=$.trim($("#account").val());
	 				if(account.length==0){
	 							$("#accountErrorMsg").html("账户号参数不能为空！");
	 							$("#accountErrorMsg").show();
	 							AccountFlag=false;
	 	 			}else{
								$("#accountErrorMsg").hide();
								AccountFlag=true;
	 	 	 		}
	         }
	            
         /**验证账户号值*/
         function checkAccountValue(){
                var   accountValue=$.trim($("#accountValue").val());
 				if(accountValue.length==0){
 							$("#accountValueErrorMsg").html("账户号值不能为空！");
 							$("#accountValueErrorMsg").show();
 							AccountValueFlag=false;
 	 			}else{
							$("#accountValueErrorMsg").hide();
							AccountValueFlag=true;
 	 	 		}
         }

        /**验证口令*/
        function  checkPassword(){
			var password=$.trim($("#password").val());
			if(password.length==0){
					$("#passwordErrorMsg").html("请输入口令参数!");
					$("#passwordErrorMsg").show();
					passwordErrorFlag=false;
			  }else{
					$("#passwordErrorMsg").hide();
					passwordErrorFlag=true;
				  }
            }

        /**验证口令值*/
        function checkPasswordValue(){
               var   passwordValue=$.trim($("#passwordValue").val());
				if(passwordValue.length==0){
							$("#passwordValueErrorMsg").html("口令值不能为空！");
							$("#passwordValueErrorMsg").show();
							passwordValueErrorFlag=false;
	 			}else{
							$("#passwordValueErrorMsg").hide();
							passwordValueErrorFlag=true;
	 	 		}
        }
        
        /**验证手机号码*/
        function  checkMobile(){
			var mobile=	$.trim($("#mobile").val());
			if(mobile.length==0){
					$("#mobileErrorMsg").html("请输入手机号码参数!");
					$("#mobileErrorMsg").show();
					mobileFlag=false;
			  }else{
					$("#mobileErrorMsg").hide();
					mobileFlag=true;
				  }
            }
        /**验证手机号码值*/
        function conPerTeleNoBlur(){
            var obj=document.getElementById("mobileValue");
            var type = ["isMobile"];  
            var errorMsg = ["手机号码格式错误!"];
            if(conPerTeleNoFlag = showErrorMsg(obj,type,errorMsg,"conPerTeleNoErrorMsg","conPerTeleNoWarnMsg")){
            	mobileValueFlag=true;
                }else{
            	mobileValueFlag=false;
                }
         }
		/**验证内容参数*/
		function checkContent(){
			var content=	$.trim($("#content").val());
			if(content.length==0){
					$("#contentErrorMsg").html("请输入内容参数!");
					$("#contentErrorMsg").show();
					contentFlag=false;
			  }else{
					$("#contentErrorMsg").hide();
					contentFlag=true;
				  }
			}
        
        /**添加时，如果选择了启用，改变其他普通服务器数据的状态*/
		function changeOtherStatus(){
			/**修改服务器的状态*/
			changeStatus();
				var status=$("#status").val();
				var method=$("#method").val();
 				/**判断是否选择的是启用*/
 				if(status==1){
 				/**提示确认用户其他服务器状态设置为禁用*/
 	 						if(method=="addSave"){
 	 							if(confirm("确认要停用其他服务器吗？")){
						/**如果确认选择的是启用，设置所有的服务器的状态为停用*/
 							  var actionUrl = "stock/messServParamConfig!changeStatus";
 							    $.ajax({
 							        url : actionUrl,
 							        dataType : "json",
 							        cache : false, 
 							        type : "POST",
 							        error : function(textStatus, errorThrown) {
 							       		 alert("修改失败！");
 							       		$("#status").val(0);
 							        },
 							        success : function(data, textStatus) {
 							       		 alert("修改成功！");
 							        }
 							    });
 	 						}else{
 	 	 	 	 					$("#status").val(0);	
 	 	 	 	 					$("#status1").val(0);	
 	 	 	 	 	 			}
 	 						}else{
 	 							if(confirm("确认要停用其他普通短信服务器吗？")){
								/**如果是修改,更新普通服务器状态*/
 	 							 var actionUrl = "stock/messServParamConfig!changeShortMessStatus";
 	 						    $.ajax({
 							        url : actionUrl,
 							        dataType : "json",
 							        cache : false, 
 							        type : "POST",
 							        error : function(textStatus, errorThrown) {
 							       		 alert("修改失败！");
 							       		$("#status").val(0);
 							        },
 							        success : function(data, textStatus) {
 							       		 alert("修改成功！");
 							        }
 							    });
 	 		 	 			}else{
 	 							$("#status").val(0);	 	 	 			
 	 					}
 				}
			}
        }
	       
		/**测试普通短信服务器*/
		function testShortMessSer(){
			conPerTeleNoBlur();
			if(mobileValueFlag){
				checkShortMess();
				if(servUrlValueFlag&&AccountFlag&&AccountValueFlag&&passwordErrorFlag&&passwordValueErrorFlag&&mobileFlag){
			  /**获取手机号码*/
				var mobile = $("#mobile").val();
			/**获取手机号码值*/
				var mobileValue = $("#mobileValue").val();
			/**用户名*/
				var account = $("#account").val();
			/**用户名值*/
				var accountValue = $("#accountValue").val();
			/**密码*/
				var password = $("#password").val();
			/**密码*/
				var passwordValue= $("#passwordValue").val();
			/**服务器Url*/
				var servUrl = $("#servUrl").val();
			/**服务器Url值*/
				var servUrlValue = $("#servUrlValue").val();
				/**内容参数*/
				var content = $("#content").val();

				/**json数据传输*/
				var params = {
						"messServParamConfigDTO.mobile": mobile,
						"messServParamConfigDTO.mobileValue": mobileValue,
						"messServParamConfigDTO.account" : account,
						"messServParamConfigDTO.accountValue" : accountValue,
						"messServParamConfigDTO.password" : password,
						"messServParamConfigDTO.passwordValue" : passwordValue,
						"messServParamConfigDTO.servUrlValue" : servUrlValue,
						"messServParamConfigDTO.content" : content
			    };
			    var actionUrl = "stock/messServParamConfig!testShortMessServCon";
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
				        alert ("普通服务器测试短信已发送成功！请注意查收......");
			        }
			    });
		}
			}
		}





		
	    /**修改服务器名称*/
	   	function changeServiceId(){
			$("#serviceId1").val($("#serviceId").val());
	}
		/**修改服务器状态*/
		function changeStatus(){
			$("#status1").val($("#status").val());
			}


		/**下拉框disable*/
        function   disableSelect(){
					var method=$("#method").val();			
					if(method=="addSave"){
							$("#status1").attr("disabled",true);
							$("#serviceId1").attr("disabled",true);
					}	
         }

		/**修改时候验证全部*/
		function checkShortMess(){
			/**手机号码*/
			checkMobile();
			/**口令值*/
			checkPasswordValue();
			/**口令*/
			checkPassword();
			/**账户号值*/
			checkAccountValue();
			/**账户号*/
			checkAccount();
			/**服务器url*/
			checkServURLValue();
			if(servUrlValueFlag&&AccountFlag&&AccountValueFlag&&passwordErrorFlag&&passwordValueErrorFlag&&mobileFlag){
						return true;
			}
			return false;
		}
	</script>
	</head>
	<body onload="disableSelect();">
		<s:hidden name="messServParamConfigDTO.mspId" id="mspId" />
		<s:hidden name="messServParamConfigDTO.paramType" id="paramType"
			value="1" />
		<div class="List_tit">
			普通短信服务器参数设置
		</div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="formTable">
			<tr>
				<th align="right" width="20%">
					短信供应商：
				</th>
				<td width="30%">
					<s:select id="serviceId" name="messServParamConfigDTO.serviceId"
						list="#request.severNameValue" listKey="key" listValue="value"
						cssClass="formSelect" onchange="changeServiceId()" />
				</td>
				<th align="right" width="20%">
					服务器状态：
				</th>
				<td width="30%">
					<s:select id="status" name="messServParamConfigDTO.status"
						list="#request.serverStatus" listKey="key" listValue="value"
						cssClass="formSelect" onchange="changeOtherStatus()"
						theme="simple" />
					<label id="warnMsg" style="display: none;"></label>
					<label id="errorMsg" style="display: none;"></label>
				</td>
			</tr>
			<tr>
				<th align="right">
					<span class="Color5">* </span>账户号参数：
				</th>
				<td>
					<s:textfield name="messServParamConfigDTO.account" id="account"
						maxlength="20" cssClass="formInput" onblur="checkAccount()" />
					<label id="accountErrorMsg" style="display: none;" class="errorMsg"></label>
				</td>
				<th align="right">
					<span class="Color5">* </span>账户号值：
				</th>
				<td>
					<s:textfield name="messServParamConfigDTO.accountValue"
						id="accountValue" maxlength="11" cssClass="formInput"
						onblur="checkAccountValue()" />
					<label id="accountValueErrorMsg" style="display: none;"
						class="errorMsg"></label>
				</td>
			</tr>
			<tr>
				<th align="right">
					<span class="Color5">* </span>口令参数：
				</th>

				<td>
					<s:textfield name="messServParamConfigDTO.password" id="password"
						maxlength="13" cssClass="formInput" onblur="checkPassword()" />
					<label id="passwordErrorMsg" style="display: none;"
						class="errorMsg"></label>
				</td>
				<th align="right">
					<span class="Color5">* </span>口令值：
				</th>
				<td>
					<s:password name="messServParamConfigDTO.passwordValue"
						id="passwordValue" maxlength="20" cssClass="formInput"
						onblur="checkPasswordValue();" />
					<label id="passwordValueErrorMsg" style="display: none;"
						class="errorMsg"></label>
				</td>
			</tr>
			<tr>
				<th align="right">
					<span class="Color5">* </span>手机号码参数：
				</th>
				<td>
					<s:textfield name="messServParamConfigDTO.mobile" id="mobile"
						maxlength="20" cssClass="formInput" onblur="checkMobile()" />
					<label id="mobileErrorMsg" style="display: none;" class="errorMsg"></label>
				</td>
				<th align="right">
					<span class="Color5">* </span>服务器URL值：
				</th>
				<td>
					<s:textfield name="messServParamConfigDTO.servUrlValue"
						id="servUrlValue" maxlength="50" cssClass="formInput"
						onblur="checkServURLValue()" />
					<label id="servUrlValueErrorMsg" style="display: none;"
						class="errorMsg"></label>
				</td>
			</tr>
			<tr>
				<th align="right">
					<span class="Color5">* </span>内容参数：
				</th>
				<td align="left" colspan="3">
					<s:textfield name="messServParamConfigDTO.content" id="content"
						maxlength="20" cssClass="formInput" onblur="checkContent();" />
					<label id="contentErrorMsg" style="display: none;" class="errorMsg"></label>
				</td>
			</tr>
			<tr>
				<th align="right" colspan="2">
					手机号码值：
					<s:textfield id="mobileValue" maxlength="11" cssClass="formInput"
						onkeyup="replaceToNum(this);" onblur="conPerTeleNoBlur();"
						name="messServParamConfigDTO.mobileValue" />
				</th>
				<td colspan="2">
					<input id="" type="button" class="formButton" value="发测试短信"
						onclick="return testShortMessSer();" />
					<label class="errorMsg" id="conPerTeleNoErrorMsg"
						style="display: none; position: relative; left: 20px;">
						手机号码格式错误!
					</label>
				</td>
			</tr>
		</table>
	</body>
</html>