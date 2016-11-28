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
	<title>油厂管理</title>
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
	<script type="text/javascript" src="js/areaSelect.js"></script>
	<script type="text/javascript">	
	$(document).ready(function (){
	       var method = document.getElementById("method"); 
	        if(method.value=='checkUI'){
	            setInputDisabled();
	            $("#status").attr("disabled","disabled");
	            $("#areaId").attr("disabled","disabled");
	            $("#invSign").attr("disabled","disabled");
	            }
	        if(method.value=='editSave'){
	        	$("#bailAmt").attr("disabled","disabled");
	            $("#buyOilRate").attr("disabled","disabled"); 	        
	        	}
	        });
	
		function replaceToNumPoint(obj){
		   if(isNaN(obj.value)){
			 	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
			 	obj.value = obj.value.replace(/^[.]/g,"");  //验证第一个字符是数字而不是.
			 	obj.value = obj.value.replace(/[.]{2,}/g,""); //清除第一个点以外的点			 	
			 }
	       }
	    function replaceToNum(obj){		   
		   if(isNaN(obj.value)){
			 	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
			 	obj.value = obj.value.replace(/[^.]/g,"");  //验证第一个字符是数字而不是.					 	
			 }
	   	   }
		
		var merNameFlag = false;
		var conPerNameFlag = false;
		var conPerTeleNoFlag = false;
		var teleNoFlag = false;
		var settPeriodFlag = false;
		var buyOilRateFlag = false;
		var bailAmtFlag = false;
	    var flag1 = false;
	    var flag2 = false;
	
	
        //检验油厂名称
		function merNameBlur(obj){
              var type = ["isNull"];
              var errorMsg = ["名称不能为空!"];
              //验证商户名称格式
              flag1 = showErrorMsg(obj,type,errorMsg,"errorMsg","warnMsg");
              //验证商户名称长度
              flag2 = checkLenMsg(obj,"长度不能大于30个汉字","errorMsg","",60);
              if(flag1&&flag2){
                  merNameFlag = true;
              }
               
		}
	   function merNameFocus(obj){
			   showWarnMsg(obj,"可填写汉字、字母以及数字!","errorMsg","warnMsg");			   
			}
			
	   //判断法人名字
       function conPerNameBlur(obj){
              var type = ["isNull","isRealName"];
              var errorMsg = ["法人姓名不能为空!","法人姓名格式错误!"];
              flag1 = showErrorMsg(obj,type,errorMsg,"conPerNameErrorMsg","conPerNameWarnMsg");
              flag2 = checkLenMsg(obj,"法人姓名不超过30个字符!","conPerNameErrorMsg","",30);
              
              if(flag1&&flag2){
                  conPerNameFlag = true;
              }
             
           }
         
       //判断手机号  
       function conPerTeleNoBlur(obj){
              var type = ["isNull","isMobile"];  
              var errorMsg = ["手机号码不能为空!","手机号码格式错误!"];
              conPerTeleNoFlag = showErrorMsg(obj,type,errorMsg,"conPerTeleNoErrorMsg","conPerTeleNoWarnMsg");
           }
      
      //判断电话号    
	  function teleNoBlur(obj){
             var type = ["isNull","isTel"];
             var errorMsg = ["固话号码不能为空!","固话号码格式错误!"];
             teleNoFlag = showErrorMsg(obj,type,errorMsg,"teleNoErrorMsg","teleNoWarnMsg");            
		   }
	  
	  //判断结算周期   
	  function settPeriodBlur(obj){		  
            var type = ["isNull","fullNumber"];
            var errorMsg = ["结算周期不能为空!","结算周期应为整型数字!"]; 
            settPeriodFlag = showErrorMsg(obj,type,errorMsg,"settPriodErrorMsg","settPriodWarnMsg");          
		   }
		
	  //判断保证金
	  function validateBail(obj){		   
		    var reg = /^-?\d+\.?\d{0,2}$/;
		    var bailAmt = $("#bailAmt").val();
		    	$("#bailMsg").hide();
			    if(bailAmt.length>0)
			    	if(bailAmt>9999999999999){
			    		checkLenMsg(obj,"保证金格式错误！（整数最长13位，小数2位）","bailMsg","",3);
			    		bailAmtFlag = false;
			    	}
			    	else{
			    		if(!reg.test(bailAmt)){
				    	checkLenMsg(obj,"保证金格式错误！（整数最长13位，小数2位）","bailMsg","",3);
				    	bailAmtFlag = false;
					    }
					    else{
					    	$("#bailMsg").hide();
					    	bailAmtFlag = true;
					    }
			    	}
				    
				else{
					$("#bailMsg").hide();
					bailAmtFlag = true;
				}    	   	   	
	   	   }
	   	   
	  //判断保证金比率
	  function validateRate(obj){
	  		var buyOilRate = $("#buyOilRate").val();
         	$("#buyOilRateMsg").hide();
         	if(buyOilRate.length>0){         		
         		if(buyOilRate>=100){
					checkLenMsg(obj,"保证金比率不能大于99！","buyOilRateMsg","",2);
					buyOilRateFlag = false;
				}        
				else{
					$("#buyOilRateMsg").hide();
					buyOilRateFlag = true;
				}
               }
            else{
            	$("#buyOilRateMsg").hide();
            	buyOilRateFlag = true;
               }
	  	   }
       
		//修改方法
		var check = function() {
	
			
            var oilFactName = getEle("oilFactName");
            merNameBlur(oilFactName);
            
            var conPerName = getEle("conPerName");
            conPerNameBlur(conPerName);

            var conPerTeleNo = getEle("conPerTeleNo");
            conPerTeleNoBlur(conPerTeleNo);

            var teleNo = getEle("teleNo");
            teleNoBlur(teleNo);
            
            var settPeriod = getEle("settPeriod");
            settPeriodBlur(settPeriod);            
         
         	var bailAmt = getEle("bailAmt");
         	validateBail(bailAmt);
         
         	var buyOilRate = getEle("buyOilRate");
         	validateRate(buyOilRate);
         	
		    if(!(merNameFlag&&conPerNameFlag
                   &&conPerTeleNoFlag&&teleNoFlag
                   &&settPeriodFlag&&buyOilRateFlag&&bailAmtFlag)){
                   alert("填写信息有误（*号信息为必填项），请根据提示信息重新填写!");
               	   return false;
               }		          
		}; 
		
		function getEle(id){
             return document.getElementById(id);
			}
			
	  </script> 
</head>
<body>

	<s:if test="method=='addSave'">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 油厂管理 &gt;&gt; 油厂信息添加
	</div>
	</s:if>
	<s:if test="method=='editSave'">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 油厂管理 &gt;&gt; 油厂信息修改
	</div>
	</s:if>
	<s:if test="method=='checkUI'">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 油厂管理 &gt;&gt; 油厂信息查看
	</div>
	</s:if>
	<s:include value="/WEB-INF/page/base/merchants/preMerchantsHelp.jsp"></s:include>
	<s:form action="oilfactory/oilfactoryaction" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>
	<div class="List_tit">油厂信息</div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr><s:hidden name="oilfactDto.oilFactId" id="oilFactId"/>
		        <th align="right" width="20%"><span class="Color5">* </span>油厂名称：</th>
		        <td width="30%">
		        	<s:textfield name="oilfactDto.oilFactName" onkeyup = "allowEnCnNu(this);" onfocus="merNameFocus(this);" onblur="merNameBlur(this);" id="oilFactName" maxlength="20" cssClass="formInput"/> <label id="warnMsg" style="display: none;"></label> <label id="errorMsg" style="display: none;"></label>
		        </td>
		        <th align="right">状 态：</th>
		        <td><s:select id="status" name="oilfactDto.status"  list="#request.status" listKey="key" listValue="value" cssClass="formSelect"/></td>
			</tr>
			<tr>
		      	<th align="right"><span class="Color5">* </span>法人代表：</th>
		        <td><s:textfield name="oilfactDto.conPerName"  onkeyup = "allowEnCnNu(this);" onblur="conPerNameBlur(this);" id="conPerName" maxlength="30" cssClass="formInput"/> <label id="conPerNameErrorMsg" style="display: none;"></label></td>
		      	<th align="right"><span class="Color5">* </span>手机号码：</th>
		        <td><s:textfield name="oilfactDto.conPerTeleNo"  onkeyup= "replaceToNum(this);" onblur="conPerTeleNoBlur(this);"  id="conPerTeleNo" maxlength="11" cssClass="formInput"/> <label id="conPerTeleNoErrorMsg" style="display: none;"></label></td>
		   	</tr>
		 	<tr>
		      	<th align="right"><span class="Color5">* </span>固话号码：</th>
		      	
		        <td><s:textfield name="oilfactDto.teleNo" id="teleNo" onfocus="this.style.imeMode='inactive'" onblur="teleNoBlur(this);" maxlength="13" cssClass="formInput"/> <label id="teleNoErrorMsg" style="display: none;"></label></td>
		       	<th align="right">所在地区：</th>
		        <td colspan="3">
				    <s:select name="oilfactDto.areaId" id="areaId"  list="#request.areaValues" listKey="key" listValue="value" cssClass="formSelect"/>
				</td>
		   	</tr>
			<tr>
		      	<th align="right">地 址：</th>
		        <td><s:textfield name="oilfactDto.address" id="address" maxlength="30" cssClass="formInput"/></td>
		      	<th align="right">邮政编码：</th>
		        <td><s:textfield name="oilfactDto.zip" id="zip" maxlength="6"  onkeypress="return onlyNum(this);" onkeyup="replaceToNum(this);" cssClass="formInput"/></td>
		 	</tr>
			<tr>
		        <th align="right">合 同 号：</th>
		        <td><s:textfield name="oilfactDto.conNo" id="conNo" maxlength="12" cssClass="formInput"/></td>
				<th align="right"><span class="Color5">* </span>结算周期：</th>
		        <td><s:textfield name="oilfactDto.settPeriod" id="settPeriod" maxlength="3" onkeyup= "replaceToNum(this);"  onkeypress="return onlyNum(this);" onblur="settPeriodBlur(this);" cssClass="formInput" cssStyle="width: 50px;"/> 天 <label id="settPriodErrorMsg" style="display: none;"></label></td>
			</tr>
			<s:if test="method=='editSave'">
			<tr>		      	
		      	<th align="right">上次结算时间：</th>
		        <td><s:textfield name="oilfactDto.lastSettTime" disabled="true" cssClass="formInput"/></td>
			</tr>
			</s:if>
		  	<tr>
		      	<th align="right">开户银行：</th>
		        <td><s:textfield name="oilfactDto.bankName" id="bankName" maxlength="20" cssClass="formInput"/></td>
		        <th align="right">开户账号：</th>
		        <td><s:textfield name="oilfactDto.bankAccNo" id="bankAccNo" maxlength="19" cssClass="formInput"/></td>
		   	</tr>
		  	<tr>
		      	<th align="right">开户名称：</th>
		        <td><s:textfield name="oilfactDto.bankUser" id="bankUser" maxlength="20" cssClass="formInput"/></td>
		        <s:if test="method=='editSave'">
		        <th align="right">更新时间：</th>
		        <td><s:textfield name="oilfactDto.updateTime" disabled="true" cssClass="formInput" /></td>
		   		</s:if>
		   	</tr>
		   	<tr>
		       	<th align="right">是否开票：</th>
			    <td><s:select name="oilfactDto.invSign" id="invSign" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect"/></td>
		 	</tr>

	 	</table>
	 	<div class="List_tit">相关信息</div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">		   
		    <tr>
		      	<th align="right" width="20%">保证金金额：</th>
		        <td><s:textfield name="oilfactDto.bailAmt" id="bailAmt" maxlength="15" onkeyup="replaceToNumPoint(this);" onblur="validateBail(this)" cssClass="formInput"  cssStyle="width: 100px;"/> 元<label id="bailMsg" style="display: none;"></label></td>
		        <th align="right">购油比率：</th>
		        <td><s:textfield name="oilfactDto.buyOilRate" id="buyOilRate" maxlength="6" onkeyup="replaceToNumPoint(this);" onblur="validateRate(this);" cssClass="formInput" cssStyle="width: 100px;"/><span class="Color3">（例如98或0.98）</span><label id="buyOilRateMsg" style="display: none;"></label></td>
		   	</tr>
		   	<tr>
	      		<th align="right" width="20%">售油信息：</th>
	      		<td colspan="3">
	      			<table id="cardBinTb" width="100%" class="listTable" cellpadding="0" cellspacing="0">
	      				
	      				<tr>
	      					<th width="10%"></th>
	      					<th>油品类型</th>
	      					<th>售油价</th>
	      					<th>日产量</th>
	      					<th>储备量</th>
	      				</tr>	      				
	      				<s:if test="#request.saleOils!=null&&#request.saleOils.size>0">
				    		<s:iterator value="#request.saleOils" status="status">
				    		<tr>
				    			<td ><input type="checkbox" name="oilfactDto.oilTypeStatuss" id="id" value="<s:property value="oilType"/>"  
				    				<s:if test="oilTypeStatus==1">checked="checked"</s:if>/></td>
				    			<td><s:property value="oilTypeName" /><input type="hidden" name="oilfactDto.oilTypes" value="<s:property value="oilType"/>"/></td>
				    			<td><input type="text" name="oilfactDto.saleAmts" maxlength="6" onkeyup="replaceToNumPoint(this);" cssClass="formInput" value="<s:property value="saleAmt"/>" cssStyle="width: 100px;"/> 元/升</td>
				    			<td><input type="text"  name="oilfactDto.oilDailys" maxlength="6" onkeyup="replaceToNumPoint(this);" cssClass="formInput" value="<s:property value="oilDaily"/>"  cssStyle="width: 100px;"/> 吨</td>
				    			<td><input type="text"  name="oilfactDto.reserves" maxlength="6" onkeyup="replaceToNumPoint(this);" cssClass="formInput" value="<s:property value="reserve"/>" cssStyle="width: 100px;" /> 吨</td>
				    		</tr>
				    		</s:iterator>
				    	</s:if>
				    	
	      			</table>
	      		</td>
	      	</tr>
		   	
		</table>
	
	
	<div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1311-01' value='添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-1311-03' value='修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		<input type="button" class="formButton" value="返 回" onclick="go('oilfactory/oilfactoryaction!list')"/>
	 </div>
	</s:form>
</body> 
</html>