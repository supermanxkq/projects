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
	<title>角色管理</title>
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
	<script type="text/javascript"><!--
		
		//查询方法
		function query(page) {
		
			var name = $.trim($("#name").val());
			var status = $.trim($("#status").val());
			var params = {
		        "roleDTO.name" : name,
		        "roleDTO.status" : status,
		        "roleDTO.page" : page
		    }; 
		   ajaxData("system/role!jsonPageList",params);
		}
	--></script> 
</head>
<body onload="query(1);">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 系统设置 &gt;&gt; 角色管理
	</div>
	
	<div class="search">
		<div class="Fl">
			<my:permission key='sy-9102-02' value='角色添加'>
				<input type="button" class="formButton" value="添加" onclick="go('system/role!addUI')"/>
			</my:permission>
		</div>
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
			    <td>角色名称:</td>
				<td><input type="text" id="name" name="name" class="formInput"/></td>
				<td>状态:</td>
				<td>
						<s:select name="status" id="status"
							list="#request.statusValues" listKey="key" listValue="value"
							cssClass="formInput"
							theme="simple" />
				</td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="5%">序号</th>
			<th width="10%">角色编号</th>
			<th width="15%">角色名称</th>
			<th width="8%">所属机构</th>
			<th width="8%">状态</th>
			<th width="10%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<my:permission key='sy-9102-02' value='角色添加'>
				<input type="button" class="formButton" value="添加" onclick="go('system/role!addUI')"/>
			</my:permission>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>