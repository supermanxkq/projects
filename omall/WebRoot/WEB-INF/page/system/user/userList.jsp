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
	<title>用户管理</title>
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
		
		//初始化密码
		var initData = function(id) {
			if(confirm("是否初始化密码？")){
				var consoleDlg = $("#dialog-confirm");   
	    		var params = {   
	        		"userDTO.userName" : id 
	    		};   
	    		var actionUrl = "system/user!init";   
	    		$.ajax({   
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
		                	alert("初始化成功，密码为123456"); 
		            	}else {
		            		alert("初始化失败!");   
		            	}
	        		}   
	    		});  
	        }
		};
		
		function bangUKeyUI(userName){
			var consoleDlg = $("#dialog-key"); 
		    consoleDlg.find("#dialog_userName").val(userName);
		    consoleDlg.find("#dialog_wPwd1").val("");
		    consoleDlg.find("#dialog_wPwd2").val("");
		    
			// 打开对话框
			$("#dialog-key").dialog({
				resizable: true,
				top: 370,
				height:170,
				width:650,
				modal: true,
				buttons: {
					'取消': function() {
						$(this).dialog('close');
					},
					'确定': bangUKey
				}
			});
		}
		
		//绑定UKEY
		function bangUKey(){
			var consoleDlg = $("#dialog-key"); 
		    var dialog_userName = $.trim(consoleDlg.find("#dialog_userName").val());
		    var dialog_wPwd1 = $.trim(consoleDlg.find("#dialog_wPwd1").val());
		    var dialog_wPwd2 = $.trim(consoleDlg.find("#dialog_wPwd2").val());
		    if(dialog_wPwd1==""||dialog_wPwd2==""){
				window.alert ( "请输入写密码！");
				return false;
			}
			try{
				var DevicePath,mylen,ret;
				var s_simnew1 = new ActiveXObject("Syunew6A.s_simnew6");
				DevicePath = s_simnew1.FindPort(0);
				if( s_simnew1.LastError!= 0 ){
					window.alert ( "未发现加密锁，请插入加密锁");
					return false;
				}
				
				//读取锁的唯一ID
				var keyID = toHex(s_simnew1.GetID_1(DevicePath))+toHex(s_simnew1.GetID_2(DevicePath));
				if(keyID==""){
					window.alert ( "未发现加密锁，请插入加密锁");
					return false;
				}
				
				var params = {
		       		"userDTO.keyID" : keyID
		   		};
		   		var actionUrl = "system/user!checkKeyID";
		   		$.ajax({
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
		                	mylen = s_simnew1.YWriteString(dialog_userName, 1, dialog_wPwd1, dialog_wPwd2, DevicePath);
							if( s_simnew1.LastError!= 0 ){ 
								window.alert ( "用户名写入失败");
								return false;
							}
							
							s_simnew1.SetBuf( mylen, 0);
							ret = s_simnew1.YWrite(0, 1, dialog_wPwd1, dialog_wPwd2,DevicePath);
							if( ret != 0 ){ 
								window.alert ( "用户名写入失败2");
								return false;
							}
							
							var params = {
					       		"userDTO.userName" : dialog_userName,
					       		"userDTO.keyID" : keyID
					   		};
					   		var actionUrl = "system/user!bangUKey";
					   		$.ajax({
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
						                alert("绑定成功");
						                query($("#currPage").text());	
					            	}else {
					            		alert("绑定失败!");   
					            	}
					       		}
					   		}); 
		            	}else {
		            		alert(data.msgResult);   
		            	}
		       		}
		   		}); 
				
			}catch(e){  
				alert(e.name + ": " + e.message);
				return false;
			}
		}
		
		//解绑UKEY
		function tieUKey(userName){
			if(confirm("是否解绑UKEY？")){
	    		var consoleDlg = $("#dialog-key");
	    		var params = {   
	        		"userDTO.userName" : userName 
	    		};   
	    		var actionUrl = "system/user!tieUKey";   
	    		$.ajax({
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
			                alert("解绑成功");
			                query($("#currPage").text());	
		            	}else {
		            		alert("解绑失败!");   
		            	}
	        		}   
	    		});  
	        }
		}
		
		//查询方法
		function query(page) {
			var userName = $.trim($("#userName").val());
			var realName = $.trim($("#realName").val());
			var status = $.trim($("#status").val());
			
			var params = {
		        "userDTO.userName" : userName,
		        "userDTO.realName" : realName,
		        "userDTO.status" : status,
		        "userDTO.page" : page
		    };
		   ajaxData("system/user!jsonPageList",params);
		}
	--></script> 
</head>
<body onload="query(1);">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 系统设置 &gt;&gt; 用户管理
	</div>
	
	<s:hidden name="userDTO.userType" id="userType"/>
	
	<div id="dialog-key" style="display: none;" title="绑定UKEY">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		      <tr>
		       	<th align="right" width="15%">请输入写密码：<input type='hidden' id='dialog_userName' /><input type='hidden' id='keyID' /></th>
		        <td width="30%"><input type="text" id="dialog_wPwd1" maxlength="8" class="formInput"/>-<input type="text" id="dialog_wPwd2" maxlength="8" class="formInput"/></td>
		      </tr>
		 </table>
	</div>
	
	<div class="search">
		<div class="Fl">
			<my:permission key='sy-9103-02' value='用户添加'>
				<input type="button" class="formButton" value="添加" onclick="go('system/user!addUI')"/>
			</my:permission>
		</div>
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
			    <td>登陆名称:</td>
				<td><input type="text" id="userName" name="userName" class="formInput"/></td>
				<td>姓名:</td>
				<td><input type="text" id="realName" name="realName" class="formInput"/></td>
				<td>状态:</td>
				<td>
						<s:select name="status" id="status"
							list="#request.statusValues" listKey="key" listValue="value"
							cssClass="formInput"
							theme="simple" />
				</td>
	        	<td><input type="button" class="formButton" onclick="query(1);" value="查 询" /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%">登陆名称</th>
			<th width="8%">姓名</th>
			<th width="10%">发卡机构</th>
			<th width="10%">商户</th>
			<th width="8%">用户级别</th>
<%--			<th width="8%">绑定UKEY</th>--%>
			<th width="8%">状态</th>
			<th width="13%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<my:permission key='sy-9103-02' value='用户添加'>
				<input type="button" class="formButton" value="添加" onclick="go('system/user!addUI')"/>
			</my:permission>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body>
</html>