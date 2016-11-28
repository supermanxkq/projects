<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<title>添加软件</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
	type="image/x-icon" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.4.2.min.js"></script>
<link href="js/jquery/css/jquery.ui.all.css" rel="stylesheet"
	type="text/css" />
<script src="js/jquery/jquery.ui.core.js"></script>
<script src="js/jquery/jquery.ui.widget.js"></script>
<script src="js/jquery/jquery.ui.mouse.js"></script>
<script src="js/jquery/jquery.ui.button.js"></script>
<script src="js/jquery/jquery.ui.draggable.js"></script>
<script src="js/jquery/jquery.ui.position.js"></script>
<script src="js/jquery/jquery.ui.autocomplete.js"></script>
<script src="js/jquery/jquery.ui.resizable.js"></script>
<script src="js/jquery/jquery.ui.dialog.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript" src="js/areaSelect.js"></script>
<link href="resources/css/style.css" rel="stylesheet" type="text/css" />
<script src="js/common.js"></script>
<script src="js/pubValiPattern.js"></script>
<script src="js/pubValidate.js"></script>
<!-- uediter开始 -->
<script type="text/javascript" charset="utf-8"
	src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="ueditor/ueditor.all.min.js">
	
</script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="ueditor/lang/zh-cn/zh-cn.js"></script>
<!-- ueditoer结束 -->
<script type="text/javascript">
	var nameFlag = false;
	var panHrefFlag = false;
	var thumbnailFlag = false;
	var descFlag = false;
	var summaryFlag = false;
	//验证简介
	function checkSummary() {
		var summary = $.trim($("#summary").val());
		if (summary.length == 0) {
			$("#summaryMsg").html("请输入简介！");
			$("#summaryMsg").show();
			summaryFlag = false;
		} else {
			$("#summaryMsg").hide();
			summaryFlag = true;
		}
	}
	//验证代码名称
	function checkName() {
		if ($.trim($("#name").val()) == "") {
			$("#nameMsg").html("");
			$("#nameMsg").html("请输入软件名称!");
			$("#nameMsg").show();
			nameFlag = false;
		} else {
			$("#nameMsg").hide();
			nameFlag = true;
		}
	}

	//验证网盘地址
	function checkPanHref() {
		if ($.trim($("#panHref").val()) == "") {
			$("#panHrefMsg").html("");
			$("#panHrefMsg").html("请输入网盘地址!");
			$("#panHrefMsg").show();
			panHrefFlag = false;
		} else {
			var strReg = /(http[s]?|ftp):\/\/[^\/\.]+?\..+\w$/i;
			var re = new RegExp(strReg);
			if (re.test($("#panHref").val())) {
				$("#panHrefMsg").hide();
				panHrefFlag = true;
			} else {
				$("#panHrefMsg").hide();
				$("#panHrefMsg").html("网盘地址不合法!");
				$("#panHrefMsg").show();
				panHrefFlag = false;
			}
		}
	}
	//验证缩略图是否上传
	function checkThumbnail() {
		var thumbnail = $.trim($("#thumbnail").val());
		if (thumbnail.length == 0 && $("#method").val() == "addSave") {
			$("#thumbnailMsg").html("");
			$("#thumbnailMsg").html("请选择缩略图!");
			$("#thumbnailMsg").show();
			thumbnailFlag = false;
		} else {
			$("#thumbnailMsg").hide();
			thumbnailFlag = true;
		}
	}

	//验证分类
	function checkType() {
		if (!$(':radio[name=softWareDTO.typeId]:checked').length) {
			$("#typeIdMsg").html("");
			$("#typeIdMsg").html("请选择分类!");
			$("#typeIdMsg").show();
			typeIdFlag = false;
		} else {
			$("#typeIdMsg").hide();
			typeIdFlag = true;
		}
	}

	//提交表单验证全部
	function check() {
		checkName();
		checkPanHref();
		checkType();
		checkThumbnail();
		checkSummary();
		if (!UE.getEditor('editor').hasContents()) {
			alert('请先填写内容!');
			descFlag = false;
		} else {
			$("#description").val(UE.getEditor('editor').getContent());
			descFlag = true;
		}
		if (nameFlag && panHrefFlag && thumbnailFlag && descFlag && summaryFlag) {
			$("#fm").submit();
			return true;
		} else {
			return false;
		}
	}
</script>
</head>
<body>
	<div class="Position">
		当前位置是:<a href="managers/goods">软件库</a> &gt;&gt;软件管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<s:form action="software/software" method="post" theme="simple" id="fm"
		onsubmit="document.getElementById('submitInput').disabled = true;return true;"
		enctype="multipart/form-data">
		<s:hidden name="method" id="method" />
		<s:hidden name="softWareDTO.id" id="id" />
		<s:hidden name="softWareDTO.thumbnailPath" />
		<s:hidden name="softWareDTO.author" />
		<s:hidden name="softWareDTO.createDate" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="formTable">
			<tr>
				<th style="text-align: center; width: 10%;"><span
					class="Color5">* </span>软件名称:</th>
				<td style="text-align: left;"><s:textfield
						name="softWareDTO.name" id="name" maxlength="20"
						cssClass="formInput" theme="simple" onblur="checkName();" /> <label
					id="nameMsg" class="errorMsg" style="display: none;"></label></td>
				<th style="text-align: center; width: 10%;"><span
					class="Color5">* </span>网盘地址:</th>
				<td style="text-align: left;"><s:textfield
						name="softWareDTO.panHref" id="panHref" maxlength="100"
						cssClass="formInput" theme="simple" onblur="checkPanHref();" /><label
					id="panHrefMsg" class="errorMsg" style="display: none;"></label></td>
			</tr>
			<tr>
				<th style="text-align: center; width: 10%;"><span
					class="Color5">* </span>分类:</th>
				<td style="text-align: left;" colspan="3"><s:radio
						name="softWareDTO.typeId" id="typeId"
						list="#request.softWareStyles" onchange="checkType();"
						listKey="key" listValue="value" /></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="4" style="text-align: left;"><label id="typeIdMsg"
					class="errorMsg" style="display: none;"></label></td>
			</tr>
			<tr>
				<th style="text-align: center; width: 10%;"><span
					class="Color5">* </span>缩略图:</th>
				<td colspan="" style="text-align: left;"><s:file
						name="softWareDTO.imageFile" id="thumbnail" cssClass="formInput"
						onblur="checkThumbnail();" theme="simple" /> <label
					id="thumbnailMsg" class="errorMsg" style="display: none;"></label></td>
				<th style="text-align: center; width: 10%;">预览:</th>
				<td colspan="" style="text-align: left;"><img
					src="${softWareDTO.thumbnailPath }" width="30px" height="30px"></img></td>
			</tr>
			<tr>
				<th style="text-align: center; width: 10%;"><span
					class="Color5">* </span>软件简介:</th>
				<td style="text-align: left;" colspan="3"><s:textarea
						name="softWareDTO.summary" id="summary" cssClass="formTextarea"
						cols="45" rows="20" maxlength="255" cssStyle="resize:none;"
						onblur="checkSummary();" /> <label id="summaryMsg"
					class="errorMsg"
					style="display: none; position: relative; top: -10px;"></label></td>
			</tr>
			<tr>
				<th style="text-align: center; width: 10%;"><span
					class="Color5">* </span>软件描述:</th>
				<td style="text-align: left;" colspan="3">
					<!-- 加载编辑器的容器 --> <script id="editor" type="text/plain"
						style="width:900px;height:500px;">${softWareDTO.description}
														</script> <s:hidden id="description" name="softWareDTO.description" />
				</td>
			</tr>
		</table>
		<div class="formTableBottom">
			<s:if test="method=='addSave'">
				<my:permission key='sy-2101-01' value='软件添加'>
					<input id="submitInput" type="submit" class="formButton" value="添加"
						onclick="return check();" />
				</my:permission>
			</s:if>
			<s:else>
				<my:permission key='sy-2101-03' value='软件修改'>
					<input id="submitInput" type="submit" class="formButton" value="修改"
						onclick="return check();" />
				</my:permission>
			</s:else>
			<input type="button" class="formButton" value="返 回"
				onclick="go('software/software!listB')" />
		</div>
	</s:form>
	<script>
	<!--初始化ueditor-->
		var ue = UE.getEditor('editor');
	</script>
</body>
</html>