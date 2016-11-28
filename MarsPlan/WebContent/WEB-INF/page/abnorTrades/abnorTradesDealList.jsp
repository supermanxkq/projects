<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<base href="<%=basePath%>" />
		<title>异常管理</title>
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
		<script src="js/datepicker/WdatePicker.js"></script>
		<script src="js/common.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script type="text/javascript">
		//查询方法
		function query(page) {
			  var userLevel=$("#userLevel").val();
			  if(userLevel=="2"){
			  	$("#formtd").attr("colspan","4");
			  }
			  var tradeId = $("#tradeId").val();
		      var termName = $("#termName").val();
		      var merName = $("#merName").val();
		      var tradeType = $("#tradeType").val();
		      var beginDate = $("#beginDate").val();
		      var endDate = $("#endDate").val();
		      
			  var params = {
			    "abnorTradesDTO.termName":termName,
			    "abnorTradesDTO.tradeId":tradeId,
			    "abnorTradesDTO.merName":merName,
			    "abnorTradesDTO.tradeType":tradeType,
			    "abnorTradesDTO.beginDate":beginDate,
			    "abnorTradesDTO.endDate":endDate,
		        "orderProperty":$("#orderProperty").val(),
		        "orderDirection":$("#orderDirection").val(),
		        "abnorTradesDTO.page":page
		    }; 
		   ajaxData("abnorTrades/abnorTradesDealList!jsonPageList",params);
		}
		//加载页面
		var loadData=function(id,viewFlag){
			$("#downAmt").removeAttr("disabled");
			$("#upAmt").removeAttr("disabled");
		   var params={
		      "abnorTradesDTO.tradeId":id
		   };
		   var actionUrl="abnorTrades/abnorTradesDealList!findById";
		   $.ajax({
		       url : actionUrl,
		       data : params,
		       dataType : "json",
		       cache : false,
		       type :"POST",
		       error : function(textStatus, errorThrown){
		           alert("系统ajax交互错误!");
		       },
		       success : function(data, textStatus) {
		       var date=new Date();
		       $("#dealTime").val(date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
		       var consoleDlg=$("#dialog-confirm");
		       
		       consoleDlg.find("#tradeId2").val(data.obj.tradeId);
		           consoleDlg.find("#cardNo2").val(data.obj.cardNo);
		           consoleDlg.find("#accTypeId2").val(data.obj.accTypeId);
		           consoleDlg.find("#termName2").val(data.obj.termName);
		           consoleDlg.find("#merName2").val(data.obj.merName);
		           consoleDlg.find("#points2").val(data.obj.points);
		           consoleDlg.find("#retailAmt2").val(data.obj.retailAmt);
		           consoleDlg.find("#sxf2").val(data.obj.sxf);
		           consoleDlg.find("#tradeAmt2").val(data.obj.tradeAmt);
		           consoleDlg.find("#createTime2").val(data.obj.createTime);
		           consoleDlg.find("#updateTime2").val(data.obj.updateTime);
		           if(viewFlag=="1"){
			          $("#listt").css("display","none")
			          $("#dialog-confirm").dialog({
			          resizable: true,
				      top: 370,
				      height:500,
				      width:800,
				      modal: true,
		              buttons: {
							'取消': function() {
								$(this).dialog('close');
							}
						}
				      
		       });
			         }else{
			          $("#listt").removeAttr("style");
			          $("#downAmt").val("");
			       	  $("#upAmt").val("");
			       	  $("#abnorType").val("");
			       	  $("#dealDesc").val("");
		              $("#dialog-confirm").dialog({
			          resizable: true,
				      top: 370,
				      height:500,
				      width:800,
				      modal: true,
				      buttons: {
							'取消': function() {
								$(this).dialog('close');
							},
							'保存':function(){
								success();
								
							}
						}                                                                                   
		              
		       });
		       }
		    }
		});
		}
		function success(){
			
			var consoleDlg = $("#dialog-confirm"); 
			var tradeId = $.trim(consoleDlg.find("#tradeId2").val());
			var cardNo= $.trim(consoleDlg.find("#cardNo2").val());
		    var accTypeId = $.trim(consoleDlg.find("#accTypeId2").val());
		    var upAmt= $.trim(consoleDlg.find("#upAmt").val());
		    var downAmt= $.trim(consoleDlg.find("#downAmt").val());
		    var dealDesc = $.trim(consoleDlg.find("#dealDesc").val());
		    var dealTime = $.trim(consoleDlg.find("#dealTime").val());
		    var abnorType= $.trim(consoleDlg.find("#abnorType").val());
		    if(abnorType==-1){
		    	alert("请选择异常类型！");
		    	return;
		    }
		    if(upAmt.length==0&&downAmt.length==0){
		    	alert("请输入调整金额！");
		    	return;
		    }
		    var params={
		      "abnorTradesDTO.tradeId" : tradeId,
		      "abnorTradesDTO.cardNo" : cardNo,
		      "abnorTradesDTO.accTypeId" : accTypeId,
		      "abnorTradesDTO.upAmt" : upAmt,
		      "abnorTradesDTO.downAmt" : downAmt,
		      "abnorTradesDTO.dealDesc" : dealDesc,
		      "abnorTradesDTO.dealTime" : dealTime,
		      "abnorTradesDTO.abnorType" : abnorType
		    };
			$.ajax({
				async:false,
				url:"abnorTrades/abnorTradesDealList!audit",
				type:"post",
				data:params,
				dataType:"json",
				success:function(data, textStatus){
					$("#dialog-confirm").dialog('close');
					query(1);
				}
			});
		}
		
		/**修改文本框状态**/
		function changeUp(obj){
			var reg=/^[0-9]{1,6}(([\\.][0-9]{1,2})|([\\.]))?$/;
			if(!reg.test(obj.value)){
				$("#"+obj.id).val("");
			}
			var str=$.trim(obj.value);
			if(str.length>0){
					$("#downAmt").attr("disabled","true");
			}else{
				$("#downAmt").removeAttr("disabled");
			}
		}
		
		function changeDown(obj){
			var reg=/^[0-9]{1,6}(([\\.][0-9]{1,2})|([\\.]))?$/;
			if(!reg.test(obj.value)){
				$("#"+obj.id).val("");
			}
			var str=$.trim(obj.value);
			if(str.length>0){
					$("#upAmt").attr("disabled","true");
			}else{
				$("#upAmt").removeAttr("disabled");
			}
		}
	</script>
	</head>
	<body onload="query(${abnorTradesDTO.page });">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 异常管理 &gt;&gt; 异常交易处理
		</div>
		<div id="dialog-confirm" style="display: none;" title="查看">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">
				<tr>
					<th align="right" width="20%">
						流水ID号：
					</th>
					<td width="30%">
						<input type="text" id="tradeId2" maxlength="20" class="formInput"
							readonly />
					</td>
					<th align="right">
						卡号：
					</th>
					<td>
						<input type="text" id="cardNo2" name="abnorTradesDTO.cardNo"
							maxlength="20" class="formInput" readonly />
						<s:hidden id="accTypeId2" name="abnorTradesDTO.accTypeId" />
					</td>
				</tr>
				<tr>
					<th align="right" width="20%">
						终端名称：
					</th>
					<td width="30%">
						<input type="text" id="termName2" maxlength="20" class="formInput"
							readonly />
					</td>
					<th align="right">
						商户名称：
					</th>
					<td>
						<input type="text" id="merName2" maxlength="20" class="formInput"
							readonly />
					</td>
				</tr>
				<tr>
					<th align="right" width="20%">
						交易金额：
					</th>
					<td width="30%">
						<input type="text" id="tradeAmt2" maxlength="20" class="formInput"
							readonly />
					</td>
					<th align="right">
						原价：
					</th>
					<td>
						<input type="text" id="retailAmt2" maxlength="20"
							class="formInput" readonly />
					</td>
				</tr>
				<tr>
					<th align="right" width="20%">
						产生积分：
					</th>
					<td width="30%">
						<input type="text" id="points2" maxlength="20" class="formInput"
							readonly />
					</td>
					<th align="right">
						手续费：
					</th>
					<td>
						<input type="text" id="sxf2" maxlength="20" class="formInput"
							readonly />
					</td>
				</tr>
				<tr>
					<th align="right" width="20%">
						创建日期：
					</th>
					<td width="30%">
						<input type="text" id="createTime2" maxlength="20"
							class="formInput" readonly />
					</td>
					<th align="right">
						更新日期：
					</th>
					<td>
						<input type="text" id="updateTime2" maxlength="20"
							class="formInput" readonly />
					</td>
				</tr>
			</table>
			<div id="listt">
				<div class="List_tit" id="List_div">
					异常处理
				</div>

				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formTable" id="List_tit">
					<tr>
						<th align="right" width="20%">
							异常类型：
						</th>
						<td>
							<s:select name="abnorTradesDTO.abnorType" id="abnorType"
								list="#request.abnorSign" listKey="key" headerKey="-1"
								headerValue="请选择" listValue="value" cssClass="formSelect"
								theme="simple" />
						</td>
						<th align="right">
							处理时间：
						</th>
						<td>
							<input type="text" id="dealTime" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							上调整整数：
						</th>
						<td width="30%">
							<input type="text" id="upAmt" maxlength="20" class="formInput"
								onkeyup="changeUp(this);" />
						</td>
						<th align="right">
							下调整整数：
						</th>
						<td>
							<input type="text" id="downAmt" maxlength="20" class="formInput"
								onkeyup="changeDown(this);" />
						</td>
					</tr>
					<tr>
						<th align="right">
							处理描述：
						</th>
						<td align="left" colspan="3">
							<textarea id="dealDesc" class="formTextarea" cols="45" rows="5"></textarea>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<div class="search">
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
					<th>
						交易时间:
					</th>
					<td>
						<s:textfield id="beginDate" name="abnorTradesDTO.beginDate"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
							cssStyle="width:144px;" maxlength="20" theme="simple" />
					</td>
					<th>
						&nbsp;&nbsp;至:&nbsp;&nbsp;&nbsp;
					</th>
					<td>
						<s:textfield id="endDate" name="abnorTradesDTO.endDate"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
							cssStyle="width:144px;" maxlength="20" theme="simple" />
					</td>
				</tr>
				<tr>
					<th>
						流水ID号:
					</th>
					<td>
						<input type="text" id="tradeId" name="abnorTradesDTO.tradeId"
							class="formInput" maxlength="20" style="width: 144px" />
					</td>
					<th>
						交易类型:
					</th>
					<td>
						<s:select name="abnorTradesDTO.tradeType" id="tradeType"
							list="#request.flag" listKey="key" headerKey="-1"
							headerValue="全部" listValue="value" cssClass="formSelect"
							theme="simple" />
					</td>
				</tr>
				<tr>
					<th>
						终端名称:
					</th>
					<td>
						<input type="text" id="termName" name="abnorTradesDTO.termName"
							class="formInput" maxlength="20" style="width: 144px" />
					</td>
					<s:if test="#session.user_session.userLevel!=2">
						<th>
							商户名称:
						</th>
						<td>
							<input type="text" id="merName" name="abnorTradesDTO.merName"
								class="formInput" maxlength="20" style="width: 144px" />
						</td>
					</s:if>
					<td id="formtd">
						<input type="button" class="formButton" onclick="query();"
							value="查 询" />
						<input type="button" name="Submit2" class="formButton"
							onclick="javascript:document.form1.submit()" value="导出" />
					</td>
				</tr>
			</table>

			<input type="hidden" id="userLevel"
				value="${sessionScope.user_session.userLevel }" />

		</div>

		<table width="96%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="3%">
					序号
				</th>
				<th width="10%">
					<a name="tradeIdd">流水ID</a>
				</th>
				<th width="10%">
					<a name="termNamee">终端名称</a>
				</th>
				<th width="10%">
					<a name="merNamee">商户名称</a>
				</th>
				<th width="5%">
					<a name="tradeTypee">交易类型</a>
				</th>
				<th width="8%">
					<a name="tradeTimee">交易时间</a>
				</th>
				<th width="8%">
					<a name="sxff">手续费</a>
				</th>
				<th width="8%">
					<a name="tradeAmtt">交易金额</a>
				</th>
				<th width="5%">
					相关操作
				</th>
			</tr>
		</table>
		<div class="listBottom">
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
		</div>
	</body>
</html>