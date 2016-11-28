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
	<title>商户管理</title>
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
	<script src="js/jquery/jquery.ui.tabs.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValidate.js"></script>
   <script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
 
</head>
<body>
    <s:hidden name="merchantsDTO.isSalePointStr" id="isSalePointStr"/>
	<div class="List_tit">商户业务参数</div>
	<s:if test="#session.user_session.userLevel!=2">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable"><%--
			<tr>
		      	<th align="right"><span class="Color5">* </span>商家手机号：</th>
		        <td><s:textfield name="merchantsDTO.teleNo" id="teleNo" onfocus=" this.style.imeMode='inactive' " onblur="teleNoBlur(this);" maxlength="13" cssClass="formInput"/> <label id="teleNoErrorMsg" style="display: none;"></label></td>   
		   	</tr>
			--%><tr>
		      	<th align="right">ICP证书或ICP备案证书号：</th>
		        <td><s:textfield name="merchantsDTO.icpNo" id="icpNo" maxlength="40" cssClass="formInput"/> <label id="rakeRateErrorMsg" style="display: none;"></label></td>
		   	</tr>
		   	<tr>
		   		<th align="right">ICP备案证书文件附件：</th>
		        <td><s:file name="icpFile" id="icpFile"></s:file>
		   	</tr>
			<tr>
		      	<th align="right"><span class="Color5">* </span>商品货号前缀：</th>
		        <td><s:textfield name="merchantsDTO.gnoPrefix"  onkeyup = "allowEnCnNu(this);" onblur="gnoPrefixBlur(this);" id="gnoPrefix" maxlength="18" cssClass="formInput"/> <label id="gnoPrefixErrorMsg" style="display: none;"></label></td>
		      	
		   	</tr>

			<tr>
		      	<th align="right"><strong>客户下单时是否给商家发送短信：</strong></th>
		        <td><s:radio name="merchantsDTO.cosmSign" list="%{#{'1':'是','0':'否'}}"></s:radio></td>
		      
		 	</tr>
		 	<tr>
		      	<th align="right"><strong>客户付款时是否给商家发送短信：</strong></th>
		        <td><s:radio  name="merchantsDTO.cpsmSign" list="%{#{'1':'是','0':'否'}}"></s:radio></td>
		        
		  	</tr>
			<tr>
		      	<th align="right"><strong>客户油卡付款时是否给客户发送短信：</strong></th>
		        <td><s:radio name="merchantsDTO.cpscSign" list="%{#{'1':'是','0':'否'}}"></s:radio></td>
		       
			</tr>
			<tr>
		      	<th align="right"><span class="Color5">* </span><strong>商家发货时是否给客户发送短信：</strong></th>
		        <td><s:radio name="merchantsDTO.msscSign" list="%{#{'1':'是','0':'否'}}"></s:radio><label id="settPriodErrorMsg" style="display: none;"></label></td>
			</tr>
						<tr>
		      	<th align="right"><span class="Color5">* </span><strong>商户已有商品数量：</strong></th>
		        <td>&nbsp;&nbsp;<s:property value="merchantsDTO.exitsCount"/>&nbsp;&nbsp;<font color="red"><strong> 件</strong></font></td>
		      	
		   	</tr>
		   	<tr>
		      	<th align="right"><span class="Color5">* </span><strong>商户已上架商品数量：</strong></th>
		        <td>&nbsp;&nbsp;<s:property value="merchantsDTO.salingCount"/>&nbsp;&nbsp;<font color="red"> <strong> 件</strong></font></td>
		      	
		   	</tr>
	 	</table>
	 </s:if>
	<s:else>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable"><%--
			<tr>
		      	<th align="right"><span class="Color5">* </span>商家手机号：</th>
		        <td><s:textfield name="merchantsDTO.teleNo" id="teleNo" onfocus=" this.style.imeMode='inactive' " onblur="teleNoBlur(this);" maxlength="13" cssClass="formInput"/> <label id="teleNoErrorMsg" style="display: none;"></label></td>   
		   	</tr>
			--%><tr>
		      	<th align="right">ICP证书或ICP备案证书号：</th>
		        <td><s:textfield name="merchantsDTO.icpNo" id="icpNo" maxlength="40" cssClass="formInput"/> <label id="rakeRateErrorMsg" style="display: none;"></label></td>
		   	</tr>
		   	<tr>
		   		<th align="right">ICP备案证书文件附件：</th>
		        <td><s:file name="icpFile" id="icpFile"></s:file>
		   	</tr>
			<tr>
		      	<th align="right"><span class="Color5">* </span>商品货号前缀：</th>
		        <td><s:textfield name="merchantsDTO.gnoPrefix"  onkeyup = "allowEnCnNu(this);" onblur="gnoPrefixBlur(this);" id="gnoPrefix" maxlength="18" cssClass="formInput"/> <label id="gnoPrefixErrorMsg" style="display: none;"></label></td>
		      	
		   	</tr>

			<tr>
		      	<th align="right"><strong>客户下单时是否给商家发送短信：</strong></th>
		        <td>
		           <s:radio name="merchantsDTO.cosmSign" disabled="true" list="%{#{'1':'是','0':'否'}}" ></s:radio>
		        </td>
		      
		 	</tr>
		 	<tr>
		      	<th align="right"><strong>客户付款时是否给商家发送短信：</strong></th>
		        <td><s:radio  name="merchantsDTO.cpsmSign" list="%{#{'1':'是','0':'否'}}" disabled="true"></s:radio></td>
		        
		  	</tr>
			<tr>
		      	<th align="right"><strong>客户油卡付款时是否给客户发送短信：</strong></th>
		        <td><s:radio name="merchantsDTO.cpscSign" list="%{#{'1':'是','0':'否'}}" disabled="true"></s:radio></td>
		       
			</tr>
			<tr>
		      	<th align="right"><span class="Color5">* </span><strong>商家发货时是否给客户发送短信：</strong></th>
		        <td><s:radio name="merchantsDTO.msscSign" list="%{#{'1':'是','0':'否'}}" disabled="true"></s:radio><label id="settPriodErrorMsg" style="display: none;"></label></td>
			</tr>
						<tr>
		      	<th align="right"><span class="Color5">* </span><strong>商户已有商品数量：</strong></th>
		        <td>&nbsp;&nbsp;<s:property value="merchantsDTO.exitsCount"/>&nbsp;&nbsp;<font color="red"><strong> 件</strong></font></td>
		      	
		   	</tr>
		   	<tr>
		      	<th align="right"><span class="Color5">* </span><strong>商户已上架商品数量：</strong></th>
		        <td>&nbsp;&nbsp;<s:property value="merchantsDTO.salingCount"/>&nbsp;&nbsp;<font color="red"> <strong> 件</strong></font></td>
		      	
		   	</tr>
	 	</table>
	 </s:else>
	 
	 </body> 
</html>