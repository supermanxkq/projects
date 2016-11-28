<%@ page contentType="text/html; charset=gbk"%>
<%@include file="../common/admin_head.jsp"%>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>后台管理系统 版本:3.0</title>
<link href="<%=basepath%>/css/admin.css" rel="stylesheet" type="text/css" />

</head>
<body>
<center>
	<br/><div class="titleText">文章详情</div><br/>
	<div class="formDiv">
		<table width="660" align="center" border="0" cellpadding="0" cellspacing="0">
		  <tr>
		    <td align="right"><span class="redText">*</span>文章标题：</td>
		    <td><s:textfield name="title" size="40"/></td>
		    		       
		  </tr>
		  
		    <tr>
		    <td align="right"><span class="redText">*</span>文章内容：</td>
		    <td><s:textarea name="content" cols="60" rows="5" ></s:textarea></td>
		   
		   	    	    
		 
		  </tr>
		
		 <tr>
		    <td align="right"><span class="redText">*</span>显示颜色：</td>
		    <td><s:radio name="color" list="#{'1':'绿色','0':'红色'}" listKey="key" listValue="value"/></td>		    	    	    
		  </tr>
		</table>
		<br>
	  <div align="center">
	    <input type="button" name="btn_ret" value="返回" onClick="window.location='article_browseArticle.action';">
	  </div>
	</div>
</center>
<s:if test="#request.privilege!=null && #request.privilege.length()>0">
	<script language="javascript">
		recoverPrim('${privilege}');
	</script>	
</s:if>
</body>
</html>
