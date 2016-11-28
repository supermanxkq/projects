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
	<title>油品管理</title>
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
		//查询会员方法
		function query(page) {
			var oilTypeId = $.trim($("#oilTypeName").val());
			var status = $.trim($("#status").val());
		//json数据传输
			var params = {
		        "oilTypeDto.oilTypeName" : oilTypeId,
		        "oilTypeDto.status":status,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "oilTypeDto.page" : page
		    }; 
		   ajaxData("base/oilType!jsonPageList",params);
		}
	</script> 
</head>
<body onload="query(${oilTypeDto.page});">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 油品管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
				<td>油品名称:</td>
				<td><s:textfield id="oilTypeName" name="oilTypeDto.oilTypeName" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>状态:</td>
				<td><s:select name="oilTypeDto.status" id="status"  list="#request.statusValues" listKey="key" listValue="value" headerKey="-1" headerValue="全部"
				 cssClass="formInput" theme="simple"/></td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr> 
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%"><a name="oilTypeId" >油品编号</a></th>
			<th width="10%"><a name="oilTypeName">油品名称</a></th>
			<th width="10%"><a name="operId" >操作人</a></th>
			<th width="5%"><a name="status" >状态</a></th>
			<th width="8%"><a name="updateTime" >更新时间</a></th>
			<s:if test="#session.user_session.userLevel!=2">
				<th width="5%">相关操作</th>
			</s:if>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<s:if test="#session.user_session.userLevel!=2">
				<my:permission key='sy-1211-02' value='油品添加'>
					<input type="button" class="formButton" value="添加" onclick="go('base/oilType!addUI')"/>
				</my:permission>
			</s:if>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>