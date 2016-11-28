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
	<script src="js/jquery-1.4.2.min.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript">
		function query(page){
			var storeId=$("#searchStoreId").val();
			var searchType=$("#searchType").val();
			var secarchFiledId=$("#searchFiledId").val();
			var params = {
			        "complaintFiledDTO.page" : page,
			        "complaintFiledDTO.storeId":storeId,
			        "complaintFiledDTO.compType":searchType,
			        "complaintFiledDTO.filedId":secarchFiledId
			 }; 
			ajaxData("complaintFiled/complaintFiled!jsonPageList",params);
		}
		/**指派给商家处理**/
		function appoint(id){
			if(confirm("确定要指派给卖家处理吗？")){
				$.ajax({
					url:"complaintFiled/complaintFiled!appoint",
					data:"complaintFiledDTO.filedId="+id,
					dataType : "json",   
		    		cache : false,  
		    		type : "POST", 
		    		error : function(textStatus, errorThrown) {
						alert("系统ajax交互错误!");
		    		},
		    		success : function(data, textStatus) {
			    		if(data==true){
							query(1);
				    	}else{
							alert("指派失败，请重试！");
					    }
		    		}
				});
			}
			
		}
	</script>
  </head>
  
  <body onload="query(${complaintFiledDTO.page });">
  
	   <div class="Position">
			当前位置是：HOME &gt;&gt; 投诉管理&gt;&gt; 投诉处理
	   </div>
	   <jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	   	<!--搜索  -->
		<div class="search">
				<table class="searchTable" cellpadding="0" cellspacing="0">
					<tr>
						<c:if test="${sessionScope.user_session.userLevel==0 }">
						<td>投诉店铺编号:</td>
						<td>
						<td><s:textfield id="searchStoreId" onkeyup="allowEnCnNu(this);" cssClass="formInput" maxlength="20" theme="simple"/></td>
						</td>
						</c:if>
						<td>投诉编号:</td>
						<td>
						<td><s:textfield id="searchFiledId" onkeyup="allowEnCnNu(this);" cssClass="formInput" maxlength="20" theme="simple"/></td>
						</td>
			        	<td>投诉类型:</td>
						<td>
						<td>
							<s:select theme="simple" id="searchType"  list="#request.comPlaintType" key="value" value="key"  headerKey="-1" headerValue="全部" listKey="key" listValue="value" >
								
							</s:select>
						</td>
						</td>
			        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
					</tr>
				</table>
		</div>
	   <table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
				<tr>
					<th width="3%" >序号</th>
					<th width="8%"><a name="namee" >投诉编号</a></th>
					<th width="8%"><a name="QQQ">订单编号</a></th>
					<th width="8%"><a name="createTimee">投诉类型</a></th>
					<th width="8%"><a name="createTimee">投诉店铺编号</a></th>
					<th width="8%"><a name="createTimee">投诉管理状态</a></th>
					<th width="8%"><a name="operr">相关操作</a></th>
				</tr>
		</table> 
		<div class="listBottom">
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false"/>
			</div>
		</div>
  </body>
</html>
