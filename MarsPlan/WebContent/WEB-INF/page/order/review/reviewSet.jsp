<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'reviewEdit.jsp' starting page</title>
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
		
	</script>
  </head>
  <body>
	<div class="Position">
			当前位置是：HOME &gt;&gt; 订单处理 &gt;&gt; 订单审核管理
	</div>
	<form action="order/reviewRecord!editUI" method="post">
		<table  width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable" >
			<tr>
				<th align="right" width="20%">
					<span class="Color5">* </span>订单编号：
				</th>
				<td width="30%">
					<input type="text" readonly="readonly" name="saleOrderDTO.saleOrderId" id="saleOrderId" value='<s:property value="saleOrderDTO.saleOrderId" />'/>
				</td>
				
				<th align="right" width="20%">
					<span class="Color5">* </span>订单数量：
				</th>
				<td width="30%">
					<input type="text" readonly="readonly" name="saleOrderDTO.cardQty" id="cardQty" value='<s:property value="saleOrderDTO.cardQty" />'/>
					
				</td>
			</tr>
			<tr>
				<th align="right" width="20%">
					<span class="Color5">* </span>订单金额：
				</th>
				<td width="30%">
					<input type="text" readonly="readonly" name="saleOrderDTO.orderAmt" id="orderAmt" value='<s:property value="saleOrderDTO.orderAmt" />'/>
					<span class="Color3">元</span>
				</td>
				<th align="right" width="20%">
					<span class="Color5">* </span>订单状态：
				</th>
				<td width="30%">
					<select name="saleOrderDTO.status" id="status" >
						<s:iterator value="#request.auditStatus" var="status">
							<option value="${status.key }" >${status.value }</option>
						</s:iterator>
					</select>
				</td>
			</tr>
			<tr>
				<th align="right" width="20%">
					<span class="Color5">* </span>付款方式：
				</th>
				<td width="30%">
					<input type="text" readonly="readonly" name="saleOrderDTO.paidType" id="paidType" value='<s:property value="saleOrderDTO.paidType" />'/>
				</td>
			</tr>
		</table>
	</form>	
	</body>
</html>
