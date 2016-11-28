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
	<title>商户管理</title>
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
	<script type="text/javascript">
		//更改级别
		function changeLevel(){
			var userLevel = $('[name="userDTO.userLevel"]:checked').val();
			if(userLevel==0){
				$(".level0").show();
				$(".level1").hide();
				$(".level2").hide();
			}else if(userLevel==1){
				$(".level0").hide();
				$(".level1").show();
				$(".level2").hide();
			}else if(userLevel==2){
				$(".level0").hide();
				$(".level1").hide();
				$(".level2").show();
			}
		}
		
		var ajaxRole = function(){
			
			var userName = $("#userName").val();
			var userLevel = $('[name="userDTO.userLevel"]:checked').val();
			var organId = $("#organId").val();
		    var merId = $("#merId").val();
		    if(userLevel==0||userLevel==1){
		    	if(organId==""){
		    		alert("请选择发卡机构");
		    		return false;
		    	}
		    }else if(userLevel==2){
		    	if(merId==""){
		    		alert("请选择商户");
		    		return false;
		    	}
		    }
			var params = {
		   		"userDTO.userName" : userName,
		        "userDTO.userLevel" : userLevel,
		        "userDTO.organId" : organId,
		        "userDTO.merId" : merId
		    };
		    var actionUrl = "system/user!ajaxRole";
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
		            $("#role").html(data.obj.roleName);
		        } 
		    });
		}
		
		var check = function() {
			var userName = $("#userName").val(); 
		    var realName = $("#realName").val();
		    var organId = $("#organId").val();
		    var merId = $("#merId").val();
		    var teleNo = $("#teleNo").val();
		    var status = $("#status").val();
		    var userLevel = $('[name="userDTO.userLevel"]:checked').val();
		    var dlsFlag = $("#dlsFlag").val();
			if (userName==''){
				alert("登陆账号不能为空!");
		    	$("#userName").focus();
		    	return false;
			}
		    if (realName==''){
		    	alert("真实姓名不能为空!");	 
		    	$("#realName").focus();
		    	return false;
			}
			
			if(userLevel==1){
				if(organId<0){
					alert("请选择发卡机构!");
		    		$("#organId").focus();
		    		return false;
				}
			}else if(userLevel==2){
				if(merId<0){
					alert("请选择商户!");
		    		$("#merId").focus();
		    		return false;
				}
			}
		}
		
	</script>

</head>
<body onload="ajaxRole();">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 系统设置 &gt;&gt; 用户管理
	</div>
	<s:form action="system/user" method="post" theme="simple" onsubmit="document.getElementById('submitInput').disabled = true;return true;">
	<s:hidden name="method"/>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		<tr>
			<th align="right" width="20%"><span class="Color5">* </span>登陆名称：</th>
		 	<td width="30%">
		 		<s:if test="method=='addSave'">
		 			<s:textfield name="userDTO.userName" id="userName" maxlength="20" cssClass="formInput"/>
		 		</s:if>
		 		<s:else>
		 			<s:property value="userDTO.userName"/><s:hidden name="userDTO.userName" id="userName"/>
		 		</s:else>
		 	</td>
		  	<th align="right" width="20%"><span class="Color5">* </span>真实姓名：</th>
		   	<td width="30%"><s:textfield name="userDTO.realName" id="realName" maxlength="20" cssClass="formInput"/></td>
		</tr>
		<tr>
		 	<th align="right">用户级别：</th>
		   	<td colspan="3"><s:radio name="userDTO.userLevel" id="userLevel" list="#request.userLevelValues" listKey="key" listValue="value" onclick="changeLevel();ajaxRole();"/></td>
		</tr>
		<!-- 总部 -->
		<s:if test="#session.user_session.userLevel==0">
		<tr class="level0" <s:if test="userDTO.userLevel!=0">style="display: none;"</s:if>>
			<th align="right">发卡机构：</th>
		   	<td colspan="3"><s:property value="#request.redpassOrganName"/></td>
		</tr>
		<tr class="level1" <s:if test="userDTO.userLevel!=1">style="display: none;"</s:if>>
		   	<th align="right">发卡机构：</th>
		   	<td>
		       	<s:select name="userDTO.organId" id="organId" list="#request.organValues" listKey="key" listValue="value" cssClass="formSelect" onchange="ajaxRole();"/>
		  	</td>
		 	<th align="right">是否代理商：</th>
		  	<td>
		    	<s:select name="userDTO.dlsFlag" id="dlsFlag" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect"/>
			</td>
		</tr>
		
		</s:if>
		<!-- 发卡机构 -->
		<s:if test="#session.user_session.userLevel==1">
		<tr class="level1" <s:if test="userDTO.userLevel!=1">style="display: none;"</s:if>>
			<th align="right">发卡机构：</th>
			<td>
		  		<s:property value="userDTO.organName"/><s:hidden name="userDTO.organId" id="organId"/>
		 	</td>
		 	<th align="right">是否代理商：</th>
		 	<td>
		  		<s:select name="userDTO.dlsFlag" id="dlsFlag" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect"/>
		  	</td>
		</tr>
		</s:if>
		<tr class="level2" <s:if test="userDTO.userLevel!=2">style="display: none;"</s:if>>
			<th align="right">商户：</th>
			<td colspan="3">
		 		<s:if test="#session.user_session.userLevel==0||#session.user_session.userLevel==1">
		     		<s:select name="userDTO.merId" id="merId" list="#request.merchantValues" listKey="key" listValue="value" cssClass="formSelect" onchange="ajaxRole();"/>
		   		</s:if>
		     	<s:else>
		      		<s:property value="userDTO.merName"/><s:hidden name="userDTO.merId" id="merId"/>
		    	</s:else>
			</td>
		</tr>
		<tr>
			<th align="right">联系电话：</th>
		   	<td><s:textfield name="userDTO.teleNo" id="teleNo" maxlength="20" cssClass="formInput"/></td>
		 	<th align="right">状态：</th>
		 	<td><s:select name="userDTO.status" id="status" list="#request.statusValues" listKey="key" listValue="value" cssClass="formSelect"/></td>
		</tr>
		<tr>
		 	<th align="right">角色：</th>
		  	<td colspan="3" id="role"></td>
		</tr>
		<tr>
			<th align="right">密码错误次数：</th>
		 	<td id="wrpass"><s:property value="userDTO.wrpass"/></td>
		  	<th align="right">最后登陆时间：</th>
		  	<td><s:property value="userDTO.lastLoginTime"/></td>
		</tr>
	</table>
	<div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-9103-02' value='用户添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-9103-03' value='用户修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		<input type="button" class="formButton" value="返 回" onclick="go('system/user!list')"/>
	 </div>
	</s:form>
</body>
</html>