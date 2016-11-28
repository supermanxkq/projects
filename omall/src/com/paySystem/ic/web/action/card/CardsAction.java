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
import com.paySystem.ic.service.card.CardsDetailService;
import com.paySystem.ic.service.card.CardsService;
import com.paySystem.ic.utils.ExportUtil;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.card.CardsDTO;
import com.paySystem.ic.web.dto.card.CardsDetailDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 卡信息管理
 * 
 * @author Administrator
 * 
 */
@Controller("/card/cards")
@Scope("prototype")
public class CardsAction extends BaseAction {

	private static final long serialVersionUID = -3798678109624184767L;
	@Resource
	CardsService cardsService;
	@Resource
	CardsDetailService cardsDetailService;

	// @Resource
	private CardsDTO cardsDTO = new CardsDTO();
	private CardsDetailDTO cardsDetailDTO = new CardsDetailDTO();

	public CardsDTO getCardsDTO() {
		return cardsDTO;
	}

	public void setCardsDTO(CardsDTO cardsDTO) {
		this.cardsDTO = cardsDTO;
	}

	/**
	 * 列表页面
	 * 
	 * @version 2011-9-8 下午08:50:23
	 * @return
	 * @throws Exception
	 */
	public String list() {
	
		UserSession us = Utils.getUserSession();
		if(us.getUserLevel()!=2){		
			this.getRequest()
					.setAttribute("statusValues", OptionsValue.CARD_STATUS);
			return "list";
		}else{
			return "intercepthtml";
		}
	}

	/**
	 * 异步获取列表数据
	 * 
	 * @version 2011-9-8 下午08:51:04
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {

		QueryResult<CardsDTO> terResult = queryResult((cardsDTO.getPage() - 1)
				* pageNum, pageNum);
		List<CardsDTO> list = terResult.getResultlist();
		List<List<String>> lists = getListString(list);

		PageView pageView = new PageView(cardsDTO.getPage(), terResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	public String viewUI() {
		this.setMethod("seachDetail");
		cardsDetailDTO = cardsDetailService.findByCardNo(cardsDTO.getCardNo());
		if (cardsDetailDTO != null) {
			this.objResult = cardsDetailDTO;
			return "objResult";
		}

		return "error";
	}

	/**
	 * 导出功能
	 * */
	public String export() {
		String title = "卡表";
		try {
			setFileName(this.getRequest(), this.getResponse(), title);
			String str = "";
			ExportUtil util = new ExportUtil();
			List<String> headers = new ArrayList<String>();
			headers.add("卡号");
			headers.add("卡名称");
			headers.add("发卡机构");
			headers.add("卡标志");
			headers.add("持卡人");
			headers.add("卡级别");
			headers.add("有效期");
			headers.add("会员");
			headers.add("密码");
			headers.add("状态");

			QueryResult<CardsDTO> terResult = queryResult(-1, -1);
			List<CardsDTO> list = terResult.getResultlist();
			List<List<String>> lists = new ArrayList<List<String>>();
			for (int i = 0; i < list.size(); i++) {
				// 向页面赋值
				CardsDTO cardsDTO = list.get(i);
				// 封装json串儿
				List<String> strings = new ArrayList<String>();
				strings = getString(strings, cardsDTO);
				lists.add(strings);
			}
			str = util.createXls(headers, lists, title, this.getResponse());
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获得查询结果
	 * 
	 * @param page
	 * @param pageNum
	 * @return
	 */
	public QueryResult<CardsDTO> queryResult(int page, int pageNum) {
		/**
		 * 查询结果排序参数设定
		 */
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderBy.put("createTime", "desc");
		}

		QueryResult<CardsDTO> terResult = cardsService.queryAll(page, pageNum,
				cardsDTO, orderBy);

		return terResult;
	}

	/**
	 * 封装字符串
	 * 
	 * @param list
	 * @return
	 */
	public List<List<String>> getListString(List<CardsDTO> list) {
		List<List<String>> lists = new ArrayList<List<String>>();
		/****************************************** */

		for (int i = 0; i < list.size(); i++) {

			// 向页面赋值
			CardsDTO cardsDTO = list.get(i);
			// 封装json串儿
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			strings = getString(strings, cardsDTO);
			String operation = "";
			if (Utils.checkPermission("sy-2103-02")) {
				// operation += "<a href=card/cards!viewUI?cardsListDTO.cardNo="
				// + cardsList.getCardNo() + " title='查看'>"
				// + Globals.IMG_VIEW + "</a>&nbsp;";
				operation += "<a href=javascript:loadData('"
						+ cardsDTO.getCardNo() + "') title='查看'>"
						+ Globals.IMG_VIEW + "</a>&nbsp;";
			}
			strings.add(operation);
			lists.add(strings);

		}
		return lists;
	}

	private List<String> getString(List<String> strings, CardsDTO cardsDTO) {

		strings.add(Utils.getString(cardsDTO.getCardNo()));
		strings.add(Utils.getString(cardsDTO.getBinName()));
		strings.add(Utils.getString(cardsDTO.getOrganName()));

		strings.add(Utils.getOptionsIntegerName(OptionsValue.CARD_SW, cardsDTO
				.getBinFlag()));
		// strings.add(Utils.getString(cardsDTO.getCardLimitValue()));
		strings.add(Utils.getString(cardsDTO.getHoldName()));
		strings.add(Utils.getString(cardsDTO.getLevelName()));
		strings.add(Utils.getString(cardsDTO.getValidTime()));
		strings.add(Utils.getString(Utils.getOptionsIntegerName(
				OptionsValue.MEM_SIGN, cardsDTO.getMemsign())));
		strings.add(Utils.getString(Utils.getOptionsIntegerName(
				OptionsValue.PWD_SIGN, cardsDTO.getPwdsign())));
		strings.add(Utils.getOptionsIntegerName(OptionsValue.CARD_STATUS,
				cardsDTO.getStatus()));

		return strings;
	}
}
