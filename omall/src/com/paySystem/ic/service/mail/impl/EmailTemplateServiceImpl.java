/**  
* @Title: EmailTemplate.java
* @Package: com.paySystem.ic.service.mail.impl
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-23 下午02:37:20
* @Version: V1.0  
*/
package com.paySystem.ic.service.mail.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.mail.EmailTemplate;
import com.paySystem.ic.dao.mail.EmailTemplateDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.mail.EmailTemplateService;
import com.paySystem.ic.web.dto.mail.EmailTemplateDTO;

/**
 * @ProjectName:omall2014911
 * @ClassName:EmailTemplate
 * @Description:邮件模板service实现类
 * @date: 2014-9-23
 * @author: 孙晓磊
 * @version: V1.0
 */
@Service(EmailTemplateService.EMAILTEMPLATESERVICE)
public class EmailTemplateServiceImpl extends DaoSupport<EmailTemplate> implements EmailTemplateService{

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.mail.EmailTemplateService#getEmailTemplateDTObyid(java.lang.Integer)
	 *@MethodName:getEmailTemplateDTObyid
	 *@Description:通过id得到模板信息
	 *@param id
	 *@return
	 *@Author:孙晓磊
	 *@Date:2014-9-23下午02:39:08
	 */
	@Resource EmailTemplateDAO emailTemplateDAO;
	public EmailTemplateDTO getEmailTemplateDTObyid(Integer id) {
	
		return emailTemplateDAO.findEmailTemplate(id);
	}
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.mail.EmailTemplateService#update(com.paySystem.ic.web.dto.mail.EmailTemplateDTO)
	 *@MethodName:update
	 *@Description:更新模板信息
	 *@param emaildto
	 *@Author:孙晓磊
	 *@Date:2014-9-24上午11:34:46
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void updateemail(EmailTemplateDTO emaildto) {
		emailTemplateDAO.updateemail(emaildto);
	
	}
	
}
