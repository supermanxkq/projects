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
	<title>短信费用管理</title>
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
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	
	<script type="text/javascript">

	        
		//查询方法
		function query(page) {
			///alert("---------");
			var merId = $.trim($("#merId").val());//使用标题
           // var merId = $.trim($("#merId").val());//服务类型
            var createTime = $.trim($("#createTime").val());//时间（截止）
            var beginDate = $.trim($("#beginDate").val());
			var endDate = $.trim($("#endDate").val());
			
			var params = {
		        "messConsTotalDTO.merId" : merId,
		       // "messConsTotalDTO.messType":messType,
		        "messConsTotalDTO.createTime":createTime,
		        "messConsTotalDTO.beginDate":beginDate,
		        "messConsTotalDTO.endDate":endDate,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "messConsTotalDTO.page" : page
		    }; 
		   //// alert("beginDate:"+beginDate);
		   ajaxData("report/messconstotal!jsonPageList",params);
		   //alert("merId:"+merId);
		}

	</script> 
</head>
<body onload="query(${messConsTotalDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 短信费用汇总报表 &gt;&gt; 短信费用汇总报表查看
	</div>

	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>

	<form id="form1" name="form1" action="report/messconstotal!export" method="post" >	
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
<!--	            <td><img src="images/fd.jpg" /></td>-->
	            <td>商户编号:</td>
	            <td><s:textfield id="merId" name="messConsTotalDTO.merId" cssStyle="width:147.5px;" cssClass="formInput" maxlength="20" theme="simple" onkeyup="allowEnCnNu(this)" /></td>
				<td></td>
				 <td>创建时间:</td>
				<td><s:textfield id="beginDate" name="messConsTotalDTO.beginDate" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssClass="Wdate formInput2" maxlength="20" theme="simple"/>至
				<s:textfield id="endDate" name="messConsTotalDTO.endDate" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssClass="Wdate formInput2" maxlength="20" theme="simple"/></td>
				
				<td align="center"><input type="button" class="formButton" onclick="query();" value="查 询" />
				<input type="button" name="Submit2" class="formButton" onclick="javascript:document.form1.submit()" value="导出" />
				</td>
				
			</tr>
						
		</table>
	</div>
	</form>

	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="7%"><a  class="sort">商户编号</a></th>
			<th width="5%"><a  class="sort">总费用</a></th>
			<th width="5%"><a  class="sort">总条数</a></th>

			<th width="7%"><a  class="sort">创建时间</a></th>
	
	
		</tr>
	</table>
	<div class="listBottom">
	<s:if test="#session.user_session.userLevel==2">
		<div class="Fl">
			<my:permission key='sy-1611-02' value='短信参数添加'>
				<input type="button" class="formButton" value="添加" onclick="go('message/shortMesSend!addUI')"/>
			</my:permission>
		</div>
	</s:if>
		<div class="Fr" id="pageNav">
		</div>
	</div>
</body> 
</html>
