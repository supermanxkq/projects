<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="js/uploadify.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.uploadify.min.js"></script>
		<script type="text/javascript">
$(function() {
    $("#file_upload").uploadify({
    	 //'buttonImage' : '/uploadify/browse-btn.png',//按钮的图片
    	 'queueID'  : 'file_queue',//上传对列所在div编号
    	'fileTypeExts': '*.jpg;*.png;*.gif',//支持上传的文件格式
<%--    	'fileSizeLimit' : '500KB',//文件大小限制--%>
    	'folder' : 'UploadImgs/photo',//上传文件的目录
<%--    	'queueSizeLimit' : 10,//最大允许上传多少个文件--%>
    	'buttonText' : '请选择文件',//按钮文本名称
<%--    	'debug'    : true,//是否开启debug--%>
    	'method'   : 'post',//post方式提交
    	'fileObjName' : 'file_upload',//上传的文件的名称
    	'multi'    : true,//是否允许多文件上传
    	'progressData' : 'percentage',//上传显示百分比，或者是速度speed
        'auto'     : false,//是否允许自动上传
        'swf'      : 'js/uploadify.swf',//swf
		'uploader' : 'gallery/gallery!uploadImg',//请求的处理action
		'onUploadSuccess' : function(file, data, response) {
<%--            alert('文件：' + file.name + '上传成功！response:' + response + ':' + data);--%>
        },
        'onUploadStart' : function(file) {
<%--            alert('开始上传文件：' + file.name);--%>
        },
        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
<%--            alert('文件： ' + file.name + ' 上传失败！ ' + errorString);--%>
        },
        'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
            $('#progress').html(totalBytesUploaded + ' bytes uploaded of ' + totalBytesTotal + ' bytes.');
        },
        'onFallback' : function() {
            alert('Flash没有找到，请安装Flash。');
        }
    });
});
</script>

	</head>

	<body>
		<div id="file_queue" class="uploadify-queue"></div>
		<input type="file" name="file_upload" id="file_upload" />
		<a href="javascript:$('#file_upload').uploadify('upload','*')">上传文件</a>
		<a href="javascript:$('#file_upload').uploadify('cancel','*')">取消上传</a>
		<a href="javascript:$('#file_upload').uploadify('stop')">停止上传</a>
		<div id="progress"></div>
		<!--显示结果-->
	</body>
</html>