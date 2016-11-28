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
	<title>全站广告管理</title>
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
	
		//查询方法
		function query(page) {
			var advertName = $.trim($("#advertName").val());
			var mediaType = $.trim($("#mediaType").val());
			var params = {
				"advertmaDTO.advertName" : advertName,
				"advertmaDTO.mediaType" : mediaType,
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		        "advertmaDTO.page" : page
		    }; 
		   ajaxData("marketing/advertma!jsonPageList",params);// !是跳转的意思,相当于？
		}
		function checkAdvertMa(){
			 var actionUrl = "marketing/advertma!findAdvertMaIsUse";
			 $.ajax({  
				 	async:false,
			        url:actionUrl,    
			        dataType:"json",
			        cache:false, 
			        async : false,
			        type:"POST",
			        error:function(textStatus, errorThrown) {   
			    		alert("系统ajax交互错误!");  
			        },
			        success:function(data, textStatus) {
				        if(data.total<6){
				        	go('marketing/advertma!addUI');
					        }else{
                                 alert("广告位置已满,请先停用或者删除其他广告")
						        }

				        
			        	
			        }
			    });




			}
		//"go('marketing/advertma!addUI')"
	</script> 
</head>
<body onload="query(${advertmaDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 商城前台 &gt;&gt; 全站广告管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
	    <div class="Fl">
			<my:permission key='sy-7201-02' value='全站广告管理添加'>
				<input type="button" class="formButton" value="添加" onclick="checkAdvertMa()"/>
			</my:permission>
		</div>
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
			    <td>广告名称:</td>
				<td><s:textfield id="advertName" name="advertmaDTO.advertName" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>媒介类型:</td>
				<td><s:select name="advertmaDTO.mediaType" id="mediaType"
							list="#request.mediaTypeList" headerKey="-1" headerValue="全部" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" /></td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%"><a name="advertName" class="sort">广告名称</a></th>
			<th width="10%"><a name="positionType" class="sort">广告位置</a></th>
			<th width="10%"><a name="mediaType" class="sort">媒介类型</a></th>
			<th width="10%"><a name="beginTime" class="sort">开始日期</a></th>
			<th width="10%"><a name="endTime" class="sort">结束日期</a></th>
			<th width="5%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<my:permission key='sy-7201-02' value='全站广告管理添加'>
				<input type="button" class="formButton" value="添加" onclick="checkAdvertMa()"/>
			</my:permission>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>