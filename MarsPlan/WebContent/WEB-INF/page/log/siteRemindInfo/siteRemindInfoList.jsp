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
	<script src="js/pubValidate.js"></script>
   <script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">	
	
	   //添加checkbox函数
	   function check()
	   {
	   	 var actionUrl = "log/siteRemindInfo!jsonPageList";
	   	 var params = {};
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
			          	    var j=0;
			        		for(var i=0;i<data.length;i++) {
			                         if(data[i][1]==1)
			                         {
			                              j=j+1;
				                          var id=data[i][0]+"1";
				                          var rid="#"+id;
				                          var html="";
				                          if(id==11||id==21||id==31||id==72)
				                          {
				                             html= $("<input type='checkbox' id='"+j+"' <s:if test='1'>checked='checked'</s:if> disabled/>");
				                          }else{
				                          html= $("<input type='checkbox' id='"+j+"' <s:if test='1'>checked='checked'</s:if> />");
				                         
				                          }
				                       
				                          $(rid).append(html);
					        		      
			                         }else{
			                             j=j+1;
				                         var id=data[i][0]+"1";
				                         var rid="#"+id;
				                         var html= $("<input type='checkbox' id='"+j+"' <s:if test='0'>checked='checked'</s:if> />");
				                          
						        		 $(rid).append(html);
						        		
			                         }
			                          if(data[i][2]==1)
			                         {
			                              j=j+1;
				                          var id=data[i][0]+"2";
				                          var rid="#"+id;
				                          var html="";
				                          if(id==11||id==21||id==31||id==72)
				                          {
				                             html= $("<input type='checkbox' id='"+j+"' <s:if test='1'>checked='checked'</s:if> disabled/>");
				                          }else{
				                          html= $("<input type='checkbox' id='"+j+"' <s:if test='1'>checked='checked'</s:if> />");
				                         
				                          }
				                          $(rid).append(html);
						        		  
			                         }else{
		                                  j=j+1;
			                              var id=data[i][0]+"2";
			                              var rid="#"+id;
			                              var html= $("<input type='checkbox' id='"+j+"' <s:if test='0'>checked='checked'</s:if> />");
					        		      $(rid).append(html);
					        		     
			                         }
			                          if(data[i][3]==1)
			                         {
			                           j=j+1;
			                            var id=data[i][0]+"3";
			                            var rid="#"+id;
			                            var html="";
				                          if(id==11||id==21||id==31||id==72)
				                          {
				                             html= $("<input type='checkbox' id='"+j+"' <s:if test='1'>checked='checked'</s:if> disabled/>");
				                          }else{
				                          html= $("<input type='checkbox' id='"+j+"' <s:if test='1'>checked='checked'</s:if> />");
				                         
				                          } $(rid).append(html);
					        		   
			                         }else{
			                           j=j+1;
			                            var id=data[i][0]+"3";
			                            var rid="#"+id;
			                            var html= $("<input type='checkbox' id='"+j+"' <s:if test='0'>checked='checked'</s:if> />");
					        		    $(rid).append(html);
					        		    
			                         }
	        				}
	        			}
			        }
			    });
	   }
	   //修改checkbox函数
	   function getcheckbox()
	   {
	        
	          var getCK=document.getElementsByTagName('input');  
	          var groupcheckIds="";
	          var  nocheckidList="";
			  for(var i=0;i<getCK.length;i++)   
			  {   
			      var whichObj=getCK[i];   
			      if(whichObj.type=='checkbox')   
			      {   if(whichObj.checked==true)
				      {   
				           var checkId=whichObj.id;
				           groupcheckIds+=";"+checkId;
				      }else{
				             var nocheckId=whichObj.id;
				             nocheckidList+=";"+nocheckId;
				      }
			      }  
			  }
			   var actionUrl = "log/siteRemindInfo!editUI";
			   var params = {
			   "siteRemindInfoDTO.groupcheckIds" : groupcheckIds,
			   "siteRemindInfoDTO.nocheckIds":nocheckidList
			   };
			
	   	       $.ajax( {
			        url : actionUrl,
			        data : params,
			        dataType : "json",
			        async : false,
			        cache : false,
			        type : "POST",
			       error : function(textStatus, errorThrown) {
						alert("系统ajax交互错误!");
		    		},
		    		success : function(data, textStatus) {		    		
		    			if (data.ajaxResult == "ajaxsuccess") {
		                	alert("操作成功!");
		            	}else if(data.ajaxResult == "ajaxfailure"){
		            		alert(data.msgResult);
		            	}else {
		            		alert("取消发布失败!");
		            	}
		    		}
			    }); 
	   }
	   
	</script> 
</head>
<body onload="check();">
	<s:form action="" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">

    <table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" border="0">
           <tr>
                  <th width="30%" style="border:0px">信息内容</th>  
                  <th style="border:0px">邮件</th>	
                  <th style="border:0px">站内信</th>  
                  <th style="border:0px">手机</th>
           </tr>                             	
    	   <tr>  
    	         <td colspan="0"  style="border:0px" style="color:gray;" align="left">卖家提醒</td>
    	   </tr>								 
           <tr>  
                 <td colspan="0"  style="border:0px" align="left">宝贝管理通知</td>
           </tr>
           <tr id="1"> 
		              <td class="cell-first" style="border:0px;color:blue;" align="left">
		                    <span  >*</span>当我的宝贝被移到其他类目时，请通知我
		    		  </td>
	                  <td  style="border:0px;color:blue;"  id="11"></td>
	                  <td  style="border:0px;color:blue;"  id="12"> </td>
	                  <td  style="border:0px;color:blue;"  id="13"></td>
                 
            </tr>  
            <tr id="2"> 
	                 <td  style="border:0px;color:blue;" align="left">
	                       <span >*</span>当我的宝贝被管理员移除时，请通知我
	    		     </td>
	                 <td  style="border:0px" id="21">  </td>
	                 <td  style="border:0px" id="22">  </td>
	                 <td  style="border:0px" id="23">  </td>
           </tr>  
           <tr id="3"> 
	                 <td  style="border:0px;color:blue;" align="left">
	                       <span >*</span>当我的宝贝被管理员下架时，请通知我
	    		     </td>
	                 <td  style="border:0px" id="31"></td>
	                 <td  style="border:0px" id="32"></td>
	                 <td  style="border:0px" id="33"></td>
           </tr>  
           <tr >  
                 <td colspan="0"  style="border:0px" align="left">留言通知</td>
           </tr>
           <tr id="4"> 
	                   <td  style="border:0px;color:blue;" align="left">
		                    <span  >*</span>买家给我的宝贝留言时，请通知我
		    		  </td>
	                  <td  style="border:0px;color:blue;" id="41"></td>
	                  <td  style="border:0px;color:blue;" id="42"></td>
	                  <td  style="border:0px;color:blue;" id="43"></td>
           </tr>  
           <tr id="5"> 
	                 <td   style="border:0px;color:blue;" align="left">
	                       <span class="spark-indeed">*</span>买家在我的店铺中留言时，请通知我
	    		     </td>
	                 <td  style="border:0px" id="51"></td>
	                 <td  style="border:0px" id="52"> </td>
	                 <td  style="border:0px" id="53"> </td>
           </tr>  
           <tr >  
                 <td colspan="0"  style="border:0px" align="left">买家提醒完成交易</td>
           </tr>
           <tr id="6"> 
		              <td  style="border:0px;color:blue;" align="left">
		                    <span  >*</span>当买家提醒我完成交易时，请通知我
		    		  </td>
	                  <td  style="border:0px;color:blue;" id="61">  </td>
	                  <td  style="border:0px;color:blue;" id="62">  </td>
	                  <td  style="border:0px;color:blue;" id="63">  </td>
           </tr>  
           <tr>  
                 <td colspan="0"  style="border:0px" align="left">交易过程</td>
           </tr>
           <tr id="7"> 
		              <td  style="border:0px;color:blue;" align="left">
		                    <span  >*</span>提醒卖家发货
		    		  </td>
	                  <td  style="border:0px;color:blue;" id="71">   </td>
	                  <td  style="border:0px;color:blue;" id="72"> </td>
	                  <td  style="border:0px;color:blue;" id="73"></td>
           </tr>  
           <tr>  
                 <td colspan="0"  style="border:0px" align="left">店铺管理</td>
           </tr>
           <tr id="8"> 
		              <td  style="border:0px;color:blue;" align="left">
		                    <span  >*</span>店铺连续3周宝贝数量为0件，被系统提醒时，通知我
		    		  </td>
	                  <td  style="border:0px;color:blue;" id="81"></td>
	                  <td  style="border:0px;color:blue;" id="82"> </td>
	                  <td  style="border:0px;color:blue;" id="83"> </td>
           </tr>  
           <tr id="9"> 
		              <td  style="border:0px;color:blue;" align="left">
		                    <span  >*</span>店铺连续5周宝贝数量为0件，被系统暂时释放时，通知我
		    		  </td>
	                  <td  style="border:0px;color:blue;" id="91" > </td>
	                  <td  style="border:0px;color:blue;" id="92"> </td>
	                  <td  style="border:0px;color:blue;" id="93"></td>
           </tr>  
           <tr id="10"> 
		              <td  style="border:0px;color:blue;" align="left">
		                    <span  >*</span>店铺连续6周宝贝数量为0件，被系统彻底释放时，通知我
		    		  </td>
	                  <td  style="border:0px;color:blue;" id="101"> </td>
	                  <td  style="border:0px;color:blue;" id="102"></td>
	                  <td  style="border:0px;color:blue;" id="103"></td>
           </tr>  
           <tr id="11"> 
		              <td  style="border:0px;color:blue;" align="left">
		                    <span  >*</span>店铺被激活时，通知我
		    		  </td>
	                  <td  style="border:0px;color:blue;" id="111"></td>
	                  <td  style="border:0px;color:blue;" id="112"></td>
	                  <td  style="border:0px;color:blue;" id="113"></td>
           </tr>  
           
     </table>  
      <div class="formTableBottom">
			<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return getcheckbox();"/>
		   </div>
 </s:form>
</body> 
</html>