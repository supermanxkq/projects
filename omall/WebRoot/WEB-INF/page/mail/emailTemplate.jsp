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
	<title>邮件常用模板管理</title>
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
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
	/**获得邮件模板信息**/
	  function getEmail()
	   {
	       var emailId = $("#eId").val();
	      if(emailId==-1)
	      {
	          $("#emailTile").attr("value","");
			  $("#etContent").attr("value","");
	         return false;
	      }else{
	      
	      	var params = {
							"emailTemplateDTO.emailId":emailId
					    }
				
			/**请求的action*/
		
			 var actionUrl = "mail/EmailTemplate!getEmail";
			 $.ajax({  
				 	async:false,
			        url:actionUrl,   
			        data:params,   
			        dataType:"json",
			        cache:false, 
			        async : false,
			        type:"POST",
			        error:function(textStatus, errorThrown) {   
			    		alert("系统ajax交互错误!");  
			        },
			        success:function(data, textStatus) {
			          var title=data[0];
			          var cont=data[1];
			          var type=data[2];
			     
			          $("#emailTile").attr("value",title);
			          $("#etContent").attr("value",cont);
			         
			         
			        }
			    });
				
	      }
			
		
	   }
	   /**重置每条模板信息**/
	   function resetEvery()
	   {
	    
	      var emailId = $("#eId").val();
	       if(emailId==-1)
	      {
	          $("#emailTile").attr("value","");
			  $("#etContent").attr("value","");
			
	         return false;
	      }else{
	      
	      	var params = {
							"emailTemplateDTO.emailId":emailId
					    }
				
			/**请求的action*/
		
			 var actionUrl = "mail/EmailTemplate!resetEvery";
			 $.ajax({  
				 	async:false,
			        url:actionUrl,   
			        data:params,   
			        dataType:"json",
			        cache:false, 
			        async : false,
			        type:"POST",
			        error:function(textStatus, errorThrown) {   
			    		alert("系统ajax交互错误!");  
			        },
			        success:function(data, textStatus) {
			          var title=data.emailTile;
			          var cont=data.etContent;
			          var type=data.emailType;
			     
			          $("#emailTile").attr("value",title);
			          $("#etContent").attr("value",cont);
			         
			         alert("重置成功");
			        }
			    });
				
	      }
	   }
	   function check()
	   {
	        var emailId = $("#eId").val();
	       
              if(emailId==-1)
              {
                   alert("页面信息错误");
                   return false;
              }
	   }
	   
	   
	   function validatelength(obj){
				obj.value = obj.value.replace(/\s/g, '');
				 obj.value = obj.value.substring(0, 999);
				}
	</script> 
</head>
<body onload="">
	<div class="Position"> 
		当前位置是：HOME &gt;&gt; 业务参数 &gt;&gt;邮件常用模板管理 
	</div>
	<s:form action="mail/EmailTemplate" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;"
	      enctype="multipart/form-data" theme="simple">
	      <s:hidden name="method" id="method"/>
	<table width="96%"  cellpadding="0" cellspacing="0" class="formTable" >
	    <tr>
	       <th align="right"width="40%"><span class="Color5">*</span>请选择邮件模板 :&nbsp;</th>
	       <td><s:select id="eId" name="emailTemplateDTO.emailId" list="#request.etName" headerKey="-1" headerValue="请选择" listKey="key" listValue="value" onchange="getEmail();"></s:select></td>
	    </tr>
		<tr>
		  <th align="right"><span class="Color5">*</span>邮件主题 :&nbsp;</th>
		  <td><s:textfield id="emailTile" name="emailTemplateDTO.emailTile" maxlength="200"  size="32"></s:textfield></td>
		</tr>
		<tr>
		  <th align="right"><span class="Color5" >*</span>邮件类型 :&nbsp;</th>
		  <td>
		   <input id="type1" type="radio" name="emailTemplateDTO.emailType"  value="1" checked disabled>纯文本邮件 
		  
		  </td>
		</tr>
		<tr>
			<th align="right"><span class="Color5">*</span>邮件内容:&nbsp;</th>
			<td>
			  <s:textarea name="emailTemplateDTO.etContent" onkeyup="validatelength(this);" id="etContent" cols="50" rows="9"></s:textarea>
			</td>
		</tr>
	</table>
	<div>
	   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#ff9900">系统提示：</span><br/>
	   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#ff9900"> 请勿修改{}中内容，以免系统出现错误或不能发送正确邮件</span>
	   </div>
	  <div class="formTableBottom">
 		<my:permission key='sy-6601-02' value='模板修改'>
	 		<input id="submitInput" type="submit" class="formButton" value="确定" onclick="return check();"/>
	    </my:permission>
	    <my:permission key='sy-6601-03' value='全部重置'>
	 		<input id="submitInput" type="button" class="formButton" value="全部重置" onclick="go('mail/EmailTemplate!resetinf')"/>
	    </my:permission>
	      <my:permission key='sy-6601-04' value='单条重置'>
	 		<input id="submitInput" type="button" class="formButton" value="重置" onclick="resetEvery();" />
	    </my:permission>
		 </div>
	</s:form>
</body> 
</html>