<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>油品管理</title>
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
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">

	var flags = false;
	//验证信息
	function check(){
		var name = $("#oilTypeName").val();
		var descr = $("#descr").val();
		if(name.length==0){
			$("#oilTypeNameError").show();
			$("#oilTypeNameError").addClass("errorMsg");
			$("#oilTypeNameError").html("油品名称不能为空！");
			return false;
		}else{
			$("#oilTypeNameError").hide();
			}
		if(descr.length>255){
			$("#descrMsg").show();
			$("#descrMsg").addClass("errorMsg");
			$("#descrMsg").html("油品描述数据过（255个字节）长！");
			return false;
		}else{
			$("#descrMsg").hide();
		}
		if(!flags){
			alert("油品已经存在！");
			return false;
			}
	}
	//查看时将所有的都设置为不可用的格式
	       $(document).ready(function (){
	       var method = document.getElementById("method"); 
	       if(method.value=="checkUI"){
	            setInputDisabled();
	            $("#status").attr("disabled",true);
	            $("#descr").attr("disabled",true);
	            $("#submitInput").attr("disabled",true);
	            }
	        });
	//不验证油品名称格式，只验证是否重名        
	function checkName(){
		var name = $("#oilTypeName").val();
		var oilTypeId = $("#oilTypeId").val();
			var params = {
				"oilTypeDto.oilTypeName" : name ,
				"oilTypeDto.oilTypeId" : oilTypeId
			}
			if(name.length>0){
		var actionUrl = "base/oilType!checkName";
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
			        	flags = data.flag;
			        	if(!data.flag){
				        	alert("该油品已经存在！");   
				       		return false;
					    }else{
							return true;
						    }
			        }
			    });
			}else{
				alert("该油品名称不能为空！");   
	       		return false;
				}
		}
	
	</script>
  </head>
  <body>

  <div class="Position">
		当前位置是：HOME &gt;&gt;基本设置 &gt;&gt; 油品管理
	</div>
	<s:form action="base/oilType" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>
	<s:if test="#session.user_session.userLevel!=2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<s:hidden name = "oilTypeDto.oilTypeId" id = "oilTypeId"></s:hidden>
			<tr>
				<th align="right"><span class="Color5">*</span>油品名称：</th>
		 		<td>
		 			<s:textfield name="oilTypeDto.oilTypeName"  id="oilTypeName"  maxlength="10" cssClass="formInput"  theme="simple" onblur="checkName();" />
		 			<label id="oilTypeNameError" class="errorMsg" style="display: none;"></label>
		 		</td>
		 		<th align="right"  width="20%">状         态：</th>
		        <td>
		        	<s:select name="oilTypeDto.status" id="status"  list="#request.statusValues" listKey="key" listValue="value"  cssClass="formInput" theme="simple"/>
		        </td>
			</tr>
		 	<tr>
		        <th align="right">操作人：</th>
		        <td>
		        <s:textfield name="oilTypeDto.operId"  id="operId"  maxlength="80" cssClass="formInput"  theme="simple" readonly="true"/>
		        </td>
		  	</tr>
		  	<tr>
		      <th align="right">油品描述：</th>
		      <td colspan="3">&nbsp;
		        <s:textarea name="oilTypeDto.descr" id="descr"  theme="simple" cssClass="formTextarea" cols="45" rows="5"  />
		        <label id="descrMsg" style="display: none;"></label> 
		       </td>
		  	</tr>
		</table>
	</s:if>

	 <div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1211-02' value='油品添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:if test="method=='editSave'">
			<my:permission key='sy-1211-03' value='油品修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:if test="method=='checkUI'">
			<my:permission key='sy-1211-03' value='油品修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('base/oilType!list')"/>
	 </div>
	 </s:form>
</body>
</html>