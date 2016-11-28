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
	<title>活动审核</title>
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
	<script src="js/pubValidate.js"></script>
    <script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
		 var logistIdFlag = true;
		//查询方法
		function query(page) {
			
			var merName = $.trim($("#merName").val());
			var proid = $.trim($("#proid").val());
			var recordstatus = $.trim($("#recordstatus").val());
			 document.getElementById("selectAll").checked=false;
			var params = {
				"merApplyRecordDTO.merid" : merName,
				"merApplyRecordDTO.proid" : proid,
				"merApplyRecordDTO.status" : recordstatus,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "merApplyRecordDTO.page" : page
		    }; 
		   ajaxData("buss/promotion!jsonPageRecordList",params);
		}
		
		
		function selectAll(){
			
		    var selectAll = document.getElementById("selectAll");
			var a = document.getElementsByTagName("input");
			
				for(var i = 0;i<a.length;i++){
					if(a[i].type == "checkbox" && a[i].name!="selectAll"&&selectAll.checked){
						a[i].checked = true;
					}else if(a[i].type == "checkbox" && a[i].name!="selectAll"&&!selectAll.checked){
						a[i].checked = false;
					}
				}
			
		}
		
	  
	  //通过审核
		var passAudit = function() {
		   var url = "buss/promotion!passAudit";
		   var inputObject = document.getElementsByTagName("input");
				var recordIds="";
				for(var i = 0;i<inputObject.length;i++){
					if(inputObject[i].type == "checkbox" && inputObject[i].name!="selectAll"&&inputObject[i].checked){
						recordIds +=  inputObject[i].value+",";
				    }
				}
			if(recordIds==""){
				alert("请选择要审核的商户");
				return false;
			}
			if(confirm("确认要审核通过？")){
				recordIds = recordIds.substring(0,recordIds.length-1)
				
				var params = {
		    		"ids" : recordIds
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
		                	alert("审核通过成功!");
		                	query($("#currPage").text());
		            	}else if(data.ajaxResult == "ajaxfailure"){
		            		alert(data.msgResult);
		            	}else {
		            		alert("审核通过失败!");
		            	}
		    		}
				});
				
			}
			
		}
	  
		//全部通过审核
		var passAllAudit = function() {
		   var url = "buss/promotion!passAllAudit";
		   
			if(confirm("确认要全部审核通过？")){
				var proid = $.trim($("#proid").val());
				var params = {
		    		"id" : proid
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
		                	alert("全部审核通过成功!");
		                	query($("#currPage").text());
		            	}else if(data.ajaxResult == "ajaxfailure"){
		            		alert(data.msgResult);
		            	}else {
		            		alert("全部审核通过失败!");
		            	}
		    		}
				});
				
			}
			
		}
			
			
		 function openRefuseWin(recordId){
			 var recordId = recordId;
			 var recordstatus = $.trim($("#recordstatus").val());
			   var inputObject = document.getElementsByTagName("input");
				var recordIds="";
				if(recordId==null||recordId==""){
					for(var i = 0;i<inputObject.length;i++){
						if(inputObject[i].type == "checkbox" && inputObject[i].name!="selectAll"&&inputObject[i].checked){
							recordIds +=  inputObject[i].value+",";
					    }
					}
					recordIds = recordIds.substring(0,recordIds.length-1)
				}else{
					recordIds=recordId
				}
				
				if(recordIds==""){
					alert("请选择要拒绝/清退的商户");
					return false;
				}
			   $("#descr").val("");
	      		 // 打开对话框   
				$("#dialog-confirm").dialog({
					resizable: true,
					top: 370,
					height:400,
					width:650,
					modal: true,
					buttons: {
						'取消': function() {
							$("#descr").val("");
							$(this).dialog('close');
							
						},
						'确定拒绝': function(){
							$("#dialog-confirm").dialog("close"); 
							refuseAudit(recordIds);
					      	
					      }
					}
				});    
	      		//query(${logisticsDTO.page });
	      }
		 
		 //拒绝/清退
			var refuseAudit = function(recordId) {
			   
			   var recordId = recordId;
			   var recordstatus = $.trim($("#recordstatus").val());
			   var descr = $.trim($("#descr").val());
			   var url = "buss/promotion!refuseAudit";
			   var inputObject = document.getElementsByTagName("input");
				var recordIds="";
				if(recordId==null||recordId==""){
					for(var i = 0;i<inputObject.length;i++){
						if(inputObject[i].type == "checkbox" && inputObject[i].name!="selectAll"&&inputObject[i].checked){
							recordIds +=  inputObject[i].value+",";
					    }
					}
					recordIds = recordIds.substring(0,recordIds.length-1)
				}else{
					recordIds=recordId
				}
				
				if(recordIds==""){
					alert("请选择要拒绝/清退的商户");
					return false;
				}
			
					
					
					var params = {
			    		"ids" : recordIds,
			    		"merApplyRecordDTO.status" : recordstatus,
			    		"merApplyRecordDTO.descr" : descr
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
			                	alert("拒绝(清退)成功!");
			                	query($("#currPage").text());
			            	}else if(data.ajaxResult == "ajaxfailure"){
			            		alert(data.msgResult);
			            	}else {
			            		alert("拒绝(清退)失败!");
			            	}
			    		}
					});
					
				
				
			}
		
		
	</script> 
</head>
<body onload="query(${merApplyRecordDTO.page });">
	<s:hidden name="promotionDTO.proid" id="proid"></s:hidden>
	<s:hidden name="merApplyRecordDTO.status" id="recordstatus"></s:hidden>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="Position">
		当前位置是：营销中心 &gt;&gt; 活动管理 &gt;&gt; 活动审核
	</div>
	
	 <div class="search" align="left" style="padding-top:10px;padding-left:120px;" >	
		<fieldset style="width:70%;height=100px;" >
		<legend>查询条件</legend>
		 	
	    
	
		  <table  cellpadding="0" cellspacing="0" height="40">
		  
			<tr>
			
			    <td>商户名称:</td>
				<td><s:textfield id="merName"  cssClass="formInput" maxlength="30" theme="simple"/></td>
	        	<td>&nbsp;&nbsp;&nbsp;<input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
			</table>
			
		</fieldset>
		</div>
	
		 <div class="search" align="left" style="padding-top:10px;padding-left:120px;" >	
			<fieldset style="width:70%;height=100px;">
			<legend>活动信息</legend>
		    
		
			  <table width="100%" cellpadding="0" cellspacing="0" height="40">
			  
				<tr>
				
				    <td width="20%"><strong>活动名称:</strong></td>
					<td width="30%"><s:property value='promotionDTO.proname'/></td>
		        	<td width="20%"><strong>活动状态:</strong></td>
		        	<td><s:property value='promotionDTO.status'/></td>
				</tr>
				</table>
			</fieldset>
	 </div>
			<table width="80%"  class="listTable" cellpadding="0" cellspacing="0">
			<tr><td width="50%" align="left" colspan="4">&nbsp;&nbsp;&nbsp; <input type="checkbox" name="selectAll" id="selectAll" onclick="selectAll()" >&nbsp;&nbsp;&nbsp;全选</td> 
	 		<td width="50" colspan="4" align="center"><input type="button"  value="全部商户通过" onclick="passAllAudit()"/></td></tr>
            </table>
		
	<table width="80%" id="listTable" class="listTable" cellpadding="0" cellspacing="0">
		
	 	
		<tr>
			<th width="3%">选择</th>
			<th width="10%">商户名称</th>
			<th width="6%">审核状态</th>
			<th width="8%">商户信誉度</th>
			<th width="6%">商户好评率</th>
			<th width="4%">违规分数</th>
			<th width="6%">计划参与商品数量</th>
			<th width="6%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom" style="width:80%;" >
		
		<div class="Fr" id="pageNav" >
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
	   <div width="80%" align="center" style="padding-top:50px;">
			
				
				<s:if test="merApplyRecordDTO.status==1">
				<input type="button" class="formButton" value="审核通过" onclick="passAudit()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" value="审核拒绝" onclick="openRefuseWin('')"/>
			    </s:if>
			    <s:if test="merApplyRecordDTO.status==2">
				<input type="button" class="formButton" value="清退活动" onclick="openRefuseWin('')"/>
			    </s:if>
			    <input type="button" class="formButton" value="返回" onclick="go('buss/promotion!list')"/>
		</div>
		
		<div id="dialog-confirm" style="display: none;" title="拒绝(清退)说明">
		<strong>拒绝(清退)原因说明:</strong>可告知本商户本次 拒绝(清退)原因，以期商户能不断自我提高。拒绝(清退)原因可不填写，直接确认拒绝(清退)。
			<br>
				<s:textarea  name="merApplyRecordDTO.descr" id="descr" maxLength="10000"   cols="80" rows="10" onpropertychange="if(value.length>255) value=value.substr(0,255)" ></s:textarea>
				
			
		</div>
</body> 
</html>