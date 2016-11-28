package com.paySystem.ic.web.action.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.order.ReMoneyReasonService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.order.ReMoReasonDTO;

/**
 * @ProjectName: omproject
 * @ClassName: ReMoneyAction
 * @Description: 退款信息记录表Action
 * @date: 2014-11-12 下午04:07:38
 * @author: 王少辉
 * @version: V1.0
 */
@Controller("/orders/remoney")
@Scope("prototype")
public class ReMoneyAction extends BaseAction {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 6610401497735202776L;
	
	/**
	 * 日志
	 */
	private static final Log log = LogFactory.getLog(ReMoneyAction.class);

	/**
	 * 退款申请Service
	 */
	@Resource
	private ReMoneyReasonService reMoneyReasonService;
	
	/**
	 * 退款申请表
	 */
	ReMoReasonDTO reMoReasonDTO = new ReMoReasonDTO();

	/**
	 * @return ReMoReasonDTO 退款申请表
	 */
	public ReMoReasonDTO getReMoReasonDTO() {
		return reMoReasonDTO;
	}

	/**
	 * @param reMoReasonDTO
	 *            退款申请表
	 */
	public void setReMoReasonDTO(ReMoReasonDTO reMoReasonDTO) {
		this.reMoReasonDTO = reMoReasonDTO;
	}

	/**
	 *@Title: list
	 *@Description: 跳转到退款列表界面
	 *@Return: String 返回退款列表
	 *@author: 王少辉
	 *@Date: 2014-11-12 下午05:19:24
	 */
	public String list() {
		// 起始时间默认为前七天时间
		reMoReasonDTO.setBeginDate(DateTimeTool.dateFormat("yyyy-MM-dd",
				DateTimeTool.nDaysAgo(7, new Date())));
		reMoReasonDTO.setEndDate(DateTimeTool.dateFormat("yyyy-MM-dd",
				new Date()));

		return "list";
	}

	/**
	 *@Title: jsonPageList
	 *@Description: 动态查询退款列表信息
	 *@Return: String 返回退款结果
	 *@author: 王少辉
	 *@Date: 2014-11-12 下午05:21:25
	 */
	public String jsonPageList() {
		// 分布查询数据
		QueryResult<ReMoReasonDTO> terResult = queryResult((reMoReasonDTO
				.getPage() - 1)
				* pageNum, pageNum);

		// 封装页面显示数据
		List<ReMoReasonDTO> list = terResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		lists = getListString(list);

		// 设置分页信息
		PageView<ReMoReasonDTO> pageView = new PageView<ReMoReasonDTO>(
				reMoReasonDTO.getPage(), terResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));

		return Utils.printInfo(listInfoDTO);
	}

	/**
	 *@Title: queryResult
	 *@Description: 查询退款列表
	 *@Params: page 当前页数
	 *@Params: pageNum 每页记录数
	 *@Return: QueryResult<ReMoReasonDTO> 退款列表
	 *@author: 王少辉
	 *@Date: 2014-11-12 下午05:22:47
	 */
	private QueryResult<ReMoReasonDTO> queryResult(int page, int pageNum) {

		// 设置排序，默认退款申请时间降序
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderBy.put("reMoTime", "desc");
		}

		QueryResult<ReMoReasonDTO> qr = new QueryResult<ReMoReasonDTO>();
		try {
			qr = reMoneyReasonService.queryAll(page, pageNum, reMoReasonDTO, orderBy);
		} catch (Exception e) {
			log.error("ReMoneyAction.queryResult() encounted an error!");
		}

		return qr;
	}

	/**
	 *@Title: getListString
	 *@Description: 封装页面显示数据
	 *@Params: list 需要封装的参数
	 *@Return: List<List<String>> 返回页面显示数据列表
	 *@author: 王少辉
	 *@Date: 2014-11-12 下午07:06:14
	 */
	private List<List<String>> getListString(List<ReMoReasonDTO> list) {
		List<List<String>> lists = new ArrayList<List<String>>();

		if (CollectionUtils.isEmpty(list)) {
			return lists;
		}

		// 保存具体数据
		List<String> strings = null;
		String operation = "";
		ReMoReasonDTO dto = null;
		for (int i = 0; i < list.size(); i++) {
			dto = list.get(i);

			// 开始封装
			strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			strings.add(Utils.getString(dto.getReMoReId()));
			strings.add(Utils.getString(dto.getOrderId()));
			strings.add(Utils.getString(dto.getMemId()));
			
			int isTakeGoods = dto.getIsTakeGoods();
			if (0 == isTakeGoods) {
				strings.add("否");
			}else if (1 == isTakeGoods) {
				strings.add("是");
			}
			
			strings.add(DateTimeTool.dateFormat("", dto.getReMoTime()));
			strings.add(NumberUtil.numberFormat("", dto.getReturnMoney()));
			strings.add(NumberUtil.numberFormat("", dto.getActuReturnMoney()));
			strings.add(DateTimeTool.dateFormat("", dto.getUpdateTime()));

			if (Utils.checkPermission("sy-4103-01")) {
				operation = "<a href='orders/remoney!findById?reMoReasonDTO.reMoReId="
					+ dto.getReMoReId()
					+ "' title='查看'>"
					+ Globals.IMG_VIEW
					+ "</a>&nbsp;";
			}
			// 可以审核未处理的申请或拒绝的申请
			if (Utils.checkPermission("sy-4103-02") && (null == dto.getStatus() || 0 == dto.getStatus() || 2 == dto.getStatus())) {
				operation += "<a href=javascript:void(0) onclick=loadData('"
					+ dto.getReMoReId() + "',this) title='审核'>"
					+ Globals.IMG_AUDIT + "</a> &nbsp;";
			}
			
			strings.add(operation);

			lists.add(strings);
		}

		return lists;
	}
	
	/**
	 *@Title: findById
	 *@Description: 根据主键ID查询退款详细
	 *@Params: id 主键ID
	 *@Return: String 返回退款详细
	 *@author: 王少辉
	 *@Date: 2014-11-13 下午03:26:52
	 */
	public String findById(){
		try {
			// 查询退款详细
			reMoReasonDTO = reMoneyReasonService.findById(reMoReasonDTO.getReMoReId());
			
			// 设置查看操作
			reMoReasonDTO.setReturnMoneyShow(NumberUtil.numberFormat("", reMoReasonDTO.getReturnMoney()));
		} catch (Exception e) {
			log.error("ReMoneyAction.findById() encounted an error!");
		}
		
		return INPUT;
	}
	
	/**
	 *@Title: auditSure
	 *@Description: 审核退款信息
	 *@Return: String 返回退款详细
	 *@author: 王少辉
	 *@Date: 2014-11-13 下午05:16:30
	 */
	public String auditSure(){
		ReturnDTO dto = new ReturnDTO();
		try {
			// 查询退款详细
			reMoReasonDTO = reMoneyReasonService.findById(reMoReasonDTO.getReMoReId());
			
			// 回显数据
			reMoReasonDTO.setReturnMoneyShow(NumberUtil.numberFormat("", reMoReasonDTO.getReturnMoney()));
			dto.setFlag(true);
			dto.setObj(reMoReasonDTO);
		} catch (Exception e) {
			log.error("ReMoneyAction.auditSure() encounted an error!");
		}
		
		return Utils.printInfo(dto);
	}
	
	/**
	 *@Title: auditSuccess
	 *@Description: 审核通过，更新申请表审核状态并在申请记录表添加审核记录
	 *@Return: String
	 *@author: 王少辉
	 *@Date: 2014-11-13 下午08:37:21
	 */
	public String auditSuccess(){
		try {
			// 审核通过，更新申请表审核状态并在申请记录表添加审核记录
			reMoneyReasonService.auditSuccess(reMoReasonDTO);
		} catch (Exception e) {
			log.error("ReMoneyAction.auditSuccess() encounted an error!");
		}
		
		return "list";
	}
	
	/**
	 *@Title: auditFailure
	 *@Description: 审核拒绝，更新申请表审核状态并在申请记录表添加审核记录
	 *@Params: @return
	 *@Return: String
	 *@author: 王少辉
	 *@Date: 2014-11-13 下午08:37:28
	 */
	public String auditFailure(){
		try {
			// 审核拒绝，更新申请表审核状态并在申请记录表添加审核记录
			reMoneyReasonService.auditFailure(reMoReasonDTO);
		} catch (Exception e) {
			log.error("ReMoneyAction.auditFailure() encounted an error!");
		}
		
		return "list";
	}

}
