<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>" />
	<title>退款管理</title>
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
	<script src="js/datepicker/WdatePicker.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<script src="js/common.js"></script>
	<script type="text/javascript">
		function query(page) {
			var params = {
			    "reMoReasonDTO.orderId" : $.trim($("#orderId").val()),
			    "reMoReasonDTO.memId" : $.trim($("#memId").val()),
			    "reMoReasonDTO.beginDate" : $.trim($("#beginDate").val()),
			    "reMoReasonDTO.endDate" : $.trim($("#endDate").val()),
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "reMoReasonDTO.page" : page
		    };
		   	ajaxData("orders/remoney!jsonPageList",params);
		   	$("#orderProperty").val($("#orderProperty").val());
			$("#orderDirection").val($("#orderDirection").val());
		}
		
		//加载详情页面
		var loadData = function(id, obj) {
			var type = obj.title;//得到操作方式
		  	var params = {   
		        "reMoReasonDTO.reMoReId" : id
		  	};
		  	var actionUrl = "orders/remoney!auditSure";
		    $.ajax({
		    	async : false,
		        url : actionUrl,   
		        data : params,   
		        dataType : "json",   
		        cache : false,   
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误!");
		        }, 
		        success : function(data, textStatus) {
		        	if(data.flag){
						var consoleDlg = $("#dialog-confirm"); 
						consoleDlg.attr("title","审核");
			        	
			        	// 设值
			    		consoleDlg.find("#orderId").val(data.obj.orderId);
						consoleDlg.find("#memId").val(data.obj.memId);
						consoleDlg.find("#reMoTime").val(data.obj.reMoTime);
						consoleDlg.find("#returnMoney").val(data.obj.returnMoneyShow);
						if(data.obj.isTakeGoods == 0){
							consoleDlg.find("#isTakeGoods").val("否");
						}else{
							consoleDlg.find("#isTakeGoods").val("是");
						}
			    			
			        	// 退款理由
						if(null == data.obj.reMoReason){
			    			consoleDlg.find("#reMoReason").val("");
				    	}else if(0 == data.obj.reMoReason){
				    		consoleDlg.find("#reMoReason").val("不想买了");
						}else if(1 == data.obj.reMoReason){
							consoleDlg.find("#reMoReason").val("未收到货");
						}else if(2 == data.obj.reMoReason){
							consoleDlg.find("#reMoReason").val("衣服有瑕疵");
						}else if(3 == data.obj.reMoReason){
							consoleDlg.find("#reMoReason").val("商品错发、漏发");
						}else if(4 == data.obj.reMoReason){
							consoleDlg.find("#reMoReason").val("收到的商品与描述不符");
						} else {
							consoleDlg.find("#reMoReason").val("");
						}
						    
						// 退款说明
						if(data.obj.reMoDesc == null){
			    			consoleDlg.find("#reMoDesc").val("");
				    	}else{
				    		consoleDlg.find("#reMoDesc").val(data.obj.reMoDesc);
						}
					
		            	$("#dialog-confirm").dialog({
							resizable: true,
							top: 370,
							height:400,
							width:750,
							modal: true,
							buttons: {
								'关闭': function() {
									$(this).dialog('close');
								},
								'审核拒绝':function(){
									if(confirm("是否确认审核拒绝？")){
										noSuccess(id, data);
										$(this).dialog('close');
										query(1);
									}
								},
								'审核通过':function(){
									var returnMoney = $.trim($("#returnMoney").val());
									var re = /^\d{1,8}.{0,1}\d{0,4}$/;
									if(!re.test(returnMoney)){
										alert("退款金额输入有误，请重新输入！");
										return false;
									}
								
										if(confirm("是否确认审核通过？")){
											success(id, data);
											$(this).dialog('close');
											query(1);
										}
									
								}
							}
						}); 
		            }else{
		            	alert("数据加载出错!!!");
		            }
		        }   
		    });
		};

		/*审核通过*/
		function success(id, data){
			var params = {   
			        "reMoReasonDTO.reMoReId" : id,
			        "reMoReasonDTO.orderId" : data.obj.orderId,
			        "reMoReasonDTO.returnMoney" : data.obj.returnMoney,
			        "reMoReasonDTO.actuReturnMoney" : $.trim($("#returnMoney").val()),
			        "reMoReasonDTO.isTakeGoods" : data.obj.isTakeGoods,
			        "reMoReasonDTO.memId" : data.obj.memId,
			        "reMoReasonDTO.reMoDesc" : data.obj.reMoDesc,
					"reMoReasonDTO.reMoReason" : data.obj.reMoReason,
					"reMoReasonDTO.reMoSta" : data.obj.reMoSta,
			        "reMoReasonDTO.reMoTime" : data.obj.reMoTime,
			        "reMoReasonDTO.status" : data.obj.status
			  	};
				$.ajax({
					async:false,
					url:"orders/remoney!auditSuccess",
					type:"post",
					data:params,
					dataType:"json",
					success:function(data, textStatus){						
					}
				});
		}

		/*审核不通过*/
		function noSuccess(id, data){
			var params = {   
			        "reMoReasonDTO.reMoReId" : id,
			        "reMoReasonDTO.orderId" : data.obj.orderId,
			        "reMoReasonDTO.returnMoney" : data.obj.returnMoney,
			        "reMoReasonDTO.actuReturnMoney" : data.obj.actuReturnMoney,
			        "reMoReasonDTO.isTakeGoods" : data.obj.isTakeGoods,
			        "reMoReasonDTO.memId" : data.obj.memId,
			        "reMoReasonDTO.reMoDesc" : data.obj.reMoDesc,
					"reMoReasonDTO.reMoReason" : data.obj.reMoReason,
					"reMoReasonDTO.reMoSta" : data.obj.reMoSta,
			        "reMoReasonDTO.reMoTime" : data.obj.reMoTime,
			        "reMoReasonDTO.status" : data.obj.status
			  	};
			$.ajax({
				async:false,
				url:"orders/remoney!auditFailure",
				type:"post",
				data:params,
				dataType:"json",
				success:function(data, textStatus){					
				}
			});
		}
	</script> 
</head>
<body onload="query(${reMoReasonDTO.page});">
	<div class="Position">
		当前位置是：订单管理 &gt;&gt; 订单管理 &gt;&gt; 退款管理
	</div>
	
	<form id="form1" name="form1" action="" method="post">
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
				<td>订单编号:</td>
				<td><s:textfield id="orderId" name="reMoReasonDTO.orderId" cssClass="formInput" maxlength="16" theme="simple" onkeyup = "replaceToNum(this);" /></td>
				<td>会员编号:</td>
		        <td><s:textfield id="memId" name="reMoReasonDTO.memId" cssClass="formInput" maxlength="10" theme="simple" onkeyup="replaceToNum(this);" /></td>
				<td>退款申请时间:</td>
				<td><s:textfield id="beginDate" name="reMoReasonDTO.beginDate" cssClass="Wdate formInput2" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:100px;" maxlength="20" theme="simple"/> - <s:textfield id="endDate" name="reMoReasonDTO.endDate" cssClass="Wdate formInput2" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:100px;" maxlength="20" theme="simple"/></td>
		        <td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
		</table>
		</div>
	</form>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="5%">序号</th>
			<th width="15%"><a name="reMoReId" class="sort">退款编号</a></th>
			<th width="15%"><a name="orderId" class="sort">订单编号</a></th>
			<th width="15%"><a name="memId" class="sort">会员编号</a></th>
			<th width="10%"><a name="isTakeGoods" class="sort">收到商品</a></th>
			<th width="10%"><a name="reMoTime" class="sort">申请时间</a></th>
			<th width="5%"><a name="returnMoney" class="sort">申请退款</a></th>
			<th width="5%"><a name="actuReturnMoney" class="sort">实际退款</a></th>
			<th width="10%"><a name="updateTime" class="sort">更新时间</a></th>
			<th width="10%">相关操作</th>
		</tr>
	</table>
	
	<div class="listBottom">
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
	
	<!-- 审批 -->
	<div id="dialog-confirm" style="display : none;" title="审批">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		<tr>
			<th align="right" width="20%">订单编号：</th>
			<td width="30%"><s:textfield name="reMoReasonDTO.orderId" id="orderId" cssClass="formInput" disabled="true" cssStyle="width:220px;" theme="simple" /></td>
			
			<th align="right" width="20%">会员编号：</th>
			<td width="30%"><s:textfield name="reMoReasonDTO.memId" id="memId" cssClass="formInput" disabled="true" cssStyle="width:220px;" theme="simple" /></td>
		</tr>
		<tr>
			<th align="right" width="20%">申请时间：</th>
			<td width="30%"><s:textfield name="reMoReasonDTO.reMoTime" id="reMoTime" cssClass="formInput" disabled="true" cssStyle="width:220px;" theme="simple" /></td>

			<th align="right" width="20%">退款金额：</th>
			<td width="30%"><s:textfield name="reMoReasonDTO.returnMoneyShow" id="returnMoney" cssClass="formInput" cssStyle="width:220px;" maxlength="13" onkeypress="replaceToNumPoint(this);" theme="simple" /></td>
		</tr>
		<tr>
			<th align="right" width="20%">收到商品：</th>
			<td width="30%"><s:textfield name="reMoReasonDTO.isTakeGoods" id="isTakeGoods" cssClass="formInput" disabled="true" cssStyle="width:220px;" theme="simple" /></td>
			
			<th align="right" width="20%">申请原因：</th>
			<td width="30%"><s:textfield name="reMoReasonDTO.reMoReason" id="reMoReason" cssClass="formInput" disabled="true" cssStyle="width:220px;" theme="simple" /></td>
		</tr>
		<tr>
			<th align="right" width="20%">申请说明：</th>
			<td colspan="3"><textarea name="reMoReasonDTO.reMoDesc" id="reMoDesc" cssClass="formTextarea" disabled="true" rows="6" style="resize:none;" cols="82" /></td>
	    </tr>
	</table>
	</div>
</body> 
</html>