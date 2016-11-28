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
	<title>商户结算</title>
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
	<script type="text/javascript" src="js/fusioncharts/fusioncharts.js"></script>
	<script src="js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript">
		//查询方法
		function query(page) {
			var merId = $.trim($("#merId").val());
			var merName = $.trim($("#merName").val());
			var flag = $.trim($("#flag").val());
			var params = {
				"merSettTotalDTO.merId":merId,
				"merSettTotalDTO.merName":merName,
				"merSettTotalDTO.flag":flag,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "modStockDTO.page" : page
		    }; 
		   ajaxData("report/mersettle!jsonPageList",params);
		}
		//加载审核/详情页面 
		function sureSett(id,settFlag,flag,lastBal,supSettAmt){
			alert(1);
			var show_MerSett = $("#show_MerSett");
			$("#needSettAmtSign").val(supSettAmt-lastBal);
			show_MerSett.find("#merSettId").val(id);
			show_MerSett.find("#actualSettAmt").val("0.00");
			show_MerSett.find("#merSettStatus").html(settFlag);
			show_MerSett.find("#lastBalance").html(lastBal+" 元");
			show_MerSett.find("#supportSettAmt").html(supSettAmt+" 元");
			show_MerSett.find("#needSettAmt").html((supSettAmt-lastBal)+" 元");
			show_MerSett.find("#thisTimeBalance").html((0-(supSettAmt-lastBal))+" 元");
            if(flag == 1){
                   button={
            	        '取消': function() {
		        
				              $(this).dialog('close');
			                 }
                       }
                }
            else{
            	button={
            	'取消': function() {
		        
			 	      $(this).dialog('close');
			         },
	             '确认结算':function(){
			        sureMerSett();//确认结算 
			        $(this).dialog('close');
			        ajaxData("report/mersettle!jsonPageList",condition);
		          }
                }
            }
			$("#show_MerSett").dialog({
				resizable: true,
				top: 240,
				height:400,
				width:800,
				modal: true,
				buttons:button,
				title:"商户结算" 
			});
			
		}
		//计算本次结余 
		function changeThisBal(){
          var needSettAmt = $("#needSettAmtSign").val(); //需要结算金额
          var actualSettAmt = $("#actualSettAmt").val(); //本次实际交付金额
          var thisBal = (actualSettAmt - needSettAmt).toFixed(2);
          var show_MerSett = $("#show_MerSett");
			show_MerSett.find("#thisTimeBalance").html(thisBal+" 元"); 
		}
		//查看结算信息
		function checkMerSett(id,settFlag,flag,lastBal,supSettAmt,thisBal,actAmt){
			var show_MerSett = $("#show_MerSett");
			$("#needSettAmtSign").val(supSettAmt-lastBal);
			show_MerSett.find("#merSettStatus").html(settFlag);   //结算状态
			show_MerSett.find("#lastBalance").html(lastBal+" 元"); //上次结余 
			show_MerSett.find("#supportSettAmt").html(supSettAmt+" 元"); //本次结算金额 
			show_MerSett.find("#needSettAmt").html((supSettAmt-lastBal)+" 元"); //本次需要结算金额 
			$("#actualSettAmt").val(actAmt); //实际支付金额 
			$("#actualSettAmt").attr({ readonly: 'true' });
			show_MerSett.find("#thisTimeBalance").html(thisBal+" 元");//本次结余 
			 if(flag == 1){
                 button={
          	        '取消': function() {
		        
				              $(this).dialog('close');
			                 }
                     }
              }
          else{
          	button={
          	'取消': function() {
		        
			 	      $(this).dialog('close');
			         },
	             '确认结算':function(){
			        sureMerSett();//确认结算 
			        $(this).dialog('close');
			       
		          }
              }
          }
			$("#show_MerSett").dialog({
				resizable: true,
				top: 240,
				height:400,
				width:800,
				modal: true,
				buttons:button,
				title:"查看结算信息"
			});
			}
		//确认结算操作
		function sureMerSett(){
			
		  var merSettId = $("#merSettId").val();//结算信息ID 
		  var actualSettAmt = $("#actualSettAmt").val(); //实际支付金额 
		  alert(actualSettAmt);
		  var actionUrl = "report/mersettle!sureMerSett";
		  var params = {
				  "merSettTotalDTO.id":merSettId,
				  "merSettTotalDTO.actualSettAmt":actualSettAmt
				   }
		  $.ajax({
		          url:actionUrl,
                  data:params,
                  dataType:"json",
                  cache:false,
                  type:"POST",
                    success:function(data){
                      alert(data.msg);
                      query($("#currPage").text());
                  },
                  error:function(data,status){
                  alert(data.msg);   
                 }
                
		  });
		}
		
		function showThreeD(){
			var actionUrl = "report/mersettle!showReportView";
			
			$.ajax({
                url:actionUrl,
                dataType:"json",
                cache:false,
                type:"POST",
                success:function(data){
				         var myChart = new FusionCharts("js/fusioncharts/fusioncswf/Pie3D.swf",
	                                                    "myChartId","500","300","0","0");
	                     myChart.setJSONData(data);
	                     myChart.render("chartDiv");	             
                 },
                error:function(){
                      alert("获取数据异常！");        
                 }
				});
			}
	</script> 
</head>
<body onload="query(${modStockDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 结算管理 &gt;&gt; 商户结算
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
		<table class="searchTable" cellpadding="0" width="96%" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
	            <td>商户编号:</td>
				<td><s:textfield id="merId" name="merSettTotalDTO.merId" cssClass="formInput" maxlength="20" theme="simple"/></td>
			    <td>商户名称:</td>
				<td><s:textfield id="merName" name="merSettTotalDTO.merName" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>结算状态</td>
	        	<td><s:select name="merSettTotalDTO.status" id="flag" list="#request.settFlag" listKey="key" headerKey="-1" headerValue="全部" listValue="value" cssClass="formSelect" theme="simple"/></td>
			     <%--<td>结算时间:</td>
				<td><s:textfield id="beginDate" name="modStockDTO.beginDate" onfocus="WdatePicker({skin:'blue'})" cssClass="Wdate formInput2" maxlength="20" theme="simple"/>至<s:textfield id="endDate" name="modStockDTO.endDate" onfocus="WdatePicker({skin:'blue'})" cssClass="Wdate formInput2" maxlength="20" theme="simple"/></td>
			    --%><td align="center"><input type="button" class="formButton" onclick="query();" value="查 询" />
			                           <input type="button" class="formButton" onclick="showThreeD();" value="show3D" /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="10%">商户编号</th>
			<th width="15%">商户名称</th>
			<th width="8%">结算状态</th>
			<th width="8%">结算日期</th>
			<th width="8%">充值总金额</th>
			<th width="8%">消费总金额</th>
			<th width="8%">消费总手续费</th>
			<th width="8%">退货总金额</th>
			<th width="8%">退货总手续费</th>
			<th width="8%">结算金额</th>
			<th width="8%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div><%--
	//显示弹出框 
	--%><div id="show_MerSett" style="display: none;">
	<s:hidden id="merSettId" name="merSettTotalDTO.Id"></s:hidden>
	<s:hidden id="needSettAmtSign"></s:hidden>
	    <table class="formTable" width="50%">
	        <tr>
	            <th align="right" width="20%"><span class="Color5">* </span>状态：</th>
		        <td width="30%" id="merSettStatus"></td>
		   </tr>
		   <tr>
		        <th align="right" width="20%">上次结余：</th>
		        <td width="30%" id="lastBalance"></td>
		   </tr>
		   <tr>
		        <th align="right" width="20%">本次结算金额：</th>
		        <td width="30%" id="supportSettAmt"></td>
		   </tr>
		   <tr>
		        <th align="right" width="20%">本次需结算金额:</th>
		        <td width="30%" id="needSettAmt"></td>  
		   </tr>
		   <tr>
		        <th align="right" width="20%">实际支付金额：</th>
		        <td width="30%"><s:textfield id="actualSettAmt" name="merSettTotalDTO.actualSettAmt"  cssStyle="width:80px;"  maxlength="20" theme="simple" onblur="changeThisBal();"/>&nbsp;&nbsp;元</td>
		   </tr>
		   <tr>
		        <th align="right" width="20%">本次结余：</th>
		        <td width="30%" id="thisTimeBalance"></td>
		   </tr>
	    </table>
		<div id="msgDiv" style="display:block">
		
		</div>
		<div id="chartDiv"></div>
	</div>
</body> 
</html>