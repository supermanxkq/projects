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
	<title>退款管理</title>
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
	<script src="js/datepicker/WdatePicker.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<script src="js/common.js"></script>
	<script type="text/javascript">
		$(function(){
			var isTakeGoods = $.trim($("#isTakeGoods").val());
			if(0 == isTakeGoods){
				$("#isTakeGoods").val("否");
			} else if (1 == isTakeGoods) {
				$("#isTakeGoods").val("是");
			}
			
			// 退款理由
			var reMoReason = $.trim($("#reMoReason").val());
			if(0 == reMoReason){
				$("#reMoReason").val("不想买了");
			} else if (1 == reMoReason) {
				$("#reMoReason").val("未收到货");
			} else if (2 == reMoReason) {
				$("#reMoReason").val("衣服有瑕疵");
			} else if (3 == reMoReason) {
				$("#reMoReason").val("商品错发、漏发");
			} else if (4 == reMoReason) {
				$("#reMoReason").val("收到的商品与描述不符");
			} else {
				$("#reMoReason").val("");
			}
		});
	</script> 
</head>
<body onload="query(${reMoReasonDTO.page});">
	<div class="Position">
		当前位置是：订单管理 &gt;&gt; 订单管理 &gt;&gt; 退款管理
	</div>
	
	<s:form action="tanker/tankerTrades" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formTable">
		<tr>
			<th align="right" width="20%">
				申请编号：
			</th>
			<td width="30%">
				<s:textfield name="reMoReasonDTO.reMoReId" id="reMoReId" cssClass="formInput" disabled="true" cssStyle="width:220px;" theme="simple" />
				<span class="Color5" id="reMoReIdTip"></span>
			</td>

			<th align="right" width="20%">
				订单编号：
			</th>
			<td width="30%">
				<s:textfield name="reMoReasonDTO.orderId" id="orderId" cssClass="formInput" disabled="true" cssStyle="width:220px;" theme="simple" />
				<span class="Color5" id="orderIdTip"></span>
			</td>
		</tr>
		<tr>
			<th align="right">
				会员编号：
			</th>
			<td>
				<s:textfield name="reMoReasonDTO.memId" id="memId" cssClass="formInput" disabled="true" cssStyle="width:220px;" theme="simple" />
				<span class="Color5" id="memIdTip"></span>
			</td>

			<th align="right">
				收到商品：
			</th>
			<td>
				<s:textfield name="reMoReasonDTO.isTakeGoods" id="isTakeGoods" cssClass="formInput" disabled="true" cssStyle="width:220px;" theme="simple" />
				<span class="Color5" id="isTakeGoodsTip"></span>
			</td>
		</tr>
		<tr>
			<th align="right">
				申请时间：
			</th>
			<td>
				<s:textfield name="reMoReasonDTO.reMoTime" id="reMoTime" cssClass="formInput" disabled="true" cssStyle="width:220px;" theme="simple" >
					<s:param name="value"><s:date name="reMoReasonDTO.reMoTime" format="yyyy-MM-dd HH:mm:ss"/></s:param>
				</s:textfield>
				<span class="Color5" id="reMoTimeTip"></span>
			</td>

			<th align="right">
				退款金额：
			</th>
			<td>
				<s:textfield name="reMoReasonDTO.returnMoneyShow" id="returnMoney" cssClass="formInput" disabled="true" cssStyle="width:220px;" theme="simple" />
				<span class="Color5" id="returnMoneyTip"></span>
			</td>
		</tr>
		<tr>
			<th align="right">
				申请原因：
			</th>
			<td>
				<s:textarea name="reMoReasonDTO.reMoReason" id="reMoReason" cssClass="formTextarea" disabled="true" cols="45" rows="20" style="resize:none;" maxlength="240" />
				<span class="Color5" id="reMoReasonTip"></span>
			</td>

			<th align="right">
				申请说明：
			</th>
			<td>
				<s:textarea name="reMoReasonDTO.reMoDesc" id="reMoDesc" cssClass="formTextarea" disabled="true" cols="45" rows="20" style="resize:none;" maxlength="240" />
				<span class="Color5" id="reMoDescTip"></span>
			</td>
		</tr>
	</table>
		
	<div class="formTableBottom">
		<s:if test="#session.user_session.userLevel==0">
			<!-- 0：查看；1：审核； -->
			<s:if test="reMoReasonDTO.action == 1">
				<input type="button" class="formButton" value="通过" onclick="go('orders/remoney!auditSuccess')" />
				<input type="button" class="formButton" value="拒绝" onclick="go('orders/remoney!auditFailure')" />
			</s:if>
		</s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('orders/remoney!list')" />
	</div>
		
	</s:form>
	
</body> 
</html>