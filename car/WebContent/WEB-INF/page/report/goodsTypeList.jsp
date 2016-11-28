
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
		<title>商品类别</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
			type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.4.2.min.js"></script>
		<link href="js/jquery/css/jquery.ui.all.css" rel="stylesheet"
			type="text/css" />
		<script src="js/jquery/jquery.ui.core.js"></script>
		<script src="js/jquery/jquery.ui.widget.js"></script>
		<script src="js/jquery/jquery.ui.mouse.js"></script>
		<script src="js/jquery/jquery.ui.button.js"></script>
		<script src="js/jquery/jquery.ui.draggable.js"></script>
		<script src="js/jquery/jquery.ui.position.js"></script>
		<script src="js/jquery/jquery.ui.resizable.js"></script>
		<script src="js/jquery/jquery.ui.dialog.js"></script>
		<script src="js/pubValiPattern.js"></script>
		<script src="js/pubValidate.js"></script>
		<script src="js/common.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script src="js/datepicker/WdatePicker.js"></script>
				<script src="js/tips/jquery-1.2.6.pack.js"></script>
		<script src="js/tips/jquery.messager.js"></script>
		<script type="text/javascript">
		//查询方法
		function query(page) {
		    var name=$.trim($("#name").val());
		    var status=$("#status").val();
			var params = {
 				"goodsTypeDTO.status":status,
				"goodsTypeDTO.name":name,
 		        "orderProperty":$("#orderProperty").val(),
 		        "orderDirection":$("#orderDirection").val(),
		        "goodsTypeDTO.page" : page
		    };
		    /**使用Ajax请求响应的Action方法，并且传递参数*/
		   ajaxData("basic/type!jsonPageList",params);
		}
	</script>
	</head>
	<body onload="query(${goodsTypeDTO.page});">
		<div class="Position">
			当前位置是：HOME &gt;&gt;商品类别管理&gt;&gt;商品类别列表
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
			<div class="search">
			<div class="Fl">
<%-- 			<my:permission key='sy-9103-02' value='用户添加'> --%>
				<input type="button" class="formButton" value="添加" onclick="go('basic/type!addUI')"/>
<%-- 			</my:permission> --%>
		</div>
				<table class="searchTable" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<td>
							类别名称:
						</td>
						<td>
						<s:textfield id="name" name="goodsTypeDTO.name" 
							cssClass="formInput" cssStyle="width:150px;" maxlength="20" onkeyup="query(1);" 
								theme="simple" />
						</td>
						<td>
							状态:
						</td>
						<td>
								<s:select name="goodsTypeDTO.status" id="status" 
								headerKey="-1"  headerValue="全部"   value="1"
 								list="#session.goodsTypeStatus" listKey="key" listValue="value" 
 								cssStyle="width:153.5px;" cssClass="formSelect" theme="simple"/> 
						</td>
						<td>
							<input type="button" class="formButton" onclick="query(1);"
								value="查 询" />
						</td>
					</tr>
				</table>
			</div>
		<table width="100%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="5%">
					<a name="id" class="sort">编号</a>
				</th>
				<th width="5%">
					<a name="name" class="sort">类别名称</a>
				</th>
				<th width="5%">
					状态
				</th>
				<th width="10%">
					操作
				</th>
			</tr>
		</table>
		<div class="listBottom">
		<div class="Fl">
<%-- 			<my:permission key='sy-9103-02' value='用户添加'> --%>
				<input type="button" class="formButton" value="添加" onclick="go('basic/type!addUI')"/>
<%-- 			</my:permission> --%>
		</div>
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
		</div>
	</body>
</html>