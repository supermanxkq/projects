package com.paySystem.ic.web.action.order;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.goods.VioRegulService;
import com.paySystem.ic.service.goods.impl.VioRegulServiceImpl;
import com.paySystem.ic.service.order.ComplaintFiledService;
import com.paySystem.ic.service.order.impl.ComplaintFileServiceImpl;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.order.ComplaintFiledDTO;
import com.paySystem.ic.web.dto.order.ComplaintHandleDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ProjectName:omallBackstage
 * @ClassName:ComplaintFiledAction
 * @Description:投诉后台处理Action
 * @date: 2014-11-19
 * @author: 孟凡岭
 * @version: V1.0
 */
@Controller("/complaintFiled/complaintFiled")
@Scope("prototype")
public class ComplaintFiledAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private ComplaintFiledService complaintFiledService = new ComplaintFileServiceImpl();
	/** 投诉申请DTO **/
	private ComplaintFiledDTO complaintFiledDTO = new ComplaintFiledDTO();
	/** 投诉处理DTO **/
	private ComplaintHandleDTO complaintHandleDTO = new ComplaintHandleDTO();
	@Resource
	private VioRegulService vioRegulService = new VioRegulServiceImpl();
	@Resource
	private FunctionsService functionsService;

	/**
	 * 
	 *@Title:list
	 *@Description:页面跳转
	 *@Params:@return
	 *@Return:String
	 *@author:孟凡岭
	 *@Date:2014-11-19上午11:49:53
	 */
	public String list() {
		this.getRequest().setAttribute("comPlaintType",
				OptionsValue.COMPLAINT_TYPE);
		this.getRequest().setAttribute("orderCompsta",
				OptionsValue.ORDERCOMPSTA);
		return "list";
	}

	@SuppressWarnings("unchecked")
	public void jsonPageList() throws Exception {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {// 如果页面没有要求排序方式，则设置按照申请时间排序
			orderby.put("compTime", "desc");
		}
		QueryResult<ComplaintFiledDTO> queryResult = complaintFiledService
				.queryResult((complaintFiledDTO.getPage() - 1) * pageNum,
						pageNum, complaintFiledDTO, orderby);
		List<ComplaintFiledDTO> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		UserSession us = Utils.getUserSession();
		for (int i = 0; i < list.size(); i++) {
			ComplaintFiledDTO c = list.get(i);
			List<String> str = new ArrayList<String>();
			str.add(String.valueOf(i + 1));
			str.add(String.valueOf(c.getFiledId()));
			str.add(c.getOrderId());
			str.add(Utils.getOptionsIntegerName(OptionsValue.COMPLAINT_TYPE, c
					.getCompType()));
			str.add(c.getStoreId());
			str.add(Utils.getOptionsIntegerName(OptionsValue.ORDERCOMPSTA, c
					.getCompSta()));

			String oper = "<a href='complaintFiled/complaintFiled!viewUI?complaintFiledDTO.filedId="
					+ c.getFiledId()
					+ "&complaintFiledDTO.orderId="
					+ c.getOrderId()
					+ "&complaintFiledDTO.storeId="
					+ c.getStoreId()
					+ "&complaintFiledDTO.memId="
					+ c.getMemId()
					+ "' title='查看详情'>"
					+ Globals.IMG_VIEW
					+ "</a>&nbsp;";
			if (us.getUserLevel() == 0) {
				if (c.getCompSta() != 5) {
					// 3代表投诉已经完成
					if (c.getCompSta() != 3&&c.getCompSta()!=2) {
						oper += "<a href='complaintFiled/complaintFiled!editUI?complaintFiledDTO.filedId="
								+ c.getFiledId()
								+ "&complaintFiledDTO.orderId="
								+ c.getOrderId()
								+ "&complaintFiledDTO.storeId="
								+ c.getStoreId()
								+ "&complaintFiledDTO.memId="
								+ c.getMemId()
								+ "' title='处理'>"
								+ Globals.IMG_EDIT + "</a>&nbsp;";
						if (Utils.getUserSession().getUserLevel() == 0
								&& c.getCompSta() != 3 && c.getCompSta() != 2
								&& c.getCompSta() != 1) {
							oper += "<a href='javascript:void(0);' onclick='"
									+ "appoint(" + c.getFiledId()
									+ ");' title='指派给商家'>指派给商家" + "</a>&nbsp;";
						}
					}
				}
			} else {
				if (c.getCompSta() == 1) {
					oper += "<a href='complaintFiled/complaintFiled!editUI?complaintFiledDTO.filedId="
							+ c.getFiledId()
							+ "&complaintFiledDTO.orderId="
							+ c.getOrderId()
							+ "&complaintFiledDTO.storeId="
							+ c.getStoreId()
							+ "&complaintFiledDTO.memId="
							+ c.getMemId()
							+ "' title='处理'>"
							+ Globals.IMG_EDIT
							+ "</a>&nbsp;";
				}
			}
			str.add(oper);
			lists.add(str);
		}
		PageView pageView = new PageView(complaintFiledDTO.getPage(),
				queryResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		Utils.printInfo(listInfoDTO);
	}

	/**
	 * 
	 *@Title:view
	 *@Description:查询详情
	 *@Params:@return
	 *@Return:String
	 *@author:孟凡岭
	 *@Date:2014-11-19下午05:40:32
	 */
	public void viewData() throws Exception {
		complaintHandleDTO = complaintFiledService
				.loadComplaint(this.complaintFiledDTO);
		List<OptionsInteger> vio = vioRegulService.find();
		this.getRequest().setAttribute("vio", vio);
		this.getRequest().setAttribute("admin", OptionsValue.ADMINORDERCOMPSTA);
		this.getRequest().setAttribute("compsta", OptionsValue.ORDERCOMPSTA);
		this.getRequest().setAttribute("type", OptionsValue.COMPLAINT_TYPE);
		this.getRequest().setAttribute("handlWay", OptionsValue.HANDLEWAY);
	}

	/**
	 * 
	 *@Title:viewUI
	 *@Description:查看数据
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:String
	 *@author:孟凡岭
	 *@Date:2014-11-24上午10:00:15
	 */
	public String viewUI() throws Exception {
		this.setMethod("view");
		viewData();
		return "view";
	}

	public String editUI() throws Exception {
		this.setMethod("edit");
		viewData();
		return "view";
	}

	/**
	 * 
	 *@Title:updateCom
	 *@Description:平台处理投诉
	 *@Params:@return
	 *@Return:String
	 *@author:孟凡岭
	 * @throws Exception
	 *@Date:2014-11-21下午01:44:32
	 */
	public String updateCom() throws Exception {
		boolean flag = complaintFiledService.updateCom(complaintHandleDTO);
		if (flag) {
			this.getRequest().setAttribute("result", "操作成功");
			this.getRequest().setAttribute("url",
					"complaintFiled/complaintFiled!list");
			functionsService.saveFunction("投诉处理", 2, "平台处理投诉成功");
			return SUCCESS;
		}
		this.getRequest().setAttribute("result", "操作失败!");
		this.getRequest().setAttribute("url",
				"complaintFiled/complaintFiled!list");
		functionsService.saveFunction("投诉处理", 2, "平台处理投诉失败");
		return ERROR;
	}

	/**
	 * 
	 *@Title:businessAppeal
	 *@Description:商户申诉
	 *@Params:@return
	 *@Return:String
	 *@author:孟凡岭
	 * @throws Exception
	 *@Date:2014-11-24下午12:06:20
	 */
	public String businessAppeal() throws Exception {
		boolean flag = complaintFiledService.businessAppeal(complaintHandleDTO);
		if (flag) {
			this.getRequest().setAttribute("result", "操作成功");
			this.getRequest().setAttribute("url",
					"complaintFiled/complaintFiled!list");
			functionsService.saveFunction("投诉处理", 2, "商户申诉成功");
			return SUCCESS;
		}
		this.getRequest().setAttribute("result", "操作失败!");
		this.getRequest().setAttribute("url",
				"complaintFiled/complaintFiled!list");
		functionsService.saveFunction("投诉处理", 2, "商户申诉失败");
		return ERROR;
	}

	/**
	 * 
	 *@Title:appoint
	 *@Description:指派给商家处理
	 *@Params:
	 *@Return:void
	 *@author:孟凡岭
	 *@Date:2014-11-24上午11:23:43
	 */
	public void appoint() {
		try {
			complaintFiledService.appoint(complaintFiledDTO.getFiledId());
			functionsService.saveFunction("投诉处理", 2, "平台处理投诉，指派商户处理");
			Utils.printInfo(true);
		} catch (Exception e) {
			functionsService.saveFunction("投诉处理", 2, "平台处理投诉，指派商户处理失败");
			Utils.printInfo(false);
		}
	}

	public ComplaintFiledDTO getComplaintFiledDTO() {
		return complaintFiledDTO;
	}

	public void setComplaintFiledDTO(ComplaintFiledDTO complaintFiledDTO) {
		this.complaintFiledDTO = complaintFiledDTO;
	}

	public ComplaintFiledService getComplaintFiledService() {
		return complaintFiledService;
	}

	public void setComplaintFiledService(
			ComplaintFiledService complaintFiledService) {
		this.complaintFiledService = complaintFiledService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ComplaintHandleDTO getComplaintHandleDTO() {
		return complaintHandleDTO;
	}

	public void setComplaintHandleDTO(ComplaintHandleDTO complaintHandleDTO) {
		this.complaintHandleDTO = complaintHandleDTO;
	}

	public VioRegulService getVioRegulService() {
		return vioRegulService;
	}

	public void setVioRegulService(VioRegulService vioRegulService) {
		this.vioRegulService = vioRegulService;
	}

	public FunctionsService getFunctionsService() {
		return functionsService;
	}

	public void setFunctionsService(FunctionsService functionsService) {
		this.functionsService = functionsService;
	}

}
