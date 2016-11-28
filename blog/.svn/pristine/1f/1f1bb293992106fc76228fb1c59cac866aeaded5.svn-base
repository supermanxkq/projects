<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/back/share/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort()
+ path + "/"; %>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="true">
    <head>
     <base href="<%=basePath%>">
        <script type="text/javascript" src="js/jquery.min.js">
        </script>
        <link href="css/ordercancel.css" rel="stylesheet" type="text/css" />
        <link href="css/user.ordercancel.css" rel="stylesheet" type="text/css" />
        <link href="css/user.base.css" rel="stylesheet" type="text/css" />
         <script src="js/jquery-1.8.3.min.js"></script>
          <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
   <link href="assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
   <link href="assets/bootstrap/css/bootstrap-fileupload.css" rel="stylesheet" />
   <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
   <script src="js/common.js"></script>
        <script type="text/javascript">
            /**取消原因显示隐藏*/
            $(document).ready(function(){
                $(".cont-text").click(function(){
                    if ($(".content").is(":hidden")) {
                        $(".content").show();
                    }
                    else {
                        $(".content").hide();
                    }
                });
            });
            
            /**取消对话框显示*/
            function orderCancelShow(orderId){
                $("#orderId").html(orderId);
                /**判断是否有选中的，如果有选中的清空*/
               $(".radio").removeAttr("checked");
                $("#cancelText").hide();
                $(".cont-text p").html("");
                $(".content").hide();
                $("#optionArea").val("");
                $("#errorMsg").hide();
				$("#errorMsg").hide();
                $(".thickdiv").show();
                $(".thickbox").show();
            }
            
            /**取消对话框隐藏*/
            function orderCancelHide(){
                $(".thickdiv").hide();
                $(".thickbox").hide();
            }
            
            /**设置选中值*/
            function setSuitShows(obj){
                $("#cancelText").hide();
                $(".cont-text p").html(obj.value);
                $(".content").hide();
            }
            
            /**显示其他原因对话框*/
            function showOthorReason(obj){
                $("#cancelText").show();
                $(".cont-text p").html(obj.value);
                $(".content").hide();
            }
            
            /**验证字数*/
            function checkText(){
                if ($("#optionArea").val() == ""){
                    $("#errorMsg").html("请输入原因！");
                    $("#errorMsg").show();
                    return false;
                }
                else {
                    if ($("#optionArea").val().length > 255) {
                        $("#errorMsg").html("最多255字！");
                        $("#errorMsg").show();
                        return false;
                    }
                    else {
                        $("#errorMsg").hide();
                        return true;
                    }
                }
            }
            
            
            /**取消订单*/
            function cancelOrder(){
                /**判断是否有选中的，如果有选中的清空*/
                var val = $('input:radio[name="radio_option1"]:checked').val();
                if (val == null) {
					 $("#errorMsg").html("请选择取消原因。");
                        $("#errorMsg").show();
                }
                else {
					 $("#errorMsg").hide();
                    /**获取取消原因*/
                    var orderCancelReason = $(".cont-text p").html();
                    if (orderCancelReason == "其他原因") {
						orderCancelReason = $("#optionArea").val();
						if(checkText()){
							/**获取取消订单号*/
						var orderId = $("#orderId").html();
						/**json数据传输*/
						var params = {
							"orderCancelDTO.cancelReason": orderCancelReason,
							"orderCancelDTO.orderId": orderId
						};
						var actionUrl = "orders/orders!orderCancel";
						$.ajax({
							url: actionUrl,
							data: params,
							dataType: "json",
							cache: false,
							type: "POST",
							error: function(textStatus, errorThrown){
							},
							success: function(data, textStatus){
								$(".thickdiv").hide();
								$(".thickbox").hide();
								query(1);
							}
						});
						}
					}
					else {
						/**获取取消订单号*/
						var orderId = $("#orderId").html();
						/**json数据传输*/
						var params = {
							"orderCancelDTO.cancelReason": orderCancelReason,
							"orderCancelDTO.orderId": orderId
						};
						var actionUrl = "orders/orders!orderCancel";
						$.ajax({
							url: actionUrl,
							data: params,
							dataType: "json",
							cache: false,
							type: "POST",
							error: function(textStatus, errorThrown){
							},
							success: function(data, textStatus){
								$(".thickdiv").hide();
								$(".thickbox").hide();
								query(1);
							}
						});
					}
                }
            }
        </script>
    </head>
    <body>
        <!--取消订单提示框-->
        <div class="thickdiv" id="" style="display: none;">
        </div>
        <div class="thickbox" id="" style="left: 476.5px; top: 172px; width: 422px; height: 333px;display: ;">
            <div class="thickwrap" style="width: 420px;">
                <div class="thicktitle" id="" style="width:400">
                    <span>用户登录</span>
                </div>
                <form name="frmlogin" action="system/login!login"
											method="post">
										
                <div class="thickcon" id="" style="width: 400px; height: 380px; padding-left: 10px; padding-right: 10px;">
                    <div class="cancle-box">
                        <div class="form">
                            <div class="item clearfix">
                            <table>
                            <tr>
                            	<td><em>*</em>用户名：</td>
                            	<td><input type="text" name="userDTO.userName" id="userName"
												placeholder="用户名" class="input-medium"> </td>
                            </tr>
                               <tr>
                               	<td><em>*</em>密码：</td>
                               	<td><input type="password" name="userDTO.passWord"
												placeholder="密码" class="input-medium"></td>
                               </tr>
                                </table>
                            </div>
                        </div>
                        <div class="op-btns" id="odo_cancel_step">
                                	<button type="submit" class="btn btn-success">
												登录
											</button>
                                	<input type="button" class="btn" value="返回" onclick="go('article/article!list?columnCode=1');"/>
                        </div>
                        <div class="tip-msg ftx-04" id="cancelText0">
                        ·<s:if test="hasFieldErrors()">
                            <dl>
                                <dt>
                                  	  温馨提示：
                                </dt>
                                <dd>
                                    <div id="cancelTextDiv0">
                                   <dt>    
							<s:fielderror name="error"
								cssStyle="list-style-type:none;" />
								</dt>
                                    </div>
                                </dd>
                            </dl>
                            </s:if>
                        </div>
                    </div>
                </div>
               </form>
            </div>
        </div>
        <!--取消订单提示框结束-->
    </body>
</html>
