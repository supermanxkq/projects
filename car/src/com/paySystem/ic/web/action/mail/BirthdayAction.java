package com.paySystem.ic.web.action.mail;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.mail.MailBirthdayService;
import com.paySystem.ic.service.mail.MailContentService;
import com.paySystem.ic.service.mail.MailHolidayService;
import com.paySystem.ic.service.member.MemberService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.MailUtils;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.mail.MailBirthdayDTO;
import com.paySystem.ic.web.dto.mail.MailContentDTO;
import com.paySystem.ic.web.dto.mail.MailRecordDTO;
import com.paySystem.ic.web.dto.member.MemberDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/***
 * 
 * @ClassName:BirthdayAction
 * @TODO:会员生日定时发送邮件
 * @date: 2014-3-24下午05:09:28
 * @author: 孟凡岭
 * @version: V1.0
 */
@Controller("/mail/birthday")
@Scope("prototype")
public class BirthdayAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private MailBirthdayDTO birthdayDTO = new MailBirthdayDTO();
	@Resource
	private MemberService memberService;
	@Resource
	private MailHolidayService mailHolidayService;
	@Resource
	private MailContentService mailContentService;
	@Resource
	private MailBirthdayService mailBirthdayService;

	public String list() {
		this.getRequest().setAttribute("mailStatus",
				OptionsValue.MAILBIRTHDAY_STATUS);
		UserSession us = Utils.getUserSession();
		if (us.getUserLevel() == 2) {
			return "no";
		}
		return "list";
	}

	/***
	 * 
	 * @Title:jsonPageList
	 * @TODO:分页查询
	 * @date:2014-3-24
	 * @param:@return
	 * @return:String
	 * @author:孟凡岭
	 */
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
		QueryResult<MailBirthdayDTO> queryResult = mailBirthdayService
				.queryResult((birthdayDTO.getPage() - 1) * pageNum, pageNum,
						birthdayDTO, orderby);
		List<MailBirthdayDTO> list = queryResult.getResultlist();
		List<List<String>> listt = new ArrayList<List<String>>();
		MailBirthdayDTO m = null;
		/** 循环遍历List集合 **/
		for (int i = 0; i < list.size(); i++) {
			m = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			strings.add(m.getSubject());
			/** 判断内容长度，或是内容太长则只显示部分信息，否则全部显示 **/
			if (m.getContent().length() > 7) {
				strings.add(m.getContent().substring(0, 6) + "......");
			} else {
				strings.add(m.getContent());
			}
			strings.add(m.getCreateTime());
			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.MAILBIRTHDAY_STATUS, m.getStatus()));
			String operation = "";
			operation += "<a href=javascript:void(0) onclick=editData('"
					+ m.getId() + "') title='编辑'>" + Globals.IMG_EDIT
					+ "</a> &nbsp;";
			operation += "<a href=javascript:void(0) onclick=deleteData('"
					+ m.getId() + "') title='删除'>" + Globals.IMG_DELETE
					+ "</a> &nbsp;";
			strings.add(operation);
			listt.add(strings);
		}
		PageView pageView = new PageView(birthdayDTO.getPage(), queryResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(listt, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/***
	 * 
	 * @Title:add
	 * @TODO:添加数据
	 * @date:2014-3-24
	 * @param:
	 * @return:void
	 * @author:孟凡岭
	 */
	public void add() throws Exception {
		mailBirthdayService.saveDTO(birthdayDTO);
	}

	/***
	 * 
	 * @Title:update
	 * @TODO:更新数据
	 * @date:2014-3-24
	 * @param:
	 * @return:void
	 * @author:孟凡岭
	 */
	public void update() throws Exception {
		mailBirthdayService.updateByDTO(birthdayDTO);
	}

	/**
	 * 
	 * @Title:editData
	 * @TODO:查看和修改数据
	 * @date:2014-3-24
	 * @param:@return
	 * @return:String
	 * @author:孟凡岭
	 */
	public String editData() {
		MailBirthdayDTO m = mailBirthdayService.findById(birthdayDTO.getId());
		return Utils.printInfo(m);
	}

	/***
	 * 
	 *@Title:sendMail
	 *@TODO:查询会员数据
	 *@data:2014-3-24
	 *@param:
	 *@return:void
	 *@author:孟凡岭
	 *@thorws:
	 */
	public void sendMail() throws Exception {
		List<MailBirthdayDTO> list = mailBirthdayService.queryAll();
		List<MemberDTO> memList = new ArrayList<MemberDTO>();
		/** 会员邮件数组 **/
		String[] groupMail = null;
		/** 邮件内容 **/
		String content = null;
		/** 邮件主题 **/
		String subject = null;
		/** 判断list是否为null并且size大于0 **/
		if (list != null && list.size() > 0) {
			/** 遍历List集合 **/
			for (int i = 0; i < list.size(); i++) {
				MailBirthdayDTO m = list.get(i);
				content = m.getContent();
				subject = m.getSubject();
				memList = memberService.findByOrgId(m.getOrgId());
				groupMail = getMail(memList);
				/** 如果数组长度为1，则进行单用户发送，若大于1则进行群发 **/
				if (groupMail.length == 1) {
					MailUtils.sendMail(subject, content, groupMail[0]);
					mailContentService.saveDTO(getContentDTO(m),
							getMailRecordDTO(groupMail[0], m.getOrgId()));
				} else if (groupMail.length > 1) {
					MailUtils.sendMail(subject, content, groupMail);
					mailContentService.saveListDTO(getContentDTO(m),
							getRecordDTOList(groupMail, m.getOrgId()));
				}
			}
		}
	}

	/***
	 * 
	 * @Title:getContentDTO
	 * @TODO:获取邮件内容记录DTO
	 * @date:2014-3-24
	 * @param:@param m
	 * @param:@return
	 * @return:MailContentDTO
	 * @author:孟凡岭
	 */
	public MailContentDTO getContentDTO(MailBirthdayDTO m) {
		// TODO Auto-generated method stub
		MailContentDTO dto = new MailContentDTO();
		dto.setContent(m.getContent());
		dto.setCreateTime(mailContentService.getSysTime());
		dto.setOrgId(Utils.getUserSession().getOrganId());
		dto.setSubject(m.getSubject());
		return dto;
	}

	/***
	 * 
	 *@Title:getRecordDTOList
	 *@TODO:得到MailRecordDTO集合
	 *@data:2014-3-24
	 *@param:@param groupMail
	 *@param:@return
	 *@return:List<MailRecordDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<MailRecordDTO> getRecordDTOList(String[] groupMail, String orgId)
			throws Exception {
		// TODO Auto-generated method stub
		List<MailRecordDTO> list = new ArrayList<MailRecordDTO>();
		if(groupMail!=null){
			for (int i = 0; i < groupMail.length; i++) {
				MailRecordDTO m = new MailRecordDTO();
				m = getMailRecordDTO(groupMail[i], orgId);
				list.add(m);
			}
		}
		
		return list;
	}

	/***
	 * 
	 *@Title:getMailRecordDTO
	 *@TODO:得到一个单独的记录DTO
	 *@data:2014-3-24
	 *@param:@return
	 *@return:MailRecordDTO
	 *@author:孟凡岭
	 * @param mailHolidayDTO
	 *@thorws:
	 */
	public MailRecordDTO getMailRecordDTO(String toMail, String orgId)
			throws Exception {
		// TODO Auto-generated method stub
		InputStream is = new BufferedInputStream(MailAction.class
				.getClassLoader().getResourceAsStream("mail.properties"));
		Properties p = new Properties();
		p.load(is);
		MailRecordDTO m = new MailRecordDTO();
		m.setFromMail(p.getProperty("userName"));
		m.setSendDate(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", mailContentService.getSysTime()));
		m.setType(3);
		m.setToMail(toMail);
		m.setOrgId(orgId);
		return m;
	}

	/**
	 * 
	 *@Title:getMail
	 *@TODO:获取发送的邮箱
	 *@data:2014-3-24
	 *@param:@param memList
	 *@param:@return
	 *@return:String[]
	 *@author:孟凡岭
	 *@thorws:
	 */
	public String[] getMail(List<MemberDTO> memList) {
		List<String> list = new ArrayList<String>();
		MemberDTO m = null;
		String sysDate = DateTimeTool.dateFormat("MM-dd", new Date());
		String birthday = "";
		boolean flag = false;
		for (int i = 0; i < memList.size(); i++) {
			m = memList.get(i);
			birthday = m.getBirthday().substring(5);
			/** 判断当天是否为会员生日 **/
			flag = judgeDate(sysDate, birthday);
			/** 如果则加入邮箱信息 **/
			if (flag) {
				list.add(memList.get(i).getEmail());
			}
		}
		return removeEqual(list.toArray());
	}

	/***
	 * 
	 * @Title:judgeDate
	 * @TODO:判断当天是否为会员生日,如果是则返回true
	 * @date:2014-3-24
	 * @param:@param sysDate
	 * @param:@param holidayDate
	 * @param:@return
	 * @return:boolean
	 * @author:孟凡岭
	 */
	public boolean judgeDate(String sysDate, String birthday) {
		// TODO Auto-generated method stub
		int sysMonth = Integer.parseInt(sysDate.substring(0, 2).trim());
		int sysDay = Integer.parseInt(sysDate.substring(3, 5).trim());
		int holidayMonth = Integer.parseInt(birthday.substring(0, 2).trim());
		int holidayDay = Integer.parseInt(birthday.substring(3, 5).trim());
		if (sysMonth == holidayMonth && sysDay == holidayDay) {
			return true;
		}
		return false;
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
		/** 遍历数组 **/
		for (int i = 0; i < array.length; i++) {
			/** 如果list集合中不包含此字符串就进行添加操作 **/
			if (!list.contains(array[i])) {
				list.add(array[i].toString());
			}
		}
		/** list转为数组 **/
		String[] array2 = list.toArray(new String[list.size()]);
		return array2;
	}

	public MailBirthdayDTO getBirthdayDTO() {
		return birthdayDTO;
	}

	public void setBirthdayDTO(MailBirthdayDTO birthdayDTO) {
		this.birthdayDTO = birthdayDTO;
	}

}
