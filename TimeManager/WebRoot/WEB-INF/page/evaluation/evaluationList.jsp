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
	<title>商品属性规格管理</title>
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
	<script src="js/jquery/jquery.ui.tabs.js"></script>
	<script src="js/common.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<style type="text/css">
	</style>
	<script type="text/javascript">
		$(function() {
			var $tabs = $("#tabs").tabs();	
			$('#tabs-2').click(function() { // 绑定单击事件
			    $tabs.tabs('select', 1);
			    return true;
			});		
		});
	</script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 评价管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<div style="margin:2px 4px;width:1150px;">
		<div style="float:left">
			<div style="border:1px solid #999;text-align:center;width:200px;height:300px;;line-height:25px;">
				<ul>
					<li><span>会员名：</span></li>
					<li><span>QQ号码：</span></li>
					<li><span>站内信息：</span></li>
					<li><span>地址：</span></li>
					<li><span>认证信息：</span></li>
					<li><a href="#">店铺评分是怎么打的？：</a></li>
					<li><a href="#">石油电商评价规则：</a></li>
					<li><a href="#">评价如何修改和删除：</a></li>
					<li><a href="#">炒作信用"如何惩罚"?：</a></li>
					<li><a href="#">被差评怎么办?：</a></li>
					<li><a href="#">手机上评价的步骤?：</a></li>
				</ul>
			</div>
		</div>
		<div style="margin-left:15px;float:left;width:920px;">
			<div style="border:1px solid #999;height:300px;">
				<div style="background-color:#DDD;line-height:30px;width:100%;overflow:hidden;">店铺半年内评分动态</div>
				<div style="margin:10px 30px;width:200px;float:left;">
					<div style="margin:20px 0;text-align:center;">
						<ul>
							<li>宝贝与描述想符合度</li>
							<li>★★★★★</li>
							<li>暂时无人打分</li>
						<ul>
					</div>
					
					<div style="margin:20px 0;text-align:center;">
						<ul>
							<li>卖家的服务态度</li>
							<li>★★★★★</li>
							<li>暂时无人打分</li>
						<ul>
					</div>
					
					<div style="margin:20px 0;text-align:center;">
						<ul>
							<li>卖家的发货速度</li>
							<li>★★★★★</li>
							<li>暂时无人打分</li>
						<ul>
					</div>
				</div>
				<div style="float:left;width:600px;margin:10px 0;">
					<div style="width:600px;height:30px;background-color:#F02;margin:35px 0;"></div>
					<div style="width:600px;height:30px;background-color:#F02;margin:35px 0;"></div>
					<div style="width:600px;height:30px;background-color:#F02;margin-top:35px;"></div>
					<div style="margin-top:25px;">
						<ul style="text-align:center;">
							<li style="float:left;width:120px;">
								<div style="width:100px;">1分</div>
								<div style="width:100px;">非常不满意</div>
							</li>
							<li style="float:left;width:120px;">
								<div style="width:100px;">2分</div>
								<div style="width:100px;">不满意</div>
							</li>
							<li style="float:left;width:120px;">
								<div style="width:100px;">3分</div>
								<div style="width:100px;">一般</div>
							</li>
							<li style="float:left;width:120px;">
								<div style="width:100px;">4分</div>
								<div style="width:100px;">满意</div>
							</li>
							<li style="float:left;width:120px;">
								<div style="width:100px;">5分</div>
								<div style="width:100px;">非常满意</div>
							</li>
						</ul>
					</div>
				</div>
				<div style="clear:both"></div>
			</div>
			<div>
				<div style="background-color:#DDD;margin:10px 0;line-height:30px;">卖家信用度累计</div>
				<div>
					<table width="920px" class="listTable" cellpadding="0" cellspacing="0">
						<tr>
							<th width="15%"></th>
							<th width="15%">最近一周</th>
							<th width="15%">最近一个月</th>
							<th width="15%">最近六个月</th>
							<th width="15%">六个月前</th>
							<th width="15%">总计</th>
						</tr>
						<tr>
							<th>好评</th>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
						</tr>
						<tr>
							<th>中评</th>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
						</tr>
						<tr>
							<th>差评</th>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
						</tr>
						<tr>
							<th>总计</th>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
						</tr>
					</table>
				</div>
				
				<div style="background-color:#DDD;margin:10px 0;line-height:30px;">卖家累积信用</div>
				<div>
					<div style="width:910px" class="search" id="tabs">
					     <ul>
						   <li><a href="#tabs-1">来自买家的评论</a></li>
						   <li><a href="#tabs-2">给他人的评论</a></li>
						   <li><a href="#tabs-3">退回评论</a></li>
					     </ul>
						 <div class="search" id="tabs-1">
						 	<jsp:include page="evaluationTab.jsp"></jsp:include>
						 </div>
						 <div class="search" id="tabs-2">
						 	<jsp:include page="evaluationTab.jsp"></jsp:include>
						 </div>
						 <div class="search" id="tabs-3">
						 	<jsp:include page="evaluationTab.jsp"></jsp:include>
						 </div>
					</div>
				</div>
			</div>
		</div>
		<div style="clear:both"></div>
	</div>
	
	
	
</body> 
</html>