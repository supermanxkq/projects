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
	<title>机构账户查询</title>
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
	<script src="js/datepicker/WdatePicker.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">
	 var PasWodFalg=false;//支付密码
     var NewPasWodFalg=false;//输入新密码
     var PardenNewPasWodFalg=false;//输入新密码
     var ValitePswFlag=false;//检查密码两次输入时否一样
     var MemPhoneNumFlag=false;//手机号
     var MemPhoneNumTypeFlag=false;//手机号格式
     var PhoneCheckNumFalg=false;//校验验证码
	$(document).ready(function() {
		          $("#findOut").hide();	//隐藏账户信息div
		          $("#pasChange").hide();//隐藏修改密码div
		          //查询账户信息
			$("#submitOgan").click(function() {
                        var organPws=$("#oganpws").val();
                        if(organPws==""){   
                              alert("请输入支付密码");
                               }else{
                            	      if(organPws.length !=6){
                                           alert("请输入6位支付密码")  
                                           }else{
                                	$.ajax({
									    type : "POST",
										url : "account/ogaccounts!findOgins",
										contentType : "application/x-www-form-urlencoded; charset=utf-8",
										data : $("#formSerch").serialize(),
										error : function() {
											alert("系统忙，请稍候");
										},
										success : function(data,textStatus) {
                                           if(data!=null&&data!=""){
											
                                           if(data.amount=="5"){
                                        	   var obj=data.obj[0];
                                               $("#onAccId").val(obj.onAccId);
                                               $("#onAccId").attr("disabled" ,true); 
                                               $("#onAccIdHI").val(obj.onAccId);
                                               $("#onAccIdPasC").val(obj.onAccId);
                                               $("#onAccIdPasC").attr("disabled" ,true);
                                               	
                                               $("#pointsNum").val(obj.pointsNum);
                                               $("#pointsNum").attr("disabled" , true);
                                               $("#cardMoney").val(obj.cardMoney);
                                               $("#cardMoney").attr("disabled" , true);
                                               $("#findSer").hide();	
                                               $("#pasChange").hide();	
                                               $("#findOut").show();	
                                               }else if(data.amount=="1"){
                                                 alert("网络错误");
                                                }else if(data.amount=="2"){
                                                 alert("账户信息不存在,或者密码错误!");
                                                }else if(data.amount=="3"){
                                                 alert("此账户没有密码,请设置密码!");
                                                }else if(data.amount=="4"){
                                                     alert("此账户没有信息!");
                                                 }else if(data.amount=="6"){
                                                	 alert("此账户没有绑定卡号!");
                                                     }
                          
										}else{
                                                  alert("查询失败，请稍后再试");

											}
									}
									});

                            }
                          }
                        
			           });

	           //修改密码按钮
			$("#changePswod").click(function() {

				 $("#findSer").hide();
				 $("#findOut").hide();		
                 $("#pasChange").show();	
 
	           });
            //发送验证码
			$("#sendPnoneCheckNum").click(function() { 
				validatePhone();
	            if(MemPhoneNumFlag&&MemPhoneNumTypeFlag){         
				$.ajax({
				    type : "POST",
					url : "account/ogaccounts!sendPhoneCheckNum",
					contentType : "application/x-www-form-urlencoded; charset=utf-8",
					data : $("#changePassWordForm").serialize(),
					error : function() {
						alert("系统忙，请稍候");
					},
					success : function(data,textStatus) {
					    if(data!=null&&data!=""){
                       	 if(data.amount=="1"){
                               alert("内部网络错误!");
                       	 }else if(data.amount=="2"){
                                   alert("发送验证码失败！");

                             }else if(data.amount=="3"){
                            	 alert("发送验证码成功！");
                            	 for (i = 1; i <= 60 * 1; i++) {
         							window.setTimeout("update1( " + i + ") ", i * 1000);
         						}                                  
                           	 }
                            }else{
                                   alert("系统出错!");                
                                }
					}
				});	
	            }
	            else{
                     return;
		            }
	           });
	           //修改支付密码
	           $("#changePswodSub").click(function() {
	        	   checkPasWod();
	        	   checkNewPasWod();
	        	   checkPardenNewPasWod();
	        	   validatePhone();
	        	   checkPhoneCheckNum();
		           if(PasWodFalg&&NewPasWodFalg&&PardenNewPasWodFalg&&ValitePswFlag&&MemPhoneNumFlag&&MemPhoneNumTypeFlag&&PhoneCheckNumFalg){
					$.ajax({
					    type : "POST",
						url : "account/ogaccounts!changePassWord",
						contentType : "application/x-www-form-urlencoded; charset=utf-8",
						data : $("#changePassWordForm").serialize(),
						error : function() {
							alert("系统忙，请稍候");
						},
						success : function(data,textStatus) {
						    if(data!=null&&data!=""){
	                       	 if(data.amount=="1"){
	                               alert("内部网络错误!");
	                       	 }else if(data.amount=="2"){
	                                   alert(data.msg);
	                                   $("#findSer").show();
	                                   $("#findOut").hide();	//隐藏账户信息div
	                 		           $("#pasChange").hide();//隐藏修改密码div

	                             }else if(data.amount=="3"){
	                            	 alert(data.msg);
	                           	 }
	                            }else{	                        
	                                   alert("系统出错!")
	                    
	                                }
						}
					});	
		           }else{

		        	   return;
			           }
					 
		           });
		         });

	
		//校验支付密码
		function checkPasWod(){
			var pwd = $.trim($("#PasWod").val())
			if(pwd == ""){
				$("#pasWodError").html("密码不能为空!");
				$("#pasWodError").show();
				PasWodFalg=false;
			} else if(pwd.length != 6){
				 $("#pasWodError").html("密码长度为6位数字!");
				 $("#pasWodError").show();
				 PasWodFalg=false;
			}else{
				 $("#pasWodError").html("");
				 $("#pasWodError").hide();
				 PasWodFalg=true;
				}
		}
		//倒计时
		function update1(num) {
            var secs = 60 * 1;
            if (num == secs) {
                $("#sendPnoneCheckNum").val("发送验证码");
                $("#sendPnoneCheckNum").attr("disabled", false);
            }
            else {
                printnr = secs - num;
                $("#sendPnoneCheckNum").val( printnr + "秒后重发");
                $("#sendPnoneCheckNum").attr("disabled", true);
            }
        }
		//校验输入新密码
		function checkNewPasWod(){
			var pwd = $.trim($("#NewPasWod").val());
			var pwd2 = $.trim($("#PardenNewPasWod").val());
			if(pwd == ""){
				$("#NewPasWodError").html("密码不能为空!");
				$("#NewPasWodError").show();
				 NewPasWodFalg=false;
			} else if(pwd.length != 6){
				 $("#NewPasWodError").html("密码长度为6位数字!");
				 $("#NewPasWodError").show();
				 NewPasWodFalg=false;
			}else{
				 $("#NewPasWodError").html("");
				 $("#NewPasWodError").hide();
				 NewPasWodFalg=true;
				 if(pwd2!=""&&pwd2.length== 6){
					 validatePwd()
					 }
				}
		}

			//校验再次输入新密码
				function checkPardenNewPasWod(){
					var pwd2 = $.trim($("#NewPasWod").val())
					var pwd = $.trim($("#PardenNewPasWod").val())
					if(pwd == ""){
						$("#PardenNewPasWodError").html("密码不能为空!");
						$("#PardenNewPasWodError").show();
						 NewPasWodFalg=false;
					} else if(pwd.length != 6){
						 $("#PardenNewPasWodError").html("密码长度为6位数字!");
						 $("#PardenNewPasWodError").show();
						 NewPasWodFalg=false;
					}else{
						 $("#PardenNewPasWodError").html("");
						 $("#PardenNewPasWodError").hide();
						 PardenNewPasWodFalg=true;
						 if(pwd2!=""&&pwd2.length== 6){
							 validatePwd()
							 }
						}
				}
				//两次密码比对
				function validatePwd(){
					var pwd = $.trim($("#NewPasWod").val())
					var pwd2 = $.trim($("#PardenNewPasWod").val());
					if(pwd != pwd2){
						$("#PardenNewPasWodError").html("两次密码不符!");
						$("#PardenNewPasWodError").show();
						ValitePswFlag=false;
					}else{
						$("#PardenNewPasWodError").html("");
						 $("#PardenNewPasWodError").hide();
						ValitePswFlag=true;
					}
				}
				//验证手机号
				function validatePhone(){
					var phone = $.trim($("#memPhoneNum").val());
					if(phone==""){
						$("#memPhoneNumError").html("手机号不能为空!");
						$("#memPhoneNumError").show();
						MemPhoneNumFlag=false;
						}else{
							$("#memPhoneNumError").html("");
							$("#memPhoneNumError").hide();
							 MemPhoneNumFlag=true;
							 var phonereg =/^((\+86)|(86))?(1)\d{10}$/;
							
								if(!phonereg.test(phone)){
									$("#memPhoneNumError").html("请输入正确的手机号！");
									$("#memPhoneNumError").show();
									MemPhoneNumTypeFlag=false;
								}else{
									 $("#memPhoneNumError").html("");
									 $("#memPhoneNumError").hide();
									  MemPhoneNumTypeFlag=true;
									
									}

							} 
				}
				//校验验证码
				function checkPhoneCheckNum(){
					var pwd = $.trim($("#phoneCheckNum").val())
					if(pwd == ""){
						$("#phoneCheckNumError").html("验证码不能为空!");
						$("#phoneCheckNumError").show();
						PhoneCheckNumFalg=false;
					} else if(pwd.length != 6){
						 $("#phoneCheckNumError").html("验证码长度为6位数字!");
						 $("#phoneCheckNumError").show();
						 PhoneCheckNumFalg=false;
					}else{
						 $("#phoneCheckNumError").html("");
						 $("#phoneCheckNumError").hide();
						 PhoneCheckNumFalg=true;
						}
				}
	
	
	</script>
</head>


  <body >
   
	  <div class="Position">
		  当前位置是：HOME &gt;&gt; 账户管理 &gt;&gt; 账户查看
	  </div>
	  
	  <div id="findSer">
	  <div style="height: 200px">
		
	  </div>
	<s:form  id="formSerch" method="post"  enctype="multipart/form-data" theme="simple">
		 <table style="width:50%;margin:10px auto; border-collapse:collapse;border:#CCC solid 1px;"  border="0" cellpadding="0" style="border:0px" cellspacing="0" >
			<tr>
		  		<th style="background: #f7f7f7;border:#CCC solid 1px; height: 36px;color: #5b5b5b;font-weight: normal;font-size: 16px;" align="right" width="20%">请输入支付密码：</th>
		  		  <td width="30%" style="border:#CCC solid 1px; height: 36px;padding:0px 8px;	font-size: 16px;">
							 <s:password cssClass="formInput" theme="simple" maxlength="6"  onkeyup="replaceToNum(this);" id="oganpws" name="accountsDTO.oganpws"></s:password>
							  <my:permission key='sy-1503-01' value='机构账户查看'>
			 		          <input id="submitOgan" type="button" class="formButton" value="查询" style="font-size: 16px;"  />
			 	             </my:permission>		
		         </td>
				
			</tr>
	    </table>
	    </s:form>
	   </div>
	  <div id="findOut">
   		<table width="100%" border="0" cellpadding="0" style="border:0px" cellspacing="0" class="formTable">
			<tr>
		  		<th align="right" width="20%">账户号：</th>
		  		  <td width="30%">
							<s:textfield  id="onAccId"    maxlength="12"
							cssClass="formInput" theme="simple" />
		          </td>
			</tr>
			<tr>
		  		<th align="right" width="20%">积分账户余额：</th>
		  		  <td width="30%">
							<s:textfield  id="pointsNum"    maxlength="12"
							cssClass="formInput" theme="simple"   />
		          </td>
			</tr>
			<tr>
		  		<th align="right" width="20%">现金账户余额：</th>
		  		  <td width="30%">
							<s:textfield  id="cardMoney"    maxlength="12"
							cssClass="formInput" theme="simple"  />
		          </td>
			</tr>
	</table>
	
		 <div class="formTableBottom">
		  <my:permission key='sy-1503-03' value='机构账户修改支付密码'>
		   <input type="button" id="changePswod" class="formButton"      value="修改密码"  align="center"/>
		  </my:permission>
			<input type="button" class="formButton" value="返回"     onclick="go('account/ogaccounts!list')"  align="center"/>
		 </div>
	 </div>
	 <div id="pasChange">
	 <s:form  id="changePassWordForm" method="post"  enctype="multipart/form-data" theme="simple">
   		<table width="100%" border="0" cellpadding="0" style="border:0px" cellspacing="0" class="formTable">
   		  <s:hidden name="accountsDTO.onAccId" id="onAccIdHI"/>
			<tr>
		  		<th align="right" width="20%">账户号：</th>
		  		  <td width="30%">
					<s:textfield  id="onAccIdPasC"   maxlength="12" cssClass="formInput" theme="simple" />
		          </td>
			</tr>
			<tr>
		  		<th align="right" width="20%">请输入支付密码：</th>
		  		  <td width="30%">
		  		   <s:password cssClass="formInput" theme="simple" maxlength="6"  onkeyup="replaceToNum(this);"  onblur="checkPasWod();" name="accountsDTO.oganpws" id="PasWod"></s:password>
				   <label id="pasWodError" style="display:none" class="errorMsg"></label>
		          </td>
				
			</tr>
			<tr>
		  		<th align="right" width="20%">请输入新密码：</th>
		  		  <td width="30%">
		  		   <s:password cssClass="formInput" theme="simple" maxlength="6"  onkeyup="replaceToNum(this);"   onblur="checkNewPasWod();" id="NewPasWod"  name="accountsDTO.newOganpws"></s:password>
				   <label id="NewPasWodError" style="display:none" class="errorMsg"></label>
		          </td>
				
			</tr>
			<tr>
		  		<th align="right" width="20%">请再输入一次：</th>
		  		  <td width="30%">
					<s:password cssClass="formInput" theme="simple" maxlength="6"  onkeyup="replaceToNum(this);"   onblur="checkPardenNewPasWod();" id="PardenNewPasWod"  ></s:password>
					<label id="PardenNewPasWodError" style="display:none" class="errorMsg"></label>
		          </td>
			</tr>
			<tr>
		  		<th align="right" width="20%">手机号：</th>
		  		  <td width="30%">
							<s:textfield  id="memPhoneNum"    
							cssClass="formInput" theme="simple" name="accountsDTO.memPhoneNum" onblur="validatePhone();"  maxlength="11"  onkeyup="replaceToNum(this);"  />
							 <input type="button"  class="formButton" id="sendPnoneCheckNum"  width="120px" value="发送验证码"  />
							 <label id="memPhoneNumError" style="display:none" class="errorMsg"></label>
		          </td>
			</tr>
			<tr>
		  		<th align="right" width="20%">手机验证码：</th>
		  		  <td width="30%">
							<s:textfield  id="phoneCheckNum"    
							cssClass="formInput" theme="simple" name="accountsDTO.phoneCheckNum"  maxlength="6"  onblur="checkPhoneCheckNum();"   onkeyup="replaceToNum(this);"  />
							 <label id="phoneCheckNumError" style="display:none" class="errorMsg"></label>
		          </td>
			</tr>
			
	</table>
	</s:form>
		 <div class="formTableBottom">
		    <input type="button" id="changePswodSub" class="formButton"      value="提交" />
			<input type="button" class="formButton" value="返回"     onclick="go('account/ogaccounts!list')" />
		 </div>
	 </div>
</body>
</html>