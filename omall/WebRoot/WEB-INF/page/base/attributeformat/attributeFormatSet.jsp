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
	<title>商品属性规格管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<link href="js/jquery-easyui/easyui.css"  rel="stylesheet"  type="text/css" />	
	
	<script src="js/jquery-1.4.2.min.js"></script>
	<script src="js/jquery-easyui/jquery.easyui.min.js"></script>
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
	
	<style type="text/css">
		.attribute{display:block;padding:10px 10px;float:left;}
	</style>
    <script>
	   var groups = ${groups};
	   //保存已经选择项
	   var attArray = new Array();
	   var formatGroupArray = new Array();
	   var formatGroupArrayIndex = new Array();
	   //保存添加的行数
	   var gRowdIds = new Array();
	   var attIndex=0;
	   var groupIndex=0;
	   var formatIndex=0;
	   var existName = false;
	   var statusFlag = false;
	   var attrFlag = false;
	   var preFlag = "1";
       $(document).ready(function(){
	       var method = document.getElementById("method");
	       $("#familyId").combotree({onSelect:loadAttAndFmtByFamilyId});
	       var obj = new Object();
	       if(method.value!="addSave"){
	        	obj.id="${familyId}";
	       }else{
	       		obj.id="${familyDTO.familyId}";
	       }
	       if(obj.id!=""){
           		loadAttAndFmtByFamilyId(obj);
           		$('#familyId').combotree('setValue',obj.id);
	       }
	       if(method.value=='checkUI'){
               setInputDisabled();
               $("#backButton").attr("disabled",false);
               $("#familyId").combotree('disable');
	       }
       });

		function check(){
		  var familyId = $('#familyId').combotree('getValue');
		  var familyFlag = true;
		  $("#familyErrorMsg").hide();
		  if(familyId==""){
		  	 pubErrorShow($("#familyErrorMsg"),"请选择商品分类!");
		  	 familyFlag = false;
		  }
		  checkSelAttrSize();
		  if(!familyFlag || !attrFlag){
    	  	 alert("信息填写有误，请按提示信息重新填写!");
    	  	 return false;
    	  }
    	  return true;
		}
		
		function checkSelAttrSize(){
			var len = $(".familyAttr:checked").length;
			$("#attributeErrorMsg").hide();
			if(len==0){
				 pubErrorShow($("#attributeErrorMsg"),"请选择商品分类属性!");
		  		 attrFlag = false;
		  		 return;
			}
			if(len>5){
				 pubErrorShow($("#attributeErrorMsg"),"商品分类属性个数不能超过5个!");
		  		 attrFlag = false;
		  		 return;
			}
			attrFlag = true;
		}
		
		function getRowId(){
			return new Date().getTime();
		}
		
		function addFormatAndGroup(groupId,type){
			if(preFlag=="0" && type=="add"){
				alert("该类型不是私有属性不能扩展属性规格!");
				return;
			}
			var method = document.getElementById("method");
			var rowId = getRowId();
			gRowdIds[groupIndex] = rowId;
			var trHtml = "<tr id="+rowId+">";
			trHtml+="<th align=\"right\">";
		    var isdisabled="disabled=true";
			if(method.value!='checkUI'){
		   		isdisabled="";
				trHtml+="<a href=\"javascript:void(0)\" onclick=\"deleteFormat("+rowId+",'add')\"> x </a>";
			}
			trHtml+="<strong>扩展规格：</strong></th>";
			trHtml+="<td nowrap>";
			var d="";
			if(type=="edit"){
				d="disabled=true";
			}
			trHtml+="<select class=\"fmtGroupSel\" name=\"formatGroupRelaDTOs["+groupIndex+"].fgroupId\" id=\""+rowId+"_sel\" onclick=\"saveChangeVal("+rowId+")\" onchange=\"removeSelFormat("+rowId+")\" "+isdisabled+" "+d+">";
			trHtml+="<option value=\"\">请选择</option>";
			var list = groups.list;
			for(var i=0;i<list.length;i++){
				var id = list[i][0];
				var name = list[i][1];
				if(id==groupId){
					trHtml+="<option value=\""+id+"\" selected>"+name+"</option>";
				}else{
					trHtml+="<option value=\""+id+"\">"+name+"</option>";
				}
			}
			trHtml+="</select>";
			
		    trHtml+="    	<input id=\"selectInput\" type=\"button\" class=\"formButton\" value=\"选择规格\" "+isdisabled+" onclick=\"openFormatWin("+rowId+");\"/>";
		    trHtml+="    	<input id=\"selectInput\" type=\"button\" class=\"formButton\" value=\"添加规格分组\" "+isdisabled+" onclick=\"addFormatGroup("+rowId+");\"/>";
		    trHtml+="<div id=\""+rowId+"DIV\" style=\"display:none;margin-left:20px;width:300px;\"><input type=\"text\"  cssClass=\"formInput\" id=\""+rowId+"_txt\" maxlength=\"30\"  onblur=\"checkFormatGroupName("+rowId+")\"><input type=\"button\" value=\"确定\" class=\"formButton\" onclick=\"saveFormGroup("+rowId+")\"><input type=\"button\" value=\"<<\" class=\"formButton\" onclick=\"hideFormatGroup("+rowId+")\"></div>";
		    trHtml+="</td>";
		    trHtml+="</tr>";
		    trHtml+="<tr id="+rowId+">";
		   	trHtml+="	<th align=\"right\">&nbsp;</th>";
		    trHtml+="    <td><div id=\""+rowId+"CON\"></div></td>";
		   	trHtml+="</tr>";
			$("#attributeFormatTb tbody>tr:last").before(trHtml); 
			groupIndex++;
			return rowId;
		}
		
		function deleteFormat(rowId,type){
			if(preFlag=="0" && type=="add"){
				alert("该类型不是私有属性不能扩展属性规格!");
				return;
			}
			var  groupId = $("#"+rowId+"_sel").val();
			if(groupId){
				delete formatGroupArray[groupId];
				delete formatGroupArrayIndex[groupId];
			}
			$("#"+rowId).remove();
			$("#"+rowId).remove();
		}
		
		function addFormatGroup(rowId){
			if(preFlag=="0"){
				alert("该类型不是私有属性不能扩展属性规格!");
				return;
			}
			$("#"+rowId+"DIV").css("display","inline");
		}
		//保存规格
		function saveFormGroup(rowId){
			var url = "base/goodsattributeformat!saveFormatGroup";
			var groupName = $("#"+rowId+"_txt").val();
			if(groupName==""){
				alert("请输入规格分组名称");
				return;
			}
			checkFormatGroupName(rowId)
			if(!existName){
		   	  return;
		    }
			var params = {
               "formatGroupDTO.fgroupName":groupName
            };
            ajaxRequest(url,params,"POST",function(data){
           	 	if(data.flag){
                   	hideFormatGroup(rowId);
                   	refreshFormatGroup(rowId,groupName);
                   	removeSelFormat(rowId);
                }
           });
		}
		
		//刷新规格下拉菜单
		function refreshFormatGroup(rowId,groupName){
			var url = "base/goodsattributeformat!getAllFormatGroups";
			var params = {};
            ajaxRequest(url,params,"GET",function(data){
           	 	groups = data;
                var list = data.list;
                $(".fmtGroupSel").each(function(i){
              		//$(this).empty();
              		//$(this).append("<option value=''>前选择</option>");
              		var selId = $(this).attr("id");
           			var fgId = list[list.length-1][0];
           			var fgName = list[list.length-1][1];
           			if(selId==rowId+"_sel"){
           				$(this).append("<option value="+fgId+" selected>"+fgName+"</option>");
           			}else{
           				$(this).append("<option value="+fgId+">"+fgName+"</option>");
           			}
                });
           });
		}
		
		
		function hideFormatGroup(rowId){
			$("#"+rowId+"DIV").css("display","none");
			$("#"+rowId+"_txt").val("");
		}
		
		function openAttitudeWin(){
			if(preFlag=="0"){
				alert("该类型不是私有属性不能扩展属性规格!");
				return;
			}
      		 // 打开对话框   
			$("#dialog-attribute").dialog({
				resizable: true,
				top: 300,
				height:350,
				width:550,
				modal: true,
				close:function(){
					$("#addFmtContal").css("display","none");
					$("#addAttContal").css("display","none");
				}
			});
			getAllAttributes();    
      	}
      	//保存属性
		function saveAttribute(){
		   var url = "base/goodsattributeformat!saveAttribute";
		   var attrName = $("#attrName").val();
		   if(attrName==""){
		   	  alert("请输入属性名称");
		   	  return;
		   }
		  
		   var isEn=$("#isEn").val();
		   if(isEn==""){
		   	 alert("请选择是否为枚举");
		   	 return;
		   }

		   var showlable=$("#showlable").val();
		   if(showlable==""){
		   	 alert("请输入显示名称");
		   	 return;
		   }

		   checkAttributeName();
		   checkDisplayName();
		   if(!existName){
		   	  return;
		   }
		   
           var params = {
               "attributeDTO.attrName":attrName,
               "attributeDTO.showlable":showlable,
               "attributeDTO.isEn":isEn
           };
           ajaxRequest(url,params,"POST",function(data){
           	 	if(data.flag){
                	//alert("添加属性成功！");
                	getAllAttributes();
                	$("#isEn").val("");
                	$("#attrName").val("");
                	$("#showlable").val("");
                }
           });
		} 
		
		function getAllAttributes(){
		   var url = "base/goodsattributeformat!getAllAttributes";
           var params = {};
           ajaxRequest(url,params,"GET",function(data){
           	 	var list = data.list;
	        	if (list.length>0){
                   	var html="";
	        		for(var i=0;i<list.length;i++) {
	        			var attId = list[i][0];
	        			var attName = list[i][1];
	        			var cked="";
	        			for(var j=0;j<attArray.length;j++){
	        				if(attId == attArray[j]){
	        					cked="checked";
	        				}
	        			}
	        			html+="<div class=\"attribute\">"+attName+"<input type=\"checkbox\" name=\"attribute\" value=\""+attId+"_"+attName+"\" "+cked+"></div>";
	            	}
	            	$("#attributeContainer").html(html);
	        	}
           });
		}
		function addAtt(){
			$("#addAttContal").css("display","block");
		}
		
		function selectedAll(sel,boxName){
			 if($("#"+sel).is(':checked')){
			 	 $("input:checkbox[name='"+boxName+"']").attr("checked","true");//全选
			 }else{
			 	 $("input:checkbox[name='"+boxName+"']").removeAttr("checked");//取消全选
			 }
		}
		
		function returnAttrSelected(){
		   var html=$("#formAttributeContainer").html();
		   $("input:checkbox[name=attribute]:checked").each(function(i){
		   		var value = $(this).val();
		   		var attId = value.split("_")[0];
		   		var attName = value.split("_")[1];
		   		var exist=false;
		   		for(var j=0;j<attArray.length;j++){
      				if(attId == attArray[j]){
      					exist = true;
      					break;
      				}
      			}
      			if(!exist){
			   		html+="<div class=\"attribute\">"+attName+"<input type=\"checkbox\" class=\"familyAttr\" name=\"familyAttrRelaDTOs["+attIndex+"].attrId\" value=\""+attId+"\" checked=true></div>";
			   		attArray[attIndex]=attId;
			   		attIndex++;
		   		}
	       });
	       $("#formAttributeContainer").html(html);
	       $("#dialog-attribute").dialog("close");
		   $("#checkedAttrAll").removeAttr("checked");
		   $("#addAttContal").css("display","none");
		   attCheckLenCheck();
		   registerAttrClick();
		   
		}
		
		function attCheckLenCheck(){
		   var len = $(".familyAttr:checked").length;
		   if(len==0){
				 pubErrorShow($("#attributeErrorMsg"),"请选择商品分类属性!");
		   }else if(len>5){
				 pubErrorShow($("#attributeErrorMsg"),"商品分类属性个数不能超过5个!");
		   }else{
		   		$("#attributeErrorMsg").hide();
		   }
		}
		
		function openFormatWin(id){
			if(preFlag=="0"){
				alert("该类型不是私有属性不能扩展属性规格!");
				return;
			}
			var value="0";
			var currentRowId="formBaseFormatContainer";
			if(id!=0){
			   value = $("#"+id+"_sel").val();
			   currentRowId = id;
			}
			if(value==""){
				alert("请选择规格分组!")
				return;
			}
			 // 打开对话框   
			$("#dialog-Format").dialog({
				resizable: true,
				top: 300,
				height:350,
				width:550,
				modal: true,
				close:function(){
					$("#addFmtContal").css("display","none");
					$("#addAttContal").css("display","none");
				}
			});
			$("#fgroupId").val(value);
			$("#currentRowId").val(currentRowId);
			getFormatsByGroup();
		}
		
		function addFormat(){
			$("#addFmtContal").css("display","block");
		}
		
		function saveFormat(){
		   var url = "base/goodsattributeformat!saveFormat";
		   var formatName = $("#formatName").val();
		   var fgroupId = $("#fgroupId").val();
		   if(formatName==""){
		   	  alert("请输入规格名称");
		   	  return;
		   }
		   checkFormatName();
		   
		   if(!existName){
		   	  return;
		   }
           var params = {
               "formatDTO.formatName":formatName,
               "formatGroupRelaDTO.fgroupId":fgroupId
           };
           ajaxRequest(url,params,"POST",function(data){
          	 	if(data.flag){
                	getFormatsByGroup();
                	$("#formatName").val("");
                }
           });
		}
		
		function getFormatsByGroup(){
		   var url = "base/goodsattributeformat!getFormatsByGroup";
		   var fgroupId = $("#fgroupId").val();
           var params = {
           		"formatGroupRelaDTO.fgroupId":fgroupId
           };
           ajaxRequest(url,params,"GET",function(data){
          	    var list = data.list;
                var html="";
        		for(var i=0;i<list.length;i++) {
        			var formatId = list[i][0];
        			var formatName = list[i][1];
        			var cked="";
        			if(formatGroupArray[fgroupId]){
	        			for(var j=0;j<formatGroupArray[fgroupId].length;j++){
	        				if(formatId == formatGroupArray[fgroupId][j]){
	        					cked="checked";
	        				}
	        			}
        			}
        			html+="<div class=\"attribute\">"+formatName+"<input type=\"checkbox\" name=\"format\" value=\""+formatId+"_"+formatName+"\" "+cked+"></div>";
            	}
            	$("#formatContainer").html(html);
           });
		}
		//返回规格选择
		function returnFormatSelected(){
		   var groupId = $("#fgroupId").val();
		   if(!formatGroupArray[groupId]){
		   		formatGroupArray[groupId] = new Array();
		   		formatGroupArrayIndex[groupId]=0;
		   }
		   var currentRowId = $("#currentRowId").val();
		   var html=$("#"+currentRowId+"CON").html();
		   $("input:checkbox[name=format]:checked'").each(function(i){
		   		var value = $(this).val();
		   		
		   		var formatId = value.split("_")[0];
		   		var formatName = value.split("_")[1];
		   		var exist=false;
		   		for(var j=0;j<formatGroupArray[groupId].length;j++){
      				if(formatId == formatGroupArray[groupId][j]){
      					exist = true;
      					break;
      				}
      			}
      			if(!exist){
			   		html+="<div class=\"attribute\">"+formatName+"<input type=\"checkbox\" name=\"familyGroupRelaDTOs["+formatIndex+"].formatId\" value=\""+formatId+"\" checked=true></div>";
			   		formatGroupArray[groupId][formatGroupArrayIndex[groupId]]=formatId;
			   		formatGroupArrayIndex[groupId]++;
			   		formatIndex++;
		   		}
	       });
	       $("#"+currentRowId+"CON").html(html);
	       $("#dialog-Format").dialog("close");
	       $("#addFmtContal").css("display","none");
	       $("#checkedFmtAll").removeAttr("checked");
		}
		var bakSelvalue;
		function saveChangeVal(rowId){
			bakSelvalue = $("#"+rowId+"_sel").val();
		}  	
		//改变分组清空之前的选择
		function removeSelFormat(rowId){
			var groupId =  $("#"+rowId+"_sel").val();
			$(".fmtGroupSel").each(function(i){
				var obj = $(this);
				if(obj.attr("id")!=+rowId+"_sel" && groupId==obj.val()){
					alert("已存在相同的规格分组请选择其他!");
					$("#"+rowId+"_sel").val("");
					return;
				}
			});
			$("#"+rowId+"CON").html("");
			if(bakSelvalue){
				delete formatGroupArray[bakSelvalue];
				delete formatGroupArrayIndex[bakSelvalue];
			}
			
		}
		
		function loadAttAndFmtByFamilyId(node){
		   var method = document.getElementById("method").value;
		   var isdisabled="";
		   if(method=='checkUI'){
		   		isdisabled="disabled=true";
		   }
		   var familyId = node.id;
		   var url = "base/goodsattributeformat!getAssociationByFamilyId";
		   var fgroupId = $("#fgroupId").val();
           var params = {
           		"familyDTO.familyId":familyId,
           		"flag":method
           };
           ajaxRequest(url,params,"GET",function(data){
          	    attArray = new Array();
          		formatGroupArray = new Array();
          		formatGroupArrayIndex = new Array();
          		attIndex = 0;
          		formatIndex = 0;
          		var attributesList = data["attributes"];
          		
          		var status = data["status"];
          		preFlag = data["preFlag"];
          		$("#status").val(status);
          		var html="";
          		if(attributesList){
	           		for(var i=0;i<attributesList.length;i++){
	           			var attId = attributesList[i]["attrId"];
	           			var attName = attributesList[i]["attrName"];
	           			var isParent = attributesList[i]["isParent"];
	           			var readOnlyStr="";
	           			if(isParent=="true"){
	           				readOnlyStr="onclick=\"return false\"";
	           			}
		            	html+="<div class=\"attribute\">"+attName+"<input type=\"checkbox\" class=\"familyAttr\" name=\"familyAttrRelaDTOs["+attIndex+"].attrId\" value=\""+attId+"\" checked=true "+isdisabled+" "+readOnlyStr+"></div>";
				   		attArray[attIndex]=attId;
				   		attIndex++;
				    }
			    }
		   		$("#formAttributeContainer").html(html);
		   		
		   		registerAttrClick();
		   		//删除已经存在的拓展规格
		   		for(var i=0;i<gRowdIds.length;i++){
		   			deleteFormat(gRowdIds[i],"edit");
		   		}
		   		//清除基本规格
		   		removeSelFormat("formBaseFormatContainer");
		   		//初始化
		   		gRowdIds = new Array();
		   		groupIndex = 0;
		   		
		   		var groupIds = data["groupIds"];
		   		if(!groupIds){
		   			return;
		   		}
		   		//基本规格
		   		for(var i=0;i<groupIds.length;i++){
		   			var groupId = groupIds[i];
		   			html="";
		   			var currentRowId = "formBaseFormatContainer";
		   			if(groupId!="0"){
		   				var currentRowId = addFormatAndGroup(groupId,"edit");
		   			}
		   			if(!formatGroupArray[groupId]){
		   				formatGroupArray[groupId] = new Array();
		   				formatGroupArrayIndex[groupId]=0;
		   			}
	   				var item = data[groupId];
	   				for(var j=0;j<item.length;j++){
	   					var formatName = item[j]["formatName"];
	   					var formatId = item[j]["formatId"];
	   					var isParent = item[j]["isParent"];
	   					var readOnlyStr="";
	           			if(isParent=="true"){
	           				readOnlyStr="onclick=\"return false\"";
	           			}
		   				html+="<div class=\"attribute\">"+formatName+"<input type=\"checkbox\" name=\"familyGroupRelaDTOs["+formatIndex+"].formatId\" value=\""+formatId+"\" checked=true "+isdisabled+" "+readOnlyStr+"></div>";
				   		formatGroupArray[groupId][formatGroupArrayIndex[groupId]]=formatId;
				   		formatGroupArrayIndex[groupId]++;
				   		formatIndex++;
	   				}
	   				$("#"+currentRowId+"CON").html(html);
		   		}
           });
		}
		function closeDialog(dialogId){
			$("#"+dialogId).dialog("close");
			$("#addFmtContal").css("display","none");
			$("#addAttContal").css("display","none");
		}
		
		function checkAttributeName(){
		   var attrName = $("#attrName").val();
		   if(attrName==""){
		   	  return;
		   }
		   var url = "base/goodsattributeformat!checkAttributeName";
           var params = {
               "attributeDTO.attrName":attrName
           };
           ajaxRequest(url,params,"POST",function(data){
           	 	if(data.flag){
                    alert("该属性名称已存在!");
                    existName = false;
                 }else{
                    existName = true;
                }
           });
		}

		function checkDisplayName(){
			   var showlable = $("#showlable").val();
			   if(showlable==""){
			   	  return;
			   }
			   var url = "base/goodsattributeformat!checkDisplayName";
	           var params = {
	               "attributeDTO.showlable":showlable
	           };
	           ajaxRequest(url,params,"POST",function(data){
	           	 	if(data.flag){
	                    alert("该属性显示名称已存在!");
	                    existName = false;
	                 }else{
	                    existName = true;
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
		
		function checkFormatName(){
		   var formatName = $("#formatName").val();
		   var fgroupId = $("#fgroupId").val();
		   if(formatName==""){
		   	  return;
		   }
		   var url = "base/goodsattributeformat!checkFormatName";
           var params = {
               "formatDTO.formatName":formatName,
               "formatGroupDTO.fgroupId":fgroupId
           };
           ajaxRequest(url,params,"POST",function(data){
           	 	if(data.flag){
                    alert("该规格名称已存在!");
                    existName= false;
                 }else{
                    existName= true;
                }
           });
		}
		
		
		function checkFormatGroupName(rowId){
		   var url = "base/goodsattributeformat!checkFormatGroupName";
			var groupName = $("#"+rowId+"_txt").val();
			if(groupName==""){
				return;
			}
			var params = {
               "formatGroupDTO.fgroupName":groupName
            };
            ajaxRequest(url,params,"POST",function(data){
           	 	if(data.flag){
                    alert("该规格分组名称已存在!");
                    existName= false;
                }else{
                    existName= true;
                }
           });
		}
		//给所有的属性checkbox注册点击事件
		function registerAttrClick(){
			$(".familyAttr").each(function(){
				$(this).click(function(e){
					attCheckLenCheck();
					e.stopPropagation();
				});
			});
		}
    </script>
</head>
<body>
    
	<div class="Position"> 
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 商品管理 &gt;&gt; 商品属性规格设置 
	</div>
	<s:form action="/base/goodsattributeformat" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
		<s:hidden name="method" id="method"></s:hidden>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable" id="attributeFormatTb">
			<tbody>
			<tr>
		      	<th align="right" width="352px"><span class="Color5">* </span><strong>商品分类：</strong></th>
		        <td>
		        <select  class="easyui-combotree" id="familyId" name="familyDTO.familyId" data-options="url:'base/goodsattributeformat!getTypeListAjax',method:'get'" style="width:200px;"></select>
		       <label id="familyErrorMsg" style="display: none;"></label><br></td>
		   	</tr>
		   	<tr>
		      	<th align="right"><strong>状态：</strong></th>
		        <td><s:select id="status" name="familyDTO.status" list="#request.status" headerKey="-1" headerValue="请选择" listKey="key" listValue="value"  cssClass="formSelect" style="width:200px;" disabled="true"/></td>
		   	</tr>
		   	<tr>
		      	<th align="right"><strong>商品属性：</strong></th>
		        <td><input id="selectInput" type="button" class="formButton" value="选择属性" onclick="openAttitudeWin();"/>
		        <label id="attributeErrorMsg" style="display: none;"></label><br>
		        </td>
		   	</tr>
		   	<tr>
		   		<th align="right">&nbsp;</th>
		        <td>
		        	<div id="formAttributeContainer">
		        		
					</div>
		        </td>
		   	</tr>
		   	<tr>
		      	<th align="right"><strong>基本规格：</strong></th>
		        <td>
		        	<input id="selectInput" type="button" class="formButton" value="选择规格" onclick="openFormatWin(0);"/>
		        </td>
		   	</tr>
		   		<tr>
		   		<th align="right">&nbsp;</th>
		        <td>
		        	<div id="formBaseFormatContainerCON">
		        		
					</div>
		        </td>
		   	</tr>
		   		<tr>
		      	<th align="right"><strong></strong></th>
		        <td>
		        	<input id="selectInput" type="button" class="formButton" value="扩展规格" onclick="addFormatAndGroup('','add');"/>
		        </td>
		   	</tr>
		   	</tbody>
	 	</table>
	 	<div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-1704-02' value='商品属性规格添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:elseif test="method=='editSave'">
		    <my:permission key='sy-1704-03' value='商品属性规格修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:elseif>
		<s:else>
		</s:else>
		<input type="button" id="backButton" class="formButton" value="返 回" onclick="go('base/goodsattributeformat!list')"/>
		</div>
	 	</s:form>
	 	
	 	<!-- 属性对话框 -->
	 	<div id="dialog-attribute" style="display:none;margin:0 0;padding:0 0 0 0;" title="选择商品属性">
			<table class="searchTable" cellpadding="0" cellspacing="0" width="100%">
				<tr>
				    <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="checkedAttrAll" id="checkedAttrAll" onclick="selectedAll('checkedAttrAll','attribute')">全选</td>
		        	<td width="220px">&nbsp;</td>
		        	<td align="right">
		        		<input type="button" class="formButton" onclick="returnAttrSelected();" value="确定选择" />
		        		<input type="button" class="formButton" onclick="closeDialog('dialog-attribute');" value="取   消" />
		        		<input type="button" class="formButton" onclick="addAtt();" value="补充属性" />
		        	</td>
				</tr>
			</table>
			<div style="display:block;width:96%;padding-top:15px;padding-left:10px;">
				<fieldset>
					<legend>选择属性</legend>
					<div id="attributeContainer" style="padding:10px 10px;height:100px;overflow:auto">
					</div>
				</fieldset>
				
				<fieldset style="margin-top:20px;display:none" id="addAttContal">
					<legend>添加商品属性</legend>
					<div style="display:block;margin:10px 20px;line-height:28px;overflow:hidden;">
						<div style="display:inline;float:left;padding-right:10px">属性名称：<input type="text" style="width:150px" cssClass="formInput" name="attrName" id="attrName" maxlength="30"  onblur="checkAttributeName()"></div>
																	
						<div style="display:inline;float:left;padding-right:20px">是否为枚举：
							<select name="isEn" id="isEn" style="width:150px">
								<option value="">请选择</option>
								<option value="0">是</option>
								<option value="1">不是</option>
								<option value="2">一半是</option>
							</select>
						</div>
						<div style="display:inline;float:left;padding-right:10px">显示名称：<input type="text" style="width:150px" cssClass="formInput" name="showlable" id="showlable" maxlength="30"  onblur="checkDisplayName()"></div>	
						<div style="display:inline;float:left;padding-right:10px"><input type="button" class="formButton" value="确定补充" onclick="saveAttribute()"></div>
					</div>
				</fieldset>
			</div>
		</div>
		
		<!-- 规格对话框 -->
		<div id="dialog-Format" style="display:none;margin:0 0;padding:0 0 0 0;" title="选择商品规格">
			<table class="searchTable" cellpadding="0" cellspacing="0" width="100%">
				<tr>
				    <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="checkedFmtAll" id="checkedFmtAll" onclick="selectedAll('checkedFmtAll','format')">全选</td>
					<td width="220px">&nbsp;</td>
		        	<td>
		        		<input type="button" class="formButton" onclick="returnFormatSelected();" value="确定选择" />
		        		<input type="button" class="formButton" onclick="closeDialog('dialog-Format');" value="取   消" />
		        		<input type="button" class="formButton" onclick="addFormat();" value="补充规格" />
		        	</td>
				</tr>
			</table>
			<div style="display:block;width:96%;padding-top:15px;padding-left:10px;">
				<fieldset>
					<legend>选择规格</legend>
					<div id="formatContainer" style="padding:10px 10px;height:100px;overflow:auto">
					</div>
				</fieldset>
				
				<fieldset style="margin-top:20px;display:none" id="addFmtContal">
					<legend>添加商品规格</legend>
					<div style="display:block;margin:10px 20px;line-height:28px;overflow:hidden;">
						<input type="hidden" name="fgroupId" id="fgroupId">
						<input type="hidden" name="currentRowId" id="currentRowId">
						<div style="display:inline;float:left;padding-right:10px">规格名称：<input type="text" cssClass="formInput" name="formatName" id="formatName" maxlength="30" onblur="checkFormatName()"></div>
						<div style="display:inline"><input type="button" class="formButton" value="确定补充" onclick="saveFormat()"></div>
					</div>
				</fieldset>
			</div>
		</div>
	 </body> 
</html>