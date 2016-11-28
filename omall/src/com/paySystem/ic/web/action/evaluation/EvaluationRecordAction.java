package com.paySystem.ic.web.action.evaluation;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.service.evaluation.EvaluationService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.ExportUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.evaluation.CriticalContextDTO;
import com.paySystem.ic.web.dto.evaluation.MerCreditRecordDTO;
import com.paySystem.ic.web.dto.system.UserSession;


/**  
 * @Title: EvaluationAction.java
 * @Package: com.paySystem.ic.web.action.evaluation
 * @Description: 评价管理Action
 * @Author: yanwuyang 
 * @Date: 2014-10-9 下午11:50:14
 * @Version: V1.0  
 */
@Controller("/evaluation/evaluationRecord")
@Scope("prototype")
public class EvaluationRecordAction extends BaseAction {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8350221423037461974L;
	
	@Resource
	private EvaluationService evaluationService;
	
	@Resource
	FunctionsService functionsService;
	
	/** 评价记录DTO*/
	private MerCreditRecordDTO creditRecordDTO = new MerCreditRecordDTO();
	
	/** 评论DTO*/
	private CriticalContextDTO contextDTO;
	
	public MerCreditRecordDTO getCreditRecordDTO() {
		return creditRecordDTO;
	}

	public void setCreditRecordDTO(MerCreditRecordDTO creditRecordDTO) {
		this.creditRecordDTO = creditRecordDTO;
	}

	public CriticalContextDTO getContextDTO() {
		return contextDTO;
	}

	public void setContextDTO(CriticalContextDTO contextDTO) {
		this.contextDTO = contextDTO;
	}


	public String list() {
		
		creditRecordDTO.setBeginDate(DateTimeTool.dateFormat("yyyy-MM-dd",DateTimeTool.nDaysAgo(7, new Date())));
		creditRecordDTO.setEndDate(DateTimeTool.dateFormat("yyyy-MM-dd",new Date()));
		/** 获取当前操作员信息*/
		UserSession us = Utils.getUserSession();
		
		/**获取商户信息*/
		String userName= us.getUserName();

		/**
		 * 根据操作员不同级别进行界面跳转
		 * 
		 * us.getUserLevel 0 : 平台操作员 1 : 机构操作员 2 : 商户操作员 操作员为平台操作员，进行界面正常跳转；
		 * 操作员为机构或商户操作员，进行拦截提示其不拥有该权限；
		 */

		return "list";
	}
	

	
	/**
	 *@Title:jsonPageList
	 *@Description:异步获取列表数据
	 *@param:@return
	 *@param:@throws Exception
	 *@return:String
	 *@author:张国法
	 *@thorws:
	 */

	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {

		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		// 判断排序参数是否有值
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {// 如果页面没有要求排序方式，则设置按照会员编号排序
			orderBy.put("memId", "desc");
		}
		List<MerCreditRecordDTO> messConsList = evaluationService.queryAll(
				(creditRecordDTO.getPage() - 1) * pageNum, pageNum,
				creditRecordDTO, orderBy);

		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < messConsList.size(); i++) {
			// 向页面赋值
			MerCreditRecordDTO creditRecordDTO = messConsList.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			strings.add(Utils.getString(creditRecordDTO.getMcrId()));
			strings.add(Utils.getString(creditRecordDTO.getMerId()));
			strings.add(Utils.getString(creditRecordDTO.getAppType()));
			strings.add(Utils.getString(creditRecordDTO.getGoodsapprise()));
			strings.add(Utils.getString(creditRecordDTO.getGoodsMatch()));
			strings.add(Utils.getString(creditRecordDTO.getServiceAtt()));
			strings.add(Utils.getString(creditRecordDTO.getSendSpead()));
			strings.add(Utils.getString(creditRecordDTO.getMemId()));
			strings.add(Utils.getString(creditRecordDTO.getCreateTIme()));
			lists.add(strings);
		}

		PageView pageView = new PageView(creditRecordDTO.getPage(),
				messConsList.size());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);

	}

	/**
	 *@Title:export
	 *@Description:导出Excel表Action
	 *@param:@return
	 *@return:String
	 *@author:张国法
	 *@thorws:
	 */
	public String export() {
		String title = "好评价记录汇总报表 ";
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();

		// 判断排序参数是否有值
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderProperty());
		} else {
			orderBy.put("merId", "desc");
		}
		try {
			setFileName(this.getRequest(), this.getResponse(), title);
			String str = "";
			ExportUtil util = new ExportUtil();
			List<String> headers = new ArrayList<String>();
			headers.add("序号");
			headers.add("评价编号");
			headers.add("商户编号");
			headers.add("评价类型");
			headers.add("评价分数");
			headers.add("宝贝描述相符");
			headers.add("服务态度");
			headers.add("发货态度");
			headers.add("会员编号");
			headers.add("创建时间");


			List<MerCreditRecordDTO> mdtoList = evaluationService
					.queryMessConsTotalReport(creditRecordDTO, orderBy);
			List<List<String>> lists = new ArrayList<List<String>>();
			for (int i = 0; i < mdtoList.size(); i++) {

				MerCreditRecordDTO mdto = mdtoList.get(i);

				List<String> slist = new ArrayList<String>();
				slist = retTradesViews(slist, mdto, i++);
				lists.add(slist);
			}
			str = util.createXls(headers, lists, title, this.getResponse());
			return str;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 *@Title:retTradesViews
	 *@Description:封装String方法
	 *@param:@param mdtoList
	 *@param:@param mctdto
	 *@param:@return
	 *@return:List<String>
	 *@author:张国法
	 *@thorws:
	 */
	private List<String> retTradesViews(List<String> mdtoList,
			MerCreditRecordDTO mctdto,int index) {
		mdtoList.add(Utils.getString(index));
		mdtoList.add(Utils.getString(mctdto.getMcrId()));
		mdtoList.add(Utils.getString(mctdto.getMerId()));
		mdtoList.add(Utils.getString(mctdto.getAppType()));
		mdtoList.add(Utils.getString(mctdto.getGoodsapprise()));
		mdtoList.add(Utils.getString(mctdto.getGoodsMatch()));
		mdtoList.add(Utils.getString(mctdto.getServiceAtt()));
		mdtoList.add(Utils.getString(mctdto.getSendSpead()));
		mdtoList.add(Utils.getString(mctdto.getMemId()));
		mdtoList.add(Utils.getString(mctdto.getCreateTIme()));
		return mdtoList;
	}
}
