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
	<title>商城业务参数配置</title>
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
		//单选框改变值后赋值的方法
		$(document).ready(function (){
	
			var sendBusinessRecom=$("#sendBusinessRecom").val();
			var noDeliveMerRecom = $("#noDeliveMerRecom").val();
			var method=$("#method").val();
			if(sendBusinessRecom==1){
			    document.getElementById("sendBusinessRecom1").checked=false;
			    document.getElementById("sendBusinessRecom2").checked=true;
			}
			
 			if(noDeliveMerRecom==1){
 				document.getElementById("noDeliveMerRecom1").checked = false;
 				document.getElementById("noDeliveMerRecom2").checked = true;
 			}
 			var th = document.getElementById("thisShow");
	        var td = document.getElementById("tdisShow");
	        
            if(document.getElementById("noDeliveMerRecom2").checked){               	 
                th.style.display = "";
                td.style.display = "";                                                          
            }
            else{
            	$("#maxDelayDays").val("");
            	th.style.display = "none";
            	td.style.display = "none";
            }
             if(method.value=='addSave'){
             	$("#noDeliveMerRecom").val(0);
             	$("#sendBusinessRecom").val(0);
             }
	     })
		
		//控制最大延迟发货天数框是否显现的方法
		var ctrlIsShow = function(){
			
	        var th = document.getElementById("thisShow");
	        var td = document.getElementById("tdisShow");
	        
            if(document.getElementById("noDeliveMerRecom2").checked){               	 
                th.style.display = "";
                td.style.display = "";                                                          
            }
            else{
            	th.style.display = "none";
            	td.style.display = "none";
            }
            
            var noDeliveMerRecom="";
			var noDeliveMerRecoms=document.getElementsByName("noDeliveMerRecoms");
			for(var i=0;i<noDeliveMerRecoms.length;i++){
				if(noDeliveMerRecoms[i].checked){
					noDeliveMerRecom = noDeliveMerRecoms[i].value;
				}
			}			
			$("#noDeliveMerRecom").val(noDeliveMerRecom);            
	     }
	     //修改单选框值的方法
	     var changeChecked=function(){
	        var sendBusinessRecom="";
	        var sendBusinessRecoms=document.getElementsByName("sendBusinessRecoms");
	        for(var i=0;i<sendBusinessRecoms.length;i++){
	            if(sendBusinessRecoms[i].checked){
	              sendBusinessRecom=sendBusinessRecoms[i].value;
	            }
	        }
	        $("#sendBusinessRecom").val(sendBusinessRecom);
	     }
	     
	     var limtReceAutoFlag=false;
	     var limtAfterSaleFlag=false;
	     var rateFlag=false;
	     var maxDelayDaysFlag=false;
	     //验证自动收货期限
	     function limtReceAutoBlur(obj){
		   var type=["isNull"];
		   var errorMsg=["收货期限不能为空 ！"];
		   limtReceAutoFlag = showErrorMsg(obj,type,errorMsg,"limtReceAutoErrorMsg");
		   return limtReceAutoFlag;
		}
		//验证售后保障期限
		function limtAfterSaleBlur(obj){
		   var type=["isNull"];
		   var errorMsg=["售后保障期限不能为空 ！"];
		   limtAfterSaleFlag = showErrorMsg(obj,type,errorMsg,"limtAfterSaleErrorMsg");
		   return limtAfterSaleFlag;
		}
		//验证积分赠送比率
		function rateBlur(obj){
		       var rateVal=$("#rate").val();
		 	    if(rateVal>=1){
		 	       $("#rateErrorMsg").show();
		 	       $("#rateErrorMsg").addClass("errorMsg");
		 	       $("#rateErrorMsg").html("赠送比率应该小于1！");
		 	       return false;
		 	    }
			   var type=["isNull","isRate"];
			   var errorMsg=["赠送比率不能为空 ！","赠送比率格式错误！"];
			   rateFlag=showErrorMsg(obj,type,errorMsg,"rateErrorMsg","rateErrorMsg");
			   return rateFlag;
			}
			
		//验证最大延迟发货 天数
		function maxDelayDaysBlur(obj){
		
		   var type=["isNull"];
		   var errorMsg=["最大延迟发货天数不能为空 ！"];
		   maxDelayDaysFlag = showErrorMsg(obj,type,errorMsg,"maxDelayDaysErrorMsg");
		   return maxDelayDaysFlag;
		}
		//保存时对所校验信息检查是否正确填写
		function check(){
	 	    var limtReceAuto=document.getElementById("limtReceAuto");
	 	    var limtAfterSale=document.getElementById("limtAfterSale");
	 	    var rate=document.getElementById("rate");
	 	    var maxDelayDays=document.getElementById("maxDelayDays");
	 	    
 	    	limtReceAutoBlur(limtReceAuto);
 	    	limtAfterSaleBlur(limtAfterSale);
 	    	rateBlur(rate);
 	    	maxDelayDaysBlur(maxDelayDays);
 	    	if(document.getElementById("noDeliveMerRecom2").checked){
 	    	  if(limtReceAutoFlag && limtAfterSaleFlag && rateFlag && maxDelayDaysFlag){
 	    	  return true;
 	    	}else{
 	    	alert("页面信息填写有误，请按照提示进行修改！");
        		return false;
 	    	}
 	    	}else{
 	    	   if(limtReceAutoFlag && limtAfterSaleFlag && rateFlag){
 	    	   return true;
 	    	}else{
 	    	alert("页面信息填写有误，请按照提示进行修改！");
        		return false;
 	    	}
 	    	}
 	}
		
    </script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt;商城业务参数配置&gt;&gt;商城业务参数
	</div>
	
	<s:form action="buss/serviceparams" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>	
		  <table class="formTable">
		    <tr>
		    	<s:hidden name="bussParamsConfigDTO.paramsId"></s:hidden>
		        <th align="right">操作人：</th>
		        <td width="30%"><s:property value="bussParamsConfigDTO.operMan"/></td>
		        <th align="right">操作时间：</th>
		        <td><s:date format="yyyy-MM-dd HH:mm:ss"  name="bussParamsConfigDTO.operTime"/></td>
		    </tr>
		    
			<tr>				
		  	    <th align="right" width="20%"><span class="Color5">* </span>自动确认收货期限：</th>
		  		<td width="30%"><s:textfield name="bussParamsConfigDTO.limtReceAuto" id="limtReceAuto" maxlength="8" 
		  		                        cssStyle="width:150px;" cssClass="formInput" theme="simple" onblur="limtReceAutoBlur(this);"
		  		                         onkeyup = "replaceToNum(this);"/>
		  		                <label id="limtReceAutoErrorMsg" style="display: none;" class="errorMsg"></label></td>
		  		<th align="right" width="20%"><span class="Color5">*</span>统一售后保障期限：</th>
		  	    <td width="30%"><s:textfield name="bussParamsConfigDTO.limtAfterSale" id="limtAfterSale" maxlength="8" cssStyle="width:150px;"
		  	                          cssClass="formInput" theme="simple" onblur="limtAfterSaleBlur(this);" onkeyup = "replaceToNum(this);"/>
		  	                          <label id="limtAfterSaleErrorMsg" style="display: none;" class="errorMsg"></label>
			    </td>
		   </tr>
			
		   <tr>
		       <th align="right" width="20%"><span class="Color5">* </span>消费赠送积分比率：</th>
		  		     <td width="30%"><s:textfield name="bussParamsConfigDTO.rate" id="rate" maxlength="6" 
		  		                        cssStyle="width:150px;" cssClass="formInput" theme="simple" onblur="rateBlur(this);"/>
		  		    <label class="Color3">（赠送比率小于1，如0.0000）</label>
		  		    <label id="rateErrorMsg" style="display: none;"></label>                    
		  		    </td>
		       <th align="right">发送业务提醒信息：</th>
		 		 <td>
		 		 <input type="radio" name="sendBusinessRecoms" id="sendBusinessRecom1" checked="checked" value='0' onclick="changeChecked();"/><label>否</label>
		 		 <input type="radio" name="sendBusinessRecoms" id="sendBusinessRecom2" value='1' onclick="changeChecked();"/><label>是</label>
		 		 <s:hidden id="sendBusinessRecom" name="bussParamsConfigDTO.sendBusinessRecom"/>
		 		 </td>
		   </tr>
		   
		   <tr>	
		       <th align="right">商家发货提醒：</th>
		 		<td>
		 		 <input type="radio" name="noDeliveMerRecoms" id="noDeliveMerRecom1" checked="checked" value='0' onclick="ctrlIsShow();"/><label>否</label>
		 		 <input type="radio" name="noDeliveMerRecoms" id="noDeliveMerRecom2" value='1' onclick="ctrlIsShow();"/><label>是</label>
		 		 <s:hidden id="noDeliveMerRecom" name="bussParamsConfigDTO.noDeliveMerRecom"/>
		 		</td>	
		 		 
		  		<th id="thisShow" style="display:none;" align="right" width="20%"><span class="Color5">* </span>最大延迟发货天数：</th>
		  		<td id="tdisShow" style="display:none;" width="30%"><s:textfield name="bussParamsConfigDTO.maxDelayDays" id="maxDelayDays"  cssStyle="width:150px;" 
		  		                     cssClass="formInput" theme="simple" maxlength="8" onkeyup = "replaceToNum(this);" onblur="maxDelayDaysBlur(this);"/>
			    <label id="maxDelayDaysErrorMsg" style="display: none;"></label>      
			    </td>
		   </tr>
		  </table>
		   <div class="List_tit">会员成长值规则</div>
              <table width="96%" id="MembersTb" class="listTable" cellpadding="0" cellspacing="0">
              <s:hidden name="bussParamsConfigDTO.mgcId" id="mgcId"></s:hidden>
                 <tr>
						<th width="5%">来源名称</th>
			            <th width="5%">成长值</th>
			            <th width="5%">来源名称</th>
			          	<th width="5%">成长值</th>
			     </tr>
						<tr>
						    <th>登录</th>
							<td><input name="bussParamsConfigDTO.loginValue" id="loginValue" maxlength="8" value="${bussParamsConfigDTO.loginValue}" 
							class="xhx"  type="text" onkeypress="onlyNumFloadt(this);" onkeyup = "replaceToNum(this);" />分
							</td>
							<th>实名认证</th>
							<td><input name="bussParamsConfigDTO.realNameAuthValue" id="realNameAuthValue" maxlength="8" value="${bussParamsConfigDTO.realNameAuthValue}" 
							class="xhx"  type="text" onkeypress="onlyNumFloadt(this);" onkeyup = "replaceToNum(this);"/>分</td>
						</tr>
						<tr>
						    <th>服务评价</th>
							<td><input name="bussParamsConfigDTO.serviceEvaluValue" id="serviceEvaluValue" maxlength="8" value="${bussParamsConfigDTO.serviceEvaluValue}" 
							class="xhx"  type="text" onkeypress="onlyNumFloadt(this);" onkeyup = "replaceToNum(this);"/>分</td>
							<th>商品评价</th>
							<td><input name="bussParamsConfigDTO.goodsEvaluValue" id="goodsEvaluValue" maxlength="8" value="${bussParamsConfigDTO.goodsEvaluValue}" 
							class="xhx"  type="text" onkeypress="onlyNumFloadt(this);" onkeyup = "replaceToNum(this);"/>分</td>
						</tr>
						<tr>
						    <th>晒单</th>
							<td><input name="bussParamsConfigDTO.baskValue" id="baskValue" maxlength="8" value="${bussParamsConfigDTO.baskValue}" 
							class="xhx"  type="text" onkeypress="onlyNumFloadt(this);" onkeyup = "replaceToNum(this);"/>分</td>
							<th>推荐客户</th>
							<td><input name="bussParamsConfigDTO.recomClientValue" id="recomClientValue" maxlength="8" value="${bussParamsConfigDTO.recomClientValue}" 
							class="xhx"  type="text" onkeypress="onlyNumFloadt(this);" onkeyup = "replaceToNum(this);"/>分</td>
						</tr>
						<tr>
						    <th>晒搞活动照片</th>
							<td><input name="bussParamsConfigDTO.baskPhotosValue" id="baskPhotosValue" maxlength="8" value="${bussParamsConfigDTO.baskPhotosValue}" 
							class="xhx"  type="text" onkeypress="onlyNumFloadt(this);" onkeyup = "replaceToNum(this);"/>分</td>
							<th>晒活动视频</th>
							<td><input name="bussParamsConfigDTO.baskVideoValue" id="baskVideoValue" maxlength="8" value="${bussParamsConfigDTO.baskVideoValue}" 
							class="xhx"  type="text" onkeypress="onlyNumFloadt(this);" onkeyup = "replaceToNum(this);"/>分</td>
						</tr>
						<tr>
						    <th>邮箱认证</th>
							<td><input name="bussParamsConfigDTO.emailAuthValue" id="emailAuthValue" maxlength="8" value="${bussParamsConfigDTO.emailAuthValue}" 
							class="xhx"  type="text" onkeypress="onlyNumFloadt(this);" onkeyup = "replaceToNum(this);"/>分</td>
							<th>手机认证</th>
							<td><input name="bussParamsConfigDTO.phoneAuthValue" id="phoneAuthValue" maxlength="8" value="${bussParamsConfigDTO.phoneAuthValue}" 
							class="xhx"  type="text" onkeypress="onlyNumFloadt(this);" onkeyup = "replaceToNum(this);"/>分</td>
						</tr>
              </table>
	   <div class="formTableBottom">
				<s:if test="method=='addSave'">
					<my:permission key='sy-6201-01' value='业务参数配置添加'>
						<input id="submitInput" type="submit" class="formButton"
							value="保 存" onclick="return check();" />
					</my:permission>
				</s:if>
				<s:else>
					<my:permission key='sy-6201-02' value='业务参数配置修改'>
						<input id="submitInput" type="submit" class="formButton"
							value="保存" onclick="return check();" />
					</my:permission>
				</s:else>
				<input type="button" class="formButton" value="返 回"
					onclick="go('buss/serviceparams!list')" />
			</div>
	 </s:form>
	
</body>
</html>