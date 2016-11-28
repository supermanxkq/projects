<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加成功！</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
  添加成功 ！
   <span id="tiao" class="Color1 F16 B">3</span>
				<a href="javascript:countDown"></a> 秒后页面将自动跳转 
   <script language="javascript" >
					function countDown(secs){ 
						tiao.innerText=secs;
						if(--secs>0){ 
							setTimeout("countDown("+secs+")",1000);
						}else{ 
							window.location.href='shop/shop!toShopPage?columnCode=6';
						}
					}
					countDown(3);
				</script>	
  </body>
</html>
