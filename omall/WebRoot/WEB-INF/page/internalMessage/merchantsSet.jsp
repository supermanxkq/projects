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
		<title>查询所有的商户名称</title>
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
		<script src="js/pubValidate.js"></script>
		<script src="js/pubValiPattern.js"></script>
		<script src="js/jquery/jquery.ui.tabs.js"></script>
		<script src="js/base/member.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script type="text/javascript">
	/**异步获取所有的商户名称*/
	function findMerchants() {
		/**json数据传输*/
		var params = {};
		var actionUrl = "internalMessage/internalMessage!findMerchants";
		$
				.ajax( {
					url : actionUrl,
					data : params,
					dataType : "json",
					cache : false,
					type : "POST",
					error : function(textStatus, errorThrown) {
					},
					success : function(data, textStatus) {
						var option = "<select multiple='multiple' style='width:200px;'>";
						$("#div1").empty();
						for ( var i = 0; i < data.obj.length; i++) {
							option = option + "<option value='"
									+ data.obj[i].key + "'>"
									+ data.obj[i].value + "</option>";
						}
						option = option + "</select>";
						$("#div1").html(option);
						/**设置默认值*/
<%--						if ($("#cityId").val() != '') {--%>
<%--							$("#city").val($("#cityId").val());--%>
<%--						} else {--%>
<%--							$("#city option[index='0']").remove();--%>
<%--						}--%>
					}
				});
	}
</script>
	</head>
	<body>
	<div class="List_tit">
			请选择要发送的商户
		</div>
	<div id="div1"></div>
	</body>
</html>