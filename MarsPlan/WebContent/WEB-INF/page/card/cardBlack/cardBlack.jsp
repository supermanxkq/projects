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
	<title>黑名单查询</title>
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
	function query(page) {
		//通过获取Id为CardNo的节点从而获取该节点的值
			var cardNo = $.trim($("#cardNo").val());
			var params = {
				"blackDTO.cardNo" : cardNo,		        
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),//这3句主要是进行分页的作用
		        "blackDTO.page" : page
		    }; 
		   ajaxData("card/black!jsonPageList",params);// !是跳转的意思,相当于？，主要是跳转路径
		}
	</script> 
</head>
<body onload="query(${blackDTO.page});">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 卡信息管理 &gt;&gt; 黑名单查询
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<form id="form1" name="form1" action="card/black!exportCardBlacklistXls" method="post" >
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>黑名单卡号:</td>
				<td><s:textfield id="cardNo" name="blackDTO.cardNo" cssClass="formInput" maxlength="20" theme="simple"/></td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
	        	<td><input type="button" name="Submit2" class="formButton" onclick="javascript:document.form1.submit()" value="导出" /></td>	        	
			</tr>
		</table>
	</div>
	</form>
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
		    <th width="3%">序号</th>
		    <th width="10%"><a name="verNo" class="sort">版本号</a></th>
			<th width="8%"><a name="cardNo" class="sort">黑名单卡号</a></th>
			<th width="8%"><a name="operId">操作人</a></th>
			<th width="10%"><a name="lockTime" class="sort">操作时间</a></th>
			<th width="10%"><a name="memo">备注</a></th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>