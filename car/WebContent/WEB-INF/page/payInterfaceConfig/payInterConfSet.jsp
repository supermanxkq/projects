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
	<title>支付接口配置</title>
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
	<script src="js/datepicker/WdatePicker.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
		var psNameFlag=false;
		var checkPsNameFlag=false;
		var payOrgNameFlag=false;
		var psUrlFlag=false;
		var psAccountFlag=false;
		var psPwdFlag=false;
		var psRateFlag=false;
		var secretKeyFlag=false;
		var descrFlag=false;
		
		//验证接口名称
		function psNameBlur(obj){
		   var type=["isNull","isCompName"];
		   var errorMsg=["接口名称不能为空 ！","接口名称格式错误！"];
		   psNameFlag=showErrorMsg(obj,type,errorMsg,"psNameErrorMsg","psNameErrorMsg");
		   return psNameFlag;
		}
		
		//验证接口名称是否重复
      function checkName(obj){
      var psFlag=psNameBlur(obj);
      if(!psFlag){
         return false;
      }
      var psName = $("#psName").val();
      var psId = $("#psId").val();
      var method = $("#method").val();
      var params = {
           "payInterConfDTO.psName":psName,
           "payInterConfDTO.psId":psId,
           "method":method
        }
        var actionUrl = "payInterface/payInterfaceConfig!checkName";
		 $.ajax({ 
		 		async:false, 
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
			        	   $("#psNameErrorMsg").show();
			        	   $("#psNameErrorMsg").html("接口名称已经存在！");
			        	   $("#psNameErrorMsg").addClass("errorMsg");
			        	   checkPsNameFlag = false;
			        	   
					    }else{
					        checkPsNameFlag = true;
					      		    	
					    }
			        }
			    });
      }
		
		//验证支付机构
		function payOrgNameBlur(obj){
		   var type=["isNull","isCompName"];
		   var errorMsg=["支付机构名称不能为空 ！","支付机构名称格式错误！"];
		   payOrgNameFlag=showErrorMsg(obj,type,errorMsg,"payOrgNameErrorMsg","payOrgNameErrorMsg");
		   return payOrgNameFlag;
		}
		
		//验证接口地址
		function psUrlBlur(obj){
		   var type=["isNull","isUrl"];
		   var errorMsg=["接口地址不能为空 ！","接口地址格式错误！"];
		   psUrlFlag=showErrorMsg(obj,type,errorMsg,"psUrlErrorMsg","psUrlErrorMsg");
		   return psUrlFlag;
		}
		
		//验证账户号
	    function checkPsAccount(){
	                var psAccount=$.trim($("#psAccount").val());
	                var   psPwd=$.trim($("#psPwd").val());
	 				if(psAccount.length==0){
	 							$("#psAccountErrorMsg").html("账户号值不能为空！");
	 							$("#psAccountErrorMsg").show();
	 							psAccountFlag=false;
	 	 			}else{
								$("#psAccountErrorMsg").hide();
								psAccountFlag=true;
	 	 	 		}
	 	 	 		if(psPwd.length!=0){
	 	 	 		   checkPsPwd();
	 	 	 		}
	         }
	                      
	   //验证口令
        function checkPsPwd(){
	                var   psPwd=$.trim($("#psPwd").val());
	 				if(psPwd.length==0){
	 							$("#psPwdErrorMsg").html("口令值不能为空！");
	 							$("#psPwdErrorMsg").show();
	 							psPwdFlag=false;
	 	 			}else{
	 							$("#psPwdErrorMsg").hide();
	 							psPwdFlag=true;
	 	 	 		}
	         }
	         
	     //验证手续费率
	    function psRateBlur(obj){
		       var psRateVal=$("#psRate").val();
		 	    if(psRateVal>=1){
		 	       $("#psRateErrorMsg").show();
		 	       $("#psRateErrorMsg").addClass("errorMsg");
		 	       $("#psRateErrorMsg").html("手续费率应该小于1！");
		 	       return false;
		 	    }
			   var type=["isNull","isRate"];
			   var errorMsg=["手续费率不能为空 ！","手续费率格式错误！"];
			   psRateFlag=showErrorMsg(obj,type,errorMsg,"psRateErrorMsg","psRateErrorMsg");
			   return psRateFlag;
			}
			
		//验证密钥
        function checkSecretKey(){
	                var   secretKey=$.trim($("#secretKey").val());
	 				if(secretKey.length==0){
	 							$("#secretKeyErrorMsg").html("口令值不能为空！");
	 							$("#secretKeyErrorMsg").show();
	 							secretKeyFlag=false;
	 	 			}else{
	 							$("#secretKeyErrorMsg").hide();
	 							secretKeyFlag=true;
	 	 	 		}
	         }
	         
	     //验证描述框
		var checkDescr=function(){
			var descrLength = $("#descr").val();
			if(descrLength.length >= 255){
				$("#descrMsg").show();
				$("#descrMsg").addClass("errorMsg");
				$("#descrMsg").html("255以内字符!");
				descrFlag= false;
				}else{
				$("#descrMsg").hide();
				descrFlag= true;
				}
			}
	
		//保存时对所校验信息检查是否正确填写
 	    function check(){
	 	    var psName=document.getElementById("psName");
	 	    var payOrgName=document.getElementById("payOrgName");
	 	    var psUrl=document.getElementById("psUrl");
	 	    var psRate=document.getElementById("psRate");
	 	    
	 	    
 	    	payOrgNameBlur(payOrgName);
 	    	psUrlBlur(psUrl);
 	    	psRateBlur(psRate);
 	    	checkPsAccount();
 	    	checkPsPwd();
 	        checkSecretKey();
 	    	checkDescr();
 	    	checkName(psName);
 	    	if(psNameFlag && payOrgNameFlag && psUrlFlag &&psAccountFlag && psPwdFlag&&
 	    	   psRateFlag && secretKeyFlag && descrFlag &&checkPsNameFlag){
 	    	 return true;
 	    	}else{
 	    	alert("页面信息填写有误，请按照提示进行修改！");
        		return false;
 	    	}
 	}
 	
 	         //查看方法设置输入框为disabled
 	         $(document).ready(function(){
 	             var method=$("#method").val();
 	             if(method=='checkUI'){
 	                 setInputDisabled();
 	                 $("#status").attr("disabled","true");
 	                 $("#psType").attr("disabled","true");
 	                 $("#currency").attr("disabled","true");
 	                 $("#descr").attr("readonly","true");
 	                 $("#descr").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
 	                 
 	             }
 	         });
 	      
    </script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt;支付接口配置&gt;&gt; 支付接口配置
	</div>
	
	<s:form action="payInterface/payInterfaceConfig" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>	
		  <table class="formTable">
			<tr>				
		  	    <th align="right" width="20%"><span class="Color5">* </span>接口名称：</th>
		  		<td width="30%"><s:textfield name="payInterConfDTO.psName" id="psName" maxlength="60" 
		  		                        cssStyle="width:150px;" cssClass="formInput" theme="simple" onblur="checkName(this);"/>
		  		<s:hidden name="payInterConfDTO.psId" id="psId"></s:hidden>
		  		<s:hidden name="payInterConfDTO.createTime" id="createTime"></s:hidden>
		  		<s:hidden name="payInterConfDTO.updateTime" id="updateTime"></s:hidden>
		  		<label id="psNameErrorMsg" style="display: none;"></label>
			    </td>
		  		<th align="right" width="20%"><span class="Color5">*</span>支付机构：</th>
		  	    <td width="30%"><s:textfield name="payInterConfDTO.payOrgName" id="payOrgName" maxlength="60" cssStyle="width:150px;"
		  	                          cssClass="formInput" theme="simple" onblur="payOrgNameBlur(this);"/>
		  	    <label id="payOrgNameErrorMsg" style="display: none;"></label>
			    </td>
			</tr>
			<tr>			
		  		<th align="right" width="20%"><span class="Color5">* </span>接口地址：</th>
		  		<td width="30%"><s:textfield name="payInterConfDTO.psUrl" id="psUrl"  cssStyle="width:150px;" 
		  		                     cssClass="formInput" theme="simple" maxlength="150" onblur="psUrlBlur(this);"/>
		  		 <label id="psUrlErrorMsg" style="display: none;"></label>
			    </td>
		  		<th align="right" width="20%">接口类型：</th>
				<td width="30%"><s:select name="payInterConfDTO.psType" id="psType" list="#request.type" listKey="key" listValue="value" cssStyle="width:156px;" cssClass="formSelect" theme="simple"/></td>	
			</tr>
			<tr>
			    <th align="right" width="20%"><span class="Color5">* </span>账户号：</th>
		  		<td width="30%"><s:textfield name="payInterConfDTO.psAccount" id="psAccount" maxlength="60" cssStyle="width:150px;" 
		  		                    cssClass="formInput" theme="simple" onblur="checkPsAccount()"/>
		  		<label id="psAccountErrorMsg" style="display:none;" class="errorMsg"></label>
		  		</td>
		  		<th align="right" width="20%"><span class="Color5">* </span>口令：</th>
		  		<td width="30%"><s:password name="payInterConfDTO.psPwd" id="psPwd" maxlength="30" cssStyle="width:150px;" cssClass="formInput" 
		  		                    theme="simple" onblur="checkPsPwd()"/>
		  		<label id="psPwdErrorMsg" style="display:none;" class="errorMsg"></label>
		  		</td>
			</tr>
			<tr>
			    <th align="right" width="20%"><span class="Color5">* </span>手续费率：</th>
		  		<td width="30%"><s:textfield name="payInterConfDTO.psRate" id="psRate" maxlength="6" cssStyle="width:150px;" cssClass="formInput" onkeyup="replaceToNumPoint(this)"
		  		                   theme="simple" onblur="psRateBlur(this);"/>
		  		<label class="Color3">（手续费率小于1，如0.0000）</label>
		  		<label id="psRateErrorMsg" style="display: none;"></label>
		  		</td>
		  		<th align="right" width="20%"><span class="Color5">* </span>密钥：</th>
		  		<td width="30%"><s:textfield name="payInterConfDTO.secretKey" id="secretKey" maxlength="300" cssStyle="width:150px;" cssClass="formInput" 
		  		                   theme="simple" onblur="checkSecretKey()"/>
		  		<label id="secretKeyErrorMsg" style="display:none;" class="errorMsg"></label>
		  		</td>
			</tr>
		 	<tr>
		 		<th align="right" width="20%">使用状态：</th>
				<td width="30%"><s:select name="payInterConfDTO.status" id="status" list="#request.status" listKey="key" listValue="value" cssStyle="width:156px;" cssClass="formSelect" theme="simple"/></td>
				<th align="right" width="20%">支持交易货币类型：</th>
				<td width="30%"><s:select name="payInterConfDTO.currency" id="currency"  list="#request.currType" listKey="key" listValue="value" cssStyle="width:156px;" cssClass="formSelect" theme="simple"/></td>	
		   </tr>
		   <tr>
		   <th align="right">是否支持货到付款：</th>
		 		<td>
		 		 <s:if test="payInterConfDTO.isNotArrivPay==0">
		 		 <input type="radio" name="payInterConfDTO.isNotArrivPay" id="isNotArrivPay1" checked="checked" value='0'/><label>否</label>
		 		 <input type="radio" name="payInterConfDTO.isNotArrivPay" id="isNotArrivPay2" value='1'/><label>是</label>
		 		</s:if>
		 		<s:else>
		 		<input type="radio" name="payInterConfDTO.isNotArrivPay" id="isNotArrivPay1" value='0'/><label>否</label>
		 		 <input type="radio" name="payInterConfDTO.isNotArrivPay" id="isNotArrivPay2" checked="checked"  value='1'/><label>是</label>
		 		</s:else>
		 		 </td>
		   <th align="right">是否支持在线支付：</th>
		 		 <td>
		 		 <s:if test="payInterConfDTO.isNotOnLinePay==0">
		 		 <input type="radio" name="payInterConfDTO.isNotOnLinePay" id="isNotOnLinePay1" checked="checked" value='0' onblur="changeChecked();"/><label>否</label>
		 		 <input type="radio" name="payInterConfDTO.isNotOnLinePay" id="isNotOnLinePay2" value='1' onblur="changeChecked();"/><label>是</label>
		 		 </s:if>
		 		 <s:else>
		 		 <input type="radio" name="payInterConfDTO.isNotOnLinePay" id="isNotOnLinePay1" value='0' /><label>否</label>
		 		 <input type="radio" name="payInterConfDTO.isNotOnLinePay" id="isNotOnLinePay2" value='1'checked="checked" /><label>是</label>
		 		 </s:else>
		 		 </td>
		   </tr>
		   <tr>
				<th align="right">支付接口配置描述：</th>
					<td colspan="3">
						<s:textarea name="payInterConfDTO.descr" id="descr" 
							onblur="checkDescr();" cssClass="formTextarea" cols="80" rows="60" />
						<span class="Color5"><label id="descrMsg" style="display: none;"></label> </span>
					</td>
				</tr>
		  </table>
	 <div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1801-02' value='支付接口配置添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:if test="method=='editSave'">
			<my:permission key='sy-1801-03' value='支付接口配置修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:if test="method=='checkUI'"></s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('payInterface/payInterfaceConfig!list')"/>
	 </div>
	 </s:form>
	
</body>
</html>