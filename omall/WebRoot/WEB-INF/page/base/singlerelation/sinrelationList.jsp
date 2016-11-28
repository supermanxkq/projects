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
	<title>收单关系管理</title>
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
			var merId = $.trim($("#merId").val());  //商户编号 
			var merName = $.trim($("#merName").val());//商户名称 
            var organName = $.trim($("#organName").val());//机构名称
            var status = $("#status").val();
			var params = {
				"singleRelationDTO.merId" : merId,
		        "singleRelationDTO.merName" : merName,
		        "singleRelationDTO.organName":organName,
		        "singleRelationDTO.status":status,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "singleRelationDTO.page" : page
		    }; 
		   ajaxData("base/sinrelation!jsonPageList",params);
		}
	</script> 
</head>
<body onload="query(${singleRelationDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 收单关系管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>商户编号:</td>
				<td><s:textfield id="merId" name="singleRelationDTO.merId" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td> &nbsp;商户名称:</td>
				<td><s:textfield id="merName" name="singleRelationDTO.merName" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td> &nbsp;发卡机构名称:</td>
				<td><s:textfield id="organName" name="singleRelationDTO.organName" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td> &nbsp;启用状态:</td>
				<td><s:select name="singleRelationDTO.status" id="status" list="#request.status" headerKey="-1" headerValue="全部" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%"><a name="merchants" class="sort">商户编号</a></th>
			<th width="10%"><a name="merchants" class="sort">商户名称</a></th>
			<th width="10%"><a name="sinOrgans" class="sort">收单机构</a></th>
			<th width="10%"><a name="organs" class="sort">发卡机构</a></th>
			<th width="10%">启用状态</th>
			<th width="8%"><a name="updateTime" class="sort">更新时间</a></th>
			<th width="5%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
	   <s:if test="#session.user_session.userLevel==0">
		<div class="Fl">
			<my:permission key='sy-1203-02' value='收单关系添加'>
				<input type="button" class="formButton" value="添加" onclick="go('base/sinrelation!addUI')"/>
			</my:permission>
		</div>
		</s:if>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>