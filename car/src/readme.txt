1、初始化系统模块以及相应权限：在Globals.java中ISINIT_MODULE_PRIVILEGE值设置成true;
2、如初始化需创建角色信息：在Globals.java中ISCREATE_ADMIN_ROLE_USER值设置成true，
	ROLE_CODE值对应会员系统角色编号，ROLE_NAME为待创建的角色名称;（慎用）
3、系统初始化执行路径：http://localhost:8080/omall/system/init!init?sign=TYT57GFR68IJGT8EOMFMU8WR87326GFS
http://localhost:8080/car/system/init!init?sign=TYT57GFR68IJGT8EOMFMU8WR87326GFS
4、删除系统自带struts2包
	antlr-2.7.2.jar
	struts2-convention-plugin-2.1.8.jar
	struts2-json-plugin-2.1.8.jar
	
	
	
	2016.02.16更新记录
	
	1.更新了查询中的trim方法，正确的使用方法是$.trim("a b cd");而项目中使用的是$("#id").val().trim();导致在IE浏览器中不能加载出数据。
	2.更新了，如果添加到购物车中的商品，如果没有来得及进行处理的话，就直接将库存里面的商品删除或者是下架，那么购物车中的商品也应该是不存在的。
	