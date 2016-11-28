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
	<title>发货管理</title>
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
		$().ready(function() {
			$("#tabs").tabs({
				"selected":${activeTab}
			});
		});
		
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
		
		function batchSend() {
			var checklist = $("input[name='deOrderId']:checkbox");
			if(checklist.length < 1) {
				alert('请选中需要批量发货的订单！');
				return;
			}
			var paramStr = "deliveryDTO.orderIdStr=";
			checklist.each(function() {
                if($(this).attr("checked") == "checked") {
                	paramStr += $(this).val()+",";
                }  
            }) ;
            window.location.href = "base/delivery!deliverPage?"+paramStr;
            
		}
		
		function turnPage(page) {
			$('#pageId').val(page);
			$('#queryForm').submit();
		}
		
		function changeSelect(obj) {
			$('#pageId').val(obj.value);
			$('#queryForm').submit();
		}
		
		function didntSend() {
			$('#orderStatusId').val(0);
			$('#queryForm').submit();
		}
		
		function cancelSend() {
			$('#orderStatusId').val(3);
			$('#queryForm').submit();
		}
		
		function tabClick(obj) {
			if($(obj).attr("href") == "#tabs-1") {
				$('#actTabId').val(0);
				$('#orderStatusId').val(0);
				$('#queryForm').submit();
			} else if($(obj).attr("href") == "#tabs-2") {
				$('#actTabId').val(1);
				$('#orderStatusId').val(1);
				$('#queryForm').submit();
			} else if($(obj).attr("href") == "#tabs-3") {
				$('#actTabId').val(2);
				$('#orderStatusId').val(2);
				$('#queryForm').submit();
			}
		}
		
		
		function yesIdo(obj) {
			alert($(obj).attr('order-no'));
		}
		
	</script> 
	<style>
	</style>
</head>
<body>
	
	<div class="Position">
		当前位置是：基本设置 &gt;&gt; 发货管理 &gt;&gt; 发货管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<div class="search" align="left" style="margin-bottom:10px;padding-bottom:0px;">	
		<form action="base/delivery!list" method="POST" id="queryForm">
			<fieldset  style='margin-left:20px;margin-top:20px;padding-bottom:20px;padding-left:20px;'>
				<legend >
					查询条件
				</legend>
				<table style="margin-left:20px;margin-top:20px;" cellspacing="5">
					<tr>
						<td>收件人名称:</td>
						<td><s:textfield name="deliveryDTO.memName" theme="simple"></s:textfield> </td>
						<td>创建时间:</td>
						<td><s:textfield name="deliveryDTO.startTime" theme="simple" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></s:textfield> </td>
						<td>至:</td>
						<td><s:textfield name="deliveryDTO.endTime" theme="simple" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></s:textfield></td>
					</tr>
					<tr>
						<td>买家昵称:</td>
						<td><s:textfield name="deliveryDTO.memId" theme="simple"></s:textfield> </td>
						<td>订单编号:</td>
						<td><s:textfield name="deliveryDTO.orderId" theme="simple"></s:textfield> </td>
						<td> </td>
						<td><input type="submit" class="formButton"  value="查 询" /></td>
					</tr>
				</table>
			</fieldset>
			<s:hidden name="deliveryDTO.page" id="pageId"></s:hidden>
			<s:hidden name="deliveryDTO.activeTab" id="actTabId"></s:hidden>
			<s:hidden name="deliveryDTO.status" id="orderStatusId"/>
			
		</form>
	</div>
	<div style="width:96%;margin:0px auto;">
		<div id="tabs" style="margin-left:20px;margin-right:20px;">
			<ul>
				<li><a href="#tabs-1" onclick="tabClick(this)">等待发货的订单</a></li>
				<li><a href="#tabs-2" onclick="tabClick(this)">发货中的订单</a></li>
				<li><a href="#tabs-3" onclick="tabClick(this)">已发货的订单</a></li>
			</ul>
			<div id="tabs-1" style="padding-left:0px;">
				
				<div style="margin:0px;padding:0px;width:100%">
					<div style="padding-left:20px;">
						<a href="javascript:void(0)" onclick="didntSend()" style="color:blue;" >未发货的订单</a> | <a href="javascript:void(0)" onclick="cancelSend()" style="color:blue;">被取消的订单</a>
					</div>
					<div style="padding-left:10px;padding-top:2px;padding-bottom:2px;border:1px solid #9DB5E3;">
					<input type="checkbox" value="1" onclick="clickDeOrder(this)" /> <a href="javascript:void(0)" onclick="batchSend()" style="color:blue;">批量发货</a></div>
					<s:if test="#request.deliveryList.resultlist.size > 0">
						<s:iterator value="#request.deliveryList.resultlist" var="delivery">
							<div style="margin:5px auto;">
								<div style="padding-left:10px;padding-top:2px;padding-bottom:2px;border:1px solid #9DB5E3;background-color: 	#BEBEBE">
									<span><input type="checkbox" name="deOrderId" value="${delivery.orderId }"/>订单编号：${delivery.orderId }</span>
									<span style="margin-left:20px;">成交时间：${delivery.createTimeStr}</span>
								</div>
								<table width="100%" id="listTable" class="listTable" style="padding-left:0px;" cellpadding="0" cellspacing="0" >
									<tr>
										<td width="30%">${delivery.goodsName}</td>
										<td width="20%">${delivery.price2Scale} x ${delivery.qty}</td>
										<td width="50%" align="left" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>收货信息：${delivery.address}</span><br/>
											<span>买家选择：快递</span><br/>
											<span>物流公司：未选择物流公司</span>
										</td>
									</tr>
								</table>
								<div align="right" style="padding-left:10px;padding-top:2px;padding-bottom:2px;border:1px solid #9DB5E3;background-color: #F0F0F0">
									<span><a href="javascript:void(0)" style="color:blue;">关闭订单</a></span>
									<span style="margin-left:20px;"><input type="button" class="formButton"   value="发货"/></span>
								</div>
							</div>	
						</s:iterator>
						<div class="listBottom" style="margin-top:10px;margin-bottom:20px;">
							<div class="Fr" id="pageNav">
								<font color="#000">共<span class="page">${deliveryList.totalrecord}</span>条记录，
								第<span id="currPage">${deliveryDTO.page}</span>/<span>${deliveryDTO.totalPage}</span>页，每页${deliveryDTO.pageNum}条&nbsp;
								<s:if test="deliveryDTO.page > 1"><a href="javascript:void(0)" onclick="turnPage(1)">[首页]</a></s:if>
								<s:else>[首页]</s:else>
								&nbsp;
								<s:if test="deliveryDTO.page > 1">
									<a href="javascript:void(0)" onclick="turnPage(${deliveryDTO.page-1})">[上一页]</a>
								</s:if>
								<s:else>
									[上一页]
								</s:else>
								<s:if test="deliveryDTO.totalPage > deliveryDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${deliveryDTO.page+1})">[下一页]</a>
								</s:if>
								<s:else>
									[下一页]
								</s:else>
								&nbsp;
								<s:if test="deliveryDTO.totalPage > deliveryDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${deliveryDTO.totalPage})">[尾页]</a>
								</s:if>
								<s:else>
									[尾页]
								</s:else>
								&nbsp;转到
								<select name="" onchange="changeSelect(this)" >
									<s:iterator value="new int[deliveryDTO.totalPage]" status="i">
										<s:if test="#i.index+1 == deliveryDTO.page">
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
				<div style="margin:0px;padding:0px;">
					<div style="padding-left:20px;"> </div>
					<div style="padding-left:10px;padding-top:2px;padding-bottom:2px;border:1px solid #9DB5E3;">
					<input type="checkbox" onclick="batchSelect(this)" value="1"/>批量确认</div>
					
					<s:if test="#request.deliveryingList.resultlist.size > 0">
						<s:iterator value="#request.deliveryingList.resultlist" var="delivery" status="i">
							<div style="margin:5px auto;">
								<div style="padding-left:10px;padding-top:2px;padding-bottom:2px;border:1px solid #9DB5E3;background-color:#BEBEBE">
									<span><input type="checkbox" name="ingOrderId" value="${delivery.orderId }"/>订单编号：${delivery.orderId }</span>
									<span style="margin-left:20px;">成交时间：${delivery.createTimeStr}</span>
									<span style="float:right;margin-right:20px;"><font color="red">请补充运单号码：</font><input type="text" id="yunOrder_${i.index}" /></span>
								</div>
								<table width="100%" id="listTable" class="listTable" style="padding-left:0px;" cellpadding="0" cellspacing="0" >
									<tr>
										<td width="30%">${delivery.goodsName}</td>
										<td width="20%">${delivery.price2Scale} x ${delivery.qty}</td>
										<td width="50%" align="left" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>收货信息：${delivery.address}</span><br/>
											<span>买家选择：快递</span><br/>
											<span>物流公司：未选择物流公司</span>
										</td>
									</tr>
								</table>
								<div align="right" style="padding-left:10px;padding-top:2px;padding-bottom:2px;border:1px solid #9DB5E3;background-color: #F0F0F0">
									<span style="margin-right:20px;"><input type="button" order-no="yunOrder_${i.index}" onclick="yesIdo(this)" class="formButton" value="确认发货"><a href="base/delivery!deliverOrderDetail?orderId=${delivery.orderId }" style="color:blue;">物流订单详情</a></span>
								</div>
							</div>	
						</s:iterator>
						<div class="listBottom" style="margin-top:10px;margin-bottom:20px;">
							<div class="Fr" id="pageNav">
								<font color="#000">共<span class="page">${deliveryList.totalrecord}</span>条记录，
								第<span id="currPage">${deliveryDTO.page}</span>/<span>${deliveryDTO.totalPage}</span>页，每页${deliveryDTO.pageNum}条&nbsp;
								<s:if test="deliveryDTO.page > 1"><a href="javascript:void(0)" onclick="turnPage(1)">[首页]</a></s:if>
								<s:else>[首页]</s:else>
								&nbsp;
								<s:if test="deliveryDTO.page > 1">
									<a href="javascript:void(0)" onclick="turnPage(${deliveryDTO.page-1})">[上一页]</a>
								</s:if>
								<s:else>
									[上一页]
								</s:else>
								<s:if test="deliveryDTO.totalPage > deliveryDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${deliveryDTO.page+1})">[下一页]</a>
								</s:if>
								<s:else>
									[下一页]
								</s:else>
								&nbsp;
								<s:if test="deliveryDTO.totalPage > deliveryDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${deliveryDTO.totalPage})">[尾页]</a>
								</s:if>
								<s:else>
									[尾页]
								</s:else>
								&nbsp;转到
								<select name="" onchange="changeSelect(this)" >
									<s:iterator value="new int[deliveryDTO.totalPage]" status="i">
										<s:if test="#i.index+1 == deliveryDTO.page">
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
				<div style="margin:0px;padding:0px;">
					<div style="padding-left:20px;"></div>
					<!--<div style="padding-left:10px;padding-top:2px;padding-bottom:2px;border:1px solid #9DB5E3;">
					  input type="checkbox" onclick="sentBatch(this)" value="1"/>批量发货</div>-->
					<s:if test="#request.deliveryedList.resultlist.size > 0">
						<s:iterator value="#request.deliveryedList.resultlist" var="delivery">
							<div style="margin:5px auto;">
								<div style="padding-left:10px;padding-top:2px;padding-bottom:2px;border:1px solid #9DB5E3;background-color: 	#BEBEBE">
									<span><!--  input type="checkbox" name="sentOrderId" value="${delivery.orderId }"/-->订单编号：${delivery.orderId }</span>
									<span style="margin-left:20px;">成交时间：${delivery.createTimeStr}</span>
								</div>
								<table width="100%" id="listTable" class="listTable" style="padding-left:0px;" cellpadding="0" cellspacing="0" >
									<tr>
										<td width="30%">${delivery.goodsName}</td>
										<td width="20%">${delivery.price2Scale} x ${delivery.qty}</td>
										<td width="50%" align="left" style="padding-left:10px;padding-top:10px;padding-bottom:10px;">
											<span>收货信息：${delivery.address}</span><br/>
											<span>买家选择：快递</span><br/>
											<span>物流公司：未选择物流公司</span>
										</td>
									</tr>
								</table>
								<div align="right" style="padding-left:10px;padding-top:2px;padding-bottom:2px;border:1px solid #9DB5E3;background-color: #F0F0F0">
									<span style="float:left;margin-left:20px;">发货完成</span>
									<span><a href="javascript:void(0)" style="color:blue;">关闭订单</a></span>
									<span style="margin-left:20px;margin-right:20px;"><input type="button" class="formButton"   value="重新发货"/></span>
								</div>
							</div>	
						</s:iterator>
						<div class="listBottom" style="margin-top:10px;margin-bottom:20px;">
							<div class="Fr" id="pageNav">
								<font color="#000">共<span class="page">${deliveryList.totalrecord}</span>条记录，
								第<span id="currPage">${deliveryDTO.page}</span>/<span>${deliveryDTO.totalPage}</span>页，每页${deliveryDTO.pageNum}条&nbsp;
								<s:if test="deliveryDTO.page > 1"><a href="javascript:void(0)" onclick="turnPage(1)">[首页]</a></s:if>
								<s:else>[首页]</s:else>
								&nbsp;
								<s:if test="deliveryDTO.page > 1">
									<a href="javascript:void(0)" onclick="turnPage(${deliveryDTO.page-1})">[上一页]</a>
								</s:if>
								<s:else>
									[上一页]
								</s:else>
								<s:if test="deliveryDTO.totalPage > deliveryDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${deliveryDTO.page+1})">[下一页]</a>
								</s:if>
								<s:else>
									[下一页]
								</s:else>
								&nbsp;
								<s:if test="deliveryDTO.totalPage > deliveryDTO.page">
									<a href="javascript:void(0)" onclick="turnPage(${deliveryDTO.totalPage})">[尾页]</a>
								</s:if>
								<s:else>
									[尾页]
								</s:else>
								&nbsp;转到
								<select name="" onchange="changeSelect(this)" >
									<s:iterator value="new int[deliveryDTO.totalPage]" status="i">
										<s:if test="#i.index+1 == deliveryDTO.page">
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