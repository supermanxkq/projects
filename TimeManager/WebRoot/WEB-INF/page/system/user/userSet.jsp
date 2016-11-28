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
		<title>用户管理</title>
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
		<script src="js/common.js"></script>
		<script src="js/pubValidate.js"></script>
		<script src="js/pubValiPattern.js"></script>
		<script type="text/javascript">

	  /**用户名称是否重复标志*/
    var userNameFlag=false;
    var telNoFlag=false;
    var emailFlag = false;
    var merIdFlag = false;
	var organsId = false;
	var realNameFlag = false;
		//更改级别
		function changeLevel(){
			var userLevel = $('[name="userDTO.userLevel"]:checked').val();
			if(userLevel==0){
				$(".level0").show();
				$(".level1").hide();
				$(".level2").hide();
			}else if(userLevel==1){
				$(".level0").hide();
				$(".level1").show();
				$(".level2").hide();
			}else if(userLevel==2){
				$(".level0").hide();
				$(".level1").hide();
				$(".level2").show();
			}
		}
		
		
		var ajaxRole = function(){
			var userName = $("#userName").val();
			var userLevel = $('[name="userDTO.userLevel"]:checked').val();
			var organId = $("#organId").val();
		    var merId = $("#merId").val();
		    var params = {
			   		"userDTO.userName" : userName,
			        "userDTO.userLevel" : userLevel,
			        "userDTO.organId" : organId,
			        "userDTO.merId" : merId
			    };
			    var actionUrl = "system/user!ajaxRole";
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
			            $("#role").html(data.obj.roleName);
			        } 
			    });
		    if(userLevel==0||userLevel==1){
		    	if(organId==""){
		    		$("#organIdMsg").html("请选择发卡机构!");
	        	 	$("#organIdMsg").show();
		    		organIdFlag = false;
		    		return false;
		    	}else{
		    	$("#organIdMsg").html("");
	        	 	$("#organIdMsg").hide();
		    		organIdFlag = true;
		    		return true;
		    	}
		    }else if(userLevel==2){
		    	if(merId==""){
		    		$("#merIdMsg").html("请选择商户!");
	        	 	$("#merIdMsg").show();
		    		merIdFlag = false;
		    		return false;
		    	}else{
		    		$("#merIdMsg").html("");
	        	 	$("#merIdMsg").hide();
		    		merIdFlag = true;
		    		return true;
		    	
		    	}
		    }
			
		}


		/**
		 * 1.功能说明： 验证电话格式是否正确 2.调用说明：onblur = "phoneVali(this);" 3.可通过验证格式: 6872770
		 * 68727700 06336872770 063368727700 0633-6872770 0633-68727700 0106872770
		 * 01068727700 010-6872770 010-68727700
		 */
	 function phoneVali(obj){
	   var phoneNo = obj.value;
	   var phonereg =/^\d{7,8}$/;
	   var pattern=/(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^[0-9]{3,4}[0-9]{7,8}$)|(^0{0,1}1[0-9]{10}$|(^\d{7,8,11}$))/; 
	   if(phoneNo!=""&&!pattern.test(phoneNo)){
	      $("#realNameError").html("固话格式错误，请填写正确联系方式.");
	      $("#realNameError").show();
	      return false;
	   }else{
		   $("#realNameError").hide();
		      }
	   return true;
	 }

	 function conPerTeleNoBlur(obj){
         var type = ["isNull","isMobile"];  
         var errorMsg = ["手机号码不能为空!!","手机号码格式错误!"];
         telNoFlag = showErrorMsg(obj,type,errorMsg,"realNameError","realNameError");
      }
	   /**用户名称是否重复的验证*/
	 	function testUserName(){
	 		/**获取用户名称,因为数据库中的主键为用户名称，没有用户的编号，所以只获取用户的名称即可*/
	 		var userName = $.trim($("#userName").val());
	 		var method=$("#method").val();
	 	
	 			/**json数据传输*/
	 			if(userName.length != 0){
		 		if(method!="editSave"){
	 			var params = {
	 				"userDTO.userName" : userName
	 		    };
	 		    var actionUrl = "system/user!testUserName";
	 		    $.ajax({   
	 		    	async : false,
	 		        url : actionUrl,   
	 		        data : params,   
	 		        dataType : "json",
	 		        cache : false, 
	 		        type : "POST",
	 		        error : function(textStatus, errorThrown) {   
	 			    	$("#message").html("发生错误了！");
	 					$("#message").show();
	 					userNameFlag=false;
	 		        },
	 		        success : function(data, textStatus) { 
	 			          if(data.flag==true){
	 			        	 $("#message").html("用户名称已存在！");
	 			        	  $("#message").show();
	 			        	 userNameFlag=false;
	 			          }else{
	 			        	 $("#message").hide();
	 			        	 userNameFlag=true;
	 					   }
	 		        }
	 		    });
	 		    }else{
	 		    	 userNameFlag=true;
		 		    }
	 		    
	 		 }else{
	 		     $("#message").html("用户名称不能为空！");
	        	 $("#message").show();
	        	 userNameFlag=false;
	 		 }
	 	}
		var check = function() {
			var userName = $("#userName").val(); 
		    var realName = $("#realName").val();
		    var organId = $("#organId").val();
		    var merId = $("#merId").val();
		    var teleNo = $("#teleNo").val();
		    var phone = $("#teleNo");
		    var status = $("#status").val();
		    var userLevel = $('[name="userDTO.userLevel"]:checked').val();
		    var dlsFlag = $("#dlsFlag").val();
			testUserName();
			checkRealName();
			checkEmail();
		    /**验证电话号码是否错误*/
		     var teleNo = document.getElementById("teleNo");
			conPerTeleNoBlur(teleNo);
			if(userLevel==1){
				if(!(telNoFlag&&userNameFlag&&realNameFlag&&organIdFlag&&emailFlag)){
				alert("页面数据填写有误！");
				return false;
				}
			}else if(userLevel==2){
				if(!(telNoFlag&&userNameFlag&&realNameFlag&&merIdFlag&&emailFlag)){
				alert("页面数据填写有误！");
		    		return false;
				}
			}else{
				if(!(telNoFlag&&userNameFlag&&realNameFlag&&emailFlag)){
					alert("页面数据填写有误！");
					return false;
				}
		}
		
        
      
			/**判断当前执行的方法，如果是添加，才验证名称是否重复*/
			var method=$("#method").val();
			if(method=="addSave"){
					/**验证名称是否重复*/
					testUserName();
					if(!userNameFlag){
						return false;
						}else{
						return true;
						}
			}
		}
		
		
       function checkRealName(){
			var memRealName=$.trim($("#realName").val());
			 var reg= new RegExp("^[A-Za-z\\u4e00-\\u9fa5]+$");
			if(memRealName.length==0){
				$("#realNameMsg").html("真实姓名不能为空!");
				$("#realNameMsg").show();
	            realNameFlag = false;
				return false;
			}else{
				if(!reg.test(memRealName)){
					$("#realNameMsg").text("真实姓名格式错误!");
		            $("#realNameMsg").addClass("errorMsg");
		            realNameFlag = false;
					return false;
				}else{
					$("#realNameMsg").hide();
					realNameFlag = true;
					return true;
				}
			}
        }
      
       function checkEmail(){
			var email=$.trim($("#email").val());
			 var reg= new RegExp("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
			if(email.length==0){
				$("#emailMsg").html("邮箱不能为空!");
				$("#emailMsg").show();
	           emailFlag = false;
				return false;
			}else{
				if(!reg.test(email)){
					$("#emailMsg").text("邮箱格式错误!");
		            $("#emailMsg").addClass("errorMsg");
		            emailFlag = false;
					return false;
				}else{
					$("#emailMsg").hide();
					emailFlag = true;
					return true;
				}
			}
        }
        
	</script>

	</head>
	<body onload="ajaxRole();">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 操作员管理 &gt;&gt; 用户管理
		</div>
		<s:form action="system/user" method="post" theme="simple"
			onsubmit="document.getElementById('submitInput').disabled = true;return true;">
			<s:hidden name="method" id="method" />

			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">
				<tr>
					<th align="right" width="20%">
						<span class="Color5">* </span>登陆名称：
					</th>
					<td width="30%">
						<s:if test="method=='addSave'">
							<s:textfield name="userDTO.userName" id="userName" maxlength="20"
								cssClass="formInput" onblur="testUserName();" onkeyup = "allowEnNu(this);" />
							<s:label id="message" cssClass="errorMsg"
								cssStyle="display:none;"></s:label>
						</s:if>
						<s:else>
							<s:property value="userDTO.userName" />
							<s:hidden name="userDTO.userName" id="userName" />
							<s:label id="message" cssClass="errorMsg"
								cssStyle="display:none;"></s:label>
						</s:else>
					</td>
					<th align="right" width="20%">
						<span class="Color5">* </span>真实姓名：
					</th>
					<td width="30%">
						<s:textfield name="userDTO.realName" id="realName" maxlength="20"
							onblur="checkRealName();" cssClass="formInput" />
						<s:label id="realNameMsg" cssClass="errorMsg"
							cssStyle="display:none;"></s:label>
					</td>
				</tr>
				<tr>
					<th align="right">
						用户级别：
					</th>
					<td colspan="">
						<s:radio name="userDTO.userLevel" id="userLevel"
							list="#request.userLevelValues" listKey="key" listValue="value"
							onclick="changeLevel();ajaxRole();" />
					</td>
					<th align="right">
						<span class="Color5">* </span>邮箱：
					</th>
					<td colspan="3">
						<s:textfield name="userDTO.email" id="email" maxlength="20"
							onblur="checkEmail();" cssClass="formInput" />
						<s:label id="emailMsg" cssClass="errorMsg"
							cssStyle="display:none;"></s:label>
					</td>
				</tr>
				<!-- 总部 -->
				<s:if test="#session.user_session.userLevel==0">
					<tr class="level0"
						<s:if test="userDTO.userLevel!=0">style="display: none;"</s:if>>
						<th align="right">
							发卡机构：
						</th>
						<td colspan="3">
							<s:property value="#request.redpassOrganName" />
						</td>
					</tr>
					<tr class="level1"
						<s:if test="userDTO.userLevel!=1">style="display: none;"</s:if>>
						<th align="right">
							发卡机构：
						</th>
						<td>
							<s:select name="userDTO.organId" id="organId"
								list="#request.organValues" listKey="key" listValue="value"
								cssClass="formSelect" onchange="ajaxRole();" />
							<s:label id="organIdMsg" cssClass="errorMsg"
								cssStyle="display:none;"></s:label>
						</td>
						<th align="right">
							是否代理商：
						</th>
						<td>
							<s:select name="userDTO.dlsFlag" id="dlsFlag"
								list="#request.visibleValues" listKey="key" listValue="value"
								cssClass="formSelect" />
						</td>
					</tr>

				</s:if>
				<!-- 发卡机构 -->
				<s:if test="#session.user_session.userLevel==1">
					<tr class="level1"
						<s:if test="userDTO.userLevel!=1">style="display: none;"</s:if>>
						<th align="right">
							发卡机构：
						</th>
						<td>
							<s:property value="userDTO.organName" />
							<s:hidden name="userDTO.organId" id="organId" />
						</td>
						<th align="right">
							是否代理商：
						</th>
						<td>
							<s:select name="userDTO.dlsFlag" id="dlsFlag"
								list="#request.visibleValues" listKey="key" listValue="value"
								cssClass="formSelect" />
						</td>
					</tr>
				</s:if>
				<tr class="level2"
					<s:if test="userDTO.userLevel!=2">style="display: none;"</s:if>>
					<th align="right">
						商户：
					</th>
					<td colspan="3">
						<s:if
							test="#session.user_session.userLevel==0||#session.user_session.userLevel==1">
							<s:select name="userDTO.merId" id="merId"
								list="#request.merchantValues" listKey="key" listValue="value"
								cssClass="formSelect" onchange="ajaxRole();" />
							<s:label id="merIdMsg" cssClass="errorMsg"
								cssStyle="display:none;"></s:label>

						</s:if>
						<s:else>
							<s:property value="userDTO.merName" />
							<s:hidden name="userDTO.merId" id="merId" />
						</s:else>
					</td>
				</tr>
				<tr>
					<th align="right">
						<span class="Color5">* </span>手机号码：
					</th>
					<td>
						<s:textfield name="userDTO.teleNo" id="teleNo" maxlength="11"
							cssClass="formInput"
							onblur="conPerTeleNoBlur(this);" />
						<label id="realNameError" class="errorMsg" style="display: none;"></label>
					</td>
					<th align="right">
						状态：
					</th>
					<td>
						<s:select name="userDTO.status" id="status"
							list="#request.statusValues" listKey="key" listValue="value"
							cssClass="formSelect" />
					</td>
				</tr>
				<tr>
					<th align="right">
						角色：
					</th>
					<td colspan="3" id="role"></td>
				</tr>
				<tr>
					<th align="right">
						密码错误次数：
					</th>
					<td id="wrpass">
						<s:property value="userDTO.wrpass" />
					</td>
					<th align="right">
						最后登陆时间：
					</th>
					<td>
						<s:property value="userDTO.lastLoginTime" />
					</td>
				</tr>
			</table>
			<div class="formTableBottom">
				<s:if test="method=='addSave'">
					<my:permission key='sy-9103-02' value='用户添加'>
						<input id="submitInput" type="submit" class="formButton"
							value="保 存" onclick="return check();" />
					</my:permission>
				</s:if>
				<s:else>
					<my:permission key='sy-9103-03' value='用户修改'>
						<input id="submitInput" type="submit" class="formButton"
							value="保 存" onclick="return check();" />
					</my:permission>
				</s:else>
				<input type="button" class="formButton" value="返 回"
					onclick="go('system/user!list')" />
			</div>
		</s:form>
	</body>
</html>