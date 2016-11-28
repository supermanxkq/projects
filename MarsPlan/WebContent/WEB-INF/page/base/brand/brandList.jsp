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
	<title>商品品牌管理</title>
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
	<script type="text/javascript">
		
		//查询方法
		function query(page) {
			var brandName = $.trim($("#brandName").val());
           
			var params = {
		        "goodBrandsDTO.brandName" : brandName,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "goodBrandsDTO.page" : page
		    }; 
		   ajaxData("base/brand!jsonPageList",params);
		}
	</script> 
</head>
<body onload="query(${goodBrandsDTO.page});" >
	<div class="Position">
		当前位置是：HOME &gt;&gt; 常用操作 &gt;&gt; 商品品牌管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">		
		<div class="Fl">
			<my:permission key='sy-1703-02' value='品牌添加'>
				<input type="button" class="formButton" value="添加" onclick="go('base/brand!addUI')"/>
			</my:permission>
		</div>
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
			    <td>品牌名称:</td>
				<td><s:textfield id="brandName" name="goodBrandsDTO.brandName" cssClass="formInput" maxlength="60" theme="simple"/></td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
		</table>
	</div>
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="15%"><a name="brandName" class="sort">品牌名称</a></th>
			<th width="15%"><a name="brandUrl" class="sort">品牌网址</a></th>
			<th width="30%">品牌描述</th>
			<th width="5%"><a name="isShow" class="sort">是否显示</a></th>
			<th width="5%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<my:permission key='sy-1703-02' value='品牌添加'>
				<input type="button" class="formButton" value="添加" onclick="go('base/brand!addUI')"/>
			</my:permission>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>