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
	<title>全站公告管理</title>
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
	
	$(document).ready(function (){
	      
	       var method = document.getElementById("method"); 
	        if(method.value=='checkDetail'){
	             setInputDisabled();
	            $("#AnnounContent").attr("readonly","true");
	              $("#AnnounContent").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
	            }
	        })
	var validateAnnounTitle;
	var validateAuthor;
	var validateAnnounContent;
	var flagTitle;
	//检查公告标题是否重复
		var checkAnnounTitle=function (){
	
			var method = $("#method").val();
			var announTitle=$("#AnnounTitle").val();
			var announId = $("#AnnounId").val();
			if(announTitle.length!=0){
			$("#announTitleValue").html("");
				/**如果是添加的方法*/
				if(method=="addSave"){
					var params = {
							"method":method,
							"annountDTO.AnnounTitle":announTitle
					    }
				}else{
					var params = {
							"method":method,
							"annountDTO.AnnounTitle":announTitle,
					    	"annountDTO.AnnounId":announId
					    }    	
				}
			/**请求的action*/
		
			 var actionUrl = "marketing/annount!checkAnnounTitle";
			 $.ajax({  
				 	async:false,
			        url:actionUrl,   
			        data:params,   
			        dataType:"json",
			        cache:false, 
			        async : false,
			        type:"POST",
			        error:function(textStatus, errorThrown) {   
			    		alert("系统ajax交互错误!");  
			        },
			        success:function(data, textStatus) {
			        	if(data.flag==false){
			        		$("#announTitleValue").html("");
			        		$("#announTitleValue").hide();
			        		flagTitle=true;
			        	
					    }else{
					     
					       $("#announTitleValue").html("");
		    	           $("#announTitleValue").html("标题名重复");
		    	           $("#announTitleValue").show();
					       flagTitle=false;
					       
						 }
			        }
			    });
				}
			}
			//对标题进行测试
	var validateAnnounTitle =function(){
	    	var announTitle = $.trim($("#AnnounTitle").val());
			if(announTitle.length ==0 ||announTitle==null){
			  $("#announTitleValue").html("");
		    	$("#announTitleValue").html("请填写标题名");
		    	  $("#announTitleValue").show();
		    	return false;
			}else{
			   checkAnnounTitle();
			 
			   if(flagTitle==false)
			   {
			    
			      return false;
			   }
			}
			return true;
	}
	//对发布人进行测试
	var validateAuthor =function(){
	      $("#authorValue").html("");
			var author = $.trim($("#author").val());
			if(author.length ==0 ||author==null){
			  $("#authorValue").html("");
			  $("#authorValue").html("请填写发布人");
			  $("#authorValue").show();
			  return false;
			}
			 $("#authorValue").html("");
			 $("#authorValue").hide();
			return true;
	}
	//对公告内容进行测试
	var validateAnnounContent=function(){
		    var textareaLength=$("#AnnounContent").val().length; 
		    $("#AnnounContentValue").html(""); 
			var announContent = $.trim($("#AnnounContent").val()); 
			if(textareaLength>=100){ 
			
			   $("#AnnounContentValue").html("公告内容过长");
			   $("#AnnounContentValue").show();
				return false;
			} 
	       
			if(announContent.length ==0 ||announContent==null){
			  $("#AnnounContentValue").html("");
			  $("#AnnounContentValue").html("请填写公告内容");
			   $("#AnnounContentValue").show();
			  return  false;
			}
			 $("#AnnounContentValue").html("");
			 $("#AnnounContentValue").hide();
			return true;
	}
	//测试函数
	function check(){
	     validateAnnounTitle();
	     validateAuthor();
	     validateAnnounContent();
		if(!(validateAnnounTitle()&&validateAuthor()&&validateAnnounContent()))
		{
		 alert("页面信息有误");
		 return validateAnnounTitle()&&validateAuthor()&&validateAnnounContent();
		}
		return	validateAnnounTitle()&&validateAuthor()&&validateAnnounContent();
		
		}
		
	
   </script> 
</head>
<body >
	<div class="Position">
		当前位置是：HOME &gt;&gt; 商城前台 &gt;&gt; 全站公告管理
	</div>
	<s:form action="marketing/annount" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
    <s:hidden name="method" id="method"/>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>公告标题：</th>
		        <td width="30%">
							<s:textfield name="annountDTO.AnnounTitle" id="AnnounTitle"    maxlength="12"
							cssClass="formInput" theme="simple" onblur="validateAnnounTitle();" />
							
							<label id="announTitleValue" style="display: none;" class="errorMsg"></label>
		        </td>
		        <th align="right" width="20%"><span class="Color5">* </span>发布人：</th>
			    <td width="30%">
							<s:textfield name="annountDTO.author" id="author"   maxlength="12"
							cssClass="formInput" theme="simple" onblur="validateAuthor();" />
						
							<label id="authorValue" style="display: none;" class="errorMsg"></label>
							
		        </td>
			</tr>	
		 	<tr >
		 	    <th align="right" width="20%"><span class="Color5">* </span>状态：</th>
				<td>
						<s:select name="annountDTO.status" id="status"
							list="#request.statusList"  listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />		
				</td>
				 <th align="right" width="20%"><span class="Color5">* </span>是否置顶：</th>
				<td>
						<s:select name="annountDTO.isTop" id="isTop"
							list="#request.isTop"  listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />		
				</td>
				
		  	</tr>
		  	
		  	<tr>
			    <th align="right" width="20%"><span class="Color5">* </span>公告内容：</th>
				<td width="30%" style="border-right:0px ">
						<s:textarea name="annountDTO.AnnounContent" id="AnnounContent" rows="4" cols="30"  onblur="validateAnnounContent();"/>
					
						<label id="AnnounContentValue" style="display: none;" class="errorMsg"></label>
						<s:hidden name="annountDTO.AnnounId" id="AnnounId" />
						<s:hidden name="annountDTO.CreateTime" id="CreateTime" />
				</td>
				
			</tr>
		</table>
	
		 <div class="formTableBottom">
		 	<s:if test="method=='addSave'">
				<my:permission key='sy-7101-02' value='公告信息添加'>
			 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
			 	</my:permission>
			</s:if>
			<s:if test="method=='editSave'">
				<my:permission key='sy-7101-03' value='公告信息修改'>
			 		<input id="submitInput" type="submit" class="formButton" onclick="return check();" value="保 存" />
			 	</my:permission>
			</s:if>
			<input type="button" class="formButton" value="返 回" onclick="go('marketing/annount!list')"/>
		 </div>
	 </s:form>
</body>
</html>