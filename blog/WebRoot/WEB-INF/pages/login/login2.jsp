<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back/share/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html lang="zh-CN"><head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <base href="<%=basePath %>">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/logo.png">

    <title>用户登录</title>

    <!-- Bootstrap core CSS -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/css/signin.css" rel="stylesheet">

    <script src="assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="container">

      <form class="form-signin" action="system/login!login" method="post">
        <h2 class="form-signin-heading">请您登录</h2>
        <input type="email" id="inputEmail"  name="userDTO.userName"  class="form-control" placeholder="请输入用户名(邮箱)" required="" autofocus="" value="${userName}">
        <input type="password" id="inputPassword" name="userDTO.passWord" class="form-control" placeholder="密码" required="">
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
        <s:if test="hasFieldErrors()">
               <span class="help-inline"><s:fielderror name="error"
								cssStyle="list-style-type:none;" /></span>
        </s:if>
      </form>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="assets/js/ie10-viewport-bug-workaround.js"></script>

</body></html>