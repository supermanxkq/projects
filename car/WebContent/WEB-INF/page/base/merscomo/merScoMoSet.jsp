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
         var flagTitle=false;
         var checknameflag=false;
         var checknameflag1=false;
         var checknameflag2=false;
         var flagNum=false;
     //加载页面
	$(document).ready(function (){
	     var method = $("#method").val();
	     if(method=="editSave"){ 
	      var showPic = $("#showPic").val();
	     $("#imgs").attr("src",showPic);
	     
	     } 
	          
	      if(method=="checkUI"){
	    	  
	      var showPic = $("#showPic").val();
	       $("#imgs1").attr("src",showPic);
	       setInputDisabled();
	     } 
 
	        })
     //保存前的校验
	function check()
	{
	
	  checkname();
	  checkfile();
	  checkMinSco();
	  checkMaxSco(); 
	  checkMaxmin();
	
	   if(!(flagNum&&flagTitle&&checknameflag&&checknameflag1&&checknameflag2&&checkfile()&&checkMaxmin()))
	    {
	       alert("数据错误");
	       return false;
	    }
	    
	    
	    return true;
	}
	//修改前的校验
	function check1()
	{
	   
	  checkname();
	  checkfile1();
	  checkMinSco();
	  checkMaxSco(); 
	  checkMaxmin();
	// &&checkfile1()&&checkMinSco()&&checkMaxSco()&&flagTitle&&checknameflag&&checknameflag1&&checknameflag2
	if(!(flagNum&&flagTitle&&checknameflag&&checknameflag1&&checknameflag2&&checkfile1()&&checkMaxmin()))
	    {
	       alert("数据错误");
	       return false;
	    }
	    return true;
	}
	  //验证上传文件
	function checkfile(){
	var file = $("#file").val();
	  if(file=="undefine" || file==""|| file==null){
	    $("#fileerr").html("");
	    $("#fileerr").html("上传文件不能为空！");
	    $("#fileerr").show();
        return false;
      
      }else{
      
       var array = file.split('.');
	   var ext = array[array.length-1].toLowerCase();	  
	   if( ext != "png" && ext !="gif" && ext !="jpg" && ext != "jpeg" ){		   
		  $("#fileerr").html("");
	      $("#fileerr").html("只能上传图片格式！");
	      $("#fileerr").show();
		  return false;
	  }else{
	   $("#fileerr").html("");
	     
	      $("#fileerr").hide();
	      return true;
	  
	  }
      
       
      }
	  
	}
	 //修改上传文件
	function checkfile1(){
	var file = $("#file").val();
	
	  if(file=="undefine" || file==""|| file==null){
	    
		  return true;
      }else{
      
       var array = file.split('.');
       //var ext = file.substring(file.lastIndexOf(".") + 1).toLowerCase();
       //alert(array);
	   var ext = array[array.length-1].toLowerCase();	  
	   if( ext != "png" && ext !="gif" && ext !="jpg" && ext != "jpeg" ){		   
		  $("#fileerr1").html("");
	      $("#fileerr1").html("只能上传图片格式！");
	      $("#fileerr1").show();
		  return false;
	  }else{ 
	   $("#fileerr1").html("");
	     
	      $("#fileerr1").hide();
	      return true;
	  
	  
	  }
      
      
      }
	  
	}
	//验证最大分数是否小于最小分数
	function  checkMaxmin(){
	
	  var valmin=$("#minSco").val();
	  var valmax=$("#maxSco").val();
	  
	  
	  if(valmin!="" && valmax !=""&& valmax !=null&& valmin !=null)
	 {
	// alert(valmin);
	// alert(valmax);
	   if(Number(valmin)>Number(valmax)){
	    $("#Scoerr2").html("");
	    $("#Scoerr2").html("商户评分最小分数不能大于商户评分最大分数！");
	    $("#Scoerr2").show();
	    return false;
	   
	   }else{
		   //alert("fasdfew");
		   
	    $("#Scoerr2").html("");
	    
	    $("#Scoerr2").hide();
	   
	   return true;
	   
	   }
	
	 }
	
	
	}
	
	 //校验商户评分分数
	function  checkMinSco(){
	 var type=["isNull","fullNumber"];
	 
	var errorMsg=["商户评分模型最小分数不能为空!","商户评分模型最小分数只能输入数字！"];
	 checknameflag=showErrorMsg(document.getElementById("minSco"),type,errorMsg,"Scoerr1","Scoerr1");
	
	 
	 if(checknameflag1&&checknameflag){

		 checkAnnounNum();
		 }
	}
	//校验商户评分最大分数
	function  checkMaxSco(){
	 var type=["isNull","fullNumber"];
	 
	 var errorMsg=["商户评分模型最大分数不能为空!","商户评分模型最大分数只能输入数字！"];
	
	 checknameflag1=showErrorMsg(document.getElementById("maxSco"),type,errorMsg,"Scoerr","Scoerr");


	 if(checknameflag1&&checknameflag){

		 checkAnnounNum();
		
		 }
	
	}
	
	 //校验商户评分模型名称
	function checkname()
	{
	 var type=["isNull",];
	 var errorMsg=["商户评分模型名称不能为空！"];
	 checknameflag2=showErrorMsg(document.getElementById("moName"),type,errorMsg,"moNameerr","moNameerr");
	
	 var val=$.trim($("#moName").val());
	 if(val!="")
	 {
	 checkAnnounTitle();
	 }
	}
	//校验商户评分模型名称是否重复
		var checkAnnounTitle=function (){
		  
			var method = $("#method").val();
			var moName=$("#moName").val();
			var merScoMoId = $("#merScoMoId").val();
			if(moName.length!=0){
			$("#moNameerr").html("");
				/**如果是添加的方法*/
				if(method=="addSave"){
					var params = {
							"method":method,
							"merScoMoDTO.moName":moName
					    }
				}else{
					var params = {
							"method":method,
							"merScoMoDTO.moName":moName,
					    	"merScoMoDTO.merScoMoId":merScoMoId
					    }    	
				}
			/**请求的action*/
			 var actionUrl = "base/merscomo!checkmoName";
			 
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
			        		$("#moNameerr").html("");
			        		$("#moNameerr").hide();
			        		flagTitle=true;
			        	
					    }else{
					       $("#moNameerr").html("");
		    	           $("#moNameerr").html("商户评分模型名称重复！");
		    	           $("#moNameerr").show();
					       flagTitle=false;
						 }
			        }
			    });
				}
			}
		//校验商户评分是否在集合内
		var checkAnnounNum=function (){
			var method = $("#method").val();
			var min =$.trim($("#minSco").val());
			var max  = $.trim($("#maxSco").val());
			var merScoMoId = $("#merScoMoId").val();
			$("#Scoerr3").html("");
			if(min.length!=0&&max.length!=0){
				
				/**如果是添加的方法*/
				if(method=="addSave"){
					var params = {
							"method":method,
							"merScoMoDTO.minSco":min,
							"merScoMoDTO.maxSco":max
					    }
				}else{
					var params = {
							"method":method,
							"merScoMoDTO.minSco":min,
							"merScoMoDTO.maxSco":max,
					    	"merScoMoDTO.merScoMoId":merScoMoId
					    }    	
				}
			/**请求的action*/
			 var actionUrl = "base/merscomo!checkMaxNum";
			 
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
				       // alert(data.total);
			            
			        	if(data.total==1){
			        		$("#Scoerr3").html("");
			        		$("#Scoerr3").hide();
							checkMaxmin();
			        		flagNum=true;
			        	
					    }else if(data.total==2){
					    	if(!checkMaxmin()){ 
								  
							       flagNum=false;
								   }else{
									   $("#Scoerr3").html("");
					    	           $("#Scoerr3").html("最小分数已经包含其他的范围！");
					    	           $("#Scoerr3").show();
									   flagNum=false;

									   }

						 }else{
							   if(!checkMaxmin()){ 
							  
						       flagNum=false;
							   }else{
								   $("#Scoerr3").html("");
				    	           $("#Scoerr3").html("最大分数已经包含其他的范围！");
				    	           $("#Scoerr3").show();
								   flagNum=false;

								   }

							 }
			        }
			    });
				}
			}
		
  </script> 
</head>
<body >
   
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 商户评分模型
	</div>
	<s:form action="base/merscomo" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;"
	      enctype="multipart/form-data" theme="simple">
    <s:hidden name="method" id="method"/>
	<s:hidden name="merScoMoDTO.merScoMoId" id="merScoMoId"/>
	<s:hidden name="merScoMoDTO.showPic" id="showPic"/>
		<table width="100%" border="0" cellpadding="0" style="border:0px" cellspacing="0" class="formTable">
			<tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>商户评分模型名称：</th>
		  		  <td width="30%">
							<s:textfield name="merScoMoDTO.moName" id="moName"    maxlength="12"
							cssClass="formInput" theme="simple" onblur="checkname();"  />
							
							 <label id="moNameerr" style="display:none" class="errorMsg"></label>
							
		        </td>
				
			</tr>
			<tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>商户分数范围：</th>
		  		  <td width="30%">
							<s:textfield name="merScoMoDTO.minSco" id="minSco"  cssStyle="width:62px"    maxlength="6"  
							cssClass="formInput" theme="simple" onblur="checkMinSco();" />
							至
							
							<s:textfield name="merScoMoDTO.maxSco" id="maxSco" cssStyle="width:62px"    maxlength="6"  
							cssClass="formInput" theme="simple" onblur="checkMaxSco();" />
							
							<label id="Scoerr1" style="display:none" class="errorMsg"></label> 
							<label id="Scoerr" style="display:none" class="errorMsg"></label>
							<label id="Scoerr2" style="display:none" class="errorMsg"></label>
							<label id="Scoerr3" style="display:none" class="errorMsg"></label>		
		         </td>
				
			</tr>
			
			
			<tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>商户显示图片：</th>
		        <td width="30%">
		        
		       
					<s:if test="method=='checkUI'">
					     <span id="upimg" style="color:gray"> <img  id="imgs1" /></span>
					</s:if>
					<s:elseif test="method=='editSave'">
					  <s:file id="file" name="image" size="40" onChange="checkfile1();"  ></s:file>
					  <span id="upimg" style="color:gray"> <img  id="imgs" />已上传图片!如需修改请选择</span>
					  <label id="fileerr1" style="display:none" class="errorMsg"></label>
					</s:elseif>
					<s:elseif test="method=='addSave'">
					  <s:file id="file" name="image" size="40" onChange="checkfile();"  ></s:file>
					  <label id="fileerr" style="display:none" class="errorMsg"></label>
					</s:elseif>			
		        </td>
		       
			</tr>			
				
	</table>
	
		 <div class="formTableBottom">
		 		<s:if test="method=='addSave'">
		 		<my:permission key='sy-1202-02' value='商户评分模型添加'>
			 		<input id="submitInput" type="submit" class="formButton" value="保 存"  onclick="return check();" />
			 </my:permission>
	           	</s:if>
			<s:if test="method=='editSave'">
				<my:permission key='sy-1202-03' value='商户评分模型修改'>
			 		<input id="submitInput" type="submit" class="formButton" value="保 存"  onclick="return check1();" />
			 	</my:permission>
			</s:if>
			<input type="button" class="formButton" value="返 回" onclick="go('base/merscomo!list')"/>
		 </div>
	 </s:form>
</body>
</html>