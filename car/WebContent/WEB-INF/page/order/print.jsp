<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<title>订单预览</title>
<meta name="description"
	content="前程无忧为企业提供人才招聘、猎头、培训、测评和人事外包在内的全方位的人力资源服务，帮助个人求职者与企业搭建最佳的人才招募和人才培养渠道。">
	<meta name="keywords" content="人才，招聘，简历，工作，面试，薪酬，跳槽，猎头，培训，测评，人事">
		<meta name="robots" content="all">
			<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
				<link href="http://js.51jobcdn.com/in/css/style.css?20151231"
					rel="stylesheet" type="text/css">
					<link href="http://js.51jobcdn.com/in/css/cv.css?20091117"
						rel="stylesheet" type="text/css">

						<script language="javascript"
							src="http://js.51jobcdn.com/in/js/2009/jQuery.js?20151231"></script>
						<script language="javascript"
							src="http://js.51jobcdn.com/in/js/2009/JsBase.js?20151231"></script>
						<script language="javascript"
							src="http://js.51jobcdn.com/in/js/2009/Base.js?20151231"></script>
						<script language="javascript"
							src="http://js.51jobcdn.com/in/js/2009/Base.ZzLayer.js"></script>
						<script language="javascript"
							src="http://js.51jobcdn.com/in/js/2009/Base.ZzLayer.ExtZzLayer.js"></script>
						<script language="javascript"
							src="http://js.51jobcdn.com/in/js/2009/cv/cv_export.js?20091117"></script>
</head>
<body bgcolor="#FFFFFF">
	<table width="778" border="0" cellpadding="0" align="center">
		<tbody>
			<tr align="right">
				<td style="padding-right: 20px;"><img
					src="http://img01.51jobcdn.com/im/2009/cv/print_ico.gif"
					align="absmiddle"><a
						style="text-decoration: none; color: #333333;"
						href="javascript:window.print();">打印</a</td>
			</tr>
		</tbody>
	</table>
	<style>
table {
	FONT-SIZE: 14px;
	LINE-HEIGHT: 150%;
	FONT-FAMILY: Arial, Helvetica, sans-serif, '宋体', tahoma, Srial,
		helvetica, sans-serif;
}

td {
	FONT-SIZE: 14px;
	LINE-HEIGHT: 200%;
	FONT-FAMILY: Arial, Helvetica, sans-serif, '宋体', tahoma, Srial,
		helvetica, sans-serif;
	word-break: break-all;
}

P {
	FONT-SIZE: 14px;
	LINE-HEIGHT: 150%;
	FONT-FAMILY: Arial, Helvetica, sans-serif, '宋体', tahoma, Srial,
		helvetica, sans-serif;
	margin: 0px;
}

.blue {
	COLOR: #256fb8
}

A:link {
	COLOR: #296ebb;
	TEXT-DECORATION: none
}

A:visited {
	COLOR: black;
	TEXT-DECORATION: none
}

.cvtitle {
	font-weight: 700;
	color: #256fb8;
	font-size: 14px;
	padding-top: 5px;
}

.blue1 {
	COLOR: #ff7400
}

.blue1 {
	color: #256fb8;
}

.blue2 {
	COLOR: #ff7400
}

.font14 {
	font-size: 14px;
}

.graybutton {
	color: #676767;
}

.v_table001 {
	width: 710px;
	line-height: 22px;
	margin: 0 0 10px 10px;
	padding: 0px;
	text-align: left;
}

.titleLineB {
	border-bottom: #256fb8 4px solid;
	height: 32px;
	line-height: 32px;
	margin: 0px;
	padding: 0px;
}

.titleLineR {
	border-bottom: #cbcdcc 4px solid;
	height: 32px;
	line-height: 32px;
	margin: 0px;
	padding: 0px;
}

.traitLabel {
	background-color: #ffdfc1;
	color: #5a5a5a;
	border: #f0f0f0 1px solid;
	font-size: 12px;
	padding: 2px 6px;
	height: 22px;
	line-height: 22px;
	margin-right: 6px;
	display: inline-block; `
	box-shadow: 1px 1px 1px #b8b8b8;
	-moz-box-shadow: 1px 1px 1px #b8b8b8;
	-webkit-box-shadow: 1px 1px 1px #b8b8b8;
}

.TextinTable {
	line-height: 32px;
	word-break: break-all;
}

.textCard {
	padding: 4px 20px;
	margin: 10px auto 10px 0px;
	border: #ccc 1px solid;
}
/*评价*/
.textComment {
	border: #b8dcfe 1px solid;
	padding: 10px;
	margin-top: 0px auto 0px 0px;
}

.textComment h4 {
	margin: 0px;
	font-size: 14px;
}

.textComment h4 span {
	color: #0275e8;
	font-size: 14px;
	font-weight: normal;
}

.textComment h4 img {
	vertical-align: middle;
}

.textComment p {
	font-size: 12px;
	line-height: 20px;
	margin: 0px;
}

.textCommentLine {
	border-bottom: #b8dcfe 1px dashed;
	height: 1px;
	margin-top: 6px;
}
</style>
	<table width="778" border="0" cellpadding="0" align="center">
		<tbody>
			<tr>
				<td>
					<table
						style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; MARGIN: 0px auto; BORDER-RIGHT-WIDTH: 0px"
						cellspacing="0" cellpadding="0" width="760" align="center"
						border="0">
						<tbody>
							<tr>
								<td style="BORDER: #93B0ED 2px solid;" valign="top">
									<table cellspacing="0" cellpadding="0" width="760"
										align="center" border="0">
										<tbody>
											<tr>
												<td valign="top">
													<table
														style="BORDER: #3076BC 1px solid; PADDING-RIGHT: 0px; PADDING-LEFT: 8px; BACKGROUND: #F5FAFE; PADDING-BOTTOM: 0px; MARGIN: 8px auto; LINE-HEIGHT: 22px; PADDING-TOP: 8px;"
														cellspacing="0" cellpadding="0" width="97%" align="center"
														border="0">
														<tbody>
															<tr>
																<td style="BORDER-BOTTOM: #88b4e0 1px dashed"
																	height="30" align="center"><span
																	style="FLOAT: right"> <img height="1"
																		src="http://img01.51jobcdn.com/im/2009/resumetemplate/space.gif"
																		width="12" border="0"></span> <span
																	style="FONT-SIZE: 25px; LINE-HEIGHT: 30px; HEIGHT: 30px;">
																		<strong>欢迎光临徐强汽修</strong>
																</span> <img height="1"
																	src="http://img01.51jobcdn.com/im/2009/resumetemplate/space.gif"
																	width="15" border="0"></td>
															</tr>
															<tr>
																<td valign="top">
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tbody>
																			<tr>
																				<td height="26" colspan="4"><span class="blue1">
																						<b>3-4年工作经验&nbsp;|&nbsp;男&nbsp;|&nbsp;23岁(1992年8月
																							16日 )
																							&nbsp;|&nbsp;未婚&nbsp;|&nbsp;170cm&nbsp;|&nbsp;中共党员</b>
																				</span></td>
																				<td width="20%" rowspan="6" align="center"
																					valign="middle"><img
																					src="http://img01.51jobcdn.com/im/2009/resumetemplate/resume_match_manpic.gif"
																					width="90" height="110">
																						<p>(微信：xukaiqiang1234)</p></td>
																			</tr>
																			<tr>
																				<td width="10%" height="20">居住地：</td>
																				<td width="42%" height="20">额济纳旗</td>
																			</tr>
																			<tr>
																				<td height="20">地 址：</td>
																				<td height="20" colspan="3">额济纳旗徐强汽修
																					（邮编：100016）</td>
																			</tr>
																			<tr>
																				<td height="20">电 话：</td>
																				<td height="20" colspan="3">15001164424（手机）</td>
																			</tr>
																			<tr>
																				<td height="20">E-mail：</td>
																				<td height="20" colspan="3"><a
																					href="mailto:15001164424@163.com
																	   "
																					class="blue1"> 994028591@qq.com </a></td>
																			</tr>
																		</tbody>
																	</table>
																</td>
															</tr>
														</tbody>
													</table>
													<hr>
													<table width="97%" border="0" align="center"
														cellspacing="0" cellpadding="0">
														<tbody>
															<tr>
																<th width="100" align="left">
																	<div class="titleLineB">销售清单</div>
																</th>
																<th align="left" width="690">
																	<div class="titleLineR">&nbsp;</div>
																</th>
															</tr>
															<tr>
																<td height="10" align="left" valign="middle"></td>
															</tr>
														</tbody>
													</table>


													<table cellspacing="0" cellpadding="0" width="97%"
														align="center" border="1">
														<thead>
															<tr >
																<td align="center">商品名称</td>
																<td align="center">数量</td>
																<td align="center">单价</td>
																<td align="center">金额</td>
															</tr>
														</thead>
														<tbody>
														<s:iterator value="orderDetails" var="orderDetail">
															<tr>
																<td><s:property value="#orderDetail.goodsName"/></td>
																<td><s:property value="#orderDetail.count" /></td>
																<td><s:property value="#orderDetail.price"/>元</td>
																<td><s:property value="#orderDetail.count*#orderDetail.price"/>元</td>
															</tr>
														</s:iterator>
														</tbody>
														<tfoot>
															<tr>
																<td>日期：</td>
																<td><s:date format="yyyy-MM-dd HH:mm:ss" name="order.createTime"/></td>
																<td>总计：</td>
																<td colspan="1" align="right">${totalPrice}元</td>
															</tr>
														</tfoot>
													</table>
										</tbody>
									</table>
									<hr/>
									<table width="97%" border="0" align="center"
														cellspacing="0" cellpadding="0">
														<tbody>
															<tr>
																<th width="100" align="left">
																	<div class="titleLineB">客户信息</div>
																</th>
																<th align="left" width="690">
																	<div class="titleLineR">&nbsp;</div>
																</th>
															</tr>
															<tr>
																<td height="10" align="left" valign="middle"></td>
															</tr>
														</tbody>
													</table>
													<table cellspacing="0" cellpadding="0" width="97%"
														align="center" border="0">
														<thead>
															<tr >
																<td align="right" width="240px">顾客名称:</td>
																<td align="left" width="100px"><s:property value="order.customer"/></td>
															</tr>
														</thead>
													</table>
									<br/><br/>
									
								</td>
							</tr>
							<tr style="HEIGHT: 10px">
								<td style="HEIGHT: 10px"></td>
							</tr>
							<tr>
								<td style="WIDTH: 100%; HEIGHT: 10px" align="middle"></td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>