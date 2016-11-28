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
	<title>商户评分模型</title>
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
	<script src="js/common.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script src="js/pubValidate.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script type="text/javascript">

		//查询方法
		function query(page) {

			
			var moName = $.trim($("#moName").val());
			var minSco = $.trim($("#minSco").val());
            var maxSco = $.trim($("#maxSco").val());
  
			var params = {
				"merScoMoDTO.moName" : moName,
		        "merScoMoDTO.minSco" : minSco,
		        "merScoMoDTO.maxSco":maxSco,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "merScoMoDTO.page" : page
		    }; 
		 
		   ajaxData("base/merscomo!jsonPageList",params);
		}

		 
		
		
		
 
	</script> 
	
	
</head>
<body onload="query(${merScoMoDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 商户评分模型
	</div>
	
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
	  <div class="Fl">
			<my:permission key='sy-1202-02' value='商户评分模型添加'>
				<input type="button" class="formButton" value="添加" onclick="go('base/merscomo!addUI')" />
			</my:permission>
		</div>	
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            
			    <td>商户评分模型名称:</td>
				<td>
				<s:textfield id="moName"   maxlength="6"   cssClass="formInput" theme="simple"  />
				</td>
				<td>商户分数范围:</td>
				<td>
			    <s:textfield  id="minSco"  cssStyle="width:62px"     maxlength="6"  
						     onkeyup="replaceToNum(this);"
						 	cssClass="formInput" theme="simple"  />
							至
							
							<s:textfield  id="maxSco" cssStyle="width:62px"  onkeyup="replaceToNum(this);"
							   maxlength="6"  
							cssClass="formInput" theme="simple"  />
							
							<label id="Scoerr1" style="display:none" class="errorMsg"></label> 
							<label id="Scoerr" style="display:none" class="errorMsg"></label>
							
							
				</td>			
	        	
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询"   /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="8%"><a name="merScoMoId" class="sort">商户评分模型序号</a></th>
			<th width="10%"><a name="moName" class="sort">商户评分模型名称</a></th>
			<th width="5%"><a name="minSco" class="sort">最小分数</a></th>
			<th width="5%"><a name="minSco" class="sort">最大分数</a></th>
			
			<th width="15%"><a name="showPic" class="sort">显示图片</a></th>
			<th width="5%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fl">
			<my:permission key='sy-1202-02' value='商户评分模型添加'>
				<input type="button" class="formButton" value="添加" onclick="go('base/merscomo!addUI')" />
			</my:permission>
		</div>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>