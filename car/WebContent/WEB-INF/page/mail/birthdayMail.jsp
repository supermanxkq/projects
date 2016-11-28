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
	<title>会员生日邮件发送</title>
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
	<script src="js/jquery/jquery.ui.autocomplete.js"></script>
	<script src="js/jquery/jquery.ui.resizable.js"></script>
	<script src="js/jquery/jquery.ui.dialog.js"></script>
	<script src="js/common.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script src="js/datepicker/WdatePicker.js"></script>
	
		
	 <script type="text/javascript" charset="utf-8" src="mailEdit/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="mailEdit/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="mailEdit/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="mailEdit/lang/zh-cn/zh-cn.js"></script>
	
    <style type="text/css">
        .clear {
            clear: both;
        }
        </style>
	<script type="text/javascript">
		//实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
   

   
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
        UE.getEditor('editor').setContent('', isAppendTo);
    }
	
		//查询方法
		function query(page) {
			UE.getEditor('editor');
			if($.browser.msie){
				$("#subTd").css("width","40%");
				$("#subject").css("width","100%");
				$("#content").css("width","100%");
			}
			var date=$.trim($("#creatTime").val());
			var params = {
				"birthdayDTO.creatTime" : date,
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		        "birthdayDTO.page" : page
		    }; 
		   ajaxData("mail/birthday!jsonPageList",params);// !是跳转的意思
		  /*
		   $( "#subject" ).autocomplete({
				source: availableTags
			});
		   $( "#toMail" ).autocomplete({
				source: availableTags
			});
		 */
		}

		function showDialog(){
			setContent('');
			$("#dialog-confirm").css("overflow","hidden");
			init();
			$("#dialog-confirm").dialog({
				resizable: false,
				top: 370,
				height:550,
				width:1024,
				modal: true,
				buttons:{
					'关闭':function(){
				$("#dialog-confirm").css("overflow","auto");
						$(this).dialog("close");
					},
					'确认添加':function(){
						add();
						
					}
				}
			});
		}
		function add(){
			var subject=$.trim($("#subject").val());
			var content="";//邮件内容
			var arr = [];
        	arr.push(UE.getEditor('editor').getContent());
     	   	content=$.trim(arr.join());
			var status=0;
			var radio=document.getElementsByName("statuss");
			for(var i=0;i<radio.length;i++){
				if(radio[i].checked){
					status=radio[i].value;
				}
			}
			if(content.length==0||subject.length==0){
				$( "#dialog-message" ).dialog({
					modal: true,
					height:90,
					width:230,
					resizable:false
				});
				return;
			}
			var actionUrl = "mail/birthday!add";
			var params={
					"birthdayDTO.subject":subject,	
					"birthdayDTO.content":content,	
					"birthdayDTO.status":status	
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
					alert("节日已添加");
					$("#dialog-confirm").css("overflow","auto");
					$("#dialog-confirm").dialog("close");
					query(1);
		        }
			});
		}
		/*编辑查看数据*/
		function editData(id){
			$("#dialog-confirm").css("overflow","hidden");
			var actionUrl = "mail/birthday!editData";
			var params={
					"birthdayDTO.id":id	
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
		        	$("#content").val(data.content);
		        	$("#subject").val(data.subject);
		        	$("#createTime").val(data.createTime);
		        	var radio=document.getElementsByName("statuss");
					for(var i=0;i<radio.length;i++){
						if(radio[i].value==data.status){
							radio[i].checked=true;
						}
					}
					$("#dialog-confirm").dialog({
						resizable: false,
						top: 370,
						height:550,
						width:1024,
						modal: true,
						buttons:{
							'关闭':function(){
								$("#dialog-confirm").css("overflow","auto");
								$(this).dialog("close");
							},
							'修改信息':function(){
								update(id);
								
							}
						}
					});
		        }
			});
		}
		/*更新数据*/
		function update(id){
			var createTime=$.trim($("#createTime").val());
			var subject=$.trim($("#subject").val());
			var content="";//邮件内容
			var arr = [];
        	arr.push(UE.getEditor('editor').getContent());
     	   	content=$.trim(arr.join());
			var status=0;
			var radio=document.getElementsByName("statuss");
			for(var i=0;i<radio.length;i++){
				if(radio[i].checked){
					status=radio[i].value;
				}
			}
			
			if(subject.length==0||content.length==0){
				$( "#dialog-message" ).dialog({
					modal: true,
					height:90,
					width:230,
					resizable:false
				});
				return;
			}
			var actionUrl = "mail/birthday!update";
			var params={
					"birthdayDTO.id":id,
					"birthdayDTO.status":status,
					"birthdayDTO.subject":subject,
					"birthdayDTO.content":content,
					"birthdayDTO.createTime":createTime
			};
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
	        	success:function(data, textStatus){
	        		$("#dialog-confirm").dialog("close");
	        		$("#dialog-confirm").css("overflow","auto");
	        		query(1);
		        }			
			});
		}
		/*删除数据*/
		function deleteData(id){
			var actionUrl = "mail/birthday!delete";
			var params={
					"birthdayDTO.id":id
			}
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
	        	success:function(data, textStatus){
	        		query(1);
		        }
			});
		}
		/*初始化元素内容*/
		function init(){
			$("#subject").val("");
			$("#createTime").val("");
			var radio=document.getElementsByName("statuss");
			for(var i=0;i<radio.length;i++){
				if(radio[i].value==0){
					radio[i].checked=true;
				}
			}
		}
		function test(){
			$.ajax({
				async:false,
				url : "mail/birthday!sendMail",   
		        cache : false,   
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
	    			alert("系统ajax交互错误!");	    
	        	}, 
	        	success:function(data, textStatus){
		        }
			});

		}
	</script> 
</head>
<body onload="query(${birthdayDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 邮件发送&gt;&gt; 生日邮件管理
	</div>
	
	<div id="dialog-message" title="提示信息" style="display: none;">
	<p align="center" style="font-size: 130%">
		以上两项为必添项!
	</p>
	</div>
	
	
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div id="dialog-confirm" style="display: none;text-align: center;width: 1024px;"  title="生日邮件" >
	 <input type="hidden" id="createTime" />
				<table width="100%" border="0" bordercolor="#000000" cellpadding="0" cellspacing="0"
					class="formTable"  >
					<tr>
						<th style="text-align: center;">主题：</th>
						<td style="text-align:left;width: 10%;" id="subTd" >
							<input type="text" id="subject" colspan="3" style="width: 100%;" maxlength="200" />
						</td>
						<th style="text-align: center;">启用状态：</th>
						<td style="text-align:left;">
							<s:radio list="#request.mailStatus" name="statuss" listKey="key"  listValue="value" value="0" theme="simple"/>
						</td>
					</tr>
					</tr>
						<tr>
						<td colspan="4">
								<script id="editor" type="text/plain" style="width:100%;height:260px;"></script>
						</td>
					</tr>
				</table>
		</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
			<tr>
				<th width="3%">序号</th>
				<th width="10%"><a name="subjectt">主题</a></th>
				<th width="5%"><a name="contentt" >内容</a></th>
				<th width="10%"><a name="creatTimee" >创建时间</a></th>
				<th width="10%"><a name="status" >启用状态</a></th>
				<th width="8%"><a name="control" >操作</a></th>
			</tr>
		</table>
		<div class="listBottom">
			<div class="Fl">
				<s:if test="#session.user_session.userLevel!=2">
					<input type="button" class="formButton" value="添加邮件" onclick="showDialog();"/>
				</s:if>
		</div>
		<div class="listBottom">
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false"/>
			</div>
		</div>
</html>