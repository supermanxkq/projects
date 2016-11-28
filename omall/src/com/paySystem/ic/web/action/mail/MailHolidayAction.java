package com.paySystem.ic.web.action.mail;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
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
import com.paySystem.ic.web.dto.mail.MailContentDTO;
import com.paySystem.ic.web.dto.mail.MailHolidayDTO;
import com.paySystem.ic.web.dto.mail.MailRecordDTO;
import com.paySystem.ic.web.dto.member.MemberDTO;
import com.paySystem.ic.web.dto.member.MembersDTO;
import com.paySystem.ic.web.dto.system.UserSession;

@Controller("/mail/holidayMail")
@Scope("prototype")
public class MailHolidayAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private MailHolidayDTO mailHolidayDTO = new MailHolidayDTO();
	@Resource
	private MailHolidayService mailHolidayService;
	@Resource
	private MemberService memberService;
	@Resource
	private MailContentService mailContentService;

	public String list() {
		this.getRequest().setAttribute("status", OptionsValue.STATE_STATUS);
		UserSession us = Utils.getUserSession();
		if (us.getUserLevel() == 2) {
			return "no";
		}
		return "list";
	}

	/**
	 * 
	 * @Title:jsonPageList
	 * @TODO:查询数据
	 * @date:2014-3-21
	 * @param:@return
	 * @param:@throws Exception
	 * @return:String
	 * @author:孟凡岭
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
			orderby.put("holidayDate", "asc");
		}
		QueryResult<MailHolidayDTO> queryResult = mailHolidayService
				.queryResult((mailHolidayDTO.getPage() - 1) * pageNum, pageNum,
						mailHolidayDTO, orderby);
		List<MailHolidayDTO> list = queryResult.getResultlist();
		List<List<String>> listt = new ArrayList<List<String>>();

		for (int i = 0; i < list.size(); i++) {
			MailHolidayDTO m = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			strings.add(String.valueOf(m.getHolidayName()));
			strings.add(m.getHolidayDate());
			strings.add(m.getSubject());
			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.HOLIDAY_STATUS, m.getStatus()));
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
		PageView pageView = new PageView(mailHolidayDTO.getPage(), queryResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(listt, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * 
	 * @Title:add
	 * @TODO:添加节日信息
	 * @date:2014-3-21
	 * @param:
	 * @return:void
	 * @author:孟凡岭
	 */
	public void add() throws Exception {
		mailHolidayService.saveDTO(mailHolidayDTO);
	}

	/***
	 * 
	 * @Title:editData
	 * @TODO:查看和修改数据
	 * @date:2014-3-22
	 * @param:@return
	 * @return:String
	 * @author:孟凡岭
	 */
	public String editData() {
		MailHolidayDTO m = mailHolidayService.findById(mailHolidayDTO.getId());
		return Utils.printInfo(m);
	}

	/***
	 * 
	 * @Title:update
	 * @TODO:更新数据
	 * @date:2014-3-22
	 * @param:@throws Exception
	 * @return:void
	 * @author:孟凡岭
	 */
	public void update() throws Exception {
		mailHolidayService.updateByDTO(mailHolidayDTO);
	}

	/**
	 * 
	 * @Title:delete
	 * @TODO:删除数据
	 * @date:2014-3-22
	 * @param:
	 * @return:void
	 * @author:孟凡岭
	 */
	public void delete() {
		mailHolidayService.delete(mailHolidayDTO.getId());
	}

	/**
	 * 
	 *@Title:sendMail
	 *@TODO:发送邮件
	 *@data:2014-3-25
	 *@param:@throws Exception
	 *@return:void
	 *@author:孟凡岭
	 *@thorws:
	 */
	public void sendMail() throws Exception {
		List<MailHolidayDTO> list = mailHolidayService.queryAll();
		List<MembersDTO> memList = new ArrayList<MembersDTO>();
		/** 会员邮件数组 **/
		String[] groupMail = null;
		/** 邮件内容 **/
		String content = null;
		/** 邮件主题 **/
		String subject = null;
		/** 格式化当前系统时间 **/
		String sysDate = DateTimeTool.dateFormat("MM-dd", new Date());

		boolean flag;
		for (int i = 0; i < list.size(); i++) {
			MailHolidayDTO m = new MailHolidayDTO();
			m = list.get(i);
			/** 调用方法判断节日日期是否和系统时间相等 **/
			flag = judgeDate(sysDate, m.getHolidayDate());
			/** 如果相等则发送邮件 **/
			if (flag) {
				memList = memberService.findByOrgId(m.getOrgId());
				content = m.getContent();
				subject = m.getSubject();
				groupMail = getMail(memList);
				if(groupMail!=null){
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
				/** 将节日状态改为已过 **/
				mailHolidayService.updateStatus(m);
			}
			
		}
	}

	/**
	 * 
	 *@Title:judgeDate
	 *@TODO:判断节日日期是否和系统时间相等,相等则返回一个true
	 *@data:2014-3-24
	 *@param:@param sysDate
	 *@param:@param holidayDate
	 *@param:@return
	 *@return:boolean
	 *@author:孟凡岭
	 *@thorws:
	 */
	public boolean judgeDate(String sysDate, String holidayDate) {
		// TODO Auto-generated method stub
		int sysMonth = Integer.parseInt(sysDate.substring(0, 2).trim());
		int sysDay = Integer.parseInt(sysDate.substring(3, 5).trim());
		int holidayMonth = Integer.parseInt(holidayDate.substring(0, 2).trim());
		int holidayDay = Integer.parseInt(holidayDate.substring(3, 5).trim());
		if (sysMonth == holidayMonth && sysDay == holidayDay) {
			return true;
		}
		return false;
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
		if (groupMail != null) {
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
		m.setType(2);
		m.setToMail(toMail);
		m.setOrgId(orgId);
		return m;
	}

	/***
	 * 
	 *@Title:getContentDTO
	 *@TODO:获取邮件内容记录DTO
	 *@data:2014-3-24
	 *@param:@return
	 *@return:MailContentDTO
	 *@author:孟凡岭
	 * @param m
	 *@thorws:
	 */
	public MailContentDTO getContentDTO(MailHolidayDTO m) {
		MailContentDTO dto = new MailContentDTO();
		dto.setContent(m.getContent());
		dto.setCreateTime(mailContentService.getSysTime());
		dto.setOrgId(m.getOrgId());
		dto.setSubject(m.getSubject());
		return dto;
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
	public String[] getMail(List<MembersDTO> memList) {
		List<String> list = new ArrayList<String>();
		if(memList!=null&&memList.size()>0){
			for (int i = 0; i < memList.size(); i++) {
				list.add(memList.get(i).getEmail());
			}
			return removeEqual(list.toArray());
		}
		return null;
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
			System.out.println(array[i]);
		}
		for (int i = 0; i < array.length; i++) {
			if (!list.contains(array[i])) {
				list.add(array[i].toString());
			}
		}
		String[] array2 = list.toArray(new String[list.size()]);
		return array2;
	}

	/**
	 * 
	 *@Title:getMailArr
	 *@TODO:获取邮箱数组
	 *@data:2014-3-25
	 *@param:@param list
	 *@param:@return
	 *@return:String[]
	 *@author:孟凡岭
	 *@thorws:
	 */
	public String[] getMailArr(List<Member> list) {
		String[] groupMail = null;
		if (list.size() == 1) {
			groupMail = new String[1];
		} else if (list.size() > 1) {
			groupMail = new String[list.size()];
		} else {
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			groupMail[i] = list.get(i).getEmail();
		}
		return groupMail;
	}

	public MailHolidayDTO getMailHolidayDTO() {
		return mailHolidayDTO;
	}

	public void setMailHolidayDTO(MailHolidayDTO mailHolidayDTO) {
		this.mailHolidayDTO = mailHolidayDTO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
