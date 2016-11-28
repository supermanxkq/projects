<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!-- Author: Denis Petyukov <denis.petyukov@gmail.com> -->
		<title>留言版</title>
		<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="css/normalize.css">
		<link rel="stylesheet" href="kindeditor/themes/default/default.css" />
		<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
		<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
		<script charset="utf-8" src="validate/leavemessagejsp.js"></script>
		<script>
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="leaveMessage.leaveMessageContent"]',
				{
					newlineTag : this.value,
					resizeType : 1,
					allowPreviewEmoticons : false,
					allowImageUpload : false,
					items : [ 'fontname', 'fontsize', '|', 'forecolor',
							'hilitecolor', 'bold', 'italic', 'underline',
							'removeformat', '|', 'emoticons' ],
					afterChange : function() {
						//定义最大的字数限制
						var maxlimit = 500;
						if (this.count('text') > maxlimit)
							//截取
							editor.html(editor.html().substr(0, maxlimit));
						K('.word_count1').html(this.count());
						K('.word_count2').html(this.count('text'));
					}
				});
	});
</script>
		<script type="text/javascript">
	var editor;
	function checkNull() {
		if (editor.isEmpty()) {
			alert("请您输入留言内容吧。。。。");
			return false;
		} else {
			return true;
		}
		if (checkCount()) {
			return false;
		}
	}
</script>
		<style type="text/css">
#leaveBtn:HOVER {
	background: url(' back/ resources/images/ bg-menu-item-green-hover.gif')
		top left repeat-x !important;
}
</style>
		<link rel="stylesheet" type="text/css" href="reset.css" media="screen">
		<link rel="stylesheet" type="text/css" href="style.css" media="screen">
		<link rel="stylesheet" type="text/css" href="css/style4.css"
			media="screen">
		<!--[if lte IE 7]><link rel="stylesheet" type="text/css" href="ie_fixes.css" media="screen" /><![endif]-->
	</head>
	<body>
		<div
			style="margin-bottom: 20px; margin-top: 20px; margin-left: 200px;">
			<form action="addLeaveMessageAction" method="post"
				id="leaveMessageForm" onsubmit="return checkNull();">
				<span style="font: normal 0.80em arial, sans-serif; color: #666666;">留言者昵称：</span>
				<input type="text" name="leaveMessage.leaveMessageName"
					maxlength="10" />
				<br />
				<br />
				<textarea
					style="width: 953px; height: 100px; border: none; background: none;"
					name="leaveMessage.leaveMessageContent"></textarea>
				<span
					style="font: normal 0.80emarial, sans-serif; color: #666666; font-size: 12px; margin-top: 5px;">当前输入<span
					class="word_count2" id="word_count">0</span> 字，限制500字内。 </span>
				<br />
				<br />
				<input type="submit" value="留言" id="leaveBtn" class="leaveBtn"
					name="isEmpty"
					style="font-family: Verdana, Arial, sans-serif; -webkit-border-radius: 4px; border-radius: 4px; display: inline-block; background: #459300 url('back/resources/images/bg-button-green.gif') top left repeat-x !important; border: 1px solid #459300 !important; padding: 10px 30px 10px 30px !important; margin: 10px 0px 0px 850px; color: #fff !important; font-size: 11px !important; cursor: pointer;" />
			</form>
		</div>
		<div id="paper_left">
			<div id="paper_right">
				<div id="layout_wrapper">
					<div id="layout_container">
						<div id="layout_content">
							<div id="main">
								<div class="" style="background-color: black;">
									<div class="post_metadata">
										<div class="content">
											<span class="comment"><a href="#comments"
												style="text-decoration: none;"><s:property value="count" />条留言</a>
											</span>
										</div>
									</div>
								</div>
								<div class="post">
									<div class="post_body nicelist">
										<ol>
											<s:iterator value="leaveMessages" var="leaveMessage"
												status="st">
												<%--判断索引是奇数还是偶数 --%>
												<s:if test="#st.isEven()">
													<li class="alt">
														<div class="comment_gravatar left">
															<img alt=""
																src="headphoto/<s:property value="#leaveMessage.leaveHeadPhoto"/>"
																height="42" width="42">
														</div>
														<div class="comment_author left">
															<span class="comment"><s:property
																	value="#leaveMessage.leaveMessageName" />
															</span>
															<div class="date">
																<a href="#"><s:date
																		name="#leaveMessage.leaveMessageDate"
																		format="yyyy-MM-dd HH:mm:ss" />
																</a>
																<span style="margin-left: 700px;">#${floor-st.index}楼</span>
															</div>
														</div>
														<div class="clearer">
															&nbsp;
														</div>
														<div class="body">
															<p
																style="word-break: break-all; overflow: auto; font-family: xin; font-size: 16px;">
																<s:property value="#leaveMessage.leaveMessageContent"
																	escape="false" />
															</p>
															<p>
																<s:if
																	test="#leaveMessage.leaveMessageReply.leaveMessageReplyName!=null">
																	<s:property
																		value="#leaveMessage.leaveMessageReply.leaveMessageReplyName" />回复：
									<s:property
																		value="#leaveMessage.leaveMessageReply.leaveMessageReplyContent"
																		escape="false" />
																</s:if>
																<s:else>
											主人很懒，没有回复！
									</s:else>
															</p>
														</div>
													</li>
												</s:if>
												<s:else>
													<li id="comment-256">
														<div class="comment_gravatar left">
															<img alt=""
																src="headphoto/<s:property value="#leaveMessage.leaveHeadPhoto"/>"
																height="42" width="42">
														</div>
														<div class="comment_author left">
															<span class="comment"><s:property
																	value="#leaveMessage.leaveMessageName" />
															</span>
															<div class="date">
																<a href="#"><s:date
																		name="#leaveMessage.leaveMessageDate"
																		format="yyyy-MM-dd HH:mm:ss" />
																</a>
																<span style="margin-left: 700px;">#${floor-st.index}楼</span>
															</div>
														</div>
														<div class="clearer">
															&nbsp;
														</div>
														<div class="body">
															<p
																style="word-break: break-all; overflow: auto; font-family: xin; font-size: 16px;">
																<s:property value="#leaveMessage.leaveMessageContent"
																	escape="false" />
															</p>
															<p>
																<s:if
																	test="#leaveMessage.leaveMessageReply.leaveMessageReplyName!=null">
																	<s:property
																		value="#leaveMessage.leaveMessageReply.leaveMessageReplyName" />回复：
									<s:property
																		value="#leaveMessage.leaveMessageReply.leaveMessageReplyContent"
																		escape="false" />
																</s:if>
																<s:else>
											主人很懒，没有回复！
									</s:else>
															</p>
														</div>
													</li>
												</s:else>
											</s:iterator>
										</ol>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>










		<!-- 分页 -->
		<div style="margin-left: 500px;">
			共
			<span class="right-text09"><s:property value="pageAll" /> </span> 页|
			第
			<span class="right-text09"><s:property value="pageNow" /> </span> 页
			[
			<a href="findLeaveMessageToFrontAction?pageNow=1"
				class="right-font08">首页</a> |
			<a
				href="findLeaveMessageToFrontAction?pageNow=<s:property value="pageNow-1==0?1:pageNow-1"/>"
				class="right-font08">上一页</a> |
			<s:if test="pageNow+1<=pageAll">
				<a
					href="findLeaveMessageToFrontAction?pageNow=<s:property value="pageNow+1"/>"
					class="right-font08">下一页</a>
			</s:if>
			<s:else>
				<a
					href="findLeaveMessageToFrontAction?pageNow=<s:property value="pageAll"/>"
					class="right-font08">下一页</a>
			</s:else>
			|
			<a
				href="findLeaveMessageToFrontAction?pageNow=<s:property value="pageAll"/>"
				class="right-font08">末页</a>]
		</div>
	</body>
</html>

