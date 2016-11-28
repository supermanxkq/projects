<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>添加商品类别</title>
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
		<script src="js/datepicker/WdatePicker.js"></script>
		<link href="resources/css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/common.js"></script>
		<script src="js/pubValiPattern.js"></script>
		<script src="js/pubValidate.js"></script>
		<script type="text/javascript">
		var goodTypeNameFlag=false;
		function checkGoodType(){
			var name=$("#name").val();
			if(name==""){
				$("#nameMsg").html("");
				$("#nameMsg").html("请输入商品类别名称!");
				$("#nameMsg").show();
				goodTypeNameFlag=false;
			}else{
				$("#nameMsg").hide();
				goodTypeNameFlag=true;
			}
		}
		
		function check(){
			checkGoodType();
			if(goodTypeNameFlag){
				return true;
			}else{
				return false;
			}
		}
</script>
	</head>
	<body>
		<div class="Position">
			当前位置是：<a href="basic/type">商品类别管理</a> &gt;&gt;添加类别
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
			<s:form action="basic/type" method="post" theme="simple" onsubmit="return check();">
				<s:hidden name="method" id="method" />
				<s:hidden name="goodsType.id" id="id" />
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formTable">
					<tr>
						<th style="text-align: center; width: 10%;">
							<span class="Color5">* </span>类别名称:
						</th>
						<td style="text-align: left;">
							<s:textfield name="goodsType.name" id="name" maxlength="20"
								cssClass="formInput" theme="simple" onblur="checkGoodType()"/>
								<label id="nameMsg" class="errorMsg"  
								style="display: none;"></label>
						</td>
						<th style="text-align: center; width: 10%;">
							状态:
						</th>
						<td style="text-align: left;">
							<s:select name="goodsType.status" id="status" 
								list="#session.goodsTypeStatus" listKey="key" listValue="value"
								cssStyle="width:153.5px;" cssClass="formSelect" theme="simple"/>
						</td>
					</tr>
				</table>
				<div class="formTableBottom">
				<s:if test="method=='addSave'">
					<my:permission key='sy-5802-01' value='商品类别添加'>
						<input id="submitInput" type="submit" class="formButton"
							value="添加"  />
				</my:permission>
				</s:if>
				<s:else>
					<my:permission key='sy-5802-02' value='商品类别修改'>
						<input id="submitInput" type="submit" class="formButton"
							value="修改" />
					</my:permission>
				</s:else>
				<input type="button" class="formButton" value="返 回"
					onclick="go('basic/type!list')" />
			</div>
			</s:form>
	</body>
</html>