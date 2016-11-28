var jqueryAjaxLoading = $('.white_content');
var black_overla = $('.black_overla');
var black_overla = $('.ajax_msg');
function ajax_start(val){
	var content=val;
    if(content==null || content==""){
    	content="正在加载中...";
    }
    if(content == "fail"){
    	content=  "对不起，提交数据失败！"
    }
    if(content == "submit"){
    	content=  "正在提交请求，请稍后！"
    }
    if(content == "success"){
    	content=  "恭喜，操作成功！"
    }
    var html = "<div  class=\"white_content_ajax\" style=\"display:none;  position:absolute; top:50%; left:40%; width:200px; height:45px; z-index:9999; overflow:auto;\">";
        html +="<div class=\"ajax_msg\" style=\"width:190px; height:19px;background:#FF9900; font-size:14px; color:#fff; font-weight:bold; padding:3px 0 0 10px;\"><img src=\"images/loading.gif\"   width=\"15\" height=\"15\"/>"+content+"</div>";
        html +="</div>";;
        html +="<div class=\"black_overla_ajax\" style=\"display:none; position:absolute; top:0px; left:0px; width:100%; height:200%; \"><iframe src=\"javascript:false\" class=\"PassIframe\" style=\"position:absolute; visibility:inherit; top:0px; left:0px; width:90%; height:90%; z-index:-1;filter: alpha(opacity=0);-moz-opacity:0;opacity: 0;\"></iframe></div>";

    if(!jqueryAjaxLoading.html()) {
    	jQuery('body').append(html);
    	jqueryAjaxLoading = $(".white_content_ajax");
    	var offsetTop = 250 + $(window).scrollTop() +"px";
    	$(".white_content_ajax").animate({top : offsetTop },{ duration:600 , queue:false });
    }else{
    	$(".white_content").remove();
    	jQuery('body').append(html);
    	jqueryAjaxLoading = $(".white_content_ajax");
    	var offsetTop = 250 + $(window).scrollTop() +"px";
    	$(".white_content_ajax").animate({top : offsetTop },{ duration:600 , queue:false });
    }
    $('.white_content_ajax').show();
    $("div.black_overla_ajax").show(); 
}

function ajax_end(val){
	$("div.black_overla_ajax").hide();
    $('.white_content_ajax').hide("slow");
    if(val == 1){
    	history.go(-1);
    }
}

function del(url, msg,remove_type, obj){
	if (msg == "" || msg == null){
		msg = "是否确定删除该信息";
	}
	if (!confirm(msg)){
		return;
	}
	var data = $.ajax({
		type: "GET",
		url:url,
		async : false,//是否异步
		ifModified : false //IE下是否缓存
    }).responseText;
    if($.trim(data)=="success"){
    	ajax_start("success");
        if (remove_type == "remove_tr"){
        	$(obj).parent().parent().parent().remove();
            setTimeout("ajax_end()", 1000);
        }else if (remove_type == "remove"){
        	$("#"+obj).remove();
            setTimeout("ajax_end()", 1000);
        }else{
        	window.location.reload();
        }
    }else{
    	ajax_start(data);
    	setTimeout("ajax_end()", 1000);
        $("#btn_submit").attr("disabled","");
     }
}


function changeStatus(url, msg){
	if (msg == "" || msg == null){
		msg = "是否确定删除该信息";
	}
	if (!confirm(msg)){
		return;
	}
	var data = $.ajax({
		type: "GET",
		url:url,
		async : false,//是否异步
		ifModified : false //IE下是否缓存
    }).responseText;
  	if($.trim(data)=="success"){
  		ajax_start("success");
  		setTimeout("ajax_end()", 2000);
  		window.location.reload();
  	}else{
  		ajax_start(data);
  		setTimeout("ajax_end()", 1000);
  		$("#btn_submit").attr("disabled","");
  	}
}

function reload(){
	window.location.reload();
}

$(function(){
	$(window).scroll(function (){
		var offsetTop = 250 + $(window).scrollTop() +"px";
		$(".white_content_ajax").animate({top : offsetTop },{ duration:600 , queue:false });
	});
});







