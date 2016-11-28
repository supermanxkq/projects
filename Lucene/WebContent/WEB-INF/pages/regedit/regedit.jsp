<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>注册新用户</title>
<base href="<%=basePath%>" />
<script src="js/jquery-1.4.2.min.js"></script>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<script type="text/javascript">
	/**验证码是否正确*/
	var codeFlag = false;
	//验证验证码
	function validateCode() {
		//获取输入框中的验证码
		var code = $("#rand").val();
		var dataUrl = 'system/user!validateCode';
		if (code == "") {
			$("#codeMsg").html("");
			$("#codeMsg").html("请输入验证码！");
		} else {
			//Ajax获取Session的验证码
			var params = {
				"rand" : code
			};
			$.ajax({
				async : false,
				url : dataUrl,
				data : params,
				dataType : "json",
				cache : false,
				type : "POST",
				error : function(textStatus, errorThrown) {
				},
				beforeSend : function() {
				},
				success : function(data, textStatus) {
					if (data == 1) {
						$("#codeMsg").html("");
						$("#codeMsg").html("验证码正确！");
						codeFlag = true;
					} else {
						$("#codeMsg").html("");
						$("#codeMsg").html("验证码错误！");
					}
				}
			});
		}
	}

	//验证验证码
	function luceneSearch() {
		//获取输入框中的验证码
		var keyWord = $("#keyWord").val();
		var dataUrl = 'system/user!luceneSearch';
		if (keyWord != "") {
			//Ajax获取Session的验证码
			var params = {
				"keyWord" : keyWord
			};
			$.ajax({
				async : false,
				url : dataUrl,
				data : params,
				dataType : "json",
				cache : false,
				type : "POST",
				error : function(textStatus, errorThrown) {
				},
				beforeSend : function() {
				},
				success : function(data, textStatus) {
					
					var obj = eval(data.searches);
					$("#result").html("");
					var insertHTML = "";
					 $(obj).each(function(index){    
						 var val = obj[index];
						 insertHTML+="编号："+val.id+"-------------标题："+val.title+"-------------内容："+val.content+"</br>";
// 						 $.each(object,function(key,value){    
// 							insertHTML+=this.title+"<br/>";
// 						       });    
						 });  
					
					if (data.success) {
						$("#result").html(insertHTML);
					}
				}
			});
		}
	}

	//重新产生验证码
	function changeValidateCode(obj) {
		// 获取当前的时间作为参数，无具体意义
		var timenow = new Date().getTime();
		// 每次请求需要一个不同的参数，否则可能会返回同样的验证码
		// 这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。
		obj.src = "system/randAction?d=" + timenow;
	}
	//验证全部
	function check() {
		//验证验证码
		validateCode();
		if (codeFlag) {
			return true;
		} else {
			return false;
		}
	}
</script>
</head>
<body>

	<form action="system/user!addSave" method="post"
		onsubmit="return check();">
		<table>
			<s:hidden name="method" id="method" />
			<tr>
				<td>用户名： <input type="text" maxlength="15"
					name="userDTO.userName" id="userNameInput"></input>
				</td>
			</tr>
			<tr>
				<td>验证码： <input type="text" id="rand" name="rand"
					onblur="validateCode();" /> <img src="system/randAction"
					id="CreateCheckCode" onclick="changeValidateCode(this)"
					title="点击图片刷新验证码" /> <span id="codeMsg"></span>
				</td>
			</tr>
			<tr>
				<td><button type="submit">注册</button></td>
			</tr>
		</table>
	</form>
	<hr />
	<input type="text" name="keyWord" id="keyWord" />
	<input type="submit" onclick="luceneSearch();" value="搜索" />
	<div id="result"></div>
</body>
</html>