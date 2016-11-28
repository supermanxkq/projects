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
	<base target="_self" />
	<title>修改订单价格</title>
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
	<script src="js/pubValidate.js"></script>
    <script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
	window.name="win_test"
	var yuanyunfei = "";
	var yuanyouhui = "";
	//免运费
		 function freeFre(){
			 if(confirm("确认要免运费？")){
				 $("#postAmt").val("0.0000");
				 var youhui = $("#favorable").val();
				 var youfei = $("#postAmt").val();
			 	var yuanjia = $("#yuanjia").html();
				 if(youhui==null||youhui==""){
					 youhui=0;
				 }
			 
			 	$("#youhui").html(youhui);
			    $("#youfei").html(youfei);
				 var shifu = parseFloat(yuanjia)+parseFloat(youfei)+parseFloat(youhui);
			
				$("#shifu").html(shifu);
				 $("#paidAmt").val(shifu);
				 }
			 
		 }
		 //根据优惠价格修改价格
		 function updatePriceByyouhui(obj){
			 var youhui = obj.value;
			 
			 
			 
			 var youfei = $("#postAmt").val();
			 var yuanjia = $("#yuanjia").html();
			 if(youhui==null||youhui==""){
				 youhui=0;
			 }
			 
			 if(parseFloat(youhui)<=parseFloat(-yuanjia)){
			 	obj.value=yuanyouhui;
			 	alert("优惠价格不能大于原价");
			 	return;
			 }
			 
			 $("#youhui").html(youhui);
			 yuanyouhui = youhui;
			 var shifu = parseFloat(yuanjia)+parseFloat(youfei)+parseFloat(youhui);
			
			$("#shifu").html(shifu);
			 $("#paidAmt").val(shifu);
			 
		 }
		 //修改价格
		 function updatePriceByyoufei(obj){
			 var youfei = obj.value;
			 
			 if(parseFloat(youfei)>1000){
			 	obj.value=yuanyunfei;
			 	
			 	alert("运费不可大于1000");
			 	return;
			 }
			 
			 var youhui = $("#favorable").val();
			 if(youhui==null||youhui==""){
				 youhui=0;
			 }
			 var yuanjia = $("#yuanjia").html();
			 $("#youfei").html(youfei);
			 
			 var shifu = parseFloat(yuanjia)+parseFloat(youfei)+parseFloat(youhui);
			
			$("#shifu").html(shifu);
			 $("#paidAmt").val(shifu);
			 
			 yuanyunfei = youfei;
			 
		 }
		 //取消运费
		 function cancelTrade(obj){
			 if(obj.checked==true){
				 $("#orderStatus").val(5);
			 }else{
				 $("#orderStatus").val("");
			 }
		 }
		//清空优惠价格
		 function clearNoNumyunfei(obj){   
		  obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  

		  obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是. 

		  obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.   

		  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");

		}
		 function clearNoNumyunhui(obj){  
			 var t = obj.value.charAt(0);
			  obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  

			  obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是. 

			  obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.   

			  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");

			  if(t == '-'){  
			         obj.value = '-'+obj.value;  
			   }
			}
		//提交
		 function priceSubmit(){
			 $('#queryForm').submit();
			 
		 }
		 function instanceYouhui(){
		 	var shifu = $("#paidAmt").val();
		 	yuanyunfei=$("#paidAmt").val();
		 	var youfei = $("#postAmt").val();
		 	var yuanjia = $("#yuanjia").html();
		 	yuanyouhui = parseFloat(shifu)-parseFloat(youfei)-parseFloat(yuanjia);
		 	$("#youhui").html(yuanyouhui);
		 	$("#favorable").val(yuanyouhui);
		 }
	</script> 
</head>
<body onload="instanceYouhui()">
	
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search" style="font-size:17px">	
	订单原价(不含运费)<font color="red"><s:property value="ordersDTO.orderAmt"/> </font>元	
	</div>
	<s:form id="queryForm" action="orders/orders!updatePrice" method="post" target="win_test"  theme="simple" enctype="multipart/form-data" onsubmit="document.getElementById('submitInput').disabled = true;return true;" >
		<s:hidden name="method" id="method"></s:hidden>
		<s:hidden name="ordersDTO.orderId" id="orderId"></s:hidden>
		<s:hidden name="ordersDTO.paidAmt" id="paidAmt"></s:hidden>
		<s:hidden name="ordersDTO.status" id="orderStatus"></s:hidden>
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0">
		<tr>
			<th width="10%">取消交易</th>
			<th width="25%">订单编号</th>
			<th width="15%">交易时间</th>
			<th width="6%">原价</th>
			<th width="10%">涨价或优惠</th>
			<th width="10%">邮费(元)</th>
		</tr>
		<tr>
		<td><input type="checkbox" name="cancTrade" onchange="cancelTrade(this);"/></td>
		<td><s:property value="ordersDTO.orderId"/></td>
		<td><s:property value="ordersDTO.orderTimeStr"/></td>
		
		<td><s:property value="ordersDTO.orderAmt"/></td>
		<td><input type="text"  id="favorable" maxlength="6" cssClass="formInput" onblur="updatePriceByyouhui(this);" onkeyup="clearNoNumyunhui(this)" /></td>
		<td><input type="text" onblur="updatePriceByyoufei(this);" value="<s:property value='ordersDTO.postAmt'/>" name="ordersDTO.postAmt" id="postAmt" maxlength="7" cssClass="formInput" onkeyup="clearNoNumyunfei(this)"/></td>
		
		</tr>
	</table>
	<br><br>
	<div class="listBottom" style="font-size:14px">
		<div>
		<span>收货地址：<s:property value="ordersDTO.address"/></span>
		<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span><input type="button" value="免运费" onclick="freeFre();"></span>
		<span><input id="submitInput" type="button" value="确定" onclick="priceSubmit()" ></span>
		<span><input type="button" value="关闭" onclick="javascript:window.close();"></span></div>
		<div>买家实付：<span id="yuanjia"><s:property value="ordersDTO.orderAmt"/></span>+<span id="youhui">0.00</span>+<span id="youfei"><s:property value="ordersDTO.postAmt"/></span>=<font color="red"><span id="shifu"><s:property value="ordersDTO.paidAmt"/></span></font>元</div>
	<div  style="color:#888">买家实付 = 原价 + 涨价或优惠  + 运价 </div>
	<div style="color:#888">邮费为0是货到付款由买家承担</div>
	</div>
	</s:form>
	
</body> 
</html>