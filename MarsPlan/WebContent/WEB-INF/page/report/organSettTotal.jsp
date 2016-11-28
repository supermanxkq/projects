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
	<title>机构结算报表查询</title>
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
			var organId = $.trim($("#organId").val());
			var orgName = $.trim($("#orgName").val());
			var status = $.trim($("#status").val());
			var beginDate = $.trim($("#beginDate").val());
			var endDate = $.trim($("#endDate").val());
			var params = {
				"organSettTotalDTO.organId":organId,
				"organSettTotalDTO.orgName":orgName,
				"organSettTotalDTO.status":status,
				"organSettTotalDTO.beginDate":beginDate,
				"organSettTotalDTO.endDate":endDate,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "organSettTotalDTO.page" : page
		    }; 
		   ajaxData("report/organSettTotal!jsonPageList",params);
		}
	</script> 
</head>
<body onload="query(${organSettTotalDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 报表查询&gt;&gt; 机构结算报表查询
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<form id="form1" name="form1" action="report/organSettTotal!export" method="post" >
	<div class="search">
		<table class="searchTable" cellpadding="0" width="96%" cellspacing="0">
		    <s:if test="#session.user_session.userLevel==0">
			<tr>
	            <td>机构编号:</td>
				<td><s:textfield id="organId" name="organSettTotalDTO.organId" cssClass="formInput" maxlength="20" theme="simple"/></td>
			    <td>机构名称:</td>
			    <td><s:textfield id="orgName" name="organSettTotalDTO.orgName" cssClass="formInput" maxlength="20" theme="simple"/></td>
			</tr>
			<tr>
				<td>结算状态</td>
	        	<td><s:select name="organSettTotalDTO.status" id="status" list="#request.status" listKey="key" headerKey="-1" headerValue="全部" listValue="value" cssStyle="width:153.5px;" cssClass="formSelect" theme="simple"/></td>
	        	<td>交易时间:</td>
				<td><s:textfield id="beginDate" name="organSettTotalDTO.beginDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple"/>至:
				<s:textfield id="endDate" name="organSettTotalDTO.endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple"/></td>			            
			    <td align="center"><input type="button" class="formButton" onclick="query();" value="查 询" />
			                       <input type="button" name="Submit2" class="formButton" onclick="javascript:document.form1.submit()" value="导出" /></td>
			</tr>		
			</s:if>	
			<s:if test="#session.user_session.userLevel==1">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
				<td>结算状态</td>
	        	<td><s:select name="organSettTotalDTO.status" id="status" list="#request.status" listKey="key" headerKey="-1" headerValue="全部" listValue="value" cssStyle="width:153.5px;" cssClass="formSelect" theme="simple"/></td>
	        	<td>交易时间:</td>
				<td><s:textfield id="beginDate" name="organSettTotalDTO.beginDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple"/>至:
			        <s:textfield id="endDate" name="organSettTotalDTO.endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple"/></td>			            
			    <td align="center"><input type="button" class="formButton" onclick="query();" value="查 询" />
			                       <input type="button" name="Submit2" class="formButton" onclick="javascript:document.form1.submit()" value="导出" /></td>
			</tr>
			</s:if>		
		</table>
	</div>
	</form>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="10%">机构编号</th>
			<th width="10%">机构名称</th>
			<th width="10%">结算状态</th>
			<th width="10%">本机构结算金额</th>
			<th width="10%">其他机构结算金额</th>
			<th width="10%">本机构卡对外结算金额</th>
			<th width="8%">总结算金额</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>