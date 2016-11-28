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
	<title>保证金管理</title>
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



	var bailAmtFlag = false;
	var buyOilRateFlag = false;

	function setBailAmt(obj){
		var type = ["isNull","isMinusAmt"];
        var errorMsg = ["保证金额不能为空!","保证金格式错误!"];
        bailAmtFlag = showErrorMsg(obj,type,errorMsg,"bailAmtMsg","bailAmtError");
		}

	function setBuyOilRate(obj){
		var type = ["isNull","isMinusAmt"];
        var errorMsg = ["购油比率不能为空!","购油比率格式错误!"];
        buyOilRateFlag = showErrorMsg(obj,type,errorMsg,"buyOilRateMsg","buyOilRateMsg");
		}
	
	//保存时验证字段是否为空等问题
	function check(){
		var descr = $("#descr").val(); 
		var bailAmt = document.getElementById("bailAmt");
		var buyOilRate = document.getElementById("buyOilRate");
		setBailAmt(bailAmt);
		setBuyOilRate(buyOilRate);
		if(buyOilRate.value>=100){
			alert("保证金比率不能大于99！");
			return false;
		}else{
			$("#buyOilRateMsg").hide();
			$("#buyOilRateError").hide();
			}
		if(!bailAmtFlag||!buyOilRateFlag){
			alert("带*号的数据填写错误！");
			return false;
		}
		if(descr.length>255){
			alert("描述信息输入过长！");
			return false;
			}
	}
	$(document).ready(function (){
			       var method = $("#method").val(); 
		            if(method=="checkUI"){
		            	setInputDisabled();
		            	$("#descr").attr("disabled",true);
		            	}
		            if(method=="edtiSave"){
		            $("#oilName").attr("readonly",true);
		            }
			        });
	
	</script> 
</head>
<body >

  <div class="Position">
		当前位置是：HOME &gt;&gt;基本设置 &gt;&gt; 保证金管理
	</div>
	<s:form action="base/bail" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable" >
	<s:hidden name = "method" id = "method"></s:hidden>
	<s:hidden name = "bailDTO.bailId" id = "bailId"></s:hidden>
		<s:if test="method=='addSave'">
			<tr>
			  	<th align="right" width="20%">
			  		类型标志：
			  	</th>
			    <td width="30%">
			        <s:radio  name="bailDTO.typeSign"  id="typeSign" list="#request.typeSign" listKey="key" listValue="value" value="1" theme="simple" onclick="selRad();"/>
			     </td>
			     <th align="right" width="20%" >
			     	交保机构：
			   	 </th>
			     <td width="30%"> 
					 <s:textfield name="bailDTO.merOrgName" id="merOrgName"  maxlength="20" cssClass="formInput"  theme="simple" />
			     </td>
			</tr>
			</s:if>
			<s:else>
				<s:hidden name="bailDTO.typeSign" id="typeSign"></s:hidden>
				<tr>
					<th align="right" width="20%" >
						类型标志：
					</th>
					<td width="30%">
						<s:property value="bailDTO.typeSignName"/>
					</td>
					<th align="right" width="20%" >
			     		交保机构：
			   	 	</th>
			        <td width="30%"> 
			        	<s:textfield name="bailDTO.merOrgName" id="merOrgName"  maxlength="20" cssClass="formInput"  theme="simple" readonly="true"/>
			        	<s:hidden name="bailDTO.merOrgId" id="merOrgId"></s:hidden>
			        </td>
				</tr>
			</s:else>
			<tr>
				<th align="right" width="20%" >
						合同编号：
				</th>
				<td width="30%"> 
			        <s:textfield name="bailDTO.contractNo" id="contractNo"  maxlength="20" cssClass="formInput"  theme="simple" readonly="true"/>
			    </td>
			    <th align="right" width="20%" >
					法人代表：
				</th>
				<td width="30%"> 
			        <s:textfield name="bailDTO.conPerName" id="conPerName"  maxlength="20" cssClass="formInput"  theme="simple" readonly="true"/>
			    </td>
			</tr>
			<tr>
				<th align="right" width="20%" >
					合作方式：
				</th>
				<td width="30%"> 
			        <s:textfield name="bailDTO.coopWayName" id="coopWayName"  maxlength="20" cssClass="formInput"  theme="simple" readonly="true"/>
			    </td>
			    <th align="right" width="20%" >
					所属经销商：
				</th>
				<td width="30%"> 
			        <s:textfield name="bailDTO.perMerName" id="perMerName"  maxlength="20" cssClass="formInput"  theme="simple" readonly="true"/>
			        <s:hidden name="bailDTO.perMerId" id="perMerId"></s:hidden>
			    </td>  
			</tr>
			<tr>
				<th align="right" width="20%" >
					收保机构：
				</th>
				<td width="30%"> 
				<s:hidden name="bailDTO.orgOilId" id="organId"></s:hidden>
			        <s:textfield name="bailDTO.orgOilName" id="oilName"  maxlength="20" cssClass="formInput"  theme="simple" />
			    </td>
			    <th align="right" width="20%" >
					<span class="Color5">* </span>缴纳金额：
				</th>
				<td width="30%"> 
			        <s:textfield name="bailDTO.bailAmt" id="bailAmt"  maxlength="20" cssClass="formInput"  theme="simple"  onblur="setBailAmt(this);"/>
			        <label id="bailAmtMsg" style="display: none;"></label> 
 					<label id="bailAmtError" style="display: none;"></label>
			    </td>
			</tr>
			<tr>
				<th align="right" width="20%" >
					<span class="Color5">* </span>购油比率：
				</th>
				<td width="30%"> 
			        <s:textfield name="bailDTO.buyOilRate" id="buyOilRate"  maxlength="20" cssClass="formInput"  theme="simple"  onblur="setBuyOilRate(this);"/>
			        <label id="buyOilRateMsg" style="display: none;"></label> 
 					<label id="buyOilRateError" class ="errorMsg" >购油比率格式为0.00</label>
			    </td>
			    <th align="right" width="20%" >
					合作状态：
				</th>
				<td width="30%"> 
		        	<s:textfield name="bailDTO.coopStatusName" id="coopStatusName" maxlength="20" cssClass="formInput"  theme="simple" readonly="true"/>
			    	<s:hidden name = "bailDTO.coopStatus"  id = "coopStatus"></s:hidden>
			    </td>
			</tr>
			<tr>
				<th align="right" width="20%" >
					保证金描述：
				</th>
				<td width="30%" colspan="3"> 
		        	<s:textarea name="bailDTO.descr" id="descr"  theme="simple" cssClass="formTextarea" cols="45" rows="5"   />
			    </td>
			</tr>
	</table>
	 <div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1210-02' value='保证金添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:if test="method=='edtiSave'">
			<my:permission key='sy-1210-03' value='保证金修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:if test="method=='checkUI'">
			<my:permission key='sy-1210-01' value='保证金修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" />
		 	</my:permission>
		</s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('base/bail!list')"/>
	 </div>
	 </s:form>
</body>
</html>