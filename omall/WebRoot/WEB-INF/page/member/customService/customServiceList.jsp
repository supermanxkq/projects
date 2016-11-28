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
    <title>QQ客服设置</title>
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
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript">
	
		/**查询方法**/
		function query(page){
			var name=$.trim($("#searchName").val());
			var qq=$.trim($("#searchQQ").val());
			var params = {
			        "customServiceDTO.page" : page,
			        "customServiceDTO.name" : name,
			        "customServiceDTO.qq" : qq
			 }; 
			 ajaxData("member/customService!jsonPageList",params);
		}

		/**删除方法**/
		function deleteData(url,id){
			if(confirm("确认要删除？")){
				var params = {
		    		"id" : id
				};
				$.ajax({
		    		url : url,   
		    		data : params,   
		    		dataType : "json",   
		    		cache : false,  
		    		type : "POST", 
		    		error : function(textStatus, errorThrown) {
						alert("系统ajax交互错误!");
		    		},
		    		success : function(data, textStatus) {
						query(1);
		    		}
				});
			}
		}

		/**更新方法**/
		function updateData(url,id){
			loadData(id);
			var dialog=$("#dialog-confirm");
			dialog.css("overflow","hidden");
			dialog.dialog({
				position:'center',
				resizable: false,
				height:200,
				width:420,
				modal: true,
				buttons:{
					'取消':function(){
						$("#dialog-confirm").css("overflow","auto");
						$(this).dialog("close");
						$("#QQ").val("");
						$("#name").val("");
					},
					'更新':function(){
						if(!check()){
							return;
						}
						var qq=$.trim($("#QQ").val());
						var name=$.trim($("#name").val());
						var param={
								"customServiceDTO.id":id,
								"customServiceDTO.name":name,
								"customServiceDTO.qq":qq
								};
						$.ajax({
							url:url,
							data:param,	
							dataType:"json",
							type:"post",
							cache:false,
							error : function(textStatus, errorThrown) {   
			    				alert("系统ajax交互错误!");	    
				        	}, 
				        	success:function(data, textStatus){
								alert("更新完成");
								$("#dialog-confirm").css("overflow","auto");
								$("#dialog-confirm").dialog("close");
								$("#QQ").val("");
								$("#name").val("");
								query(1);
					        }					
						});
					}
				}
			});
		}
		
		/**加载单条数据**/
		function loadData(id){
			$.ajax({
				url:"member/customService!loadData",
				data:"customServiceDTO.id="+id,
				dataType:"json",
				type:"post",
				cache:false,
				error : function(textStatus, errorThrown) {   
    				alert("系统ajax交互错误!");	    
	        	}, 
	        	success:function(data, textStatus){
					$("#QQ").val(data.qq);
					$("#name").val(data.name);
	        	}					
			});
		}
		
		/**添加方法**/
		function add(){
			var dialog=$("#dialog-confirm");
			dialog.css("overflow","hidden");
			dialog.dialog({
				position:'center',
				resizable: false,
				height:200,
				width:420,
				modal: true,
				buttons:{
					'取消':function(){
						$("#dialog-confirm").css("overflow","auto");
						$(this).dialog("close");
						$("#QQ").val("");
						$("#name").val("");
					},
					'添加':function(){
						if(!check()){
							return;
						}
						var qq=$.trim($("#QQ").val());
						var name=$.trim($("#name").val());
						var param={
								"customServiceDTO.name":name,
								"customServiceDTO.qq":qq
								};
						$.ajax({
							url:"member/customService!add",
							data:param,
							dataType:"json",
							type:"post",
							cache:false,
							error : function(textStatus, errorThrown) {   
			    				alert("系统ajax交互错误!");	    
				        	}, 
				        	success:function(data, textStatus){
								alert("添加完成");
								$("#dialog-confirm").css("overflow","auto");
								$("#dialog-confirm").dialog("close");
								$("#QQ").val("");
								$("#name").val("");
								query(1);
					        }					
						});
					}
				}
			});
		}
		
		/**验证QQ号**/
		function validateQQ(){
			var qqFlag=true;
			var qq=$.trim($("#QQ").val());
			if(qq.length==0){
				$("#QQError").html("不能为空！");
				$("#QQError").show();
				qqFlag=false;
				return qqFlag;
			}
			var qqVali=["isQQ"];
			var qqMsg=["QQ格式不正确！"];
			qqFlag=showErrorMsg(document.getElementById("QQ"),qqVali,qqMsg,"QQError","QQError");
			return qqFlag;
			
		}
		
		/**验证职位**/
		function validateName(){
			var name=$.trim($("#name").val());
			var nameError=$("#nameError");
			if(name.length==0){
				nameError.html("职位不能为空！");
				nameError.show();
				return false;
			}
			nameError.hide();
			return true
		}
		function check(){
			var nameFlag=validateName();
			var qqFlag=validateQQ();
			return nameFlag&&qqFlag;
		}
	</script>
  </head>
  
  <body onload="query(${customServiceDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 外部服务参数配置&gt;&gt; QQ客服设置
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<!--提示信息  -->
	<div id="dialog-message" title="提示信息" style="display: none;">
	<p align="center" style="font-size: 130%">
		操作完成！
	</p>
	</div>
	<!--编辑内容框  -->
	<div id="dialog-confirm" style="display: none;text-align: center;" title="编辑内容" >
				<table width="100%" border="0" bordercolor="#000000" cellpadding="0" cellspacing="0"
					class="formTable"  >
					<tr>
						<th  style="text-align: center; width: 20%;">
								<label for="tags" >职位: </label>
								
						</th>
						<td style="text-align: left;">
							<input onblur="validateName();" type="text" id="name" maxlength="50" />
							<span id="nameError" class="errorMsg" style="display: none;"></span>
						</td>
					</tr>
					<tr>
						<th  style="text-align: center;">
								<label for="tags" ">QQ号: </label>
						</th>
						<td style="text-align: left;">
							<input onblur="validateQQ();" type="text" id="QQ" maxlength="20" />
							<span id="QQError" class="errorMsg" style="display: none;"></span>
						</td>
					</tr>
				</table>
				
		</div>
		
	<!--搜索  -->
	<div class="search">
		<div class="Fl">
			<input type="button" class="formButton" value="添加" onclick="add();" />
		</div>
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
					<td>职位名:</td>
					<td><s:textfield id="searchName" name="customServiceDTO.name" cssClass="formInput" maxlength="20" theme="simple"/></td>
		        	<td>QQ号:</td>
					<td>
					<td><s:textfield id="searchQQ" onkeyup="allowEnCnNu(this);" name="customServiceDTO.qq" cssClass="formInput" maxlength="20" theme="simple"/></td>
					</td>
		        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
				</tr>
			</table>
		</div>
		<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
				<tr>
					<th width="3%" >序号</th>
					<th width="8%"><a name="namee" >职位</a></th>
					<th width="8%"><a name="QQQ">QQ号</a></th>
					<th width="8%"><a name="createTimee">创建时间</a></th>
					<th width="8%"><a name="operr">操作</a></th>
				</tr>
		</table> 
		<div class="listBottom">
			<div class="Fl">
					<input type="button" class="formButton" value="添加" onclick="add();" />
			</div>
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false"/>
			</div>
		</div>
  </body>
</html>
