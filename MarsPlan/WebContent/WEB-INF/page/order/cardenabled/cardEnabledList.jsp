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
		<title>订单卡片激活</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.4.2.min.js"></script>
		<link href="js/jquery/css/jquery.ui.all.css" rel="stylesheet" type="text/css" />
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
			var orderId = $.trim($("#orderId").val());
			var activateSign = $.trim($("#activateSign").val());
			var params = {
				"cardEnabledDTO.orderId" : orderId,
				"cardEnabledDTO.status" : activateSign,
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		        "cardEnabledDTO.page" : page
		    }; 
		   ajaxData("order/cardEnabled!jsonPageList",params);// !是跳转的意思,相当于？
		};
	//弹出框	
		var loadData = function(id,viewFlag) {
		  	var params = {   
		        "saleOrderDTO.saleOrderId" : id,
		        "cardEnabledDTO.orderId" : id
		  	};  
		  	var actionUrl = "order/cardEnabled!LookAll"; 
		    $.ajax({ 
		    	//async:false,
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
		         
		          if(viewFlag == 1 ){
					 showAudOrCheck(1,data);
		             // 打开对话框
					$("#dialog-confirm").dialog({
						resizable: true,
						top: 370,
						height:330,
						width:650,
						modal: true,
						buttons: {
							'关闭': function() {
								$(this).dialog('close');
							}
						}
					}); 
		            }else if(viewFlag != 1 ){
					   showAudOrCheck(0,data);
										
		             // 打开对话框
					$("#dialog-confirm").dialog({
						resizable: true,
						top: 500,
						height:500,
						width:800,
						modal: true,
						buttons: {
							'取消': function() {
								$(this).dialog('close');
							},
							//'激活不通过': auditNotPass,
					         '激活通过': auditPass
						}
					});     
		        }
		      }   
		    });
		};
		
		function showAudOrCheck(flag,data){
		           var consoleDlg = $("#dialog-confirm");
		           // consoleDlg.find("#dialog_enabledId").val(data.objResult.enabledId);
		           $("#saleOId").val(data.saleOrderId);
					consoleDlg.find("#saleOrderId").html(data.saleOrderId);
					consoleDlg.find("#orderAmt").html(data.orderAmt+" 元");
					//consoleDlg.find("#activateSign").html(data.activateSign);
					consoleDlg.find("#operatorName").html(data.operatorId);
					//consoleDlg.find("#initAmt").val(data.initAmt);
					consoleDlg.find("#paidAmt").html(data.paidAmt+" 元");
					if(data.paidType==="0"){
						consoleDlg.find("#paidType").html("现金");
					}else if(data.paidType==="1"){
						consoleDlg.find("#paidType").html("支票");
					}else{
						consoleDlg.find("#paidType").html("转账");
					}
					if(data.payMen!=null){
						consoleDlg.find("#buyMem").html(data.payMen);
					}else{
						consoleDlg.find("#buyMem").html("");
					}
					
					consoleDlg.find("#createTime").html(data.createTime);
					consoleDlg.find("#beginCardNo").html(data.beginCardNo);
					consoleDlg.find("#enanbleTime").html(data.enanbleTime);
					if(data.enableDesc!=null){
						consoleDlg.find("#enableDescc").val(data.enableDesc);
					}else{
						consoleDlg.find("#enableDescc").val("");
					}
					$("#cardNosTb").find('tr:eq(0)').nextAll().remove();
					$("#cardNosTb").append(data.cardNoStr);
					 if(data.activateSign==0){
		              
					       consoleDlg.find("#statusVal").html("未激活 ");
					       $("#status").val(0);
					   }
					   else{
					    consoleDlg.find("#statusVal").html("已激活 ");
					    $("#status").val(1);
					    }
					if(flag==0){
					    consoleDlg.find("#operatorName").hide();
						consoleDlg.find("#operatorName1").hide();
						consoleDlg.find("#enanbleTime").hide();
						consoleDlg.find("#enanbleTime1").hide();
						consoleDlg.find("#enableDescc").val("");
						consoleDlg.find("#enableDescc").attr("readonly",false);
					}
					else if(flag==1){
					   	consoleDlg.find("#operatorName").show();
						consoleDlg.find("#operatorName1").show();
						consoleDlg.find("#enanbleTime").show();
						consoleDlg.find("#enanbleTime1").show();
						consoleDlg.find("#operatorName").html(data.operatorName);
					    consoleDlg.find("#enanbleTime").html(data.enanbleTime);
					    consoleDlg.find("#enableDescc").attr("readonly",true);
					}
		}
		
		var auditPass = function(){
			if(confirm("是否确认激活通过？")){
				enabled(1);
			}
		}
		
		var auditNotPass = function(){
			if(confirm("是否确认激活不通过？")){
				enabled(2);
			}
		}
		
		function enabled (activateSign) {
		    var consoleDlg = $("#dialog-confirm"); 
		  	var activateSign= $("#status").val();
		  	
		    var saleOrderId= $.trim($("#saleOId").val());
		    var enableDesc=$("#enableDescc").val();
		    
		    var params = {
		   		"saleOrderDTO.saleOrderId": saleOrderId,
		        "cardEnabledDTO.status" : activateSign,
		        "enableDesc" : enableDesc
		    };
		 
		   
		     var actionUrl = "order/cardEnabled!enabled";   
		        	
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
		            if (data.flag) {   
						consoleDlg.dialog("close");  
		                alert("操作成功!");
		                query($("#currPage").text());	                
		           	} else{
		            	alert("操作失败!");
		            }
		        }
		    });
		    
      }
	</script>
	</head>
	<body onload="query(${cardEnabledDTO.page });">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 订单管理 &gt;&gt; 订单卡片激活
		</div>
		<div id="dialog-confirm" style="display: none;" title="订单卡片激活">
			<table width="96%" id="dialog_listTable" class="formTable" cellpadding="0"
			cellspacing="0">
			    <s:hidden id="status"/>
			    <s:hidden id="saleOId"/>
			    
			        <tr>
						<th align="right" width="20%">
							订单编号：
						</th>
						<td width="30%" id="saleOrderId"></td>
						<th align="right" width="20%">
							订单总额：
						</th>
						<td width="30%" id="orderAmt"></td>
					</tr>
					<tr>
					    <th align="right" >
							激活状态：
						</th>
						<td id="statusVal"></td>
						<th align="right">
							实际支付金额：
						</th>
						<td id="paidAmt"></td>
					</tr>
					<tr>
						<th align="right">
							支付类型：
						</th>
						<td id="paidType"></td>
						<th align="right">
							购卡人：
					    </th>
					    <td id="buyMem">
					    </td>
					</tr>
					<tr>
						<th align="right">
							创建时间：
						</th>
						<td id="createTime" ></td>
						<th align="right">
							起始卡号：
					    </th>
					    <td id="beginCardNo">
					    </td>
					</tr>
					<tr>
						<th align="right" id="operatorName1">
							操作人：
						</th>
						<td id="operatorName"></td>
						<th align="right" id="enanbleTime1">
							激活时间：
						</th>
						<td id="enanbleTime" ></td>
					</tr>
					<tr>
						<th align="right">
							激活描述：
						</th>
						<td  colspan="3">
							<textarea rows="3" cols="50" id="enableDescc" 
								maxlength="260" style="resize:none;"
							></textarea>
						</td>
					</tr>
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
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<div class="search">
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<img src="images/fd.jpg" />
					</td>
					<td>
						订单编号:
					</td>
					<td>
						<s:textfield id="orderId" name="cardEnabledDTO.orderId"
							cssClass="formInput" maxlength="20" theme="simple" />
					</td>
					<td>状态:</td>
					<td>
					 
						<s:select name="activateSign" id="activateSign"
								list="#request.statusValues" listKey="key" listValue="value"
								headerKey="-1" headerValue="--请选择--"
								cssClass="formInput"
								theme="simple" />
					<!-- 
						<select id="activateSign">
							<option value="-1">请选择</option>
							<option value="0">未激活</option>
							<option value="1">激活</option>
						</select>
						-->
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
				<th width="3%">序号</th>
<!--				<th width="8">-->
<!--					<a id="enabledId" name="enabledId" class="sort">卡片激活编号</a>-->
<!--				</th>-->
				<th width="10%">
					<a  name="organName" >所属机构</a>
				</th>
				<th width="12%">
					<a  name="orderId" >订单编号</a>
				</th>
				<th width="8%">
					<a  name="activateSign" >激活状态</a>
				</th>
				<th width="8%">
					<a  name="operator" >操作人</a>
				</th>
				<th width="8%">
					<a  name="createTime" >创建时间</a>
				</th>
				<th width="8%">
					<a  name="enanbleTime" >激活时间</a>
				</th>
				<th width="10%">
					<a  name="enableDesc" >激活描述</a>
				</th>
				<th width="5%">
					相关操作
				</th>
			</tr>
			</table>
		
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</body>
</html>