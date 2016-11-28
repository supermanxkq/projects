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
	function ajaxorderId(){
	
		orderQuery(1);
		// 打开对话框
		$("#dialog-com").dialog({
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
	function orderQuery(page) {
	 //alert("********************");
		var companyId = $.trim($("#companyIdHelp").val());
		var companyName = $.trim($("#companyNameHelp").val());
		var status = $.trim($("#statusHelp").val());
		var params = {
	        "companyInfoDTO.companyId" : companyId,
	        "companyInfoDTO.companyName" : companyName,
	        "companyInfoDTO.status" : status,
	        "companyInfoDTO.page" : page
	    };
	   ajaxDatas("base/compInfo!compJsonPageList",params,"comTb","comPageNav");
	}
	
	
	//选择会员，并为memberSet.jsp设置值 
	function setCom(companyId,companyName,comTele,comEmail,comConPerTele,comConPer,compNum){
		$("#companyId").val(companyId);
		$("#companyName").val(companyName);
		$("#comTele").val(comTele);
		$("#comEmail").val(comEmail);
		$("#comConPer").val(comConPer);
		$("#compNum").val(compNum);
		$("#comConPerTele").val(comConPerTele);
		$("#dialog-com").dialog('close');
	}
	</script>
	
	<input name="jmKeyID" type="hidden" id="jmKeyID" />
	<div id="dialog-com" style="display: none;" title="选择企业"><div align="right"> 
		</div><div ><div align="right"> 
			</div><table class="searchTable" cellpadding="0" cellspacing="0">
				<tr align="right">
		            <td><img src="images/fd.jpg" /></td>
		            <td>企业编号:</td>
					<td><input type="text" id="companyIdHelp" name="companyInfoDTO.companyId" class="formInput"/></td>
					<td>企业名称:</td>
					<td><input type="text" id="companyNameHelp" name="companyInfoDTO.companyName" class="formInput"/></td>
		        	<td><input type="button" class="formButton" onclick="return orderQuery();" value="查 询" /></td>
				</tr>
			</table>
		</div>
		<div>
		<table width="96%" id="comTb" class="listTable" cellpadding="0" cellspacing="0" >
			<tr>
				<th width="3%">序号</th>
				<th width="6%">企业编号</th>
				<th width="6%">企业名称</th>
				<th width="6%">企业人数</th>
				<th width="5%">企业电话</th>
				<th width="5%">企业联系人</th>
				<th width="5%">企业Email</th>
				<th width="5%">相关操作</th>
			</tr>
		</table>
		</div>
		<div class="listBottom">
			
			<div class="Fr" id="comPageNav">
				<s:property value="pageHTML" escape="false"/>
			</div>
		</div>
	</div>