<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" +

			request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<base href="<%=basePath%>" />
		<title>站内信</title>
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
		<script src="js/jquery/jquery.ui.tabs.js"></script>
		<script src="js/common.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script src="js/datepicker/WdatePicker.js"></script>
		<script type="text/javascript">
	/**查询方法*/
	function query(page) {
		var title = $("#title").val();
		var params = {
			"internalMessageDTO.title" : title,
			"orderProperty" : $("#orderProperty").val(),
			"orderDirection" : $("#orderDirection").val(),
			"internalMessageDTO.page" : page
		};
		ajaxData("internalMessage/internalMessage!jsonPageList", params);
	}
</script>
	</head>
	<body onload="query(${internalMessageDTO.page})">
		<div class="Position">
			当前位置是：HOME &gt;&gt;站内信&gt;&gt; 站内信
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<div class="search">
			<s:if test="#session.user_session.userLevel==0">
			<my:permission key='sy-8301-01' value="发送站内信">
				<input type="button" class="formButton" value="发送站内信"
					onclick="go('internalMessage/internalMessage!addUI')" />
			</my:permission>
			</s:if>
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						标题:
					</td>
					<td>
						<s:textfield name="internalMessageDTO.title" id="title"
							cssClass="formInput" theme="simple" maxlength="20" />
					</td>
					<td>
						<input type="button" class="formButton" onclick="query();"
							value="查询" />
					</td>
				</tr>
			</table>
		</div>
		<table width="96%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="3%">
					序号
				</th>
				<th width="5%">
					标题
				</th>
				<th width="8%">
					内容
				</th>
				<th width="5%">
					发送人
				</th>
				<th width="5%">
					发送时间
				</th>
				<th width="8%">
					操作
				</th>
			</tr>
		</table>
		<div class="listBottom">
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
			<div class="Fl">
				<s:if test="#session.user_session.userLevel==0">
				<my:permission key='sy-8301-01' value="发送站内信">
				<input type="button" class="formButton" value="发送站内信"
					onclick="go('internalMessage/internalMessage!addUI')" />
			</my:permission>
			</s:if>
			</div>
		</div>
	</body>
</html>