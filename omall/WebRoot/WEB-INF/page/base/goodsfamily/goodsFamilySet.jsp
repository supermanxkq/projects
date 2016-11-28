<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>" />
	<title>商品分类管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="css/zTreeStyle.css" type="text/css">	
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
	<script type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript">
	
	function goHome() {
		parent.leftFrame.location.href='<%=basePath%>system/index!firstleft';
		parent.main.location.href='<%=basePath%>system/index!right';
		}

	$(document).ready(function (){
	       var method = document.getElementById("method"); 
	        if(method.value=='checkUI'){
	        	 $("#orderSort").attr("disabled",true);
	             $("#showAdvertSign").attr("disabled",true);
	             $("#advertContentSign").attr("disabled",true);
	             $("#objectId").attr("disabled",true);
	             setInputDisabled();
	             $("#status").attr("disabled","disabled");
	             $("#defaultShow").attr("disabled","disabled"); 
	             $("#parentId").attr("disabled","disabled");
	             $("#familyWay").attr("disabled","disabled");
	             if($("#parentLevel").val()==2){
	 				$("tr[name='tr']").show();
	 				$("tr[name='tr']").find("input").attr("disabled","disabled");
	 			}
				return ;	             
	       	}else{
	       		 if($("#parentLevel").val()==2){
	 				$("tr[name='tr']").show();
	 			}else{
	 				$("tr[name='tr']").hide();
	 				$("tr[name='tr']").find("input").attr("disabled","disabled");
		 		}
		    }

	       	if($("#createFloorSign").val()==1){
				$("#orderSort").attr("disabled",false);
				$("#showAdvertSign").attr("disabled",false);
				if($("#showAdvertSign").val()==1){
					$("#advertContentSign").attr("disabled",false);
					$("#objectId").attr("disabled",false);
					$("#advertPic").attr("disabled",false);
				}
		    }
		  
	   });
    	var familyNameFlag;	
		
		/**判断分类名称*/
		var validateName = function(obj){
			 var flag1 = false;
		     var flag2 = false;
             var type = ["isNull"];
             var errorMsg = ["分类名称不能为空!"];
             //检验分类名称格式
             flag1 = showErrorMsg(obj,type,errorMsg,"errorMsg","warnMsg");
             //检验名称长度
             flag2 = checkLenMsg(obj,"长度不能大于30个汉字","errorMsg","",60)  
				
				var familyId = $("#familyId").val();
				var familyName = $("#familyName").val();
				if (!(flag1&&flag2)){
					familyNameFlag=false;
			    	return familyNameFlag;
				}
			    else{
			    	var params = {
			    			"goodsfamilyDto.familyId" : familyId,
					        "goodsfamilyDto.familyName" : familyName
					    }; 
						
					var actionUrl = "base/goodsfamily!checkName";   
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
					    success : function(data, textStatus) {   
					    	if (data.flag) {
					    		var type = ["isRate"];
             					var errorMsg = ["该分类已存在!"];
					    		showErrorMsg(obj,type,errorMsg,"errorMsg","warnMsg");
						    	familyNameFlag = false;
						    	return familyNameFlag;
							}else {
								
						    	familyNameFlag = true;
						    	return familyNameFlag;
						 	}
						}
					 });		    	
			    	}
				}
	  	
	  	function familyNameFocus(obj){
		   showWarnMsg(obj,"可填写汉字、字母以及数字!","errorMsg","warnMsg");
		}
		
	  
		
       //关闭输入法 
		function colseImeMode(obj){
			 obj.style.css = ("ime-mode","disabled");
			}
			
		function addImg(id, src) {
            var target = document.getElementById(id);
            if (target) {
                var img = document.createElement("img");
                img.src = src;
                target.appendChild(img);
            }
}	

		
		
		//树属性设置
		var setting = {
			view: {
				dblClickExpand: false,
				selectedMulti: false
			},
			callback: {
				onClick: onClick,
				onExpand: zTreeOnExpand,
				onDblClick: zTreeOnDblClick
			}
		};
		
		//树节点展开方法
		function zTreeOnExpand(event, treeId, treeNode) {
			 //alert(treeNode.id + ", " + treeNode.name);
			
			 var params = {
			 	"goodsfamilyDto.parentId" : treeNode.id
			 }; 		
			 var actionUrl = "base/goodsfamily!ajaxFamily";
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
				  success : function(data, textStatus) {
				  		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				  		var sNodes = zTree.getSelectedNodes();
				  		for(var i=0;i<data.length;i++){
				  			var newNode = {name:data[i].familyName,id:data[i].familyId,isParent:true};
				  			var parentZNode = zTree.getNodeByParam("id", data[i].parentId, null); //获取父节点
				  			var treeNode = zTree.getNodeByParam("id", data[i].familyId, null);
				  			if ((!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) || treeNode.zAsync){
				  				parentZNode = zTree.addNodes(parentZNode, newNode,true);
				  			}else{
				  				zTree.reAsyncChildNodes(parentZNode, "refresh");
				  			}		  			
				  		}
				  }
				 });		    	
		}
		
		//节点单击方法
		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			v = "";
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) {
				$("#parent").val(nodes[i].name);
				$("#parentId").val(nodes[i].id);
				$("#parentMsg").hide();
				$("#parentLevel").val(nodes[i].level);
				hideMenu();
				setCreateFloorSign(nodes[i].id);
				
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			
			
		}
		
		//双击方法
		function zTreeOnDblClick(event, treeId, treeNode) {
    		onClick(event, treeId, treeNode);
    		hideMenu();
		};
		
		//默认节点
		var zNodes =[{ name:"顶级分类", isParent:true,id:0}];

		//json串格式化
		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].familyName;
				var tId = childNodes[i].familyId;
				var parentTId = childNodes[i].parentId;
				var level = childNodes[i].nodeLevel;
				childNodes[i].isParent = true;
				var zAsync = childNodes[i].zAsync
			}
			childNode=childNodes;
			return childNodes;
		}

		//init初始化
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting,zNodes);
		});
		
		//显示div
		function showMenu() {
			var parent = $("#parent");
			var parentOffset = $("#parent").offset();
			$("#menuContent").css({left:parentOffset.left + "px", top:parentOffset.top + parent.outerHeight() + "px"}).slideDown("fast");

			$(this).bind("mousedown", onBodyDown);
		}
		
		//隐藏div
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$(this).unbind("mousedown", onBodyDown);
		}
		
		//判断鼠标点击其他地方
		function onBodyDown(event) {
			if (!(event.target.id == "parentId" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
		
		 
		 function showAdvertChange(){
		   var showAdvertSign = $("#showAdvertSign").val();
		   if(showAdvertSign == 1)
		    {
		      $("#advertContentSign").attr("disabled",false);
		      $("#objectId").attr("disabled",false);
		      $("#advertPic").attr("disabled",false);
		    }
		    else {
		      $("#advertContentSign").val(-1);
		      $("#advertContentSign").attr("disabled",true);
		      $("#advertContentSignMsg").hide();
		      $("#objectId").val("");
		      $("#objectId").attr("disabled",true);
		      $("#objectIdMsg").hide();
		      $("#advertPicMsg").hide();
		      $("#advertPic").val("");
		      $("#advertPic").attr("disabled",true);
		      
		    }
		 }
		//var createFloorSignFlag=false;//商城首页数据状态
		 var fileFlag=false;//分类图片状态
		 var parentFlag=false;//父级状态
		 var advertPicFlag=false;//广告图片状态 
		 var objectFlag=false;//广告对象状态
		 var ordreSortFlag=false;//楼层排序编号状态
		 var advertContentSignFlag=false;//广告类型状态
		//融芯宝手续费率标志
		var rswinPayFlag=false;
		/**快捷支付手续费率标志**/
		var fastPayValueFlag=false;
		/**网银支付手续费率标志**/
		var wyPayValueFlag=false;
		/**支付宝支付手续费率标志**/
		var aliPayValueFlag=false;
		/**第三方支付手续费率标志**/
		var otherPayValueFlag=false;
		 /**验证商城首页数据**/
			function createFloorChange()
			 { 
				var parentId = $("#parentId").val();
				if(parentId != 0 )
				 {
	                 alert("只有顶级分类才能选择该项!");
	                 $("#createFloorSign").val(0);
	                 return ;
				 }
				 
			    var createFloorSign = $("#createFloorSign").val();
			    if(createFloorSign == 1){ 
			       $("#showAdvertSign").attr("disabled",false);
			       $("#orderSort").attr("disabled",false);
			       
			     }
			     else
			     {
			       $("#orderSort").attr("disabled",true);
			       $("#orderSort").val("");
			       $("#showAdvertSign").val(0);
			       $("#showAdvertSign").attr("disabled",true);
			       $("#advertContentSign").val(-1);
			      $("#advertContentSign").attr("disabled",true);
			      $("#objectId").val("");
			      $("#objectId").attr("disabled",true);
			      $("#orderSortMsg").hide();
			        $("#advertContentSignMsg").hide();
			        $("#objectIdMsg").hide();
			        $("#advertPic").attr("disabled",true);
			        $("#advertPicMsg").hide();
			     }
			 }
			//验证分类图片
		function changeFile(){
				//$("#uploadValue").html("");
				var file=$("#upload").val();
				var val=$("#uploadValue");
				var extName=file.substring(file.lastIndexOf(".")+1,file.length);
				if(file.length==0){
					if($("#picPathImg").length>0){
					fileFlag=true;
					val.hide();
					return;
					}
				}
				if(file.length==0){
					fileFlag=false;
					val.html("必须上传图片");
					val.show();
					return;
				}
				if(/^(jpg|png|jpeg)$/.test(extName)){
					fileFlag=true;
					val.hide();
					return;
				}else{
					fileFlag=false;
					val.html("图片格式不符");
					val.show();
					return;
				}
			}
		 /**验证父级**/
		 function checkParent(){
			var parent=$.trim($("#parent").val());
			var parentMsg=$("#parentMsg");
			if(parent.length>0){
				parentMsg.hide();
				parentFlag=true;
			}else{
				parentMsg.show();
				parentFlag=false;
			}
		}
		/**验证广告图片**/ 
		function checkAdvertPic(){
			var file=$("#advertPic").val();
			var val=$("#advertPicMsg");
			var extName=file.substring(file.lastIndexOf(".")+1,file.length);
			if(file.length==0){
				if($("#adverPathImg").length>0){
					advertPicFlag=true;
					val.hide();
					return;
				}
			}
			if(file.length==0){
				advertPicFlag=false;
				val.html("必须上传图片");
				val.show();
				return;
			}
			if(/^(jpg|png|jpeg)$/.test(extName)){
				advertPicFlag=true;
				val.hide();
				return;
			}else{
				advertPicFlag=false;
				val.html("图片格式不符");
				val.show();
				return;
			}
		}
		/**验证广告对象编号**/
		function checkObject(){
			var type=$("#advertContentSign").val();//广告类型
			var error=$("#objectIdMsg");
			var viliade=["isNull","fullNumber"];
			var msg=["广告对象编号不能为空","必须全为数字"];
			var obj=document.getElementById("objectId");
			objectFlag=showErrorMsg(obj,viliade,msg,"objectIdMsg","objectIdMsg");
			//如果状态为true，再进行验证
			if(objectFlag){
				if(type!=-1){
					ajaxObject(type,obj.value);
				}else{
					error.html("请选择广告类型！");
					error.show();
			    }
			}
		} 
		//ajax验证广告对象是否存在
		function ajaxObject(type,objectId){
			var url="";
			//商品广告
			url="base/goodsfamily!ajaxObject";
			var param={
					"type":type,
					"id":objectId
			};
			$.ajax({
				async:false,
				type:"post",
				url:url,
				data:param,
				cache : false, 
				error : function(textStatus, errorThrown) {   
				    alert("系统ajax交互错误!");  
				},
				success : function(data, textStatus) {
					var err=$("#objectIdMsg");
					if(data==false){
						objectFlag=false;
						err.html("该广告不存在");
						err.show();
					}else{
						objectFlag=true;
						err.hide();
					}
				}
			});
		}
		/**验证广告类型**/
		function checkAdvertContentSign(){
			var val=$("#advertContentSign").val();
			var errorMsg=$("#advertContentSignMsg");
			if(val==-1){
				advertContentSignFlag=false;
				errorMsg.show();
			}else{
				advertContentSignFlag=true;
				errorMsg.hide();
			}
			checkObject();
		}
		/**验证楼层排序编号**/
		function checkOrderSort(){ 
			var viliade=["isNull","fullNumber"];
			var msg=["楼层排序编号不能为空","必须全为数字"];
			ordreSortFlag=showErrorMsg(document.getElementById("orderSort"),viliade,msg,"orderSortMsg","orderSortMsg");
		}
		/*验证融芯宝手续费率*/
      	function checkRswinPayValue(){
			  var type = ["isNull","isDoubleRate"];
              var errorMsg = ["手续费率不能为空!","手续费率格式错误!"];
              rswinPayFlag = showErrorMsg(document.getElementById("rswinPayValue"),type,errorMsg,"rswinPayValueErr","rswinPayValueErr");
        }
        /**快捷支付**/
      	function checkFastPayValue(){
      		var type = ["isNull","isDoubleRate"];
      	    var errorMsg = ["手续费率不能为空!","手续费率格式错误!"];
      	    fastPayValueFlag = showErrorMsg(document.getElementById("fastPayValue"),type,errorMsg,"fastPayValueErr","fastPayValueErr");
      	}
      	/**网银支付**/
      	function checkWyPayValue(){
      		var type = ["isNull","isDoubleRate"];
      	    var errorMsg = ["手续费率不能为空!","手续费率格式错误!"];
      	    wyPayValueFlag = showErrorMsg(document.getElementById("wyPayValue"),type,errorMsg,"wyPayValueErr","wyPayValueErr");
      	}
      	/**支付宝支付**/
      	function checkAliPayValue(){
      		var type = ["isNull","isDoubleRate"];
      	    var errorMsg = ["手续费率不能为空!","手续费率格式错误!"];
      	    aliPayValueFlag = showErrorMsg(document.getElementById("aliPayValue"),type,errorMsg,"aliPayValueErr","aliPayValueErr");
      	}
      	/**第三方支付**/
      	function checkOtherPayValue(){
      		var type = ["isNull","isDoubleRate"];
      	    var errorMsg = ["手续费率不能为空!","手续费率格式错误!"];
      	    otherPayValueFlag = showErrorMsg(document.getElementById("otherPayValue"),type,errorMsg,"otherPayValueErr","otherPayValueErr");
      	}
		/**form提交前验证**/
		 function check(){
			var form=document.getElementById("formm");
			 var createFloorSign = $("#createFloorSign").val();
			 validateName(document.getElementById("familyName"));
			 changeFile();
			 checkParent();
			 
			 //判断是否生成首页楼层
			var flag=familyNameFlag&&fileFlag&&parentFlag;
			 if(createFloorSign!=0){
				 checkOrderSort();
					flag=flag&&ordreSortFlag;
					var showAdvertSign = $("#showAdvertSign").val();
					if(showAdvertSign!=0){
						checkAdvertPic();
						checkAdvertContentSign();
						checkObject();
						flag=flag&&advertContentSignFlag&&objectFlag&&advertPicFlag;
					}
			}
			
			if($("#parentLevel").val()==2){
				checkRswinPayValue();
				checkFastPayValue();
				checkWyPayValue();
				checkAliPayValue();
				checkOtherPayValue();
				flag=flag&&rswinPayFlag&&fastPayValueFlag&&wyPayValueFlag&&aliPayValueFlag&&otherPayValueFlag;
			}
			
			if(flag){
				form.submit();
			}
		}
		function setCreateFloorSign(parentId){
			//var parentId=$("#parentId").val();
			var tr=$("tr[name='tr']");
			if($("#parentLevel").val()==2){
				tr.find("input").attr("disabled",false);
				tr.show();
			}else{
				tr.hide();
				tr.find("input").val("");
				tr.find("input").attr("disabled","disabled");
				tr.find("span").hide();   
			}
			if(parentId!=0){
				$("#createFloorSign").val(0);
				 var createFloorSign = $("#createFloorSign").val();
				    if(createFloorSign == 1){ 
				       $("#showAdvertSign").attr("disabled",false);
				       $("#orderSort").attr("disabled",false);
				       
				     }
				     else
				     {
				       $("#orderSort").attr("disabled",true);
				       $("#orderSort").val("");
				       $("#showAdvertSign").val(0);
				       $("#showAdvertSign").attr("disabled",true);
				       $("#advertContentSign").val(-1);
				      $("#advertContentSign").attr("disabled",true);
				      $("#objectId").val("");
				      $("#objectId").attr("disabled",true);
				      $("#orderSortMsg").hide();
				        $("#advertContentSignMsg").hide();
				        $("#objectIdMsg").hide();
				        
				     }
			}
			
		}   
	</script>
	<style type="text/css">
		ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:360px;overflow-y:scroll;overflow-x:auto;}
		ul.log {border: 1px solid #617775;background: #f0f6e4;width:300px;height:170px;overflow: hidden;}
		ul.log.small {height:45px;}
		ul.log li {color: #666666;list-style: none;padding-left: 10px;}
		ul.log li.dark {background-color: #E3E3E3;}
	</style> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 商品分类管理 &gt;&gt; 添加分类
	</div>
	<s:form id="formm" action="base/goodsfamily" enctype="multipart/form-data" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>
	<s:if test="#session.user_session.userLevel==0">
	
	  <fieldset style="width:100%;height=100px;">
		<legend>分类信息</legend>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
				<s:hidden name="goodsfamilyDto.familyId" id="familyId"/>
		        <th align="right" width="20%"><span class="Color5">* </span>分类名称：</th>
		        <td width="30%"><s:textfield name="goodsfamilyDto.familyName" id="familyName" cssStyle="width:150px;" onkeyup = "allowEnCnNu(this)" maxlength="60" cssClass="formInput" onfocus="familyNameFocus(this);" onblur="validateName(this);" theme="simple"/><label id="warnMsg" style="display: none;"></label> <label id="errorMsg" style="display: none;"></label></td>
				<th align="right"><span class="Color5">* </span>上级分类：</th>
		 		<td width="30%">
		 		<s:textfield name="goodsfamilyDto.pFamilyName"  readonly="true" id="parent" theme="simple" onchange="setCreateFloorSign();" onblur="checkParent(parent);" onclick="showMenu();return false;"/>
		 		<s:hidden name="goodsfamilyDto.parentId" id="parentId" theme="simple"/>
		 		<s:hidden id="parentLevel" name="goodsfamilyDto.parentLevel" />
		 		<s:hidden id="nodeLevel" name="goodsfamilyDto.nodeLevel" />
		 		<span id="parentMsg" class="errorMsg" style="display: none;">该项为必选项</span>
		 		</td>	
			</tr>
		 	<tr>		 			      	
		      	<th align="right">使用状态：</th>
		        <td><s:select name="goodsfamilyDto.status" id="status" cssStyle="width:156px;" list="#request.statusValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
		  		<th align="right"><span class="Color5">* </span>添加图片：</th>
		  		<s:if test="method=='checkUI'">
		  			<td>
		  				<s:if test="goodsfamilyDto.picPath != null && goodsfamilyDto.picPath != ''">
								<img id="picPathImg" style="width:150px;height:150px;" src="<s:property value='goodsfamilyDto.picPath'/>"/>
						</s:if>
						<s:else>
							<span class="Color3">暂无图片！</span>
						</s:else>
		  			</td>
		  		</s:if>
		  		<s:else>
		        	<td>
		        		<s:if test="goodsfamilyDto.picPath != null && goodsfamilyDto.picPath != ''">
								<img id="picPathImg" style="width:150px;height:150px;" src='<s:property value="goodsfamilyDto.picPath"/>'/>
						</s:if>
						
    					<s:file id="upload"  name="upload" accept=".jpg,.png,.jpeg" cssClass="formInput" theme="simple" onchange="changeFile();"/>
    					<span class="Color3">(只允许jpg,png,jpeg文件，且大小不能超过1M!)</span><br />
						<span id="uploadValue" style="display: none;" class="errorMsg" ></span>
		        	</td>
		        </s:else>
		  	</tr>
		  	<tr>
		  		<th align="right">默认展开：</th>
		        <td><s:select name="goodsfamilyDto.defaultShow" id="defaultShow" cssStyle="width:156px;" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
		        <th align="right">分类类型：</th>
		        <td><s:select name="goodsfamilyDto.familyWay" id="familyWay" cssStyle="width:156px;" list="#request.familySign" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
		        </td>	
		  	</tr>
				<tr name="tr" style="display: none;text-align: center;">
			        <th  colspan="4">
			 			请设置支付手续费率（格式为两位整数，小数选填，为1到4位）
			 		</th>
			   	</tr>
			   	
				<tr name="tr" style="display: none;">
			  		<th align="right">
			  			<span class="Color5">* </span>融芯宝:
			  		</th>
			  		<td>	
			  			<s:textfield name="goodsfamilyDto.rswinPayValue" id="rswinPayValue" maxlength="7" onblur="checkRswinPayValue();"/>
			  			%
			  			<span id="rswinPayValueErr" style="display: none;" class="errorMsg" ></span>
			  		</td>
					<th align="right">
						<span class="Color5">* </span>快捷支付:
					</th>
					<td>
						<s:textfield name="goodsfamilyDto.fastPayValue" id="fastPayValue" maxlength="7" onblur="checkFastPayValue();"/>
						%
						<span id="fastPayValueErr" style="display: none;" class="errorMsg" ></span>
					</td>		  		
			  	</tr>
				
				<tr name="tr" style="display: none;">
			  		<th align="right">
			  			<span class="Color5">* </span>网银费率:
			  		</th>
			  		<td>	
			  			<s:textfield name="goodsfamilyDto.wyPayValue" id="wyPayValue" maxlength="7" onblur="checkWyPayValue();"/>
			  			%
			  			<span id="wyPayValueErr" style="display: none;" class="errorMsg" ></span>
			  		</td>
					<th align="right">
						<span class="Color5">* </span>支付宝:
					</th>
					<td>
						<s:textfield name="goodsfamilyDto.aliPayValue" id="aliPayValue" maxlength="7" onblur="checkAliPayValue();"/>
						%
						<span id="aliPayValueErr" style="display: none;" class="errorMsg" ></span>
					</td>		  		
			  	</tr>
				<tr name="tr" style="display: none;">
			  		<th align="right">
			  			<span class="Color5">* </span>第三方支付:
			  		</th>
			  		<td colspan="3">	
			  			<s:textfield name="goodsfamilyDto.otherPayValue" id="otherPayValue" maxlength="7" onblur="checkOtherPayValue();"/>
			  			%
			  			<span id="otherPayValueErr" style="display: none;" class="errorMsg" ></span>
			  		</td>
						
			  	</tr>
		   	
		</table>
		</fieldset>
		<p/>
		<div style="height: 50px;"/>
	 <fieldset style="width:100%;height=100px;">
		<legend>分类&楼层信息</legend>
	   <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
		   		<th align="right" width="20%">生成商城首页楼层：</th>
		        <td width="30%"><s:select name="goodsfamilyDto.createFloorSign" id="createFloorSign" cssStyle="width:156px;" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple" onchange="createFloorChange();"/></td>
		        <th align="right" width="20%">楼层展示顺序：</th>
		        <td><s:textfield maxlength="10" name="goodsfamilyDto.orderSort" onblur="checkOrderSort();" id="orderSort" disabled="true"  cssStyle="width:156px;" theme="simple" ></s:textfield>
		      		<span id="orderSortMsg" style="display: none;"></span>  
		       </td>
		   	</tr>  
		   	<tr>
		   	    <th align="right" width="20%">展示楼层广告：</th>
		        <td><s:select name="goodsfamilyDto.showAdvertSign" id="showAdvertSign" cssStyle="width:156px;" disabled="true" list="#request.visibleValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple" onchange="showAdvertChange();"/></td>
		   		<th align="right" width="20%">选择广告类型：</th>
		        <td width="30%"><s:select onchange="checkAdvertContentSign();" name="goodsfamilyDto.advertContentSign" id="advertContentSign" cssStyle="width:156px;"  disabled="true"  list="#request.advertContents" headerKey="-1" headerValue="  请选择  " listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
		        	<span id="advertContentSignMsg" class="errorMsg" style="display: none;">请选择广告类型</span>
		        </td>
		        
		   	</tr>	
		   	<tr> 
		   	  <th align="right">广告对象编号 ：</th>
		      <td><s:textfield maxlength="10" onblur="checkObject();" name="goodsfamilyDto.objectId" id="objectId"  disabled="true"  cssStyle="width:156px;" theme="simple"/>
		      	<span id="objectIdMsg" class="errorMsg" style="display: none;"></span>
		      </td>
		      
		      <th align="right">广告图片 ：</th>
		      	<s:if test="method=='checkUI'">
		  			<td>
		  				<s:if test="goodsfamilyDto.advertPicPath != null && goodsfamilyDto.advertPicPath != ''">
								<img id="adverPathImg" style="width:150px;height:150px;" src="<s:property value='goodsfamilyDto.advertPicPath'/>"/>
						</s:if>
						<s:else>
							<span class="Color3">暂无图片！</span>
						</s:else>
		  			</td>
		  		</s:if>
		  		<s:else>
		        	<td>
		        		<s:if test="goodsfamilyDto.advertPicPath != null && goodsfamilyDto.advertPicPath != ''">
								<img id="adverPathImg" style="width:150px;height:150px;" src='<s:property value="goodsfamilyDto.advertPicPath"/>'/>
						</s:if>
		  			  <s:file name="goodsfamilyDto.advertPic" id="advertPic" size="40" disabled="true" onchange="checkAdvertPic();" ></s:file>
		      		<span class="Color3">(只允许jpg,png,jpeg文件，且大小不能超过1M!)</span><br />	
		      		 <span id="advertPicMsg" class="errorMsg" style="display: none;"></span>
		      		</td>
		  		</s:else>
		      
		      
		    
		      <%--<s:textfield name="goodsfamilyDto.advertPicPath" id="advertPicPath"  disabled="true"  cssStyle="width:156px;" theme="simple"/></td>
		   	--%>
		   	</tr>
		</table>
	 </fieldset>
	</s:if>
		
	  <div class="formTableBottom">
	 	<s:if test="#session.user_session.userLevel==0">
	 	<s:if test="method=='addUI'">
			<my:permission key='sy-1701-02' value='商品分类添加'>
		 		<input id="submitInput" type="button" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:if test="method=='checkUI'">
			<my:permission key='sy-1701-01' value='商品分类查看'>
		 	
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-1701-03' value='商品分类修改'>
		 		<input id="submitInput" type="button" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		</s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('base/goodsfamily!list')"/>
     </div>
	 </s:form>
	 <div id="menuContent" class="menuContent" style=" display:none;position: absolute;">
		<ul id="treeDemo" class="ztree" style="margin-top:0; width:156px;"></ul>
	</div>
</body>
</html>