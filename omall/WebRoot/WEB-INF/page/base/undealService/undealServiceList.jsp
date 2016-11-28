<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>" />
	<title>商城首界面业务提醒</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />	
	<script src="js/jquery-1.4.2.min.js"></script>
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
	<script type="text/javascript">	
	
		
    </script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt;商城首界面业务提醒&gt;&gt;待处理业务查看
	</div>
	
	<s:form action="buss/serviceparams" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
		   <div class="List_tit">待处理业务统计信息</div>
              <table width="99%" id="MembersTb" class="listTable" cellpadding="0" cellspacing="0">
              <s:hidden name="undealServiceNumDTO.dealId" id="dealId"></s:hidden>
                
						<tr>
						    <th>
						    <!-- 定义的参数为type，其值为等号后面的值，用于在订单管理中根据传过来的值来判断要跳转的不同类型订单页面 -->
						 	<a  href="orders/orders?type=2">等待发货的订单 </a>   
						    </th>
							<td width=30%>${undealServiceNumDTO.noDelivNum}     &nbsp;&nbsp;&nbsp;笔 </td>
							<th>
							<a href="orders/orders?type=3">已发货的订单</a>
							</th>
							<td>${undealServiceNumDTO.postedNum} &nbsp;&nbsp;&nbsp;笔  </td>
						</tr>
						<tr>
						    <th>
						    <a href="orders/orders?type=1">等待买家付款的订单</a>
						    </th>
							<td>${undealServiceNumDTO.noPaidNum}&nbsp;&nbsp;&nbsp;笔</td>
							<th>
							<a href="orders/orders?type=6">已成功的订单</a>
							</th>
							<td>${undealServiceNumDTO.completedNum} &nbsp;&nbsp;&nbsp;笔 </td>
						</tr>
						<tr>
						    <th>
						    <a href="orders/orders?type=4">退款中的订单</a>   
						    </th>
							<td>${undealServiceNumDTO.returnNum} &nbsp;&nbsp;&nbsp;笔 </td>
							<th>
							<a href="orders/orders?type=5">未阅读的商品评价</a>
							</th>
							<td>${undealServiceNumDTO.unReadNum} &nbsp;&nbsp;&nbsp;条</td>
						</tr>
						
						<tr>
						    <th>收到的投诉</th>
							<td>${undealServiceNumDTO.complaintsNum} &nbsp;&nbsp;&nbsp; 封 </td>
						</tr>
						
              </table>
	   
	 </s:form>
	
</body>
</html>