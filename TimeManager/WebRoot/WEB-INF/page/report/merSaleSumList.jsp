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
	<title>终端交易汇总</title>
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
		      var termId = $("#termId").val();
		      var termName = $("#termName").val();
		      var merName = $("#merName").val();
		      var tradeType = $("#tradeType").val();
		      var beginDate = $("#beginDate").val();
		      var endDate = $("#endDate").val();
			  var params = {
			    "termConsTotalDTO.termName":termName,
			    "termConsTotalDTO.termId":termId,
			    "termConsTotalDTO.merName":merName,
			    "termConsTotalDTO.tradeType":tradeType,
			    "termConsTotalDTO.beginDate":beginDate,
			    "termConsTotalDTO.endDate":endDate,
		        "orderProperty":$("#orderProperty").val(),
		        "orderDirection":$("#orderDirection").val(),
		        "tradesViewDTO.page":page
		    }; 
		   ajaxData("report/termConsTotalList!jsonPageList",params);
		}
	</script> 
</head>
<body onload="query(${termConsTotalDTO.page});">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 终端交易汇总 &gt;&gt; 终端交易汇总
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<form id="form1" name="form1" action="report/termConsTotalList!export" method="post" >
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
		    <tr>
				<th>交易时间:</th>
				<td><s:textfield id="beginDate" name="termConsTotalDTO.beginDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:144px;" maxlength="20" theme="simple"/></td>
				<th>至:</th>
				<td><s:textfield id="endDate" name="termConsTotalDTO.endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:144px;" maxlength="20" theme="simple"/></td>			            
			</tr>
				<tr>
	            <th>终端名称:</th>
	        	<td><input type="text"  id="termName" name="termConsTotalDTO.termName" class="formInput" maxlength="20" style="width: 144px"/></td>      	
	        </tr>
			<tr>
				<th>终端编号:</th>
	        	<td><input type="text" id="termId" name="termConsTotalDTO.termId" class="formInput" maxlength="20" style="width: 144px"/></td>
				<s:if test="#session.user_session.userLevel!=2">
			    <th>商户名称:</th>
			    <td><input type="text" id="merName" name="termConsTotalDTO.merName" class="formInput" maxlength="20" style="width: 144px"/></td>
				</s:if>
				<s:else>
					<td></td>
				</s:else>
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
			<th width="4%"><a name="termId" class="sort">终端编号</a></th>
			<th width="8%"><a name="termName" class="sort">终端名称</a></th>
			<th width="8%"><a name="merName" class="sort">商户名称</a></th>
			<th width="6%"><a name="tradeType" class="sort">交易类型</a></th>
			<th width="6%"><a name="tradesNum" class="sort">交易笔数</a></th>
			<th width="6%"><a name="consAmt" class="sort">总交易金额</a></th>
			<th width="6%"><a name="consCommis" class="sort">总手续费</a></th>
		</tr>
	    </table>
		<div class="listBottom">
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>