<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<base href="<%=basePath%>" />
<script language="javascript" type="text/javascript" defer="defer" src="<%=basePath%>js/datepicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="<%=basePath%>js/des.js"></script>
<script type="text/javascript">
	function search(recOrderId){
	 //   alert(recOrderId);
		rechJsonPageList(recOrderId);
		// 打开对话框
		$("#dialog-detial").dialog({
			resizable: true,
			top: 240,
			height:400,
			width:800,
			modal: true,
			buttons: {
				'取消': function() {
					$(this).dialog('close');
				}
			}
		});
	}
	
	//查询方法
	function rechJsonPageList(recOrderId) {
		var params = {
	        "rechargeDTO.recOrderId" : recOrderId
	    };
	   var actionUrl = "order/recharge!rechJsonPageList";
	    $.ajax({   
	        url : actionUrl,   
	        data : params,   
	        dataType : "json",
	        cache : false, 
	        type : "POST",
	        error : function(textStatus, errorThrown) {   
	    		alert("系统ajax交互错误!");  
	        },
	        success : function(data) {
	        	$("#recOrderIdA").val(data.recOrderId);
				$("#statusA").val(data.statusName);
				$("#orderAmtA").val(data.orderAmt);
				$("#realityAmtA").val(data.realityAmt);
				$("#paidTypeA").val(data.paidTypeName);
				$("#memNameA").val(data.memName);
				$("#operatorNameA").val(data.operatorName);
				$("#createTimeA").val(data.createTime);
				$("#cardNoA").val(data.cardNo);
				$("#descrA").val(data.descr);
	        }
	    });
	}
	</script>
	
	<input name="jmKeyID" type="hidden" id="jmKeyID" />
	<div id="dialog-detial" style="display: none;" title="充值订单详细信息">
		<div>
			<table width="96%"  class="formTable" cellpadding="0" cellspacing="0" >
				<tr>
					<th align="right" width="20%">订单编号：</th>
		        	<td width="30%"><input type="text"  name="rechargeDTO.recOrderId" id="recOrderIdA"  maxlength="20" class="formInput"  readonly="readonly"/></td>
					<th align="right" width="20%">订单状态：</th>
					<td width="30%"><input type="text"   name="rechargeDTO.statusName" id="statusA"  maxlength="20" class="formInput" readonly="readonly"/></td>
				</tr>
				<tr>
					<th align="right" width="20%">充值金额：</th>
					<td width="30%"><input type="text"  name="rechargeDTO.orderAmt" id="orderAmtA"  maxlength="20" class="formInput" readonly="readonly"/></td>
					<th align="right" width="20%">支付金额</th>
					<td width="30%"><input type="text"   name="rechargeDTO.realityAmt" id="realityAmtA"  maxlength="20" class="formInput" readonly="readonly"/></td>
				</tr>
				<tr>
					<th align="right" width="20%">支付方式</th>
					<td width="30%"><input type="text"   name="rechargeDTO.paidTypeName" id="paidTypeA"  maxlength="20" class="formInput" readonly="readonly"/></td>
					<th align="right" width="20%">充值人</th>
					<td width="30%"><input type="text"   name="rechargeDTO.memName" id="memNameA"  maxlength="20" class="formInput" readonly="readonly"/></td>
				</tr>
				<tr>
					<th align="right" width="20%">操作人</th>
					<td width="30%"><input type="text"  name="rechargeDTO.operatorName" id="operatorNameA"  maxlength="20" class="formInput" readonly="readonly"/></td>
					<th align="right" width="20%">充值时间</th>
					<td width="30%"><input type="text"   name="rechargeDTO.createTime" id="createTimeA"  maxlength="20" class="formInput" readonly="readonly"/></td>
				</tr>
				<tr>
					<th align="right" width="20%">充值卡号</th>
					<td width="30%"><input type="text"   name="rechargeDTO.cardNo" id="cardNoA" maxlength="20" class="formInput" readonly="readonly"/></td>
					<th align="right" width="20%">描述</th>
					<td width="30%"><input type="text"  name="rechargeDTO.descr" id="descrA" maxlength="20" class="formInput" readonly="readonly"/></td>
					
				</tr>
			</table>
		</div>
		<div class="listBottom">
			<div class="Fr" id="memPageNav">
				<s:property value="pageHTML" escape="false"/>
			</div>
		</div>
	</div>