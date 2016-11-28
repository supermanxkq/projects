<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
		<script type="text/javascript">
		/**店铺名称标志*/
		var storeNameFlag=false;
		/**联系人名称标志*/
		var contPersonFlag=false;
		/**手机号码标志*/
		var teleNoFlag=false;
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
		case "teleNo":
			var teleNo=$.trim($("#teleNo").val());
			if(teleNo.length!=0){
			$("#teleNoMsg").hide();
			teleNoFlag=validateRules.isMobile(teleNo);
			if(!teleNoFlag){
					$("#teleNoMsg").html("手机号码格式错误！");
					$("#teleNoMsg").show();
			}else{
				$("#teleNoMsg").hide();
				}
			}else{
				$("#teleNoMsg").html("请输入手机号码！");
				$("#teleNoMsg").show();
				teleNoFlag=false;
			}
			break;
		case "storeAddress":
			var storeAddress=$.trim($("#storeAddress").val());
			if(storeAddress.length==0){
					$("#storeAddressMsg").html("请输入地址！");
					$("#storeAddressMsg").show();
					storeAddressFlag=false;
				}else{
				$("#storeAddressMsg").hide();
				storeAddressFlag=true;
			};break;
		case "bank":
			var bank=$.trim($("#bank").val());
			if(bank.length==0){
					$("#bankMsg").html("请输入开户银行！");
					$("#bankMsg").show();
					bankFlag=false;
				}else{
				$("#bankMsg").hide();
				bankFlag=true;
			};break;
		case "bankAccNo":
			var bankAccNo=$.trim($("#bankAccNo").val());
			if(bankAccNo.length==0){
					$("#bankAccNoMsg").html("请输入开户银行帐号！");
					$("#bankAccNoMsg").show();
					bankAccNoFlag=false;
				}else{
				$("#bankAccNoMsg").hide();
				bankAccNoFlag=true;
			};break;
		case "bankName":
			var bankName=$.trim($("#bankName").val());
			if(bankName.length==0){
					$("#bankNameMsg").html("请输入开户名称！");
					$("#bankNameMsg").show();
					bankNameFlag=false;
				}else{
				$("#bankNameMsg").hide();
				bankNameFlag=true;
			};break;
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
		case "settPeriod":
			var settPeriod=$.trim($("#settPeriod").val());
			if(settPeriod.length==0){
					$("#settPeriodMsg").html("请输入结算周期！");
					$("#settPeriodMsg").show();
					settPeriodFlag=false;
				}else{
				$("#settPeriodMsg").hide();
				settPeriodFlag=true;
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
				fileValueMsg=false;
			}else{
				$("#fileValueMsg").hide();
				fileValueMsg=true;
			}
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
	function check(){
		/**验证所有*/
		var storeName=document.getElementById("storeName");
		var contPerson=document.getElementById("contPerson");
		var teleNo=document.getElementById("teleNo");
		var storeAddress=document.getElementById("storeAddress");
		var bank=document.getElementById("bank");
		var bankAccNo=document.getElementById("bankAccNo");
		var bankName=document.getElementById("bankName");
		var license=document.getElementById("license");
		var storeIntroduct=document.getElementById("storeIntroduct");
		var settPeriod=document.getElementById("settPeriod");
		var imageFileValue=document.getElementById("file");
		checkValue(imageFileValue);
		checkValue(storeName);
		checkValue(contPerson);
		checkValue(teleNo);
		checkValue(storeAddress);
		checkValue(bank);
		checkValue(bankAccNo);
		checkValue(bankName);
		checkValue(license);
		checkValue(storeIntroduct);
		checkValue(settPeriod);
		getContentValue();
		if(storeNameFlag&&getContentValueFlag&&contPersonFlag&&teleNoFlag&&settPeriodFlag&&storeAddressFlag&&bankFlag&&bankAccNoFlag&&bankNameFlag&&licenseFlag&&storeIntroductFlag&&fileValueMsg){
			return true;
			}else{
				alert("页面信息填写有误!");
			return false;
		}
	}


	/**设置显示图片信息**/
    function setEditorStatus(){
    	 var method = document.getElementById("method"); 
	        if(method.value=='editSave'){
	      				$("#imgcontd").show();
	      			      $("#container").val(($("#storeDesc").val()));
	            }else{
	            	$("#imgcontd").hide();
		            }
       }
	</script>
	    <style type="text/css">
        .clear {
            clear: both;
        }
    </style>
	</head>
	<body onload="setEditorStatus();">
		<div class="Position">
			当前位置是：HOME &gt;&gt;基本信息 &gt;&gt;店铺基本设置
		</div>
		<s:form action="base/storeInfo" method="post"
			onsubmit="document.getElementById('submitInput').disabled = true;return true;"
			theme="simple" enctype="multipart/form-data">
			<s:hidden name="method" id="method" />
			<s:hidden name="storeInfoDTO.imageFileFileName"
				id="imageFileFileName" />
			<%--店铺编号--%>
			<s:hidden name="storeInfoDTO.storeId" id="storeId" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">
				<tr>
					<td align="right">
						<span class="Color5">* </span>店铺名称：
					</td>
					<td>
						<s:textfield name="storeInfoDTO.storeName" id="storeName"
							maxlength="30" cssClass="formInput" theme="simple"
							onblur="checkValue(this);" />
						<label id="storeNameMsg" class="errorMsg" style="display: none;"></label>
					</td>
					<th align="right">
						域名地址：
					</th>
					<td>
						<s:textfield name="storeInfoDTO.domainAddress" id="domainAddress"
							maxlength="50" cssClass="formInput" theme="simple" onblur="" />
						<label id="nameMsg" class="errorMsg" style="display: none;"></label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="Color5">* </span>联系人：
					</td>
					<td>
						<s:textfield name="storeInfoDTO.contPerson" id="contPerson"
							maxlength="30" cssClass="formInput" theme="simple"
							onblur="checkValue(this);" />
						<label id="contPersonMsg" class="errorMsg" style="display: none;"></label>
					</td>
					<th align="right">
						<span class="Color5">* </span>手机号码：
					</th>
					<td>
						<s:textfield name="storeInfoDTO.teleNo" id="teleNo" maxlength="11"
							cssClass="formInput" theme="simple" onblur="checkValue(this);" />
						<label id="teleNoMsg" class="errorMsg" style="display: none;"></label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="Color5">* </span>店铺地址：
					</td>
					<td>
						<s:textfield name="storeInfoDTO.storeAddress" id="storeAddress"
							maxlength="100" cssClass="formInput" theme="simple"
							onblur="checkValue(this);" cssStyle="width:300px;" />
						<label id="storeAddressMsg" class="errorMsg"
							style="display: none;"></label>
					</td>
					<th align="right">
						<span class="Color5">* </span>开户银行：
					</th>
					<td>
						<s:textfield name="storeInfoDTO.bank" id="bank" maxlength="40"
							cssClass="formInput" theme="simple" onblur="checkValue(this);" />
						<label id="bankMsg" class="errorMsg" style="display: none;"></label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="Color5">* </span>开户帐号：
					</td>
					<td>
						<s:textfield name="storeInfoDTO.bankAccNo" id="bankAccNo"
							maxlength="40" cssClass="formInput" theme="simple"
							onblur="checkValue(this);" />
						<label id="bankAccNoMsg" class="errorMsg" style="display: none;"></label>
					</td>
					<th align="right">
						<span class="Color5">* </span>开户名称：
					</th>
					<td>
						<s:textfield name="storeInfoDTO.bankName" id="bankName"
							maxlength="40" cssClass="formInput" theme="simple"
							onblur="checkValue(this);" />
						<label id="bankNameMsg" class="errorMsg" style="display: none;"></label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="Color5">* </span>营业执照：
					</td>
					<td>
						<s:textfield name="storeInfoDTO.license" id="license"
							maxlength="30" cssClass="formInput" theme="simple"
							onblur="checkValue(this);" />
						<label id="licenseMsg" class="errorMsg" style="display: none;"></label>
					</td>
					<th align="right">
						经营类型：
					</th>
					<td>
						<s:radio name="storeInfoDTO.businType" id="businType"
							list="#request.businTypeValue" listKey="key" listValue="value"
							theme="simple" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="Color5">* </span>结算周期：
					</td>
					<td>
						<s:textfield name="storeInfoDTO.settPeriod" id="settPeriod"
							maxlength="4" cssClass="formInput" theme="simple"
							onblur="checkValue(this);" onkeypress="return onlyNum(this);" />
						<label id="settPeriodMsg" class="errorMsg" style="display: none;"></label>
					</td>
					<th align="right">
						结算方式：
					</th>
					<td>
						<s:select id="settWay" name="storeInfoDTO.settWay"
							list="#request.settWayValue" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />
					</td>
				</tr>
				<tr>
					<th align="right" width="10%">
						<span class="Color5">* </span>店铺介绍：
					</th>
					<td width="30%" colspan="3">
						<!-- 加载编辑器的容器 -->
						<textarea id="container" name="content" type="text/plain"
							style="width: 800px;">
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
					<th></th>
					<td></td>
				</tr>
				<tr>
					<td align="right">
						店铺标志：
					</td>
					<td width="30%">
						<s:file id="file" name="storeInfoDTO.imageFile" size="40" onblur="checkValue(this);"></s:file>
							<label id="fileValueMsg" class="errorMsg"
							style="display: none;"></label>
						<s:fielderror></s:fielderror>
					</td>
				</tr>
				<tbody id="imgcontd" style="display: none;">
					<tr>
						<th align="right" width="20%">
							<span class="Color5">* </span>图片内容：
						</th>
						<td>
							<img src="<s:url value='%{storeInfoDTO.imageFileFileName}'/>"  width="300px" height="400px"/>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="formTableBottom">
				<s:if test="method=='addSave'">
					<my:permission key='sy-1221-02' value='店铺基本设置添加'>
						<input id="submitInput" type="submit" class="formButton"
							value="保 存" onclick="return check();" />
					</my:permission>
				</s:if>
				<s:else>
					<my:permission key='sy-1221-03' value='店铺基本设置修改'>
						<input id="submitInput" type="submit" class="formButton"
							value="修改" onclick="return check();" />
					</my:permission>
				</s:else>
			</div>
		</s:form>
		<script type="text/javascript" charset="utf-8"
			src="mailEdit/ueditor.config.js"></script>
		<script type="text/javascript" charset="utf-8"
			src="mailEdit/ueditor.all.min.js"> </script>
		<script type="text/javascript" charset="utf-8"
			src="mailEdit/ueditor.all.js"> </script>
		<script type="text/javascript" charset="utf-8"
			src="mailEdit/lang/zh-cn/zh-cn.js"></script>
		<!-- 实例化编辑器 -->
		<script type="text/javascript" charset="UTF-8">
	var ue = UE.getEditor('container');
  //对编辑器的操作最好在编辑器ready之后再做
  ue.ready(function() {
      //设置编辑器的内容
      ue.setContent($("#storeDesc").val());
  });
</script>
	</body>
</html>