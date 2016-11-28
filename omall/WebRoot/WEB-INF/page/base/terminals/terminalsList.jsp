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
	<title>终端管理</title>
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
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
		//查询方法
		function query(page) {
			var organId = $.trim($("#organId").val());
			
			var orgName = $.trim($("#orgName").val());
			
			var merId = $.trim($("#merId").val());
			
			var merName = $.trim($("#merName").val());
			
			var termId = $.trim($("#termId").val());

			var state = $.trim($("#state").val());

			var params = {
			
				"terminalsDTO.organId" : organId,
		        "terminalsDTO.orgName" : orgName,
		        "terminalsDTO.merId" : merId,
		        "terminalsDTO.merName" : merName,
		        "terminalsDTO.termId":termId,
		        "terminalsDTO.state":state,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "terminalsDTO.page" : page
		    }; 
		   ajaxData("base/terminals!jsonPageList",params);
		}
	</script> 
</head>
<body onload="query(${terminalsDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 终端管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
		<table class="searchTable" width="800px" cellpadding="0" cellspacing="0">
		  <s:if test="#session.user_session.userLevel==0">
		     <tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>机构编号:</td>
				<td><s:textfield id="organId" name="terminalsDTO.organId" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>机构名称:</td>
				<td><s:textfield id="orgName" name="terminalsDTO.orgName" cssClass="formInput" maxlength="20" theme="simple"/></td>
			    <td>终端编号:</td>
			    <td><s:textfield id="termId" name="terminalsDTO.termId" cssClass="formInput" maxlength="20" theme="simple"/></td>
			</tr>
			<tr>
			    <td></td>
				<td>商户编号:</td>
				<td><s:textfield id="merId" name="terminalsDTO.merId" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>商户名称:</td>
				<td><s:textfield id="merName" name="terminalsDTO.merName" cssClass="formInput" maxlength="20" theme="simple"/></td>
	        	<td>终端状态:</td>
	        	<td><s:select name="terminalsDTO.state" id="state" list="#request.condValues" headerKey="-1" headerValue="全部" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
	        	<td align="center"><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr> 
		  </s:if>
			<s:if test="#session.user_session.userLevel==1">
			   <tr>
			      <td><img src="images/fd.jpg" /></td>
			      <td>终端编号：</td>
			      <td><s:textfield id="termId" name="terminalsDTO.termId" cssClass="formInput" maxlength="20" theme="simple"/></td>
			      <td>商户编号:</td>
				  <td><s:textfield id="merId" name="terminalsDTO.merId" cssClass="formInput" maxlength="20" theme="simple"/></td>
			   </tr>
			   <tr>
			   <td></td>
			    <td>商户名称:</td>
				<td><s:textfield id="merName" name="terminalsDTO.merName" cssClass="formInput" maxlength="20" theme="simple"/></td>
	        	<td>终端状态:</td>
	        	<td><s:select name="terminalsDTO.state" id="state" list="#request.condValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
	        	<td align="center"><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			   </tr>
			</s:if>
			<s:if test="#session.user_session.userLevel==2">
			   <tr>
			      <td><img src="images/fd.jpg" /></td>
			      <td>终端编号:</td>
			      <td><s:textfield id="termId" name="terminalsDTO.termId" cssClass="formInput" maxlength="20" theme="simple"/></td>
	        	<td>终端状态:</td>
	        	<td><s:select name="terminalsDTO.state" id="state" list="#request.condValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
	        	<td align="center"><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			   </tr>	  
			</s:if>
			
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="10%">终端编号</th>
			<th width="15%">终端名称</th>
			<th width="10%">收单机构</th>
			<th width="10%">所属商户</th>
			<th width="6%">状态</th>
			<th width="10%">更新时间</th>
			<th width="6%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
	 <s:if test="#session.user_session.userLevel!=2">
		<div class="Fl">
			<my:permission key='sy-1202-02' value='终端添加'>
				<input type="button" class="formButton" value="添加" onclick="go('base/terminals!addUI')"/>
			</my:permission>
		</div>
	</s:if>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>