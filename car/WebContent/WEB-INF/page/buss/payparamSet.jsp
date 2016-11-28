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
	<script src="js/jquery/jquery.ui.tabs.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValidate.js"></script>
    <script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
    <script>

       var psNameFlag = false;
       var payOrgansFlag = false;
       var psUrlFlag = false;
       var accountNoFlag = false;
       var psPwdFlag = false;
       var secretKeyFlag = false;
       var feeRateFlag = false;
       var diffNameFlag = false;
       var psNFlag = false;
    
       $(document).ready(function(){
	       var method = document.getElementById("method"); 
	        if(method.value=='checkUI'){
	             setInputDisabled();
	          }
           });

       function psNameBlur(obj){
    	   var type = ["isNull"];  
           var errorMsg = ["支付方式名称不能为空!"];
           psNFlag = showErrorMsg(obj,type,errorMsg,"psNameErrorMsg","psNameWarnMsg");
           
           var psName = $("#psName").val();
           var url = "buss/payparam!checkSameName";
           var params = {
               "payParamDTO.psName":psName
                   };
           if(psName==""){
        	   diffNameFlag = true;
            }else{
              
                $.ajax({
                    url : url,
                    data : params,
                    async:false,
                    dataType : "json",
                    type : "POST",
                    cache : false,
                    error:function(errText){
                       alert("ajax加载数据异常!请联系管理员");
                    },
                    success:function(data){
                         if(data.flag){
                            diffNameFlag = true;
                            $("#psNameErrorMsg").hide();
                         }else{
                            diffNameFlag = false;
                            pubErrorShow($("#psNameErrorMsg"),"该支付方式名称已存在!");
                         }
                         
                    }
                    
                });
            }

         }

       function psOrgansBlur(obj){
        
             var type = ["isSelectNull"];
             var errorMsg = ["所属支付机构不能为空!"];
             payOrgansFlag = showErrorMsg(obj,type,errorMsg,"payOrgansErrorMsg","payOrgansErroMsg");
           }

       function psUrlBlur(obj){
             var type = ["isNull"];
             var urlErrorMsg = ["Url地址不能为空!"];
             psUrlFlag = showErrorMsg(obj,type,urlErrorMsg,"psUrlErrorMsg","psUrlErrorMsg");
           }

       function accountNoBlur(obj){
             var type = ["isNull"];
             var accountNoErrorMsg=["账户号不能为空!"];
             accountNoFlag = showErrorMsg(obj,type,accountNoErrorMsg,"accountNoErrorMsg","accountNoErrorMsg");

          }

       function psPwdBlur(obj){
              var type = ["isNull"];
              var psPwdErrorMsg=["口令不能为空!"];
              psPwdFlag = showErrorMsg(obj,type,psPwdErrorMsg,"psPwdErrorMsg","psPwdErrorMsg");
           }

       function secretKeyBlur(obj){
              var type = ["isNull"];
              var secretKeyEMsg=["密钥不能为空!"];
              secretKeyFlag = showErrorMsg(obj,type,secretKeyEMsg,"secretKeyErrorMsg","secretKeyErrorMsg");
           }

       function feeRateBlur(obj){
             var type = ["isNull"];
             var feeRateEMsg = ["手续费率不能为空!"];
             feeRateFlag = showErrorMsg(obj,type,feeRateEMsg,"feeRateErrorMsg","feeRateErrorMsg");
           }

       function check(){

    	   psNameBlur(getEle("psName"));
    	   psOrgansBlur(getEle("payOrgans"));
    	   psUrlBlur(getEle("psUrl"));
    	   accountNoBlur(getEle("accountNo"));
    	   psPwdBlur(getEle("psPwd"));
    	   secretKeyBlur(getEle("secretKey"));
    	   feeRateBlur(getEle("feeRate"));

    	   if(psNFlag&&diffNameFlag){
              psNameFlag = true;
          }
    	   else{
             psNameFlag = false;
          }
    	   
    	   //alert(psNameFlag);
    	   //alert(payOrgansFlag);
    	   //alert(psUrlFlag);
    	   
           if(!(psNameFlag&&payOrgansFlag&&psUrlFlag&&accountNoFlag&&psPwdFlag&&secretKeyFlag&&feeRateFlag)){

               alert("信息填写有误，请按提示信息重新填写!");
               
               return false;
              }
        }

       function getEle(ele){

           var element = document.getElementById(ele);

           return element;
        }

    </script>
</head>
<body>
    
	<div class="Position">
		当前位置是：HOME &gt;&gt; 业务参数 &gt;&gt; 支付接口配置
	</div>
	<s:form action="buss/payparam" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
		<s:hidden name="method" id="method"></s:hidden>
		<s:hidden name="payParamDTO.psId" id="psId"></s:hidden>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		    <tr>
		      	<th align="right"><span class="Color5">* </span><strong>接口名称：</strong></th>
		        <td><s:textfield name="payParamDTO.psName" id="psName" maxlength="40" onblur="psNameBlur(this);" cssClass="formInput"/> <label id="psNameErrorMsg" style="display: none;"></label></td>
		   	</tr>
		   	<tr>
		   		<th align="right"><span class="Color5">* </span><strong>所属支付机构：</strong></th>
		        <td><s:select id="payOrgans" name="payParamDTO.payOrgId" list="#request.payOrgans" headerKey="-1" headerValue="请选择" listKey="key" listValue="value" onchange="psOrgansBlur(this);" cssClass="formSelect"/><label id="payOrgansErrorMsg" style="display: none;"></label></td>
		   	</tr>
			<tr>
		      	<th align="right"><span class="Color5">* </span><strong>接口地址(URL)：</strong></th>
		        <td><s:textfield name="payParamDTO.psUrl" onblur="psUrlBlur(this);" id="psUrl" maxlength="150" cssClass="formInput"/> <label id="psUrlErrorMsg" style="display: none;"></label></td>
		      	
		   	</tr>

			<tr>
		      	<th align="right"><span class="Color5">* </span><strong>账户号：</strong></th>
		        <td><s:textfield name="payParamDTO.accountNo"  onkeyup = "allowEnCnNu(this);" onblur="accountNoBlur(this);" id="accountNo" maxlength="50" cssClass="formInput"/> <label id="accountNoErrorMsg" style="display: none;"></label></td>
		      
		 	</tr>
		 	<tr>
		      	<th align="right"><span class="Color5">* </span><strong>口 &nbsp;&nbsp;令：</strong></th>
		        <td><s:textfield name="payParamDTO.psPwd"  onblur="psPwdBlur(this);" id="psPwd" maxlength="18" cssClass="formInput"/> <label id="psPwdErrorMsg" style="display: none;"></label></td>
		        
		  	</tr>
		  	<tr>
		      	<th align="right"><span class="Color5">* </span><strong>密 &nbsp;&nbsp;钥：</strong></th>
		        <td><s:textfield name="payParamDTO.secretKey"  onkeyup = "allowEnCnNu(this);" onblur="secretKeyBlur(this);" id="secretKey" maxlength="32" cssClass="formInput"/> <label id="secretKeyErrorMsg" style="display: none;"></label></td>
		        
		  	</tr>
		  	<tr>
		      	<th align="right"><span class="Color5">* </span><strong>手续费：</strong></th>
		        <td><s:textfield name="payParamDTO.feeRate"   onblur="feeRateBlur(this);" id="feeRate"  maxlength="32" cssClass="formInput"/> <label id="feeRateErrorMsg" style="display: none;"></label></td>
		        
		  	</tr>
			<tr>
		      	<th align="right"><span class="Color5">* </span><strong>支持货到付款：</strong></th>
		        <td><s:radio name="payParamDTO.codSign" list="%{#{'1':'是','0':'否'}}"></s:radio></td>
		       
			</tr>
			<tr>
		      	<th align="right"><span class="Color5">* </span><strong>支持在线支付：</strong></th>
		        <td><s:radio name="payParamDTO.payOnlineSign" list="%{#{'1':'是','0':'否'}}"></s:radio><label id="settPriodErrorMsg" style="display: none;"></label></td>
			</tr>
			<tr>
		      	<th align="right"><strong>支付方式描述：</strong></th><td><s:textarea  name="payParamDTO.descr" maxLength="255"  cssClass="formTextarea" cols="80" rows="5" ></s:textarea></td>
		   	</tr>
	 	</table>
	 	<div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-6301-01' value='支付方式添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:elseif test="method=='editSave'">
		    <my:permission key='sy-6301-03' value='支付方式修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:elseif>
		<s:else>
		</s:else>
		<input type="button" class="formButton" value="返 回" onclick="go('buss/payparam!list')"/>
		</div>
	 	</s:form>	 
	 </body> 
</html>