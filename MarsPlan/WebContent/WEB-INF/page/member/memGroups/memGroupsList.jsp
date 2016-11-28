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
		<title>会员群组管理</title>
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
		<script src="js/common.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script type="text/javascript">
		//查询方法
		function query(page) {
		
		
		
			var groupName = $.trim($("#groupNamee").val());
			var name = $.trim($("#name").val());
            var status = $("#status").val(); 
			var params = {
				"memGroupsDTO.groupName" : groupName,
		        "memGroupsDTO.name" : name,
		        "memGroupsDTO.status":status,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "memGroupsDTO.page" : page
		    }; 
		   ajaxData("member/memGroups!jsonPageList",params);
		}
	</script>
	</head>
	<body onload="query(${memGroupsDTO.page });">
		
		<div class="Position">
			当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 会员群组管理
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>

		<div class="search">
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<img src="images/fd.jpg" />
					</td>
					<td>
						群组名称:
					</td>
					<td>
						<s:textfield id="groupNamee" name="memGroupsDTO.groupName"
							cssClass="formInput" maxlength="20" theme="simple" />
					</td>
					<td>
						机构名称:
					</td>
					<td>
						<s:textfield id="name" name="memGroupsDTO.name"
							cssClass="formInput" maxlength="20" theme="simple" />
						<s:hidden name="memGroupsDTO.organId"></s:hidden>
					</td>
					<td>
						群组状态:
					</td>
					<td>
						<s:select id="status" list="#request.statusValues" headerKey="-1"
							headerValue="全部" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />
					</td>
					<td>
						<input type="button" class="formButton" onclick="query();"
							value="查 询" />
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
				<th width="8%">
					<a name="groupId" >群组编号</a>
				</th>
				<th width="10%">
					<a name="groupName" >群组名称</a>
				</th>
				<th width="10%">
					<a name="name" >所属机构</a>
				</th>
				<th width="10%">
					<a name="userName" >创建人</a>
				</th>
				<th width="10%">
					<a name="createTime" >创建时间</a>
				</th>
				<th width="5%">
					<a name="status" >群组状态</a>
				</th>
				<th width="8%">
					<a name="updateTime" >更新时间</a>
				</th>
				<th width="5%">
					相关操作
				</th>
			</tr>
		</table>
		
		<div class="listBottom">
			<div class="Fl">
				<s:if test="#session.user_session.userLevel!=2">
					<input type="button" class="formButton" value="添加"
						onclick="go('member/memGroups!addUI')" />
				</s:if>
			</div>
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
		</div>
	</body>
</html>