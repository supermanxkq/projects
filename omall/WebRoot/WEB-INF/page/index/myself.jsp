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
	<title>账号设置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />	
	<script src="js/jquery-1.4.2.min.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript"><!--
		function check(){
			var realName = $("#realName").val();
			var teleNo =$("#teleNo").val();
			if (realName==''){
		    	alert("真实姓名不能为空!");	 
		    	$("#realName").focus();
		    	return false;
			}
			var passWord = $("#passWord").val();
			var newPassWord = $("#newPassWord").val();
			var confirmNewPassWord = $("#confirmNewPassWord").val();
			if(passWord!=''||newPassWord!=''||confirmNewPassWord!=''){
				if (passWord==''){
			    	alert("旧密码不能为空!");	 
			    	$("#passWord").focus();
			    	return false;
				}
				if (newPassWord==''){
			    	alert("新密码不能为空!");	 
			    	$("#newPassWord").focus();
			    	return false;
				}
				if (confirmNewPassWord==''){
			    	alert("重复密码不能为空!");	 
			    	$("#confirmNewPassWord").focus();
			    	return false;
				}
				if (newPassWord!=confirmNewPassWord){
			    	alert("两次密码输入不一致!");	 
			    	return false;
				}
				if (newPassWord.length<6){
					alert("密码不能少于6位!");
			    	return false;
				}
				if (teleNo==''){
					alert("电话号码不能为空 !");
					$("#teleNoInfo").html("电话号码不能为空 !");
			    	return false;
				}
				var ls = 0; 
				if(newPassWord.match(/([a-z])+/)||newPassWord.match(/([A-Z])+/)){   
				    ls++;
				}
				if(newPassWord.match(/([0-9])+/)){   
				    ls++;
				}
				if(ls<2){
					alert("密码必须包含数字、字母!");	 
			    	return false;
				}
			}
		}
		
		
		
function mobileValidate(obj){
   var phoneNo = obj.value;
   var phonereg =/^((\+86)|(86))?(1)\d{10}$/;
   if((phoneNo=="")||!phonereg.test(phoneNo)){
	   obj.value='';
	   $("#teleNoInfo").html("手机格式错误，请填写正确联系方式.");
      return false;
   }else{
   	   $("#teleNoInfo").html("");
   }
   return true;
 }
	</script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 用户设置 &gt;&gt; 用户设置
	</div>
	<div style="text-align: center;margin: 10px auto;">
	<s:form action="system/index!saveMyself" method="post" theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
	      <tr>
	      	<td colspan="4" style="font-size: 14px;color: #000;font-weight: bold;" align="left">用户信息</td>
	      </tr>
	      <tr>
	       	<th align="right" width="20%">登陆名称：</th>
	        <td align="left" width="30%"><s:property value="userDTO.userName"/><s:hidden name="userDTO.userName"/></td>
	        <th align="right" width="20%">真实姓名：</th>
	        <td align="left" width="30%"><s:textfield name="userDTO.realName" id="realName" maxlength="20" cssClass="formInput"/></td>
	      </tr>
	      <tr>
	       	<th align="right">用户级别：</th>
	        <td align="left"><s:property value="userDTO.userLevelName"/></td>
	        <s:if test="#session.user_session.userLevel==0||#session.user_session.userLevel==1">
	        	<th align="right">机构名称：</th>
	        	<td align="left"><s:property value="#session.user_session.organName"/></td>
	        </s:if>
	        <s:elseif test="#session.user_session.userLevel==2">
	        	<th align="right">商户名称：</th>
	        	<td align="left"><s:property value="#session.user_session.merName"/></td>
	        </s:elseif>
	        <s:else>
	        	<th align="right"></th>
	        	<td align="left"></td>
	        </s:else>
	      </tr>
	      <tr>
	       	<th align="right">联系电话：</th>
	        <td align="left"><s:textfield name="userDTO.teleNo" id="teleNo" maxlength="11" cssClass="formInput"  onblur="mobileValidate(this);"/>&nbsp;&nbsp;&nbsp;<span><font color="grey"><label id="teleNoInfo"></label></font></span></td>
	        <th align="right">角色：</th>
	        <td align="left"><s:property value="userDTO.roleName"/></td>
	      </tr>
	      <tr>
	       	<th align="right">最后登陆时间：</th>
	        <td align="left" colspan="3"><s:property value="userDTO.lastLoginTime"/></td>
	      </tr>
	       <tr>
	      	<td colspan="4" style="font-size: 14px;color: #000;font-weight: bold;" align="left">修改密码</td>
	      </tr>
	      <tr>
	       	<th align="right">旧密码：</th>
	        <td align="left" colspan="3"><input type="password" name="userDTO.passWord" id="passWord" maxlength="20" class="formInput"/></td>
	      </tr>
	      <tr>
	       	<th align="right">新密码：</th>
	        <td align="left" colspan="3"><input type="password" name="userDTO.newPassWord" id="newPassWord" maxlength="20" class="formInput"/></td>
	      </tr>
	      <tr>
	       	<th align="right">重复新密码：</th>
	        <td align="left" colspan="3"><input type="password" name="userDTO.confirmNewPassWord" id="confirmNewPassWord" maxlength="20" class="formInput"/></td>
	      </tr>
	 </table>
	 <div class="formTableBottom">
	 	<input type="submit" class="formButton" value="保 存" onclick="return check();"/>
	 </div>
	 </s:form>
	</div>
</body> 
</html>