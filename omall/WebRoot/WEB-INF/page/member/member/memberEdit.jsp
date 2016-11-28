<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>会员管理</title>
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
	<script src="js/pubValiPattern.js"></script>
	<script src="js/base/member.js"></script>
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">

	var teleNoFlag = false;
	var emailFlag = false;
	//验证电话号码
  	function teleNoBlur(obj){
           var type = ["isNull","isMobile"];
           var errorMsg = ["手机号码不能为空!","手机号码格式错误!"];
           teleNoFlag = showErrorMsg(obj,type,errorMsg,"teleNoErrorMsg","teleNoWarnMsg");
           return teleNoFlag;
	  }
	function teleNoFocus(obj){
        showWarnMsg(obj,"填写正确手机号码!","teleNoErrorMsg","teleNoWarnMsg");
 	}  
	
	//验证电子邮箱
  	function emailBlur(obj){
           var type = ["isNull","isEmail"];
           var errorMsg = ["电子邮箱不能为空!","电子邮箱格式错误!"];
           emailFlag = showErrorMsg(obj,type,errorMsg,"emailErrorMsg","emailWarnMsg");
           return emailFlag;
	  }
	function emailFocus(obj){
        showWarnMsg(obj,"填写正确邮箱格式!","emailErrorMsg","emailWarnMsg");
 	} 
 	//保存时对所校验信息检查是否正确填写
 	function check(){
 	    var teleNo=document.getElementById("teleNo");
 	    var email=document.getElementById("email");
 	    	teleNoBlur(teleNo);
 	    	emailBlur(email);
 	    	if(teleNoFlag && emailFlag){
 	    	 return true;
 	    	}else{
 	    	alert("页面信息填写有误，请按照提示进行修改！");
        		return false;
 	    	}
 	}
 	//删除状态下的数据查看时使信息变成失去功能，即disable方法
 	$(document).ready(function(){
 	var method=document.getElementById("method");
 	  if(method.value=='checkUI'){
 	  setInputDisabled();
 	  $("#status").attr("disabled",true);
 	  $("#type").attr("disabled",true);
 	}
 	});
 	
 	   
	</script>
  </head>
  <body>

  <div class="Position">
		当前位置是：HOME &gt;&gt;会员基本信息 &gt;&gt; 会员管理
	</div>
	<input type="hidden" id="realName" value="${sessionScope.user_session.realName }" />
	<jsp:include page="/WEB-INF/page/member/member/memberSaleHelp.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/page/base/organs/organHelp.jsp"></jsp:include>
	<s:form action="member/member" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		
			<tr>
		        <th align="right"><span class="Color5">* </span>真实姓名：</th>
		 		<td>
		 			<s:textfield name="memberDTO.memRealName"  id="memRealName"  maxlength="80" cssClass="formInput"  theme="simple" disabled="true"/>
		 			<s:hidden name="memberDTO.memId"/>
		 		</td>
		 		<th align="right"><span class="Color5">* </span>手机号码：</th>
		        <td>
		        	<s:textfield name="memberDTO.teleNo" id="teleNo" maxlength="11" cssClass="formInput" theme="simple"  onfocus="teleNoFocus(this);" onblur="teleNoBlur(this);"
                                                                                                                         onkeyup = "replaceToNum(this);"/>
		        	<label id="teleNoErrorMsg" style="display: none;"></label>
			     <label id="teleNoWarnMsg" style="display: none"></label> 
		        </td>
			</tr>
			
		 	<tr>	
		 	    <th align="right"><span class="Color5">* </span>会员等级：</th>
		        <td>
		        	<s:textfield name="memberDTO.gradeName" id="gradeName"  cssClass="formInput" theme="simple" disabled="true"/>
		        </td> 	
		 		<th align="right">状         态：</th>
		        <td>
		        	<s:select name="memberDTO.status" id="status"  list="#request.statusValues" listKey="key" listValue="value"  cssClass="formInput" theme="simple"/>
		        </td>
		     </tr>
		     
		     <tr>
		        <th align="right">性         别：</th>
		        <td>
		        	<s:select name="memberDTO.sex" id="sex" list="#request.sexValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple" disabled="true"/>
		        </td>
		        <th align="right"><span class="Color5">* </span>证件编号：</th>
		        <td>
		        	<s:textfield name="memberDTO.personId" id="personId" maxlength="18" cssClass="formInput"  theme="simple" disabled="true" />
		        </td>
		   </tr>
		  <tr>
		      <th align="right"><span class="Color5">* </span>电子邮箱：</th>
		        <td><s:textfield name="memberDTO.email" id="email" maxlength="30" cssClass="formInput" theme="simple" onfocus="emailFocus(this);" onblur="emailBlur(this);" />
			        <label id="emailErrorMsg" style="display: none;"></label>
			        <label id="emailWarnMsg" style="display: none"></label></td>
		        <th align="right">注册时间：</th>
				   <td><s:textfield id="createTime" name="memberDTO.createTime" cssClass="formInput"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
								cssStyle="width:150px;" maxlength="20" theme="simple" disabled="true"/></td>
		   	</tr>
		</table>
		<s:if test="method=='checkUI'">
              <div class="List_tit">账户信息</div>
              <table width="80%" border="0" cellpadding="0" cellspacing="0" class="formTable">
              <tr>
		 		<th align="right" width="15%"><span class="Color5">* </span>可用积分：</th>
		 		<td colspan="3">
		 			<s:textfield name="memberDTO.availableAmt"  id="availableAmt" cssClass="formInput"  theme="simple"  disabled="true"/>
		 		</td>
		        </tr>
		        </table>
              <div class="List_tit">收货信息</div>
              <table width="96%" id="MembersTb" class="listTable" cellpadding="0" cellspacing="0">
                 <tr>
						<th width="8%"><a name="memName" >收货人</a></th>
			            <th width="10%"><a name="area" >所在地区</a></th>
			            <th width="5%"><a name="streetAddress" >接到地址</a></th>
			          	<th width="10%"><a name="zip" >邮编</a></th>
			           	<th width="10%"><a name="phoneNo" >电话/手机</a></th>
			     </tr>
					<c:forEach items="${memberDTO.receivingInfo}" var="receivingInfo">
						<tr>
							<td>${receivingInfo.memName }</td>
							<td>${receivingInfo.area }</td>
							<td>${receivingInfo.streetAddress}</td>
							<td>${receivingInfo.zip }</td>
							<td>${receivingInfo.phoneNo }</td>
						</tr>
					</c:forEach>
              </table>
        </s:if>
	 <div class="formTableBottom">
	 	<s:if test="method=='editSave'">
			<my:permission key='sy-1301-03' value='会员修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		 </s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('member/member!list')"/>
	 </div>
	 </s:form>
</body>
</html>