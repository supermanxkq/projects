<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8">
		<title>服务清单</title>
		
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.4.2.min.js"></script>
		<script src="js/jquery/jquery.ui.core.js"></script>
		<script src="js/pubValiPattern.js"></script>
		<script src="js/pubValidate.js"></script>
		<link href="js/jquery/css/jquery.ui.all.css" rel="stylesheet" type="text/css" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
			type="image/x-icon" />
		<script src="js/jquery/jquery.ui.widget.js"></script>
		<script src="js/jquery/jquery.ui.mouse.js"></script>
		<script src="js/jquery/jquery.ui.button.js"></script>
		<script src="js/jquery/jquery.ui.draggable.js"></script>
		<script src="js/jquery/jquery.ui.position.js"></script>
		<script src="js/jquery/jquery.ui.autocomplete.js"></script>
		<script src="js/jquery/jquery.ui.resizable.js"></script>
		<script src="js/jquery/jquery.ui.dialog.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script src="js/common.js"></script>
       	<script type="text/javascript">
		/**标志*/
		var checkNameFlag=false;
		var getContentValueFlag=false;
		var flagTitle=true;
         
    //例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		/**设置为disabled*/
		$(document).ready(function (){
		       var method = document.getElementById("method"); 
		       //alert(method.value);
		        if(method.value=='checkDetail'){
			          setInputDisabled();
		              $("#submitInput").attr("hidden","hidden");
		             
		            }
		        });		
	/**验证标题*/
	function checkName(){
		var name=$.trim($("#name").val());
		if(name.length==0){
				$("#nameMsg").html("请输入标题！");
				$("#nameMsg").show();
				checkNameFlag=false;
			}else{
			$("#nameMsg").hide();
			checkNameFlag=true;
			 checkAnnounTitle();
		}
  }
	//校验商户评分模型名称是否重复
	var checkAnnounTitle=function (){
	  
		var method = $("#method").val();
		var name=$.trim($("#name").val());
		var serviceId = $("#servId").val();
		if(name.length!=0){
			$("#nameMsg").html("");
			/**如果是添加的方法*/
			if(method=="addSave"){
				var params = {
						"method":method,
						"serviceListDTO.name":name
				    }
			}else{
				var params = {
						"method":method,
						"serviceListDTO.name":name,
				    	"serviceListDTO.servId":serviceId
				    }    	
			}
		/**请求的action*/
		 var actionUrl = "stock/servicelist!checkName";
		 
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
		        		$("#nameMsg").html("");
		        		$("#nameMsg").hide();
		        		flagTitle=true;
		        	
				    }else{
				       $("#nameMsg").html("");
	    	           $("#nameMsg").html("标题名称不能重复！");
	    	           $("#nameMsg").show();
				       flagTitle=false;
					 }
		        }
		    });
			}
		}


	/**验证内容，提交赋值*/
  function getContentValue(){
	var editorValue=$("#container").val();
	  if(editorValue.length==0){
				alert("请输入内容详情！");
				getContentValueFlag=false;
		  }else{
         $("#descr").val(editorValue);
         getContentValueFlag=true;
		}
	  }

	
	
	/**提交时验证的方法*/
	function check(){
		checkName();
		getContentValue();
		if(flagTitle&checkNameFlag&&getContentValueFlag){
			return true;
			}else{
			return false;
		}
	}


	/**设置编辑器状态**/
    function setEditorStatus(){
    	 var arr = [];
    	 var method = document.getElementById("method"); 
	        if(method.value=='checkDetail'){
	        	 $("#container").val($("#descr").val());
	        	    
	            }
       }
	</script>  
         
	</head>
	<body onload="setEditorStatus();">
		<div class="Position">
			当前位置是：HOME &gt;&gt;服务中心信息管理 &gt;&gt;服务清单
		</div>
	
		<s:form action="stock/servicelist" method="post"
			onsubmit="document.getElementById('submitInput').disabled = true;return true;"
			theme="simple">
			<s:hidden name="method" id="method" />
			<s:hidden name="serviceListDTO.servId" id="servId" />
			<s:hidden name="serviceListDTO.status" id="status" />
			<s:hidden name="serviceListDTO.addTime" id="addTime" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">
				<tr>
					<td align="right">
						<span class="Color5">* </span>标题：
					</td>
					<td>
						<s:textfield name="serviceListDTO.name" id="name" maxlength="50"
							cssClass="formInput" theme="simple" onblur="checkName();" />
						<label id="nameMsg" class="errorMsg" style="display: none;"></label>
					</td>
					<th align="right">
						<span class="Color5">* </span>服务类型：
					</th>
					<td>
						<s:select id="servType" name="serviceListDTO.servType"
							list="#request.serviceType" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />
					</td>
				</tr>
				<tr>
					<th align="right" width="10%">
						内容详情：
					</th>
					<td width="30%" colspan="3">
						<!-- 加载编辑器的容器 -->
						<textarea id="container" name="content" type="text/plain"
							style="width: 800px;">
			           </textarea>
						<s:hidden name="serviceListDTO.descr" id="descr"
							cssClass="formInput" theme="simple" />
					</td>
				</tr>
			</table>
			<div class="formTableBottom">
				<s:if test="method=='addSave'">
					<my:permission key='sy-6801-01' value='消费信息添加'>
						<input id="submitInput" type="submit" class="formButton"
							value="保 存" onclick="return check();" />
					</my:permission>
				</s:if>
				<s:if test="method=='updateData'">
					<my:permission key='sy-6801-03' value='消费信息修改'>
						<input id="submitInput" type="submit" class="formButton"
							value="保存" onclick="return check();" />
					</my:permission>
				</s:if>
				<input type="button" class="formButton" value="返 回"
					onclick="go('stock/servicelist!list')" />
					
			</div>
		</s:form>
	</body>
			<!-- 配置文件 -->
		<script type="text/javascript" src="mailEdit/ueditor.config.js"></script>
		<!-- 编辑器源码文件 -->
		<script type="text/javascript" src="mailEdit/ueditor.all.js"></script>
		<script type="text/javascript" src="mailEdit/ueditor.all.min.js"></script>
		<!-- 实例化编辑器 -->
		<script type="text/javascript" charset="UTF-8">
	var ue = UE.getEditor('container');
  //  var ue = UE.getContent();
  //对编辑器的操作最好在编辑器ready之后再做
  ue.ready(function() {
	  //设置编辑器的内容
      ue.setContent($("#descr").val());
      //获取纯文本内容，返回: hello
      if($("#method").val()=='checkDetail'){
    	  ue.setDisabled();
          }
  });
  
</script>
</html>