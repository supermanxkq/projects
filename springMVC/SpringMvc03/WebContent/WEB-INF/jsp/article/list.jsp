<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<th colspan="2">
			
			<img alt="文章列表" src="${pageContext.request.contextPath}/resources/article_list.jpg">
		</th>
	</tr>
	<tr>
		<td>1</td>
		<td>
			<a href="${pageContext.request.contextPath}/article/details/1" target="_blank">文章一</a>
		</td>
	</tr>
	<tr>
		<td>2</td>
		<td>
			<a href="${pageContext.request.contextPath}/article/details/2" target="_blank">文章二</a>
		</td>
	</tr>
</table>
</body>
</html>