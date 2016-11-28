package com.paySystem.ic.web.action.mail;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.MemGroups;
import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.bean.member.MemberGroupsDetail;
import com.paySystem.ic.service.mail.MailContentService;
import com.paySystem.ic.service.member.MemGroupsService;
import com.paySystem.ic.service.member.MemberGroupsDetailService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.MailUtils;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.mail.MailContentDTO;
import com.paySystem.ic.web.dto.mail.MailRecordDTO;
import com.paySystem.ic.web.dto.member.MemGroupsDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/***
 * 
 * @ClassName:GroupMailAction
 * @TODO:群组邮件发送
 * @date: 2014-3-19上午09:40:27
 * @author: 孟凡岭
 * @version: V1.0
 */
@Controller("/mail/groupMail")
@Scope("prototype")
public class GroupMailAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private MemGroupsDTO memGroupsDTO = new MemGroupsDTO();
	/** 会员邮箱 **/
	private String groupId;
	/** 邮件主题 **/
	private String emailSubject;
	/** 邮件内容 **/
	private String emailContent;
	@Resource
	private MemGroupsService memGroupsService;
	@Resource
	private MailContentService mailContentService;
	@Resource
	private MemberGroupsDetailService memberGroupsDetailService;

	public String list() {
		this.getRequest().setAttribute("status", OptionsValue.STATE_STATUS);
		UserSession us = Utils.getUserSession();
		if (us.getUserLevel() == 2) {
			return "no";
		}
		return "list";
	}

	public String jsonPageList() throws Exception {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		/*** 判断排序参数是否有值 */
		if (StringUtils.isNotBlank(this.getOrderProperty()) && StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			/** 如果页面没有要求排序方式，则设置创建时间排序 **/
			orderby.put("createTime", "desc");
		}
		QueryResult<MemGroups> queryResult = memGroupsService.queryResult((memGroupsDTO.getPage() - 1) * pageNum, pageNum, memGroupsDTO, orderby);
		List<MemGroups> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			MemGroups m = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add("<input type='checkbox' name='email' title='" + m.getGroupName() + "' value='" + m.getGroupId() + "' onclick='check();' /> ");
			strings.add(String.valueOf(i + 1));
			strings.add(m.getGroupId().toString());
			strings.add(m.getGroupName());
			strings.add(m.getUserName());
			strings.add(DateTimeTool.dateFormat("yyyy-MM-dd", m.getCreateTime()));
			strings.add(Utils.getString(memberGroupsDetailService.findByGroupId(m.getGroupId()).size()));
			strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS, m.getStatus()));
			lists.add(strings);

		}
		PageView pageView = new PageView(memGroupsDTO.getPage(), queryResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/***
	 * 
	 *@Title:sendMail
	 *@TODO:发送邮件
	 *@data:2014-3-20
	 *@param:
	 *@return:void
	 *@author:孟凡岭
	 *@thorws:
	 */
	public void sendMail() throws Exception {
		/** 群组ID数组 **/
		String[] emailArr = groupId.split(";");
		/** 会员list集合 **/
		List<Member> list = null;
		/** 存放会员list **/
		List<List<Member>> listt = new ArrayList<List<Member>>();
		/** 邮件地址数组 **/
		String[] groupMail = null;
		List<MemberGroupsDetail> groupList = new ArrayList<MemberGroupsDetail>();
		if (emailArr != null) {
			if (emailArr.length == 1) {
				groupList = memberGroupsDetailService.findByGroupId(Integer.parseInt(emailArr[0]));
				if (groupList != null && groupList.size() > 0) {
					list = new ArrayList<Member>();
					for (int i = 0; i < groupList.size(); i++) {
						list.add(groupList.get(i).getMember());
					}
				}
				/** 通过会员list集合得到邮件地址数组 **/
				groupMail = getMailArr(list);
				MailUtils.sendMail(emailSubject, emailContent, groupMail);
				mailContentService.saveDTO(getContentDTO(), getMailRecordDTO());
			} else {
				for (int i = 0; i < emailArr.length; i++) {
					groupList = memberGroupsDetailService.findByGroupId(Integer.parseInt(emailArr[i]));
					if (groupList != null && groupList.size() > 0) {
						list = new ArrayList<Member>();
						for (int j = 0; j < groupList.size(); j++) {
							list.add(groupList.get(j).getMember());
						}
						listt.add(list);
					}
				}
				if (listt != null && listt.size() > 0) {
					groupMail = getMail(listt);
				}
				mailContentService.saveListDTO(getContentDTO(), getRecordDTOList(groupMail));
				MailUtils.sendMail(emailSubject, emailContent, groupMail);
			}
		}

	}

	/***
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
	public List<MailRecordDTO> getRecordDTOList(String[] emailArr) throws Exception {
		UserSession us = Utils.getUserSession();
		List<MailRecordDTO> list = new ArrayList<MailRecordDTO>();
		InputStream is = new BufferedInputStream(MailAction.class.getClassLoader().getResourceAsStream("mail.properties"));
		Properties p = new Properties();
		p.load(is);
		String fromMail = p.getProperty("userName");
		if (emailArr != null) {
			for (int i = 0; i < emailArr.length; i++) {
				MailRecordDTO mailRecordDTO = new MailRecordDTO();
				mailRecordDTO.setFromMail(fromMail);
				mailRecordDTO.setSendDate(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", mailContentService.getSysTime()));
				mailRecordDTO.setToMail(emailArr[i]);
				mailRecordDTO.setType(1);
				mailRecordDTO.setOrgId(us.getOrganId());
				list.add(mailRecordDTO);
			}
		}

		return list;
	}

	/***
	 * 
	 *@Title:getMailRecordDTO
	 *@TODO:得到一个单独的记录DTO
	 *@data:2014-3-20
	 *@param:@return
	 *@param:@throws Exception
	 *@return:MailRecordDTO
	 *@author:孟凡岭
	 *@thorws:
	 */
	public MailRecordDTO getMailRecordDTO() throws Exception {
		UserSession us = Utils.getUserSession();
		MailRecordDTO mailRecordDTO = new MailRecordDTO();
		InputStream is = new BufferedInputStream(MailAction.class.getClassLoader().getResourceAsStream("mail.properties"));
		Properties p = new Properties();
		p.load(is);
		mailRecordDTO.setFromMail(p.getProperty("userName"));
		mailRecordDTO.setSendDate(DateTimeTool.dateFormat("yyyy-MM-dd", mailContentService.getSysTime()));
		mailRecordDTO.setToMail("");
		mailRecordDTO.setType(1);
		mailRecordDTO.setOrgId(us.getOrganId());
		return mailRecordDTO;
	}

	/***
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

	/***
	 * 
	 *@Title:getMail
	 *@TODO:TODO
	 *@data:2014-3-20
	 *@param:@param listt
	 *@param:@return
	 *@return:String[]
	 *@author:孟凡岭
	 *@thorws:
	 */
	public String[] getMail(List<List<Member>> listt) {
		String[] groupMail = null;
		List<String> list = new ArrayList<String>();
		if (listt != null && listt.size() > 0) {
			/** 第一次遍历list集合 **/
			for (int i = 0; i < listt.size(); i++) {
				groupMail = getMailArr(listt.get(i));
				/** 第二次遍历数组 **/
				if (groupMail != null) {
					for (int j = 0; j < groupMail.length; j++) {
						list.add(groupMail[j]);
					}
				}
			}
		}

		return removeEqual(list.toArray());
	}

	/**
	 * 
	 *@Title:removeEqual
	 *@TODO:去掉数组中重复的字符串
	 *@data:2014-3-20
	 *@param:@param array
	 *@param:@return
	 *@return:String[]
	 *@author:孟凡岭
	 *@thorws:
	 */
	public String[] removeEqual(Object[] array) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			if (!list.contains(array[i])) {
				list.add(array[i].toString());
			}
		}
		String[] array2 = list.toArray(new String[list.size()]);
		return array2;
	}

	/***
	 * 
	 *@Title:getMailArr
	 *@TODO:从list中取中邮箱，转成数组
	 *@data:2014-3-20
	 *@param:@param list
	 *@param:@return
	 *@return:String[]
	 *@author:孟凡岭
	 *@thorws:
	 */
	public String[] getMailArr(List<Member> list) {
		String[] groupMail = null;
		if (list != null) {
			if (list.size() == 1) {
				groupMail = new String[]{list.get(0).getEmail()};
			} else if (list.size() > 1) {
				groupMail = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					groupMail[i] = list.get(i).getEmail();
				}
			}
		} else {
			return null;
		}
		
		return groupMail;
	}

	public MemGroupsDTO getMemGroupsDTO() {
		return memGroupsDTO;
	}

	public void setMemGroupsDTO(MemGroupsDTO memGroupsDTO) {
		this.memGroupsDTO = memGroupsDTO;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
}
