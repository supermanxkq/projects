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
	<title>全站公告管理</title>
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
	var checknameflag=false;//名称是否为空
	var flagTitle=false;//名称是否重复
	var checkcotflag=false;//广告链接
	var	advertContentFlag=false;//广告描述
	var contractFlag=false;//广告联系人
	var checktelflag=false;//联系电话
	var filePicFlag=false;//上传图片
	var  endTimeFalg=false;//结束时间
	var startTimeFalg=false;//开始时间
	var checkTimeFalg=false;//两个时间比较
	
	//加载页面
	$(document).ready(function (){
	       var method = document.getElementById("method"); 
	           var meobj=$.trim($("#mediaType").val());
	        if(method.value=='checkDetail'){
	             setInputDisabled();
	              $("#advertContent").attr("readonly","true");
	              $("#advertContent").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
	              $("#advertName").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
	              $("#startTime").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
	              $("#endTime").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
	              $("#adverCot").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
	            }
	        })
	        
	        //校验广告名称
	          function checkname(){
	               var type=["isNull",];
	               var errorMsg=["广告名称不能为空"];
	               checknameflag=showErrorMsg(document.getElementById("advertName"),type,errorMsg,"advertNameerr","advertNameerr");
	               var val=$.trim($("#advertName").val());
	               if(val!="")
	               {
	                checkAnnounTitle();
	                }
	               }
	        //检查广告标题是否重复
		var checkAnnounTitle=function(){		  
			var method = $("#method").val();
			var advertName=$("#advertName").val();
			var advertId = $("#advertId").val();
			if(advertName.length!=0){
			$("#advertNameerr").html("");
				/**如果是添加的方法*/
				if(method=="addSave"){
					var params = {
							"method":method,
							"advertmaDTO.advertName":advertName
					    }
				}else{
					var params = {
							"method":method,
							"advertmaDTO.advertName":advertName,
					    	"advertmaDTO.advertId":advertId
					    }    	
				}
			/**请求的action*/
			 var actionUrl = "marketing/advertma!checkadvertName";
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
			        	if(data.flag==false){
			        		$("#advertNameerr").html("");
			        		$("#advertNameerr").hide();
			        		flagTitle=true;
			        	
					    }else{
					       $("#advertNameerr").html("");
		    	           $("#advertNameerr").html("标题名重复");
		    	           $("#advertNameerr").show();
					       flagTitle=false;
						 }
			        }
			    });
				}
			}
		//校验广告链接
		function validateAdverCot(){
		   var type=["isNull","isUrl"];
		   var errorMsg=["链接不能为空","链接格式不正确"];
		   checkcotflag=showErrorMsg(document.getElementById("adverCot"),type,errorMsg,"advertContErr","advertContErr");
		}
		//验证分类图片
		function changeFile(){
				//$("#uploadValue").html("");
				var file=$("#upload").val();
				var val=$("#uploadValue");
				var extName=file.substring(file.lastIndexOf(".")+1,file.length);
				if(file.length==0){
					if($("#picPathImg").length>0){
					filePicFlag=true;
					val.hide();
					return;
					}
				}
				if(file.length==0){
					fileFlag=false;
					val.html("必须上传图片");
					val.show();
					filePicFlag=false;
				}else{
					if(/^(jpg|png|jpeg)$/.test(extName)){
						fileFlag=true;
						val.html("");
						val.hide();
						filePicFlag=true;
					}else{
						fileFlag=false;
						val.html("图片格式不符");
						val.show();
						filePicFlag=false;
					}

			 }
				
			}
		//校验广告描述
		function validateAnnounContent(){
			 var textareaValue=$.trim($("#advertContent").val());

			 if(textareaValue==""){
				 $("#advertContentErr").html("请填写广告描述内容");
				 $("#advertContentErr").show();
				 advertContentFlag=false;
				 }else{
	                 if(textareaValue.length>100){	           
	                	 $("#advertContentErr").html("广告描述内容过长");
	      			     $("#advertContentErr").show();
	      			      advertContentFlag=false;
	                     }else{
	                    	 $("#advertContentErr").html("");
		      			     $("#advertContentErr").hide();
		      			      advertContentFlag=true;

		                     }
					 } 
		        }
		//校验联系人
		function validatecontractError(){
		    	var type = ["isNull","isRealName"];
		        var errorMsg = ["联系人不能为空!","不是真实姓名!"];  
		      contractFlag = showErrorMsg(document.getElementById("contract"),type,errorMsg,"contractError","contractError");
		    
		}
		//校验Email
		function validateEmail(){	
		  var email=$.trim($("#ContractEmail").val());
		  $("#emailError").html("");
		  var reg=new RegExp("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$");
		  if(email.length==0){
		    $("#emailError").html("");
		    $("#emailError").html("email为空");
		    $("#emailError").show();
		       emailFalg=false;
		  }else{
		      if(!reg.test(email)){ 
		          $("#emailError").html("email格式错误");
		          $("#emailError").show();
		           emailFalg=false;
		      }else{
		    	  $("#emailError").html("email格式错误");
		          $("#emailError").hide();
		           emailFalg=true;
			      }
		  }
		}
		//校验电话号码
		function validateTel(){
		  var type=["isNull","isMobile"];
		  var errorMsg=["电话号码不能为空","电话号码的格式错误"];
		  checktelflag=showErrorMsg(document.getElementById("ContractTel"),type,errorMsg,"TelError","TelError");
		}
		//检验开始时间
		function checkBeginTime(){
		  var startTime=$("#startTime").val();
		  var endTime=$("#endTime").val();
		  var d1 = new Date(startTime.replace(/\-/g, "\/"));  
		  var d2 = new Date(endTime.replace(/\-/g, "\/")); 
          if(startTime==""){
        	  $("#startTimeError").html("开始时间不能为空");
	          $("#startTimeError").show();
	          startTimeFalg=false;
              }else{
            	  $("#startTimeError").html("");
    	          $("#startTimeError").hide();
    	          startTimeFalg=true;
    	          if(endTime!=""){
    	        	  checkTime();
        	          }
                  } 
			}
		//检验结束时间
		function checkEndTime(){
		  var startTime=$("#startTime").val();
		  var endTime=$("#endTime").val();
		  var d1 = new Date(startTime.replace(/\-/g, "\/"));  
		  var d2 = new Date(endTime.replace(/\-/g, "\/")); 
          if(endTime==""){
        	  $("#endTimeError").html("开始时间不能为空");
	          $("#endTimeError").show();
	          endTimeFalg=false;
              }else{
            	  $("#endTimeError").html("");
    	          $("#endTimeError").hide();
    	          endTimeFalg=true;
    	          if(startTime!=""){
    	        	  checkTime();
        	          }
                  } 
			}
		//校验开始时间和结束时间
		function checkTime(){
			 var startTime=$("#startTime").val();
			 var endTime=$("#endTime").val();
			 var d1 = new Date(startTime.replace(/\-/g, "\/"));  
			 var d2 = new Date(endTime.replace(/\-/g, "\/")); 
			 if(d1 >=d2){
      		    $("#endTimeError").html("开始时间不能大于结束时间");
      		    $("#endTimeError").show();
      		    checkTimeFalg=false;
          		   }else{
          			 $("#endTimeError").html("");
           		     $("#endTimeError").hide();
           		    checkTimeFalg=true;

              		   }  
		}

		
				
	 //切换第一个文字
	       function showNotice(){
	            var name=document.getElementById("hid").name ;
	            if(name==0){
	                $("#hid").attr("name","1");
	                $("#NameNotic").show();
	            }else{
	                $("#NameNotic").hide();
	                $("#hid").attr("name","0");
	            }
	       }
	//切换第二个文字
	function showwen(){ 	 
	      var name=document.getElementById("wen").name;
	      if(name==0){ 
	          $("#upimg").show();
	          $("#wen").attr("name","1");
	        }else{
	          $("#upimg").hide();
	          $("#wen").attr("name","0");
	        }
	       }
	
	
	
	//保存前的校验
	function check()
	{
		
		checkname();
		validateAdverCot();
		changeFile();
		validateAnnounContent();
		validatecontractError();
		validateEmail();
		validateTel();
		checkEndTime();
		checkBeginTime();
		//alert(checknameflag&&flagTitle);
		//alert(endTimeFalg&&startTimeFalg&&checkTimeFalg&&checktelflag&&filePicFlag&&contractFlag&&checkcotflag&&advertContentFlag);
	    if(checknameflag&&flagTitle&&checkcotflag&&advertContentFlag&&contractFlag&&checktelflag&&filePicFlag&&endTimeFalg&&startTimeFalg&&checkTimeFalg){
	    	 return true;
	    }else{
	    	 alert("数据错误");
		       return false;
		    }
	   
	}
	
   </script>    
</head>
<body >
	<s:form action="marketing/advertma" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;"
	      enctype="multipart/form-data" theme="simple">
	 <div class="Position">
		当前位置是：HOME &gt;&gt; 商城前台 &gt;&gt; 全站广告管理
	</div>
	    <s:hidden id="hid" name="0"/>
	    <s:hidden id="wen" name="0"/>
        <s:hidden name="method" id="method"/>
        <s:hidden name="advertmaDTO.createTime" id="createTime"/>
        <s:hidden name="0" id="medihid"/>
        <s:hidden name="advertmaDTO.advertId" id="advertId" />
         <s:hidden name="advertmaDTO.imagePath" />
		<table width="100%" border="0" cellpadding="0" style="border:0px" cellspacing="0" class="formTable">
			<tr>
		  		<th align="right" width="20%">
		  		  <a  title="点击此处查看提示信息" href="javascript:showNotice();">
                  <img src="js/img/notice.jpg" width="16" height="16" border="0" ></a>广告名称
                 </th>
		        <td width="30%">
							<s:textfield name="advertmaDTO.advertName" id="advertName"  size="70"  maxlength="30"
							onblur="checkname();" />
							 <br /><span style="display:none;color:gray;"  id="NameNotic">广告名称只是作为辨别多个广告条目之用，并不显示在广告中</span>
							 <label id="advertNameerr" style="display:none" class="errorMsg"></label>
		        </td>
		        <th align="right" width="20%"><span class="Color5">* </span>媒介类型：</th>
				<td>
				 <s:if test="method=='addSave'||method=='checkDetail'||method=='editSave'">
				 <s:select name="advertmaDTO.mediaType" id="mediaType"
							list="#request.mediaTypeList" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />
							</s:if></td>
			</tr>	
			
			<tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>广告类型：</th>
				<td>
				 <s:if test="method=='addSave'||method=='checkDetail'||method=='editSave'">
				 <s:select name="advertmaDTO.advertType" id="mediaType"
							list="#request.mediaAdvertContentsList" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple"/>
							</s:if></td>
			<th align="right" width="20%"><span class="Color5">* </span>广告位置：</th>
		        <td>
		        <s:if test="method=='addSave'||method=='checkDetail'||method=='editSave'">
		        <s:select name="advertmaDTO.positionTypeId" id="positionTypeId"
							list="#request.mediaLocationList" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />
				</s:if>
				</td>
			</tr>
			
			<tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>开始日期：</th>
		        <td width="30%"> 
		           <s:textfield id="startTime" name="advertmaDTO.beginTime" readonly="true" cssStyle="width:150px;"  onchange="checkBeginTime()" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>      
		           <label id="startTimeError" style="display:none;" class="errorMsg"></label>
		           <br/> <div id="div1"></div>
		        </td>
		        <th align="right" width="20%"><span class="Color5">* </span>结束日期：</th>
		        <td width="30%">
					  <s:textfield id="endTime" name="advertmaDTO.endTime" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" onchange="checkEndTime()" cssStyle="width:150px;" maxlength="20" theme="simple"/>
					 <label id="endTimeError" style="display:none;" class="errorMsg"></label>
		        </td>
			</tr>
			<tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>广告链接：</th>
		        <td width="30%">
							<s:textfield name="advertmaDTO.adverCot" id="adverCot"    maxlength="40"
							size="50" onblur="validateAdverCot();" />
							<label id="advertContErr" style="display:none;" class="errorMsg"></label>
		        </td>
		       <th align="right" width="20%"><span class="Color5">* </span>联系人电话：</th>
		        <td width="30%">
							<s:textfield name="advertmaDTO.ContractTel" id="ContractTel" maxlength="25"
							cssClass="formInput" theme="simple" onblur="validateTel();" />
							<label id="TelError" style="display:none" class="errorMsg"></label> 						
		        </td>								  		
			</tr>			
			<tr>
			   <th align="right" width="20%"><span class="Color5">* </span>排列顺序：</th>
				<td>
				 <s:if test="method=='addSave'||method=='checkDetail'||method=='editSave'">
				 <s:select name="advertmaDTO.showId" id="mediaType"
							list="#request.mediashowList" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple"/>
							</s:if></td>
		  		<th align="right" width="20%"><span class="Color5">* </span>是否开启：</th>
		        <td width="30%">
		                   
							<input type="radio" id="star" name="advertmaDTO.openType"  value=0 checked/>开启
							<input type="radio" id="stop"  name="advertmaDTO.openType"  value=1 />关闭
		        </td>
			</tr>
			<tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>广告联系人：</th>
		        <td width="30%">
							<s:textfield name="advertmaDTO.contract" id="contract"    maxlength="12"
							cssClass="formInput" theme="simple" onblur="validatecontractError();" />
							<label id="contractError" style="display:none" class="errorMsg"></label> 
							
		        </td>
		        <th align="right" width="20%"><span class="Color5">* </span>联系人Email：</th>
		        <td width="30%">
							<s:textfield name="advertmaDTO.ContractEmail" id="ContractEmail" maxlength="25"
							cssClass="formInput" theme="simple" onblur="validateEmail();" />
							<label id="emailError" style="display:none" class="errorMsg"></label> 
		        </td>
			</tr>						
			   <tr>
			     <th align="right" width="20%"><span class="Color5">* </span>广告描述：</th>
			     <td>
			      <s:textarea name="advertmaDTO.advertContent" id="advertContent" rows="4" cols="50" onblur="validateAnnounContent();"></s:textarea>
			      <label id="advertContentErr" style="display:none;" class="errorMsg"></label>
			     </td>
			     <th align="right" width="20%"><a title="点击查看提示信息" href="javascript:showwen()"><img src="js/img/notice.jpg"/></a>上传广告图片</th>
				 <s:if test="method=='checkDetail'">
		  			<td>
		  				<s:if test="advertmaDTO.imagePath != null && advertmaDTO.imagePath != ''">
								<img id="picPathImg" style="width:150px;height:150px;" src="<s:property value='advertmaDTO.imagePath'/>"/>
						</s:if>
						<s:else>
							<span class="Color3">暂无图片！</span>
						</s:else>
		  			</td>
		  		</s:if>
		  		<s:else>
		        	<td>
		        		<s:if test="advertmaDTO.imagePath != null && advertmaDTO.imagePath != ''">
								<img id="picPathImg" style="width:150px;height:150px;" src='<s:property value="advertmaDTO.imagePath"/>'/>
						</s:if>
						
    					<s:file id="upload"  name="advertmaDTO.imageFile" accept=".jpg,.png,.jpeg" cssClass="formInput" theme="simple" onchange="changeFile();"/>
						    <span id="uploadValue" style="display: none;" class="errorMsg" ><s:fielderror/></span>
							<br/>
							<span id="upimg" style="color:gray;display:none">上传该广告的图片文件,图片类型为只允许jpg,png,jpeg文件，且大小不能超过1M!</span>		
		        	</td>
		        </s:else> 			  
			  </tr>
			
			
			
			
				
	</table>	
		 <div class="formTableBottom">
		 		<s:if test="method=='addSave'">
		 		<my:permission key='sy-7201-02' value='广告信息添加'>
			 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
			 </my:permission>
	           	</s:if>
			<s:if test="method=='editSave'">
				<my:permission key='sy-7201-03' value='广告信息修改'>
			 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
			 	</my:permission>
			</s:if>
			<input type="button" class="formButton" value="返 回" onclick="go('marketing/advertma!list')"/>
		 </div>
	 </s:form>
	
</body>
</html>