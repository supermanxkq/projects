
//查询会员方法
function query(page) {
	var memId = $.trim($("#memId").val());
	var memRealName = $.trim($("#memRealName").val());
	var teleNo = $.trim($("#teleNo").val());
	var gradeId = $.trim($("#gradeId").val());
	var status = $.trim($("#status").val());

//json数据传输
	var params = {
		"memberDTO.memId" : memId,
        "memberDTO.memRealName" : memRealName,
        "memberDTO.teleNo" : teleNo,
        "memberDTO.status":status,
        "memberDTO.gradeId":gradeId,
        "orderProperty" : $("#orderProperty").val(),
        "orderDirection" : $("#orderDirection").val(),
        "memberDTO.page" : page
    }; 
   ajaxData("member/member!jsonPageList",params);
}
