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
	<title>交易报表</title>
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
	<script src="js/jquery/jquery.ui.tabs.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<script src="js/common.js"></script>
	<script src="js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript">
		function query1(page,status,type) {
			
			var tradesId = $("#tradesId").val();
		    var omemId = $("#omemId").val();
		    var beginDate = $("#beginDate").val();
		    var endDate = $("#endDate").val();
			if(type=1){
				
			}
		
			$("#status").val(status);
		    var payOrderId = $("#payOrderId").val();
		    var payAccNo = $("#payAccNo").val();
		    var payAccName = $("#payAccName").val();
		    var payAmt = $("#payAmt").val();
		    var merAccNo = $("#merAccNo").val();
		    var tradesTime = $("#tradesTime").val();
		    var merAccName = $("#merAccName").val();
			var params = {
			    "btradesDTO.tradesId":tradesId,
			    "btradesDTO.status":status,
			    "btradesDTO.omemId":omemId,
			    "btradesDTO.payOrderId":payOrderId,
			    "btradesDTO.payAccNo":payAccNo,
			    "btradesDTO.payAccName":payAccName,
			    "btradesDTO.payAmt":payAmt,
			    "btradesDTO.merAccNo":merAccNo,
			    "btradesDTO.merAccName":merAccName,
			    "btradesDTO.tradesTime":tradesTime,
			    "btradesDTO.beginDate":beginDate,
			    "btradesDTO.endDate":endDate,			    
		        "orderProperty":$("#orderProperty").val(),
		        "orderDirection":$("#orderDirection").val(),
		        "btradesDTO.page":page
		    }; 
		   	ajaxData("report/btrades!jsonPageList",params);
		}
		
		// 分页使用
		function query(page) {
			query1(page,$("#status").val(),2);
		}
		
	</script> 
</head>
<body onload="query1(1,1,1)">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 交易报表 &gt;&gt; 业务交易报表
	</div>
	
	<div id="tabs">
		<div class="search">
			<br />
			<a onclick="query1(1,1,1);"><span style="background:none repeat-x scroll 50% 50% #d3d3d3; border: 1px solid #d3d3d3; color: #000; text-decoration: none; outline: none;">交易成功信息</span></a>
			<a onclick="query1(1,2,1);"><span style="background:none repeat-x scroll 50% 50% #d3d3d3; border: 1px solid #d3d3d3; color: #000;">交易失败信息</span></a>
		</div>
		
		<form id="form1" name="form1" action="" method="post">
			<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
			<div class="search">
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
					<!-- 查询正常状态的交易记录 -->
					<td><input type="hidden" id="status" value="1" /></td>
					<td>交易流水号:</td>
					<td><s:textfield id="tradesId" name="btradesDTO.tradesId" cssClass="formInput" maxlength="16" theme="simple" onkeyup="replaceToNum(this);" /></td>
					<td>会员编号:</td>
			        <td><s:textfield id="omemId" name="btradesDTO.omemId" cssClass="formInput" maxlength="10" theme="simple" onkeyup="replaceToNum(this);" /></td>
					<td>交易时间:</td>
					<td><s:textfield id="beginDate" name="btradesDTO.beginDate" cssClass="Wdate formInput2" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:100px;" maxlength="20" theme="simple"/> - <s:textfield id="endDate" name="btradesDTO.endDate" cssClass="Wdate formInput2" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:100px;" maxlength="20" theme="simple"/></td>
			        <td><input type="button" class="formButton" onclick="query(${btradesDTO.page});" value="查 询" /></td>
				</tr>
			</table>
			</div>
		</form>
	
		<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
			<tr>
				<th width="3%">序号</th>
				<th width="10%"><a name="tradesId" class="sort">流水号</a></th>
				<th width="5%"><a name="omemId" class="sort">会员编号</a></th>
				<th width="10%"><a name="payOrderId" class="sort">订单号</a></th>
				<th width="15%"><a name="payAccNo" class="sort">支付账户号</a></th>
				<th width="10%"><a name="payAccName" class="sort">支付账户名称</a></th>
				<th width="5%"><a name="payAmt" class="sort">支付金额</a></th>
				<th width="15%"><a name="merAccNo" class="sort">商户账户号</a></th>
				<th width="10%"><a name="merAccName" class="sort">商户账户名称</a></th>
				<th width="12%"><a name="tradesTime" class="sort">交易时间</a></th>
			</tr>
		</table>
	    
		<div class="listBottom">
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false"/>
			</div>
		</div>
	</div>
</body> 
</html>