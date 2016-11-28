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
	<title>地区管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />	
	<script src="js/jquery-1.4.2.min.js"></script>
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui/easyui.css">
	<script type="text/javascript" src="js/jquery-easyui/jquery.easyui.min.js"></script>
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
	<script type="text/javascript">
		//加载添加页面
		var addUI = function(parentName,parentId){
			var consoleDlg = $("#dialog-confirm");
			consoleDlg.attr("title","编辑");					
			consoleDlg.find("#dialog_id").val('');
    		consoleDlg.find("#dialog_displayName").val(''); 
			consoleDlg.find("#parent").html(parentName+'<input type="hidden" id="dialog_parentId" name="areaDTO.parentId" value="'+parentId+'"/>');
            // 打开对话框
			$("#dialog-confirm").dialog({
				resizable: true,
				top: 240,
				height:280,
				width:600,
				modal: true,
				buttons: {
					'取消': function() {
						$(this).dialog('close');
					},
					'确定': addData
				}
			}); 
		}
		
		//添加方法
		var addData = function() {   
		    var consoleDlg = $("#dialog-confirm");  			
		    var dialog_areaCode = $.trim(consoleDlg.find("#dialog_areaCode").val()); 
		    var dialog_displayName = $.trim(consoleDlg.find("#dialog_displayName").val());
		    var dialog_parentId = $.trim(consoleDlg.find("#dialog_parentId").val());
		    var dialog_status = $.trim(consoleDlg.find("#dialog_status").val());
		    
		    if (dialog_displayName==''){
		    	alert("名称不能为空!");	 
		    	consoleDlg.find("#dialog_displayName").focus();
		    	return
			}
			
		    var params = {
		    	"areaDTO.parentId" : dialog_parentId,
		   		"areaDTO.areaCode" : dialog_areaCode,
		        "areaDTO.displayName" : dialog_displayName,
		        "areaDTO.status" : dialog_status
		    };
		    var actionUrl = "system/area!add";
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
		            if(data.ajaxResult == "ajaxsuccess") {	                
		                consoleDlg.dialog("close");   
		                alert("操作成功!");
		                query('1');
		            } else if(data.ajaxResult == "ajaxfailure") {	                 
		                alert(data.msgResult); 
		            }else {
		            	alert("操作失败!");   
		            }   
		        } 
		    });   
		};  
		
		//加载修改页面
		var loadData = function(id) {
		  	var params = {   
		        "areaDTO.id" : id 
		  	};  
		  	var actionUrl = "system/area!editUI"; 
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
					var rowData = data.objResult;
		            consoleDlg.find("#dialog_id").val(rowData.id);
					consoleDlg.find("#dialog_areaCode").val(rowData.areaCode);
		            consoleDlg.find("#dialog_displayName").val(rowData.displayName);
		            consoleDlg.find("#dialog_status").val(rowData.status);
		            consoleDlg.find("#parent").html(rowData.parentName);
		            // 打开对话框
					$("#dialog-confirm").dialog({
						resizable: true,
						top: 370,
						height:280,
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
		    var dialog_id = consoleDlg.find("#dialog_id").val();
			var dialog_displayName = $.trim(consoleDlg.find("#dialog_displayName").val());  
			var dialog_areaCode = $.trim(consoleDlg.find("#dialog_areaCode").val()); 
			var dialog_status = $.trim(consoleDlg.find("#dialog_status").val());
			
		    if (dialog_displayName==''){
		    	alert("角色名称不能为空!");	 
		    	consoleDlg.find("#dialog_displayName").focus();
		    	return
			}
			
		    var params = {
		        "areaDTO.id" : dialog_id,
		        "areaDTO.areaCode" : dialog_areaCode,
		        "areaDTO.displayName" : dialog_displayName,
		        "areaDTO.status" : dialog_status
		    };  

		    var actionUrl = "system/area!update";   
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
		           	} else if(data.ajaxResult == "ajaxfailure") {	                 
		                alert(data.msgResult); 
		            }else {
		            	alert("操作失败!");   
		            }   
		        }   
		    });   
		}; 
		//查询方法
		function query() {
			$('#test').treegrid({
				title:'地区管理',
				iconCls:'icon-ok',
				height:360,
				rownumbers: true,
				animate:true,
				collapsible:true,
				fitColumns:true,
				url:'system/area!getList',
				idField:'id',
				treeField:'displayName',
				showFooter:true,
				rowStyler:function(row){
					if (row.persons > 1){
						return 'background:#AAD684;color:#fff';
					}
				},
				columns:[[
	                {title:'名称',field:'displayName',width:180},
	                {title:'区号',field:'areaCode',width:180},
					{title:'常用操作',field:'operate',width:180}
				]]
			});
		}
		$(document).ready(function(){
			areaSelection.selectionIdPre = "level";
			areaSelection.firstAreaLevel = 1;
			areaSelection.lastArealevel = 3;
			areaSelection.initAreaSelection(${userDTO.countyid});
		});
	</script>
	
</head>
<body onload="query();">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 系统设置 &gt;&gt; 地区管理
	</div>
	<!-- 
	<div id="selection">
      <select id="level1" name="userDTO.provinceid" style="width:90px;"></select>
      <select id="level2" name="userDTO.cityid" style="width:90px;"></select>
      <select id="level3" name="userDTO.countyid" style="width:90px;"></select>
    </div>
     -->
	<div id="dialog-confirm" style="display: none;" title="编辑角色">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		      <tr>
		       	<th align="right" width="30%">上级名称：</th>
		        <td width="70%" id="parent"></td>
		      </tr>
		      <tr>
		       	<th align="right">地区名称：</th>
		        <td><input type="text" id="dialog_displayName" name="areaDTO.displayName" maxlength="20" class="formInput"/><input type="hidden" id="dialog_id" name="areaDTO.id"/></td>
		      </tr>
		      <tr>
		      	<th align="right">区号：</th>
		        <td><input type="text" id="dialog_areaCode" name="areaDTO.areaCode" maxlength="20" class="formInput"/></td>
		      </tr>
		      <tr>
		      	<th align="right">是否显示：</th>
		        <td><s:select id="dialog_status" list="#request.statusValues" listKey="key" listValue="value" theme="simple"/></td>
		      </tr>
		 </table>
	</div>
	
	<div style="width: 96%;margin:10px auto;">
	<table id="test"></table>
	</div>
	<div class="listBottom">
		<div class="Fl">
			<my:permission key='sy-9201-02' value='地区添加'>
				<input type="button" class="formButton" value="添加" onclick="addUI('顶级地区',0);"/>
			</my:permission>
		</div>
	</div>
</body> 
</html>