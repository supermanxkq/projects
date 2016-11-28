<%@ page contentType="text/html; charset=gbk"%>
<%@include file="../common/admin_head.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%--������ת����¼ҳ--%>
<s:if test="#session.admin==null">
<jsp:forward page="/admin/tologin.htm"/>
</s:if>
<%--������ת����¼ҳ--%>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>��̨����ϵͳ �汾:3.0</title>
<link href="<%=basepath%>/css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body style="padding-top:10px;">
<center>
	<div class="titleText">���¹���</div>

	<div>
		<s:form action="article_browseArticle">
		<s:hidden name="selType" value="1"/>
        <s:hidden name="newquery" value="yes"/>
		   �ؼ���:<s:textfield name="keyWord"/>
		   <s:submit value="��ѯ" style="cursor:pointer; width:40px; height:20px;"/>
           <s:url value="/admin/addArticle.jsp" id="addArticleUrl">
			<s:param name="tmp"><%=System.currentTimeMillis()%></s:param>
		   </s:url>
           <input type="button" value="��������" onClick="javascript:window.location='${addArticleUrl}'" style="cursor:pointer; width:90px; height:20px;"/>	
		</s:form>
	</div>
	
	<div class="displayTable">
		<display:table name="pageList" id="row" pagesize="15" export="false" class="displaytag" requestURI="article_browseArticle.action" >
			<display:column title="�к�" sortable="false"  style="text-align:center;"> 
				${row_rowNum} 
			</display:column>
			<display:column property="title" title="���±���" sortable="false"  style="text-align:center;"/>
			<display:column property="content" title="��������" sortable="false"  style="text-align:center;"/>
			<display:column property="color" title="��ʾ��ɫ" sortable="false"  style="text-align:center;"/>
			<display:column property="cdate" title="����ʱ��" sortable="false" style="text-align:center;"/>
			
			<display:column title="�༭" media="html" style="text-align:center;">
				<a href="${addArticleUrl}">����</a>&nbsp;
				<s:url action="article_viewArticle" id="viewArticle">
					<s:param name="id" value="%{#attr.row.id}" />
				</s:url>
				<a href="${viewArticle}">�鿴</a>&nbsp;									
				<s:url action="article_editArticle" id="modifyArticle">
					<s:param name="id" value="%{#attr.row.id}" />
				</s:url>
				<a href="${modifyArticle}">�޸�</a>&nbsp;				
				<s:url action="article_delArticle" id="delArticle">
					<s:param name="id" value="%{#attr.row.id}" />
				</s:url>
				<a href="javascript:delcur('${delArticle}')">ɾ��</a>			
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
		if(confirm("�����Ҫɾ����ǰ�û���")){
			window.location=urlstr;
		}			
 }

</script>
</body>
</html>