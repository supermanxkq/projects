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
	<script src="js/common.js"></script>
	<script src="js/pubValidate.js"></script>
   <script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">	
	$(document).ready(function (){
	       var method = document.getElementById("method"); 
	        if(method.value=='checkUI'){
	             setInputDisabled();

		          
	            }
	        });
	
	var merNameFlag = false;
	var organIdFlag = false;
	var coopWayFlag = false;
	var conPerNameFlag = false;
	var conPerTeleNoFlag = false;
	var teleNoFlag = false;
	var settlementPeriodFlag = false;
	var settlementWayFlag = false;
	var singleFeeFlag = false;
	var rakeRateFlag = false;
	var feeLimitFlag = false;
	var platformRateFlag = false;
	var acquirerRateFlag = false;
	var organRateFlag = false;
	var settleCountWayFlag = false;
    var flag1 = false;
    var flag2 = false;
    var gnoPrefixFlag = false;
	//银行名标志
	var bankNameFlag=false;
	//开户号标志
	var bankAccountNoFlag=false;
	//开户名标志
	var bankUserFlag=false;
	//手续费率标志
	var chargeRateFlag=false;
	
		var ajaxCardBins = function(){
			
		    var tb = $("#cardBinTb");
		    tb.find('tr:eq(0)').nextAll().remove();  
		    var organId = $.trim($("#organId").val());
			if(organId>0){
				$("#organIdErrorMsg").html("");
				organIdFlag = true;
				var params = {
			        "merchantsDTO.organId" : organId
			    };
			    var actionUrl = "base/merchants!ajaxCardBins";
			    $.ajax( {
			        url : actionUrl,
			        data : params,
			        dataType : "json",
			        cache : false,
			        type : "POST",
			        error : function(textStatus, errorThrown) {
			          	alert("系统ajax交互错误!");
			        },
			        success : function(data, textStatus) {
			          	if (data.length>0){
			        		for(var i=0;i<data.length;i++) {
	        					var tr = $("<tr></tr>");
	        					var html = "";
	        					html += "<td><input type='checkbox' name='merchantsDTO.cardBinStatuss' id='id' value='"+data[i].key+"' /></td>";
	        					html += "<td>"+data[i].value+"<input type='hidden' name='merchantsDTO.cardBins' value='"+data[i].key+"'/></td>";
	        					html += "<td>"+$("#isSalePointStr").val()+"</td>";
	        					tr.html(html);
			          			tb.append(tr);
	        				}
	        			}
			        }
			    });
			}
			else{
				organIdFlag = false;
				}
			
		}

		function organChange(){
                var organId = $("#organId").val();
                if(organId>-1){
                     organIdFlag = true;
                     $("#organIdErrorMsg").html("");
                  }
   
			}
		
		  //结算方式变更后续操作
		function settWayChange(){
			changeRate();
		   var settlementWay = $("#settlementWay").val();
		   if(settlementWay>-1){
			   settlementWayFlag = true;
			   $("#settWayErrorMsg").html("");
		    }
		   else{
			   settlementWayFlag = false;
			   }

		    
		   if(settlementWay=='0'){//按消费金额
		      $("#singleFee").attr("disabled","disabled");
		      $("#rakeRate").attr("disabled",false);
			  $("#feeLimit").attr("disabled",false);
			  changeReate('0');
		   }
		   else if(settlementWay=='1'){ //按消费笔数
			   $("#singleFee").attr("disabled",false);
			   $("#rakeRate").attr("disabled","disabled");
			   $("#feeLimit").attr("disabled","disabled");
			   changeReate('0');
		   }
		   else if(settlementWay=='-1'){//请选择
			  
			   $("#rakeRate").attr("disabled","disabled");
			   $("#feeLimit").attr("disabled","disabled");
			   $("#singleFee").attr("disabled","disabled");
			   changeReate('1');
		   }
		   else{//无需结算
			   $("#rakeRate").attr("disabled","disabled");
			   $("#feeLimit").attr("disabled","disabled");
			   $("#singleFee").attr("disabled","disabled");
			   changeReate('1');
		   }
		}

		//结算方式变更 .费率初始 
		function changeRate(){
     			   $("#rakeRate").val("0");;
     			   $("#feeLimit").val("0");
     			   $("#singleFee").val("0");
			}
		
		function changeReate (flag){
                 if(flag =='0'){//禁用分成比率
                    $("#platformRate").attr("disabled",false);
                    $("#acquirerRate").attr("disabled",false);
                    $("#organRate").attr("disabled",false);
                }
                 else{
                               //启用分成比率 
                	 $("#platformRate").attr("disabled","disabled");
                     $("#acquirerRate").attr("disabled","disabled");
                     $("#organRate").attr("disabled","disabled");
                }
			}
		function coopWayChange(){
			var coopWay = $("#coopWay").val();
			if(coopWay>-1){
				coopWayFlag = true;
				$("#coopWayErrorMsg").html("");
			    }
			   else{
				   coopWayFlag = false;
				   }
			}
	
        //检验商户名称
		function merNameBlur(obj){
              var type = ["isNull"];
              var errorMsg = ["商户名称不能为空!"];
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

		function rakeRateBlur(obj){
			   if($("#rakeRate").val()==null||$("#rakeRate").val()==""){
				 
                     $("#rakeRate").val(0);
				  }
               var flagRate = false;
               var rakeFlag = false;
		       var type = ["isRate"];
		       var errorMsg = ["手续费率不能大于1!"];
		       
				if($("#rakeRate").val()>1){
                   rakeFlag = false;
                   pubErrorShow($("#rakeRateErrorMsg"),"手续费率不能大于1!");
				}
				else{
					rakeFlag = true;
                             pubErrorShow($("#rakeRateErrorMsg"),"");
				} 
				flagRate = showErrorMsg(obj,type,errorMsg,"rakeRateErrorMsg","rakeRateErrorMsg");
				
				if(flagRate&&rakeFlag){
					rakeRateFlag = true;
				}
				else{
					rakeRateFlag = false;
			    }

			}
		
       function conPerNameBlur(obj){
              var type = ["isNull","isRealName"];
              var errorMsg = ["联系人姓名不能为空!","联系人姓名格式错误!"];
              flag1 = showErrorMsg(obj,type,errorMsg,"conPerNameErrorMsg","conPerNameWarnMsg");
              flag2 = checkLenMsg(obj,"联系人姓名不超过30个字符!","conPerNameErrorMsg","",30);
              if(flag1&&flag2){
                  conPerNameFlag = true;
              }
         }

        function gnoPrefixBlur(obj){
                var type = ["isNull"];
                var errorMsg = ["商品前缀不能为空!"];
                gnoPrefixFlag = showErrorMsg(obj,type,errorMsg,"gnoPrefixErrorMsg","gnoPrefixErrorMsg");
            }


       
       function conPerTeleNoBlur(obj){
              var type = ["isNull","isMobile"];  
              var errorMsg = ["手机号码不能为空!","手机号码格式错误!"];
              conPerTeleNoFlag = showErrorMsg(obj,type,errorMsg,"conPerTeleNoErrorMsg","conPerTeleNoWarnMsg");
           }
	  function teleNoBlur(obj){
             var type = ["isNull","isTel"];
             var errorMsg = ["固话号码不能为空!","固话号码格式错误!"];
             teleNoFlag = showErrorMsg(obj,type,errorMsg,"teleNoErrorMsg","teleNoWarnMsg");
		  }
	  function settPeriodBlur(obj){
		  if(${user_session.userLevel}!=2){
            var type = ["isNull","fullNumber"];
            var errorMsg = ["结算周期不能为空!","结算周期应为整型数字!"]; 
            settlementPeriodFlag = showErrorMsg(obj,type,errorMsg,"settPriodErrorMsg","settPriodWarnMsg");
		  }
		}

		//验证银行名
		function checkBankName(obj){
			var name=$.trim($("#bankName").val());
			if(name.length==0){
				$("#bankNameErrorMsg").show();
				bankNameFlag=false;
			}else{
				$("#bankNameErrorMsg").hide();	
				bankNameFlag=true;			
			}
		}
		//验证开户号
		function checkBankAccountNo(obj){
			var reg=/^\d{15,19}$/;
			var val=$.trim(obj.value);
			var error=$("#bankAccountNoErrorMsg");
			if(val.length==0){
				error.html("银行卡号不能为空!");
				error.show();
				bankAccountNoFlag=false;
			}else if(reg.test(val)){
				error.hide();
				bankAccountNoFlag=true;
			}else{
				error.html("卡号为15到19位数字");
				error.show();
				bankAccountNoFlag=false;
			}
			//var type = ["isNull","isBankAccount"];
	       // var errorMsg = ["银行卡号不能为空!","卡号15到19位数字!"]; 
	       // bankAccountNoFlag = showErrorMsg(obj,type,errorMsg,"bankAccountNoErrorMsg","bankAccountNoErrorMsg");
		}
		//验证开户名
		function checkBankUser(obj){
			var name=$.trim($("#bankUser").val());
			if(name.length==0){
				$("#bankUserErrorMsg").show();
				bankUserFlag=false;
			}else{
				$("#bankUserErrorMsg").hide();	
				bankUserFlag=true;			
			}
		}
		/*验证支付手续费率
      	function checkChargeRate(){
			  var type = ["isNull","isDoubleRate"];
              var errorMsg = ["手续费率不能为空!","手续费率格式错误!"];
              chargeRateFlag = showErrorMsg(document.getElementById("chargeRate"),type,errorMsg,"chargeRateErrorMsg","chargeRateErrorMsg");
        }*/
		//修改方法
		var check = function() {
			//var settlementWay = $("#settWay").val();
			var singleFee = $("#singleFee").val();
			var rakeRate = $("#rakeRate").val();
			var feeLimit = $("#feeLimit").val();
		    var platformRate = $("#platformRate").val();
		    var acquirerRate = $("#acquirerRate").val();
		    var organRate = $("#organRate").val();
		    //var settelCountWay = $("#settelCountWay").val();
		    //var settlementWay = $("#settlementWay").val();
		    var coopWay = $("#coopWay").val();
            
		    var errorMsg =["请选择发卡机构!"];
		    var type =["isSelectNull"];
		    var organId = getEle("organId");		    
		    if(${user_session.userLevel}!=2){
			   organIdFlag = showErrorMsg(organId,type,errorMsg,"organIdErrorMsg","organIdWarnMsg");
		    }else{
                  organIdFlag = true;
			    }
       
		  //  var setmentWay = getEle("settlementWay");
		  //  var settWayErrorMsg = ["请选择结算方式!"];
          //  settlementWayFlag = showErrorMsg(setmentWay,type,settWayErrorMsg,"settWayErrorMsg","settWayWarnMsg");
			if(${user_session.userLevel}!=2){
	            var coopWayEMsg = ["请选择合作方式!"];
    	        var coopWayType = ["isSelectNull"];
        	    var coopWayEle = getEle("coopWay");
            	coopWayFlag = showErrorMsg(coopWayEle,coopWayType,coopWayEMsg,"coopWayErrorMsg","coopWayWarnMsg");
			
            
	            var conPerName = getEle("conPerName");
	            conPerNameBlur(conPerName);
	
	            var conPerTeleNo = getEle("conPerTeleNo");
	            conPerTeleNoBlur(conPerTeleNo);
	            var merName = getEle("merName");
    	         merNameBlur(merName);
			}
            var teleNo = getEle("teleNo");
            teleNoBlur(teleNo);

            // var settlementPeriod = getEle("settPeriod");
            //settPeriodBlur(settlementPeriod);

            var gnoPrefix = getEle("gnoPrefix");
            gnoPrefixBlur(gnoPrefix);
            checkBankUser(document.getElementById("bankUser"))
            checkBankAccountNo(document.getElementById("bankAccountNo"));
            checkBankName(document.getElementById("bankName"));

         	if(${user_session.userLevel}!=2){
         		 if(!(merNameFlag&&conPerNameFlag
                         &&conPerTeleNoFlag&&teleNoFlag
                         &&organIdFlag
                         &&bankUserFlag&&bankNameFlag&&bankAccountNoFlag&&gnoPrefixFlag)){
                        alert("商户基本信息   填写信息有误，*号信息为必填项!");
                        return false;
                  }	
                 return true;
             }else{
            	 if(!(teleNoFlag
                         &&organIdFlag
                         &&bankUserFlag&&bankNameFlag&&bankAccountNoFlag&&gnoPrefixFlag)){
                        alert("商户基本信息   填写信息有误，*号信息为必填项!");
                        return false;
                  }	
                 return true;
             }
		}; 
	
		function getEle(id){
                  return document.getElementById(id);
			}
	</script> 
</head>
<body>
	<s:include value="/WEB-INF/page/base/merchants/preMerchantsHelp.jsp"></s:include>
	<s:hidden name="merchantsDTO.isSalePointStr" id="isSalePointStr"/>
	<div class="List_tit">商户信息</div>
	<s:if test="#session.user_session.userLevel!=2">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
				<th align="right" width="20%">商户编号：</th>
		        <td width="30%"><s:property value="merchantsDTO.merId"/><s:hidden name="merchantsDTO.merId" id="merId"/></td>
		        <th align="right" width="20%"><span class="Color5">* </span>商户名称：</th>
		        <td width="30%">
		        	<s:textfield name="merchantsDTO.merName" onkeyup = "allowEnCnNu(this);" onfocus="merNameFocus(this);" onblur="merNameBlur(this);" id="merName" maxlength="20" cssClass="formInput"/> <label id="warnMsg" style="display: none;"></label> <label id="errorMsg" style="display: none;"></label>
		        </td>
			</tr>
			<tr>
				<th align="right"><span class="Color5">* </span>发卡机构：</th>
		        <td>
		        	<s:if test="method=='addSave'&&#session.user_session.userLevel==0">
			        	<s:select name="merchantsDTO.organId" id="organId" list="#request.organsValues" headerKey="-1" headerValue="请选择"  listKey="key" listValue="value" onchange="organChange();" cssClass="formSelect"/><label id="organIdErrorMsg" style="display: none;"></label> 
		        	</s:if>
		        	<s:else>
		        		<s:property value="merchantsDTO.organName"/><input type="hidden" name="merchantsDTO.organId" id="organId" value="${merchantsDTO.organId }"/>
		        	</s:else>
		        </td>
		        <th align="right">状 态：</th>
		        <td><s:select id="status" name="merchantsDTO.status" list="#request.statusValues" listKey="key" listValue="value" cssClass="formSelect"/></td>
			</tr>
			<tr>
		      	<th align="right"><span class="Color5">* </span>合作方式：</th>
		        <td><s:select id="coopWay" name="merchantsDTO.coopWay" list="#request.coopWay" headerKey="-1" headerValue="请选择" listKey="key" listValue="value" onchange="coopWayChange();" cssClass="formSelect"/> <label id="coopWayErrorMsg" style="display: none;"></label><label id="coopWayWarnMsg" style="display: none;"></label></td>
		      	<th align="right"><span class="Color5">* </span>手续费率：</th>
		        <td><s:textfield name="merchantsDTO.rakeRate" id="rakeRate" maxlength="6" onblur="rakeRateBlur(this);" onkeyup="replaceToNumPoint(this);" cssClass="formInput"/> <label id="rakeRateErrorMsg" style="display: none;"></label></td>
		   	</tr>
			<tr>
		      	<th align="right"><span class="Color5">* </span>法人代表：</th>
		        <td><s:textfield name="merchantsDTO.conPerName"  onkeyup = "allowEnCnNu(this);" onblur="conPerNameBlur(this);" id="conPerName" maxlength="20" cssClass="formInput"/> <label id="conPerNameErrorMsg" style="display: none;"></label></td>
		      	<th align="right"><span class="Color5">* </span>手机号码：</th>
		        <td><s:textfield name="merchantsDTO.conPerTeleNo"  onkeyup= "replaceToNum(this);" onblur="conPerTeleNoBlur(this);"  id="conPerTeleNo" maxlength="11" cssClass="formInput"/> <label id="conPerTeleNoErrorMsg" style="display: none;"></label></td>
		   	</tr>
		 	<tr>
		      	<th align="right"><span class="Color5">* </span>固话号码：</th>
		      	
		        <td><s:textfield name="merchantsDTO.teleNo" id="teleNo" onfocus=" this.style.imeMode='inactive' " onblur="teleNoBlur(this);" maxlength="11" cssClass="formInput"/> <label id="teleNoErrorMsg" style="display: none;"></label></td>
		       <th align="right">所在地区：</th>
		        <td colspan="3">

				    <s:select name="merchantsDTO.areaId" id="areaId" list="#request.areaValues" listKey="key" listValue="value" cssClass="formSelect"/>
				</td>
		   	</tr>
			<tr>
		      	<th align="right">地 址：</th>
		        <td><s:textfield name="merchantsDTO.address" id="address" maxlength="20" cssClass="formInput"/></td>
		      	<th align="right">邮政编码：</th>
		        <td><s:textfield name="merchantsDTO.zip" id="zip" maxlength="6"  onkeypress="return onlyNum(this);" onkeyup="replaceToNum(this);" cssClass="formInput"/></td>
		 	</tr>
		 	<%--<tr>
		      	<th align="right">是否经销商：</th>
		        <td><s:select name="merchantsDTO.agentSign" id="agentSign" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect"/></td>
		        <th align="right">上级经销商：</th>
		        <td><s:textfield name="merchantsDTO.preMerName" id="preMerName" readonly="readonly" cssClass="formInput"/><s:hidden name="merchantsDTO.preMerId" id="preMerId"/><img alt="查找经销商" src="images/search.gif" style="cursor:pointer;" onclick="showPreMerHelp();"/></td>
		  	</tr>
			--%><tr>
		      	<th align="right">是否充值限额：</th>
		        <td><s:select name="merchantsDTO.tranLimitSign" id="tranLimitSign" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect"/></td>
		        <th align="right">合 同 号：</th>
		        <td><s:textfield name="merchantsDTO.conNo" id="conNo" maxlength="20" cssClass="formInput"/></td>
			</tr>
			<tr>
				<th align="right">是否私户：</th>
				<td><s:select name="merchantsDTO.privateSign" id="privateSign" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect"/></td>
		      	<th align="right">上次结算时间：</th>
		        <td><s:date format="yyyy-MM-dd HH:mm:ss"  name="merchantsDTO.lastSettTime"/></td>
			</tr>
		  	<tr>
		      	<th align="right"><span class="Color5">* </span>开户银行：</th>
		        <td><s:textfield name="merchantsDTO.bankName" onblur="checkBankName(this);" id="bankName" maxlength="40" cssClass="formInput"/>
		        	<span id="bankNameErrorMsg" class="errorMsg" style="display: none;">请填写真实的信息！</span>
		        </td>
		        <th align="right"><span class="Color5">* </span>开户账号：</th>
		        <td><s:textfield name="merchantsDTO.bankAccountNo"  onblur="checkBankAccountNo(this);" id="bankAccountNo" maxlength="19" cssClass="formInput"/>
		        	<span id="bankAccountNoErrorMsg" class="errorMsg"  style="display: none;"></span>
		        </td>
		   	</tr>
		  	<tr>
		      	<th align="right"><span class="Color5">* </span>开户名称：</th>
		        <td><s:textfield name="merchantsDTO.bankUser"  onblur="checkBankUser(this);" id="bankUser" maxlength="30" cssClass="formInput"/>
		        	<span id="bankUserErrorMsg" class="errorMsg"  style="display: none;">请填写真实的信息！</span>
		        </td>
		        <th align="right">更新时间：</th>
		        <td><s:date format="yyyy-MM-dd HH:mm:ss" name="merchantsDTO.updateTime"/></td>
		   	</tr>
		   	<tr>
		       	<th align="right">是否开票：</th>
			    <td><s:select name="merchantsDTO.invSign" id="invSign" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect"/></td>
		 	</tr>

	 	</table>
</s:if>
	<s:else>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
				<th align="right" width="20%">商户编号：</th>
		        <td width="30%"><s:property value="merchantsDTO.merId"/><s:hidden name="merchantsDTO.merId" id="merId"/></td>
		        <th align="right" width="20%"><span class="Color5">* </span>商户名称：</th>
		        <td width="30%">
		        	<s:property value="merchantsDTO.merName"/>
		        </td>
			</tr>
			<tr>
				<th align="right"><span class="Color5">* </span>发卡机构：</th>
		        <td>
		        	<s:property value="#session.user_session.organName"/>
		        </td>
		        <th align="right">状 态：</th>
		        <td><s:select id="status" name="merchantsDTO.status" list="#request.statusValues" listKey="key" listValue="value" cssClass="formSelect" disabled="true"/></td>
			</tr>
			<tr>
		      	<th align="right"><span class="Color5">* </span>联 系 人：</th>
		        <td><s:textfield name="merchantsDTO.conPerName" id="conPerName" maxlength="20" cssClass="formInput"/></td>
		      	<th align="right"><span class="Color5">* </span>手机号码：</th>
		        <td><s:textfield name="merchantsDTO.conPerTeleNo" id="conPerTeleNo" maxlength="11" cssClass="formInput"/></td>
		   	</tr>
		 	<tr>
		      	<th align="right">固话号码：</th>
		        <td><s:textfield name="merchantsDTO.teleNo" id="teleNo" maxlength="13" cssClass="formInput"/></td>
		        <th align="right">所在地区：</th>
		        <td>
				    <s:select name="merchantsDTO.areaId" id="areaId" list="#request.areaValues" listKey="key" listValue="value" cssClass="formSelect" disabled="true"/>
				</td>
		   	</tr>
		   	<tr>
		      	
				<th align="right">是否代理商：</th>
		        <td><s:select id="dlsFlag" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect" disabled="true"/></td>
		      	<th align="right">地 址：</th>
		        <td><s:textfield name="merchantsDTO.address" id="address" maxlength="20" cssClass="formInput"/></td>
			</tr>
			<tr>
		      	<th align="right">邮政编码：</th>
		        <td><s:textfield name="merchantsDTO.zip" id="zip" onkeypress="return onlyNum(this);" onkeyup="replaceToNum(this);" maxlength="6" cssClass="formInput"/></td>
		      	<th align="right">是否充值限额：</th>
		        <td><s:select id="tranLimitSw" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect" disabled="true"/></td>
		 	</tr>
			<tr>
		        <th align="right">合 同 号：</th>
		        <td><s:property value="merchantsDTO.conNo"/></td>
		      	<th align="right">上次结算时间：</th>
		        <td><s:date format="yyyy-MM-dd HH:mm:ss" name="merchantsDTO.lastSettTime"/></td>
			</tr>
			<tr>
		        <th align="right"><span class="Color5">* </span>开户名称：</th>
		        <td><s:textfield name="merchantsDTO.bankUser"  onblur="checkBankUser(this);" id="bankUser" maxlength="30" cssClass="formInput"/>
		        	<span id="bankUserErrorMsg" class="errorMsg"  style="display: none;">请填写真实的信息！</span>
		        </td>
		      	<th align="right"><span class="Color5">* </span>开户银行：</th>
		        <td><s:textfield name="merchantsDTO.bankName" onblur="checkBankName(this);"  id="bankName" maxlength="40" cssClass="formInput"/>
		        	
		        	<span id="bankNameErrorMsg" class="errorMsg"  style="display: none;">请填写真实的信息！</span>
		        </td>
			</tr>
		  	<tr>
		        <th align="right"><span class="Color5">* </span>开户账号：</th>
		        <td><s:textfield name="merchantsDTO.bankAccountNo" onblur="checkBankAccountNo(this);" id="bankAccountNo" maxlength="19" cssClass="formInput"/>
		        	
		        	<span id="bankAccountNoErrorMsg" class="errorMsg"  style="display: none;"></span>
		        </td>
		        <th align="right">更新时间：</th>
		        <td><s:date format="yyyy-MM-dd HH:mm:ss" name="merchantsDTO.updateTime"/></td>
		   	</tr>
	 	</table>
	 </s:else>
	

</body> 
</html>