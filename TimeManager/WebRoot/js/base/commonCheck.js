//			公共验证函数	
			/**
			 * 卡号验证
			 * @return
			 */
			var validateCardNo = function(cardNo){
				var cardNo = $("#cardNo").val();
				if (cardNo==''){
					alert("卡号不能为空!");
			    	return false;
				}
				var cardNoreg = /^[0-9]*$/;
				if(!cardNoreg.test(cardNo)){
					alert("请输入16或19位数字!");
					return false;
				}
				if(cardNo.length !=16&&cardNo.length!=19){
					alert("请输入16位或19位卡号!");
			    	return false;
				}
				var params = {
			        "saleOrderDTO.beginCardNo" : cardNo
			    }; 
				
			}
			/***
			 * 开卡积分验证
			 * @return
			 */
			var queryNewPoint = function(saleLevelId,newPoint){
				if(saleLevelId==''){
					return false;
				}
				var params = {
				        "asaleOrderDTO.saleLevelId" : saleLevelId
				    }; 
				var validateNewPoint= function(){
					var newPointreg = /^[0-9]+(.[0-9]{2})?$/;
					if(!newPointreg.test(newPoint)){
						alert("请整数或两位小数!");
				    	return false;
				    }else{
				    	newPointFlag=true;
				    }
				}
			}
			/***
			 * 金额验证
			 */
			var validateInitAmt= function(initAmt){
				var initAmtreg = /^[0-9]+(.[0-9]{2})?$/;
				if (initAmt==''){
			    	alert("请输入面值!");
			    	initAmtFlag=false;
			    	return false;
				}else if(!initAmtreg.test(initAmt)){
					alert("请正确的现金格式(0.00)!");
					initAmtFlag=false;
			    	return false;
			    }else{
			    	initAmtFlag=true;
			    }
			}
			/***
			 * 真实姓名验证
			 * @return
			 */
			var validateMemRealName= function(memRealName){
				if (memRealName==''){
			    	alert("请输入真实姓名!");
			    	return false;
				}else{
					memRealNameFlag=true;
				}
				
			}
			/****
			 *
			 * 身份证编号验证
			 * */
			var validatePersonId= function(personId,perType){
				
				if (personId==''){
			    	alert("请输入证件编号!");
			    	return false;
				}else if(perType=='1'){
					if (!perTypereg.test(personId)){
				    	alert("请输入正确的身份证号(15或18位)!");
				    	return false;
					}else if(personId.length !=15&&personId.length!=18){
						alert("请输入正确的身份证号(15或18位)!");
				    	return false;
					}else{
						personIdFlag = true;
					}
				}
			}
			/***
			 * 电话号码验证
			 * @return
			 */
			var validateTeleNo= function(teleNo){
				var teleNoreg = /^[0-9]*$/;
				if (teleNo==''){
			    	alert("请输入联系电话!");
			    	return false;
				}else if (!teleNoreg.test(teleNo)){
			    	alert("请输入正确的电话格式(11位数字)!");
			    	return false;
				}else{
					teleNoFlag = true;
				}
			}
			/****
			 * 邮箱验证
			 * @return
			 */
			var validateEmail= function(email){
				var emailreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,}$/;
				if (email==''){
				    alert("请输入电子邮箱!");
				    return false;
				}else if(!emailreg.test(email)){
					alert("请输入有效的E_mail！");
					return false;
				}else {
					emailFlag = true;
				}
			}
			/****
			 * 邮编验证
			 * @return
			 */
			var validateResidZip= function(residZip){
				var residZipreg = /^[0-9]*$/;
				if(!residZipreg.test(residZip)){
					alert("请输入数字格式！");
					return false;
				}else{
					residZipFlag = true;
				}
			}
			/***
			 * 描述验证
			 * 此处Id名需要和参数名相同
			 * @return
			 */
			var validateDescr= function(descr){
				var textareaLength=descr.length;  
				if(textareaLength>=200){ 
					var test=descr.substr(0,200); 
					$("#descr").val(test); 
					alert("200以内字符！");
					return false;
				} 
			}
