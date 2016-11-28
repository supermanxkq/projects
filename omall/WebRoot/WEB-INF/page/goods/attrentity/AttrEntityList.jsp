<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<base href="<%=basePath%>" />
		<title>商品属性值管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
			type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.8.2.min.js"></script>
		<script src="js/jquery-easyui/jquery.easyui.min.js"></script>
		<script src="js/jquery.validate.js"></script>
		<script src="js/jquery.metadata.js"></script>
		<script src="js/additional-methods.min.js"></script>
		<script src="js/common.validate.js"></script>
		<link href="js/jquery/css/jquery.ui.all.css" rel="stylesheet"
			type="text/css" />
		<link href="js/jquery-easyui/easyui.css" rel="stylesheet"
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
		<script type="text/javascript">


	// 查询方法
	function query(page) {
		var attrId = $("#attrId").val();
		var params = {
			"goodsAttributeDTO.attrId" : attrId,
			"orderProperty" : $("#orderProperty").val(),
			"orderDirection" : $("#orderDirection").val(),
			"attrEntityDTO.page" : page
		};
		ajaxData("base/attrEntity!jsonPageListForAttrEntities", params);
	}

    //查看属性枚举值
    function detailData(id){
       var actionUrl = "base/attrEntity!viewData";
       var params={
					"attrEntityDTO.attrEnId":id
			}
			
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
	        			$("#d_attrEnName").val(data.attrEnName);
	        			$("#d_attrEnName").attr("disabled",true);
	        			$("#message").hide();
					  	$("#dialog-confirm").dialog({
						resizable: true,
						top: 290,
						height:230,
						width:360,
						modal: true,
						buttons: {
							'取消': function() {
								$(this).dialog('close');
							 }
						}
					});  
	        	}
			});
			
    }

	var existName = false;


  	//保存属性属性值方法
	function saveAttrEntity(){
		
	   var url = "base/attrEntity!saveAttrEntity";

	   var attrId = $("#attrId").val();
	   var attrEnId = $("#attrEnId").val();
	   var attrEnName = $("#d_attrEnName").val();	   
	   
	   checkAttrEnName();
	   
	   if(!existName){
	   	  return false;
	   }
	   
       var params = {
           "attrEntityDTO.attrId":attrId,
           "attrEntityDTO.attrEnId":attrEnId,
           "attrEntityDTO.attrEnName":attrEnName
       };
       
       ajaxRequest(url,params,"POST",function(data){
       	 	if(data.flag){
       	 		//query(1);  	
            	$("#d_attrEnName").val("");
            	$("#dialog-attribute").dialog("close");               	
            }
       });
	} 


	function checkAttrEnName(){
		   var attrId = $("#attrId").val();
		   var attrEnName = $("#d_attrEnName").val();
		    $("#message").hide();
		   if(attrEnName==""){
			   $("#message").html("请输入属性值名称!");
			   $("#message").show();
			   existName = false;
			   return false;
		   }else{
			   $("#message").hide();
		   }
		   var url = "base/attrEntity!checkAttrEnName";
           var params = {
        	   "attrEntityDTO.attrId":attrId,
               "attrEntityDTO.attrEnName":attrEnName
           };
           ajaxRequest(url,params,"POST",function(data){
           	 	if(data.flag){
           	 		$("#message").html("该属性值名称已存在!");
 			    	$("#message").show();
                    //alert("该属性值名称已存在!");
                    existName = false;
                }else{
                	$("#message").hide();
                    existName = true;
                }
           });
		}

		
		function ajaxRequest(url,params,method,callback){
			$.ajax({
               url : url,
               data : params,
               async:false,
               dataType : "json",
               type : method,
               cache : false,
               error:function(errText){
                  alert("ajax加载数据异常!请联系管理员");
               },
               success:callback
           });
		}

		//打开添加属性值窗口
		var openForm = function() {
		        	
	        var consoleDlg = $("#dialog-confirm");
	        $("#d_attrEnName").val("");
      
           	$("#dialog-confirm").dialog({
				resizable: true,
				top: 290,
				height:230,
				width:360,
				modal: true,
				buttons: {
					'取消': function() {
					    $("#message").hide();
						$(this).dialog('close');
					 },
					'确定添加':function(){
						saveAttrEntity();
						if(!existName){
							return false;
						}
						$(this).dialog('close');
						query(1);						
					 }
				}
			}); 
		};

    //停用
      function stopData(id){
	 var actionUrl = "base/attrEntity!drop";
			var params={
					"attrEntityDTO.attrEnId":id
			}
			if(confirm("是否确认停用？")){
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
			
	 }


	
</script>
	</head>
	<body onload="query(${attrEntityDTO.page});">

		<div class="Position">
			当前位置是：基本设置 &gt;&gt; 商品管理 &gt;&gt; 商品属性值管理
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<s:hidden name="#request.goodsAttributeDTO.attrId" id="attrId" />
		<s:hidden name="#request.goodsAttributeDTO.attrName" id="attrName" />
		<s:hidden name="attrEntityDTO.attrEnId" id="attrEnId" />
		<input type="hidden" name="method" id="methodId" />
		<div class="search">
			<div class="Fl">
				<my:permission key='sy-1706-02' value='添加属性值'>
					<input type="button" class="formButton" value="添加属性值"
						onclick="openForm();"/>
				</my:permission>
			</div>
		</div>
		<table width="96%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="10%">
					<a name="attrEnId" class="sort">属性值编号</a>
				</th>
				<th width="10%">
					<a name="attrEnName" class="sort">属性值名称</a>
				</th>
				<th width="10%">
					<a name="attrName" class="sort">所属属性</a>
				</th>
				<th width="5%">
				    <a>相关操作</a>
				</th>
			</tr>
		</table>
		<div class="listBottom">
			<div class="Fl">
				<my:permission key='sy-1706-02' value='添加属性值'>
					<input type="button" class="formButton" value="添加属性值"
						onclick="openForm();" />
				</my:permission>
				<input type="button" class="formButton" value="返回"
						onclick="go('base/attrEntity!list')" />
			</div>
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
		</div>
		
		
		
		<!-- 属性对话框 -->
	 	<div id="dialog-confirm" style="display: none;" title="添加属性值 ">
			<div style="float:left;padding-left:30px">
				所属属性:&nbsp;&nbsp;
				<span style="Color:red;font-weight:bold;font-size:18px">
					<s:property value="#request.goodsAttributeDTO.attrName"/>
				</span>
			</div>
			<div style="float:left;padding-top:20px;padding-left:30px">属性值名称:
				<input type="text" style="width:150px" onblur="checkAttrEnName();" cssClass="formInput" id="d_attrEnName" maxlength="30">
			</div>
			<div style="float:left;padding-top:20px;padding-left:30px">
				<label id="message" class="errorMsg" style="display: none;"></label>
			</div>					
		</div>
	</body>
</html>