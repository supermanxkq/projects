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
	<title>企业信息管理</title>
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
			var companyId = $.trim($("#companyId").val());
			var companyName = $.trim($("#companyName").val());
			var status = $.trim($("#status").val());
			var params = {
				"companyInfoDTO.companyId" : companyId,
		        "companyInfoDTO.companyName" : companyName,
		        "companyInfoDTO.status":status,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "companyInfoDTO.page" : page
		    }; 
		   ajaxData("base/compInfo!jsonPageList",params);
		   
		}
		
		
	</script> 
</head>
<body onload="query(${companyInfoDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 企业信息管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>企业编号:</td>
				<td><s:textfield id="companyId" name="companyInfoDTO.companyId" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>企业名称:</td>
				<td><s:textfield id="companyName" name="companyInfoDTO.companyName" cssClass="formInput" maxlength="20" theme="simple"/></td>
	        	<td>状态:</td>
	        	<td><s:select name="companyInfoDTO.status" id="status" list="#request.status" headerKey="-1" headerValue="全部" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="10%"><a name="companyId" class="sort" >企业编号</a></th>
			<th width="10%"><a name="companyName" class="sort" >企业名称</a></th>
			<th width="6%"><a name="compName" class="sort" >企业人数</a></th>
			<th width="10%"><a name="comTele" class="sort" >企业电话</a></th>
			<th width="6%"><a name="comConPer" class="sort" >企业联系人</a></th>
			<th width="6%"><a name="status" class="sort">状态</a></th>
			<th width="6%"><a name="createTime" class="sort" >创建时间</a></th>
			<th width="6%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<my:permission key='sy-1701-02' value='企业信息添加'>
				<input type="button" class="formButton" value="添加" onclick="go('base/compInfo!addUI')"/>
			</my:permission>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>