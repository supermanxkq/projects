<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>收集箱</title>
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
		<script src="js/jquery/jquery.ui.autocomplete.js"></script>
		<script src="js/jquery/jquery.ui.resizable.js"></script>
		<script src="js/jquery/jquery.ui.dialog.js"></script>
		<script src="js/common.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script src="js/datepicker/WdatePicker.js"></script>
		<link href="resources/css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/common.js"></script>
		<script src="js/pubValiPattern.js"></script>
		<script src="js/pubValidate.js"></script>
		<script type="text/javascript">
		/**设置为disabled*/
		$(document).ready(function (){
		       var method = document.getElementById("method"); 
		        if(method.value=='checkDetail'){
			          setInputDisabled();
			          $("#descr").attr("disabled","disabled");
			          $("#comments").attr("disabled","disabled");
		            }
		        });		
</script>
	</head>
	<body>
		<div class="Position">
			当前位置是：收集箱 &gt;&gt; 主任务查看
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<s:hidden name="subTaskDTO.mainTaskId" id="mainTaskIdForSubTask" />
		<s:hidden name="subTaskDTO.subTaskId" id="subTaskId" />
		<%--主任务修改对话框开始--%>
		<div id="dialog-confirm" style="display: block;" title="修改主任务">
			<s:form action="collection/collection" method="post" theme="simple">
				<s:hidden name="method" id="method" />
				<s:hidden name="mainTaskDTO.mainTaskId" id="mainTaskId" />
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formTable">
					<tr>
						<th style="text-align: center; width: 10%;">
							主任务名称:
						</th>
						<td style="text-align: left;">
							<s:textfield name="mainTaskDTO.mainTaskName" id="mainTaskName"
								cssClass="formInput" theme="simple" onblur="" />
						</td>
					</tr>
					<tr>
						<th style="text-align: center;">
							任务描述:
						</th>
						<td>
							<s:textarea name="mainTaskDTO.descr" id="descr"
								cssClass="formTextarea" cols="45" rows="20"
								cssStyle="resize:none;" />
						</td>
					</tr>
					<tr>
						<th style="text-align: center;">
							优先级:
						</th>
						<td width="30%">
							<s:select name="mainTaskDTO.level" id="level"
								list="#session.levelValue" listKey="key" listValue="value"
								cssClass="formSelect" theme="simple" />
						</td>
					</tr>
					<tr>
						<th style="text-align: center;">
							任务状态：
						</th>
						<td width="30%">
							<s:select name="mainTaskDTO.status" id="statusValue"
								list="#session.statusValue" listKey="key" listValue="value"
								cssStyle="width:153.5px;" cssClass="formSelect" theme="simple" />
						</td>
					</tr>
					<tr>
						<th style="text-align: center;">
							任务时间:
						</th>
						<td width="30%">
							<s:textfield id="createTime" name="mainTaskDTO.createTimeString"
								cssStyle="width:150px;" maxlength="20" theme="simple"
								cssClass="formInput" readonly="true" />
						</td>
					</tr>
					<tr>
						<th style="text-align: center;">
							截止时间：
						</th>
						<td width="30%">
							<s:textfield id="byDate" name="mainTaskDTO.byDateString"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
								cssStyle="width:150px;" maxlength="20" theme="simple"
								cssClass="formInput" />
						</td>
					</tr>
					<tr>
						<th style="text-align: center;">
							主任务评论：
						</th>
						<td width="30%">
							<s:textarea name="mainTaskDTO.comments" id="comments"
								cssClass="formTextarea" cols="45" rows="20"
								cssStyle="resize:none;" />
						</td>
					</tr>
					<tr>
						<th></th>
						<td>
							<input type="button" class="formButton" value="返回"
								onclick="go('finished/finished!list')" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		<%--主任务修改对话框结束--%>
	</body>
</html>