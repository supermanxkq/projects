<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
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
	<script type="text/javascript">
		
		//选择卡BIN
		function secCardBin(){
		    var cardBin = $("#cardBin").val();
			if(cardBin<0){
				alert("请选择卡BIN！");
				return false;
			}
			var params = {
		   		"sellCardOrdersDTO.cardBin" : cardBin
		    };

		    var actionUrl = "transa/sellCardOrders!secCardBin";   
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
		            if (data.flag == true) {
			        	$('#cardValueTd').html(data.obj.cardValue);
						getStartNo();
		           	} else {
		            	alert("操作失败!");
		            }
		        }
		    });
		}
		
		//获取起始号码
		function getStartNo(){
			var cardBin = $("#cardBin").val();
			
	      	var cardNosList = $('[name=sellCardOrdersDTO.cardNos]');
			var cardNos = "";
		    for(var i=0;i<cardNosList.length;i++){
	         	cardNos = cardNos + "," + cardNosList[i].value;
	      	}
			if (cardBin<0){
				alert("请选择卡BIN!");
		    	$("#cardBin").focus();
		    	return false;
			}
			var params = {
		   		"sellCardOrdersDTO.cardBin" : cardBin,
		   		"sellCardOrdersDTO.cardNosStr" : cardNos
		    };
			var actionUrl = "transa/sellCardOrders!getStartNo";   
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
		            if (data.flag == true) {
						$("#startNo").val(data.msg);                
		           	} else {
		            	alert("无可添加号码!");
		            	$("#startNo").val('');
		            }
		        }
		    });
		}

		//增加卡片
		function addCardNo(){
		    var startNo = $("#startNo").val();
		    var num = $("#num").val();
		    var cardBin = $("#cardBin").val();
		    var levelId = $("#levelId").val();
		    var groupId = $("#groupId").val();
		    var cardValue = $("#cardValue").val();
		    
		    var cardNosList = $('[name=sellCardOrdersDTO.cardNos]');
			var cardNos = "";
		    for(var i=0;i<cardNosList.length;i++){
	         	cardNos = cardNos + "," + cardNosList[i].value;
	      	}
		    if (cardBin<0){
				alert("请选择卡BIN!");
		    	$("#cardBin").focus();
		    	return false;
			}
			
			if (startNo==''){
				alert("起始卡号不能为空!");
		    	$("#startNo").focus();
		    	return false;
			}
			if (cardValue==''){
				alert("面值不能为空!");
		    	$("#cardValue").focus();
		    	return false;
			}
			if (num==''){
				alert("数量不能为空!");
		    	$("#num").focus();
		    	return false;
			}
			
			var params = {
		   		"sellCardOrdersDTO.startNo" : startNo,
		   		"sellCardOrdersDTO.levelId" : levelId,
		   		"sellCardOrdersDTO.groupId" : groupId,
		   		"sellCardOrdersDTO.num" : num,
		   		"sellCardOrdersDTO.cardBin" : cardBin,
		   		"sellCardOrdersDTO.cardNosStr" : cardNos,
		   		"sellCardOrdersDTO.cardValue" : cardValue
		    };
			var actionUrl = "transa/sellCardOrders!addCardNo";   
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
		            if (data.flag) {
						var list = data.obj;
						if (list.length>0){
        					for(var i=0;i<list.length;i++) {
        						var row = Math.ceil(Math.random( )*1000000);
								var tr = $("<tr id='r_"+row+"'></tr>");
			        			var html = "";
			        			html += "<td width='10%'>"+list[i].cardNoView+"<input type='hidden' name='sellCardOrdersDTO.cardNos' value='"+list[i].cardNo+"'/></td>";
			        			html += "<td width='10%'>"+list[i].cardBin+"</td>";
			        			html += "<td width='10%'>"+list[i].levelName+"<input type='hidden' name='sellCardOrdersDTO.levelIds' value='"+list[i].levelId+"'/></td>";
			        			html += "<td width='10%'>"+list[i].cardValue+"<input type='hidden' name='sellCardOrdersDTO.cardValues' value='"+list[i].cardValue+"'/></td>";
			        			html += "<td width='10%'>"+list[i].groupName+"<input type='hidden' name='sellCardOrdersDTO.groupIds' value='"+list[i].groupId+"'/></td>";
			        			html += '<td width=\"10%\"><a href="javascript:delRow(\'r_'+row+'\');">[移除]</a></td>';
			              		tr.html(html);
			              		$("#cardNosTb").append(tr);
        					}
        					sumCardPrice();
						}
		           	} else {
		            	alert(data.msg);
		            }
		        }
		    });
		}
		
		//计算售卡总金额
		var sumCardPrice = function(){
			var cardPrice = 0;
            var cardValuesList = $('[name=sellCardOrdersDTO.cardValues]');
            var numsList = $('[name=sellCardOrdersDTO.nums]');
		    for(var i=0;i<cardValuesList.length;i++){
	         	cardPrice = parseFloat(cardPrice)+parseFloat(cardValuesList[i].value);
	      	}
            $("#cardPrice").val(cardPrice);
		}
		
		var delRow = function(aa){
			$('#'+aa).remove();
			sumCardPrice();
		}
		
		var check = function(){
			
			var cardPrice = $("#cardPrice").val();
			var sellLimitAmount = $("#sellLimitAmount").val();
			var payType = $("#payType").val();
			var price = $("#price").val();
			var vipId = $("#vipId").val();
			var isActive = $("#isActive").val();
			var isRealName = $("#isRealName").val();
			
			var cardNosList = $('[name=sellCardOrdersDTO.cardNos]');
			if(cardNosList.length<1){
				alert("请添加卡号!");
		    	return false;
			}
			if (payType<0){
				alert("请选择支付方式!");
		    	$("#payType").focus();
		    	return false;
			}
			if (price==''){
				alert("支付金额不能为空!");
		    	$("#price").focus();
		    	return false;
			}
			if (isActive<0){
				alert("请选择卡是否激活!");
		    	$("#isActive").focus();
		    	return false;
			}
			if (isRealName<0){
				alert("请选择卡是否实名!");
		    	$("#isRealName").focus();
		    	return false;
			}
			if(isRealName==1){
				if (vipId==''){
					alert("请选择购卡人!");
			    	$("#vipId").focus();
			    	return false;
				}
			}
			
			if(parseFloat(cardPrice)>parseFloat(sellLimitAmount)){
				if(!confirm("售卡总金额大于订单的售卡充值限额,提交后需要等待审核，是否确认提交？")){
					return false;
				}
			}
			if(!verifyUKey(false)){
				return false;
			}
			ajax_start("处理中，请稍候！");
		}
	--></script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 售卡管理 &gt;&gt; 售卡订单
	</div>
	

 
  

<base href="http://192.168.1.3:8080/mciu/" />
<script language="javascript" type="text/javascript" defer="defer" src="http://192.168.1.3:8080/mciu/js/datepicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="http://192.168.1.3:8080/mciu/js/des.js"></script>
<script type="text/javascript"><!--
	
	var ajaxVip = function(){
		vipQuery(1);
		// 打开对话框
		$("#dialog-vip").dialog({
			resizable: true,
			top: 240,
			height:360,
			width:700,
			modal: true,
			buttons: {
				'取消': function() {
					$(this).dialog('close');
				}
			}
		});
	}
	
	//查询方法
	function vipQuery(page) {
	
		var realName = $.trim($("#search_realName").val());

		var params = {
	        "vipsDTO.realName" : realName,
	        "vipsDTO.page" : page
	    };
	   ajaxDatas("base/vips!vipJsonPageList",params,"vipTb","vipPageNav");
	}
	
	//选择会员
	var secVip = function(id,name){
		$("#vipId").val(id);
		$("#vipName").val(name);
		$("#dialog-vip").dialog('close');
	}

	var addVipUI = function(){
		var actionUrl = "base/vips!addUI";
	    $.ajax( {   
	        url : actionUrl,
	        dataType : "json",   
	        cache : false,
	        type : "POST",
	        error : function(textStatus, errorThrown) {
	    		alert("系统ajax交互错误!");
	        },
	        success : function(data, textStatus) {
				var consoleDlg = $("#dialog-addvip");
				var rowData = data.objResult;
				consoleDlg.find("#statusHtml").html(rowData.statusHtml);
				consoleDlg.find("#personIdTypeHtml").html(rowData.personIdTypeHtml);
				consoleDlg.find("#sexHtml").html(rowData.sexHtml);
				consoleDlg.find("#areaHtml").html(rowData.areaHtml);
				consoleDlg.find("#parentVipHtml").html(rowData.parentVipHtml);
				consoleDlg.find("#groupHtml").html(rowData.groupHtml);
				consoleDlg.find("#vipTypeHtml").html(rowData.vipTypeHtml);
	            consoleDlg.find("#td_vipId").html(rowData.vipId+"<input type='hidden' id='dialog_vipId' value='"+rowData.vipId+"'/>");
				consoleDlg.find("#dialog_nameChinese").val('');
				consoleDlg.find("#dialog_realName").val('');
	            consoleDlg.find("#dialog_sex").val('');
	            consoleDlg.find("#dialog_birthDay").val('');
	            consoleDlg.find("#dialog_teleNo").val('');
	            consoleDlg.find("#dialog_email").val('');
	            consoleDlg.find("#dialog_personIdType").val('');
	            consoleDlg.find("#dialog_personId").val('');
	            consoleDlg.find("#dialog_cultDegree").val('');
	            consoleDlg.find("#dialog_career").val('');
	            consoleDlg.find("#dialog_residAddress").val('');
	            consoleDlg.find("#dialog_residZip").val('');
	            consoleDlg.find("#dialog_status").val('1');
	            consoleDlg.find("#dialog_groupId").val('-1');
	            consoleDlg.find("#dialog_parentVipId").val('-1');
	            consoleDlg.find("#dialog_vipType").val('0');
	            
	            if(0==1){
					var cipherCode = getCipherCode();
					if(rowData.teleNo!=""){
						var dialog_teleNo = uncMe(cipherCode,rowData.teleNo);
						consoleDlg.find("#dialog_teleNo").val(dialog_teleNo);
					}
					if(rowData.personId!=""){
						var dialog_personId = uncMe(cipherCode,rowData.personId);
						consoleDlg.find("#dialog_personId").val(dialog_personId);
					}
				}
	            
	            // 打开对话框
				$("#dialog-addvip").dialog({
					resizable: true,
					top: 370,
					height:370,
					width:650,
					modal: true,
					buttons: {
						'取消': function() {
							$(this).dialog('close');
						},
						'确定': addVipData
					}
				});
	        }
	    });
	}
	
	//添加方法
	var addVipData = function() {   
	    var consoleDlg = $("#dialog-addvip");  			
	    var dialog_vipId = $.trim(consoleDlg.find("#dialog_vipId").val()); 
	    var dialog_nameChinese = $.trim(consoleDlg.find("#dialog_nameChinese").val());
	    var dialog_realName = $.trim(consoleDlg.find("#dialog_realName").val());
	    var dialog_sex = $.trim(consoleDlg.find("#dialog_sex").val());
	    var dialog_birthDay = $.trim(consoleDlg.find("#dialog_birthDay").val());
	    var dialog_teleNo = $.trim(consoleDlg.find("#dialog_teleNo").val());
	    var dialog_email = $.trim(consoleDlg.find("#dialog_email").val());
	    var dialog_personIdType = $.trim(consoleDlg.find("#dialog_personIdType").val());
	    var dialog_personId = $.trim(consoleDlg.find("#dialog_personId").val());
	    var dialog_cultDegree = $.trim(consoleDlg.find("#dialog_cultDegree").val());
	    var dialog_career = $.trim(consoleDlg.find("#dialog_career").val());
	    var dialog_residAddress = $.trim(consoleDlg.find("#dialog_residAddress").val());
	    var dialog_residZip = $.trim(consoleDlg.find("#dialog_residZip").val());
	    var dialog_status = $.trim(consoleDlg.find("#dialog_status").val());
	    var dialog_areaId = $.trim(consoleDlg.find("#dialog_areaId").val());
	    var dialog_parentVipId = $.trim(consoleDlg.find("#dialog_parentVipId").val());
	    var dialog_groupId = $.trim(consoleDlg.find("#dialog_groupId").val());
	    var dialog_vipType = $.trim(consoleDlg.find("#dialog_vipType").val());
	    var jmKeyID = "";
	    
		if (dialog_vipId==''){
			alert("会员号不能为空!");
	    	consoleDlg.find("#dialog_vipId").focus();
	    	return false;
		}
		if (dialog_vipType<0){
	    	alert("请选择会员类型!");	 
	    	consoleDlg.find("#dialog_vipType").focus();
	    	return false;
		}
	    if (dialog_nameChinese==''){
	    	alert("会员名称不能为空!");	 
	    	consoleDlg.find("#dialog_nameChinese").focus();
	    	return false;
		}
		if (dialog_realName==''){
	    	alert("真实姓名不能为空!");	 
	    	consoleDlg.find("#dialog_realName").focus();
	    	return false;
		}
	
		if (dialog_teleNo==''){
	    	alert("联系电话不能为空!");	 
	    	consoleDlg.find("#dialog_teleNo").focus();
	    	return false;
		}
		
		if(0==1){
			
			var cipherCode = getCipherCode();	
			jmKeyID = $("#jmKeyID").val();
			if(dialog_teleNo!=""){
				dialog_teleNo = encMe(cipherCode,dialog_teleNo);
			}
			if(dialog_personId!=""){
				dialog_personId = encMe(cipherCode,dialog_personId);
			}
			
		}
		
	    var params = {
	   		"vipsDTO.vipId" : dialog_vipId,
	   		"vipsDTO.vipType" : dialog_vipType,
	   		"vipsDTO.nameChinese" : dialog_nameChinese,
	   		"vipsDTO.realName" : dialog_realName,
	   		"vipsDTO.sex" : dialog_sex,
	   		"vipsDTO.birthDay" : dialog_birthDay,
	   		"vipsDTO.teleNo" : dialog_teleNo,
	   		"vipsDTO.email" : dialog_email,
	   		"vipsDTO.personIdType" : dialog_personIdType,
	   		"vipsDTO.personId" : dialog_personId,
	   		"vipsDTO.cultDegree" : dialog_cultDegree,
	   		"vipsDTO.career" : dialog_career,
	   		"vipsDTO.residAddress" : dialog_residAddress,
	   		"vipsDTO.residZip" : dialog_residZip,
	   		"vipsDTO.status" : dialog_status,
	        "vipsDTO.areaId" : dialog_areaId,
	        "vipsDTO.parentVipId" : dialog_parentVipId,
	        "vipsDTO.groupId" : dialog_groupId,
	        "vipsDTO.jmKeyID" : jmKeyID
	    };
	    var actionUrl = "base/vips!add";
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
	            if(data.ajaxResult == "ajaxsuccess") {	                
	                consoleDlg.dialog("close");   
	                alert("操作成功!");
	                vipQuery('1');
	                query('1');
	            } else if(data.ajaxResult == "ajaxfailure") {	                 
	                alert(data.msgResult); 
	            }else {
	            	alert("操作失败!");   
	            }   
	        } 
	    });   
	};
	
	
	//加载修改页面
	var loadVipData = function(id) {
	  	var params = {   
	        "vipsDTO.vipId" : id 
	  	};  
	  	var actionUrl = "base/vips!editUI"; 
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
				var consoleDlg = $("#dialog-addvip"); 
				var rowData = data.objResult;
	            consoleDlg.find("#statusHtml").html(rowData.statusHtml);
				consoleDlg.find("#personIdTypeHtml").html(rowData.personIdTypeHtml);
				consoleDlg.find("#sexHtml").html(rowData.sexHtml);
				consoleDlg.find("#areaHtml").html(rowData.areaHtml);
				consoleDlg.find("#parentVipHtml").html(rowData.parentVipHtml);
				consoleDlg.find("#groupHtml").html(rowData.groupHtml);
				consoleDlg.find("#vipTypeHtml").html(rowData.vipTypeHtml);
	            consoleDlg.find("#td_vipId").html(rowData.vipId+"<input type='hidden' id='dialog_vipId' value='"+rowData.vipId+"'/>");
				consoleDlg.find("#dialog_nameChinese").val(rowData.nameChinese);
				consoleDlg.find("#dialog_realName").val(rowData.realName);
	            consoleDlg.find("#dialog_sex").val(rowData.sex);
	            consoleDlg.find("#dialog_birthDay").val(rowData.birthDay);
	            consoleDlg.find("#dialog_teleNo").val(rowData.teleNo);
	            consoleDlg.find("#dialog_email").val(rowData.email);
	            consoleDlg.find("#dialog_personIdType").val(rowData.personIdType);
	            consoleDlg.find("#dialog_personId").val(rowData.personId);
	            consoleDlg.find("#dialog_cultDegree").val(rowData.cultDegree);
	            consoleDlg.find("#dialog_career").val(rowData.career);
	            consoleDlg.find("#dialog_residAddress").val(rowData.residAddress);
	            consoleDlg.find("#dialog_residZip").val(rowData.residZip);
	            consoleDlg.find("#dialog_status").val(rowData.status);
	            consoleDlg.find("#dialog_areaId").val(rowData.areaId);
	            consoleDlg.find("#dialog_updateTime").html(rowData.updateTime);
	            consoleDlg.find("#dialog_parentVipId").val(rowData.parentVipId);
	            consoleDlg.find("#dialog_groupId").val(rowData.groupId);
	            consoleDlg.find("#dialog_vipType").val(rowData.vipType);
	            
	            if(0==1){
					var cipherCode = getCipherCode();
					if(rowData.teleNo!=""){
						var dialog_teleNo = uncMe(cipherCode,rowData.teleNo);
						consoleDlg.find("#dialog_teleNo").val(dialog_teleNo);
					}
					if(rowData.personId!=""){
						var dialog_personId = uncMe(cipherCode,rowData.personId);
						consoleDlg.find("#dialog_personId").val(dialog_personId);
					}
				}
	            
	            // 打开对话框
				$("#dialog-addvip").dialog({
					resizable: true,
					top: 370,
					height:370,
					width:650,
					modal: true,
					buttons: {
						'取消': function() {
							$(this).dialog('close');
						},
						'确定': updateVipData
					}
				});    
	        }   
	    });
	};
		
	//修改方法
	var updateVipData = function() {
	    var consoleDlg = $("#dialog-addvip"); 
	    var dialog_vipId = $.trim(consoleDlg.find("#dialog_vipId").val()); 
	    var dialog_nameChinese = $.trim(consoleDlg.find("#dialog_nameChinese").val());
	    var dialog_realName = $.trim(consoleDlg.find("#dialog_realName").val());
	    var dialog_sex = $.trim(consoleDlg.find("#dialog_sex").val());
	    var dialog_birthDay = $.trim(consoleDlg.find("#dialog_birthDay").val());
	    var dialog_teleNo = $.trim(consoleDlg.find("#dialog_teleNo").val());
	    var dialog_email = $.trim(consoleDlg.find("#dialog_email").val());
	    var dialog_personIdType = $.trim(consoleDlg.find("#dialog_personIdType").val());
	    var dialog_personId = $.trim(consoleDlg.find("#dialog_personId").val());
	    var dialog_cultDegree = $.trim(consoleDlg.find("#dialog_cultDegree").val());
	    var dialog_career = $.trim(consoleDlg.find("#dialog_career").val());
	    var dialog_residAddress = $.trim(consoleDlg.find("#dialog_residAddress").val());
	    var dialog_residZip = $.trim(consoleDlg.find("#dialog_residZip").val());
	    var dialog_status = $.trim(consoleDlg.find("#dialog_status").val());
	    var dialog_areaId = $.trim(consoleDlg.find("#dialog_areaId").val());
	    var dialog_parentVipId = $.trim(consoleDlg.find("#dialog_parentVipId").val());
	    var dialog_groupId = $.trim(consoleDlg.find("#dialog_groupId").val());
	    var dialog_vipType = $.trim(consoleDlg.find("#dialog_vipType").val());
	   	var jmKeyID = "";
	    
		if (dialog_vipId==''){
			alert("会员号不能为空!");
	    	consoleDlg.find("#dialog_vipId").focus();
	    	return false;
		}
		if (dialog_vipType<0){
	    	alert("请选择会员类型!");	 
	    	consoleDlg.find("#dialog_vipType").focus();
	    	return false;
		}
		
	    if (dialog_nameChinese==''){
	    	alert("会员名称不能为空!");	 
	    	consoleDlg.find("#dialog_nameChinese").focus();
	    	return false;
		}
		if (dialog_realName==''){
	    	alert("真实姓名不能为空!");	 
	    	consoleDlg.find("#dialog_realName").focus();
	    	return false;
		}
		if (dialog_teleNo==''){
	    	alert("联系电话不能为空!");	 
	    	consoleDlg.find("#dialog_teleNo").focus();
	    	return false;
		}
		
		if(0==1){
			var cipherCode = getCipherCode();	
			jmKeyID = $("#jmKeyID").val();
			if(dialog_teleNo!=""){
				dialog_teleNo = encMe(cipherCode,dialog_teleNo);
			}
			if(dialog_personId!=""){
				dialog_personId = encMe(cipherCode,dialog_personId);
			}
		}
		
	    var params = {
	        "vipsDTO.vipId" : dialog_vipId,
	        "vipsDTO.vipType" : dialog_vipType,
	   		"vipsDTO.nameChinese" : dialog_nameChinese,
	   		"vipsDTO.realName" : dialog_realName,
	   		"vipsDTO.sex" : dialog_sex,
	   		"vipsDTO.birthDay" : dialog_birthDay,
	   		"vipsDTO.teleNo" : dialog_teleNo,
	   		"vipsDTO.email" : dialog_email,
	   		"vipsDTO.personIdType" : dialog_personIdType,
	   		"vipsDTO.personId" : dialog_personId,
	   		"vipsDTO.cultDegree" : dialog_cultDegree,
	   		"vipsDTO.career" : dialog_career,
	   		"vipsDTO.residAddress" : dialog_residAddress,
	   		"vipsDTO.residZip" : dialog_residZip,
	   		"vipsDTO.status" : dialog_status,
	        "vipsDTO.areaId" : dialog_areaId,
	        "vipsDTO.parentVipId" : dialog_parentVipId,
        	"vipsDTO.groupId" : dialog_groupId,
        	"vipsDTO.jmKeyID" : jmKeyID
	    };  

	    var actionUrl = "base/vips!update";
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
	    })
	};
	
	</script>
	
	<input name="jmKeyID" type="hidden" id="jmKeyID" />
	<div id="dialog-vip" style="display: none;" title="选择会员">
		<div >
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
		            <td><img src="images/fd.jpg" /></td>
		            <td>会员姓名:</td>
					<td><input type="text" id="search_realName" name="vipId" class="formInput"/></td>
		        	<td><input type="button" class="formButton" onclick="vipQuery();" value="查 询" /></td>
				</tr>
			</table>
		</div>
		<table width="96%" id="vipTb" class="listTable" cellpadding="0" cellspacing="0" >
			<tr>
				<th width="3%">序号</th>
				<th width="6%">会员号</th>
				<th width="6%">会员类型</th>
				<th width="10%">会员名称</th>
				<th width="6%">会员姓名</th>
				<th width="5%">相关操作</th>
			</tr>
		</table>
		<div class="listBottom">
			<div class="Fl">
				<input type="button" class="formButton" value="添加" onclick="addVipUI();"/>
			</div>
			<div class="Fr" id="vipPageNav">
				
			</div>
		</div>
	</div>

	<div id="dialog-addvip" style="display: none;" title="编辑">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		      <tr>
		       	<th align="right" width="20%">会员号：</th>
		        <td width="30%" id="td_vipId"></td>
		        <th align="right" width="20%">会员类型：</th>
		        <td width="30%" id="vipTypeHtml"></td>
		      </tr>
		      <tr>
		      	<th align="right" width="20%"><span class="Color5">* </span>会员名称：</th>
		        <td width="30%"><input type="text" id="dialog_nameChinese" maxlength="20" class="formInput"/></td>
		      	<th align="right" width="20%"><span class="Color5">* </span>真实姓名：</th>
		        <td width="30%"><input type="text" id="dialog_realName" maxlength="20" class="formInput"/></td>
		      </tr>
		      <tr>
		      	<th align="right">性&nbsp;&nbsp;别：</th>
		        <td id="sexHtml"><select id="dialog_sex" class="formSelect"><option value="-1">请选择</option></select></td>
		      	<th align="right">生&nbsp;&nbsp;日：</th>
		        <td><input id="dialog_birthDay" class="Wdate" type="text" onfocus="WdatePicker({skin:'blue'})" readonly="readonly"/></td>
		      </tr>
		      <tr>
		      	<th align="right">状 态：</th>
		        <td id="statusHtml"><select id="dialog_status" class="formSelect"><option value="-1">请选择</option></select></td>
		      	<th align="right"><span class="Color5">* </span>联系电话：</th>
		        <td><input type="text" id="dialog_teleNo" maxlength="20" class="formInput"/></td>
		      </tr>
		      <tr>
		      	<th align="right">证件类型：</th>
		        <td id="personIdTypeHtml"><select id="dialog_personIdType" class="formSelect"><option value="-1">请选择</option></select></td>
		        <th align="right">证件号码：</th>
		        <td><input type="text" id="dialog_personId" maxlength="20" class="formInput"/></td>
		      </tr>
		      <tr>
		      	<th align="right">学&nbsp;&nbsp;历：</th>
		        <td><input type="text" id="dialog_cultDegree" maxlength="20" class="formInput"/></td>
		        <th align="right">职&nbsp;&nbsp;业：</th>
		        <td><input type="text" id="dialog_career" maxlength="20" class="formInput"/></td>
		      </tr>
		      <tr>
		      	<th align="right">所在地区：</th>
		        <td id="areaHtml"><select id="dialog_areaId" class="formSelect"><option value="-1">请选择</option></select></td>
		        <th align="right">电子邮箱：</th>
		        <td><input type="text" id="dialog_email" maxlength="20" class="formInput"/></td>
		      </tr>
		      <tr>
		      	<th align="right">地 址：</th>
		        <td><input type="text" id="dialog_residAddress" maxlength="20" class="formInput"/></td>
		      	<th align="right">邮政编码：</th>
		        <td><input type="text" id="dialog_residZip" maxlength="20" class="formInput"/></td>
		      </tr>
		      <tr>
		      	
		      	<th align="right">会员群组：</th>
		        <td id="groupHtml"></td>
		        
		      	<th align="right">推 荐 人：</th>
		        <td id="parentVipHtml"></td>
		      </tr>
		 </table>
	</div>
	        
<form id="sellCardOrders!save" name="sellCardOrders!save" onsubmit="document.getElementById('submitInput').disabled = true;return true;" action="transa/sellCardOrders!save" method="post">
	<input type="hidden" name="sellCardOrdersDTO.sellLimitAmount" value="1000.00" id="sellLimitAmount"/>
	<input name="sellCardOrdersDTO.keyID" type="hidden" id="keyID" size="20" />
	<input name="sellCardOrdersDTO.rnd" type="hidden" id="rnd" value="35038" />
	<input name="sellCardOrdersDTO.return_EncData" type="hidden" id="return_EncData"  />
	<input type="hidden" name="struts.token.name" value="struts.token" />
<input type="hidden" name="struts.token" value="CT46MQ7PSNLJAUR9E0YQLFH4XCEQFN70" />
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		<tr>
			<th align="right" width="20%">卡BIN：</th>
			<td align="left" width="30%"><select name="sellCardOrdersDTO.cardBin" id="cardBin" class="formSelect2" onchange="secCardBin();">
    <option value="-1"
    >请选择</option>
    <option value="000001">会员卡</option>


</select>
</td>
			<th align="right">面值 ：</th>
			<td align="left" id="cardValueTd"></td>
		</tr>
		<tr>
			<th align="right" width="20%">卡类型：</th>
			<td align="left" width="30%">
				<select name="sellCardOrdersDTO.levelId" id="levelId" class="formSelect2">
    <option value="2">一级会员</option>


</select>

			</td>
			<th align="right" width="20%">是否加入商圈：</th>
			<td align="left" width="30%">
				<select name="sellCardOrdersDTO.groupId" id="groupId" class="formSelect2">
    <option value="-1"
    >不加入</option>


</select>

			</td>
		</tr>
		<tr>
			<th align="right">起始卡号：</th>
			<td align="left">
				<input type="text" name="sellCardOrdersDTO.startNo" id="startNo" maxlength="16" class="formInput2"/>
				<img alt="刷新" src="images/flush.gif" style="cursor:pointer;" onclick="return getStartNo();"/>
			</td>
			<th align="right">数量：</th>
			<td align="left">
				<input type="text" name="sellCardOrdersDTO.num" id="num" maxlength="4" class="formInput" style="width: 50px;"/>
		        <input type="button" class="formButton" onclick="addCardNo();" value="添加" />
		    </td>
		</tr>
	</table>
	<table width="96%" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="10%">卡号</th>
			<th width="10%">卡名称</th>
			<th width="10%">卡类型</th>
			<th width="10%">面额</th>
			<th width="10%">商圈</th>
			<th width="10%">相关操作</th>
		</tr>
	</table>
	<div class="cardNoArea">
		<table id="cardNosTb" class="listTable" cellpadding="0" cellspacing="0" width="98%">
		</table>
	</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		<tr>
			<th align="right" width="20%"><span class="Color5">* </span>售卡总金额：</th>
			<td align="left" width="30%"><input type="text" name="sellCardOrdersDTO.cardPrice" id="cardPrice" maxlength="20" class="formInput2" readonly="readonly"/></td>
			<th align="right" width="20%"><span class="Color5">* </span>支付方式：</th>
			<td align="left" width="30%"><select name="sellCardOrdersDTO.payType" id="payType" class="formSelect2">
    <option value="-1"
    >请选择</option>
    <option value="0" selected="selected">现金</option>
    <option value="1">转账</option>
    <option value="2">支票</option>


</select>
</td>
		</tr>
		<tr>
			<th align="right"><span class="Color5">* </span>实际支付金额：</th>
			<td align="left"><input type="text" name="sellCardOrdersDTO.price" id="price" maxlength="20" onkeyup="verifyIsNaN(this);" onblur="fmtnum(this);" class="formInput2"/></td>
			<th align="right"><span class="Color5">* </span>卡是否激活：</th>
			<td align="left"><select name="sellCardOrdersDTO.isActive" id="isActive" class="formSelect2">
    <option value="-1"
    >请选择</option>
    <option value="0">否</option>
    <option value="1" selected="selected">是</option>


</select>
</td>
		</tr>
		<tr>
			<th align="right"><span class="Color5">* </span>卡是否实名：</th>
			<td align="left"><select name="sellCardOrdersDTO.isRealName" id="isRealName" class="formSelect2">
    <option value="-1"
    >请选择</option>
    <option value="0" selected="selected">否</option>
    <option value="1">是</option>


</select>
</td>
			<th align="right">购卡人：</th>
			<td align="left">
				<input type="text" name="sellCardOrdersDTO.vipName" id="vipName" maxlength="20" class="formInput2" readonly="readonly"/>
				<img alt="查找客户" src="images/search.gif" style="cursor:pointer;" onclick="ajaxVip();"/>
				<input type="hidden" name="sellCardOrdersDTO.vipId" id="vipId"/>
			</td>
		</tr>
		
		<tr>
			<th align="right">描述：</th>
			<td align="left" colspan="3"><textarea id="description" name="sellCardOrdersDTO.descriptionApp" class="formTextarea" cols="45" rows="5" ></textarea></td>
		</tr>
	</table>
	<div class="formTableBottom">
		
			<input type="submit" id="submitInput" class="formButton" value="保 存" onclick="return check();"/>
		
		<input type="button" class="formButton" value="返 回" onclick="go('transa/sellCardOrders!list')"/>
	</div>
	</form>




</body> 
</html>