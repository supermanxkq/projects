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
	<title>油厂信息管理</title>
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
	<script src="js/datepicker/WdatePicker.js"></script>
	<script src="js/common.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
		
		//查询方法
		function query(page) {
			
			var oilFactId = $.trim($("#oilFactId").val());//使用状态
			var oilFactName = $.trim($("#oilFactName").val());//使用状态
            var status = $.trim($("#status").val());//使用状态
            var beginTime = $.trim($("#beginTime").val());//时间（起始）
            var endTime = $.trim($("#endTime").val());//时间（截止）
			
			var params = {
				"oilfactDto.oilFactId":oilFactId,
				"oilfactDto.oilFactName":oilFactName,				
		        "oilfactDto.status":status,
		        "oilfactDto.beginTime":beginTime,
		        "oilfactDto.endTime":endTime,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "oilfactDto.page" : page
		    }; 
		   ajaxData("oilfactory/oilfactoryaction!jsonPageList",params);
		}
	</script> 
</head>
<body onload="query(${oilfactDto.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 油厂管理 &gt;&gt; 油厂信息管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>

	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
	            <td>油厂编号:</td>
	            <td><s:textfield id="oilFactId" name="oilfactDto.oilFactId" cssStyle="width:150px;" maxlength="20" theme="simple"/></td>
	            <td>油厂名称:</td>
	            <td><s:textfield id="oilFactName" name="oilfactDto.oilFactName" cssStyle="width:150px;" maxlength="20" theme="simple"/></td>
	            <td>使用状态:</td>
			    <td><s:select id="status" name="oilfactDto.status" list="#request.status" listKey="key" listValue="value" cssStyle="width:153.5px;" cssClass="formSelect" theme="simple"/></td>
			    <td></td>
			    <td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
			<!--  
			<tr>
			    <td></td>				    	    
				<td>更新时间:</td>
				<td><s:textfield id="beginTime" name="oilfactDto.beginTime" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple"/></td>
				<td>至:</td>
				<td><s:textfield id="endTime" name="oilfactDto.endTime" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple"/></td>			            
	        	<td></td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
			-->			
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="7%"><a name="oilFactId" class="sort">油厂编号</a></th>
			<th width="6%">油厂名称</th>
			<th width="5%">法人代表</th>
			<th width="5%">使用状态</th>
			<th width="8%"><a name="updateTime" class="sort">更新时间</a></th>
			<th width="5%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<my:permission key='sy-1311-01' value='油厂信息添加'>
				<input type="button" class="formButton" value="添加" onclick="go('oilfactory/oilfactoryaction!addUI')"/>
			</my:permission>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>
