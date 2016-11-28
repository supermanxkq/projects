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
	<title>司机信息管理</title>
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
		var driverId = $.trim($("#driverId").val());
		var memRealName = $.trim($("#memRealName").val());    
		var teleNo = $.trim($("#teleNo").val());  
		var personId = $.trim($("#personId").val());   
		var driverNo = $.trim($("#driverNo").val());
		var contractNo = $.trim($("#contractNo").val());    
		var params = {
				"driverDTO.driverId" : driverId,
		        "driverDTO.memRealName" : memRealName,
		        "driverDTO.teleNo" : teleNo,
		        "driverDTO.personId" : personId,
		        "driverDTO.driverNo" : driverNo,
		        "driverDTO.contractNo" : contractNo,
	        "orderProperty" : $("#orderProperty").val(),
	        "orderDirection" : $("#orderDirection").val(),
	        "driverDTO.page" : page
	    }; 
	   ajaxData("base/driver!jsonPageList",params);
	}
</script> 
</head>
<body onload="query(${driverDTO.page });">
	<div class="Position">
			当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 司机信息管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>司机编号:</td>
				<td><s:textfield id="driverId" name="driverDTO.driverId" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>司机姓名:</td>
				 <td><s:textfield id="memRealName" name="driverDTO.memRealName" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>司机手机号:</td>
				<td><s:textfield id="teleNo" name="driverDTO.teleNo" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>司机驾驶证编号:</td>
				<td><s:textfield id="driverNo" name="driverDTO.driverNo" cssClass="formInput" maxlength="20" theme="simple"/></td> 
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="10%">司机编号</th>
			<th width="10%">司机名称</th>
			<th width="10%">司机手机号</th>
			<th width="10%">司机身份证号</th>
			<th width="15%">司机驾驶证编号</th>
			<th width="15%">司机合同编号</th>
			<th width="10%">启用状态</th>
			<th width="10%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<my:permission key='sy-1801-02' value='司机信息添加'>
				<input type="button" class="formButton" value="添加" onclick="go('base/driver!addUI')"/>
			</my:permission>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>