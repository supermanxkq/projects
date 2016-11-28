package com.paySystem.ic.web.action.internalMessage;

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
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.base.MerchantsDTO;
import com.paySystem.ic.web.dto.internalMessage.InternalMessageDTO;
import com.paySystem.ic.web.dto.internalMessage.ReceiversDTO;
import com.paySystem.ic.web.dto.member.MembersDTO;

/**
 * @ProjectName:omall
 * @ClassName:InternalMessageAction
 * @Description:站内信控制类
 * @date: 2014-11-18下午03:19:30
 * @author: 徐凯强
 * @version: V1.0
 */
@Controller("/internalMessage/internalMessage")
@Scope("prototype")
public class InternalMessageAction extends BaseAction {
	private static final long serialVersionUID = 4566492141509678748L;

	/** 实例化DTO对象 */
	private InternalMessageDTO internalMessageDTO = new InternalMessageDTO();
	/** 注入service */
	@Resource
	private InternalMessageService internalMessageService;
	private ReceiversDTO receiversDTO = new ReceiversDTO();

	/**
	 *@Title:addSave
	 *@Description:添加保存站内信信息
	 *@Return:String返回字符串结果到struts.xml
	 *@author:徐凯强
	 *@Date:2014-11-20下午04:25:51
	 */
	public String addSave() {
		/** 保存站内信记录 */
		InternalMessage internalMessage = internalMessageService
				.addSave(internalMessageDTO);
		/** 将会员编号和商户编号分离出来进行保存 */
		/** 发送全部商户 */
		if (internalMessageDTO.getReceiverFlag().equals(1)) {
			QueryResult<MerchantsDTO> mQueryResult = internalMessageService
					.findMerchants();
			for (int i = 0; i < mQueryResult.getResultlist().size(); i++) {
				MerchantsDTO merchantsDTO = mQueryResult.getResultlist().get(i);
				receiversDTO.setMerId(merchantsDTO.getMerId());
				/** 设置为未读状态 */
				receiversDTO.setStatus(0);
				receiversDTO.setReceiverName(merchantsDTO.getMerName());
				receiversDTO.setInternalMessageId(internalMessage
						.getInternalMessageId());
				internalMessageService.addReceiver(receiversDTO);
			}

		} else {
			/** 发送全部会员 */
			QueryResult<MembersDTO> mQueryResult = internalMessageService
					.findMembers();
			for (int i = 0; i < mQueryResult.getResultlist().size(); i++) {
				MembersDTO membersDTO = mQueryResult.getResultlist().get(i);
				/**禁用的会员不能接收站内信*/
				if (!membersDTO.getStatus().equals(0)) {
					receiversDTO.setMemId(membersDTO.getMemId());
					receiversDTO.setReceiverName(membersDTO.getUserName());
					receiversDTO.setInternalMessageId(internalMessage
							.getInternalMessageId());
					/** 设置为未读状态 */
					receiversDTO.setStatus(0);
					internalMessageService.addReceiver(receiversDTO);
				}
			}
		}
		/** 跳转 */
		this.getRequest().setAttribute("result",
				this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url",
				"internalMessage/internalMessage!list");
		return SUCCESS;
	}

	/**
	 *@Title:addUI
	 *@Description:跳转到添加站内信
	 *@Return:String返回字符串结果到struts.xml
	 *@author:徐凯强
	 *@Date:2014-11-20下午05:04:21
	 */
	public String addUI() {
		/** 收信人 */
		this.getRequest().setAttribute("receiversFlag",
				OptionsValue.RECEIVERS_FLAG);
		this.setMethod("addSave");
		return "input";
	}

	/**
	 *@Title:checkUI
	 *@Description:查看站内信详情
	 *@Return:String返回字符串结果到struts.xml
	 *@author:徐凯强
	 *@Date:2014-11-20下午05:49:52
	 */
	public String checkUI() {
		this.setMethod("checkUI");
		/** 收信人 */
		this.getRequest().setAttribute("receiversFlag",
				OptionsValue.RECEIVERS_FLAG);
		/** 从数据库中这个站内信下的详细信息 */
		internalMessageDTO = internalMessageService
				.findDetailsByInternalMessageId(internalMessageDTO);
		receiversDTO = internalMessageService
				.findReceiversByInternalMessageId(internalMessageDTO);
		return "input";
	}

	/**
	 *@Title:jsonPageList
	 *@Description:获取所有站内信记录
	 *@throws Exception抛出异常
	 *@Return:String返回字符串结果到struts.xml文件中
	 *@author:徐凯强
	 *@Date:2014-11-21下午12:19:24
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {
		/** 存储排序信息的集合 */
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		/** 如果获取排序顺序为空，设置为降序排列 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderby.put("internalMessageId", "desc");
		}
		/** 获取queryResult中的值 */
		QueryResult<InternalMessageDTO> quResult = internalMessageService
				.queryAll((internalMessageDTO.getPage() - 1) * pageNum,
						pageNum, internalMessageDTO, orderby);
		/** 获取queryResult中的集合 */
		List<InternalMessageDTO> dtoResult = quResult.getResultlist();
		/** 结算信息字符串信息集合 */
		List<List<String>> lists = new ArrayList<List<String>>();
		/** QueryResult对象的getResultlist方法获取GoodsAttributeDTO集合 */
		for (int i = 0; i < dtoResult.size(); i++) {
			InternalMessageDTO internalMessageDTO = dtoResult.get(i);
			List<String> strings = new ArrayList<String>();
			/** 添加到字符串集合中去 */
			/** 序号 */
			strings.add(String.valueOf(i + 1));
			/** 标题 */
			strings.add(Utils.getString(internalMessageDTO.getTitle()));
			/** 内容 */
			if (internalMessageDTO.getContent().length() < 5) {
				strings.add(internalMessageDTO.getContent());
			} else {
				String contentValue = internalMessageDTO.getContent()
						.substring(0, 5)
						+ "……";
				strings.add(contentValue);
			}
			/** 发送人 */
			strings.add(internalMessageDTO.getSender());
			/** 发送时间 */
			strings.add(DateTimeTool.dateFormat("", internalMessageDTO
					.getSendTime()));
			/** 操作字段 */
			String operation = "";
			operation += "<a href=internalMessage/internalMessage!checkUI?internalMessageDTO.internalMessageId="
					+ internalMessageDTO.getInternalMessageId()
					+ " title='查看'>" + Globals.IMG_VIEW + "</a>&nbsp;";
			strings.add(operation);
			lists.add(strings);
		}
		/** 实例化PageView对象,获取分页的参数、总页数、总记录数 */
		PageView pageView = new PageView(internalMessageDTO.getPage(), quResult
				.getTotalrecord());
		/** 实例化ListInfoDTO */
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 *@Title:list
	 *@Description:页面跳转到internalMessageList.jsp
	 *@Return:String返回字符串结果到struts.xml文件中
	 *@author:徐凯强
	 *@Date:2014-11-21下午12:20:39
	 */
	public String list() {
		return "list";
	}

	public void setInternalMessageDTO(InternalMessageDTO internalMessageDTO) {
		this.internalMessageDTO = internalMessageDTO;
	}

	public void setReceiversDTO(ReceiversDTO receiversDTO) {
		this.receiversDTO = receiversDTO;
	}

	public InternalMessageDTO getInternalMessageDTO() {
		return internalMessageDTO;
	}

	public ReceiversDTO getReceiversDTO() {
		return receiversDTO;
	}

}
