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

	var memRealNameFlag = false;
	var teleNoFlag = false;
	var personIdFlag = false;
	var emailFlag = false;
	
	/**身份证是否可用，0：不可用  1：可用**/
	var personFlag=1;
	
	$(document).ready(function (){
	       var method = document.getElementById("method"); 
	       if(method.value=='checkUI'){
	            setInputDisabled();
	            }
	        });
	function setMemRealName(){
		var memRealName=$.trim($("#memRealName").val());
		 var reg= new RegExp("^[A-Za-z\\u4e00-\\u9fa5]+$");
		if(memRealName.length==0){
			$("#memRealNameError").text("真实姓名不能为空!");
			return false;
		}
		if(!reg.test(memRealName)){
			$("#memRealNameError").text("真实姓名格式错误!");
			return false;
		}
		$("#memRealNameError").text("");
		return true;
		/*
		var type = ["isNull","isRealName"];
        var errorMsg = ["真实姓名不能为空!","真实姓名格式错误!"];
        memRealNameFlag = showErrorMsg(obj,type,errorMsg,"memRealNameError","memRealNameMsg");
       	*/
        }
    function setTelNo(){
    	var teleNo=$.trim($("#teleNo").val());
    	var reg=new RegExp("^0?(13|15|18|14)[0-9]{9}$");
    	if(teleNo.length==0){
			$("#telNoError").text("手机不能为空!");
			return false;
		}
		if(!reg.test(teleNo)){
			$("#telNoError").text("手机格式错误!");
			return false;
		}
		$("#telNoError").text("");
		return true;
    /*
    	var type = ["isNull","isMobile"];
        var errorMsg = ["手机号码不能为空!","手机号码格式错误!"];
        teleNoFlag = showErrorMsg(obj,type,errorMsg,"telNoMsg","telNoError");
        */
        }
    function setPersonId(obj,flag){
    	var type = ["isNull",];
        var errorMsg = ["身份证号码不能为空!"];
        perFlag = showErrorMsg(obj,type,errorMsg,"personIdMsg");
        if(flag&&perFlag){
        	personIdFlag = true;
            }
        else{
            if(!flag){
                $("#personIdErrors").show();
				$("#personIdErrors").html("身份证编号格式错误！");	
                }
        	personIdFlag = false;
            }
        }
    function setEmail(){
    	
    	var email=$.trim($("#email").val());
    	var reg=new RegExp("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
    	if(email.length==0){
			$("#emailError").text("Email不能为空!");
			return false;
		}
		if(!reg.test(email)){
			$("#emailError").text("Email格式错误!");
			return false;
		}
		$("#emailError").text("");
		return true;
    
    	/*
    	var type = ["isNull","isEmail"];
        var errorMsg = ["电子邮箱不能为空!","电子邮箱格式错误!"];
        emailFlag = showErrorMsg(obj,type,errorMsg,"emailMsg","emailError");
        */
        }
	function check(){
		checkPersonId();
		var method = $("#method").val();
		var memRealName = $("#memRealName").val();
		var telNo = $("#teleNo").val();
		var personId = $("#personId").val();
		var email = $("#email").val();
		if(method=="addSave"){
			if(personFlag==1&&setEmail()&&setMemRealName()&&setTelNo()){
				return true;
			}else{
	            alert("填写信息有误，*号信息为必填项。请根据提示信息重新填写!");
				return false;
	        }
   		 }
		/*
		if(memRealNameFlag&&teleNoFlag&&personIdFlag&&emailFlag){
                  return true;
            }else{
            	alert("填写信息有误，*号信息为必填项。请根据提示信息重新填写!");
				  return false;
                }
		}
		*/
		else if(method=="editSave"){
			if(setEmail()&&setTelNo()){
				return true;
			}else{
				return false;
			}
		}
	}
	function carNumberBlur(){
		  
		var carNo=$.trim($("#carNumber").val());
		var error=$("#carNumberErrorMsg");
		var reg=new RegExp("^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$");
		if(carNo==null||carNo.length==0){
			error.show();
			error.text("车牌号不能为空！");
			return false;
		}else if(reg.test(carNo)){
			error.hide();
			error.text("");
			
			return true;
		}else{
			error.show();
			error.text("车牌号格式错误！");				
			return false;
		}
	}
	function driverNoBlur(){
		//var driverNo = $.trim($("#personId").val());
		//var error = $("driverNoErrorMsg");
		//var a = new 
		var type = ["isNull","isPersonId"];
        var errorMsg = ["驾驶证编号不能为空!","驾驶证编号格式错误!"];  
        driverNoFlag = showErrorMsg(document.getElementById("driverNo"),type,errorMsg,"driverNoErrorMsg","driverNoErrorMsg");
       // alert(driverNoFlag);	
	}
	
	function checkPersonId() {
		
		var personId = $.trim($("#personId").val());
	//json数据传输
		if(personId.length==0){
			$("#personIdError").html("请输入身份证号!");
	 	    $("#personIdError").addClass("errorMsg");
	 	    $("#personIdError").show();
	 	    personFlag= 0;
		}else if(personId.length!=18&&personId.length!=15){
			$("#personIdError").html("身份证号长度输入不正确!");
	 	    $("#personIdError").addClass("errorMsg");
	 	    $("#personIdError").show();
	 	    personFlag= 0;
		}else{
		var params = {
			"memberDTO.personId" : personId
	    }; 
		var actionUrl = "member/member!checkPersonId";
	    $.ajax({   
	        url : actionUrl,   
	        data : params,   
	        dataType : "json",
	        cache : false, 
	        type : "POST",
	        error : function(textStatus, errorThrown) {   
	    		alert("系统ajax交互错误!");  
	        },
	        success : function(data, textStatus) { 
	           if(data.flag==false){
	           		personFlag=0;
	        	   $("#personIdError").text("该身份证号已经被注册!");
	        	   $("#personIdError").addClass("errorMsg");
	        	   $("#personIdError").show();
		       }else{
		       		personFlag=1;
		       		$("#personIdError").text("");
		           $("#personIdError").hide();
			   }
	        }
	    });
		}
	}
	$(document).ready(function (){
	       var method = document.getElementById("method"); 
	       var cardType = $("#cardType").val();
	       if(cardType=="1"){
			   $("#tr1").hide();
               $("#tr2").hide();
               $("#tr3").hide();
              }
          else{
			   $("#tr1").show();
               $("#tr2").show();
               $("#tr3").show();
              }
	        if(method.value=='editSave'){
	        	$("#memRealName").attr("readonly","readonly");
	        	$("#memRealName").css("background","#F2F2F2");
	            $("#perType").attr("readonly","readonly");
	            $("#perType").css("background-color-color","#F2F2F2");
	            $("#sex").attr("readonly","readonly");
	            $("#sex").css("background-color","#F2F2F2");
	            $("#name").attr("readonly","readonly");
	            $("#name").css("background-color","#F2F2F2");
	            $("#personId1").attr("readonly","readonly");
	            $("#personId1").css("background-color","#F2F2F2");
	            $("#birthday").attr("readonly","readonly");
	            $("#birthday").css("background-color","#F2F2F2");
	            $("#carNumber").attr("readonly","readonly");
	            $("#carNumber").css("background-color","#F2F2F2");
	            $("#carType").attr("readonly","readonly");
	            $("#carType").css("background-color","#F2F2F2");
	            $("#recomId").attr("readonly","readonly");
	            $("#recomId").css("background-color","#F2F2F2");
	            $("#recomType").attr("readonly","readonly");
	            $("#recomType").css("background-color","#F2F2F2");
	            
	            }
            if(method.value=='checkUI'){
            	setInputDisabled();
	            $("#areaId").attr("readonly","readonly");
	            $("#perType").attr("readonly","readonly");
	            $("#sex").attr("readonly","readonly");
	            $("#status").attr("readonly","readonly");
                }
	        });


	  
   	  function changeRecom(){
   	   	  	var realName=$("#realName").val()
			var type=$("#recomType").val();
			if(type==2){
				$("#payMenName").attr("readonly",true);
				$("#payMenName").val(realName);
				$("#imgSearch").hide();
			}else{
				$("#payMenName").attr("readonly",false);
				$("#payMenName").val("");
				$("#imgSearch").show();
			}
        }

   	//初始化DIV显示结果 
		function cardKinds(){
			var cardType = $("#cardType").val();
			 if(cardType=="1"){
			   $("#tr1").hide();
               $("#tr2").hide();
               $("#tr3").hide();
              }else if(cardType=="3"){
            	  $("#tr1").hide();
                  $("#tr2").hide();
                  $("#tr3").hide();
              }
              else{
			   $("#tr1").show();
               $("#tr2").show();
               $("#tr3").show();
              }
			}

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
	<s:if test="#session.user_session.userLevel!=2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<s:hidden name="memberDTO.updateTime" id="updateTime" />
			<s:hidden name="memberDTO.memId"/>
			<s:hidden name="memberDTO.organId"/>
			<s:hidden name="driverDTO.driverId"/>
			<tr>
		  		
		        <th align="right"><span class="Color5">* </span>真实姓名：</th>
		 		<td>
		 			<s:textfield name="memberDTO.memRealName"  id="memRealName"  maxlength="80" cssClass="formInput"  theme="simple"  onkeyup = "allowEnCnNu(this);" onblur="setMemRealName();"/>
		 			<label id="memRealNameError"></label> 
		 			<label id="memRealNameMsg" style="display: none;"></label>
		 		</td>
		 		<th align="right"><span class="Color5">* </span>手机号码：</th>
		        <td>
		        	<s:textfield name="memberDTO.teleNo" id="teleNo" maxlength="11" cssClass="formInput" theme="simple" onblur = "setTelNo();"  onkeyup = "replaceToNum(this);"/>
		        	<label id="telNoMsg" style="display: none;"></label> 
		        	<label id="telNoError" ></label> 
		        </td>
			</tr>
		 	<tr>	 	
		 		<th align="right">状         态：</th>
		        <td>
		        	<s:select name="memberDTO.status" id="status"  list="#request.statusValues" listKey="key" listValue="value"  cssClass="formInput" theme="simple"/>
		        	<label id="warnMsg" style="display: none;"></label> 
		        </td>
		        <th align="right">性         别：</th>
		        <td>
		        	<s:select name="memberDTO.sex" id="sex" list="#request.sexValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
		        	<label id="warnMsg" style="display: none;"></label> 
		        </td>
		   </tr>
		   <tr>
		        <th align="right">推荐人类型：</th>
		        <td>
		        	<s:select name="memberDTO.recomType" id="recomType"  onchange="changeRecom();" list="#request.recomType" listKey="key" listValue="value"  cssClass="formInput" theme="simple"/>
		        	<label id="warnMsg" style="display: none;"></label> 
		        </td>
		        <th align="right" width="20%">推荐人：</th>    
		        <td width="30%"><s:textfield name="memberDTO.recomId" id="payMenName"  readonly="true" maxlength="20" cssClass="formInput" theme="simple"/><img id="imgSearch" alt="查找会员" src="images/search.gif" style="cursor:pointer;" onclick="ajaxParentMemId();"/>
		        <label id="merNameErrorMsg" style="display: none;"></label>
		        </td>
		  </tr>
		  <tr>
		  		
		        <s:if test="method=='addSave'">
		      	<th align="right"><span class="Color5">* </span>证件编号：</th>
		        <td>
		        	<s:textfield name="memberDTO.personId" id="personId" maxlength="18" cssClass="formInput"  theme="simple" />
		        	<label id="personIdMsg" style="display: none;"></label> 
		 			<label id="personIdError"></label>
		 			<label id="personIdErrors" style="display: none;"></label>
		        </td>
		        </s:if>
		        <s:else>
		        <th align="right"><span class="Color5">* </span>证件编号：</th>
		        <td>
		        	<s:textfield name="memberDTO.personId" id="personId1" maxlength="18" cssClass="formInput"  theme="simple" />
		        </td>
		        </s:else>
		         <th align="right">车牌号：</th>  
		        <td><s:textfield name="memberDTO.carNumber"  id="carNumber" onblur="carNumberBlur();"  maxlength="13" cssClass="formInput" theme="simple"/>
		        <label id="carNumberErrorMsg" style="display: none;" class="errorMsg"></label> 
		        <label id="carNumberWarnMsg" style="display: none;"></label></td>
		   	</tr>
		   	<tr>
		      	<th align="right">驾驶证编号：</th>
		        <td><s:textfield name="memberDTO.driverNo" id="driverNo"  onblur="driverNoBlur();"   cssClass="formInput" theme="simple"/>
		        <label id="driverNoErrorMsg" style="display: none;" class="errorMsg"></label> <label id="driverNoWarnMsg" style="display: none;"></label></td>
		      	<th align="right">车型：</th>
		        <td><s:select  name="memberDTO.carType" id="carType" list="#request.carType"  listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
		        <label id="carTypeErrorMsg" style="display: none;"></label> <label id="carTypeWarnMsg" style="display: none;"></label>
		        </td>
		   	</tr>
		  	<tr>
		  		<th align="right">居住地址：</th>
		         <td><s:textfield name="amemberDTO.address" id="address" maxlength="60" cssClass="formInput" theme="simple"/></td>
		      	 <th align="right">邮           编：</th>
		        <td><s:textfield name="amemberDTO.residZip" id="residZip" maxlength="6" cssClass="formInput" theme="simple" onkeyup= "replaceToNum(this);"/></td>
		 	</tr>
		   	<tr>
		      	<th align="right">学         历：</th>
		        <td><s:textfield name="amemberDTO.cultDegree" id="cultDegree" maxlength="20" cssClass="formInput" theme="simple"/></td>
		      	<th align="right">出生日期：</th>
		        <td><s:textfield name="amemberDTO.birthday" id="birthday" maxlength="20" cssClass="formInput" theme="simple" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/></td>
		  	</tr>
		   	<tr>
		    	<th align="right">职         业：</th>
		      	 <td><s:textfield name="amemberDTO.career" id="career" maxlength="20" cssClass="formInput" theme="simple"/></td>
		   			<th align="right"><span class="Color5">* </span>电子邮箱：</th>
		        <td>
		        	<s:textfield name="memberDTO.email" id="email" maxlength="30" cssClass="formInput" theme="simple" onblur="setEmail();" />
		        	<label id=emailMsg style="display: none;"></label> 
		 			<label id="emailError"></label>
		        </td>
		  	</tr>
		  <tr>
		      	 <th align="right">所属区域：</th>
		        <td>
		        <s:select name="memberDTO.areaId" id="areaId" list="#request.areaIdValues" listKey="key" listValue="value" value="1" cssClass="formSelect" theme="simple"/>
		        </td>
		        <th align="right">会员卡类型：</th>
		        <td><s:select name="memberDTO.cardType" id="cardType" onchange="cardKinds();" list="#request.cardType" listKey="key" listValue="value"  cssClass="formInput" theme="simple"/></td>		       
		  	</tr>
			  	<tr id = "tr1">
			  		<th align="right">合同编号：</th>
			         <td><s:textfield name="driverDTO.contractNo" id="contractNo" maxlength="60" cssClass="formInput" theme="simple"/></td>
			      	 <th align="right">开户银行：</th>
			        <td><s:textfield name="driverDTO.bank" id="bank" maxlength="6" cssClass="formInput" theme="simple" /></td>
			 	</tr>
			   	<tr id = "tr2">
			      	<th align="right">开户行账号：</th>
			        <td><s:textfield name="driverDTO.bankAccount" id="bankAccount" maxlength="20" cssClass="formInput" theme="simple"/></td>
			      	<th align="right">紧密联系人：</th>
			        <td><s:textfield name="driverDTO.contacts" id="contacts" maxlength="20" cssClass="formInput" theme="simple"/></td>
			  	</tr>
			  	<tr id = "tr3">
			  		<th align="right">紧密联系人电话:</th>
			  		<td><s:textfield name="driverDTO.contactstel" id="contactstel" maxlength="20" cssClass="formInput" theme="simple"/></td>
			  	</tr>
		</table>
	</s:if>

	 <div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1301-02' value='会员添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-1301-03' value='会员修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		<input type="button" class="formButton" value="返 回" onclick="go('member/member!list')"/>
	 </div>
	 </s:form>
</body>
</html>