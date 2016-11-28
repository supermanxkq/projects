<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<base href="<%=basePath%>" />
		<title>今日待办</title>
		<script src="js/jquery-1.8.2.min.js"></script>
		<script src="js/tips/jquery-1.2.6.pack.js"></script>
		<script src="js/tips/jquery.messager.js"></script>
		<link href="js/tips/animate.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
			type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="resources/css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/common.js"></script>
		<script type="text/javascript">
	// 查询方法
	function query(page) {
		var params = {
			"orderProperty" : $("#orderProperty").val(),
			"orderDirection" : $("#orderDirection").val(),
			"mainTaskDTO.page" : page
		};
		ajaxData("todayTodo/todayTodo!jsonPageList", params);
	}

	/**监听回车事件，添加主任务*/
	$(document).ready(function() {
		$("#mainTaskName").keydown(function(e) {
			var curKey = e.which;
			if (curKey == 13) {
				addMainTask();
			}
		});
	});
	/**添加主任务*/
	function addMainTask() {
		var mainTaskId = $("#mainTaskId").val();
		var mainTaskName =$.trim( $("#mainTaskName").val());
		if (mainTaskName.length != 0) {
			var params = {
				"mainTaskDTO.mainTaskId" : mainTaskId,
				"mainTaskDTO.mainTaskName" : mainTaskName
			};
			var url = "todayTodo/todayTodo!addSave";
			$.ajax( {
				async : false,
				url : url,
				data : params,
				dataType : "html",
				cache : false,
				type : "post",
				error : function(textStatus, errorThrown) {
					$(document).ready(function() {
						$.messager.lays(200, 40);
						$.messager.show('', '任务添加失败！', 4000);
					});
				},
				beforeSend : function() {
					$("#data_loading").removeClass().addClass(
							"animated flipInX").show();
					$("#data_loading").addClass("animate flipInX").show();
				},
				success : function(data, textStatus) {
					setTimeout(function() {
						$("#data_loading").removeClass().addClass(
								"animated flipOutX")
					});
					$("#data_loading").addClass("animate flipOutX").show();
					/**消息提示*/
					$(document).ready(function() {
						$.messager.lays(200, 40);
						$.messager.show('', '任务添加成功！', 4000);
					});
					$("#mainTaskName").val("");
					query(1);
				}
			});
		}
	}
	/**删除主任务*/
	function deleteResult(taskId) {
		deleteData('todayTodo/todayTodo!delete', taskId);
	}
</script>
		<style type="text/css">
.batch {
	position: absolute;
	right: 28px;
	cursor: pointer;
	font-size: 12px;
	color: #111;
	height: 20px;
	line-height: 20px;
	float: left;
	text-decoration: underline;
	padding: 0 16px;
	border-left: 1px dashed #e2e2e2;
	margin-top: -30px;
}
</style>
	</head>
	<body onload="query(${mainTaskDTO.page});">
		<div class="Position">
			当前位置是：今日待办 &gt;&gt; 今日待办
			<div id="data_loading" style="display: none;">
				<div
					style="padding: 0 10px; background: rgba(0, 0, 0, .6); display: inline-block; -ms-border-radius: 2px; -o-border-radius: 2px; border-radius: 2px; height: 22px; line-height: 22px;">
					处理中...
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<input type="hidden" name="method" id="methodId" />
		<s:hidden name="method" id="method" />
		<s:hidden name="mainTaskDTO.mainTaskId" id="mainTaskId" />
		<jsp:include page="/WEB-INF/page/todaytodo/batchAdd.jsp"></jsp:include>
		<table width="96%" border="0" cellpadding="0" cellspacing="0"
			style="margin-left: 25px; float: center;">
			<tr>
				<td>
					<s:textfield name="mainTaskDTO.mainTaskName" id="mainTaskName"
						maxlength="255" cssClass="textInput" theme="simple"
						placeHolder="添加任务并回车" cssStyle="width:1015px;" />
					<div class="batch" batch-add="">
						<a class='blue' href='javascript:void(0)'
							onclick="orderCancelShow()" title="批量添加">批量添加</a>
					</div>
				</td>
			</tr>
		</table>
		<br />
		<br />
		<table width="96%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="6%">
					选择
				</th>
				<th width="6%">
					优先级
				</th>
				<th width="6%">
					任务名称
				</th>
				<th width="6%">
					任务状态
				</th>
				<th width="6%">
					提醒方式
				</th>
				<th width="6%">
					提醒时间
				</th>
				<th width="6%">
					距截止日期
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