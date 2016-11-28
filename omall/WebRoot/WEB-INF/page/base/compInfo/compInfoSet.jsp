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
	<title>企业信息管理</title>
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
		var companyNameFlag = false;
		var comTeleFlag = false;
		var compNumFlag=false;         
		var comConPerFlag = false;
		var comConPerTeleFlag=false;
		var comEmailFlag=false;
		var descrFlag=false;
		
		
	function goHome() {
			parent.leftFrame.location.href='<%=basePath%>system/index!firstleft';
		parent.main.location.href='<%=basePath%>system/index!right';
		}

	
	//验证企业邮箱
    function comEmailBlur(obj){
           var type = ["isNull","isEmail"];
           var errorMsg = ["邮箱不能为空!","邮箱格式错误!"];
           comEmailFlag = showErrorMsg(obj,type,errorMsg,"comEmailErrorMsg","comEmailWarnMsg");
           return comEmailFlag;
		}
    function comEmailFocus(obj){ 
          showWarnMsg(obj,"填写正确邮箱！!","comEmailErrorMsg","comEmailWarnMsg");
        }
        
	//验证企业名称
	function  companyNameBlur(obj){
		     var flag1 = false;
		     var flag2 = false;
             var type = ["isNull"];
             var errorMsg = ["企业名称不能为空!"];
             //检验企业名称格式
             flag1 = showErrorMsg(obj,type,errorMsg,"errorMsg","warnMsg");
             //企业名称长度
             flag2 = checkLenMsg(obj,"长度不能大于30个汉字","errorMsg","",60)
             if(flag1&&flag2){
                  companyNameFlag = true;
              }
             return companyNameFlag;
		}
	function companyNameFocus(obj){
		   showWarnMsg(obj,"可填写汉字、字母以及数字!","errorMsg","warnMsg");
		}
		
	//验证企业联系人
	function comConPerBlur(obj){
           var type = ["isNull","isRealName"];
           var errorMsg = ["联系人不能为空!","联系人格式错误!"];
           comConPerFlag = showErrorMsg(obj,type,errorMsg,"comConPerErrorMsg","comConPerWarnMsg");
           return comConPerFlag;
		}
    function comConPerFocus(obj){
          showWarnMsg(obj,"填写真实姓名!","comConPerErrorMsg","comConPerWarnMsg");
        }
        
     //验证联系人电话
	function comPerTeleBlur(obj){
		var type = ["isNull","isMobile"];
		var errorMsg = ["手机号码不能为空!","手机号码格式错误!"]
		comConPerTeleFlag = showErrorMsg(obj,type,errorMsg,"comConPerTeleErrorMsg","comConPerTeleWarnMsg");
		return comConPerTeleFlag;
	  }
	function comPerTeleFocus(obj){
             showWarnMsg(obj,"填写正确手机号码!","comPerTeleErrorMsg","comPerTeleWarnMsg");
	  }
	  
	  //验证企业电话
  function comTeleBlur(obj){
           var type = ["isNull","isTel"];
           var errorMsg = ["固话号码不能为空!","固话号码格式错误!"];
           comTeleFlag = showErrorMsg(obj,type,errorMsg,"comTeleErrorMsg","comTeleWarnMsg");
           return comTeleFlag;
	  }
	function comTeleFocus(obj){
        showWarnMsg(obj,"填写正确固话号码!","comTeleErrorMsg","comTeleWarnMsg");
 	}    
 	
 	//验证企业人数
 	function compNumFocus(obj){
		$("#compNumErrorMsg").hide();
		$("#compNumWarnMsg").show();
		$("#compNumWarnMsg").text("请输入人数！");
		
	}
	
	function compNumBlur(obj){
		var count=$.trim(obj.value);
		if(count==null||count.length==0){
		 	$("#compNumWarnMsg").hide();
		 	$("#compNumErrorMsg").show();
		 	$("#compNumErrorMsg").text("人数不能为空！");
		 	compNumFlag=false;
		 }else if(count<0){
		 	$("#compNumWarnMsg").hide();
		 	$("#compNumErrorMsg").show();
		 	$("#compNumErrorMsg").text("人数必须为正数！");
		 	compNumFlag=false;
		 }else if(count>=999999){
		    $("#compNumWarnMsg").hide();
		 	$("#compNumErrorMsg").show();
		 	$("#compNumErrorMsg").text("人数输入值过大！");
		 	compNumFlag=false;
		 }else{
		 	$("#compNumErrorMsg").hide();
		 	$("#compNumWarnMsg").hide();
		 	compNumFlag=true;
		 }
		return compNumFlag;
	}
	
	function replaceToNum(obj){
		var count=$.trim(obj.value);
		 if(isNaN(obj.value)){
			 	obj.value = obj.value.replace(/[^\d]/g,"");  //清除“数字”和“.”以外的字符
			 	obj.value = obj.value.replace(/[.]/g,"");			 	
		 }
	}
	
	//修改方法	
 	function temp(){
               companyNameFlag = companyNameBlur(companyName);
               var comTele = getEle("comTele"); 
               comTeleFlag = comTeleBlur(comTele);
               var compNum = getEle("compNum"); 
               compNumFlag = comTeleBlur(compNum);
               var comConPer = getEle("comConPer");
               comConPerFlag = comConPerBlur(comConPer);
               var comConPerTele=getEle("comConPerTele");
               comConPerTeleFlag=comConPerTeleBlur(comConPerTele);
               var comEmail = getEle("comEmail");
               comEmailFlag = comEmailBlur(comEmail);
               var compAddress=getEle("compAddress");
               compAddressFlag=compAddressBlur(compAddress);
               var businLicen=getEle("businLicen");
               businLicenFlag=businLicenBlur(businLicen);
               var status=getEle("status");
               statusFlag=statusBlur(status);
              
              
				//(companyNameFlag&&comConPerFlag&&comTeleFlag&&compNumFlag&&comConPerTeleFlag&&comEmailFlag&&compAddressFlag)
			if(!(companyNameFlag&&comConPerFlag&&comTeleFlag&&compNumFlag&&comConPerTeleFlag&&comEmailFlag)){
                  alert("请按照提示信息正确填写.(*部分为必填项!)");
	             return false;
				}
 	}
	//验证描述
	var validateDescr = function(){
			
			var textareaLength=$("#description").val().length; 
			
			if(textareaLength>=255){
				
				$("#descrMsg").show();
				$("#descrMsg").text("255以内字符！");				
				descrFlag = false;
				//alert("输入的字数过多，请重新输入！！！")
			} else{
				
				$("#descrMsg").hide();
				
				descrFlag = true;
			}
			
		}
	
	function check(){
		comEmailBlur(document.getElementById("comEmail"));
		companyNameBlur(document.getElementById("companyName"));
		comConPerBlur(document.getElementById("payMenName"));
	comPerTeleBlur(document.getElementById("telNo"));
		comTeleBlur(document.getElementById("comTele"));
		compNumBlur(document.getElementById("compNum"));
		validateDescr();
		return companyNameFlag&&comTeleFlag&&compNumFlag&&comConPerFlag&&comConPerTeleFlag&&comEmailFlag&&descrFlag;
		//return companyNameFlag&&comTeleFlag&&compNumFlag&&comEmailFlag&&descrFlag;
	}
	
	
	$(function(){
		
		var status=$("#status").val();
		if(status==9){
			$("#comTele").attr("disabled",true);
			$("#compNum").attr("disabled",true);
			$("#payMenName").attr("disabled",true);
			$("#telNo").attr("disabled",true);
			$("#companyName").attr("disabled",true);
			$("#comEmail").attr("disabled",true);
			$("#compAddress").attr("disabled",true);
			$("#businLicen").attr("disabled",true);
			$("#description").attr("disabled",true);
			$("#status").attr("disabled",true);
			$("#submitInput").remove();
		}
	});
	</script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 企业信息管理
	</div>
	<!-- 
		document.getElementById('submitInput').disabled = true;return true;
	 -->
	<jsp:include page="/WEB-INF/page/member/member/memberHelp.jsp"></jsp:include>
	<s:form action="base/compInfo" method="post" onsubmit="return check();" theme="simple">
	<s:hidden name="method" id="method"/>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
		        <th align="right" width="20%"><span class="Color5">* </span>企业名称：</th>
		        <td width="30%"><s:textfield name="companyInfoDTO.companyName" id="companyName" onkeyup = "allowEnCnNu(this)" maxlength="60" cssClass="formInput" onfocus="companyNameFocus(this);" onblur="companyNameBlur(this);" theme="simple"/><label id="warnMsg" style="display: none;"></label> <label id="errorMsg" style="display: none;"></label></td>
			</tr>
			<tr>
		      	<th align="right"><span class="Color5">* </span>企业电话：</th>
		        <td><s:textfield name="companyInfoDTO.comTele"  id="comTele" maxlength="20" onblur="comTeleBlur(this);" onfocus="comTeleFocus(this);" cssClass="formInput" theme="simple"/> <label id="comTeleErrorMsg" style="display: none;"></label> <label id="comTeleWarnMsg" style="display: none;"></label></td>
		      	<th align="right"><span class="Color5">* </span>企业人数：</th>
		        <td><s:textfield name="companyInfoDTO.compNum" id="compNum" onfocus="compNumFocus(this);" onkeyup = "replaceToNum(this);"  onblur="compNumBlur(this);"  maxlength="11" cssClass="formInput" theme="simple"/>
			       
			       	<label id="compNumErrorMsg" style="display: none;" class="errorMsg"></label> 
			        <label id="compNumWarnMsg" style="display: none;"  class="warnMsg"></label>
		        </td>
		   	</tr>
		   	<tr>
		      	<th align="right"><span class="Color5">* </span>企业联系人：</th>
		      	<td>
			        <s:textfield name="companyInfoDTO.comConPer" id="payMenName" readonly="true" cssClass="formInput" theme="simple" onblur="setPayMenFlag();"/>
			        <s:hidden name="companyInfoDTO.companyId" id="companyId"/>
			        <img alt="查找会员" src="images/search.gif" style="cursor:pointer;" onclick="ajaxParentMemId();"/>
			        <span class="Color5"><label id="payMenMsg" ></label></span> 
		 			<label id="payMenError" ></label>
			        </td>
		      	<th align="right"><span class="Color5">* </span>联系人电话：</th>
		        <td><s:textfield name="companyInfoDTO.comConPerTele" id="telNo" readonly="true" onkeyup = "replaceToNum(this);"  onblur="comPerTeleBlur(this);"  maxlength="11" cssClass="formInput" theme="simple"/>
		        <label id="comConPerTeleErrorMsg" style="display: none;"></label> 
		        <label id="comConPerTeleWarnMsg" style="display: none;"></label>
		        
		        </td>
		   	</tr>
		   	<tr>
		      	<th align="right"><span class="Color5">* </span>企业邮箱：</th>    
		        <td><s:textfield name="companyInfoDTO.comEmail"  id="comEmail" maxlength="40" onblur="comEmailBlur(this);" onfocus="comEmailFocus(this);" cssClass="formInput" theme="simple"/>
		         <label id="comEmailErrorMsg" style="display: none;">
		         </label>
		          <label id="comEmailWarnMsg" style="display: none;">
		          </label>
		         </td>
		      	<th align="right">企业地址：</th>
		        <td><s:textfield name="companyInfoDTO.compAddress"  id="compAddress" maxlength="20"  cssClass="formInput" theme="simple"/> <label id="compAddressErrorMsg" style="display: none;"></label> <label id="compAddressWarnMsg" style="display: none;"></label></td>
		   	</tr>
		   	<tr>
		 		<th align="right">营业执照：</th>
		 		<td><s:textfield name="companyInfoDTO.businLicen" id="businLicen" maxlength="11" cssClass="formInput" theme="simple"/></td>
		      	<th align="right">状 态：</th>
		        <td><s:select name="companyInfoDTO.status" id="status" list="#request.status" listKey="key" listValue="value" cssClass="formSelect" theme="simple" /></td>
		  	</tr>
		   	<tr>
		      	<th align="right">企业描述：</th>
		        <td colspan="3"><s:textarea name="companyInfoDTO.descr" id="description" maxlength="255" cssClass="formTextarea" cols="80" rows="5" onblur="validateDescr();" />
		        <span class="Color5"><label id="descrMsg" style="display: none;"></label></span>
		        </td>
		   	</tr>
		</table>
	 <div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1701-02' value='企业添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-1701-03' value='企业修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		<s:if test="#session.user_session.userLevel==0">
		   <input type="button" class="formButton" value="返 回" onclick="go('base/compInfo!list')"/>
		</s:if>
		<s:else>
		  <input type="button" class="formButton" value="返 回" onclick="goHome()"/>
		</s:else>
     </div>
	 </s:form>
</body>
</html>