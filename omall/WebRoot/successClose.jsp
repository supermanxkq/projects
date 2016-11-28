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
		<title>success</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		
	</head>
	<body>

		<dl class="H_return">
			<dt></dt>
			<dd>
				<p class="Color1 F16 B">
					<%=request.getAttribute("result")%>
				</p>
				
				<script language="javascript" >
					function countDown(secs){ 
					
						if(--secs>0){ 
							
							setTimeout("countDown("+secs+")",1000);
						}else{ 
							window.close();
						}
					}
					//alert(window.parent.dialogArguments);
					 //window.parent.dialogArguments.updateBack();
					countDown(2);
				</script>	
			</dd>
		</dl>

	</body>
</html>
