<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'showStuinfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<SCRIPT type="text/javascript" src="js/prototype1.5.1.js"></SCRIPT>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	//跳转到添加页面中
	function goFuc() {
		window.location.href = "addUI.action";
	}


	//第一种使用ajax添加数据的方法
	function addSave() {
		function onSuccess(request) {
			alert("添加成功！");
			var json=eval("("+request.responseText+")");
			 document.getElementById("result").options.length=1;
			for(var i=0;i<json.length;i++){
			    document.getElementById("result").options.add(new Option(json[i].stuName,json[i].stuId));
		    }
		}

		function onComplete(request) {
		}

		function onFailure(request) {
			alert("failure");
			//$("result").innerHTML = request.responseText;
		}
		var stuName=document.getElementById("stuName").value;
		var studentDetailId=document.getElementById("studentDetailId").value;
		var paras ={
				"stuInfo.stuName":stuName,
				"stuInfo.studentDetail.studentDetailId":studentDetailId
		};
		var ajax = new Ajax.Request(
		"<%=basePath%>/addSave.action", {
			asynchronous : false,
			method : 'post',
			parameters : paras,
			onSuccess : onSuccess,
			onComplete : onComplete,
			onFailure : onFailure
		});
	}

	//Ajax声明
	var httpRequest1;
	function createXMLHttpRequest1() {
		if (window.ActiveXObject) {
			httpRequest1 = new ActiveXObject("Microsoft.XMLHTTP");
		} else {
			httpRequest1 = new XMLHttpRequest();
		}
	}

	//第二种使用ajax操作数据的方法
	function getStuInAsAjax() {
		var url = "queryStuNamesAsAjax.action";
		//Ajax声明
		createXMLHttpRequest1();
		//设置返回结果的处理函数
		httpRequest1.onreadystatechange = stuInfoLoad;
		httpRequest1.open("POST", url, true);
		httpRequest1.setRequestHeader("Cache-Control", "no-cache");
		httpRequest1.send(null);
	}

	//加载学生信息
	function stuInfoLoad() {
		if (httpRequest1.readyState == 4) {
			if (httpRequest1.status == 200) {
				//获取返回的学生的信息
				var stuId = httpRequest1.responseXML
						.getElementsByTagName("stuId");
				var stuName = httpRequest1.responseXML
						.getElementsByTagName("stuName");
				var obj = document.getElementById("stuNameOptions");
				//清空下拉框的值
				obj.options.length = 1;
				//添加学生信息
				for (var i = 0; i < stuId.length; i++) {
					var option = new Option(stuName[i].firstChild.nodeValue,
							stuId[i].firstChild.nodeValue);
					obj.add(option);
				}
			}
		}
	}
</script>
</head>

<body>
	<center>
		<h1>
			Spring、iBatis、Struts2整合项目 2015年3月26日_By半仙儿<br /> 更多请访问：<a
				href="http://www.xukaiqiang.com">http://www.xukaiqiang.com</a>
		</h1>
		<hr />

		<form method="post" action="queryAll.action">
			爱好： <input type="text" name="stuInfo.studentDetail.hobby" /> <input
				type="submit" value="查询" />
		</form>
		<table border="1" cellspacing="0px" bordercolor="green" width="60%">
			<tr>
				<th>编号</th>
				<th>姓名</th>
				<th>年龄</th>
				<th>性别</th>
				<th>手机</th>
				<th>邮箱</th>
				<th>地址</th>
				<th>爱好</th>
				<th>操作</th>
			</tr>
			<s:iterator value="stuList" var="stuInfo">
				<tr>
					<td><s:property value="#stuInfo.stuId" /></td>
					<td><s:property value="#stuInfo.stuName" /></td>
					<td><s:property value="#stuInfo.age" /></td>
					<td><s:property value="#stuInfo.sex" /></td>
					<td><s:property value="#stuInfo.mobile" /></td>
					<td><s:property value="#stuInfo.email" /></td>
					<td><s:property value="#stuInfo.address" /></td>
					<td><s:property value="#stuInfo.studentDetail.hobby" /></td>
					<td><a
						href="editUI.action?stuInfo.stuId=<s:property value="#stuInfo.stuId"/>">修改</a>
						<a
						href="deleteStuInfoById.action?stuInfo.stuId=<s:property value="#stuInfo.stuId"/>">删除</a>
					</td>
				</tr>
			</s:iterator>
		</table>
		<input type="button" onclick=goFuc();;; value="添加" />
		<hr />
		<p>prototype.js的例子,添加数据用ajax,并进行查询</p>
		学生姓名： <input type="text" name="stuInfo.stuName" id="stuName" /> 学生爱好：<input
			type="text" name="stuInfo.studentDetail.studentDetailId"
			id="studentDetailId" value="1" /> <input type="button"
			onclick="addSave();" value="提交" /> <br /> <span>添加成功后查看结果：</span> <select
			id="result">
			<option value="">--请选择--</option>
		</select>
		<hr />
		<p>prototype.js的例子,ajax的另外一种使用方法,点击提交查询，显示学生姓名到下拉列表</p>
		学生姓名： <select id="stuNameOptions">
			<option value="">-请选择-</option>
		</select> <input type="button" value="提交查询" onclick="getStuInAsAjax();" />
		<hr />
		<p>POI表格导入兼容xls和xlsx格式</p>
		<form method="post" action="importAll.action"
			enctype="multipart/form-data">
			<input type="file" name="file" /> <input type="submit" value="上传" />
		</form>
		<hr />
		<p>使用map给sqlmap传递参数
			<br/>
		</p>
		<form method="post" action="insertStuInfoByMap.action">
			<input type="submit" value="使用Map向数据库中插入stuInfo_表中年龄为22的记录"></input>
		</form>
		
		<hr/>
		
		<p>批量添加
		</p>
		<form method="post" action="batchAdd.action">
			<input type="submit" value="批量添加"></input>
		</form>
		<hr/>
	</center>
</body>
</html>
