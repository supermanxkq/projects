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
	<title>会员消费积分规则管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
	<script src="js/datepicker/WdatePicker.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
	
	   var pointsNumFlag=false;
	   var regisPntsValueFlag=false;
	   var commPntsValueFlag=false;
	   var silverPntsValueFlag=false;
	   var goldPntsValueFlag=false;
	   var diamondPntsValueFlag=false;
	   
	   //验证兑换积分数
	   function pointsNumBlur(obj){
	       var type=["isNull"];
		   var errorMsg=["积分兑换数不能为空 ！"];
		   pointsNumFlag = showErrorMsg(obj,type,errorMsg,"pointsNumErrorMsg");
		   return pointsNumFlag;
	   }
	   
	   //验证注册会员积分倍数不能为空
	   function regisPntsValueBlur(obj){
	   
	       var regFlg1 = false;
	       var regFlg2 = false;
	   
	       var type=["isNull","isDoubleRate"];
		   var errorMsg=["请填写积分倍数 ！","积分倍数格式错误！"];
		   regFlg1=showErrorMsg(obj,type,errorMsg,"regisPntsValueErrorMsg","regisPntsValueErrorMsg");
		   
		   var regisPntsValue=obj.value;
		   
	      if(regisPntsValue>=100){
	        $("#regisPntsValueErrorMsg").show();
	    	$("#regisPntsValueErrorMsg").addClass("errorMsg");
			$("#regisPntsValueErrorMsg").html("积分倍数应小于100！");
			regFlg2 = false;
	       }else{
	        regFlg2 = true;
	       }

           if(regFlg1 && regFlg2){
              regisPntsValueFlag = true;
           }
           else{
              regisPntsValueFlag = false;
           }   
		   
		   return regisPntsValueFlag;
	   }
	   
	   //验证普通会员积分倍数不能为空
	   function commPntsValueBlur(obj){
	       var comFlg1=false;
	       var comFlg2=false;
	       
	       var type=["isNull","isDoubleRate"];
		   var errorMsg=["请填写积分倍数 ！","积分倍数格式错误！"];
		   comFlg1=showErrorMsg(obj,type,errorMsg,"commPntsValueErrorMsg","commPntsValueErrorMsg");
		    var commPntsValue=obj.value;
		   
	      if(commPntsValue>=100){
	        $("#commPntsValueErrorMsg").show();
	    	$("#commPntsValueErrorMsg").addClass("errorMsg");
			$("#commPntsValueErrorMsg").html("积分倍数应小于100！");
			comFlg2 = false;
	       }
	       else{
	         comFlg2 = true;
	       }
           if(comFlg1 && comFlg2){
              commPntsValueFlag = true;
           }
           else{
              commPntsValueFlag = false;
           }
           	
		   return commPntsValueFlag;
	   }
	   
	    //验证白银会员积分倍数不能为空
	   function silverPntsValueBlur(obj){
	       var silFlg1=false;
	       var silFlg2=false;
	       var type=["isNull","isDoubleRate"];
		   var errorMsg=["请填写积分倍数 ！","积分倍数格式错误！"];
		   silFlg1=showErrorMsg(obj,type,errorMsg,"silverPntsValueErrorMsg","silverPntsValueErrorMsg");
		   
		  var silverPntsValue=obj.value;
	      if(silverPntsValue>=100){
	        $("#silverPntsValueErrorMsg").show();
	    	$("#silverPntsValueErrorMsg").addClass("errorMsg");
			$("#silverPntsValueErrorMsg").html("积分倍数应小于100！");
			silFlg2 = false;
	       }else{
	        silFlg2 = true;
	       }

           if(silFlg1 && silFlg2){
              silverPntsValueFlag = true;
           }
           else{
              silverPntsValueFlag = false;
           }
           		   
		   
		   return silverPntsValueFlag;
	   }
	   
	    //验证黄金会员积分倍数不能为空
	   function goldPntsValueBlur(obj){
	       var golFlg1=false;
	       var golFlg2=false;
	       var type=["isNull","isDoubleRate"];
		   var errorMsg=["请填写积分倍数 ！","积分倍数格式错误！"];
		   golFlg1=showErrorMsg(obj,type,errorMsg,"goldPntsValueErrorMsg","goldPntsValueErrorMsg");
		   
		   var goldPntsValue=obj.value;
	       if(goldPntsValue>=100){
	        $("#goldPntsValueErrorMsg").show();
	    	$("#goldPntsValueErrorMsg").addClass("errorMsg");
			$("#goldPntsValueErrorMsg").html("积分倍数应小于100！");
			golFlg2 = false;
	       }else{
	        golFlg2=true;
	       }

           if(golFlg1 && golFlg2){
              goldPntsValueFlag = true;
           }
           else{
              goldPntsValueFlag = false;
           }
           		   
		   
		   return goldPntsValueFlag;
	   }
	   
	   //验证钻石会员积分倍数不能为空
	   function diamondPntsValueBlur(obj){
	       var diaFlg1=false;
	       var diaFlg2=false;
	       var type=["isNull","isDoubleRate"];
		   var errorMsg=["请填写积分倍数 ！","积分倍数格式错误！"];
		   diaFlg1=showErrorMsg(obj,type,errorMsg,"diamondPntsValueErrorMsg","diamondPntsValueErrorMsg");
		   var diamondPntsValue=obj.value;
	       if(diamondPntsValue>=100){
	        $("#diamondPntsValueErrorMsg").show();
	    	$("#diamondPntsValueErrorMsg").addClass("errorMsg");
			$("#diamondPntsValueErrorMsg").html("积分倍数应小于100！");
			diaFlg2 = false;
	       }else{
	        diaFlg2 = true;
	       }

           if(diaFlg1 && diaFlg2){
              diamondPntsValueFlag = true;
           }
           else{
              diamondPntsValueFlag = false;
           }
		   return diamondPntsValueFlag;
	   }
	   
	   //保存时对所校验信息检查是否正确填写
       function check(){
          var pointsNum=document.getElementById("pointsNum");
          var regisPntsValue=document.getElementById("regisPntsValue");
          var commPntsValue=document.getElementById("commPntsValue");
          var silverPntsValue=document.getElementById("silverPntsValue");
          var goldPntsValue=document.getElementById("goldPntsValue");
          var diamondPntsValue=document.getElementById("diamondPntsValue");
          
          pointsNumBlur(pointsNum);
          regisPntsValueBlur(regisPntsValue);
          commPntsValueBlur(commPntsValue);
          silverPntsValueBlur(silverPntsValue);
          goldPntsValueBlur(goldPntsValue);
          diamondPntsValueBlur(diamondPntsValue);
          if(pointsNumFlag && regisPntsValueFlag && commPntsValueFlag
            && silverPntsValueFlag &&goldPntsValueFlag && diamondPntsValueFlag){
             return true;
          }else{
            alert("页面信息填写有误，请按照提示进行修改！");
             return false;
          }
       }
	</script> 
</head>
<body>
	
	<div class="Position">
		当前位置是：HOME &gt;&gt; 会员管理 &gt;&gt; 会员消费积分规则
	</div>
	<s:form action="member/memConsPntsRule" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		   	<tr>
		   		<th width="30%" align="right"><span class="Color5">* </span>积分兑换比例</th>
		        <td>
		            <s:hidden name="memConsPntsRuleDTO.mcpId" id="mcpId"/>
			        <s:textfield name="memConsPntsRuleDTO.moneyNum" id="moneyNum" onkeyup = "replaceToNum(this);"  cssStyle="width:195px;" value='1' maxlength="6" disabled="true" cssClass="formInput"/> 
			         : 
			        <s:textfield name="memConsPntsRuleDTO.pointsNum" id="pointsNum" onkeyup = "replaceToNum(this);"  cssStyle="width:195px;"  maxlength="6" cssClass="formInput" onblur="pointsNumBlur(this);"/> 
		            <label id="pointsNumErrorMsg" style="display: none;" class="errorMsg"></label>
		        </td>
		   	</tr>
	    
		   	</table>
	         <div align="left" style="padding: 50px; border: solid;border-color: #F2F2F2 ;border-width: thin">
	    <font color="#ff9900" >
	                系统提示：
	                </p>
	                </p>
              1、   积分兑换比例填写格式为1元人民币等于多少积分，如1元人民币等于100积分，此处填写1:100。
              </p>
              </p>
              2、不同会员级别之间的积分倍数格式为00.00，且积分倍数不能大于100。
                                              
	    </font>
	 </div>
		   	<div class="List_tit">设置购买同一物品，不同会员级别之间的积分倍数：</div>
              <table width="96%" id="MembersTb" class="listTable" cellpadding="0" cellspacing="0">
		   	<tr>
		      	<th>会员级别</th>	
		      	<th>积分倍数</th>	      	
		   	</tr>
		   	<tr>
		   	    <th>注册会员</th>
		   	    <td>
		   	    <input name="memConsPntsRuleDTO.regisPntsValue" id="regisPntsValue" maxlength="5"
		   	     value="${memConsPntsRuleDTO.regisPntsValue }" onkeypress="onlyNumFloadt(this);"
		   	     class="xhx" type="text"  onblur="regisPntsValueBlur(this);"/>倍
		   	     <label id="regisPntsValueErrorMsg" style="display: none;" class="errorMsg"></label>
		   	    </td>
		   	</tr>
		   	<tr>
		   	    <th>普通会员</th>
		   	    <td>
		   	    <input name="memConsPntsRuleDTO.commPntsValue" id="commPntsValue" maxlength="5"
		   	     value="${memConsPntsRuleDTO.commPntsValue }" onblur="commPntsValueBlur(this);"
		   	     class="xhx" type="text" onkeypress="onlyNumFloadt(this);"/>倍
		   	     <label id="commPntsValueErrorMsg" style="display: none;" class="errorMsg"></label>
		   	    </td>
		   	</tr>
		   	<tr>
		   	    <th>白银会员</th>
		   	    <td>
		   	    <input name="memConsPntsRuleDTO.silverPntsValue" id="silverPntsValue" maxlength="5"
		   	     value="${memConsPntsRuleDTO.silverPntsValue }" onblur="silverPntsValueBlur(this);"
		   	     class="xhx" type="text" onkeypress="onlyNumFloadt(this);">倍
		   	     <label id="silverPntsValueErrorMsg" style="display: none;" class="errorMsg"></label>
		   	    </td>
		   	</tr>
		   	<tr>
		   	    <th>黄金会员</th>
		   	    <td>
		   	    <input name="memConsPntsRuleDTO.goldPntsValue" id="goldPntsValue" maxlength="5"
		   	     value="${memConsPntsRuleDTO.goldPntsValue }" onblur="goldPntsValueBlur(this);"
		   	     class="xhx" type="text" onkeypress="onlyNumFloadt(this);"/>倍
		   	     <label id="goldPntsValueErrorMsg" style="display: none;" class="errorMsg"></label>
		   	    </td>
		   	</tr>
		   	<tr>
		   	    <th>钻石会员</th>
		   	    <td>
		   	    <input name="memConsPntsRuleDTO.diamondPntsValue" id="diamondPntsValue" maxlength="5"
		   	     value="${memConsPntsRuleDTO.diamondPntsValue }" onblur="diamondPntsValueBlur(this);"
		   	     class="xhx" type="text" onkeypress="onlyNumFloadt(this);"/>倍
		   	     <label id="diamondPntsValueErrorMsg" style="display: none;" class="errorMsg"></label>
		   	    </td>
		   	</tr>
		</table>
		<div class="formTableBottom">
					<my:permission key='sy-1307-01' value='会员消费积分规则添加'>
						<input id="submitInput" type="submit" class="formButton"
							value="保 存" onclick="return check();" />
					</my:permission>
				<input type="button" class="formButton" value="返 回"
					onclick="go('member/memConsPntsRule!list')" />
		</div> 
	 </s:form>
</body>
</html>