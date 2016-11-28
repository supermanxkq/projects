
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
		<title>销售额汇总报表</title>
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
		<script src="js/pubValiPattern.js"></script>
		<script src="js/pubValidate.js"></script>
		<script src="js/common.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script src="js/datepicker/WdatePicker.js"></script>
		<script type="text/javascript">
		//查询方法
		function query(page) {
			/**获取表单中的商户的编号*/
			var merId = $.trim($("#merId").val());
			/**获取表单中的商户名称*/
			var merName=$.trim($("#merName").val());
			/**起始时间*/
		    var beginDate = $("#beginDate").val();
		    /**结束时间*/
		    var endDate = $("#endDate").val();
			/**Ajax传递给struts的参数，用来实例化对象*/
			var params = {
				"salesSummaryDTO.merId":merId,
				"salesSummaryDTO.merName":merName,
				"salesSummaryDTO.beginDate":beginDate,
			    "salesSummaryDTO.endDate":endDate,
		        "orderProperty":$("#orderProperty").val(),
		        "orderDirection":$("#orderDirection").val(),
		        "salesSummaryDTO.page" : page
		    };
		    /**使用Ajax请求响应的Action方法，并且传递参数*/
		   ajaxData("report/salesSummary!jsonPageList",params);
		}
	</script>
	</head>
	<body onload="query(${salesSummaryDTO.page});">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 汇总报表 &gt;&gt; 销售额汇总报表
		</div>
		<jsp:include page="/WEB-INF/page/base/terminals/mercHelps.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<form id="form1" name="form1" action="report/salesSummary!export"
			method="post">
			<div class="search">
				<table class="searchTable" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<s:if test="#session.user_session.userLevel!=2">
							<td>
								商户名称:
							</td>
							<td>
								<s:textfield id="merName" name="salesSummaryDTO.merName"
									cssClass="formInput" maxlength="20"
									onkeyup="allowEnCnNu(this);" theme="simple" />
								<s:hidden name="salesSummaryDTO.merId" id="merId" />
								<img alt="查找商户" src="images/search.gif" style="cursor: pointer;"
									onclick="ajaxMerc();" />
							</td>
						</s:if>
						<td>
							起始时间:
						</td>
						<td>
							<s:textfield id="beginDate" name="salesSummaryDTO.beginDate"
								cssClass="formInput"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
								cssStyle="width:150px;" maxlength="20" theme="simple" />
						</td>
						<td>
							终止时间：
						</td>
						<td>
							<s:textfield id="endDate" name="salesSummaryDTO.endDate"
								cssClass="formInput"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
								cssStyle="width:150px;" maxlength="20" theme="simple" />
						</td>
						<td>
							<input type="button" class="formButton" onclick="query();"
								value="查 询" />
							<input type="button" name="Submit2" class="formButton"
								onclick="javascript:document.form1.submit()" value="导出" />
						</td>
					</tr>
				</table>
			</div>
		</form>
		<table width="100%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="6%">
					序号
				</th>
				<th width="10%">
					<a name="merId" class="sort">商户编号</a>
				</th>
				<th width="10%">
					<a name="merName" class="sort">商户名称</a>
				</th>
				<th width="10%">
					<a name="salesQty" class="sort">成交数量</a>
				</th>
				<th width="10%">
					<a name="salesAmt" class="sort">销售额</a>
				</th>
				<th width="10%">
					<a name="lastTime" class="sort">成交时间</a>
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