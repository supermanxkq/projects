$().ready( function() {
	var $sort = $("#listTable a.sort");
	var $orderProperty = $("#orderProperty");
	var $orderDirection = $("#orderDirection");
	// 排序
	$sort.click( function() {
		var orderProperty = $(this).attr("name");
		if ($orderProperty.val() == orderProperty) {
			if ($orderDirection.val() == "asc") {
				$orderDirection.val("desc")
			} else {
				$orderDirection.val("asc");
			}
		} else {
			$orderProperty.val(orderProperty);
			$orderDirection.val("asc");
		}
		
		// 排序图标
		if ($orderProperty.val() != "") {
			$(".sort").removeClass("desc").removeClass("asc");
			$sort = $("#listTable a[name='" + $orderProperty.val() + "']");
			if ($orderDirection.val() == "asc") {
				$sort.removeClass("desc").addClass("asc");
			} else {
				$sort.removeClass("asc").addClass("desc");
			}
		}
		query($("#currPage").text());
	});
	
	
});

//验证数字
function verifyIsNaN(obj){
	 if(isNaN(obj.value)){
	 	obj.value=obj.value.substring(0,obj.value.length-1);
	 }
}

//格式化数字，封小数点第二位进一位
function fmtnum(a){
	a.value = Math.round(a.value*100)/100;
	//a.value = Math.ceil(a.value*100)/100;
	//a.value = a.value.toFixed(2);
	return false;
}
//全选、反选
function switchAll() {
	for (var i = 0; i < document.getElementsByName("id").length; i++) {
		document.getElementsByName("id")[i].checked = !document.getElementsByName("id")[i].checked;
   }
}

//全选、反选
function switchAllByName(name) {
	for (var i = 0; i < document.getElementsByName(name).length; i++) {
		document.getElementsByName(name)[i].checked = !document.getElementsByName(name)[i].checked;
   }
}

function readcard(obj){
	var str = obj.value;
	$("#"+obj.id).val(str.substring(0,str.indexOf('=')));
}

function go(url){
	document.location.href = url;
}
//textarea字数限制
var textarea_maxlen = {
		isMax : function (area){
		   var textarea = document.getElementById(area);
		   var max_length = 80;
		if(textarea.value.length > max_length){ 
		   textarea.value = textarea.value.substring(0, max_length);
		   }
		},
		disabledRightMouse : function (){
		document.oncontextmenu = function (){ return false; }},
		enabledRightMouse : function (){
		document.oncontextmenu = null;
		}
};

//异步加载数据
function ajaxData(dataUrl,params){
    var tbId = "listTable";
    var tb = $("#"+tbId);
    var thLength = $("#"+tbId+" th").length;
    var first = tb.find('tr:eq(0)');
    $.ajax( {   
        url : dataUrl, 
        data : params,   
        dataType : "json",
        cache : false, 
        type : "POST",
        error : function(textStatus, errorThrown) {   
    		first.nextAll().remove();
        	var tr = $("<tr></tr>");
            var html = "<td colspan='"+thLength+"'>获取数据异常，请重试 ....</td>";		              		
            tr.html(html);
            tb.append(tr);
        },   
        beforeSend : function(){
        	first.nextAll().remove();
        	var tr = $("<tr></tr>");
            var html = "<td colspan='"+thLength+"'> <b>数据加载中 ....</b></td>";		              		
            tr.html(html);
            tb.append(tr);
		},
        success : function(data, textStatus) {  
        	$("#pageNav").html(data.pagehtml);
        	$("#otherhtml").html(data.otherhtml);
        	first.nextAll().remove();
        	var list = data.list;
        	if (list.length>0){
        		for(var i=0;i<list.length;i++) {
        			var tr = $("<tr></tr>");
        			var html = "";
        			for(var j=0;j<list[i].length;j++){
        				html += "<td >"+list[i][j]+"</td>";
        			}
              		tr.html(html);
              		if (i%2==1) tr.addClass("ghhs");
              		tb.append(tr);
            	}
        	} else {
        			var tr = $("<tr></tr>");
              		var html = "<td colspan='"+thLength+"' >没有找到相关数据</td>";		              		
              		tr.html(html);
              		tb.append(tr);
        	}
        }
    });
}

//异步加载数据
function ajaxDatas(dataUrl,params,tbId,pageNav){
    //var tbId = "listTable";
    var tb = $("#"+tbId);
    var thLength = $("#"+tbId+" th").length;
    var first = tb.find('tr:eq(0)');
    $.ajax( {   
        url : dataUrl, 
        data : params,   
        dataType : "json",
        cache : false, 
        type : "POST",
        error : function(textStatus, errorThrown) {   
    		first.nextAll().remove();
        	var tr = $("<tr></tr>");
            var html = "<td colspan='"+thLength+"'>获取数据异常，请重试 ....</td>";		              		
            tr.html(html);
            tb.append(tr);
        },   
        beforeSend : function(){
        	first.nextAll().remove();
        	var tr = $("<tr></tr>");
            var html = "<td colspan='"+thLength+"'><img src='images/loading.gif' /> <b>数据加载中 ....</b></td>";		              		
            tr.html(html);
            tb.append(tr);
		},
        success : function(data, textStatus) {  
        	$("#"+pageNav).html(data.pagehtml);
        	$("#otherhtml").html(data.otherhtml);
        	first.nextAll().remove();
        	var list = data.list;
        	if (list.length>0){
        		for(var i=0;i<list.length;i++) {
        			var tr = $("<tr></tr>");
        			var html = "";
        			for(var j=0;j<list[i].length;j++){
        				html += "<td >"+list[i][j]+"</td>";
        			}
              		tr.html(html);
              		if (i%2==1) tr.addClass("ghhs");
              		tb.append(tr);
            	}
        	} else {
        			var tr = $("<tr></tr>");
              		var html = "<td colspan='"+thLength+"' >没有找到相关数据</td>";		              		
              		tr.html(html);
              		tb.append(tr);
        	}
        }
    });
}

//删除一条数据
var deleteData = function(url,id) {
	if(confirm("确认要删除？")){
		var params = {
    		"id" : id
		};
		
		$.ajax({
    		url : url,   
    		data : params,   
    		dataType : "json",   
    		cache : false,  
    		type : "POST", 
    		error : function(textStatus, errorThrown) {
				alert("系统ajax交互错误!");
    		},
    		success : function(data, textStatus) {
    			if (data.ajaxResult == "ajaxsuccess") {
                	alert("删除成功!");
                	query($("#currPage").text());
            	}else if(data.ajaxResult == "ajaxfailure"){
            		alert(data.msgResult);
            	}else {
            		alert("删除失败!");
            	}
    		}
		});
	}
}


//审核一条数据
var verifyData = function(url,id) {
	if(confirm("审核通过？")){
		var params = {
    		"id" : id
		};
		
		$.ajax({
    		url : url,   
    		data : params,   
    		dataType : "json",   
    		cache : false,  
    		type : "POST", 
    		error : function(textStatus, errorThrown) {
				alert("系统ajax交互错误!");
    		},
    		success : function(data, textStatus) {
    			if (data.ajaxResult == "ajaxsuccess") {
                	alert("审核成功!");
                	query($("#currPage").text());
            	}else if(data.ajaxResult == "ajaxfailure"){
            		alert(data.msgResult);
            	}else {
            		alert("审核失败!");
            	}
    		}
		});
	}
}

//删除数据
var deleteDatas = function(url) {
	var aa = $('[name=id]');
	var ids = '';
 	for(var i=0;i<aa.length;i++){
        if(aa[i].checked){                             
        	if(ids == '') 
        	 	ids = aa[i].value;
        	else
         		ids = ids + "," + aa[i].value;
       	}
  	}
  	if(ids == ''){
   		alert("请选择批量删除项");
   		return ;
  	}
		
	if(confirm("确认要删除？")){
		var params = {   
    		"ids" : ids
		};   
		$.ajax({   
    		url : url,   
    		data : params,   
    		dataType : "json",
    		cache : false,  
    		type : "POST", 
    		error : function(textStatus, errorThrown) {   
				alert("系统ajax交互错误!");	   
    		}, 
    		success : function(data, textStatus) {
            	if (data.ajaxResult == "ajaxsuccess") {
                	alert("删除成功!");
                	query($("#currPage").text());
            	}else if(data.ajaxResult == "ajaxfailure"){
            		alert(data.msgResult);
            	}else {
            		alert("删除失败!");
            	}
    		}
		});
    }
}

//批量禁用启用
function changestatus(url,status){
	var aa = $('[name=id]');
	var ids = '';
 	for(var i=0;i<aa.length;i++){
        if(aa[i].checked){                             
        	if(ids == '') 
        	 	ids = aa[i].value;
        	else
         		ids = ids + "," + aa[i].value;
       	}
  	}
  	if(ids == ''){
   		alert("请选择批量操作项");
   		return ;
  	}
  	var params = {
   		"ids" : ids,
   		"status" : status
	};
    $.ajax( {   
        url : url,
        data : params,   
        dataType : "json",
        cache : false, 
        type : "POST",
        error : function(textStatus, errorThrown) {   
    		alert("系统ajax交互错误!");  
        },   
        success : function(data, textStatus) {   
            if (data.ajaxResult == "ajaxsuccess") {   
            	query($("#currPage").text());
                alert("操作成功!");		                
            } else {   
            	alert("操作失败!");   
            }   
        }
    });   
}

//除法函数，用来得到精确的除法结果
function accDiv(arg1,arg2){
    var t1=0,t2=0,r1,r2;
    try{t1=arg1.toString().split(".")[1].length}catch(e){}
    try{t2=arg2.toString().split(".")[1].length}catch(e){}
    with(Math){
        r1=Number(arg1.toString().replace(".",""));
        r2=Number(arg2.toString().replace(".",""));
        return (r1/r2)*pow(10,t2-t1);
    }
}

//乘法函数，用来得到精确的乘法结果
function accMul(arg1,arg2)
{
    var m=0,s1=arg1.toString(),s2=arg2.toString();
    try{m+=s1.split(".")[1].length}catch(e){}
    try{m+=s2.split(".")[1].length}catch(e){}
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
}

//加法函数，用来得到精确的加法结果
function accAdd(arg1,arg2){
    var r1,r2,m;
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2));
    return (arg1*m+arg2*m)/m;
}

//减法函数
function accSub(arg1,arg2){
     var r1,r2,m,n;
     try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
     try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
     m=Math.pow(10,Math.max(r1,r2));
     //last modify by deeka
     //动态控制精度长度
     n=(r1>=r2)?r1:r2;
     return ((arg2*m-arg1*m)/m).toFixed(n);
}

//身份证判断是否非法
function isIDno(strIDno){
	/**if (isIDno(sfzNo.value) == false) {
		alert("不存在此身份证");
		sfzNo.focus();
		return false;
	}**/
	var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};  
    var iSum = 0;  
    var info = "";  
    //var strIDno = obj.value;  
    var idCardLength = strIDno.length;    
    if(!/^\d{17}(\d|x)$/i.test(strIDno)&&!/^\d{15}$/i.test(strIDno))   {
        //alert("非法身份证号");  
        return false;  
    }  
   
    //在后面的运算中x相当于数字10,所以转换成a  
    strIDno = strIDno.replace(/x$/i,"a");  
  
    if(aCity[parseInt(strIDno.substr(0,2))]==null){  
        //alert("非法地区");  
        return false;  
    }  
      
    if (idCardLength==18){  
        sBirthday=strIDno.substr(6,4)+"-"+Number(strIDno.substr(10,2))+"-"+Number(strIDno.substr(12,2));  
        var d = new Date(sBirthday.replace(/-/g,"/"))  
        if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())){         
            //alert("非法生日");  
            return false;  
        }  
  
        for(var i = 17;i>=0;i --)  
            iSum += (Math.pow(2,i) % 11) * parseInt(strIDno.charAt(17 - i),11);  
  
        if(iSum%11!=1){  
            //alert("非法身份证号");  
            return false;  
        }  
    }  
    else if (idCardLength==15){  
        sBirthday = "19" + strIDno.substr(6,2) + "-" + Number(strIDno.substr(8,2)) + "-" + Number(strIDno.substr(10,2));  
        var d = new Date(sBirthday.replace(/-/g,"/"))  
        var dd = d.getFullYear().toString() + "-" + (d.getMonth()+1) + "-" + d.getDate();     
        if(sBirthday != dd)  
        {  
            //alert("非法生日");  
            return false;  
        }  
    }
    return true;
}

//UKEY验证
var digitArray = new Array('0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f');

function toHex( n ) {

    var result = ''
    var start = true;

    for ( var i=32; i>0; ) {
            i -= 4;
            var digit = ( n >> i ) & 0xf;

            if (!start || digit != 0) {
                    start = false;
                    result += digitArray[digit];
            }
    }

    return ( result == '' ? '0' : result );
}

function verifyUKey(isVerify){
	if(isVerify){
		var DevicePath,ret,n,mylen;
		try{
			//建立操作我们的锁的控件对象，用于操作我们的锁
	        var aObject = new ActiveXObject("Syunew6A.s_simnew6");
	        
	        //查找是否存在锁,这里使用了FindPort函数
			DevicePath = aObject.FindPort(0);
			if( aObject.LastError!= 0 ){
				alert("请插入UKEY再进行操作");
				return false;
			}
			//读取锁的唯一ID
			$("#keyID").val(toHex(aObject.GetID_1(DevicePath))+toHex(aObject.GetID_2(DevicePath)));
			
			if( aObject.LastError!= 0 )
			{
				window.alert( "Err to GetID,ErrCode is:"+aObject.LastError.toString());
				return false;
			}
			//这里返回对随机数的HASH结果
			$("#return_EncData").val(aObject.EncString(document.getElementById('rnd').value,DevicePath));
			
			if( aObject.LastError!= 0 ){
				window.alert( "Err to StrEnc,ErrCode is:"+aObject.LastError.toString());
				return false;
			}
			return true;
		} catch (e){
			alert(e.name + ": " + e.message);
		}
	}else{
		return true;
	}
}

//获取密钥
function getCipherCode(){
	var DevicePath,ret,n,mylen;
	try{
		//建立操作我们的锁的控件对象，用于操作我们的锁
        var aObject = new ActiveXObject("Syunew6A.s_simnew6");
        
        //查找是否存在锁,这里使用了FindPort函数
		DevicePath = aObject.FindPort(0);
		if( aObject.LastError!= 0 ){
			alert("请插入UKEY再进行操作");
			return false;
		}
		
		//读取锁的唯一ID
		$("#jmKeyID").val(toHex(aObject.GetID_1(DevicePath))+toHex(aObject.GetID_2(DevicePath)));
		
		if( aObject.LastError!= 0 ){
			window.alert("Err to GetID,ErrCode is:"+aObject.LastError.toString());
			return false;
		}
		
		//获取设置在锁中的用户名，使用默认的读密码"FFFFFFFF","FFFFFFFF
		//ret=aObject.YRead(8,1,"ffffffff","ffffffff",DevicePath);
		/**mylen =aObject.GetBuf(1);
		
		var cipherCode = aObject.YReadString(100,mylen, "ffffffff", "ffffffff", DevicePath);
		if( aObject.LastError!= 0 ){
			window.alert("Err to GetUserName,ErrCode is:"+aObject.LastError.toString());
			return ;
		}
		alert(aObject.LastError+"-"+cipherCode);
		if(cipherCode==""){
			alert("UKEY未设置密钥");
			return false;
		}**/
		
		addr=100;
		//先从地址0读到以前写入的字符串的长度
		ret = aObject.YRead(addr+0, 1, "ffffffff", "ffffffff", DevicePath);
		nlen =aObject.GetBuf(0);
		if( ret != 0 ){
           window.alert("读取字符串长度错误。错误码：" );
           return false;
		}
		//再读取相应长度的字符串
		cipherCode = aObject.YReadString(addr+1, nlen, "ffffffff", "ffffffff", DevicePath);
		if( aObject.LastError != 0 ){
			window.alert("读取密钥错误。错误码："+aObject.LastError);
			return false;
		}
		
		if(cipherCode==""){
			alert("UKEY未设置密钥");
			return false;
		}
		
		//这里返回对密钥的HASH结果
		cipherCode = aObject.EncString_New(cipherCode,DevicePath);
		
		if( aObject.LastError!= 0 ){
			window.alert( "Err to StrEnc,ErrCode is:"+aObject.LastError.toString());
			return false;
		}
		
		return cipherCode;
	}catch (e){
		alert(e.name + ": " + e.message);
		return false;
	}
	
	/**测试弹出框**/
	function  testAlert(test){
		alert("测试专用："+ test);
	}
	
}

