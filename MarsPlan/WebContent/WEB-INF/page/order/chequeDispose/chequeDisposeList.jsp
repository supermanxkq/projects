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
		<title>订单到账处理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.4.2.min.js"></script>
		<link href="js/jquery/css/jquery.ui.all.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery/jquery.ui.core.js"></script>
		<script src="js/jquery/jquery.ui.widget.js"></script>
		<script src="js/jquery/jquery.ui.mouse.js"></script>
		<script src="js/jquery/jquery.ui.button.js"></script>
		<script src="js/jquery/jquery.ui.draggable.js"></script>
		<script src="js/jquery/jquery.ui.position.js"></script>
		<script src="js/jquery/jquery.ui.resizable.js"></script>
		<script src="js/jquery/jquery.ui.dialog.js"></script>
		<script src="js/common.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script type="text/javascript" src="js/datepicker/WdatePicker.js"></script>
		<script type="text/javascript">
		
		//查询方法
		function query(page) {
			var orderId = $.trim($("#orderIdA").val());
			var status = $.trim($("#status").val());
			var params = {
				"chequeDisposeDTO.orderId" : orderId,
				"chequeDisposeDTO.status" : status,
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		        "chequeDisposeDTO.page" : page
		    }; 
		   ajaxData("order/chequeDispose!jsonPageList",params);// !是跳转的意思,相当于？
		};
		//到账处理
		function seeChequeDispose(orderId,flag){
			//加载查看详情页面
			  	var params = {
			        "chequeDisposeDTO.orderId" : orderId
			  	};
			  	var actionUrl = "order/chequeDispose!chequeDisposeDealUI";
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
						//订单号：dialog_orderId
						consoleDlg.find("#dialog_orderId").html(data.saleOrderId+"<input type='hidden' id='saleOrderId' value='"+data.saleOrderId+"'/>");
						$("#orderId").val(data.saleOrderId);
						//状态：dialog_status
						consoleDlg.find("#dialog_status").html(data.status);
						//支付方式：dialog_paidType
						consoleDlg.find("#dialog_paidType").html(data.paidTypeStr);
						//面值：dialog_initAmt
						consoleDlg.find("#dialog_initAmt").html(data.initAmt);
						//订单总金额：dialog_orderAmt
						consoleDlg.find("#dialog_orderAmt").html(data.orderAmt);
						//支付金额：dialog_paidAmt
						consoleDlg.find("#dialog_paidAmt").html(data.paidAmt);
						//订单类型：dialog_orderType
						consoleDlg.find("#dialog_orderType").html(data.ordreType);
						//卡片数量：dialog_cardQyt
						consoleDlg.find("#dialog_cardQyt").html(data.cardQty);
						//起始卡号：dialog_beginCardNo
						consoleDlg.find("#dialog_beginCardNo").html(data.beginCardNo);
						//购卡人：dialog_paidMen
						consoleDlg.find("#dialog_paidMen").html(data.payMen);
						if(flag == 0){
							//开卡银行：dialog_bank文本框
							consoleDlg.find("#dialog_bank").html(data.bankName);
							//开户账户
							consoleDlg.find("#dialog_bankAcc").html(data.bankAcc);
							//开户人
							consoleDlg.find("#dialog_bankAccHolder").html(data.accHolder);
							//到账描述：dialog_descr
							consoleDlg.find("#dialog_descr").html(data.descr);
							} else{
							consoleDlg.find("#dialog_bank").html("<input type='text' id='bankA'  />");
							consoleDlg.find("#dialog_bankAcc").html("<input type='text' id='bankAccA' />");
							consoleDlg.find("#dialog_bankAccHolder").html("<input type='text' id='bankAccHolderA' />");
							consoleDlg.find("#dialog_descr").html("<textarea id='descrA' style='width :400px;hight:400px;'  />");
							}
						//售卡时间：dialog_saleTime
						consoleDlg.find("#dialog_saleTime").html(data.createTime);
			            $("#cardNosTb").find('tr:eq(0)').nextAll().remove();
	        			$("#cardNosTb").append(data.cardNoStr);
			        }
			    });
			};
			//查看页面
			function viewData(orderId,flag){
				seeChequeDispose(orderId,flag);
				var consoleDlg = $("#dialog-confirm");	
				consoleDlg.find("#dialog_orderId").html("");
				consoleDlg.find("#dialog_status").html("");
				consoleDlg.find("#dialog_paidType").html("");
				consoleDlg.find("#dialog_initAmt").html("");
				consoleDlg.find("#dialog_orderAmt").html("");
				consoleDlg.find("#dialog_paidAmt").html("");
				consoleDlg.find("#dialog_orderType").html("");
				consoleDlg.find("#dialog_cardQyt").html("");
				consoleDlg.find("#dialog_beginCardNo").html("");
				consoleDlg.find("#dialog_paidMen").html("");
				consoleDlg.find("#dialog_bank").html("");
				consoleDlg.find("#dialog_bankAcc").html("");
				consoleDlg.find("#dialog_saleTime").html("");
				consoleDlg.find("#dialog_bankAccHolder").html("");
				consoleDlg.find("#dialog_descr").html("");
				$("#cardNosTb").find('tr:eq(0)').nextAll().remove();
				// 打开对话框
				if(flag!=1){
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
					}else{
				$("#dialog-confirm").dialog({
					resizable: true,
					top: 370,
					height:380,
					width:730,
					modal: true,
					buttons: {
						 '取消': function() {
							$(this).dialog('close');
						},
				'已到账':function arrived(){
							//验证是否为空
							if(confirm("是否确认进行到账处理？")){
								var orderId = $("#dialog_orderId").val();
								var bank = $("#bankA").val();
								var bankAcc = $("#bankAccA").val();
								var bankAccHolder = $("#bankAccHolderA").val();
								var bankAccHolder = $("#bankAccHolderA").val();
								var descr = $("#descrA").value;
								$("#orderId").val(orderId);
								$("#bank").val(bank);
								$("#bankAcc").val(bankAcc);
								$("#bankAccHolder").val(bankAccHolder);
								$("#descr").val(descrA);
								chequeSucc();
								}
							$(this).dialog('close');
							}
						}
					});
				}
			};
			/**进行到账处理***/
			function chequeSucc(){
				var saleOrderId = $("#saleOrderId").val();
				var bank = $("#bank").val();
				var bankAcc = $("#bankAcc").val();
				var bankAccHolder = $("#bankAccHolder").val();
				var descr = $("#descr").val();
				var params = {
				        "chequeDisposeDTO.orderId" : saleOrderId,
				        "chequeDisposeDTO.bank" : bank,
				        "chequeDisposeDTO.bankAcc" : bankAcc,
				        "chequeDisposeDTO.bankAccHolder" : bankAccHolder,
				        "chequeDisposeDTO.descr" : descr
				  	};
				  	var actionUrl = "order/chequeDispose!chequeDisposeDeal";
				    $.ajax({
				        url : actionUrl,
				        data : params,   
				        dataType : "json",
				        cache : false,
				        type : "POST",
				        error : function(textStatus, errorThrown) {
				    		alert("系统ajax交互错误111111!");
				        },
				        success : function(data, textStatus) {
					        if(data.flag == true){
					        	alert("到账处理成功!");
					        	query(1);
						        }else{
						        alert("到账处理失败!");
							    }
					        }
						});
				}
	</script>
	</head>
	<body onload="query(${chequeDisposeDTO.page});">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 订单管理 &gt;&gt; 订单到账处理
		</div>
		<s:hidden name = "chequeDisposeDTO.orderId" id = "orderId"/>
		<s:hidden name = "chequeDisposeDTO.descr" id = "descr"/>
		<s:hidden name = "chequeDisposeDTO.bank" id = "bank"/>
		<s:hidden name = "chequeDisposeDTO.bankAcc" id = "bankAcc"/>
		<s:hidden name = "chequeDisposeDTO.bankAccHolder" id = "bankAccHolder"/>
		<div id="dialog-confirm" style="display: none;" title="编辑">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
				<tr>
					<th align="right" width="20%">
						订单编号：
					</th>
					<td align="left" width="30%" id="dialog_orderId"></td>
					<th align="right" width="20%">
						到账状态：
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
						订单类型：
					</th>
					<td align="left" width="30%" id="dialog_orderType"></td>
					<th align="right">
						卡片数量：
					</th>
					<td align="left" id="dialog_cardQyt"></td>
				</tr>
				<tr>
					<th align="right">
						起始卡号：
					</th>
					<td align="left" id="dialog_beginCardNo"></td>
					<th align="right">
						购卡人：
					</th>
					<td align="left" id="dialog_paidMen"></td>
				</tr>
				<tr>
				<th align="right">
						开户银行：
					</th>
					<td align="left" id="dialog_bank"></td>
					<th align="right">
						银行账户：
					</th>
		        	<td id="dialog_bankAcc"></td>
				</tr>
				<tr>
					<th align="right">
						开户人：
					</th>
					<td align="left" id="dialog_bankAccHolder"></td>
					<th align="right">
						售卡时间：
					</th>
					<td align="left" id="dialog_saleTime"></td>
				</tr>
				<tr>
					<th align="right">
						到账描述：
					</th>
					<td colspan="3" id="dialog_descr" ></td>
				</tr>
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
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<div class="search">
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<img src="images/fd.jpg" />
					</td>
					<td>
						订单编号:
					</td>
					<td>
						<s:textfield id="orderIdA" name="chequeDisposeDTO.orderId" cssClass="formInput" maxlength="20" theme="simple" />
					</td>
					<td>状态:</td>
					<td>
						<s:select name="chequeDisposeDTO.status" id="status" list="#request.chequeDisposeValue" listKey="key" listValue="value" headerKey="-1" headerValue="--请选择--" cssClass="formInput" theme="simple" />
					</td>
					<td>
						<input type="button" class="formButton" onclick="query();" value="查 询" />
					</td>
				</tr>
			</table>
		</div>
		<table width="96%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="3%">序号</th>
				<th width="10%">
					<a  name="orderId" class="sort">订单编号</a>
				</th>
				<th width="12%">
					<a  name="organName" class="sort">所属机构</a>
				</th>
				<th width="8%">
					<a  name="status" class="sort">到账状态</a>
				</th>
				<th width="8%">
					<a  name="orderType" class="sort">订单类型</a>
				</th>
				<th width="8%">
					<a  name="operator" class="sort">操作人</a>
				</th>
				<th width="8%">
					<a  name="paidTime" class="sort">到账时间</a>
				</th>
				<th width="5%">
					相关操作
				</th>
			</tr>
			</table>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</body>
</html>