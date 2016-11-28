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
	<script src="js/datepicker/WdatePicker.js"></script>
	<script src="js/common.js"></script>
	<script type="text/javascript" src="js/fusionchats/FusionCharts.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<%
	   String a = (String)request.getAttribute("twoDstr");
	%>
	<script type="text/javascript">
		function query(page) {
		      var tradeId = $("#tradeId").val();
		      var termName = $("#termName").val();
		      var termId = $("#termId").val();
		      var tradeType = $("#tradeType").val();
		      var merRealName = $("#merRealName").val();
		      var merId = $("#merId").val();
		      var morganId = $("#morganId").val();
		      var memRealName = $("#memRealName").val();
		      var morganName = $("#morganName").val();
		      var beginDate = $("#beginDate").val();
		      var endDate = $("#endDate").val();
			var params = {
			    "tradesViewDTO.tradeId":tradeId,
			    "tradesViewDTO.termName":termName,
			    "tradesViewDTO.termId":termId,
			    "tradesViewDTO.tradeType":tradeType,
			    "tradesViewDTO.merRealName":merRealName,
			    "tradesViewDTO.merId":merId,
			    "tradesViewDTO.morganId":morganId,
			    "tradesViewDTO.morganName":morganName,
			    "tradesViewDTO.memRealName":memRealName,
			    "tradesViewDTO.beginDate":beginDate,
			    "tradesViewDTO.endDate":endDate,			    
		        "orderProperty":$("#orderProperty").val(),
		        "orderDirection":$("#orderDirection").val(),
		        "tradesViewDTO.page":page
		    }; 
		   ajaxData("report/tradesview!jsonPageList",params);
		}

		
		function drawReport(){
			   
			   var params={
                         "tradesViewsDTO.tradeId":tradesId
					   };
               var actionURL = "report/tradesview!drawReport",params;
               $.ajax({
                     url:actionURL,
                     data:params,
                     dataType:"json",
                     cache:false,
                     type:"POST",
                     sucess:function (){
                       
                    },
                     error:function () {}   
  
                   });
			}
	</script> 
</head>
<body onload="query(${tradesViewDTO.page});">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 查询统计 &gt;&gt; 交易报表查询
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<form id="form1" name="form1" action="report/tradesview!export" method="post" >
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>交易流水号:</td>
				<td><s:textfield id="tradeId" name="tradesViewDTO.tradeId" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>终端编号:</td>
	        	<td><s:textfield id="termId" name="tradesViewDTO.termId" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>终端名称:</td>
				<td><s:textfield id="termName" name="tradesViewDTO.termName" cssClass="formInput" maxlength="20" theme="simple"/></td>        	
			</tr>
			<tr>
	            <td></td>
			    <td>交易类型:</td>
				<td><s:select name="tradesViewDTO.tradeType" id="tradeType" list="#request.flag" listKey="key" headerKey="-1" headerValue="全部" listValue="value" cssClass="formSelect" theme="simple"/></td>
				<td>商户编号:</td>
	        	<td><s:textfield id="merId" name="tradesViewDTO.merId" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>商户名称:</td>
				<td><s:textfield id="merRealName" name="tradesViewDTO.merRealName" cssClass="formInput" maxlength="20" theme="simple"/></td>        	
			</tr>
			<tr>
	            <td></td>
			    <td>机构编号:</td>
				<td><s:textfield id="morganId" name="tradesViewDTO.morganId" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>机构名称:</td>
				<td><s:textfield id="morganName" name="tradesViewDTO.morganName" cssClass="formInput" maxlength="20" theme="simple"/></td>
	        	<td>会员名称:</td>
				<td><s:textfield id="memRealName" name="tradesViewDTO.memRealName" cssClass="formInput" maxlength="20" theme="simple"/></td>
			</tr>
			<tr>
			<td></td>
	        	
			  <td>结算时间:</td>
				<td><s:textfield id="beginDate" name="tradesViewDTO.beginDate" onfocus="WdatePicker({skin:'blue'})" cssStyle="width:100px;" maxlength="20" theme="simple"/>至<s:textfield id="endDate" name="tradesViewDTO.endDate" onfocus="WdatePicker({skin:'blue'})" cssStyle="width:100px;" maxlength="20" theme="simple"/></td>
	        	<td></td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" />
<input type="button" name="Submit2" class="formButton" onclick="javascript:document.form1.submit()" value="导出" />

</td>	
<td></td>        	
			</tr>
</table>
	</div>
	</form>
	<table width="100%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%"><a name="cardNo" class="sort">交易流水号</a></th>
			<th width="8%"><a name="orderAmt" class="sort">商户编号</a></th>
			<th width="12%"><a name="recOrderId" class="sort">商户名称</a></th>
			<th width="4%"><a name="createTime" class="sort">终端编号</a></th>
			<th width="10%"><a name="status" class="sort">终端名称</a></th>
			<th width="8%"><a name="status" class="sort">消费卡号</a></th>
			<th width="4%"><a name="status" class="sort">账户类型</a></th>
			<th width="9%"><a name="status" class="sort">交易时间</a></th>
			<th width="4%"><a name="cardNo" class="sort">交易金额</a></th>
			<th width="4%"><a name="orderAmt" class="sort">手续费</a></th>
			<th width="4%"><a name="recOrderId" class="sort">结算金额</a></th>
			<th width="4%"><a name="createTime" class="sort">交易类型</a></th>
			<th width="6%"><a name="status" class="sort">会员名称</a></th>
			
		</tr>
	</table>
		<div class="listBottom">
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
	<div id="3DColumn" align="center">
	${ twoDstr }
	</div>
	
</body> 
</html>