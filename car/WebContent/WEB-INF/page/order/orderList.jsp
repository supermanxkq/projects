

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
		<title>订单列表</title>
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
 		    var status=$("#status").val();
 		    var beginDate=$("#beginDate").val();
 		    var endDate=$("#endDate").val();
 		    var customer=$.trim($("#customer").val());
			var params = {
  				"orderDTO.status":status,
 				"orderDTO.beginDate":beginDate,
 				"orderDTO.customer":customer,
 				"orderDTO.endDate":endDate,
 		        "orderProperty":$("#orderProperty").val(),
 		        "orderDirection":$("#orderDirection").val(),
		        "orderDTO.page" : page
		    };
		    /**使用Ajax请求响应的Action方法，并且传递参数*/
		   ajaxData("order/order!jsonPageList",params);
		}
	</script>
	</head>
	<body onload="query(${orderDTO.page});">
		<div class="Position">
			当前位置是：HOME &gt;&gt;订单管理&gt;&gt;订单列表
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
			<div class="search">
			<div class="Fl">
				<input type="button" class="formButton" value="购物" onclick="go('managers/goods')"/>
		</div>
				<table class="searchTable" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<td>
							顾客:
						</td>
						<td>
						<s:textfield id="customer" name="orderDTO.customer" 
							cssClass="formInput" cssStyle="width:150px;" maxlength="20" onkeyup="query(1)"
								theme="simple" />
						</td>
						<td>
							状态:
						</td>
						<td>
						<s:select name="orderDTO.status" id="status" 
 								headerKey="-1" headerValue="全部" 
 								value="1"
 								list="#session.orderstatus" listKey="key" listValue="value" 
 								cssStyle="width:153.5px;" cssClass="formSelect" theme="simple"/> 
						</td>
						<td>
							<input type="button" class="formButton" onclick="query(1);"
								value="查 询" />
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td> -->
<!-- 							创建时间： -->
<!-- 						</td> -->
<!-- 						<td> -->
<%-- 							<s:textfield id="beginDate" name="orderDTO.beginDate" --%>
<%-- 								cssClass="formInput" --%>
<%-- 								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" --%>
<%-- 								cssStyle="width:150px;" maxlength="20" theme="simple" /> --%>
<!-- 						</td> -->
<!-- 						<td> -->
<!-- 							至： -->
<!-- 						</td> -->
<!-- 						<td> -->
<%-- 							<s:textfield id="endDate" name="orderDTO.endDate" --%>
<%-- 								cssClass="formInput" --%>
<%-- 								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" --%>
<%-- 								cssStyle="width:150px;" maxlength="20" theme="simple" /> --%>
<!-- 						</td> -->
<!-- 						<td> -->
<!-- 							<input type="button" class="formButton" onclick="query(1);" -->
<!-- 								value="查 询" /> -->
<!-- 						</td> -->
<!-- 					</tr> -->
				</table>
			</div>
		<table width="100%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="5%">
					<a name="id" class="sort">编号</a>
				</th>
				<th width="5%">
					商品名称
				</th>
				<th width="5%">
					<a name="customer" class="sort">顾客</a>
				</th>
					<th width="5%">
					<a name="customerPhoneNumber" class="sort">手机号码</a>
				</th>
				<th width="5%">
					总计
				</th>
				<th width="5%">
					<a name="status" class="sort">状态</a>
				</th>
				<th width="5%">
					<a name="createTime" class="sort">创建时间</a>
				</th>
				<th width="5%">
					操作
				</th>
			</tr>
		</table>
		<div class="listBottom">
		<div class="Fl">
				<input type="button" class="formButton" value="购物" onclick="go('managers/goods')"/>
		</div>
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
		</div>
	</body>
</html>