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
	<title>访问流量统计报表</title>
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
	<script src="js/jquery/jquery.ui.tabs.js"></script>
	<script src="js/common.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script src="js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1252891797'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s23.cnzz.com/z_stat.php%3Fid%3D1252891797%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
	<script type="text/javascript">
	
		//查询方法
		function query(page) {
		    var beginDate = $("#beginDate").val();
		    var endDate = $("#endDate").val();
			
			var params = {			
				"visitSlowRecordDTO.beginDate":beginDate,
			    "visitSlowRecordDTO.endDate":endDate,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "visitSlowRecordDTO.page" : page
		    };
		   ajaxData("report/visitorSlowRecord!jsonPageList",params);
		}
		$(function() {
			var $tabs = $("#tabs").tabs();	
		});
		
	function drawReport(){
		var beginDate = $("#beginDate").val();
	    var endDate = $("#endDate").val();
	
		var params = {
			"visitSlowRecordDTO.beginDate":beginDate,
		    "visitSlowRecordDTO.endDate":endDate,
	        "orderProperty" : $("#orderProperty").val(),
	        "orderDirection" : $("#orderDirection").val(),
	        "visitSlowRecordDTO.page" : 1
	    };
	    $.ajax({
	    	url : "report/visitorSlowRecord!demoMap", 
	        data : params,   
	        cache : false, 
	        type : "POST",
	        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误!");	    
		    }, 
		    success : function(data, textStatus) {
		    	$("#3DColumn").html(data);
		    }
	    });
	}
	</script> 
</head>
<body onload="query(${memcaDto.page});">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 访客流量统计报表 &gt;&gt; 访客流量统计报表查看
	</div>
	<div id="tabs">
	<ul>
		<li><a href="#tabs-1">访客流量明细查看</a></li>
		<li><a href="#tabs-2" onclick="drawReport();">访客流量统计报表</a></li>
	</ul>
	<div id="tabs-1">
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<form id="form1" name="form1" action="" method="post" >
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">		
			<tr>			    	    
				<th>消费时间:</th>
				<td><s:textfield id="beginDate" name="visitSlowRecordDTO.beginDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple"/></td>
				<td>至:</td>
				<td><s:textfield id="endDate" name="visitSlowRecordDTO.endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple"/></td>			            
			<td><input type="button" class="formButton" onclick="query();" value="查 询" />   
	        	<input type="button" name="Submit2" class="formButton" onclick="javascript:document.form1.submit()" value="导出" />
	        	                 
                </td> 
			</tr>
			
		</table>
	</div>
	</form>
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="10%">地域</th>
			<th width="15%">IP</th>
			<th width="10%">访问时间</th>
			<th width="10%">来源</th>		
	        <th width="10%">入口页面</th>
	        <th width="10%">访问总数</th>	
	        <th width="10%">IP数</th>			        		
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
	</div>
	<div class="search" id="tabs-2">
		<div id="3DColumn" align="center"></div>		
	</div>
    </div>
</body> 
</html>