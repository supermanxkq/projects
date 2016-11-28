<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html lang="en-US">

	<head>
		<meta charset="UTF-8">
		<title>服务清单</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
			type="image/x-icon" />
		<link href="../css/style.css" rel="stylesheet" type="text/css" />
		<script src="../js/jquery-1.4.2.min.js"></script>
		<script src="../js/jquery/jquery.ui.core.js"></script>
		<script src="../js/pubValiPattern.js"></script>
		<script src="../js/pubValidate.js"></script>
		<script type="text/javascript">
		/**标志*/
		var checkNameFlag=false;
		var getContentValueFlag=false;

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		/**设置为disabled*/
		$(document).ready(function (){
		       var method = document.getElementById("method"); 
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
		if(checkNameFlag&&getContentValueFlag){
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
				<s:else>
					<my:permission key='sy-6801-03' value='消费信息修改'>
						<input id="submitInput" type="submit" class="formButton"
							value="修改" onclick="return check();" />
					</my:permission>
				</s:else>
				<input type="button" class="formButton" value="返 回"
					onclick="go('stock/servicelist!list')" />
			</div>
		</s:form>
		<!-- 配置文件 -->
		<script type="text/javascript" src="../mailEdit/ueditor.config.js"></script>
		<!-- 编辑器源码文件 -->
		<script type="text/javascript" src="../mailEdit/ueditor.all.js"></script>
		<script type="text/javascript" src="../mailEdit/ueditor.all.min.js"></script>
		<!-- 实例化编辑器 -->
		<script type="text/javascript" charset="UTF-8">
	var ue = UE.getEditor('container');
	 function setDisabled() {
		 var ue = UE.getEditor('container');
		 ue.setDisabled('fullscreen');
	        disableBtn("enable");
	    }
  //  var ue = UE.getContent();
  //对编辑器的操作最好在编辑器ready之后再做
  ue.ready(function() {
      //设置编辑器的内容
      ue.setContent($("#descr").val());
      //获取纯文本内容，返回: hello
      if($("#method").val()=='checkDetail'){
    	  setDisabled();
          }
  });
</script>
	</body>
</html>