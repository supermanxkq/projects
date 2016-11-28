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
	<script src="js/pubValidate.js"></script>
   <script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">	
	$(document).ready(function (){
	       var method = document.getElementById("method");
	       if(method.value=='editSave'){
	            $("#groupId").attr("readonly","readonly");
	        	$("#groupId").css("background","#F2F2F2");
	            $("#groupName").attr("readonly","readonly");
	            $("#groupName").css("background-color-color","#F2F2F2");
	            $("#userName").attr("readonly","readonly");
	            $("#userName").css("background-color","#F2F2F2");
	            $("#name").attr("readonly","readonly");
	            $("#name").css("background-color","#F2F2F2");
	            $("#status").attr("readonly","readonly");
	            $("#status").css("background-color","#F2F2F2");
	            $("#groupDesc").attr("readonly","readonly");
	            $("#groupDesc").css("background-color","#F2F2F2");
	       } 
	        if(method.value=='checkUI'){
	             setInputDisabled();
	            $("#perType").attr("readonly","readonly");
	            $("#sex").attr("readonly","readonly");
	            $("#status").attr("readonly","readonly"); 
	            }
	        });
	       
	</script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 会员群组管理
	</div>
	<s:form action="member/memGroups" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>
	<s:hidden name="memGroupsDTO.isSalePointStr" id="isSalePointStr"/>
	<div class="List_tit">群组信息</div>
	<s:if test="#session.user_session.userLevel!=2">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<s:hidden name="memGroupsDTO.createTime" id="createTime"></s:hidden>
			<tr>
				<th align="right" width="20%">群组编号：</th>
		        <td width="30%">
		        <s:select name="memGroupsDTO.groupId" id="groupId"
								list="#request.groupId" listKey="key" listValue="value"
								headerKey="-1" headerValue="--请选择--" cssClass="formInput"
								theme="simple" /></td>
		        <th align="right" width="20%">群组名称：</th>
		        <td width="30%">
		        	<s:textfield name="memGroupsDTO.groupName"  id="groupName" maxlength="20" cssClass="formInput"/> <label id="warnMsg" style="display: none;"></label> <label id="errorMsg" style="display: none;"></label>
		        </td>
			</tr>
			<tr>
		        <th align="right">群组状态：</th>
		        <td><s:select id="status" name="memGroupsDTO.status" list="#request.statusValues" listKey="key" listValue="value" cssClass="formSelect"/></td>
		        <th align="right">会员群组描述：</th>
				<td colspan="3">
					<s:textarea name="memGroupsDTO.groupDesc" id="groupDesc"
							cssClass="formTextarea" cols="45" rows="20" />
				</td>
			</tr>
			<!-- 
			<tr>
			 <th align="right" width="20%">创建人：</th>
		        <td width="30%">
							<s:textfield name="memGroupsDTO.userName" id="userName"  maxlength="6"
							cssClass="formInput" theme="simple"/>
		        </td>
		       <th align="right" width="20%">所属机构：</th>
		        <td width="30%">
					<s:textfield id="name" name="memGroupsDTO.name" cssClass="formInput" maxlength="20" theme="simple"/>
				    <s:hidden name="memGroupsDTO.organId"></s:hidden>
		        </td>
			</tr>
		    -->
	 	</table>
	 	<div class="List_tit">群组会员信息</div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		<tr>
		 <th>出生日期范围:</th>
		 <td>
			<input name="saleCardRecordDTO.from" id="from" class="Wdate" type="text" onFocus="WdatePicker({skin:'blue',maxDate:'#F{$dp.$D(\'to\')||\'2020-10-01\'}'})" readonly="readonly"/> 
			      	至
			<input name="saleCardRecordDTO.to" id="to" class="Wdate" type="text" onFocus="WdatePicker({skin:'blue',minDate:'#F{$dp.$D(\'from\')}',maxDate:'2020-10-01'})" readonly="readonly"/>
		 </td>
		 <!-- 
		 <th>职业:</th>
		 <td width="30%">
			<s:textfield name="accRecordDTO.cardNoView" id="cardNoView" onkeyup= "replaceToNumPoint(this)"  maxlength="6"
				cssClass="formInput" theme="simple"  />
		 </td>
		  <th>证件类型:</th>
		 <td>
			<s:select name="saleOrderDTO.status" id="status"
								list="#request.statusValues" listKey="key" listValue="value"
								headerKey="-1" headerValue="--请选择--" cssClass="formInput"
								theme="simple" />
		 </td>
		  -->
		 <td>
			<input type="button" class="formButton" onclick="query();"
								value="查 询" />
		 </td>
		 </tr>
		 <tr>
		 <th>性别:</th>
		 <td>
			<s:select name="saleOrderDTO.status" id="status"
								list="#request.statusValues" listKey="key" listValue="value"
								headerKey="-1" headerValue="--请选择--" cssClass="formInput"
								theme="simple" />
		 </td>
		 <th>地区:</th>
		 <td>
			<s:select name="saleOrderDTO.status" id="status"
								list="#request.statusValues" listKey="key" listValue="value"
								headerKey="-1" headerValue="--请选择--" cssClass="formInput"
								theme="simple" />
		</td>
		<!-- 
		<th>学历：</th>
		<td >
			<s:textfield name="accRecordDTO.cardNoView" id="cardNoView" onkeyup= "replaceToNumPoint(this)"  maxlength="6"
					cssClass="formInput" theme="simple" onblur="queryCardNo();" />
				     <span class="Color5" id="cardNoViewValue" ></span>
		 </td>
		  -->
		 </tr>
		 <tr>
		 <th>可添加会员:</th>
		 <td>
		   <s:select name="" id="" multiple="multiple" list="#request.statusValues" listKey="key" 
		 listValue="value" cssClass="formInput" theme="simple"></s:select></td>
		 <td><input type="button" class="formButton" onclick="query();"
								value="《《" name="添加"/>
		 </td>
		 <td><input type="button" class="formButton" onclick="query();"
								value="《《" name="移除"/>
		 </td>
				 <th>已添加会员:</th>
		 <td>
		   <s:select name="" id="" multiple="multiple" list="#request.statusValues" listKey="key" 
		 listValue="value" cssClass="formInput" theme="simple"></s:select>
		 </td>
		 </tr>	
		</table>
		</s:if>
	<div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1305-02' value='会员群组添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" />
		 	</my:permission>
		</s:if>
		    <input type="button" class="formButton" value="取 消" onclick="go('member/memGroups!list')"/>
		<s:else>
			<my:permission key='sy-1305-03' value='会员群组修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" />
		 	</my:permission>
		</s:else>
		<input type="button" class="formButton" value="取 消" onclick="go('member/memGroups!list')"/>
	 </div>
	</s:form>
</body> 
</html>