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
	<title>退卡管理</title>
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
		function query(page){
			var retId = $("#retId").val();
		//	var cardNo = $("#cardNo").val();
			var params = {
					"cardReturnDTO.retId" : retId,
			 //       "cardReturnDTO.cardsNoShow" : cardNo,
			        "orderProperty" : $("#orderProperty").val(),
			        "orderDirection" : $("#orderDirection").val(),
			        "cardReturnDTO.page" : page
			    }; 
			   ajaxData("card/cardReturn!jsonPageList",params);
			}
		

	</script> 
</head>
<body onload="query(${cardReturnDTO.page});">
	
	<div class="Position">
		当前位置是：HOME &gt;&gt; 账户管理 &gt;&gt; 退卡管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/page/card/cardReturn/cardReturnDetail.jsp"></jsp:include>
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>退卡编号:</td>
				<td><s:textfield id="retId" name="cardReturnDTO.retId" cssClass="formInput" maxlength="20" theme="simple"/></td>
<%--				<td>退卡卡号:</td>--%>
<%--				<td>--%>
<%--					<s:textfield id="cardNo" name="cardReturnDTO.cardNo" cssClass="formInput" maxlength="20" theme="simple"/>--%>
<%--				</td>--%>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr> 
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%"><a name="retId" class="sort">退卡单号</a></th>
			<th width="8%"><a name="payWay" class="sort">退款方式</a></th>
			<th width="10%"><a name="allAmt" class="sort">卡片金额</a></th>
			<th width="10%"><a name="relAmt" class="sort">实际金额</a></th>
			<th width="8%"><a name="reCount" class="sort">数量</a></th>
			<th width="8%"><a name="memName" class="sort">退卡人名称</a></th>
			<th width="8%"><a name="operTime" class="sort">退卡时间</a></th>
			<th width="8%"><a name="retOrderStatus" class="sort">订单状态</a></th>
			<th width="8%"><a >相关操作</a></th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<my:permission key='sy-2106-02' value='退卡订单添加'>
				<input type="button" class="formButton" value="添加" onclick="go('card/cardReturn!addUI')"/>
			</my:permission>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>