package com.blog.web.action.leavemessage;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.bean.PageView;
import com.blog.bean.QueryResult;
import com.blog.dto.ObjectListInfoDTO;
import com.blog.dto.leavemessage.LeaveMessageDTO;
import com.blog.service.leavemessage.LeaveMessageService;
import com.blog.util.Utils;
import com.blog.web.action.BaseAction;

@Controller("/leavemessage/leavemessage")
@Scope("prototype")
public class LeaveMessageAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 226448021540232246L;
	// @Autowired
	// private LeaveMessageService leaveMessageService;
	public List<LeaveMessageDTO> leaveMessages;
	public PageView pageView;
	public String pageViewHtml;
	public String getPageViewHtml() {
		return pageViewHtml;
	}

	public void setPageViewHtml(String pageViewHtml) {
		this.pageViewHtml = pageViewHtml;
	}

	public PageView getPageView() {
		return pageView;
	}

	public void setPageView(PageView pageView) {
		this.pageView = pageView;
	}

	// private LeaveMessage leaveMessage;
	// // 表单中的rand
	// private String rand;
	// private long floor;
	//
	// public long getFloor() {
	// return floor;
	// }
	//
	// public void setFloor(long floor) {
	// this.floor = floor;
	// }
	//
	// public String getRand() {
	// return rand;
	// }
	//
	// public void setRand(String rand) {
	// this.rand = rand;
	// }
	//
	// private int leaveMessageId;
	// private int pageNow = 1;
	// private int pageSize = 16;
	// private long pageAll = 0;
	// private long count;
	//
	// public long getCount() {
	// return count;
	// }
	//
	// public void setCount(long count) {
	// this.count = count;
	// }
	//
	// private LeaveMessageReply leaveMessageReply;
	// // 获取要删除的所有菜单的id字符串
	// private String menuIds;
	//
	// public String getMenuIds() {
	// return menuIds;
	// }
	//
	// public void setMenuIds(String menuIds) {
	// this.menuIds = menuIds;
	// }
	//
	// public LeaveMessageReply getLeaveMessageReply() {
	// return leaveMessageReply;
	// }
	//
	// public void setLeaveMessageReply(LeaveMessageReply leaveMessageReply) {
	// this.leaveMessageReply = leaveMessageReply;
	// }
	//
	// public void setPageAll(long pageAll) {
	// this.pageAll = pageAll;
	// }
	//
	// public int getPageNow() {
	// return pageNow;
	// }
	//
	// public void setPageNow(int pageNow) {
	// this.pageNow = pageNow;
	// }
	//
	// public int getPageSize() {
	// return pageSize;
	// }
	//
	// public void setPageSize(int pageSize) {
	// this.pageSize = pageSize;
	// }
	//
	// public Long getPageAll() {
	// return pageAll;
	// }
	//
	// private Long leaveMessageSize;
	//
	// public Long getLeaveMessageSize() {
	// return leaveMessageSize;
	// }
	//
	// public void setLeaveMessageSize(Long leaveMessageSize) {
	// this.leaveMessageSize = leaveMessageSize;
	// }
	//
	public List<LeaveMessageDTO> getLeaveMessages() {
		return leaveMessages;
	}

	//
	public void setLeaveMessages(List<LeaveMessageDTO> leaveMessages) {
		this.leaveMessages = leaveMessages;
	}

	//
	// public LeaveMessage getLeaveMessage() {
	// return leaveMessage;
	// }
	//
	// public void setLeaveMessage(LeaveMessage leaveMessage) {
	// this.leaveMessage = leaveMessage;
	// }
	//
	// public int getLeaveMessageId() {
	// return leaveMessageId;
	// }
	//
	// public void setLeaveMessageId(int leaveMessageId) {
	// this.leaveMessageId = leaveMessageId;
	// }
	@Resource
	private LeaveMessageService leaveMessageService;

	public LeaveMessageDTO leaveMessageDTO=new LeaveMessageDTO();

	public LeaveMessageDTO getLeaveMessageDTO() {
		return leaveMessageDTO;
	}

	public void setLeaveMessageDTO(LeaveMessageDTO leaveMessageDTO) {
		this.leaveMessageDTO = leaveMessageDTO;
	}

	public String list() {
		return "list";
	}

	public String addSave() {
		try {
			leaveMessageDTO.setLeaveMessageDate(new Date());
			if (leaveMessageDTO.getLeaveMessageName().equals("")) {
				leaveMessageDTO.setLeaveMessageName("无名英雄");
			}
			leaveMessageService.saveLeaveMessage(leaveMessageDTO);
			this.getRequest().setAttribute("url", "leavemessage/leavemessage!list");
			this.getRequest().setAttribute("result", "添加成功！");
			return "ajaxsuccess";
		} catch (Exception e) {
			return "ajaxfailure";
		}
	}

	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderby.put("leaveMessageDate", "desc");
		}
		/** 返回结果 */
		QueryResult<LeaveMessageDTO> queryResult = leaveMessageService
				.queryAll((leaveMessageDTO.getPage() - 1) * pageNum, pageNum,
						leaveMessageDTO, orderby);
		leaveMessages = queryResult.getResultlist();
		
		
		PageView pageView = new PageView(leaveMessageDTO.getPage(), queryResult
				.getTotalrecord());
		ObjectListInfoDTO objectListInfoDTO = new ObjectListInfoDTO(leaveMessages, getPageHTML(pageView));
		return Utils.printInfo(objectListInfoDTO);
	}
	/**
	 * 1.查找所有的留言
	 * 
	 * @return
	 */
	// public String findLeaveMessageToFront() {
	// leaveMessages = leaveMessageService.findLeaveMessage(pageSize,
	// (pageNow - 1) * pageSize);
	// count = leaveMessageService.queryAllCount();
	// pageAll = count % pageSize == 0 ? count / pageSize : count / pageSize
	// + 1;
	// floor = count - (pageNow - 1) * pageSize;
	// return "success";
	// }

	/**
	 * 查找留言信息到后台
	 * 
	 * @return
	 */
	// public String findLeaveMessage() {
	// leaveMessages = leaveMessageService.findLeaveMessage(pageSize,
	// (pageNow - 1) * pageSize);
	// count = leaveMessageService.queryAllCount();
	// pageAll = count % pageSize == 0 ? count / pageSize : count / pageSize
	// + 1;
	// floor = count - (pageNow - 1) * pageSize;
	// return "success";
	// }

	/**
	 * 2.通过id查找留言信息
	 * 
	 * @return
	 */
	// public String findLeaveMessageById() {
	// leaveMessage = leaveMessageService.findLeaveMessageById(leaveMessageId);
	// return "success";
	// }

	/**
	 * 通过留言信息的id找到回复
	 * 
	 * @return
	 */
	// public String findLeaveMessageReplyByLeaveMessageId() {
	// leaveMessageReply = leaveMessageService
	// .findLeaveMessageReplyByLeaveMessageId(leaveMessageId);
	// return "success";
	// }

	/**
	 * 添加留言信息
	 * 
	 * @return
	 * @throws IOException
	 */
	// public String addLeaveMessage() {
	//
	// // 如果留言者没有输入昵称，默认为热心网友
	// if (leaveMessage.getLeaveMessageName().equals("")
	// || leaveMessage.getLeaveMessageName() == null) {
	// leaveMessage.setLeaveMessageName("热心网友");
	// }
	// // 时区的设置
	// TimeZone time = TimeZone.getTimeZone("GMT+8"); // 设置为东八区
	// TimeZone.setDefault(time);// 设置时区
	// Calendar calendar = Calendar.getInstance();// 获取实例
	// leaveMessage.setLeaveMessageDate(calendar.getTime());
	//
	// // 设置留言者的头像,随机产生1-30包括1和30之间的数字
	// int number = (int) (Math.random() * 29 + 1);
	// leaveMessage.setLeaveHeadPhoto(number + ".jpg");
	// // 保存留言
	// leaveMessageService.saveLeaveMessage(leaveMessage);
	// return "success";
	// }

	/*
	 * 删除
	 */
	// public String deleteLeaveMessage() {
	// leaveMessage = leaveMessageService.findLeaveMessageById(leaveMessageId);
	// leaveMessageService.deleteLeaveMessage(leaveMessage);
	// return "success";
	// }

	/**
	 * 4删除所有的留言（批量删除）
	 * 
	 * @return
	 */
	// public String deleteAllLeaveMessage() {
	// // 获取要删除导航菜单的id数组
	// // 拆分，页面传来的，拼接字符串
	// String[] arry = menuIds.split(",");
	//
	// for (int i = 0; i < arry.length; i++) {
	// String id = arry[i];
	// leaveMessage = leaveMessageService.findLeaveMessageById(Integer
	// .parseInt(id));
	// leaveMessageService.deleteLeaveMessage(leaveMessage);
	// System.out.println(id);
	// }
	// return "success";
	// }
}
