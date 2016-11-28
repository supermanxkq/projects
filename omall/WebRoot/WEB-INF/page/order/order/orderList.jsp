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
	<title>订单管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />	
	<script src="js/jquery-1.8.2.min.js"></script>
	<script src="js/jquery-easyui/jquery.easyui.min.js"></script>
	<script src="js/jquery.validate.js"></script>
	<script src="js/jquery.metadata.js"></script>
	<script src="js/additional-methods.min.js"></script>
	<script src="js/common.validate.js"></script>
	<link href="js/jquery/css/jquery.ui.all.css"  rel="stylesheet"  type="text/css" />	
	<link href="js/jquery-easyui/easyui.css"  rel="stylesheet"  type="text/css" />	
	<script src="js/jquery/jquery.ui.core.js"></script>
	<script src="js/jquery/jquery.ui.widget.js"></script>
	<script src="js/jquery/jquery.ui.mouse.js"></script>
	<script src="js/jquery/jquery.ui.button.js"></script>
	<script src="js/jquery/jquery.ui.draggable.js"></script>
	<script src="js/jquery/jquery.ui.position.js"></script>
	<script src="js/jquery/jquery.ui.resizable.js"></script>
	<script src="js/jquery/jquery.ui.dialog.js"></script>
	<script src="js/jquery/jquery.ui.tabs.js"></script>
	<script src="js/datepicker/WdatePicker.js"></script>
	<script src="js/common.js"></script>
	<script type="text/javascript">
	var basepath = "<%=basePath%>";
	
		$().ready(function() {
			$("#tabs").tabs({
				"selected":${activeTab}
			});
		});
		//全选\全不选
		function clickDeOrder(obj) {
			if (obj.checked == true) {  
                $("input[name='deOrderId']:checkbox").each(function() {
                    $(this).attr("checked", "checked");  
                })  
            } else {
            	$("input[name='deOrderId']:checkbox").each(function() {
                    $(this).removeAttr("checked");  
                }) 
            }
		}
		//批量选择
		function batchSelect(obj) {
			if (obj.checked == true) {  
                $("input[name='ingOrderId']:checkbox").each(function() {
                    $(this).attr("checked", "checked");  
                })  
            } else {
            	$("input[name='ingOrderId']:checkbox").each(function() {
                    $(this).removeAttr("checked");  
                }) 
            }
		}
		//批量发货
		function sentBatch(obj) {
			if (obj.checked == true) {  
                $("input[name='sentOrderId']:checkbox").each(function() {
                    $(this).attr("checked", "checked");  
                })  
            } else {
            	$("input[name='sentOrderId']:checkbox").each(function() {
                    $(this).removeAttr("checked");  
                }) 
            }
		}
		//批量免费
		function batchFree() {
			var checklist = $("input[name='deOrderId']:checkbox");
			var ischeck = false;
			for(var i=0;i<checklist.length;i++){
				if(checklist[i].checked){
					ischeck=true;
					break;
				}
			}
			if(!ischeck) {
				alert('请选中需要批量免运费的订单！');
				return;
			}
			var paramStr = "";
			checklist.each(function() {
                if($(this).attr("checked") == "checked") {
                	paramStr += $(this).val()+",";
                }  
            }) ;
			
			if(confirm("确认要批量免运费？")){
				paramStr = paramStr.substring(0,paramStr.length-1)
				
				var params = {
		    		"ids" : paramStr
				};
				 var url = basepath+"orders/orders!freeFreight";
				$.ajax({
		    		url : url,   
		    		data : params,   
		    		dataType : "json",   
		    		cache : false,  
		    		type : "POST", 
		    		error : function(textStatus, errorThrown) {
						alert("系统ajax交互错误!");
		    		},
		    		success : function(data, textStatus) {
		    			if (data.ajaxResult == "ajaxsuccess") {
		                	alert("批量免运费成功!");
		        			updateBack();
		            	}else if(data.ajaxResult == "ajaxfailure"){
		            		alert(data.msgResult);
		            	}else {
		            		alert("批量免运费失败!");
		            	}
		    		}
				});
				
			}
			
            
		}
		//批量发货
		function batchSend() {
			var checklist = $("input[name='deOrderId']:checkbox");
			var ischeck = false;
			for(var i=0;i<checklist.length;i++){
				if(checklist[i].checked){
					ischeck=true;
					break;
				}
			}
			if(!ischeck) {
				alert('请选中需要批量发货的订单！');
				return;
			}
			var paramStr = "deliveryDTO.orderIdStr=";
			checklist.each(function() {
                if($(this).attr("checked") == "checked") {
                	paramStr += $(this).val()+",";
                }  
            }) ;
            window.showModalDialog(basepath+"base/delivery!deliverPage?"+paramStr+"&close=1",window,"dialogWidth:1200px;dialogHeight:700px;dialogLeft:100px;dialogTop:50px;scroll:yes;status:no");
            updateBack(); 
            
		}
		
		//发货
		function sendOrder(orderId) {
			window.showModalDialog(basepath+"base/delivery!deliverPage?deliveryDTO.orderIdStr="+orderId+"&close=1",window,"dialogWidth:1200px;dialogHeight:700px;dialogLeft:100px;dialogTop:50px;scroll:yes;status:no") ;
			updateBack(); 
		}
		//分页
		function turnPage(page) {
			$('#pageId').val(page);
			updateBack();
		}
		//改变选择
		function changeSelect(obj) {
			$('#pageId').val(obj.value);
			updateBack();
		}
		
		function didntSend() {
			$('#orderStatusId').val(0);
			updateBack();
		}
		//取消订单
		function cancelSend() {
			$('#orderStatusId').val(3);
			updateBack();
		}
		
		function tabClick(obj) {
		
		$('#pageId').val(0);
			if($(obj).attr("href") == "#tabs-1") {
				$('#actTabId').val(0);
				$('#orderStatusId').val(0);
				$('#orderIsClose').val("");
				updateBack();
			} else if($(obj).attr("href") == "#tabs-2") {
				$('#actTabId').val(1);
				$('#orderStatusId').val(3);
				$('#orderIsClose').val("");
				updateBack();
			} else if($(obj).attr("href") == "#tabs-3") {
				$('#actTabId').val(2);
				$('#orderStatusId').val(4);
				$('#orderIsClose').val("");
				updateBack();
			}else if($(obj).attr("href") == "#tabs-4") {
				$('#actTabId').val(3);
				$('#orderStatusId').val(8);
				$('#orderIsClose').val("");
				updateBack();
			}else if($(obj).attr("href") == "#tabs-5") {
				$('#actTabId').val(4);
				$('#orderStatusId').val(6);
				$('#orderIsClose').val("");
				updateBack();
			}else if($(obj).attr("href") == "#tabs-6") {
				$('#actTabId').val(5);
				$('#orderStatusId').val(6);
				$('#orderIsClose').val("");
				updateBack();
			}else if($(obj).attr("href") == "#tabs-7") {
				$('#actTabId').val(6);
				$('#orderStatusId').val("");
				$('#orderIsClose').val("");
				updateBack();
			}else if($(obj).attr("href") == "#tabs-8") {
				$('#actTabId').val(7);
				$('#orderIsClose').val("");
				$('#orderStatusId').val("-1");
				updateBack();

		  }
		
		}
		function yesIdo(obj) {
			alert($(obj).attr('order-no'));
		}
		//显示更多
		function displayMore(){
			var moreselect = document.getElementById('moreselect');
			var moreselect1 = document.getElementById('moreselect1');
			var moreselect2 = document.getElementById('moreselect2');
			if(moreselect.style.display=="none"){
				 moreselect.style.display="";
				 moreselect1.style.display="";
			     moreselect2.style.display="";
			     $('#displaymore').val("隐藏查询");
			}else{
				 moreselect.style.display="none";
				 moreselect1.style.display="none";
			     moreselect2.style.display="none";
			     $('#displaymore').val("更多查询");
			}
			
		}
		//显示关闭的订单
		function showCloseOrder(check){
			
			if(check.checked==true){
				$('#orderIsClose').val(0);
				updateBack();
				
			}else{
				$('#orderIsClose').val("");
				updateBack();
			}
			
		}
		//显示订单
		function showStatusLs(){
			$('#orderStatusId').val($('#orderStatusLs').val());
			updateBack();
		}
		
		//关闭订单
		var closeOrder = function(url,id) {
			if(confirm("确认要关闭订单？")){
				var params = {
		    		"id" : id
				};
				
				$.ajax({
		    		url : url,   
		    		data : params,   
		    		dataType : "json",   
		    		cache : false,  
		    		type : "POST", 
		    		error : function(textStatus, errorThrown) {
						alert("系统ajax交互错误!");
		    		},
		    		success : function(data, textStatus) {
		    			if (data.ajaxResult == "ajaxsuccess") {
		                	alert("关闭订单成功!");
		                	updateBack();
		            	}else if(data.ajaxResult == "ajaxfailure"){
		            		alert(data.msgResult);
		            	}else {
		            		alert("关闭订单失败!");
		            	}
		    		}
				});
			}
		}
		//打开修改价格窗口
		function openUpdatePrice(orderId){
			window.showModalDialog(basepath+"orders/orders!updatePriceUI?ordersDTO.orderId="+orderId,window,"dialogWidth:800px;dialogHeight:400px;dialogLeft:200px;dialogTop:100px;scroll:no;status:no") ;
			updateBack(); 
		}
		//延长收货时间
		function openExtendDays(orderId){
			window.showModalDialog(basepath+"orders/orders!extendDayUI?ordersDTO.orderId="+orderId,window,"dialogWidth:800px;dialogHeight:400px;dialogLeft:200px;dialogTop:100px;scroll:no;status:no") 
		}
		//订单详情
		function openOrderInfo(orderId){
			window.showModalDialog(basepath+"orders/orders!orderUI?ordersDTO.orderId="+orderId,window,"dialogWidth:1200px;dialogHeight:700px;dialogLeft:100px;dialogTop:50px;scroll:yes;status:no") 
		}
		//发货详情
		function openSendOrderInfo(orderId){
			window.showModalDialog(basepath+"orders/orders!sendOrderUI?ordersDTO.orderId="+orderId,window,"dialogWidth:1200px;dialogHeight:700px;dialogLeft:100px;dialogTop:50px;scroll:no;status:no") ;
			updateBack(); 
		}
		//查看物流
		function openDeliverInfo(orderId){
			window.showModalDialog(basepath+"base/delivery!deliverOrderDetail?deliveryDTO.orderId="+orderId+"&close=1",window,"dialogWidth:1000px;dialogHeight:600px;dialogLeft:200px;dialogTop:100px;scroll:no;status:no") 
		}
		//去评价
		var win={};
		function openEvaluation(orderId){
			if(win.closed==false){
			 	win.focus();
			 	return;
			} 
			win=window.open(basepath+"evaluation/evaluation!addUI?orderId="+orderId,"evaluation","width=700px,height=500px,left=300px,top=100px,scroll=no,status=no,toolbar=no,menubar=no,location=no") 
		}
		
		
		
		
		function updateBack(){
			document.getElementById("queryForm").action=basepath+"orders/orders!list";
		    document.getElementById("queryForm").target=""
			$('#queryForm').submit();
		}
		
		
		
		//导出订单
		var exportOrderList = function() {
		    var goodsName = $.trim($("#goodsName").val());
			var orderId = $.trim($("#orderId").val());
			var memName = $.trim($("#memName").val());
			var startTime = $.trim($("#startTime").val());
			var endTime = $.trim($("#endTime").val());
			var criticalStatus = $.trim($("#criticalStatus").val());
			var actTabId = $.trim($("#actTabId").val());
			var orderStatusId = $.trim($("#orderStatusId").val());
			var orderIsClose = $.trim($("#orderIsClose").val());
			var strurl = "ordersDTO.goodsName="+goodsName+
			             "&ordersDTO.orderId="+orderId+
			             "&ordersDTO.memName="+memName+
			             "&ordersDTO.startTime="+startTime+
			             "&ordersDTO.endTime="+endTime+
			             "&ordersDTO.criticalStatus="+criticalStatus+
			             "&ordersDTO.actTabId="+actTabId+
			             "&ordersDTO.status="+orderStatusId+
			             "&ordersDTO.isClose="+orderIsClose;
			             
		   document.getElementById("queryForm").action=basepath+"orders/orders!export";
		    document.getElementById("queryForm").target="_blank"
		   $('#queryForm').submit();
			
		}
		
		/* 商城首界面待处理业务查看中用于跳转不同订单类型tab页面         王楠*/
		$(function(){
			var href=document.getElementById("jumpTab");
			var title=href.title;
			$("#type").val(title);
			tabClick(href);
			if(title.length>0){
				$("#back").show();
			}else{
				$("#back").hide();
			}
			
		  }
		);
		
		 function clearNoNumyunfei(obj){   
		  obj.value = obj.value.replace(/[^\d]/g,"");  //清除“数字”以外的字符  

		  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");

		}
		
	</script> 
	<style>
	</style>
</head>
<body>
	
	<div class="Position">
		当前位置是：订单管理 &gt;&gt; 订单管理 &gt;&gt; 订单管理
	</div>
	<div style="display:none"><form action="" id="exportAction" target="_blank"></form></div>
	<!-- 商城首界面待处理业务查看中用于跳转不同订单类型tab页面         王楠-->
	<a href="#tabs-${param.type }"  title="${param.type }" id="jumpTab" style="display: none;"></a>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<div class="search" align="left" style="margin-bottom:10px;padding-bottom:0px;">	
		<form action="orders/orders!list" method="POST" id="queryForm">
			<fieldset  style='margin-left:20px;margin-top:0px;padding-bottom:20px;padding-left:20px;'>
				<legend >
					查询条件
				</legend>
				<table><tr><td width="92%">
				<table style="margin-left:20px;margin-top:20px;" cellspacing="5">
					<tr>
						<td>买家昵称:</td>
						<td><s:textfield name="ordersDTO.memName" id="memName" theme="simple" maxlength="25"></s:textfield> </td>
						<td>订单编号:</td>
						<td><s:textfield name="ordersDTO.orderId" id="orderId" onkeyup="clearNoNumyunfei(this)" maxlength="16" theme="simple"></s:textfield> </td>
						
						<td> </td>
					</tr>
					<tr id="moreselect" style="display:">
						
						<td>创建时间:</td>
						<td><s:textfield name="ordersDTO.startTime" id="startTime" theme="simple" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></s:textfield>  </td>
						<td>至:</td>
						<td><s:textfield name="ordersDTO.endTime" id="endTime" theme="simple" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></s:textfield></td>
					</tr>
					<tr>
					<td ><div id="moreselect1" style="display:">评价状态:</div></td>
					<td ><div id="moreselect2" style="display:"><s:select name="ordersDTO.criticalStatus" id="criticalStatus"   list="#request.criticalStatusDic" listKey="key" listValue="value" headerKey="-1" headerValue="全部"
				 cssClass="formInput" theme="simple"/></div></td>
				 <td></td><td><input type="button" onclick="updateBack()" class="formButton"  value="查 询" /></td>
					<td ><input type="button" class="formButton" onclick="exportOrderList()" value="导出" /></td>
					</tr>
				</table>
				</td>
				<td  ><div></div style="margin-left:10px;margin-top:20px;" ><input type="button" class="formButton" id="displaymore" onclick="displayMore()"  value="隐藏查询" /></div></td>
				</tr>
				</table>
			</fieldset>
			<s:hidden name="ordersDTO.page" id="pageId"></s:hidden>
			<s:hidden name="ordersDTO.activeTab" id="actTabId"> </s:hidden>
			<s:hidden name="ordersDTO.status" id="orderStatusId"> </s:hidden>
			<s:hidden name="ordersDTO.isClose" id="orderIsClose"> </s:hidden>
		
		
		
		</form>
	</div>
	<div style="width:100%;margin:0px auto;margin-left:5px;">
		<div id="tabs" style="margin-left:30px;margin-right:20px;">
			<ul>
				<li><a href="#tabs-1" onclick="tabClick(this)">等待买家付款</a></li>
				<li><a href="#tabs-2" onclick="tabClick(this)">等待发货</a></li>
				<li><a href="#tabs-3" onclick="tabClick(this)">已发货</a></li>
				<li><a href="#tabs-4" onclick="tabClick(this)">退款中</a></li>
				<li><a href="#tabs-5" onclick="tabClick(this)">需要评价</a></li>
				<li><a href="#tabs-6" onclick="tabClick(this)">成功的订单</a></li>
				<li><a href="#tabs-7" onclick="tabClick(this)">关闭的订单</a></li>
				<li><a href="#tabs-8" onclick="tabClick(this)">历史订单</a></li>
			</ul>
			<div id="tabs-1" style="padding-left:0px;">
				
				<div style="margin:0px;padding:0px;width:100%">
					<div style="padding-left:0px;">
						<table width="100%" class="listTable">
						<tr>
						<td width="30%">订单编号</td><td width="15%">交易时间</td>
						<td width="15%">买家</td><td width="15%">交易状态</td><td width="15%">实收款(元)</td><td width="10%">评价</td>
						</tr>
						</table>
					</div>
					<div style="padding-left:10px;padding-top:2px;padding-bottom:2px;border:1px solid #9DB5E3;">
					
						 <input type="checkbox" value="1" onclick="clickDeOrder(this)"/> 
				
					
					<a href="javascript:void(0)" onclick="batchFree()" style="color:blue;">批量免运费</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<s:if test="ordersDTO.isClose==0">
					<input type="checkbox" checked=true  onclick="showCloseOrder(this)" /> 
					</s:if>
					<s:else>
					<input type="checkbox"  onclick="showCloseOrder(this)" /> 
					</s:else>
					不显示已关闭的订单
					</div>
					<s:if test="#request.orderListDfk.resultlist.size > 0">
						<s:iterator value="#request.orderListDfk.resultlist" var="order">
							<div style="margin:5px auto;">
								<table width="100%" id="listTable" class="listTable" style="padding-left:0px;" cellpadding="0" cellspacing="0" >
									<tr>
									<td width="5%" ><s:if test="#order.isClose!=1">
									      <input type="checkbox" name="deOrderId" value="${order.orderId }"/>
									      </s:if></td>
										<td width="25%" >${order.orderId }</td>
										<td width="15%">${order.orderTimeStr}</td>
										<td width="15%">${order.memName}</td>
										<td width="15%" align="center" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>等待买家付款</span><br/>
											<span><a href=javascript:openOrderInfo('${order.orderId}')  style="color:blue;" >详情</a></span><br/>
											<s:if test="#order.isClose==1">
											<span>已关闭</span>
										    </s:if>
										    <s:else>
										    <span><a style="color:blue;" href=javascript:closeOrder('orders/orders!closeOrder','${order.orderId}') title='关闭订单'>关闭订单</a></span>
										    </s:else>
										</td>
										
										<td width="15%" align="center" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>${order.paidAmt}</span><br/>
											<span>(含快递：${order.postAmt})</span><br/>
											<s:if test="#order.isClose!=1">
											<span><a href=javascript:openUpdatePrice('${order.orderId}')  style="color:blue;" >修改价格</a></span>
											</s:if>
										</td>
										<td width="10%"></td>
									</tr>
								</table>
								
							</div>	
						</s:iterator>
						<div class="listBottom" style="margin-top:10px;margin-bottom:20px;">
							<div class="Fr" id="pageNav">
								<font color="#000">共<span class="page">${orderListDfk.totalrecord}</span>条记录，
								第<span id="currPage">${ordersDTO.page}</span>/<span>${ordersDTO.totalPage}</span>页，每页${ordersDTO.pageNum}条&nbsp;
								<s:if test="ordersDTO.page > 1"><a href="javascript:void(0)" onclick="turnPage(1)">[首页]</a></s:if>
								<s:else>[首页]</s:else>
								&nbsp;
								<s:if test="ordersDTO.page > 1">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.page-1})">[上一页]</a>
								</s:if>
								<s:else>
									[上一页]
								</s:else>
								<s:if test="ordersDTO.totalPage > ordersDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.page+1})">[下一页]</a>
								</s:if>
								<s:else>
									[下一页]
								</s:else>
								&nbsp;
								<s:if test="ordersDTO.totalPage > ordersDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.totalPage})">[尾页]</a>
								</s:if>
								<s:else>
									[尾页]
								</s:else>
								&nbsp;转到
								<select name="" onchange="changeSelect(this)" >
									<s:iterator value="new int[ordersDTO.totalPage]" status="i">
										<s:if test="#i.index+1 == ordersDTO.page">
											<option value="${i.index+1}" selected="selected">${i.index+1}</option>
										</s:if>
										<s:else>
											<option value="${i.index+1}">${i.index+1}</option>
										</s:else>
									</s:iterator>
								</select>
								页</font>
							</div>
						</div>
						
					</s:if>
					<s:else>
						<div align="center" style="margin-top:20px;">
							没有找到数据！
						</div>
					</s:else>
				
			</div>
			</div>
			<div id="tabs-2" style="padding-left:0px;">
				
				<div style="margin:0px;padding:0px;width:100%">
					<div style="padding-left:0px;">
						<table width="100%" class="listTable">
						<tr>
						<td width="30%">订单编号</td><td width="15%">交易时间</td>
						<td width="15%">买家</td><td width="15%">交易状态</td><td width="15%">实收款(元)</td><td width="10%">评价</td>
						</tr>
						</table>
					</div>
					<div style="padding-left:10px;padding-top:2px;padding-bottom:2px;border:1px solid #9DB5E3;">
					
					<input type="checkbox" value="1" onclick="clickDeOrder(this)" /> 
					
					<a href="javascript:void(0)" onclick="batchSend()" style="color:blue;">批量发货</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<s:if test="ordersDTO.isClose==0">
					<input type="checkbox" checked=true  onclick="showCloseOrder(this)" /> 
					</s:if>
					<s:else>
					<input type="checkbox"  onclick="showCloseOrder(this)" /> 
					</s:else>
					不显示已关闭的订单
					</div>
					<s:if test="#request.orderListDfh.resultlist.size > 0">
						<s:iterator value="#request.orderListDfh.resultlist" var="order">
							<div style="margin:5px auto;">
								
								<table width="100%" id="listTable" class="listTable" style="padding-left:0px;" cellpadding="0" cellspacing="0" >
									<tr>
										<td width="5%" ><s:if test="#order.isClose!=1">
									      <input type="checkbox" name="deOrderId" value="${order.orderId }"/>
									      </s:if></td>
										<td width="25%" >${order.orderId }</td>
										<td width="15%">${order.orderTimeStr}</td>
										<td width="15%">${order.memName}</td>
										<td width="15%" align="center" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>买家已付款</span><br/>
											<span><a href=javascript:openSendOrderInfo('${order.orderId}')  style="color:blue;" >详情</a></span><br/>
											<span><a  href=javascript:sendOrder('${order.orderId}')  style="color:blue;" >发货</a></span>
										</td>
										
										<td width="15%" align="center" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>${order.paidAmt}</span><br/>
											<span>(含快递：${order.postAmt})</span><br/>
										</td>
										<td width="10%"></td>
									</tr>
								</table>
								
							</div>	
						</s:iterator>
						<div class="listBottom" style="margin-top:10px;margin-bottom:20px;">
							<div class="Fr" id="pageNav">
								<font color="#000">共<span class="page">${orderListDfh.totalrecord}</span>条记录，
								第<span id="currPage">${ordersDTO.page}</span>/<span>${ordersDTO.totalPage}</span>页，每页${ordersDTO.pageNum}条&nbsp;
								<s:if test="ordersDTO.page > 1"><a href="javascript:void(0)" onclick="turnPage(1)">[首页]</a></s:if>
								<s:else>[首页]</s:else>
								&nbsp;
								<s:if test="ordersDTO.page > 1">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.page-1})">[上一页]</a>
								</s:if>
								<s:else>
									[上一页]
								</s:else>
								<s:if test="ordersDTO.totalPage > ordersDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.page+1})">[下一页]</a>
								</s:if>
								<s:else>
									[下一页]
								</s:else>
								&nbsp;
								<s:if test="ordersDTO.totalPage > ordersDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.totalPage})">[尾页]</a>
								</s:if>
								<s:else>
									[尾页]
								</s:else>
								&nbsp;转到
								<select name="" onchange="changeSelect(this)" >
									<s:iterator value="new int[ordersDTO.totalPage]" status="i">
										<s:if test="#i.index+1 == ordersDTO.page">
											<option value="${i.index+1}" selected="selected">${i.index+1}</option>
										</s:if>
										<s:else>
											<option value="${i.index+1}">${i.index+1}</option>
										</s:else>
									</s:iterator>
								</select>
								页</font>
							</div>
						</div>
						
					</s:if>
					<s:else>
						<div align="center" style="margin-top:20px;">
							没有找到数据！
						</div>
					</s:else>
				</div>
			</div>
			
			<div id="tabs-3" style="padding-left:0px;">
				
				<div style="margin:0px;padding:0px;width:100%">
					<div style="padding-left:0px;">
						<table width="100%" class="listTable">
						<tr>
						<td width="30%">订单编号</td><td width="15%">交易时间</td>
						<td width="15%">买家</td><td width="15%">交易状态</td><td width="15%">实收款(元)</td><td width="10%">评价</td>
						</tr>
						</table>
					</div>
					<div style="padding-left:10px;padding-top:2px;padding-bottom:2px;border:1px solid #9DB5E3;">
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<s:if test="ordersDTO.isClose==0">
					<input type="checkbox" checked=true  onclick="showCloseOrder(this)" /> 
					</s:if>
					<s:else>
					<input type="checkbox"  onclick="showCloseOrder(this)" /> 
					</s:else>
					不显示已关闭的订单
					</div>
					<s:if test="#request.orderListYfh.resultlist.size > 0">
						<s:iterator value="#request.orderListYfh.resultlist" var="order">
							<div style="margin:5px auto;">
								
								<table width="100%" id="listTable" class="listTable" style="padding-left:0px;" cellpadding="0" cellspacing="0" >
									<tr>
										
										<td width="30%" >${order.orderId }</td>
										<td width="15%">${order.orderTimeStr}</td>
										<td width="15%">${order.memName}</td>
										<td width="15%" align="center" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>卖家已发货</span><br/>
											<span><a href=javascript:openOrderInfo('${order.orderId}')  style="color:blue;" >详情</a></span><br/>
											<span><a href=javascript:openExtendDays('${order.orderId}')  style="color:blue;" >延长收货时间</a></span>
										</td>
										
										<td width="15%" align="center" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>${order.paidAmt}</span><br/>
											<span>(含快递：${order.postAmt})</span><br/>
											<span>
											
											<s:if test="#order.deliveryWay==0">无需物流</s:if>
											<s:else>
											<a href=javascript:openDeliverInfo('${order.orderId}')  style="color:blue;" >查看物流</a>
											</s:else>
											
											</span>
										</td>
										<td width="10%"></td>
									</tr>
								</table>
								
							</div>	
						</s:iterator>
						<div class="listBottom" style="margin-top:10px;margin-bottom:20px;">
							<div class="Fr" id="pageNav">
								<font color="#000">共<span class="page">${orderListYfh.totalrecord}</span>条记录，
								第<span id="currPage">${ordersDTO.page}</span>/<span>${ordersDTO.totalPage}</span>页，每页${ordersDTO.pageNum}条&nbsp;
								<s:if test="ordersDTO.page > 1"><a href="javascript:void(0)" onclick="turnPage(1)">[首页]</a></s:if>
								<s:else>[首页]</s:else>
								&nbsp;
								<s:if test="ordersDTO.page > 1">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.page-1})">[上一页]</a>
								</s:if>
								<s:else>
									[上一页]
								</s:else>
								<s:if test="ordersDTO.totalPage > ordersDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.page+1})">[下一页]</a>
								</s:if>
								<s:else>
									[下一页]
								</s:else>
								&nbsp;
								<s:if test="ordersDTO.totalPage > ordersDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.totalPage})">[尾页]</a>
								</s:if>
								<s:else>
									[尾页]
								</s:else>
								&nbsp;转到
								<select name="" onchange="changeSelect(this)" >
									<s:iterator value="new int[ordersDTO.totalPage]" status="i">
										<s:if test="#i.index+1 == ordersDTO.page">
											<option value="${i.index+1}" selected="selected">${i.index+1}</option>
										</s:if>
										<s:else>
											<option value="${i.index+1}">${i.index+1}</option>
										</s:else>
									</s:iterator>
								</select>
								页</font>
							</div>
						</div>
						
					</s:if>
					<s:else>
						<div align="center" style="margin-top:20px;">
							没有找到数据！
						</div>
					</s:else>
				</div>
			</div>
			
			<div id="tabs-4" style="padding-left:0px;">
				
				<div style="margin:0px;padding:0px;width:100%">
					<div style="padding-left:0px;">
						<table width="100%" class="listTable">
						<tr>
						<td width="30%">订单编号</td><td width="15%">交易时间</td>
						<td width="15%">买家</td><td width="15%">交易状态</td><td width="15%">实收款(元)</td><td width="10%">评价</td>
						</tr>
						</table>
					</div>
					<div style="padding-left:10px;padding-top:2px;padding-bottom:2px;border:1px solid #9DB5E3;">
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<s:if test="ordersDTO.isClose==0">
					<input type="checkbox" checked=true  onclick="showCloseOrder(this)" /> 
					</s:if>
					<s:else>
					<input type="checkbox"  onclick="showCloseOrder(this)" /> 
					</s:else>
					不显示已关闭的订单
					</div>
					<s:if test="#request.orderListTkz.resultlist.size > 0">
						<s:iterator value="#request.orderListTkz.resultlist" var="order">
							<div style="margin:5px auto;">
								
								<table width="100%" id="listTable" class="listTable" style="padding-left:0px;" cellpadding="0" cellspacing="0" >
									<tr>
										<td width="30%" >${order.orderId }</td>
										<td width="15%">${order.orderTimeStr}</td>
										<td width="15%">${order.memName}</td>
										<td width="15%" align="center" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>退款中</span><br/>
											<span><a href=javascript:openOrderInfo('${order.orderId}')  style="color:blue;" >详情</a></span><br/>
										</td>
										
										<td width="15%" align="center" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>${order.paidAmt}</span><br/>
											<span>(含快递：${order.postAmt})</span><br/>
										</td>
										<td width="10%"></td>
									</tr>
								</table>
								
							</div>	
						</s:iterator>
						<div class="listBottom" style="margin-top:10px;margin-bottom:20px;">
							<div class="Fr" id="pageNav">
								<font color="#000">共<span class="page">${orderListTkz.totalrecord}</span>条记录，
								第<span id="currPage">${ordersDTO.page}</span>/<span>${ordersDTO.totalPage}</span>页，每页${ordersDTO.pageNum}条&nbsp;
								<s:if test="ordersDTO.page > 1"><a href="javascript:void(0)" onclick="turnPage(1)">[首页]</a></s:if>
								<s:else>[首页]</s:else>
								&nbsp;
								<s:if test="ordersDTO.page > 1">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.page-1})">[上一页]</a>
								</s:if>
								<s:else>
									[上一页]
								</s:else>
								<s:if test="ordersDTO.totalPage > ordersDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.page+1})">[下一页]</a>
								</s:if>
								<s:else>
									[下一页]
								</s:else>
								&nbsp;
								<s:if test="ordersDTO.totalPage > ordersDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.totalPage})">[尾页]</a>
								</s:if>
								<s:else>
									[尾页]
								</s:else>
								&nbsp;转到
								<select name="" onchange="changeSelect(this)" >
									<s:iterator value="new int[ordersDTO.totalPage]" status="i">
										<s:if test="#i.index+1 == ordersDTO.page">
											<option value="${i.index+1}" selected="selected">${i.index+1}</option>
										</s:if>
										<s:else>
											<option value="${i.index+1}">${i.index+1}</option>
										</s:else>
									</s:iterator>
								</select>
								页</font>
							</div>
						</div>
						
					</s:if>
					<s:else>
						<div align="center" style="margin-top:20px;">
							没有找到数据！
						</div>
					</s:else>
				</div>
			</div>
			
			<div id="tabs-5" style="padding-left:0px;">
				
				<div style="margin:0px;padding:0px;width:100%">
					<div style="padding-left:0px;">
						<table width="100%" class="listTable">
						<tr>
						<td width="30%">订单编号</td><td width="15%">交易时间</td>
						<td width="15%">买家</td><td width="15%">交易状态</td><td width="15%">实收款(元)</td><td width="10%">评价</td>
						</tr>
						</table>
					</div>
					<div style="padding-left:10px;padding-top:2px;padding-bottom:2px;border:1px solid #9DB5E3;">
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<s:if test="ordersDTO.isClose==0">
					<input type="checkbox" checked=true  onclick="showCloseOrder(this)" /> 
					</s:if>
					<s:else>
					<input type="checkbox"  onclick="showCloseOrder(this)" /> 
					</s:else>
					不显示已关闭的订单
					</div>
					<s:if test="#request.orderListXpj.resultlist.size > 0">
						<s:iterator value="#request.orderListXpj.resultlist" var="order">
							<div style="margin:5px auto;">
								
								<table width="100%" id="listTable" class="listTable" style="padding-left:0px;" cellpadding="0" cellspacing="0" >
									<tr>
										<td width="30%" >${order.orderId }</td>
										<td width="15%">${order.orderTimeStr}</td>
										<td width="15%">${order.memName}</td>
										<td width="15%" align="center" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>交易成功</span><br/>
											<span><a href=javascript:openOrderInfo('${order.orderId}')  style="color:blue;" >详情</a></span><br/>
											<s:if test="#order.isClose==1">
											<span>已关闭</span>
										    </s:if>
										    <s:else>
										    <span><a style="color:blue;" href=javascript:closeOrder('orders/orders!closeOrder','${order.orderId}') title='关闭订单'>关闭订单</a></span>
										    </s:else>
										</td>
										
										<td width="15%" align="center" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>${order.paidAmt}</span><br/>
											<span>(含快递：${order.postAmt})</span><br/>
											<span>
											
											<s:if test="#order.deliveryWay==0">无需物流</s:if>
											<s:else>
											<a href=javascript:openDeliverInfo('${order.orderId}')  style="color:blue;" >查看物流</a>
											</s:else>
											</span>
											
										</td>
										<td width="10%">
										<s:if test="#order.isClose!=1&&#order.status==6&&#order.criticalStatus!=1">
										<span><a href=javascript:openEvaluation('${order.orderId}')  style="color:blue;" >去评价</a></span>
										</s:if>
										</td>
									</tr>
								</table>
								
							</div>	
						</s:iterator>
						<div class="listBottom" style="margin-top:10px;margin-bottom:20px;">
							<div class="Fr" id="pageNav">
								<font color="#000">共<span class="page">${orderListXpj.totalrecord}</span>条记录，
								第<span id="currPage">${ordersDTO.page}</span>/<span>${ordersDTO.totalPage}</span>页，每页${ordersDTO.pageNum}条&nbsp;
								<s:if test="ordersDTO.page > 1"><a href="javascript:void(0)" onclick="turnPage(1)">[首页]</a></s:if>
								<s:else>[首页]</s:else>
								&nbsp;
								<s:if test="ordersDTO.page > 1">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.page-1})">[上一页]</a>
								</s:if>
								<s:else>
									[上一页]
								</s:else>
								<s:if test="ordersDTO.totalPage > ordersDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.page+1})">[下一页]</a>
								</s:if>
								<s:else>
									[下一页]
								</s:else>
								&nbsp;
								<s:if test="ordersDTO.totalPage > ordersDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.totalPage})">[尾页]</a>
								</s:if>
								<s:else>
									[尾页]
								</s:else>
								&nbsp;转到
								<select name="" onchange="changeSelect(this)" >
									<s:iterator value="new int[ordersDTO.totalPage]" status="i">
										<s:if test="#i.index+1 == ordersDTO.page">
											<option value="${i.index+1}" selected="selected">${i.index+1}</option>
										</s:if>
										<s:else>
											<option value="${i.index+1}">${i.index+1}</option>
										</s:else>
									</s:iterator>
								</select>
								页</font>
							</div>
						</div>
						
					</s:if>
					<s:else>
						<div align="center" style="margin-top:20px;">
							没有找到数据！
						</div>
					</s:else>
				</div>
			</div>
			
			<div id="tabs-6" style="padding-left:0px;">
				
				<div style="margin:0px;padding:0px;width:100%">
					<div style="padding-left:0px;">
						<table width="100%" class="listTable">
						<tr>
						<td width="30%">订单编号</td><td width="15%">交易时间</td>
						<td width="15%">买家</td><td width="15%">交易状态</td><td width="15%">实收款(元)</td><td width="10%">评价</td>
						</tr>
						</table>
					</div>
					<div style="padding-left:10px;padding-top:2px;padding-bottom:2px;border:1px solid #9DB5E3;">
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<s:if test="ordersDTO.isClose==0">
					<input type="checkbox" checked=true  onclick="showCloseOrder(this)" /> 
					</s:if>
					<s:else>
					<input type="checkbox"  onclick="showCloseOrder(this)" /> 
					</s:else>
					不显示已关闭的订单
					</div>
					<s:if test="#request.orderListYcg.resultlist.size > 0">
						<s:iterator value="#request.orderListYcg.resultlist" var="order">
							<div style="margin:5px auto;">
								
								<table width="100%" id="listTable" class="listTable" style="padding-left:0px;" cellpadding="0" cellspacing="0" >
									<tr>
										<td width="30%" >${order.orderId }</td>
										<td width="15%">${order.orderTimeStr}</td>
										<td width="15%">${order.memName}</td>
										<td width="15%" align="center" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>交易成功</span><br/>
											<span><a href=javascript:openOrderInfo('${order.orderId}')  style="color:blue;" >详情</a></span><br/>
											<s:if test="#order.isClose==1">
											<span>已关闭</span>
										    </s:if>
										    <s:else>
										    <span><a style="color:blue;" href=javascript:closeOrder('orders/orders!closeOrder','${order.orderId}') title='关闭订单'>关闭订单</a></span>
										    </s:else>
										</td>
										
										<td width="15%" align="center" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>${order.paidAmt}</span><br/>
											<span>(含快递：${order.postAmt})</span><br/>
											<span>
											
											<s:if test="#order.deliveryWay==0">无需物流</s:if>
											<s:else>
											<a href=javascript:openDeliverInfo('${order.orderId}')  style="color:blue;" >查看物流</a>
											</s:else>
											</span>
										</td>
										<td width="10%">
											<s:if test="#order.isClose!=1&&#order.status==6&&#order.criticalStatus!=1">
										    <span><a href=javascript:openEvaluation('${order.orderId}')  style="color:blue;" >去评价</a></span>
										    </s:if>
										    <s:elseif test="#order.status==6&&#order.criticalStatus==1">
										     已评价
										    </s:elseif>
										</td>
									</tr>
								</table>
								
							</div>	
						</s:iterator>
						<div class="listBottom" style="margin-top:10px;margin-bottom:20px;">
							<div class="Fr" id="pageNav">
								<font color="#000">共<span class="page">${orderListYcg.totalrecord}</span>条记录，
								第<span id="currPage">${ordersDTO.page}</span>/<span>${ordersDTO.totalPage}</span>页，每页${ordersDTO.pageNum}条&nbsp;
								<s:if test="ordersDTO.page > 1"><a href="javascript:void(0)" onclick="turnPage(1)">[首页]</a></s:if>
								<s:else>[首页]</s:else>
								&nbsp;
								<s:if test="ordersDTO.page > 1">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.page-1})">[上一页]</a>
								</s:if>
								<s:else>
									[上一页]
								</s:else>
								<s:if test="ordersDTO.totalPage > ordersDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.page+1})">[下一页]</a>
								</s:if>
								<s:else>
									[下一页]
								</s:else>
								&nbsp;
								<s:if test="ordersDTO.totalPage > ordersDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.totalPage})">[尾页]</a>
								</s:if>
								<s:else>
									[尾页]
								</s:else>
								&nbsp;转到
								<select name="" onchange="changeSelect(this)" >
									<s:iterator value="new int[ordersDTO.totalPage]" status="i">
										<s:if test="#i.index+1 == ordersDTO.page">
											<option value="${i.index+1}" selected="selected">${i.index+1}</option>
										</s:if>
										<s:else>
											<option value="${i.index+1}">${i.index+1}</option>
										</s:else>
									</s:iterator>
								</select>
								页</font>
							</div>
						</div>
						
					</s:if>
					<s:else>
						<div align="center" style="margin-top:20px;">
							没有找到数据！
						</div>
					</s:else>
				</div>
			</div>
			
			<div id="tabs-7" style="padding-left:0px;">
				
				<div style="margin:0px;padding:0px;width:100%">
					<div style="padding-left:0px;">
						<table width="100%" class="listTable">
						<tr>
						<td width="30%">订单编号</td><td width="15%">交易时间</td>
						<td width="15%">买家</td><td width="15%">交易状态</td><td width="15%">实收款(元)</td><td width="10%">评价</td>
						</tr>
						</table>
					</div>
					<div style="padding-left:10px;padding-top:2px;padding-bottom:2px;border:1px solid #9DB5E3;">
					
					</div>
					<s:if test="#request.orderListYgb.resultlist.size > 0">
						<s:iterator value="#request.orderListYgb.resultlist" var="order">
							<div style="margin:5px auto;">
								
								<table width="100%" id="listTable" class="listTable" style="padding-left:0px;" cellpadding="0" cellspacing="0" >
									<tr>
										<td width="30%" >${order.orderId }</td>
										<td width="15%">${order.orderTimeStr}</td>
										<td width="15%">${order.memName}</td>
										<td width="15%" align="center" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>交易关闭</span><br/>
											<span><a href=javascript:openOrderInfo('${order.orderId}')  style="color:blue;" >详情</a></span><br/>
											
										</td>
										
										<td width="15%" align="center" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>${order.paidAmt}</span><br/>
											<span>(含快递：${order.postAmt})</span><br/>
											
										</td>
										<td width="10%">
										<s:if test="#order.isClose!=1&&#order.status==6&&#order.criticalStatus!=1">
										       <span><a href=javascript:openEvaluation('${order.orderId}')  style="color:blue;" >去评价</a></span>
										    </s:if>
										    <s:elseif test="#order.status==6&&#order.criticalStatus==1">
										     已评价
										    </s:elseif>
										</td>
									</tr>
								</table>
								
							</div>	
						</s:iterator>
						<div class="listBottom" style="margin-top:10px;margin-bottom:20px;">
							<div class="Fr" id="pageNav">
								<font color="#000">共<span class="page">${orderListYgb.totalrecord}</span>条记录，
								第<span id="currPage">${ordersDTO.page}</span>/<span>${ordersDTO.totalPage}</span>页，每页${ordersDTO.pageNum}条&nbsp;
								<s:if test="ordersDTO.page > 1"><a href="javascript:void(0)" onclick="turnPage(1)">[首页]</a></s:if>
								<s:else>[首页]</s:else>
								&nbsp;
								<s:if test="ordersDTO.page > 1">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.page-1})">[上一页]</a>
								</s:if>
								<s:else>
									[上一页]
								</s:else>
								<s:if test="ordersDTO.totalPage > ordersDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.page+1})">[下一页]</a>
								</s:if>
								<s:else>
									[下一页]
								</s:else>
								&nbsp;
								<s:if test="ordersDTO.totalPage > ordersDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.totalPage})">[尾页]</a>
								</s:if>
								<s:else>
									[尾页]
								</s:else>
								&nbsp;转到
								<select name="" onchange="changeSelect(this)" >
									<s:iterator value="new int[ordersDTO.totalPage]" status="i">
										<s:if test="#i.index+1 == ordersDTO.page">
											<option value="${i.index+1}" selected="selected">${i.index+1}</option>
										</s:if>
										<s:else>
											<option value="${i.index+1}">${i.index+1}</option>
										</s:else>
									</s:iterator>
								</select>
								页</font>
							</div>
						</div>
						
					</s:if>
					<s:else>
						<div align="center" style="margin-top:20px;">
							没有找到数据！
						</div>
					</s:else>
				</div>
			</div>
			
			<div id="tabs-8" style="padding-left:0px;">
				
				<div style="margin:0px;padding:0px;width:100%">
					<div style="padding-left:0px;">
						<table width="100%" class="listTable">
						<tr>
						<td width="30%">订单编号</td><td width="15%">交易时间</td>
						<td width="15%">买家</td><td width="15%">交易状态</td><td width="15%">实收款(元)</td><td width="10%">评价</td>
						</tr>
						</table>
					</div>
					<div style="padding-left:10px;padding-top:2px;padding-bottom:2px;border:1px solid #9DB5E3;">
					&nbsp;&nbsp;&nbsp;&nbsp;
					订单状态：&nbsp;&nbsp;<s:select onchange="showStatusLs();" name="ordersDTO.status" id="orderStatusLs"   list="#request.statusDic" listKey="key" listValue="value" headerKey="-1" headerValue="全部"
				 cssClass="formInput" theme="simple"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<s:if test="ordersDTO.isClose==0">
					<input type="checkbox" checked=true  onclick="showCloseOrder(this)" /> 
					</s:if>
					<s:else>
					<input type="checkbox"  onclick="showCloseOrder(this)" /> 
					</s:else>
					不显示已关闭的订单
					</div>
					<s:if test="#request.orderListLs.resultlist.size > 0">
						<s:iterator value="#request.orderListLs.resultlist" var="order">
							<div style="margin:5px auto;">
								
								<table width="100%" id="listTable" class="listTable" style="padding-left:0px;" cellpadding="0" cellspacing="0" >
									<tr>
										<td width="30%" >${order.orderId }</td>
										<td width="15%">${order.orderTimeStr}</td>
										<td width="15%">${order.memName}</td>
										<td width="15%" align="center" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>
											<s:if test="#order.isClose==1">
											交易关闭
											</s:if>
											<s:else>
											${order.orderStatusStr}
											</s:else>
											
											</span><br/>
											<s:if test="#order.status==3">
											<span><a href=javascript:openSendOrderInfo('${order.orderId}')  style="color:blue;" >详情</a></span><br/>
											</s:if>
											<s:else>
											<span><a href=javascript:openOrderInfo('${order.orderId}')  style="color:blue;" >详情</a></span><br/>
											</s:else>
											<s:if test="#order.isClose==1">
											<span>已关闭</span>
										    </s:if>
										    <s:else>
										    <span><a style="color:blue;" href=javascript:closeOrder('orders/orders!closeOrder','${order.orderId}') title='关闭订单'>关闭订单</a></span>
										    </s:else>
										  </td>
										
										<td width="15%" align="center" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>${order.paidAmt}</span><br/>
											<span>(含快递：${order.postAmt})</span><br/>
											<s:if test="#order.isClose!=1&&(#order.status==4||#order.status==6)">
											<span>
											
											<s:if test="#order.deliveryWay==0">无需物流</s:if>
											<s:else>
											<a href=javascript:openDeliverInfo('${order.orderId}')  style="color:blue;" >查看物流</a>
											</s:else>
											</span>
											</s:if>
				
										</td>
										<td width="10%">
										<s:if test="#order.isClose!=1&&#order.status==6&&#order.criticalStatus!=1">
										       <span><a href=javascript:openEvaluation('${order.orderId}')  style="color:blue;" >去评价</a></span>
										    </s:if>
										    <s:elseif test="#order.status==6&&#order.criticalStatus==1">
										          已评价
										    </s:elseif>
										</td>
									</tr>
								</table>
								
							</div>	
						</s:iterator>
						<div class="listBottom" style="margin-top:10px;margin-bottom:20px;">
							<div class="Fr" id="pageNav">
								<font color="#000">共<span class="page">${orderListLs.totalrecord}</span>条记录，
								第<span id="currPage">${ordersDTO.page}</span>/<span>${ordersDTO.totalPage}</span>页，每页${ordersDTO.pageNum}条&nbsp;
								<s:if test="ordersDTO.page > 1"><a href="javascript:void(0)" onclick="turnPage(1)">[首页]</a></s:if>
								<s:else>[首页]</s:else>
								&nbsp;
								<s:if test="ordersDTO.page > 1">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.page-1})">[上一页]</a>
								</s:if>
								<s:else>
									[上一页]
								</s:else>
								<s:if test="ordersDTO.totalPage > ordersDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.page+1})">[下一页]</a>
								</s:if>
								<s:else>
									[下一页]
								</s:else>
								&nbsp;
								<s:if test="ordersDTO.totalPage > ordersDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${ordersDTO.totalPage})">[尾页]</a>
								</s:if>
								<s:else>
									[尾页]
								</s:else>
								&nbsp;转到
								<select name="" onchange="changeSelect(this)" >
									<s:iterator value="new int[ordersDTO.totalPage]" status="i">
										<s:if test="#i.index+1 == ordersDTO.page">
											<option value="${i.index+1}" selected="selected">${i.index+1}</option>
										</s:if>
										<s:else>
											<option value="${i.index+1}">${i.index+1}</option>
										</s:else>
									</s:iterator>
								</select>
								页</font>
							</div>
						</div>
						
					</s:if>
					<s:else>
						<div align="center" style="margin-top:20px;">
							没有找到数据！
						</div>
					</s:else>
				</div>
			</div>
			
			
			
		</div>		
	</div>
	<div class="listBottom">
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
	
	
	
</body> 
</html>