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
	<title>会员管理</title>
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
	<script type="text/javascript">
	//查询会员方法
	function query(page) {
		var cardNo = $.trim($("#cardNo").val());
		var memRealName = $.trim($("#memRealName").val());
		var levelName = $.trim($("#levelName").val());
	//json数据传输
		var params = {
			"memCardDTO.cardNoShow" : cardNo,
	        "memCardDTO.memRealName" : memRealName,
	        "memCardDTO.levelName" : levelName,
	        "orderProperty" : $("#orderProperty").val(),
	        "orderDirection" : $("#orderDirection").val(),
	        "memberDTO.page" : page
	    }; 
	   ajaxData("memCard/memCard!jsonPageList",params);
	}
			
	</script> 
</head>
<body onload="query(${memCardDTO.page});">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 会员卡信息管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>会员卡号:</td>
				<td><s:textfield id="cardNo" name="memCardDTO.cardNoShow" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>会员名称:</td>
				<td><s:textfield id="memRealName" name="memCardDTO.memRealName" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>卡等级:</td>
				<td><s:textfield id="levelName" name="memCardDTO.levelName" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<s:hidden name = "memCardDTO.levelId"></s:hidden>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr> 
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%"><a name="cardNo" class="sort">会员卡号</a></th>
			<th width="10%"><a name="memRealName" >会员姓名</a></th>
			<th width="10%"><a name="balanceAnt">卡余额</a></th>
			<th width="5%"><a name="balancePnt">卡积分</a></th>
			<th width="8%"><a name="levelName">卡等级</a></th>
			<th width="5%"><a name="cstatus">卡状态</a></th>
			<th width="8%"><a name="createTime" class="sort">开卡日期</a></th>
			<th width="5%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
				<input type="button" class="formButton" value="添加" onclick="go('memCard/memCard!addUI')"/>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>