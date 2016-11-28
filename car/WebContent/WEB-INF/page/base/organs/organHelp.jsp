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
	function ajaxOrganId(){
		orgQuery(1);
		// 打开对话框
		$("#dialog-org").dialog({
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
	function orgQuery(page) {
	//alert("********************");
		var orgId = $.trim($("#orgId").val());
		var orgname = $.trim($("#orgname").val());
		var params = {
	        "organsDTO.organId" : orgId,
	        "organsDTO.name" : orgname,
	        "organsDTO.page" : page
	    };
	   ajaxDatas("base/organs!orgJsonPageList",params,"orgTb","orgPageNav");
	}
	
	
	//选择会员，并为memberSet.jsp设置值 12
	function secOrg(organId,name){
		$("#organId").val(organId);
		$("#name").val(name);
		$("#dialog-org").dialog('close');
	}
	</script>
	
	<input name="jmKeyID" type="hidden" id="jmKeyID" />
	<div id="dialog-org" style="display:none;" title="选择机构">
		<div >
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
		            <td><img src="images/fd.jpg" /></td>
		            <td>机构编号:</td>
					<td><input type="text" id="orgId" name="organsDTO.organId" class="formInput"/></td>
					<td>机构名称:</td>
					<td><input type="text" id="orgname" name="organsDT.name" class="formInput"/></td>
		        	<td><input type="button" class="formButton" onclick="return orgQuery();" value="查 询" /></td>
				</tr>
			</table>
		</div>
		<div>
		<table width="96%" id="orgTb" class="listTable" cellpadding="0" cellspacing="0" >
			<tr>
				<th width="3%">序号</th>
				<th width="6%">机构编号</th>
				<th width="6%">机构名称</th>
				<th width="6%">状态</th>
				<th width="5%">相关操作</th>
			</tr>
		</table>
		</div>
	</div>