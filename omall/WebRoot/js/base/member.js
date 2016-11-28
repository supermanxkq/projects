var   contentFlag=false;


//查询会员方法
function query(page) {
	var memId = $.trim($("#memId").val());
	var realName = $.trim($("#realName").val());
	var teleNo = $.trim($("#teleNo").val());
	var status = $.trim($("#status").val());
	/**去掉全选状态*/
	$("#checkAll").attr("checked",false);
	// json数据传输
	var params = {
		"membersDTO.memId" : memId,
		"membersDTO.realName" : realName,
		"membersDTO.teleNo" : teleNo,
		"membersDTO.status" : status,		
		"orderProperty" : $("#orderProperty").val(),
		"orderDirection" : $("#orderDirection").val(),
		"membersDTO.page" : page
	};
	ajaxData("member/member!jsonPageList", params);
}


/**验证内容信息*/
function checkContent(){
	var  contentValue=$("#content").val();
		if(contentValue.length>256){
			$("#contentMsg").html("最多输入255字！");
			$("#contentMsg").show();
			contentFlag=false;
			}else{
				$("#contentMsg").hide();
				contentFlag=true;
			}
}
