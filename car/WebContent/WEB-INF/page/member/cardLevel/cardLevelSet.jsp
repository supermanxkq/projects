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
    
    <title>卡等级管理</title>
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
		//更改级别
		function changeFlag(){
			//设定被选中的按钮的Key值
			var flag = $('[name="cardLevelDTO.levelSign"]:checked').val();
			if(flag=="1"){
				$("#org1").show();
				$("#mer1").hide();
				$("#org2").show();
				$("#mer2").hide();
				$("#merName").val("");
				$("#merId").val("");
			}
			if(flag=="2"){
				$("#org2").hide();
				$("#org1").hide();
				$("#mer1").show();
				$("#mer2").show();
				$("#name").html("");
				$("#organId").html("");
			}
		};
	var organsFlag = false;
	var merchantsFlag = false;
	var cardLevelNameFlag = false;
	var newPointFlag = false;
	function setOrgans(obj){
		var type = ["isNull","isCompName"];
        var errorMsg = ["发卡机构不能为空!","发卡机构格式错误!"];
        organsFlag = showErrorMsg(obj,type,errorMsg,"organsMsg","organsError");
		}
	function setMerchants(obj){
		var type = ["isNull","isCompName"];
        var errorMsg = ["商户不能为空!","商户格式错误!"];
        merchantsFlag = showErrorMsg(obj,type,errorMsg,"merchantsMsg","merchantsError");
	}
	function setCardLevelName(obj){
		var type = ["isNull","isCompName"];
        var errorMsg = ["卡等级名称不能为空!","卡等级名称格式错误!"];
        cardLevelNameFlag = showErrorMsg(obj,type,errorMsg,"cardLevelNameMsg","cardLevelNameError");
	}
	function setNewPoint(obj){
		var type = ["isNull","fullNumber"];
        var errorMsg = ["开户积分不能为空!","开户积分格式错误!"];
        newPointFlag = showErrorMsg(obj,type,errorMsg,"newPointMsg","newPointError");
	}

	//增加折扣积分
		function addMerAccRuleHtml(){
		    var actionUrl = "cardLevel/cardLevel!addMerAccRuleHtml";
		    $.ajax( {
		        url : actionUrl,
		        //data : params,
		        dataType : "json",
		        cache : false,
		        type : "POST",
		        error : function(textStatus, errorThrown) {
		          	alert("系统ajax交互错误!");
		        },
		        success : function(data, textStatus) {
		            if(data.ajaxResult == "ajaxsuccess") {          
		                $("#table1").append(data.msgResult);
		            }
		        }
		    });
		}
		
	function checkAmt(){
		var cpayDiscount = document.getElementById("cpayDiscount").value;
		if(parseFloat(cpayDiscount)>parseFloat(1)){
			alert("消费折扣不能大于1！");
			return false;
			}
		}
	function checkPoint(){
		var spayDiscount = document.getElementById("spayDiscount").value;
		if(parseFloat(spayDiscount)>parseFloat(1)){
			alert("消费折扣不能大于1！");
			return false;
			}
		}
		function checkLevelName(obj){
			setCardLevelName(obj);
			var method = $("#method").val();
			var levelName = $("#levelName").val();
			var levelId =  $("#levelId").val();
			if(levelName.length!=0){
				if(method == "addSave"){
					var params = {
							"method" : method,
							"cardLevelDTO.levelName" : levelName
					    }
					}else{
						var params = {
								"method" : method,
								"cardLevelDTO.levelName" : levelName,
								"cardLevelDTO.levelId": levelId
						    }
			 var actionUrl = "cardLevel/cardLevel!checkLevelName";
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
			        	if(data.flag==false){
				        	   $("#cardLevelNameError").html("该卡等级名称已经被使用!");
				        	   $("#cardLevelNameError").addClass("errorMsg");
				        	   $("#cardLevelNameError").show();
				        	   return false;
					    }
			        }
			    });
				}
			}
		}
		function levelCheck(){
			var descr = $("#descr").val();
			var orgname = $("#name").val();
			var merName = $("#merName").val();
			var levelName = $("#levelName").val();
			var levelNameA = document.getElementById("levelName");
			var orgnameA = document.getElementById("orgname");
			var method = $("#method").val();
			var newPoint = $("#newPoint").val();
			var newPointA = document.getElementById("newPoint");
			var sign = $('[name="cardLevelDTO.levelSign"]:checked').val();
			checkLevelName(levelNameA);
			setNewPoint(newPointA);
			setOrgans(orgnameA);
			if(descr.length>255){
				alert("描述输入过长!");
				return false;
				}
			if(method=="addSave"){
				if(sign=="1"){//选择发卡机构时
					merchantsFlag = true;
					if(orgname != ""){
						organsFlag = true;
						}
					}else if(sign=="2"){//选择商户时
						organsFlag = true;
						}
					if(!organsFlag||!merchantsFlag||!cardLevelNameFlag||!newPointFlag){
						alert("带*号的选项填写错误！");
						return false;
				}
				}else{
					if(method=="editSave"){
						if(!cardLevelNameFlag||!newPointFlag){
							alert("带*号的选项填写错误！");
							return false;
						}else{
							return true;
							}
						}
					return true;
				}
		};
			$(document).ready(function (){
			       var method = document.getElementById("method"); 
		            if(method.value=="checkUI"){
		            	setInputDisabled();
		            	$("#status").attr("disabled","disabled");
		            	$("#descr").attr("disabled","disabled");
		            	$("#crpointRate").attr("disabled","disabled");
		            	$("#cppointRate").attr("disabled","disabled");
		            	$("#cpayDiscount").attr("disabled","disabled");
		            	$("#spayDiscount").attr("disabled","disabled");
		            	$("#sppointRate").attr("disabled","disabled");
		            	$("#srpointRate").attr("disabled","disabled");
		            	$("#submitInput").attr("readonly","readonly");
		            	}
			        });
		
</script>
  </head>
  <body>
  <div class="Position">
		当前位置是：HOME &gt;&gt;卡等级基本信息 &gt;&gt; 卡等级管理
	</div>
<!--机构、商户帮助页面的包含	-->
	<jsp:include page="/WEB-INF/page/base/terminals/mercHelps.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/page/base/organs/organHelp.jsp"></jsp:include>
	<s:form action="cardLevel/cardLevel" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;">
	<s:hidden name="method" id="method"/>
	<div class="List_tit">卡等级设置</div>
	<div>
	<s:if test="#session.user_session.userLevel!=2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable" >
			<s:if test="method=='addSave'">
				<tr>
					<s:hidden id="updateTime" name="cardLevelDTO.updateTime"></s:hidden>
					<s:hidden name="cardLevelDTO.levelId"></s:hidden>
			  		<th align="right" width="20%">
			  			<span class="Color5">* </span>卡等级标志：
			  		</th>
			        <td width="30%">
			        	<s:radio  name="cardLevelDTO.levelSign"  id="levelSign" list="#session.cardSignValues" listKey="key" listValue="value" value="1" theme="simple"  onclick="changeFlag();"/>
			        </td>
			     <th align="right" width="20%" >
			     <div id="org1">
			      	<span class="Color5">* </span>发卡机构：
			     </div >
			      <div  id="mer1" style="display:none">
			      	<span class="Color5">* </span>商        户：
			     </div >
			    </th>
			        <td width="30%"> 
				        <div  id="org2">
				        	<s:textfield name="cardLevelDTO.name" id="name"  maxlength="20" cssClass="formInput"  theme="simple" onkeyup = "allowEnCnNu(this);" onblur="setOrgans(this);"/>
				        	 <s:hidden name="cardLevelDTO.organId" id="organId"></s:hidden>
				        	 <img alt="查找机构" src="images/search.gif" style="cursor:pointer;" onclick="ajaxOrganId();"/>
				        	 <label id="organsMsg" style="display: none;"></label> 
		 					 <label id="organsError" style="display: none;"></label>
				        </div >
				        <div  id="mer2"  style="display:none">
				        	<s:textfield name="cardLevelDTO.merName" id="merName"  maxlength="20" cssClass="formInput"  theme="simple" onkeyup = "allowEnCnNu(this);" onblur="setMerchants(this);"/>
				        	 <s:hidden name="cardLevelDTO.merId" id="merId"></s:hidden>
				        	 <img alt="查找商户" src="images/search.gif" style="cursor:pointer;" onclick="ajaxMerc();"/>
				        	 <label id="merchantsMsg" style="display: none;"></label> 
		 					 <label id="merchantsError" style="display: none;"></label>
				        </div>
			        </td>
				</tr>
				</s:if>
				<s:else>
				<s:hidden name="cardLevelDTO.levelSign" />
				<s:hidden name="cardLevelDTO.levelId" id="levelId"></s:hidden>
					<s:if test="cardLevelDTO.levelSign==1">
						<tr>
							<th align="right" width="20%" >
								<span class="Color5">* </span>发卡机构：
							</th>
							<td width="30%">
								<s:property value="cardLevelDTO.name"/>
				        	 	<s:hidden name="cardLevelDTO.organId"></s:hidden>
							</td>
						</tr>
					</s:if>
				
					<s:if test="cardLevelDTO.levelSign==2">
						<tr>
							<th align="right" width="20%" >
								<span class="Color5">* </span>商         户：	
							</th>
							<td width="30%">
								<s:property value="cardLevelDTO.merName"/>
				        	<s:hidden name="cardLevelDTO.merId" ></s:hidden>	
							</td>
						</tr>
					</s:if>
				</s:else>
			 	<tr>
			 		<th align="right"><span class="Color5">* </span>级别名称：</th>
			 		<td>
			 			<s:textfield name="cardLevelDTO.levelName"  id="levelName"  maxlength="10" cssClass="formInput"  theme="simple" onkeyup = "allowEnCnNu(this);" onblur="checkLevelName(this);"/>
			 			<label id="cardLevelNameMsg" style="display: none;"></label> 
		 				<label id="cardLevelNameError" style="display: none;"></label>
			 		</td>
			      	<th align="right"><span class="Color5">* </span>开户积分：</th>
			        <td>
				        <s:textfield name="cardLevelDTO.newPoint" id="newPoint" maxlength="8" cssClass="formInput"  theme="simple" onkeyup= "replaceToNum(this);" onblur="setNewPoint(this);"/>
				        <label id="newPointMsg" style="display: none;"></label> 
			 			<label id="newPointError" style="display: none;"></label>
			        </td>
			  	</tr>
		 
		  	<tr>
		      	<th align="right">状 态：</th>
		        <td><s:select  name="cardLevelDTO.status" id="status" list="#session.statusValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
		   	</tr>
		  	<tr>
		  	<th align="right">描述：</th>
		         <td><s:textarea name="cardLevelDTO.descr" id="descr" cssClass="formTextarea" theme="simple"/></td>
		  	</tr>
		</table>
	</s:if>
	</div>
	<div>
		<div class="List_tit">账户折扣积分设置</div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable" align="center">
			<s:hidden name="cardLevelDTO.accDisPntId" id="accDisPntId"></s:hidden>
			<tr>
				<th width="8%">账户类型</th>
				<th width="10%">消费折扣</th>
				<th width="10%">消费赠送积分比率</th>
				<th width="10%">充值赠送积分比率</th>
			</tr>
			<tr>
	    		<td width="8%"  align="center">现金<input name="cardLevelDTO.caccTypeId"  value="1" type="hidden"></td>
	    		<td width="10%"  align="center"><input name="cardLevelDTO.cpayDiscount" id="cpayDiscount" maxlength="13" value="${cardLevelDTO.cpayDiscount}" class="xhx" type="text" id="cpayDiscount" onkeypress="onlyNumFloadt(this);" onblur = "checkAmt();" />折<span class="Color3">(0.8表示八折，1表示不打折)</span></td>
	    	    <td width="10%"  align="center">1：<input name="cardLevelDTO.cppointRate" id="cppointRate" maxlength="13" value="${cardLevelDTO.cppointRate}" class="xhx" type="text" onkeypress="onlyNumFloadt(this);"  /><span class="Color3"/>(0不赠送)</span></td>
	    		<td width="10%"  align="center">1：<input name="cardLevelDTO.crpointRate" id="crpointRate" maxlength="13"  value="${cardLevelDTO.crpointRate}" class="xhx" type="text" onkeypress="onlyNumFloadt(this);"  /><span class="Color3"/>(0不赠送)</span></td>
			</tr>
			<tr>
	    		<td width="8%" align="center">积分<input name="cardLevelDTO.saccTypeId" value="2" type="hidden"/></td>
	    		<td width="10%" align="center"><input name="cardLevelDTO.spayDiscount" id="spayDiscount" maxlength="13" value="${cardLevelDTO.spayDiscount}" class="xhx"  type="text" id="spayDiscount" onkeypress="return onlyNumFloadt(this);" onblur="checkPoint();"/>折<span class="Color3">(0.8表示八折，1表示不打折)</span></td>
	    		<td width="10%" align="center">1：<input name="cardLevelDTO.sppointRate" id="sppointRate" maxlength="13" value="${cardLevelDTO.sppointRate}" class="xhx"  type="text" onkeypress="onlyNumFloadt(this);"/><span class="Color3">(0不赠送)</span></td>
	    		<td width="10%" align="center">1：<input name="cardLevelDTO.srpointRate" id="srpointRate" maxlength="13" value="${cardLevelDTO.srpointRate}" class="xhx" type="text" onkeypress="onlyNumFloadt(this);"/><span class="Color3">(0不赠送)</span></td>
			</tr>
		</table>
	</div>
	 <div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1302-02' value='卡等级添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return levelCheck();"/>
		 	</my:permission>
		</s:if>
		<s:if test="method=='editSave'">
			<my:permission key='sy-1302-03' value='卡等级修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return levelCheck();"/>
		 	</my:permission>
		</s:if>
		<s:if test="method=='checkUI'">
			<my:permission key='sy-1302-02' value='卡等级添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return levelCheck();"/>
		 	</my:permission>
		</s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('cardLevel/cardLevel!list')"/>
	 </div>
	</s:form>
</body>
</html>