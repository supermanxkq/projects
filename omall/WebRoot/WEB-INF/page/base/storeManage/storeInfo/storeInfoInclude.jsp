<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!--在商户管理中添加了店铺管理的tab页，为避免原始的页面不受影响，此页面为tab页专用  -->
<!DOCTYPE HTML>
<html>
	<head>
		<title>店铺基本设置</title>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
			type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.4.2.min.js"></script>
		<script src="js/jquery/jquery.ui.core.js"></script>
		<script src="js/pubValiPattern.js"></script>
		<script src="js/pubValidate.js"></script>
		<link href="js/jquery/css/jquery.ui.all.css" rel="stylesheet"
			type="text/css" />
		<script src="js/jquery/jquery.ui.widget.js"></script>
		<script src="js/jquery/jquery.ui.mouse.js"></script>
		<script src="js/jquery/jquery.ui.button.js"></script>
		<script src="js/jquery/jquery.ui.draggable.js"></script>
		<script src="js/jquery/jquery.ui.position.js"></script>
		<script src="js/jquery/jquery.ui.autocomplete.js"></script>
		<script src="js/jquery/jquery.ui.resizable.js"></script>
		<script src="js/jquery/jquery.ui.dialog.js"></script>
		<script src="js/jquery/jquery.ui.tabs.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script type="text/javascript">
		/**店铺名称标志*/
		var storeNameFlag=false;
		/**联系人名称标志*/
		var contPersonFlag=false;
		/**手机号码标志*/
		var teleNoFlag2=false;
		/**店铺地址标志*/
		var  storeAddressFlag=false;
		/**开户银行标志*/
		var  bankFlag=false;
		/**开户银行帐号标志*/
		var  bankAccNoFlag=false;
		/**开户名称*/
		var  bankNameFlag=false;
		/**营业执照标志*/
		var licenseFlag=false;
		/**结算周期标志*/
		var  settPeriodFlag=false;
		/**店铺简介标志*/
		var storeIntroductFlag=false;
		/**上传图片标志*/
		var  imageFileFlag=false;
		/**商品分类标志*/
		var goodsFamilyFlag=false;
		var fileValueFlag=false;
	/**验证控件值*/
	function checkValue(obj){
		switch(obj.id){
		case "storeName":
			var storeName=$.trim($("#storeName").val());
			if(storeName.length==0){
					$("#storeNameMsg").html("请输入店铺名称！");
					$("#storeNameMsg").show();
					storeNameFlag=false;
				}else{
				$("#storeNameMsg").hide();
				storeNameFlag=true;
			};break;
		case "contPerson":
			var contPerson=$.trim($("#contPerson").val());
			if(contPerson.length==0){
					$("#contPersonMsg").html("请输入联系人！");
					$("#contPersonMsg").show();
					contPersonFlag=false;
				}else{
				$("#contPersonMsg").hide();
				contPersonFlag=true;
			};break;
		case "teleNo2":
			var teleNo=$.trim($("#teleNo2").val());
			if(teleNo.length!=0){
			$("#teleNoMsg2").hide();
			teleNoFlag2=validateRules.isMobile(teleNo);
			if(!teleNoFlag2){
					$("#teleNoMsg2").html("手机号码格式错误！");
					$("#teleNoMsg2").show();
			}else{
				$("#teleNoMsg2").hide();
				}
			}else{
				$("#teleNoMsg2").html("请输入手机号码！");
				$("#teleNoMsg2").show();
				teleNoFlag2=false;
			}
			break;
<%--		case "storeAddress":--%>
<%--			var storeAddress=$.trim($("#storeAddress").val());--%>
<%--			if(storeAddress.length==0){--%>
<%--					$("#storeAddressMsg").html("请输入地址！");--%>
<%--					$("#storeAddressMsg").show();--%>
<%--					storeAddressFlag=false;--%>
<%--				}else{--%>
<%--				$("#storeAddressMsg").hide();--%>
<%--				storeAddressFlag=true;--%>
<%--			};break;--%>
		case "license":
			var license=$.trim($("#license").val());
			if(license.length==0){
					$("#licenseMsg").html("请输入营业执照！");
					$("#licenseMsg").show();
					licenseFlag=false;
				}else{
				$("#licenseMsg").hide();
				licenseFlag=true;
			};break;
		case "storeIntroduct":
			var storeIntroduct=$.trim($("#storeIntroduct").val());
			if(storeIntroduct.length==0){
				$("#storeIntroductMsg").html("请输入店铺简介！");
				$("#storeIntroductMsg").show();
				storeIntroductFlag=false;
				break;
			}else{
			$("#storeIntroductMsg").hide();
			storeIntroductFlag=true;
		};
			if(storeIntroduct.length>255&&storeIntroduct.length!=0){
					$("#storeIntroductMsg").html("输入内容过多，最多255字！");
					$("#storeIntroductMsg").show();
					storeIntroductFlag=false;
				}else{
				$("#storeIntroductMsg").hide();
				storeIntroductFlag=true;
			};
			break;
		case "file":
			var imageFileValue=$.trim($("#file").val());
			var method=$("#method").val();
			if(imageFileValue.length==0&&method=="addSave"){
				$("#fileValueMsg").html("请选择要上传的图片！");
				$("#fileValueMsg").show();
				fileValueFlag=false;
			}else{
				var extName=imageFileValue.substring(imageFileValue.lastIndexOf(".")+1,imageFileValue.length);
				if(/^(jpg|png|jpeg)$/.test(extName)){
					$("#fileValueMsg").hide();
					fileValueFlag= true;
				}else{
					$("#fileValueMsg").html("图片格式为PNG,JPG,JPEG");
					$("#fileValueMsg").show();
					fileValueFlag= false;
				}
			}
			break;
		case "checkedTypes":
			var goodsFamily=$("#checkedTypes").html();
			if(goodsFamily.length==0){
				$("#goodsFamilyError").html("请选择商品分类");
				$("#goodsFamilyError").show();
				goodsFamilyFlag=false;
				}else{
					$("#goodsFamilyError").hide();
					goodsFamilyFlag=true;
				}
			break;
		case "storeAddress":
			var storeAddress=$.trim($("#storeAddress").val());
			if(storeAddress.length==0){
				$("#storeAddressMsg").html("请输入店铺地址！");
				$("#storeAddressMsg").show();
				storeAddressFlag=false;
				break;
			}else{
			$("#storeAddressMsg").hide();
			storeAddressFlag=true;
		};
			if(storeAddress.length>100&&storeAddress.length!=0){
					$("#storeAddressMsg").html("输入内容过多，最多100字！");
					$("#storeAddressMsg").show();
					storeAddressFlag=false;
				}else{
				$("#storeAddressMsg").hide();
				storeAddressFlag=true;
			};
			break;
		}
  }


	/**验证内容，提交赋值*/
  function getContentValue(){
	var editorValue=$("#container").val();
	  if(editorValue.length==0){
				alert("请输入店铺介绍！");
				getContentValueFlag=false;
		  }else{
         $("#storeDesc").val(editorValue);
         getContentValueFlag=true;
		}
	  }

	
	/**提交时验证的方法*/
	function check3(){
		var form=document.getElementById("storeInfoForm");
		/**验证所有*/
		var storeName=document.getElementById("storeName");
		var contPerson=document.getElementById("contPerson");
		var teleNo=document.getElementById("teleNo2");
		var storeAddress=document.getElementById("storeAddress");
		var license=document.getElementById("license");
		var storeIntroduct=document.getElementById("storeIntroduct");
		var imageFileValue=document.getElementById("file");
		var checkedTypes=document.getElementById("checkedTypes");
		getContentValue();
		checkValue(imageFileValue);
		checkValue(storeName);
		checkValue(contPerson);
		checkValue(teleNo);
		checkValue(storeAddress);
		checkValue(license);
		checkValue(storeIntroduct);
		checkValue(checkedTypes);
		if($.trim($("#sign").attr("src")).length>0){
			fileValueFlag=true;
		}
		if(goodsFamilyFlag&&storeNameFlag&&getContentValueFlag&&contPersonFlag&&teleNoFlag2&&storeAddressFlag&&licenseFlag&&storeIntroductFlag&&fileValueFlag){
			form.submit();
		}else{
			alert("页面信息填写有误!");
		}
	}


	/**设置显示图片信息**/
    function setEditorStatus(){
    	  setCityByProvinceId();
    	 var method = document.getElementById("method"); 
	        if(method.value=='editSave'){
	      				$("#imgcontd").show();
	      			      $("#container").val(($("#storeDesc").val()));
	            }else{
	            	$("#imgcontd").hide();
		            }
            /**加载已选择商品分类*/
	        findCheckedTypes();
       }

    /**根据省查找省下面的城市*/
	function setCityByProvinceId(){
		var Fcode = $.trim($("#Fname").val());
			/**json数据传输*/
			var params = {
				"areaDicDTO.Fcode" : Fcode
		    }; 
		    var actionUrl = "base/storeInfo!findCityByProvinceId";
		    $.ajax({   
		        url : actionUrl,   
		        data : params,   
		        dataType : "json",
		        cache : false, 
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		        },
		        success : function(data, textStatus) { 
					var option="<select name='storeInfoDTO.cityId' id='city'>";
		        	 $("#div1").empty();
		        	  for(var i=0;  i<data.obj.length; i++){
		        		  option =option+"<option value='"+data.obj[i].key+"'>"+data.obj[i].value+"</option>";
			        	  }
		        	  option = option+"</select>";
		              $("#div1").html(option);
		              /**设置默认值*/
		              if($("#cityId").val()!=''){
		              $("#city").val($("#cityId").val());
		              }else{
		            	  $("#city option[index='0']").remove();
				       }
		        }
		    });
	}
	  /**加载商品分类*/
	function chooseTypes(){
		    var actionUrl = "base/storeInfo!chooseTypes";
		    $.ajax({   
		    	 async:false,
		        url : actionUrl,   
		        dataType : "json",
		        cache : false, 
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		        },
		        success : function(data, textStatus) { 
		        	  $("#types").empty();
		        	  for(var i=0;  i<data.obj.length; i++){
		        		  var option="<input type='checkbox' name='id' style='margin-left:30px;'onclick='deleteById(this.id)' value='"+data.obj[i].value+"'   id='"+data.obj[i].key+"'>";
							option+=data.obj[i].value;
		        		  option+="</input>";
		        		  if((i+1)%7==0){
                       	       option+="<br/>";
				        	}
		        		  $("#types").append(option);
			        	  }
		        }
		    });
	}
	//查找所有分类信息，并显示到对话框中
	function editContent(){
		/**查找所有的分类*/
		chooseTypes();
		/**查找已选中的分类*/
		findCheckedTypes();
		$("#dialog-confirm").css("overflow","hidden");
		$("#dialog-confirm").dialog({
			resizable: false,
			top: 370,
			height:330,
			width:624,
			modal: true,
			buttons:{
					'取消':function(){
					$("#dialog-confirm").css("overflow","auto");
					$(this).dialog("close");
				},
				'确认':function(){
					/**设置选中的复选框*/
					insertFind();
				}
			}
		});
	}
	//全选
	function checkAll(){
		var status=document.getElementById("checkAll").checked;
		var typeList=document.getElementsByName("id");
		if(status==true){
			for(var i=0;i<typeList.length;i++){
				typeList[i].checked=true;
			}
		}else{
			for(var i=0;i<typeList.length;i++){
				typeList[i].checked=false;
			}
		}
		
	}
	 /**向商品属性分类表和店铺基本设置表关联表GoodsFamily中插入数据*/
	function insertFind(){
		deleteAll();
		/**获取选中的类型*/
		var aa = $('[name=id]');
		var ids = '';
	 	for(var i=0;i<aa.length;i++){
	        if(aa[i].checked){                             
	        	if(ids == '') 
	        	 	ids = aa[i].id;
	        	else
	         		ids = ids + "," + aa[i].id;
	       	}
	  	}
	  	if(ids == ''){
	   		alert("请选择商品分类");
	   		return ;
	  	}
		/**获取店铺的编号*/
		var  storeId=$("#storeId").val();
		/**插入到数据库中*/
		/**json数据传输*/
			var params = {
				"storeGtypeRelDTO.familiesIds" : ids,
				"storeGtypeRelDTO.storeId" : storeId
		    }; 
		    var actionUrl = "base/storeInfo!insertRel";
		    $.ajax({   
			    async:false,
		        url : actionUrl,   
		        data : params,   
		        dataType : "html",
		        cache : false, 
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		        	alert("error");
		        },
		        success : function(data, textStatus) { 
			        /**隐藏错误提示信息*/
		        	$("#goodsFamilyError").hide();
					findCheckedTypes();
					$("#dialog-confirm").css("overflow","auto");
					$("#dialog-confirm").dialog("close");
					$("#checkAll").attr("checked",false);
		        }
		    });
		/**从数据库中获取/删除已经选中的商品分类*/
		    /**删除全部*/
			function deleteAll(){
				    var actionUrl = "base/storeInfo!deleteAll";
				    $.ajax({   
				    	 async:false,
				        url : actionUrl,   
				        data : params,   
				        dataType : "html",
				        cache : false, 
				        type : "POST",
				        error : function(textStatus, errorThrown) {   
				        	alert("delerror");
				        },
				        success : function(data, textStatus) { 
				        }
				    });
			}
	}
	/**查询所有选中的商品类型*/
	function findCheckedTypes(){
		  var actionUrl = "base/storeInfo!findCheckedTypes";
		    $.ajax({   
		    	async:false,
		        url : actionUrl,   
		        dataType : "json",
		        cache : false, 
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		        },
		        success : function(data, textStatus) { 
		       	  $("#checkedTypes").empty();
		       	  /**查出所有选中的商品类型*/
	        	  for(var i=0;  i<data.obj.length; i++){
	        		  $("input:checkbox[value="+data.obj[i].value+"]").attr('checked','true');
		        	  /**在页面中显示已经选择的商品类型，disabled*/
	        		  var option="&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox'  disabled='disabled' checked='checked' id="+data.obj[i].key+">";
						option+=data.obj[i].value;
	        		  option+="</input>&nbsp;&nbsp;&nbsp;&nbsp;";
	        		  $("#checkedTypes").append(option);
		        	  }
		        }
		    });
	}

	function setImg(){
		var src=$("#file").val();
	}
	
	/**根据编号进行删除*/
	function deleteById(id){
		var familyId=id;
		/**json数据传输*/
		var params = {
			"storeGtypeRelDTO.familyId" : familyId
	    }; 
	    var actionUrl = "base/storeInfo!deleteById";
	    $.ajax({   
	    	 async:false,
	        url : actionUrl,   
	        data : params,   
	        dataType : "json",
	        cache : false, 
	        type : "POST",
	        error : function(textStatus, errorThrown) {   
	        	alert("error");
	        },
	        success : function(data, textStatus) { 
				findCheckedTypes();
	        }
	    });
		}
	
	</script>
		<style type="text/css">
.clear {
	clear: both;
}
</style>
	</head>
	<body onload="setEditorStatus();">
		<s:hidden name="storeInfoDTO.cityId" id="cityId" />
		<s:form id="storeInfoForm" action="base/storeInfo" method="post"
			theme="simple" enctype="multipart/form-data">
			<!--跳转标志，用于在tab页中修改后再次跳转回tab页  -->
			<s:hidden name="type" value="tab" />
			<s:hidden name="method" id="method" />
			<s:hidden name="storeInfoDTO.imageFileFileName"
				id="imageFileFileName" />
			<%--店铺编号--%>
			<s:hidden name="storeInfoDTO.storeId" id="storeId" />
			<div id="dialog-confirm" style="display: none; width: 1024px;"
				title="请选择商品分类内容">
				<input type="checkbox" value="全选" id="checkAll"
					onclick="checkAll();" />
				全选
				<br />
				<br />
				<fieldset>
					<legend>
						商品分类
					</legend>
					<span id="types"
						style="line-height: 40px;width:300px;"></span>
				</fieldset>
			</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">
				<tr>
					<th align="right">
						<span class="Color5">* </span>店铺名称：
					</th>
					<td>
						<s:textfield name="storeInfoDTO.storeName" id="storeName"
							maxlength="30" cssClass="formInput" theme="simple"
							onblur="checkValue(this);" />
						<label id="storeNameMsg" class="errorMsg" style="display: none;"></label>
					</td>
					<th align="right">
						店铺昵称：
					</th>
					<td>
						<s:textfield name="storeInfoDTO.nickName" id="nickName"
							maxlength="30" cssClass="formInput" theme="simple" />
					</td>

				</tr>
				<tr>
					<th align="right">
						<span class="Color5">* </span>商品分类：
					</th>
					<td colspan="3">
						<input type="button" value="选择分类" onclick="editContent();" />
						<span id="checkedTypes"></span>
						<label id="goodsFamilyError" class="errorMsg" style="display: none;"></label>
					</td>
				</tr>
				<tr>
					<th align="right">
						域名地址：
					</th>
					<td>
						<s:textfield name="storeInfoDTO.domainAddress" id="domainAddress"
							maxlength="50" cssClass="formInput" theme="simple" onblur="" />
						<label id="nameMsg" class="errorMsg" style="display: none;"></label>
					</td>
					<th align="right">
						<span class="Color5">* </span>联系人：
					</th>
					<td>
						<s:textfield name="storeInfoDTO.contPerson" id="contPerson"
							maxlength="30" cssClass="formInput" theme="simple"
							onblur="checkValue(this);" />
						<label id="contPersonMsg" class="errorMsg" style="display: none;"></label>
					</td>
				</tr>
				<tr>
					<th align="right">
						<span class="Color5">* </span>省/自治区：
					</th>
					<td>
						<s:select id="Fname" name="storeInfoDTO.provinceId"
							list="#request.areaDicDTOsValue" listKey="key" listValue="value"
							 cssClass="formSelect"
							theme="simple" onchange="setCityByProvinceId();" />
					</td>
					<th align="right">
						城市：
					</th>
					<td id="div1">
					</td>
				</tr>
				<tr>
					<th align="right">
						<span class="Color5">* </span>手机号码：
					</th>
					<td>
						<s:textfield name="storeInfoDTO.teleNo" id="teleNo2" maxlength="11"
							cssClass="formInput" theme="simple" onblur="checkValue(this);" />
						<label id="teleNoMsg2" class="errorMsg" style="display: none;"></label>
					</td>
					<th align="right">
						<span class="Color5">* </span>营业执照：
					</th>
					<td>
						<s:textfield name="storeInfoDTO.license" id="license"
							maxlength="30" cssClass="formInput" theme="simple"
							onblur="checkValue(this);" />
						<label id="licenseMsg" class="errorMsg" style="display: none;"></label>
					</td>
				</tr>
				<tr>
					
					<th align="right">
						经营类型：
					</th>
					<td colspan="3">
						<s:radio name="storeInfoDTO.businType" id="businType"
							list="#request.businTypeValue" listKey="key" listValue="value"
							theme="simple" />
					</td>
				</tr>
				<tr>
					<th align="right" width="10%">
						<span class="Color5">* </span>店铺介绍：
					</th>
					<td width="30%" colspan="3">
						<!-- 加载编辑器的容器 -->
						<textarea id="container" name="content" style="width: 800px;">
			    		</textarea>
						<s:hidden name="storeInfoDTO.storeDesc" id="storeDesc"
							cssClass="formInput" theme="simple" />
					</td>
				</tr>
				<tr>
					<td align="right">
						主要货源：
					</td>
					<td colspan="3">
						<s:radio name="storeInfoDTO.mainProduct" id="mainProduct"
							list="#request.mainProductValue" listKey="key" listValue="value"
							theme="simple" />
					</td>
				</tr>
				<tr>
					<td align="right">
						是否有实体店：
					</td>
					<td>
						<s:radio name="storeInfoDTO.isStoreOrNot" id="isStoreOrNot"
							list="#request.isStoreOrNotValue" listKey="key" listValue="value"
							theme="simple" />
					</td>
					<th>
						是否有工厂或仓库：
					</th>
					<td>
						<s:radio name="storeInfoDTO.isFactOrNot" id="isFactOrNot"
							list="#request.isFactOrNotValue" listKey="key" listValue="value"
							theme="simple" />
					</td>
				</tr>
				<tr>
					<td align="right">
						店铺简介：
					</td>
					<td>
						<s:textarea name="storeInfoDTO.storeIntroduct" rows="5" cols="50"
							maxlength="255" id="storeIntroduct" onblur="checkValue(this);"></s:textarea>
						<label id="storeIntroductMsg" class="errorMsg"
							style="display: none;"></label>
					</td>
					<td align="right">
						<span class="Color5">* </span>店铺地址：
					</td>
					<td>
						<s:textarea name="storeInfoDTO.storeAddress" id="storeAddress"
							rows="5" cols="40" onblur="checkValue(this);"></s:textarea>
						<label id="storeAddressMsg" class="errorMsg"
							style="display: none;"></label>
					</td>
				</tr>
				<tr>
					<td align="right">
						店铺标志：
					</td>
					<td width="30%">
						<s:file id="file" name="storeInfoDTO.imageFile" size="40"
							onblur="checkValue(this);" onchange="setImg();"></s:file>
						<label id="fileValueMsg" class="errorMsg" style="display: none;"></label>
						<s:fielderror></s:fielderror>
					</td>
				</tr>
				<tbody id="imgcontd" style="display: none;">
					<tr>
						<th align="right" width="20%">
							<span class="Color5">* </span>图片内容：
						</th>
						<td>
							<img id="sign" src="${storeInfoDTO.imageFileFileName }" width="300px" height="400px" >
						</td>
					</tr>
				</tbody>
			</table>
			<div class="formTableBottom">
				<s:if test="method=='addSave'">
					<my:permission key='sy-1221-02' value='店铺基本设置添加'>
						<input  id="submitInput2" type="button" class="formButton"
							value="保 存" onclick="check3();" />
					</my:permission>
				</s:if>
				<s:else>
					<my:permission key='sy-1221-03' value='店铺基本设置修改'>
						<input id="submitInput2" type="button" class="formButton"
							value="修改" onclick="check3();" />
					</my:permission>
				</s:else>
			</div>
		</s:form>
	</body>
</html>