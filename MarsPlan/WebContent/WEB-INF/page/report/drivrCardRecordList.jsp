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
	<title>司机刷卡记录报表</title>
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
		      var trackNo = $.trim($("#trackNo").val());
		      var beginDate = $.trim($("#beginDate").val());
		      var endDate = $.trim($("#endDate").val());
		      var telph = $.trim($("#telph").val());
			  var params = {
			    "drivrCardRecordDTO.trackNo":trackNo,
			    "drivrCardRecordDTO.telph":telph,
			    "drivrCardRecordDTO.beginTime":beginDate,
			    "drivrCardRecordDTO.endTime":endDate,
		        "orderProperty":$("#orderProperty").val(),
		        "orderDirection":$("#orderDirection").val(),
		        "drivrCardRecordDTO.page":page
		    }; 
		   ajaxData("report/driverCardRecord!jsonPageList",params);
		}
	</script> 
</head>
<body onload="query(${drivrCardRecordDTO.page});">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 司机刷卡记录报表 &gt;&gt; 司机刷卡记录报表
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<form id="form1" name="form1" action="report/driverCardRecord!export" method="post" >
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
		    <tr>
				<th>交易时间:</th>
				<td><s:textfield id="beginDate" name="drivrCardRecordDTO.beginDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:144px;" maxlength="20" theme="simple"/></td>
				<th>至:</th>
				<td><s:textfield id="endDate" name="drivrCardRecordDTO.endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:144px;" maxlength="20" theme="simple"/></td>			            
			</tr>
			<tr>
				
			    <th>运单号码:</th>
			    <td><input type="text"  id="trackNo" name="drivrCardRecordDTO.trackNo" class="formInput" maxlength="20" style="width: 144px"/></td>
			    <th>手机号码:</th>
			    <td><input type="text" id="telph" name="drivrCardRecordDTO.telph" class="formInput" maxlength="20" style="width: 144px"/></td>
				
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
			<th width="4%">司机编号</th>
			<th width="8%">司机名称</th>
			<th width="8%">运单编号</th>
			<th width="6%">司机电话号</th>
			<th width="6%">司机上次到达的地点</th>
			<th width="6%">司机上次到达的时间</th>
		</tr>
	    </table>
		<div class="listBottom">
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>