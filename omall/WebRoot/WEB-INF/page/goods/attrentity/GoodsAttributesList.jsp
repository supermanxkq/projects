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
		<title>商品属性值管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
			type="image/x-icon" />
		<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/jquery.validate.js"></script>
		<script type="text/javascript" src="js/jquery.metadata.js"></script>
		<script type="text/javascript" src="js/additional-methods.min.js"></script>
		<script type="text/javascript" src="js/common.validate.js"></script>
		<link href="js/jquery/css/jquery.ui.all.css" rel="stylesheet"
			type="text/css" />
		<link href="js/jquery-easyui/easyui.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="js/jquery/jquery.ui.core.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.ui.widget.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.ui.mouse.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.ui.button.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.ui.draggable.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.ui.position.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.ui.resizable.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.ui.dialog.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript">

		// 查询方法
		function query(page) {
			var attrName = $("#attrName").find("option:selected").text(); 
			var params = {
		        "goodsAttributeDTO.attrName": attrName,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "goodsAttributeDTO.page" : page
		    }; 
		   ajaxData("base/attrEntity!jsonPageList",params);
		}


		var attrNameFlag = false;
		var showNameFlag = false;
		var isEnFlag = false;


		//打开添加属性值窗口
		var openAttrForm = function() {
		    $("#d_attrName,#d_isEn,#d_displayName").removeAttr("disabled");
	        $("#d_isEn").val("");
        	$("#d_attrName").val("");
        	$("#d_displayName").val("");
        	$("#attrNameMsg").hide();
        	$("#isEnMsg").hide();
        	$("#disNameMsg").hide();
      
           	$("#dialog-attribute").dialog({
           		resizable: true,
				top: 300,
				height:250,
				width:550,
				modal: true,
				buttons: {
					'取消': function() {
						$(this).dialog('close');
					 },
					'确定添加':function(){
						saveAttribute();
						if(!(attrNameFlag&&isEnFlag&&showNameFlag)){
							return false;
						}
						$(this).dialog('close');
						query(1);						
					 }
				}
			}); 
		};

		

      	//保存属性
		function saveAttribute(){
			
		   var url = "base/goodsattributeformat!saveAttribute";
		   $("#attrNameMsg").hide();
		   $("#isEnMsg").hide();
		   $("#disNameMsg").hide();
		   var attrName = $("#d_attrName").val();
		   if(attrName==""){
		   	  $("#attrNameMsg").html("请输入属性名称!");
			  $("#attrNameMsg").show();
			  attrNameFlag = false;
		   	  //return false;
		   }
		   
		   var isEn=$("#d_isEn").val();
		   if(isEn==""){
		   	  $("#isEnMsg").html("请选择是否为枚举!");
			  $("#isEnMsg").show();
			  isEnFlag = false;
		   	  //return false;
		   }

		   var displayName=$("#d_displayName").val();
		   if(displayName==""){
		   	  $("#disNameMsg").html("请输入显示名称!");
			  $("#disNameMsg").show();
			  showNameFlag = false;
		   	  //return false;
		   }

		   checkAttributeName();
		   checkDisplayName();
		   checkIsEn();
		   if(!(attrNameFlag&&isEnFlag&&showNameFlag)){
		   	  return false;
		   }
		   
           var params = {
               "attributeDTO.attrName":attrName,
               "attributeDTO.showlable":displayName,
               "attributeDTO.isEn":isEn
           };
           ajaxRequest(url,params,"POST",function(data){
           	 	if(data.flag){
                	$("#d_isEn").val("");
                	$("#d_attrName").val("");
                	$("#d_displayName").val("");  	
                }
           });
		} 

   //  查看属性的方法
   function viewData(id){
			var actionUrl = "base/attrEntity!lookData";
			var params={
					"goodsAttributeDTO.attrId":id
			}
			
			$.ajax({
				url : actionUrl,   
		        data : params,   
		        dataType : "json",   
		        cache : false,   
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
	    			alert("系统ajax交互错误!");	    
	        	}, 
	        	success:function(data, textStatus){
	        			$("#d_attrName").val(data.attrName);
	        			$("#d_isEn").val(data.isEn);
	        			$("#d_displayName").val(data.showlable);
	        			$("#d_attrName,#d_isEn,#d_displayName").attr("disabled",true);
	        			$("#attrNameMsg,#isEnMsg,#disNameMsg").hide();
					  	$("#dialog-attribute").dialog({
			           		resizable: true,
							top: 300,
							height:250,
							width:550,
							modal: true,
							buttons: {
								'取消': function() {
									$(this).dialog('close');
								 }
							}
						});  
	        	}
			});
			
		}

	 //删除数据
	 function deleteData(id){
	 var actionUrl = "base/attrEntity!delete";
			var params={
					"goodsAttributeDTO.attrId":id
			}
			if(confirm("是否确认停用？")){
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
		        	success:function(data, textStatus){
		        		query(1);
			        }
				});
			}
			
	 }
	 
		function checkAttributeName(){

		   var attrName = $("#d_attrName").val();
		   $("#attrNameMsg").hide();
		   if(attrName==""){
			  $("#attrNameMsg").html("请输入属性名称!");
			  $("#attrNameMsg").show();
			  attrNameFlag = false;
		   	  return false;
		   }
		   var url = "base/goodsattributeformat!checkAttributeName";
           var params = {
               "attributeDTO.attrName":attrName
           };
           ajaxRequest(url,params,"POST",function(data){
           	 	if(data.flag){
                    $("#attrNameMsg").html("该属性名称已存在!");
     			    $("#attrNameMsg").show();
     			    attrNameFlag = false;
                }else{
                	$("#attrNameMsg").hide();
                	attrNameFlag = true;
                }
           });
		}


		function checkIsEn(){

		   var isEn = $("#d_isEn").val();
		   $("#isEnMsg").hide();
		   if(isEn==""){
			  $("#isEnMsg").html("请选择是否为枚举!");
			  $("#isEnMsg").show();
			  isEnFlag = false;
		   	  return false;
		   }else{
			  $("#isEnMsg").hide();
			  isEnFlag = true;
		   }
		  
		}
		


		function checkDisplayName(){

		   var displayName = $("#d_displayName").val();
		   $("#disNameMsg").hide();
		   if(displayName==""){
			   $("#disNameMsg").html("请输入属性显示名称！");
			   $("#disNameMsg").show();
			   showNameFlag = false;
			   return false;
		   }
		   
		   var url = "base/goodsattributeformat!checkDisplayName";
           var params = {
               "attributeDTO.showlable":displayName
           };
           ajaxRequest(url,params,"POST",function(data){
           	 	if(data.flag){
                    $("#disNameMsg").html("该属性显示名称已存在!");
     			    $("#disNameMsg").show();
     			    showNameFlag = false;
                 }else{
                	$("#disNameMsg").hide();
                    showNameFlag = true;
                }
           });
		}
			
		function ajaxRequest(url,params,method,callback){
			$.ajax({
               url : url,
               data : params,
               async:false,
               dataType : "json",
               type : method,
               cache : false,
               error:function(errText){
                  alert("ajax加载数据异常!请联系管理员");
               },
               success:callback
           });
		}

			
		function closeDialog(dialogId){
			$("#"+dialogId).dialog("close");
		}
		
		/** 打开导入属性、属性值对话框 */
		function openExportForm(){

      		// 打开对话框   
			$("#dialog-export").dialog({
				resizable: true,
				top: 300,
				height:250,
				width:550,
				modal: true
			});
			
			// 清空file值
      		var file = $(":file");
      		file.after(file.clone().val(""));
			file.remove();
      	}
      	
      	// 检验选择的Excel文件
      	function checkExport(){
      		var upload = $("#upload").val();
      		var suf = upload.substring(upload.lastIndexOf('.'))
      		if (upload == "undefined" || (suf != ".xls" && suf != ".xlsx")) {
				alert("请选择Excel文件！");
				return false;
			} else {
				//alert("上传成功！");
				return true;
			}
      		
      	}
      	
      	//自动上传
      	function autoUpload(){
      		$("#uploadForm").submit();
      	}
      	
      	// 导入
      	function saveExport(){
	        var url = "base/attrEntity!export";
	        var params = {
	        	"upload" : $("uploadForm").serialize()
	        };
	        ajaxRequest(url,$("uploadForm").serialize(),"POST",function(data){
	        	 if("success" == data.msg){
	        	 	  query(1);
	                 alert("导入成功!");
	                 closeDialog('dialog-export');
	             } else {
	                 alert("导入失败!");
	             }
	        });
      	}
		
	</script>
	</head>
	<body onload="query(${goodsAttributeDTO.page});">
		<div class="Position">
			当前位置是：基本设置 &gt;&gt; 商品管理 &gt;&gt; 商品属性值管理
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<div class="search">
			<div class="Fl">
				<my:permission key='sy-1706-04' value='添加属性'>
					<input type="button" class="formButton" value="添加属性"
						onclick="openAttrForm();" />
					<input type="button" class="formButton" value="导入" onclick="openExportForm();" />
				</my:permission>
			</div>
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
					<th align="right">
						商品属性名称:
					</th>
					<td>
						<s:select headerKey="-1" headerValue="全部" id="attrName"
							list="#request.goodsAttributeName" onchange="changeselect()"
							listKey="key" listValue="value" theme="simple"
							name="goodsAttributeDTO.attrName"></s:select>
					</td>
					<th align="right" id="merId-th" style="display: none">
						商户名称:
					</th>
					<td>
						<s:textfield id="merId" cssStyle="display:none"
							name="goodsDTO.merId" cssClass="formInput" maxlength="60"
							theme="simple" />
					</td>
					<td>
						<input type="button" id="button1" class="formButton" value="查 询"
							onclick="query();" />
					</td>
				</tr>
			</table>
		</div>
		<form action="base/goods" id="batchForm" method="POST">
			<input type="hidden" name="method" id="methodId" />
			<table width="96%" id="listTable" class="listTable" cellpadding="0"
				cellspacing="0">
				<tr>
					<th width="10%">
						<a name="attrId" class="sort">商品属性编号</a>
					</th>
					<th width="10%">
						<a name="attrName" class="sort">商品属性名称</a>
					</th>
					<th width="10%">
						<a name="attrName" class="sort">属性显示名称</a>
					</th>
					<th width="10%">
						<a name="isEn" class="sort">是否枚举</a>
					</th>
					<th width="5%">
						<a>相关操作</a>
					</th>
				</tr>
			</table>
		</form>
		<div class="listBottom">
			<div class="Fl">
				<my:permission key='sy-1706-04' value='添加属性'>
					<input type="button" class="formButton" value="添加属性"
						onclick="openAttrForm();" />
					<input type="button" class="formButton" value="导入" onclick="openExportForm();" />
				</my:permission>
			</div>
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
		</div>
		
		<!-- 属性对话框 -->
	 	<div id="dialog-attribute" style="display:none;" title="添加属性">
			<table style="padding-top:20px;" width="100%" frame="void">
				<tr>
					<th align="right" width="15%"><span style="font-weight:normal;">属性名称：</span></th>
					<s:hidden name="goodsAttributeDTO.attrId" id="attrId"></s:hidden>
					<td align="left" width="30%">
						<input type="text" style="width:150px" cssClass="formInput" 
						id="d_attrName" maxlength="30" onblur="checkAttributeName();">						
					</td>
					<th align="right" width="15%"><span style="font-weight:normal;">是否枚举：</span></th>
					<td align="left" width="30%">
						<select name="isEn" id="d_isEn" style="width:150px" onchange="checkIsEn();">
							<option value="">请选择</option>
							<option value="0">是</option>
							<option value="1">不是</option>
						</select>
					</td>
				</tr>
				<tr>
					<th align="right" height="20px" width="15%"></th>
					<td align="left" width="30%">
						<label id="attrNameMsg" class="errorMsg" style="display: none;"></label>
					</td>
					<th align="right" width="15%"></th>
					<td align="left" width="30%">
						<label id="isEnMsg" class="errorMsg" style="display: none;"></label>
					</td>
				</tr>
				<tr>
					<th align="right" width="15%">
					<span style="font-weight:normal;">显示名称：</span>					
					</th>
					<td align="left" width="30%">
						<input type="text" style="width:150px" cssClass="formInput"
						 id="d_displayName" maxlength="30" onblur="checkDisplayName();">
					</td>
					<th align="right" width="20%"></th>
					<td align="left" width="30%"></td>
				</tr>
				<tr>
					<th align="right" height="20px" width="15%"></th>
					<td align="left" width="30%">
						<label id="disNameMsg" class="errorMsg" style="display: none;"></label>
					</td>
				</tr>
			</table>
		</div>
		
		<!-- 导入属性、属性值对话框 -->
	 	<div id="dialog-export" style="display:none;margin:0 0;padding:0 0 0 0;" title="导入属性、属性值">
			<div style="display:block;width:96%;padding-top:15px;padding-left:10px;">
				<fieldset style="margin-top:20px;" id="addAttContal">
					<legend>导入属性、属性值</legend>
					<div style="display:block;margin:10px 20px;line-height:28px;overflow:hidden;">
						<s:form id="uploadForm" action="base/attrEntity!uploadExcel" method="post" enctype="multipart/form-data" target="iframe" onsubmit="return checkExport();">
							<div style="display:inline;float:left;padding-right:10px">Excel文件：
								<s:file id="upload" name="upload" accept=".xls" cssClass="formInput" theme="simple" onchange="autoUpload();"/>
								<span class="Color3">(只允许上传xls,xlsx文件，且大小不能超过1M!)</span>
								<span id="uploadValue" style="color: red;"><s:fielderror/></span>
							</div>
						</s:form>
						<div style="display:inline;float:left;padding-right:10px;" align="center">
							<input type="button" class="formButton" value="导   入" onclick="saveExport()">
							<input type="button" id="cancelButton" class="formButton" onclick="closeDialog('dialog-export');" value="取   消" />
						</div>
						<iframe id="iframe" name="iframe" style="display: none"></iframe>
					</div>
				</fieldset>
			</div>
		</div>
		
	</body>
</html>