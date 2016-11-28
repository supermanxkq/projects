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
	<script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">



</script> 
</head>
<body onload="loadset();">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 会员群组管理
	</div>
	<s:form method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>
	<input type="hidden" id="flag" />
	<input type="hidden" id="flag1"/>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
			   <th align="right" width="20%" >群组编号：</th>
		        <td width="30%">
							<s:textfield name="memGroupsDTO.groupId" id="groupId" readonly="true" maxlength="6"
							cssClass="formInput" theme="simple" />
		        </td>
		  		<th align="right" width="20%">群组名称：</th>
		        <td width="30%">
							<s:textfield name="memGroupsDTO.groupName" id="groupName" readonly="true" maxlength="6"
							cssClass="formInput" theme="simple"  />
		        </td>
			</tr>
		 	<tr >
		 	<th align="right" width="20%">创建时间：</th>
		        <td width="30%">
							<s:textfield name="memGroupsDTO.createTime" id="createTime"  readonly="true" maxlength="6"
							cssClass="formInput" theme="simple"  />
		        </td>
				<th align="right" width="20%">更新时间：</th>
		        <td width="30%">
							<s:textfield name="memGroupsDTO.updateTime" id="updateTime"  readonly="true" maxlength="6"
							cssClass="formInput" theme="simple"  />
		        </td>
		  	</tr>
		  	<tr>
				<th align="right" >所属机构：</th>
		        <td width="30%">
							<s:textfield name="memGroupsDTO.name" id="name"  readonly="true" maxlength="6"
							cssClass="formInput" theme="simple"  />
		        </td>
		        <th align="right" width="20%">创建人：</th>
		        <td width="30%">
							<s:textfield name="memGroupsDTO.userName" id="userName" readonly="true" maxlength="6"
							cssClass="formInput" theme="simple"  />
		        </td>
			</tr>
			<tr>
			   <th align="right">群组状态：</th>
				<td width="30%">	
							<s:textfield name="memGroupsDTO.status" id="status" readonly="true"  maxlength="6"
							cssClass="formInput" theme="simple"  />
               </td>  
				<th align="right">
						会员群组描述：
				</th>
				<td colspan="3">
					<s:textarea name="memGroupsDTO.groupDesc" id="groupDesc"
							cssClass="formTextarea" cols="45" rows="20" readonly="true" />
				</td>
			</tr>
		</table>
		
		<table width="96%" id="MembersTb" class="listTable" cellpadding="0"
					cellspacing="0">
				
					<tr>
						<th width="8%"><a name="memId" >会员编号</a></th>
			            <th width="10%"><a name="memRealName" >会员昵称</a></th>
			            <th width="5%"><a name="status" >会员状态</a></th>
			          	<!--<th width="10%"><a name="birthday" >会员生日</a></th>
			           	<th width="10%"><a name="career" >会员职业</a></th>
			            <th width="10%"><a name="cultDegree" >会员学历</a></th>-->
			            </tr>
					<c:forEach items="${memGroupsDTO.member}" var="member">
						<tr>
							<td>${member.memId }</td>
							<td>${member.memRealName }</td>
							<c:if test="${member.status==9 }">
								<td>删除</td>
				
							</c:if>
							<c:if test="${member.status==1 }">
								<td>正常</td>
							</c:if>
							<c:if test="${member.status==2 }">
								<td>禁用</td>
							</c:if>
						</tr>
					</c:forEach>
		</table>
	
	 <div class="formTableBottom">
		<input type="button" class="formButton" value="返 回" onclick="go('member/memGroups!list')"/>
	 </div>
	 </s:form>
</body>

</html>