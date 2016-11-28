$().ready(function() {
	var dataUrl = "softwarestyle/softwarestyle!jsonPageList";
	var divId = "softwarestylelist";
	var softwarestylelist = $("#" + divId);
	$.ajax({
		async : false,
		url : dataUrl,
		dataType : "json",
		cache : false,
		type : "POST",
		error : function(textStatus, errorThrown) {
		},
		beforeSend : function() {
		},
		success : function(data, textStatus) {
			softwarestylelist.html("");
			softwarestylelist.html(data);
		}
	});
});
