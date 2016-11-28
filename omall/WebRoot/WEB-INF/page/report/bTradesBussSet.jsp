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
	<title>交易报表</title>
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
	<script src="js/jquery/jquery.ui.tabs.js"></script>
	<script src="js/datepicker/WdatePicker.js"></script>
	<script src="js/common.js"></script>
	<script type="text/javascript" src="js/fusionchats/FusionCharts.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
		
	</script> 
</head>
<body onload="query(${btradesDTO.page});">
	
	<s:form action="report/btrades" method="post"
			onsubmit="document.getElementById('submitInput').disabled = true;return true;"
			theme="simple">
		<s:hidden name="method" id="method" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			
			<tr>
				<th align="right" width="20%">
					交易流水号：
				</th>
				<td width="30%">
					<s:textfield name="btradesDTO.tradesId" id="tradesId"
						cssClass="formInput" disabled="true" cssStyle="width:220px;"
						theme="simple" />
					<span class="Color5" id="tradesIdTip"></span>
				</td>

				<th align="right" width="20%">
					交易状态：
				</th>
				<td width="30%">
					<s:textfield name="btradesDTO.status" id="status"
						cssClass="formInput" disabled="true" cssStyle="width:220px;"
						theme="simple" />
					<span class="Color5" id="statusTip"></span>
				</td>
			</tr>
			
		</table>
		<div class="formTableBottom">
			<s:if test="#session.user_session.userLevel==0">
				<input type="button" class="formButton" value="返 回"
					onclick="go('report/btrades!list')" />
			</s:if>
		</div>
	</s:form>
	
</body> 
</html>