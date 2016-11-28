<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.spring.demo.test.object.TUser"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <%@ taglib prefix="ww" uri="/webwork" %>
    <title>add_info</title>
    
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
		<h3>add_info资料</h3> <br>
				<div id="2">							
					 <ww:form  action="uinfo!saveAndUpdate" >  
					 <ww:hidden name="info.id"/><ww:hidden name="info.fid" />
		    	电话: <ww:textfield name="info.mobile" /> <br>
		    	地址：<ww:textfield name="info.addr" /> <br>
		    	年龄： <ww:select  name="info.ages"  list="{'1','2','3','4','5','6'}" value="info.ages.charAt(0)"/>	<br>
		    	Email	<ww:textfield name="info.email"/> <br>
		    	<br>
		    		 <ww:submit value="add_info......" />  
		     	</ww:form>	
		     	</div>
	
  </body>
</html>
