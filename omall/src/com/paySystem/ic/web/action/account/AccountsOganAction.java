package com.paySystem.ic.web.action.account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sun.misc.BASE64Decoder;

import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.service.base.MerchantsService;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.member.MemCardConnService;
import com.paySystem.ic.utils.MD5;
import com.paySystem.ic.utils.PublicConfig;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.utils.rsa.RsaUtil;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.account.AccountsDTO;
import com.paySystem.ic.web.dto.member.MemCardConnDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/** 账户信息action */
@Controller("/account/ogaccounts")
@Scope("prototype")
public class AccountsOganAction extends BaseAction {
	private static final long serialVersionUID = 4852135548955087560L;
	@Resource
	private  OrgansService oganService;
	@Resource
	private  MerchantsService merchantsService;
	@Resource
	private MemCardConnService memCardConnService;
	/** 帐户DTO **/
	private AccountsDTO accountsDTO = new AccountsDTO();

	/**
	 * 列表页面
	 * 
	 * @version 2011-9-8 下午08:50:23
	 * @return
	 * @throws Exception
	 */
	public String list() {
		UserSession us = Utils.getUserSession();
		if(us.getUserLevel()==0&&Utils.checkPermission("sy-1503-01")){
			return "organList";	
		}else if(us.getUserLevel()==2&&Utils.checkPermission("sy-1503-02")){
			return "memList";			
		}else {
			return "intercepthtml";
		}
	}
	

	
	
	/**
	*@Title:findOgins
	*@Description:机构查询账户信息
	*@Params:@return
	*@Params:@throws Exception
	*@Return:String
	*@author:张军磊
	*@Date:2014-11-28下午04:21:17
	*/
	public String findOgins() throws Exception{
		
		UserSession us = Utils.getUserSession();
		 ReturnDTO dto=new ReturnDTO();
		 List<AccountsDTO> listAccountsDTO = new ArrayList<AccountsDTO>();
	     Organs ogan = oganService.find(us.getOrganId());   
	     String orgCardNO =ogan.getOrgCard();
	     StringBuffer sb = new StringBuffer(PublicConfig.IP_ADDRESS+":"+PublicConfig.PORT+PublicConfig.ACCOUNTQUERY_URL);
	     sb.append("cardNo=" + orgCardNO + "");
	     //RSAUtils.
	     String pinBlock = RsaUtil.pubKey(MD5.MD5Encode(accountsDTO.getOganpws()));
	     String pubKey = "";
	     sb.append("&pinBlock="+pinBlock+"&pubKey="+pubKey+"");
	     System.out.println("访问地址--------------->"+sb.toString());
	    // String result="{ 'ret': 1, 'items': [  {   'cardNo': '0000000100000000020',   'pntMoney': '500.00',   'cardMoney': '10596.00'  } ]}" ;
	     String result = this.loadJSON(sb.toString());
	 	if (result != null && !"".equals(result) && !"null".equalsIgnoreCase(result)) {
	 		JSONObject jsonObj = JSONObject.fromObject(result);
			String ret = jsonObj.get("ret").toString();
			if ("1".equals(ret)) {
				String items=jsonObj.get("items").toString();
				JSONArray jsonArray=JSONArray.fromObject(items);
				for(int i=0;i<jsonArray.size();i++){
					AccountsDTO accountsDTOS=new AccountsDTO();
					String cardNO=jsonArray.getJSONObject(i).get("cardNo").toString();
					String pntMoney=jsonArray.getJSONObject(i).get("pntMoney").toString();
					String cardMoney=jsonArray.getJSONObject(i).get("cardMoney").toString();
					if("无积分账户".equals(pntMoney)&&"无现金账户".equals(cardMoney)){
						
						dto.setAmount("4");//此账户没有信息
					}else{
						dto.setAmount("5");//成功
						accountsDTOS.setOnAccId(cardNO);
						accountsDTOS.setCardMoney( new BigDecimal(cardMoney));
						accountsDTOS.setPointsNum(new BigDecimal(pntMoney));
						if(us.getMerId()==null){
							accountsDTOS.setMemId("");
						}else{
							accountsDTOS.setMemId(us.getMerId());
							
						}
						listAccountsDTO.add(accountsDTOS);
					}	
				}
			}else if ("0".equals(ret)) {
				
				dto.setAmount("2");//账户不存在//密码错误
				
			}else if ("2".equals(ret)){
				
				dto.setAmount("3");//此账户没有密码
				
			}
			
		
	 	}else{
	 		
	 		dto.setAmount("1");//网络错误
	 	}
	 	  dto.setObj(listAccountsDTO);
		 return Utils.printInfo(dto);
	}
	
	
	/**
	*@Title:findBusiness
	*@Description:查询商户账户信息
	*@Params:@return
	*@Params:@throws Exception
	*@Return:String
	*@author:张军磊
	*@Date:2014-11-28下午04:23:23
	*/
	public String findBusiness() throws Exception{
		UserSession us = Utils.getUserSession();
		 ReturnDTO dto=new ReturnDTO();
		 List<AccountsDTO> listAccountsDTO = new ArrayList<AccountsDTO>();
		  if(us.getMerId()!=null&&us.getMerId()!=""){
			  Merchants merchants= merchantsService.find(us.getMerId());
			  if(merchants!=null&&merchants.getMemId()!=null){
			  
			  MemCardConnDTO memCardConnDTO=memCardConnService.findByMemId(Integer.valueOf(merchants.getMemId()));
			  if(memCardConnDTO!=null){
				  String orgCardNO = memCardConnDTO.getCardNo();  
				  if(orgCardNO!=null&&orgCardNO!=""){
					  StringBuffer sb = new StringBuffer(PublicConfig.IP_ADDRESS+":"+PublicConfig.PORT+PublicConfig.ACCOUNTQUERY_URL);
					     sb.append("cardNo=" + orgCardNO + "");
					     //RSAUtils.
					     String pinBlock = RsaUtil.pubKey(MD5.MD5Encode(accountsDTO.getOganpws()));
					     String pubKey = "";
					     sb.append("&pinBlock="+pinBlock+"&pubKey="+pubKey+"");
					     System.out.println("访问地址--------------->"+sb.toString());
					     //String result="{ 'ret': 1, 'items': [  {   'cardNo': '0000000100000000020',   'pntMoney': '500.00',   'cardMoney': '10596.00'  } ]}" ;
					     String result = this.loadJSON(sb.toString());
					 	if (result != null && !"".equals(result) && !"null".equalsIgnoreCase(result)) {
					 		JSONObject jsonObj = JSONObject.fromObject(result);
							String ret = jsonObj.get("ret").toString();
							if ("1".equals(ret)) {
								String items=jsonObj.get("items").toString();
								JSONArray jsonArray=JSONArray.fromObject(items);
								for(int i=0;i<jsonArray.size();i++){
									AccountsDTO accountsDTOS=new AccountsDTO();
									String cardNO=jsonArray.getJSONObject(i).get("cardNo").toString();
									String pntMoney=jsonArray.getJSONObject(i).get("pntMoney").toString();
									String cardMoney=jsonArray.getJSONObject(i).get("cardMoney").toString();
									if("无积分账户".equals(pntMoney)&&"无现金账户".equals(cardMoney)){
										
										dto.setAmount("4");//此账户没有信息
									}else{
										dto.setAmount("5");//成功
										accountsDTOS.setOnAccId(cardNO);
										accountsDTOS.setCardMoney( new BigDecimal(cardMoney));
										accountsDTOS.setPointsNum(new BigDecimal(pntMoney));
										if(us.getMerId()==null){
											accountsDTOS.setMemId("");
										}else{
											accountsDTOS.setMemId(us.getMerId());
											
										}
										listAccountsDTO.add(accountsDTOS);
									}	
								}
							}else if ("0".equals(ret)) {
								
								dto.setAmount("2");//账户不存在//密码错误
								
							}else if ("2".equals(ret)){
								
								dto.setAmount("3");//此账户没有密码
								
							}
							String str = new String(new BASE64Decoder().decodeBuffer(jsonObj.get("tip").toString()));
				            dto.setMsg(str);
						
					 	}else{
					 		
					 		dto.setAmount("1");//网络错误
					 	}  
					  
					  
					  
				  }else{
					  
					  dto.setAmount("6");  
				  }
				  
				  
			  }else{
				  dto.setAmount("6");  
				  
			  }
		
		  }else{
			  
			  dto.setAmount("6"); 	  
		  }
		  }else{
			  dto.setAmount("6"); 
		  }
	    
	    
	   
	 	  dto.setObj(listAccountsDTO);
		 return Utils.printInfo(dto);
	}
	/**
	*@Title:changePassWord
	*@Description:更改支付账户密码
	*@Params:@return
	*@Params:@throws Exception
	*@Return:String
	*@author:张军磊
	*@Date:2014-11-28下午04:22:02
	*/
	public String changePassWord() throws Exception{
		 ReturnDTO dto=new ReturnDTO();
		 StringBuffer sb = new StringBuffer(PublicConfig.IP_ADDRESS+":"+PublicConfig.PORT+""+"/"+PublicConfig.PWDCHANGEUPDATE_URL);
		// ../omall/pwdChange?cardNo=1000010000000020&code=249727&tel=13213117596&oldPinBlock=1&newPinBlock=2&pubKey=1
		 String oldpinBlock = RsaUtil.pubKey(MD5.MD5Encode(accountsDTO.getOganpws()));
	     //String oldpubKey = "";		
	     String newpinBlock = RsaUtil.pubKey(MD5.MD5Encode(accountsDTO.getNewOganpws()));
	     String newpubKey = "";	
		 sb.append("cardNo="+accountsDTO.getOnAccId()+"");
		 sb.append("&code="+accountsDTO.getPhoneCheckNum()+"");
		 sb.append("&tel="+accountsDTO.getMemPhoneNum()+"");
		 sb.append("&oldPinBlock="+oldpinBlock+"");
		 sb.append("&newPinBlock="+newpinBlock+"");
		 sb.append("&pubKey="+newpubKey+"");
		 System.out.println("访问地址--------------->"+sb.toString());
		 String result = this.loadJSON(sb.toString());
			if (result != null && !"".equals(result) && !"null".equalsIgnoreCase(result)) {
				JSONObject jsonObj = JSONObject.fromObject(result);
				String ret = jsonObj.get("ret").toString();
				if("1".equals(ret)){
					
					dto.setAmount("2");//修改密码成功！
				}else{
					
					dto.setAmount("3");//修改密码失败！
				}
				String str = new String(new BASE64Decoder().decodeBuffer(jsonObj.get("tip").toString()));
	            dto.setMsg(str);
			}else{
				
				dto.setAmount("1");//网络错误
			}
		 return Utils.printInfo(dto);
		
	}
	
	
	 	/**
		 * 
		 *@Title: loadJSON
		 *@Description: 访问接口
		 *@Params: @param url 接口地址
		 *@Params: @return
		 *@Return: String
		 *@author:张军磊
		 *@Date:2014-11-27上午10:15:23
		 */
		public String loadJSON(String url) {
			StringBuilder json = new StringBuilder();
			try {
				URL oracle = new URL(url);
				URLConnection yc = oracle.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(yc
						.getInputStream(), "utf8"));
				String inputLine = null;
				while ((inputLine = in.readLine()) != null) {
					json.append(inputLine);
				}
				in.close();
			} catch (MalformedURLException e) {
			} catch (IOException e) {
			}
			return json.toString();
		}
	
	/**
	*@Title:sendPhoneCheckNum
	*@Description:发送短信验证码
	*@Params:@return
	*@Return:String
	*@author:张军磊
	 * @throws Exception 
	*@Date:2014-11-28下午12:08:23
	*/
	public String sendPhoneCheckNum() throws Exception{
		
		
		 ReturnDTO dto=new ReturnDTO();
		
		 StringBuffer sb = new StringBuffer(PublicConfig.IP_ADDRESS+":"+PublicConfig.PORT+"/"+PublicConfig.CHECKSENDPHONECHECKNUM_URL);
		
		 String memPhoneNum= accountsDTO.getMemPhoneNum();
		 sb.append("name="+memPhoneNum+"");
		 System.out.println("访问地址--------------->"+sb.toString());
		 String result = this.loadJSON(sb.toString());
			if (result != null && !"".equals(result) && !"null".equalsIgnoreCase(result)) {
				JSONObject jsonObj = JSONObject.fromObject(result);
				String ret = jsonObj.get("ret").toString();
				if("0".equals(ret)){
					
					dto.setAmount("2");//发送验证码失败！
				}else{
					dto.setAmount("3");//发送验证码成功！
				}
				String str = new String(new BASE64Decoder().decodeBuffer(jsonObj.get("tip").toString()));
	            dto.setMsg(str);
			}else {
				dto.setAmount("1");//网络错误
			}
		 return Utils.printInfo(dto);
	}

	public AccountsDTO getAccountsDTO() {
		return accountsDTO;
	}

	public void setAccountsDTO(AccountsDTO accountsDTO) {
		this.accountsDTO = accountsDTO;
	}

}
