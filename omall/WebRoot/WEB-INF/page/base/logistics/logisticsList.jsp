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
	<title>物流公司管理</title>
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
		 var logistIdFlag = true;
		//查询方法
		function query(page) {
			var logistId = $.trim($("#logistId").val());
			var logistName = $.trim($("#logistName").val());
			if(!(logistIdFlag)){

               alert("信息填写有误，请按提示信息重新填写!");
               
               return false;
              }
           
			var params = {
				"logisticsDTO.logistId" : logistId,
				"logisticsDTO.logistName" : logistName,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "logisticsDTO.page" : page
		    }; 
		   ajaxData("base/logistics!jsonPageList",params);
		}
		
		function logistIdBlur(obj){
			var logistId = $.trim($("#logistId").val());
			 $("#logistIdErrorMsg").hide();
			 logistIdFlag = true;
			if(logistId!=""){
				
	            if(logistId.length>10){
	            	logistIdFlag=false;
	            	
	            	 pubErrorShow($("#logistIdErrorMsg"),"编号长度不能超过10位数!");
	            	
	            }
			}
			
		}
	</script> 
</head>
<body onload="query(${logisticsDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 物流公司管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">		<div class="Fl">
			<my:permission key='sy-1901-02' value='物流公司信息添加'>
				<input type="button" class="formButton" value="添加" onclick="go('base/logistics!addUI')"/>
			</my:permission>
		</div>
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
			    <td>物流编号:</td>
				<td><s:textfield id="logistId" name="logisticsDTO.logistId" cssClass="formInput" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') "  maxlength="9" theme="simple"/><label id="logistIdErrorMsg" style="display: none;"></label></td>
				<td>物流公司:</td>
				<td><s:textfield id="logistName" name="logisticsDTO.logistName" cssClass="formInput" maxlength="30" theme="simple"/></td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0">
		<tr>
			<th width="3%">序号</th>
			<th width="5%">物流编号</th>
			<th width="10%">物流公司</th>
			<th width="10%">官方地址</th>
			<th width="6%">创建时间</th>
			<th width="3%">使用状态</th>
			<th width="5%">操作人</th>
			<th width="6%">更新时间</th>
			<th width="5%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<my:permission key='sy-1901-02' value='物流公司信息添加'>
				<input type="button" class="formButton" value="添加" onclick="go('base/logistics!addUI')"/>
			</my:permission>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>