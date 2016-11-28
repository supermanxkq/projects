<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>换卡管理</title>
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
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<script src="js/base/member.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script language="javascript" type="text/javascript" defer="defer" src="<%=basePath%>js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript">
	var dataBack;
	var flag=false;
	/**
	 *检验卡号的方法
	*/
	var queryCardNo =function(){
	  var oldNoView =$.trim($("#oldNoView").val());
	 
	 
	  if(oldNoView==''||oldNoView==null){
	    $("#cardStatusValue").html("卡号不能为空！");
	          removeInfo();
	    return false;
	  }
	  var params ={
	     "cardChangDTO.oldNoView" :oldNoView
	  }
	  var actionUrl ="card/change!queryCardNo";
	  $.ajax({
	      url :actionUrl,
	      data :params,
	      dataType :"json",
	      cache:false,
	      type :"POST",
	      error :function(textStatus,errorThrow){
	        alert("系统ajax交互错误！");
	      },
	      success:function(data,textStatus){
	       dataBack=data;
	       if(data.flag){
	         $("#cardStatusValue").html("");
	         $("#flag").val("1");
	       }else{
	         $("#cardStatusValue").html("此号未处于正常使用状态!");
	         $("#flag").val("0");
	       }
	       if(data.obj!=null && data.obj.holdmemId!="null"){
	         $("#merName").val(data.obj.merName);
	         $("#memIdNum").val(data.obj.memIdNum);
	         $("#binId").val(data.obj.binId);
	         $("#accId").val(data.obj.accId);
	         $("#cardNo").val(data.obj.cardNo);
	         $("#binIdValue").html("");
	         $("#newNoValue").html("");
	         $("#holdmemId").val(data.obj.holdmemId);
	         loadInfo(data.obj.cardNo);
	       }else{
	        removeInfo();
	         $("#cardStatusValue").html("此号没有绑定会员信息!");
	       }
	      }
	  });
	}
	/**
	*清除关联信息
	*/
	function removeInfo(){
	  $("#merName").val("");
	         $("#memIdNum").val("");
	         $("#binId").val("");
	         $("#accId").val("");
	         $("#balance").val("");
	         $("#newNoView").val("");
	         var tb =$("#accIdTb");
	          var tr = $("<tr></tr>");
       			 tb.find('tr:eq(0)').nextAll().remove();  
	}
	/**
	*加载账户信息的方法
	*
	*/
	function loadInfo(cardNo){
		var tb =$("#accIdTb");
      	tb.find('tr:eq(0)').nextAll().remove();  	
		var params = {
				        "cardChangDTO.cardNo" : cardNo
				    }; 
		 $.ajax({
			  url : "card/change!loadInfo", 
	          type : "POST",  
	          data : params, 
	          dataType:"json",
			  success : function(data){
			  dataBack=data;
			  	if (data.length>0){
	        		for(var i=0;i<data.length;i++) {
       					var tr = $("<tr></tr>");
       					var html = "";
       				
       					html += "<td>"+data[i].accId+"</td>";
       					html += "<td>"+data[i].accTypeName+"</td>";
       					html += "<td>"+data[i].balPoint+"</td>";
       					html+="<input type='hidden' name='cardChangDTO.accIds'  value='"+data[i].accId+"'/>";
       					html+="<input type='hidden' name='cardChangDTO.typeNames' value='"+data[i].accTypeID+"'/>";
       					html+="<input type='hidden' name='cardChangDTO.balances' value='"+data[i].balPoint+"' />";
       					tr.html(html);
	          			tb.append(tr);
       				}
       			}else{
       			 var tr = $("<tr></tr>");
       			 tb.find('tr:eq(0)').nextAll().remove();  
       			}
			  },
			   error:function(){
	             alert("系统ajax交互错误!");
	          }
		 });
	}
	/**
	 *刷新卡号调用的方法
	*/
         var loadBeginCardNo = function (data) {
		    	 var binId = $.trim($("#binId").val());
			     if(binId == null||binId.length==0){
	                     $("#binIdValue").html("卡BIN不能为空");
	                     $("#newNoValue").html("检查卡BIN!");
	                     return;
	             }else{
	               $("#binIdValue").html("");
	             }
				var params = {
				        "cardChangDTO.binId" : binId
				    }; 
			    
	             $.ajax({
	             	 async:false,
	                 url : "card/change!loadMinCardNo", 
	                 type : "POST",
	                 data : params, 
	                 dataType : "json",
	                 success : function(data){
	                     dataBack=data;                      
	                      var newNoView = data;
	                      /**顺序不能颠倒 if和 else if 是按顺序执行的*/
	                      if(newNoView=="null"){
	                      	$("#newNoView").val("");
	                      	alert("本卡号所属的卡BIN没有正常状态的卡号!!"); 
	                      }else if(newNoView!=""&&newNoView!=null){
	                      	$("#newNoView").val(newNoView.substring(9,newNoView.length-1));
	                      	$("#newNo").val(newNoView);
	                      }
	                 },
	                 error:function(){
	                      alert("系统ajax交互错误!");
	                 }
	              });
			    }
	    /**
	    *点击保存的方法
        */
	 var check = function(){
	
	   var oldNoView =$("#oldNoView").val();
	   
	   var newNoView =$("#newNoView").val();
	   var flag =$("#flag").val();
	   var binId =$("#binId").val();
	 
	    if(holdmemId.length==0){
		    alert("会员信息不正确，请核对信息");
		    $("#cardNo").focus();
		    return false;
		}
	   if(oldNoView==''||oldNoView==null){
	     alert("旧卡号不能为空");
	     return false;
	   }
	   if(newNoView ==''||newNoView==null){
	     alert("新卡号不能为空");
	     return false;
	   }
	   if(binId ==''||binId==null){
	     alert("卡BIN不能为空");
	     return false;
	   }
	   if(flag !="1"){
	    alert("请按照提醒内容修改");
	    return false;
	   }
	   
	  
	 }
	</script>
  </head>
   
  
  <body>
  <div class="Position">
		当前位置是：HOME &gt;&gt;卡信息管理 &gt;&gt; 换卡管理
	</div>
	<s:form action="card/change" method="post" onsubmit=" document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method"/>
	<s:hidden name="cardChangDTO.isSalePointStr" id="isSalePointStr"/>
	<input type="hidden" id="flag" />
	<input type="hidden"  id="cardNo"/>
	<input type="hidden" name="cardChangDTO.newNo" id="newNo" />
	<input type="hidden"  id="holdmemId"/>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
				
			        <th align="right" >
						<span class="Color5">* </span>旧卡号：
					</th>
					<td align="left" width="30%" >
						<input type="text" name="cardChangDTO.oldNoView" id="oldNoView"
							maxlength="6" class="formInput2" onblur="queryCardNo();" />
                       	<label id="cardStatusValue"></label>				
					</td>
		 		
		 		  <th align="right" width="20%">
						卡BIN：
					</th>
					<td align="left">
						<input name="cardChangDTO.binId" id="binId" 
							maxlength="20" class="formInput2" readonly  />
                        <label id="binIdValue"></label>
					</td>
		         
		           
		 		</tr>
		 		
		 		<tr>
		 		 <th align="right" width="20%">
						新卡号：
					</th>
					<td>
						<input type="text" name="cardChangDTO.newNoView" id="newNoView"
							maxlength="6" class="formInput2" " readonly/>
							<a onclick="loadBeginCardNo();" href="javascript:void(0);"><img
									src='images/flush.gif' /> </a>  <label id="newNoValue"></label>
					</td>
					
				  <th align="right">
						持卡人：
					</th>
					<td align="left">
						<input name="cardChangDTO.merName" id="merName" 
							maxlength="20" class="formInput2" readonly />

					</td>
					
				</tr>
		 		
		        <tr>
		        <th align="right">
						身份证号：
					</th>
					<td align="left">
						<input type="text" id="memIdNum" name="cardChangDTO.memIdNum"
							maxlength="20" class="formInput2" readonly/>
					</td>
				 
					<th align="right">
						<span class="Color5">* </span>换卡原因：
					</th>
					<td>
						<s:select name="cardChangDTO.chagReason" id="chagReason"
							list="#session.chagReasonValues" listKey="key" listValue="value"
							cssClass="formSelect" theme="simple" />
					</td>
				</tr>
		  
		
		<tr>
	      		<th align="right">账户信息：</th>
	      		<td colspan="3">
	      			<table id="accIdTb" width="96%" class="listTable" cellpadding="0" cellspacing="0">
	      				<tr>
	      					<th>账户号</th>
	      					<th>账户类型</th>
	      					<th>余额</th>
	      				</tr>
	      				<s:if test="#request.accIds!=null&&#request.accIds.size>0">
				    		<s:iterator value="#request.accIds" >
				    		<tr>
				    			<td><s:property value="accId"/></td>
				    			<td><s:property value="typeId"/></td>
				    			<td><s:property value="balance"/></td>
				    		</tr>
				    		</s:iterator>
				    	</s:if>
	      			</table>
	      		</td>
	     </tr>
	     	<tr>
		<th align="right">
						备注：
					</th>
					<td align="left" colspan="3">
						<textarea id="descr" class="formTextarea" cols="45"
							rows="5"></textarea>
					</td>
		</tr>
		</table>

	
 <div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-2110-02' value='换卡信息添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();" />
		 	</my:permission>
		</s:if>
		
		<input type="button" class="formButton" value="返 回" onclick="go('card/change!list')"/>
	 </div>
	 </s:form>
</body>
</html>