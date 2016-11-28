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

		<title>售卡订单</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
			type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.4.2.min.js"></script>
		<link href="js/jquery/css/jquery.ui.all.css" rel="stylesheet"
			type="text/css" />
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
		<script type="text/javascript">

			var cardNoFlag = false;
			var newPointFlag = true;
			var initAmtFlag = true;
			var paidAmtFlag = true;
			var memRealNameFlag = true;
			var personIdFlag = true;
			var teleNoFlag = true;
			var emailFlag = true;
			var residZipFlag = true;  
			var descrFlag = true;
			
			var personId = $("#personId").val();

			function valiIdCard(personId){
				  
				  $("#personIdValue").html("");
				  var checkFlag = new clsIDCard(personId);
				  if (!checkFlag.IsValid()) {
				   alert("输入的身份证号无效,请输入真实的身份证号！");
				   return false;
				  }else{
				    alert("正确！");
				  }
				 }
             //保存时的校验
			var check = function(){
		
				if(!(validateDescr())){
					return false;
				}
				//alert(cardNoFlag+","+newPointFlag+","+initAmtFlag+","+paidAmtFlag+","+memRealNameFlag+","+personIdFlag+","+teleNoFlag+","+emailFlag);
				if(!(cardNoFlag&&newPointFlag&&initAmtFlag&&paidAmtFlag&&memRealNameFlag&&personIdFlag&&teleNoFlag&&emailFlag)){
					alert("请检查输入订单信息，带*号的为必填项");
					return false;
				}
				var birthDay=$("#birthday").val(); 
				$("#birthDayValue").html(""); 
				if (birthDay==null||birthDay==''){
					alert("请选择出生日期!");
				    $("#birthDayValue").html("请选择出生日期!");
				    return false;
				}else{
					birthDayFlag = true;
				}
			}
			//校验卡号
			var validateCardNo = function(){
				var cardNo = $("#cardNo").val();
				var cardNoreg = /^[0-9]*$/;
				if (cardNo==''){
					$("#cardStatusValue").html("卡号不能为空!");
			    	return false;
				}else if(!cardNoreg.test(cardNo)){
					$("#cardStatusValue").html("请输入16或19位数字!");
					return false;
				}else if(cardNo.length !=16&&cardNo.length!=19){
					$("#cardStatusValue").html("请输入16位或19位卡号!");
			    	return false;
				}else{
					queryCardNo(cardNo);
				}
				
			}

			//加载起始卡号
		    var loadBeginCardNo = function () {
		    	var binId = $.trim($("#binId").val());
				if(binId==''){
					alert("请选择卡BIN!");
					$("#binIdValue").html("请选择卡BIN!");
					return false;
				}

				var params = {
				        "cardBINDTO.binId" : binId
				    }; 
			    
	             $.ajax({
	                 url : "order/singlesale!loadMinCardNo",
	                 type : "POST",
	                 data : params, 
	                 dataType : "json",
	                 success : function(data){                         
	                      var cardNo = data;
	                      if(cardNo!=""&&cardNo!=null){
	                        $("#cardNo").val(cardNo);
	                        queryCardNo(cardNo);
	                      }
	                      else{
	                           alert("没有可供出售的卡号，请查看库存信息!"); 
	                           $("#cardNo").val("");
	                           $("#initAmt").val("");
				               $("#paidAmt").val("");
	                       }
	                      
	                 },
	                 error:function(){
	                      alert("系统ajax交互错误!");
	                 }
	              });
			    }

		    var queryCardNo = function(cardNo,binId){
		      var binId = $.trim($("#binId").val());
		    	var params = {
				        "saleOrderDTO.beginCardNo" : cardNo,
				        "cardBINDTO.binId" : binId
				    }; 
					
					var actionUrl = "order/singlesale!queryCardNo";   
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
				            if (data.flag) {
				                $("#cardStatusValue").html(data.msg);
				                $("#initAmt").val(data.amount);
				                $("#initAmtValue").html("");
				                $("#paidAmt").val(data.amount);
				                $("#paidAmtValue").html("");
				                cardNoFlag = true; 
				           	}else {
				            	$("#cardStatusValue").html(data.msg);
				            }
				        }
				    });
			    
		    }
			
			var queryNewPoint = function(){
				var saleLevelId = $.trim($("#saleLevelId").val());
				if(saleLevelId==''){
					alert("请选择卡级别!");
					$("#saleLevelValue").html("请选择卡级别!");
					newPointFlag = false;
					return false;
				}
				var params = {
				        "asaleOrderDTO.saleLevelId" : saleLevelId
				    }; 
					
				var actionUrl = "order/singlesale!queryNewPoint";   
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
					    if (data.flag) {
					    	var consoleDlg = $("#dialog-confirm");
					    	$("#newPoint").val(data.amount);
					    	$("#hidnewPoint").val(data.amount);
					    	
					    }else {
					       	$("#newPoint").val("");
					    }
					}
				 });
			}
			
			var validateNewPoint= function(){
				var newPoint = $("#newPoint").val();
				var newPointreg = /^[0-9]+(.[0-9]{0,2})?$/;
				
				$("#newPointValue").html("");
				if(!newPointreg.test(newPoint)){
					$("#newPointValue").html("请整数或两位小数!");
					newPointFlag=false;
			    	return false;
			    }else{
			    	newPointFlag=true;
			    }
			}
			//面值校验
			var validateInitAmt= function(){
				var initAmt = $("#initAmt").val();
				var cardNo = $("#cardNo").val();
				var bin = cardNo.substring(0,6);
				var initAmtreg = /^[0-9]+(.[0-9]{0,2})?$/;
				$("#initAmtValue").html("");
				if (initAmt==''){
			    	$("#initAmtValue").html("请输入面值!");
			    	initAmtFlag=false;
			    	return false;
				}else if(!initAmtreg.test(initAmt)){
					$("#initAmtValue").html("请正确的现金格式(0.00)!");
					initAmtFlag=false;
			    	return false;
			    }else if(cardNo==''){
			    	validateCardNo();
				}
			    else{
			    	var params = {
					        "cardBINDTO.binId" : bin,
					        "saleOrderDTO.initAmt" : initAmt
					    }; 
						
					var actionUrl = "order/singlesale!checkSingleRechAmt";   
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
					    	if (data.flag) {
						    	var consoleDlg = $("#dialog-confirm");
						    	initAmtFlag=true;
						    	$("#initAmtValue").html("");
						    }else {
						    	$("#initAmt").val(data.amount);
						    	$("#initAmtValue").html(data.msg);
						    }
						}
					 });		    	
			    }
			}
			var validatePaidAmt= function(){
				var paidAmt = $("#paidAmt").val();
				$("#paidAmtValue").html("");
				var paidAmtreg = /^[0-9]+(.[0-9]{0,2})?$/;
				if (paidAmt==''){
			    	$("#paidAmtValue").html("请输入支付金额!");
			    	//$("#paidAmt").focus();
			    	return false;
				}else if(!paidAmtreg.test(paidAmt)){
					$("#paidAmtValue").html("请正确的现金格式(0.00)!");
			    	//$("#paidAmt").focus();
			    	return false;
			    }else{
			    	paidAmtFlag=true;
			    }
			}
			
			var validateMemRealName= function(){
				var memRealName = $("#memRealName").val();
				$("#memRealNameValue").html("");
				if (memRealName==''){
			    	$("#memRealNameValue").html("请输入真实姓名!");
			    	return false;
				}else{
					memRealNameFlag=true;
				}
				
			}

			
			var validatePersonId= function(){
				var personId = $("#personId").val();
				$("#personIdValue").html("");
				var perType = $.trim($("#perType").val());
				var perTypereg = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;				
				//var perTypereg = /^\d{15}|\d{18}$/;
				if (personId==''){
			    	$("#personIdValue").html("请输入证件编号!");
			    	return false;
				}else if(perType=='1'){
					if (perTypereg.test(personId)){
				    	personIdFlag = true;				    	
					}else{
						$("#personIdValue").html("请输入正确的身份证号(15或18位)!");
				    	return false;
					}
				}else if(personId.length!=15&&personId.length!=18){
					$("#personIdValue").html("身份证号长度为15为或18位!");
				    	return false;
				}
			}
			var validateTeleNo= function(){
				var teleNo = $("#teleNo").val();
				
				//var teleNoreg = /^[a-zA-Z0-9_]{1,30}$/;
				$("#teleNoValue").html("");
				//var teleNoreg = /^\d{11}$/;
				var teleNoreg = /1[3-8]+\d{9}/;
				//var teleNoreg = /^((\+86)|(86))?(1)\d{10}$/;
				if (teleNo==''){
			    	$("#teleNoValue").html("请输入联系电话!");
			    	return false;
				}else if (!teleNoreg.test(teleNo)){
			    	$("#teleNoValue").html("请输入正确的电话格式(11位数字)!");
			    	return false;
				}else{
					teleNoFlag = true;
				}
			}
			var validateEmail= function(){
				var email = $("#email").val();
				$("#emailValue").html("");				
				var emailreg = /^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+[a-zA-Z]+$/gi;
				//var emailreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
				if (email==""){
				     $("#emailValue").html("请输入电子邮箱!");
				     return false;
				}else{
					 if(!emailreg.test(email)){				
						$("#emailValue").html("请输入有效的E_mail！");
						return false;				
					 }else {
						emailFlag = true;	
					 }
				}
			}
			var validateResidZip= function(){
				var residZip = $("#residZip").val();
				$("#residZipValue").html("");
				var residZipreg = /^[0-9]*$/;
				if(!residZipreg.test(residZip)){
					$("#residZipValue").html("请输入数字格式！");
					return false;
				}else{
					residZipFlag = true;
				}
			}
			var validateDescr= function(){
				var textareaLength=$("#descr").val().length;  
				if(textareaLength>=200){ 					
					$("#descrValue").html("200以内字符！");
					return false;
				} 
				else{
					$("#descrValue").html("");
					return true;
				}
			}
         var changeBin =function(){
            var cardNo = $("#cardNo").val();
            $("#cardNo").val("");
            $("#cardStatusValue").val("");
           
         }
//触发时修改为不是只读形式
		$(function(){
			var single=$("#single").val();
			if(single=="单张售卡"){
				$("#payMenName").attr("readonly",false);
				$("#personId").attr("readonly",false);
				$("#email").attr("readonly",false);
				$("#telNo").attr("readonly",false);
				$("#birthday").attr("readonly",false);
			}
		});
		</script>
	</head>
	<body onload="queryNewPoint();">

		<div class="Position">
			当前位置是：HOME &gt;&gt;售卡订单 &gt;&gt; 单张售卡订单添加
		</div>
		<input  type="hidden" id="single" value="单张售卡" />
		<jsp:include page="/WEB-INF/page/member/member/memberSaleHelp.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/page/base/compInfo/compInfoHelp.jsp"></jsp:include>
		<s:form action="order/singlesale!addSave" method="post" theme="simple"
			onsubmit="document.getElementById('submitInput').disabled = true;return true;">
			<s:hidden name="method" />
			<div class="List_tit">
				订单信息
			</div>
			<div id="dialog-confirm">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formTable">
					<tr>
						<th align="right" width="6%" colspan="1">
							<span class="Color5">* </span>订单号：
						</th>
						<td>
							<s:property value="saleOrderDTO.saleOrderId" />
							<s:hidden name="saleOrderDTO.saleOrderId" id="saleOrderId" />

						</td>
						
						<th align="right" width="6%" colspan="1">
							<span class="Color5">* </span>卡标志：
						</th>
						<td>
							<s:select name="saleOrderDTO.cardSingn" id="cardSingn"
								list="#request.siginValues" listKey="key" listValue="value"
								cssClass="formSelect" theme="simple"/>
								
							<span class="Color5" id="saleLevelValue"></span>
						</td>
					</tr>
					<tr>
						<th align="right" width="6%" colspan="1">
							<span class="Color5">* </span>卡BIN：
						</th>
						<td>
							<s:select name="cardBINDTO.binId" id="binId"
								list="#request.binsList" listKey="key" listValue="value"
								onchange="changeBin();" cssClass="formSelect" theme="simple" />
						</td>
						<th align="right" width="6%" colspan="1">
							<span class="Color5">*</span>卡号：
						</th>
						<td>
							<s:textfield name="saleOrderDTO.beginCardNo" id="cardNo"
								maxlength="19" cssClass="formInput" theme="simple"
								onblur="validateCardNo();" />
							<a href=javascript:loadBeginCardNo();><img
									src='images/flush.gif' /> </a>
							<span class="Color5" id="cardStatusValue"></span>
						</td>
					</tr>
					<tr>
						<th align="right" width="6%">
							<span class="Color5">* </span>面值(¥)：
						</th>
						<td width="30%">
							<s:textfield name="saleOrderDTO.initAmt" id="initAmt"
								maxlength="11" cssClass="formInput" theme="simple"
								onblur="validateInitAmt();" />
							<span class="Color3">(元)</span>
							<span class="Color5" id="initAmtValue"></span>
						</td>
						<th align="right" width="6%">
							<span class="Color5">* </span>支付金额(¥)：
						</th>
						<td width="30%">
							<s:textfield name="saleOrderDTO.paidAmt" id="paidAmt"
								maxlength="11" cssClass="formInput" theme="simple"
								onblur="validatePaidAmt();" />
							<span class="Color3">(元)</span>
							<span class="Color5" id="paidAmtValue"></span>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="Color5">* </span>卡级别：
						</th>
						<td>
							<s:select name="asaleOrderDTO.saleLevelId" id="saleLevelId"
								list="#request.levelValues" listKey="key" listValue="value"
								cssClass="formSelect" theme="simple" onchange="queryNewPoint();" />
							<span class="Color5" id="saleLevelValue"></span>
						</td>
						<th align="right">
							<span class="Color5"></span>开卡送积分：
						</th>
						<td>
							<s:textfield id="newPoint" maxlength="11" cssClass="formInput"
								theme="simple" onblur="validateNewPoint();" disabled="true" />
							<s:hidden name="asaleOrderDTO.newPoint" id="hidnewPoint"></s:hidden>
							<span class="Color5" id="newPointValue"></span>
						</td>

					</tr>
					<tr>
						<th align="right">
							描述：
						</th>
						<td>
							<s:textarea name="asaleOrderDTO.descr" id="descr"
								cssClass="formTextarea" theme="simple" onblur="validateDescr();" />
							<span id="descrValue" class="Color5"></span>
						</td>
					</tr>

				</table>
			</div>
			<div>
				<div class="List_tit">
					会员信息
				</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formTable">
					<tr>
						<th align="right" width="10%">
							<span class="Color5">* </span>真实姓名：
						</th>
						<td width="40%">   
							<s:textfield name="memberDTO.memRealName" id="payMenName"
								maxlength="30" cssClass="formInput" theme="simple" 
								onblur="validateMemRealName();"  />
								 <s:hidden name="memberDTO.memId" id="payMenId"/>
								 <img alt="查找会员" src="images/search.gif" style="cursor:pointer;" onclick="ajaxSaleMemId();"/>
							<span class="Color5" id="memRealNameValue"></span>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="Color5">* </span>证件编号：
						</th>
						<td>
							<s:textfield name="memberDTO.personId" id="personId"
								maxlength="18" cssClass="formInput" theme="simple"
								onblur="validatePersonId();"  />
							<span class="Color5" id="personIdValue"></span>
						</td>
						<th align="right">
							<span class="Color5">* </span>电子邮箱：
						</th>
						<td>
							<s:textfield name="memberDTO.email" id="email" maxlength="50"
								cssClass="formInput"  theme="simple" onblur="validateEmail();" />
							<span class="Color5" id="emailValue" ></span>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="Color5">* </span>手机：
						</th>
						<td>
							<s:textfield name="memberDTO.teleNo" id="teleNo" maxlength="11"
								cssClass="formInput" theme="simple"  onblur="validateTeleNo();"
								onkeyup="replaceToNum(this);" />
							<span class="Color5" id="teleNoValue"></span>
						</td>
						<th align="right">
							<span class="Color5">* </span>出生日期：
						</th>
						<td>
							<input name="amemberDTO.birthday" id="birthday" value=""
								class="Wdate formInput2" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
								onblur="validateBirthDay();" />

							<span class="Color5" id="birthDayValue"></span>
						</td>
					</tr>
					<tr>
						<th align="right">
							职 业：
						</th>
						<td>
							<s:textfield name="amemberDTO.career" id="career" maxlength="20"
								cssClass="formInput" theme="simple" />
						</td>
						<th align="right">
							学 历：
						</th>
						<td>
							<s:textfield name="amemberDTO.cultDegree" id="cultDegree"
								maxlength="20" cssClass="formInput" theme="simple" />
						</td>
					</tr>
					<tr>
						<th align="right">
							居住地址：
						</th>
						<td>
							<s:textfield name="amemberDTO.address" id="address"
								maxlength="60" cssClass="formInput" theme="simple" />
						</td>
						<th align="right">
							邮 编：
						</th>
						<td>
							<s:textfield name="amemberDTO.residZip" id="residZip"
								maxlength="6" cssClass="formInput" theme="simple"
								onblur="validateResidZip();" />
							<span class="Color5" id="residZipValue"></span>
						</td>
					</tr>
					<tr>
						<th align="right">
							区域ID：
						</th>
						<td>
							<s:select name="memberDTO.areaId" id="areaId"
								list="#request.areaIdValues" listKey="key" listValue="value"
								value="0" cssClass="formSelect" theme="simple" />
						</td>
						<th align="right">
							<span class="Color5">* </span>性 别：
						</th>
						<td>
							<s:select name="memberDTO.sex" id="sex" list="#request.sexValues"
								listKey="key" listValue="value" value="1" cssClass="formSelect"
								theme="simple" />
						</td>
					</tr>
				</table>
			</div>

			<div>
				<div class="List_tit">
					企业信息
				</div>
				<jsp:include page="/WEB-INF/page/base/organs/organHelp.jsp"></jsp:include>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formTable">
					<!--<s:hidden name="amemberDTO.memId" />
					<s:hidden name="amemberDTO.amemId" />-->
				<tr>
				    <th align="right" width="10%"><span class="Color5">* </span>企业名称：</th>
			        <td>
			        <s:textfield name="saleOrderDTO.companyName" id="companyName" readonly="true" cssClass="formInput" theme="simple"/>
			        <s:hidden name="saleOrderDTO.companyId" id="companyId"/>
			        <img alt="查找企业" src="images/search.gif" style="cursor:pointer;" onclick="ajaxorderId();"/>
			        <span class="Color5"></span> 
			        </td>
						<th align="right" width="10%">
							<span class="Color5">* </span>企业电话：
						</th>
						<td width="40%">
							<s:textfield name="saleOrderDTO.comTele" id="comTele"
								maxlength="30" cssClass="formInput" theme="simple" readonly="true"/>
						</td>
				</tr>
					<tr>
						<th align="right">
							<span class="Color5">* </span>企业的邮箱：
						</th>
						<td>
							<s:textfield name="saleOrderDTO.comEmail" id="comEmail"
								maxlength="30" cssClass="formInput" theme="simple" readonly="true"/>
						</td>
						<th align="right">
							<span class="Color5">* </span>企业联系人：
						</th>
						<td>
							<s:textfield name="saleOrderDTO.comConPer" id="comConPer" maxlength="50"
								cssClass="formInput" theme="simple" readonly="true" />
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="Color5">* </span>企业人数：
						</th>
						<td>
						<s:textfield name="saleOrderDTO.compNum" id="compNum" maxlength="50"
								cssClass="formInput" theme="simple" readonly="true" />

						</td>
						<th align="right">
							<span class="Color5">* </span>企业联系人电话：
						</th>
						<td>
							<s:textfield name="saleOrderDTO.comConPerTele" id="comConPerTele"
								maxlength="18" cssClass="formInput" theme="simple"readonly="true" />
						</td>
					</tr>
				
				</table>
		</div>
			<div class="formTableBottom">
				<s:if test="method=='addSave'">
					<my:permission key='sy-4101-02' value='保存'>
						<input id="submitInput" type="submit" class="formButton"
							value="保 存" onclick="return check();" />
					</my:permission>
				</s:if>
				<s:else>
					<my:permission key='sy-4101-03' value='修改'>
						<input id="submitInput" type="submit" class="formButton"
							value="保 存" />
					</my:permission>
				</s:else>
				<input type="button" class="formButton" value="返 回"
					onclick="go('order/saleorders!list')" />
			</div>
		</s:form>
	</body>
</html>