  /**
      文档名称:   Public Validate Parttern
      文档内容：  公用验证格式js文件
      应用范围：  全局
      编写日期：  2013-12-30
      作    者:   谢
   */

    /**
	 * 1.功能说明：限制文本框只能输入正整数数字.目前不能控制输入汉字，需要禁用输入法 2.调用说明：onkeypress="return
	 * onlyNum(this);"
	 */
	   function onlyNum(obj){
           var e = obj.event || window.event;
           
           var code = parseInt(e.keyCode);
           if (((code >= 48) && (code <= 57)) || (code == 8)) {
               return true;  
           } else {
               return false;  
           }  
		}
	    /**
		 * 1.功能说明：限制文本框只能输入正整数数字和小数点.目前不能控制输入汉字，需要禁用输入法
		 * 2.调用说明：onkeypress="return onlyNum(this);"
		 */
	   
	   function onlyNumPoint(obj){
		   var e = obj.event || window.event;
           var code = parseInt(e.keyCode);
           if (((code >= 48) && (code <= 57)) || (code == 8) || (code == 46)) {
               return true;  
           } else {
               return false;  
           }  
	   }
	   
	   /**
		 * 1.功能说明：限制文本框只能输入正整数数字和小数点. 2.调用说明：onkeypress="return
		 * onlyNumFloadt(this);"
		 */
	      function onlyNumFloadt(obj){
	          var e = obj.event || window.event;
	          var code = parseInt(e.keyCode);
	          if (((code >= 48) && (code <= 57)) || (code == 8) || (code == 46)) {
	              return true;  
	          } else {
	              return false;  
	          }  
	   	}
	      
	      
	   /**
		 * 1.功能说明：限制文本框只能输入英文字母. 2.调用说明: onkeyup = "replaceToEng(this);";
		 */
	   function replaceToEng(obj){
		   obj.value = obj.value.replace(/[^\w\u4E00-\u9FA5]/g,'');
	   }
	   /**
		 * 1.功能说明：限制文本框只能输入英文字母. 2.调用说明: 在提交表单js中，return valiEng(valiText);
		 */
	   function valiEng(text){
		   var engreg = "\w\u4E00-\u9FA5";
		   return engreg.test(text);
		   
	   }
	   /**
		 * 只能輸入數字和字母^[A-Za-z0-9]+$ return numAndA(text);
		 */
	   function numAndA(text){
		   var engreg = "^[A-Za-z0-9]+$";
		   return engreg.test(text);
		   
	   }
	   /**
		 * 1.功能说明：限制文本框只能输入汉字. 2.调用说明: onkeyup = "replaceToCn(this);";
		 */
	   function replaceToCn(obj){
		   
		   obj.value = obj.value.replace(/[^\u4e00-\u9fa5]/g,'');
	   }
	   /**
		 * 1.功能说明：限制文本框只能输入汉字/字母和数字. 2.调用说明: onkeyup = "allowEnCnNu(this);"
		 */
	   function allowEnCnNu(obj){
		   obj.value = obj.value.replace(/[^\w\u4E00-\u9FA5]/g, '');
	   }
	   /**
		 * 1.功能说明：限制文本框只能输入汉字/字母和数字. 2.调用说明: onkeyup = "allowEnCnNu(this);"
		 */
	   function allowEnNu(obj){
		   obj.value = obj.value.replace(/[^\w]/g, '');
	   }
	   /**
		 * 1.功能说明：限制文本框只能输入数字，如果非数字则清除 2.调用说明：onkeyup= "replaceToNum(this);"
		 */
	   function replaceToNum(obj){
		   obj.value=obj.value.replace(/\D/g,'');
		   // obj.value=obj.value.replace(/^(-?\d+)(\.\d+)?$/,'');
	   }
	   /**
		 * 1.功能说明:限制文本框只能输入数字和小数点，如果非数字/小数点则清除 第一位输入不能为小数点
		 * 2.调用说明:onkeyup="replaceToNumPoint(this);"
		 * 
		 * @param obj
		 *            HtmlElement
		 * @return
		 */
	   function replaceToNumPoint(obj){
		   if(isNaN(obj.value)){
			 	obj.value=obj.value.substring(0,obj.value.length-1);
			 }
		   // obj.value = obj.value.replace(/[^\d.]/g,""); //清除“数字”和“.”以外的字符
		   // obj.value = obj.value.replace(/^[.]/g,""); //验证第一个字符是数字而不是.
		   // obj.value = obj.value.replace(/[.]{2,}/g,""); //只保留第一个. 清除多余的.
		   // obj.value =
			// obj.value.replace(".","$#$").replace(/./g,"").replace("$#$",".");
	   }
	   
	   function replaceToPoint(obj){
		   if(isNaN(obj.value)){
			 	obj.value = obj.value.replace(/[^\d.]/g,"");  // 清除“数字”和“.”以外的字符
			 	obj.value = obj.value.replace(/^[.]/g,"");  // 验证第一个字符是数字而不是.
			 	obj.value = obj.value.replace(/[.]{2,}/g,""); // 清除第一个点以外的点
			 	
			 }
	       }
			   
	   /**
		 * 1.功能说明:限制是整数数字和小数点数字 2.调用说明: boolean flag = isNumber(valiText);
		 */
	   function isNumber(text){ 
		   
		   if(!isNaN(text)){ 
		      return true;
		    }
		   else
		   { 
		       return false; 
		   } 
	 } 


   /**
	 * 1.功能说明：验证邮箱格式是否正确 2.调用说明：onblur = "emailValiFormat(this);"
	 */
   function emailValiFormat(email){
	   var emailval = email.value;
	   var emailreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	   // alert(email);
	   if((emailval=="")||!emailreg.test(emailval)){
	      alert("邮箱格式错误，请填写正确邮箱格式.");
	      return false;
	   }
	    return true;
	 }
   
   /**
	 * 1.功能说明：验证手机/电话格式是否正确 2.调用说明：在提交表单验证js中 "return phoneVali(phoneNo);"
	 */
   function mobphoneVali(phoneNo){
     var phonereg =/^\d{7,8,11}$/;
     if((phoneNo=="")||!phonereg.test(phoneNo)){
        alert("格式错误，请填写正确联系方式.");
        return false;
     }
     return true;
   }
   /**
	 * 1.功能说明：验证手机格式是否正确 2.调用说明：onblur = "mobileVali(this);"
	 */
 function mobileVali(obj){
   var phoneNo = obj.value;
   var phonereg =/^((\+86)|(86))?(1)\d{10}$/;
   if((phoneNo=="")||!phonereg.test(phoneNo)){
      alert("手机格式错误，请填写正确联系方式.");
      return false;
   }
   return true;
 }
 /**
	 * 1.功能说明: 验证身份证是否符合格式 2.调用说明: onblur = "idVali(obj)";
	 */
 function idVali(obj){
	 var id = obj.value;
	 var idreg =/^\d{18}$/;
	 var perTypereg = /^\d{17}(\d|x)$/;
	 if(!idreg.test(id)&&!perTypereg.test(id)){
		 alert("身份证格式错误，请重新输入!");
		return false;
	 }
	 return true;
	 
 }
 /**
	 * 1.功能说明: 验证身份证是否符合格式 2.调用说明: return idVali(test)";
	 */
 function idVali(i){
	 var id = obj.value;
	 var idreg =/^\d{18}$/;
	 var perTypereg = /^\d{17}(\d|x)$/;
	 if(!idreg.test(id)&&!perTypereg.test(id)){
		 alert("身份证格式错误，请重新输入!");
		return false;
	 }
	 return true;
	 
 }
   
   
  /**
	 * 1.功能说明： 验证电话格式是否正确 2.调用说明：onblur = "phoneVali(this);" 3.可通过验证格式: 6872770
	 * 68727700 06336872770 063368727700 0633-6872770 0633-68727700 0106872770
	 * 01068727700 010-6872770 010-68727700
	 */
 function phoneVali(obj){
   var phoneNo = obj.value;
   var phonereg =/^\d{7,8}$/;
   var pattern=/(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^[0-9]{3,4}[0-9]{7,8}$)|(^0{0,1}1[0-9]{10}$|(^\d{7,8,11}$))/; 
   if((phoneNo!="")&&!pattern.test(phoneNo)){
      alert("固话格式错误，请填写正确联系方式.");
      obj.focus();
      return false;
   }
   return true;
 }
 /**
	 * 1.功能说明： 验证电话格式是否正确 2.调用说明： boolean a = phoneTextVali(phoneVal);
	 */
 function phoneTextVali(phoneVal){
	 var pattern=/(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^[0-9]{3,4}[0-9]{7,8}$)|(^0{0,1}1[0-9]{10}$|(^\d{7,8,11}$))/;
	 if((phoneNo!="")&&!pattern.test(phoneVal)){
	      return false;
	   }
	   return true;
 }
 /**
	 * 1.功能说明：验证数字和小数点 2.调用说明：return valiNumFloat(text);
	 */
 function valiNumFloat(text){
	 var numfloatReg = /^[0-9]{1}([0-9]|[.])*$/;
	 if(!numfloatReg.test(text)){
		 return false;
	 }
	 return true;
 }
 
 /**
	 * 1.功能说明：验证数字和小数点 2.调用说明：onblur = "valiNumFloatBlur(this);"
	 */
 function valiNumFloatBlur(obj){
	 var valiNum = obj.value;
	 var numfloatReg = /^[0-9]{1}([0-9]|[.])*$/;
	 if(!numfloatReg.test(valiNum)){
		 alert("格式错误，请重新输入!");
		 return false;
	 }
	 return true;
 }
 
   /**
	 * 1.邮箱自动补全功能
	 */
$(function(){  
			var mailList = new Array('@163.com','@126.com','@hotmail.com','@qq.com');
			$("#email").bind("keyup",function(){
				var val = $(this).val();
				if((val == '') || (val.indexOf("@")>-1)){
					$(".emaillist").hide();
					return false;
				}
				$('.emaillist').empty();
				for(var i = 0;i<mailList.length;i++){
				var emailText = $(this).val();
				$('.emaillist').append('<li class=addr>'+emailText+mailList[i]+'</li>');
				}
				$('.emaillist').show();
				$('.emaillist li').click(function(){
					$('#email').val($(this).text());
					$('.emaillist').hide();
				})	
			})			
		})

 
 /**
	 * 使用前提页面包含 ul: emaillist
	 * 
	 * <div class="inputposition"><label>Email Address:</label><input
	 * type="text" id="email" value="" class="inputbg"/>
	 * <ul class="emaillist">
	 * <!--
	 * <p>
	 * </p>
	 * -->
	 * </ul>
	 * </div>
	 */
   
   

  

