6<%@ page language="java" contentType="text/html; charset=UTF-8"
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
		<title>会员群组管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
			type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.4.2.min.js"></script>
	<link href="js/jquery/css/jquery.ui.all.css" rel="stylesheet"
			type="text/css" />
		<script src="js/jquery/jquery.ui.core.js"></script>
		<script src="js/jquery/jquery.ui.widget.js"></script>
		<script src="js/jquery/jquery.ui.mouse.js"></script>
		<script src="js/jquery/jquery.ui.button.js"></script>
		<script src="js/jquery/jquery.ui.draggable.js"></script>
		<script src="js/jquery/jquery.ui.position.js"></script>
		<script src="js/jquery/jquery.ui.resizable.js"></script>
		<script src="js/jquery/jquery.ui.dialog.js"></script>
		<script src="js/datepicker/WdatePicker.js"></script>
		<script src="js/common.js"></script>
		<script src="js/pubValidate.js"></script>
		<script src="js/pubValiPattern.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script type="text/javascript">	
		var rightCount=0;
		$(function(){
			rightCount = document.getElementById("right").options.length;
		});
		
	   function add(){
	     	var left=$("#left option:selected");
	     	$("#right").append(left);
	   }
	   
     function removeRight(){
     	var right=$("#right option:selected");
     	$("#left").append(right);
     }
     
     function addAll(){
     	var left=$("#left option");
     	$("#right").append(left);
     }
     
     function removeAll(){
     	var right=$("#right option");
     	$("#left").append(right);
     }

	function queryAll(){
		if(rightCount!=0){
			$("#right option:gt("+(rightCount-1)+")").remove();
		}else{
			$("#right option").remove();
		}
		
	    var left = document.getElementById("left");
		var sex = $("#sex").val();
		var areaId = $("#areaId").val();
		var groupId = $("#groupId").val();
		var memId=$("#memIdd").val();
		var params = {
		    "memDto.sex" : sex,
	        "memDto.areaId" : areaId,
	        "memDto.memId":memId,
	        "memGroupsDTO.groupId":groupId
	    }; 
	    $("#left option").remove();
	    var url="member/memGroups!queryAllList";
	     $.ajax({ 
		        url : url,
		        data : params,
		        dataType : "json",
		        cache : false,
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误!");	    
		        },
		        
		        success : function(data, textStatus) {
		        	//遍历数据，添加到select
		        	for(var i=0;i<data.length;i++){
		        		left.options.add(new Option(data[i].value , data[i].key));
		        	}
		        	
	     	}		
	     });
	}
	
	$(document).ready(function (){
	       var method = document.getElementById("method");
	       
	        if(method.value=='checkUI'){
	             setInputDisabled();
	            $("#perType").attr("readonly","readonly");
	            $("#sex").attr("readonly","readonly");
	            $("#status").attr("readonly","readonly"); 
	        }
	 });
	        
	        function memCheck(){
	        	var flag=setMemGroupName(document.getElementById("groupName"));
	        	if(!flag){
	        		return false;
	        	}
	        	
		       var method = $.trim($("#method").val());
		       var temp="";
		       $("#right option").each(function(){
		       		temp+=";"+$(this).val();
		       });
				$("#rights").val(temp.substring(1)); 
	           if(method=="addSave"){
                   return true;
                  }
		       else if(method=="editSave"){
			       return true;
			      }
		 	 }
		   
		   function setMemGroupName(obj){
		   		if($.trim(obj.value).length>0){
		   			$("#message").text("");
		   			$("#message").hide();
		   			return true;
		   		}else{
		   			$("#message").text("群组名必须添写");
		   			$("#message").show();
		   			return false;
		   		}
		   }
		   
	     function queryGroupInfo(){
	     	var groupId=document.getElementById("groupId").value;
	     	var actionUrl="/member/memGroups!addUI";
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
		     }
	     	});
	     }
	     
	  
	</script>
	</head>
	<body>
		<div class="Position">
			当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 会员群组管理
		</div>
		<s:form id="sform" action="member/memGroups" method="post"
			onsubmit="return memCheck();" theme="simple">
			
			<s:hidden name="method" id="method" />
			<s:hidden name="rights" id="rights"></s:hidden>
			<s:hidden name="memGroupsDTO.isSalePointStr" id="isSalePointStr" />
			<div class="List_tit">
				群组会员信息
			</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">
				<s:hidden name="memGroupsDTO.groupId" id="groupId" />
				<s:hidden name="memGroupsDTO.memId" id="memId" />
				<s:hidden name="memGroupsDTO.name" id="name" />
				<s:hidden name="memGroupsDTO.organId" id="organId" />
				<s:hidden name="memGroupsDTO.updateName" id="updateName" />
				<s:hidden name="memGroupsDTO.createTime" id="createTime" />
				<s:hidden name="memGroupsDTO.userName" id="userName"></s:hidden>
				<tr>
					<th align="right" width="20%">
						群组名称：
					</th>
					<td width="30%">
						<s:textfield name="memGroupsDTO.groupName" id="groupName"
							maxlength="20" cssClass="formInput"
							onblur="setMemGroupName(this);" />
						<label id="message" class="errorMsg" style="display: none;"></label>
						<span class="Color5">*</span>
					</td>
					<th align="right">
						群组状态：
					</th>
					<td>
						<s:select id="status" name="memGroupsDTO.status"
							list="#request.statusValues" listKey="key" listValue="value"
							cssClass="formSelect" />
					</td>
				</tr>
				<tr>
					<th align="right">
						会员群组描述：
					</th>
					<td colspan="3">
						<s:textarea name="memGroupsDTO.groupDesc" id="groupDesc"
							cssClass="formTextarea" cols="45" rows="20" maxlength="255" style="resize:none;" />
					</td>
				</tr>
			</table>

			<div class="List_tit">
				会员信息
			</div>

			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">
				
				<tr>
					<th>会员编号：</th>
					<td>
						<s:textfield name="memDto.memId" id="memIdd" cssClass="formInput" theme="simple" >
							
						</s:textfield>
					</td>
					<th>
						性别:
					</th>
					<td>
						<s:select name="memDto.sex" id="sex" list="#request.sexValues"
							listKey="key" listValue="value" headerKey="-1"
							headerValue="--请选择--" cssClass="formInput" theme="simple" />
					</td>
					<th>
						地区:
					</th>
					<td>
						<s:select name="memDto.areaId" id="areaId"
							list="#request.areaIdValues" listKey="key" listValue="value"
							headerKey="-1" headerValue="--请选择--" cssClass="formInput"
							theme="simple" />
					</td>
					
					<td>
						<input type="button" class="formButton" onclick="queryAll();"
							value="查 询" />
					</td>
				</tr>
				<tr>
					
				</tr>
				<tr>
					
					<td colspan="7" style="text-align: center;">
						<table style="width: 103%;" cellpadding="0" cellspacing="0">
							<tr>
								<td style="text-align: right;width: 20%;height: 100%;">
									<select id="left" multiple="multiple" style="height: 130px;width: 90px;">
									</select>
								</td>
								<td style="text-align: center;width: 5%;">
									<input  type="button" id="adds" value="添加》" class="formButton"  onclick="add();" />
									<br/>
									<input  type="button" id="removes" value="《移除" class="formButton"  onclick="removeRight();"/>
									<br/>
									<input  type="button" id="addAlls" value="全部添加" class="formButton"  onclick="addAll();" />
									<br/>
									<input  type="button" id="removeAlls" value="全部移除" class="formButton"  onclick="removeAll();" />
								</td>
									
								<td style="text-align: left;width: 25%;height: 100%;">
									<select id="right" multiple="multiple" style="height: 130px;width: 90px;">
										<s:iterator value="#request.groupslist" var="temp">
											<option value="${temp.key }">${temp.value }</option>
										</s:iterator>
									</select>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
			<div class="formTableBottom">
				<s:if test="method=='addSave'">
					<my:permission key='sy-1305-02' value='会员群组添加'>
						<input id="submitInput" name="addbtn" type="submit"
							class="formButton" value="保 存" onclick="return memCheck();" />
					</my:permission>
				</s:if>
				<s:else>
					<my:permission key='sy-1305-03' value='会员群组修改'>
						<input id="submitInput" type="submit" class="formButton"
							value="保 存" onclick="return memCheck();" />
					</my:permission>
				</s:else>
				<input type="button" class="formButton" value="取 消"
					onclick="go('member/memGroups!list')" />
			</div>
		</s:form>
	</body>
</html>