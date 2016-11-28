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
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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

	function goHome() {
		parent.leftFrame.location.href='<%=basePath%>system/index!firstleft';
		parent.main.location.href='<%=basePath%>system/index!right';
		}

	$(document).ready(function (){
	       var method = document.getElementById("method"); 
	        if(method.value=='checkUI'){
	             setInputDisabled();
	             $("#status").attr("disabled","disabled");
	             $("#defaultShow").attr("disabled","disabled"); 
	             $("#parentId").attr("disabled","disabled");
	             $("#familyWay").attr("disabled","disabled");
	            }
	        });
    
    	var familyNameFlag;	
		
		/**判断分类名称*/
		var validateName = function(obj){
			 var flag1 = false;
		     var flag2 = false;
             var type = ["isNull"];
             var errorMsg = ["分类名称不能为空!"];
             //检验分类名称格式
             flag1 = showErrorMsg(obj,type,errorMsg,"errorMsg","warnMsg");
             //检验名称长度
             flag2 = checkLenMsg(obj,"长度不能大于30个汉字","errorMsg","",60)  
				
				var familyId = $("#familyId").val();
				var familyName = $("#familyName").val();
				if (!(flag1&&flag2)){
			    	return false;
				}
			    else{
			    	var params = {
			    			"goodsfamilyDto.familyId" : familyId,
					        "goodsfamilyDto.familyName" : familyName
					    }; 
						
					var actionUrl = "base/goodsfamily!checkName";   
					$.ajax({   
				        async:false,
				        url : actionUrl,   
				        data : params,   
				        dataType : "json",
				        cache : false, 
				        type : "POST",
					    error : function(textStatus, errorThrown) {   
					    	alert("系统ajax交互错误!");  
					    },
					    success : function(data, textStatus) {   
					    	if (data.flag) {
					    		var type = ["isRate"];
             					var errorMsg = ["该分类已存在!"];
					    		showErrorMsg(obj,type,errorMsg,"errorMsg","warnMsg");
						    	familyNameFlag = false;
						    	return familyNameFlag;
							}else {
								
						    	familyNameFlag = true;
						    	return familyNameFlag;
						    	alert(familyNameFlag);
						 	}
						}
					 });		    	
			    	}
				}
	  	
	  	function familyNameFocus(obj){
		   showWarnMsg(obj,"可填写汉字、字母以及数字!","errorMsg","warnMsg");
		}
		
	  
    
		//修改方法
		var check = function() {
					    
		    var familyName = getEle("familyName");
            validateName(familyName);  
            //alert(familyNameFlag);	   
           	     	              
			if(!(familyNameFlag)){
	            alert("请按照提示信息正确填写.(*部分为必填项!)");
		        return false;					
			}			
		}
		
       //关闭输入法 
		function colseImeMode(obj){
			 obj.style.css = ("ime-mode","disabled");
			}
			
		function addImg(id, src) {
            var target = document.getElementById(id);
            if (target) {
                var img = document.createElement("img");
                img.src = src;
                target.appendChild(img);
            }
}	
			
	
	</script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 商品分类管理 &gt;&gt; 添加分类
	</div>
	<s:form action="base/goodsfamily" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>
	<s:if test="#session.user_session.userLevel==0">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
				<s:hidden name="goodsfamilyDto.familyId" id="familyId"/>
		        <th align="right" width="20%"><span class="Color5">* </span>分类名称：</th>
		        <td width="30%"><s:textfield name="goodsfamilyDto.familyName" id="familyName" cssStyle="width:150px;" onkeyup = "allowEnCnNu(this)" maxlength="60" cssClass="formInput" onfocus="familyNameFocus(this);" onblur="validateName(this);" theme="simple"/><label id="warnMsg" style="display: none;"></label> <label id="errorMsg" style="display: none;"></label></td>
				<th align="right">上级分类：</th>
		 		<td>
		 		<s:select name="goodsfamilyDto.parentId" id="parentId" cssStyle="width:156px;" list="#request.preFamilyValues" listKey="key"  listValue="value" cssClass="formSelect" theme="simple"/>
		 		</td>	
			</tr>
		 	<tr>		 			      	
		      	<th align="right">使用状态：</th>
		        <td><s:select name="goodsfamilyDto.status" id="status" cssStyle="width:156px;" list="#request.statusValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
		  		<th align="right">添加图片：</th>
		        <td>
    				<input type="file" id="file" name="theFile" onchange="document.getElementById('fPicPath').value=this.value"/>   
					<!-- 
					<input type="button" value="添加图片" onclick="addImg('asd',document.getElementById('file').value);" />
					<input type="hidden" id="fPicPath" name="goodsfamily.fPicPath" value="">
					<div id="asd"></div>
					<input id="File1" type="file" name="fPicPath"/>
					<input id="btAdd" type="button" value="Add" onclick="alert(document.getElementById('File1').value);" />
		        	 -->
		        </td>
		  	</tr>
		  	<tr>
		  		<th align="right">默认展开：</th>
		        <td><s:select name="goodsfamilyDto.defaultShow" id="defaultShow" cssStyle="width:156px;" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
		        <th align="right">分类类型：</th>
		        <td><s:select name="goodsfamilyDto.familyWay" id="familyWay" cssStyle="width:156px;" list="#request.familySign" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>		      	
		  	</tr>	  			   		      	
		   	<tr>
		   		<th align="right">关键字：</th>
		        <td><s:textfield name="goodsfamilyDto.keyWords" id="keyWords" cssStyle="width:150px;" maxlength="20" cssClass="formInput" theme="simple"/></td>
		   	</tr>
		</table>
	</s:if>
		
	 <div class="formTableBottom">
	 	<s:if test="#session.user_session.userLevel==0">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1701-02' value='商品分类添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-1701-03' value='商品分类修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		</s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('base/goodsfamily!list')"/>
     </div>
	 </s:form>
</body>
</html>