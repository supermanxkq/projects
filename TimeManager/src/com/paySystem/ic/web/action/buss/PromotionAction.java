package com.paySystem.ic.web.action.buss;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.buss.MerApplyRecordService;
import com.paySystem.ic.service.buss.MerPromotionService;
import com.paySystem.ic.service.buss.PromotionService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.buss.MerApplyRecordDTO;
import com.paySystem.ic.web.dto.buss.MerPromotionDTO;
import com.paySystem.ic.web.dto.buss.PromotionDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 活动管理Action
 * 
 * @ClassName:PromotionAction
 * @Description:活动管理Action
 * @date: 2014-8-21 下午04:42:50
 * @author: 赵瑞群
 * @version: V1.0
 */
@Controller("/buss/promotion")
@Scope("prototype")
public class PromotionAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource
	PromotionService promotionService;
	@Resource
	MerPromotionService merPromotionService;
	@Resource
	MerApplyRecordService merApplyRecordService;
	@Resource
	OrgansService organsService;
	@Resource
	FunctionsService functionsService;

	private PromotionDTO promotionDTO = new PromotionDTO();

	public PromotionDTO getPromotionDTO() {
		return promotionDTO;
	}

	public void setPromotionDTO(PromotionDTO promotionDTO) {
		this.promotionDTO = promotionDTO;
	}

	private MerPromotionDTO merPromotionDTO = new MerPromotionDTO();

	public MerPromotionDTO getMerPromotionDTO() {
		return merPromotionDTO;
	}

	public void setMerPromotionDTO(MerPromotionDTO merPromotionDTO) {
		this.merPromotionDTO = merPromotionDTO;
	}

	private MerApplyRecordDTO merApplyRecordDTO = new MerApplyRecordDTO();

	public MerApplyRecordDTO getMerApplyRecordDTO() {
		return merApplyRecordDTO;
	}

	public void setMerApplyRecordDTO(MerApplyRecordDTO merApplyRecordDTO) {
		this.merApplyRecordDTO = merApplyRecordDTO;
	}

	/**
	 * 活动管理菜单访问方法
	 * 
	 * @Title: list
	 * @Description: 活动管理菜单访问默认方法， 方法中对列表界面所需数据进行准备
	 * @Param: @return
	 * @Return: String 跳转参数标识
	 * @Author: 赵瑞群
	 * @Thorws:
	 */
	public String list() {

		/** 获取当前操作员信息 */
		UserSession us = Utils.getUserSession();

		/**
		 * 根据操作员不同级别进行界面跳转
		 * 
		 * us.getUserLevel 0 : 平台操作员 2 : 商户操作员 操作员为平台操作员，进行界面正常跳转；
		 * 操作员为机构或商户操作员，进行拦截提示其不拥有该权限；
		 */
		/**
		 * switch (us.getUserLevel()) { case 0: break; case 1: return
		 * "intercepthtml"; case 2: break; }
		 **/
		this.getRequest().setAttribute("status", OptionsValue.PROMOTION_STATUS);
		this.getRequest().setAttribute("proType",
				OptionsValue.PROMOTION_PROTYPE);
		this.getRequest().setAttribute("proItem",
				OptionsValue.PROMOTION_PROITEM);
		this.getRequest().setAttribute("hostSign",
				OptionsValue.PROMOTION_HOSTSIGN);
		this.getRequest().setAttribute("rateSign",
				OptionsValue.VISIBLE_STATUS_CONV);
		this.getRequest().setAttribute("userLevel", us.getUserLevel());

		return "list";
	}

	/**
	 * 
	 * @Title:checkRecordList
	 * @Description:进入对参与活动的商户进行审核的界面
	 * @Params:@return
	 * @Return:String
	 * @author:luckygroup
	 * @Date:2014-8-26下午11:00:13
	 */

	public String checkRecordList() {

		/** 获取当前操作员信息 */
		UserSession us = Utils.getUserSession();

		merApplyRecordDTO.setStatus("1");

		promotionDTO = promotionService.findById(promotionDTO.getProid());
		promotionDTO.setStatus(Utils.getOptionsIntegerName(
				OptionsValue.PROMOTION_STATUS,
				Integer.parseInt(promotionDTO.getStatus())));
		return "checkRecordList";
	}

	/**
	 * 
	 * @Title:checkRecordList
	 * @Description:进入对参与活动的商户进行维护的界面
	 * @Params:@return
	 * @Return:String
	 * @author:luckygroup
	 * @Date:2014-8-26下午11:00:13
	 */

	public String editApplyMer() {

		/** 获取当前操作员信息 */
		UserSession us = Utils.getUserSession();

		merApplyRecordDTO.setStatus("2");

		promotionDTO = promotionService.findById(promotionDTO.getProid());
		promotionDTO.setStatus(Utils.getOptionsIntegerName(
				OptionsValue.PROMOTION_STATUS,
				Integer.parseInt(promotionDTO.getStatus())));
		return "checkRecordList";
	}

	/**
	 * 列表查询
	 * 
	 * @Title:jsonPageList
	 * @Description: 根据列表界面条件对数据进行分页查询
	 * @param:@return
	 * @param:@throws Exception
	 * @return:String
	 * @author: 赵瑞群
	 * @Thorws:
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();

		/** 封装排序方式参数，如界面传递排序方式则 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {

			orderby.put(this.getOrderProperty(), this.getOrderDirection());

		} else {
			orderby.put("updateTime", "desc");
		}

		/** 调用活动管理Service，根据条件查询活动管理信息列表及分页信息 */
		QueryResult<PromotionDTO> dtoResult = promotionService
				.queryPromotionByCond((promotionDTO.getPage() - 1) * pageNum,
						pageNum, promotionDTO, orderby);

		/** 获取活动管理DTO对象集合 */
		List<PromotionDTO> promotionList = dtoResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();

		UserSession us = Utils.getUserSession();
		int userLevel = us.getUserLevel();

		this.getRequest().setAttribute("userLevel", us.getUserLevel());

		/** 遍历DTO对象集合并生成要输出到界面的信息 */
		for (int i = 0; i < promotionList.size(); i++) {

			List<String> strings = new ArrayList<String>();

			PromotionDTO promotionDTO = promotionList.get(i);

			strings.add(String.valueOf(i + 1));
			strings.add(promotionDTO.getProname());
			if (promotionDTO.getHostSign().equals("0")) {
				strings.add(Utils.getOptionsIntegerName(
						OptionsValue.PROMOTION_STATUS,
						Integer.parseInt(promotionDTO.getStatus())));
			} else if (promotionDTO.getHostSign().equals("1")) {
				strings.add(Utils.getOptionsIntegerName(
						OptionsValue.PROMOTION_MERSTATUS,
						Integer.parseInt(promotionDTO.getStatus())));
			}

			strings.add(promotionDTO.getProHost());

			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.PROMOTION_PROTYPE,
					Integer.parseInt(promotionDTO.getProType())));

			strings.add(Utils.getOptionsIntegerName(
					OptionsValue.PROMOTION_HOSTSIGN,
					Integer.parseInt(promotionDTO.getHostSign())));

			/** 查询参与活动商户的个数 */
			String merCount = promotionService.findCountMerByProId(promotionDTO
					.getProid()) + "";

			/** 查询参与活动商品的个数 */
			String goodsCount = promotionService
					.findCountGoodsByProId(promotionDTO.getProid()) + "";
			strings.add(merCount);
			strings.add(goodsCount);
			strings.add(DateTimeTool.dateFormat("", promotionDTO.getBeginTime()));

			strings.add(DateTimeTool.dateFormat("", promotionDTO.getEndTime()));

			String operation = "";

			MerPromotionDTO merPtomotionIs = merPromotionService
					.findByProIdAndMerId(promotionDTO.getProid(), us.getMerId());

			if (userLevel == 0 && promotionDTO.getHostSign().equals("0")
					&& promotionDTO.getStatus().equals("1")) { // 平台看平台活动未招募
				
				if (Utils.checkPermission("sy-8101-01")) {
					operation += "<a href=buss/promotion!checkUI?promotionDTO.proid="
							+ promotionDTO.getProid()
							+ " title='查看'>查看 </a>&nbsp;";
				}

				if (Utils.checkPermission("sy-8101-05")) {
					operation += "<a href=javascript:openRecruit('buss/promotion!openRecruit','"
							+ promotionDTO.getProid()
							+ "') title='开始招募'>开始招募</a>&nbsp;";

				}

				if (Utils.checkPermission("sy-8101-03")) {

					operation += "<a href=buss/promotion!editUI?promotionDTO.proid="
							+ promotionDTO.getProid()
							+ " title='修改'>修改</a>&nbsp;";
				}
				
				if (Utils.checkPermission("sy-8101-04")) {
					operation += "<a href=javascript:deleteData('buss/promotion!delete','"
							+ promotionDTO.getProid()
							+ "') title='删除'>删除</a>&nbsp;";
				}

			} else if (userLevel == 0 && promotionDTO.getHostSign().equals("0")
					&& promotionDTO.getStatus().equals("2")) { // 平台看平台活动招募中

				if (Utils.checkPermission("sy-8101-01")) {
					operation += "<a href=buss/promotion!checkUI?promotionDTO.proid="
							+ promotionDTO.getProid()
							+ " title='查看'>查看 </a>&nbsp;";
				}
				
				if (Utils.checkPermission("sy-8101-03")) {

					operation += "<a href=buss/promotion!editRecruitUI?promotionDTO.proid="
							+ promotionDTO.getProid()
							+ " title='修改'>修改</a>&nbsp;";
				}

				if (Utils.checkPermission("sy-8101-06")) {
					operation += "<a href=javascript:stopRecruit('buss/promotion!stopRecruit','"
							+ promotionDTO.getProid()
							+ "') title='停止招募'>停止招募</a>&nbsp;";
				}

				if (Utils.checkPermission("sy-8101-07")) {
					operation += "<a href=buss/promotion!checkRecordList?promotionDTO.proid="
							+ promotionDTO.getProid()
							+ " title='审核商户'>审核商户 </a>&nbsp;";
				}

			} else if (userLevel == 0 && promotionDTO.getHostSign().equals("0")
					&& promotionDTO.getStatus().equals("3")) {// 平台看平台活动未开始

				if (Utils.checkPermission("sy-8101-01")) {
					operation += "<a href=buss/promotion!checkUI?promotionDTO.proid="
							+ promotionDTO.getProid()
							+ " title='查看'>查看 </a>&nbsp;";
				}

				if (Utils.checkPermission("sy-8101-08")) {
					operation += "<a href=buss/promotion!editApplyMer?promotionDTO.proid="
							+ promotionDTO.getProid()
							+ " title='维护参与商户'>维护参与商户 </a>&nbsp;";
				}

			} else if (userLevel == 0 && promotionDTO.getHostSign().equals("0")
					&& promotionDTO.getStatus().equals("4")) {// 平台看平台活动进行中

				if (Utils.checkPermission("sy-8101-01")) {
					operation += "<a href=buss/promotion!checkUI?promotionDTO.proid="
							+ promotionDTO.getProid()
							+ " title='查看'>查看 </a>&nbsp;";
				}

				if (Utils.checkPermission("sy-8101-09")) {
					operation += "<a href=javascript:stopPromotion('buss/promotion!stopPromotion','"
							+ promotionDTO.getProid()
							+ "') title='终止活动'>终止活动</a>&nbsp;";
				}

				if (Utils.checkPermission("sy-8101-08")) {
					operation += "<a href=buss/promotion!editMer?promotionDTO.proid="
							+ promotionDTO.getProid()
							+ " title='维护参与商户'>维护参与商户 </a>&nbsp;";
				}

				if (Utils.checkPermission("sy-8101-10")) {
					operation += "<a href=buss/promotion!editEndTime?promotionDTO.proid="
							+ promotionDTO.getProid()
							+ " title='修改结束时间'>修改结束时间 </a>&nbsp;";
				}

			} else if (userLevel == 0 && promotionDTO.getHostSign().equals("1")
					&& promotionDTO.getStatus().equals("4")) {// 平台看商户活动活动中

				if (Utils.checkPermission("sy-8101-01")) {
					operation += "<a href=buss/promotion!checkUI?promotionDTO.proid="
							+ promotionDTO.getProid()
							+ " title='查看'>查看 </a>&nbsp;";
				}

				if (Utils.checkPermission("sy-8101-09")) {
					operation += "<a href=javascript:stopPromotion('buss/promotion!stopPromotion','"
							+ promotionDTO.getProid()
							+ "') title='终止活动'>终止活动</a>&nbsp;";
				}

			} else if (merPtomotionIs == null && userLevel == 2
					&& promotionDTO.getHostSign().equals("0")
					&& promotionDTO.getStatus().equals("2")) {// 未参加商户看平台活动招募中

				if (Utils.checkPermission("sy-8101-11")) {
					operation += "<a href=buss/promotion!applyPromotion?promotionDTO.proid="
							+ promotionDTO.getProid()
							+ " title='报名参与'>报名参与 </a>&nbsp;";
				}

				if (Utils.checkPermission("sy-8101-01")) {
					operation += "<a href=buss/promotion!checkUI?promotionDTO.proid="
							+ promotionDTO.getProid()
							+ " title='查看'>查看 </a>&nbsp;";
				}

			} else if (merPtomotionIs != null && userLevel == 2
					&& promotionDTO.getHostSign().equals("0")
					&& !promotionDTO.getStatus().equals("1")) {// 参加商户看平台活动招募中

				if (Utils.checkPermission("sy-8101-12")) {
					operation += "<a href=buss/promotion!applyMerCheckUI?promotionDTO.proid="
							+ promotionDTO.getProid()
							+ " title='查看结果'>查看结果 </a>&nbsp;";
				}

			} else if (userLevel == 2 && promotionDTO.getHostSign().equals("1")
					&& promotionDTO.getStatus().equals("3")) {// 商户看商户活动未开始

				if (Utils.checkPermission("sy-8101-01")) {
					operation += "<a href=buss/promotion!checkUI?promotionDTO.proid="
							+ promotionDTO.getProid()
							+ " title='查看'>查看 </a>&nbsp;";
				}

				if (Utils.checkPermission("sy-8101-13")) {
					operation += "<a href=javascript:beginPromotion('buss/promotion!beginPromotion','"
							+ promotionDTO.getProid()
							+ "') title='开始活动'>开始活动</a>&nbsp;";
				}

				if (Utils.checkPermission("sy-8101-03")) {
					operation += "<a href=buss/promotion!editUI?promotionDTO.proid="
							+ promotionDTO.getProid()
							+ " title='修改'>修改</a>&nbsp;";
				}

				if (Utils.checkPermission("sy-8101-04")) {
					operation += "<a href=javascript:deleteData('buss/promotion!delete','"
							+ promotionDTO.getProid()
							+ "') title='删除'>删除</a>&nbsp;";
				}

			} else if (userLevel == 2 && promotionDTO.getHostSign().equals("1")
					&& promotionDTO.getStatus().equals("4")) {// 商户看商户活动活动中

				if (Utils.checkPermission("sy-8101-09")) {
					operation += "<a href=javascript:stopPromotion('buss/promotion!stopPromotion','"
							+ promotionDTO.getProid()
							+ "') title='终止活动'>终止活动</a>&nbsp;";
				}

				if (Utils.checkPermission("sy-8101-01")) {
					operation += "<a href=buss/promotion!checkUI?promotionDTO.proid="
							+ promotionDTO.getProid()
							+ " title='查看'>查看 </a>&nbsp;";
				}

			} else {

				if (Utils.checkPermission("sy-8101-01")) {
					operation += "<a href=buss/promotion!checkUI?promotionDTO.proid="
							+ promotionDTO.getProid()
							+ " title='查看'>查看 </a>&nbsp;";
				}
			}

			strings.add(operation);
			lists.add(strings);

		}

		PageView pageView = new PageView(promotionDTO.getPage(),
				dtoResult.getTotalrecord());

		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		// 向前台输出查询数据结果集字符串
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * 列表查询
	 * 
	 * @Title:jsonPageRecordList
	 * @Description: 根据列表界面条件对数据进行分页查询
	 * @param:@return
	 * @param:@throws Exception
	 * @return:String
	 * @author: 赵瑞群
	 * @Thorws:
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageRecordList() throws Exception {

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();

		/** 调用活动管理Service，根据条件查询活动管理信息列表及分页信息 */
		QueryResult<List> dtoResult = promotionService.queryApplyRecordByCond(
				(merApplyRecordDTO.getPage() - 1) * pageNum, pageNum,
				merApplyRecordDTO, orderby);

		/** 获取活动管理DTO对象集合 */
		List recordList = dtoResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();

		for (int i = 0; i < recordList.size(); i++) {
			List<String> strings = new ArrayList<String>();
			Object[] o = (Object[]) recordList.get(i);
			String merName = (String) o[0];
			int recordId = (Integer) o[1];
			String status = (String) o[2];
			int quantity = 0;
			if(o[3]!=null){
				quantity = (Integer) o[3];
			}
			String merId = (String) o[4];

			strings.add("<input type='checkbox' value='" + recordId + "'>");
			strings.add("<a  target='_blank' href=base/merchants!checkUI?merchantsDTO.merId="
					+ merId
					+ " title='查看'>"
					+ merName + "</a>&nbsp;");
			String statusStr = "未审核";
			if (status.equals("1")) {
				statusStr = "未审核";
			} else if (status.equals("2")) {
				statusStr = "审核通过";
			} else if (status.equals("3")) {
				statusStr = "审核不通过";
			} else if (status.equals("9")) {
				statusStr = "清退";

			}
			strings.add(statusStr);
			strings.add("");
			strings.add("");
			strings.add("");

			String operation = "";
			if (status.equals("1")) {
				operation += "<a href=javascript:openRefuseWin('" + recordId
						+ "')" + " title='审核拒绝'>审核拒绝" + "</a>&nbsp;";
			}

			if (status.equals("2")) {
				operation += "<a href=javascript:openRefuseWin('" + recordId
						+ "')" + " title='清退活动'>清退活动" + "</a>&nbsp;";
			}
           if(quantity==0){
        	   strings.add("");
           }else{
			 strings.add(quantity + "");
           }
			strings.add(operation);
			lists.add(strings);

		}
		PageView pageView = new PageView(merApplyRecordDTO.getPage(),
				dtoResult.getTotalrecord());

		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		/** 向前台输出查询数据结果集字符串 */
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * 新增活动管理界面跳转请求方法
	 * 
	 * 1.返回跳转信息 2.为添加界面准备数据
	 * 
	 * @Title:addUI
	 * @Description:列表界面中"添加"按钮出发方法
	 * @param:@return
	 * @return:String
	 * @author: 赵瑞群
	 * @Thorws:
	 */
	public String addUI() {

		this.setMethod("addSave");

		UserSession us = Utils.getUserSession();

		promotionDTO.setOperatorId(us.getUserName());
		merPromotionDTO.setOperatorId(us.getUserName());

		if (us.getUserLevel() == 0) {
			this.getRequest().setAttribute("status",
					OptionsValue.PROMOTION_ADDSTATUS);
			promotionDTO.setStatus("1");
			promotionDTO.setHostSign("0");
			promotionDTO.setProHost(us.getOrganName());
		} else if (us.getUserLevel() == 2) {
			this.getRequest().setAttribute("status",
					OptionsValue.PROMOTION_ADDMERSTATUS);
			promotionDTO.setStatus("3");
			promotionDTO.setProHost(us.getMerName());
			promotionDTO.setHostSign("1");
		}
		this.getRequest().setAttribute("proType",
				OptionsValue.PROMOTION_PROTYPE);
		this.getRequest().setAttribute("proItem",
				OptionsValue.PROMOTION_PROITEM);
		this.getRequest().setAttribute("hostSign",
				OptionsValue.PROMOTION_HOSTSIGN);
		this.getRequest().setAttribute("rateSign",
				OptionsValue.VISIBLE_STATUS_CONV);
		merPromotionDTO.setRateSign("1");

		return INPUT;
	}

	/**
	 * 新增保存
	 * 
	 * 1.保存活动管理信息 2.保存操作日志信息
	 * 
	 * @Title:addSave
	 * @Description:保存活动管理新增内容
	 * @param:@return
	 * @return:String
	 * @author: 赵瑞群
	 * @Thorws:
	 */
	public String addSave() throws Exception {

		try {

			/** 1.保存活动管理信息 */
			promotionService.savePromotion(promotionDTO, merPromotionDTO);

			/** 2.保存操作日志信息 */
			functionsService.saveFunction("活动管理管理", 1, "增加活动管理信息：");

			/** 保存成功提示内容 */
			this.getRequest().setAttribute("result",
					this.getText("operation.success.notice"));
			/** 保存成功后跳转界面 */
			this.getRequest().setAttribute("url", "buss/promotion!list");

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().setAttribute("result", "保存活动管理出错啦!");
			this.getRequest().setAttribute("url", "buss/promotion!list");
			return ERROR;
		}
	}

	/**
	 * 修改界面跳转
	 * 
	 * @Title:editUI
	 * @Description:跳转到活动管理修改界面
	 * @param:@return
	 * @return:String
	 * @author: 赵瑞群
	 * @Thorws:
	 */

	public String editUI() {
		try {
			this.setMethod("editSave");
			UserSession us = Utils.getUserSession();

			this.getRequest().setAttribute("proType",
					OptionsValue.PROMOTION_PROTYPE);
			this.getRequest().setAttribute("proItem",
					OptionsValue.PROMOTION_PROITEM);
			this.getRequest().setAttribute("rateSign",
					OptionsValue.VISIBLE_STATUS_CONV);
			promotionDTO = promotionService.findById(promotionDTO.getProid());

			if (us.getUserLevel() == 0) {
				this.getRequest().setAttribute("status",
						OptionsValue.PROMOTION_STATUS);
			} else if (us.getUserLevel() == 2) {
				this.getRequest().setAttribute("status",
						OptionsValue.PROMOTION_MERSTATUS);
				merPromotionDTO = merPromotionService.findByProIdAndMerId(
						promotionDTO.getProid(), us.getMerId());
			}

			return INPUT;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	
	/**
	 * 修改招募中的活动跳转页面
	 * 
	 * @Title:editUI
	 * @Description:跳转到活动管理修改界面
	 * @param:@return
	 * @return:String
	 * @author: 赵瑞群
	 * @Thorws:
	 */

	public String editRecruitUI() {
		try {
			this.setMethod("editRecruitSave");
			UserSession us = Utils.getUserSession();

			this.getRequest().setAttribute("proType",
					OptionsValue.PROMOTION_PROTYPE);
			this.getRequest().setAttribute("proItem",
					OptionsValue.PROMOTION_PROITEM);
			this.getRequest().setAttribute("rateSign",
					OptionsValue.VISIBLE_STATUS_CONV);
			promotionDTO = promotionService.findById(promotionDTO.getProid());

			if (us.getUserLevel() == 0) {
				this.getRequest().setAttribute("status",
						OptionsValue.PROMOTION_STATUS);
			} else if (us.getUserLevel() == 2) {
				this.getRequest().setAttribute("status",
						OptionsValue.PROMOTION_MERSTATUS);
				merPromotionDTO = merPromotionService.findByProIdAndMerId(
						promotionDTO.getProid(), us.getMerId());
			}

			return INPUT;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 修改结束时间
	 * 
	 * @Title:editUI
	 * @Description:跳转到活动修改结束时间界面
	 * @param:@return
	 * @return:String
	 * @author: 赵瑞群
	 * @Thorws:
	 */

	public String editEndTime() {
		try {
			this.setMethod("editEndTimeSave");
			UserSession us = Utils.getUserSession();

			this.getRequest().setAttribute("proType",
					OptionsValue.PROMOTION_PROTYPE);
			this.getRequest().setAttribute("proItem",
					OptionsValue.PROMOTION_PROITEM);
			this.getRequest().setAttribute("rateSign",
					OptionsValue.VISIBLE_STATUS_CONV);
			promotionDTO = promotionService.findById(promotionDTO.getProid());

			if (us.getUserLevel() == 0) {
				this.getRequest().setAttribute("status",
						OptionsValue.PROMOTION_STATUS);
			} else if (us.getUserLevel() == 2) {
				this.getRequest().setAttribute("status",
						OptionsValue.PROMOTION_MERSTATUS);
				merPromotionDTO = merPromotionService.findByProIdAndMerId(
						promotionDTO.getProid(), us.getMerId());
			}

			return INPUT;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 修改活动结束时间保存方法
	 * 
	 * @Title:editSave
	 * @Description:保存活动管理信息修改内容
	 * @param:@return
	 * @return:String
	 * @author: 赵瑞群
	 * @Thorws:
	 */

	public String editEndTimeSave() {

		try {
			// merPromotionDTO.setOperatorId(promotionDTO.getOperatorId());
			promotionService.updateEndTime(promotionDTO);
			functionsService.saveFunction("活动管理管理", 1, "修改活动结束时间：");

			this.getRequest().setAttribute("result",
					this.getText("operation.success.notice"));
			this.getRequest().setAttribute("url", "buss/promotion!list");

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().setAttribute("result", "保存活动管理信息出错啦!");
			this.getRequest().setAttribute("url", "buss/promotion!list");
			return ERROR;
		}

	}

	/**
	 * 
	 * @Title:applyPromotion
	 * @Description:商户申请活动
	 * @Params:@return
	 * @Return:String
	 * @author:luckygroup
	 * @Date:2014-8-26下午10:57:21
	 */

	public String applyPromotion() {
		try {
			this.setMethod("applySave");
			UserSession us = Utils.getUserSession();

			this.getRequest().setAttribute("proType",
					OptionsValue.PROMOTION_PROTYPE);
			this.getRequest().setAttribute("proItem",
					OptionsValue.PROMOTION_PROITEM);
			this.getRequest().setAttribute("rateSign",
					OptionsValue.VISIBLE_STATUS_CONV);
			promotionDTO = promotionService.findById(promotionDTO.getProid());
			merPromotionDTO.setRateSign("1");

			this.getRequest().setAttribute("status",
					OptionsValue.PROMOTION_STATUS);

			return INPUT;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 
	 * @Title:applySave
	 * @Description:保存参加活动商户信息
	 * @Params:@return
	 * @Params:@throws Exception
	 * @Return:String
	 * @author:luckygroup
	 * @Date:2014-8-24下午11:19:32
	 */
	public String applySave() throws Exception {

		try {

			UserSession us = Utils.getUserSession();

			merPromotionDTO.setOperatorId(us.getUserName());
			merApplyRecordDTO.setOperatorId(us.getUserName());
			merPromotionDTO.setProid(promotionDTO.getProid());
			merApplyRecordDTO.setProid(promotionDTO.getProid());
			merPromotionDTO.setMerid(us.getMerId());
			merApplyRecordDTO.setMerid(us.getMerId());
			merApplyRecordDTO.setStatus("1");

			/** 1.保存活动管理信息 */
			promotionService.saveApplyMer(merPromotionDTO, merApplyRecordDTO);

			/** 2.保存操作日志信息 */
			functionsService.saveFunction("活动管理管理", 1, "增加参加活动商户信息：");

			/** 保存成功提示内容 */
			this.getRequest().setAttribute("result",
					this.getText("operation.success.notice"));
			/** 保存成功后跳转界面 */
			this.getRequest().setAttribute("url", "buss/promotion!list");

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().setAttribute("result", "保存活动管理出错啦!");
			this.getRequest().setAttribute("url", "buss/promotion!list");
			return ERROR;
		}
	}

	/**
	 * 修改保存方法
	 * 
	 * @Title:editSave
	 * @Description:保存活动管理信息修改内容
	 * @param:@return
	 * @return:String
	 * @author: 赵瑞群
	 * @Thorws:
	 */

	public String editSave() {

		try {
			merPromotionDTO.setOperatorId(promotionDTO.getOperatorId());
			promotionService.savePromotion(promotionDTO, merPromotionDTO);
			functionsService.saveFunction("活动管理管理", 1, "修改活动管理信息：");

			this.getRequest().setAttribute("result",
					this.getText("operation.success.notice"));
			this.getRequest().setAttribute("url", "buss/promotion!list");

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().setAttribute("result", "保存活动管理信息出错啦!");
			this.getRequest().setAttribute("url", "buss/promotion!list");
			return ERROR;
		}

	}
	
	/**
	 * 修改招募中的活动保存方法
	 * 
	 * @Title:editSave
	 * @Description:保存活动管理信息修改内容
	 * @param:@return
	 * @return:String
	 * @author: 赵瑞群
	 * @Thorws:
	 */

	public String editRecruitSave() {

		try {
			merPromotionDTO.setOperatorId(promotionDTO.getOperatorId());
			promotionService.savePromotion(promotionDTO, merPromotionDTO);
			functionsService.saveFunction("活动管理管理", 1, "修改活动管理信息：");

			this.getRequest().setAttribute("result",
					this.getText("operation.success.notice"));
			this.getRequest().setAttribute("url", "buss/promotion!list");

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().setAttribute("result", "保存活动管理信息出错啦!");
			this.getRequest().setAttribute("url", "buss/promotion!list");
			return ERROR;
		}

	}

	/**
	 * 删除活动信息
	 * 
	 * @Title:delete
	 * @Description:删除信息
	 * @param:@return
	 * @return:String
	 * @author: 赵瑞群
	 * @Thorws:
	 */

	public String delete() {

		try {
			PromotionDTO promotionDTO = promotionService.delPromotion(Integer
					.parseInt(this.id));

			functionsService.saveFunction("活动管理管理", 3,
					"删除活动管理：" + promotionDTO.getProid());

			this.ajaxResult = "ajaxsuccess";

		} catch (Exception e) {

			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}

	/**
	 * 
	 * @Title:openRecruit
	 * @Description:开始招募
	 * @Params:@return
	 * @Return:String
	 * @author:luckygroup
	 * @Date:2014-8-24下午5:44:16
	 */

	public String openRecruit() {

		try {
			PromotionDTO promotionDTO = promotionService.openRecruit(Integer
					.parseInt(this.id));

			functionsService.saveFunction("活动管理", 3,
					"开始招募：" + promotionDTO.getProid());

			this.ajaxResult = "ajaxsuccess";

		} catch (Exception e) {

			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}

	/**
	 * 
	 * @Title:openRecruit
	 * @Description:停止招募
	 * @Params:@return
	 * @Return:String
	 * @author:luckygroup
	 * @Date:2014-8-24下午5:44:16
	 */

	public String stopRecruit() {

		try {

			PromotionDTO promotionDTO = promotionService.stopRecruit(Integer
					.parseInt(this.id));

			functionsService.saveFunction("活动管理", 3,
					"停止招募：" + promotionDTO.getProid());

			this.ajaxResult = "ajaxsuccess";

		} catch (Exception e) {

			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}

	/**
	 * 
	 * @Title:openRecruit
	 * @Description:终止活动
	 * @Params:@return
	 * @Return:String
	 * @author:luckygroup
	 * @Date:2014-8-24下午5:44:16
	 */

	public String stopPromotion() {

		try {

			PromotionDTO promotionDTO = promotionService.stopPromotion(Integer
					.parseInt(this.id));

			functionsService.saveFunction("活动管理", 3,
					"终止活动：" + promotionDTO.getProid());

			this.ajaxResult = "ajaxsuccess";

		} catch (Exception e) {

			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}

	/**
	 * 
	 * @Title:openRecruit
	 * @Description:开始活动
	 * @Params:@return
	 * @Return:String
	 * @author:luckygroup
	 * @Date:2014-8-24下午5:44:16
	 */

	public String beginPromotion() {

		try {

			PromotionDTO promotionDTO = promotionService.beginPromotion(Integer
					.parseInt(this.id));

			functionsService.saveFunction("活动管理", 3,
					"开始活动：" + promotionDTO.getProid());

			this.ajaxResult = "ajaxsuccess";

		} catch (Exception e) {

			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}

	/**
	 * 
	 * @Title:openRecruit
	 * @Description:审核通过
	 * @Params:@return
	 * @Return:String
	 * @author:luckygroup
	 * @Date:2014-8-24下午5:44:16
	 */

	public String passAudit() {

		try {

			merApplyRecordService.passAudit(this.ids);

			functionsService.saveFunction("活动审核", 3, "审核通过：" + this.ids);

			this.ajaxResult = "ajaxsuccess";

		} catch (Exception e) {

			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}

	/**
	 * 
	 * @Title:openRecruit
	 * @Description:拒绝（清退）通过
	 * @Params:@return
	 * @Return:String
	 * @author:luckygroup
	 * @Date:2014-8-24下午5:44:16
	 */

	public String refuseAudit() {

		try {

			merApplyRecordService.refuseAudit(this.ids, merApplyRecordDTO);

			functionsService.saveFunction("活动审核", 3, "活动拒绝（清退）：" + this.ids);

			this.ajaxResult = "ajaxsuccess";

		} catch (Exception e) {

			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}

	/**
	 * 
	 * @Title:openRecruit
	 * @Description:审核通过
	 * @Params:@return
	 * @Return:String
	 * @author:luckygroup
	 * @Date:2014-8-24下午5:44:16
	 */

	public String passAllAudit() {

		try {

			merApplyRecordService.passAllAudit(Integer.parseInt(this.id));

			functionsService.saveFunction("活动审核", 3, "全部审核通过：" + this.id);

			this.ajaxResult = "ajaxsuccess";

		} catch (Exception e) {

			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}

	/**
	 * 查看跳转请求方法
	 * 
	 * @Title: applyMerCheckUI
	 * @Description: 商户查看参加平台活动的申请结果
	 * @Param: @return
	 * @Return: String
	 * @Author: 赵瑞群
	 * @Thorws:
	 */

	public String applyMerCheckUI() {

		this.setMethod("applyMerCheckUI");

		UserSession us = Utils.getUserSession();

		this.getRequest().setAttribute("proType",
				OptionsValue.PROMOTION_PROTYPE);
		this.getRequest().setAttribute("proItem",
				OptionsValue.PROMOTION_PROITEM);
		this.getRequest().setAttribute("rateSign",
				OptionsValue.VISIBLE_STATUS_CONV);
		promotionDTO = promotionService.findById(promotionDTO.getProid());
		try {

			merPromotionDTO = merPromotionService.findByProIdAndMerId(
					promotionDTO.getProid(), us.getMerId());

			merApplyRecordDTO = merApplyRecordService.findByProIdAndMerIdNew(
					promotionDTO.getProid(), us.getMerId());

			merApplyRecordDTO.setStatus(Utils.getOptionsIntegerName(
					OptionsValue.PROMOTION_MERAPPLYSTATUS,
					Integer.parseInt(merApplyRecordDTO.getStatus())));

			if (promotionDTO.getHostSign().equals("0")) {
				this.getRequest().setAttribute("status",
						OptionsValue.PROMOTION_STATUS);
			} else {
				this.getRequest().setAttribute("status",
						OptionsValue.PROMOTION_MERSTATUS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return INPUT;
	}

	/**
	 * 查看跳转请求方法
	 * 
	 * @Title: checkUI
	 * @Description: 平台查看平台活动
	 * @Param: @return
	 * @Return: String
	 * @Author: 赵瑞群
	 * @Thorws:
	 */

	public String checkUI() {

		this.setMethod("checkUI");

		UserSession us = Utils.getUserSession();

		this.getRequest().setAttribute("proType",
				OptionsValue.PROMOTION_PROTYPE);
		this.getRequest().setAttribute("proItem",
				OptionsValue.PROMOTION_PROITEM);
		this.getRequest().setAttribute("rateSign",
				OptionsValue.VISIBLE_STATUS_CONV);
		promotionDTO = promotionService.findById(promotionDTO.getProid());

		if (promotionDTO.getHostSign().equals("1")) {
			merPromotionDTO = merPromotionService.findByProId(promotionDTO
					.getProid());
		}

		if (promotionDTO.getHostSign().equals("0")) {
			this.getRequest().setAttribute("status",
					OptionsValue.PROMOTION_STATUS);
		} else {
			this.getRequest().setAttribute("status",
					OptionsValue.PROMOTION_MERSTATUS);
		}

		return INPUT;
	}

	/**
	 * 检查活动管理名称是否已存在
	 * 
	 * @Title:checkSameName
	 * @Description:检查活动管理名称是否已存在
	 * @param:@return
	 * @return:String 返回是否已存在状态
	 * @author: 赵瑞群
	 * @Thorws:
	 */

	public String checkSameName() {

		ReturnDTO data = new ReturnDTO();

		boolean flag = promotionService
				.checkSameName(promotionDTO.getProname());

		data.setFlag(flag);

		return Utils.printInfo(data);
	}

}
