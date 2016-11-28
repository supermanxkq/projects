<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.spring.demo.test.object.TUser"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <%@ taglib prefix="ww" uri="/webwork" %>
    <title>regedit</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
	<%
		
		TUser u=null;
		if(null != session.getAttribute("user_bean"))
			u=(TUser)session.getAttribute("user_bean"); 
		if(!"edit".equals(request.getParameter("opt"))){
			u=null;
			session.setAttribute("user_bean",null); 
		}
		if(u!=null){
	%>
			<h3>修改注册资料</h3> <br>
			<div id="1">	
			 <ww:form  action="regedit!regeditEditSave" >
			 		<ww:hidden name="user.uname"  value="<%=u.getUname()%>" /> 
		    	中文名：<ww:textfield name="user.cnname"  value="<%=u.getCnname() %>" /> <br>
		    	性别：  <ww:select  name="user.sex"  list="{'男','女'}" value="<%=u.getSex() %>" />	<br><br>  
		    	<!-- 如果是后台直接绑定过来对象user的，可以为select设置user.sex的值....如：value="user.sex.charAt(0)"...即可(此处不做处理了,请参考info.jsp页面) -->  	
		  			  <ww:submit value="regeditEdit..." /> 
	  		   </ww:form>	
	  	 	 </div>
	
	<%	
		}else{
	%>
	
				<h3>注册资料</h3> <br>
				<div id="2">	
					 <ww:form  action="regedit!regedit" >  
		    	用户名：<ww:textfield name="user.uname"/> <br>
		    	密  码：<ww:password name="user.pwd" /> <br>
		    	中文名：<ww:textfield name="user.cnname" /> <br>
		    	性别： <ww:select  name="user.sex"  list="{'男','女'}" />	<br><br>
		    		 <ww:submit value="regedit......" />  
		     	</ww:form>	
		     	</div>
	
	<%	
		}	
	 %>
	
  </body>
</html>
