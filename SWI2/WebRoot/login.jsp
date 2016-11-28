<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="/webwork" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    
    <title>login</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	<ww:if test="fieldErrors['name']!=null">	
		<ww:property value="fieldErrors['name']"/>
	</ww:if> 
	 <br>
	<h1>登录(02)</h1> <br>
    <ww:form  action="login!login" method="post" > 
    	<input type="hidden" name="opt" id="opt" value="login">
    	用户名：<ww:textfield name="user.uname" /> <br>
    	密  码：<ww:password name="user.pwd" /> <br><br>
   			 <input type="submit" value="Login...">
    		 &nbsp;&nbsp; <a href="regedit.jsp" target="bank">Regedit...</a> 
     </ww:form>	
  </body>
</html>
