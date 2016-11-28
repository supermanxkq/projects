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
	<title>订单报表</title>
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
	<script type="text/javascript" src="js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript">
	//查询方法
	function query(page) {
	
		var cardNo = $.trim($("#cardNo").val());
		var saleOrderId = $.trim($("#saleOrderId").val());
		var orgId = $.trim($("#orgId").val());
		var from = $.trim($("#from").val());
		var to = $.trim($("#to").val());
		
		var params = {
	        "saleCardRecordDTO.cardNo" : cardNo,
	        "saleCardRecordDTO.saleOrderId" : saleOrderId,
	        "saleCardRecordDTO.orgId" : orgId,
	        "saleCardRecordDTO.from" : from,
		    "saleCardRecordDTO.to" : to,
	        "saleCardRecordDTO.page" : page
	    }; 
	   ajaxData("report/saleCardRecord!jsonPageList",params);
	}
	</script> 
</head>
<body onload="query(${saleCardRecordDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 订单报表 &gt;&gt; 售卡订单报表
	</div>
	
	<form id="form1" name="form1" action="report/saleCardRecord!export" method="post">
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <th>卡&nbsp;&nbsp;&nbsp;&nbsp;号:</th>
				<td><input type="text" id="cardNo" name="saleCardRecordDTO.cardNo" class="formInput"/></td>
				<th>售卡订单号:</th>
				<td><input type="text" id="saleOrderId" name="saleCardRecordDTO.saleOrderId" class="formInput"/></td>
	        		<td>发卡机构:</td>
					<td>
						<s:select name="saleCardRecordDTO.orgId" id="orgId"
								list="#request.organValues" listKey="key" listValue="value"
								headerKey="-1" headerValue="--请选择--" cssClass="formInput"
								theme="simple" />
		        	</td>
	        	
			</tr>
			<tr>
				<td></td>
				<th>售卡时间:</th>
				<td colspan="3">
					<input name="saleCardRecordDTO.from" id="from" class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/> 
			      	至
			      	<input name="saleCardRecordDTO.to" id="to" class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
	        	</td>
				
				<td colspan="2">
					<input type="button" class="formButton" onclick="query();" value="查 询" /> 
	        		<input type="button" name="Submit2" class="formButton" value="导出" onclick="javascript:document.form1.submit()" />
	        		<span id="otherhtml"></span>
				</td>
			</tr>
		</table>
	</div>
	</form>
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%">卡号</th>
			<th width="6%">卡名称</th>
			<th width="8%">发卡机构</th>
			<th width="8%">卡面额</th>
			<th width="8%">售卡单号</th>
			<th width="8%">售卡时间</th>
			<th width="5%">售卡状态</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
		</div>
		<div class="Fr" id="pageNav">
			
		</div>
	</div>
</body> 
</html>