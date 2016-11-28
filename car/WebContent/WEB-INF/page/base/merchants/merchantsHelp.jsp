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
	function ajaxMerchantsId(){
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
	   ajaxDatas("base/merchants!mercJsonPageList",params,"merTb","merPageNav");
	}
	
	
	//选择会员，并为memberSet.jsp设置值 
	function secMer(merchantsId,organId,merchantsName,organName){
	alert("AAAAAAAAA");
		document.getElementById("#merId").html(merchantsId);
		document.getElementById("#merName").html(merchantsName);
		document.getElementById("#organId").html(organId);
		document.getElementById("#name").html(organName);
		document.getElementById("#dialog-mer").dialog('close');
	}
	</script>
	
	<input name="jmKeyID" type="hidden" id="jmKeyID" />
	<div id="dialog-mer" style="display: none;" title="选择商户">
		<div >
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
		            <td><img src="images/fd.jpg" /></td>
		            <td>商户编号:</td>
					<td><input type="text" id="merchantsId" name="merchantsDTO.merId" class="formInput"/></td>
					<td>商户名称:</td>
					<td><input type="text" id="merchantsName" name="merchantsDTO.merName" class="formInput"/></td>
		        	<td><input type="button" class="formButton" onclick="return merQuery();" value="查 询" /></td>
				</tr>
			</table>
		</div>
		<div>
		<table width="96%" id="merTb" class="listTable" cellpadding="0" cellspacing="0" >
			<tr>
				<th width="3%">序号</th>
				<th width="6%">商户编号</th>
				<th width="6%">商户名称</th>
				<th width="6%">所属机构</th>
				<th width="6%">商户状态</th>
				<th width="6%">创建时间</th>
				<th width="5%">相关操作</th>
			</tr>
		</table>
		</div>
		<div class="listBottom">
			<div class="Fr" id="merPageNav">
				<s:property value="pageHTML" escape="false"/>
			</div>
		</div>
	</div>