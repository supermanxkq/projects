package com.paySystem.ic.web.action.system;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.system.Role;
import com.paySystem.ic.bean.system.User;
import com.paySystem.ic.service.base.MerchantsService;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.system.RoleService;
import com.paySystem.ic.service.system.UserService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.MD5;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.system.UserDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 用户管理
 * 
 * @author
 * @version
 */
@Controller("/system/user")
@Scope("prototype")
public class UserAction extends BaseAction {
	private static final long serialVersionUID = -3051940652987671139L;
	@Resource
	UserService userService;
	@Resource
	RoleService roleService;
	@Resource
	OrgansService organsService;
	@Resource
	MerchantsService merchantsService;
	@Resource
	FunctionsService functionsService;

	private UserDTO userDTO = new UserDTO();

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	/**
	 * 列表页面
	 * 
	 * @author
	 * @version
	 * @return
	 */
	public String list() {
		this.getRequest().setAttribute("statusValues",
				OptionsValue.STATE_STATUS);
		return "list";
	}

	/**
	 * 异步获取列表数据
	 * 
	 * @author
	 * @version
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");

		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
		case 1:
			sb.append(" and o.organId = '" + us.getOrganId() + "'");
			break;
		case 2:
			sb.append(" and o.merId = '" + us.getMerId() + "'");
			break;
		}
		if (userDTO.getStatus() != null && userDTO.getStatus() != -1) {

			sb.append(" and o.status = ?").append(params.size() + 1);
			params.add(userDTO.getStatus());
		}
		/** 判断过滤条件，如果无过滤条查询全部数据 */
		if (StringUtils.isNotBlank(userDTO.getUserName())) {
			sb.append(" and o.userName like ?").append(params.size() + 1);
			params.add("%" + userDTO.getUserName().trim() + "%");
		}
		if (StringUtils.isNotBlank(userDTO.getRealName())) {
			sb.append(" and o.realName like ?").append(params.size() + 1);
			params.add("%" + userDTO.getRealName() + "%");
		}

		// 返回结果
		QueryResult<User> queryResult = userService.getScrollData((userDTO
				.getPage() - 1)
				* pageNum, pageNum, sb.toString(), params.toArray(), orderby);

		List<User> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();

		for (int i = 0; i < list.size(); i++) {
			User user = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			strings.add(Utils.getString(user.getUserName()));
			strings.add(Utils.getString(user.getRealName()));
			if (user.getOrganId() != null) {
				Organs organs = organsService.find(user.getOrganId());
				strings.add(organs != null ? organs.getName() : "");
			} else {
				strings.add("");
			}
			if (user.getUserLevel() == 2) {
				Merchants merchants = merchantsService.find(user.getMerId());
				strings.add(merchants != null ? merchants.getMerName() : "");
			} else {
				strings.add("");
			}
			strings.add(Utils.getOptionsIntegerName(OptionsValue.USER_LEVEL,
					user.getUserLevel()));
			// strings.add(StringUtils.isNotBlank(user.getKeyID())?"已绑定":"未绑定");
			strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS,
					user.getStatus()));
			String operation = "";
			if (user.getStatus() != 9) {
				if (Utils.checkPermission("sy-9103-03")) {
					operation += "<a href=system/user!editUI?userDTO.userName="
							+ user.getUserName() + " title='修改'>"
							+ Globals.IMG_EDIT + "</a>&nbsp;";
					// operation +=
					// "<a href=javascript:loadData('"+user.getUserName()+"') title='修改'>"+Globals.IMG_EDIT+"</a>&nbsp;";
				}
				if (Utils.checkPermission("sy-9103-04")) {
					operation += "<a href=javascript:deleteData('system/user!delete','"
							+ user.getUserName()
							+ "') title='删除'>"
							+ Globals.IMG_DELETE + "</a>&nbsp;";
				}
				if (Utils.checkPermission("sy-9103-05")) {
					operation += "<a href=javascript:initData('"
							+ user.getUserName()
							+ "') title='初始化密码'><img src='images/cx1.gif'/></a>&nbsp;";
				}
				if (Utils.checkPermission("sy-9103-06")) {
					if (StringUtils.isBlank(user.getKeyID())) {
						operation += "<a href=javascript:bangUKeyUI('"
								+ user.getUserName()
								+ "') title='绑定UKEY'><img src='images/lock.gif'/></a>&nbsp;";
					} else {
						operation += "<a href=javascript:tieUKey('"
								+ user.getUserName()
								+ "') title='解绑UKEY'><img src='images/unlock.gif'/></a>&nbsp;";
					}

				}
			}
			strings.add(operation);
			lists.add(strings);
		}

		PageView pageView = new PageView(userDTO.getPage(), queryResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * 异步获取角色
	 * 
	 * @return
	 */
	public String ajaxRole() {
		ReturnDTO dto = new ReturnDTO();
		Set<Role> roleSet = null;
		if (StringUtils.isNotBlank(userDTO.getUserName())) {
			User user = userService.find(userDTO.getUserName());
			if (user != null && !user.getRoles().isEmpty()) {
				roleSet = user.getRoles();
			}
		}
		List<Role> list = null;

		if (userDTO.getUserLevel() == 0) {
			list = roleService
					.findByJpl("from Role o where o.status = 1 and (o.organId = '"
							+ Globals.MAIN_ORGANID + "' or o.isCommon = 1)");
		} else if (userDTO.getUserLevel() == 1) {
			list = roleService
					.findByJpl("from Role o where o.status = 1 and (o.organId = '"
							+ userDTO.getOrganId() + "' or o.isCommon = 1)");
		} else {
			list = roleService
					.findByJpl("from Role o where o.status = 1 and (o.organId = '"
							+ merchantsService.find(userDTO.getMerId())
									.getOrgans().getOrganId()
							+ "' or o.isCommon = 1)");
		}

		String checkbox = "<table width='100%' cellpadding='0' cellspacing='0' style='margin:3px auto;border-collapse:collapse;'>";
		for (int i = 0; i < list.size(); i++) {
			Role role = list.get(i);
			if (i % 3 == 0) {
				checkbox += "<tr>";
			}
			checkbox += "<td width='20%'><input type='checkbox' name='userDTO.roleIds' id='dialog_role_"
					+ role.getId() + "' value='" + role.getId() + "' ";
			if (roleSet != null && roleSet.contains(role)) {
				checkbox += "checked='checked'";
			}
			checkbox += " />";
			checkbox += "<label for='dialog_role_" + role.getId()
					+ "' class='checkboxLabel'>" + role.getName()
					+ "</label></td>";
			if (i % 3 == -1) {
				checkbox += "</tr>";
			}
		}
		checkbox += "</table>";
		userDTO.setRoleName(checkbox);
		dto.setObj(userDTO);
		return Utils.printInfo(dto);
	}

	/**
	 * 添加用户页面
	 * 
	 * @return
	 */
	public String addUI() {

		this.setMethod("addSave");
		this.getRequest().setAttribute("statusValues",
				OptionsValue.STATE_STATUSD);// 状态
		this.getRequest().setAttribute("visibleValues",
				OptionsValue.VISIBLE_STATUS);// 是否
		userDTO.setUserLevel(0);
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
		case 0:
			this.getRequest().setAttribute("userLevelValues",
					OptionsValue.USER_LEVEL);// 用户等级
			this.getRequest().setAttribute("organValues",
					organsService.getOption());
			this.getRequest().setAttribute("merchantValues",
					merchantsService.getOption());
			this.getRequest().setAttribute("redpassOrganName",
					Globals.MAIN_ORGANNAME);
			break;
		case 1:
			userDTO.setUserLevel(1);
			userDTO.setOrganId(us.getOrganId());
			userDTO.setOrganName(us.getOrganName());
			this.getRequest().setAttribute("userLevelValues",
					OptionsValue.USER_LEVEL1);// 用户等级
			this.getRequest().setAttribute("merchantValues",
					merchantsService.getOptionByOrganId(us.getOrganId()));
			break;
		case 2:
			userDTO.setUserLevel(2);
			userDTO.setMerId(us.getMerId());
			userDTO.setMerName(us.getMerName());
			this.getRequest().setAttribute("userLevelValues",
					OptionsValue.USER_LEVEL2);// 用户等级
			break;
		}
		return INPUT;
	}

	/**
	 * 添加操作
	 * 
	 * @author
	 * @version
	 * @return
	 */
	public String addSave() throws Exception {
		User user = new User();
		switch (userDTO.getUserLevel()) {
		case 0:
			user.setOrganId(Globals.MAIN_ORGANID);
			break;
		case 1:
			user.setOrganId(userDTO.getOrganId());
			break;
		case 2:
			Merchants merchants = merchantsService.find(userDTO.getMerId());
			user.setMerId(userDTO.getMerId());
			if (merchants.getOrgans() != null) {
				Organs organs = merchants.getOrgans();
				user.setOrganId(organs.getOrganId());
			}
			break;
		}
		user.setUserName(userDTO.getUserName());
		user.setPassWord(MD5.MD5Encode(Globals.PASSWORD));
		user.setRealName(userDTO.getRealName());
		user.setTeleNo(Utils.getString(userDTO.getTeleNo()));
		user.setEmail(userDTO.getEmail());
		user.setStatus(userDTO.getStatus());
		user.setUserLevel(userDTO.getUserLevel());
		user.setDlsFlag(userDTO.getDlsFlag());

		user.setCreateTime(new Date());
		user.getRoles().clear();
		if (userDTO.getRoleIds() != null) {
			for (String string : userDTO.getRoleIds()) {
				if (StringUtils.isNotBlank(string)) {
					user.getRoles().add(
							roleService.find(Integer.valueOf(string)));
				}
			}
		}
		userService.save(user);
		functionsService.saveFunction("用户管理", 1, "新增用户：" + user.getUserName());
		this.getRequest().setAttribute("result",
				this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url", "system/user!list");
		return SUCCESS;
	}

	/**
	 * 进入修改页面
	 * 
	 * @author
	 * @version
	 * @return
	 */
	public String editUI() {
		this.setMethod("editSave");
		this.getRequest().setAttribute("statusValues",
				OptionsValue.STATE_STATUSD);// 状态
		this.getRequest().setAttribute("visibleValues",
				OptionsValue.VISIBLE_STATUS);// 是否
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
		case 0:
			this.getRequest().setAttribute("userLevelValues",
					OptionsValue.USER_LEVEL);// 用户等级
			this.getRequest().setAttribute("organValues",
					organsService.getOption());
			this.getRequest().setAttribute("merchantValues",
					merchantsService.getOption());
			this.getRequest().setAttribute("redpassOrganName",
					Globals.MAIN_ORGANNAME);
			break;
		case 1:
			userDTO.setOrganId(us.getOrganId());
			userDTO.setOrganName(us.getOrganName());
			this.getRequest().setAttribute("userLevelValues",
					OptionsValue.USER_LEVEL1);// 用户等级
			this.getRequest().setAttribute("merchantValues",
					merchantsService.getOptionByOrganId(us.getOrganId()));
			break;
		case 2:
			userDTO.setMerId(us.getMerId());
			userDTO.setMerName(us.getMerName());
			this.getRequest().setAttribute("userLevelValues",
					OptionsValue.USER_LEVEL2);// 用户等级
			break;
		}
		User user = null;
		try {
			user = userService.find(new String(userDTO.getUserName().getBytes(
					"ISO-8859-1"), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (user != null) {
			userDTO.setUserName(user.getUserName());
			userDTO.setRealName(Utils.getString(user.getRealName()));
			userDTO.setMerId(user.getMerId());
			userDTO.setOrganId(user.getOrganId());
			userDTO.setTeleNo(Utils.getString(user.getTeleNo()));
			userDTO.setEmail(user.getEmail());
			userDTO.setStatus(user.getStatus());
			userDTO.setUserLevel(user.getUserLevel());
			userDTO.setDlsFlag(user.getDlsFlag());
			userDTO.setWrpass(user.getWrpass());
			userDTO.setLoginFlag(Utils.getOptionsIntegerName(
					OptionsValue.LOGIN_FLAG, user.getLoginFlag()));
			userDTO.setLastLoginTime(DateTimeTool.dateFormat("", user
					.getLastLoginTime()));
			return INPUT;
		}
		return ERROR;
	}

	/**
	 * 修改模块操作
	 * 
	 * @author
	 * @version
	 * @return
	 */
	public String editSave() throws Exception {

		User user = userService.find(userDTO.getUserName());
		switch (userDTO.getUserLevel()) {
		case 0:
			user.setOrganId(Globals.MAIN_ORGANID);
			user.setMerId(null);
			break;
		case 1:
			user.setOrganId(userDTO.getOrganId());
			user.setMerId(null);
			break;
		case 2:
			Merchants merchants = merchantsService.find(userDTO.getMerId());
			user.setMerId(userDTO.getMerId());
			if (merchants.getOrgans() != null) {
				Organs organs = merchants.getOrgans();
				user.setOrganId(organs.getOrganId());
			}
			break;
		}
		user.setRealName(userDTO.getRealName());
		user.setTeleNo(Utils.getString(userDTO.getTeleNo()));
		user.setStatus(userDTO.getStatus());
		user.setUserLevel(userDTO.getUserLevel());
		user.setDlsFlag(userDTO.getDlsFlag());
		user.setTeleNo(userDTO.getTeleNo());
		user.setEmail(userDTO.getEmail());
		user.getRoles().clear();
		if (userDTO.getRoleIds() != null) {
			for (String string : userDTO.getRoleIds()) {
				if (StringUtils.isNotBlank(string)) {
					user.getRoles().add(
							roleService.find(Integer.valueOf(string)));
				}
			}
		}
		userService.update(user);
		functionsService.saveFunction("用户管理", 2, "修改用户：" + user.getUserName());
		this.getRequest().setAttribute("result",
				this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url", "system/user!list");
		return SUCCESS;
	}

	/**
	 * 删除操作
	 * 
	 * @author
	 * @version
	 */
	public String delete() {

		try {
			// userService.delete(this.getId());
			User user = userService.find(this.getId());
			user.setStatus(9);
			userService.update(user);
			functionsService.saveFunction("用户管理", 3, "删除用户：" + this.getId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}

	/**
	 * 初始化密码...
	 * 
	 * @author
	 * @version
	 */
	public String init() {

		try {
			User user = userService.find(userDTO.getUserName());
			if (user != null) {
				user.setPassWord(MD5.MD5Encode(Globals.PASSWORD));
				userService.update(user);
				functionsService.saveFunction("用户管理", 2, "初始化密码："
						+ user.getUserName());
			}
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}

	/**
	 * 验证UKEY是否被绑定
	 * 
	 * @return
	 */
	public String checkKeyID() {

		List<User> list = userService.findByJpl("from User o where o.keyID ='"
				+ userDTO.getKeyID().toUpperCase() + "'");
		if (list == null || list.isEmpty()) {
			this.ajaxResult = "ajaxsuccess";
		} else {
			this.ajaxResult = "ajaxfailure";
			this.msgResult = "该UKEY已绑定其他用户！";
		}

		return this.ajaxResult;
	}

	/**
	 * 绑定UKEY
	 * 
	 * @return
	 */
	public String bangUKey() {

		try {
			User user = userService.find(userDTO.getUserName());
			if (user != null) {
				user.setKeyID(userDTO.getKeyID().toUpperCase());
				userService.update(user);
				functionsService.saveFunction("用户管理", 2, "绑定UKEY："
						+ user.getUserName());
			}
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}

	/**
	 * 绑定UKEY
	 * 
	 * @return
	 */
	public String tieUKey() {

		try {
			User user = userService.find(userDTO.getUserName());
			if (user != null) {
				user.setKeyID("");
				userService.update(user);
				functionsService.saveFunction("用户管理", 2, "解绑UKEY："
						+ user.getUserName());
			}
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}

	/**
	 *@Title:testUserName
	 *@Description:检测用户名称是否重复的方法
	 *@Return:String返回String类型的字符串到Struts.xml文件中
	 *@author:徐凯强
	 *@Date:2014-8-27下午02:50:57
	 */
	public String testUserName() {
		ReturnDTO returnDTO = new ReturnDTO();
		if (userService.testUserName(userDTO) == true) {
			returnDTO.setFlag(true);
		} else {
			returnDTO.setFlag(false);
		}
		return Utils.printInfo(returnDTO);
	}
}
