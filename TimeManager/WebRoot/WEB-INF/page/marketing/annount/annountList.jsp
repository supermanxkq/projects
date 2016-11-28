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
	<title>全站公告管理</title>
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
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
	//取消发布
		var  CancerReprotAnnount = function(url,id) {
			if(confirm("确认要取消发布？")){
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
		                	alert("取消发布成功!");
		                	query($("#currPage").text());
		            	}else if(data.ajaxResult == "ajaxfailure"){
		            		alert(data.msgResult);
		            	}else {
		            		alert("取消发布失败!");
		            	}
		    		}
				});
			}
}
      //发布
		var  reprotAnnount = function(url,id) {
					if(confirm("确认要发布？")){
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
				                	alert("发布成功!");
				                	query($("#currPage").text());
				            	}else if(data.ajaxResult == "ajaxfailure"){
				            		alert(data.msgResult);
				            	}else {
				            		alert("发布失败!");
				            	}
				    		}
						});
					}
		}
		//查询方法
		function query(page) {
		
			var announTitle = $.trim($("#announTitle").val());
			var status = $.trim($("#status").val());
			var author = $.trim($("#author").val());
            var createTime =$("#createTime").val(); 
            var stopTime =$("#stopTime").val(); 
			var params = {
				"annountDTO.AnnounTitle" : announTitle,
				"annountDTO.status" : status,
		        "annountDTO.Author" : author,
		        "annountDTO.CreateTime" : createTime,
		        "annountDTO.StopTime" : stopTime,
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		        "annountDTO.page" : page
		    }; 
		   ajaxData("marketing/annount!jsonPageList",params);// !是跳转的意思,相当于？
		}
		
	</script> 
</head>
<body onload="query(${annountDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 商城前台 &gt;&gt; 全站公告管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
	    <div class="Fl">
			<my:permission key='sy-7101-02' value='全站公告管理添加'>
				<input type="button" class="formButton" value="添加" onclick="go('marketing/annount!addUI')"/>
			</my:permission>
		</div>
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
			    <td>公告标题:</td>
				<td><s:textfield id="announTitle" name="annountDTO.AnnounTitle" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>发布人:</td>
				<td><s:textfield id="author" name="annountDTO.Author" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>
						状态:
				</td>
				<td>
						<s:select name="annountDTO.status" id="status"
							list="#request.statusList" headerKey="-1" headerValue="全部" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />
							
				</td>
				
	        	<td>创建时间:</td>
				<td>
				       <s:textfield id="createTime" name="annountDTO.CreateTime" 
				       readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
				       cssStyle="width:150px;" maxlength="20" theme="simple"/>
			    </td>
				<td>至:</td>
				<td>
				       <s:textfield id="stopTime" name="annountDTO.StopTime" 
				       readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
				       cssStyle="width:150px;" maxlength="20" theme="simple"/>
				</td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%"><a name="announId" class="sort">公告编号</a></th>
			<th width="10%"><a name="announTitle" class="sort">公告标题</a></th>
			<th width="10%"><a name="author" class="sort">发布人</a></th>
			<th width="10%"><a name="status" class="sort">状态</a></th>
			<th width="10%"><a name="isTop" class="sort">是否置顶</a></th>
			<th width="5%"><a name="announContent" class="sort">最近发布时间</a></th>
			<th width="5%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<my:permission key='sy-7101-02' value='全站公告管理添加'>
				<input type="button" class="formButton" value="添加" onclick="go('marketing/annount!addUI')"/>
			</my:permission>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>