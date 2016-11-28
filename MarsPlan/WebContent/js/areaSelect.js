var areaSelection = new Object();

areaSelection = {
	currentAreaChildsData : new Array(), 		//内部使用变量
	areaLayerId : "selection",					//可配置变量div的ID名称
	selectionIdPre : "level", 					//可配置变量#select框的ID名称
	firstAreaLevel : 1,            				//可配置变量#geo开始的级别
	lastArealevel : 5,             				//可配置变量#geo层的最后一层是多少
	
	initAreaSelection : function (id) {
		try{
			var url = "common/areaSelect!findById";
			var params = {
				"id" : id
			};
	  		var data = eval('('+this.ajaxLoad(url,params)+')');
	  		for(var i = 0; i < data.length; i++) {
	  			var parentId = data[i].child[0].parentId;
	  			if(data[i].child != null) {
			    	this.currentAreaChildsData[parentId] = data[i].child;
			    }
	      		this.buildNewSelection(this.currentAreaChildsData[parentId], data[i].areaLevel, data[i].id);
	  		}
	  		
	    	for(var g = 1; g <= 5; g++) {
	      		if(g < this.firstAreaLevel || g > this.lastArealevel) {
	        		$("#"+this.selectionIdPre+g).hide();
	     	 	}
	    	}
		}catch(ex){
	    }
		
		//初始化select框的动作属性
	    $("#"+this.areaLayerId).find("select").each(function(level) {
	    	level+=1;
	      	$(this).change(function () {
	        	if(level < areaSelection.lastArealevel) {
	          		for(var i = level+1; i <= areaSelection.lastArealevel; i++) {
	            		$("#"+areaSelection.selectionIdPre+i).empty();
	          		}

	          		//如果当前的select值不为空 则对其子选项初始化
	          		if($(this).val() != 0) {
	            		var data = areaSelection.getAreaChildByParentId($(this).val());
	            		areaSelection.buildNewSelection(data, level+1);
	          		}
	        	}

	        	areaSelection.currentLevel = 1;
	        	for(var i = areaSelection.firstAreaLevel; i <= areaSelection.lastArealevel; i++) {
	          		if($("#"+areaSelection.selectionIdPre+i).val() > 0) {
	          			areaSelection.currentLevel = i;
	          		}
	        	}
	      	});
	    });
  	},
  	
    buildNewSelection : function(data, level, selectedId) {
  	     var selectionObj = $("#"+this.selectionIdPre+level);
  	     selectionObj.empty();
  	     selectionObj.append("<option value='-1'>-请选择-</option>");
  	     for(var i = 0; i < data.length; i ++) {
  	       if(data[i].id == selectedId) {
  	         this.currentLevel = level;
  	         selectionObj.append("<option value='"+data[i].id+"' selected='selected' id='"+data[i].id+"'>"+data[i].displayName+"</option>");
  	       } else {
  	         selectionObj.append("<option value='"+data[i].id+"' id='"+data[i].id+"'>"+data[i].displayName+"</option>");
  	       }
  	       //this.currentGeoData[data[i].id] = data[i].geo_code;
  	     }
  	  },
  	
    getAreaChildByParentId : function (pid) {
  	  	 if(typeof(this.currentAreaChildsData[pid]) != "undefined") {
  	  		 return this.currentAreaChildsData[pid];
  	     } else {
  	    	 //把取回来的数据缓存起来，防止重复取数据浪费资源
  	    	 var url = "common/areaSelect!findByPid";
  	    	 var params = {
  	    		"pid" : pid
  	    	 };
  	    	 var data = eval('('+this.ajaxLoad(url,params)+')');
  	    	 this.currentAreaChildsData[pid] = data;
  	    	 return this.currentAreaChildsData[pid];
  	     }
  	  },
  	
  	ajaxLoad : function(url,params){
  		var result = "";
  		result = $.ajax({
  	       type: "GET",
  	       url: url,
  	       data : params,
  	       async : false
  	    }).responseText;
  		//alert(result);
  		return result;
  	}
}
