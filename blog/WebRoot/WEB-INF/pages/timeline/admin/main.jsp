<%@ page contentType="text/html; charset=gbk"%>
<%@include file="../common/admin_head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>��̨����ϵͳ</title>
<link href="<%=basepath%>/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function notice(){
	if(confirm("ϵͳ��⵽����һ��������δ����,�Ƿ�����ϴβ�����")){
		window.location.href = "getNews.action";
	}else{
		//����������Ŵӻ��������
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
