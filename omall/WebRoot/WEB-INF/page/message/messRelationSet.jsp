<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.paySystem.ic.web.dto.system.UserSession" %>
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
	<title>参数使用关系管理</title>
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
				        			
	       	if(method.value=='checkUI'){
	             setInputDisabled();
	             $("#mfpDesc").attr("readonly","true");
                 $("#mfpDesc").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
	        }
	       	if(method.value=='addSave'){
	       		$("#useLives").val(1);
	        }
	        
	    });

		var orgNameFlag = false;
		var merNameFlag = false;
		var messNameFlag = false;
		var useLivesFlag = false;
		var descFlag = false;
		

		/*校验机构商户名称*/
		var validateOrgName = function(){
			
			var orgName = $("#name").val();
			var merName = $("#merName").val();
			var userLevel="<%=((UserSession)(session.getAttribute("user_session"))).getUserLevel()%>";
			$("#orgNameError").hide();
			$("#merNameError").hide();  
			if(userLevel==0&&""==orgName){
				$("#orgNameError").html("机构名称不能为空！"); 
				$("#orgNameError").show();
                orgNameFlag = false;
			} else if(userLevel==1&&""==merName){
				$("#merNameError").html("商户名称不能为空！");
				$("#merNameError").show();   
				orgNameFlag = false;
		    } else{
		    	$("#orgNameError").hide();
				$("#merNameError").hide();  
		    	orgNameFlag = true;
			}
		}

		/*校验短信参数名称*/
		var validateMessName = function(){
			var messName = $.trim($("#messName").val());
			$("#messNameError").hide(); 
			if(messName==""){
				$("#messNameError").html("参数名称不能为空!");
				$("#messNameError").show(); 
				messNameFlag = false;
			} else{
				$("#messNameError").hide(); 
				messNameFlag = true;               
			}						
		}

		validateName = function(){

			if(messNameFlag){
				/**Ajax传输参数*/
				var mprId = $.trim($("#mprId").val());
	            var orgId = $.trim($("#organId").val()); 
	            var merId = $.trim($("#merId").val());       	            	            
		    	var params = {
		    		"messDto.mprId" : mprId,
		    		"messDto.orgId" : orgId,
		    		"messDto.merId" : merId
				}; 					
				var actionUrl = "message/messagerelation!validateName";   
				$.ajax({   
			        async:false,
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
				    		$("#messNameError").html("该机构已存在参数关系!");
							$("#messNameError").show(); 				    	
							messNameFlag = false;
						}else {
							$("#messNameError").hide(); 
							messNameFlag = true;					    	
					 	}
					}
				 });		   
			}			

		}
				

		/*校验使用期限*/
		var validateUseLives = function(){
			var useLives = $.trim($("#useLives").val());
			$("#useLivesError").hide(); 
			if(useLives==""&&useLives==0){
				$("#useLivesError").html("使用期限不能为空");
				$("#useLivesError").show(); 
				useLivesFlag = false;	    
		    }else{
		    	$("#useLivesError").hide(); 
		    	useLivesFlag = true;
			}
		}

				
		/*校验描述*/
		var validateDesc = function(){
			var textareaLength=$.trim($("#mfpDesc").val()).length; 
			$("#mfpDescError").hide(); 
			if(textareaLength>=255){ 
				$("#mfpDescError").html("255以内字符！");
				$("#mfpDescError").show(); 
				descFlag = false;
			} else{
				$("#mfpDescError").hide(); 
				descFlag = true;
			}
		}
			

	function organsCheck(){
	$("#orgNameError").html("");
	$("#orgNameError").hide();
	$("#messNameError").html("");
	$("#messNameError").hide();
	validateName();
	}
		function merCheck(){
	$("#merNameError").html("");
	$("#merNameError").hide();
	$("#messNameError").html("");
	$("#messNameError").hide();
	validateName();
	}
		//修改方法
		var check = function() {
			validateOrgName();
			validateMessName();
			validateName();
			validateUseLives();
			validateDesc();
			
			if(orgNameFlag&&messNameFlag&&useLivesFlag&&descFlag){
				return true;
  			}else{
      			alert("页面信息填写有误，请根据提示信息填写！");
				return false;
      		}			 
		}

		
		
    </script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 短信管理  &gt;&gt; 参数使用关系管理
	</div>
	<jsp:include page="/WEB-INF/page/base/terminals/mercHelps.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/page/base/organs/organHelp.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/page/message/messParamHelp.jsp"></jsp:include>
	<s:form action="message/messagerelation" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>
	
		<table width="96%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
				<s:hidden name="messDto.mprId" id="mprId"></s:hidden>
				<s:if test="method=='addSave'">
					<s:if test="#session.user_session.userLevel==0">
				    <th align="right"><span class="Color5">* </span>机构名称:</th>
					<td width="30%"><s:textfield readonly="true" name="messDto.orgName" id="name" onchange="validateOrgName();" cssClass="formInput" theme="simple"/>
			            <s:hidden name="messDto.orgId" id="organId"></s:hidden>
					    <s:if test="method!='checkUI'">
					    <img alt="查找机构" src="images/search.gif" style="cursor:pointer;"  onmousedown="organsCheck();" onclick="ajaxOrganId();"/>
			        	</s:if>
			        	<label id="orgNameError" style="display: none;" class="errorMsg"></label>
			        </td>
					</s:if>
					<s:else>
					<th align="right"><span class="Color5">* </span>商户名称:</th>
					<td width="30%"><s:textfield name="messDto.merName" id="merName" readonly="true" maxlength="20" cssClass="formInput" theme="simple"/>
					    <s:hidden name="messDto.merId" id="merId"/>
					    <s:if test="method!='checkUI'">
					    <img alt="查找商户" src="images/search.gif" style="cursor:pointer;"  onmousedown="merCheck();" onclick="ajaxMerc();"/>
						</s:if>
						<label id="merNameError" style="display: none;" class="errorMsg"></label>
					</td>
					</s:else> 
				</s:if>
				<s:else>
					<s:if test="#session.user_session.userLevel==0">
					<th align="right">机构名称:</th>
					<td width="30%"><s:property value="messDto.orgName"/>
			            <s:hidden name="messDto.orgId" id="organId"></s:hidden>
			        </td>
			        </s:if>
			        <s:else>
					<th align="right">商户名称:</th>
					<td width="30%"><s:property value="messDto.merName"/>
					    <s:hidden name="messDto.merId" id="merId"/>
					</td>
					</s:else>
				</s:else>
				<th align="right"><span class="Color5">* </span>参数名称:</th>
		 		<td width="30%"><s:textfield name="messDto.messName" id="messName" onchange="validateName();" readonly="true" maxlength="20" cssClass="formInput" theme="simple"/>
				  	<s:hidden name="messDto.mfpId" id="mfpId"/>
				  	<s:if test="method!='checkUI'">
				  	<img alt="查找参数" src="images/search.gif" style="cursor:pointer;" onmousedown="organsCheck();" onclick="ajaxMfp();"/>
					</s:if>
					<label id="messNameError" style="display: none;" class="errorMsg"></label>
				</td>
			</tr>
			<tr>
		 		<th align="right"><span class="Color5">* </span>使用状态:</th>
				<td width="30%"><s:select name="messDto.state" id="state" list="#request.status" listKey="key" listValue="value" cssStyle="width:156px;" cssClass="formSelect" theme="simple"/></td>
				<th align="right"><span class="Color5">* </span>使用期限:</th>
		 		<td>
		 			<s:textfield id="useLives" name="messDto.useLives" maxlength="3" onblur="validateUseLives();" onkeypress="return onlyNum(this);" onkeyup="checkNum(this);" cssStyle="width:147.5px;" cssClass="formInput" theme="simple"/>(/月)
		 			<label id="useLivesError" style="display: none;" class="errorMsg"></label>
		 		</td>	
		    </tr>
		 	<tr>		 		
		      	<th align="right"><span class="Color5">* </span>开始时间:</th>
		        <td width="30%"><s:textfield id="beginTime" name="messDto.beginTime" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" cssStyle="width:150px;" maxlength="20" theme="simple"/>
		        </td>
		  		<th align="right"><span class="Color5"></span></th>
		 		<td></td>
		  	</tr>
		  	<tr>
		  		<th align="right">具体描述：</th>
		        <td>
					<s:textarea name="messDto.mfpDesc" id="mfpDesc" cssClass="formTextarea" onblur="validateDesc();" rows="3" cols="40" />
					<label id="mfpDescError" style="display: none;" class="errorMsg"></label>
				</td>
		 	</tr>
		 	</table>	
	 <div class="formTableBottom">
	 	<s:if test="method!='checkUI'">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1602-02' value='参数使用关系添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-1602-03' value='参数使用关系修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		</s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('message/messagerelation!list')"/>
	 </div>
	 </s:form>
</body>
</html>