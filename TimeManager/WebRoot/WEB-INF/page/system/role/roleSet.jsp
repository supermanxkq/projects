<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>" />
	<title>商户管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />	
	<script src="js/jquery-1.4.2.min.js"></script>
	<link href="js/jquery/css/jquery.ui.all.css"  rel="stylesheet"  type="text/css" />	
	<script src="js/jquery/jquery.ui.core.js"></script>
	<script src="js/jquery/jquery.ui.widget.js"></script>
	<script src="js/jquery/jquery.ui.mouse.js"></script>
	<script src="js/jquery/jquery.ui.button.js"></script>
	<script src="js/jquery/jquery.ui.draggable.js"></script>
	<script src="js/jquery/jquery.ui.position.js"></script>
	<script src="js/jquery/jquery.ui.resizable.js"></script>
	<script src="js/jquery/jquery.ui.dialog.js"></script>
	<script src="js/common.js"></script>
	<script type="text/javascript">
		
		var ajaxPrivilege = function(){
			var id = consoleDlg.find("#id").val();
		    var organId = $("#organId").val();
			$("#privilege").html("<iframe id='privilegeFrame' name='privilegeFrame' src='system/role!privilegeTree?roleDTO.id="+id+"&roleDTO.organId="+organId+"' width='100%' height='300' frameborder='0' ></iframe>"); 
		}
		
		var check = function() {
			var name = $("#name").val();
		    if (name==''){
		    	alert("角色名称不能为空!");	 
		    	$("#name").focus();
		    	return false;
			}
			
			var ids = "";
			var form = window.frames['privilegeFrame'].document.getElementById("testForm");
			for (var i=0; i<form.elements.length; i++) {
				var element = form.elements[i];
				if (element.name == "id" && element.type=='checkbox'){
					if( element.checked == true ){
						ids = ids + element.value + ",";
					}
				}
			}
			$("#ids").val(ids);
		}
		
	</script>

</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 系统设置 &gt;&gt; 用户管理
	</div>
	<s:form action="system/role" method="post" theme="simple" onsubmit="document.getElementById('submitInput').disabled = true;return true;">
	<s:hidden name="method"/>
	<s:hidden name="roleDTO.id" id="id"/>
	<s:hidden name="ids" id="ids"/>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		<tr>
      		<th align="right" width="20%">角色编号：</th>
       		<td width="30%"><s:property value="roleDTO.code"/><s:hidden name="roleDTO.code" id="code"/></td>
       		<th align="right" width="20%"><span class="Color5">* </span>角色名称：</th>
       		<td width="30%"><s:textfield name="roleDTO.name" id="name" maxlength="20" cssClass="formInput"/></td>
     	</tr>
     	<tr>
     		<th align="right">所属机构：</th>
       		<td>
       			<s:if test="#session.user_session.userLevel==0">
       				<s:select name="roleDTO.organId" id="organId" list="#request.organValues" listKey="key" listValue="value" cssClass="formSelect" onchange="ajaxPrivilege();"/>
       			</s:if>
       			<s:if test="#session.user_session.userLevel==1">
       				<s:property value="roleDTO.organName"/><s:hidden name="roleDTO.organId" id="organId"/>
       			</s:if>
       		</td>
     		<th align="right">状态：</th>
       		<td><s:select name="roleDTO.status" id="status" list="#request.statusValues" listKey="key" listValue="value" cssClass="formSelect"/></td>
     	</tr>
     	<s:if test="#session.user_session.userLevel==0">
     		<tr>
				<th align="right">是否通用角色：</th>
       			<td><s:radio name="roleDTO.isCommon" id="isCommon" list="#request.visibleValues" listKey="key" listValue="value"/></td>
     		</tr>
     	</s:if>
     	<tr>
     		<th align="right">权限：</th>
       		<td id="privilege" colspan="3"><iframe id="privilegeFrame" name="privilegeFrame" src="system/role!privilegeTree?roleDTO.id=${roleDTO.id }&roleDTO.organId=${roleDTO.organId}" width="100%" height="300" frameborder="0" ></iframe></td>
     	</tr>
     	<tr>
     		<th align="right">描述：</th>
       		<td colspan="3"><s:textarea name="roleDTO.memo" id="memo" cssClass="formTextarea"/></td>
     	</tr>
	</table>
	<div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-9102-02' value='角色添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-9102-03' value='角色修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		<input type="button" class="formButton" value="返 回" onclick="go('system/role!list')"/>
	 </div>
	</s:form>
</body>
</html>