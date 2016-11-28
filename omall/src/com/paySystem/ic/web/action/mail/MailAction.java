package com.paySystem.ic.web.action.mail;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.bean.member.Members;
import com.paySystem.ic.service.mail.MailContentService;
import com.paySystem.ic.service.mail.MailRecordService;
import com.paySystem.ic.service.member.MemberService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.MailUtils;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.mail.MailContentDTO;
import com.paySystem.ic.web.dto.mail.MailRecordDTO;
import com.paySystem.ic.web.dto.member.MemberDTO;
import com.paySystem.ic.web.dto.member.MembersDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/***
 * 
 * @ClassName:MailAction
 * @TODO:会员邮件发送
 * @date: 2014-3-17下午12:05:29
 * @author: 孟凡岭
 * @version: V1.0
 */
@Controller("/mail/mail")
@Scope("prototype")
public class MailAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	/** 会员邮箱 **/
	private String emailStr;
	/** 邮件主题 **/
	private String emailSubject;
	/** 邮件内容 **/
	private String emailContent;
	private MembersDTO memberDTO = new MembersDTO();
	@Resource
	private MemberService memberService;
	@Resource
	private MailRecordService mailRecordService;
	@Resource
	private MailContentService mailContentService;

	/**
	 * 
	 *@Title:jsonPageList
	 *@TODO:查询会员数据
	 *@data:2014-3-17
	 *@param:@return
	 *@param:@throws Exception
	 *@return:String
	 *@author:孟凡岭
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		/*** 判断排序参数是否有值 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			/** 如果页面没有要求排序方式，则设置创建时间排序 **/
			orderby.put("createTime", "desc");
		}
		/*** 返回结果 */
		QueryResult<MembersDTO> queryResult = memberService.queryResult(
				(memberDTO.getPage() - 1) * pageNum, pageNum, memberDTO,
				orderby);
		List<MembersDTO> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			MembersDTO memberDTO = list.get(i);
			List<String> strings = new ArrayList<String>();
			/** 为每一行添加一个复选框 **/
			strings.add("<input type='checkbox' name='email' value='"
					+ memberDTO.getEmail() + "' onclick='check();' /> ");
			strings.add(String.valueOf(i + 1));
			strings.add(memberDTO.getMemId().toString());
			if (memberDTO.getMemRealName() != null) {
				strings.add(memberDTO.getMemRealName());
			} else {
				strings.add("");
			}
			strings.add(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",
					memberDTO.getCreateTime()).substring(0, 10));
			strings.add(Utils.getOptionsIntegerName(OptionsValue.SEX_STATUS,
					memberDTO.getSex()));

			if (memberDTO.getStatus() == 1) {
				strings.add("启用");
			} else if (memberDTO.getStatus() == 0) {
				strings.add("禁用");
			} else {
				strings.add("删除");
			}
			lists.add(strings);
		}
		PageView pageView = new PageView(memberDTO.getPage(), queryResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * 
	 *@Title:getDTO
	 *@TODO:返回一个MemberDTO对象
	 *@data:2014-3-17
	 *@param:@param member
	 *@param:@return
	 *@return:MemberDTO
	 *@author:孟凡岭
	 *@thorws:
	 */
	public MembersDTO getDTO(Members member) {
		MembersDTO memberDTO = new MembersDTO();
		memberDTO.setAreaId(member.getAreaId());
		memberDTO.setCreateTime(member.getCreateTime());
		memberDTO.setEmail(member.getEmail());
		memberDTO.setGroupId(member.getGroupId());
		memberDTO.setMemId(member.getMemId());
		memberDTO.setMemRealName(member.getRealName());
		memberDTO.setName(member.getRealName());
		//memberDTO.setOrganId(member.getOrgans().getOrganId());
		memberDTO.setPersonId(member.getPersonId());
		memberDTO.setPerType(member.getPerType());
		memberDTO.setSex(member.getSex());
		memberDTO.setStatus(member.getStatus());
		memberDTO.setTeleNo(member.getTeleNo());
		memberDTO.setUpdateTime(member.getUpdateTime());
		return memberDTO;
	}

	/***
	 * 
	 *@Title:sendMail
	 *@TODO:邮件发送，对接收的数据进行分割，一个邮箱进行单发，若为多个邮箱则进行群发。
	 *@data:2014-3-17
	 *@param:
	 *@return:void
	 *@author:孟凡岭
	 *@thorws:Exception
	 */
	public void sendMail() throws Exception {
		String[] emailArr = emailStr.split(";");

		/** 如果数组长度大于1代表群发，等于1则代表只给一个会员发送 **/
		if (emailArr.length == 1) {
			MailUtils.sendMail(emailSubject, emailContent, emailArr[0]);
			mailContentService.saveDTO(getContentDTO(),
					getMailRecordDTO(emailArr[0]));
		} else {
			MailUtils.sendMail(emailSubject, emailContent, emailArr);
			mailContentService.saveListDTO(getContentDTO(),
					getRecordDTOList(emailArr));
		}
	}

	/**
	 * 
	 *@Title:getContentDTO
	 *@TODO:获取邮件内容记录DTO
	 *@data:2014-3-20
	 *@param:@return
	 *@return:MailContentDTO
	 *@author:孟凡岭
	 *@thorws:
	 */
	public MailContentDTO getContentDTO() {
		UserSession us = Utils.getUserSession();
		MailContentDTO content = new MailContentDTO();
		content.setCreateTime(mailContentService.getSysTime());
		content.setContent(emailContent);
		content.setOrgId(us.getOrganId());
		content.setSubject(emailSubject);
		return content;
	}

	/**
	 * 
	 *@Title:getMailRecordDTO
	 *@TODO:得到一个单独的记录DTO
	 *@data:2014-3-19
	 *@param:@param toMail
	 *@param:@return
	 *@param:@throws Exception
	 *@return:MailRecordDTO
	 *@author:孟凡岭
	 *@thorws:
	 */
	public MailRecordDTO getMailRecordDTO(String toMail) throws Exception {
		UserSession us = Utils.getUserSession();
		MailRecordDTO mailRecordDTO = new MailRecordDTO();
		InputStream is = new BufferedInputStream(MailAction.class
				.getClassLoader().getResourceAsStream("mail.properties"));
		Properties p = new Properties();
		p.load(is);
		mailRecordDTO.setFromMail(p.getProperty("userName"));
		mailRecordDTO.setSendDate(DateTimeTool.dateFormat(
				"yyyy-MM-dd HH:mm:ss", mailContentService.getSysTime()));
		mailRecordDTO.setToMail(toMail);
		mailRecordDTO.setType(0);
		mailRecordDTO.setOrgId(us.getOrganId());
		return mailRecordDTO;
	}

	/**
	 * 
	 *@Title:getRecordDTOList
	 *@TODO:获取MailRecordDTO的list集合
	 *@data:2014-3-20
	 *@param:@param emailArr
	 *@param:@return
	 *@param:@throws Exception
	 *@return:List<MailRecordDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<MailRecordDTO> getRecordDTOList(String[] emailArr)
			throws Exception {
		UserSession us = Utils.getUserSession();
		List<MailRecordDTO> list = new ArrayList<MailRecordDTO>();
		InputStream is = new BufferedInputStream(MailAction.class
				.getClassLoader().getResourceAsStream("mail.properties"));
		Properties p = new Properties();
		p.load(is);
		String fromMail = p.getProperty("userName");
		if (emailArr != null) {
			for (int i = 0; i < emailArr.length; i++) {
				MailRecordDTO mailRecordDTO = new MailRecordDTO();
				mailRecordDTO.setFromMail(fromMail);
				mailRecordDTO
						.setSendDate(DateTimeTool.dateFormat(
								"yyyy-MM-dd HH:mm:ss", mailContentService
										.getSysTime()));
				mailRecordDTO.setToMail(emailArr[i]);
				mailRecordDTO.setType(0);
				mailRecordDTO.setOrgId(us.getOrganId());
				list.add(mailRecordDTO);
			}
		}

		return list;
	}

	public String list() {
		this.getRequest().setAttribute("status", OptionsValue.STATE_STATUS);
		UserSession us = Utils.getUserSession();
		if (us.getUserLevel() == 2) {
			return "no";
		}
		return "list";
	}

	public MembersDTO getMemberDTO() {
		return memberDTO;
	}

	public void setMemberDTO(MembersDTO memberDTO) {
		this.memberDTO = memberDTO;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public MailRecordService getMailRecordService() {
		return mailRecordService;
	}

	public void setMailRecordService(MailRecordService mailRecordService) {
		this.mailRecordService = mailRecordService;
	}

	public MailContentService getMailContentService() {
		return mailContentService;
	}

	public void setMailContentService(MailContentService mailContentService) {
		this.mailContentService = mailContentService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEmailStr() {
		return emailStr;
	}

	public void setEmailStr(String emailStr) {
		this.emailStr = emailStr;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

}
