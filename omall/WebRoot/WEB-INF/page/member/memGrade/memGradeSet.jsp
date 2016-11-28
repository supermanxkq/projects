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
	<title>会员等级管理</title>
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
		
		/**查看方法输入框禁用*/
		$(document).ready(function (){
	        var method = document.getElementById("method"); 
	       
	        if(method.value=='checkUI'){
	             setInputDisabled();
	            }
	        });
	
		
		gradeNameFlag = false;
		lowerLimitFlag = false;
		upperLimitFlag = false;
		imgSrcFlag = false;
		
		/**获取选中的模版的数据*/		
		var validateName = function(obj){
			 var flag = false;
             var type = ["isNull"];
             var errorMsg = ["会员等级名称不能为空!"];
             //检验分类名称格式
             flag = showErrorMsg(obj,type,errorMsg,"errorMsg","errorMsg");
				
				var gradeId = $("#gradeId").val();
				var gradeName = $("#gradeName").val();
				if (!flag){
			    	return false;
				}
			    else{
		    	var params = {
		    			"memGradeDto.gradeId" : gradeId,
				        "memGradeDto.gradeName" : gradeName
				    }; 
					
				var actionUrl = "memGrade/memGrade!checkName";   
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
	           				var errorMsg = ["该会员等级已存在!"];
				    		showErrorMsg(obj,type,errorMsg,"errorMsg","errorMsg");
					    	gradeNameFlag = false;
						}else {								
					    	gradeNameFlag = true;					    	
					 	}
					}
				});	
				return gradeNameFlag;	    	
			}
		}
				
		var validateLower = function(obj){
             var type = ["isNull"];
             var errorMsg = ["积分下限不能为空!"];
             lowerLimitFlag = showErrorMsg(obj,type,errorMsg,"lowerMsg","lowerMsg"); 
             return lowerLimitFlag;           
        }
        
        var validateUpper = function(obj){
			 var flag = false;
             var type = ["isNull"];
             var errorMsg = ["积分上限不能为空!"];
             flag = showErrorMsg(obj,type,errorMsg,"upperMsg","upperMsg");
             if(flag){
            	 var lower = $("#lowerLimit").val();
            	 var upper = $("#upperLimit").val();
            	 if(lower-upper>=0){
            		 var type = ["isEmail"];
        			 var errorMsg = ["积分上限应大于积分下限!"];
            		 showErrorMsg(obj,type,errorMsg,"upperMsg","upperMsg"); 
            		  upperLimitFlag = false;           		 
            	 }else{
            		 upperLimitFlag = true;
            	 }	
             } 
             return upperLimitFlag;           
        }


        function imgSrcChange() {
            
			
        	$('#imgSrcMsg').html('');
        	$('#imgSrcMsg').hide();
        	var method = document.getElementById("method");
        	if(method.value=='editSave'&& $.trim($('#imgSrc').val()) == ""){
				imgSrcFlag = true;
        	}
        	else{            	
				if($.trim($('#imgSrc').val()) == "") {
					$('#imgSrcMsg').html("等级图片不能为空！");
		        	$('#imgSrcMsg').show();
					imgSrcFlag = false;
					return false;
				}
				var strRegex = "(.jpg|.png|.gif|.ps|.jpeg)$"; //用于验证图片扩展名的正则表达式
		        var re=new RegExp(strRegex);
		        if (re.test($('#imgSrc').val().toLowerCase())){
		        	$('#imgSrcMsg').html('');
		        	$('#imgSrcMsg').hide();
		        	
		        	//if(imgSrcFlag){
		        		//document.getElementById("divSrc").style.display = "none";
		        	//}
		        	imgSrcFlag = true;	
		        } else {
		        	$('#imgSrcMsg').html("文件类型不支持！");
		        	$('#imgSrcMsg').show();
		        	imgSrcFlag = false;
		        }
	        }
	        
		}
				
				
				
		//修改方法
		var check = function() {	              
			
			var gradeName = getEle("gradeName");
            gradeNameFlag = validateName(gradeName);            
            var lowerLimit = getEle("lowerLimit");
            lowerLimitFlag = validateLower(lowerLimit);           
            var upperLimit = getEle("upperLimit");
            upperLimitFlag = validateUpper(upperLimit);
            imgSrcChange();
                        
		    if(!(gradeNameFlag&&lowerLimitFlag&&upperLimitFlag&&imgSrcFlag)){
		    
				return false;	
		    }									
		}
			
	
       	//关闭输入法 
		function colseImeMode(obj){
			 obj.style.css = ("ime-mode","disabled");
		}
	
	
	</script> 
</head>
<body>
	
	<div class="Position">
		当前位置是：HOME &gt;&gt; 会员管理 &gt;&gt; 会员等级管理
	</div>
	<s:form action="memGrade/memGrade" method="post" enctype="multipart/form-data" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>
	<s:if test="#session.user_session.userLevel==0">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		   	<tr>
		   		<th width="45%" align="right"><span class="Color5">* </span>会员等级名称：</th>
		        <td>
		        	<s:select name="memGradeDto.gradeName" id="gradeName" onblur="validateName(this);" cssStyle="width:200px;"
			        list="#request.memGrade" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
			        <s:hidden name="memGradeDto.gradeId" id="gradeId"/>
			        <label id="errorMsg" class="errorMsg" style="display: none;"></label>
		        </td>
		   	</tr>
		   	<tr>
		      	<th align="right"><span class="Color5">* </span>积分下限：</th>
		        <td>
			        <s:textfield name="memGradeDto.lowerLimit" id="lowerLimit" onblur="validateLower(this);" onkeyup="replaceToNum(this);" cssStyle="width:195px;"  maxlength="6" cssClass="formInput"/> 
			        <label id="lowerMsg" class="errorMsg" style="display: none;"></label>			                
		        </td>
		   	</tr>
		   	<tr>
		   		<th align="right"><span class="Color5">* </span>积分上限：</th>
		        <td>
					<s:textfield name="memGradeDto.upperLimit" id="upperLimit" onblur="validateUpper(this);" onkeyup="replaceToNum(this);" cssStyle="width:195px;"  maxlength="6" cssClass="formInput"/> 
			        <label id="upperMsg" class="errorMsg" style="display: none;"></label>			
				</td>
		    </tr>
		    
		    <tr>
		   		<th align="right">等级图片：</th>
		        <td>
					
					
					<s:if test="method=='checkUI' && memGradeDto.imgSrcFileName!=null && memGradeDto.imgSrcFileName!=''">
		        		<img src="<s:url value='%{memGradeDto.imgSrcFileName}'/>" width="240" height="180" />
		        	</s:if>
		        	<s:else>
		        		<s:file name="memGradeDto.imgSrc" size="40" id="imgSrc" cssStyle="width:200px;" onchange="imgSrcChange()" />
						<label id="imgSrcMsg" class="errorMsg" style="display: none;"></label>
						<span class="Color3">（只允许 jpg，png，gif，ps，jpeg 格式文件）</span><br/>
						<s:if test="method=='editSave' && memGradeDto.imgSrcFileName!=null && memGradeDto.imgSrcFileName!=''">
						<div id="divSrc" style="">
							<img src="<s:url value='%{memGradeDto.imgSrcFileName}'/>" width="240" height="180">
						</div>	
						</s:if>					
		        	</s:else>
				</td>
		    </tr>	    		    
		    <tr>
		   		<th align="right">使用状态：</th>
		        <td>
					<s:select name="memGradeDto.status" id="status" cssStyle="width:200px;"
			        list="#request.statusValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
				</td>
		    </tr>
		</table>
		</s:if>
	 <div class="formTableBottom">
	 	<s:if test="method!='checkUI'">
	 	<s:if test="#session.user_session.userLevel==0">
			<my:permission key='sy-1303-02' value='会员等级添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		</s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('memGrade/memGrade!list')"/>
     </div>
	 </s:form>
</body>
</html>