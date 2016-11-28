<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html lang="en-US">
	<head>
		<meta charset="UTF-8">
		<title>服务清单</title>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
			type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.4.2.min.js"></script>
		<script src="js/common.js"></script>
		<script src="js/datepicker/WdatePicker.js"></script>
		<script type="text/javascript" charset="utf-8"
			src="ueditor/ueditor.config.js"></script>
		<script type="text/javascript" charset="utf-8"
			src="ueditor/ueditor.all.min.js"> </script>
		<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
		<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
		<script type="text/javascript" charset="utf-8"
			src="ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
		//提交
		function check(){
			//获取UEditor中的内容，然后进行表单的提交
				    if (!UE.getEditor('editor').hasContents()){
				    alert('请先填写内容!');
				    return false;
				    }else{
				    $("#content").val(UE.getEditor('editor').getContent());
				    $("#fm").submit();
				  }
		}
		/**修改时初始化UEditor*/
		function init(){
				var method=$("#method").val();
				if(method=="updateData"){
					ue.addListener('ready', function (){
						UE.getEditor('editor').setContent($('#content').val(), false);
					});
				}
		}
</script>

	</head>
	<body onload="init();">
		<div class="Position">
			当前位置是：HOME &gt;&gt;文章管理 &gt;&gt;文章管理
		</div>
		<s:form action="article/article" method="post"  id="fm"
			onsubmit="document.getElementById('submitInput').disabled = true;return true;"
			theme="simple">
			<s:hidden name="method" id="method" />
			<input name="articleDTO.user.userId"  value="${user_session.userId}" type="hidden"/>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">
				<tr>
					<td align="right">
						<span class="Color5">* </span>标题：
					</td>
					<td>
						<s:textfield name="articleDTO.title" id="name" maxlength="50"
							cssClass="formInput" theme="simple" onblur="checkName();" />
						<label id="nameMsg" class="errorMsg" style="display: none;"></label>
					</td>
					<th align="right">
						<span class="Color5">* </span>发布状态：
					</th>
					<td>
						<s:select id="servType" name="articleDTO.status"
							list="#request.status" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="Color5">* </span>邀请嘉宾：
					</td>
					<td>
						<s:textfield name="articleDTO.guest" id="guest" maxlength="50"
							cssClass="formInput" theme="simple" onblur="checkName();" />
						<label id="nameMsg" class="errorMsg" style="display: none;"></label>
					</td>
					<th align="right">
						<span class="Color5">* </span>参与人：
					</th>
					<td>
						<s:textfield id="somePeople" name="articleDTO.somePeople"
							cssClass="formInput" theme="simple" maxlength="20" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="Color5">* </span>相关媒体：
					</td>
					<td>
						<s:textfield name="articleDTO.someMedia" id="someMedia"
							maxlength="50" cssClass="formInput" theme="simple"
							onblur="checkName();" />
						<label id="nameMsg" class="errorMsg" style="display: none;"></label>
					</td>
					<th align="right">
						<span class="Color5">* </span>活动地址：
					</th>
					<td>
						<s:textfield id="address" name="articleDTO.address"
							cssClass="formInput" theme="simple" maxlength="20" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="Color5">* </span>活动时间：
					</td>
					<td>
						<s:textfield id="actionDateString"
							name="articleDTO.actionDateString" cssClass="formInput2"
							maxlength="20" theme="simple"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
						<label id="nameMsg" class="errorMsg" style="display: none;"></label>
					</td>
					<th align="right">
						<span class="Color5">* </span>编辑者：
					</th>
					<td>
						${user_session.userName}
						<input name="articleDTO.user.userName" id="userName"
							value="${user_session.userName}" type="hidden" />
					</td>
				</tr>
				<tr>
					<th align="right" width="10%">
						内容详情：
					</th>
					<td width="30%" colspan="3">
						<!-- 加载编辑器的容器 -->
						<script id="editor" type="text/plain"
							style="width:900px;height:500px;"></script>
						<s:hidden id="content" name="articleDTO.content" />
					</td>
				</tr>
			</table>
			<div class="formTableBottom">
				<s:if test="method=='addSave'">
					<input id="submitInput" type="submit" class="formButton"
						value="保 存" onclick="return check();" />
				</s:if>
				<s:else>
					<input id="submitInput" type="submit" class="formButton" value="修改"
						onclick="return check();" />
				</s:else>
				<input type="button" class="formButton" value="返 回"
					onclick="go('article/article!list?columnCode=1')" />
			</div>
		</s:form>
		<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
</script>
	</body>
</html>