<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Random"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>${title}</title>
<link rel="Shortcut Icon" href="images/time.png" type="image/x-icon" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
	type="image/x-icon" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/style1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script src="js/common.js"></script>
<style>
* {
	padding: 0;
	margin: 0 auto;
	font-size: 12px;
}

.Spacer {
	clear: both;
	height: 0;
	line-height: 0;
	font-size: 0;
	overflow: hidden;
}

img {
	border: 0;
}

body {
	background: url(images/login/main_bj.jpg) no-repeat;
	background-position: center 144px;
	background-attachment: fixed;
	background-color: #6BADF6;
	font-family: "SimSun", "微软雅黑", "Arial Narrow";
	background-attachment: fixd;
	font-size: 12px;
	color: #666;
	margin-left: 570px;
	margin-top: 50px;
}

.R_login {
	width: 480px;
	margin: 0;
	overflow: hidden;
	display: inline;
}

.page-container {
	margin-top: 144px;
}

//
登陆界面		
		.r_hy_tab {
	font-size: 12px;
	color: #666;
	margin-top: 100px;
}

.r_hy_inp {
	width: 174px;
	height: 25px;
	border: 1px solid #ccc;
	border-top: 1px solid #999;
	line-height: 25px;
	font-size: 14px;
	padding-left: 1px;
	color: #333;
	font-family: Arial;
}

.r_hy_inp02 {
	width: 150px;
}

.r_hy_btn {
	height: 38px;
	width: 250px;
	background: url(images/login/denglu.png) no-repeat;
	border: none;
	cursor: pointer
}

.r_hy_footer {
	text-align: center;
	color: #fff;
}
</style>
<script language="JavaScript">
			function checkForm(){
				if($.trim($("#userName").val()) == ''){
					alert("登录账号不能为空！");
					$("#userName").focus();
					return false;
				}
				if($.trim($("#passWord").val()) == ''){
					alert("登录密码不能为空！");
					$("#passWord").focus();
					return false;
				}
				if(${isFormal}){
					if($.trim($("#authCode").val()) == ''){
						alert("验证码不能为空！");
						$("#authCode").focus();
						return false;
					}
				}
				return verifyUKey(${isVerifyUkey});
			}
		</script>

</head>
<!-- 	<body style="position: relative; height:100%; width:100%; margin:0; background:url('images/bg.jpg') "> 
  -->
<body
	style="background-image: url(images/main_bj.jpg) no-repeat float:left;">
	<div id="ukey"></div>
	<div class="R_login">
		<div class="r_hy">

			<div class="page-container">

				<form name="frmlogin" action="system/login!login" method="post"
					onsubmit="return checkForm();">
					<input name="userDTO.keyID" type="hidden" id="keyID" size="20" />
					<input name="userDTO.rnd" type="hidden" id="rnd"
						value="<%=session.getAttribute("rnd") %>" /> <input
						name="userDTO.return_EncData" type="hidden" id="return_EncData" />
					<table width="100%" border="0" cellspacing="15" class="r_hy_tab"
						align="center">
						<tr>
							<td><input name="userDTO.userName" id="userName"
								class="username" placeholder="用户名" type="text" class="r_hy_inp"
								maxlength="20" /></td>
						</tr>
						<tr>
							<td><input name="userDTO.passWord" class="password"
								type="password" placeholder="密码" id="passWord" class="r_hy_inp"
								maxlength="20" /></td>
						</tr>
						<tr>
							<td>
								<!--				    <input type="text" name="userDTO.authCode" id="authCode" class="r_hy_inp r_hy_inp02" maxlength="4"/>-->
								<!--				    <img src="authimg.servlet?random=<%=new Random(1000)%>" align="absmiddle" onclick="this.src='authimg.servlet?random='+Math.random()" style="cursor:hand;padding-top:25px;" alt="点击刷新"/>-->
							</td>
						</tr>
						<tr>
							<td colspan="2"><label><input type="submit"
									name="Submit" class="r_hy_btn" value="" /></label></td>
						</tr>
						<tr>
							<td height="50" colspan="2" align="left"><font color=red
								size="2"><s:actionerror /></font></td>
						</tr>
					</table>
				</form>
			</div>
			<br class="Spacer" />
		</div>
</body>
</html>