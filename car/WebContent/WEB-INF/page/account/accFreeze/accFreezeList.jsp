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
	<title>账户管理</title>
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
		
		//查询方法
		function query(page) {
			var cardNo = $.trim($("#cardNo").val());
		      
			var accId = $.trim($("#accId").val());
            var accTId =$("#accTId").val(); 
			var params = {
				"accRecordDTO.cardNo" : cardNo,
		        "accRecordDTO.accId" : accId,
		        "accRecordDTO.accTId" : accTId,
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		        "accRecordDTO.page" : page
		    }; 
		   ajaxData("account/accFreeze!jsonPageList",params);// !是跳转的意思,相当于？
		}
	</script> 
</head>
<body onload="query(${accRecordDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 账户管理 &gt;&gt; 账户冻结
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>卡号:</td>
				<td><s:textfield id="cardNo" name="accRecordDTO.cardNo" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>账户号:</td>
				<td><s:textfield id="accId" name="accRecordDTO.accId" cssClass="formInput" maxlength="20" theme="simple"/></td>
	        	<td>账户类型:</td>
	        
                <td><s:select name="accRecordDTO.accTId" id="accTId" list="#request.typesList" headerKey="-1" 
                             headerValue="全部" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
                </td>
               
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
	        	
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%"><a name="accId" class="sort">账户号</a></th>
			<th width="10%"><a name="cardNo" class="sort">卡号</a></th>
			<th width="10%"><a name="accType" >账户类型</a></th>
			<th width="5%"><a name="operTime" class="sort">操作时间</a></th>
			<th width="5%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<my:permission key='sy-2107-02' value='账户冻结添加'>
				<input type="button" class="formButton" value="添加" onclick="go('account/accFreeze!addUI')"/>
			</my:permission>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>