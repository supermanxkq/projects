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
import com.paySystem.ic.bean.card.CardNo;

import com.paySystem.ic.service.card.CardNoService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;

import com.paySystem.ic.web.dto.card.CardNoDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @作者 赵巧鹤
 * @类名称 CardNoAction 卡号Action
 * @项目名称 mciu
 * @创建时间 2013-12-10 下午02:55:35
 */
@Controller("/card/cardNo")
@Scope("prototype")
public class CardNoAction extends BaseAction {
	private static final long serialVersionUID = -2556890253684757183L;

	@Resource
	CardNoService cardNoService;
	@Resource
	FunctionsService functionsService;
	private CardNoDTO cardNoDTO = new CardNoDTO();

	public CardNoDTO getCardNoDTO() {
		return cardNoDTO;
	}

	public void setCardNoDTO(CardNoDTO cardNoDTO) {
		this.cardNoDTO = cardNoDTO;
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
		this.getRequest()
				.setAttribute("condValues", OptionsValue.CARDNO_STATUS);// 状态
		UserSession us = Utils.getUserSession();
		if (us.getUserLevel() == 0) {
			return "list";
		} else if (us.getUserLevel() == 1) {
			return "list";
		} else if (us.getUserLevel() == 2) {
			return "intercepthtml";
		}
		return ERROR;
	}

	/**
	 * @Title:jsonPageList
	 * @Descrition:TODO 查询
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public String jsonPageList() throws Exception {

		/**
		 * 查询结果排序参数设定
		 */
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();

		// 判断排序参数是否有值
		if (StringUtils.isNotBlank(this.getOrderProperty())// 如果有值,按照界面的值排序，如果没值，按照我下面的binId降序排
				&& StringUtils.isNotBlank(this.getOrderDirection())) {

			orderBy.put(this.getOrderProperty(), this.getOrderProperty());
		} else {
			// 如果页面排序方式没有要求，则设置按照卡BIN号排序
			orderBy.put("createTime", "desc");
		}

		QueryResult<CardNo> cardResult = cardNoService.queryCardNo((cardNoDTO
				.getPage() - 1)
				* pageNum, pageNum, cardNoDTO, orderBy);
		List<CardNo> list = cardResult.getResultlist();

		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			CardNo cardNo = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));// 序号
			strings.add(Utils.getString(cardNo.getCardNo()));// 卡号

			/*
			 * if(cardNo.getOrgans()!=null){
			 * strings.add(Utils.getString(cardNo.getOrgans().getName()));//机构名称
			 * }else{ strings.add(""); }
			 */
			strings.add(Utils.getString(cardNo.getGeneraId()));// 批次号
			strings.add(Utils.getOptionsIntegerName(OptionsValue.CARDNO_STATUS,
					cardNo.getStatus()));// 卡状态
			strings.add(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", cardNo.getCreateTime())); // 生成时间

			lists.add(strings);
		}

		PageView pageView = new PageView(cardNoDTO.getPage(), cardResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);

	}

	/**
	 * @Title:export
	 * @Descrition:TODO 导出
	 * @param: @return
	 * @return: String
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public String export() {
		String title = "卡号表";
		/**
		 * 查询结果排序参数设定
		 */
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();

		// 判断排序参数是否有值
		if (StringUtils.isNotBlank(this.getOrderProperty())// 如果有值,按照界面的值排序，如果没值，按照我下面的binId降序排
				&& StringUtils.isNotBlank(this.getOrderDirection())) {

			orderBy.put(this.getOrderProperty(), this.getOrderProperty());
		} else {
			// 如果页面排序方式没有要求，则设置按照卡BIN号排序
			orderBy.put("createTime", "desc");
		}

		try {

			setFileName(this.getRequest(), this.getResponse(), title);
			// cardNoService.exportXls(sql.toString(),params.toArray(),title,
			// this.getResponse());
			cardNoService.exportCardConsumeXls(cardNoDTO, title, this
					.getResponse());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
