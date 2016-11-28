<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>" />
	<title>首页展示内容</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.messager.js"></script>
	<link href="js/jquery/css/jquery.ui.all.css"  rel="stylesheet"  type="text/css" />	
	<script src="js/jquery/jquery.ui.core.js"></script>
	<script src="js/jquery/jquery.ui.widget.js"></script>
	<script src="js/jquery/jquery.ui.mouse.js"></script>
	<script src="js/jquery/jquery.ui.button.js"></script>
	<script src="js/jquery/jquery.ui.draggable.js"></script>
	<script src="js/jquery/jquery.ui.position.js"></script>
	<script src="js/jquery/jquery.ui.resizable.js"></script>
	<script src="js/jquery/jquery.ui.dialog.js"></script>
	<script src="js/datepicker/WdatePicker.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<style>
	.R_ts_bg{ width:727px; height:238px; background:url(images/ts_bg.gif) no-repeat; margin:160px auto 0 auto;padding:60px 0 0 50px;}
	.R_ts_bg_ul{}
	.R_ts_bg_ul li{ float:left; width:160px; height:39px; line-height:39px; border:1px solid #ccc; padding-left:40px; margin:20px 0 0 15px; overflow:hidden; background:url(images/sy_li_bg.jpg) no-repeat}
	.R_ts_bg_ul li a{ font-size:14px; font-weight:bold; color:#333}
	.R_ts_bg_ul li a span{  color:#f60}
	</style>
	<script type="text/javascript">
		var sendTime = 3*60*1000;//弹出频率   1秒=1000
		var stayTime = 5*1000;//停留时间   1秒=1000
		function showmsg(){
			var userName = $("#userName").val();
			var params = {
		        "userDTO.userName" : userName
		  	};
			var actionUrl = "system/index!tips";
		    $.ajax( {
		        url : actionUrl,
		        data : params,   
		        dataType : "json",
		        cache : false, 
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		    		//var thestr = "系统ajax交互错误!";
			 		//$.messager.lays(280, 160);//设置宽高 
					//$.messager.show('<font color=red>系统提示</font>',thestr, stayTime);
		        },
		        success : function(data, textStatus) {
		            if(data!=""){
		            	var thetable = "<ul class='H_system' id='message_content'>";
		            	thetable = thetable + data;
		            	thetable = thetable + "</ul>";
		            	$.messager.lays(280, 160);//设置宽高 
						$.messager.show('<font color=red>系统提示</font>',thetable, stayTime);
		            }
		        }   
		    });
		}
		
	</script>
</head>
<body>
<input type="hidden" id="userName" name="userName" value="${user_session.userName}"/>

<div class="H_header" style="padding-top: 10px;">
  			  
			  <div id="userMenu" style="display: none;width:10;height:30;z-index:101111" >
			  <ul>
			   	  <li style="line-height: 50px;height: 0px; backgroung-color:#fff;"><img src="images/quit.png"/><a href="javascript:quit()">&nbsp;&nbsp;&nbsp;&nbsp;安全退出</a></li>
			      <li style="line-height: 10px;height: 0px; z-index: 100000"><img src="images/home.png"/><a href="javascript:goHome()">&nbsp;&nbsp;&nbsp;&nbsp;返回主页</a></li>
			      <li style="line-height: 40px;height: 10px;  z-index: 100000"><img src="images/u.png"/><a href="system/index!myself" target="main">&nbsp;&nbsp;&nbsp;&nbsp;用户设置</a></li>

			   </ul>	
			  		      
              </div>               
          </div>
       <div class="Position">
		当前位置是：HOME &gt;&gt;首页展示内容&gt;&gt;待处理业务查看
	</div>   
      <s:form action="system/index" method="post" style="font-size:14px; line-height:30px; padding-left:50px;" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
         
          <s:hidden name="method" id="method"/>	
          <s:if test="#session.user_session.userLevel!=2">
        
		        <div class="List_tit01">基本信息</div>                                                                                                                                 	<tr>
		        <th align="right" style="font-size:90">机构名称：</th>
		        ${homePageShowDTO.name}
		        <br/>
                <th align="right">地 址：</th>
                ${homePageShowDTO.address }
		        <br/>
		      	<th align="right">联系人：</th>
		      	${homePageShowDTO.conPerName }
		      	<br/>
		        <th align="right">联系电话：</th>
		        ${homePageShowDTO.conPerTeleNo }
         </s:if>
         <s:if test="#session.user_session.userLevel==2">
                <div class="List_tit01">基本信息</div> 
					<th align="right">店铺名称：</th>
					${homePageShowDTO.storeName }
					<br/>
					<th align="right">联系人：</th>
					${homePageShowDTO.contPerson }
					<br/>
					<th align="right">手机号码：</th>
					${homePageShowDTO.teleNo }
					<br/>
					<th align="right">店铺地址：</th>
					${homePageShowDTO.storeAddress }
					<br/>
				    <th align="right">店铺简介：</th>
				    ${homePageShowDTO.storeIntroduct }
         </s:if>
         
         <div class="List_tit01">待处理的业务</div>
         <s:if test="#session.user_session.userLevel!=2">
						    
						                商铺入驻申请 :
						 	<a href="base/auditMerchants">&nbsp;&nbsp;&nbsp;${undealServiceNumDTO.merCheckNum}&nbsp;&nbsp;&nbsp;笔<br/></a>
							收到的会员投诉:
							<a href="complaintFiled/complaintFiled">&nbsp;${undealServiceNumDTO.complaintsNum} &nbsp;&nbsp;封<br/></a>
							
         </s:if>
           <s:if test="#session.user_session.userLevel==2">
              <s:hidden name="undealServiceNumDTO.dealId" id="dealId"></s:hidden>
                
						    <!-- 定义的参数为type，其值为等号后面的值，用于在订单管理中根据传过来的值来判断要跳转的不同类型订单页面 -->
						 	等待发货的订单:    
							<a  href="orders/orders?type=2"> &nbsp;&nbsp;${undealServiceNumDTO.noDelivNum}     &nbsp;&nbsp;&nbsp;封</a>
							 &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
						              等待买家付款的订单：
							<a href="orders/orders?type=1">${undealServiceNumDTO.noPaidNum}&nbsp;&nbsp;&nbsp;笔</a>
						    <br/>
						             收到的退款申请：   
							<a href="orders/remoney">${undealServiceNumDTO.returnNum} &nbsp;&nbsp;&nbsp;笔 </a>
							&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;未阅读的商品评价：
							<a href="orders/orders?type=5">${undealServiceNumDTO.unReadNum} &nbsp;&nbsp;&nbsp;条</a>
						    <br/>
						             收到的投诉：
							<a href="complaintFiled/complaintFiled">${undealServiceNumDTO.complaintsNum} &nbsp;&nbsp;&nbsp; 封</a>
              </s:if>
      </s:form>  
</body>
</html>	
	
	