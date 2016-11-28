<%@ page contentType="text/html; charset=gbk"%>
<%@include file="../common/admin_head.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%--加入跳转到登录页--%>
<s:if test="#session.admin==null">
<jsp:forward page="/admin/tologin.htm"/>
</s:if>
<%--加入跳转到登录页--%>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>后台管理系统 版本:3.0</title>
<link href="<%=basepath%>/css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body style="padding-top:10px;">
<center>
	<div class="titleText">文章管理</div>

	<div>
		<s:form action="article_browseArticle">
		<s:hidden name="selType" value="1"/>
        <s:hidden name="newquery" value="yes"/>
		   关键字:<s:textfield name="keyWord"/>
		   <s:submit value="查询" style="cursor:pointer; width:40px; height:20px;"/>
           <s:url value="/admin/addArticle.jsp" id="addArticleUrl">
			<s:param name="tmp"><%=System.currentTimeMillis()%></s:param>
		   </s:url>
           <input type="button" value="新增文章" onClick="javascript:window.location='${addArticleUrl}'" style="cursor:pointer; width:90px; height:20px;"/>	
		</s:form>
	</div>
	
	<div class="displayTable">
		<display:table name="pageList" id="row" pagesize="15" export="false" class="displaytag" requestURI="article_browseArticle.action" >
			<display:column title="行号" sortable="false"  style="text-align:center;"> 
				${row_rowNum} 
			</display:column>
			<display:column property="title" title="文章标题" sortable="false"  style="text-align:center;"/>
			<display:column property="content" title="文章内容" sortable="false"  style="text-align:center;"/>
			<display:column property="color" title="显示颜色" sortable="false"  style="text-align:center;"/>
			<display:column property="cdate" title="创建时间" sortable="false" style="text-align:center;"/>
			
			<display:column title="编辑" media="html" style="text-align:center;">
				<a href="${addArticleUrl}">新增</a>&nbsp;
				<s:url action="article_viewArticle" id="viewArticle">
					<s:param name="id" value="%{#attr.row.id}" />
				</s:url>
				<a href="${viewArticle}">查看</a>&nbsp;									
				<s:url action="article_editArticle" id="modifyArticle">
					<s:param name="id" value="%{#attr.row.id}" />
				</s:url>
				<a href="${modifyArticle}">修改</a>&nbsp;				
				<s:url action="article_delArticle" id="delArticle">
					<s:param name="id" value="%{#attr.row.id}" />
				</s:url>
				<a href="javascript:delcur('${delArticle}')">删除</a>			
			</display:column>
		</display:table>  		
	</div>
</center>
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
<script type="text/JavaScript">
  function delcur(urlstr){
		if(confirm("您真的要删除当前用户吗？")){
			window.location=urlstr;
		}			
 }

</script>
</body>
</html>