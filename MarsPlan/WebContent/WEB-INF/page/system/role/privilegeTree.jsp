<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="com.paySystem.ic.bean.system.Privilege"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<base href="<%=basePath%>" />
<title></title>
<link href="css/style.css" rel="stylesheet" type="text/css" />	
<link rel="StyleSheet" href="dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="dtree/dtree2.js"></script>

</head>
<body onload="init();">
<div class="dtree">

<p><a href="javascript: d.openAll();">全部展开</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javascript: d.closeAll();">全部关闭</a></p>
  
   <script type="text/javascript">
		d = new dTree('d','.','testForm');
		d.add('',-1,'全部');
 		<s:iterator value="#request.modules" >
 			d.add('<s:property value="id" />','<s:property value="parent.id" />','<s:property value="name" />');
	 		<s:iterator value="privileges" >
	 			d.add('<s:property value="id" />','<s:property value="module.id" />','<s:property value="name" />');
	 		</s:iterator>
 		</s:iterator>
 		
 		document.write(d);
 		d.closeAll();
 		
	</script>
	<script type="text/javascript"><!--
		function init(){
			var form = document.getElementById("testForm");
			for (var i=0; i<form.elements.length; i++) {
				var element = form.elements[i];
				
				if ( element.type=='checkbox'){
					<%		
						if(request.getAttribute("rolePrivileges") != null){
							Set<Privilege> perList =(Set<Privilege>)request.getAttribute("rolePrivileges");
							
							if(perList.size()>0&&perList!=null){
								for (Privilege privilege : perList) {
					%>
									if(element.value == '<%=privilege.getId()%>'){
										element.checked = true;
										element.onclick();
									}
					<%
								}
							}
						}
					%>
				}
			}		
		}
		
	</script>
</div>
</body>
</html>
