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
	<title>收单关系管理</title>
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
	<script src="js/pubValidate.js"></script>
   <script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">

        $(document).ready(function (){
            var method = document.getElementById("method");
            if(method.value=='checkUI'){
          	  setInputDisabled();
              }
            });
              
            

	
	      var merNameFlag = false;
	      var orgIdFlag = false;
	      var settWayFlag = false;

	      var ajaxCardBins = function(){
		      var orgId = $("#organId").val();
		        if(orgId!=-1||orgId!='-1'){
                   $("#orgIdErrorMsg").html("");       
			    }
			    var tb = $("#cardBinTb");
			    tb.find('tr:eq(0)').nextAll().remove();  
			    var organId = $.trim($("#organId").val());
				if(organId>0){
					$("#organIdErrorMsg").html("");
					organIdFlag = true;
					var params = {
				        "merchantsDTO.organId" : organId
				    };
				    var actionUrl = "base/merchants!ajaxCardBins";
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
				          	if (data.length>0){
				        		for(var i=0;i<data.length;i++) {
		        					var tr = $("<tr></tr>");
		        					var html = "";
		        					html += "<td><input type='checkbox' name='singleRelationDTO.cardBinStatuss' id='id' value='"+data[i].key+"' /></td>";
		        					html += "<td>"+data[i].value+"<input type='hidden' name='singleRelationDTO.cardBins' value='"+data[i].key+"'/></td>";
		        					html += "<td>"+$("#isSalePointStr").val()+"</td>";
		        					tr.html(html);
				          			tb.append(tr);
		        				}
		        			}
				        }
				    });
				}
				else{
					organIdFlag = false;
					}
				
			}
		$(function(){
			var methodOfSett = $("#methodOfSett").val();
			if(methodOfSett=='-1'||methodOfSett=='2'){//请选择
			   $("#asinTranRateFee").attr("disabled","disabled");
			   $("#rateFee").attr("disabled","disabled");
			   $("#feeTopLimit").attr("disabled","disabled");
			   $("#servPlatformRatio").attr("disabled","disabled");  
		   	   $("#acquirerRate").attr("disabled","disabled");
		  	   $("#organRate").attr("disabled","disabled");
			}
		});

		function getEle(id){
               return document.getElementById(id);
			}
		//修改方法
		var check = function() {
		   var merId = $.trim($("#merId").val());
		   var rateFee = $.trim($("#rateFee").val());  //手续费率
		   var asinTranRateFee = $.trim($("#asinTranRateFee").val());//单笔手续费
		   var organName = $.trim($("#name").val());
		   var methodOfSett = $.trim($("#methodOfSett").val());//结算方式
		   var feeTopLimit = $.trim($("#feeTopLimit").val());//手续费上限 
		   //分成比率
		   var servPlatformRatio = $.trim($("#servPlatformRatio").val());  
		   var acquirerRate = $.trim($("#acquirerRate").val());
		   var organRate = $.trim($("#organRate").val());
           var organId =  $.trim($("#organId").val());
           var type = ["isNull"];
           var errorMsg = [" 请选择商户! "];
           var merName = getEle("merName");
           merNameFlag = showErrorMsg(merName,type,errorMsg,"merNameErrorMsg","merNameErrorMsg");

           var type = ["isSelectNull"];
           var errorMsg = ["请选择发卡机构!"];
           orgIdFlag = showErrorMsg(getEle("organId"),type,errorMsg,"orgIdErrorMsg","orgIdErrorMsg");

           var type = ["isSelectNull"];
           var errorMsg = ["请选择结算方式!"];
           settWayFlag = showErrorMsg(getEle("methodOfSett"),type,errorMsg,"methodOfSettMsg","methodOfSettMsg");
           
           if(!(merNameFlag&&orgIdFlag&&settWayFlag)){
                alert(" * 号内容为必填项，请确认输入正确!");
                return false;
               }
		   

		   if(methodOfSett==0){//如果按照消费金额结算  
			   if(rateFee==""){
                     alert("手续费率不能为空!");
                     $("#rateFee").focus();
                     return false;
				   }
			   if(feeTopLimit==""){
                     alert("手续费上限不能为空!");
                     $("#feeTopLimit").focus();
                     return false;
				   }
			   if(feeTopLimit<0){
                     alert("手续费上线不能小于0!");
				   }
			   }
		   if(rateFee>1||rateFee<0){
                 alert("手续费率不能大于1或小于0");
                 $("#rateFee").focus();
                 return false;
			   }
		   else if(methodOfSett==1){//如果交易消费笔数结算 
                   if(asinTranRateFee==""){
                      alert("单笔手续费不能为空!");
                      $("#asinTranRateFee").focus();
                      return false;
                    }
                   if(asinTranRateFee>999.99){
                      alert("单笔手续费输入过大，请重新输入!");
                      return false;
                       }
                   if(asinTranRateFee<0){
                       alert("单笔手续费不能小于0，请重新输入!");
                       return false;
                       }
			   }
		   else{
                 //... 
			   }
		   if(methodOfSett!=2){//如果结算方式不是"无需结算"，则需要填写分成比率
               if(servPlatformRatio==""&&acquirerRate==""&&organRate==""){
                     alert("需要结算时，分成比率不能为空!");
                     return false;
                   }else if(parseFloat(servPlatformRatio)+parseFloat(acquirerRate)+parseFloat(organRate)!=10){
			   		alert("分成比率总合必须为10!");
                     return false;
				}
			}
			   
		}
		/**当下拉框的值改变时，对费率进行清空**/		
		function clearValue(){
			$("#rateFee").val("0")	;  //手续费率
			$("#asinTranRateFee").val("0");//单笔手续费
			$("#feeTopLimit").val("0");//手续费上限 
		}
		
		
		 //结算方式变更后续操作
		function settWayChange(){
			clearValue();
		   var methodOfSett = $("#methodOfSett").val();
		   if(methodOfSett=='0'){//按消费金额
		      $("#asinTranRateFee").attr("disabled","disabled"); 
		      $("#rateFee").attr("disabled",false);
			  $("#feeTopLimit").attr("disabled",false);
			  $("#servPlatformRatio").attr("disabled",false);  
		   	  $("#acquirerRate").attr("disabled",false);
		  	  $("#organRate").attr("disabled",false);
		  	  $("#methodOfSettMsg").html("");
		   }
		   else if(methodOfSett=='1'){ //按消费笔数
			   $("#asinTranRateFee").attr("disabled",false);
			   $("#rateFee").attr("disabled","disabled");
			   $("#feeTopLimit").attr("disabled","disabled");
			   $("#servPlatformRatio").attr("disabled",false);  
		   	   $("#acquirerRate").attr("disabled",false);
		  	   $("#organRate").attr("disabled",false);
		  	 $("#methodOfSettMsg").html("");
		   }
		   else if(methodOfSett=='-1'||methodOfSett=='2'){//请选择
			   if(methodOfSett=='2'){
				   $("#methodOfSettMsg").html("");
	             }
			   $("#asinTranRateFee").attr("disabled","disabled");
			   $("#rateFee").attr("disabled","disabled");
			   $("#feeTopLimit").attr("disabled","disabled");
			   $("#servPlatformRatio").attr("disabled","disabled");  
		   	   $("#acquirerRate").attr("disabled","disabled");
		  	   $("#organRate").attr("disabled","disabled");
		   }
		}
    </script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 收单关系管理
	</div>
	<jsp:include page="/WEB-INF/page/base/terminals/mercHelps.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/page/base/organs/organHelp.jsp" flush="true"></jsp:include>
	<s:form action="base/sinrelation" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>
	<s:hidden name="singleRelationDTO.isSalePointStr" id="isSalePointStr"/>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>商户：</th>
		        <td width="30%"><s:hidden name="singleRelationDTO.sinRelId" id="sinRelId"/><s:textfield name="singleRelationDTO.merName" id="merName" readonly="true" maxlength="20" cssClass="formInput" theme="simple"/><s:hidden name="singleRelationDTO.merId" id="merId"/><s:hidden name="singleRelationDTO.sinOrganId" id="sdorganId"/><img alt="查找商户" src="images/search.gif" style="cursor:pointer;" onclick="ajaxMerc();"/> <label id="merNameErrorMsg" style="display: none;"></label></td>
		        <th align="right" width="20%"><span class="Color5">* </span>发卡机构：</th>
		        <td width="30%">
		            <%--<s:textfield readonly="true" name="singleRelationDTO.organName" id="name" cssClass="formInput" theme="simple"/>
		            <s:hidden name="singleRelationDTO.organId" id="organId"></s:hidden>
				 <img alt="查找机构" src="images/search.gif" style="cursor:pointer;" onclick="ajaxOrganId();"/>
				 --%><s:if test="method=='addSave'&&#session.user_session.userLevel==0">
			        	<s:select name="singleRelationDTO.organId" id="organId" list="#request.organsValues" headerKey="-1" headerValue="请选择"  listKey="key" listValue="value" onchange="ajaxCardBins();" cssClass="formSelect"/><label id="orgIdErrorMsg" style="display: none;"></label>
		        </s:if>
		        <s:else>
		        		<s:property value="singleRelationDTO.organName"/><input type="hidden" name="singleRelationDTO.organId" id="organId" value="${singleRelationDTO.organId }"/>
		        	</s:else>
		        </td>
		        
			</tr>
		 	<tr>
		 		<th align="right"><span class="Color5">* </span>结算方式：</th>
		 		<td><s:select name="singleRelationDTO.mehodOfSett" id="methodOfSett" headerKey="-1" headerValue="请选择" list="#request.settlemetValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"  onchange="settWayChange();" /><label id="methodOfSettMsg" style="display: none;"></label></td>
		      	<th align="right"><span class="Color5">* </span>手续费率：</th>
		        <td width="30%"><s:textfield name="singleRelationDTO.rateFee" id="rateFee" maxlength="6" cssClass="formInput" theme="simple"/><span> &nbsp;(手续费不能大于1. 如：0.01)</span></td>
		  	</tr>
		  	<tr>
		  		<th align="right"><span class="Color5">* </span>单笔手续费：</th>
		        <td width="30%"><s:textfield name="singleRelationDTO.asinTranRateFee" id="asinTranRateFee" maxlength="6" cssClass="formInput" theme="simple"/></td>
		      	<th align="right"><span class="Color5">* </span>手续费上限：</th>
		        <td width="30%"><s:textfield name="singleRelationDTO.feeTopLimit" id="feeTopLimit" maxlength="9" cssClass="formInput" theme="simple"/></td>
		 	</tr>
		  	<tr>
		      	<th align="right"><span class="Color5">* </span>分成比率：</th>
		        <td colspan="3"><s:textfield name="singleRelationDTO.servPlatformRatio"  cssStyle="width:30px;" id="servPlatformRatio" maxlength="5" theme="simple"/>：<s:textfield  name="singleRelationDTO.acquirerRate" id="acquirerRate"  cssStyle="width:30px;" maxlength="5" theme="simple"/>：<s:textfield  name="singleRelationDTO.organRate" id="organRate" maxlength="5" cssStyle="width:30px;" theme="simple"/>(服务平台：收单机构：发卡机构) </td>
		      	
		   	</tr>
		  	<tr>
		  		<th align="right">计算结算金额方式：</th>
		        <td colspan="3"><s:select name="singleRelationDTO.countSettType" id="countSettType" list="#request.settTypeValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
		 	</tr>
		 	
		 	<tr>
		 	   <th align="right">允许消费的卡BIN：</th>
		        <td colspan="3">
		           <table id="cardBinTb" width="96%" class="listTable" cellpadding="0" cellspacing="0">
	      				<tr>
	      					<th><input type="checkbox" name="checkbox" id="checkbox" onclick="switchAll();" /></th>
	      					<th>卡BIN</th>
	      					<th>消费是否赠送积分</th>
	      				</tr>
	      				<s:if test="#request.cardBinDtos!=null&&#request.cardBinDtos.size>0">
				    		<s:iterator value="#request.cardBinDtos" status="status">
				    		<tr>
				    			<td><input type="checkbox" name="singleRelationDTO.cardBinStatuss" id="id" value="<s:property value="cardBin"/>"  
				    				<s:if test="cardBinStatus==1">checked="checked"</s:if>/></td>
				    			<td><s:property value="cardName"/><input type="hidden" name="singleRelationDTO.cardBins" value="<s:property value="cardBin"/>"/></td>
				    			<td><s:select name="singleRelationDTO.isSalePoints" id="isSalePoints" value="isSalePoint" list="#request.convIsOrNot" listKey="key" listValue="value" cssClass="formSelect"/></td>
				    		</tr>
				    		</s:iterator>
				    	</s:if>
	      			</table>
		        </td>
		 	</tr>
		</table>	
	 <div class="formTableBottom">
	 <s:if test="#session.user_session.userLevel==0">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1203-02' value='收单关系添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-1203-03' value='收单关系修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
	</s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('base/sinrelation!list')"/>
	 </div>
	 </s:form>
	 <p/>
	 <p/>
	 <div align="left" style="padding: 50px; border: solid;border-color: #F2F2F2 ;border-width: thin">
	    <font color="#ff9900" >
	                系统提示：
	                <p />
	                1.收单关系维护是针对[本机构] 与  [非本机构商户] 之间的收单关系维护；
	               
	                <p/>
	                2.[本机构] 与 [本机构商户]之间的收单关系在添加商户时已经形成；
	    </font>
	    
	 </div>
</body>
</html>