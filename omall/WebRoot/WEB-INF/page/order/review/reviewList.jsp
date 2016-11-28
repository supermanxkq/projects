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
	<title>审核管理</title>
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
	<script type="text/javascript">
		
		//查询方法
		function query(page) {
			var revRecId = $.trim($("#revRecId").val());
			var orderId = $.trim($("#orderId").val());
			var reg = new RegExp("^[0-9]*$");
			if(!reg.test(orderId)){
				alert("订单号不合法！");
				return;
			}
			var str = document.getElementById("statuss");
			var status=str.options[str.selectedIndex].value;
			var params = {
				"reviewRecordDTO.revRecId" : revRecId,
		        "reviewRecordDTO.orderId" : orderId,
		        "saleOrderDTO.saleOrderId" : orderId,
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		        "reviewRecordDTO.page" : page,
		        "saleOrderDTO.status" : status,
		        "reviewRecordDTO.status":status
		    }; 
		   ajaxData("review/reviewRecord!jsonPageList",params);// !是跳转的意思,相当于？
		}
		
		//加载详情页面
		var loadData = function(id,obj) {
			var type=obj.title;//得到操作方式
		  	var params = {   
		        "reviewRecordDTO.orderId" : id
		  	};
		  	var actionUrl = "review/reviewRecord!lookUI"; 
		    $.ajax( {  
		    	async:false,
		        url : actionUrl,   
		        data : params,   
		        dataType : "json",   
		        cache : false,   
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误!");	    
		        }, 
		        success : function(data, textStatus) {
		        	//var selectData=[{"key":0,"value":"未审核"},{"key":1,"value":"审核通过"},{"key":2,"value":"审核不通过"}]; 
		        	//var select =document.getElementById("statuss");
		        	//select.options.length=0;
					var consoleDlg = $("#dialog-confirm"); 
					consoleDlg.attr("title","查看详情");
					consoleDlg.find("#saleOrderId").html(data.objResult.saleOrderId); 
		    		consoleDlg.find("#beginCardNo").html(data.objResult.beginCardNo); 
		    		consoleDlg.find("#cardQty").html(data.objResult.cardQty); 
		    		consoleDlg.find("#orderAmt").html(data.objResult.orderAmt+"元");
		    		consoleDlg.find("#paidAmt").html(data.objResult.paidAmt+"元");
		    		consoleDlg.find("#buyMen").html(data.objResult.payMen);
		    		if(data.objResult.paidType==0){
		    			consoleDlg.find("#paidType").html("现金");	
		    		}else if(data.objResult.paidType==1){
		    			consoleDlg.find("#paidType").html("转帐");	
		    		}else{
		    			consoleDlg.find("#paidType").html("支票");
		    		}
		    		if(data.objResult.status==0){
		    			consoleDlg.find("#status").html("未审核");
		    		}else if(data.objResult.status==3){
		    			consoleDlg.find("#status").html("审核通过");
		    		}else if(data.objResult.status==1){
		    			consoleDlg.find("#status").html("审核未通过");
		    		}
		    		if(data.objResult.activateSign==0){
		    			consoleDlg.find("#activateSign").html("未激活");
		    		}else{
		    			consoleDlg.find("#activateSign").html("激活");
		    		}
		    		if(data.objResult.descr==null){
		    			consoleDlg.find("#descr").val("");
			    	}else{
			    		consoleDlg.find("#descr").val(data.objResult.descr);
				    }
		    		
		    		if(data.objResult.revrecTime!=null){
		    			consoleDlg.find("#revrecTime").html(data.objResult.revrecTime);
		    		}else{
		    			consoleDlg.find("#revrecTime").html("未审核");
		    		}
		    		$("#cardNosTb").find('tr:eq(0)').nextAll().remove();
        			$("#cardNosTb").append(data.objResult.cardNoStr);
		            // 打开对话框   ,判断 状态
		            if(type==="查看"){
		            	consoleDlg.find("#descr").attr("readonly",true);
		            	$("#dialog-confirm").dialog({
		            		resizable: true,
							top: 370,
							height:360,
							width:600,
							modal: true,
							buttons:{
								'关闭': function() {
								$(this).dialog('close');
								}
							}
		            	});
		            }else {
		            	consoleDlg.find("#descr").attr("readonly",false);
		            	$("#dialog-confirm").dialog({
						resizable: true,
						top: 370,
						height:360,
						width:600,
						modal: true,
						buttons: {
							'关闭': function() {
								$(this).dialog('close');
							},
							'审核通过':function(){
								if(confirm("是否确认审核通过？")){
									success();
									$(this).dialog('close');
									query(1);
								}
								
							},
							'审核不通过':function(){
								if(confirm("是否确认审核不通过？")){
									noSuccess();
									$(this).dialog('close');
									query(1);
								}
							}
						}
					}); 
		            }
		        }   
		    });
		};
		/*
			审核通过
		*/
		function success(){
			//var orderId = document.getElementById("orderId").value;
				var orderId = $("#saleOrderId").text();
				var paidType = $("#payType").text();
				var descr=$("#descr").val();
				var params = {
					"saleOrderDTO.saleOrderId" : orderId,
					"saleOrderDTO.paidType": paidType,
					"descr": descr
			    };
				$.ajax({
					async:false,
					url:"review/reviewRecord!updateStatus",
					type:"post",
					data:params,
					dataType:"json",
					success:function(data, textStatus){
						
					}
				});
		}
		function noSuccess(){
			var orderId = $("#saleOrderId").text();
			var paidType = $("#payType").text();
			var descr=$("#descr").val();
			var params = {
				"saleOrderDTO.saleOrderId" : orderId,
				"saleOrderDTO.paidType": paidType,
				"descr": descr
		    };
			$.ajax({
				async:false,
				url:"review/reviewRecord!noSuccess",
				type:"post",
				data:params,
				dataType:"json",
				success:function(data, textStatus){
					
				}
			});
		}
	</script> 
</head>
<body onload="query(${reviewRecordDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 订单处理 &gt;&gt; 订单审批管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<s:if test="#session.user_session.userLevel!=2">
		<div id="dialog-confirm" style="display: none;" title="详细信息">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formTable">
					<input type="hidden" id="payType" />
					<tr>
						<th align="right" width="20%">
							订单编号：
						</th>
						<td width="30%" id="saleOrderId"></td>
						<th align="right" width="20%">
							起始卡号：
						</th>
						<td width="30%" id="beginCardNo"></td>
					</tr>
					
					<tr>
						<th align="right">
							购卡数量：
						</th>
						<td id="cardQty"></td>
						<th align="right">
							支付方式：
						</th>
						<td id="paidType"></td>
					</tr>
					<tr>
						<th align="right">
							订单金额：
						</th>
						<td id="orderAmt"></td>
						<th align="right">
							实际支付：
						</th>
						<td id="paidAmt"></td>
					</tr>
					<tr>
						<th align="right">
							审核时间：
						</th>
						<td id="revrecTime"></td>
						<th align="right">
							审核状态：
						</th>
						<td id="status"></td>
					</tr>
					<tr>
						<th align="right">
							订单卡激活状态：
						</th>
						<td id="activateSign"></td>
						<th align="right">
							购卡人姓名：
						</th>
						<td id="buyMen" colspan="3"></td>
					</tr>
					<tr>
						<th align="right">
							审核备注信息：
						</th>
						<td  colspan="3">
							<textarea rows="3" cols="50" id="descr" maxlength="230" ></textarea>
						</td>
					</tr>
				</table>
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
		<div class="search">
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
		            <td><img src="images/fd.jpg" /></td>
					<td>订单编号:</td>
					<td><s:textfield id="orderId" name="reviewRecordDTO.orderId" cssClass="formInput" maxlength="20" theme="simple"/></td>
		        	<td>审核状态:</td>
					<td>
					<!-- 
					<s:textfield id="revRecId" name="reviewRecordDTO.revRecId" cssClass="formInput" maxlength="20" theme="simple"/>
					-->
					<s:select  id="statuss" list="#request.pub" headerKey="-1" headerValue="请选择" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
					</td>
		        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
					
				</tr>
			</table>
		</div>
		
		<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
			<tr>
				<th width="3%">序号</th>
				<th width="10%"><a name="orderId">订单编号</a></th>
				<th width="10%"><a name="beginCardNo">起始卡号</a></th>
				<th width="10%"><a name="orgName" >数量</a></th>
				<th width="5%"><a name="orderAmt" >订单金额</a></th>
				<th width="8%"><a name="paidType" >支付方式</a></th>
				<th width="8%"><a name="reviewStatus">审核状态</a></th>
				<th width="8%"><a name="operatorId" >操作人</a></th>
				<th width="8%"><a name="revRecTime" >审核时间</a></th>
				<th width="5%">相关操作</th>
			</tr>
		</table>
		<div class="listBottom">
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false"/>
			</div>
		</div>
	</s:if>
</body> 
</html>