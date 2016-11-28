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
    
    <title>退卡信息管理</title>
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
	<script src="js/base/member.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
	
	var cardNoFlag = false;
	var relAmtFlag = false;
	var compareFlag = false;
	
	function setCardNo(obj){
		var type = ["isNull","fullNumber"];
        var errorMsg = ["卡号不能为空!","卡号格式错误!"];
        cardNoFlag = showErrorMsg(obj,type,errorMsg,"cardNoAMsg","cardNoAError");
        }
    function setRelAmt(obj){
    	compare();
    	var type = ["isNull","fullNumber"];
        var errorMsg = ["退款金额不能为空!","退款金额格式错误!"];
        relAmtFlag = showErrorMsg(obj,type,errorMsg,"relAmtMsg","relAmtError");
        }
    function compare(){
		var allAmtA = $("#allAmtA").val();
		var relAmt = $("#relAmt").val();
		if(relAmt>allAmtA){
			compareFlag = false;
			var compare = $('#'+compare);
			compare.html("退款金额不能大于卡余额！");
		}else{
			compareFlag=true;
			compare.html("");
		}
        };

    function check(){
        var cardNo = $("#cardNoA").val();
        setCardNo(document.getElementById("cardNoA"));
        setRelAmt(document.getElementById("relAmt"));
        compare();
        alert(cardNoFlag&&relAmtFlag&&compareFlag);
        
        return false;
		if(!(cardNoFlag&&relAmtFlag&&compareFlag)){
			alert("填写信息有误，*号信息为必填项。请根据提示信息重新填写!");
			return false;
			}
		return false;
        };

	function checkCardNo(){
		var cardNo = $("#cardNoA").val();
		if(cardNo.length==6){
			//json数据传输
			var params = {
				"cardReturnDTO.cardNo" : cardNo
		    }; 
		    var actionUrl = "card/cardReturn!checkParams";
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
			        if(data.flag==true){
		            $("#allAmtA").val(data.obj.allAmt);
		            $("#allPntA").val(data.obj.allPnt);
		            $("#memNameA").val(data.obj.memName);
		            $("#perTypeA").val(data.obj.perTypeName);
		            $("#personIdA").val(data.obj.personId);
			        }else{
						alert("此卡不能被退回!");
						return false;
				        }
		        }
		    });
	    }else{
	    	alert("卡号填写不正确!");
	    	return false;
	    }
	}
	</script>
  </head>
  <body>

  <div class="Position">
		当前位置是：HOME &gt;&gt;账户管理 &gt;&gt; 退卡管理
	</div>
	<s:form action="card/cardReturn" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method"/>
	<s:if test="#session.user_session.userLevel!=2">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>退卡卡号：</th>
		        <td width="30%">   
		        	<s:textfield name="cardReturnDTO.cardNo"  id="cardNoA"  maxlength="20" cssClass="formInput"  theme="simple"  onkeyup = "replaceToNum(this);" onblur="checkCardNo()" />
		        	<label id=cardNoAMsg style="display: none;"></label> 
		 			<label id="cardNoAError" style="display: none;"></label>
		        </td>
		        <th align="right" width="20%">卡余额：</th>
		        <td width="30%" ><input type="text" name="cardReturnDTO.allAmt" id="allAmtA"  maxlength="20" class="formInput" readonly="readonly"/></td>
			</tr>
		 	<tr>
		 		<th align="right" width="20%">卡积分：</th>
		        <td width="30%" ><input type="text" name="cardReturnDTO.allPnt" id="allPntA"  maxlength="20" class="formInput" readonly="readonly"/></td>
		        <th align="right" width="20%">持卡人：</th>
		        <td width="30%"><input type="text" name="cardReturnDTO.memName" id="memNameA"  maxlength="20" class="formInput" readonly="readonly"/></td>
		  	</tr>
		  	<tr>
		  		
		  		<th align="right" width="20%">持卡人证件类型：</th>
		        <td width="30%"><input type="text" name="cardReturnDTO.perTypeName" id="perTypeA"  maxlength="20" class="formInput" readonly="readonly"/></td>
		        <th align="right" width="20%">持卡人证件号：</th>
		        <td width="30%" ><input type="text" name="cardReturnDTO.personId" id="personIdA"  maxlength="20" class="formInput" readonly="readonly"/></td>
		  	</tr>
		  	<tr >
		        <th align="right" width="20%">操作人：</th>
		        <td width="30%" ><s:property value="cardReturnDTO.proposer" /><s:hidden name="cardReturnDTO.proposer" id="proposer"/></td>
		        <th align="right" width="20%"><span class="Color5">* </span>退款金额：</th>
		  		<td width="30%" colspan="3">
		  			<s:textfield name="cardReturnDTO.relAmt" id="relAmt" cssClass="formInput" maxlength="20" theme="simple" onblur="setRelAmt(this)" onkeypress= "return onlyNumFloadt(this);"/>
		  			<label id=relAmtMsg style="display: none;"></label> 
		 			<label id="relAmtError" style="display: none;"></label>
		 			<label id=compare style="display: none;"></label>
		  		</td>
		  	</tr>
		  	<tr>
		  		<th align="right" width="20%">退卡描述：</th>
		        <td colspan="3"><s:textarea name="cardReturnDTO.descr" id="descr"  cssClass="formTextarea" cols="45" rows="5" maxlength="255" /></td>	
		  	</tr>
		</table>
	</s:if>
	 <div class="formTableBottom">
	 	
			<my:permission key='sy-2106-02' value='退卡信息添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		<input type="button" class="formButton" value="返 回" onclick="go('card/cardReturn!list')"/>
	 </div>
	 </s:form>
</body>
</html>