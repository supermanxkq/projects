<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<base href="<%=basePath%>" />
		<title>会员管理</title>
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
			type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.4.2.min.js"></script>
		<link href="js/jquery/css/jquery.ui.all.css" rel="stylesheet"
			type="text/css" />
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
		<script src="js/base/member.js"></script>
		<script type="text/javascript">
		/**编辑内容，发送站内信**/
		function editContent(){
			$("#content").val("");
			$("#title").val("");
			$("#toMember").val("");
			$("#dialog-confirm").css("overflow","hidden");
			var member=document.getElementsByName("member");
			/**判断用户是否选择会员*/
			var check=$("input:checkbox[name='member']:checked");
			if(check.length==0){
				$( "#dialog-check" ).dialog({
					modal: true,
					height:90,
					width:230,
					resizable:false
				});
				return;
			}
			var memberStr="";
			for(var i=0;i<member.length;i++){
				if(member[i].checked){
					/**拼接会员邮箱**/
					memberStr+=";"+member[i].title+"<会员号:"+member[i].value+">";
				}
			}
			memberStr=memberStr.substring(1);
			/**在对话框中显示选中的会员*/
			$("#toMember").html(memberStr);
			$("#dialog-confirm").dialog({
				resizable: false,
				top: 370,
				height:550,
				width:1024,
				modal: true,
				buttons:{
					'取消':function(){
						$("#dialog-confirm").css("overflow","auto");
						$(this).dialog("close");
					},
					'确认':function(){
						if(contentFlag){
							sendMessage();
						}
					}
				}
			});
		}
		/**全选**/
		function checkAll(){
			var status=document.getElementById("checkAll").checked;
			var member=document.getElementsByName("member");
			if(status==true){
				for(var i=0;i<member.length;i++){
					member[i].checked=true;
				}
			}else{
				for(var i=0;i<member.length;i++){
					member[i].checked=false;
				}
			}
		}

		/**发送站内信*/
		function sendMessage(){
			var memId="";
			var member=document.getElementsByName("member");
			for(var i=0;i<member.length;i++){
				if(member[i].checked){
					/**拼接会员编号**/
					memId+=";"+member[i].value;
				}
			}
			memId=memId.substring(1);
			/**站内信标题*/
			var title=$.trim($("#title").val());
			/**站内信内容*/
			var content=$("#content").val();
			//当站内信没有内容或者没有标题不允许发送
			if(content.length==0||title.length==0){
				$( "#dialog-message" ).dialog({
					modal: true,
					height:90,
					width:230,
					resizable:false
				});
				return;
			}
			/**添加请求的actionURL*/
			var actionUrl = "member/member!addSave"; 
			/**向Action中传递一个参数*/
			var params={
				"internalMessageDTO.memId":memId,
				"internalMessageDTO.content":content,		
				"internalMessageDTO.title":title		
			};
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
					alert("站内信已发送");
					$("#dialog-confirm").css("overflow","auto");
					$("#dialog-confirm").dialog("close");
		        }
			});
		}
		/**禁用一条数据**/
		var forbidData = function(url,id) {
			if(confirm("确认操作？")){
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
		    			if (data.ajaxResult == "ajaxsuccess") {
		                	alert("操作成功!");
		                	query($("#currPage").text());
		            	}else if(data.ajaxResult == "ajaxfailure"){
		            		alert(data.msgResult);
		            	}else {
		            		alert("操作失败!");
		            	}
		    		}
				});
			}
		}
		</script>
	</head>
	<body onload="query(${membersDTO.page});">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 会员管理 &gt;&gt; 会员信息管理
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<div class="search">
			<my:permission key='sy-1301-04' value='发送站内信'>
			<div class="Fl" style="position: relative; top: 20px;">
				<input type="button" class="formButton" value="发送站内信"
					onclick="editContent();" />
			</div>
			</my:permission>
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						会员编号:
					</td>
					<td>
						<s:textfield id="memId" name="membersDTO.memId"
							cssClass="formInput" maxlength="10" theme="simple"
							onkeyup= "replaceToNum(this);" />
					</td>
					<td>
						会员姓名:
					</td>
					<td>
						<s:textfield id="realName" name="membersDTO.realName"
							cssClass="formInput" maxlength="20" theme="simple"
							onkeyup="allowEnCnNu(this);" />
					</td>
				</tr>
				<tr>
					<td>
						状态:
					</td>
					<td>
						<s:select name="membersDTO.status" id="status"
							list="#request.statusValues" listKey="key" listValue="value"
							headerKey="-1" headerValue="全部" cssClass="formInput"
							theme="simple" />
					</td>
					<td>
						手机号码：
					</td>
					<td>
						<s:textfield id="teleNo" name="membersDTO.teleNo"
							cssClass="formInput" maxlength="11" theme="simple"
							onkeyup= "replaceToNum(this);" />
					</td>
					<td>
						<input type="button" class="formButton" onclick="query();"
							value="查 询" />
					</td>
				</tr>
			</table>
		</div>
		<div id="dialog-message" title="提示信息" style="display: none;">
			<p align="center" style="font-size: 130%">
				以上两项为必添项！
			</p>
		</div>

		<div id="dialog-check" title="提示信息" style="display: none;">
			<p align="center" style="font-size: 130%">
				您还没有选择会员！
			</p>
		</div>
		<div id="dialog-confirm" style="display: none; width: 1024px;"
			title="发送站内信">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">
				<tr>
					<th align="right">
						<span class="Color5">* </span>收件人：
					</th>
					<td style="text-align: left;">
						<span id="toMember"></span>
					</td>
				</tr>
				<tr>
					<th align="right">
						<span class="Color5">* </span>标题：
					</th>
					<td>
						<s:textfield name="internalMessageDTO.title" id="title"
							maxlength="15" cssClass="formInput" theme="simple" />
					</td>
				</tr>
				<tr>
					<th align="right" width="10%">
						<span class="Color5">* </span>内容：
					</th>
					<td width="30%">
						<s:textarea name="internalMessageDTO.content" rows="5" cols="70"
							id="content" onblur="checkContent();" theme="simple"/>
						<label id="contentMsg" class="errorMsg" style="display: none;"></label>
					</td>
				</tr>
			</table>
		</div>

		<table width="96%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="5%">
					<input type="checkbox" id="checkAll" onclick="checkAll();" />
					全选
				</th>
				<th width="7%">
					<a name="memId" class="sort">编号</a>
				</th>
				<th width="7%">
					真实姓名
				</th>
				<th width="10%">
					用户名
				</th>
				<th width="3%">
					性别
				</th>
				<th width="6%">
					证件类型
				</th>
				<th width="12%">
					证件号码
				</th>
				<th width="10%">
					电话
				</th>
				<th width="11%">
					电子邮箱
				</th>
				<th width="5%">
					状态
				</th>
				<th width="11%">
					创建时间
				</th>
				<th width="5%">
					相关操作
				</th>
			</tr>
		</table>
		<div class="listBottom">
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
			<my:permission key='sy-1301-04' value='发送站内信'>
			<div class="Fl">
				<input type="button" class="formButton" value="发送站内信"
					onclick="editContent();" />
			</div>
			</my:permission>			
		</div>
	</body>
</html>