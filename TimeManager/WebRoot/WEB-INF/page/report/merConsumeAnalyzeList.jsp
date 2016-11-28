<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + 

request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<base href="<%=basePath%>" />
		<title>商户消费分析报表</title>
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
		<script src="js/jquery/jquery.ui.tabs.js"></script>
		<script src="js/common.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script src="js/datepicker/WdatePicker.js"></script>
		<script type="text/javascript">
		//查询方法
		
		function query(page) {
			var flag = $("#flag").val();
			if(flag!=2){
				var merId = $.trim($("#merId").val());
				if (merId==""){
					alert("请选择商户！");
					return false;
				}
			}
			var merRealName = $.trim($("#merRealName").val());
			var sign = $("#sign").val();
		    var beginDate = $("#beginDate").val();
		    var endDate = $("#endDate").val();
			var status = $("#status").val();
					
			var params = {
				"merConsumeAnalyzeDTO.merId":merId,
				"merConsumeAnalyzeDTO.sign":sign,
				"merConsumeAnalyzeDTO.merRealName":merRealName,
				"merConsumeAnalyzeDTO.beginDate":beginDate,
			    "merConsumeAnalyzeDTO.endDate":endDate,
				"merConsumeAnalyzeDTO.status":status,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "merConsumeAnalyzeDTO.page" : page
		    };
		   if(flag!=2){
				if (merId!=""){
					ajaxData("report/merconsumeanalyze!jsonPageList",params);
				}			 
			 }
		   if(flag==2){
		   		ajaxData("report/merconsumeanalyze!jsonPageList",params);
		   }	 
		}
		$(function() {
			var $tabs = $("#tabs").tabs();	
			$('#tabs-2').click(function() { // 绑定单击事件
			    $tabs.tabs('select', 1);
			    return true;
			});		
		});
	function chartReport(){
		    var merId = $.trim($("#merId").val());
			var merRealName = $.trim($("#merRealName").val());
			var sign = $("#sign").val();
		    var beginDate = $("#beginDate").val();
		    var endDate = $("#endDate").val();
			var status = $("#status").val();
			
			var params = {
				"merConsumeAnalyzeDTO.merId":merId,
				"merConsumeAnalyzeDTO.sign":sign,
				"merConsumeAnalyzeDTO.merRealName":merRealName,
				"merConsumeAnalyzeDTO.beginDate":beginDate,
			    "merConsumeAnalyzeDTO.endDate":endDate,
				"merConsumeAnalyzeDTO.status":status,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "merConsumeAnalyzeDTO.page" : 1
	    };
	    $.ajax({
	    	url : "report/merconsumeanalyze!chart", 
	        data : params,   
	       // dataType : "json",
	        cache : false, 
	        type : "POST",
	        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误!");	    
		    }, 
		    success : function(data, textStatus) {
		    	$("#3DColumn").html(data);
		    }
	    });
		document.getElementById("");
	}
	</script>
	</head>
	<body>
		<div class="Position">
			当前位置是：HOME &gt;&gt; 交易报表 &gt;&gt; 商户消费分析报表
		</div>
		<div class="search" id="tabs">
	     <ul>
		   <li><a href="#tabs-1">商户消费记录查看</a></li>
		   <li><a href="#tabs-2"  onclick="chartReport();">商户消费分析报表</a></li>
	     </ul>
	    <div class="search" id="tabs-1">
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<form id="form1" name="form1" action="report/merconsumeanalyze!export"
			method="post">
			<input type="hidden" name="flag" id="flag" value="${sessionScope.user_session.userLevel }"/>			
			<div class="search">
			<jsp:include page="/WEB-INF/page/base/terminals/mercHelps.jsp"></jsp:include>
				<table class="searchTable" cellpadding="0" cellspacing="0">
					<s:if test="#session.user_session.userLevel!=2">
						<tr>
							<td><img src="images/fd.jpg"/></td>	 
							<td>商户名称:</td>
							<td><s:textfield name="merConsumeAnalyzeDTO.merRealName" id="merName"  maxlength="20" readonly="true" cssClass="formInput" cssStyle="width:147.5px;"  theme="simple"/>
								<s:hidden name="merConsumeAnalyzeDTO.merId" id="merId"/>
								<img alt="查找商户" src="images/search.gif" style="cursor:pointer;" onclick="ajaxMerc();"/>
							</td>
							<td>所按条件:</td>
							<td><s:select name="merConsumeAnalyzeDTO.status" id="status" list="#request.statuss" listKey="key" listValue="value" cssStyle="width:147.5px;" cssClass="formSelect" theme="simple"/></td>
							<td>图表方式:</td>
							<td><s:select name="merConsumeAnalyzeDTO.sign" id="sign" list="#request.sign" listKey="key" listValue="value" cssStyle="width:147.5px;" cssClass="formSelect" theme="simple"/></td>
						</tr>
						<tr>
							<td></td>
							<td>交易时间:</td>
							<td><s:textfield id="beginDate"	name="merConsumeAnalyzeDTO.beginDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:147.5px;" maxlength="20" theme="simple" /></td>
							<td>至:</td>
							<td><s:textfield id="endDate" name="merConsumeAnalyzeDTO.endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:147.5px;"  maxlength="20" theme="simple" /></td>
							<td></td>
							<td><input type="button" class="formButton" onclick="query();" value="查 询" />
								<input type="button" name="Submit2" class="formButton" onclick="javascript:document.form1.submit()" value="导出" />
							</td>
						</tr>
					</s:if>

					<s:if test="#session.user_session.userLevel==2">
						<tr>
							<td><img src="images/fd.jpg"/></td>
							<s:hidden name="flag" id="flag"></s:hidden>
							<td>交易时间:</td>
							<td><s:textfield id="beginDate" name="merConsumeAnalyzeDTO.beginDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple" /></td>
							<td>至:</td>
							<td><s:textfield id="endDate" name="merConsumeAnalyzeDTO.endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple" /></td>
							<td>所按条件:</td>
							<td><s:select name="merConsumeAnalyzeDTO.status" id="status" list="#request.statuss" listKey="key" listValue="value" cssStyle="width:153.5px;" cssClass="formSelect" theme="simple" />
							</td>
							<td>图表方式:</td>
							<td><s:select name="merConsumeAnalyzeDTO.sign" id="sign" list="#request.sign" listKey="key" listValue="value" cssStyle="width:147.5px;" cssClass="formSelect" theme="simple"/></td>
							<td><input type="button" class="formButton" onclick="query();" value="查 询" />
								<input type="button" name="Submit2" class="formButton" onclick="javascript:document.form1.submit()" value="导出" />
							</td>
						</tr>
					</s:if>
					<tr>
						<td></td>
					</tr>
				</table>
			</div>
		</form>
		<table width="96%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="3%">
					序号
				</th>
				<th width="8%">
					商户编号
				</th>
				<th width="10%">
					商户名称
				</th>
				<th width="8%">
					交易金额
				</th>
				<th width="8%">
					交易笔数
				</th>
				<th width="8%">
					对应时间
				</th>
				<th width="8%">
					所按时间（季度/月份/日）
				</th>
			</tr>
		</table>
		
		<table width="96%" id="quarter" class="listTable" cellpadding="0"
			cellspacing="0" style="display: none;">
			<tr>
				<th width="3%">
					序号
				</th>
				<th width="10%">
					商户编号
				</th>
				<th width="15%">
					商户名称
				</th>
				<th width="8%">
					交易金额
				</th>
				<th width="8%">
					所按时间
				</th>
			</tr>
		</table>
		
		
		<div class="listBottom">
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
		</div>
		</div>
		<div class="search" id="tabs-2">
		  <div id="3DColumn" align="center">
		    
		  </div>
	    </div>
	</div>
	</body>
</html>