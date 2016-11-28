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
	<title>油价信息管理</title>
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
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
		
		
	    $(document).ready(function (){
	     
	        var method = document.getElementById("method"); 	        
	        if(method.value=='checkDetail'){
	             setInputDisabled();
	             $("#inPrice").attr("disabled","disabled");
	             $("#salePrice").attr("disabled","disabled"); 
	             $("#govPrice").attr("disabled","disabled");
	             $("#descr").attr("disabled","disabled");
	            }
	        });
		
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
	   	   
	   	//验证进油价
	   	
	   	var inpriceflag=true;
	   	var salepriceflag=true;
	   	var govpriceflag=true;
	   	var descrflag=true;
	   	
		var validateInPrice = function(){
			var inprice=$("#inPrice").val().length; 
			$("#inPriceMsg").html(""); 
			if(inprice<1){ 
				$("#inPriceMsg").html("进油价不能为空！");
				inpriceflag = false;
			} else{
				inpriceflag = true;
			}
		}
		//验证售油价
		var validateSalePrice = function(){
			var saleprice=$("#salePrice").val().length; 
			$("#salePriceMsg").html(""); 
			if(saleprice<1){ 
				$("#salePriceMsg").html("售油价不能为空！");
				salepriceflag = false;
			} else{
				salepriceflag = true;
			}
		}
		//验证发改委售油价
		var validateGovPrice = function(){
			var govprice=$("#govPrice").val().length; 
			$("#govPriceMsg").html(""); 
			if(govprice<1){ 
				$("#govPriceMsg").html("发改委售油价不能为空！");
				govpriceflag = false;
			} else{
				govpriceflag = true;
			}
		}
		
		
		var validateDescr = function(){
			var textareaLength=$("#descr").val().length; 
			$("#descrMsg").html(""); 
			if(textareaLength>=200){ 
				$("#descrMsg").html("255以内字符！");
				descrflag = false;
			} else{
				descrflag = true;
			}
		}
		
		//修改方法
		var check = function() {
		   return inpriceflag&&salepriceflag&&govpriceflag&&descrflag;		   
		}
    </script> 
</head>
<body>
	<s:if test="method=='checkDetail'">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 油价信息管理&gt;&gt; 油价信息查看
	</div>
	</s:if>
	<s:else>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 油价信息管理&gt;&gt; 油价信息修改
	</div>
	</s:else>
	<s:form action="oilprice/oilpriceaction" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>	
		  <table class="formTable">
			<tr>			
		  		<th align="right" width="20%">油品名称：</th>
		  	    <td width="30%"><s:textfield name="oilpDto.oilTypeName" id="oilTypeName" disabled="true" cssClass="formInput"  cssStyle="width:150px;" theme="simple"/>
		  		<s:hidden name="oilpDto.oilId" id="oilId"></s:hidden>
		  		<s:hidden name="oilpDto.merOrgId" id="merOrgId"></s:hidden>
		  		<s:hidden name="oilpDto.merOrgSign" id="merOrgSign"></s:hidden>
		  		<s:hidden name="oilpDto.oilTypeId" id="oilTypeId"></s:hidden>
		  		</td>
		  		<th align="right" width="20%">售油方：</th>
		  		<td width="30%"><s:textfield name="oilpDto.merOrgName" id="merOrgName" disabled="true" cssClass="formInput"  cssStyle="width:150px;" theme="simple"/></td>
			</tr>
		  </table>
		  <table class="formTable">
		 	<tr>
		 		<s:if test="#session.user_session.userLevel==0">
		 		<th align="right" width="20%"><span class="Color5">* </span>进油价：</th>
				<td width="30%"><s:textfield name="oilpDto.inPrice" id="inPrice" onblur="validateInPrice();" maxlength="5" onkeyup="replaceToNumPoint(this);"
				  cssClass="formInput"  cssStyle="width:150px;" theme="simple"/>(元/升)
				<span id="inPriceMsg" class="Color5"></span>
				</td>
				</s:if>
				<s:else>
				<th align="right" width="20%">进油价：</th>
				<td width="30%"><s:textfield name="oilpDto.inPrice" id="inPrice" disabled="true"
				  cssClass="formInput"  cssStyle="width:150px;" theme="simple"/>(元/升)
				<s:hidden name="oilpDto.inPrice" id="inPrice2"></s:hidden>
				</td>
				</s:else>
				<th align="right" width="20%"><span class="Color5">* </span>售油价：</th>
				<td width="30%"><s:textfield name="oilpDto.salePrice" id="salePrice" onblur="validateSalePrice();" maxlength="5" onkeyup="replaceToNumPoint(this);"
				  cssClass="formInput"  cssStyle="width:150px;" theme="simple"/>(元/升)
				<span id="salePriceMsg" class="Color5"></span>
				</td>	
		  </tr>
		  </table>
		  <table  class="formTable">	     
		    <tr>
		    	<s:if test="#session.user_session.userLevel==0">
				  	<th align="right" width="20%"><span class="Color5">*</span>发改委售价：</th>
				    <td width="30%"><s:textfield name="oilpDto.govPrice" id="govPrice" onblur="validateGovPrice();" maxlength="5" onkeyup="replaceToNumPoint(this);"
				      cssClass="formInput"  cssStyle="width:150px;" theme="simple"/>(元/升)
				    <span id="govPriceMsg" class="Color5"></span> 
				    </td>
			    </s:if>
			    <s:else>
				    <th align="right" width="20%">发改委售价：</th>
				    <td width="30%"><s:textfield name="oilpDto.govPrice" id="govPrice" disabled="true"
				      cssClass="formInput"  cssStyle="width:150px;" theme="simple"/>(元/升)
				       <s:hidden name="oilpDto.govPrice" id="govPrice2"></s:hidden> 
				    </td>
			    </s:else>				    
				<th align="right" width="20%"><span class="Color5"></span>创建时间：</th>
		        <td width="30%"><s:textfield name="oilpDto.createTime" id="createTime" disabled="true" cssClass="formInput" cssStyle="width:150px;" theme="simple"/></td>
			</tr>
		  </table>
		  <s:if test="method=='checkDetail'">
		   <table class="formTable">	     
		    <tr>
			  	<th align="right" width="20%">操作人：</th>
			    <td width="30%"><s:textfield name="oilpDto.operId" id="operId" disabled="true" cssClass="formInput"  cssStyle="width:150px;" theme="simple"/>
			    <span id="govPriceMsg" class="Color5"></span>  
			    </td>				    
				<th align="right" width="20%">更新时间：</th>
		        <td width="30%"><s:textfield name="oilpDto.updateTime" id="updateTime" disabled="true" cssClass="formInput" cssStyle="width:150px;" theme="simple"/></td>
			</tr>
		  </table>
		  </s:if>		      			
		  <table class="formTable">
		  	<tr>
		  		<th align="right" width="20%">油价描述：</th>		        
		 		<td>
					<s:textarea name="oilpDto.descr" id="descr" onblur="validateDescr();" rows="5" cols="85"/>
					<span id="descrMsg" class="Color5"></span>
				</td>
		 	</tr>
		  </table>
	 <div class="formTableBottom">		
			<my:permission key='sy-1601-03' value='油价信息修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>		
		<input type="button" class="formButton" value="返 回" onclick="go('oilprice/oilpriceaction!list')"/>
	 </div>
	 </s:form>
	
</body>
</html>