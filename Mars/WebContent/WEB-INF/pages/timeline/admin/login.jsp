<%@ page contentType="text/html; charset=gbk"%>
<%@include file="../common/admin_head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>后台管理系统 版本:3.0</title>
<link href="<%=basepath%>/css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
<br/><br/><br/>
<s:form action="admin_login">
<table border="0" cellpadding="0" cellspacing="0" align="center" width="476" height="298" background="<%=basepath%>/images/login_back.jpg">
<tr height="110">
	<td colspan="5"></td>
</tr>
<tr>
	<td width="60"></td>
	<td class="labeltext" width="100">登录账号:</td>
	<td><s:textfield name="loginname" size="26"/></td>
	<td></td>
	<td width="60"></td>
</tr>
<tr>
	<td width="60"></td>
	<td class="labeltext" width="100">登录密码:</td>
	<td><s:password name="loginpwd" size="26"/></td>
	<td></td>
	<td width="60"></td>
</tr>
<tr>
	<td width="60"></td>
	<td class="labeltext" width="100">验证码:</td>
	<td><s:textfield name="rand" size="26"/>&nbsp;<img src="<%=basepath%>/common/rand.jsp" height="16" border="1"/></td>
	<td></td>
	<td width="60"></td>
</tr>
<tr>
	<td colspan="5" align="center">
		<s:submit key="login_submit"/>&nbsp;
		<s:reset key="label_reset"/>
	</td>
</tr>
<tr height="50">
	<td colspan="5" align="center"></td>
</tr> 
</table>
</s:form>
<s:if test="hasFieldErrors()">
	<cms:msgdialog basepath="<%=basepath%>">
		<s:fielderror/>
	</cms:msgdialog>
</s:if>
<s:if test="hasActionMessages()">
	<cms:msgdialog basepath="<%=basepath%>">
		<s:actionmessage/>
	</cms:msgdialog>
</s:if>
</body>
</html>
