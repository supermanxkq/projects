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
	<title>物流公司管理</title>
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
	<script type="text/javascript"  src="js/areaSelect.js"></script>
    <script>

       var logistNameFlag = false;
       var urlFlag = false;
       var diffNameFlag = true;
       var logistNameBak;
       var method;
       $(document).ready(function(){
	       method = document.getElementById("method"); 
	        if(method.value=='checkUI'){
	             setInputDisabled();
	          }
	          logistNameBak=$("#logistName").val();
       });

       function logistNameBlur(obj){
           diffNameFlag = true;
    	   var type = ["isNull"];  
           var errorMsg = ["物流公司名称不能为空!"];
           logistNameFlag = showErrorMsg(obj,type,errorMsg,"logistNameErrorMsg","logistNameErrorMsg");
           
           var logistName = $("#logistName").val();
           if(logistName.length>30){
              pubErrorShow($("#logistNameErrorMsg"),"长度不能超过30!");
              logistNameFlag = false;
           }
           
           var url = "base/logistics!checkSameName";
           var params = {
               "logisticsDTO.logistName":logistName
           };
          
           
           if(((method.value=="editSave"&&logistName!=logistNameBak)||method.value=="addSave")&&logistName!=""){
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
                            diffNameFlag = true;
                            $("#logistErrorMsg").hide();
                         }else{
                            
                            diffNameFlag = false;
                            pubErrorShow($("#logistNameErrorMsg"),"该物流公司名称已存在!");
                         }
                         
                    }
                    
                });
            }

         }

      

       function urlBlur(obj){
              urlFlag = true;
             var type = ["isNull"];
             var urlErrorMsg = ["Url地址不能为空!"];
             urlFlag = showErrorMsg(obj,type,urlErrorMsg,"urlErrorMsg","urlErrorMsg");
             if(obj.value.length>100){
	             pubErrorShow($("#urlErrorMsg"),"长度不能超过100!");
	             urlFlag = false;
             }
             
             if($('#url').val()) {
				var type = ["isUrl"];
	            var urlErrorMsg=["物流公司网址不符合规范!"];
	            urlFlag = showErrorMsg(obj,type,urlErrorMsg,"urlErrorMsg","urlErrorMsg");
	        }
           }

      
       

       function check(){
    	   logistNameBlur(getEle("logistName"));
    	   
    	   urlBlur(getEle("url"));
			
    	   if(logistNameFlag&&diffNameFlag){
              logistNameFlag = true;
          }
    	   else{
             logistNameFlag = false;
          }
    	   
    	   //alert(psNameFlag);
    	   //alert(payOrgansFlag);
    	   //alert(psUrlFlag);
    	   
           if(!(logistNameFlag&&urlFlag)){

               alert("信息填写有误，请按提示信息重新填写!");
               
               return false;
              }
        }

       function getEle(ele){

           var element = document.getElementById(ele);

           return element;
        }

    </script>
</head>
<body>
    
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 物流公司管理
	</div>
	<s:form action="base/logistics" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
		<s:hidden name="method" id="method"></s:hidden>
		<s:hidden name="logisticsDTO.logistId" id="logistId"></s:hidden>
		<s:hidden name="logisticsDTO.createTime" id="createTime"></s:hidden>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		   <tr>
		  		<th align="right" width="20%"><span class="Color5">* </span><strong>物流公司：</th>
		        <td width="30%"><s:textfield name="logisticsDTO.logistName" id="logistName" maxlength="30" onblur="logistNameBlur(this);" cssClass="formInput"/> <label id="logistNameErrorMsg" style="display: none;"></label></td>
		        <th align="right" width="20%"><span class="Color5">* </span>官网地址：</th>
		        <td width="30%"><s:textfield name="logisticsDTO.url" onblur="urlBlur(this);" id="url" maxlength="200" cssClass="formInput"/> <label id="urlErrorMsg" style="display: none;"></label></td>
			</tr>
		    <tr>
		      	<th align="right"><strong>使用状态：</strong></th>
		        <td colspan="3"><s:select id="status" name="logisticsDTO.status" list="#request.statusValues" headerKey="0"  listKey="key" listValue="value"  cssClass="formSelect"/><label id="statusErrorMsg" style="display: none;"></label></td>
		   	</tr>
		   	
	 	</table>
	 	<div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1901-02' value='物流公司信息添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:elseif test="method=='editSave'">
		    <my:permission key='sy-1901-03' value='物流公司信息修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:elseif>
		<s:else>
		</s:else>
		<input type="button" class="formButton" value="返 回" onclick="go('base/logistics!list')"/>
		</div>
	 	</s:form>	 
	 </body> 
</html>