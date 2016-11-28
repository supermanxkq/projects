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
		<title>站内信</title>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
		<script src="js/jquery/jquery.ui.tabs.js"></script>
		<script src="js/common.js"></script>
		<script src="js/pubValidate.js"></script>
		<script src="js/pubValiPattern.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script type="text/javascript">

      /**标题标志*/
	  var  titleFlag=false;
	  /**内容标志*/
	  var   contentFlag=false;
		/**设置所有输入控件是Disabled,并且隐藏发送按钮*/
		$(document).ready(function (){
		       var method = document.getElementById("method"); 
		        if(method.value=='checkUI'){
		        	  $("#submitInput").hide();
		              $("#content").attr("readonly","true");
		              $("#receiverFlag").attr("disabled","disabled");
		              $("#title").attr("disabled","disabled");
		              $("#sender").attr("disabled","disabled");
		              $("#content").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
		            }
		        });		

	/**验证标题信息*/
	function checkTitle(){
		var  titleValue=$("#title").val();
		if(titleValue.length==0){
			$("#titleMsg").html("请输入标题信息！");
			$("#titleMsg").show();
			titleFlag=false;
		}else{
			$("#titleMsg").hide();
			titleFlag=true;
		}
}
/**验证内容信息*/
	function checkContent(){
		var  contentValue=$("#content").val();
		if(contentValue.length==0){
			$("#contentMsg").html("请输入要发送的内容！");
			$("#contentMsg").show();
			contentFlag=false;
		}else{

			if(contentValue.length>256){
				$("#contentMsg").html("最多输入255字！");
				$("#contentMsg").show();
				contentFlag=false;
				}else{
					$("#contentMsg").hide();
					contentFlag=true;
				}
		}
}

/**验证全部信息*/
    function  check(){
			checkTitle();
			checkContent();
			if(titleFlag&&contentFlag){
				return true;
			}else{
				alert("页面信息添加错误！");
				return false;
			}
    }

</script>

	</head>
	<body>
		<div class="Position">
			当前位置是：HOME &gt;&gt;站内信 &gt;&gt;站内信
		</div>
		<s:form action="internalMessage/internalMessage" method="post"
			theme="simple">
			<s:hidden name="method" id="method" />
			<s:hidden name="receiversDTO.receiversId" id="receiversId" />
			<s:hidden name="internalMessageDTO.internalMessageId"
				id="internalMessageDTOId" />
			<table width="100%" border="1" cellpadding="0" cellspacing="0"
				class="formTable">
				<tr>
					<th align="right">
						<span class="Color5">* </span>收件人：
					</th>
					<td width="50%">
						<s:select id="receiverFlag" name="InternalMessageDTO.receiverFlag"
							list="#request.receiversFlag" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />
					</td>
					<s:if test="method=='addSave'">
					</s:if>
					<s:else>
						<th>
							发送人
						</th>
						<td>
							<s:textfield name="internalMessageDTO.sender" id="sender"
								maxlength="50" cssClass="formInput" theme="simple" />
						</td>
					</s:else>
				</tr>
				<tr>
					<th align="right">
						<span class="Color5">* </span>标题：
					</th>
					<td>
						<s:textfield name="internalMessageDTO.title" id="title"
							maxlength="15" cssClass="formInput" theme="simple"
							onblur="checkTitle();" />
						<label id="titleMsg" class="errorMsg" style="display: none;"></label>
					</td>
					<s:if test="method=='checkUI'">
						<th>
							发送时间：
						</th>
						<td>
							<s:date name="internalMessageDTO.sendTime"
								format="yyyy-MM-dd HH:mm:ss" />
						</td>
					</s:if>
				</tr>
				<tr>
					<th align="right" width="10%">
						内容：
					</th>
					<td width="30%" colspan="3">
						<s:textarea name="internalMessageDTO.content" rows="5" cols="70"
							id="content" onblur="checkContent();"></s:textarea>
						<label id="contentMsg" class="errorMsg" style="display: none;"></label>
					</td>
				</tr>
			</table>
			<div class="formTableBottom">
				<input id="submitInput" type="submit" class="formButton" value="发送"
					onclick="return check();" />
				<input type="button" class="formButton" value="返 回"
					onclick="go('internalMessage/internalMessage!list')" />
			</div>
		</s:form>
	</body>
</html>