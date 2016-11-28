<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back/share/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>徐半仙儿</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
   <!-- BEGIN FOOTER -->
   <div id="footer">
       &copy;2015-03-17 徐半仙儿 蒙ICP备13002765号-2
       <p>uv:<font color="red"><s:property value="#session.uv"/>次</font>
       &nbsp;&nbsp;本站所有资源大部分来自个人原创，部分文章有所转载，如有侵犯权益，请联系我们删除！网站已运行<span id="days"></span></p>
      <div class="span pull-right">
         <span class="go-top"><i class="icon-arrow-up"></i></span>
      </div>
  
   </div>
   <!-- END FOOTER -->
    <SCRIPT language=javascript>
BirthDay=new Date("3 17,2015");
today=new Date();
timeold=(today.getTime()-BirthDay.getTime());
sectimeold=timeold/1000
secondsold=Math.floor(sectimeold);
msPerDay=24*60*60*1000
e_daysold=timeold/msPerDay
daysold=Math.floor(e_daysold);
$("#days").html("<font color=red>"+daysold+"</font>天 !");
//-->
</SCRIPT>
  </body>
</html>
