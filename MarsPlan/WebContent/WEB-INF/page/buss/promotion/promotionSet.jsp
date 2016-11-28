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
	<title>线上活动管理</title>
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
	<script src="js/jquery/jquery.ui.tabs.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValidate.js"></script>
    <script src="js/pubValiPattern.js"></script>
    <script language="javascript" type="text/javascript" defer="defer" src="<%=basePath%>js/datepicker/WdatePicker.js"></script>
    
    <script src="js/jquery.validate.js"></script>
	<script src="js/jquery.metadata.js"></script>
	<script src="js/additional-methods.min.js"></script>
	<script src="js/common.validate.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
    <script>

      var method = null;
       $(document).ready(function(){
		    $('#beginTime').val($('#beginTimeForment').val());
		    $('#endTime').val($('#endTimeForment').val());
	       method = document.getElementById("method"); 
	       
	        var rateSign =  $('input[name="merPromotionDTO.rateSign"]:checked').val();
		               if(rateSign==0){
		            	 $('#zhekoulv').hide();
		               }else{
		            	  $('#zhekoulv').show();
		               }
		               if(method.value=='addSave'||method.value=='applySave'){
		            	   $('#suggRate').val('1');
		               }
		               if(method.value=='applySave' || method.value=='applyMerCheckUI'){
		               	 $('#merName').show();
		               }
		               
	        if(method.value=='checkUI' || method.value=='applyMerCheckUI' ){
	             setInputDisabled();
	             $("#descr").attr("readonly","true");
                 $("#descr").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
                  $("#descrrecord").attr("readonly","true");
                 $("#descrrecord").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
                 $("#descrmer").attr("readonly","true");
                 $("#descrmer").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
                 
	          }else if(method.value=='applySave'){
	        	  var selList = document.getElementsByTagName("select");
	        		for ( var i = 0; i < selList.length; i++) {
	        			
	        				selList[i].disabled = true
	        		}

	        		var radList = document.getElementsByTagName("input");
	        		for ( var i = 0; i < (radList.length - 1); i++) {
	        			
		        		if(radList[i].id=='proname'||radList[i].id=='beginTime'||radList[i].id=='endTime'){
		        			radList[i].disabled = true;
		        			
		        		}
	        			
	        		}
	        		
	        		 $("#descr").attr("readonly","true");
                 $("#descr").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
                  $("#descrrecord").attr("readonly","true");
                 $("#descrrecord").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
                 $("#descrmer").attr("readonly","true");
                 $("#descrmer").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
	        		
	          }else if(method.value == 'editEndTimeSave'){
	          	var selList = document.getElementsByTagName("select");
	        		for ( var i = 0; i < selList.length; i++) {
	        			
	        				selList[i].disabled = true
	        		}

	        		var radList = document.getElementsByTagName("input");
	        		for ( var i = 0; i < (radList.length - 1); i++) {
	        			
		        		if(radList[i].id=='proname'||radList[i].id=='beginTime'
		        		||radList[i].id=='applyGoodsQuantity' || radList[i].id=='rateSign' ||radList[i].id== 'suggRate'){
		        			radList[i].disabled = true;
		        			
		        		}
	        			
	        		}
	        		
	        		 $("#descr").attr("readonly","true");
                 $("#descr").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
                  $("#descrrecord").attr("readonly","true");
                 $("#descrrecord").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
                 $("#descrmer").attr("readonly","true");
                 $("#descrmer").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
	          }else if(method.value == 'editSave'){
	          	 $("#status").attr("disabled","true");
	          }else if(method.value == 'editRecruitSave'){
	          	$("#status").attr("disabled","true");
	          	$("#proType").attr("disabled","true");
	          	$("#proItem").attr("disabled","true");
	          }
       });
       
        var pronameDifferFlag = false;
         var pronameFlag = false;
       var statusFlag = false;
       var proTypeFlag = false;
       var proItemFlag = false;
       var beginTimeFlag = false;
       var endTimeFlag = false;
       var proImgFlag = true;
       var descFlag = true;
      
       function pronameBlur(obj){
    	   var type = ["isNull"];  
           var errorMsg = ["活动名称不能为空!"];
           pronameFlag = showErrorMsg(obj,type,errorMsg,"pronameErrorMsg","pronameErrorMsg");
            
           var proname = $("#proname").val();
           var url = "buss/promotion!checkSameName";
           var params = {
               "promotionDTO.proname":proname
           }; 
           
           if($.trim($('#oldProname').val()) == $.trim($(obj).val())) {
              pronameDifferFlag = true;
            	return;
            }
            
          if(pronameFlag) {
	            $.ajax({
	                url : url,
	                data : params,
	                async:false,
	                dataType : "json",
	                type : "POST",
	                cache : false,
	                error:function(errText){
	                   alert("ajax加载数据异常!请联系管理员");
	                },
	                success:function(data){
	                     if(data.flag){
	                        pronameDifferFlag = true;
	                        $("#pronameErrorMsg").hide();
	                     }else{
	                        pronameDifferFlag = false;
	                        pubErrorShow($("#pronameErrorMsg"),"该活动名称已存在!");
	                     }
	                }
	                
	            });
            }
            
       }
       
       function proTypeBlur(obj){
    	   var type = ["isSelectNull"];  
           var errorMsg = ["活动性质不能为空!"];
           proTypeFlag = showErrorMsg(obj,type,errorMsg,"proTypeErrorMsg","proTypeErrorMsg");
       }
       
        function proItemBlur(obj){
    	   var type = ["isSelectNull"];  
           var errorMsg = ["活动名目不能为空!"];
           proItemFlag = showErrorMsg(obj,type,errorMsg,"proItemErrorMsg","proItemErrorMsg");
       }
       
        function beginTimeBlur(obj){
        
         //获取开始时间和结束时间的值
	     var starttime = document.forms[0].beginTime.value;
	     var endtime = document.forms[0].endTime.value;
/**
		if (endtime!="" && endtime !="undefined"){
		     if (starttime > endtime){
			alert("开始时间不能晚于结束时间！");
	           document.forms[0].beginTime.value = "";
	           return;
		     }
		} 
		**/
    	   var type = ["isNull"];  
           var errorMsg = ["开始时间不能为空!"];
           beginTimeFlag = showErrorMsg(document.forms[0].beginTime,type,errorMsg,"beginTimeErrorMsg","beginTimeErrorMsg");
           
            
       }
       
        function endTimeBlur(obj){
		          //获取开始时间和结束时间的值
			var starttime=document.forms[0].beginTime.value;
			var endtime=document.forms[0].endTime.value;
		
		        //判断开始时间值是否为空	
		        /**
		        if(starttime=="" || starttime== "undefined"){
			     alert("开始时间不能为空");
		              document.forms[0].endTime.value="";
			    return;
		        }
		
			if (starttime!="" && starttime !="undefined"){
			    if (starttime > endtime){
					alert("结束时间不能早于开始时间！");
			         document.forms[0].endTime.value="";
			          return;
			    }
			}
			**/
        
    	   var type = ["isNull"];  
           var errorMsg = ["结束时间不能为空!"];
           endTimeFlag = showErrorMsg(document.forms[0].endTime,type,errorMsg,"endTimeErrorMsg","endTimeErrorMsg");
       }
       
       function selectEndTime(){
	       var starttime=document.forms[0].beginTime.value;
	      
	       if(starttime=="" || starttime== "undefined"){
	       		WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'});
	       	
	       }else{
	       		 WdatePicker({dateFmt:'yyyy-MM-dd',minDate:starttime});
	       	 
	       }
      }
      
       function selectBeginTime(){
	       var endtime=document.forms[0].endTime.value;
	      
	       if(endtime=="" || endtime== "undefined"){
	       		WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'});
	       	
	       }else{
	       		 WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d',maxDate:endtime});
	       	 
	       }
      }
       

       function statusBlur(obj){
    	   var type = ["isSelectNull"];  
           var errorMsg = ["活动状态不能为空!"];
           statusFlag = showErrorMsg(obj,type,errorMsg,"statusErrorMsg","statusErrorMsg");
          

         }

         function suggRateBlur(obj){
            var suggRate = $("#suggRate").val();
             if(isNaN(suggRate)){
             pubErrorShow($("#suggRateErrorMsg"),"请输入数字!");
            	$("#suggRate").val("1");
             }else{
	            if(suggRate<0||suggRate>1){
	            	pubErrorShow($("#suggRateErrorMsg"),"折扣率不能小于0或者大于1!");
	            	$("#suggRate").val("1");
	            }else{
	            	 $("#suggRateErrorMsg").hide();
	            }
         	
         	}
         } 
         
       

       function check(){
       
    	   pronameBlur(getEle("proname"));
    	   proTypeBlur(getEle("proType"));
    	   proItemBlur(getEle("proItem"));
    	   beginTimeBlur(getEle("beginTime"));
    	   endTimeBlur(getEle("endTime"));
    	   statusBlur(getEle("status"));
    	   proImgChange();
    	   
 
           if(!(pronameFlag&&statusFlag&&proTypeFlag&&proItemFlag&&beginTimeFlag&&endTimeFlag&&pronameDifferFlag&&proImgFlag)){

               alert("信息填写有误，请按提示信息重新填写!");
               
               return false;
              }
        }

       function getEle(ele){

           var element = document.getElementById(ele);

           return element;
        }
        
        
        function proImgChange() {
			if($.trim($('#proImg').val()) == "") {
				return;
			}
			var strRegex = "(.jpg|.png|.gif|.ps|.jpeg)$"; //用于验证图片扩展名的正则表达式
	        var re=new RegExp(strRegex);
	        if (re.test($('#proImg').val().toLowerCase())){
	        	$('#proImgErrorMsg').html('');
	        	$('#proImgErrorMsg').attr('style','display: none;');
	        	$('#proImgErrorMsg').removeClass('errorMsg');
	        	$('#see').hide();
	        	$('#file_msg').html("");
	        	$('#clearPicButton').hide();
	        	proImgFlag = true;
	            return true;
	        } else {
	        	$('#proImgErrorMsg').html("文件类型不支持！");
	        	$('#proImgErrorMsg').addClass('errorMsg');
	        	$('#proImgErrorMsg').removeAttr("style");
	        	proImgFlag = false;
	        	return false;
	        }
		}
		
		function clearUploadFile() {
			if(confirm("确认要删除？")) {
				// 清空文件
				$('#proImg').attr("value","");
				// 修改显示文案
				$('#file_msg').html("设置删除");
				// 设置删除标记
				$('#cleanParam').val("1");
				$('#see').hide();
				$('#clearPicButton').hide();
				$('#proImgErrorMsg').html('');
	        	$('#proImgErrorMsg').attr('style','display: none;');
	        	$('#proImgErrorMsg').removeClass('errorMsg');
	        	
	        	proImgFlag = true;
	        }
		}
		
		
		
		function isHiddenRate(obj){
			var isHidden = $(obj).val();
			if(isHidden==0){
				$('#zhekoulv').hide();
			}else if(isHidden == 1){
				$('#zhekoulv').show();
				 $('#suggRate').val('1');
			}
		}
		
		

    </script>
</head>
<body>
    
	<div class="Position">
		当前位置是：营销中心 &gt;&gt; 活动管理 &gt;&gt; 线上活动管理
	</div>
	<s:form action="buss/promotion" method="post" theme="simple" enctype="multipart/form-data" onsubmit="document.getElementById('submitInput').disabled = true;return true;" >
		<s:hidden name="method" id="method"></s:hidden>
		<s:hidden name="promotionDTO.proid" id="proid"></s:hidden>
		<s:hidden name="merPromotionDTO.mpid" id="mpid"></s:hidden>
		<s:hidden name="promotionDTO.createTime" id="createTime"></s:hidden>
		<s:hidden name="merPromotionDTO.createTime" id="createTime"></s:hidden>
		<s:hidden name="promotionDTO.operatorId" id="operatorId"></s:hidden>
		<s:hidden name="merPromotionDTO.operatorId" id="merOperatorId"></s:hidden>
		<s:hidden name="promotionDTO.proHost" id="prohost"></s:hidden>
		<s:if test="method!='addSave'">
			<s:hidden name="promotionDTO.status" id="Status"></s:hidden>
			<s:hidden name="promotionDTO.proType" id="protype"></s:hidden>
			<s:hidden name="promotionDTO.proItem" id="proitem"></s:hidden>
		</s:if>
		
		<input type="hidden" id = "beginTimeForment" value="<s:date name='promotionDTO.beginTime' format='yyyy-MM-dd' />">
		
		<input type="hidden" id = "endTimeForment" value="<s:date name='promotionDTO.endTime' format='yyyy-MM-dd' />">
		
		<s:hidden name="promotionDTO.hostSign" id="hostSign"></s:hidden>
		<input type="hidden" id="oldProname" value="<s:property value="promotionDTO.proname"/>" />
		<input type="hidden" id="cleanParam" name="promotionDTO.deleteProImg"/>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		   <tr>
		  		<th align="right" width="20%"><span class="Color5">* </span><strong>活动名称：</strong></th>
		        <td width="30%"><s:textfield name="promotionDTO.proname" id="proname" maxlength="15" onblur="pronameBlur(this);" cssClass="formInput"/> <label id="pronameErrorMsg" style="display: none;"></label></td>
		        <th align="right" width="20%"><strong>举办单位：</strong></th>
		        <td width="30%"><s:textfield name="promotionDTO.proHost"   id="proHost" maxlength="30" disabled="true" cssClass="formInput"/> </td>
			</tr>
		    <tr>
		      	<th align="right" width="20%"><span class="Color5">* </span><strong>活动状态：</strong></th>
		        <td width="30%"><s:select id="status" name="promotionDTO.status" list="#request.status" onblur="statusBlur(this);" headerKey="-1" headerValue="请选择"  listKey="key" listValue="value"  cssClass="formSelect"/><label id="statusErrorMsg" style="display: none;"></label></td>
		        <th align="right" width="20%"><span class="Color5">* </span><strong>活动性质：</strong></th>
		        <td width="30%"><s:select id="proType" name="promotionDTO.proType" list="#request.proType" onblur="proTypeBlur(this);" headerKey="-1" headerValue="请选择"  listKey="key" listValue="value"  cssClass="formSelect"/><label id="proTypeErrorMsg" style="display: none;"></label></td>
		   	</tr>
		   	
		   	<tr>
		      	<th align="right" width="20%"><span class="Color5">* </span><strong>开始时间：</strong></th>
		        <td width="30%"><s:textfield name="promotionDTO.beginTime" id="beginTime"  onchange="beginTimeBlur(this);" maxlength="20" cssClass="formInput" theme="simple" onfocus="selectBeginTime();"/><label id="beginTimeErrorMsg" style="display: none;"></label></td>
		        <th align="right" width="20%"><span class="Color5">* </span><strong>结束时间：</strong></th>
		        <td width="30%"><s:textfield name="promotionDTO.endTime" id="endTime" maxlength="20" onchange="endTimeBlur(this);" cssClass="formInput" theme="simple" onfocus="selectEndTime();"/><label id="endTimeErrorMsg" style="display: none;"></label></td>
		   	</tr>
		   	
		   	<tr>
		      	<th align="right" width="20%"><span class="Color5">* </span><strong>活动名目：</strong></th>
		        <td width="30%"><s:select id="proItem" name="promotionDTO.proItem" list="#request.proItem" onblur="proItemBlur(this);" headerKey="-1" headerValue="请选择"  listKey="key" listValue="value"  cssClass="formSelect"/><label id="proItemErrorMsg" style="display: none;"></label></td>
		        <th align="right" width="20%"><strong>活动主题图片：</strong></th>
		       <td>
		        	<s:if test="method!='checkUI'&&method!='applySave'&&method != 'applyMerCheckUI' &&method != 'editEndTimeSave'">
		        		<s:file name="promotionDTO.proImg" size="40" id="proImg" onchange="proImgChange()" /><label id="proImgErrorMsg" style="display: none;"></label>
		        	</s:if>
		        	<s:if test="method=='editSave' && promotionDTO.proImgFileName!=null && promotionDTO.proImgFileName!=''">
		        		<span id="see"><img src="<s:url value='%{promotionDTO.proImgFileName}'/>" ></img></span>&nbsp;<input type="button" value="删除图片" id="clearPicButton" onclick="clearUploadFile()"/>
		        	</s:if><br/>
		        	<s:if test="method=='editSave' && promotionDTO.proImgFileName!=null && promotionDTO.proImgFileName!=''">
		        		<font size="2" color="gray" id="file_msg">您已经上传过图片，再次上传时将覆盖原图片！</font>
		        	</s:if>
		        	<s:if test="(method=='checkUI' || method == 'applySave' || method == 'applyMerCheckUI' || method == 'editEndTimeSave') && promotionDTO.proImgFileName!=null && promotionDTO.proImgFileName!=''">
		        		<img src="<s:url value='%{promotionDTO.proImgFileName}'/>"  height="300" width="400"/>
		        	</s:if>
		        	
		        </td>
		   	</tr>
		   <s:if test="promotionDTO.hostSign == 0">
		   	<tr>
		      	<th align="right"><strong>活动规则说明：</strong></th>
		        <td colspan="3"><s:textarea name="promotionDTO.descr" id="descr" maxLength="255"  cssClass="formTextarea" onpropertychange="if(value.length>255) value=value.substr(0,255)"></s:textarea><span class="Color3">(最多可输入255个字)</span> </td>
		      
		 	</tr>
		 	</s:if>
		   	</table>
		   	<s:if test="method=='applySave' || method=='applyMerCheckUI'">
		   		<div id="applyPromotionTitle" class="List_tit">报名申请信息</div>
		   </s:if>
		   <s:if test="promotionDTO.hostSign == 1 || method=='applySave' || method=='applyMerCheckUI'">
		   <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		   <tr id="merName" style="display:none;">
		   <td  align="left" colspan="4">
		  <span > <strong>商户名称：</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#session.user_session.merName" /></span>
		   </td>
		   
		   </tr>
		   	<tr>
		   		<th align="right" width="20%"><span class="Color5">* </span><strong>是否统一折扣：</strong></th>
		        <td align="left" >
		           <s:radio list="#request.rateSign" listKey="key" id="rateSign" listValue="value"  name="merPromotionDTO.rateSign" onclick="isHiddenRate(this);"/>
		            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		           
		            <span align="right" id="zhekoulv" style="display:none;"><strong>折扣率：</strong><s:textfield onkeyup="value=value.replace(/[^\-?\d.]/g,'')" onblur="suggRateBlur(this);" name="merPromotionDTO.suggRate"   id="suggRate" maxlength="8"></s:textfield>折<br><label id="suggRateErrorMsg" style="display: none;"></label></span>
		                
		         </td>
		         
		         <th align="right" width="20%"><strong>参与活动商品数量：</strong></th>
		   		 <td width="30%"><s:textfield name="merPromotionDTO.applyGoodsQuantity" id="applyGoodsQuantity" maxlength="9" cssClass="formInput" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') "/> </td>
		        
		   	</tr>
		   	<s:if test="method!='applySave' && method!='applyMerCheckUI'">
		   	<tr>
		      	<th align="right" width="20%"><strong>活动规则说明：</strong></th>
		        <td colspan="3"><s:textarea name="promotionDTO.descr" id="descrmer" maxLength="255"  cssClass="formTextarea" onpropertychange="if(value.length>255) value=value.substr(0,255)"></s:textarea><span class="Color3">(最多可输入255个字)</span></td>
		      
		 	</tr>
		   	</s:if>
		   	</s:if>
	
		   
		
		   	
	 	</table>
	 	
	 	
	 	<s:if test="method=='applyMerCheckUI'">
		   	<div id="applyPromotionTitle" class="List_tit">平台审核信息</div>
		   <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			   <tr>
			   
			   <th align="right" width="20%"><strong>审核状态：</strong></th>
			    <td colspan="3"><s:textfield name="merApplyRecordDTO.status" id="applystatus"  maxlength="20"/> </td>
			   </tr>
			   
			   <tr>
		      	<th align="right" width="20%"><strong>审核意见：</strong></th>
		        <td colspan="3"><s:textarea name="merApplyRecordDTO.descr" id="descrrecord"  maxLength="255" cssClass="formTextarea" /> <span class="Color3">(最多可输入255个字)</span></td>
		      
		 	</tr>
		   </table>
		</s:if>
	 	
	 	<div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	
		</s:if>
		<s:elseif test="method=='editSave' || method=='applySave' || method=='editEndTimeSave' || method=='editRecruitSave' " >
		    
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	
		</s:elseif>
	
		<input type="button" class="formButton" value="返回" onclick="go('buss/promotion!list')"/>
		</div>
	 	</s:form>	 
	 </body> 
	 
</html>