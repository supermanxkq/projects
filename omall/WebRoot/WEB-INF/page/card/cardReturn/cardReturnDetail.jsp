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
	function search(retId){
		cardReturnJsonPageList(retId);
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
	function cardReturnJsonPageList(retId) {
		var params = {
	        "cardReturnDTO.retId" : retId
	    };
	   var actionUrl = "card/cardReturn!cardReturnJsonPageList";
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
	        	$("#retIdA").val(data.retId);
				$("#retOrderStatusA").val(data.retOrderStatusName);//
				$("#allAmtA").val(data.allAmt);
				$("#relAmtA").val(data.relAmt);
				$("#payWayA").val(data.payWayName);
				$("#retTypeA").val(data.retTypeName);
				$("#memNameA").val(data.memName);
				$("#telNoA").val(data.telNo);
				$("#proposerA").val(data.proposer);
				$("#auditIdA").val(data.proposer);
				$("#cardNoA").val(data.cardNo);//
				$("#relAmtA").val(data.relAmt);
				$("#operTimeA").val(data.operTime);//
				$("#descrA").val(data.descr);
				
	        }
	    });
	}
	</script>
	
	<input name="jmKeyID" type="hidden" id="jmKeyID" />
	<div id="dialog-detial" style="display: none;" title="充值订单详细信息">
		<div>
			<table width="96%" class="formTable" cellpadding="0" cellspacing="0" >
				<tr>
					<th align="right" width="20%">流水号：</th>
		        	<td width="30%"><input type="text" name="cardReturnDTO.retId" id="retIdA"   maxlength="20" class="formInput" readonly="readonly"/></td>
					<th align="right" width="20%">订单状态：</th>
					<td width="30%"><input type="text" name="cardReturnDTO.retOrderStatusName" id="retOrderStatusA" maxlength="20" class="formInput" readonly="readonly"/></td>
				</tr>
				<tr>
					<th align="right" width="20%">退卡金额：</th>
					<td width="30%"><input type="text" name="cardReturnDTO.allAmt" id="allAmtA"  maxlength="20" class="formInput" readonly="readonly"/></td>
					<th align="right" width="20%">实退金额</th>
					<td width="30%"><input type="text" name="cardReturnDTO.relAmt" id="relAmtA"  maxlength="20" class="formInput" readonly="readonly"/></td>
				</tr>
				<tr>
					<th align="right" width="20%">支付方式</th>
					<td width="30%"><input type="text" name="cardReturnDTO.payWayName" id="payWayA"  maxlength="20" class="formInput" readonly="readonly"/></td>
					<th align="right" width="20%">退卡类型</th>
					<td width="30%"><input type="text" name="cardReturnDTO.retTypeName" id="retTypeA"  maxlength="20" class="formInput" readonly="readonly"/></td>
				</tr>
				<tr>
					<th align="right" width="20%">退卡人姓名</th>
					<td width="30%"><input type="text"   name="cardReturnDTO.memName" id="memNameA"  maxlength="20" class="formInput" readonly="readonly"/></td>
					<th align="right" width="20%">联系电话</th>
					<td width="30%"><input type="text"   name="cardReturnDTO.telNo" id="telNoA"  maxlength="20" class="formInput" readonly="readonly"/></td>
				</tr>
				<tr>
					<th align="right" width="20%">操作人</th>
					<td width="30%"><input type="text" name="cardReturnDTO.proposer" id="proposerA"  maxlength="20" class="formInput" readonly="readonly"/></td>
					<th align="right" width="20%">审核人</th>
					<td width="30%"><input type="text" name="cardReturnDTO.proposer" id="auditIdA"  maxlength="20" class="formInput" readonly="readonly"/></td>
				</tr>
				<tr>
					<th align="right" width="20%">退卡卡号</th>
					<td width="30%"><input type="text" name="cardReturnDTO.cardNo" id="cardNoA"  maxlength="20" class="formInput" readonly="readonly"/></td>
					<th align="right" width="20%">退卡时间</th>
					<td width="30%"><input type="text" name="cardReturnDTO.operTime" id="operTimeA"  maxlength="20" class="formInput" readonly="readonly"/></td>
				</tr>
				<tr>
					<th align="right" width="20%">描述</th>
					<td width="30%"><input type="text" name="cardReturnDTO.descr" id="descrA"  maxlength="20" class="formInput" readonly="readonly"/></td>
				</tr>
			</table>
		</div>
		<div class="listBottom">
			<div class="Fr" id="memPageNav">
				<s:property value="pageHTML" escape="false"/>
			</div>
		</div>
	</div>