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
	<title>一般账户提现管理</title>
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
			var memName = $("#memName").val();
			var payMenId = $("#payMenId").val();
			var getCashSign = $("#getCashSign").val();
            var getCashType =$("#getCashType").val(); 
            var status =$("#status").val(); 
			var params = {
				"getCashDTO.memName" : memName,
		        "getCashDTO.getCashSign" : getCashSign,
		        "getCashDTO.getCashType" : getCashType,
		        "getCashDTO.status" : status,
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		        "getCashDTO.page" : page
		    }; 
		   ajaxData("trade/getCash!jsonPageList",params);// !是跳转的意思,相当于？
		}
	</script> 
</head>
<body onload="query(${getCashDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 订单管理 &gt;&gt; 交易管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/page/member/member/memberHelp.jsp"></jsp:include>
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
			    <td>提现姓名:</td>
				<td><s:textfield id="payMenName" name="getCashDTO.memName"  readonly="true" cssClass="formInput" maxlength="20" theme="simple" onkeyup="allowEnCnNu(this);"/>
				<img alt="查找机构" src="images/search.gif" style="cursor:pointer;" onclick="ajaxParentMemId();"/>
				</td>
				<s:hidden name = "getCashDTO.memId" id = "payMenId"></s:hidden>
				<td>提现状态:</td>
				<td>
				<s:select  name="getCashDTO.status"  id="status" list="#request.status" listKey="key" listValue="value"  headerKey="-1" headerValue="全部"  theme="simple" cssClass="formSelect"/>
				</td>
	        	<td>提现标识:</td>
                <td><s:select name="getCashDTO.getCashSign" id="getCashSign" list="#request.getCashSign" headerKey="-1" headerValue="全部" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
                </td>
                <td>提现类型:</td>
                <td><s:select name="getCashDTO.getCashType" id="getCashType" list="#request.getCashType" headerKey="-1" headerValue="全部" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
                </td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
	        	<td>
		        	<my:permission key='sy-4111-02' value='油品添加'>
		        		<input type="button" class="formButton" value="添加" onclick="go('trade/getCash!addUI')"/>
		        	</my:permission>
	        	</td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%"><a name="memName" >提现姓名</a></th>
			<th width="10%"><a name="getCashSign" >提现标识</a></th>
			<th width="8%"><a name="getCashType" >提现类型</a></th>
			<th width="10%"><a name="orgOilName" >提现状态</a></th>
			<th width="10%"><a name="status" >提现时间</a></th>
			<th width="10%"><a name="operId" >操作人</a></th>
			<th width="10%"><a name="updateTime" >更新时间</a></th>
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