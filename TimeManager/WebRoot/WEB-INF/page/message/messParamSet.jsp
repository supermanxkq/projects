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
	<title>短信参数管理</title>
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
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
		
		
		//初始化DIV显示结果 
		function resetShowDiv(messType){
			 if(messType=="0"){
				 $("#tiao").css("display","block");
				 $("#yue").css("display","none");
           	     $("#tiao").show();
                 $("#yue").hide();
                }
            else{
            	 $("#tiao").css("display","none");
				 $("#yue").css("display","block");
           	     $("#tiao").hide();
                 $("#yue").show();
                }
			}
			
			
		//根据限制类型显示不同限制信息 ：messType下拉框onChange触发
		function changeDiv(){
                  var messType = $("#messType").val();
                  if(messType=="0"){
                         $("#tiao").show();
                         $("#yue").hide();
                      }
                  else
                      {
                         $("#tiao").hide();
                         $("#yue").show();
                      }
			}
		
		function replaceToNumPoint(obj){
		   if(isNaN(obj.value)){
			 	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
			 	obj.value = obj.value.replace(/^[.]/g,"");  //验证第一个字符是数字而不是.
			 	obj.value = obj.value.replace(/[.]{2,}/g,""); //清除第一个点以外的点
			 	
			 }
	       }
	    function replaceToNum(obj){		   
		   if(isNaN(obj.value)){
			 	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
			 	obj.value = obj.value.replace(/[^.]/g,"");  //验证第一个字符是数字而不是.					 	
			 }
	   	   }
		
		//修改方法
		var check = function() {
		
		   var mfpId = $.trim($("#mfpId").val());
		   var messName = $.trim($("#messName").val());
		   var useSign = $.trim($("#useSign").val());   //使用状态
		   var messType =$.trim( $("#messType").val());  //参数类型
		   var messPeriod = $.trim($("#messPeriod").val());//使用条数
		   var messFee = $.trim($("#messFee").val());  //费用
		   var miniPeriod =$.trim( $("#miniPeriod").val());//最低条数
		   var singleFee = $.trim($("#singleFee").val());  //费用
		   var mfpDesc = $.trim($("#mfpDesc").val());  //参数描述
		   
		   if(messName==""){
                alert("请填写参数名称!");
                return false;
			   }
		   if(messType==0&&miniPeriod.length==0&&0==miniPeriod){
                 alert("请填写条数!");
                 return false;
			   }
		   if(messType==0&&singleFee.length==0){
                 alert("请填写费用!");
                 return false;
			   }
		   if(singleFee > "5"){
		         alert("单条费用设置不合理（太高）");
		         return false;
		       }
		   if(singleFee!=""){
			     var floatreg = /^\d{1}\.\d{1,3}$/;
			     if(!floatreg.test(singleFee)){
	                    alert("单条费用格式错误，请重新填写.形如:0.1/0.15");
	                    return false;
	  			   }		   
		   	   }    
		  
		   if(messType=="1"&&messPeriod==""){
                 alert("请填写条数!");
                 return false;
			   }
		   if(messType=="1"&&messFee==""){
                 alert("请填写费用!");
                 return false;
			   }   
		   if(mfpDesc.length>255){
                  alert("机构描述输入字符过长，请重新输入!");
                  return false;
			   }
		}
    </script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 短信费管理&gt;&gt; 短信参数管理
	</div>
	
	<s:form action="message/messageparam" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method"/>	
		  <table class="formTable">
			<s:if test="method=='addSave'">
			<tr>				
		  	    <th align="right" width="20%"><span class="Color5">* </span>参数名称：</th>
		  		<td width="30%"><s:textfield name="messDto.messName" id="messName" maxlength="20" cssStyle="width:150px;" cssClass="formInput" theme="simple"/>
		  		<s:hidden name="messDto.mfpId" id="mfpId"></s:hidden></td>
		  		<th align="right" width="20%"></th>
		  		<td width="30%"></td>
			</tr>
			</s:if>
			<s:else>
			<tr>			
		  		<th align="right" width="20%">参数名称：</th>
		  	    <td width="30%"><s:property value="messDto.messName"/><s:hidden name="messDto.messName" id="messName"/>
		  		<s:hidden name="messDto.mfpId" id="mfpId"></s:hidden></td>
		  		<th align="right" width="20%"></th>
		  		<td width="30%"></td>
			</tr>
			</s:else>
		  </table>
		  <table class="formTable">
		 	<tr>
		 		<th align="right" width="20%"><span class="Color5">* </span>使用状态：</th>
				<td width="30%"><s:select name="messDto.useSign" id="useSign" list="#request.status" listKey="key" listValue="value" cssStyle="width:156px;" cssClass="formSelect" theme="simple"/></td>
				<th align="right" width="20%"><span class="Color5">* </span>套餐类型：</th>
				<td width="30%"><s:select name="messDto.messType" id="messType" onchange="changeDiv();" list="#request.type" listKey="key" listValue="value" cssStyle="width:156px;" cssClass="formSelect" theme="simple"/></td>	
		  </tr>
		  </table>
		  
		  <div id="tiao" style="display: block;">
		  <table  class="formTable">	     
		    <tr>
			  	<th align="right" width="20%"><span class="Color5">* </span>最低使用条数：</th>
			    <td width="30%"><s:textfield name="messDto.miniPeriod" id="miniPeriod" maxlength="5"  onkeyup="replaceToNum(this);" cssClass="formInput"  cssStyle="width:150px;" theme="simple"/></td>				    
				<th align="right" width="20%"><span class="Color5">* </span>单条收费标准：</th>
		        <td width="30%"><s:textfield name="messDto.singleFee" id="singleFee" maxlength="5" onkeyup="replaceToNumPoint(this);" cssClass="formInput" cssStyle="width:150px;"  theme="simple"/>(如：0.1或0.15)</td>
			</tr>
		  </table>
		  </div>		  
		  <div id="yue" style="display: none;">
		  <table class="formTable">		
	        <tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>包月条数：</th>
		        <td width="30%"><s:textfield name="messDto.messPeriod" id="messPeriod"  maxlength="5"  onkeypress="return onlyNum(this);" onkeyup="replaceToNum(this);" cssClass="formInput"  cssStyle="width:150px;" theme="simple"/></td>
		        <th align="right" width="20%"><span class="Color5">* </span>包月费用：</th>
		        <td width="30%"><s:textfield name="messDto.messFee" id="messFee" maxlength="5"  onkeypress="return onlyNum(this);" onkeyup="replaceToNumPoint(this);" cssClass="formInput" cssStyle="width:150px;" theme="simple"/></td>
			</tr>
		  </table>
		  </div>		    			
		  <table class="formTable">
		  	<tr>
		  		<th align="right" width="20%">参数描述：</th>		        
		 		<td>
					<s:textarea name="messDto.MfpDesc" id="mfpDesc" rows="2" cols="78"/>
				</td>
		 	</tr>
		  </table>
	 <div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1601-02' value='短信参数添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-1601-03' value='短信参数修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		<input type="button" class="formButton" value="返 回" onclick="go('message/messageparam!list')"/>
	 </div>
	 </s:form>
	
</body>
</html>