/**  
* @Title: EmailTemplateDaoImpl.java
* @Package: com.paySystem.ic.dao.mail.impl
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-23 下午12:06:45
* @Version: V1.0  
*/
package com.paySystem.ic.dao.mail.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import com.paySystem.ic.bean.mail.EmailTemplate;
import com.paySystem.ic.dao.mail.EmailTemplateDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.web.dto.mail.EmailTemplateDTO;

/**
 * @ProjectName:omall2014911
 * @ClassName:EmailTemplateDaoImpl
 * @Description:邮件常用模板dao实现类
 * @date: 2014-9-23
 * @author: 孙晓磊
 * @version: V1.0
 */
@Repository(EmailTemplateDAO.EMAILTEMPLATE)
public class EmailTemplateDaoImpl extends DaoSupport<EmailTemplate> implements EmailTemplateDAO{

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.mail.EmailTemplateDAO#find(java.lang.Integer)
	 *@MethodName:find
	 *@Description:通过id查找模板信息
	 *@param eId
	 *@return
	 *@Author:孙晓磊
	 *@Date:2014-9-23下午12:07:56n.,
	 */
	@SuppressWarnings("unchecked")
	public EmailTemplateDTO findEmailTemplate(Integer eId) {
		
		String sql="select o from EmailTemplate o where o.emailId=?";
		Query query=em.createQuery(sql);
		query.setParameter(1, eId);
		List<EmailTemplate> emaillist=query.getResultList();
		EmailTemplate emailtemplate=emaillist.get(0);
		EmailTemplateDTO emailtemplatedto=getemailplateDto(emailtemplate);
		return emailtemplatedto;
	}
	/**
	*@Title:getemailplateDto
	*@Description:实体转换
	*@Params:@param emailTemplate
	*@Params:@return
	*@Return:EmailTemplateDTO
	*@author:孙晓磊
	*@Date:2014-9-23下午02:35:19
	*/
	public EmailTemplateDTO getemailplateDto(EmailTemplate emailTemplate)
	{
		EmailTemplateDTO emailtemplatedto=new EmailTemplateDTO();
		if(emailTemplate!=null)
		{
			if(emailTemplate.getEmailId()!=null)
			{
				emailtemplatedto.setEmailId(emailTemplate.getEmailId());
			}
			if(emailTemplate.getEmailTitle()!=null)
			{
				emailtemplatedto.setEmailTile(emailTemplate.getEmailTitle());
			}
			if(emailTemplate.getEmailType()!=null)
			{
				emailtemplatedto.setEmailType(Integer.parseInt(emailTemplate.getEmailType()));
			}
			if(emailTemplate.getEtContent()!=null)
			{
				emailtemplatedto.setEtContent(emailTemplate.getEtContent());
			}
			if(emailTemplate.getEtmailName()!=null)
			{
				emailtemplatedto.setEmailName(emailTemplate.getEtmailName());
			}
			if(emailTemplate.getOperator()!=null)
			{
				emailtemplatedto.setOperator(emailTemplate.getOperator());
			}
			if(emailTemplate.getUpdateTime()!=null)
			{
				String emailupdate=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(emailTemplate.getUpdateTime());
				emailtemplatedto.setUpdate(emailupdate);
			}
			
		}
		return emailtemplatedto;
	}
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.mail.EmailTemplateDAO#updateemail(com.paySystem.ic.web.dto.mail.EmailTemplateDTO)
	 *@MethodName:updateemail
	 *@Description:更新模板信息
	 *@param emaildto
	 *@Author:孙晓磊
	 *@Date:2014-9-24上午11:40:53
	 */
	public void updateemail(EmailTemplateDTO emaildto) {
		
		EmailTemplate emailTemplate=getEmailTemplate(emaildto);
		this.update(emailTemplate);
		
	}
	/**
	*@Title:getEmailTemplate
	*@Description:实体转换
	*@Params:@param emaildto
	*@Params:@return
	*@Return:EmailTemplate
	*@author:孙晓磊
	*@Date:2014-9-24下午02:19:45
	*/
	public EmailTemplate getEmailTemplate(EmailTemplateDTO emaildto)
	{
		EmailTemplate emailTemplate=new EmailTemplate();
		if(emaildto.getEmailId()!=null)
		{
			emailTemplate.setEmailId(emaildto.getEmailId());
		}
		if(emaildto.getEmailName()!=null)
		{
			emailTemplate.setEtmailName(emaildto.getEmailName());
		}
		if(emaildto.getEmailType()!=null)
		{
			emailTemplate.setEmailType(emaildto.getEmailType().toString());
		}
		if(emaildto.getEtContent()!=null)
		{
			emailTemplate.setEtContent(emaildto.getEtContent());
		}
		if(emaildto.getOperator()!=null)
		{
			emailTemplate.setOperator(emaildto.getOperator());
		}
		if(emaildto.getUpdate()!=null)
		{
			Date update=null;
			try {
				update = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").parse(emaildto.getUpdate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			emailTemplate.setUpdateTime(update);
		}
		if(emaildto.getEmailTile()!=null)
		{
			emailTemplate.setEmailTitle(emaildto.getEmailTile());
		}
		return emailTemplate;
	}

}
