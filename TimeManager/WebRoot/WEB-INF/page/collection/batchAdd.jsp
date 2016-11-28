<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<link href="css/ordercancel.css" rel="stylesheet" type="text/css" />
	<link href="css/user.ordercancel.css" rel="stylesheet" type="text/css" />
	<link href="css/user.base.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
	/**对话框显示*/
	function orderCancelShow(orderId) {
		$("#optionArea").val("");
		/**判断是否有选中的，如果有选中的清空*/
		$(".thickdiv").show();
		$(".thickbox").show();
	}

	/**取消对话框隐藏*/
	function orderCancelHide() {
		$(".thickdiv").hide();
		$(".thickbox").hide();
	}

	/**批量添加任务*/
	function createTask() {
		var content = $.trim($("#optionArea").val());
		if (content != "") {
			//拼接
			var newContent = content.replace(/(\r)*\n/g, "<br/>").replace(/\s/g," ");;
			/**json数据传输*/
			var params = {
				"mainTaskDTO.tasksString" : newContent
			};
			var actionUrl = "collection/collection!batchAddSave";
			$.ajax( {
				url : actionUrl,
				data : params,
				dataType : "json",
				cache : false,
				type : "POST",
				error : function(textStatus, errorThrown) {
				},
				success : function(data, textStatus) {
					$(".thickdiv").hide();
					$(".thickbox").hide();
					query(1);
				}
			});
		} else {
			//链接失效
			return false;
		}
	}
</script>
</head>
<body>
	<!--取消订单提示框-->
	<div class="thickdiv" id="" style="display: none;">
	</div>
	<div class="thickbox" id=""
		style="left: 376.5px; top: 0px; width: 422px; height: 433px; display: none;">
		<div class="thickwrap" style="width: 420px;">
			<div class="thicktitle" id="" style="width: 400">
				<span>批量添加任务</span>
			</div>
			<div class="thickcon" id=""
				style="width: 400px; height: 400px; padding-left: 10px; padding-right: 10px;">
				<div class="cancle-box">
					<div class="form">
						<textarea name="" id="optionArea" class="area"
							style="margin: 0px 0px 0px -20px; width: 389px; height: 330px; border: 0px; resize: none; line-height: 18px; font-size: 14px;"
							></textarea>
					</div>
					<div class="op-btns" id="odo_cancel_step"
						style="position: absolute; top: 400px; left: 266px;">
						<a href="javascript:void(0)" onclick="createTask();"class="btn btn-11"> <s> </s>保存 </a>
						<a href="javascript:void(0)" onclick="orderCancelHide();" class="btn btn-11"> <s> </s>取消 </a>
					</div>
				</div>
			</div>
			<a href="javascript:void(0)" class="thickclose"
				onclick="orderCancelHide();">×</a>
		</div>
	</div>
	<!--取消订单提示框结束-->
</body>
</html:html>
