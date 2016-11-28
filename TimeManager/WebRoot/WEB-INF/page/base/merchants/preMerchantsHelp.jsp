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
function showPreMerHelp(){
	merQuery(1);
	// 打开对话框
	$("#dialog-mer").dialog({
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
function merQuery(page) {
	
	var merchantsId = $.trim($("#merchantsId").val());
	var merchantsName = $.trim($("#merchantsName").val());
	var params = {
        "merchantsDTO.merId" : merchantsId,
        "merchantsDTO.merName" : merchantsName,
        "merchantsDTO.page" : page
    };	   
   ajaxDatas("base/merchants!preMerchantsHelpList",params,"merTb","merPageNav");
}


//选择会员，并为memberSet.jsp设置值 
function secPreMer(merchantsId,merchantsName){
	$("#preMerId").val(merchantsId);
	$("#preMerName").val(merchantsName);
	$("dialog-mer").dialog('close');
}
	</script>
	
	<input name="jmKeyID" type="hidden" id="jmKeyID" />
	<div id="dialog-mer" style="display: none;" title="选择经销商">
		<div >
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
		            <td><img src="images/fd.jpg" /></td>
		            <td>经销商编号:</td>
					<td><input type="text" id="merchantsId"  class="formInput"/></td>
					<td>经销商名称:</td>
					<td><input type="text" id="merchantsName" class="formInput"/></td>
		        	<td><input type="button" class="formButton" onclick="return merQuery();" value="查 询" /></td>
				</tr>
			</table>
		</div>
		<div>
		<table width="96%" id="merTb" class="listTable" cellpadding="0" cellspacing="0" >
			<tr>
				<th width="3%">序号</th>
				<th width="6%">经销商编号</th>
				<th width="6%">经销商名称</th>
				<th width="6%">所属机构</th>
				<th width="6%">经销商状态</th>
				<th width="6%">创建时间</th>
				<th width="5%">确认选择</th>
			</tr>
		</table>
		</div>
		<div class="listBottom">
			<div class="Fr" id="merPageNav">
				<s:property value="pageHTML" escape="false"/>
			</div>
		</div>
	</div>