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
	<script src="js/datepicker/WdatePicker.js"></script>
	<script src="js/common.js"></script>
	<script type="text/javascript" src="js/fusionchats/FusionCharts.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
		//异步加载数据
function ajaxData1(dataUrl,params){
    var tbId = "listTable1";
    var tb = $("#"+tbId);
    var thLength = $("#"+tbId+" th").length;
    var first = tb.find('tr:eq(0)');
    $.ajax( {   
        url : dataUrl, 
        data : params,   
        dataType : "json",
        cache : false, 
        type : "POST",
        error : function(textStatus, errorThrown) {   
    		first.nextAll().remove();
        	var tr = $("<tr></tr>");
            var html = "<td colspan='"+thLength+"'>获取数据异常，请重试 ....</td>";		              		
            tr.html(html);
            tb.append(tr);
        },   
        beforeSend : function(){
        	first.nextAll().remove();
        	var tr = $("<tr></tr>");
            var html = "<td colspan='"+thLength+"'><img src='images/loading.gif' /> <b>数据加载中 ....</b></td>";		              		
            tr.html(html);
            tb.append(tr);
		},
        success : function(data, textStatus) {  
        	$("#pageNav").html(data.pagehtml);
        	$("#otherhtml").html(data.otherhtml);
        	first.nextAll().remove();
        	var list = data.list;
        	if (list.length>0){
        		for(var i=0;i<list.length;i++) {
        			var tr = $("<tr></tr>");
        			var html = "";
        			for(var j=0;j<list[i].length;j++){
        				html += "<td >"+list[i][j]+"</td>";
        			}
              		tr.html(html);
              		if (i%2==1) tr.addClass("ghhs");
              		tb.append(tr);
            	}
        	} else {
        			var tr = $("<tr></tr>");
              		var html = "<td colspan='"+thLength+"' >没有找到相关数据</td>";		              		
              		tr.html(html);
              		tb.append(tr);
        	}
        }
    });
}
	
		function queryExcepBTrades(page) {
			var tradesId = $("#tradesId").val();
			var status = $("#status").val();
		    var omemId = $("#omemId").val();
		    var payOrderId = $("#payOrderId").val();
		    var payAccNo = $("#payAccNo").val();
		    var payAccName = $("#payAccName").val();
		    var payAmt = $("#payAmt").val();
		    var merAccNo = $("#merAccNo").val();
		    var tradesTime = $("#tradesTime").val();
		    var merAccName = $("#merAccName").val();
		    var beginDate = $("#beginDate").val();
		    var endDate = $("#endDate").val();
			var params = {
			    "btradesExceDTO.tradesId":tradesId,
			    "btradesExceDTO.status":2,
			    "btradesExceDTO.omemId":omemId,
			    "btradesExceDTO.payOrderId":payOrderId,
			    "btradesExceDTO.payAccNo":payAccNo,
			    "btradesExceDTO.payAccName":payAccName,
			    "btradesExceDTO.payAmt":payAmt,
			    "btradesExceDTO.merAccNo":merAccNo,
			    "btradesExceDTO.merAccName":merAccName,
			    "btradesExceDTO.tradesTime":tradesTime,
			    "btradesExceDTO.beginDate":beginDate,
			    "btradesExceDTO.endDate":endDate,			    
		        "orderProperty":$("#orderProperty").val(),
		        "orderDirection":$("#orderDirection").val(),
		        "btradesExceDTO.page":page
		    }; 
		   	ajaxData("report/btrades!jsonPageExcepList",params);
		}
	</script> 
</head>
<body onload="queryExcepBTrades(${btradesExceDTO.page});">
	
	<form id="form1" name="form1" action="" method="post">
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<div class="search">
	<table class="searchTable" cellpadding="0" cellspacing="0">
		<tr>
			<!-- 查询异常状态的交易记录 -->
			<td><input type="hidden" id="status" value="2"/></td>
			<td>交易流水号:</td>
			<td><s:textfield id="tradesId" name="btradesExceDTO.tradesId" cssClass="formInput" maxlength="16" theme="simple" onkeyup = "replaceToNum(this);" /></td>
			<td>会员编号:</td>
	        <td><s:textfield id="omemId" name="btradesExceDTO.omemId" cssClass="formInput" maxlength="10" theme="simple" onkeyup="replaceToNum(this);" /></td>
			<td>交易时间:</td>
			<td><s:textfield id="beginDate" name="btradesExceDTO.beginDate" cssClass="Wdate formInput2" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:100px;" maxlength="20" theme="simple"/> - <s:textfield id="endDate" name="btradesExceDTO.endDate" cssClass="Wdate formInput2" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:100px;" maxlength="20" theme="simple"/></td>
	        <td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
		</tr>
	</table>
	</div>
	</form>
	
	<table width="96%" id="listTable1" class="listTable" cellpadding="0" cellspacing="0" >
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
			<th width="5%">相关操作</th>
		</tr>
	</table>
	
<!--	<div class="listBottom">-->
<!--		<div class="Fr" id="pageNav">-->
<!--			<s:property value="pageHTML" escape="false"/>-->
<!--		</div>-->
<!--	</div>-->
</body> 
</html>