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

		orgQuery(1);
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
	function orgQuery(page) {

		var orgName = $.trim($("#search_name").val());//机构名称
		var organId = $.trim($("#search_mercId").val());

		var params = {
	        "OrgansDTO.organId" : organId,//和上面的机构名称和机构id保持一致
	        "OrgansDTO.orgName" : orgName,
	        "OrgansDTO.page" : page
	    };
	   ajaxDatas("base/organs!orgJsonPageList",params,"mercTb","mercPageNav");
	}
	
	
	//选择机构，并为cardBINSet.jsp设置值 
	var secOrg = function(organId,orgName){
		$("#organId").val(organId);//#orgId和#Name是从set的界面获取的！机构名称的id 和机构的id
		$("#Name").val(orgName);//在Set.jsp
		$("#dialog-merc").dialog('close');
	}
	</script>
	
	<input name="jmKeyID" type="hidden" id="jmKeyID" />
	<div id="dialog-merc" style="display: none;" title="选择机构"> 
		<div >
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
		            <td><img src="images/fd.jpg" /></td>
		            <td>机构编号:</td>
					<td><input type="text" id="search_mercId" name="mercId" class="formInput"/></td>
					<td>机构名称:</td>
					<td><input type="text" id="search_name" name="mercName" class="formInput"/></td>
					
		        	<td><input type="button" class="formButton" onclick="orgQuery();" value="查 询" /></td>
				</tr>
			</table>
		</div>
		<table width="96%" id="mercTb" class="listTable" cellpadding="0" cellspacing="0" >                                   
			<tr>
				<th width="3%">序号</th>
				<th width="6%">机构编号</th>
				<th width="6%">机构名称</th>
				<th width="5%">状态</th>
				<th width="10%">相关操作</th>
			</tr>
		</table>
		<div class="listBottom">
		
			<div class="Fr" id="mercPageNav">
				<s:property value="pageHTML" escape="false"/>
			</div>
		</div>
	</div>

	