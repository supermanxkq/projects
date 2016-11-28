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
	<title>推荐人收益报表</title>
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
	<script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
		function query(page) {
		      var recommName = $("#recommName").val();
		      var recommTeleNo = $("#recommTeleNo").val();
		      var status = $("#status").val();
		      var recommType = $("#recommType").val();
		      var beginDate = $("#beginDate").val();
		      var endDate = $("#endDate").val();
			  var params = {
			    "recommProceedsDTO.recommName":recommName,
			    "recommProceedsDTO.recommTeleNo":recommTeleNo,
			    "recommProceedsDTO.status":status,
			    "recommProceedsDTO.recommType":recommType,
			    "recommProceedsDTO.beginDate":beginDate,
			    "recommProceedsDTO.endDate":endDate,
		        "orderProperty":$("#orderProperty").val(),
		        "orderDirection":$("#orderDirection").val(),
		        "recommProceedsDTO.page":page
		    }; 
		   ajaxData("report/recommProceeds!jsonPageList",params);
		}
	</script> 
</head>
<body onload="query(${recommProceedsDTO.page});">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 推荐人收益报表 &gt;&gt; 推荐人收益报表
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<form id="form1" name="form1" action="report/recommProceeds!export" method="post" >
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
		    <tr>
				<th>推荐人姓名:</th>
	        	<td><input type="text" id="recommName" name="recommProceedsDTO.recommName" class="formInput" maxlength="20" style="width: 144px"/></td>
			    <th>手机号码:</th>
			    <td><input type="text" id="recommTeleNo" name="recommProceedsDTO.recommTeleNo" class="formInput" maxlength="20" style="width: 144px"/></td>
		    </tr>
		    <tr>
		        <th>结算状态:</th>
				<td><s:select name="recommProceedsDTO.status" id="status" list="#request.flag"
				 listKey="key" headerKey="-1" headerValue="全部" listValue="value" cssClass="formSelect" theme="simple" /></td>
		        <th>推荐人类型:</th>
				<td><s:select name="recommProceedsDTO.recommType" id="recommType" list="#request.type" 
				listKey="key" headerKey="-1" headerValue="全部" listValue="value" cssClass="formSelect" theme="simple" /></td>
		    </tr>
		    <tr>
				<th>交易时间:</th>
				<td><s:textfield id="beginDate" name="termConsTotalDTO.beginDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:144px;" maxlength="20" theme="simple"/></td>
				<th>至:</th>
				<td><s:textfield id="endDate" name="termConsTotalDTO.endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:144px;" maxlength="20" theme="simple"/></td>			            
			    <td>
		        	<input type="button" class="formButton" onclick="query();" value="查 询" />
	                <input type="button" name="Submit2" class="formButton" onclick="javascript:document.form1.submit()" value="导出" />
                </td>
			</tr>
        </table>
	</div>
	</form>
	    <table width="100%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="4%"><a name="recommId" class="sort">推荐人编号</a></th>
			<th width="8%"><a name="recommName" class="sort">推荐人姓名</a></th>
			<th width="8%"><a name="recommType" class="sort">推荐人类型</a></th>
			<th width="8%"><a name="recommNum" class="sort">推荐人数</a></th>
			<th width="6%"><a name="status" class="sort">结算状态</a></th>
			<th width="6%"><a name="proceedAmt" class="sort">收益金额</a></th>
			<th width="6%"><a name="settTime" class="sort">结算时间</a></th>
		</tr>
	    </table>
		<div class="listBottom">
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>