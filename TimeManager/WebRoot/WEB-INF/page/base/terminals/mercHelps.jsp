<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<base href="<%=basePath%>" />
<script language="javascript" type="text/javascript" defer="defer" src="<%=basePath%>js/datepicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="<%=basePath%>js/des.js"></script>
<script type="text/javascript">
	var ajaxMerc = function(){
		var stockSsOrganId = $("#stockSsOrganId").val();
		if(stockSsOrganId==-1){
             alert("请选择所属机构!");
             return false;
		}
		mercQuery(1);
		// 打开对话框
		$("#dialog-merc").dialog({
			resizable: true,
			top: 240,
			height:400,
			width:800,
			modal: true,
			buttons: {
				'取消': function() {
					$(this).dialog('close');
				}
			}
		});
	}
	
	//查询方法
	function mercQuery(page) {
	
		var mercName = $.trim($("#search_name").val());
		var mercId = $.trim($("#search_mercId").val());
		var stockSsOrganId = $("#stockSsOrganId").val();
		var params = {
	        "MerchantsDTO.merId" : mercId,
	        "MerchantsDTO.nameChinese" : mercName,
	        "MerchantsDTO.stockSsOrganId":stockSsOrganId,
	        "MerchantsDTO.page" : page
	    };
	   ajaxDatas("base/merchants!mercJsonPageList",params,"mercTb","mercPageNav");
	}
	
	
	//选择商户，并为terminalsSet.jsp设置值 
	var secMerc = function(mercId,organId,mercName,organName){
		//alert(mercId+">>"+organId+">>>"+mercName+">>>"+organName);
	
		$("#merId").val(mercId);
		$("#sdorganId").val(organId);
		if(mercName==null||mercName=="null"){
			$("#merName").val("");
		}else{
			$("#merName").val(mercName);
		}
		$("#merNameErrorMsg").html("");
	    $("#organName").val(organName);
		$("#dialog-merc").dialog('close');
	}
	</script>
	
	<input name="jmKeyID" type="hidden" id="jmKeyID" />
	<div id="dialog-merc" style="display: none;" title="选择商户">
		<div >
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
		            <td><img src="images/fd.jpg" /></td>
		            <td>商户编号:</td>
					<td><input type="text" id="search_mercId" name="mercId" class="formInput"/></td>
					<td>商户名称:</td>
					<td><input type="text" id="search_name" name="mercName" class="formInput"/></td>
		        	<td><input type="button" class="formButton" onclick="mercQuery();" value="查 询" /></td>
				</tr>
			</table>
		</div>
		<table width="96%" id="mercTb" class="listTable" cellpadding="0" cellspacing="0" >
			<tr>
				<th width="3%">序号</th>
				<th width="6%">商户编号</th>
				<th width="6%">商户名称</th>
				<th width="10%">发卡机构</th>
				<th width="6%">状态</th>
				<th width="5%">操作时间</th>
				<th width="5%">相关操作</th>
			</tr>
		</table>
		<div class="listBottom">
			<div class="Fr" id="mercPageNav">
				<s:property value="pageHTML" escape="false"/>
			</div>
		</div>
	</div>

	