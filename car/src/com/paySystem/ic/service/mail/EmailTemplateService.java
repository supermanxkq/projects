/**  
* @Title: EmailTemplateService.java
* @Package: com.paySystem.ic.service.mail
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-23 下午02:08:22
* @Version: V1.0  
*/
package com.paySystem.ic.service.mail;

import com.paySystem.ic.bean.mail.EmailTemplate;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.mail.EmailTemplateDTO;

/**
 * @ProjectName:omall2014911
 * @ClassName:EmailTemplateService
 * @Description:邮件常用模板service接口
 * @date: 2014-9-23
 * @author: 孙晓磊
 * @version: V1.0
 */
public interface EmailTemplateService extends DAO<EmailTemplate>{
	public static final String EMAILTEMPLATESERVICE="emailTemplateService";
	
	/**
	*@Title:getEmailTemplateDTObyid
	*@Description:通过id得到模板信息
	*@Params:@param id
	*@Params:@return
	*@Return:EmailTemplateDTO
	*@author:孙晓磊
	*@Date:2014-9-23下午02:45:26
	*/
	public EmailTemplateDTO getEmailTemplateDTObyid(Integer id);
	
	/**
	*@Title:updateemail
	*@Description:更新模板信息
	*@Params:@param emaildto
	*@Return:void
	*@author:孙晓磊
	*@Date:2014-9-24下午12:00:07
	*/
	public void updateemail(EmailTemplateDTO emaildto);
	

}
