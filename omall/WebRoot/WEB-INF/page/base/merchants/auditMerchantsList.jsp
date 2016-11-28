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
	<title>商户管理</title>
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
	<script src="js/pubValidate.js"></script>
   <script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
		
		//查询方法
		function query(page) {
			var cardNo = $.trim($("#cardNo").val());
			var email = $.trim($("#email").val());
			var status = $.trim($("#status").val());
           
			var params = {
				"merStoreApplyDTO.cardNo" : cardNo,
		        "merStoreApplyDTO.email" : email,
		        "merStoreApplyDTO.status" : status,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "merStoreApplyDTO.page" : page
		    }; 
		   ajaxData("base/auditMerchants!jsonPageList",params);
		}
		
		function  modifyStatus (object){
				var modifyValue=""
				var check=$('input[type=checkbox][name=checkbox]:checked');
				  check.each(function(i){        //循环拼装被选中项的值
    				modifyValue=modifyValue+$(this).val()+"|";
   				  });
   				  if (modifyValue!=null&&modifyValue!=""){
   				  		if(object.value=="批量审核"){
   				  			modify(modifyValue,'1');
   				  		}
   				  		if(object.value=="批量拒绝"){
   				  			modify(modifyValue,'2');
   				  		}
   				  }else{
   				  			return false;
   				  }
   				  
		}
		
		function  checkAll(obj){
		var check=$('input[type=checkbox][name=checkbox]');
				  check.each(function(i){        //循环拼装被选中项的值
    				$(this).attr('checked',obj.checked);
   				  });
		}
		
		//批量修改的数据 
		function  modify(modfiyValue,status){
			var params = {
			        "modfiyValue" : modfiyValue,
			        "status":status
			    };
			    var actionUrl = "base/auditMerchants!MerApplyAction";
			    $.ajax( {
			        url : actionUrl,
			        data : params,
			        dataType : "json",
			        cache : false,
			        type : "POST",
			        error : function(textStatus, errorThrown) {
			          	alert("系统ajax交互错误!");
			        },
			        success : function(data, textStatus) {
			        	if(data=='success'){
			        	  alert("操作成功 ")		
			        	  window.location.reload();
			        	  }
			        }
			    });		
				
		
		
		}
		
	</script> 
</head>
<body onload="query(${merStoreApplyDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 商铺入驻审核
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<div class="search">		
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            
			    <td>身份证号:</td>
				<td><s:textfield id="cardNo" name="merStoreApplyDTO.cardNo" cssClass="formInput" onkeyup = "allowEnCnNu(this);" maxlength="20" theme="simple"/></td>
				<td>邮箱:</td>
				<td><s:textfield id="email" name="merStoreApplyDTO.email" cssClass="formInput"  onkeyup = "notAllowNull(this);"   maxlength="60" theme="simple"/></td>
	        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
	        	<td><input type="button" class="formButton"   onclick="modifyStatus(this);" value="批量审核" /></td>
	        	<td><input type="button" class="formButton"   onclick="modifyStatus(this);" value="批量拒绝" /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%"><input type="checkbox"  name="allcheckbox" onclick="checkAll(this)">全选</th>
			<th width="3%">序号</th>
			<th width="8%"><a name="cardNo" class="sort">身份证号</a></th>
			<th width="10%"><a name="name" class="sort">负责人</a></th>
			<th width="10%"><a name="email" class="sort">邮箱</a></th>
			<th width="5%"><a name="telephone" class="sort">营业电话</a></th>
			<th width="8%"><a name="status" class="sort">状态</a></th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>