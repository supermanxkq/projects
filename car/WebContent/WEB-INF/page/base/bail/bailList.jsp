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
	<title>保证金管理</title>
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
	<script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
		
		//查询方法
		function query(page) {
			var merOrgName = $("#merOrgName").val();
			var typeSign = $("#typeSign").val();
            var coopStatus =$("#coopStatus").val(); 
			var params = {
				"bailDTO.merOrgName" : merOrgName,
		        "bailDTO.typeSign" : typeSign,
		        "bailDTO.coopStatus" : coopStatus,
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		        "bailDTO.page" : page
		    }; 
		   ajaxData("base/bail!jsonPageList",params);// !是跳转的意思,相当于？
		}
	</script> 
</head>
<body onload="query(${bailDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 保证金管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>交保机构:</td>
				<td><s:textfield id="merOrgName" name="bailDTO.merOrgName" cssClass="formInput" maxlength="20" theme="simple" onkeyup="allowEnCnNu(this);"/></td>
				<td>类型标识:</td>
				<td>
				<s:select  name="bailDTO.typeSign"  id="typeSign" list="#request.typeSign" listKey="key" listValue="value"  theme="simple" cssClass="formSelect"/>
				</td>
	        	<td>合作状态:</td>
                <td><s:select name="bailDTO.coopStatus" id="coopStatus" list="#request.coopStatus" headerKey="-1" headerValue="全部" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
                </td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%"><a name="merName">交保机构</a></th>
			<th width="10%"><a name="coopWay" >合作方式</a></th>
			<th width="8%"><a name="typeSign">类型标志</a></th>
			<th width="10%"><a name="orgOilName">收保机构</a></th>
			<th width="10%"><a name="coopStatus">合作状态</a></th>
			<th width="10%"><a name="updateTime">更新时间</a></th>
			<th width="5%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>