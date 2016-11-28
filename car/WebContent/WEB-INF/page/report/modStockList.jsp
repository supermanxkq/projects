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
	<title>库存变动查询</title>
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
	<script src="js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript">
		//查询方法
		function query(page) {
			var id = $.trim($("#id").val());
			var name = $.trim($("#name").val());
			var merName = $.trim($("#merName").val());
			var status=$.trim($("#status").val());
		    var beginDate = $("#beginDate").val();
		    var endDate = $("#endDate").val();
			var flag = $.trim($("#flag").val());
			var params = {
				"modStockDTO.id":id,
				"modStockDTO.name":name,
				"modStockDTO.merName":merName,
				"modStockDTO.status":status,
				"modStockDTO.beginDate":beginDate,
			    "modStockDTO.endDate":endDate,
				"modStockDTO.flag":flag,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "modStockDTO.page" : page
		    };
		   ajaxData("report/modStockList!jsonPageList",params);
		}
	</script> 
</head>
<body onload="query(${modStockDTO.page});">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 报表查询 &gt;&gt; 库存变动查看
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<form id="form1" name="form1" action="report/modStockList!export" method="post" >
	<div class="search">
		<table class="searchTable"  cellpadding="0" cellspacing="0">
			<tr>
			    <th>入库时间:</th>
				<td><s:textfield id="beginDate" name="modStockDTO.beginDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:146px;" maxlength="20" theme="simple"/></td>
				<th>&nbsp;至:&nbsp;&nbsp;&nbsp;</th>
				<td><s:textfield id="endDate" name="modStockDTO.endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:146px;" maxlength="20" theme="simple"/></td>			            
			    <td></td>
			</tr>
			<tr>
				<th>流水号:</th>
	        	<td><s:textfield id="id" name="modStockDTO.id" cssStyle="width:144px;" cssClass="formInput" maxlength="20" theme="simple"/></td>
			    <s:if test="#session.user_session.userLevel==0">
			    <th>机构名称:</th>
			    <td><s:textfield id="name" name="modStockDTO.name" cssStyle="width:144px;" cssClass="formInput" maxlength="20" theme="simple"/></td>
			    </s:if>
			</tr>
			<tr>
			    <th>入库状态:</th>
				<td><s:select name="modStockDTO.status" id="status" list="#request.status" listKey="key" headerKey="-1" headerValue="全部" listValue="value" cssClass="formSelect" theme="simple" /></td>
			    <s:if test="#session.user_session.userLevel!=2">
	            <th>商户名称:</th>
				<td><input type="text"  id="merName" name="modStockDTO.merName" class="formInput" maxlength="20" style="width: 144px"/></td>
	            </s:if>
	            <td>
	            <s:if test="#session.user_session.userLevel!=2">
	        	<input type="button" class="formButton" onclick="query();" value="查 询" />
                <input type="button" name="Submit2" class="formButton" onclick="javascript:document.form1.submit()" value="导出" />
			    </s:if>
			    <s:else>
	            <th>
	        	<input type="button" class="formButton" onclick="query();" value="查 询" />
                <input type="button" name="Submit2" class="formButton" onclick="javascript:document.form1.submit()" value="导出" />
			    </th>
	            </s:else>
			    </td>
			</tr>
		</table>
	</div>
	</form>
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="10%">流水号</th>
			<th width="6%">入库方类型</th>
			<th width="6%">出库方类型</th>
			<th width="6%">数量</th>
			<th width="6%">状态</th>
			<th width="10%">起始卡号</th>
			<th width="10%">入库操作人</th>
			<th width="10%">审核操作人</th>
			<th width="10%">创建时间</th>
			<th width="10%">入库时间</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>