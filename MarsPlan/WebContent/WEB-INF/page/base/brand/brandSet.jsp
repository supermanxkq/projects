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
	<title>商品品牌管理</title>
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
	<script src="js/jquery/jquery.ui.tabs.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValidate.js"></script>
    <script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
	
		var brandNameFlag = true;
		var brandUrlFlag = true;
		var brandDescFlag = true;
		var brandLogoFlag = true;
		
		function check() {
			
			if(!($('#brandNameInput').val() && $.trim($('#brandNameInput').val()).length > 0)) {
				var type = ["isNull"];
	            var brandNameErrorMsg=["商品品牌名称不能为空!"];
				brandNameFlag = showErrorMsg($('#brandNameInput'),type,brandNameErrorMsg,"brandNameErrorMsg","brandNameErrorMsg");
				return false;
			}
			
			if(!(brandNameFlag && brandUrlFlag && brandDescFlag && brandLogoFlag)) {
				alert("信息填写有误，请按提示信息重新填写!");               
				return false;
			}
			
			return true;
		}
		
		function brandNameBlur(obj) {
			var type = ["isNull"];
            var brandNameErrorMsg=["商品品牌名称不能为空!"];
            brandNameFlag = showErrorMsg(obj,type,brandNameErrorMsg,"brandNameErrorMsg","brandNameErrorMsg");
            if($.trim($('#oldBrandName').val()) == $.trim($(obj).val())) {
            	return;
            }
            var brandName = $(obj).val();
            var url = "base/brand!checkSameName";
            var params = {
               "goodBrandsDTO.brandName":brandName
                   };
            if(brandNameFlag) {
	            $.ajax({
	                url : url,
	                data : params,
	                async:false,
	                dataType : "json",
	                type : "POST",
	                cache : false,
	                error:function(errText){
	                   alert("ajax加载数据异常!请联系管理员");
	                },
	                success:function(data){
	                     if(data.flag){
	                        brandNameFlag = true;
	                        $("#brandNameErrorMsg").hide();
	                     }else{
	                        brandNameFlag = false;
	                        pubErrorShow($("#brandNameErrorMsg"),"该商品品牌名称已存在!");
	                     }
	                }
	                
	            });
            }
		}
		
		function descContentBlur(obj) {
			if($.trim($(obj).val()).length > 255) {
				brandDescFlag = false;
	        	$('#brandDescErrorMsg').html("品牌描述不能超过255个字符！");
	        	$('#brandDescErrorMsg').addClass('errorMsg');
	        	$('#brandDescErrorMsg').removeAttr("style");
			} else {
				$('#brandDescErrorMsg').html('');
	        	$('#brandDescErrorMsg').attr('style','display: none;');
	        	$('#brandDescErrorMsg').removeClass('errorMsg');
	            brandDescFlag = true;
			}
		}
		
		function brandUrl(obj) {
			// 填写了Url才校验
			if($('#goodBrandsUrlId').val()) {
				var type = ["isUrl"];
	            var brandUrlErrorMsg=["商品品牌网址不符合规范!"];
	            brandUrlFlag = showErrorMsg(obj,type,brandUrlErrorMsg,"brandUrlErrorMsg","brandUrlErrorMsg");
	        } else {
	        	$('#brandUrlErrorMsg').html('');
	        	$('#brandUrlErrorMsg').attr('style','display: none;');
	        	brandUrlFlag = true;
	        }
		}
		
		function brandLogoChange() {
			if($.trim($('#goodBrandLog').val()) == "") {
				return;
			}
			var strRegex = "(.jpg|.png|.gif|.ps|.jpeg)$"; //用于验证图片扩展名的正则表达式
	        var re=new RegExp(strRegex);
	        if (re.test($('#goodBrandLog').val().toLowerCase())){
	        	$('#goodBrandLogErrorMsg').html('');
	        	$('#goodBrandLogErrorMsg').attr('style','display: none;');
	        	$('#goodBrandLogErrorMsg').removeClass('errorMsg');
	        	$('#see').hide();
	        	$('#clearPicButton').hide();
	        	brandLogoFlag = true;
	            return true;
	        } else {
	        	$('#goodBrandLogErrorMsg').html("文件类型不支持！");
	        	$('#goodBrandLogErrorMsg').addClass('errorMsg');
	        	$('#goodBrandLogErrorMsg').removeAttr("style");
	        	brandLogoFlag = false;
	        	return false;
	        }
		}
		
		function clearUploadFile() {
			if(confirm("确认要删除？")) {
				// 清空文件
				$('#goodBrandLog').attr("value","");
				// 修改显示文案
				$('#file_msg').html("设置删除");
				// 设置删除标记
				$('#cleanParam').val("1");
				$('#see').hide();
				$('#clearPicButton').hide();
				$('#goodBrandLogErrorMsg').html('');
	        	$('#goodBrandLogErrorMsg').attr('style','display: none;');
	        	$('#goodBrandLogErrorMsg').removeClass('errorMsg');
	        	
	        	brandLogoFlag = true;
	        }
		}
		
		$(function() {
			$('#clearPicButton').bind("click",clearUploadFile);
			var method = document.getElementById("method"); 
	        if(method.value=='checkUI'){
	             setInputDisabled();
	             $("#brandDescIds").attr("readonly","true");
                 $("#brandDescIds").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
	        }
		});
	</script>
</head>
<body>
    <div class="Position">
		当前位置是：HOME &gt;&gt; 常用操作 &gt;&gt; 商品品牌管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<s:form action="base/brand" method="post" theme="simple" enctype="multipart/form-data" onsubmit="document.getElementById('submitInput').disabled = true;return true;" >
		<s:hidden name="method" id="method"></s:hidden>
		<s:hidden name="goodBrandsDTO.brandId" ></s:hidden>
		<input type="hidden" id="cleanParam" name="goodBrandsDTO.deleteLogo"/>
		<input type="hidden" id="oldBrandName" value="<s:property value="goodBrandsDTO.brandName"/>" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		    <tr>
		      	<th align="right"><span class="Color5">* </span><strong>品牌名称：</strong></th>
		        <td><s:textfield name="goodBrandsDTO.brandName" id="brandNameInput" onblur="brandNameBlur(this)" maxlength="60" cssClass="formInput" /> <label id="brandNameErrorMsg" style="display: none;"></label></td>
		   	</tr>
		   	<tr>
		   		<th align="right"><strong>品牌网址：</strong></th>
		        <td><s:textfield name="goodBrandsDTO.brandUrl" maxlength="100" id="goodBrandsUrlId" onblur="brandUrl(this)" cssClass="formInput"/> <label id="brandUrlErrorMsg" style="display: none;"></label></td>
		   	</tr>
			<tr>
		      	<th align="right"><strong>品牌LOGO：</strong></th>
		        <td>
		        	<s:if test="method!='checkUI'">
		        		<s:file name="goodBrandsDTO.brandLogo" size="40" id="goodBrandLog" onchange="brandLogoChange()" /><label id="goodBrandLogErrorMsg" style="display: none;"></label>
		        	</s:if>
		        	<s:if test="method=='editSave' && goodBrandsDTO.brandLogoFileName!=null && goodBrandsDTO.brandLogoFileName!=''">
		        		<span id="see"><img src="<s:url value='%{goodBrandsDTO.brandLogoFileName}'/>" ></img></span>&nbsp;<input type="button" value="删除图标" id="clearPicButton"/>
		        	</s:if><br/>
		        	<s:if test="method=='editSave' && goodBrandsDTO.brandLogoFileName!=null && goodBrandsDTO.brandLogoFileName!=''">
		        		<font size="2" color="gray" id="file_msg">您已经上传过图片，再次上传时将覆盖原图片！</font>
		        	</s:if>
		        	<s:if test="method=='checkUI' && goodBrandsDTO.brandLogoFileName!=null && goodBrandsDTO.brandLogoFileName!=''">
		        		<img src="<s:url value='%{goodBrandsDTO.brandLogoFileName}'/>"/>
		        	</s:if>
		        </td>
		   	</tr>
			<tr>
		      	<th align="right"><strong>品牌描述：</strong></th>
		        <td><s:textarea name="goodBrandsDTO.brandDesc" id="brandDescIds"  cssClass="formTextarea" onblur="descContentBlur(this)"/> <label id="brandDescErrorMsg" style="display: none;"></label></td>
		      
		 	</tr>
		 	<tr>
		      	<th align="right"><span class="Color5">* </span><strong>是否显示：</strong></th>
		        <td><s:radio list="#request.showList" listKey="key" listValue="value" cssClass="formSelect" name="goodBrandsDTO.isShow"/> <label id="psPwdErrorMsg" style="display: none;"></label></td>
		        
		  	</tr>
	 	</table>
	 	<div class="formTableBottom">
	 		<s:if test="method=='addSave'">
	 			<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
	 		</s:if>
	 		<s:elseif test="method=='editSave'">
	 			<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
	 		</s:elseif>
	 		<input type="button" class="formButton" value="返 回" onclick="go('base/brand!list')"/>
	 	</div>
	 	</s:form>	 
	 </body> 
</html>