<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<title>入库</title>
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
	var goodsNameFlag = false;
	var priceFlag = false;
	var countFlag = false;
	var purchaseDateFlag = false;
	var goodsTypeIdFlag = false;
	function checkName() {
		if ($("#goodsName").val() == "") {
			$("#nameMsg").html("");
			$("#nameMsg").html("请输入商品名称!");
			$("#nameMsg").show();
			goodsNameFlag = false;
		} else {
			$("#nameMsg").hide();
			goodsNameFlag = true;
		}
	}

	function checkPrice() {
		if ($("#price").val() == "") {
			$("#priceMsg").html("");
			$("#priceMsg").html("请输入价格!");
			$("#priceMsg").show();
			priceFlag = false;
		} else {
			$("#priceMsg").hide();
			priceFlag = true;
		}
	}

	function checkCount() {
		if ($("#count").val() == "") {
			$("#countMsg").html("");
			$("#countMsg").html("请输入数量!");
			$("#countMsg").show();
			countFlag = false;
		} else {
			$("#countMsg").hide();
			countFlag = true;
		}
	}

// 	function checkPurchaseDate() {
// 		if ($("#purchaseDate").val() == "") {
// 			$("#purchaseDateMsg").html("");
// 			$("#purchaseDateMsg").html("请输入进货日期!");
// 			$("#purchaseDateMsg").show();
// 			purchaseDateFlag = false;
// 		} else {
// 			$("#purchaseDateMsg").hide();
// 			purchaseDateFlag = true;
// 		}
// 	}

	function checkGoodType() {
		if(!$(':radio[name=goods.goodsTypeId]:checked').length) {
			$("#goodsTypeIdMsg").html("");
			$("#goodsTypeIdMsg").html("请选择商品分类!");
			$("#goodsTypeIdMsg").show();
			goodsTypeIdFlag = false;
		}else{
			$("#goodsTypeIdMsg").hide();
			goodsTypeIdFlag = true;
		}
	}
	function check() {
		checkName();
		checkPrice();
		checkCount();
		//checkPurchaseDate();
		checkGoodType();
		if (goodsNameFlag && priceFlag && goodsTypeIdFlag) {
			return true;
		} else {
			return false;
		}
	}
</script>
</head>
<body>
	<div class="Position">当前位置是:<a href="managers/goods">入库管理</a> &gt;&gt;入库</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<%--主任务修改对话框开始--%>
	<s:form action="managers/goods" method="post" theme="simple"
		onsubmit="return check();">
		<s:hidden name="method" id="method" />
		<s:hidden name="goods.id" id="id" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="formTable">
			<tr>
				<th style="text-align: center; width: 10%;"><span class="Color5">* </span>商品名称:</th>
				<td style="text-align: left;"><s:textfield
						name="goods.goodsName" id="goodsName" maxlength="20"
						cssClass="formInput" theme="simple" onblur="checkName();"  /> <label
					id="nameMsg" class="errorMsg" style="display: none;"></label></td>
				<th style="text-align: center; width: 10%;">品牌:</th>
				<td style="text-align: left;"><s:textfield name="goods.brand"
						id="brand" maxlength="20" cssClass="formInput" theme="simple"
						onblur="" /></td>
			</tr>
			<tr>
				<th style="text-align: center; width: 10%;">型号:</th>
				<td style="text-align: left;"><s:textfield name="goods.model"
						id="model" maxlength="20" cssClass="formInput" theme="simple"
						onblur="" /></td>
				<th style="text-align: center; width: 10%;"><span class="Color5">* </span>价格:</th>
				<td style="text-align: left;"><s:textfield name="goods.price" onkeyup="replaceToNumPoint(this);"
						id="price" maxlength="10" cssClass="formInput" theme="simple"
						onblur="checkPrice();" /> <label id="priceMsg" class="errorMsg"
					style="display: none;"></label></td>
			</tr>
			<tr>
				<th style="text-align: center; width: 10%;"><span class="Color5">* </span>数量:</th>
				<td style="text-align: left;"><s:textfield name="goods.count"  onkeyup="replaceToNum(this);"
						id="count" cssClass="formInput" theme="simple" maxlength="10"
						onblur="checkCount();" /> <label id="countMsg" class="errorMsg" 
					style="display: none;"></label></td>
				<th style="text-align: center; width: 10%;">厂家名称:</th>
				<td style="text-align: left;"><s:textfield
						name="goods.merchant" id="merchant" cssClass="formInput"
						theme="simple" onblur="" /></td>
			</tr>
			<tr>
				<th style="text-align: center; width: 10%;">状态:</th>
				<td style="text-align: left;"><s:select name="goods.status"
						id="status" list="#session.goodsSessionStatus" listKey="key" 
						listValue="value" cssStyle="width:153.5px;" cssClass="formSelect"
						theme="simple" /></td>
<!-- 				<th style="text-align: center; width: 10%;"><span class="Color5">* </span>进货日期:</th> -->
<%-- 				<td style="text-align: left;"><s:textfield id="purchaseDate" --%>
<%-- 						name="goods.purchaseDate" cssClass="formInput" --%>
<%-- 						onblur="checkPurchaseDate();"  --%>
<%-- 						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" --%>
<%-- 						cssStyle="width:150px;" maxlength="20" theme="simple" > --%>
<%-- 						<s:param name="value"><s:date name="goods.purchaseDate" format="yyyy-MM-dd HH:mm:ss"/></s:param>   --%>
<%-- 						</s:textfield> <label --%>
<!-- 					id="purchaseDateMsg" class="errorMsg" style="display: none;"></label> -->
<!-- 				</td> -->
			</tr>
			<tr>
				<th style="text-align: center; width: 10%;"><span class="Color5">* </span>类别:</th>
				<td style="text-align: left;" colspan="3"><s:radio
						name="goods.goodsTypeId" id="goodsTypeId" onchange="checkGoodType();"
						list="#request.goodsTypes" listKey="key" listValue="value" /></td>
			</tr>
			<tr>
			<td></td>
				<td colspan="4" style="text-align: left;"><label
					id="goodsTypeIdMsg" class="errorMsg" style="display: none;"></label>
				</td>
			</tr>
			<tr>
				<th style="text-align: center; width: 10%;">厂家地址:</th>
				<td style="text-align: left;"><s:textarea name="goods.address"
						id="address" cssClass="formTextarea" cols="45" rows="20"
						maxlength="255" cssStyle="resize:none;" /> <label
					id="commentsMsg" class="errorMsg"
					style="display: none; position: relative; top: -10px;"></label></td>
				<th style="text-align: center; width: 10%;">商品描述:</th>
				<td style="text-align: left;"><s:textarea
						name="goods.goodsDesc" id="goodsDesc" cssClass="formTextarea"
						cols="45" rows="20" maxlength="255" cssStyle="resize:none;" /></td>
			</tr>
		</table>
		<div class="formTableBottom">
			<s:if test="method=='addSave'">
				<my:permission key='sy-5801-01' value='商品添加'>
					<input id="submitInput" type="submit" class="formButton"
						value="添加商品" />
				</my:permission>
			</s:if>
			<s:else>
				<my:permission key='sy-5801-02' value='商品修改'>
					<input id="submitInput" type="submit" class="formButton"
						value="修改商品" />
				</my:permission>
			</s:else>
			<input type="button" class="formButton" value="返 回"
				onclick="go('managers/goods!list')" />
		</div>
	</s:form>
</body>
</html>