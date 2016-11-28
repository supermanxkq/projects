<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.paySystem.ic.web.dto.system.UserSession" %>
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
	<title>参数使用关系管理</title>
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
	<script src="js/datepicker/WdatePicker.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
		
		//修改方法
		var check = function() {
		   var orgName = $("#name").val();
		   var merName = $("#merName").val();
		   var messName = $("#messName").val();
		   var beginTime = $("#beginTime").val();
		   var useLives = $("#useLives").val();
		   var userLevel="<%=((UserSession)(session.getAttribute("user_session"))).getUserLevel()%>";
		   
		   if(userLevel==0&&""==orgName){
                alert("请选择机构!");
                return false;
			}
		   if(userLevel==1&&""==merName){
                alert("请选择商户!");
                return false;
		    }
		   if(""==messName){
                alert("请选择参数名称!");
                return false;
			}
		   if(""==useLives){
		        alert("请填写使用期限!");
                return false;   		    
		    }
		   if(""==beginTime){
                alert("请选择开始时间!");
                return false;
			}	 
		}
    </script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 短信费管理&gt;&gt; 短信参数管理
	</div>
	<jsp:include page="/WEB-INF/page/base/terminals/mercHelps.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/page/base/organs/organHelp.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/page/message/messParamHelp.jsp"></jsp:include>
	<s:form action="message/messagerelation" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method"/>
	
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
				<s:hidden name="messDto.mprId" id="mprId"></s:hidden>
				<s:if test="method=='addSave'">
					<s:if test="#session.user_session.userLevel==0">
				    <th align="right"><span class="Color5">* </span>机构名称:</th>
					<td width="30%"><s:textfield readonly="true" name="messDto.orgName" id="name" cssClass="formInput" theme="simple"/>
			            <s:hidden name="messDto.orgId" id="organId"></s:hidden>
					    <img alt="查找机构" src="images/search.gif" style="cursor:pointer;" onclick="ajaxOrganId();"/>
			        </td>
					</s:if>
					<s:else>
					<th align="right"><span class="Color5">* </span>商户名称:</th>
					<td width="30%"><s:textfield name="messDto.merName" id="merName" readonly="true" maxlength="20" cssClass="formInput" theme="simple"/>
					    <s:hidden name="messDto.merId" id="merId"/>
					    <img alt="查找商户" src="images/search.gif" style="cursor:pointer;" onclick="ajaxMerc();"/>
					</td>
					</s:else> 
				</s:if>
				<s:else>
					<s:if test="#session.user_session.userLevel==0">
					<th align="right">机构名称:</th>
					<td width="30%"><s:property value="messDto.orgName"/>
			            <s:hidden name="messDto.orgId" id="organId"></s:hidden>
			        </td>
			        </s:if>
			        <s:else>
					<th align="right">商户名称:</th>
					<td width="30%"><s:property value="messDto.merName"/>
					    <s:hidden name="messDto.merId" id="merId"/>
					</td>
					</s:else>
				</s:else>
				<th align="right"><span class="Color5">* </span>参数名称:</th>
		 		<td width="30%"><s:textfield name="messDto.messName" id="messName" readonly="true" maxlength="20" cssClass="formInput" theme="simple"/>
				  <s:hidden name="messDto.mfpId" id="mfpId"/>
				  <img alt="查找参数" src="images/search.gif" style="cursor:pointer;" onclick="ajaxMfp();"/>
				</td>
			</tr>
			<tr>
		 		<th align="right"><span class="Color5">* </span>使用状态:</th>
				<td width="30%"><s:select name="messDto.state" id="state" list="#request.status" listKey="key" listValue="value" cssStyle="width:156px;" cssClass="formSelect" theme="simple"/></td>
				<th align="right"><span class="Color5">* </span>使用期限:</th>
		 		<td><s:textfield id="useLives" name="messDto.useLives" value="1" maxlength="3"  onkeypress="return onlyNum(this);" onkeyup="replaceToNum(this);" cssStyle="width:147.5px;" cssClass="formInput" theme="simple"/>(/月)</td>	
		    </tr>
		 	<tr>		 		
		      	<th align="right"><span class="Color5">* </span>开始时间:</th>
		        <td width="30%"><s:textfield id="beginTime" name="messDto.beginTime" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" cssStyle="width:150px;" maxlength="20" theme="simple"/></td>
		  		<th align="right"><span class="Color5"></span></th>
		 		<td></td>
		  	</tr>
		  	<tr>
		  		<th align="right">具体描述：</th>
		        <td>
					<s:textarea name="messDto.mfpDesc" id="mfpDesc" rows="2" cols="50" />
				</td>
		 	</tr>
		 	</table>	
	 <div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1602-02' value='参数使用关系添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-1602-03' value='参数使用关系修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		<input type="button" class="formButton" value="返 回" onclick="go('message/messagerelation!list')"/>
	 </div>
	 </s:form>
</body>
</html>