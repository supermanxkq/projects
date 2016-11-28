<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<script src="http://cdn.bootcss.com/jquery/3.0.0-beta1/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$.ajax({
			url : 'http://wx.lrcyz.com/api/article/query.html',
			data : {
				id : 1,
				name : 'qq'
			},
			dataType : 'jsonp',
			jsonpCallback : 'jsonObject', //服务器封装的方法  形如：jsonObject(data,data,data);
			success : function(data) {
				console.log("成功")
				console.log(data)
				if (data.list) {
					var l = data.list;
					for (var i = 0; i < l.length; i++) {
						var obj = l[i];
						$('.test').append("<div> " + obj.id + " </div>")
					}

				}
				//$("#id").append("<div> 1 </div>")
			},

			error : function(data) {
				console.log(data)
			}

		});

	});
</script>
</head>

<body>

	<div class="test"></div>
</body>
</html>