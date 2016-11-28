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
	<title>商户领卡</title>
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
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script src="js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript">
		//查询方法
		function query(page) {
			var merId = $.trim($("#merId").val());
			var merName = $.trim($("#merName").val());
			var params = {
				"stockInfoDTO.merId" : merId,
		        "stockInfoDTO.merName" : merName,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "modStockDTO.page" : page
		    }; 
		   ajaxData("stock/cardstock!jsonPageList",params); 
		}

		//查看库存详情信息
		function checkStockInfo(id,flag){
			var showModify = $("#show_Detail");
            
            var actionUrl = "stock/cardstock!checkStockInfo"; 
            var params = {
                       "stockInfoDTO.Id":id
                    };
        	$.ajax({
                url:actionUrl,
                data:params,
                dataType:"json",
                cache:false,
                type:"POST",
                error:function(data,status){
                 alert("库存信息加载失败!");   
               },
                success:function(data){
                   showModify.find("#merName").html("<font color='green'><strong>"+data.merName+" </strong></font>"); 
                   showModify.find("#dwxz").html("<font color='green'><strong>"+data.dwxz+" </strong></font>");
                   showModify.find("#cardBinName").html("<font color='green'><strong>"+data.cardBinName+" </strong></font>");
                   showModify.find("#nowStock").html("<font color='red'><strong>"+data.existsTotal+" </strong></font>张"); 
                   showModify.find("#inStock").html("<font color='green'><strong>"+data.InTotal+"  </strong></font>张");
                   showModify.find("#outStock").html("<font color='green'><strong>"+data.outTotal+"  </strong></font>张");
                   showModify.find("#updateTime").html("<font color='green'><strong>"+data.updateTime+" </strong></font>");
                   
                   if(flag == 1){
                          button={
                   	        '取消': function() {
       				              $(this).dialog('close');
       			                 }
                              }
                       }

       			$("#show_Detail").dialog({
       				resizable: true,
       				top: 240,
       				height:400,
       				width:800,
       				modal: true,
       				title:"卡片库存明细信息查看",
       				buttons:button
       			});
               }
        	});
		}
		
	</script> 
</head>
<body onload="query(${stockInfoDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 库存管理 &gt;&gt; 库存信息查看
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
		<table class="searchTable" cellpadding="0" width="50%" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>单位名称:</td>
				<td><s:textfield id="merName" name="stockInfoDTO.merName" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>单位编号:</td>
				<td><s:textfield id="merIdId" name="stockInfoDTO.merId" cssClass="formInput" maxlength="20" theme="simple"/></td>
			    <td align="center"><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>			
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="12%">单位名称</th>
			<th width="6%">单位性质</th>
			<th width="12%">所属卡BIN</th>
			<th width="10%">当前数量</th>
			<th width="12%">更新时间</th>
			<th width="8%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
	<%--
	<s:if test="#session.user_session.userLevel==0">
		<div class="Fl">
			<my:permission key='sy-2203-02' value='机构领卡添加'>
				<input type="button" class="formButton" value="添加" onclick="go('stock/mercard!addUI')"/>
			</my:permission>
		</div>
	</s:if>
	--%>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
	<%--
	//显示弹出框 
	--%>
	<div id="show_Detail" style="display: none;">
	<s:hidden id="Id" name="stockInfoDTO.Id"></s:hidden>
	    <table class="formTable">
	        <tr>
		  		<th align="right" width="20%">单位名称：</th>
		        <td width="30%" id="merName"></td>
		        <th align="right" width="20%">单位性质：</th>
		        <td width="30%" id="dwxz"></td>
			</tr>
			<tr>
		  		<th align="right" width="20%">卡BIN名称：</th>
		        <td width="30%" id="cardBinName"></td>
		        <th align="right" width="20%">现有库存：</th>
		        <td width="30%" id="nowStock"></td>
			</tr>
			<tr>
		  		<th align="right" width="20%">入库总数：</th>
		        <td width="30%" id="inStock"></td>
		        <th align="right" width="20%">出库总数：</th>
		        <td width="30%" id = outStock></td>
			</tr>
			<tr>
		  		<th align="right" width="20%">更新时间：</th>
		        <td colspan="3" id="updateTime"></td>
		        
			</tr>
	    </table>
	</div>
</body> 
</html>