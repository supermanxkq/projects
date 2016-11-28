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
	<script src="js/jquery/jquery.ui.tabs.js"></script> 
	<script src="js/jquery/jquery.ui.button.js"></script>
	<script src="js/jquery/jquery.ui.draggable.js"></script>
	<script src="js/jquery/jquery.ui.position.js"></script>
	<script src="js/jquery/jquery.ui.resizable.js"></script>
	<script src="js/jquery/jquery.ui.dialog.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValidate.js"></script>
   <script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">	
	$(document).ready(function (){
	       var method = document.getElementById("method"); 
	        if(method.value=='checkUI'){
	             setInputDisabled();
	            }
            
	        });
	
	

		$(function() {
			var $tabs = $("#tabs").tabs();	
			$('#tabs-2').click(function() { // 绑定单击事件
			    $tabs.tabs('select', 1);
			    return true;	
			});
			if($("#level").val()!=2){
				$('#tabs-3').click(function() {
			    	$tabs.tabs('select', 2);
			    	return true;	
				});	
			}	
		});

		function getEle(id){
                  return document.getElementById(id);
			}

		

		//控制保存和返回按钮  0隐藏  1为显示
		function hideButton(type){
			if(type==0){
				$("#back,#submitInput").hide(); 
			}else{
				$("#back,#submitInput").show();
			}
		}

	</script> 
</head>
<body>
	<input type="hidden" id="level" value="${sessionScope.user_session.userLevel }" />
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 商户管理${storeInfoDTO.nickName }
	</div>
	<div class="search" id="tabs">
	     <ul>
		   <li><a href="#tabs-1" onclick="hideButton(1);">商户基本信息</a></li>
		   <li><a href="#tabs-2" onclick="hideButton(1);">商户业务参数</a></li>
		   <s:if test="#session.user_session.userLevel==2">
		  	 <li><a href="#tabs-3" onclick="hideButton(0);">店铺管理</a></li>
		   </s:if>
	     </ul>
	 <s:form action="base/merchants" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	 <s:hidden name="method" id="method"></s:hidden>
	 <div class="search" id="tabs-1">
	    <jsp:include page="merBaseSet.jsp"></jsp:include>
	 </div>
	 <div class="search" id="tabs-2">
	   <jsp:include page="merBussSet.jsp"></jsp:include>
	 </div>
	
	<div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1201-02' value='商户添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:elseif test="method=='editSave'">
		    <my:permission key='sy-1201-03' value='商户修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:elseif>
		<s:else>
			
		</s:else>
		<input id="back" type="button" class="formButton" value="返 回" onclick="go('base/merchants!list')"/>
	 </div>
	</s:form>
	<s:if test="#session.user_session.userLevel==2">
	 	<div class="search" id="tabs-3">
			 <jsp:include page="../storeManage/storeInfo/storeInfoInclude.jsp"></jsp:include>
	 	</div>
	</s:if>

		
</div>
</body> 
</html>