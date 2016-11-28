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
	<title>换卡管理</title>
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
		var cardNo = $.trim($("#cardNo").val());
		var checkStatus = $.trim($("#checkStatus").val());
	//json数据传输
		var params = {
			"cardChangDTO.cardNo" : cardNo,
	        "cardChangDTO.checkStatus" : checkStatus,
	        "orderProperty" : $("#orderProperty").val(),
	        "orderDirection" : $("#orderDirection").val(),
	        "cardChangDTO.page" : page
	    }; 
	   ajaxData("card/change!jsonPageList",params);
	}
	//加载账户信息的方法
	function loadInfo(cardNo){
		var tb =$("#accIdTb");
      	tb.find('tr:eq(0)').nextAll().remove();  	
		var params = {
				        "cardChangDTO.cardNo" : cardNo
				    }; 
		 $.ajax({
			  url : "card/change!loadInfo", 
	          type : "POST",  
	          data : params, 
	          dataType:"json",
			  success : function(data){
			  	if (data.length>0){
	        		for(var i=0;i<data.length;i++) {
       					var tr = $("<tr></tr>");
       					var html = "";
       					html += "<td>"+data[i].accId+"</td>";
       					html += "<td>"+data[i].accTypeName+"</td>";
       					html += "<td>"+data[i].balPoint+"</td>";
       				    html+="<input type='hidden' name='cardChangDTO.accIds'  value='"+data[i].accId+"'/>";
       					html+="<input type='hidden' name='cardChangDTO.typeNames' value='"+data[i].accTypeName+"'/>";
       					html+="<input type='hidden' name='cardChangDTO.balances' value='"+data[i].balPoint+"' />";
       					tr.html(html);
	          			tb.append(tr);
       				}
       			}else{
       			 var tr = $("<tr></tr>");
       			 tb.find('tr:eq(0)').nextAll().remove();  
       			}
			  },
			   error:function(){
	             alert("系统ajax交互错误!");
	          }
		 });
	}
	function loadData(id,viewFlag){
	
	  var params ={
	      "cardChangDTO.changId": id
	  };
	  
	  var actionUrl = "card/change!loadChangeById";
	 
	  $.ajax({
	     url  : actionUrl,
	     data : params,
	     dataType :"json",
	     cache : false,
	     type : "POST",
	     error :function(textStatus,errorThrown){
	       alert("系统ajax交互错误!");
	     },
	     success : function(data, textStatus){
	     
	       var consoleDlg =$("#dialog-confirm");
	       if(viewFlag ==1){
	          if(data.obj.checkStatus ==0){
	            consoleDlg.find("#dialog_checkStatus").val("未审核");
	          }else if(data.obj.checkStatus==1){
	            consoleDlg.find("#dialog_checkStatus").val("审核通过")
	          }else{
	            consoleDlg.find("#dialog_checkStatus").val("审核不通过");
	          }
	          if(data.obj.chagReason ==0){
	            consoleDlg.find("#dialog_chagReason").val("损坏");
	          }else if(data.obj.chagReason==1){
	            consoleDlg.find("#dialog_chagReason").val("挂失")
	          }else{
	            consoleDlg.find("#dialog_chagReason").val("其他");
	          }
	          
	          
	          consoleDlg.find("#dialog_oldNo").val(data.obj.oldNo);
	          consoleDlg.find("#dialog_newNo").val(data.obj.newNo);
	          consoleDlg.find("#dialog_merName").val(data.obj.merName);
	          consoleDlg.find("#dialog_accId").val(data.obj.accId);
	          consoleDlg.find("#dialog_balance").val(data.obj.balance);
	          consoleDlg.find("#dialog_descr").val(data.obj.descr);
	          consoleDlg.find("#dialog_checkDesc").val(data.obj.checkDesc);
	          consoleDlg.find("#dialog_changId").val(data.obj.changId);
	          consoleDlg.find("#dialog_memIdNum").val(data.obj.memIdNum);
	          consoleDlg.find("#dialog_autitTime").val(data.obj.auditTime);
	          consoleDlg.find("#dialog_auditt").val(data.obj.auditt);
	          consoleDlg.find("#dialog_proposer").val(data.obj.proposer);
	          consoleDlg.find("#dialog_operTime").val(data.obj.operTime);
	          consoleDlg.find("#dialog_descr").attr("disabled",true);
			  consoleDlg.find("#dialog_descAudit").attr("disabled",true);
	          consoleDlg.find("#hh").show();
	          loadInfo(data.obj.oldNo);
	          $("#dialog-confirm").dialog({
	              resizable :true,
	              top :370,
	              height:330,
	              width :650,
	              modal:true,
	              buttons: {
	                  '取消':function(){
	                    $(this).dialog('close');
	                  }
	              }
	          });
	       }else{
	        if(data.obj.checkStatus==0){
	         consoleDlg.find("#dialog_checkStatus").val("未审核");
	        }else if(data.obj.checkStatus==1){
	         consoleDlg.find("#dialog_checkStatus").val("审核通过"); 
	        }else{
	         consoleDlg.find("#dialog_checkStatus").val("审核不通过"); 
	      }
	       if(data.obj.chagReason ==0){
	            consoleDlg.find("#dialog_chagReason").val("损坏");
	          }else if(data.obj.chagReason==1){
	            consoleDlg.find("#dialog_chagReason").val("挂失")
	          }else{
	            consoleDlg.find("#dialog_chagReason").val("其他");
	          }
	          consoleDlg.find("#dialog_oldNo").val(data.obj.oldNo);
	          consoleDlg.find("#dialog_newNo").val(data.obj.newNo);
	          consoleDlg.find("#dialog_merName").val(data.obj.merName);
	          consoleDlg.find("#dialog_accId").val(data.obj.accId);
	          consoleDlg.find("#dialog_balance").val(data.obj.balance);
	          consoleDlg.find("#dialog_descr").val(data.obj.descr);
	          consoleDlg.find("#dialog_checkDesc").val(data.obj.checkDesc);
	          consoleDlg.find("#dialog_changId").val(data.obj.changId);
	          consoleDlg.find("#dialog_memIdNum").val(data.obj.memIdNum);
	          consoleDlg.find("#dialog_proposer").val(data.obj.proposer);
	          consoleDlg.find("#dialog_operTime").val(data.obj.operTime);
	          consoleDlg.find("#dialog_autitTime").val(data.obj.auditTime);
	          consoleDlg.find("#dialog_auditt").val(data.obj.auditt);
	          consoleDlg.find("#dialog_descr").attr("disabled",true);
			  consoleDlg.find("#dialog_descAudit").attr("disabled",false);
	           loadInfo(data.obj.oldNo);
	          consoleDlg.find("#hh").hide();
	          	$("#dialog-confirm").dialog({
					 
						resizable: true,
						top: 370,
						height:330,
						width:650,
						modal: true,
						buttons: {
							'取消': function() {
								$(this).dialog('close');
							},
							'审核不通过': auditNotPass,
					         '审核通过': auditPass
						}
					});  
	     }
	     }
	  });
	}	
	 var auditPass = function(){
			  if(confirm("是否确认审核通过？")){
				audit(1);
			  }
		    }
		
		var auditNotPass = function(){
			if(confirm("是否确认审核不通过？")){
				audit(2);
			}
		}
		var audit = function(status) {
		
		    var consoleDlg = $("#dialog-confirm"); 
		    
		  	var accIds=document.getElementsByName("cardChangDTO.accIds");
		  	var typeNames=document.getElementsByName("cardChangDTO.typeNames");
		  	var balances=document.getElementsByName("cardChangDTO.balances");
		  	var accIdsStr="";
		  	var typeNamesStr="";
		  	var balancesStr="";
		  	for(var i=0;i<accIds.length;i++){
		  		accIdsStr+=";"+accIds[i].value;
		  	}
		  	for(var i=0;i<typeNames.length;i++){
		  		typeNamesStr+=";"+typeNames[i].value;
		  	}
		  	for(var i=0;i<balances.length;i++){
		  		balancesStr+=";"+balances[i].value;
		  	}
		  	
		    var dialog_descr = $.trim(consoleDlg.find("#dialog_descr").val());
		    var dialog_changId = $.trim(consoleDlg.find("#dialog_changId").val());
		    var params = {
		    	"cardChangDTO.oldNo ":$("#dialog_oldNo").val(),
		   		"cardChangDTO.changId ": dialog_changId,
		   		"cardChangDTO.descr" : dialog_descr,
		        "cardChangDTO.checkStatus" : status,
		        "accIdsStr" : accIdsStr.substring(1),
		        "typeNamesStr" : typeNamesStr.substring(1),
		        "balancesStr" : balancesStr.substring(1)
		    };
		 
		     var actionUrl = "card/change!audit";   
		        	
		    $.ajax({   
		  
		        url : actionUrl,   
		        data : params,   
		        //dataType : "json",
		        cache : false, 
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误!");  
		        },  
		        success : function(data, textStatus) {   
		            if (data == "ajaxsuccess") {   
						consoleDlg.dialog("close");  
		                alert("操作成功!");
		                query($("#currPage").text());	                
		           	} else if(data== "ajaxfailure") {
		               // alert(data.msgResult);
		            }else if(data=="noSuccess"){
		            	alert("旧卡状态是挂失状态，无法操作！");
		            }else if(data=="noSearch"){
		            	alert("旧卡未找到，无法操作！");
		            }else {
		            	alert("操作失败!");
		            }
		        }
		    });
		    
      }
	</script> 
</head>
<body onload="query(${cardChangDTO.page});">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 卡信息管理 &gt;&gt; 换卡管理
	</div>
	<div id="dialog-confirm" style="display:none;" title="查看">
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">

				<tr>
					<th align="right" width="20%">
						<span class="Color5">* </span>旧卡号：
					</th>
					   <td width="30%">
						<input type="text" id="dialog_oldNo" maxlength="20"
							class="formInput" readonly />
						<s:hidden id="dialog_changId" />
					  </td>
				   <th align="right">
						<span class="Color5">* </span>新卡号：
				  </th>
					<td>
						<input type="text" id="dialog_newNo" maxlength="20"
							class="formInput" readonly />
					</td>
			  </tr>

			 <tr>
					<th align="right" width="20%">
						<span class="Color5">* </span>持卡人：
					</th>
					<td width="30%">
						<input type="text" id="dialog_merName" maxlength="20"
							class="formInput" readonly />
					</td>
					<th align="right">
						<span class="Color5">* </span>身份证号：
					</th>

					<td>
						<input type="text" id="dialog_memIdNum" maxlength="20"
							class="formInput" readonly />
					</td>
			  </tr>
			
	        <tr>
					<th align="right">
						换卡原因：
					</th>
					<td width="30%">
						<input type="text" id="dialog_chagReason" maxlength="20"
							class="formInput" readonly />
					</td>
					<th align="right" width="20%">
						<span class="Color5">* </span>审核状态：
					</th>
					<td width="30%">
						<input type="text" id="dialog_checkStatus" maxlength="20"
							class="formInput" readonly />
					</td>
			</tr>
			
			 <tr>
					<th align="right" width="20%">
						<span class="Color5">* </span>申请操作人：
					</th>
					<td width="30%">
						<input type="text" id="dialog_proposer" maxlength="20"
							class="formInput" readonly />
					</td>
					<th align="right">
						<span class="Color5">* </span>申请时间：
					</th>

					<td>
						<input type="text" id="dialog_operTime" maxlength="20"
							class="formInput" readonly />
					</td>
			  </tr>
			  
			  <tr id="hh">
					
					<th align="right">
						<span class="Color5">* </span>审核人：
					</th>

					<td>
						<input type="text" id="dialog_auditt" maxlength="20"
							class="formInput" readonly />
					</td>
					
					<th align="right" width="20%">
						<span class="Color5">* </span>审核时间：
					</th>
					<td width="30%">
						<input type="text" id="dialog_autitTime" maxlength="20"
							class="formInput" readonly />
					</td>
			</tr>
		<tr>
	      		<th align="right">账户信息：</th>
	      		<td colspan="3">
	      			<table id="accIdTb" width="96%" class="listTable" cellpadding="0" cellspacing="0">
	      				<tr>
	      					<th>账户号</th>
	      					<th>账户类型</th>
	      					<th>余额</th>
	      				</tr>
	      				<s:if test="#request.accIds!=null&&#request.accIds.size>0">
				    		<s:iterator value="#request.accIds" >
				    		<tr>
				    			<td><s:property value="accId"/></td>
				    			<td><s:property value="typeId"/></td>
				    			<td><s:property value="balance"/></td>
				    		</tr>
				    		</s:iterator>
				    	</s:if>
	      			</table>
	      		</td>
	     </tr>
				<tr>
					<th align="right">
						备注：
					</th>
					<td align="left" colspan="3">
						<textarea id="dialog_descr" class="formTextarea" cols="50" style="resize:none;"
							rows="5"></textarea>
					</td>
				</tr>
				<tr>
					<th align="right">
						解冻描述：
					</th>
					<td align="left" colspan="3">
						<textarea id="dialog_descAudit" class="formTextarea" cols="45"
							rows="5"></textarea>
					</td>
				</tr>
	</table>
	</div>
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>卡号:</td>
				<td><s:textfield id="cardNo" name="cardChangDTO.cardNo" cssClass="formInput" maxlength="20" theme="simple"/></td>
				
				<td>审核状态:</td>
				<td>
                    <s:select name="cardChangDTO.checkStatus" id="checkStatus"
							list="#request.condValues" headerKey="-1" headerValue="全部"
							listKey="key" listValue="value" cssClass="formSelect"
							theme="simple" />				
				</td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr> 
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%"><a name="oldNo" class="sort">旧卡号</a></th>
			<th width="10%"><a name="newNo" >新卡号</a></th>
			<th width="10%"><a name="proposer">申请人</a></th>
			<th width="5%"><a name="operTime">申请时间</a></th>
			<th width="8%"><a name="auditt">审核人</a></th>
			<th width="5%"><a name=auditTime>审核时间</a></th>
			<th width="5%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<my:permission key='sy-2110-02' value='换卡信息添加'>
				<input type="button" class="formButton" value="添加" onclick="go('card/change!addUI')"/>
			</my:permission>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>