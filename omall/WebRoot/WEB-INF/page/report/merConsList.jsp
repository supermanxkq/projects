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
	<title>商户消费汇总</title>
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
			var merId = $.trim($("#merId").val());
			var merName = $.trim($("#merName").val());
			var tradeType = $.trim($("#tradeType").val());
		    var beginDate = $("#beginDate").val();
		    var endDate = $("#endDate").val();
			var flag = $.trim($("#flag").val());
			
			var params = {
				"termConsDto.merId":merId,
				"termConsDto.merName":merName,
				"termConsDto.tradeType":tradeType,
				"termConsDto.beginDate":beginDate,
			    "termConsDto.endDate":endDate,
				"termConsDto.flag":flag,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "termConsDto.page" : page
		    };
		   ajaxData("report/merconstotal!jsonPageList",params);
		}
	
	</script> 
</head>
<body onload="query(${termConsDto.page});">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 商户消费汇总 &gt;&gt; 商户消费汇总查看
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<form id="form1" name="form1" action="report/merconstotal!export" method="post" >
	<div class="search">
		<table class="searchTable"  cellpadding="0" cellspacing="0">
		    <s:if test="#session.user_session.userLevel!=2">	
			<tr>			    		 
	            <td><img src="images/fd.jpg"/></td>	            	            
	            <td>商户编号:</td>
				<td><s:textfield id="merId" name="termConsDto.merId" cssStyle="width:147.5px;" cssClass="formInput" maxlength="20" theme="simple"/></td>
			    <td>商户名称:</td>
				<td><s:textfield id="merName" name="termConsDto.merName" cssStyle="width:147.5px;" cssClass="formInput" maxlength="20" theme="simple"/></td>			    	
			</tr>			
			<tr>
				<td></td>				    	    
				<td>交易时间:</td>
				<td><s:textfield id="beginDate" name="termConsDto.beginDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple"/></td>
				<td>至:</td>
				<td><s:textfield id="endDate" name="termConsDto.endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple"/></td>			            
			</tr>
			</s:if>
			<s:if test="#session.user_session.userLevel==2">
			<tr>
				<td><img src="images/fd.jpg"/></td>				    	    
				<td>交易时间:</td>
				<td><s:textfield id="beginDate" name="termConsDto.beginDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple"/></td>
				<td>至:</td>
				<td><s:textfield id="endDate" name="termConsDto.endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple"/></td>			            
			</tr>
			</s:if>
			<tr>			
			<td></td>   	
	        	<td>交易类型:</td>
				<td><s:select name="termConsDto.tradeType" id="tradeType" list="#request.flag" listKey="key" headerKey="-1" headerValue="全部" listValue="value" cssStyle="width:153.5px;" cssClass="formSelect" theme="simple"/></td>
	        	<td></td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" />
                    <input type="button" name="Submit2" class="formButton" onclick="javascript:document.form1.submit()" value="导出" />
                </td>                        	
			</tr>			
		</table>
	</div>
	</form>
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="10%">商户编号</th>
			<th width="15%">商户名称</th>
			<th width="10%">交易类型</th>
			<th width="8%">总交易金额</th>
			<th width="8%">总手续费</th>
			<th width="8%">交易笔数</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>