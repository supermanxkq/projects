package com.paySystem.ic.web.action.base;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.SingleRelation;
import com.paySystem.ic.service.base.MerSinRelationService;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.base.SingleRelationService;
import com.paySystem.ic.service.card.CardBINService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.MerchantsDTO;
import com.paySystem.ic.web.dto.base.SingleRelationDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsString;

@Controller("/base/sinrelation")
@Scope("prototype")
public class SingleRelationAction extends BaseAction {

	private static final long serialVersionUID = 4105173519965883793L;

	@Resource SingleRelationService singleRelationService;
	@Resource FunctionsService functionsService;
	@Resource OrgansService organsService;
	@Resource CardBINService cardBinService;
	@Resource MerSinRelationService merSinService;
	public static Logger singleRelLog = Logger.getLogger(SingleRelationAction.class);
	private SingleRelationDTO singleRelationDTO = new SingleRelationDTO();
	private MerchantsDTO merchantsDTO = new MerchantsDTO(); 

	
	
	public MerchantsDTO getMerchantsDTO() {
		return merchantsDTO;
	}

	public void setMerchantsDTO(MerchantsDTO merchantsDTO) {
		this.merchantsDTO = merchantsDTO;
	}

	public SingleRelationDTO getSingleRelationDTO() {
		return singleRelationDTO;
	}

	public void setSingleRelationDTO(SingleRelationDTO singleRelationDTO) {
		this.singleRelationDTO = singleRelationDTO;
	}

	/**
	 * 点击界面左侧菜单访问方法
	 * 
	 * @return String retString
	 */
	public String list() {
		this.getRequest().setAttribute("status", OptionsValue.STATE_STATUS);
		singleRelationDTO.setStatus(1);
		UserSession us = Utils.getUserSession();
		
		if(us.getUserLevel()==0){
			return "list";
		}else{
			return "intercepthtml";
		}
	}

	/**
	 * 页面查询请求访问方法
	 * 
	 * @return String retString
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {

		/**
		 * 查询结果排序参数设定
		 */
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		UserSession us = Utils.getUserSession();
		// 判断排序参数是否有值
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderProperty());
		} else {
			orderBy.put("sinRelId", "desc");
		}

		QueryResult<SingleRelation> terResult = singleRelationService
				.querySingleRelByCond((singleRelationDTO.getPage() - 1)
						* pageNum, pageNum, singleRelationDTO, orderBy);

		List<SingleRelation> list = terResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();

		for (int i = 0; i < list.size(); i++) {
			// 向页面赋值
			SingleRelation singleRelation = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			/* strings.add(Utils.getString(singleRelation.getSinRelId())); */
			strings.add(Utils.getString(singleRelation.getMerchants()
					.getMerId()));
			if (singleRelation.getMerchants() != null) {
				// 添加商户名称
				strings.add(Utils.getString(singleRelation.getMerchants()
						.getMerName()));
			} else {
				strings.add("");
			}

			if (singleRelation.getMerchants().getOrgans() != null) {
				// 添加商户所属机构名称
				strings.add(Utils.getString(singleRelation.getMerchants()
						.getOrgans().getName()));
			} else {
				strings.add("");
			}

			if (singleRelation.getOrgans() != null) {
				// 添加收单机构名称
				strings.add(Utils.getString(singleRelation.getOrgans()
						.getName()));
			} else {
				strings.add("");
			}
			strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS,
					singleRelation.getStatus()));
			// strings.add(singleRelation.getCardBin().getBinName());
			strings.add(DateTimeTool.dateFormat("", singleRelation
					.getUpdateTime()));

			String operation = "";
			if (us.getUserLevel() == 0) {
				if (singleRelation.getStatus() != 9) {// 如果状态不为删除状态

					if (Utils.checkPermission("sy-1203-03")) {
						operation += "<a href=base/sinrelation!editUI?singleRelationDTO.sinRelId="
								+ singleRelation.getSinRelId()
								+ " title='修改'>"
								+ Globals.IMG_EDIT + "</a>&nbsp;";
					}
					if (Utils.checkPermission("sy-1203-04")) {
						operation += "<a href=javascript:deleteData('base/sinrelation!delete','"
								+ singleRelation.getSinRelId()
								+ "') title='删除'>"
								+ Globals.IMG_DELETE
								+ "</a>&nbsp;";
					}

				} else {
					if (Utils.checkPermission("sy-1203-01")) {
                         operation +="<a href=base/sinrelation!checkUI?singleRelationDTO.sinRelId=" +
                         		singleRelation.getSinRelId()+
                         		" title='修改内容查看'>"
                         		+Globals.IMG_VIEW+"</a>&nbsp;";
					}
				}
			}
			if (us.getUserLevel() == 1 || us.getUserLevel() == 2) {
				operation += "<a href=base/sinrelation!editUI?singleRelationDTO.sinRelId="
						+ singleRelation.getSinRelId()
						+ " title='查看'>"
						+ Globals.IMG_CHECK + "</a>&nbsp;";
			}
			strings.add(operation);
			lists.add(strings);
		}

		PageView pageView = new PageView(singleRelationDTO.getPage(), terResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * [添加]按钮访问方法：转到增加关系页面
	 * 
	 * @return
	 */
	public String addUI() {
		this.setMethod("addSave");		
		singleRelationDTO.setIsSalePointStr(Utils.getSelectHtml(
				OptionsValue.VISIBLE_STATUS, "singleRelationDTO.isSalePoints",
				"isSalePoints", false));
		this.getRequest().setAttribute("organsValues",organsService.getOption());// 机构
		this.getRequest().setAttribute("settlemetValues",OptionsValue.SETTLEMENT_STATUS);// 结算方式
		this.getRequest().setAttribute("settTypeValues",OptionsValue.SETTLE_COUNT_WAY);
		this.getRequest().setAttribute("areaValues", OptionsValue.AREA_VALUE);
		this.getRequest().setAttribute("convIsOrNot",OptionsValue.VISIBLE_STATUS_CONV);
		return INPUT;
	}

	/**
	 * 新增-[保存]按钮访问方法： 保存一条收单关系记录
	 * 
	 * @return String retString
	 */
	public String addSave() {

		if (!singleRelationService.checkExsisSinRel(singleRelationDTO)) {
			this.getRequest().setAttribute("result", "该收单关系已存在!");
			this.getRequest().setAttribute("url", "base/sinrelation!list");
			return ERROR;
		}
		if (!singleRelationService.checkExsisCardBin(singleRelationDTO
				.getOrganId())) {
			this.getRequest().setAttribute("result", "发卡机构无卡BIN，无需添加收单关系!");
			this.getRequest().setAttribute("url", "base/sinrelation!list");
			return ERROR;
		}
		try {
			@SuppressWarnings("unused")
			SingleRelation singleRelation = singleRelationService.saveSingleRelation(singleRelationDTO);
		} catch (Exception e) {
			singleRelLog.info("商户 编号:"+singleRelationDTO.getMerId()+
					"  与机构："+singleRelationDTO.getOrganName()+"收单关系维护保存异常!");
			e.printStackTrace();
		}
		functionsService.saveFunction("收单关系管理", 1, "增加收单关系："	+ singleRelationDTO.getSinRelId());
		this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url", "base/sinrelation!list");
		return SUCCESS;
	}

	/**
	 * [修改]按钮访问方法：转到修改关系页面
	 * 
	 * @return
	 */
	public String editUI() {
		this.setMethod("editSave");
		SingleRelation singleRelation = singleRelationService.find(singleRelationDTO.getSinRelId());
		HttpServletRequest request = this.getRequest();/*
														 * .setAttribute("settlemetValues"
														 * ,OptionsValue.
														 * SETTLEMENT_STATUS
														 * );//结算方式
														 */
		request.setAttribute("settlemetValues", OptionsValue.SETTLEMENT_STATUS);
		request.setAttribute("settTypeValues", OptionsValue.SETTLE_COUNT_WAY);
		this.getRequest().setAttribute("convIsOrNot",OptionsValue.VISIBLE_STATUS_CONV);
		/*
		 * this.getRequest().setAttribute("countSettTypeValues",
		 * OptionsValue.SETTLE_COUNT_WAY);
		 */// 计算结算金额方式
		if (singleRelation != null) {
			singleRelationDTO.setSinRelId(singleRelation.getSinRelId());
			singleRelationDTO.setMerName(singleRelation.getMerchants()
					.getMerName());
			singleRelationDTO.setOrganId(singleRelation.getOrgans()
					.getOrganId());
			singleRelationDTO
					.setOrganName(singleRelation.getOrgans().getName());
			singleRelationDTO
					.setMerId(singleRelation.getMerchants().getMerId());
			singleRelationDTO.setMehodOfSett(singleRelation.getMehodOfSett());
			singleRelationDTO.setRateFee(singleRelation.getRateFee());
			singleRelationDTO.setAsinTranRateFee(singleRelation
					.getAsinTranRateFee());
			singleRelationDTO.setFeeTopLimit(singleRelation.getFeeTopLimit());
			singleRelationDTO.setServPlatformRatio(singleRelation
					.getServPlatformRatio());
			singleRelationDTO.setAcquirerRate(singleRelation.getAcquirerRate());
			singleRelationDTO.setOrganRate(singleRelation.getOrganRate());
			singleRelationDTO.setCountSettType(singleRelation
					.getCountSettType());
		}
		//通过发卡机构编号，获取该机构下属所有卡BIN
		List<OptionsString> list = cardBinService.getOptionByOrganId(singleRelation.getOrgans().getOrganId());
		/** 允许消费的卡BIN信息 */
		List<SingleRelationDTO> cardBinDtos = new ArrayList<SingleRelationDTO>();
		
		/*for (OptionsString optionsString : list) {
			SingleRelationDTO cardBinDto = new SingleRelationDTO();
			cardBinDto.setCardBin(optionsString.getKey());
			cardBinDto.setCardName(optionsString.getValue());
			cardBinDto.setIsSalePoint(0);
			List<MerSinRelation> merSinRelList =
				   merSinService.queryMerBinByMerId(singleRelationDTO.getMerId(),optionsString.getKey());
			MerSinRelation merSinRelation = null;
			if (merSinRelList.size() > 0) {
				merSinRelation = merSinRelList.get(0);
				cardBinDto.setIsSalePoint(merSinRelation.getGivePointSign());
				cardBinDto.setCardBinStatus("1");
			}
			cardBinDtos.add(cardBinDto);
		}*/

		this.getRequest().setAttribute("cardBinDtos", cardBinDtos);
		return INPUT;
	}
	
	/**
	 *  针对删除的收单关系只能进行查看；
	 *@Title:checkUI
	 *@Description:TODO
	 *@param:
	 *@return:void
	 *@author:谢洪飞
	 *@thorws:
	 */
	public String checkUI(){
		this.setMethod("checkUI");
		SingleRelation singleRelation = singleRelationService
		.find(singleRelationDTO.getSinRelId());
		this.getRequest().setAttribute("settlemetValues", OptionsValue.SETTLEMENT_STATUS);
		this.getRequest().setAttribute("settTypeValues", OptionsValue.SETTLE_COUNT_WAY);
		singleRelationDTO = this.getSinDto(singleRelation);
		return INPUT;
	}

	/**
	 * 修改-[保存]按钮访问方法： 保存一条变更的收单关系记录
	 * 
	 * @return String string
	 */
	public String editSave() {
		try {
			ReturnDTO dto = singleRelationService
					.updateSingleRelation(singleRelationDTO);
			if (dto.getFlag()) {
				functionsService.saveFunction("收单关系管理", 2, "修改收单关系："
						+ singleRelationDTO.getSinRelId());
				this.getRequest().setAttribute("result",
						this.getText("operation.success.notice"));
				this.getRequest().setAttribute("url", "base/sinrelation!list");
				return SUCCESS;
			} else {
				this.getRequest().setAttribute("result", dto.getMsg());
				this.getRequest().setAttribute("url", "base/sinrelation!list");
				return ERROR;
			}
		} catch (Exception e) {
			singleRelLog.info("商户 ："+singleRelationDTO.getMerName()+"与机构："+singleRelationDTO.getOrganName()+"收单关系修改失败!");
			e.printStackTrace();
		}
		return ERROR;
	}

	/**
	 * [删除] - 操作
	 * 
	 * @return
	 */
	public String delete() {
		System.out.println(id);
		try {
			singleRelationService.deleteSingleRelation(this.getId());
			functionsService
					.saveFunction("收单关系管理", 3, "删除收单关系：" + this.getId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

	public SingleRelationDTO getSinDto(SingleRelation singleRelation){
		
		singleRelationDTO.setSinRelId(singleRelation.getSinRelId());
		singleRelationDTO.setMerName(singleRelation.getMerchants()
				.getMerName());
		singleRelationDTO.setOrganId(singleRelation.getOrgans()
				.getOrganId());
		singleRelationDTO
				.setOrganName(singleRelation.getOrgans().getName());
		singleRelationDTO
				.setMerId(singleRelation.getMerchants().getMerId());
		singleRelationDTO.setMehodOfSett(singleRelation.getMehodOfSett());
		singleRelationDTO.setRateFee(singleRelation.getRateFee());
		singleRelationDTO.setAsinTranRateFee(singleRelation
				.getAsinTranRateFee());
		singleRelationDTO.setFeeTopLimit(singleRelation.getFeeTopLimit());
		singleRelationDTO.setServPlatformRatio(singleRelation
				.getServPlatformRatio());
		singleRelationDTO.setAcquirerRate(singleRelation.getAcquirerRate());
		singleRelationDTO.setOrganRate(singleRelation.getOrganRate());
		singleRelationDTO.setCountSettType(singleRelation
				.getCountSettType());
		
		return singleRelationDTO;
		
	}
}
