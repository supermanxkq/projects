package com.paySystem.ic.web.action.mail;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.mail.MailContent;
import com.paySystem.ic.service.mail.MailRecordService;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.mail.MailContentDTO;
import com.paySystem.ic.web.dto.mail.MailRecordDTO;
import com.paySystem.ic.web.dto.system.UserSession;

@Controller("/mail/record")
@Scope("prototype")
public class MailRecordAction extends BaseAction {
	/**
	 * @Fields serialVersionUID : TODO
	 * @author 孟凡岭
	 * @date 2014-3-20 下午06:13:46
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private MailRecordService mailRecordService;
	private MailRecordDTO mailRecordDTO = new MailRecordDTO();

	public String list() {
		UserSession us = Utils.getUserSession();
		if (us.getUserLevel() == 2) {
			return "no";
		}
		return "list";
	}

	public String jsonPageList() throws Exception {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		/*** 判断排序参数是否有值 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			/** 如果页面没有要求排序方式，则设置创建时间排序 **/
			orderby.put("sendDate", "desc");
		}
		QueryResult<MailRecordDTO> queryResult = mailRecordService.queryResult(
				(mailRecordDTO.getPage() - 1) * pageNum, pageNum,
				mailRecordDTO, orderby);
		List<MailRecordDTO> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				MailRecordDTO m = list.get(i);
				List<String> strings = new ArrayList<String>();
				strings.add(String.valueOf(i + 1));
				strings.add(m.getId().toString());
				strings.add(m.getFromMail());
				if (m.getToMail() != null && !m.getToMail().equals("null")) {
					strings.add(m.getToMail());
				} else {
					strings.add("未找到邮箱");
				}
				strings.add(m.getSendDate());
				strings.add(Utils.getOptionsIntegerName(OptionsValue.MAIL_TYPE,
						m.getType()));
				String operation = "";
				operation += "<a href=javascript:void(0) onclick=lookData('"
						+ m.getId() + "') title='查看'>" + Globals.IMG_VIEW
						+ "</a> &nbsp;";
				operation += "<a href=javascript:void(0) onclick=deleteData('"
						+ m.getId() + "') title='删除'>" + Globals.IMG_DELETE
						+ "</a> &nbsp;";
				strings.add(operation);
				lists.add(strings);
			}
		}

		PageView pageView = new PageView(mailRecordDTO.getPage(), queryResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	public String lookData() {
		MailContent m = mailRecordService.find(mailRecordDTO.getId())
				.getMailContent();
		MailContentDTO dto = getMailContentDTO(m);

		return Utils.printInfo(dto);
	}

	/***
	 * 
	 * @Title:getMailContentDTO
	 * @TODO:获取MailContentDTO 用于查看数据
	 * @date:2014-3-25
	 * @param:@param m
	 * @param:@return
	 * @return:MailContentDTO
	 * @author:孟凡岭
	 */
	private MailContentDTO getMailContentDTO(MailContent m) {
		// TODO Auto-generated method stub
		MailContentDTO dto = new MailContentDTO();
		dto.setContent(m.getContent());
		dto.setCreateTime(m.getCreateTime());
		dto.setId(m.getId());
		dto.setOrgId(m.getOrgId());
		dto.setSubject(m.getSubject());
		return dto;
	}

	/**
	 * 
	 * @Title:delete
	 * @TODO:删除数据
	 * @date:2014-3-25
	 * @param:
	 * @return:void
	 * @author:孟凡岭
	 */
	public void delete() {
		mailRecordService.delete(mailRecordDTO.getId());
	}

	public MailRecordService getMailRecordService() {
		return mailRecordService;
	}

	public void setMailRecordService(MailRecordService mailRecordService) {
		this.mailRecordService = mailRecordService;
	}

	public MailRecordDTO getMailRecordDTO() {
		return mailRecordDTO;
	}

	public void setMailRecordDTO(MailRecordDTO mailRecordDTO) {
		this.mailRecordDTO = mailRecordDTO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
