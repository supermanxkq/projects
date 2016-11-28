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
		<title>购油申请管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
			type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.4.2.min.js"></script>
		<link href="js/jquery/css/jquery.ui.all.css" rel="stylesheet"
			type="text/css"/>
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
			var orderId = $.trim($("#orderId").val());
			var status = $.trim($("#status").val());
			//var orgId = $.trim($("#orgId").val());
			var beginTime = $.trim($("#beginTime").val());
			var endTime = $.trim($("#endTime").val());

			var params = {
				"buyOilDto.orderId" : orderId,
		        "buyOilDto.status" : status,
		        //"butOilDto.orgId" : orgId,
		        "buyOilDto.beginTime" : beginTime,
		        "buyOilDto.endTime" : endTime,
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		        "buyOilDto.page" : page
		    }; 
		   ajaxData("buyoilappl/buyoilapplaction!jsonPageList",params);// !是跳转的意思,相当于？
		}
		
		
		//加载详情页面
		var loadData = function(id,obj) {
			var type=obj.title;//得到操作方式
		  	var params = {   
		        "buyOilDto.orderId" : id
		  	};
		  	var actionUrl = "buyoilappl/buyoilapplaction!loadOrder"; 
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
		        	if(type=="查看"){
			        	var consoleDlg = $("#dialog-confirm"); 
						consoleDlg.attr("title","查看页面");
		        	}else{
						var consoleDlg = $("#dialog-confirm"); 
						consoleDlg.attr("title","审核页面");
					}
					consoleDlg.find("#dialog_orderId").html(data.obj.orderId); 
		    		    		
		    		if(data.obj.status==0){
		    			consoleDlg.find("#dialog_status").html("待审核");
		    		}else if(data.obj.status==2){
		    			consoleDlg.find("#dialog_status").html("审核未通过");
		    		}else if(data.obj.status==1){
		    			consoleDlg.find("#dialog_status").html("审核通过");
		    		}
		    		
		    		consoleDlg.find("#dialog_orgMerId").html(data.obj.orgMerName); 
		    		consoleDlg.find("#dialog_proposer").html(data.obj.proposer);
		    		consoleDlg.find("#dialog_oilTypeId").html(data.obj.oilTypeName);
		    		consoleDlg.find("#dialog_oilQty").html(data.obj.oilQty+" （吨）"); 
		    		consoleDlg.find("#dialog_orderAmt").html(data.obj.orderAmt+" （元）");
		    		consoleDlg.find("#dialog_createTime").html(data.obj.createTime);
		    		if(data.obj.descR==null){
		    			consoleDlg.find("#descr").val("");
			    	}else{
			    		consoleDlg.find("#descr").val(data.obj.descR);
				    }
		            
		            // 打开对话框   ,判断 状态
		            if(type=="查看"){
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
								if(descrFlag){
									if(confirm("是否确认审核通过？")){
										success();
										$(this).dialog('close');
										query(1);
									}
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
				var dorderId = $("#dialog_orderId").text();
				var descr=$("#descr").val();
				var params = {
					"buyOilDto.orderId" : dorderId,
					"buyOilDto.descR": descr
			    };
				$.ajax({
					async:false,
					url:"buyoilappl/buyoilapplaction!auditSuccess",
					type:"post",
					data:params,
					dataType:"json",
					success:function(data, textStatus){						
					}
				});
		}
		function noSuccess(){
			var dorderId = $("#dialog_orderId").text();
			var descr=$("#descr").val();
			var params = {
				"buyOilDto.orderId" : dorderId,
				"buyOilDto.descR": descr
		    };
			$.ajax({
				async:false,
				url:"buyoilappl/buyoilapplaction!auditFailure",
				type:"post",
				data:params,
				dataType:"json",
				success:function(data, textStatus){					
				}
			});
		}
		
		//判断描述内容								
			var validateDescr= function(){
				var textareaLength=$("#descr").val().length;  
				$("#descrValue").html("");
				if(textareaLength>254){ 					
					$("#descrValue").html("254以内字符！");
					descrFlag = false;
					return false;
				} 
				else{
					$("#descrValue").html("");
					descrFlag = true;
				}
			 }
		
		
		//撤销一条数据
		var cancelData = function(url,id) {
			if(confirm("确认要撤销该条记录？")){
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
		                	alert("撤销成功!");
		                	query($("#currPage").text());
		            	}else if(data.ajaxResult == "ajaxfailure"){
		            		alert(data.msgResult);
		            	}else {
		            		alert("撤销失败!");
		            	}
		    		}
				});
			}
		}
		
		
	</script>

	</head>
	<body onload="query(${buyOilDto.page });">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 购油申请管理 &gt;&gt; 购油申请查看
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<div id="dialog-confirm" style="display: none;" title="编辑">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">
				<tr>
					<th align="right" width="20%">订单号：</th>
					<td align="left" width="30%" id="dialog_orderId"></td>
					<th align="right" width="20%">状态：</th>
					<td align="left" width="30%" id="dialog_status"></td>
				</tr>
				<tr>
					<th align="right" width="20%">申请方：</th>
					<td align="left" width="30%" id="dialog_orgMerId"></td>
					<th align="right" width="20%">申请人：</th>
					<td align="left" width="30%" id="dialog_proposer"></td>
				</tr>
				<tr>
					<th align="right" width="20%">油品：</th>
					<td align="left" width="30%" id="dialog_oilTypeId"></td>
					<th align="right" width="20%">购油数量：</th>
					<td align="left" width="30%" id="dialog_oilQty"></td>
				</tr>
				<tr>
					<th align="right" width="20%">订单金额：</th>
					<td align="left" width="30%" id="dialog_orderAmt"></td>
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
		        	<td colspan="4"><textarea id="descr"  onblur="validateDescr();" cols="70" rows="5" ></textarea>
		        		<span class="Color3">(254字符以内！)</span>
		        		<span class="Color5" id="descrValue"></span>
		        	</td>
				</tr>
				
			</table>
			
		</div>

		<form id="form1" name="form1" action="order/saleorders!export"
			method="post">
			<div class="search">
				<table class="searchTable" cellpadding="0" cellspacing="0">
					<tr>
						<td><img src="images/fd.jpg" /></td>
						<th>订单编号:</th>
						<td><input type="text" id="orderId"
							name="buyOilDto.orderId" maxlength="19" width="149px"/>
						</td>
						<th>订单状态:</th>
						<td><s:select name="buyOilDto.status" id="status"
							list="#request.status" listKey="key" listValue="value"
							theme="simple" cssStyle="width:151px;" />
						</td>
					</tr>
					<tr>
						<td></td>
						<th>申请时间:</th>
						<td>
							<input name="buyOilDto.beginTime" id="beginTime" type="text"  
							onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" width="149px" /> 
			      		</td>
			      		<th>至:</th>
			      		<td>
			      			<input name="buyOilDto.endTime" id="endTime" type="text"  
			      			onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" width="149px" />
						</td>
						<td colspan="6">
							<input type="button" class="formButton" onclick="query();" value="查 询" />
							<%--<input type="button" class="formButton" value="添加"
								onclick="go('order/saleorders!addUI')" />--%>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<table width="96%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="3%">序号</th>
				<th width="8%"><a name="orderId" class="sort">订单编号</a></th>
				<th width="8%">申请方</th>
				<th width="8%">申请人</th>
				<th width="10%"><a name="createTime" class="sort">创建时间</a></th>
				<th width="8%"><a name="oilTypeId" class="sort">油品</a></th>
				<th width="6%">数量</th>
				<th width="7%">购油金额</th>
				<th width="6%"><a name="status" class="sort">订单状态</a></th>
				<th width="10%">操作人</th>
				<th width="10%">操作时间</th>
				<th width="6%">相关操作</th>				
			</tr>
		</table>
		<div class="listBottom">
			<s:if test="#session.user_session.userLevel!=3">
			<div class="Fl">				
				<input type="button" class="formButton" value="添加" onclick="go('buyoilappl/buyoilapplaction!addUI')"/>			
			</div>
			</s:if>
			<div class="Fr" id="pageNav"/>
		</div>
	</body>
</html>
