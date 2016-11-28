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
		            	$("#getCashType").attr("disabled","disabled");
		            	$("#getCashSign").attr("disabled",true);
		            	}
			        });

	var cardNoFalg = false;
	var getCashAmtFlag = false;
	var telNocheckFlag = false;
	var pwdFlag = false;
	
	function checkCardNo(){
		var cardNo = $("#cardNo").val();
		if(cardNo.length>0){
			var params = {
				"getCashDTO.cardNo" : cardNo
					}
			var actionUrl = "trade/getCash!checkCardNo";
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
		        		alert(data.obj.holdName);
						if( data.flag == true ){//不是平台标识
							$("#menName").val(data.obj.holdName);
							$("#memId").val(data.obj.holdmemId);
							$("#telNo").val(data.obj.telNo);
							$("#bank").val(data.obj.bank);
							$("#bankAcc").val(data.obj.bankAcc);
							$("#pwdReadly").val(data.obj.pwd);
							$("#getCashSign").val(data.obj.cardSingn);
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
		var getCashAmt = $("#getCashAmt").val();

		var params = {
			"getCashDTO.cardNo" : cardNo,
			"getCashDTO.getCashAmt" : getCashAmt
				}
		var actionUrl = "trade/getCash!checkAmt";
		if(getCashAmt.length>0){
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
		        		getCashAmtFlag = data.flag;
						if(!data.flag){
							$("#getCashAmtMsg").show();
							$("#getCashAmtMsg").addClass("errorMsg");
							$("#getCashAmtMsg").html("账户余额不足！");
							return false;
							}else{
								$("#getCashAmtMsg").hide();
								$("#getCashAmtMsg").html("");
								}
			      	  	}
		       		 }
				);
			}else{
				$("#getCashAmtMsg").show();
				$("#getCashAmtMsg").addClass("errorMsg");
				$("#getCashAmtMsg").html("提现金额不能为空！");
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
			"getCashDTO.telNo" : telNo,
			"getCashDTO.telNoCheck" : telNoCheck
				}
		var actionUrl = "trade/checkTel";
		if(telNocheck.length>0){
			if(getCashAmt.length>0){
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
		if(!telNocheckFlag||!cardNoFlag||!getCashAmtFlag||!pwdFlag){
			alert("带*号数据填写有误!");
			return false;
			}
        }
	
	</script> 
</head>
<body >

  <div class="Position">
		当前位置是：HOME &gt;&gt;订单管理 &gt;&gt; 一般账户提现
	</div>
	<jsp:include page="/WEB-INF/page/base/organs/organHelp.jsp"></jsp:include>
	<s:form action="trade/getCash" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable" >
	<s:hidden name = "method" id = "method"></s:hidden>
	<s:hidden name = "getCashDTO.getCashId" id = "getCashId"></s:hidden>
	<s:hidden name = "getCashDTO.pwd" id = "pwdReadly"></s:hidden>
	<s:hidden name = "getCashDTO.cardSingn" id = "cardSingn"></s:hidden>
			<tr>
			  	<th align="right" width="20%">
			  		提现卡号：
			  	</th>
			    <td width="30%">
			        <s:textfield  name="getCashDTO.cardNo"  id="cardNo" theme="simple" onkeyup = "replaceToNum(this)" maxlength="16" onblur="checkCardNo();"/>
			     	<label id="cardNoMsg" style="display: none;" ></label>
			     </td>
			     <th align="right" width="20%" >
			     	提款人：
			   	 </th>
			     <td width="30%"> 
					<s:textfield id="menName" name="getCashDTO.memName"   cssClass="formInput" maxlength="20" theme="simple" readonly="true"/>
					<s:hidden name = "getCashDTO.memId" id = "memId"></s:hidden>
					<s:hidden name = "getCashDTO.telNo" id = "telNo"></s:hidden>
				</td>
			</tr>
			<tr>
				<th align="right" width="20%" >
					提现类型：
				</th>
				<td width="30%"> 
			        <s:select name="getCashDTO.getCashType" id="getCashType" list = "#request.getCashType"  listKey="key"  listValue="value"  value="1" theme="simple" />
			    </td>
			    <th align="right" width="20%" >
					提现标识：
				</th>
				<td width="30%"> 
			        <s:textfield name="getCashDTO.getCashSignName" id="getCashSignName"  cssClass="formInput"  theme="simple" readonly="true"/>
			        <s:hidden name = "getCashDTO.getCashSign" id="getCashSign" ></s:hidden>
			    </td>  
			</tr>
			<tr>
				<th align="right" width="20%" >
					提现金额：
				</th>
				<td width="30%"> 
			        <s:textfield name="getCashDTO.getCashAmt" id="getCashAmt"  maxlength="20" cssClass="formSelect"  theme="simple" onblur="checkAmt();" onkeyup = "replaceToNumPoint(this)"/>
			    	<label id="getCashAmtMsg" style="display: none;" ></label>
			    </td>
			    <th align="right" width="20%" >
					提现金额大写：
				</th>
				<td width="30%"> 
			        <s:textfield name="getCashDTO.amtUpper" id="amtUpper"  maxlength="20" cssClass="formInput"  theme="simple" readonly="true"/>
			    </td>
			</tr>
			<tr>
				<th align="right" width="20%" >
					提现银行：
				</th>
				<td width="30%"> 
			        <s:textfield name="getCashDTO.bank" id="bank"  maxlength="20" cssClass="formInput"  theme="simple"/>
			    </td>
			    <th align="right" width="20%" >
					银行账户：
				</th>
				<td width="30%"> 
			        <s:textfield name="getCashDTO.bankAcc" id="bankAcc"  maxlength="20" cssClass="formInput"  theme="simple" onkeyup = "replaceToNum(this)"/>
			    </td>  
			</tr>
			<s:if test = "method=='addSave'">
			<tr>
				<th align="right" width="20%" >
					<span class="Color5">* </span>提现密码：
				</th>
				<td width="30%"> 
			        <s:textfield name="getCashDTO.pwd" id="pwd"  maxlength="20" cssClass="formInput"  theme="simple" onblur="checkPwd();"/>
			    </td>
			    <th align="right" width="20%" >
					<span class="Color5">* </span>手机验证码：
				</th>
				<td width="30%"> 
			        <s:textfield name="getCashDTO.telNoCheck" id="telNoCheck"  maxlength="20" cssClass="formInput"  theme="simple"  onkeyup="replaceToNum(this);" onblur="checkTel();"/>
			    </td>
			</tr>
			</s:if>
	</table>
	 <div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-4111-02' value='一般账户提现添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:if test="method=='checkUI'">
			<my:permission key='sy-4111-01' value='一般账户提现查看'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" />
		 	</my:permission>
		</s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('trade/getCash!list')"/>
	 </div>
	 </s:form>
	 <p/>
	 <p/>
	 <div align="left" style="padding: 50px; border: solid;border-color: #F2F2F2 ;border-width: thin">
	    <font color="#ff9900" >
	                系统提示：
	                <p/>
	                1.加油站用户提现，在平台确认转账之后会在2个工作日之内，将金额转入加油站的银行账号，请注意查收；
	    </font>
	    
	 </div>
</body>
</html>