/**  
* @Title: EmailTemplateDAO.java
* @Package: com.paySystem.ic.dao.mail
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-23 下午12:01:21
* @Version: V1.0  
*/
package com.paySystem.ic.dao.mail;
import com.paySystem.ic.bean.mail.EmailTemplate;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.mail.EmailTemplateDTO;

/**
 * @ProjectName:omall2014911
 * @ClassName:EmailTemplateDAO
 * @Description:邮件模板dao接口
 * @date: 2014-9-23
 * @author: 孙晓磊
 * @version: V1.0
 */
public interface EmailTemplateDAO extends DAO<EmailTemplate>{
	public static final String EMAILTEMPLATE="emailTemplateDAO";
	/**
	*@Title:findEmailTemplate
	*@Description:通过id得到模板信息
	*@Params:@param eId
	*@Params:@return
	*@Return:EmailTemplateDTO
	*@author:孙晓磊
	*@Date:2014-9-23下午02:35:12
	*/
	public EmailTemplateDTO findEmailTemplate(Integer eId);
	
	/**
	*@Title:updateemail
	*@Description:更新模板信息
	*@Params:@param emaildto
	*@Return:void
	*@author:孙晓磊
	*@Date:2014-9-24上午11:40:42
	*/
	public void updateemail(EmailTemplateDTO emaildto);
	
	

}
