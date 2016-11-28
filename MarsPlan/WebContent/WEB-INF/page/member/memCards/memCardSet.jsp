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
    
    <title>会员卡管理</title> 
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
	<script src="js/base/member.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script language="javascript" type="text/javascript" defer="defer" src="<%=basePath%>js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript">
	$(function(){
		var method=$("#method").val();
		if(method=="editSave"){
			$("#personId").attr("disabled","true");
		}
	});
	
	var cardNoFlag = false;
	var memRealNameFlag = false;
	var teleNoFlag = false;
	var personIdFlag = false;
	var emailFlag = false;
	
	
	/**身份证是否可用，0：不可用  1：可用**/
	var personFlag=1;
	/**卡号是否可用，0：不可用  1：可用**/
	var cardsFlag=1;

	function setCardNo(obj){
		var type = ["isNull","fullNumber"];
        var errorMsg = ["卡号不能为空!","卡号格式错误!"];
        cardNoFlag = showErrorMsg(obj,type,errorMsg,"cardNoAMsg","cardNoAError");
        }
	function setMemRealName(){
		var memRealName=$.trim($("#memRealName").val());
		 var reg= new RegExp("^[A-Za-z\\u4e00-\\u9fa5]+$");
		if(memRealName.length==0){
			$("#memRealNameError").text("真实姓名不能为空!");
			$("#memRealNameError").show();
			return false;
		}
		if(!reg.test(memRealName)){
			$("#memRealNameError").text("真实姓名格式错误!");
			$("#memRealNameError").show();
			return false;
		}
		$("#memRealNameError").hide();
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
			$("#telNoError").show();
			return false;
		}
		if(!reg.test(teleNo)){
			$("#telNoError").text("手机格式错误!");
			$("#telNoError").show();
			return false;
		}
		$("#telNoError").hide();
		$("#telNoError").text("");
		return true;
	 /*
	    	var type = ["isNull","isMobile"];
	        var errorMsg = ["手机号码不能为空!","手机号码格式错误!"];
	        teleNoFlag = showErrorMsg(obj,type,errorMsg,"telNoMsg","telNoError");
	        */
	        }
    function setPersonId(obj){
    	var type = ["isNull","isPerson"];
        var errorMsg = ["身份证号码不能为空!","身份证号码格式错误!"];
        personIdFlag = showErrorMsg(obj,type,errorMsg,"personIdMsg","personIdError");
        }
    function setEmail(){
    
    	var email=$.trim($("#email").val());
    	var reg=new RegExp("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
    	if(email.length==0){
			$("#emailError").text("Email不能为空!");
			$("#emailError").show();
			return false;
		}
		if(!reg.test(email)){
			$("#emailError").text("Email格式错误!");
			$("#emailError").show();
			return false;
		}
		
		$("#emailError").hide();
		$("#emailError").text("");
		return true;
    
    
    /*
    	//emailValiFormat(this);
    	var type = ["isNull","isEmail"];
        var errorMsg = ["电子邮箱不能为空!","电子邮箱格式错误!"];
        emailFlag = showErrorMsg(obj,type,errorMsg,"emailMsg","emailError");
      */
        }

		
	function setParams() {
		var cardNo = $.trim($("#cardNo").val());		
		if(cardNo.length != 6){
			$("#cardNoAError").text("卡号长度错误");
			$("#cardNoAError").show();
			cardsFlag=0;
		}else{
			$("#cardNoAError").hide();
			$("#cardNoAError").text("");
			//json数据传输
			var params = {
				"memCardDTO.cardNoShow" : cardNo
		    }; 
		    var actionUrl = "memCard/memCard!setParams";
		    $.ajax({
	            url:actionUrl,
	            dataType:"json",
	            type:"POST",
	            data:params,
	            success:function(data){
	            	if(data.flag==false){
						$("#cardNoAError").text("该卡号不能被绑定!");
						$("#cardNoAError").show();
						cardsFlag =0;
	            	}else if(date.memSign==1){
		            	$("#carNoAError").text("该卡号已经与会员进行绑定！");	
		            	$("#cardNoAError").show();
	            	}else{
		            $("#balancePnt").val(data.obj.balancePnt);
		    		$("#balanceAnt").val(data.obj.balanceAnt);
		    		$("#levelName").val(data.obj.levelName);
		    		$("#levelId").val(data.obj.levelId);
		    		$("#cstatus").val(data.obj.cstatus);
		    		$("#cardNoAError").hide();
		    		$("#cardNoAError").text("");
		    		cardsFlag=1;
	            	}
	            },
	            error:function(){
	            	cardsFlag=0;
	            	$("#cardNoAError").text("卡号异常");
	            	$("#cardNoAError").show();
	                }    
			 });
		}
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
	

	function check() {
		var method=$("#method").val();
		var cardNo = $("#cardNo").val();
		var memRealName = $("#memRealName").val();
		var teleNo = $("#teleNo").val();
		var personId = $("#personId").val();
		var email = $("#email").val();
		if(method=="addSave"){
			alert(1);
			if(personFlag==1&&cardsFlag==1&&setEmail()&&setMemRealName()&&setTelNo()){
				alert(2);
				return true;
			}else{
				return false;
			}
		}
		else if(method=="editSave"){
			alert(3);
			if(cardsFlag==1&&setEmail()&&setMemRealName()&&setTelNo()){
				return true;
			}else{
				alert(4);
				return false;
			}
		}
		
	};
	
	</script>
  </head>
   
  
  <body>
  <div class="Position">
		当前位置是：HOME &gt;&gt;会员基本信息 &gt;&gt; 会员卡信息管理
	</div>
	<s:form action="memCard/memCard" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method"  id="method"/>
	<s:if test="#session.user_session.userLevel!=2">
	<s:hidden name="memCardDTO.cstatus" id="cstatus"></s:hidden>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
				<s:if test="method=='addSave'">
			        <th align="right"><span class="Color5">* </span>会员卡号：</th>
			 		<td>
				 		<s:textfield name="memCardDTO.cardNo"  id="cardNo"  maxlength="6" cssClass="formInput"  theme="simple" onkeyup = "replaceToNum(this);" onblur="setParams()"/>
				 		<label id=cardNoAMsg style="display: none;"></label> 
			 			<label id="cardNoAError" class="errorMsg" style="display: none;"></label>
			 		</td>
		 		</s:if>
		 		<s:else>
			 		<th align="right"><span class="Color5">* </span>会员卡号：</th>
			 		<td><s:textfield name="memCardDTO.cardNo"  id="cardNo"  maxlength="16" cssClass="formInput"  theme="simple" readonly="true"/></td>
		 		</s:else>
		 		<th align="right" width="20%">卡积分：</th>
		        <td>
		        	<s:textfield name="memCardDTO.balancePnt" id="balancePnt" maxlength="20" cssClass="formInput" readonly="true"/>
				</td>
			</tr>
		  	<tr>
		 		<th align="right">卡余额：</th>
		        <td>
		        	<s:textfield name="memCardDTO.balanceAnt" id="balanceAnt" maxlength="20" cssClass="formInput" readonly="true"/>
		        </td>
		  		<th align="right">卡等级：</th>
		        <td>
		        	<s:textfield name="memCardDTO.levelName" id="levelName" maxlength="20" cssClass="formInput" readonly="true"/>
		        	<s:hidden name="memCardDTO.levelId" id="levelId"></s:hidden>
		        </td>
		  	</tr>
		  	<tr>
		  		 <th align="right"><span class="Color5">* </span>真实姓名：</th>
		 		<td>
		 			<s:textfield name="memCardDTO.memRealName"  id="memRealName"  maxlength="80" cssClass="formInput"  theme="simple"  onkeyup = "allowEnCnNu(this);" onblur="setMemRealName()"/>
		 			<label id="memRealNameError" class="errorMsg" style="display: none;"></label> 
		 			<label id="memRealNameMsg" style="display: none;"></label>
		 			<s:hidden name="memCardDTO.memId"></s:hidden>
		 		</td>
		        <th align="right">会员状态：</th>
		        <td><s:select name="memCardDTO.mstatus" id="mstatus"  list="#session.mstatusValues" listKey="key" listValue="value"  cssClass="formInput" theme="simple"/></td>
		  	
		  	</tr>
		  	<tr>
		 		<th align="right"><span class="Color5">* </span>手机号码：</th>
		        <td>
		        	<s:textfield name="memCardDTO.teleNo" id="teleNo" maxlength="11" cssClass="formInput" theme="simple" onblur = "setTelNo()"  onkeyup = "replaceToNum(this)"/>
		        	<label id="telNoMsg" style="display: none;"></label> 
		        	<label id="telNoError" class="errorMsg" style="display: none;"></label> 
		        </td>
		  		<th align="right">性别：</th>
		        <td><s:select name="memCardDTO.sex" id="sex" list="#session.sexValues" listKey="key" listValue="value"  cssClass="formSelect" theme="simple"/></td>
		  	</tr>
		  	<tr>
		  	 <th align="right">证件类型：</th>
		        <td><s:select  name="memCardDTO.perType" id="perType" list="#session.personTypeValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
		  		<th align="right"><span class="Color5">* </span>证件编号：</th>
		        <td>
		        	<s:textfield name="memCardDTO.personId" id="personId" maxlength="18" cssClass="formInput"  theme="simple" onkeyup="idVali(this)" onblur="checkPersonId()" onkeypress="return onlyNum(this);"/>
		        	<label id="personIdMsg" style="display: none;"></label> 
		 			<label id="personIdError" style="display: none;"></label>
		        </td>
		 	</tr>
		  	<tr>
		  		<th align="right">所属机构：</th>
		        <td>
		        	<s:textfield name="memCardDTO.orgName" id="orgName" maxlength="20" cssClass="formInput" theme="simple" readonly="true"/>
					<s:hidden name="memCardDTO.organId" id="organId"></s:hidden>
		        </td>
		      <th align="right">职         业：</th>
		      	 <td><s:textfield name="memCardDTO.career" id="career" maxlength="20" cssClass="formInput" theme="simple" onkeyup = "replaceToCn(this);"/></td>
		   	</tr>
		  	<tr>
		      	<th align="right">邮           编：</th>
		        <td><s:textfield name="memCardDTO.residZip" id="residZip" maxlength="6" cssClass="formInput" theme="simple" onkeyup= "replaceToNum(this);"/></td>
		        <th align="right"><span class="Color5">* </span>电子邮箱：</th>
		        <td>
		        	<s:textfield name="memCardDTO.email" id="email" maxlength="30" cssClass="formInput" theme="simple" onblur="setEmail();" />
		        	<label id=emailMsg style="display: none;"></label> 
		 			<label id="emailError" class="errorMsg" style="display: none;"></label>
		        </td>
		 	</tr>
		   	<tr>
		    	 <th align="right">居住地址：</th>
		         <td><s:textfield name="memCardDTO.address" id="address" maxlength="20" cssClass="formInput" theme="simple"/></td>
		       	<th align="right">出生日期：</th>
		        <td><s:textfield name="memCardDTO.birthday" id="birthday" maxlength="20" cssClass="formInput" theme="simple" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'});"/></td>
		  	</tr>
		   	<tr>
		   		<th align="right">所属区域：</th>
		        <td><s:select name="memCardDTO.areaId" id="areaId"  list="#session.areaIdValues" listKey="key" listValue="value"  cssClass="formInput" theme="simple"/></td>
		   	<th align="right">学         历：</th>
		        <td><s:textfield name="memCardDTO.cultDegree" id="cultDegree" maxlength="20" cssClass="formInput" theme="simple" onkeyup = "replaceToCn(this);"/></td>
		  	</tr>
<%--		  	<tr>--%>
<%--		        <th align="right">群组编号：</th>--%>
<%--		        <td><s:textfield name="memCardDTO.groupId" id="groupId" maxlength="20" cssClass="formInput" theme="simple"/></td>--%>
<%--		  	</tr>--%>
		</table>
		
	</s:if>
	 <div class="formTableBottom">
	 	<s:if test="#session.user_session.userLevel!=2">
		 	<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		</s:if>
			<input type="button" class="formButton" value="返 回" onclick="go('memCard/memCard!list')"/>
	 </div>
	 </s:form>
	 
</body>
</html>