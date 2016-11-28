
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
		<title>购物车列表</title>
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
			var params = {
		    };
		    /**使用Ajax请求响应的Action方法，并且传递参数*/
		   ajaxData("shopcar/shopcar!jsonPageList",params);
		}
	</script>
	</head>
	<body onload="query(${shopCarDTO.page});">
		<div class="Position">
			当前位置是：HOME &gt;&gt;购物车&gt;&gt;查看购物车
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<s:form action="order/order!confirmOrder" method="post" theme="simple">
		<table width="100%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="5%">
					编号
				</th>
				<th width="5%">
					商品名称
				</th>
				<th width="5%">
					数量
				</th>
				<th width="10%">
					操作
				</th>
			</tr>
		</table>
		<div class="listBottom">
		<div class="Fl">
<%-- 			<my:permission key='sy-9103-02' value='用户添加'> --%>
				<input type="submit" class="formButton" id="confirmOrderBtn" value="确认订单" />
				<input type="button" class="formButton" value="继续购买" onclick="go('managers/goods!list')"/>
<%-- 			</my:permission> --%>
		</div>
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
		</div>
		</s:form>
	</body>
</html>