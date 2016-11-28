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
	<title>卡号生成列表</title>
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
	<script type="text/javascript"><!--
		
		
		function query(page) {
			var cardNo = $.trim($("#cardNo").val());
			var generaId = $.trim($("#generaId").val());
            var status =$("#status").val();
			var params = {
				"cardNoDTO.cardNo" : cardNo,
		        "cardNoDTO.generaId" : generaId,
		        "cardNoDTO.status" :status,
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		        "cardNoDTO.page" : page
		    }; 
		   ajaxData("card/cardNo!jsonPageList",params);// !是跳转的意思,相当于？
		}
	--></script> 
</head>
<body onload="query(${cardNoDTO.page});">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 卡信息管理 &gt;&gt; 卡号生成列表
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<form id="form1" name="form1" action="card/cardNo!export" method="post" >
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>卡号:</td>
				<td><s:textfield id="cardNo" name="cardNoDTO.cardNo" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>批次号:</td>
				<td><s:textfield id="generaId" name="cardNoDTO.generaId" cssClass="formInput" maxlength="20" theme="simple"/></td>
	        	<td>启用状态:</td>
	        	<td><s:select name="cardNoDTO.status" id="status" list="#request.condValues" headerKey="-1" headerValue="全部" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
	        	<td>
				<input type="button" name="Submit2" class="formButton"
					 onclick="javascript:document.form1.submit()" value="导出" />
				</td>	        	
			</tr>
		</table>
	</div>
	</form>
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%"><a name="cardNo" >卡号</a></th>
			<th width="10%"><a name="generaId" >批次号</a></th>
			<th width="5%"><a name="status" >状态</a></th>
			<th width="8%"><a name="createTime" >生成时间</a></th>
		</tr>
	</table>
	
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
</body> 
</html>