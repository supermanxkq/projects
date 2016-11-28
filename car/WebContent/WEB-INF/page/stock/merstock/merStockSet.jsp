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
	<title>商户领卡</title>
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

	<script type="text/javascript">

	    //卡BIN OnChange事件 
	    function changeBin(){
                 $("#beginCardNo").val("");
                 $("#cardCount").val("");
                 
                 var organId = $("#organId").val();
 	               if(organId=="-1"){
 	 	             $("#cardBinNo").val("-1");
                     alert("请选择机构!");
                     return false;
 	   	            }
		    }
	    //根据机构编号加载卡BIN信息
	    function loadCardBin(){
              var organId = $("#stockSsOrganId").val();
              actionUrl = "stock/orgcard!loadCardBin";
              $.ajax({
                   url:actionUrl,
                   type:"POST",
                   data:"modStockDTO.organId="+organId,
                   dataType:"json",
                   success:function(data){
                     $("#cardBinNo").empty();
 	            	$('#cardBinNo').append("<option id='' value='-1'>请选择</option>");
 	            	if (data.length>0){
 		        		for(var i=0;i<data.length;i++) {
 		        			$('#cardBinNo').append("<option id='' value='"+data[i].key+"'>"+data[i].value+"</option>");
 		        		}
 		        	}
                   },
                   error:function(data){
                       alert("系统ajax交互错误!");
                   }

                  });
		    }
	    //加载起始卡号
	    var loadBeginCardNo = function () {
             var cardBinNo = $("#cardBinNo").val();
             var organId = $("#stockSsOrganId").val();
             
             if(cardBinNo == "-1"){
                  alert("请选择要入库的卡BIN类型!");
                 }
             else{
                 $.ajax({
                 url:"stock/mercard!loadBeginCardNo",
                 type:"POST",
                 data:"modStockDTO.cardBinNo="+cardBinNo
                       +"&modStockDTO.organId="+organId, 
                 dataType:"json",
                 success:function(data){                         
                      var cardNo = data;
                      if(cardNo!=""&&cardNo!=null){
                        $("#beginCardNo").val(cardNo);
                      }
                      else{
                           alert("没有要入库的卡号!"); 
                       }
                      
                 	},
                 error:function(){
                      alert("系统ajax交互错误!");
                	}
              	});
                }            
		    }
	    //cardNo onBlur事件
	    function checkBin(){
              var cardNo = $("#beginCardNo").val();
              var cardBinNo = $("#cardBinNo").val();
              var cardBin = cardNo.substr(0,6);
              
              if(cardBinNo==-1){
                     alert("请选择卡BIN!");
                     $("#beginCardNo").val("");
                     return false;
                  }
              
              if(cardNo!=""&&cardBinNo!=cardBin){
                      alert("此卡号不输于所选卡BIN，请重新输入!");
                      $("#beginCardNo").val("");
                      $("#beginCardNo").focus();
                      return false;
                  }
		    }
	    
	    //添加卡信息
	    var appendCardNo = function(){
		       var cardBinNo = $("#cardBinNo").val();
	    	   var cardCount = $("#cardCount").val();
	    	   var beginCardNo = $("#beginCardNo").val();
	    	   var endNos = $('[name=modStockDTO.endNos]');//获取所有结束卡号
	    	   var endNoStr="";
	    	   for(var i=0;i<endNos.length;i++){
                     endNoStr = endNoStr+";"+endNos[i].value;
		    	   }
	    	   if(beginCardNo==""){
                      alert("请选择起始卡号!");
                      return false;
		    	   }
	    	   if(cardCount==""||cardCount==0){
                      alert("请填写数量!");
                      return false;
		    	   }
	    	   var params ={
                       "modStockDTO.beginCardNo":beginCardNo,
                       "modStockDTO.cardCount":cardCount,
                       "modStockDTO.cardBinNo":cardBinNo,
                       "modStockDTO.endNoStr":endNoStr
	    	         }
	    	   var actionUrl = "stock/orgcard!appendCardNo";
	    	   $.ajax({
                      url:actionUrl,
                      data:params,
                      dataType:"json",
                      cache:false,
                      type:"POST",
                      success:function(data,textStatus){
                          if(data.flag){
                        	var row = Math.ceil(Math.random( )*1000000);
      						var tr = $("<tr id='r_"+row+"'></tr>");
      	        			var html = "";
      	        			html += "<td width='20%'>"+data.obj.binName+"<input type='hidden' name='modStockDTO.cardBinNos' value='"+data.obj.cardBinNo+"'/></td>";
      	        			html += "<td width='20%'>"+data.obj.beginCardNo+"<input type='hidden' name='modStockDTO.beginCardNos' value='"+data.obj.beginCardNo+"'/></td>";
      	        			html += "<td width='20%'>"+data.obj.endNo+"<input type='hidden' name='modStockDTO.endNos' value='"+data.obj.endNo+"'/></td>";
      	        			html += "<td width='20%'>"+data.obj.cardCount+"<input type='hidden' name='modStockDTO.cardCounts' value='"+data.obj.cardCount+"'/></td>";
      	        			html += '<td width=\"20%\"><a href="javascript:RemoveRow(\'r_'+row+'\');">[移除]</a></td>';
      	              		tr.html(html);
      	              		$("#cardInfoTb").append(tr);
                           }
                          else{
                              alert(data.msg);
                              }  
                       },
                      error:function(){
                          alert("系统ajax交互错误!");
                       }

		    	   });
		   }
		   function RemoveRow(rowId){
                    $("#"+rowId).remove();
			   }
		//修改方法
		var check = function() {
			var endNos = $('[name=modStockDTO.endNos]');//获取所有结束卡号
			var merId = $("#merId").val();

			if(endNos.length<=0){
              alert("没有要保存的信息!");
              return false;
			}

			if(merId == "" || merId ==null){
	               alert("请选择领卡商户!");
	               return false;
			    }
		}
</script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 库存管理 &gt;&gt; 商户领卡
	</div>
	<jsp:include page="/WEB-INF/page/base/terminals/mercHelps.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/page/base/organs/organHelp.jsp"></jsp:include>
	<s:form action="stock/mercard" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method"/>
	<s:if test="#session.user_session.userLevel==0">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
			<tr>
			    <th align="right" width="20%"><span class="Color5">* </span>所属机构：</th>
		       <td width="30%"><s:if test="method=='addSave'&&#session.user_session.userLevel==0">
			        	       <s:select name="modStockDTO.organId" id="stockSsOrganId" list="#request.organsValues"  listKey="key" listValue="value" headerKey="-1" headerValue="请选择" onchange="loadCardBin();" cssClass="formSelect"/>
		        	          </s:if>
		        </td>
		       <th align="right" width="20%"><span class="Color5">* </span>领卡商户：</th>
		       <td width="30%"><%--<s:if test="method=='addSave'&&#session.user_session.userLevel==0">
			        	       <s:select name="modStockDTO.organId" id="organId" list="#request.organsValues"  listKey="key" listValue="value" headerKey="-1" headerValue="请选择" onchange="loadCardBin();" cssClass="formSelect"/>
		        	          </s:if>
		        --%>
		        <s:textfield id="merName" name="modStockDTO.merName" cssClass="formInput" theme="simple"/>
		 		<s:hidden name="modStockDTO.merId" id="merId">
		 		</s:hidden><img alt="查找商户" src="images/search.gif" style="cursor:pointer;" onclick="ajaxMerc();"/><label id="merIdErrorMsg" style="display: none"></label>
		 		</td>
		  		
			</tr>
		 	<tr>
		 	  <th align="right" width="20%"><span class="Color5">* </span>卡BIN：</th>
		      <td width="30%"><s:select name="modStockDTO.cardBinNo" id="cardBinNo" headerValue="请 选 择" headerKey="-1" list="#request.binsList" listKey="key" listValue="value" onchange="changeBin();" cssClass="formSelect" theme="simple"/></td>
		 	  <th align="right" width="20%"><span class="Color5">* </span>起始卡号：</th>
		        <td width="30%"><s:textfield name="modStockDTO.beginCardNo" id="beginCardNo" onblur="checkBin();" cssClass="formInput" theme="simple"/>
		         <a href=javascript:loadBeginCardNo();><img src='images/flush.gif'/></a>
		        </td>
		  	</tr>
		  	<tr>
		  	   <th align="right"><span class="Color5">* </span>数量：</th>
		 		<td colspan="3">
		 		<s:textfield name="modStockDTO.cardCount" id="cardCount" cssStyle="width:50px;" theme="simple"/>
		 		<input  type="button" class="formButton" value="添加" onclick="appendCardNo()"/>
		 		</td>
		  	</tr>
		</table>
			<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="10%">卡BIN</th>
			<th width="10%">开始卡号</th>
			<th width="10%">结束卡号</th>
			<th width="10%">数量</th>
			<th width="10%">相关操作</th>
		</tr>
	</table>
	<div class="cardNoArea"> 
	<table id="cardInfoTb" class="listTable" width="100%">
	
	</table>
	</div>
	</s:if>
	
	 <div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-2202-02' value='机构领卡添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('stock/orgcard!list')"/>
	 </div>
	 </s:form>
</body>
</html>