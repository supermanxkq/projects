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
	<title>发卡机构管理</title>
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
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">

	function goHome() {
			parent.leftFrame.location.href='<%=basePath%>system/index!firstleft';
		parent.main.location.href='<%=basePath%>system/index!right';
		}

	$(document).ready(function (){
	       var method = document.getElementById("method"); 
	        if(method.value=='checkDetail'){
	             setInputDisabled();
	             $("#status").attr("disabled","disabled");
	             $("#isTrustFunds").attr("disabled","disabled"); 
	             $("#areaId").attr("disabled","disabled");
	             $("#isServiceFee").attr("disabled","disabled");
	             $("#emergConTel").attr("disabled","disabled");
	             $("#description").attr("disabled","disabled");
	            }
	        });
    
	var orgNameFlag = false;
	var conPerFlag = false;
	var mobileFlag = false;
	var teleNoFlag = false;

	
    
	
	function organNameBlur(obj){
		     var flag1 = false;
		     var flag2 = false;
             var type = ["isNull"];
             var errorMsg = ["机构名称不能为空!"];
             //检验机构名称格式
             flag1 = showErrorMsg(obj,type,errorMsg,"errorMsg","warnMsg");
             //机构名称长度
             flag2 = checkLenMsg(obj,"长度不能大于30个汉字","errorMsg","",60)
             if(flag1&&flag2){
                  orgNameFlag = true;
              }
             return orgNameFlag;
		}
	function organNameFocus(obj){
		   showWarnMsg(obj,"可填写汉字、字母以及数字!","errorMsg","warnMsg");
		}
	function conPerBlur(obj){
           var type = ["isNull","isRealName"];
           var errorMsg = ["联系人不能为空!","联系人格式错误!"];
           conPerFlag = showErrorMsg(obj,type,errorMsg,"conPerErrorMsg","conPerWarnMsg");
           return conPerFlag;
		}
    function conPerFocus(obj){
          showWarnMsg(obj,"填写真实姓名!","conPerErrorMsg","conPerWarnMsg");
        }
	function conMobileBlur(obj){
		var type = ["isNull","isMobile"];
		var errorMsg = ["手机号码不能为空!","手机号码格式错误!"];
		mobileFlag = showErrorMsg(obj,type,errorMsg,"conPerTeleNoErrorMsg","conPerTeleNoWarnMsg");
		return mobileFlag;
	  }
	function conMobileFocus(obj){
             showWarnMsg(obj,"填写正确手机号码!","conPerTeleNoErrorMsg","conPerTeleNoWarnMsg");
	  }
  function teleNoBlur(obj){
           var type = ["isNull","isTel"];
           var errorMsg = ["固话号码不能为空!","固话号码格式错误!"];
           teleNoFlag = showErrorMsg(obj,type,errorMsg,"teleNoErrorMsg","teleNoWarnMsg");
           return teleNoFlag;
	  }
	function teleNoFocus(obj){
        showWarnMsg(obj,"填写正确固话号码!","teleNoErrorMsg","teleNoWarnMsg");
 }    
		//修改方法
		var check = function() {
			var emergConTel = $("#emergConTel").val();
			var description = $("#description").val();
			
		//	if(${user_session.userLevel}==0){
		//		if (parentId<0){
		//	    	alert("请选择上级发卡机构!");	 
		//	    	$("#parentId").focus();
		//	    	return false;
		//		}
		//	}
		       var organName = getEle("name");
               organNameFlag = organNameBlur(organName);
               var conPer = getEle("conPerName");
               conPerFlag = conPerBlur(conPer);
               var conMobile = getEle("conPerTeleNo");
               mobileFlag = conMobileBlur(conMobile);
               var teleNo = getEle("teleNo"); 
               teleNoFlag = teleNoBlur(teleNo);
               var zip = $("#zip").val();
	              
			if(emergConTel.length>255){
                 alert("紧急联系人输入字符过长，请修改..");
                 return false;
				}
			if(description.length>255){
                  alert("机构描述输入字符过长，请重新输入!");
                  return false;
				}
			if(zip==""){
				return true;
				}
			else if(zip!=""){			
				if(!(zip.length>=3&&zip.length<=8)){
					  alert("邮编不合法，请重新输入!");
	                  return false;
					}
				}
			if(!(orgNameFlag&&conPerFlag&&mobileFlag&&teleNoFlag)){
                  alert("请按照提示信息正确填写.(*部分为必填项!)");
	             return false;
				}
			
		}
		
       //关闭输入法 
		function colseImeMode(obj){
			 obj.style.css = ("ime-mode","disabled");
			}
	</script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 发卡机构管理
	</div>
	<s:form action="base/organs" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>
	<s:if test="#session.user_session.userLevel==0">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
		  		<th align="right" width="20%">机构编号：</th>
		        <td width="30%"><s:property value="organsDTO.organId"/><s:hidden name="organsDTO.organId" id="organId"/></td>
		        <th align="right" width="20%"><span class="Color5">* </span>机构名称：</th>
		        <td width="30%"><s:textfield name="organsDTO.name" id="name" onkeyup = "allowEnCnNu(this)" maxlength="60" cssClass="formInput" onfocus="organNameFocus(this);" onblur="organNameBlur(this);" theme="simple"/><label id="warnMsg" style="display: none;"></label> <label id="errorMsg" style="display: none;"></label></td>
			</tr>
		 	<tr>
		 		<th align="right">上级发卡机构：</th>
		 		<td>
		 		<s:property value="顶级发卡机构"/>顶级发卡机构<s:hidden name="organsDTO.parentId" value="0"></s:hidden>
		 		<%--<s:select name="organsDTO.parentId" id="parentId" list="#request.preOrganValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>--%>
		 		</td>
		      	
		      	<th align="right">状 态：</th>
		        <td><s:select name="organsDTO.status" id="status" list="#request.statusValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
		  	</tr>
		  	<tr>
		  		<th align="right">资金是否托管：</th>
		        <td><s:select name="organsDTO.trustFundSign" id="isTrustFunds" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
		      	<th align="right">是否年服务费：</th>
		        <td><s:select name="organsDTO.yearServSign" id="isServiceFee" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
		 	</tr>
		  	<tr>
		      	<th align="right"><span class="Color5">* </span>联系人：</th>
		        <td><s:textfield name="organsDTO.conPerName"  id="conPerName" maxlength="20" onblur="conPerBlur(this);" onfocus="conPerFocus(this);" cssClass="formInput" theme="simple"/> <label id="conPerErrorMsg" style="display: none;"></label> <label id="conPerWarnMsg" style="display: none;"></label></td>
		      	<th align="right"><span class="Color5">* </span>手机号码：</th>
		        <td><s:textfield name="organsDTO.conPerTeleNo" id="conPerTeleNo" onkeyup = "replaceToNum(this);"  onblur="conMobileBlur(this);"  maxlength="11" cssClass="formInput" theme="simple"/><label id="conPerTeleNoErrorMsg" style="display: none;"></label> <label id="conPerTeleNoWarnMsg" style="display: none;"></label></td>
		   	</tr>
		  	<tr>
		  		<th align="right"><span class="Color5">* </span>固定电话：</th>
		        <td><s:textfield name="organsDTO.teleNo" onfocus = "teleNoFocus(this);" onblur="teleNoBlur(this);" onkeyup = "replaceToNum(this);" id="teleNo" maxlength="13" cssClass="formInput" theme="simple"/><label id="teleNoErrorMsg" style="display: none;"></label> <label id="teleNoWarnMsg" style="display: none;"></label></td>
		      	<th align="right">所在地区：</th>
		        <td>
				    <s:select name="organsDTO.areaId" id="areaId" list="#request.areaValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
				</td>
		 	</tr>
		   	<tr>
		      	<th align="right">地 址：</th>
		        <td><s:textfield name="organsDTO.address" id="address" maxlength="100" cssClass="formInput" theme="simple"/></td>
		      	<th align="right">邮政编码：</th>
		        <td><s:textfield name="organsDTO.zip" id="zip"  onkeypress="return onlyNum(this);" onkeyup="replaceToNum(this);" maxlength="6" cssClass="formInput" theme="simple"/></td>
		  	</tr>
		  	<tr>
		      	<th align="right">开户银行：</th>
		        <td><s:textfield name="organsDTO.bankName" id="bankName" maxlength="20" cssClass="formInput" theme="simple" onkeyup = "allowEnCnNu(this);"/></td>
		        <th align="right">开户账号：</th>
		        <td><s:textfield name="organsDTO.bankAcctNo" id="bankAccountNo" maxlength="20" cssClass="formInput" theme="simple" onkeyup= "replaceToNum(this);"/></td>
		   	</tr>
		   	<tr>
		      	<th align="right">开户名称：</th>
		        <td ><s:textfield name="organsDTO.bankAccName" id="bankUser" maxlength="20" cssClass="formInput" theme="simple"/></td>
		       <th align="right">上次结算时间：</th>
		        <td colspan="3"><s:date format="yyyy-MM-dd HH:mm:ss" name="organsDTO.lastSettTime" /></td>
		  	</tr>
		  	<tr>
		      	<th align="right">结算周期：</th>
		        <td colspan="3"><s:textfield  onkeypress="return onlyNum(this);" maxLength="3" onkeyup="replaceToNum(this);" onfocus="colseImeMode(this);" name="organsDTO.settPeriod" id="settlementPeriod"  cssClass="formInput" cssStyle="width: 50px;" theme="simple"/> 天</td>
		      	
		      	
		  	</tr><tr>
		  		<%--<th align="right">系统类型：</th>
		        <td><s:select name="organsDTO.sysType" id="sysType" list="#request.sysTypeValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
		        --%><th align="right">紧急联系人电话：</th>
		        <td colspan="3"><s:textarea name="organsDTO.emContPhone" id="emergConTel"  cssClass="formTextarea" cols="80" rows="5"/><span class="Color3">填3-5个，以","号隔开</span></td>
		  	</tr>
		   	<tr>
		      	<th align="right">机构描述：</th>
		        <td colspan="3"><s:textarea name="organsDTO.orgDesc" id="description" cssClass="formTextarea" cols="80" rows="5" /></td>
		   	</tr>
		</table>
	</s:if>
	<s:if test="#session.user_session.userLevel==1">
		
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
		  		<th align="right" width="20%">机构编号：</th>
		        <td width="30%"><s:property value="organsDTO.organId"/><s:hidden name="organsDTO.organId" id="organId"/></td>
		        <th align="right" width="20%"><span class="Color5">* </span>机构名称：</th>
		        <td width="30%"><s:property value="organsDTO.name"/><s:hidden name="organsDTO.name" id="name"/></td>
			</tr>
		 	<tr>
		 		<th align="right">上级发卡机构：</th>
		 		<td><s:select name="organsDTO.parentId" id="parentId" list="#request.preOrganValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple" disabled="true"/></td>
		      	<th align="right">状 态：</th>
		        <td><s:select name="organsDTO.status" id="status" list="#request.statusValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple" disabled="true"/></td>
		  	</tr>
		  	<tr>
		  		<th align="right">资金是否托管：</th>
		        <td><s:select name="organsDTO.trustFundSign" id="isTrustFunds" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple" disabled="true"/></td>
		      	<th align="right">是否年服务费：</th>
		        <td><s:select name="organsDTO.yearServSign" id="isServiceFee" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple" disabled="true"/></td>
		 	</tr>
		 	
		 	
		 	
		 	
		  	<tr>
		      	<th align="right"><span class="Color5">* </span>联系人：</th>
		      	<td><s:textfield name="organsDTO.conPerName"  id="conPerName" maxlength="20" onblur="conPerBlur(this);" onfocus="conPerFocus(this);" cssClass="formInput" theme="simple"/> <label id="conPerErrorMsg" style="display: none;"></label> <label id="conPerWarnMsg" style="display: none;"></label></td>
		        <th align="right"><span class="Color5">* </span>联系电话：</th>
		        <td><s:textfield name="organsDTO.conPerTeleNo" id="conPerTeleNo" onkeyup = "replaceToNum(this);"  onblur="conMobileBlur(this);"  maxlength="11" cssClass="formInput" theme="simple"/><label id="conPerTeleNoErrorMsg" style="display: none;"></label> <label id="conPerTeleNoWarnMsg" style="display: none;"></label></td>
		   	</tr>
		  	<tr>
		  		<th align="right">电话号码：</th>
		        <td><s:textfield name="organsDTO.teleNo" onfocus = "teleNoFocus(this);" onblur="teleNoBlur(this);" onkeyup = "replaceToNum(this);" id="teleNo" maxlength="13" cssClass="formInput" theme="simple"/><label id="teleNoErrorMsg" style="display: none;"></label> <label id="teleNoWarnMsg" style="display: none;"></label></td>
		      	<th align="right">所在地区：</th>
		        <td>
				    <s:select name="organsDTO.areaId" id="areaId" list="#request.areaValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple" disabled="true"/>
				</td>
		 	</tr>
		   	<tr>
		      	<th align="right">地 址：</th>
		        <td><s:textfield name="organsDTO.address" id="address" maxlength="20" cssClass="formInput" theme="simple"/></td>
		      	<th align="right">邮政编码：</th>
		        <td><s:textfield name="organsDTO.zip" id="zip" onkeypress="return onlyNum(this);" onkeyup="replaceToNum(this);" maxlength="6" cssClass="formInput" theme="simple"/></td>
		  	</tr>
		  	<tr>
		      	<th align="right"><span class="Color5">* </span>结算周期：</th>
		        <td><s:property value="organsDTO.settPeriod"/> 天<s:hidden name="organsDTO.settPeriod" id="settlementPeriod"/></td>
		      	
		  	</tr>
		  	<tr>
		      	<th align="right">开户银行：</th>
		        <td><s:textfield name="organsDTO.bankName" id="bankName" maxlength="20" cssClass="formInput" theme="simple"/></td>
		        <th align="right">开户账号：</th>
		        <td><s:textfield name="organsDTO.bankAcctNo" id="bankAccountNo" maxlength="20" cssClass="formInput" theme="simple"/></td>
		   	</tr>
		   	<tr>
		      	<th align="right">开户名称：</th>
		        <td><s:textfield name="organsDTO.bankUser" id="bankUser" maxlength="20" cssClass="formInput" theme="simple"/></td>
		        <th align="right">上次结算时间：</th>
		        <td><s:property value="organsDTO.lastSettTime"/></td>
		  	</tr>
		  	<tr>
		  		<%--<th align="right">系统类型：</th>
		        <td><s:select name="organsDTO.sysType" id="sysType" list="#request.sysTypeValues" listKey="key" listValue="value" cssClass="formSelect" disabled="true" theme="simple"/></td>
		  	--%>
		  	<th align="right">紧急联系人电话：</th>
		        <td colspan="3"><s:textarea name="organsDTO.emContPhone" id="emergConTel"  cssClass="formTextarea" cols="80" rows="5"/><span class="Color3">填3-5个，以","号隔开</span></td>
		  	</tr>
		   	<tr>
		      	<th align="right">机构描述：</th>
		        <td colspan="3"><s:textarea name="organsDTO.orgDesc" id="description" cssClass="formTextarea" cols="45" rows="5" /></td>
		   	</tr>
		</table>
	</s:if>
	
	 <div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1101-02' value='发卡机构添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-1101-03' value='发卡机构修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		<s:if test="#session.user_session.userLevel==0">
		   <input type="button" class="formButton" value="返 回" onclick="go('base/organs!list')"/>
		</s:if>
		<s:else>
		  <input type="button" class="formButton" value="返 回" onclick="goHome()"/>
		</s:else>
     </div>
	 </s:form>
</body>
</html>