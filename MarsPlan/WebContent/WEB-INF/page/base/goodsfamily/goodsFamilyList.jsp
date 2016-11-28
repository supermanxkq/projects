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
	<title>商品分类管理</title>
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
		
		//查询方法
		function query(page) {
			var familyName = $.trim($("#familyName").val());
			var keyWords = $.trim($("#keyWords").val());
			var status = $.trim($("#status").val());
			var params = {
				"goodsfamilyDto.familyName" : familyName,
		        "goodsfamilyDto.keyWords" : keyWords,
		        "goodsfamilyDto.status" : status,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "goodsfamilyDto.page" : page
		    }; 
		   ajaxData("base/goodsfamily!jsonPageList",params);
		}

	</script> 
</head>
<body onload="query(${goodsfamilyDto.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 商品分类管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>分类名称:</td>
				<td><s:textfield id="familyName" name="goodsfamilyDto.familyName" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>关键字:</td>
				<td><s:textfield id="keyWords" name="goodsfamilyDto.keyWords" cssClass="formInput" maxlength="20" theme="simple"/></td>
	        	<td>使用状态:</td>
		        <td><s:select name="goodsfamilyDto.status" id="status" cssStyle="width:156px;" list="#request.statusValues" 
		        	listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
		        </td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="10%">分类名称</th>
			<th width="15%">节点等级</th>
			<th width="10%">默认展开</th>
			<th width="6%">分类类型</th>
			<th width="6%">使用状态</th>
			<th width="10%">创建时间</th>
			<th width="6%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<s:if test="#session.user_session.userLevel==0">
			<my:permission key='sy-1701-02' value='商品分类添加'>
				<input type="button" class="formButton" value="添加" onclick="go('base/goodsfamily!addUI')"/>
			</my:permission>
			</s:if>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>