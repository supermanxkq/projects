<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>正在处理中,请稍候...</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			 function quit() {
				if(confirm("确定要退出系统吗？")){
					parent.window.location.href="<%=basePath%>control/index!exit";
				}
			}
		</script>
	</head>
	<body>

		<dl class="H_return">
			<dt></dt>
			<dd>
				<p class="Color1 F16 B">
					请求可能已提交，请认真核对信息...
				</p>
				<p class="Color3 F12 B">
					注意：刷新页面或重复提交，可能导致重复提交！
				</p>	
			</dd>
		</dl>

	</body>
</html>
