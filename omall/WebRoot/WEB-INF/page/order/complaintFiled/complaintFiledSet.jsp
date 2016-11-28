<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>投诉处理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />	
	<script src="js/jquery-1.8.2.min.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<link href="js/jquery/css/jquery.ui.all.css"  rel="stylesheet"  type="text/css" />	
	<script src="js/jquery/jquery.ui.core.js"></script>
	<script src="js/jquery/jquery.ui.widget.js"></script>
	<script src="js/jquery/jquery.ui.mouse.js"></script>
	<script src="js/jquery/jquery.ui.draggable.js"></script>
	<script src="js/jquery/jquery.ui.position.js"></script>
	<script src="js/jquery/jquery.ui.tabs.js"></script>
	<script type="text/javascript" src="js/jquery.lightbox-0.5.js"></script>
	<link  href="js/jquery.lightbox-0.5.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
		$(function(){
			setSelect();
			var form=document.getElementById("updateCom");
			var method=$("#method").val();
			if(method=="view"){
				
				$("#compDesc").attr("disabled",true);
				$("#storeComDesc").attr("disabled",true);
				$("select,input[type!='button'],textarea").attr("disabled",true);
				var score=$("#score").val();
				$("#points").val(score.replace(/.0/,""));
				if($("#compstaa").val()!=3&&$("#compstaa").val()!=5&&$("#compstaa").val()!=2){
					$("#admin").hide();
				}
			}else if(method="edit"){
				$("#compDesc").attr("disabled",true);
				//根据不同等级设定action
				if($("#userLevel").val()==0){
					$("#storeComDesc").attr("disabled",true);
					$("#admin").show();
					$("#updateCom").attr("action","complaintFiled/complaintFiled!updateCom");
				}else{
					if($("#compstaa").val()!=3&&$("#compstaa").val()!=2){
						$("#admin").hide();
					}
					//$("#updateCom").attr("onsubmit","return checkStoreComDesc()");
					$("#updateCom").removeAttr("onsubmit");
					form.action="complaintFiled/complaintFiled!businessAppeal";
				}
			}
			var $tabs = $("#tabs").tabs();	
			$('#tabs-1').click(function() { // 绑定单击事件
			    $tabs.tabs('select', 0);
			    return true;
			});	
			/**图片弹出层**/
			 $('#gallery a').lightBox();
				
					
			
		});
		/**为选择框赋值,防止el表达式判断问题**/
		function setSelect(){
			var adminHandlSugTemp=$("#adminHandlSugTemp").val();
			var vioIdTemp=$("#vioIdTemp").val();
			var handlWayTemp=$("#handlWayTemp").val();
			if(adminHandlSugTemp.length>0){
				$("#adminHandlSug").val(adminHandlSugTemp);
				if($("#adminHandlSug").val()==1){
					$("#vioId").attr("disabled",true);
					$("#handlWay").attr("disabled",true);
					$("#points").attr("disabled",true);
				}
			}
			if(vioIdTemp.length>0){
				$("#vioId").val(vioIdTemp);
			}
			if(handlWayTemp.length>0){
				$("#handlWay").val(handlWayTemp);
			}
		}
		/**判断违规案例**/
		function checkVio(){
			var val=$.trim($("#vioId").val());
			if(val==-1){
				$("#vioError").show();
				return false;
			}else{
				$("#vioError").hide();
				return true;
			}
		}
		/**判断店铺或商品处理方式**/
		function checkHandlWay(){
			var val=$.trim($("#handlWay").val());
			if(val==-1){
				$("#wayError").show();
				return false;
			}
			$("#wayError").hide();
			return true;
		}
		/**判断积分**/
		function checkPoints(){
			var val=$.trim($("#points").val());
			var reg=/^\d{1,3}$/
			if(val.length==0){
				$("#pointsError").show();
				return false;
			}
			if(reg.test(val)){
				$("#pointsError").hide();
				return true;
			}else{
				$("#pointsError").show();
				return false;
			}
		}

		/**平台处理意见**/
		function checkSug(){
			var val=$.trim($("#adminHandlSug").val());
		
			if(val==1||val==5||val==2){
				$("#vioId").attr("disabled",true);
				$("#handlWay").attr("disabled",true);
				$("#points").attr("disabled",true);
				$("#vioError,#wayError,#pointsError").hide();
			}else{
				$("#vioId").attr("disabled",false);
				$("#handlWay").attr("disabled",false);
				$("#points").attr("disabled",false);
			}
			if(val==-1){
				$("#sugError").show();
				return false;
			}
			$("#sugError").hide();
			return true;
		}  
		/**平台处理意见说明**/
		function checkHandlSug(){
			var val=$.trim(document.getElementById('handlSug').value);
			if(val.length==0){
				$("#handlSugError").show();
				return false;
			}else if(val.length>=255){
				$("#handlSugError").show();
				return false;
			}
			$("#handlSugError").hide();
			return true;
		}
		
		function check(){
			var vioFlag=true;
			var handlWayFlag=true;
			var pointsFlag=true;
			if(!($("#vioId").attr("disabled")&&$("#handlWay").attr("disabled")&&$("#points").attr("disabled"))){
				vioFlag=checkVio();
				handlWayFlag=checkHandlWay();
				pointsFlag=checkPoints();
			}
			var sugFlag=checkSug();
			var handlSugFlag=checkHandlSug();
			return vioFlag&&handlWayFlag&&pointsFlag&&sugFlag&&handlSugFlag;
		}

		function checkStoreComDesc(){
			var flag=false;
			var desc=$.trim(document.getElementById('storeComDesc').value);
			//alert($("#updateCom").attr("onsubmit"));
			if(desc.length==0){
				$("#storeError").show();
				flag=false;
				return flag;
			}else if(desc.length>=255){
				$("#storeError").show();
				flag=false;
				return flag;
			}else{
				$("#storeError").hide();
				flag=true;
				return flag;
			}
		}
		function checkStore(){
			if(checkStoreComDesc()){
				document.getElementById("updateCom").submit();
			}
		}
	</script>
	
<style type="text/css">
	
	#gallery {
		padding: 10px;
	}
	#ul { list-style: none; }
	#gallery ul li { display: inline; }
	#gallery ul img {
		border: 5px solid #3e3e3e;
		border-width: 5px 5px 20px;
	}
	#gallery ul a:hover img {
		border: 5px solid #fff;
		border-width: 5px 5px 20px;
		color: #fff;
	}
	#gallery ul a:hover { color: #fff; }
	
</style>
	
	
  </head>
  
  <body>
    <div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 投诉详情
	</div>
	<div class="search" id="tabs">
	     <ul>
		   <li><a href="#tabs-1">商户基本信息</a></li>
	     </ul>
	 <div class="search" id="tabs-1">
	 <input type="hidden" id="userLevel" value="${sessionScope.user_session.userLevel }" />
	 <input type="hidden" id="compstaa" value="${requestScope.complaintHandleDTO.compSta }">
	 <input type="hidden" id="score" value="${requestScope.complaintHandleDTO.deductScore }"/>
	<s:form id="updateCom" action="complaintFiled/complaintFiled!updateCom" onsubmit="return check();" method="post"  theme="simple">
		<s:hidden name="method" id="method"/>
	    <s:hidden name="complaintHandleDTO.filedId" id="filedId"/>
	    <s:hidden name="complaintHandleDTO.goodsId" id="goodsId"/>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
				 <th align="right" width="20%">目前投诉状态：</th>
	       		 <td width="30%">
	       		 	<c:forEach items="${requestScope.compsta }" var="c">
	       		 		<c:if test="${c.key==complaintHandleDTO.compSta }">
	       		 			${c.value } 
	       		 		</c:if>
	       		 	</c:forEach>
	       			
	       		 </td>
	       		 <th align="right" width="20%">发出申请的会员：</th>
	       	 	 <td width="30%">
	       	 	 	<s:property value="complaintHandleDTO.userName" />
		         </td>
		    </tr>
		    <tr>
		    	<th align="right" width="20%">投诉类型：</th>
		        <td width="30%">
		        	<c:forEach items="${requestScope.type }" var="t">
	       		 		<c:if test="${t.key==complaintHandleDTO.compType }">
	       		 			${t.value } 
	       		 		</c:if>
	       		 	</c:forEach>
		        	</td>
		        <th align="right" width="20%">被投诉店铺：</th>
		        <td width="30%">
		        	<s:property value="complaintHandleDTO.storeName"/>
		        	<s:hidden name="complaintHandleDTO.storeName"></s:hidden>
		        </td>
		    </tr>
		     <tr>
		    	<th align="right" width="20%">被投诉的商品：</th>
		        <td width="30%">
		       	 	<s:property value="complaintHandleDTO.goodsName"/>
		       	 	<s:hidden name="complaintHandleDTO.comType" id="goodsName"/></td>
		       	 	<s:hidden name="complaintHandleDTO.goodsName"></s:hidden>
		        <th align="right" width="20%">投诉时间：</th>
		        <td width="30%">
		        	
		        	<s:date name="complaintHandleDTO.compTime" format="yyyy-MM-dd HH:mm:ss"/>
		        </td>
		    </tr>
		    <tr>
		    
		    	<th align="right" width="20%">投诉描述：</th>
		    	<td colspan="4" >
		    		<s:textarea name="complaintHandleDTO.compDesc" id="compDesc" cols="45" rows="5" style="resize:none;">
		    		</s:textarea>
		    	</td>
		    </tr>
		      <tr>
		    
		    	<th align="right" width="20%">商户申诉说明：</th>
		    	<td colspan="4" >
		    		<s:textarea name="complaintHandleDTO.storeComDesc" maxlength="255" onblur="checkStoreComDesc();" id="storeComDesc" cols="45" rows="5" style="resize:none;">
		    		</s:textarea>
		    		<span id="storeError" style="display: none;" class="errorMsg"  >该项为必添项且不超过255个字符！</span>
		    	</td>
		    </tr>
		    <tr>
		    	<th align="right" width="20%">投诉图片证明：
		    		<br/>
		    		<label style="color: red;">（点击查看大图）</label>
		    	</th>
		    	<td colspan="4" >
		    	<div id="gallery">
    				<ul>
					<c:forEach items="${complaintHandleDTO.picPath }" var="p">
					<li>
            			<a href="<%=basePath %>${p }" >
             			<img src="<%=basePath %>${p }" width="72" height="72" alt="" />
         			   </a>
       				 </li>
					
					</c:forEach>
					</ul>
				</div>
		    	</td>
		    </tr>
		   
		</table>
		<table id="admin" width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		 <tr style="text-align: center;">
		    	<th colspan="5" style="color: red;">平台处理</th>
		 </tr>
		 <tr>
		 	<th  align="right" width="20%">处理意见：</th>
		 	 <td width="30%">     
		 	 	<input type="hidden" id="adminHandlSugTemp" value="${complaintHandleDTO.adminHandlSug }" />
		 	 	<select  name="complaintHandleDTO.adminHandlSug" id="adminHandlSug" onchange="checkSug();">
		 	 		<option value="-1">请选择</option>
		 	 		<c:forEach items="${requestScope.admin }" var="a">
		 	 			<option value="${a.key }" >${a.value }</option>
		 	 		</c:forEach>
		 	 	</select>
		 	 	<span id="sugError" style="display: none;"  class="errorMsg">该项为必选项！</span>
		 	 </td>
		 	 <th  align="right" width="20%">违规案例：</th>
		 	 <td width="30%">
		 	 	<input type="hidden" id="vioIdTemp" value="${complaintHandleDTO.vioId }" />
		 		 <select onchange="checkVio();"  name="complaintHandleDTO.vioId" id="vioId">
		 	 		<option value="-1">请选择</option>
		 	 		<c:forEach items="${requestScope.vio }" var="a">
		 	 			<option value="${a.key }">${a.value }</option>
		 	 		</c:forEach>
		 	 	</select>
		 		<span id="vioError" style="display: none;" class="errorMsg">该项为必选项！</span>
		 	 </td>
		 	 
		 </tr>
		 <tr>
		 	<th align="right" width="20%">商品及店铺处理方式：</th>
		        <td width="30%">
		        	<input type="hidden" id="handlWayTemp" value="${complaintHandleDTO.handlWay }" />
		        	<select onchange="checkHandlWay();"  name="complaintHandleDTO.handlWay" id="handlWay">
		 	 		<option value="-1">请选择</option>
			 	 		<c:forEach items="${requestScope.handlWay }" var="a">
			 	 			<option value="${a.key }" >${a.value }</option>
			 	 		</c:forEach>
		 	 		</select>
					<span id="wayError" style="display: none;" class="errorMsg">该项为必选项！</span>
				</td>
		        <th align="right" width="20%">扣除分值：</th>
		        <td width="30%">
		        <s:textfield  name="complaintHandleDTO.points" maxlength="3" onblur="checkPoints();"  style="width: 10%;"  onkeyup="allowEnCnNu(this);" id="points"></s:textfield>分
		        	<span id="pointsError" style="display: none;" class="errorMsg">该项为必添项不得超过三位整数！</span>
			    </td>
			   
		 </tr>
		 <tr>
		 	<th  align="right" width="8%">处理意见说明：</th>
		 	 <td width="32%" colspan="4">
		 	 	<s:textarea name="complaintHandleDTO.handlSug" maxlength="255" onblur="checkHandlSug();" id="handlSug" cols="45" rows="5" style="resize:none;">
		    	</s:textarea>
		    	<span id="handlSugError" style="display: none;" class="errorMsg"  >该项为必添项且不超过255个字符！</span>
		 	 </td>
		 </tr>
		</table>
		 <div class="formTableBottom">
		 	<c:if test="${user_session.userLevel==0&&method!='view'&&complaintHandleDTO.compSta!=3 }">
				<input id="submitInput" type="submit" class="formButton" value="确认处理" onclick="return check();"/>
			</c:if>
			<c:if test="${user_session.userLevel==2&&method!='view'&&complaintHandleDTO.compSta==2 }">
				<input id="submitInput" type="button" class="formButton" value="我要申诉" onclick="checkStore();" />
			</c:if>
			<input type="button" class="formButton" value="返 回" onclick="javascript:history.back();"/>
		</div>
	</s:form>
	</div>
	</div>
  </body>
</html>
