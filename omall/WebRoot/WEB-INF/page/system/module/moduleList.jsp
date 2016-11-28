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
	<title>模块管理</title>
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
	<script type="text/javascript"><!--
		
		//查询方法
		function query(page) {
		
			var name = $.trim($("#name").val());
			var parentName = $.trim($("#parentName").val());

			var params = {
		        "moduleDTO.name" : name,
		        "moduleDTO.parentName" : parentName,
		        "moduleDTO.page" : page
		    }; 
		   ajaxData("system/module!jsonPageList",params);
		}

		//加载修改页面
		var loadData = function(id) {
			
		  	var params = {   
		        "moduleDTO.id" : id
		  	};  
		  	var actionUrl = "system/module!editUI"; 
		    $.ajax( {   
		        url : actionUrl,   
		        data : params,   
		        dataType : "json",   
		        cache : false,   
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误!");	    
		        }, 
		        success : function(data, textStatus) { 
					var consoleDlg = $("#dialog-confirm"); 
					consoleDlg.attr("title","编辑模块");
					var rowData = data.objResult;
					var id = "<input type=\"hidden\" id=\"dialog_id\" name=\"moduleDTO.id\" value=\""+rowData.id+"\" />"+rowData.id
					consoleDlg.find("#id").html(id); 
		    		consoleDlg.find("#dialog_name").val(rowData.name); 
		    		consoleDlg.find("#dialog_sortOrder").val(rowData.sortOrder); 
		    		consoleDlg.find("#dialog_linkAddr").val(rowData.linkAddr);
		    		consoleDlg.find("#dialog_isOften").val(rowData.isOften);

		            // 打开对话框   
					$("#dialog-confirm").dialog({
						resizable: true,
						top: 370,
						height:360,
						width:600,
						modal: true,
						buttons: {
							'取消': function() {
								$(this).dialog('close');
							},
							'确定': updateData
						}
					});    
		        }   
		    });
		};
		
		//修改方法
		var updateData = function() {
			var consoleDlg = $("#dialog-confirm");  			
		    var dialog_id = $.trim(consoleDlg.find("#dialog_id").val()); 
		    var dialog_name = $.trim(consoleDlg.find("#dialog_name").val());
		   	var dialog_sortOrder = $.trim(consoleDlg.find("#dialog_sortOrder").val()); 
		    var dialog_linkAddr = $.trim(consoleDlg.find("#dialog_linkAddr").val()); 
		    var dialog_isOften = $.trim(consoleDlg.find("#dialog_isOften").val()); 
		         
			if (dialog_id==''){
				alert("模块ID不能为空!");	 
		    	consoleDlg.find("#dialog_id").focus();
		    	return;
			}
		    if (dialog_name==''){
		    	alert("模块名称不能为空!");	 
		    	consoleDlg.find("#dialog_name").focus();
		    	return;
			}
			if (dialog_sortOrder==''){
		    	alert("排序号不能为空!");	 
		    	consoleDlg.find("#dialog_sortOrder").focus();
		    	return
			}

		    var params = {
		   		"moduleDTO.id" : dialog_id,
		   		"moduleDTO.name" : dialog_name,
		   		"moduleDTO.sortOrder" : dialog_sortOrder,
		   		"moduleDTO.linkAddr" : dialog_linkAddr,
		   		"moduleDTO.isOften" : dialog_isOften
		    };
		    
		    var actionUrl = "system/module!update";   
		    $.ajax( {   
		        url : actionUrl,   
		        data : params,   
		        dataType : "json",
		        cache : false, 
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误!");  
		        },   
		        success : function(data, textStatus) {   
		            if (data.ajaxResult == "ajaxsuccess") {   
						consoleDlg.dialog("close");
		                alert("操作成功!");
		                query($("#currPage").text());              
		            } else {
		            	alert("操作失败!");
		            }
		        }
		    });
		};
	
	function validatelength(obj){
		 obj.value = obj.value.substring(0, 39);
		}
		
	
	
	</script> 
	
	
</head>
<body onload="query(1);">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 系统设置 &gt;&gt; 模块管理
	</div>
	
	<div id="dialog-confirm" style="display: none;" title="编辑角色">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		      <tr>
		       	<th align="right" width="20%">模块ID：</th>
		        <td width="30%" id="id"></td>
		        <th align="right" width="20%">模块名称：</th>
		        <td width="30%"><input type="text" id="dialog_name" name="moduleDTO.name" maxlength="20" class="formInput"/></td>
		      </tr>
		      <tr>
		      	<th align="right">排序号：</th>
		        <td><input type="text" id="dialog_sortOrder" name="moduleDTO.sortOrder" maxlength="4" class="formInput"/></td>
		      	<th align="right">是否常用：</th>
		        <td><s:select id="dialog_isOften" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
		      </tr>
		      <tr>
		      	<th align="right">URL地址：</th>
		        <td colspan="3"><textarea class="formTextarea" id="dialog_linkAddr" name="moduleDTO.linkAddr" onkeyup="validatelength(this)" ></textarea></td>
		      </tr>
		 </table>
	</div>
	
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
			    <td>模块名称:</td>
				<td><input type="text" id="name" name="name" class="formInput"/></td>
			    <td>上级模块:</td>
				<td><input type="text" id="parentName" name="parentName" class="formInput"/></td>
	        	<td><input type="button" class="formButton" onclick="query(1);" value="查 询" /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="5%">序号</th>
			<th width="10%">模块ID</th>
			<th width="15%">模块名称</th>
			<th width="15%">上级模块</th>
			<th width="10%">排序号</th>
			<th width="18%">URL地址</th>
			<th width="10%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>