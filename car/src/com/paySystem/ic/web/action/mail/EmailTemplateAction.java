package com.paySystem.ic.web.action.mail;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.mail.emailList;
import com.paySystem.ic.service.mail.EmailTemplateService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.mail.EmailTemplateDTO;

/**
 * @ProjectName:omall2014911
 * @ClassName:EmailTemplateAction
 * @Description:TODO
 * @date: 2014-9-22
 * @author: 孙晓磊
 * @version: V1.0
 */
@Controller("/mail/EmailTemplate")
@Scope("prototype")
public class EmailTemplateAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	EmailTemplateDTO emailTemplateDTO=new EmailTemplateDTO();
	
	public EmailTemplateDTO getEmailTemplateDTO() {
		return emailTemplateDTO;
	}

	public void setEmailTemplateDTO(EmailTemplateDTO emailTemplateDTO) {
		this.emailTemplateDTO = emailTemplateDTO;
	}
    @Resource 
    EmailTemplateService emailTemplateService;
    @Resource
	FunctionsService functionsService;
	/**
	*@Title:list
	*@Description:TODO
	*@Params:@return
	*@Return:String
	*@author:孙晓磊
	*@Date:2014-9-23上午11:47:27
	*/
	public String list() {
		this.getRequest().setAttribute("etName", OptionsValue.EMAIL_TYPE);
		this.setMethod("addUI");
		return "list";
	}
	
	/**
	*@Title:getEmail
	*@Description:得到模板信息
	*@Params:
	*@Return:void
	*@author:孙晓磊
	*@Date:2014-9-23下午03:53:52
	*/
	public String  getEmail()
	{
		Integer eId=emailTemplateDTO.getEmailId();
		emailTemplateDTO=emailTemplateService.getEmailTemplateDTObyid(eId);
		List<String> list=new ArrayList<String>();
		list.add(emailTemplateDTO.getEmailTile());
		list.add(emailTemplateDTO.getEtContent());
		
		this.setMethod("addUI");
		functionsService.saveFunction("邮件常用模板", 1, "查找模板：" + emailTemplateDTO.getEmailId());
		return Utils.printInfo(list);
		
	}
	/**
	*@Title:addUI
	*@Description:修改方法
	*@Params:@return
	*@Return:String
	*@author:孙晓磊
	*@Date:2014-9-24下午02:09:14
	*/
	public String addUI()
	{
		 Integer id=emailTemplateDTO.getEmailId();
		 EmailTemplateDTO emailDTO=emailTemplateService.getEmailTemplateDTObyid(id);
		 emailDTO.setEmailTile(emailTemplateDTO.getEmailTile());
		
		 emailDTO.setEtContent(emailTemplateDTO.getEtContent());
	     emailTemplateService.updateemail(emailDTO);
	     functionsService.saveFunction("修改常用模板", 1, "修改模版："+emailTemplateDTO.getEmailId());
	     this.getRequest().setAttribute("result",
					this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url", "mail/EmailTemplate!list");
	     return SUCCESS;
		
	}
	/**
	*@Title:resetinf
	*@Description:重置全部信息
	*@Params:@return
	*@Return:String
	*@author:孙晓磊
	*@Date:2014-9-24下午04:43:28
	*/
	public String resetinf()
	{
		List<EmailTemplateDTO> listdto=emailList.getemailList();
	    for(int i=0;i<listdto.size();i++)
	    {
	    	EmailTemplateDTO emailTemplateDTO=listdto.get(i); 
	    	EmailTemplateDTO emailDTO=emailTemplateService.getEmailTemplateDTObyid(i+1);
	    	emailDTO.setEmailTile(emailTemplateDTO.getEmailTile());
	    	emailDTO.setEtContent(emailTemplateDTO.getEtContent());
	    	emailTemplateService.updateemail(emailDTO);
	    }
		 this.getRequest().setAttribute("result",
					this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url", "mail/EmailTemplate!list");
	     return SUCCESS;
	}
	/**
	*@Title:resetEvery
	*@Description:重置每条信息
	*@Params:@return
	*@Return:String
	*@author:孙晓磊
	*@Date:2014-9-24下午04:55:49
	*/
	public String resetEvery()
	{
		Integer id=emailTemplateDTO.getEmailId();
		List<EmailTemplateDTO> listdto=emailList.getemailList();
		EmailTemplateDTO emailTemplateDTO=listdto.get(id-1); 
		EmailTemplateDTO emailDTO=emailTemplateService.getEmailTemplateDTObyid(id);
		emailDTO.setEmailTile(emailTemplateDTO.getEmailTile());
		emailDTO.setEtContent(emailTemplateDTO.getEtContent());
		emailTemplateService.updateemail(emailDTO);
		
	     return Utils.printInfo(emailDTO);
	}


}
