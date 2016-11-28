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
	<title>网站提醒</title>
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
	<script type="text/javascript">
	function putValue(obj,name){
		if($(obj).is(':checked')){
			document.getElementsByName(name)[0].value=1;
		}
		else{
			document.getElementsByName(name)[0].value=0;
		}
	}
	
	   //form提交
		function save(){
			document.getElementById('submitInput').disabled = true;
			var actionUrl = "log/siteRemindInfo!editUI";
			$.ajax( {
			    url : actionUrl,
			    data : $("#form").serialize(),
			    cache : false,
			    type : "POST",
			    error : function(textStatus, errorThrown) {
			      	alert("系统ajax交互错误!");
			    },
			    success : function(data, textStatus) {
				    alert("修改成功");
				    window.location.href="log/siteRemindInfo";
			    }
			});
		}
	   </script> 
</head>
<body>
<div style =”color:red” >
    <s:fielderror />
</div>
	<s:form action="" id="form" method="post" theme="simple">

    <table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" border="0">
           <tr>
                  <th width="30%" style="border:0px">信息内容</th>  
                  <th style="border:0px">邮件</th>	
                  <th style="border:0px">站内信</th>  
                  <th style="border:0px">手机</th>
           </tr>                             	
           <s:iterator value="siteRemindInfoDTO.childrens" id="children" status="status" >
           <s:hidden name="siteRemindInfoDTO.childrens[%{#status.index}].contId"></s:hidden>
           <s:hidden name="siteRemindInfoDTO.childrens[%{#status.index}].infoContent"></s:hidden>
           <s:hidden name="siteRemindInfoDTO.childrens[%{#status.index}].detailId"></s:hidden>
           <s:hidden name="siteRemindInfoDTO.childrens[%{#status.index}].parentId"></s:hidden>
           <s:hidden name="siteRemindInfoDTO.childrens[%{#status.index}].emailReceive"></s:hidden>
           <s:hidden name="siteRemindInfoDTO.childrens[%{#status.index}].leterReceive"></s:hidden>
           <s:hidden name="siteRemindInfoDTO.childrens[%{#status.index}].phoReceive"></s:hidden>
           <s:if test="null==parentId">
           <tr>  
                 <td colspan="4"  style="border:0px" align="left">
                 <s:property value="infoContent"/></td>
           </tr>
           </s:if>
           <s:else>
            <tr> 
           <td class="cell-first" style="border:0px;color:blue;" align="left">
               <span  >*</span><s:property value="infoContent"/>
			</td>
           <td style="border:0px;color:blue;">
           <s:checkbox name="" onchange="putValue(this,'siteRemindInfoDTO.childrens[%{#status.index}].emailReceive')" value="emailReceive"/></td>
           <td style="border:0px;color:blue;">
           <s:checkbox name="" onchange="putValue(this,'siteRemindInfoDTO.childrens[%{#status.index}].leterReceive')" value="leterReceive"/></td>
           <td style="border:0px;color:blue;">
           <s:checkbox name="" onchange="putValue(this,'siteRemindInfoDTO.childrens[%{#status.index}].phoReceive')" value="phoReceive"/></td>
           </tr>
           </s:else>
           </s:iterator>
           
     </table>  
      <div class="formTableBottom">
			<input id="submitInput" type="button" class="formButton" value="保 存" onclick="save();"/>
		   </div>
 </s:form>
</body> 
</html>