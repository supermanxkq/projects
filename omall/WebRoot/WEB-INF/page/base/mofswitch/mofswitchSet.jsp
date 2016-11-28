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
	<title>机构商户功能设置</title>
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
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<!--
	  自建js文件
	-->
	<!--<script src="js/base/terminalsSet.js"></script>
	--><script type="text/javascript">
	  //商户帮助函数 
	</script>
	
	<script type="text/javascript">
		//修改方法
		var check = function() {
		   
		}
</script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 机构商户功能设置
	</div>
	<s:form action="base/mofswitch" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method"/>
	<s:hidden name="mofSwitchDTO.fmoId"/>
	<s:if test="#session.user_session.userLevel==0">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
			  <s:if test="mofSwitchDTO.orgMercSign==1">
			    <th align="right" width="20%">商户：</th>
		        <td colspan="3"><s:property value="mofSwitchDTO.merName"/><s:hidden name="mofSwitchDTO.termId" id="termId"/></td>
			  </s:if>
			  <s:else>
			     <th align="right" width="20%">机构：</th>
			     <td colspan="3"><s:property value="mofSwitchDTO.organName"/><s:hidden name="mofSwitchDTO.organId" id="organId"/></td>
			  </s:else>
			</tr>
		 	<tr>
		 		<th align="right">是否开通代理商：</th>
		 		<td>
		 		<s:if test="mofSwitchDTO.openAgencySign==0">
		 		 <input type="radio" name="mofSwitchDTO.openAgencySign" id="openAgencySign1" checked="checked" value='0'/><label>否</label>
		 		 <input type="radio" name="mofSwitchDTO.openAgencySign" id="openAgencySign2" value='1'/><label>是</label>
		 		</s:if>
		 		<s:else>
		 		  <input type="radio" name="mofSwitchDTO.openAgencySign" id="openAgencySign1" value='0'/><label>否</label>
		 		  <input type="radio" name="mofSwitchDTO.openAgencySign" id="openAgencySign2" checked="checked"  value='1'/><label>是</label>
		 		</s:else>
		 		
		 		</td>
		      	<th align="right">是否开通业务员：</th>
		        <td>
		        <s:if test="mofSwitchDTO.openBussManSign==0">
		          <input type="radio" name="mofSwitchDTO.openBussManSign" id="openBussManSign1" checked="checked" value="0"/><label>否</label>
		 		  <input type="radio" name="mofSwitchDTO.openBussManSign" id="openBussManSign2" value="1"/><label>是</label>
		        </s:if>
		        <s:else>
		          <input type="radio" name="mofSwitchDTO.openBussManSign" id="openBussManSign1" value="0"/><label>否</label>
		 		  <input type="radio" name="mofSwitchDTO.openBussManSign" id="openBussManSign2" checked="checked" value="1"/><label>是</label>
		        </s:else>
		        
		        </td>
		  	</tr>
		  	<tr>
		  		<th align="right">是否开通短信功能：</th>
		        <td>
		         <s:if test="mofSwitchDTO.openMessSign==0">
		         <input type="radio" name="mofSwitchDTO.openMessSign" id="openMessSign1" checked="checked" value="0"/><label>否</label>
		 		 <input type="radio" name="mofSwitchDTO.openMessSign" id="openMessSign2" value="1"/><label>是</label>
		         </s:if>
		         <s:else>
		         <input type="radio" name="mofSwitchDTO.openMessSign" id="openMessSign1" value="0"/><label>否</label>
		 		 <input type="radio" name="mofSwitchDTO.openMessSign" id="openMessSign2" checked="checked" value="1"/><label>是</label>
		         </s:else>
		        
		 		</td>
		      	<th align="right">是否开通优惠活动：</th>
		        <td>
		        <s:if test="mofSwitchDTO.openPreferSign==0">
		         <input type="radio" name="mofSwitchDTO.openPreferSign" id="openPreferSign" checked="checked" value="0"/><label>否</label>
		 	 	 <input type="radio" name="mofSwitchDTO.openPreferSign" id="openPreferSign" value="1"/><label>是</label>
		         </s:if>
		         <s:else>
		         <input type="radio" name="mofSwitchDTO.openPreferSign" id="openPreferSign" value="0"/><label>否</label>
		 		 <input type="radio" name="mofSwitchDTO.openPreferSign" id="openPreferSign" checked="checked" value="1"/><label>是</label>
		         </s:else>
		        
		        </td>
		 	</tr>
		  	<tr>
		      	<th align="right"><span class="Color5">* </span>是否开通生日赠送：</th>
		        <td>
		        <s:if test="mofSwitchDTO.openBriGiftSign==0">
		         <input type="radio" name="mofSwitchDTO.openBriGiftSign" id="openBriGiftSign" checked="checked" value="0"/><label>否</label>
		 	  	 <input type="radio" name="mofSwitchDTO.openBriGiftSign" id="openBriGiftSign" value="1"/><label>是</label>
		         </s:if>
		         <s:else>
		         <input type="radio" name="mofSwitchDTO.openBriGiftSign" id="openBriGiftSign" value="0"/><label>否</label>
		 		 <input type="radio" name="mofSwitchDTO.openBriGiftSign" id="openBriGiftSign" checked="checked" value="1"/><label>是</label>
		         </s:else>
		        
		        </td>
		      	<th align="right"><span class="Color5">* </span>是否开通节日赠送：</th>
		        <td>
		        <s:if test="mofSwitchDTO.openHolGiftSign==0">
		         <input type="radio" name="mofSwitchDTO.openHolGiftSign" id="openHolGiftSign" checked="checked" value="0"/><label>否</label>
		 		 <input type="radio" name="mofSwitchDTO.openHolGiftSign" id="openHolGiftSign" value="1"/><label>是</label>
		         </s:if>
		         <s:else>
		         <input type="radio" name="mofSwitchDTO.openHolGiftSign" id="openHolGiftSign" value="0"/><label>否</label>
		 		 <input type="radio" name="mofSwitchDTO.openHolGiftSign" id="openHolGiftSign" checked="checked"  value="1"/><label>是</label>
		         </s:else>
		        
		        </td>
		   	</tr>
		  	<tr>
		  		<th align="right">是否开通优惠券功能：</th>
		  		<td>
		  		<s:if test="mofSwitchDTO.openPreferTSign==0">
		          <input type="radio" name="mofSwitchDTO.openPreferTSign" id="openPreferTSign" checked="checked" value="0"/><label>否</label>
		 		  <input type="radio" name="mofSwitchDTO.openPreferTSign" id="openPreferTSign" value="1"/><label>是</label>
		        </s:if>
		         <s:else>
		          <input type="radio" name="mofSwitchDTO.openPreferTSign" id="openPreferTSign" value="0"/><label>否</label>
		 		  <input type="radio" name="mofSwitchDTO.openPreferTSign" id="openPreferTSign" checked="checked" value="1"/><label>是</label>
		         </s:else>
		  		
		  		</td>
		       <th align="right">是否加密会员信息：</th>
		         <td colspan="3">
		         <s:if test="mofSwitchDTO.enciVipMess==0">
		          <input type="radio" name="mofSwitchDTO.enciVipMess" id="enciVipMess" checked="checked" value="0"/><label>否</label>
		 		  <input type="radio" name="mofSwitchDTO.enciVipMess" id="enciVipMess" value="1"/><label>是</label>
		         </s:if>
		         <s:else>
		          <input type="radio" name="mofSwitchDTO.enciVipMess" id="enciVipMess" value="0"/><label>否</label>
		 		  <input type="radio" name="mofSwitchDTO.enciVipMess" id="enciVipMess" checked="checked" value="1"/><label>是</label>
		         </s:else>
		         
		       </td>
		 	</tr>
		</table>
	</s:if>
	
	 <div class="formTableBottom">
	 	<s:if test="method=='editSave'">
			<my:permission key='sy-1401-01' value='机构商户功能设置修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('base/mofswitch!list')"/>
	 </div>
	 </s:form>
</body>
</html>