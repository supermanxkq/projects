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
	function ajaxMfp(){
		mfpQuery(1);
		// 打开对话框
		$("#dialog-mfp").dialog({
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
	function mfpQuery(page) {
	//alert("********************");
		var mfpId = $.trim($("#id").val());
		var messName = $.trim($("#mname").val());
		var params = {
	        "messDto.mfpId" : mfpId,
	        "messDto.messName" : messName,
	        "messDto.page" : page
	    };
	   ajaxDatas("message/messageparam!mfpjsonPageList",params,"mfpTb","mfpPageNav");
	}
	
	
	//选择参数，并为Set页面设置值 
	function secMfp(mfpId,messName){
		$("#mfpId").val(mfpId);
		$("#messName").val(messName);
		$("#dialog-mfp").dialog('close');
	}
	</script>
	
	<input name="jmKeyID" type="hidden" id="jmKeyID" />
	<div id="dialog-mfp" style="display:none;" title="选择参数">
		<div >
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
		            <td><img src="images/fd.jpg" /></td>
		            <td>参数编号:</td>
					<td><input type="text" id="id" name="messDto.mfpId" class="formInput"/></td>
					<td>参数名称:</td>
					<td><input type="text" id="mname" name="messDto.messName" class="formInput"/></td>
		        	<td><input type="button" class="formButton" onclick="return mfpQuery();" value="查 询" /></td>
				</tr>
			</table>
		</div>
		<div>
		<table width="96%" id="mfpTb" class="listTable" cellpadding="0" cellspacing="0" >
			<tr>
				<th width="3%">序号</th>
				<th width="6%">参数编号</th>
				<th width="6%">参数名称</th>
				<th width="6%">套餐类型</th>
				<th width="6%">条数</th>
				<th width="6%">费用</th>
				<th width="6%">使用状态</th>
				<th width="5%">相关操作</th>
			</tr>
		</table>
		</div>
		<div class="listBottom">
			<div class="Fr" id="mfpPageNav">
				<s:property value="pageHTML" escape="false"/>
			</div>
		</div>
	</div>
