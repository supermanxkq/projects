<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<base href="<%=basePath%>" />
		<title>${title}</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
	<link href="css/style2.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <!--[if ie]>
			<script type="text/javascript">
			$(function(){
	        function Hresize(){
	        var a = $(window).height();
	        $("table").height(a-100);}
	        $(window).resize(function() {Hresize()});
	        Hresize();
			});
	        </script>
	     <![endif]-->
		<style type="text/css">
			html{overflow-y:auto; overflow-x:hidden; height:100%;}
			body{ height:100%;}
		</style>
		
		<script type="text/javascript">
		$(document).ready(function(){
	 			
	 			$(".info-i").hover(function(){
	 				$(this).find(".pulldown-nav").addClass("hover");
	 				$(this).find(".pulldown").show();
	 			},function(){
	 				$(this).find(".pulldown").hide();
	 				$(this).find(".pulldown-nav").removeClass("hover");
	 			});
	 			
	 		});
		 function quit() {
				if(confirm("确定要退出系统吗？")){
					parent.window.location.href="<%=basePath%>system/login!logout";
				}
			}
			function goHome() {
	 			parent.leftFrame.location.href='<%=basePath%>system/index!firstleft';
				parent.main.location.href='<%=basePath%>article/article!list?columnCode=1';
	 		}

	 		function doUserEdit(){
             $("#userMenu").show();
	 		}		 

		</script>
		
	</head>
	<body >
		 <table height="100%" cellspacing="0" cellpadding="0" width="100%" border="0">
		  <tbody>
		    <tr>
		      <td colspan="3" ><iframe  style="width:100%; height:66px; position: relative; z-index: 10" name="Explorer_Tool" marginwidth="0" marginheight="0" src="<%=basePath%>system/index!top" frameBorder="0" noResize scrolling="no" bordercolor="threedface"></iframe></td>
		    </tr>
		    <tr id="myFrame">
		     <td rowspan="2" id="frmTitle" align="center" width="180" height="100%" style=" background:#f6f6f6; border-top:none">
			 <iframe style="width:100%; height:100%; position: relative; z-index: 100; id="leftFrame" name="leftFrame"  allowTransparency="true" marginheight="0" src="<%=basePath%>system/index!firstleft" frameborder="0" noResize></iframe>
			 </td>
		      <td rowspan="2" width="6"  style="width:6px;" valign="middle" ><iframe  style="width:6px; position: relative; z-index:10 height:100%；" id="sw" name="sw"  frameSpacing="2" src="<%=basePath%>system/index!separate" frameBorder="0" scrolling="no" ></iframe></td>
		    </tr>
		    <tr>
		      <td height="100%" style=" border-top:none; "><iframe style="z-index:-10;  width:100%; height:100%;" id="main" name="main"  allowTransparency="true" marginheight="0" src="<%=basePath%>article/article!list?columnCode=1" frameborder="0" noResize></iframe></td>
		    </tr> 
		    <tr>
		      <td colspan="3"><iframe style="Z-INDEX:-1;VISIBILITY:inherit; width:100%; height:25px" name="Explorer_Tool" marginwidth="0" marginheight="0" src="<%=basePath%>system/index!footer" frameborder="0" noResize scrolling="no" bordercolor="threedface"></iframe></td>
		    </tr>
		  </tbody>
		</table>		
		 
     <div class="hd-main clearfix" id="header" style=" position:absolute; background:#016292; height:25px; width:100px; z-index:20; right:40px; top:0px">
		<div class="info" >
			<ul>
				<li class="info-i user-name has-pulldown">
					<em class="f-icon pull-arrow"></em>
					<span class="name top-username"><img src="images/ulogo.png"/>&nbsp;&nbsp;&nbsp;${user_session.userName}</span>
					<div class="pulldown user-info" >
					<em class="arrow"></em>
					<div class="content">
						<span class="li"><a href="javascript:goHome()"><img src="images/home.png"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;返回主页</a></span>
						<span class="li"><a href="system/index!myself" target="main"><img src="images/u.png"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户设置</a></span>
						<span class="li"> <a href="javascript:quit()"><img src="images/quit.png"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;安全退出</a></span>						
					</div>
				</div>
				</li>			
			</ul>
		</div>	
	</div>		 
	</body>		
</html>
