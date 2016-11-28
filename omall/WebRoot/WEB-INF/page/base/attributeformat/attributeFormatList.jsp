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
	<title>商品属性规格管理</title>
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
	<style type="text/css">
		.item{display:block;padding:10px 10px;float:left;}
	</style>
	<script type="text/javascript">
		
		//查询方法
		function query(page) {
			var familyName = $.trim($("#familyName").val());
			var params = {
				"familyDTO.familyName" : familyName,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "familyDTO.page" : page
		    }; 
		   ajaxData("base/goodsattributeformat!jsonPageList",params);
		}
		
		function showFormatByFamilyId(url,id){
			$("#dialog-Format").dialog({
				resizable: true,
				top: 300,
				height:350,
				width:550,
				modal: true
			});
			$("#formatContainer").html("");
      		var params = {
      			"familyDTO.familyId" : id
		  	};
		    $.ajax( {   
		        url : url,   
		        data : params,  
		        async:false, 
		        dataType : "json",   
		        cache : false,   
		        type : "get",
		        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误!");	    
		        }, 
		        success : function(data, textStatus) { 
		        	var groupIds = data["groupIds"];
		        	var html="";
			   		if(!groupIds){
			   			return;
			   		}
			   		var title;
			   		var baseFormatHtml="";
			   		for(var i=0;i<groupIds.length;i++){
			   			var groupId = groupIds[i];
			   			title = groupId;
			   			if(title==null){
			   				title = "基本规格";
			   				baseFormatHtml+="<fieldset style=\"width:100%\">";
							baseFormatHtml+="<legend>"+title+"</legend>";
							baseFormatHtml+="<div style=\"width:100%;height:80px;overflow:auto\">";
			   				var item = data[groupId];
			   				for(var j=0;j<item.length;j++){
			   					var formatName = item[j]["formatName"];
				   				baseFormatHtml+="<div class=\"item\">"+formatName+"</div>";
			   				}
							baseFormatHtml+="	</div>";
							baseFormatHtml+="</fieldset>";
			   			}else{
				   			html+="<fieldset style=\"width:100%\">";
							html+="<legend>"+title+"</legend>";
							html+="<div style=\"width:100%;height:80px;overflow:auto\">";
			   				var item = data[groupId];
			   				for(var j=0;j<item.length;j++){
			   					var formatName = item[j]["formatName"];
				   				html+="<div class=\"item\">"+formatName+"</div>";
			   				}
							html+="	</div>";
							html+="</fieldset>";
						}
			   		}
			   		
			   		$("#formatContainer").html(baseFormatHtml+html);
		        }   
		    });
		}
		
		// 关闭dialog
		function closeDialog(dialogId){
			$("#"+dialogId).dialog("close");
		}
		
		// 打开导入商品规格对话框
		function openExportForm(){
			$("#dialog-export").dialog({
				resizable: true,
				top: 300,
				height:250,
				width:550,
				modal: true
			});
			
			// 清空file值
      		var file = $(":file");
      		file.after(file.clone().val(""));
			file.remove();
      	}
      	
      	// 校验选择的Excel文件
      	function checkExport(){
      		var upload = $("#upload").val();
      		var suf = upload.substring(upload.lastIndexOf('.'))
      		if (upload == "undefined" || (suf != ".xls" && suf != ".xlsx")) {
				alert("请选择Excel文件！");
				return false;
			}else{
				return true;
			}
      	}
      	
   		//自动上传
      	function autoUpload(){
      		$("#uploadForm").submit();
      	}
      	
		// 导入操作
      	function saveExport(){
	        var url = "base/goodsattributeformat!export";
	        var params = {
	        	"upload" : $("uploadForm").serialize()
	        }
	        ajaxRequest(url,params,"POST",function(data){
	        	 if("success" == data.msg){
	        	 	 query(1);
	                 alert("导入成功!");
	                 closeDialog('dialog-export');
	             } else {
	                 alert("导入失败!");
	             }
	        });
      	}
      	
      	function ajaxRequest(url,params,method,callback){
			$.ajax({
               url : url,
               data : params,
               async:false,
               dataType : "json",
               type : method,
               cache : false,
               error:function(errText){
                  alert("ajax加载数据异常!请联系管理员");
               },
               success:callback
           });
		}
      	
	</script> 
</head>
<body onload="query(${familyDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 商品管理 &gt;&gt; 商品属性规格管理 
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">		<div class="Fl">
			<my:permission key='sy-1704-02' value='属性规格新增'>
				<input type="button" class="formButton" value="添加" onclick="go('base/goodsattributeformat!addUI')"/>
				<input type="button" class="formButton" value="导入" onclick="openExportForm();" />
			</my:permission>
		</div>
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
			    <td>商品分类:</td>
				<td><s:textfield id="familyName" name="familyDTO.familyName" cssClass="formInput" maxlength="15" theme="simple"/></td>
				<td width="22px">&nbsp;</td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0">
		<tr>
			<th width="3%">序号</th>
			<th width="8%">商品分类</th>
			<th width="10%">商品属性</th>
			<th width="10%">使用状态</th>
			<th width="5%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<my:permission key='sy-1704-02' value='属性规格新增'>
				<input type="button" class="formButton" value="添加" onclick="go('base/goodsattributeformat!addUI')"/>
				<input type="button" class="formButton" value="导入" onclick="openExportForm();" />
			</my:permission>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
	
	
	<!-- 规格对话框 -->
		<div id="dialog-Format" style="display:none;" title="商品规格">
			<div style="display:block;width:100%;" id="formatContainer">
				
			</div>
		</div>
		
	<!-- 导入商品规格对话框 -->
 	<div id="dialog-export" style="display:none;margin:0 0;padding:0 0 0 0;" title="导入商品规格">
		<div style="display:block;width:96%;padding-top:15px;padding-left:10px;">
			<fieldset style="margin-top:20px;" id="addAttContal">
				<legend>导入商品规格</legend>
				<div style="display:block;margin:10px 20px;line-height:28px;overflow:hidden;">
					<s:form id="uploadForm" action="base/goodsattributeformat!loadExcel" method="post" enctype="multipart/form-data" target="iframe" onsubmit="return checkExport();">
						<div style="display:inline;float:left;padding-right:10px">Excel文件：
							<s:file id="upload" name="upload" accept=".xls" cssClass="formInput" theme="simple" onchange="autoUpload();"/>
							<span class="Color3">(只允许xls,xlsx文件，且大小不能超过1M!)</span>
							<span id="uploadValue" style="color: red;"><s:fielderror/></span>
						</div>
						<div style="display:inline;float:left;padding-right:10px"><input type="button" class="formButton" value="导   入" onclick="saveExport();"></div>
						<input type="button" id="cancelButton" class="formButton" onclick="closeDialog('dialog-export');" value="取   消" />
					</s:form>
					<iframe id="iframe" name="iframe" style="display: none"></iframe>
				</div>
			</fieldset>
		</div>
	</div>
		
</body> 
</html>