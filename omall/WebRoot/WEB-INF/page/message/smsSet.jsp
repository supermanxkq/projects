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
	<title>短信群发管理</title>
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

	        if(method.value=='editSave'||method.value=='checkDetail'){

	        	if(method.value=='checkDetail'){
		             setInputDisabled();
		             $("#smsContent").attr("readonly","true");
	                 $("#smsContent").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
		            }
								
				var groups=document.getElementsByName("groupName");
				var groupIds = $("#groupIds").val();

				for(var i=0;i<groups.length;i++){					
					if(groupIds.search(','+groups[i].value+',') > -1){						
						groups[i].checked = true;						
					}
				}
	        }


	        var smsType = $.trim($("#smsType").val());
	        var tr = document.getElementById("tr");
            if(smsType=="0"){               	 
                tr.style.display = "";                                                              
            }
            else{
            	tr.style.display = "none";
            }
            
	        });

			var smsTitleFlag = false;
			var smsContentFlag = false;
			var smsGroupFlag = false;
			
			//根据限制类型显示不同限制信息 ：messType下拉框onChange触发
			function changeDiv(){
                var smsType = $.trim($("#smsType").val());
                var tr = document.getElementById("tr");
                if(smsType=="0"){               	 
                    tr.style.display = "";                                                              
                }
                else{
                	tr.style.display = "none";
                }
			}
        
	   	   		
		
			//判断短信标题不为空
			var validatesmsTitle= function(){
				var smsTitle =$.trim($("#smsTitle").val());  
				$("#smsTitleValue").html("");
				$("#smsTitleValue").hide();
				if(smsTitle==""||smsTitle==null){ 					
					$("#smsTitleValue").html("输入短信标题！");
					$("#smsTitleValue").show();
					smsTitleFlag = false;
					return smsTitleFlag;
				}else if(smsTitle.length>=30){
				$("#smsTitleValue").html("输入短信标题长度超过限制长度！");
				$("#smsTitleValue").show();
				}else{
					$("#smsTitleValue").html("");
					$("#smsTitleValue").hide();
					smsTitleFlag = true;
					return smsTitleFlag;
				}
			}


			function valContent(obj){
				obj.value=obj.value.substring(0,179);
			}
					 
			/**获取选中的模版的数据*/		
			var getStem = function(){
			
		            /**Ajax传输参数*/
		            var stemId = $("#stemId").val();          	            	            
			    	var params = {
			    		"smsTemDto.stemId" : stemId
					}; 
						
					var actionUrl = "message/smstemaction!getStem";   
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
					    		if(data.obj.stemTitle!=null){
						    		$("#smsContent").val(data.obj.stemTitle);
						    	}else{
						    		$("#smsContent").val("");					    	
						    	}
						    	if(data.obj.stemContent!=null){					    		
						    		$("#smsContent").val(data.obj.stemContent);
						    	}else{					    		
						    		$("#smsContent").val("");
						    	}					    	
						    	return true;
							}else {
						    	return false;						    	
						 	}
						}
					 });		    	
				}
			
			//判断短信内容不为空
			var validatesmsContent= function(){
				
				var smsContent =$("#smsContent").val();  
				$("#smsContentValue").html("");
				$("#smsContentValue").hide();
				if(smsContent==""||smsContent==null){ 					
					$("#smsContentValue").html("输入短信内容！");
					$("#smsContentValue").show();
					smsContentFlag = false;					
				}else if(smsContent.length>=180){
					$("#smsContentValue").html("输入短信内容长度超过限制长度(180字以内)！");
					$("#smsContentValue").show();
					smsContentFlag = false;	
				} else{
					$("#smsContentValue").html("");
					$("#smsContentValue").hide();
					smsContentFlag = true;					
				}
			}


			
		//修改方法
		var check = function() {
		
		   	var smsId = $.trim($("#smsId").val());
		   	var smsTitle = $.trim($("#smsTitle").val());
		   	var smsType = $.trim($("#smsType").val());  
		   	var smsContent =$.trim( $("#smsContent").val()); 
			
			var groupIds="";
			var groups=document.getElementsByName("groupList.groupId");
			for(var i=0;i<groups.length;i++){
				if(groups[i].checked){
					//拼接群组编号
					groupIds+=";"+groups[i].value;
				}
			}
			groupIds=groupIds.substring(1);
			if(smsType=="0"){
				$("#smsGroupValue").html("");
				$("#smsGroupValue").hide();
				if(groupIds==""||groupIds==null){
					$("#smsGroupValue").html("请选择会员分组！");
					$("#smsGroupValue").show();
					smsGroupFlag = false;
					
				}else{
					$("#smsGroupValue").html("");
					$("#smsGroupValue").hide();
					smsGroupFlag = true;
				}
			}else{
				smsGroupFlag = true;
			}
			validatesmsTitle();
			validatesmsContent();

			if(!(smsTitleFlag&&smsContentFlag&&smsGroupFlag)){
				alert("请检查输入信息，带*号的为必填项！");
				return false;
			}else{
				return true;
			}

		}
		
    </script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 短信管理 &gt;&gt; 短信群发管理
	</div>
	
	<s:form action="message/shortMesSend" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">	
	<s:hidden name="method" id="method" />
		  <table class="formTable">
			<tr>			
		  		<th align="right" width="20%"><span class="Color5">* </span>短信标题：</th>
		  	    <td width="30%"><s:textfield name="smsDto.smsTitle" id="smsTitle" cssClass="formInput" onblur="validatesmsTitle()"  cssStyle="width:150px;" theme="simple" />
		  	    <label id="smsTitleValue" style="display: none;" class="errorMsg"></label>
		  		<s:hidden name="smsDto.smsId" id="smsId"></s:hidden></td>
		  		<th align="right" width="20%"><span class="Color5">* </span>发送人群：</th>
				<td width="30%"><s:select name="smsDto.smsType" id="smsType" onchange="changeDiv();" list="#request.type" listKey="key" listValue="value" cssStyle="width:156px;" cssClass="formSelect" theme="simple"/></td>	
			</tr>
			<tr id="tr" style="display:none;">
		   		<th align="right" width="20%">选择群组：</th>
		   		<td colspan="3">
		   			<table frame="void" rules="none" border="0" cellpadding="0" cellspacing="0" style="border-collapse:collapse;">
		   				<%int num = 0;%>	      				
		   				<s:if test="#request.memGroups!=null&&#request.memGroups.size>0">	      				
		    			<s:iterator value="#request.memGroups" status="status">	
		    			<% if(num % 5 == 0) { %><tr><%} num = num + 1 ;%>			    		
		    			<td align="left" width="10%">
		    			<input <s:if test="isChecked==1">checked='checked'</s:if> type="checkbox" name="groupList.groupId" value="<s:property value="groupId"/>"/>
		    			<s:property value="groupName"/>
		    			</td>
		    			<%if (num % 5 == 0) {%></tr><%}%>
		    			</s:iterator>
		    			</s:if>				    	
		   			</table>
		   			<label id="smsGroupValue" style="display: none;" class="errorMsg"></label>
		   		</td>	   		
	      	</tr>	      		  		 		  			
			<s:if test="method=='checkDetail'">		  	     
		    <tr>
			  	<th align="right" width="20%">本次发送条数：</th>
			    <td width="30%"><s:textfield name="smsDto.num" id="miniPeriod" maxlength="5"   cssClass="formInput"  cssStyle="width:150px;" theme="simple"/>（条）</td>				    
				<th align="right" width="20%">本次发送费用：</th>
		        <td width="30%"><s:textfield name="smsDto.totalPrice" id="singleFee" maxlength="5"  cssClass="formInput" cssStyle="width:150px;"  theme="simple"/>(元)</td>
			</tr>
			<tr>
			  	<th align="right" width="20%">更新时间：</th>
			    <td width="30%"><s:textfield name="smsDto.createDate" cssClass="formInput"  cssStyle="width:150px;" theme="simple"/></td>				    
				<th align="right" width="20%">审核时间：</th>
		        <td width="30%"><s:textfield name="smsDto.auditDate" cssClass="formInput" cssStyle="width:150px;"  theme="simple"/></td>
			</tr>		  
		  	</s:if>
		  	<s:if test="method!='checkDetail'">	
		  	<%--<tr>
		  		<th align="right" width="20%">选择模版：</th>		        
		 		<td colspan="3">								
					<s:select id="stemId" cssStyle="width:156px;" onchange="getStem();"
			        list="#request.smsTems" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
				</td>				
		 	</tr>
		 	--%></s:if>
		 	<s:if test="method=='checkDetail'">	
		  	<tr>
		  		<th align="right" width="20%">申请机构/商户：</th>		        								
				<td width="30%"><s:textfield name="smsDto.merOrgName" cssClass="formInput" cssStyle="width:150px;" theme="simple" />
				</td>
				<th align="right" width="20%"></th>		        
		 		<td>													
				</td>				
		 	</tr>
		 	</s:if>
		  	<tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>短信内容：</th>		        
		 		<td colspan=2>
					<s:textarea name="smsDto.smsContent" id="smsContent" rows="4" cols="40" onkeyup="valContent(this);" onblur="validatesmsContent()" />
					<span class="Color3">(180字符以内！)</span>			
					<label id="smsContentValue" style="display: none;" class="errorMsg"></label>
				</td>				
		 	</tr>		 
		  </table>
		  		  
	 <div class="formTableBottom">
	 	<s:if test="method!='checkDetail'">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1603-02' value='短信参数添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-1603-03' value='短信参数修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		</s:if>
		 	<input type="button" class="formButton" value="返 回" onclick="go('message/shortMesSend!list')"/>
	 </div>
	 </s:form>
	 <p/>
	 <p/>
	 <div >
	   <font color="#ff9900"> 系统提示： <br> <br>
				请先配置短信服务信息，再执行短信群发操作。 <br> </font>
	</div>
</body>
</html>