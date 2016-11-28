/**  
* @Title: emailList.java
* @Package: com.paySystem.ic.bean.mail
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-24 下午04:18:21
* @Version: V1.0  
*/
package com.paySystem.ic.bean.mail;

import java.util.ArrayList;
import java.util.List;

import com.paySystem.ic.web.dto.mail.EmailTemplateDTO;

/**
 * @ProjectName:omall2014911
 * @ClassName:emailList
 * @Description:常用数据
 * @date: 2014-9-24
 * @author: 孙晓磊
 * @version: V1.0
 */
public class emailList {
	
	
	
	/**
	*@Title:getemailList
	*@Description:获得 常用数据list
	*@Params:
	*@Return:void
	*@author:孙晓磊
	*@Date:2014-9-24下午04:33:24
	*/
	public  static List<EmailTemplateDTO> getemailList()
	{
	    List<EmailTemplateDTO> listemail=new ArrayList<EmailTemplateDTO>();
		EmailTemplateDTO emaildto1=new EmailTemplateDTO();
		emaildto1.setEmailTile("设置密码");
		emaildto1.setEtContent("您好！<br><br>您已经进行了密码重置的操作，请点击以下链接(或者复制到您的浏览器):<br><br>以确认您的新密码重置操作！<br><br>");
		listemail.add(emaildto1);
		
		EmailTemplateDTO emaildto2=new EmailTemplateDTO();
		emaildto2.setEmailTile("订单确认通知");
		emaildto2.setEtContent("亲爱的{$order.consignee}，你好！ 我们已经收到您于 {$order.formated_add_time} 提交的订单，该订单编号为：{$order.order_sn} 请记住这个编号以便日后的查询。{$shop_name}{$sent_date}");
		listemail.add(emaildto2);
		
		EmailTemplateDTO emaildto3=new EmailTemplateDTO();
		emaildto3.setEmailTile("发货通知");
		emaildto3.setEtContent("亲爱的{$order.consignee}。你好！您的订单{$order.order_sn}已于{$send_time}按照您预定的配送方式给您发货了。{if $order.invoice_no}发货单号是{$order.invoice_no}。{/if} 在您收到货物之后请点击下面的链接确认您已经收到货物：{$confirm_url} 如果您还没有收到货物可以点击以下链接给我们留言： {$send_msg_url}再次感谢您对我们的支持。欢迎您的再次光临。 {$shop_name} {$send_date}");
		listemail.add(emaildto3);
		
		EmailTemplateDTO emaildto4=new EmailTemplateDTO();
		emaildto4.setEmailTile("订单取消");
		emaildto4.setEtContent("亲爱的{$order.consignee}，你好！ 您的编号为：{$order.order_sn}的订单已取消。{$shop_name}{$send_date}");
		listemail.add(emaildto4);
		
		EmailTemplateDTO emaildto5=new EmailTemplateDTO();
		emaildto5.setEmailTile("订单无效");
		emaildto5.setEtContent("亲爱的{$order.consignee}，你好！您的编号为：{$order.order_sn}的订{$shop_name}{$send_date}");
		listemail.add(emaildto5);
		
		EmailTemplateDTO emaildto6=new EmailTemplateDTO();
		emaildto6.setEmailTile("发红包");
		emaildto6.setEtContent("亲爱的{$user_name}您好！恭喜您获得了{$count}个红包，金额{if $count > 1}分别{/if}为{$money}{$shop_name}{$send_date}");
		listemail.add(emaildto6);
		
		EmailTemplateDTO emaildto7=new EmailTemplateDTO();
		emaildto7.setEmailTile("团购商品");
		emaildto7.setEtContent("亲爱的{$consignee}，您好！您于{$order_time}在本店参加团购商品活动，所购买的商品名称为：{$goods_name}，数量：{$goods_number}，订单号为：{$order_sn}，订单金额为：{$order_amount}此团购商品现在已到结束日期，并达到最低价格，您现在可以对该订单付款。请点击下面的链接：{$shop_url}请尽快登录到用户中心，查看您的订单详情信息。 {$shop_name} {$send_date}");
		listemail.add(emaildto7);
		
		EmailTemplateDTO emaildto8=new EmailTemplateDTO();
		emaildto8.setEmailTile("邮件验证");
		emaildto8.setEtContent("{$user_name}您好这封邮件是 {$shop_name} 发送的。你收到这封邮件是为了验证你注册邮件地址是否有效。如果您已经通过验证了，请忽略这封邮件。请点击以下链接(或者复制到您的浏览器)来验证你的邮件地址:{$validate_email} {$shop_name} {$send_date}");
		listemail.add(emaildto8);
		
		EmailTemplateDTO emaildto9=new EmailTemplateDTO();
		emaildto9.setEmailTile("虚拟卡片");
		emaildto9.setEtContent("亲爱的{$order.consignee}你好！您的订单{$order.order_sn}中{$goods.goods_name} 商品的详细信息如下:{foreach from=$virtual_card item=card}{if $card.card_sn}卡号：{$card.card_sn}{/if}{if $card.card_password}卡片密码：{$card.card_password}{/if}{if $card.end_date}截至日期：{$card.end_date}{/if}{/foreach}再次感谢您对我们的支持。欢迎您的再次光临。{$shop_name} {$send_date}");
		listemail.add(emaildto9);
		
		EmailTemplateDTO emaildto10=new EmailTemplateDTO();
		emaildto10.setEmailTile("关注管理");
		emaildto10.setEtContent("亲爱的{$order.consignee}，你好！ 我们已经收到您于 {$order.formated_add_time} 提交的订单，该订单编号为：{$order.order_sn} 请记住这个编号以便日后的查询。{$shop_name}{$sent_date}");
		listemail.add(emaildto10);
		
		EmailTemplateDTO emaildto11=new EmailTemplateDTO();
		emaildto11.setEmailTile("新订单提醒模板");
		emaildto11.setEtContent("亲爱的店长，您好： 快来看看吧，又有新订单了。  订单号:{$order.order_sn} 订单金额:{$order.order_amount}，用户购买商品:{foreach from=$goods_list item=goods_data}{$goods_data.goods_name}(货号:{$goods_data.goods_sn})    {/foreach} 收货人:{$order.consignee}，  收货人地址:{$order.address}，收货人电话:{$order.tel} {$order.mobile},  配送方式:{$order.shipping_name}(费用:{$order.shipping_fee}), 付款方式:{$order.pay_name}(费用:{$order.pay_fee})。     系统提醒  {$send_date}");
		listemail.add(emaildto11);
		
		EmailTemplateDTO emaildto12=new EmailTemplateDTO();
		emaildto12.setEmailTile("缺货回复");
		emaildto12.setEtContent("亲爱的{$user_name}。你好！{$dispose_note}您提交的缺货商品链接为{$goods_name}{$shop_name}{$send_date}");
		listemail.add(emaildto12);
		
		EmailTemplateDTO emaildto13=new EmailTemplateDTO();
		emaildto13.setEmailTile("留言回复模板");
		emaildto13.setEtContent("<p>亲爱的{$user_name}。你好！<br /><br />对您的留言：<br />{$message_content}<br /><br />店主作了如下回复：<br />{$message_note}<br /><br />您可以随时回到店中和店主继续沟通。<br />{$shop_name}<br />{$send_date}</p>");
		listemail.add(emaildto13);
		
		EmailTemplateDTO emaildto14=new EmailTemplateDTO();
		emaildto14.setEmailTile("用户评论回复");
		emaildto14.setEtContent("亲爱的{$user_name}。你好！对您的评论：“{$comment}”店主作了如下回复：“{$recomment}”您可以随时回到店中和店主继续沟通。{$shop_name}{$send_date}");
		listemail.add(emaildto14);
		return listemail;
	}
		

}
