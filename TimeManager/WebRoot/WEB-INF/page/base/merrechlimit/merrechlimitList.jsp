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
	<title>商户充值限额</title>
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
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">

	//查询方法
	function query(page) {
		var merId = $.trim($("#merId").val());
		var params = {
			"merLimitDTO.merId" : merId,
	        "orderProperty" : $("#orderProperty").val(),
	        "orderDirection" : $("#orderDirection").val(),
	        "merLimitDTO.page" : page
	    }; 
	   ajaxData("base/merrechlimit!jsonPageList",params);
	}
	
	    //初始化DIV显示结果 
		function resetShowDiv(limitType){
			 if(limitType=="0"){
				 $("#guding").css("display","block");
				 $("#linghuo").css("display","none");
           	     $("#guding").show();
                 $("#linghuo").hide();
                }
            else{
            	 $("#guding").css("display","none");
				 $("#linghuo").css("display","block");
           	     $("#guding").hide();
                 $("#linghuo").show();
                }
			}
		

		//加载修改页面 
		function editLimit(id){
			var limitType = $("#limitType").val();
			var params = {
					"merLimitDTO.merId":id  
					};
			var actionUrl = "base/merrechlimit!editData";
			var condition ={};
			
			$.ajax({
                  url:actionUrl,
                  data:params,
                  dataType:"json",
                  cache:false,
                  type:"POST",
                  error:function(data,status){
                   alert("系统加载商户限额信息失败");  
                 },
                  success:function(data){
                	 resetShowDiv(data.limitType);
                     var showModify = $("#show_Modify");
                     
                     showModify.find("#rechLimId").val(data.rechLimId);
                     showModify.find("#merName").val(data.merName);
                     showModify.find("#merId").val(data.merId);
                     showModify.find("#limitType").val(data.limitType);
                     showModify.find("#rechargeLimit").val(data.rechargeLimit);
                     showModify.find("#dayRecLimit").val(data.dayRecLimit);
                     showModify.find("#monthReclLimit").val(data.monthReclLimit);
                     showModify.find("#oddRecLimit").val(data.oddRecLimit);
                 }
				});
			$("#show_Modify").dialog({
				resizable: true,
				top: 240,
				height:400,
				width:800,
				modal: true,
				title:"商户充值限额设置",
				buttons: {
				    
					'取消': function() {
				        
						$(this).dialog('close');
					},
			        '保存':function(){
			        if(limitType==1){
			        //oddRechLimitByCardBin;
			        checkOddRechLimitByCardBin();
			        checkOddRecLimit();
			        checkMonthReclLimit();
			        checkDayRecLimit();
			        checkRechargeLimit();
			        	if(!(checkOddRechLimitByCardBin()&&checkOddRecLimit()&&checkMonthReclLimit()&&checkDayRecLimit())){
							alert("请输入正确的信息，带*号的为必填项");
							return false;
						}else{		
							updateRechLimit();//保存修改的商户限额信息
						}
			        }else{
			        	updateRechLimit();
			        }
				    }
				}
		});
			
		}
		
		var rechargeLimitFlag = true;
		var dayRecLimitFlag = true;
		var monthReclLimitFlag = true;
		var oddRecLimitFlag = true;
		//var oddRechLimitByCardBin = true;
		var reg = /^[0-9]+(.[0-9]{2})?$/;

		var checkRechargeLimit = function(){
			var rechargeLimit = $("#rechargeLimit").val();
			if (rechargeLimit==''){
				alert("商户充值限额不能为空,无限额请填0");
			 	return false;
			}else if(!reg.test(rechargeLimit)){
				alert("请输入商户充值限额，整数或两位小数!");
				return false;
			}else{
				return true;
			}
			
		} 
		var checkDayRecLimit = function(){
			var dayRecLimit = $("#dayRecLimit").val();
            if (dayRecLimit==''){
				alert("日充值限额不能为空,无限额请填0");
				return false;
			}else if(!reg.test(dayRecLimit)){
				alert("请输入日充值限额，整数或两位小数!");
				return false;
			}else{
				return true;	
			}
		}
		var checkMonthReclLimit = function(){
			var monthReclLimit = $("#monthReclLimit").val();
			if (monthReclLimit==''){
				alert("月充值限额不能为空,无限额请填0");
				return false;
			}else if(!reg.test(monthReclLimit)){
				alert("请输入9位月充值限额，整数或两位小数!");
				return false;
			}else{
				return true;
			}
		}	
		var checkOddRecLimit = function(){
			var oddRecLimit = $("#oddRecLimit").val();
			//alert(1);
			//checkOddRechLimitByCardBin();
			if (oddRecLimit==''){
				alert("单笔充值限额不能为空,无限额请填0");
				return false;
			}else if(!reg.test(oddRecLimit)){
				alert("请输入单笔充值限额，整数或两位小数!");
				return false;
			}else{
				return true;
			}
		}

		//根据卡BIN检查商户充值限额-单笔充值
		var checkOddRechLimitByCardBin = function(){
			var oddRecLimit = $("#oddRecLimit").val();
			var merId = $("#merId").val();
			var rechLimId = $("#rechLimId").val();
			var actionUrl ="base/merrechlimit!checkOddRechLimit";
			var params={
                      "merLimitDTO.oddRecLimit":oddRecLimit,
                      "merLimitDTO.rechLimId":rechLimId,
                      "merLimitDTO.merId":merId
					}
			$.ajax({
                     url:actionUrl,
                     data:params,
                     cache:false,
                     type:"POST",
                     dataType:"json",
                     success:function(data){
                         if(!data.flag){
                             alert(data.msg);
                             //oddRechLimitByCardBin = false;
                             return false;
                           }
                           //oddRechLimitByCardBin = true;
                      }
				  });
			}
		
        //修改充值限额方法
		function updateRechLimit(){
			  var merId = $("#merId").val();
			  var limitType = $("#limitType").val();
			  var rechargeLimit = $("#rechargeLimit").val();
			  var dayRecLimit = $("#dayRecLimit").val();
			  var monthReclLimit = $("#monthReclLimit").val();
			  var oddRecLimit = $("#oddRecLimit").val();
			  var rechLimId = $("#rechLimId").val();	
			  var showModify = $("#show_Modify");
			  if(limitType==1){
			  	if(!(parseFloat(monthReclLimit)>=parseFloat(dayRecLimit)&&parseFloat(dayRecLimit)>=parseFloat(oddRecLimit))){
			  		alert("信息添写有误，月充值>=日充值>=单笔充值！");
			  		return;
			  	}
			  }
			  var params = {
					  "merLimitDTO.merId":merId,
					  "merLimitDTO.rechargeLimit":rechargeLimit,
					  "merLimitDTO.limitType":limitType,
					  "merLimitDTO.dayRecLimit":dayRecLimit,
					  "merLimitDTO.monthReclLimit":monthReclLimit,
					  "merLimitDTO.oddRecLimit":oddRecLimit,
					  "merLimitDTO.rechLimId":rechLimId
					  }
              var actionUrl = "base/merrechlimit!editSave";
              	if(rechargeLimitFlag&&dayRecLimitFlag&&monthReclLimitFlag&&oddRecLimitFlag){

	              $.ajax({
	                      url:actionUrl,
	                      data:params,
	                      cache:false,
	                      type:"POST",
	                      error:function(){
	            	            alert("修改商户限额信息失败!"); 
	                          },
	                       success:function(data){
	                           alert("修改成功!"); 
	                           
	                           showModify.dialog('close');
	                           query($("#currPage").text());
	                         }
	 
	                  });
            	}
			}

		//根据限制类型显示不同限制信息 ：limitType下拉框onChange触发
		function changeDiv(){
                  var limitType = $("#limitType").val();
                  if(limitType=="0"){
                         $("#guding").show();
                         $("#linghuo").hide();
                      }
                  else
                      {
                         $("#guding").hide();
                         $("#linghuo").show();
                      }
			}
	</script> 
</head>
<body onload="query(${merLimitDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 基本信息 &gt;&gt; 商户充值限额
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>商户编号:</td>
				<td><s:textfield id="merId" name="merLimitDTO.merId" cssClass="formInput" maxlength="20" theme="simple"/></td>
			    <td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="8%"><a name="merchants" class="sort">商户编号</a></th>
			<th width="15%"><a name="merchants" class="sort">商户名称</a></th>
			<th width="5%"><a name="status" class="sort">类型</a></th>
			<th width="8%"><a name="rechargeLimit" class="sort">商户充值限额</a></th>
			<th width="8%"><a name="dayRecLimit" class="sort">日充值限额</a></th>
			<th width="8%"><a name="monthReclLimit" class="sort">月充值限额</a></th>
			<th width="8%"><a name="oddRecLimit" class="sort">单笔充值限额</a></th>
			<th width="10%"><a name="updateTime" class="sort">更新时间</a></th>
			<th width="5%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
	<div id="show_Modify" style="display: none;">
	<s:hidden name="merLimitDTO.rechLimId" id="rechLimId"></s:hidden>
	    <table class="formTable">
	        <tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>商户：</th>
		        <td width="30%"><s:textfield name="merLimitDTO.merName" id="merName" readonly="true" maxlength="20" cssClass="formInput" theme="simple"/><s:hidden name="merLimitDTO.merId" id="merId"></s:hidden></td>
		        <th align="right" width="20%"><span class="Color5">* </span>类型：</th>
		        <td width="30%"><s:select name="merLimitDTO.limitType" id="limitType" onchange="changeDiv();" list="#request.limitType" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/></td>
			</tr>
	    </table>
	    <div id="guding" style="display: block;">
	     <table class="formTable">
	           <tr>
		  		<th align="right" width="20%"><span class="Color5">* </span>商户充值限额：</th>
		        <td colspan="3"><s:textfield name="merLimitDTO.rechargeLimit" id="rechargeLimit" maxlength="7" cssClass="formInput"  cssStyle="width: 85px;"  theme="simple" onblur="return checkRechargeLimit();"/> (元/总计)</td>
			    
			   </tr>
	    </table>
		</div>
			<div id="linghuo" style="display: none;">
			<table class="formTable">
	           <tr>
		  		  <th align="right" width="20%"><span class="Color5">* </span>日充值限额：</th>
		          <td width="30%"><s:textfield name="merLimitDTO.dayRecLimit" id="dayRecLimit"  maxlength="10" cssClass="formInput"  cssStyle="width: 85px;" theme="simple" onblur="return checkDayRecLimit();"/> (元)</td>
		          <th align="right" width="20%"><span class="Color5">* </span>月充值限额：</th>
		          <td width="30%"><s:textfield name="merLimitDTO.monthReclLimit" id="monthReclLimit" maxlength="10" cssClass="formInput" cssStyle="width: 85px;"  theme="simple" onblur="return checkMonthReclLimit();"/> (元)</td>
			    </tr>
			    <tr>
		  	  	  <th align="right" width="20%"><span class="Color5">* </span>单笔充值限额：</th>
		          <td width="30%"><s:textfield name="merLimitDTO.oddRecLimit" id="oddRecLimit" maxlength="10" cssClass="formInput" cssStyle="width: 85px;"  theme="simple" onblur="return checkOddRecLimit();"/> (元)</td>
			     </tr>
	           </table>
            </div>
            	 <p/>
	 <p/>
	 <div align="left" style="padding: 50px; border: solid;border-color: #F2F2F2 ;border-width: thin">
	    <font color="#ff9900" >
	                系统提示：
	                <p />
	                1.设置灵活充值限额时，单笔充值限额不能大于日充值限额，日充值限额不能大于月充值限额；
	               
	                <p/>
	                2.充值限额不能大于其允许消费卡BIN的充值限额；
	    </font>
	    
	 </div>
            
	</div>
</body> 
</html>