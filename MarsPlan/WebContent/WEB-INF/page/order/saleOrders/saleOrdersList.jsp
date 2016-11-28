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
		<title>售卡订单管理</title>
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
		<script src="js/jquery/jquery.ui.resizable.js"></script>
		<script src="js/jquery/jquery.ui.dialog.js"></script>
		<script src="js/common.js"></script>
		<script src="js/ajax_alert.js"></script>
		<script type="text/javascript" src="js/datepicker/WdatePicker.js"></script>

		<script type="text/javascript">
		//查询方法
		function query(page) {
			var orderId = $.trim($("#saleOrderId").val());
			var status = $.trim($("#status").val());
			var orgId = $.trim($("#orgId").val());
			var from = $.trim($("#from").val());
			var to = $.trim($("#to").val());

			var params = {
				"saleOrderDTO.saleOrderId" : orderId,
		        "saleOrderDTO.status" : status,
		        "saleOrderDTO.orgId" : orgId,
		        "saleOrderDTO.from" : from,
		        "saleOrderDTO.to" : to,
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		        "saleOrderDTO.page" : page
		    }; 
		   ajaxData("order/saleorders!jsonPageList",params);// !是跳转的意思,相当于？
		}
		//加载查看详情页面
		var loadData = function(id) {

		  	var params = {
		        "saleOrderDTO.saleOrderId" : id
		  	};
		  	var actionUrl = "order/saleorders!viewUI";
		    $.ajax( {
		        url : actionUrl,
		        data : params,   
		        dataType : "json",
		        cache : false,
		        type : "POST",
		        error : function(textStatus, errorThrown) {
		    		alert("系统ajax交互错误!");
		        },
		        success : function(data, textStatus) {
		        
					var consoleDlg = $("#dialog-confirm");
					var rowData = data.objResult;
					//订单号：dialog_orderId
					consoleDlg.find("#dialog_orderId").html(rowData.saleOrderId+"<input type='hidden' id='saleOrderId' value='"+rowData.saleOrderId+"'/>");
					//状态：dialog_status
					consoleDlg.find("#dialog_status").html(rowData.statusStr);
					//支付方式：dialog_paidType
					consoleDlg.find("#dialog_paidType").html(rowData.paidTypeStr);
					//面值：dialog_initAmt
					consoleDlg.find("#dialog_initAmt").html(rowData.initAmt);
					//订单总金额：dialog_orderAmt
					consoleDlg.find("#dialog_orderAmt").html(rowData.orderAmt);
					//支付金额：dialog_paidAmt
					consoleDlg.find("#dialog_paidAmt").html(rowData.paidAmt);
					//卡等级：dialog_levelValue
					consoleDlg.find("#dialog_levelValue").html(rowData.saleLevelName);
					//开卡赠送积分：
					//是否实名：dialog_realNameSign
					consoleDlg.find("#dialog_realNameSign").html(rowData.realNameSign);
					//购卡人：dialog_payMen
					consoleDlg.find("#dialog_payMen").html(rowData.payMen);
					//持卡人：dialog_holdMemName
					consoleDlg.find("#dialog_holdMemName").html(rowData.memName);
					//售卡时间：dialog_saleTime
					consoleDlg.find("#dialog_saleTime").html(rowData.createTime);
					//操作人：dialog_operator
					consoleDlg.find("#dialog_operator").html(rowData.operatorId);
					//描述：dialog_descriptionApp
					consoleDlg.find("#dialog_descriptionApp").html(rowData.descr);

		            //$("#rnd").val(rowData.rnd);
		            $("#cardNosTb").find('tr:eq(0)').nextAll().remove();
        			$("#cardNosTb").append(rowData.cardNoStr);
        			
		        }
		    });
		};
		
		//查看页面
		var viewData = function(id){
			loadData(id);
			//var consoleDlg = $("#dialog-confirm");
			//consoleDlg.find("#dialog_descriptionAudit").attr("readonly","readonly");
			var consoleDlg = $("#dialog-confirm");	
			consoleDlg.find("#dialog_orderId").html("");
			consoleDlg.find("#dialog_status").html("");
			consoleDlg.find("#dialog_paidType").html("");
			consoleDlg.find("#dialog_initAmt").html("");
			consoleDlg.find("#dialog_orderAmt").html("");
			consoleDlg.find("#dialog_paidAmt").html("");
			consoleDlg.find("#dialog_levelValue").html("");
			consoleDlg.find("#dialog_realNameSign").html("");
			consoleDlg.find("#dialog_payMen").html("");
			consoleDlg.find("#dialog_holdMemName").html("");
			consoleDlg.find("#dialog_saleTime").html("");
			consoleDlg.find("#dialog_operator").html("");
			consoleDlg.find("#dialog_descriptionApp").html("");
			$("#cardNosTb").find('tr:eq(0)').nextAll().remove();
			// 打开对话框
			$("#dialog-confirm").dialog({
				resizable: true,
				top: 370,
				height:380,
				width:730,
				modal: true,
				buttons: {
					'取消': function() {
						$(this).dialog('close');
					}
					
				}
			});
		};
	</script>

	</head>
	<body onload="query(${saleOrderDTO.page });">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 售卡管理 &gt;&gt; 售卡订单查看
		</div>
		<div id="dialog-confirm" style="display: none;" title="编辑">
			<!-- 
		<input name="saleOrderDTO.keyID" id="keyID" type="hidden" />
		<input name="saleOrderDTO.rnd" id="rnd" type="hidden" />
		<input name="saleOrderDTO.return_EncData" id="return_EncData" type="hidden" />
	 -->
<%--	 <s:include value="/WEB-INF/page/order/batchSale/batchSaleSet.jsp"/>--%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">
				<tr>
					<th align="right" width="20%">
						订单号：
					</th>
					<td align="left" width="30%" id="dialog_orderId"></td>
					<th align="right" width="20%">
						状态：
					</th>
					<td align="left" width="30%" id="dialog_status"></td>

				</tr>
				<tr>
					<th align="right" width="20%">
						支付方式：
					</th>
					<td align="left" width="30%" id="dialog_paidType"></td>
					<th align="right">
						面值：
					</th>
					<td align="left" id="dialog_initAmt"></td>
				</tr>
				<tr>
					<th align="right" width="20%">
						订单总金额：
					</th>
					<td align="left" width="30%" id="dialog_orderAmt"></td>
					<th align="right">
						支付金额：
					</th>
					<td align="left" id="dialog_paidAmt"></td>
				</tr>
				<tr>
					<th align="right" width="20%">
						卡等级：
					</th>
					<td align="left" width="30%" id="dialog_levelValue"></td>
					<th align="right" width="20%">
						是否实名：
					</th>
					<td align="left" width="30%" id="dialog_realNameSign"></td>
				</tr>
				<tr>
					<th align="right">
						购卡人：
					</th>
					<td align="left" id="dialog_payMen"></td>
					<th align="right">
						持卡人：
					</th>
					<td align="left" id="dialog_holdMemName"></td>
				</tr>	
				<tr>
					<th align="right">
						售卡时间：
					</th>
					<td align="left" id="dialog_saleTime"></td>
					<th align="right">
						操作人：
					</th>
					<td align="left" id="dialog_operator"></td>
				</tr>
				<tr>
					<th align="right">
						描述：
					</th>
		        	<td colspan="3"><textarea id="dialog_descriptionApp" class="formTextarea" cols="45" rows="5" ></textarea></td>
				</tr>
				<!--
				<tr>
					<th align="right">
						审核人：
					</th>
					<td align="left" id="dialog_operIdAudit"></td>
					<th align="right">
						审核时间：
					</th>
					<td align="left" id="dialog_operTimeAudit"></td>
				</tr>
				<tr>
					<th align="right">
						审核描述：
					</th>
					<td colspan="3">
						<textarea id="dialog_descriptionAudit" class="formTextarea"
							cols="45" rows="5"></textarea>
					</td>
				</tr>
			-->
			</table>
			<table width="96%" id="cardNosTb" class="listTable" cellpadding="0"
				cellspacing="0">
				<tr>
					<th width="10%">
						卡号
					</th>
					<th width="10%">
						卡号
					</th>
					<th width="10%">
						卡号
					</th>
				</tr>
			</table>
		</div>

		<form id="form1" name="form1" action="order/saleorders!export"
			method="post">
			<div class="search">
				<table class="searchTable" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<img src="images/fd.jpg" />
						</td>
						<th>
							订 单 号:
						</th>
						<td>
							<input type="text" id="saleOrderId"
								name="saleOrderDTO.saleOrderId" class="formInput" maxlength="19"/>
						</td>
						<th>
							状态:
						</th>
						<td>
							<s:select name="saleOrderDTO.status" id="status"
								list="#request.statusValues" listKey="key" listValue="value"
								headerKey="-1" headerValue="--请选择--" cssClass="formInput"
								theme="simple" />
						</td>
						<s:if test="#session.user_session.userLevel!=0">
						</s:if>
						<s:else>
						<th>
							发卡机构:
						</th>
						<td>
							<s:select name="saleOrderDTO.orgId" id="orgId"
								list="#request.organValues" listKey="key" listValue="value"
								headerKey="-1" headerValue="--请选择--" cssClass="formInput"
								theme="simple" />
						</td>
						</s:else>
					</tr>
					<tr>
						<td></td>
						<th>
							售卡时间:
						</th>
						<td colspan="3">
							<input name="saleCardRecordDTO.from" id="from" class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/> 
			      	至
			      	<input name="saleCardRecordDTO.to" id="to" class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
						</td>
						<td colspan="6">
							<input type="button" class="formButton" onclick="query();"
								value="查 询" />
							<input type="button" name="Submit2" class="formButton" value="导出"
								onclick="javascript:document.form1.submit()" />

							<%--<input type="button" class="formButton" value="添加"
								onclick="go('order/saleorders!addUI')" />

						--%></td>

					</tr>
				</table>
			</div>
		</form>
		<table width="96%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="3%">
					序号
				</th>
				<th width="10%">
					订单号
				</th>
				<th width="8%">
					支付方式
				</th>
				<th width="8%">
					订单总价
				</th>
				<th width="8%">
					支付金额
				</th>
				<th width="6%">
					数量
				</th>
				<th width="6%">
					面值
				</th>
				<th width="10%">
					购卡人
				</th>
				<th width="10%">
					售卡时间
				</th>
				<th width="10%">
					操作人
				</th>
				<th width="6%">
					状态
				</th>
				<th width="6%">
					相关操作
				</th>
			</tr>
		</table>
		<div class="listBottom">
			<div class="Fl">
				<!-- 
				<input type="button" class="formButton" value="添加" onclick="go('order/saleorders!addUI')"/>
			 -->
			</div>
			<div class="Fr" id="pageNav">

			</div>
		</div>
	</body>
</html>
