$(document).ready(function(){
	
	// first example
	$("#browser").treeview();
	
	// second example
	$("#navigation").treeview({
		persist: "location",
		collapsed: true,
		unique: true
	});
	
	// third example
	$("#red").treeview({
		animated: "fast",
		collapsed: true,
		unique: true,
		persist: "cookie",
		toggle: function() {
			window.console && console.log("%o was toggled", this);
		}
	});
	
	// fourth example
	$("#black, #gray").treeview({
		control: "#treecontrol",
		persist: "cookie",
		cookieId: "treeview-black"
	});
	
	$(".privilegeid").click(function(){
		//查找所有父节点
		var parentlis = $(this).parents("li");
		if ($(this).attr("checked")) {
		    for (var i = 0; i < parentlis.length; i++) {
				parentlis.eq(i).children("input").attr("checked", true);
			}
		} else {
			$(this).attr("checked", false);
		}		
		//$("input[id='privilegeids']").attr("checked", true); 
	})
	$(".moduleid").click(function(){
		//查找所有父节点		
		if (!$(this).attr("checked")) {			
			var childrenprivilegeids = $(this).parent("li").find("li").children("input");
			for(var i=0;i<childrenprivilegeids.length;i++){
				childrenprivilegeids.eq(i).attr("checked", false)
			}
		} else {
			$(this).attr("checked", true);
			var parentlis = $(this).parents("li");
			for (var i = 0; i < parentlis.length; i++) {
				parentlis.eq(i).children("input").attr("checked", true);
			}
			var childrenprivilegeids = $(this).parent("li").find("li").children("input");
			for(var i=0;i<childrenprivilegeids.length;i++){
				childrenprivilegeids.eq(i).attr("checked", true)
			}
		}
	})

	$(".privilegeid").each(function(i){
		if ($(this).attr("checked")) {
			var parentlis = $(this).parents("li");
		    for (var i = 0; i < parentlis.length; i++) {
				parentlis.eq(i).children("input").attr("checked", true);
			}
		}		
	})
});
