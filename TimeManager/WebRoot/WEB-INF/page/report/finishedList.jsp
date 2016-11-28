
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
		<script type="text/javascript">
		//查询方法
		function query(page) {
			/**起始时间*/
		    var beginDate = $("#beginDate").val();
		    /**结束时间*/
		    var endDate = $("#endDate").val();
		    /**任务名称*/
		    var mainTaskName=$("#mainTaskName").val();
		    /**任务状态*/
		    var status=$("#status").val();
			/**Ajax传递给struts的参数，用来实例化对象*/
			var params = {
				"mainTaskDTO.status":status,
				"mainTaskDTO.mainTaskName":mainTaskName,
				"mainTaskDTO.beginDate":beginDate,
			    "mainTaskDTO.endDate":endDate,
		        "orderProperty":$("#orderProperty").val(),
		        "orderDirection":$("#orderDirection").val(),
		        "mainTaskDTO.page" : page
		    };
		    /**使用Ajax请求响应的Action方法，并且传递参数*/
		   ajaxData("finished/finished!jsonPageList",params);
		}
	</script>
	</head>
	<body onload="query(${mainTaskDTO.page});">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 报表&gt;&gt;报表
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<form id="form1" name="form1" action="finished/finished!export"
			method="post">
			<div class="search">
				<table class="searchTable" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<td>
							任务名称:
						</td>
						<td>
							<s:textfield id="mainTaskName" name="mainTaskDTO.mainTaskName"
								cssClass="formInput" cssStyle="width:150px;" maxlength="20"
								theme="simple" />
						</td>
						<td>
							任务状态:
						</td>
						<td>
								<s:select name="mainTaskDTO.status" id="status" 
								headerKey="-1"  headerValue="全部"  
								list="#session.statusValue" listKey="key" listValue="value"
								cssStyle="width:153.5px;" cssClass="formSelect" theme="simple" onchange="query(1);"/>
						</td>
					</tr>
					<tr>
						<td>
							创建起始时间:
						</td>
						<td>
							<s:textfield id="beginDate" name="mainTaskDTO.beginDate"
								cssClass="formInput"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
								cssStyle="width:150px;" maxlength="20" theme="simple" />
						</td>
						<td>
							终止时间：
						</td>
						<td>
							<s:textfield id="endDate" name="mainTaskDTO.endDate"
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
				<th width="10%">
					<a name="mainTaskId" class="sort">任务编号</a>
				</th>
				<th width="10%">
					<a name="level" class="sort">优先级</a>
				</th>
				<th width="10%">
					<a name="mainTaskName" class="sort">任务名称</a>
				</th>
				<th width="10%">
					<a name="status" class="sort">状态</a>
				</th>
				<th width="10%">
					<a name="createTime" class="sort">创建时间</a>
				</th>
				<th width="10%">
					<a name="byDate" class="sort">截止时间</a>
				</th>
				<th width="10%">
					操作
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