<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<base href="<%=basePath%>" />
		<title>卡BIN管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.4.2.min.js"></script>
		<link href="js/jquery/css/jquery.ui.all.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery/jquery.ui.core.js"></script>
		<script src="js/jquery/jquery.ui.widget.js"></script>
		<script src="js/jquery/jquery.ui.mouse.js"></script>
		<script src="js/jquery/jquery.ui.button.js"></script>
		<script src="js/jquery/jquery.ui.draggable.js"></script>
		<script src="js/jquery/jquery.ui.position.js"></script>
		<script src="js/jquery/jquery.ui.resizable.js"></script>
		<script src="js/jquery/jquery.ui.dialog.js"></script>
		<script src="js/common.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script src="js/pubValiPattern.js"></script>
		<script src="js/card/cardBINSet.js"></script>


		<script type="text/javascript">

		initAmtFlag=false;
		limitAmtFlag=false;
		singleRechAmtFlag=false;
		singleRechWornFlag =false;
	    singleConsAmtFlag =false;	
	    singleConsWornFlag =false;
	    binNameFlag =false;
	    nameFlag =false;
	    dispNoLenFlag =false;
		   //面值
		   
	    	var validateInitAmt= function(){
				var initAmt = $("#initAmt").val();
				$("#initAmtValue").html("");
				if (initAmt==null||initAmt==''){
			    	$("#initAmtValue").html("面值不能为空!");
			    	 initAmtFlag= false;
			    	 
				}else{
					initAmtFlag=true;
				}
				return initAmtFlag;
			 }
		
		 //卡内额度上限
		var validateLimitAmt = function(){
		  var limitAmt = $("#limitAmt").val();
			$("#limitAmtValue").html("");
			if(limitAmt==''||limitAmt==null){
				$("#limitAmtValue").html("卡内额度上限不能为空!");
				limitAmtFlag =false;
				return false;
			}else{
				limitAmtFlag=true;
			}
			return limitAmtFlag;
		}
		//单笔充值金额上限
		var validateSingleRechAmt=function(){
			var singleRechAmt = $("#singleRechAmt").val();
			$("#singleRechAmtValue").html("");
			if(singleRechAmt==''||singleRechAmt==null){
				$("#singleRechAmtValue").html("单笔充值限额不能为空!");
				singleRechAmtFlag =false;
				return false;
				
			}else{
				singleRechAmtFlag=true;
			}
			return singleRechAmtFlag;
		}
		//单笔充值金额预警值
		var validateSingleRechWorn =function(){
		  var singleRechWorn = $.trim($("#singleRechWorn").val());
		  var singleRechAmt = $.trim($("#singleRechAmt").val());
		  $("#singleRechWornValue").html("");
		  if(singleRechWorn==''||singleRechWorn==null){
		    $("#singleRechWornValue").html("单笔充值金额预警值不能为空!");
		    singleRechWornFlag =false;
		    return false;
		  }else if(parseFloat(singleRechWorn) > parseFloat(singleRechAmt)){
		   $("#singleRechWornValue").html("此值要小于单笔充值金额上限");
		    singleRechWornFlag =false;
		    return false;
		  }else{
		    singleRechAmtFlag=true;
		  }
		   return singleRechWornFlag;  
		}
		//单笔消费金额预警值
		var validateSingleConsWorn =function(){
		  var singleConsAmt =$.trim($("#singleConsAmt").val());
		  var singleConsWorn =$.trim($("#singleConsWorn").val());
		  $("#singleConsWornValue").html("");
		if(singleConsWorn==''||singleConsWorn==null){
		   $("#singleConsWornValue").html("单笔消费金额预警值不能为空!");
		   singleConsWornFlag =false;
		   return false;
		 }else if(parseFloat(singleConsWorn) > parseFloat(singleConsAmt)){
		  $("#singleConsWornValue").html("此值要小于单笔消费金额上限!");
		  singleRechWornFlag =false;
		  return false;
		 }else{
		  singleRechWornFlag =true;
		 }
		 return singleRechWornFlag;
		}
		//单笔消费金额上限
		
		var validateSingleConsAmt =function(){
		  var singleConsAmt =$("#singleConsAmt").val();
		  $("#singleConsAmtValue").html("");
		  if(singleConsAmt==''||singleConsAmt==null){
		   $("#singleConsAmtValue").html("单笔消费限额不能为空！");
		     singleConsAmtFlag =false;
		     return false;
		  }else{
		    singleConsAmtFlag =true;
		  }
		  return singleConsAmtFlag;
		}
		//判断卡BIN名称
		var validateBinName =function(){
		 	var binName = $.trim($("#binName").val());
				$("#binNameValue").html("");
				if (binName.length ==0 ||binName==null){
			    	$("#binNameValue").html("请填写卡BIN");
			    	binNameFlag= false;
			    	return false;
				}else{
					binNameFlag=true;
				}
				return binNameFlag;
		}
		//判断机构
		var  validateOrgName=function(){
		  var organId = $.trim($("#organId").val());
		  if(organId.length ==0 ||organId ==null){
		   $("#nameValue").html("请选择机构");
		    nameFlag =false;
		    return false;
		  }else{
		  	$("#nameValue").html("");
		    nameFlag = true;
		  }
		  return nameFlag;
		}
		//面值获得焦点
		var validateInitAmt1 =function(){
		 $("#singleRechAmtValue").html("");
		 $("#limitAmtValue").html("");
		}
		var validateLimitAmt1 =function(){
		    validateInitAmt();
		   $("#singleRechAmtValue").html("");
		}
		var validateSingleRechAmt1 =function(){
		 validateInitAmt();
		 validateLimitAmt();		
		}

       //判断三个值得比较
		//function judgeThree()
		var judgeThree = function(){
		 	var initAmt = $("#initAmt").val();
			var limitAmt = $("#limitAmt").val();
			var singleRechAmt = $("#singleRechAmt").val();
			if( parseFloat(initAmt) <= parseFloat(singleRechAmt) && parseFloat(singleRechAmt)<= parseFloat(limitAmt)){
			    return true;
			}else{
			  alert(" 面值<=单笔充值金额上限<=卡内额度上限");
			  return false;
			}
		}
		//判断显示卡号长度
		//function judgeLength()
		var judgeLength = function(){
		  var dispNoLen =$.trim($("#dispNoLen").val());
		  if(dispNoLen >=6 && dispNoLen<=15){
		       $("#dispNoLenValue").html("");
		       return true;
		  }else if(dispNoLen==null||dispNoLen==""){
		      $("#dispNoLenValue").html("检查卡号设置长度");
		      return false;
		  }else{
		    $("#dispNoLenValue").html("卡号长度设置大于等于6位或者小于等于15位!");
		     return false;
		  }
		 }
		  
		  //判断描述信息
		var validateDescr = function(){
				var textareaLength=$("#descr").val().length; 
				$("#descrValue").html(""); 
				if(textareaLength>=200){ 
					$("#descrValue").html("200以内字符！");
					return false;
				} 
			}
		  
		//带*内容为必填项
		/*function AllP(){
		    initAmtFlag =validateInitAmt();
			limitAmtFlag=validateLimitAmt();
			singleRechAmtFlag =validateSingleRechAmt();
			binNameFlag =validateBinName();
			singleConsAmtFlag = validateSingleConsAmt();
			singleRechWornFlag = validateSingleRechWorn();
			singleConsWornFlag = validateSingleConsWorn();
		  if(!(initAmtFlag&&limitAmtFlag&&singleRechAmtFlag&&singleConsAmtFlag&&binName)){
			    alert("带*号内容为必填项!");
		        return false;
			}else{
			   return true;
			}
		}
		*/
		function replaceToNumPoint(obj){
		   if(isNaN(obj.value)){
			 	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
			 	obj.value = obj.value.replace(/^[.]/g,"");  //验证第一个字符是数字而不是.
			 	obj.value = obj.value.replace(/[.]{2,}/g,""); //清除第一个点以外的点
			 	
			 }
	      }
		
		function check(){
	
			var initAmt = $("#initAmt").val();
			var limitAmt = $("#limitAmt").val();
			var singleRechAmt = $("#singleRechAmt").val();
			var singleConsAmt = $("#singleConsAmt").val();
			var organId =$("#Name").val();
			var singleConsWorn =$.trim($("#singleConsWorn").val());
			var singleRechWorn = $.trim($("#singleRechWorn").val());
			var descr = $.trim($("#descr").val());
			
			validateBinName();
			validateOrgName();
			judgeLength();
			validateInitAmt();
			validateLimitAmt();
			validateSingleRechAmt();
			validateSingleConsAmt();
			validateSingleRechWorn();
			validateSingleConsWorn();
			validateDescr();
		    /*
			initAmtFlag =validateInitAmt();
			limitAmtFlag=validateLimitAmt();
			singleRechAmtFlag =validateSingleRechAmt();
			binNameFlag =validateBinName();
			singleConsAmtFlag = validateSingleConsAmt();
			singleRechWornFlag = validateSingleRechWorn();
			singleConsWornFlag = validateSingleConsWorn();
		  
		   if(organId==null||organId==''){
		    $("#nameValue").html("");
		     $("#nameValue").html("请选择机构");
			    nameFlag = false;
		   }
		   if(parseFloat(limitAmt)>parseFloat(singleRechAmt)&&parseFloat(initAmt)<parseFloat(singleRechAmt)){
		            return true;
		   }else{
		   		alert("面值≤充值金额≤卡内额度上限");
		   		return false;	
		   }
		   
		   else if(parseFloat(singleRechWorn) >parseFloat(singleRechAmt)||parseFloat(singleConsWorn)> parseFloat(singleConsAmt)){
				alert("请按照提示内容修改");
				return false;
		  }else if(!(initAmtFlag&&limitAmtFlag&&singleRechAmtFlag&&singleConsAmtFlag&&binName)){
		        alert("带*号内容为必填项!");
		        return false;
		  }else{
		       return true
		  }
			*/
			/* if(organId==null||organId==''){
		      $("#nameValue").html("");
		      $("#nameValue").html("请选择机构");
			    nameFlag = false;
		     }
		     if(binName==null||binName==''){
		      $("#binNameValue").html("");
		      $("#binNameValue").html("请选择卡BIN");
			    binNameFlag = false;
		     }*/

			

		     //alert(validateOrgName());
			return validateBinName()&&validateOrgName()&&judgeLength()&&validateSingleConsAmt()&&validateSingleConsWorn()&&validateSingleRechWorn()&&judgeThree()&&validateDescr();;

		}
		
			//查询方法
		function loadset(){
			var consoleDlg = $("#method").val();
		 
			if(consoleDlg=="viewUI"){
			
				$("#binName").attr("disabled",true);
				$("#Name").attr("disabled",true);
				$("#initAmt").attr("disabled",true);
				$("#status").attr("disabled",true);
				$("#binSign").attr("disabled",true);
				$("#binType").attr("disabled",true);
				$("#limitAmt").attr("disabled",true);
				$("#singleConsAmt").attr("disabled",true);
				$("#singleRechAmt").attr("disabled",true);
				$("#singleRechWorn").attr("disabled",true);
				$("#singleConsWorn").attr("disabled",true);
				$("#dayConsTimes").attr("disabled",true);
				$("#descr").attr("disabled",true)
				
			  var submits = $(":submit");
    	      submits.attr("disabled","disabled");
             submits.removeClass("formButton").addClass("formButtonDisabled");
			}
			
		}	
</script>
	</head>
	<body onload="loadset();">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 卡信息管理 &gt;&gt; 卡BIN管理
		</div>
		<jsp:include page="/WEB-INF/page/card/cardBin/cardBINHelp.jsp"></jsp:include>
		<s:form action="card/cardBIN" method="post" 
			 onsubmit="document.getElementById('submitInput').disabled = true;return true;"
			theme="simple">
			<s:hidden name="method" id="method"/>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">
				<tr>
					<th align="right" width="20%">
						卡BIN：
					</th>
					<td width="30%">
						<s:property value="cardBINDTO.binId" />
						<s:hidden name="cardBINDTO.binId" id="binId" />
					</td>

					<th align="right" width="20%">
						<span class="Color5">* </span>卡名称：
					</th>
					<td width="30%">
						<s:textfield name="cardBINDTO.binName" id="binName"
							cssClass="formInput" theme="simple" onblur="validateBinName();" />
							<span class="Color5" id="binNameValue"></span>
					</td>
				</tr>
				<tr>

					<th align="right">
						<span class="Color5">* </span>所属机构：
					</th>
					<s:if test="method=='addSave'">
						<td>
							<s:textfield name="cardBINDTO.orgName" readonly="true" id="Name"
								cssClass="formInput" theme="simple" />
							<s:hidden name="cardBINDTO.organId" id="organId"></s:hidden>
							<img alt="查找机构" src="images/search.gif" style="cursor: pointer;"
								onclick="ajaxMerc();" />
							<span class="Color5" id="nameValue"></span>
					</s:if>
					<s:else>
						<td>
							<s:property value="cardBINDTO.orgName" />
							<s:hidden name="cardBINDTO.organId" id="organId"></s:hidden>
					</s:else>
					
					 <th align="right">
						<span class="Color5">* </span>设定显示卡号位数：
					</th>
					<td>
						<s:textfield name="cardBINDTO.dispNoLen" id="dispNoLen" onblur ="judgeLength();"
							cssClass="formSelect" theme="simple" />
						<span class="Color5" id="dispNoLenValue" ></span>
					</td>
				</tr>

				<tr>
					<th align="right" width="20%">
						<span class="Color5">* </span>面值：
					</th>
					<td>
						<s:textfield name="cardBINDTO.initAmt" id="initAmt" onkeyup="replaceToNumPoint(this);"  maxlength="8"
							cssClass="formInput" theme="simple" onblur="validateInitAmt();" onfocus="validateInitAmt1()" />
						<span class="Color3">元</span>
						<span class="Color5" id="initAmtValue" ></span>
					</td>
					<th align="right">
						状态：
					</th>
					<td>
						<s:select name="cardBINDTO.status" id="status"
							list="#request.statusValues" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />
					</td>
				</tr>
				<tr>
					<th align="right">
						<span class="Color5">* </span>卡标志：
					</th>
					<td>
						<s:select name="cardBINDTO.binSign" id="binSign"
							list="#request.binFlagValues" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />
					</td>
					<th align="right">
						卡类别：
					</th>
					<td>
						<s:select name="cardBINDTO.binType" id="binType"
							list="#request.binTypeValues" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />
					</td>

				</tr>

				<tr>
					<th align="right" width="20%">
						<span class="Color5">*</span>卡内额度上限：
					</th>
					<td>
						<s:textfield name="cardBINDTO.limitAmt" id="limitAmt" onkeyup="replaceToNumPoint(this);"
							maxlength="8" cssClass="formInput" theme="simple"
							onblur="validateLimitAmt();" onfocus="validateLimitAmt1()"/>
						<span class="Color3">元</span>
						<span class="Color5" id="limitAmtValue"></span>

					</td>

					<th align="right" width="20%">
						<span class="Color5">* </span>单笔消费金额上限：
					</th>
					<td>

						<s:textfield name="cardBINDTO.singleConsAmt" id="singleConsAmt" onkeyup="replaceToNumPoint(this);" 
							maxlength="8" cssClass="formInput" theme="simple" onblur="validateSingleConsAmt();"/>
						<span class="Color3">元</span>
						<span class="Color5" id="singleConsAmtValue"></span>
					</td>

				</tr>
				<tr>
					<th align="right" width="20%">
						<span class="Color5">* </span>单笔充值金额上限：
					</th>
					<td>
						<s:textfield name="cardBINDTO.singleRechAmt" id="singleRechAmt" onkeyup="replaceToNumPoint(this);" 
							maxlength="8" cssClass="formInput" theme="simple"
							onblur="validateSingleRechAmt();" onfocus="validateSingleRechAmt1()"/>
						<span class="Color3">元</span>
						<span class="Color5" id="singleRechAmtValue"></span>
					</td>

					<th align="right">
						日累计交易次数上限：
					</th>
					<td>
						<s:textfield name="cardBINDTO.dayConsTimes" id="dayConsTimes" 
							maxlength="8" cssClass="formInput" theme="simple"
							onkeyup="replaceToNum(this);"/>

						<span class="Color3">次</span>
						<span class="Color5" id="dayConsTimesValue"></span>
					</td>

				</tr>
				
					<tr>
					<th align="right" width="20%">
						<span class="Color5">* </span>单笔充值金额预警值：
					</th>
					<td>

						<s:textfield name="cardBINDTO.singleRechWorn" id="singleRechWorn" 
							maxlength="8" cssClass="formInput" theme="simple"
							onkeyup="replaceToNumPoint(this);" onblur="validateSingleRechWorn();"/>
						<span class="Color3">元</span>
						<span class="Color5" id="singleRechWornValue"></span>
					</td>

					<th align="right">
						<span class="Color5">* </span>单笔消费金额预警值：
					</th>
					<td>
						<s:textfield name="cardBINDTO.singleConsWorn" id="singleConsWorn" 
							maxlength="8" cssClass="formInput" theme="simple"
							onkeyup="replaceToNumPoint(this);" onblur="validateSingleConsWorn();"/>

						<span class="Color3">元</span>
						<span class="Color5" id="singleConsWornValue"></span>
					</td>

				</tr>
				
				<tr>
					<th align="right">
						卡BIN描述：
					</th>
					<td colspan="3">
						<s:textarea name="cardBINDTO.descr" id="descr"
							onblur="validateDescr();" cssClass="formTextarea" cols="45" rows="20" />
						<span id="descrValue" class="Color5"></span>
					</td>
				</tr>
			</table>

			<div class="formTableBottom">
				<s:if test="method=='addSave'">
					<my:permission key='sy-2101-02' value='卡BIN添加'>
						<input id="submitInput" type="submit" class="formButton"
							value="保 存" onclick="return check();" />
					</my:permission>
				</s:if>
				<s:else>
					<my:permission key='sy-2101-03' value='卡BIN修改'>
						<input id="submitInput" type="submit" class="formButton" 
							value="保 存"  onclick="return check();" />
					</my:permission>
				</s:else>
				<input type="button" class="formButton" value="返 回"
					onclick="go('card/cardBIN!list')" />
			</div>
		</s:form>
		 <div align="left" style="padding: 50px; border: solid;border-color: #F2F2F2 ;border-width: thin">
	    <font color="#ff9900" >
	                系统提示：
                                              带"*"号为必填项！	                
	    </font>
	    
	 </div>
	</body>
</html>