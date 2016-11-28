<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>付款成功！</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
   付款成功！系统已经给卖家发送邮件，请等待卖家发货。
  	如果卖家没有发货，请及时联系卖家！
   <span id="tiao" class="Color1 F16 B">10</span>
				<a href="javascript:countDown"></a> 秒后页面将自动跳转 
   <script language="javascript" >
					function countDown(secs){ 
						tiao.innerText=secs;
						if(--secs>0){ 
							setTimeout("countDown("+secs+")",1000);
						}else{ 
							window.location.href='article/article!list';
						}
					}
					countDown(10);
				</script>	
  </body>
</html>
