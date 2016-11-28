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
		var contractNoFlag=false;
		var driverNoFlag=false;
		//验证合同号
		function contractNoBlur(){
			var type = ["isNull","fullNumber"];
	        var errorMsg = ["合同编号不能为空!","合同编号格式错误!"];  
	        contractNoFlag = showErrorMsg(document.getElementById("contractNo"),type,errorMsg,"teleNoErrorMsg","teleNoErrorMsg");
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
	        //alert(driverNoFlag);	
		}
		function check(){		
			alert(contractNoFlag);
			alert(carNumberBlur());
			alert(driverNoFlag);
			if(!(contractNoFlag&&driverNoFlag&&carNumberBlur())){
                alert("请按照提示信息正确填写.(*部分为必填项!)");
	             return false;;
		}

		//修改方法
		var check = function() {	
			
		   var driverId = $.trim($("#driverId").val());//司机编号
		   var memId = $.trim($("#memId").val());//会员编号
		   var memRealName = $.trim($("#memRealName").val());//司机真实姓名
		   var contractNo = $.trim($("#contractNo").val());//合同编号
		   var teleNo = $.trim($("#teleNo").val());//司机电话号
		   var personId = $.trim($("#personId").val());//司机身份证号
		   var driverNo = $.trim($("#driverNo").val());//驾驶证编号
		   var carType = $.trim($("#carType").val());//车型
		   var carNo = $.trim($("#carNo").val());//车牌号
		   var contacts = $.trim($("#contacts").val());//联系人
		   var contactsTel = $.trim($("#contactsTel").val());//联系人电话
		   var bank = $.trim($("#bank").val());//开户银行
		   var bankAccount = $.trim($("#bankAccount").val());//银行卡号
		   var settlement = $.trim($("#settlement").val());//结算周期   
		   
		   }
		 $(function(){				
				var status=$("#status").val();
				if(status==9){
					$("#payMenId").attr("disabled",true);
					$("#payMenName").attr("disabled",true);
					$("#telNo").attr("disabled",true);
					$("#contractNo").attr("disabled",true);
					$("#contractsTel").attr("disabled",true);
					$("#carNumber").attr("disabled",true);
					$("#carType").attr("disabled",true);
					$("#bank").attr("disabled",true);
					$("#bankAccount").attr("disabled",true);
					$("#settlement").attr("disabled",true);
					$("#contacts").attr("disabled",true);
					$("#contactsTel").attr("disabled",true);
					$("#driverNo").attr("disabled",true);
					$("#status").attr("disabled",true);
					$("#submitInput").remove();
				}
		})
	  
	</script>
	
</head>
<body>
<jsp:include page="/WEB-INF/page/member/member/memberHelp.jsp"></jsp:include>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 司机基本信息 &gt;&gt; 司机基本信息管理
	</div>
	
	<s:form action="base/driver" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	 <s:hidden name="driverDTO.driverId" id="driverId"></s:hidden>
	<s:hidden name="method" id="method"/>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>会员编号：</th>
		        <td width="30%"><s:textfield name="driverDTO.memId" id="payMenId"  readonly="true" maxlength="20" cssClass="formInput" theme="simple"/><img alt="查找会员" src="images/search.gif" style="cursor:pointer;" onclick="ajaxParentMemId();"/>
		      
		        	<label id="merNameErrorMsg" style="display: none;"></label>
		        </td>
		        <th align="right" width="20%"><span class="Color5">* </span>司机名称：</th>
		        <td width="30%"><s:textfield readonly="true" name="driverDTO.memRealName" id="payMenName"   maxlength="60" cssClass="formInput"  theme="simple"/>
		        	<label id="warnMsg" style="display: none;"></label> <label id="errorMsg" style="display: none;"></label>
		       	</td>
			</tr>
		  	<tr>
		      	<th align="right"><span class="Color5">* </span>合同编号：</th>  
		        <td><s:textfield name="driverDTO.contractNo"  id="contractNo"  onblur="contractNoBlur();" cssClass="formInput" theme="simple"/>
		        	<label id="teleNoErrorMsg" style="display: none;"></label> <label id="teleNoWarnMsg" style="display: none;"></label></td>
		      	<th align="right"><span class="Color5">* </span>手机号码：</th>
		        <td><s:textfield name="driversDTO.teleNo" id="telNo"   maxlength="11" cssClass="formInput" theme="simple"/>
		       		 <label id="conPerTeleNoErrorMsg" style="display: none;"></label> <label id="conPerTeleNoWarnMsg" style="display: none;"></label>
		        </td>
		   	</tr>
		  	<tr>
		      	<th align="right"><span class="Color5">* </span>驾驶证编号：</th>
		        	<td><s:textfield name="driverDTO.driverNo" id="driverNo"  onblur="driverNoBlur();"   cssClass="formInput" theme="simple"/>
		        	<label id="driverNoErrorMsg" style="display: none;" class="errorMsg"></label> <label id="driverNoWarnMsg" style="display: none;"></label></td>
		      	<th align="right"><span class="Color5">* </span>车型：</th>
		        	<td><s:select  name="driverDTO.carType" id="carType" list="#request.carType"  listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
		        	<label id="carTypeErrorMsg" style="display: none;"></label> <label id="carTypeWarnMsg" style="display: none;"></label>
		        	</td>
		   	</tr>
		  	<tr>
		 	    <th align="right"><span class="Color5">*</span>车牌号：</th>  
		        	<td><s:textfield name="driverDTO.carNumber"  id="carNumber" onblur="carNumberBlur();"  maxlength="13" cssClass="formInput" theme="simple"/>
		        	<label id="carNumberErrorMsg" style="display: none;" class="errorMsg"></label> 
		        	<label id="carNumberWarnMsg" style="display: none;"></label></td>
		        <th align="right">状         态：</th>
		        	<td>
		        	<s:select name="driverDTO.status" id="status"  list="#request.status" listKey="key" listValue="value"  cssClass="formInput" theme="simple"/>
		        	<label id="warnMsg" style="display: none;"></label> 
		        	</td>		           
		 	</tr>
		  	<tr>
		  	    <th align="right">联系人：</th>
		        	<td><s:textfield name="driverDTO.contacts" id="contacts" maxlength="20" cssClass="formInput" theme="simple" />
		        	</td>
		  		<th align="right">联系人联系方式：</th>
		        	<td><s:textfield name="driverDTO.contactsTel" id="contactsTel" maxlength="20" cssClass="formInput" theme="simple" />
		       	 </td>		 	    
		 	</tr>
		  	<tr>
		  		<th align="right">开户银行：</th>
		        	<td><s:textfield name="driverDTO.bank" id="bank" maxlength="20" cssClass="formInput" theme="simple" onkeyup = "allowEnCnNu(this);"/>
		        	</td>
			    <th align="right">开户账号：</th>
			        <td><s:textfield name="driverDTO.bankAccount" id="bankAccount" maxlength="20" cssClass="formInput" theme="simple" onkeyup= "replaceToNum(this);"/>
			        </td>
		   	</tr>
		   	<tr>
		   		<th align="right">结算周期：</th>
		        	<td ><s:textfield  name="driverDTODTO.settlement" id="settlement"  maxLength="3"  cssClass="formInput" cssStyle="width: 50px;" theme="simple"/> 天
		        	</td>
		   	</tr>
		</table>	
	 <div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1801-02' value='司机信息添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-1801-03' value='司机信息修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		   <input type="button" class="formButton" value="返 回" onclick="go('base/driver!list')"/>
     </div>
	 </s:form>
</body>
</html>
