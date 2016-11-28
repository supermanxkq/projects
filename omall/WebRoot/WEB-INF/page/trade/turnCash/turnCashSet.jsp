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
	<title>一般账户提现添加</title>
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

	// 查看时所有的控件为失去功能类型
	$(document).ready(function (){
			       var method = $("#method").val(); 
		            if(method=="checkUI"){
		            	setInputDisabled();
		            	$("#turnCashType").attr("disabled",true);
		            	$("#turnCashSign").attr("disabled",true);
		            	}
			        });

	var cardNoFalg = false;
	var turnCashAmtFlag = false;
	var telNocheckFlag = false;
	var pwdFlag = false;
	function checkCardNo(){
		var cardNo = $("#cardNo").val();
		if(cardNo.length>0){
			var params = {
				"turnCashDTO.cardsNo" : cardNo
					}
			var actionUrl = "trade/turnCash!checkCardNo";
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
		        		cardNoFalg = data.flag;
		        		alert(flag);
						if( data.flag == true ){//不是平台标识
							$("#menName").val(data.cardsDTO.holdName);
							$("#memId").val(data.cardsDTO.holdmemId);
							$("#telNo").val(data.cardsDTO.telNo);
							$("#pwdReadly").val(data.cardsDTO.pwd);
							$("#turnCashSign").val(data.cardsDTO.cardSingn);
							$("#turnCashSignName").val(data.cardsDTO.cardSingnName);
						}else{
							$("#cardNoMsg").show();
							$("#cardNoMsg").addClass("errorMsg");
							$("#cardNoMsg").html("该卡号不能转账!");
							return false;
						}
			        }
		        }
			);
		}else{
				cardNoFalg = false;
				$("#cardNoMsg").show();
				$("#cardNoMsg").addClass("errorMsg");
				$("#cardNoMsg").html("卡号长度不正确!");
				return false;
			}
	}

    function checkAmt(){
		var cardNo = $("#cardNo").val();
		var turnCashAmt = $("#turnCashAmt").val();

		var params = {
			"turnCashDTO.cardNo" : cardNo,
			"turnCashDTO.turnCashAmt" : turnCashAmt
				}
		var actionUrl = "trade/checkAmt";
		if(turnCashAmt.length>0){
			$.ajax({
				url : actionUrl,   
		        data : params,   
		        dataType : "json",
		        cache : false, 
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误!");  
		    		return false;
		        },
		        success : function(data, textStatus) {
		        		turnCashAmtFlag = data.flag;
						if(!data.flag){
							$("#turnCashAmtMsg").show();
							$("#turnCashAmtMsg").addClass("errorMsg");
							$("#turnCashAmtMsg").html("账户余额不足！");
							return false;
							}
			      	  	}
		       		 }
				);
			}else{
				$("#turnCashAmtMsg").show();
				$("#turnCashAmtMsg").addClass("errorMsg");
				$("#turnCashAmtMsg").html("转账金额不能为空！");
				return false;
				}
        }

    //验证密码
    function checkPwd(){
		var pwdReadly = $("#pwdReadly").val();
		var pwd = $("#pwd").val();
		if(pwd.length==0){
			$("#pwdMsg").show();
			$("#pwdMsg").addClass("errorMsg");
			$("#pwdMsg").html("密码不能为空！");
			}else{
		if(!pwd.equals(pwdReadly)){
			$("#pwdMsg").show();
			$("#pwdMsg").addClass("errorMsg");
			$("#pwdMsg").html("密码错误！");
			return false;
			}else{
				pwdFlag = true;
				}
    		}
        }
    
    function checkTel(){
    	var telNo = $("#telNo").val();
    	var telNocheck = $("#telNoCheck").val();
		var params = {
			"turnCashDTO.telNo" : telNo,
			"turnCashDTO.telNoCheck" : telNoCheck
				}
		var actionUrl = "trade/checkTel";
		if(telNocheck.length>0){
			if(turnCashAmt.length>0){
				$.ajax({
					url : actionUrl,   
			        data : params,   
			        dataType : "json",
			        cache : false, 
			        type : "POST",
			        error : function(textStatus, errorThrown) {   
			    		alert("系统ajax交互错误!");
			    		return false;  
			        },
			        success : function(data, textStatus) {
			        		telNocheckFlag = data.flag;
							if(!data.flag){
								$("#telNoMsg").show();
								$("#telNoMsg").addClass("errorMsg");
								$("#telNoMsg").html("手机验证码不正确！");
								return false;
								}
				      	  	}
			       		 }
					);
				}
    		}else{
    			$("#telNoMsg").show();
				$("#telNoMsg").addClass("errorMsg");
				$("#telNoMsg").html("手机验证码不能为空！");
				return false;
        		}
        }

    function check(){
		if(!telNocheckFlag||!cardNoFlag||!turnCashAmtFlag||!pwdFlag){
			alert("带*号数据填写有误!");
			return false;
			}
        }
	
	</script> 
</head>
<body >

  <div class="Position">
		当前位置是：HOME &gt;&gt;订单管理 &gt;&gt; 一般账户转账
	</div>
	<jsp:include page="/WEB-INF/page/base/organs/organHelp.jsp"></jsp:include>
	<s:form action="trade/turnCash" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable" >
	<s:hidden name = "method" id = "method"></s:hidden>
	<s:hidden name = "turnCashDTO.turnCashId" id = "turnCashId"></s:hidden>
	<s:hidden name = "turnCashDTO.pwd" id = "pwdReadly"></s:hidden>
	<s:hidden name = "turnCashDTO.cardSingn" id = "cardSingn"></s:hidden>
			<tr>
			  	<th align="right" width="20%">
			  		转账卡号：
			  	</th>
			    <td width="30%">
			        <s:textfield  name="turnCashDTO.cardsNo"  id="cardNo" theme="simple" onkeyup = "replaceToNum(this)" maxlength="16" onblur="checkCardNo();"/>
			     	<label id="cardNoMsg" style="display: none;" ></label>
			     </td>
			     <th align="right" width="20%" >
			     	转账人：
			   	 </th>
			     <td width="30%"> 
					<s:textfield id="menName" name="turnCashDTO.memName"   cssClass="formInput" maxlength="20" theme="simple" readonly="true"/>
					<s:hidden name = "turnCashDTO.memId" id = "memId"></s:hidden>
					<s:hidden name = "turnCashDTO.telNo" id = "telNo"></s:hidden>
				</td>
			</tr>
			<tr>
				<th align="right" width="20%" >
					转账金额：
				</th>
				<td width="30%"> 
			        <s:textfield name="turnCashDTO.turnCashAmt" id="turnCashAmt"  maxlength="20" cssClass="formInput"  theme="simple" onblur="checkAmt();" onkeyup = "replaceToNumPoint(this)"/>
			    	<label id="turnCashAmtMsg" style="display: none;" ></label>
			    </td>
			    <th align="right" width="20%" >
					转账金额大写：
				</th>
				<td width="30%"> 
			        <s:textfield name="turnCashDTO.amtUpper" id="amtUpper"  maxlength="20" cssClass="formInput"  theme="simple" readonly="true"/>
			    </td>
			</tr>
			<tr>
				<th align="right" width="20%" >
					<span class="Color5">* </span>转账密码：
				</th>
				<td width="30%"> 
			        <s:textfield name="turnCashDTO.pwd" id="pwd"  maxlength="20" cssClass="formInput"  theme="simple" onblur="checkPwd();"/>
			    </td>
			    <th align="right" width="20%" >
					<span class="Color5">* </span>手机验证码：
				</th>
				<td width="30%"> 
			        <s:textfield name="turnCashDTO.telNoCheck" id="telNoCheck"  maxlength="20" cssClass="formInput"  theme="simple"  onkeyup="replaceToNum(this);" onblur="checkTel();"/>
			    </td>
			</tr>
			<tr>
			   <th align="right" width="20%" >
					转账标识：
				</th>
				<td width="30%"> 
			        <s:textfield name="turnCashDTO.turnCashSignName" id="turnCashSignName"  cssClass="formInput"  theme="simple" readonly="true"/>
			        <s:hidden name = "turnCashDTO.turnCashSign" id = "turnCashSign"></s:hidden>
			    </td>  
			</tr>
	</table>
	 <div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-4112-02' value='一般账户转账添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:if test="method=='checkUI'">
			<my:permission key='sy-4112-01' value='一般账户转账查看'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" />
		 	</my:permission>
		</s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('trade/turnCash!list')"/>
	 </div>
	 </s:form>
	 <p/>
	 <p/>
	 <div align="left" style="padding: 50px; border: solid;border-color: #F2F2F2 ;border-width: thin">
	 </div>
</body>
</html>