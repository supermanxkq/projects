<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<title>确认订单</title>
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
<script src="js/jquery/jquery.ui.autocomplete.js"></script>
<script src="js/jquery/jquery.ui.resizable.js"></script>
<script src="js/jquery/jquery.ui.dialog.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript" src="js/areaSelect.js"></script>
<script src="js/datepicker/WdatePicker.js"></script>
<link href="resources/css/style.css" rel="stylesheet" type="text/css" />
<script src="js/common.js"></script>
<script src="js/pubValiPattern.js"></script>
<script src="js/pubValidate.js"></script>
<script type="text/javascript">
	var customerFlag = false;
	var telNoFlag=false;
	function checkCustomer() {
		if ($("#customer").val() == "") {
			$("#customerMsg").html("");
			$("#customerMsg").html("请输入顾客名称!");
			$("#customerMsg").show();
			customerFlag = false;
		} else {
			$("#customerMsg").hide();
			customerFlag = true;
		}
	}

	 function conPerTeleNoBlur(obj){
		 if(obj.value!=""&&obj.value!=null){
         var type = ["isMobile"];  
         var errorMsg = ["手机号码格式错误!"];
         telNoFlag = showErrorMsg(obj,type,errorMsg,"customerPhoneNumberError");
		 }else{
        	 $("#customerPhoneNumberError").hide();
         }
      }
	function check() {
		checkCustomer();
		if (customerFlag) {
			if (confirm("订单确认无法修改，请仔细检查，确认提交订单吗？")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
</script>
</head>
<body>
	<div class="Position">当前位置是：订单管理 &gt;&gt;添加订单</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<s:form action="order/order" method="post" theme="simple"
		onsubmit="return check();">
		<s:hidden name="method" id="method" />
		<s:hidden name="order.id" id="id" />
		<table width="100%" class="listTable" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<td colspan="5" style="background-color: red;"><strong>已购买商品列表</strong></td>
				</tr>
			</thead>
			<tr>
				<th width="5%">编号</th>
				<th width="5%">商品名称</th>
				<th width="5%">价格</th>
				<th width="5%">数量</th>
				<th width="5%">小计</th>
			</tr>
			<s:iterator value="list" var="shopcar">
				<tr>
					<td>${shopcar.id }</td>
					<td>${shopcar.goodsName }</td>
					<td>${shopcar.price }</td>
					<td>${shopcar.count }</td>
					<td><fmt:formatNumber type="number" value="${shopcar.price*shopcar.count}" pattern="0.00" maxFractionDigits="2"/></td>
				</tr>
			</s:iterator>
			<tfoot>
				<tr>
					<td colspan="5" align="right">总计：<fmt:formatNumber type="number" value="${totalPrice}" pattern="0.00" maxFractionDigits="2"/>元</td>
				</tr>
			</tfoot>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="formTable">
			<tr>
				<th style="text-align: center; width: 10%;"><span class="Color5">* </span>顾客名称:</th>
				<td style="text-align: left;"><s:textfield
						name="order.customer" id="customer" cssClass="formInput"
						theme="simple" onblur="checkCustomer();" /> <label
					id="customerMsg" class="errorMsg" style="display: none;"></label></td>
					<th style="text-align: center; width: 10%;">手机号码:</th>
				<td style="text-align: left;"><s:textfield
						name="order.customerPhoneNumber" id="customerPhoneNumber" cssClass="formInput" onkeyup= "replaceToNum(this);" onblur="conPerTeleNoBlur(this);"
						theme="simple" /><label id="customerPhoneNumberError" class="errorMsg" style="display: none;"></label> </td>
			</tr>
			<tr>
			<th style="text-align: center; width: 10%;">状态:</th>
				<td><s:select name="order.status" id="status"
						list="#request.orderstatus" listKey="key" listValue="value"
						cssStyle="width:153.5px;" cssClass="formSelect" theme="simple" />
				</td>
				<th style="text-align: center; width: 10%;">订单描述:</th>
				<td><s:textarea
						name="order.orderDesc" id="orderDesc" cssClass="formTextarea"
						cols="45" rows="20" maxlength="255" cssStyle="resize:none;" />
				</td>
			</tr>
		</table>
		<div class="formTableBottom">
			<s:if test="method=='addSave'">
				<my:permission key='sy-5802-01' value='订单添加'>
					<input id="submitInput" type="submit" class="formButton"
						value="添加订单" />
				</my:permission>
			</s:if>
			<input type="button" class="formButton" value="继续购买"
				onclick="go('managers/goods')" />
		</div>
	</s:form>
</body>
</html>