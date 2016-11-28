<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var xmlHttpRequest=null;
	//创建Ajax引擎
	function getXmlHttpRequest() {
			if (window.ActiveXObject) {
				//ie
				xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
			} else {
				//firefox
				xmlHttpRequest = new XMLHttpRequest();
			}
	}
	//检查用户名是否重复
	function checkUserName() {
		//创建ajax引擎
		getXmlHttpRequest();
		//如果ajax引擎创建成功
		if (xmlHttpRequest) {
			//第一个参数表示请求的方式get/post
			//第二个参数指定url
			//第三个参数true表示使用异步机制
			//如果使用get方法请求，第一次进行请求，第二次从缓存里面取
			var url = "/Ajax/AjaxReturnTypeJson";
			//post提交数据
			var data="username="+$("username").value;
			xmlHttpRequest.open("post", url, true);
			xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			//回调函数
			xmlHttpRequest.onreadystatechange =chuli;
			//发送请求,如果是get，写null， 可以传参数
			xmlHttpRequest.send(data);
		}
	}

	//回调函数
	function chuli() {
		if (xmlHttpRequest.readyState == 4) {
			//使用eval将字符串转换为json对象
			var res=eval("("+xmlHttpRequest.responseText+")");
			//res.res;
			var ress="";
			for(var i=0;i<res.length;i++){
				ress+=res[i].res+","+res[i].name;
			}
			$("res").innerHTML=ress;
		}
	}

	//$方法
	function $(id) {
		return document.getElementById(id);
	}
</script>
</head>
<body>
	用户名:(输入aaa重复)
	<input type="text" id="username" onkeyup="checkUserName();" />
	<span id="res" style="color: red;"></span>
</body>
</html>