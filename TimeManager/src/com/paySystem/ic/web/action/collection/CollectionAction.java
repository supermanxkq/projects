package com.paySystem.ic.web.action.collection;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.system.User;
import com.paySystem.ic.service.collection.CollectionService;
import com.paySystem.ic.service.system.UserService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.MailUtils;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.collection.MainTaskDTO;

/**
 * @ProjectName:TimeManager
 * @ClassName:CollectionAction
 * @Description:收集箱控制类
 * @date: 2014-10-18下午10:05:54
 * @author: 徐凯强
 * @version: V1.0
 */
@Controller("/collection/collection")
@Scope("prototype")
public class CollectionAction extends BaseAction {
	/** 序列化，便于网络传输 */
	private static final long serialVersionUID = 3469932518743564890L;

	/** 注入Service */
	@Resource
	private CollectionService collectionService;
	@Resource
	private UserService userService;
	/** 实例化DTO对象 */
	MainTaskDTO mainTaskDTO = new MainTaskDTO();

	public MainTaskDTO getMainTaskDTO() {
		return mainTaskDTO;
	}

	public void setMainTaskDTO(MainTaskDTO mainTaskDTO) {
		this.mainTaskDTO = mainTaskDTO;
	}

	/**
	 * @Title:list
	 * @Description:跳转到list页面中去
	 * @Return:String返回字符串结果到struts.xml文件中
	 * @author:徐凯强
	 * @Date:2014-10-18下午10:09:43
	 */
	public String list() {
		this.setMethod("addSave");
		return "list";
	}

	/**
	 * @MethodName:jsonPageList
	 * @Description:获取所有主任务记录，并返回到CollectionList.jsp页面中
	 * @throws Exception抛出异常
	 * @author:徐凯强
	 * @return String返回字符串结果到Struts.xml
	 * @Date:2014-10-18下午11:24:03
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
			orderby.put("mainTaskId", "DESC");
		}
		/** 获取queryResult中的值 */
		QueryResult<MainTaskDTO> quResult = collectionService.findAll(
				(mainTaskDTO.getPage() - 1) * pageNum, pageNum, mainTaskDTO,
				orderby);
		/** 获取queryResult中的集合 */
		List<MainTaskDTO> dtoResult = quResult.getResultlist();
		/** 结算信息字符串信息集合 */
		List<List<String>> lists = new ArrayList<List<String>>();
		/** QueryResult对象的getResultlist方法获取GoodsAttributeDTO集合 */
		for (int i = 0; i < dtoResult.size(); i++) {
			MainTaskDTO mainTaskDTO = dtoResult.get(i);
			List<String> strings = new ArrayList<String>();
			/** 添加到字符串集合中去 */
			/** 不添加已完成状态的任务名称 */
			if (!mainTaskDTO.getStatus().equals(0)) {
				/** 操作字段 */
				strings
						.add("<input type='checkbox' id='taskName' style='cursor:pointer;' title='已完成' value='"
								+ mainTaskDTO.getMainTaskId()
								+ "' onclick='deleteResult("
								+ mainTaskDTO.getMainTaskId() + ");' /> ");
				/** 任务优先级 */
				if (mainTaskDTO.getLevel() != null) {
					if (mainTaskDTO.getLevel().equals(0)) {
						strings.add("无");
					} else if (mainTaskDTO.getLevel().equals(1)) {
						strings.add(Globals.IMG_LOW);
					} else if (mainTaskDTO.getLevel().equals(2)) {
						strings.add(Globals.IMG_MIDDLE);
					} else {
						strings.add(Globals.IMG_HIGH);
					}
				} else {
					strings.add("无");
				}

				String mainTaskNameString = "";
				/** 判断任务名称长度，或是内容太长则只显示部分信息，否则全部显示 **/
				if (mainTaskDTO.getMainTaskName().length() > 7) {
					mainTaskNameString = mainTaskDTO.getMainTaskName();
//							.substring(0, 9)
//							+ "......";
				} else {
					mainTaskNameString = mainTaskDTO.getMainTaskName();
				}
				/** 任务名称 */
				strings
						.add("<a href=collection/collection!editUI?mainTaskDTO.mainTaskId="
								+ mainTaskDTO.getMainTaskId()
								+ " title='修改' style='text-align:center;color:#0000ff;cursor:pointer;font-size: 14px;text-decoration:underline;'>"
								+ mainTaskNameString + "</a>");

				/** 任务状态 */
				strings.add(Utils.getOptionsIntegerName(
						OptionsValue.STATUSVALUE, mainTaskDTO.getStatus()));
				/** 提醒方式 */
				strings.add(Utils.getOptionsIntegerName(OptionsValue.REMINDWAY,
						mainTaskDTO.getRemindWay()));
				/** 提醒时间 */
				strings.add(DateTimeTool
						.dateFormat("yyyy-MM-dd HH:mm", mainTaskDTO.getByDate()));
				if (mainTaskDTO.getByDate() != null) {
					long days = DateTimeTool.getDiffDay(DateTimeTool
							.dateFormat("yyyy-MM-dd", new Date()), DateTimeTool
							.dateFormat("yyyy-MM-dd", mainTaskDTO.getByDate()));
					if (days > 0) {
						strings.add("<font color='gray' >还剩" + days
								+ "天</font>");
					} else if (days == 0) {
						strings.add("<font color='red' >今天到期</font>");
					} else {
						strings.add("<font color='red' >过期" + (-days)
								+ "天</font>");
					}
				} else {
					strings.add("未设置截至日期");
				}
				strings.add("<input type='button' id='"+mainTaskDTO.getMainTaskId()+"' class='formButton' style='width:80px;' onclick='startCountDown("+mainTaskDTO.getMainTaskId()+");' value='开始'/>");
			}
			lists.add(strings);
		}
		/** 实例化PageView对象,获取分页的参数、总页数、总记录数 */
		PageView pageView = new PageView(mainTaskDTO.getPage(), quResult
				.getTotalrecord());
		/** 实例化ListInfoDTO */
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * @Title:addSave
	 * @Description:添加
	 * @Return:String返回字符串结果到struts.xml
	 * @author:徐凯强
	 * @Date:2014-10-13上午12:05:40
	 */
	public String addSave() {
		try {
			mainTaskDTO.setEstimatedTimeHour(0);
			mainTaskDTO.setEstimatedTimeMinu(0);
			mainTaskDTO.setStatus(1);
			mainTaskDTO.setLevel(0);
			mainTaskDTO.setRemindWay(2);
			mainTaskDTO.setCreateTime(new Date());
			mainTaskDTO.setByDate(DateTimeTool.nDaysAfter(1, new Date()));
			collectionService.addSave(mainTaskDTO);
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

	/**
	 * @Title:delete
	 * @Description:已完成
	 * @Return:String返回字符串结果到struts.xml
	 * @author:徐凯强
	 * @Date:2014-10-13上午12:05:40
	 */
	public String delete() {
		try {
			MainTaskDTO mainTaskDTO = collectionService.findMainTaskDTO(Integer
					.valueOf(this.getId()));
			/** 将任务设置为已完成 */
			mainTaskDTO.setStatus(0);
			collectionService.updateMainTask(mainTaskDTO);
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

	/**
	 * @Title:editUI
	 * @Description:跳转到编辑界面
	 * @Return:String返回字符串结果到struts.xml
	 * @author:徐凯强
	 * @@Date:2014-10-13上午12:05:40
	 */
	public String editUI() {
		/** 任务的优先级 */
		this.getSession().setAttribute("levelValue", OptionsValue.LEVELVALUE);
		this.setMethod("updateData");
		mainTaskDTO = collectionService.findMainTaskDTO(mainTaskDTO
				.getMainTaskId());
		mainTaskDTO.setCreateTimeString(DateTimeTool.dateFormat(
				"yyyy-MM-dd HH:mm", mainTaskDTO.getCreateTime()));
		mainTaskDTO.setByDateString(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm",
				mainTaskDTO.getByDate()));
		this.getRequest().setAttribute("remindWays", OptionsValue.REMINDWAY);
		return "input";
	}

	/**
	 * @Title:updateData
	 * @Description:更新主任务
	 * @Return:String返回字符串结果到struts.xml
	 * @author:徐凯强
	 * @Date:2014-10-13上午12:05:40
	 */
	public String updateData() {
		/** 设置为启用状态 */
		mainTaskDTO.setStatus(1);
		try {
			mainTaskDTO.setCreateTime(DateTimeTool.dateFormat(
					"yyyy-MM-dd HH:mm", mainTaskDTO.getCreateTimeString()));
			mainTaskDTO.setByDate(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm",
					mainTaskDTO.getByDateString()));
			collectionService.updateMainTask(mainTaskDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getRequest().setAttribute("url", "collection/collection!list");
		return "success";
	}

	/**
	 * @Title:addSave
	 * @Description:添加
	 * @Return:String返回字符串结果到struts.xml
	 * @author:徐凯强
	 * @Date:2014-10-13上午12:05:40
	 */
	public String batchAddSave() {
		String tasksString = mainTaskDTO.getTasksString();
		String[] task = tasksString.split("<br/>");
		for (int i = 0; i < task.length; i++) {
			try {
				mainTaskDTO.setMainTaskName(task[i]);
				mainTaskDTO.setEstimatedTimeHour(0);
				mainTaskDTO.setEstimatedTimeMinu(0);
				mainTaskDTO.setStatus(1);
				mainTaskDTO.setByDate(DateTimeTool.nDaysAfter(1, new Date()));
				mainTaskDTO.setLevel(0);
				mainTaskDTO.setRemindWay(2);
				mainTaskDTO.setCreateTime(new Date());
				collectionService.addSave(mainTaskDTO);
				this.ajaxResult = "ajaxsuccess";
			} catch (Exception e) {
				e.printStackTrace();
				this.ajaxResult = "ajaxfailure";
				this.msgResult = e.getMessage();
			}

		}

		return this.ajaxResult;
	}

	/**
	 *@MethodName:sendMail
	 *@Description:定时发送邮件
	 *@author:徐凯强
	 *@return void返回空
	 *@date:2014-12-28上午04:57:36
	 */
	public void sendMail() throws Exception {
		/** 查询数据库中所有的任务记录，状态为未完成状态 */
		List<MainTaskDTO> list = collectionService.queryAll();
		/** 邮件内容 **/
		String content = null;
		/** 邮件主题 **/
		String subject = null;
		/** 格式化当前系统时间 **/
		String sysDate = DateTimeTool
				.dateFormat("yyyy-MM-dd HH:mm", new Date());
		boolean flag;
		for (int i = 0; i < list.size(); i++) {
			MainTaskDTO mainTaskDTO = new MainTaskDTO();
			mainTaskDTO = list.get(i);
			/** 调用方法判断日期是否和系统时间相等 **/
			if (mainTaskDTO.getByDate() != null) {
				flag = sysDate.equals(DateTimeTool.dateFormat(
						"yyyy-MM-dd HH:mm", mainTaskDTO.getByDate()));
				/** 如果相等则发送邮件 **/
				if (flag) {
					/** 获取和截止时间相同的记录 **/
					mainTaskDTO = collectionService.findMainTaskDTO(mainTaskDTO
							.getMainTaskId());
					User user = userService.find(mainTaskDTO.getUserName());
					/** 判断提醒的类型 */
					if (mainTaskDTO.getRemindWay().equals(1)) {
						/** 邮件发送 */
						/** 主任务标题 */
						subject = "【任务名称】：" + mainTaskDTO.getMainTaskName();
						/** 主任务内容 */
						if (mainTaskDTO.getDescr() != null
								&& !mainTaskDTO.getDescr().equals("")) {
							content = "【任务描述】：" + mainTaskDTO.getDescr();
						} else {
							content = "无。";
						}
						MailUtils.sendMail(subject, content, user.getEmail());
					} else if (mainTaskDTO.getRemindWay().equals(2)) {
						/** 短信发送 */
						System.out.println("发送短信***********************************手机号："+user.getTeleNo());
						mainTaskDTO.setToPhone(user.getTeleNo());
						collectionService.sendShortMessage(mainTaskDTO);
					}
				}
			}
		}
	}

	/**
	 *@MethodName:updateTaskStatus
	 *@Description:定时更新任务（过期）状态，每分钟一次
	 *@author:徐凯强
	 *@return String
	 *@date:2015-1-6下午09:31:44
	 *@version: V1.0
	 */
	public void updateTaskStatus() {
		
		try {
			List<MainTaskDTO> list = collectionService.queryAll();
			/** 如果当前时间>截止日期，将该任务状态设置为已过期状态9 */
			for (MainTaskDTO mainTaskDTO : list) {
				long days = DateTimeTool.judgeDate(DateTimeTool.dateFormat(
						"yyyy-MM-dd HH:mm", new Date()), DateTimeTool.dateFormat(
						"yyyy-MM-dd HH:mm", mainTaskDTO.getByDate()));
				if (days < 0||days==0) {
					mainTaskDTO.setStatus(9);
					collectionService.updateMainTask(mainTaskDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/** 查询数据库中所有的任务记录，状态为未完成状态 */

	}
}
