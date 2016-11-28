
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
		<title>已完成报表</title>
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
				<script src="js/tips/jquery-1.2.6.pack.js"></script>
		<script src="js/tips/jquery.messager.js"></script>
		<script type="text/javascript">
		//查询方法
		function query(page) {
		    var beginDate = $("#beginDate").val();
		    var endDate = $("#endDate").val();
		    var goodsName=$.trim($("#goodsName").val());
		    var brand=$.trim($("#brand").val());
		    var status=$("#status").val();
		    var merchant=$.trim($("#merchant").val());
		    var goodsTypeId=$("#goodsTypeId").val();
		    var model=$.trim($("#model").val());
			/**Ajax传递给struts的参数，用来实例化对象*/
			var params = {
				"goodsDTO.model":model,
 				"goodsDTO.brand":brand,
 				"goodsDTO.goodsTypeId":goodsTypeId,
 				"goodsDTO.status":status,
				"goodsDTO.goodsName":goodsName,
				"goodsDTO.merchant":merchant,
 				"goodsDTO.beginDate":beginDate,
 			    "goodsDTO.endDate":endDate,
 		        "orderProperty":$("#orderProperty").val(),
 		        "orderDirection":$("#orderDirection").val(),
		        "goodsDTO.page" : page
		    };
		    /**使用Ajax请求响应的Action方法，并且传递参数*/
		   ajaxData("managers/goods!jsonPageList",params);
		}
	</script>
	</head>
	<body onload="query(${goodsDTO.page});">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 入库管理&gt;&gt;入库
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
			<div class="search">
			<div class="Fl">
<%-- 			<my:permission key='sy-9103-02' value='用户添加'> --%>
				<input type="button" class="formButton" value="新入库" onclick="go('managers/goods!addUI')"/>
<%-- 			</my:permission> --%>
		</div>
		<form id="form1" name="form1" action="finished/finished!export"
			method="post">
				<table class="searchTable" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<td>
							商品名称:
						</td>
						<td>
						<s:textfield id="goodsName" name="goodsDTO.goodsName" 
							cssClass="formInput" cssStyle="width:150px;" maxlength="20" 
								theme="simple" onkeyup="query(1);"/>
						</td>
						<td>
							品牌:
						</td>
						<td>
						<s:textfield id="brand" name="goodsDTO.brand" 
							cssClass="formInput" cssStyle="width:150px;" maxlength="20"  onkeyup="query(1);"
								theme="simple" />
						</td>
					</tr>
					<tr>
						<td>
							厂家名称:
						</td>
						<td>
						<s:textfield id="merchant" name="goodsDTO.merchant" 
							cssClass="formInput" cssStyle="width:150px;" maxlength="20" onkeyup="query(1);"
								theme="simple" />
						</td>
						<td>
							商品状态:
						</td>
						<td>
								<s:select name="goodsDTO.status" id="status" 
								headerKey="-1"  headerValue="全部"   value="1"
 								list="#session.goodsSessionStatus" listKey="key" listValue="value" 
 								cssStyle="width:153.5px;" cssClass="formSelect" theme="simple"/> 
						</td>
					</tr>
					<tr>
						<td>商品分类：</td>
						<td>
							<s:select name="goodsDTO.goodsTypeId" id="goodsTypeId" 
								headerKey="-1"  headerValue="全部"   
 								list="#session.goodsTypes" listKey="key" listValue="value" 
 								cssStyle="width:153.5px;" cssClass="formSelect" theme="simple"/> 
						</td>
						<td>型号：</td>
						<td>
							<s:textfield id="model" name="goodsDTO.model" 
							cssClass="formInput" cssStyle="width:150px;" maxlength="20" onkeyup="query(1);"
								theme="simple" />
						</td>
						<td>
							<input type="button" class="formButton" onclick="query(1);"
								value="查 询" />
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td> -->
<!-- 							进货日期: -->
<!-- 						</td> -->
<!-- 						<td> -->
<%-- 							<s:textfield id="beginDate" name="goodsDTO.beginDate" --%>
<%-- 								cssClass="formInput" --%>
<%-- 								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" --%>
<%-- 								cssStyle="width:150px;" maxlength="20" theme="simple" /> --%>
<!-- 						</td> -->
<!-- 						<td> -->
<!-- 							至： -->
<!-- 						</td> -->
<!-- 						<td> -->
<%-- 							<s:textfield id="endDate" name="goodsDTO.endDate" --%>
<%-- 								cssClass="formInput" --%>
<%-- 								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" --%>
<%-- 								cssStyle="width:150px;" maxlength="20" theme="simple" /> --%>
<!-- 						</td> -->
<!-- 						<td> -->
<!-- 							<input type="button" class="formButton" onclick="query(1);" -->
<!-- 								value="查 询" /> -->
<!-- 						</td> -->
<!-- 					</tr> -->
				</table>
		</form>
			</div>
		<table width="100%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="5%">
					<a name="id" class="sort">商品编号</a>
				</th>
				<th width="5%">
					<a name="goodsName" class="sort">商品名称</a>
				</th>
				<th width="5%">
					<a name="brand" class="sort">品牌</a>
				</th>
				<th width="5%">
					<a name="goodsTypeName" class="sort">商品类别</a>
				</th>
				<th width="5%">
					<a name="model" class="sort">型号</a>
				</th>
				<th width="5%">
					<a name="price" class="sort">价格</a>
				</th>
				<th width="5%">
					<a name="count" class="sort">库存数量</a>
				</th>
				<th width="5%">
					<a name="merchant" class="sort">厂家名称</a>
				</th>
				<th width="5%">
					状态
				</th>
				<th width="8%">
					<a name="purchaseDate" class="sort">进货日期</a>
				</th>
				<th width="10%">
					操作
				</th>
			</tr>
		</table>
		<div class="listBottom">
		<div class="Fl">
<%-- 			<my:permission key='sy-9103-02' value='用户添加'> --%>
				<input type="button" class="formButton" value="新入库" onclick="go('managers/goods!addUI')"/>
<%-- 			</my:permission> --%>
		</div>
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
		</div>
	</body>
</html>