<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<base href="<%=basePath%>" />
		<title>短信服务器配置</title>
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
		<script src="js/jquery/jquery.ui.tabs.js"></script>
		<script src="js/common.js"></script>
		<script src="js/pubValidate.js"></script>
		<script src="js/pubValiPattern.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script type="text/javascript">
	$(function() {
		var $tabs = $("#tabs").tabs();
		$('#tabs-2').click(function() { // 绑定单击事件
					$tabs.tabs('select', 1);
					return true;	
				});
	});

    /**验证全部*/
     function check(){
    	 checkServURLValue();
    	 checkAccount();
    	 checkAccountValue();
    	 checkPassword();
    	 checkPasswordValue();
    	 checkServURLValue1();
    	 checkAccount1();
    	 checkAccountValue1();
    	 checkPassword1();
    	 checkPasswordValue1();
    	 checkMobile();
    	 checkMobile1();
    	 checkContent();
    	 checkContent1();
    	 if(servUrlValueFlag&&AccountFlag&&AccountValueFlag&&passwordErrorFlag&&passwordValueErrorFlag&&servUrlValueFlag1&&AccountFlag1&&AccountValueFlag1&&passwordErrorFlag1&&passwordValueErrorFlag1&&mobileFlag1&&mobileFlag&&contentFlag&&content1Flag){
						return true;
          	}else{
              			alert("*号为必填项！请确认普通短信服务器和验证码服务器参数配置完成！");
						return false;
              	}
     }

     
</script>
	</head>
	<body>
		<div class="Position">
			当前位置是：HOME &gt;&gt;短信管理 &gt;&gt; 短信服务器配置
		</div>
		<div class="search" id="tabs">
			<ul>

				<s:if test="method=='updateData'">
					<!-- 根据通道类型进行显示 -->
					<s:if test="messServParamConfigDTO.paramType==1">
						<li>
							<a href="#tabs-1">普通短信服务器参数配置</a>
						</li>
					</s:if>
					<s:else>
						<li>
							<a href="#tabs-2">验证码短信服务器参数配置</a>
						</li>
					</s:else>
				</s:if>
				<s:else>
					<li>
						<a href="#tabs-1">普通短信服务器参数配置</a>
					</li>
					<li>
						<a href="#tabs-2">验证码短信服务器参数配置</a>
					</li>
				</s:else>
			</ul>
			<s:form action="message/messServParamConfig" method="post"
				onsubmit="document.getElementById('submitInput').disabled = true;return true;"
				theme="simple">
				<s:hidden name="method" id="method" />
				<!-- 根据通道类型进行显示 -->
				<div class="search" id="tabs-1">
					<jsp:include page="membersSet.jsp"></jsp:include>
				</div>
				<div class="search" id="tabs-2">
					<jsp:include page="merchantsSet.jsp"></jsp:include>
				</div>
				<div class="formTableBottom">
					<s:if test="method=='addSave'">
						<my:permission key='sy-1605-01' value='短信服务器配置信息添加'>
							<input id="submitInput" type="submit" class="formButton"
								value="保 存" onclick="return check();" />
						</my:permission>
					</s:if>
					<s:elseif test="messServParamConfigDTO.paramType1==2">
						<my:permission key='sy-1605-03' value='短信服务器配置信息修改'>
							<input id="submitInput" type="submit" class="formButton"
								value="修改" onclick="return checkVerification();" />
						</my:permission>
					</s:elseif>
					<s:elseif test="messServParamConfigDTO.paramType==1">
						<my:permission key='sy-1605-03' value='短信服务器配置信息修改'>
							<input id="submitInput" type="submit" class="formButton"
								value="修改" onclick="return checkShortMess();" />
						</my:permission>
					</s:elseif>
					<input type="button" class="formButton" value="返 回"
						onclick="go('message/messServParamConfig!list')" />
				</div>
			</s:form>
			<font color="#ff9900"> 系统提示： <br> <br>
				请先选择合作的短信供应商，获取短信供应商相关参数，再执行短信服务器参数配置操作。 <br> </font>
		</div>
	</body>
</html>