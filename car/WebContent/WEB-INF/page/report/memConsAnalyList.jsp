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
	<title>会员消费分析报表</title>
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
	<script type="text/javascript">
	
		//查询方法
		function query(page) {
			var rank = $.trim($("#rank").val());
			var rankSign = $.trim($("#rankSign").val());
		    var beginDate = $("#beginDate").val();
		    var endDate = $("#endDate").val();
			var countType = $.trim($("#countType").val());
			
			var params = {			
				"memcaDto.rank":rank,
				"memcaDto.rankSign":rankSign,
				"memcaDto.beginDate":beginDate,
			    "memcaDto.endDate":endDate,
				"memcaDto.countType":countType,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "memcaDto.page" : page
		    };
		   ajaxData("report/memconsanalyaction!jsonPageList",params);
		}
		$(function() {
			var $tabs = $("#tabs").tabs();	
		});
		
	function drawReport(){
		var rank = $.trim($("#rank").val());
		var rankSign = $.trim($("#rankSign").val());
	    var beginDate = $("#beginDate").val();
	    var endDate = $("#endDate").val();
		var countType = $.trim($("#countType").val());
		
		var params = {
			"memcaDto.rank":rank,
			"memcaDto.rankSign":rankSign,
			"memcaDto.beginDate":beginDate,
		    "memcaDto.endDate":endDate,
			"memcaDto.countType":countType,
	        "orderProperty" : $("#orderProperty").val(),
	        "orderDirection" : $("#orderDirection").val(),
	        "memcaDto.page" : 1
	    };
	    $.ajax({
	    	url : "report/memconsanalyaction!demoMap", 
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
		当前位置是：HOME &gt;&gt; 会员消费分析报表 &gt;&gt; 会员消费分析报表查看
	</div>
	<div id="tabs">
	<ul>
		<li><a href="#tabs-1">会员消费记录查看</a></li>
		<li><a href="#tabs-2" onclick="drawReport();">会员消费分析报表</a></li>
	</ul>
	<div id="tabs-1">
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<form id="form1" name="form1" action="" method="post" >
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">		
			<tr>
				<td><img src="images/fd.jpg"/></td>	 				    	    
				<td>消费时间:</td>
				<td><s:textfield id="beginDate" name="memcaDto.beginDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple"/></td>
				<td>至:</td>
				<td><s:textfield id="endDate" name="memcaDto.endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple"/></td>			            
			</tr>
			<tr>
				<td></td>   	
	        	<td>消费排名:</td>
				<td><s:select name="memcaDto.rank" id="rank" list="#{5:'5',6:'6',7:'7',8:'8',9:'9',10:'10'}" listKey="key" listValue="value" cssStyle="width:153.5px;" cssClass="formSelect" theme="simple"/></td>
	        	<td>排名方式:</td>
				<td><s:select name="memcaDto.rankSign" id="rankSign" list="#request.sign" listKey="key" listValue="value" cssStyle="width:153.5px;" cssClass="formSelect" theme="simple"/></td>
			</tr>
			<tr>
				<td></td>
				<s:if test="#session.user_session.userLevel==0">		
	        	<td>统计方式:</td>
				<td><s:select name="memcaDto.countType" id="countType" list="#{0:'全部',1:'按机构统计',2:'按商户统计'}" listKey="key" listValue="value" cssStyle="width:153.5px;" cssClass="formSelect" theme="simple"/></td>
	        	</s:if>
	        	<s:if test="#session.user_session.userLevel==1">		
	        	<td>统计方式:</td>
				<td><s:select name="memcaDto.countType" id="countType" list="#{0:'全部',2:'按商户统计'}" listKey="key" listValue="value" cssStyle="width:153.5px;" cssClass="formSelect" theme="simple"/></td>
	        	</s:if>
	        	<s:if test="#session.user_session.userLevel==2">		
	        	<td>统计方式:</td>
				<td><s:select name="memcaDto.countType" id="countType" list="#{0:'全部'}" listKey="key" listValue="value" cssStyle="width:153.5px;" cssClass="formSelect" theme="simple"/></td>
	        	</s:if>
	        	<td></td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" />                    
                </td>                        	
			</tr>			
		</table>
	</div>
	</form>
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="10%">会员编号</th>
			<th width="15%">会员名称</th>
			<th width="10%">账户类型</th>
			<th width="10%">消费类型</th>		
	        <th width="10%">所在机构或商户</th>		        		
			<th width="8%">消费金额</th>
			<th width="8%">消费次数</th>
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