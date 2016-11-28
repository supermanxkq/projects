<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'updateStuinfo.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<center>
			<h2>
				修改
			</h2>
			<hr/>
			<form action="updateStuInfoById.action" method="post">
				<table border="1" cellspacing="0px" bordercolor="green">
					<tr>
						<td>
							编号：
						</td>
						<td>
							<input type="text" name="stuInfo.stuId"
								value="<s:property value="stuInfo.stuId"/>" />
						</td>
						<td>
							名称：
						</td>
						<td>
							<input type="text" name="stuInfo.stuName"
								value="<s:property value="stuInfo.stuName"/>" />
						</td>
					</tr>
					<tr>
						<td>
							邮箱：
						</td>
						<td>
							<input type="text" name="stuInfo.email"
								value="<s:property value="stuInfo.email"/>" />
						</td>
						<td>
							手机：
						</td>
						<td>
							<input type="text" name="stuInfo.mobile"
								value="<s:property value="stuInfo.mobile"/>" />
						</td>
					</tr>
					<tr>
						<td>
							性别：
						</td>
						<td>
							<input type="text" name="stuInfo.sex"
								value="<s:property value="stuInfo.sex"/>" />
						</td>
						<td>
							地址：
						</td>
						<td>
							<input type="text" name="stuInfo.address"
								value="<s:property value="stuInfo.address"/>" />
						</td>
					</tr>
					<tr>
						<td>
							年龄：
						</td>
						<td>
							<input type="text" name="stuInfo.age"
								value="<s:property value="stuInfo.age"/>" />
						</td>
						<td>
							<input type="text" name="stuInfo.studentDetail.studentDetailId"
								value="<s:property value="stuInfo.studentDetail.studentDetailId"/>" />
						</td>
						<td>
							<input type="submit" value="更新" />
						</td>
					</tr>
				</table>
			</form>
		</center>
	</body>
</html>
