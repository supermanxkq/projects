package com.paySystem.ic.web.action.member;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.internalMessage.InternalMessage;
import com.paySystem.ic.service.internalMessage.InternalMessageService;
import com.paySystem.ic.service.member.MemberService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.internalMessage.InternalMessageDTO;
import com.paySystem.ic.web.dto.internalMessage.ReceiversDTO;
import com.paySystem.ic.web.dto.member.MembersDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ProjectName:omall
 * @ClassName:MemberAction
 * @Description:前台会员控制类
 * @date: 2014-11-27上午09:23:33
 * @author: 徐凯强
 * @version: V1.0
 */
@Controller("/member/member")
@Scope("prototype")
public class MemberAction extends BaseAction {
	/** 序列化 */
	private static final long serialVersionUID = -3051940652987671139L;
	/** 注入Service */
	/** 会员操作Service */
	@Resource
	MemberService memberService;
	/** 站内信Service */
	@Resource
	InternalMessageService internalMessageService;
	/** 实例化数据传输对象 */
	/** 站内信 */
	private InternalMessageDTO internalMessageDTO = new InternalMessageDTO();
	/** 收信人 */
	private ReceiversDTO receiversDTO = new ReceiversDTO();
	/** 会员 */
	private MembersDTO membersDTO = new MembersDTO();

	public InternalMessageDTO getInternalMessageDTO() {
		return internalMessageDTO;
	}

	public void setInternalMessageDTO(InternalMessageDTO internalMessageDTO) {
		this.internalMessageDTO = internalMessageDTO;
	}

	public ReceiversDTO getReceiversDTO() {
		return receiversDTO;
	}

	public void setReceiversDTO(ReceiversDTO receiversDTO) {
		this.receiversDTO = receiversDTO;
	}

	public MembersDTO getMembersDTO() {
		return membersDTO;
	}

	public void setMembersDTO(MembersDTO membersDTO) {
		this.membersDTO = membersDTO;
	}

	/**
	 *@Title:list
	 *@Description:跳转到列表页面中
	 *@Return:String返回字符串结果到struts.xml
	 *@author:徐凯强
	 *@Date:2014-11-27上午09:16:27
	 */
	public String list() {
		/** 平台权限控制 */
		UserSession us = Utils.getUserSession();
		if (us.getUserLevel() == 0) {
			/** 传递状态中的值 */
			this.getRequest().setAttribute("statusValues",
					OptionsValue.STATE_STATUSD);
			/** 设置状态的初始默认值 */
			membersDTO.setStatus(1);
			return "list";
		} else {
			return "intercepthtml";
		}
	}

	/**
	 *@Title:jsonPageList
	 *@Description:Ajax异步获取数据库中的数据
	 *@throws Exception抛出异常
	 *@Return:String返回字符串结果到struts.xml
	 *@author:徐凯强
	 *@Date:2014-11-27上午09:18:28
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {
		/** 排序参数 */
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		/** 判断排序参数是否有值 **/
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			/** 默认按照会员的参数进行排序 */
			orderby.put("memId", "desc");
		}
		/** 分页获取数据库中所有的前台注册的会员信息 */
		QueryResult<MembersDTO> queryResult = memberService.queryResult(
				(membersDTO.getPage() - 1) * pageNum, pageNum, membersDTO,
				orderby);
		/** 获取queryResult中的集合 */
		List<MembersDTO> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			/** 向页面赋值 */
			MembersDTO membersDTO = list.get(i);
			List<String> strings = new ArrayList<String>();
			/** checkBox选择按钮 */
			/** 如果会员没有禁用，可以选择，如果会员禁用，则不能选择 */
			if (!membersDTO.getStatus().equals(0)) {
				strings.add("<input type='checkbox' name='member' title='"
						+ membersDTO.getUserName() + "' value='"
						+ membersDTO.getMemId() + "'  /> ");
			} else {
				strings.add("");
			}
			/** 会员编号 */
			strings.add(Utils.getString(membersDTO.getMemId()));
			/** 会员真实姓名 */
			strings.add(Utils.getString(membersDTO.getRealName()));
			/** 用户名 */
			strings.add(Utils.getString(membersDTO.getUserName()));
			/** 会员性别 1：男 2：女 */
			strings.add(Utils.getOptionsIntegerName(OptionsValue.SEX_STATUS,
					membersDTO.getSex()));
			/** 会员证件类型 1： 身份证 2：军官证 3：护照 */
			strings.add(Utils.getOptionsIntegerName(OptionsValue.PERSONID_TYPE,
					membersDTO.getPerType()));
			/** 会员证件号码 */
			strings.add(Utils.getString(membersDTO.getPersonId()));
			/** 会员电话 */
			strings.add(Utils.getString(membersDTO.getTeleNo()));
			/** 会员电子邮箱 */
			strings.add(Utils.getString(membersDTO.getEmail()));
			/** 会员状态 1：正常 0;禁用 9：删除 */
			strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS,
					membersDTO.getStatus()));
			/** 会员信息创建时间 */
			strings
					.add(DateTimeTool
							.dateFormat("", membersDTO.getCreateTime()));
			/*** 相关操作赋值 **/
			String operation = "";
			if (membersDTO.getStatus() != 0) {
				if(Utils.checkPermission("sy-1301-02")){
				/** 禁用操作按钮 */
				operation += "<a href=javascript:forbidData('member/member!updateStatus','"
						+ membersDTO.getMemId()
						+ "') title='禁用'>"
						+ Globals.IMG_FORBID + "</a>&nbsp;";
				strings.add(operation);
				}else{
					strings.add("");
				}
			} else {
				if(Utils.checkPermission("sy-1301-03")){
				/** 禁用操作按钮 */
				operation += "<a href=javascript:forbidData('member/member!updateStatus','"
						+ membersDTO.getMemId()
						+ "') title='取消禁用'>"
						+ Globals.IMG_AUDIT + "</a>&nbsp;";
				strings.add(operation);
				}else{
					strings.add("");
				}
			}
			lists.add(strings);
		}
		PageView pageView = new PageView(membersDTO.getPage(), queryResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 *@Title:updateStatus
	 *@Description:禁用用户
	 *@Return:String返回字符串结果到struts.xml
	 *@author:徐凯强
	 *@Date:2014-7-31下午12:09:04
	 */
	public String updateStatus() {
		try {
			/** 获取数据库中这条记录 */
			membersDTO = memberService.findMembersById(Integer.valueOf(this
					.getId()));
			/** 判断会员的状态 */
			if (membersDTO.getStatus().equals(1)) {
				/** 如果是正常状态就禁用 */
				/** 设置这个会员的状态为禁用 */
				membersDTO.setStatus(0);
			} else {
				membersDTO.setStatus(1);
			}

			/** 更新会员信息 */
			memberService.updateMembers(membersDTO);
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

	/**
	 *@Title:addSave
	 *@Description:添加保存站内信信息
	 *@Return:String返回字符串结果到struts.xml
	 *@author:徐凯强
	 *@Date:2014-11-20下午04:25:51
	 */
	public String addSave() {
		try {
			/** 保存站内信记录 */
			InternalMessage internalMessage = internalMessageService
					.addSave(internalMessageDTO);
			/** 会员ID数组 **/
			String[] memIds = internalMessageDTO.getMemId().split(";");
			/** 发送选中的会员 */
			for (int i = 0; i < memIds.length; i++) {
				/** 发送会员 */
				MembersDTO membersDTO = memberService.findMembersById(Integer
						.parseInt(memIds[i]));
				/** 实例化receiverDTO */
				/** 设置为未读状态 */
				receiversDTO.setStatus(0);
				receiversDTO.setMemId(membersDTO.getMemId());
				receiversDTO.setReceiverName(membersDTO.getUserName());
				receiversDTO.setInternalMessageId(internalMessage
						.getInternalMessageId());
				/** 保存收信人 */
				internalMessageService.addReceiver(receiversDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}
}
