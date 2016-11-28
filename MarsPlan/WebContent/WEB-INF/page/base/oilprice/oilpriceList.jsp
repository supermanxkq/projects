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
	<title>油价信息管理</title>
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
			
            var oilTypeId = $.trim($("#oilType").val());//油品类型
            var beginTime = $.trim($("#beginTime").val());//时间（起始）
            var endTime = $.trim($("#endTime").val());//时间（截止）
			
			var params = {				
		        "oilpDto.oilTypeId":oilTypeId,
		        "oilpDto.beginTime":beginTime,
		        "oilpDto.endTime":endTime,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "oilpDto.page" : page
		    }; 
		   ajaxData("oilprice/oilpriceaction!jsonPageList",params);
		}
	</script> 
</head>
<body onload="query(${oilpDto.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 油价信息管理 &gt;&gt; 油价信息查询
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>

	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
	            <td>油品类型:</td>
			    <td><s:select id="oilType" name="oilpDto.oilTypeId" list="#request.typesList" listKey="key" headerKey="-1" headerValue="全部" listValue="value" cssStyle="width:153.5px;" cssClass="formSelect" theme="simple"/></td>
			</tr>
			<tr>
			    <td></td>				    	    
				<td>更新时间:</td>
				<td><s:textfield id="beginTime" name="oilpDto.beginTime" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple"/></td>
				<td>至:</td>
				<td><s:textfield id="endTime" name="oilpDto.endTime" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple"/></td>			            
	        	<td></td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>			
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="7%">油品类型</th>
			<th width="6%">售油方</th>
			<th width="5%"><a name="merchants" class="sort">进油价</a></th>
			<th width="5%"><a name="sinOrgans" class="sort">售油价</a></th>
			<th width="8%"><a name="organs" class="sort">发改委售价</a></th>
			<th width="8%"><a name="organs" class="sort">更新时间</a></th>
			<th width="10%"><a name="organs" class="sort">操作人</a></th>
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
