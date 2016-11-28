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
	<title>加油站库存微调管理</title>
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
	<script src="js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript">
		//查询方法
		function query(page) {
			var beginDate = $.trim($("#beginDate").val());
			var endDate = $.trim($("#endDate").val());
			var stockId = $.trim($("#stockId").val());
			var oilType = $.trim($("#oilType").val());
			var params = {
			
				"stockAdjustmentDTO.Id" : stockId,
		        "stockAdjustmentDTO.oilType" : oilType,
		        "stockAdjustmentDTO.beginDate":beginDate,
		        "stockAdjustmentDTO.endDate":endDate,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "stockAdjustmentDTO.page" : page
		    }; 
		   ajaxData("stock/stockAdjustment!jsonPageList",params);
		}

		
		
		//加载详情页面
		var loadData = function(id,obj) {
			var type=obj.title;//得到操作方式
		  	var params = {   
		        "stockAdjustmentDTO.stockId" : id
		  	};
		  	var actionUrl = "stock/stockAdjustment!loadOrder"; 
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
		        	if(data.flag){
					var consoleDlg = $("#dialog-confirm"); 
					consoleDlg.attr("title","查看详情");
					consoleDlg.find("#dialog_orderId").html(data.obj.stockId); 
		    		    		
		    		if(data.obj.status==0){
		    			consoleDlg.find("#dialog_status").html("未审核");
		    		}else if(data.obj.status==2){
		    			consoleDlg.find("#dialog_status").html("审核失败");
		    		}else if(data.obj.status==1){
		    			consoleDlg.find("#dialog_status").html("审核成功");
		    		}
		    		
		    		consoleDlg.find("#dialog_orgMerId").html(data.obj.merName); 
		    		consoleDlg.find("#dialog_proposer").html(data.obj.teleNo);
		    		consoleDlg.find("#dialog_oilTypeId").html(data.obj.oilType);
		    		consoleDlg.find("#dialog_oilQty").html(data.obj.stockOilAmoUnt); 
		    		consoleDlg.find("#dialog_createTime").html(data.obj.createTime);
		    		if(data.obj.descR==null){
		    			consoleDlg.find("#descr").val("");
			    	}else{
			    		consoleDlg.find("#descr").val(data.obj.descR);
				    }
		            
		            // 打开对话框   ,判断 状态
		            if(type==="查看"){
		            	consoleDlg.find("#descr").attr("readonly",true);
		            	$("#dialog-confirm").dialog({
		            		resizable: true,
							top: 370,
							height:400,
							width:750,
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
						height:400,
						width:750,
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
		            }else{
		            	alert("数据加载出错!!!");
		            }
		        }   
		    });
		};
		
				/*
			审核通过
		*/
		function success(){
			//var orderId = document.getElementById("orderId").value;
				var stockId = $("#dialog_orderId").text();
				var params = {
					"stockAdjustmentDTO.stockId" : stockId				
			    };
				$.ajax({
					async:false,
					url:"stock/stockAdjustment!auditSuccess",
					type:"post",
					data:params,
					dataType:"json",
					success:function(data, textStatus){	
					alert(data);					
					}
				});
		}
		function noSuccess(){
			var stockId = $("#dialog_orderId").text();
			var descr=$("#descr").val();
			var params = {
				"stockAdjustmentDTO.stockId" : stockId,
				"stockAdjustmentDTO.descR": descr
		    };
			$.ajax({
				async:false,
				url:"stock/stockAdjustment!auditFailure",
				type:"post",
				data:params,
				dataType:"json",
				success:function(data, textStatus){					
				}
			});
		}
	</script> 
</head>
<body onload="query(${stockAdjustmentDTO.page });">

	<div class="Position">
		当前位置是：HOME &gt;&gt; 库存微调管理 &gt;&gt; 微调查看
	</div>
<!--	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>-->
	
	<div id="dialog-confirm" style="display: none;" title="编辑">
			<%--	 <s:include value="/WEB-INF/page/order/batchSale/batchSaleSet.jsp"/>--%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">
				<tr>
					<th align="right" width="20%">库存编号：</th>
					<td align="left" width="30%" id="dialog_orderId"></td>
					<th align="right" width="20%">状态：</th>
					<td align="left" width="30%" id="dialog_status"></td>
				</tr>
				<tr>
					<th align="right" width="20%">联系人：</th>
					<td align="left" width="30%" id="dialog_orgMerId"></td>
					<th align="right" width="20%">联系电话：</th>
					<td align="left" width="30%" id="dialog_proposer"></td>
				</tr>
				<tr>
					<th align="right" width="20%">油品：</th>
					<td align="left" width="30%" id="dialog_oilTypeId"></td>
					<th align="right" width="20%">购油数量：</th>
					<td align="left" width="30%" id="dialog_oilQty"></td>
				</tr>
				<tr>
					<th align="right" width="20%">申请时间：</th>
					<td align="left" width="30%" id="dialog_createTime"></td>
				</tr>				
				<!-- 
				<tr>					
					<th align="right">操作人：</th>
					<td align="left" id="dialog_operator"></td>
					<th align="right">售卡时间：</th>
					<td align="left" id="dialog_saleTime"></td>
				</tr>
				 -->
				<tr>
					<th align="right">描述：</th>
		        	<td colspan="4"><textarea id="descr"  class="formTextarea" cols="70" rows="5" readonly="readonly" maxlength="230"  ></textarea>
		        		<span class="Color3">(255字符以内！)</span>
		        	</td>
				</tr>
				
			</table>
			
		</div>
	
	
	<div class="search">
		<table class="searchTable" cellpadding="0" width="70%" cellspacing="0">
			<tr>
<!--	            <td><img src="images/fd.jpg" /></td>-->
<!--			    <td>加油站名称:</td>-->
<!--				<td><s:textfield id="stockId" name="stockAdjustmentDTO.id" cssClass="formInput" maxlength="20" theme="simple"/></td>-->
				<td>油品类型</td>
	        	<td><s:select name="stockAdjustmentDTO.oilType" id="oilType" list="#request.invChangeValues" listKey="key" headerKey="-1" headerValue="全部" listValue="value" cssClass="formSelect" theme="simple"/></td>
			     <td>创建时间:</td>
				<td><s:textfield id="beginDate" name="stockAdjustmentDTO.beginDate" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssClass="Wdate formInput2" maxlength="20" theme="simple"/>至<s:textfield id="endDate" name="stockAdjustmentDTO.endDate" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssClass="Wdate formInput2" maxlength="20" theme="simple"/></td>
			    <td align="center"><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>			
		</table>
	</div>
	<div class="search">
	<s:if test="#session.user_session.userLevel==2">
		<div class="Fl">
			<my:permission key='sy-2201-02' value='库存微调添加'>
				<input type="button" class="formButton" value="添加" onclick="go('stock/stockAdjustment!addUI')"/>
			</my:permission>
		</div>
	</s:if>
	</div>
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="6%">库存编号</th>
			<th width="6%">商户名称</th>
			<th width="6%">手机号码</th>
			<th width="6%">库存量</th>
			<th width="6%">油品种类</th>
			<th width="3%">状态</th>
			<th width="6%">创建时间</th>
			<th width="6%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom"> 
	<s:if test="#session.user_session.userLevel==2">
		<div class="Fl">
			<my:permission key='sy-2201-02' value='库存微调添加'>
				<input type="button" class="formButton" value="添加" onclick="go('stock/stockAdjustment!addUI')"/>
			</my:permission>
		</div>
	</s:if>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>