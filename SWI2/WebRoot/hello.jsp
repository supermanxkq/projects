<%@ taglib prefix="ww" uri="webwork" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head><title>welcome</title></head>
<body>

	 <form  action="regedit!regeditEdit" name="wForm" method="post">	    
  	 <form>	
  	 
	 
	 <div>Dear [${user.uname}],welcome come to Bolg! <A HREF='regedit.jsp'  >edit info</A></div>

	 <div> <A HREF="add.jsp">add</A><A HREF="">delete</A></div>

	<div>
		<table>	
		<tr>
			<th>ages</th>
			<th>mobile</th>
			<th>addr</th>
			<th>email</th>
			<th>opt</th>			
		</tr>	
			<#list infoList as info>	
		<tr>
			<td>${info.ages}</td>
			<td>${info.mobile}</td>
			<td>${info.addr}</td>
			<td>${info.email}</td>
			<td><A HREF="info?infoId=${info.id}">delete</A></td>
		</tr>						
			</#list>
		</table>
	</div>
	
	 <div>Copyright2008 1998 - 2008 XXbolg. All Rights Reserved </div>
	 
	 
</body>
<script type="text/javascript">
	function regedit_info(uname){	
		document.forms[0].action="regedit!regeditEdit.action?uname="+uname;		
		document.forms[0].submit();
	}
</script>
</html>