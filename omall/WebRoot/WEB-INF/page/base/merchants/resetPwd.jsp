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
	<title>商户密码重置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<link href="css/auto.css" rel="stylesheet" type="text/css" />
	<script src="js/jquery-1.8.2.min.js"></script>    
	<script src="js/common.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript">
		
		/**发送验证码**/
		function sendCode(){
			md5=$("#md5").val();
			$.ajax({
				type:"post",
				url:"base/merReset!sendCode",
				data:"md5="+md5,
				cache:false,
				error : function(textStatus, errorThrown) {
					alert("系统ajax交互错误!");
	    		},
	    		success : function(data, textStatus) {
		    		if(data.flag){
			    		$("#codeId").val(data.obj.codeId);
			    		 $("#btnSendPhone").attr("disabled",true);
		    			timerr();
			    	}
					alert(data.obj.message);
	    		}
			});
		}

		 var timer=60;//倒计时60秒
		 var val=null;//循环倒计时
		function timerr(){
			val=setInterval("setSendClick()",1000);
			
		}
		 function setSendClick(){
			 
			 --timer;
			 if(timer>0){
				 $("#btnSendPhone").val(timer+"秒后可再次发送");
			 }else{
				 timer=60;
				 clearInterval(val);
				 $("#btnSendPhone").val("发送验证码");
				 $("#btnSendPhone").attr("disabled",false);
			}
			 
		}

		function checkCode(){
			var code=$("#code").val();
			var msg=$("#codeMsg");
			var codeId=$("#codeId").val();
			if(codeId.length==0){
				$("#codeMsg").html("您还没有发送过验证码");
				$("#codeMsg").show();
				return false;
			}
			$("#codeMsg").hide();
			if(code.length==0||code.length<6){
				msg.html("验证码为6位数字，不得为空");
				msg.show();
				return false;
			}else{
				if(/^\d{6}$/.test(code)){
					msg.hide();
					return true;
				}else{
					msg.html("验证码格式不正确");
					msg.show();
					return false;
				}
			}
		}

		

		function checkPwd(){
			var pwd=$.trim($("#pwd").val());
			var pwdMsg=$("#pwdMsg");
			if(pwd.length==0||pwd.length<6){
				pwdMsg.html("字母、数字和下划线（6--15位），不得为空！");
				pwdMsg.show();
				return false;
			}else{
				if(/^\w{6,15}$/.test(pwd)){
					pwdMsg.hide();
					if($.trim($("#validataPwd").val()).lenght>0){
						checkValidataPwd();
					}
					return true;
				}else{
					pwdMsg.html("密码格式为字母、数字和下划线（6--15位）！");
					pwdMsg.show();
					return false;
				}
				
			}
		}
		function checkValidataPwd(){
			var pwd=$.trim($("#pwd").val());
			var valiPwd=$.trim($("#validataPwd").val());
			var msg=$("#validataPwdMsg");
			if(pwd.length==0){
				msg.hide();
				return false;
			}
			if(valiPwd.length==0){
				msg.html("请输入重复密码");
				msg.show();
				return false;
			}else{
				if(pwd!=valiPwd){
					msg.html("两次密码不一致");
					msg.show();
					return false;
				}else{
					msg.hide();
					return true;
				}
			}
		}

		function check(){
			var codeFlag=checkCode();
			var pwdFlag=checkPwd();
			var valiPwd=checkValidataPwd();
			if(!(codeFlag&&pwdFlag&&valiPwd)){
				return;
			}
			var codeId=$("#codeId").val();
			var code=$("#code").val();
			//先验证手机号和验证码是否匹配
			$.ajax({
				async:false,//同步验证
				type:"post",
				url:"base/merReset!checkCode",
				data:"codeId="+codeId+"&code="+code,
				cache:false,
				error : function(textStatus, errorThrown) {
					alert("系统交互错误，请重试!");
					return;
	    		},
	    		success : function(data, textStatus) {
					if(!data.flag){
						$("#codeMsg").html("验证码错误，请核对");
						$("#codeMsg").show();
						return;
					}
					$("#codeMsg").hide();
	    		}
			});
			document.getElementById("form").submit();
		}

		function clearSpan(){
			$("span").hide();
		}
	</script>
	<style type="text/css">
		.button { 
	    position: relative; 
	    overflow: visible; 
	    display: inline-block; 
	    padding: 0.5em 1em; 
	    border: 1px solid #d4d4d4; 
	    margin: 0;
	    text-decoration: none; 
	    text-shadow: 1px 1px 0 #fff; 
	    font:11px/normal sans-serif; 
	    color: #333; 
	    white-space: nowrap; 
	    cursor: pointer; 
	    outline: none; 
	    background-color: #ececec;
	    background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#f4f4f4), to(#ececec));
	    background-image: -moz-linear-gradient(#f4f4f4, #ececec);
	    background-image: -o-linear-gradient(#f4f4f4, #ececec);
	    background-image: linear-gradient(#f4f4f4, #ececec);
	    -webkit-background-clip: padding;
	    -moz-background-clip: padding;
	    -o-background-clip: padding-box; 
	    /*background-clip: padding-box;*/ /* commented out due to Opera 11.10 bug */
	    -webkit-border-radius: 0.2em; 
	    -moz-border-radius: 0.2em; 
	    border-radius: 0.2em; 
	    /* IE hacks */
	    zoom: 1; 
	    *display: inline; 
	}
	
	.button:hover,
	.button:focus,
	.button:active {
	    border-color: #3072b3;
	    border-bottom-color: #2a65a0;
	    text-decoration: none; 
	    text-shadow: -1px -1px 0 rgba(0,0,0,0.3); 
	    color: #fff; 
	    background-color: #3072b3;
	    background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#599bdc), to(#3072b3));
	    background-image: -moz-linear-gradient(#599bdc, #3072b3);
	    background-image: -o-linear-gradient(#599bdc, #3072b3);
	    background-image: linear-gradient(#599bdc, #3072b3);
	}
	
	.button:active,
	.button.active {
	    border-color: #2a65a0;
	    border-bottom-color: #3884CF;
	    color: #fff; 
	    background-color: #3072b3;
	    background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#3072b3), to(#599bdc));
	    background-image: -moz-linear-gradient(#3072b3, #599bdc);
	    background-image: -o-linear-gradient(#3072b3, #599bdc);
	    background-image: linear-gradient(#3072b3, #599bdc);
	}
	
	/* overrides extra padding on button elements in Firefox */
	.button::-moz-focus-inner {
	    padding: 0;
	    border: 0;
	}
	</style>
  </head>
  <body>
  
  <div class="wrap">
       	<div class="login_main_white">
            	<div class="login_main_announce">
                	<ol><b>用户须知：</b>
					<li>1:在本站注册的商户，不得在本站发表诽谤他人，侵犯他人隐私，侵犯他人知识产权，传播病毒，政治言论，商业机密等信息。</li>
					<li>2:商户必须是符合本协议第四条规定的法律实体，如无经营资格或违反本协议第五条之声明与保证的组织不当注册为乙方商户或超越其民事权利或行为能力范围从事交易的，
					其与乙方之间的协议自始无效，乙方一经发现，有权立即注销该商户服务帐户，并追究其使用乙方网服务的一切法律责任。
					商户注册是指商户登陆乙方网，并按要求填写相关信息、签署并同意履行本协议并获得乙方服务帐户的过程。</li>
					</ol>
                </div>
                <div class="clearfix"></div>
         </div>
			

           <dl>
        <s:form id="form" action="base/merReset!reset" method="post">
			<input  type="hidden" id="md5" name="md5" value="${md5 }"/>
		   <dd>手 机 号：</dt>
			<input class="log_input2" type="text" readonly="readonly" value="${tel }"/>
		  	<input class="button" maxlength="6" type="button" id="btnSendPhone"  value="获取验证码" onclick="sendCode();" />
		  	</dd>
			<dd>登陆密码：
			<input class="log_input2" name="pwd" maxlength="15" style="ime-mode: disabled;" type="password" id="pwd" onblur="checkPwd();" onkeydown="if(event.keyCode==32){return false;}" />
			<span class="errorMsg" style="display: none;" id="pwdMsg"></span>
			</dd>
			<dd>重复密码：
			<input class="log_input2" maxlength="15" type="password" id="validataPwd" onblur="checkValidataPwd();" onkeydown="if(event.keyCode==32){return false;}"  />
			<span class="errorMsg" style="display: none;" id="validataPwdMsg"></span>
			</dd>
			<dd>验 证 码：
			<input class="log_input2" type="text" maxlength="6" id="code" onblur="checkCode();" />
			<span class="errorMsg" style="display: none;" id="codeMsg"></span>
			<input type="hidden"  id="codeId"  />
			</dd>
			<dd><br /><input type="button" name="Submit2" value="确定修改" class="Btn" onclick="check();" />
			&nbsp;&nbsp;
			<input type="reset" name="Submit22" value="重 置" class="Btn" onclick="clearSpan();">
			<br /><br />
            </dd>
         </s:form>
	</dl>
	</div>
  </body>
</html>
