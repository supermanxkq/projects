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
	<title>会员管理</title>
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
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<script src="js/base/member.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript"></script> 
</head>
<body onload="query(${memberDTO.page});">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 会员管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
			    <td>会员编号:</td>
				<td><s:textfield id="memId" name="memberDTO.memId" cssClass="formInput" maxlength="20" theme="simple" onkeyup="allowEnCnNu(this);"/></td>
				<td>会员姓名:</td>
				<td><s:textfield id="memRealName" name="memberDTO.memRealName" cssClass="formInput" maxlength="20" theme="simple" onkeyup="allowEnCnNu(this);"/></td>
			     <td>手机号码：</td>
			    <td><s:textfield id="teleNo" name="memberDTO.teleNo" cssClass="formInput" maxlength="20" theme="simple" onkeyup="allowEnCnNu(this);"/></td>
			</tr>
			<tr>
				 <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态:</td>
				<td><s:select name="memberDTO.status" id="status"  list="#request.statusValues" listKey="key" listValue="value" headerKey="-1" headerValue="全部"
				 cssClass="formInput" theme="simple"/></td>
				 <td>会员等级：</td>
			     <td><s:select name="memberDTO.gradeId" id="gradeId" cssStyle="width:155px;"
			        list="#request.memGradeValues" listKey="key" listValue="value" headerKey="-1" headerValue="全部" cssClass="formSelect" theme="simple"/>			        
			     </td>
				 <td></td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr> 
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%"><a name="memId" class="sort">会员编号</a></th>
			<th width="10%">会员名称</th>
			<th width="10%">手机号码</th>
			<th width="10%">会员等级</th>
			<th width="5%">状态</th>
			<th width="8%"><a name="updateTime" class="sort">注册时间</a></th>
				<th width="5%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>