package com.paySystem.ic.web.action.card;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.CardBIN;
import com.paySystem.ic.service.card.CardBINService;

import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.card.CardBINDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @作者 赵巧鹤
 * @类名称 CardBINAction 卡BIN 的Action
 * @项目名称 mciu
 * @创建时间 2013-12-17 上午09:39:31
 */
@Controller("/card/cardBIN")
@Scope("prototype")
public class CardBINAction extends BaseAction {
	private static final long serialVersionUID = -2556890253684757183L;

	@Resource
	CardBINService cardBINService;
	@Resource
	FunctionsService functionsService;
	private CardBINDTO cardBINDTO = new CardBINDTO();

	public CardBINDTO getCardBINDTO() {
		return cardBINDTO;
	}

	public void setCardBINDTO(CardBINDTO cardBINDTO) {
		this.cardBINDTO = cardBINDTO;
	}

	/**
	 * @Title:list
	 * @Descrition:TODO 菜单跳转进入方法
	 * @param: @return
	 * @return: String
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public String list() {
		this.getRequest().setAttribute("condValues", OptionsValue.STATE_STATUS);// 状态

		/**
		 * 0、总部 1、机构 2、商户
		 * */
		UserSession us = Utils.getUserSession();
		if (us.getUserLevel() == 0) {
			cardBINDTO.setStatus(1);
			return "list";
		} else{
			return "intercepthtml";
		}
	}

	/**
	 * @Title:addUI
	 * @Descrition:TODO 添加页面,[添加]按钮访问方法
	 * @param: @return
	 * @return: String
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public String addUI() {
		this.setMethod("addSave");
		// 流水号 设置给DTO 的binId
		cardBINDTO.setBinId(cardBINService.getBinID());
		// 封装好的方法，在后面！封装要用的下拉列框的方法
		setPub();
		return INPUT;
	}

	/**
	 * 异步获取列表数据 ps:查询所有卡BIN信息
	 * 
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {

		/**
		 * 查询结果排序参数设定
		 */
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();

		// 判断排序参数是否有值
		if (StringUtils.isNotBlank(this.getOrderProperty())// 如果有值,按照界面的值排序，如果没值，按照我下面的更新时间降序排
				&& StringUtils.isNotBlank(this.getOrderDirection())) {

			orderBy.put(this.getOrderProperty(), this.getOrderProperty());
		} else {

			// 如果页面排序方式没有要求，则设置更新时间排序

			orderBy.put("updateTime", "desc");
		}

		// 查询结果
		QueryResult<CardBIN> queryResult = cardBINService.queryTermByCond(
				(cardBINDTO.getPage() - 1) * pageNum, pageNum, cardBINDTO,
				orderBy);
		List<CardBIN> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			// 向页面赋值
			CardBIN cardBIN = list.get(i);
			List<String> strings = new ArrayList<String>();

			strings.add(String.valueOf(i + 1));
			strings.add(Utils.getString(cardBIN.getBinId()));
			strings.add(Utils.getString(cardBIN.getBinName()));
			if (cardBIN.getOrgans() != null) {
				strings.add(cardBIN.getOrgans().getName());
			} else {
				strings.add("");
			}
			strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS,
					cardBIN.getStatus()));
			strings.add(DateTimeTool.dateFormat("", cardBIN.getUpdateTime()));

			String operation = "";
			if (cardBIN.getStatus() != 9) {
				if (Utils.checkPermission("sy-2101-04")) {
					operation += "<a href=javascript:deleteData('card/cardBIN!delete','"
							+ cardBIN.getBinId()
							+ "') title='删除'>"
							+ Globals.IMG_DELETE + "</a>&nbsp;";
				}
				if (Utils.checkPermission("sy-2101-03")) {
					operation += "<a href=card/cardBIN!editUI?cardBINDTO.binId="
							+ cardBIN.getBinId()
							+ " title='修改'>"
							+ Globals.IMG_EDIT + "</a>&nbsp;";
				}
			} else {
				if (Utils.checkPermission("sy-2101-05")) {
					operation += "<a href=card/cardBIN!viewUI?cardBINDTO.binId="
							+ cardBIN.getBinId()
							+ " title='查看'>"
							+ Globals.IMG_VIEW + "</a>&nbsp;";
				}
			}
			strings.add(operation);

			lists.add(strings);
		}

		PageView pageView = new PageView(cardBINDTO.getPage(), queryResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);

	}

	/**
	 * @Title:addSave
	 * @Descrition:TODO 增加卡BIN信息操作
	 * @param: @return
	 * @return: String
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public String addSave() {

		if (cardBINService.validate(cardBINDTO.getBinId())) {
			this.getRequest().setAttribute("result",
					this.getText("exist.no.notice"));
			this.getRequest().setAttribute("url", "card/cardBIN!list");
			return ERROR;
		}
		if (cardBINService.validateBinName(cardBINDTO.getBinName(), cardBINDTO
				.getBinId())) {
			this.getRequest().setAttribute("result", "卡BIN名称已存在!");
			this.getRequest().setAttribute("url", "card/cardBIN!list");
			return ERROR;
		}
		// 保存卡BIN信息
		 CardBIN cardBIN = cardBINService.saveCardBIN(cardBINDTO);

		functionsService.saveFunction("卡BIN管理", 1, "增加卡BIN信息："
				+ cardBIN.getBinId());// 功能日志
		this.getRequest().setAttribute("result",
				this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url", "card/cardBIN!list");

		return SUCCESS;

	}

	/**
	 * @Title:editUI 修改页面
	 * @Descrition:TODO
	 * @param: @return
	 * @return: String
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public String editUI() {
		this.setMethod("editSave");
		return getUI();
	}

	/**
	 * @Title:viewUI
	 * @Descrition:TODO 查看
	 * @param: @return
	 * @return: String
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public String viewUI() {
		this.setMethod("viewUI");
		return getUI();
	}

	/**
	 * 进入修改卡BIN信息
	 * 
	 * 
	 * @return
	 */
	public String getUI() {

		CardBIN cardBIN = cardBINService.find(cardBINDTO.getBinId());
		/*
		 * 从实体对象里拿出你需要的数据，把这些数据又赋值给了DTO ，
		 * 咱们在界面上是直接从DTO里那需要的数据！但是service里是从DTO里拿数据，
		 * 拿完数据再把这些数据赋值给实体！是否有返回值，视情况而定！因为我们在editSave()
		 * 的方法里在functionsService.saveFunction("卡BIN管理", 2,
		 * "修改卡BIN："+cardBINDTO.getBinId()); 要打印日志时要用到！所以 *
		 */
		if (cardBIN != null) {
			cardBINDTO.setBinId(Utils.getString(cardBIN.getBinId()));
			cardBINDTO.setBinName(Utils.getString(cardBIN.getBinName()));
			cardBINDTO.setBinSign(cardBIN.getBinSign());
			cardBINDTO.setBinType(cardBIN.getBinType());
			cardBINDTO.setCreateTime(cardBIN.getCreateTime());
			cardBINDTO.setDayConsAmtLimt(cardBIN.getDayConsAmtLimt());
			cardBINDTO.setDayConsTimes(cardBIN.getDayConsTimes());
			cardBINDTO.setDescr(cardBIN.getDescr());
			cardBINDTO.setInitAmt(cardBIN.getInitAmt());
			cardBINDTO.setLimitAmt(cardBIN.getLimitAmt());
			cardBINDTO.setSingleConsAmt(cardBIN.getSingleConsAmt());
			cardBINDTO.setSingleRechAmt(cardBIN.getSingleRechAmt());
			cardBINDTO.setStatus(cardBIN.getStatus());
			cardBINDTO.setUpdateTime(cardBIN.getUpdateTime());
			cardBINDTO.setOrgName(cardBIN.getOrgans().getName());
			cardBINDTO.setOrganId(cardBIN.getOrgans().getOrganId());// 设定修改页面上的orgId
            cardBINDTO.setSingleConsWorn(cardBIN.getSingleConsWorn());
            cardBINDTO.setSingleRechWorn(cardBIN.getSingleRechWorn());
            cardBINDTO.setDispNoLen(cardBIN.getDispNoLen());
			setPub();

			List<CardBINDTO> cardBinDtos = new ArrayList<CardBINDTO>();

			this.getRequest().setAttribute("cardBinDtos", cardBinDtos);

			return INPUT;
		}
		return "error";
	}

	/**
	 * 修改终端信息操作
	 * 
	 * @return
	 */
	public String editSave() {
		try {
			ReturnDTO dto = cardBINService.updateCardBIN(cardBINDTO);
			if (dto.getFlag()) {
				functionsService.saveFunction("卡BIN管理", 2, "修改卡BIN："
						+ cardBINDTO.getBinId());
				this.getRequest().setAttribute("result",
						this.getText("operation.success.notice"));
				this.getRequest().setAttribute("url", "card/cardBIN!list");
				return SUCCESS;
			} else {
				this.getRequest().setAttribute("result", dto.getMsg());
				this.getRequest().setAttribute("url", "card/cardBIN!list");
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	/**
	 * @Title:delete
	 * @Descrition:TODO 删除操作
	 * @param: @return
	 * @return: String
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public String delete() {

		try {

			cardBINService.deleteCardBIN(this.getId());
			functionsService
					.saveFunction("卡BIN管理", 3, "删除卡BIN：" + this.getId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

	/**
	 * 界面要用的下拉封装成的方法
	 * */
	private void setPub() {

		this.getRequest().setAttribute("binFlagValues", OptionsValue.CARD_SW);// 卡的标志
		this.getRequest().setAttribute("binTypeValues", OptionsValue.CARD_FLAG);// 卡类别
		this.getRequest().setAttribute("statusValues",
				OptionsValue.STATE_STATUS);// 状态
		this.getRequest().setAttribute("visibleValues",
				OptionsValue.VISIBLE_STATUS);// 是否
		this.getRequest().setAttribute("cardLengthView",
				OptionsValue.CARD_LENGH_VIEW);// 卡位数显示
	}
}
