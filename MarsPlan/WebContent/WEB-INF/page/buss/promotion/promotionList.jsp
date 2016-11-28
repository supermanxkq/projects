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
	<title>线上活动管理</title>
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
			var proHost = $.trim($("#proHost").val());
			var proname = $.trim($("#proname").val());
			var hostSign = $.trim($("#hostSign").val());
			var status = $.trim($("#status").val());
			var proType = $.trim($("#proType").val());
			var proItem = $.trim($("#proItem").val());
           
			var params = {
				"promotionDTO.proHost" : proHost,
				"promotionDTO.proname" : proname,
				"promotionDTO.hostSign" : hostSign,
				"promotionDTO.status" : status,
				"promotionDTO.proType" : proType,
				"promotionDTO.proItem" : proItem,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "promotionDTO.page" : page
		    }; 
		   ajaxData("buss/promotion!jsonPageList",params);
		}
		
		
		//开始招募
		var openRecruit = function(url,id) {
			if(confirm("确认要启动招募？")){
				var params = {
		    		"id" : id
				};
				
				$.ajax({
		    		url : url,   
		    		data : params,   
		    		dataType : "json",   
		    		cache : false,  
		    		type : "POST", 
		    		error : function(textStatus, errorThrown) {
						alert("系统ajax交互错误!");
		    		},
		    		success : function(data, textStatus) {
		    			if (data.ajaxResult == "ajaxsuccess") {
		                	alert("招募启动成功!");
		                	query($("#currPage").text());
		            	}else if(data.ajaxResult == "ajaxfailure"){
		            		alert(data.msgResult);
		            	}else {
		            		alert("招募启动失败!");
		            	}
		    		}
				});
			}
		}
		
		
		//停止招募
		var stopRecruit = function(url,id) {
			if(confirm("确认要停止招募？")){
				var params = {
		    		"id" : id
				};
				
				$.ajax({
		    		url : url,   
		    		data : params,   
		    		dataType : "json",   
		    		cache : false,  
		    		type : "POST", 
		    		error : function(textStatus, errorThrown) {
						alert("系统ajax交互错误!");
		    		},
		    		success : function(data, textStatus) {
		    			if (data.ajaxResult == "ajaxsuccess") {
		                	alert("招募停止成功!");
		                	query($("#currPage").text());
		            	}else if(data.ajaxResult == "ajaxfailure"){
		            		alert(data.msgResult);
		            	}else {
		            		alert("招募停止失败!");
		            	}
		    		}
				});
			}
		}
		
		//终止活动
		var stopPromotion = function(url,id) {
			if(confirm("确认要终止活动？")){
				var params = {
		    		"id" : id
				};
				
				$.ajax({
		    		url : url,   
		    		data : params,   
		    		dataType : "json",   
		    		cache : false,  
		    		type : "POST", 
		    		error : function(textStatus, errorThrown) {
						alert("系统ajax交互错误!");
		    		},
		    		success : function(data, textStatus) {
		    			if (data.ajaxResult == "ajaxsuccess") {
		                	alert("终止活动成功!");
		                	query($("#currPage").text());
		            	}else if(data.ajaxResult == "ajaxfailure"){
		            		alert(data.msgResult);
		            	}else {
		            		alert("终止活动失败!");
		            	}
		    		}
				});
			}
		}
		
		//开始活动
		var beginPromotion = function(url,id) {
			if(confirm("确认要开始活动？")){
				var params = {
		    		"id" : id
				};
				
				$.ajax({
		    		url : url,   
		    		data : params,   
		    		dataType : "json",   
		    		cache : false,  
		    		type : "POST", 
		    		error : function(textStatus, errorThrown) {
						alert("系统ajax交互错误!");
		    		},
		    		success : function(data, textStatus) {
		    			if (data.ajaxResult == "ajaxsuccess") {
		                	alert("开始活动成功!");
		                	query($("#currPage").text());
		            	}else if(data.ajaxResult == "ajaxfailure"){
		            		alert(data.msgResult);
		            	}else {
		            		alert("开始活动失败!");
		            	}
		    		}
				});
			}
		}
			
		
		
		
	</script> 
</head>
<body onload="query(${promotionDTO.page });">
	<div class="Position">
		当前位置是：营销中心 &gt;&gt; 活动管理 &gt;&gt; 线上活动管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	
	
	<div class="search">		
	    
	<div class="Fl" style="padding-top:25px;">
			<my:permission key='sy-8101-02' value='线上活动信息添加'>
				<input type="button" class="formButton" value="添加" onclick="go('buss/promotion!addUI')"/>
			</my:permission>
		</div>	
		<table class="searchTable" cellpadding="0" cellspacing="0">
		  
			<tr>
			  <s:if test="#session.user_session.userLevel==0">
			
			    <td>商户名称:</td>
				<td><s:textfield id="proHost" name="promotionDTO.proHost" cssClass="formInput" maxlength="30" theme="simple"/></td>
			  </s:if>
				<td>活动名称:</td>
				<td><s:textfield id="proname" name="promotionDTO.proname" cssClass="formInput" maxlength="30" theme="simple"/></td>
				<td>活动类型:</td>
				<td><s:select name="promotionDTO.hostSign" id="hostSign"  list="#request.hostSign" listKey="key" listValue="value" headerKey="-1" headerValue="全部"
				 cssClass="formInput" theme="simple"/></td>
				
				 </tr><tr>
				 
			
				 <td>活动状态:</td>
				<td><s:select name="promotionDTO.status" id="status"  list="#request.status" listKey="key" listValue="value" headerKey="-1" headerValue="全部"
				 cssClass="formInput" theme="simple"/></td>
				  
				 <td>活动性质:</td>
				<td><s:select name="promotionDTO.proType" id="proType"  list="#request.proType" listKey="key" listValue="value" headerKey="-1" headerValue="全部"
				 cssClass="formInput" theme="simple"/></td>
				 <td>活动名目:</td>
				<td><s:select name="promotionDTO.proItem" id="proItem"  list="#request.proItem" listKey="key" listValue="value" headerKey="-1" headerValue="全部"
				 cssClass="formInput" theme="simple"/></td>
	        	<td align="right"><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
		 
		 
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0">
		<tr>
			<th width="2%">序号</th>
			<th width="5%">活动名称</th>
			<th width="3%">活动状态</th>
			<th width="7%">举办单位</th>
			<th width="3%">活动性质</th>
			<th width="3%">活动类型</th>
			<th width="3%">参与单位数</th>
			<th width="3%">参与商品数</th>
			<th width="6%">开始时间</th>
			<th width="6%">结束时间</th>
			<th width="11%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			
			<my:permission key='sy-8101-02' value='线上活动信息添加'>
				<input type="button" class="formButton" value="添加" onclick="go('buss/promotion!addUI')"/>
			</my:permission>
			
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>