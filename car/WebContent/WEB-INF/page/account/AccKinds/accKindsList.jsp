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
		<title>账户类型管理</title>
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
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script type="text/javascript" src="js/datepicker/WdatePicker.js"></script>
		<script type="text/javascript">
		
		//查询方法
		function query(page) {
			var kindName = $.trim($("#kindName").val());
			var status = $.trim($("#status").val());
			var params = {
				"accKindsDTO.kindName" : kindName,
				"accKindsDTO.status" : status,
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		        "accKindsDTO.page" : page
		    }; 
		   ajaxData("account/acckinds!jsonPageList",params);// !是跳转的意思,相当于？
		}
		function changeFlag(){
			var consoleDlg = $("#dialog-confirm");
			//设定被选中的按钮的Key值
			var flag = $('[name="flag"]:checked').val();
			if(flag=="0"){
				$("#org1").hide();
				$("#mer1").hide();
				$("#org2").hide();
				$("#mer2").hide();	
				consoleDlg.find("#organId").val('');
				consoleDlg.find("#name").val('');
				consoleDlg.find("#merId").val('');
				consoleDlg.find("#merName").val('');
			}
			if(flag=="1"){
				$("#org1").show();
				$("#mer1").hide();
				$("#org2").show();
				$("#mer2").hide();
				consoleDlg.find("#merId").val('');
				consoleDlg.find("#merName").val('');
			}
			if(flag=="2"){
				$("#org2").hide();
				$("#org1").hide();
				$("#mer1").show();
				$("#mer2").show();
				consoleDlg.find("#organId").val('');
				consoleDlg.find("#name").val('');
			}
		};
		$(function() {
			$("#dialog").dialog("destroy");
			$("#add").click(function(){
				$("#dialog_kindName").attr("disabled",false);
				$("#dialog_accSign").attr("disabled",false);
				$("#dialog_saleTimesLimit").attr("disabled",false);
	            $("#dialog_beginDate").attr("disabled",false);
	            $("#dialog_endDate").attr("disabled",false);
	            $("#dialog_status").attr("disabled",false);
	            $("#dialog_withDraType").attr("disabled",false);
	            $("#dialog_transAccType").attr("disabled",false);
	            $("#dialog_ConsType").attr("disabled",false);
	            $("#dialog_rechargeType").attr("disabled",false);
				var actionUrl = "account/acckinds!addUI"; 
			    $.ajax( {   
			        url : actionUrl,
			        dataType : "json",   
			        cache : false,   
			        type : "POST",
			        error : function(textStatus, errorThrown) {   
			    		alert("系统ajax交互错误!");	    
			        }, 
			        success : function(data, textStatus) {
   						var rowData = data.objResult;
						var consoleDlg = $("#dialog-confirm");
						consoleDlg.find("#ajaxOrganId").html("<img alt='查找机构' src='images/search.gif' style='cursor:pointer;' onclick='ajaxOrganId();'/>");
		            	consoleDlg.find("#ajaxMerc").html("<img alt='查找商户' src='images/search.gif' style='cursor:pointer;' onclick='ajaxMerc();'/>");
						//consoleDlg.find("#kindId").html(rowData.kindId+'<input type="hidden" id="dialog_kindId" maxlength="4" class="formInput" value="'+rowData.kindId+'"/>');
						consoleDlg.find("#dialog_kindName").val('');
						consoleDlg.find("#dialog_saleTimesLimit").val('0');
						consoleDlg.find("#organId").attr("disabled",false);
		            	consoleDlg.find("#merId").attr("disabled",false);
		            	consoleDlg.find('[name="flag"][value="0"]').attr("checked","checked");
			            consoleDlg.find('[name="flag"]').attr("disabled",false);
			            consoleDlg.find("#dialog_beginDate").val('');
			            consoleDlg.find("#dialog_endDate").val('');
			            consoleDlg.find("#dialog_status").val('0');
			            changeFlag();
			            // 打开对话框
						$("#dialog-confirm").dialog({
							resizable: true,
							top: 240,
							title:'帐户类型信息添加',
							height:330,
							width:650,
							modal: true,
							buttons: {
								'取消': function() {
									$(this).dialog('close');
								},
								'确定': addData
							}
						}); 
			        }
			    });
			});	
		});
		
		//添加方法
		var addData = function() {
		    var consoleDlg = $("#dialog-confirm"); 
  			
		    
		   // consoleDlg.attr("title","添加"); 			
			//var dialog_kindId = $.trim(consoleDlg.find("#dialog_kindId").val()); 
		    var dialog_kindName = $.trim(consoleDlg.find("#dialog_kindName").val());
		    var dialog_accSign = $.trim(consoleDlg.find("#dialog_accSign").val());
		    var dialog_saleTimesLimit = $.trim(consoleDlg.find("#dialog_saleTimesLimit").val());
		    var flag = $('[name="flag"]:checked').val();
		    var dialog_organId = $.trim(consoleDlg.find("#organId").val());
		    var dialog_merId = $.trim(consoleDlg.find("#merId").val());
		    var dialog_beginDate = $.trim(consoleDlg.find("#dialog_beginDate").val());
		    var dialog_endDate = $.trim(consoleDlg.find("#dialog_endDate").val());
		    var dialog_status = $.trim(consoleDlg.find("#dialog_status").val());
		    var organId=$("#organId").val();
		     if(flag==1){
		    	if(organId.length==0){
					alert("请选择发卡机构!");
			    	consoleDlg.find("#organId").focus();
			    	return false;
		    	}
			}else if(flag==2){
		    	if (dialog_merId.length==0){
					alert("请选择商户!");
			    	consoleDlg.find("#merId").focus();
			    	return false;
				}
		    }
		    
<%--		    if (dialog_kindId==''){--%>
<%--		    	alert("账户编号不能为空!");	 --%>
<%--		    	consoleDlg.find("#dialog_kindId").focus();--%>
<%--		    	return false;--%>
<%--			}--%>
			if (dialog_kindName==''){
		    	alert("账户名称不能为空!");	 
		    	consoleDlg.find("#dialog_kindName").focus();
		    	return false;
			}
			if (dialog_saleTimesLimit==''){
		    	alert("消费次数限制不能为空!");	 
		    	consoleDlg.find("#dialog_saleTimesLimit").focus();
		    	return false;
			}
		    var params = {
		   		"accKindsDTO.kindName" : dialog_kindName,
		   		"accKindsDTO.orgId" : dialog_organId,
		    	"accKindsDTO.merId" : dialog_merId,
		   		"accKindsDTO.accSign" : dialog_accSign,
		   		"accKindsDTO.saleTimesLimit" : dialog_saleTimesLimit,
		   		"accKindsDTO.beginDate" : dialog_beginDate,
		   		"accKindsDTO.endDate" : dialog_endDate,
		   		"accKindsDTO.status" : dialog_status
		    };
		    var actionUrl = "account/acckinds!addSave";
		    
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
		var loadData = function(id,viewFlag) {
		  	var params = {   
		        "accKindsDTO.kindId" : id 
		  	};  
		  	var actionUrl = "account/acckinds!editUI"; 
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
					//consoleDlg.find("#kindId").html(rowData.kindId+'<input type="hidden" id="dialog_kindId" maxlength="1" class="formInput"/>');
					consoleDlg.find("#dialog_kindId").val(rowData.kindId);
					consoleDlg.find("#dialog_kindName").val(rowData.kindName);
					consoleDlg.find("#dialog_accSign").val(rowData.accSign);
					consoleDlg.find("#dialog_kindName").attr("disabled",false);
					consoleDlg.find("#dialog_accSign").attr("disabled",false);
					consoleDlg.find("#dialog_saleTimesLimit").val(rowData.saleTimesLimit);
					consoleDlg.find("#organId").val(rowData.orgId);
					consoleDlg.find("#name").val(rowData.orgName);
					consoleDlg.find("#merId").val(rowData.merId);
					consoleDlg.find("#merName").val(rowData.merName);
					consoleDlg.find("#dialog_withDraType").val(rowData.withDraType);
					consoleDlg.find("#dialog_transAccType").val(rowData.transAccType);
					consoleDlg.find("#dialog_consType").val(rowData.consType);
					consoleDlg.find("#dialog_rechargeType").val(rowData.rechargeType);
										
										
					
	            	var flag =0;
	            	if(rowData.orgId==null&&rowData.merId==null){
	            		flag =0;
		            }
		            if(rowData.orgId!=null){
		            	flag=1;    	
		            	consoleDlg.find("#name").val(rowData.orgName);
	            	}
	            	if(rowData.merId!=null){
		            	flag=2;
		            	consoleDlg.find("#merName").val(rowData.merName);
	            	}
	            	consoleDlg.find('[name="flag"][value='+flag+']').attr("checked","checked");
		            consoleDlg.find('[name="flag"]').attr("disabled",true);
		            consoleDlg.find("#organId").attr("disabled",true);
	            	consoleDlg.find("#merId").attr("disabled",true);
	            	if(rowData.beginDate!=null){
	            		consoleDlg.find("#dialog_beginDate").val(rowData.beginDate);
	            	}else{
	            		consoleDlg.find("#dialog_beginDate").val("");
	            	}
	            	if(dialog_endDate.length>0){
	            		consoleDlg.find("#dialog_endDate").val(rowData.endDate);
	            	}else{
	            		consoleDlg.find("#dialog_endDate").val("");
	            	}
		            
		            consoleDlg.find("#dialog_status").val(rowData.status);
		            consoleDlg.find("#dialog_withDraType").val(rowData.withDraType); 
		            consoleDlg.find("#dialog_transAccType").val(rowData.transAccType);
		            consoleDlg.find("#dialog_consType").val(rowData.consType);
		            consoleDlg.find("#dialog_rechargeType").val(rowData.rechargeType);
		            changeFlag();
		            if(viewFlag == 1){
		            	if(rowData.orgId!=null){
			            	flag=1;    	
			            	consoleDlg.find("#name").attr("disabled",true);
		            	}
		            	if(rowData.merId!=null){
			            	flag=2;
			            	consoleDlg.find("#merName").attr("disabled",true);
			            	
		            	}

		            	consoleDlg.find("#ajaxOrganId").html("");
		            	consoleDlg.find("#ajaxMerc").html("");
						consoleDlg.find("#dialog_kindName").attr("disabled",true);
						consoleDlg.find("#dialog_accSign").attr("disabled",true);
						consoleDlg.find("#dialog_saleTimesLimit").attr("disabled",true);
			            consoleDlg.find("#dialog_beginDate").attr("disabled",true);
			            consoleDlg.find("#dialog_endDate").attr("disabled",true);
			            consoleDlg.find("#dialog_status").attr("disabled",true);
			            consoleDlg.find("#dialog_withDraType").attr("disabled",true);
			            consoleDlg.find("#dialog_transAccType").attr("disabled",true);
			            consoleDlg.find("#dialog_consType").attr("disabled",true);
			            consoleDlg.find("#dialog_rechargeType").attr("disabled",true);
		            	// 打开对话框
						$("#dialog-confirm").dialog({
							resizable: true,
							top: 370,
							height:330,
							width:650,
							modal: true,
							title:"账户类型信息查看",
							buttons: {
								'取消': function() {
									$(this).dialog('close');
								}
							}
						});    
		            }else{
		            	if(rowData.orgId!=null){
			            	flag=1;    	
			            	consoleDlg.find("#name").attr("disabled",false);
		            	}
		            	if(rowData.merId!=null){
			            	flag=2;
			            	consoleDlg.find("#dialog_kindName").attr("disabled",false);
			            	consoleDlg.find("#dialog_accSign").attr("disabled",false);
			            	consoleDlg.find("#merName").attr("disabled",false);
		            	}
		            	consoleDlg.find("#ajaxOrganId").html("<img alt='查找机构' src='images/search.gif' style='cursor:pointer;' onclick='ajaxOrganId();'/>");
		            	consoleDlg.find("#ajaxMerc").html("<img alt='查找商户' src='images/search.gif' style='cursor:pointer;' onclick='ajaxMerc();'/>");
		            	consoleDlg.find("#dialog_kindName").attr("disabled",true);
						consoleDlg.find("#dialog_accSign").attr("disabled",true);
						consoleDlg.find("#dialog_saleTimesLimit").attr("disabled",false);
			            consoleDlg.find("#dialog_beginDate").attr("disabled",false);
			            consoleDlg.find("#dialog_endDate").attr("disabled",false);
			            consoleDlg.find("#dialog_status").attr("disabled",false);
			            consoleDlg.find("#dialog_withDraType").attr("disabled",false);
			            consoleDlg.find("#dialog_transAccType").attr("disabled",false);
			            consoleDlg.find("#dialog_consType").attr("disabled",false);
			            consoleDlg.find("#dialog_rechargeType").attr("disabled",false);
		            	// 打开对话框
						$("#dialog-confirm").dialog({
							resizable: true,
							top: 370,
							height:330,
							width:650,
							modal: true,
							title:"账户类型信息修改",
							buttons: {
								'取消': function() {
									$(this).dialog('close');
								},
								'确定': updateData
							}
						});    
			        }

					
		            
		        }   
		    });
		};


		var validateSaleTimesLimit = function(){
			var saleTimesLimit = $("#dialog_saleTimesLimit").val();
			var saleTimesLimitreg = /^[0-9]*$/;
			if(!saleTimesLimitreg.test(saleTimesLimit)){
				alert("消费次数限制只能为数字!");	
				return false;
			}
		}
		
		//修改方法
		var updateData = function() {
		    var consoleDlg = $("#dialog-confirm");  			
			var dialog_kindId =  $.trim(consoleDlg.find("#dialog_kindId").val());
		    var dialog_kindName = $.trim(consoleDlg.find("#dialog_kindName").val());
		    var dialog_accSign = $.trim(consoleDlg.find("#dialog_accSign").val());
		    var dialog_saleTimesLimit = $.trim(consoleDlg.find("#dialog_saleTimesLimit").val());
		    var dialog_organId = $.trim(consoleDlg.find("#organId").val());
		    var dialog_merId = $.trim(consoleDlg.find("#merId").val());
		    var dialog_beginDate = $.trim(consoleDlg.find("#dialog_beginDate").val());
		    var dialog_endDate = $.trim(consoleDlg.find("#dialog_endDate").val());
		    var dialog_status = $.trim(consoleDlg.find("#dialog_status").val());
		    var dialog_withDraType = $.trim(consoleDlg.find("#dialog_withDraType").val());
		    var dialog_transAccType = $.trim(consoleDlg.find("#dialog_transAccType").val());
		    var dialog_consType = $.trim(consoleDlg.find("#dialog_consType").val());
		    var dialog_rechargeType = $.trim(consoleDlg.find("#dialog_rechargeType").val());

<%--		    if (dialog_kindId==''){--%>
<%--		    	alert("账户编号不能为空!");	 --%>
<%--		    	consoleDlg.find("#dialog_kindId").focus();--%>
<%--		    	return false;--%>
<%--			}--%>
			
			if (dialog_kindName==''){
		    	alert("账户名称不能为空!");	 
		    	consoleDlg.find("#dialog_kindName").focus();
		    	return false;
			}

			var saleTimesLimitreg = /^[0-9]*$/;
			if (dialog_saleTimesLimit==''){
		    	alert("消费次数限制不能为空!");	 
		    	consoleDlg.find("#dialog_saleTimesLimit").focus();
		    	return false;
			}else if(!saleTimesLimitreg.test(dialog_saleTimesLimit)){
				alert("消费次数限制只能为数字!");	
				consoleDlg.find("#dialog_saleTimesLimit").focus();
				return false;
			}
			
		    var params = {
		   		"accKindsDTO.kindId" : dialog_kindId,
		   		"accKindsDTO.kindName" : dialog_kindName,
		   		"accKindsDTO.orgId" : dialog_organId,
		    	"accKindsDTO.merId" : dialog_merId,
		   		"accKindsDTO.accSign" : dialog_accSign,
		   		"accKindsDTO.saleTimesLimit" : dialog_saleTimesLimit,
		   		"accKindsDTO.beginDate" : dialog_beginDate,
		   		"accKindsDTO.endDate" : dialog_endDate,
		   		"accKindsDTO.status" : dialog_status,
		   		"accKindsDTO.withDraType" : dialog_withDraType,
		   		"accKindsDTO.transAccType" : dialog_transAccType,
		   		"accKindsDTO.consType" : dialog_consType,
		   		"accKindsDTO.rechargeType" : dialog_rechargeType	   		
		    };
		    var actionUrl = "account/acckinds!editSave";   
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
		    });   
		}; 
	</script>
	</head>
	<body onload="query(${accKindsDTO.page });">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 账户管理 &gt;&gt; 账户类型管理
		</div>
		<div id="dialog-confirm" style="display: none;" title="添加">
			<jsp:include page="/WEB-INF/page/base/terminals/mercHelps.jsp" flush="trfalse"></jsp:include>
			<jsp:include page="/WEB-INF/page/base/organs/organHelp.jsp" flush="true"></jsp:include>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">
				<tr>
					<th align="right" >
						<span class="Color5">* </span>级别标志：
					</th>
					<td colspan="1">
						<s:radio name="flag" id="flag" list="#request.accDeptTypeValues"
							listKey="key" listValue="value" value="0" theme="simple"
							onclick="changeFlag()" />
						
					</td>
					<td colspan="1"  align="right">
						<div id="org1">
							<span class="Color5">* </span>发卡机构：
						</div>
						<div id="mer1" style="display: none">
							<span class="Color5">* </span>商 户：
						</div>
					</td>
					<td colspan="1">	
					<!--  
						<div id="org2">
							<s:textfield name="accKindsDTO.orgName" id="name" maxlength="20"
								cssClass="formInput" theme="simple" />
							<s:hidden name="accKindsDTO.orgId" id="organId"></s:hidden>
							<img alt="查找机构" src="images/search.gif" style="cursor: pointer;"
								onclick="ajaxOrganId();" />
						</div>
						<div id="mer2" style="display: none">
							<s:textfield name="accKindsDTO.merName" id="merName"
								maxlength="20" cssClass="formInput" theme="simple" />
							<s:hidden name="accKindsDTO.merId" id="merId"></s:hidden>
							<img alt="查找商户" src="images/search.gif" style="cursor: pointer;"
								onclick="ajaxMerchantsId();" />
						</div>
						-->
						<div  id="org2">
				        	<s:textfield name="accKindsDTO.orgName" id="name"  maxlength="20" cssClass="formInput"  theme="simple" readonly="true"/>
				        	 <s:hidden name="accKindsDTO.orgName" id="organId"></s:hidden>
				        	 <span id="ajaxOrganId"><img alt="查找机构" src="images/search.gif" style="cursor:pointer;" onclick="ajaxOrganId();"/></span>
				        </div >
				        <div  id="mer2"  style="display:none">
				        	<s:textfield name="accKindsDTO.merName" id="merName"  maxlength="20" cssClass="formInput"  theme="simple" readonly="true"/>
				        	 <s:hidden name="accKindsDTO.merName" id="merId"></s:hidden>
				        	 <span id="ajaxMerc"><img alt="查找商户" src="images/search.gif" style="cursor:pointer;" onclick="ajaxMerc();"/></span>
				        </div>
					</td>
				</tr>
				<tr>
					<th align="right" width="20%">
						<span class="Color5">* </span>账户类型名称：
					</th>    
					<td width="30%">
						<input type="hidden" id="dialog_kindId" maxlength="1" class="formInput"/>
						<input type="text" id="dialog_kindName" maxlength="20"
							class="formInput"/>
					</td>
					<th align="right">
						<span class="Color5">* </span>账户类型标志：
					</th>
					<td>  
						<s:select name="accKindsDTO.accSign" id="dialog_accSign"
							list="#request.signValues" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple"/>
					</td>
				</tr>
				
			    <tr>
					<th align="right">
						<span class="Color5">* </span>是否可提现：
						<br />
					</th>
					<td>  
						<s:select name="accKindsDTO.withDraType" id="dialog_withDraType"
							list="#request.withDraType" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />
					</td>
					<th align="right">
						<span class="Color5">* </span>是否可转账：
					</th>
					<td>  
						<s:select name="accKindsDTO.transAccType" id="dialog_transAccType"
							list="#request.transAccType" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />
					</td>
				</tr>
				
				<tr>
					<th align="right">
						<span class="Color5">* </span>是否允许消费：
						<br />
					</th>
					<td>  
						<s:select name="accKindsDTO.consType" id="dialog_consType"
							list="#request.consType" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />
					</td>
					<th align="right">
						<span class="Color5">* </span>是否允许充值：
					</th>
					<td>  
						<s:select name="accKindsDTO.rechargeType" id="dialog_rechargeType"
							list="#request.rechargeType" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />
					</td>
				</tr>	
				
				<tr>
					<th align="right">
						<span class="Color5">* </span>日消费次数限制：
						<br />
						(0为不限制)
					</th>
					<td> 
						<input type="text" id="dialog_saleTimesLimit" maxlength="2"
							class="formInput" onblur="validateSaleTimesLimit();"/>
					</td>
					<th align="right">
						<span class="Color5">* </span>状态：
					</th>
					<td colspan="3">  
						<s:select name="accKindsDTO.accSign" id="dialog_status"
							list="#request.statusValues" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />
					</td>
				</tr>
				<tr>
					<th align="right">
						有效期：
					</th>
					<td colspan="3">
						<input name="accKindsDTO.beginDate" id="dialog_beginDate"
							class="Wdate" type="text"
							onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
							readonly="readonly" />   
						-
						<input name="accKindsDTO.endDate" id="dialog_endDate"
							class="Wdate" type="text"
							onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
							readonly="readonly" />
					</td>
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
						账户名称:
					</td>
					<td>
						<s:textfield id="kindName" name="accKindsDTO.kindName"
							cssClass="formInput" maxlength="20" theme="simple" />
					</td>
					<td>状态:</td>
					<td>
						<s:select name="status" id="status"
								list="#request.statusValues" listKey="key" listValue="value"
								cssClass="formInput"
								theme="simple" />
					</td>
					<td>
						<input type="button" class="formButton" onclick="query();"
							value="查 询" />
					</td>
				</tr>
			</table>
		</div>

		<table width="96%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="3%">
					序号
				</th>
				<th width="20%">
					<a name="kindName" class="sort">账户类型名称</a>
				</th>
				<th width="20%">
					<a name="kindId" class="sort">账户类型编号</a>
				</th>
				<th width="20%">
					<a name="accSign" class="sort">账户类型标志</a>
				</th>
				<th width="10%">
					<a name="status" class="sort">状态</a>
				</th>
				<th width="10%">
					<a name="updateTime" class="sort">更新时间</a>
				</th>
				<th width="5%">
					相关操作
				</th>
			</tr>
		</table>
		<%-- <div class="listBottom">
			<div class="Fl">
				<input type="button" id="add" class="formButton" value="添加" />
			</div>
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
		</div>--%>
	</body>
</html>