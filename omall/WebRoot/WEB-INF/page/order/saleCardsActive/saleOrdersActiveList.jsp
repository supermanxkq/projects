<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="http://192.168.1.3:8080/mciu/" />
	<title>卡类型管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="shortcut icon" href="http://192.168.1.3:8080/mciu/favicon.ico" type="image/x-icon" />
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
	<script src="js/ajax_alert.js"></script>
	<script type="text/javascript"><!--
		//加载修改页面
		var loadData = function(id) {
		  	var params = {
		        "sellCardOrdersDTO.orderId" : id
		  	};
		  	var actionUrl = "transa/sellCardOrdersActive!editUI";
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
		            consoleDlg.find("#dialog_orderId").html(rowData.orderId+"<input type='hidden' id='orderId' value='"+rowData.orderId+"'/>");
		            consoleDlg.find("#dialog_statusValue").html(rowData.statusValue);
		            consoleDlg.find("#dialog_cardPrice").html(rowData.cardPrice);
		            consoleDlg.find("#dialog_payTypeValue").html(rowData.payTypeValue);
		            consoleDlg.find("#dialog_price").html(rowData.price);
		            consoleDlg.find("#dialog_vipName").html(rowData.vipName);
		            consoleDlg.find("#dialog_operTimeApp").html(rowData.operTimeApp);
		            consoleDlg.find("#dialog_operIdApp").html(rowData.operIdApp);
		            consoleDlg.find("#dialog_descriptionApp").html(rowData.descriptionApp);
		            consoleDlg.find("#dialog_operTimeAudit").html(rowData.operTimeAudit);
		            consoleDlg.find("#dialog_operIdAudit").html(rowData.operIdAudit);
		            consoleDlg.find("#dialog_descriptionAudit").val(rowData.descriptionAudit);
		            $("#rnd").val(rowData.rnd);
		            $("#cardNosTb").find('tr:eq(0)').nextAll().remove();
		            $("#cardNosTb").append(rowData.cardNosStr);
		        }
		    });
		};
		
		//查看页面
		var viewData = function(id){
			loadData(id);
			var consoleDlg = $("#dialog-confirm");
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
		}
		
		//审核页面
		var activeData = function(id){
			loadData(id);
			var consoleDlg = $("#dialog-confirm");
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
					},
					'选中激活': selectActive,
					'全部激活': active
				}
			});
		}
		
		//选中激活
		var selectActive = function(){
			var ids = $('[name=id]');
			var idStr = '';
		 	for(var i=0;i<ids.length;i++){
		        if(ids[i].checked){                             
		        	if(idStr == ''){
		        		idStr = ids[i].value;
		        	}else{
		        		idStr = idStr + "," + ids[i].value;
		        	}
		       	}
		  	}
		  	if(idStr == ''){
		   		alert("请选择批量激活项");
		   		return ;
		  	}
			if(confirm("卡号:"+idStr+"是否确认激活？")){
				var consoleDlg = $("#dialog-confirm"); 
			    var orderId = $.trim(consoleDlg.find("#orderId").val());
			    
				if (orderId==''){
					alert("系统繁忙!");
			    	return false;
				}
				if(!verifyUKey(false)){
					return false;
				}
				var keyID = $("#keyID").val();
			    var rnd = $("#rnd").val();
			    var return_EncData = $("#return_EncData").val();
				
			    var params = {
			    	"sellCardOrdersDTO.orderId" : orderId,
			   		"sellCardOrdersDTO.cardNosStr" : idStr,
			        "sellCardOrdersDTO.keyID" : keyID,
			        "sellCardOrdersDTO.rnd" : rnd,
			        "sellCardOrdersDTO.return_EncData" : return_EncData
			    };
	
			    var actionUrl = "transa/sellCardOrdersActive!selectActive";
			    $.ajax( {   
			        url : actionUrl,   
			        data : params,   
			        dataType : "json",
			        cache : false, 
			        type : "POST",
			        error : function(textStatus, errorThrown) {   
			    		alert("系统ajax交互错误!");  
			        },
			        beforeSend : function(){
        				ajax_start("处理中，请稍候！");
					},
			        success : function(data, textStatus) {   
			            ajax_end();
			            if (data.ajaxResult == "ajaxsuccess") {   
							//consoleDlg.dialog("close");  
			                activeData(orderId);
			                alert("操作成功!");
			                query($("#currPage").text());	                
			           	} else if(data.ajaxResult == "ajaxfailure") {
			                alert(data.msgResult);
			            }else {
			            	alert("操作失败!");
			            }
			        }
			    });
			}
		}
		
		//修改方法
		var active = function() {
		    if(confirm("是否确认全部激活？")){
				var consoleDlg = $("#dialog-confirm"); 
			    var orderId = $.trim(consoleDlg.find("#orderId").val());
			    
				if (orderId==''){
					alert("系统繁忙!");
			    	return false;
				}
				
				if(!verifyUKey(false)){
					return false;
				}
				var keyID = $("#keyID").val();
			    var rnd = $("#rnd").val();
			    var return_EncData = $("#return_EncData").val();
				
			    var params = {
			   		"sellCardOrdersDTO.orderId" : orderId,
			        "sellCardOrdersDTO.keyID" : keyID,
			        "sellCardOrdersDTO.rnd" : rnd,
			        "sellCardOrdersDTO.return_EncData" : return_EncData
			    };
	
			    var actionUrl = "transa/sellCardOrdersActive!active";
			    $.ajax( {   
			        url : actionUrl,   
			        data : params,   
			        dataType : "json",
			        cache : false, 
			        type : "POST",
			        error : function(textStatus, errorThrown) {   
			    		alert("系统ajax交互错误!");  
			        },
			        beforeSend : function(){
        				ajax_start("处理中，请稍候！");
					},
			        success : function(data, textStatus) {   
			            ajax_end();
			            if (data.ajaxResult == "ajaxsuccess") {   
							consoleDlg.dialog("close");  
			                alert("操作成功!");
			                query($("#currPage").text());	                
			           	} else if(data.ajaxResult == "ajaxfailure") {
			                alert(data.msgResult);
			            }else {
			            	alert("操作失败!");
			            }
			        }
			    });
		    }
		}; 
		//查询方法
		function query(page) {
		
			var orderId = $.trim($("#orderId").val());

			var params = {
		        "sellCardOrdersDTO.orderId" : orderId,
		        "sellCardOrdersDTO.page" : page
		    }; 
		   ajaxData("transa/sellCardOrdersActive!jsonPageList",params);
		}
	--></script> 
</head>
<body onload="query(1);">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 售卡管理 &gt;&gt; 订单卡片激活
	</div>
	<input name="sellCardOrdersDTO.keyID" id="keyID" type="hidden" />
	<input name="sellCardOrdersDTO.rnd" id="rnd" type="hidden" />
	<input name="sellCardOrdersDTO.return_EncData" id="return_EncData" type="hidden" />
	<div id="dialog-confirm" style="display: none;" title="编辑">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
				<th align="right" width="20%">订单号：</th>
				<td align="left" width="30%" id="dialog_orderId"></td>
				<th align="right" width="20%">状态：</th>
				<td align="left" width="30%" id="dialog_statusValue"></td>
			</tr>
			<tr>
				<th align="right">卡片总金额：</th>
				<td align="left" id="dialog_cardPrice"></td>
				<th align="right">支付金额：</th>
				<td align="left" id="dialog_price"></td>
			</tr>
			<tr>
				<th align="right">支付方式：</th>
				<td align="left" id="dialog_payTypeValue"></td>
				<th align="right">购卡人：</th>
				<td align="left" id="dialog_vipName"></td>
			</tr>
			<tr>
				<th align="right">退卡时间：</th>
				<td align="left" id="dialog_operTimeApp"></td>
				<th align="right">描述：</th>
				<td align="left" id="dialog_descriptionApp"></td>
			</tr>
			<tr>
				<th align="right">操作人：</th>
				<td align="left" id="dialog_operIdApp"></td>
				<th align="right">审核人：</th>
				<td align="left" id="dialog_operIdAudit"></td>
			</tr>
			<tr>
				<th align="right">审核时间：</th>
				<td align="left" id="dialog_operTimeAudit" colspan="3"></td>
			</tr>
			<tr>
		      	<th align="right">审核描述：</th>
		        <td colspan="3"><textarea id="dialog_descriptionAudit" class="formTextarea" cols="45" rows="5" ></textarea></td>
		    </tr>
		</table>
		<table width="96%" id="cardNosTb" class="listTable" cellpadding="0" cellspacing="0" >
			<tr>
				<th width="3%"><input type="checkbox" name="checkbox" id="checkbox" onclick="switchAll();" /></th>
				<th width="10%">卡号</th>
				<th width="10%">卡状态</th>
			</tr>
		</table>
	</div>
	
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>订单号:</td>
				<td><input type="text" id="orderId" name="orderId" class="formInput"/></td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="10%">订单号</th>
			<th width="10%">支付方式</th>
			<th width="10%">订单总价</th>
			<th width="10%">购卡人</th>
			<th width="10%">售卡时间</th>
			<th width="6%">状态</th>
			<th width="6%">是否激活</th>
			<th width="6%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
		</div>
		<div class="Fr" id="pageNav">
			
		</div>
	</div>
</body>
</html>
