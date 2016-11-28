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
	<title>账户解冻</title>
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
	<script type="text/javascript"  src="js/areaSelect.js"></script>

	<script type="text/javascript">

	    function queryCardNo(){
	 
	        var cardNoView = $("#cardNoView").val();
			$("#cardNoViewValue").html("");
	        var binId =$("#binId").val();
			
			if (cardNoView==''){
				$("#cardNoViewValue").html("卡号不能为空!");
		    	return false;
			}
			 
			var params = {
		        "accRecordDTO.cardNoView" : cardNoView,
		        "accRecordDTO.binId": binId
		        
		    }; 
		   
			var actionUrl = "account/accThaw!queryCardNo";   
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
		             	
		                $("#cardNoViewValue").html("");
		                $("#flag").val("1");
		           	}else {
		           
		            	$("#cardNoViewValue").html("请检查卡的状态或者该卡BIN没有此号");
		            	$("#flag").val("0");
		            	
		            }
		        }
		    });
		}
		var changeBin =function(){
		
		 var cardNo =$("#cardNoView").val();
		 
		 if(cardNo!=null){
		   $("#cardNoView").val("");
		   $("#cardNoViewValue").html("");
		 }
		}
		
        var ajaxTypes =function(){
        
		    var accTId =$("#accTId").val();
		    
		    var cardNoView =$("#cardNoView").val();
		    if(cardNoView==null||cardNoView==""){
		      queryCardNo();
		    }
		    var params = {
		        "accRecordDTO.cardNoView" : cardNoView,
		        "accRecordDTO.accTId" :accTId
		    }; 
		   
			var actionUrl = "account/accThaw!ajaxTypes";   
			
		    $.ajax({
	        url : actionUrl,
			data : params,
			dataType : "json",
			cache : false,
			type : "POST",
			error : function(data, textStatus) {
				alert("系统交互错误！");
			},
			success : function(data, textStatus) {
             if(data.flag){
                 $("#typeIdErrorMsg").html("");
                 $("#flag1").val("1");
              }else{
                 $("#typeIdErrorMsg").html("该账户已解冻或账户类型不存在!");
                 $("#flag1").val("0");
              }
			}
		});
	}
	var check = function() {

		var cardNoView = $("#cardNoView").val();
		var accTId =$("#accId").val();
		var flag = $("#flag").val();
		var flag1 =$("#flag1").val();
		var accTId = $("#accTId").val();
        var binId = $("#binId").val();
        
        if(binId==-1){
		  alert("请选择卡BIN");
		  return false;
		}
		if (cardNoView == ''||cardNoView==null) {
			alert("卡号不能为空");
			return false;
		}
		if(accTId==-1){
		  alert("请选择账户类型！");
		  return false;
		}
		if (flag != "1") {
			alert("请按照提醒内容进行修改！");
			return false;
		}
		if(flag1 !="1"){
		   alert("请按照提醒内容进行修改！！！");
		   return  false;
		}

	}
	function loadset(){
	 var consoleDlg = $("#method").val();
		 
			if(consoleDlg=="viewUI"){
			   $("#binId").attr("disabled",true);
			   $("#accTId").attr("disabled",true);
			   $("#cardNoView").attr("disabled",true);
			   $("#descApp").attr("disabled",true)
			    var submits = $(":submit");
    	      submits.attr("disabled","disabled");
             submits.removeClass("formButton").addClass("formButtonDisabled");
			}
	}
	
	
	/**删除不是数字的字符**/
	 function replaceToNum(obj){
		 if(isNaN(obj.value)){
			 	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
			 	obj.value = obj.value.replace(/^[.]/g,"");  //验证第一个字符是数字而不是.
			 	obj.value = obj.value.replace(/[.]{2,}/g,""); //清除第一个点以外的点
			 	 //清除零整数
		}
	 }
</script> 
</head>
<body onload="loadset();">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 账户管理 &gt;&gt; 账户解冻
	</div>
	<s:form action="account/accThaw" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>
	<input type="hidden" id="flag" />
	<input type="hidden" id="flag1" />
	
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>卡BIN：</th>
		        <td width="30%"><s:select name="accRecordDTO.binId" id="binId" list="#request.binsList" 
		        listKey="key" listValue="value"  headerKey="-1" headerValue="请选择" onchange="changeBin();"
		         cssClass="formSelect" theme="simple"/>
		         <span class="Color5" id="singleRechAmtValue"></span>
		         </td>
		        <th align="right" width="20%"><span class="Color5">* </span>卡号：</th>
		        <td width="30%">
							<s:textfield name="accRecordDTO.cardNoView" id="cardNoView"  maxlength="6"
							cssClass="formInput" theme="simple" onblur="queryCardNo();" />
				          <span class="Color5" id="cardNoViewValue" ></span>
		        </td>
			</tr>
		
				
		 	<tr >
		 	<th align="right">账户类型：</th>
						
				<td >	
                       <s:select name="accRecordDTO.accTId" id="accTId" 
                            list="#request.typesList" listKey="key" listValue="value" onchange="ajaxTypes();" 
                             headerKey="-1" headerValue="请选择"
                             cssClass="formSelect" theme="simple"/>
                             <label id="typeIdErrorMsg" class="Color5"  ></label>
               </td>
				
		  	</tr>
		  	
		  	<tr>
					<th align="right">
						账户冻结描述：
					</th>
					<td colspan="3">
						<s:textarea name="accRecordDTO.descApp" id="descApp"
							cssClass="formTextarea" cols="45" rows="20" maxLength="240" />
					</td>
				</tr>
		</table>
		
	<div class="cardNoArea"> 
	<table id="cardInfoTb" class="listTable" width="100%">
	
	</table>
	</div>
	
	
	 <div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-2108-02' value='账户解冻添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		
		<input type="button" class="formButton" value="返 回" onclick="go('account/accThaw!list')"/>
	 </div>
	 </s:form>
</body>
</html>