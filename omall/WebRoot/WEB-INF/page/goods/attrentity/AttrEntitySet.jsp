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

		<title>商品值属性值管理</title>
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
		<script src="js/common.js"></script>
		<script src="js/pubValiPattern.js"></script>
		<script src="js/pubValidate.js"></script>
		<script src="js/base/member.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script type="text/javascript">
		var attrEnNameFlag=false;
		/**验证名称*/
	function 	testAttrEnName(){
					var attrEnName=$("#attrEnName").val();
					if(attrEnName.length==0){
						$("#message").html("请输入属性值名称！");
						$("#message").show();
						attrEnNameFlag=false;
						}else{
							$("#message").hide();
							attrEnNameFlag=true;
						}
		}
	/**提交时验证的方法*/
	function check(){
		testAttrEnName();
		if(attrEnNameFlag){
			return true;
			}else{
			alert("页面信息填写有误！");
			return false;
		}
	}
	</script>
	</head>
	<body>
		<div class="Position">
			当前位置是：基本设置 &gt;&gt; 商品管理 &gt;&gt; 商品属性值管理
		</div>
		<s:form action="base/attrEntity" method="post"
			onsubmit="document.getElementById('submitInput').disabled = true;return true;"
			theme="simple">
			<s:hidden name="method" id="method" />
			<s:hidden name="attrEntityDTO.attrId" id="attrId" />
			<s:hidden name="attrEntityDTO.attrEnId" id="attrEnId" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">
				<tr>
					<th align="right">
						<span class="Color5">* </span>属性值名称：
					</th>
					<td>
						<s:textfield name="attrEntityDTO.attrEnName" id="attrEnName"
							maxlength="20" cssClass="formInput" theme="simple"
							onblur="testAttrEnName();" />
						<label id="message" class="errorMsg" style="display: none;"></label>
					</td>
				</tr>
			</table>
			<div class="formTableBottom">
					<input id="submitInput" type="submit" class="formButton"
						value="保 存" onclick="return check();" />
					<input type="button" class="formButton" value="返 回"
						onclick="go('base/attrEntity!list')" />
			</div>
		</s:form>
	</body>
</html>