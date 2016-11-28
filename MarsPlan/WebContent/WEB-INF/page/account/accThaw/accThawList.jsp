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
		<title>账户管理</title>
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
		<script type="text/javascript">
		
		//查询方法
		function query(page) {
			var cardNo = $.trim($("#cardNo").val());
			var accId = $.trim($("#accId").val());
            var accTId =$("#accTId").val(); 
            var checkStatus =$("#checkStatus").val();
            
			var params = {
				"accRecordDTO.cardNo" : cardNo,
		        "accRecordDTO.accId" : accId,
		        "accRecordDTO.accTId" : accTId,
		        "accRecordDTO.checkStatus" :checkStatus,
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		        "accRecordDTO.page" : page
		    }; 
		    
		   ajaxData("account/accThaw!jsonPageList",params);// !是跳转的意思,相当于？
		}
		//加载页面
			var loadData = function(id,viewFlag) {
		
		  	var params = {   
		  	
		        "accRecordDTO.ftAccId" : id 
		  	};  
		  	  
		  	var actionUrl = "account/accThaw!loadThawById"; 
		  		 
		  	
		    $.ajax( {   
		        url : actionUrl,
		        data : params,   
		        dataType : "json",   
		        cache : false,   
		        type : "POST",
		        error : function(textStatus, errorThrown) { 
		        
		    		alert("系统ajax交互错误!");	    
		        },
		        //data  json封装的对象 回调的返回值，
		        // textStatus 是成功或者失败
		        
		        success : function(data, textStatus) {
		          
		          var consoleDlg = $("#dialog-confirm");
		            
		          if(viewFlag == 1){
		         	
		               if(data.obj.checkStatus==0){
					      consoleDlg.find("#dialog_checkStatus").val("未审核");
					   }
					   else if(data.obj.checkStatus==1){
					      consoleDlg.find("#dialog_checkStatus").val("审核通过"); 
					   }else{
					      consoleDlg.find("#dialog_checkStatus").val("审核不通过"); 
					   }
					consoleDlg.find("#dialog_cardNo").val(data.obj.cardNo);
					
					consoleDlg.find("#dialog_operTime").val(data.obj.operTime);
					consoleDlg.find("#dialog_proposer").val(data.obj.proposer);
					consoleDlg.find("#dialog_descApp").val(data.obj.descApp);
					consoleDlg.find("#dialog_auditTime").val(data.obj.auditTime);
					consoleDlg.find("#dialog_auditt").val(data.obj.auditt);
					consoleDlg.find("#dialog_descAudit").val(data.obj.descAudit);
					consoleDlg.find("#dialog_ftAccId").val(data.obj.ftAccId);
					consoleDlg.find("#dialog-accTId").val(data.obj.accTId);
					consoleDlg.find("#dialog-accId").val(data.obj.accId);
					consoleDlg.find("#dialog_descApp").attr("disabled",true);
					consoleDlg.find("#dialog_descAudit").attr("disabled",true);
					consoleDlg.find("#hh").show();
		             // 打开对话框
					$("#dialog-confirm").dialog({
						resizable: true,
						top: 370,
						height:330,
						width:650,
						modal: true,
						buttons: {
							'取消': function() {
								$(this).dialog('close');
							}
						}
					}); 
		            }else{
					   if(data.obj.checkStatus==0){
					      consoleDlg.find("#dialog_checkStatus").val("未审核");
					   }
					   else if(data.obj.checkStatus==1){
					      consoleDlg.find("#dialog_checkStatus").val("审核通过"); 
					   }else{
					      consoleDlg.find("#dialog_checkStatus").val("审核不通过"); 
					   }
					  
					consoleDlg.find("#dialog_cardNo").val(data.obj.cardNo);
					
					consoleDlg.find("#dialog_operTime").val(data.obj.operTime);
					
					consoleDlg.find("#dialog_proposer").val(data.obj.proposer);
					consoleDlg.find("#dialog_descApp").val(data.obj.descApp);
					consoleDlg.find("#dialog_auditTime").val(data.obj.auditTime);
					consoleDlg.find("#dialog_auditt").val(data.obj.auditt);
					consoleDlg.find("#dialog_descAudit").val(data.obj.descAudit);
					consoleDlg.find("#dialog_ftAccId").val(data.obj.ftAccId);
					consoleDlg.find("#dialog-accTId").val(data.obj.accTId);
					consoleDlg.find("#dialog-accId").val(data.obj.accId);
					consoleDlg.find("#dialog_descApp").val(data.obj.descApp);
					consoleDlg.find("#dialog_descAudit").attr("disabled",false);
					consoleDlg.find("#dialog_descApp").attr("disabled",true);
					consoleDlg.find("#hh").hide();
		             // 打开对话框
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
		   //修改
			var audit = function(status) {
		    var consoleDlg = $("#dialog-confirm"); 
		  
		    var dialog_descAudit = $.trim(consoleDlg.find("#dialog_descAudit").val());
		    var dialog_ftAccId = $.trim(consoleDlg.find("#dialog_ftAccId").val());
		    var params = {
		    	"accRecordDTO.accId ":$("#dialog-accId").val(),
		    	"accRecordDTO.accTId ":$("#dialog-accTId").val(),
		   		"accRecordDTO.ftAccId ": dialog_ftAccId,
		   		"accRecordDTO.descAudit" : dialog_descAudit,
		        "accRecordDTO.checkStatus" : status
		    };
		 
		     var actionUrl = "account/accThaw!audit";   
		        	
		    $.ajax({   
		  
		        url : actionUrl,   
		        data : params,   
		        dataType : "json",
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
		            	alert("帐户不是冻结状态，无法操作！");
		            }else if(data=="noSearch"){
		            	alert("帐户未找到，无法操作！");
		            }else {
		            	alert("操作失败!");
		            }
		        }
		    });
		    
      }
	</script>
	</head>
	<body onload="query(${accRecordDTO.page });">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 账户管理 &gt;&gt; 账户解冻
		</div>

		<div id="dialog-confirm" style="display: none;" title="查看">
			<!-- 
			<jsp:include page="/WEB-INF/page/base/merchants/merchantsHelp.jsp"></jsp:include>
			<jsp:include page="/WEB-INF/page/base/organs/organHelp.jsp"></jsp:include>
			 -->
			<input id="dialog-accTId" type="hidden" />
			<input id="dialog-accId" type="hidden" /> 
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">

				<tr>
					<th align="right" width="20%">
						<span class="Color5">* </span>卡号：
					</th>
					   <td width="30%">
						<input type="text" id="dialog_cardNo" maxlength="20"
							class="formInput" readonly />
						<s:hidden id="dialog_ftAccId" />
					  </td>
				   <th align="right">
						<span class="Color5">* </span>审核状态：
				  </th>
					<td>
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
						<span class="Color5">* </span>审核操作人：
					</th>

					<td>
						<input type="text" id="dialog_auditt" maxlength="20"
							class="formInput" readonly />
					</td>
					
					<th align="right" width="20%">
						<span class="Color5">* </span>审核时间：
					</th>
					<td width="30%">
						<input type="text" id="dialog_auditTime" maxlength="20"
							class="formInput" readonly />
					</td>
			</tr>

				<tr>
					<th align="right">
						解冻描述：
					</th>
					<td align="left" colspan="3">
						<textarea id="dialog_descAudit" class="formTextarea" cols="45"
							maxlength="200" rows="5"></textarea>
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
						卡号:
					</td>
					<td>
						<s:textfield id="cardNo" name="accRecordDTO.cardNo"
							cssClass="formInput" maxlength="20" theme="simple" />
					</td>
					<td>
						账户号:
					</td>
					<td>
						<s:textfield id="accId" name="accRecordDTO.accId"
							cssClass="formInput" maxlength="20" theme="simple" />
					</td>
					<td>
						账户类型:
					</td>
					<td>
						<s:select name="accRecordDTO.accTId" id="accTId"
							list="#request.typesList" headerKey="-1" headerValue="全部"
							listKey="key" listValue="value" cssClass="formSelect"
							theme="simple" />
					</td>
					<td>
						审核状态:
					</td>
					<td>
						<s:select name="accRecordDTO.checkStatus" id="checkStatus"
							list="#request.condValues" headerKey="-1" headerValue="全部"
							listKey="key" listValue="value" cssClass="formSelect"
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
				<th width="8%">
					<a name="accId" class="sort">账户号</a>
				</th>
				<th width="10%">
					<a name="cardNo" class="sort">卡号</a>
				</th>
				<th width="10%">
					<a name="accType">账户类型</a>
				</th>
				<th width="10%">
					<a name="checkStatus" class="sort">审核状态</a>
				</th>
				<th width="10%">
					<a name="proposer" class="sort">申请操作人</a>
				</th>
				<th width="10%">
					<a name="operTime" class="sort">申请时间</a>
				</th>
				<th width="5%">
					<a name="auditt" class="sort">审核人</a>
				</th>
				<th width="10%">
					<a name="auditTime" class="sort">审核时间</a>
				</th>
				<th width="5%">
					相关操作
				</th>
			</tr>
		</table>
		<div class="listBottom">
			<div class="Fl">
				<my:permission key='sy-2108-02' value='账户解冻添加'>
					<input type="button" class="formButton" value="添加"
						onclick="go('account/accThaw!addUI')" />
				</my:permission>
			</div>
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
		</div>

	</body>
</html>