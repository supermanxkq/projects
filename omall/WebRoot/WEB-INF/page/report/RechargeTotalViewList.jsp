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
	<title>商户充值累计汇总</title>
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
		      var merName = $("#merName").val();
		      var merId = $("#merId").val();
		      var beginTime = $("#beginTime").val();
		      var endTime = $("#endTime").val();
		   	 
			  var params = {
			    "mersetTotalDTO.merName":merName,
			    "mersetTotalDTO.merId":merId,
			    "mersetTotalDTO.selectBeginDate":beginTime,
			    "mersetTotalDTO.selectEndDate":endTime,			    
		        "orderProperty":$("#orderProperty").val(),
		        "orderDirection":$("#orderDirection").val(),
		        "mersetTotalDTO.page":page
		      }; 
		      ajaxData("report/rechargetotalview!jsonPageList",params);
		}
	</script> 
</head>
<body onload="query(${mersetTotalDTO.page});">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 查询统计 &gt;&gt; 充值累计查询
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<form id="form1" name="form1" action="report/rechargetotalview!export" method="post" >
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			
			<s:if test="#session.user_session.userLevel!=2">
			<tr id="selectFactor">
	            <td><img src="images/fd.jpg" /></td>
			    <td>商户编号:</td>
	        	<td><s:textfield id="merId" name="mersetTotalDTO.merId" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>商户名称:</td>
				<td><s:textfield id="merName" name="mersetTotalDTO.merName" cssClass="formInput" maxlength="20" theme="simple"/></td>
			</tr>
			</s:if>
			<tr>
				<td></td>
	        	
			  <td>结算时间:</td>
				<td><s:textfield id="beginTime" name="mersetTotalDTO.selectBeginDate" onfocus="WdatePicker({skin:'blue'})" cssStyle="width:100px;" maxlength="20" theme="simple"/>至<s:textfield id="endTime" name="mersetTotalDTO.selectEndDate" onfocus="WdatePicker({skin:'blue'})" cssStyle="width:100px;" maxlength="20" theme="simple"/></td>
	        	<td></td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" />
<input type="button" name="Submit2" class="formButton" onclick="javascript:document.form1.submit()" value="导出" />

</td>	
<td></td>        	
			</tr>
</table>
	</div>
	</form>
	<table width="100%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%"><a name="merID" class="sort">商户编号</a></th>
			<th width="12%"><a name="merName" class="sort">商户名称</a></th>
			<th width="4%"><a name="rechAmt" class="sort">总充值金额</a></th>
			<th width="10%"><a name="tradeCount" class="sort">交易笔数</a></th>
			
		</tr>
	</table>
		<div class="listBottom">
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>