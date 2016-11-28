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
	<title>延长收货时间</title>
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
		 function clearNoNumyunfei(obj){   
		  obj.value = obj.value.replace(/[^\d]/g,"");  //清除“数字”以外的字符  

		  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");

		}
		//延长天数提交
		 function priceSubmit(){
		 
		     var extendDay = $("#extendDay").val();
		     if(extendDay==null||extendDay==""){
		       $("#extendDay").val("0");
		     }
		     
			 $('#queryForm').submit();
			  //window.close();
		 }
		 //延长天数
		 function lastDay(){
			 
			 
			var sendDate =  $("#sendTime").html();
			var sendTime = sendDate.substring(10,sendDate.length);
			var sendDay = sendDate.substring(0,10);
			
			var extendDay = $("#extendDay").val();
			
			if(extendDay==""){
				extendDay=0;
			}
			
			sendDay = sendDay.replace(new RegExp("-","gm"),"/");
            var newsendDay = new Date(sendDay);
            var newExtend = parseInt(extendDay)+15;
            newsendDay.setDate(newsendDay.getDate()+parseInt(newExtend));
            var dd = newsendDay.getDate() < 10 ? "0" + newsendDay.getDate() : newsendDay.getDate().toString();  
            var mmm = newsendDay.getMonth()+1 < 10 ? "0" + newsendDay.getMonth()+1 : newsendDay.getMonth()+1;
            var yyyy = newsendDay.getFullYear().toString(); //2011  
            
            $("#lastTime").html(yyyy+"-"+mmm+"-"+dd +sendTime);
            
		 }
		 
	</script> 
</head>
<body onload="lastDay();">
	
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	
	<s:form id="queryForm" action="orders/orders" method="post" theme="simple" target="win_test" enctype="multipart/form-data" onsubmit="document.getElementById('submitInput').disabled = true;return true;" >
		<s:hidden name="method" id="method"></s:hidden>
		<s:hidden name="ordersDTO.orderId" id="orderId"></s:hidden>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		   <tr>
		  		<th align="right" width="20%"><strong>发货日期：</th>
		        <td width="30%" id="sendTime"><s:property value="ordersDTO.sendTimeStr"/></td>
		        <th align="right" width="20%">收货天数：</th>
		        <td width="30%">15</td>
			</tr>
		     <tr>
		  		<th align="right" width="20%"><strong>延长天数：</th>
		        <td width="30%"><s:textfield onblur = "lastDay()" onkeyup="clearNoNumyunfei(this)" name="ordersDTO.extendDays"  id="extendDay"  maxlength="8" cssClass="formInput"/></td>
		        <th align="right" width="20%">最晚收货时间：</th>
		        <td width="30%" id="lastTime"></td>
			</tr>
		   	
	 	</table>
	 	<div class="formTableBottom">
	 	
		 		<input id="submitInput" class="formButton" type="button" value="确定" onclick="priceSubmit()" >
		        <input type="button" class="formButton" value="关闭" onclick="javascript:window.close();">
		
		</div>
		
	</s:form>
	
</body> 
</html>