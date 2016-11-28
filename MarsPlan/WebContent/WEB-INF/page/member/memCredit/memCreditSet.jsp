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
	<title>会员积分规则</title>
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
	<script src="js/common.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">

		function goHome() {
			parent.leftFrame.location.href='<%=basePath%>system/index!firstleft';
			parent.main.location.href='<%=basePath%>system/index!right';
			}
		
		//查看方法输入框禁用
		$(document).ready(function (){
	        var method = document.getElementById("method"); 	       
	        if(method.value=='checkUI'){
	             setInputDisabled();
	            }
	        });
	
		
		gradeFlag = false;
		//consPointsFlag = false;
		loginPoinsFlag = false;
		rateFlag = false;
		graPointsFlag = false;
		upperLimitFlag = false;
		
		//获取选中的模版的数据	
		var validateGrade = function(obj){
			 var flag = false;
             var type = ["isSelectNull"];
             var errorMsg = ["请选择会员等级"];
             //检验分类名称格式
             flag = showErrorMsg(obj,type,errorMsg,"errorMsg","errorMsg");
				
				var gradeId = $("#gradeId").val();
				var creditId = $("#creditId").val();
				if (!flag){
			    	return false;
				}
			    else{
		    	var params = {
		    			"memCreditDto.gradeId" : gradeId,
		    			"memCreditDto.creditId" : creditId
				    }; 
					
				var actionUrl = "member/memCreditRule!checkName";   
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
				    success : function(data, textStatus) {   
				    	if (data.flag) {
				    		var type = ["isRate"];
	           				var errorMsg = ["该等级积分规则已存在!"];
				    		showErrorMsg(obj,type,errorMsg,"errorMsg","errorMsg");
					    	gradeFlag = false;
						}else {								
					    	gradeFlag = true;					    	
					 	}
					}	
					});	
				return gradeFlag;	    	
				}
			}

		
		//校验消费积分	
		//var validateConsP = function(obj){
		//	var flag = false;
        //    var type = ["isNull"];
        //    var errorMsg = ["消费积分不能为空!"];
        //    flag = showErrorMsg(obj,type,errorMsg,"consMsg","consMsg");
        //    if(flag){
        //    	var type = ["isAmt"];
        //        var errorMsg = ["积分格式输入有误!"];
        //        consPointsFlag = showErrorMsg(obj,type,errorMsg,"consMsg","consMsg");
        //    } 
        //    return consPointsFlag;           
        //}
        

		//校验登录领积分	
		var validateLogin = function(obj){
            var type = ["isNull","isIntDec"];
            var errorMsg = ["登录领取积分不能为空!","积分格式输入有误!"];
            loginPoinsFlag = showErrorMsg(obj,type,errorMsg,"consMsg","consMsg");
            return loginPoinsFlag;           
        }

		//校验连续登录增长比例	
		var validateRate = function(obj){
            var type = ["isNull","isRate"];
            var errorMsg = ["比例不能为空!","比例格式输入有误(应小于1)!"];
			rateFlag = showErrorMsg(obj,type,errorMsg,"rateMsg","rateMsg");
            return rateFlag;           
        }	

		//校验积分上限               
        var validateUpper = function(obj){        	
            var type = ["isNull","isIntDec"];
            var errorMsg = ["积分上限不能为空!","等级积分格式输入有误!"];
            upperLimitFlag = showErrorMsg(obj,type,errorMsg,"upperMsg","upperMsg");          
            return upperLimitFlag;           
        }

		//校验等级积分		
		var validateGraP = function(obj){			
            var type = ["isNull","isIntDec"];
            var errorMsg = ["等级积分不能为空!","等级积分格式输入有误!"];            
	        graPointsFlag = showErrorMsg(obj,type,errorMsg,"graMsg","graMsg");            
            return graPointsFlag;           
        }
				
        	
				
		//修改方法
		var check = function() {	              
			
			var gradeId = getEle("gradeId");
            gradeFlag = validateGrade(gradeId);
                        
            //var consPoints = getEle("consPoints");
            //consPointsFlag = validateConsP(consPoints);
            
            var loginPoints = getEle("loginPoints");
            loginPointsFlag = validateLogin(loginPoints);

            var pointsRate = getEle("pointsRate");
            rateFlag = validateRate(pointsRate);
               
            var gradePoints = getEle("gradePoints");
            graPointsFlag = validateGraP(gradePoints);
                       
            var upperLimit = getEle("upperLimit");
            upperLimitFlag = validateUpper(upperLimit);
               
		    if(!(gradeFlag&&loginPointsFlag&&rateFlag&&graPointsFlag&&upperLimitFlag)){
		    	alert("请按照提示信息正确填写.(*部分为必填项!)");
				return false;	
		    }
		    									
		}
			
	
       	//关闭输入法
		function colseImeMode(obj){
			 obj.style.css = ("ime-mode","disabled");
		}
	
	
	</script> 
</head>
<body>
	
	<div class="Position">
		当前位置是：HOME &gt;&gt; 会员等级管理 &gt;&gt; 会员等级添加
	</div>
	<s:form action="member/memCreditRule" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>
	<s:if test="#session.user_session.userLevel==0">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		   	<tr>
		   		<th width="40%" align="right"><span class="Color5">* </span>会员等级名称：</th>
		        <td>
		        	<s:select name="memCreditDto.gradeId" id="gradeId" onblur="validateGrade(this);" cssStyle="width:200px;"
			        list="#request.memGradeValues" listKey="key" listValue="value" headerKey="-1" headerValue="请选择..." cssClass="formSelect" theme="simple"/>			        
			        <s:hidden name="memCreditDto.creditId" id="creditId"/>
			        <label id="errorMsg" style="display: none;"></label>
		        </td>	      	
		   	</tr>
		   	<tr>
		      	<th align="right"><span class="Color5">* </span>登录领取积分：</th>
		        <td>
			        <s:textfield name="memCreditDto.loginPoints" id="loginPoints" onblur="validateLogin(this);" onkeyup="replaceToPoint(this);" cssStyle="width:195px;"  maxlength="6" cssClass="formInput"/> 
			        <label id="consMsg" style="display: none;"></label>
			        <span class="Color3">（每日登录领取积分）</span>			                
		        </td>		      	
		   	</tr>
		   	<tr>
		      	<th align="right"><span class="Color5">* </span>连续登录增长比例：</th>
		        <td>
			        <s:textfield name="memCreditDto.pointsRate" id="pointsRate" onblur="validateRate(this);" onkeyup="replaceToPoint(this);" cssStyle="width:195px;"  maxlength="6" cssClass="formInput"/> 
			        <label id="rateMsg" style="display: none;"></label>
			        <span class="Color3">（例如：连续两天登录，第二天积分为登录积分*（1+0.2））</span>			                
		        </td>		      	
		   	</tr>
		   	<!-- 		   	
		   	<tr>
		      	<th align="right"><span class="Color5">* </span>消费积分：</th>
		        <td>
			        <s:textfield name="memCreditDto.consPoints" id="consPoints" onblur="validateConsP(this);" onkeyup="replaceToPoint(this);" cssStyle="width:195px;"  maxlength="60" cssClass="formInput"/> 
			        <label id="consMsg" style="display: none;"></label>
			        <span class="Color3">（每日登录领取积分）</span>			                
		        </td>		      	
		   	</tr>
		   	 -->
		   	<tr>	
		   		<th align="right"><span class="Color5">* </span>等级积分：</th>
		        <td>
					<s:textfield name="memCreditDto.gradePoints" id="gradePoints" onblur="validateGraP(this);" onkeyup="replaceToPoint(this);" cssStyle="width:195px;"  maxlength="6" cssClass="formInput"/> 
			        <label id="graMsg" style="display: none;"></label>	
			        <span class="Color3">（会员消费消费1元送多少等级积分）</span>		
				</td>
		    </tr>
		    <tr>	
		   		<th align="right"><span class="Color5">* </span>每日积分上限：</th>
		        <td>
					<s:textfield name="memCreditDto.upperLimit" id="upperLimit" onblur="validateUpper(this);" onkeyup="replaceToNum(this);" cssStyle="width:195px;"  maxlength="6" cssClass="formInput"/> 			        
			        <label id="upperMsg" style="display: none;"></label>
			        <span class="Color3">（等级积分每日上限）</span>			
				</td>
		    </tr>
		    <tr>	
		   		<th align="right">使用状态：</th>
		        <td>
					<s:select name="memCreditDto.status" id="status" cssStyle="width:200px;"
			        list="#request.statusValues" listKey="key" listValue="value" cssClass="formSelect" theme="simple"/>
				</td>
		    </tr>
		    <s:if test="method=='checkUI'">
		    <tr>	
		   		<th align="right">创建时间：</th>
		        <td>
					<s:textfield name="memCreditDto.createTime" id="createTime" cssStyle="width:195px;"  cssClass="formInput"/> 			
				</td>
		    </tr>
		    <tr>	
		   		<th align="right">操作人：</th>
		        <td>
					<s:textfield name="memCreditDto.operator" id="operator" cssStyle="width:195px;" cssClass="formInput"/> 			        	
				</td>
		    </tr>
		    <tr>	
		   		<th align="right">更新时间：</th>
		        <td>
					<s:textfield name="memCreditDto.updateTime" id="updateTime" cssStyle="width:195px;" cssClass="formInput"/> 			
				</td>
		    </tr>
		    </s:if>		    	
		</table>
		</s:if>			    		    	 		
	 <div class="formTableBottom">
	 	<s:if test="method!='checkUI'">
	 	<s:if test="#session.user_session.userLevel==0">
			<my:permission key='sy-1306-02' value='会员等级添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		</s:if>
		<input type="button" class="formButton" value="返 回" onclick="go('member/memCreditRule!list')"/>
     </div>
	 </s:form>
</body>
</html>