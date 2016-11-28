<%@ page contentType="text/html; charset=gbk"%>
<%@include file="../common/admin_head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>后台管理系统</title>
<link href="<%=basepath%>/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function notice(){
	if(confirm("系统检测到您有一条新闻尚未保存,是否继续上次操作？")){
		window.location.href = "getNews.action";
	}else{
		//将保存的新闻从缓存中清除
		window.location.href = "clearNews.action";
	}
}
</script>
</head>

<body>
<cc:if test="${isNoticeUser == 'yes'}">
<script>notice()</script>
</cc:if>
<div class="main_logo">
	<img src="<%=basepath%>/images/main_logo.jpg"/>
</div>
</body>
</html>
