
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
		<title>商品销量汇总排名报表</title>
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
		    var beginDate = $("#beginDate").val();
		    /**结束时间*/
		    var endDate = $("#endDate").val();
			/**Ajax传递给struts的参数，用来实例化对象*/
			var params = {
				"goodsSaleSumDTO.beginDate":beginDate,
			    "goodsSaleSumDTO.endDate":endDate,
		        "orderProperty":$("#orderProperty").val(),
		        "orderDirection":$("#orderDirection").val(),
		        "goodsSaleSumDTO.page" : page
		    };
		    /**使用Ajax请求响应的Action方法，并且传递参数*/
		   ajaxData("report/goodSaleSum!jsonPageList",params);
		}
	</script>
	</head>
	<body onload="query(${goodsSaleSumDTO.page});">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 汇总报表 &gt;&gt; 商品销量汇总排名报表
		</div>
		<jsp:include page="/WEB-INF/page/base/terminals/mercHelps.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<form id="form1" name="form1" action="report/goodSaleSum!export"
			method="post">
			<div class="search">
				<table class="searchTable" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<td>
							起始时间:
						</td>
						<td>
							<s:textfield id="beginDate" name="goodsSaleSumDTO.beginDate"
								cssClass="formInput"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
								cssStyle="width:150px;" maxlength="20" theme="simple" />
						</td>
						<td>
							终止时间：
						</td>
						<td>
							<s:textfield id="endDate" name="goodsSaleSumDTO.endDate"
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
		<table width="96%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="6%">序号</th>
				<th width="10%">
					<a name="goodsName" class="sort">商品名称</a>
				</th>
				<th width="10%">
					<a name="goodsItem" class="sort">商品货号</a>
				</th>
				<th width="10%">
					<a name="qty" class="sort">销售量</a>
				</th>
				<th width="10%">
					<a name="price" class="sort">销售额</a>
				</th>
				<th width="10%">均价</th>
			</tr>
		</table>
		<div class="listBottom">
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
		</div>
	</body>
</html>