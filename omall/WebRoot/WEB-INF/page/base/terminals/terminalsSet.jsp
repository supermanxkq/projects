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
	<title>终端管理</title>
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
	<script src="js/pubValidate.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>

	<script type="text/javascript">

    
    var termNameFlag = false;
    var merIdFlag = false;
    var saleRateFlag = true;
    $(document).ready(function (){
        var method = document.getElementById("method"); 
        if(method.value=='checkDetail'){
             setInputDisabled();
             $("#state").attr("disabled","disabled");  
             $("#allowRechSign").attr("disabled","disabled"); 
             $("#allowRetSign").attr("disabled","disabled");    
        }
        
       var allowRetSign = document.getElementById("allowRetSign");
        if(allowRetSign.value==0){
              $("#retTimeLimit").attr("disabled","disabled");
            }
        else{
        	$("#retTimeLimit").attr("disabled",false);
            }
        });
    function termNameBlur(obj){
        var type = ["isNull"];
        var errorMsg = ["终端名称不能为空!"];
        //验证商户名称格式
        flag1 = showErrorMsg(obj,type,errorMsg,"errorMsg","warnMsg");
        //验证商户名称长度
        flag2 = checkLenMsg(obj,"长度不能大于30个汉字","errorMsg","",60);
        if(flag1&&flag2){
            termNameFlag = true;
         }
        return termNameFlag;
	}
	function termNameFocus(obj){
		   showWarnMsg(obj,"可填写汉字、字母以及数字!","errorMsg","warnMsg");
		}
    function saleRateBlur(obj){
              var type = ["isNull","isRate"];
              var errorMsg = ["折扣比率不能为空，无折扣填0!","输入格式有误!格式如：0.001"]; 
              saleRateFlag = showErrorMsg(obj,type,errorMsg,"saleRateErrorMsg","saleRateWarnMsg");
              return saleRateFlag;
        }
    function saleRateFocus(obj){
            showWarnMsg(obj,"填写格式，如：0.01或0.001","saleRateErrorMsg","saleRateWarnMsg");
        }
	
     //变更是否允许退货
    function changeAllowRet(){
           var allowRetSign = $("#allowRetSign").val();
            if(allowRetSign==0){
                  $("#retTimeLimit").val(0);
                  $("#retTimeLimit").attr("disabled","disabled");
              }
            else{
            	$("#retTimeLimit").val(10);
                $("#retTimeLimit").attr("disabled",false);
                } 
		  }
	var validateDescr= function(){
				var textareaLength=$("#termDesc").val().length;  
				if(textareaLength>=200){ 					
					$("#descrValue").html("200以内字符！");
					return false;
				} 
				else{
					$("#descrValue").html("");
					return true;
				}
			}
     
	    
		//修改方法
		var check = function() {
		    var termId = $("#termId").val();
		    var merId = $("#merId").val();
		    var organId = $("#sdorganId").val();
		    var superPwd = $("#superPwd").val();
		    var rePwd = $("#rePwd").val();
		    var saleRate = $("#saleRate").val();
		    var termName = $("#termName").val();
		    var retTimeLimit=$("#retTimeLimit").val();
		    var allowRetSign = $("#allowRetSign").val();
		    if(""==termId){
		       alert("终端编号不能为空");
		       $("#termId").focus();
		       return false;
		    }
		    if (termId.length!=8){
		    	alert("请输入8位终端编号!");
                $("#termId").focus(); 
		    	return false;
			}
			var type = ["isNull"];
			var errorMsg = ["所属商户不能为空!"];
			var merId = document.getElementById("merId");
			merIdFlag = showErrorMsg(merId,type,errorMsg,"merIdErrorMsg","merIdWarnMsg"); 

			termNameFlag = termNameBlur(document.getElementById("termName"));
			saleRateFlag = saleRateBlur(document.getElementById("saleRate"));
			
			if(!(merIdFlag&&termNameFlag&&saleRateFlag)){
	               alert("填写信息有错误，请重新填写!(带*为必填项)"); 
	               return false;
			}
			if(!(validateDescr())){
				   return false;
				}
			
			if(saleRate>1){
                 alert("折扣比率不能大于1!");
                 
                 $("#saleRate").focus();
                 return false;
			}
			if(""==organId||organId.length!=8){
			   alert("请填写8位机构编号!");
			 
			   $("#organId").focus();
			   return false;
			}
			if( $("#retTimeLimit").val()==''){
                 alert("允许退货时，退货期限不能为空!");
                 $("#retTimeLimit").focus();
                 return false;
				}

		}
</script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 终端管理
	</div>
	<jsp:include page="/WEB-INF/page/base/terminals/mercHelps.jsp"></jsp:include>
	<s:form action="base/terminals" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>
	<%--<s:if test="#session.user_session.userLevel==0">
		--%><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>终端编号：</th>
		        <td width="30%"><s:property value="terminalsDTO.termId"/><s:hidden name="terminalsDTO.termId" id="termId"/><!--<s:property value="terminalsDTO.termId"/>--></td>
		        <th align="right" width="20%"><span class="Color5">* </span>终端名称：</th>
		        <td width="30%"><s:textfield name="terminalsDTO.termName" onkeyup = "allowEnCnNu(this);" onblur="termNameBlur(this);" onfocus="termNameFocus(this);" id="termName" cssClass="formInput" theme="simple"/> <label id="warnMsg" style="display: none;"></label> <label id="errorMsg" style="display: none;"></label></td>
			</tr>
			<s:if test="#session.user_session.userLevel==0">
		 	<tr>
		 		<th align="right"><span class="Color5">* </span>所属商户：</th>
		 		<td>
		 		<s:textfield name="terminalsDTO.merName" readonly="true" id="merName" cssClass="formInput" theme="simple"/>
		 		<s:hidden name="terminalsDTO.merId" id="merId">
		 		</s:hidden><img alt="查找商户" src="images/search.gif" style="cursor:pointer;" onclick="ajaxMerc();"/><label id="merIdErrorMsg" style="display: none"></label>
		 		</td>
		      	<th align="right"><span class="Color5">* </span>收单机构：</th>
		        <td>
		        <s:textfield name="terminalsDTO.orgName" readonly="true" id="organName" cssClass="formInput" theme="simple"/>
		        <s:hidden name="terminalsDTO.organId" id="sdorganId"></s:hidden>
		        </td>
		  	</tr>
		  	</s:if>
		  	
		  	<s:if test="#session.user_session.userLevel==1">
		  	  <tr>
		 		<th align="right"><span class="Color5">* </span>所属商户：</th>
		 		<td>
		 		<s:textfield name="terminalsDTO.merName" readonly="true" id="merName" cssClass="formInput" theme="simple"/>
		 		<s:hidden name="terminalsDTO.merId" id="merId">
		 		</s:hidden><img alt="查找商户" src="images/search.gif" style="cursor:pointer;" onclick="ajaxMerc();"/>
		 		</td>
		      	<th align="right"><span class="Color5">* </span>收单机构：</th>
		        <td>
		        <s:property value="terminalsDTO.orgName"/><s:hidden name="terminalsDTO.organId" id="sdorganId"></s:hidden>
		        </td>
		  	</tr>
		  	</s:if>
		  	<tr>
		  		<th align="right">消费折扣比率：</th>
		        <td><s:textfield name="terminalsDTO.saleRate" id="saleRate" onblur = "valiNumFloatBlur(this);saleRateBlur(this);" onfocus="saleRateFocus(this);" maxlength="6" onkeypress="return onlyNumFloadt(this);" cssClass="formInput" theme="simple"/><label id="saleRateErrorMsg" style="display: none"></label><label id="saleRateWarnMsg" style="display: none"></label></td>
		      	<th align="right">状态：</th>
		        <td><s:select name="terminalsDTO.state" id="state" list="#request.statusValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
		 	</tr>
		  	<tr>
		      	<th align="right"><span class="Color5">* </span>是否允许充值：</th>
		        <td><s:select  name="terminalsDTO.allowRechSign" id="allowRechSign" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
		      	<th align="right"><span class="Color5">* </span>是否允许退货：</th>
		        <td><s:select name="terminalsDTO.allowRetSign" id="allowRetSign" list="#request.visibleValues" onchange="changeAllowRet();" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
		   	</tr>
		  	<tr>
		  		<th align="right">折扣率优先级：</th>
		  		<td><s:select  name="terminalsDTO.discPriority" disabled="true" id="discPriority" list="#request.rebateFlagValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
		        <!--<td><s:textfield name="terminalsDTO.discPriority" id="discPriority" maxlength="20" cssClass="formInput" theme="simple"/></td>
		      	--><th align="right">允许退货期限：</th>
		        <td><s:textfield name="terminalsDTO.retTimeLimit" onkeypress="return onlyNum(this);"  onkeyup="replaceToNum(this);" id="retTimeLimit" maxlength="3" cssClass="formInput" theme="simple"/> 天</td>
		      	
		 	</tr><%--
		   	<tr>
		      	<th align="right"><span class="Color5">* </span>主管密码：</th>
		         <td colspan="3"><s:textfield name="terminalsDTO.superPwd" id="superPwd" maxlength="20" cssClass="formInput" theme="simple"/></td>
		      	
		  	</tr>
		  
		   	--%><tr>
		      	<th align="right">终端描述：</th>
		        <td colspan="3"><s:textarea name="terminalsDTO.termDesc" id="termDesc" onblur="validateDescr();" cssClass="formTextarea" cols="45" rows="5" />		        
		   		<span id="descrValue" class="Color5"></span></td>
		   	</tr>
		</table><%--
	</s:if>
	
	 --%><div class="formTableBottom">
	   <s:if test="#session.user_session.userLevel!=2">
	 	<s:if test="method=='addSave'">	
			<my:permission key='sy-1202-02' value='终端添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-1202-03' value='终端修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		</s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('base/terminals!list')"/>
	 </div>
	 </s:form>
</body>
</html>