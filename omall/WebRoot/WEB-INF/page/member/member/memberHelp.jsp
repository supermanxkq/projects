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
	function ajaxParentMemId(){
		memQuery(1);
		// 打开对话框
		$("#dialog-mem").dialog({
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
	function memQuery(page) {
	//alert("********************");
		var memId = $.trim($("#memIdHelp").val());
		var memRealName = $.trim($("#memRealNameHelp").val());
		var status = $.trim($("#statusHelp").val());
		var params = {
	        "memberDTO.memId" : memId,
	        "memberDTO.memRealName" : memRealName,
	       	"memberDTO.status" : status,
	        "memberDTO.page" : page
	    };
	   ajaxDatas("member/member!memJsonPageList",params,"memTb","memPageNav");
	}
	
	
	//选择会员，并为memberSet.jsp设置值 
	
	function secMem(memId,memRealName,telNo){
		$("#payMenId").val(memId);
		$("#payMenName").val(memRealName);
		$("#telNo").val(telNo);
		$("#dialog-mem").dialog('close');
	}
	
	</script>
	
	<input name="jmKeyID" type="hidden" id="jmKeyID" />
	<div id="dialog-mem" style="display: none;" title="选择会员"><div align="right"> 
		</div><div ><div align="right"> 
			</div><table class="searchTable" cellpadding="0" cellspacing="0">
				<tr align="right">
		            <td><img src="images/fd.jpg" /></td>
		            <td>会员编号:</td>
					<td><input type="text" id="memIdHelp" name="memberDTO.memId" class="formInput"/></td>
					<td>会员名称:</td>
					<td><input type="text" id="memRealNameHelp" name="memberDTO.memRealName" class="formInput"/></td>
		        	<td><input type="button" class="formButton" onclick="return memQuery();" value="查 询" /></td>
				</tr>
			</table>
		</div>
		<div>
		<table width="96%" id="memTb" class="listTable" cellpadding="0" cellspacing="0" >
			<tr>
				<th width="3%">序号</th>
				<th width="6%">会员编号</th>
				<th width="6%">会员名称</th>
				<th width="6%">状态</th>
				<th width="5%">相关操作</th>
			</tr>
		</table>
		</div>
		<div class="listBottom">
			<div class="Fl">
				<a href="member/member!addUI"><input type="button" class="formButton" value="添加" /></a>
			</div>
			<div class="Fr" id="memPageNav">
				<s:property value="pageHTML" escape="false"/>
			</div>
		</div>
	</div>