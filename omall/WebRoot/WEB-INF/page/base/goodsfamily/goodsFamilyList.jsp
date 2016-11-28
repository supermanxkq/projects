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
	<title>商品分类管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />	
	<script src="js/jquery-1.8.2.min.js"></script>
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
	<script src="js/ajaxfileupload.js"></script>
	<script type="text/javascript">


	$(document).ready(function() {

		$("#uploadFormButton").click(function() {
			if(checkExport()){
				ajaxFileUpload("upload");
			}
		});


	});

	// 异步上传图片操作
	function ajaxFileUpload(fileId){
	    $.ajaxFileUpload({
	    	secureuri: false, 
	        url:'base/uploadGoodsFamily!savaExcelGood',  
	        fileElementId: fileId,
	        dataType:'json',
	        timeout:3000,
	        success:function(data, status){
	    		alert(data.result);
        		$("#dialog-export").dialog("close");
        		location.reload(); 
	        },  
	        error: function(data, status, e){
		        alert("导入失败！");
	        }  
	    });  
		}
		
		//查询方法
		function query(page) {
			var familyName = $.trim($("#familyName").val());
			var status = $.trim($("#status").val());
			var params = {
				"goodsfamilyDto.familyName" : familyName,
		        "goodsfamilyDto.status" : status,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "goodsfamilyDto.page" : page
		    }; 
		   ajaxData("base/goodsfamily!jsonPageList",params);
		}
		
			
		function closeDialog(dialogId){
			$("#"+dialogId).dialog("close");
		}
		
		/** 打开导入属性、属性值对话框 */
		function openExportForm(){

      		// 打开对话框   
			$("#dialog-export").dialog({
				
				resizable: true,
				top: 300,
				height:250,
				width:550,
				modal: true
			});
      	}
      	
      	// 检验选择的Excel文件
      	function checkExport(){
      		var upload = $("#upload").val();
      		var suf = upload.substring(upload.lastIndexOf('.'))
      		if (upload == "undefined" || (suf != ".xls" && suf != ".xlsx")) {
				alert("请选择Excel文件！");
				return false;
			}
      		return true;
      	}
      
		
		

	</script> 
</head>
<body onload="query(${goodsfamilyDto.page });">
<s:property value="session.result"/>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 商品分类管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
	    <div class="Fl">
			<s:if test="#session.user_session.userLevel==0">
			<my:permission key='sy-1701-02' value='商品分类添加'>
				<input type="button" class="formButton" value="添加" onclick="go('base/goodsfamily!addUI')"/>
				<input type="button" class="formButton" value="导入" onclick="openExportForm();" />
			</my:permission>
			</s:if>
		</div>
		<table class="searchTable" cellpadding="0" cellspacing="0">
		
		
		
			<tr>
	           
			    <td>分类名称:</td>
				<td><s:textfield id="familyName" name="goodsfamilyDto.familyName" cssClass="formInput" maxlength="20" theme="simple"/></td>
	        	<td>使用状态:</td>
		        <td><s:select name="goodsfamilyDto.status" id="status" cssStyle="width:156px;" list="#request.statusValues" 
		        	listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
		        </td>
	        	<td>
	        	<input type="button" class="formButton" onclick="query();" value="查 询" />
	        	</td>
			</tr>
			
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="10%">分类名称</th>
			<th width="15%">节点等级</th>
			<th width="10%">默认展开</th>
			<th width="6%">分类类型</th>
			<th width="6%">使用状态</th>
			<th width="10%">创建时间</th>
			<th width="6%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<s:if test="#session.user_session.userLevel==0">
			<my:permission key='sy-1701-02' value='商品分类添加'>
				<input type="button" class="formButton" value="添加" onclick="go('base/goodsfamily!addUI')"/>
			</my:permission>
			</s:if>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
		<!-- 导入属性、属性值对话框 -->
	 	<div id="dialog-export" style="display:none;margin:0 0;padding:0 0 0 0;" title="导入属性、属性值">
			<div style="display:block;width:96%;padding-top:15px;padding-left:10px;">
				<fieldset style="margin-top:20px;" id="addAttContal">
					<legend>导入系统分类</legend>
					<div style="display:block;margin:10px 20px;line-height:28px;overflow:hidden;">
							<div style="display:inline;float:left;padding-right:10px">Excel文件：
								<s:file id="upload" name="upload" accept=".xls" cssClass="formInput" theme="simple" />
								<span class="Color3">(只允许xls,xlsx文件，且大小不能超过1M!)</span>
								</br><span  style="color: red;"><s:fielderror/> <a href="<%=basePath%>download/template/系统分类.xls">下载模板</a></span>
								<span id="uploadValue" style="color: red;"><s:fielderror/></span>
							</div>
							
							<div style="display:inline;float:left;padding-right:10px"><input type="button" id="uploadFormButton" class="formButton" value="导   入"></div>
							<input type="button" id="cancelButton" class="formButton" onclick="closeDialog('dialog-export');" value="取   消" />
					</div>
				</fieldset>
			</div>
		</div>
</body> 
</html>