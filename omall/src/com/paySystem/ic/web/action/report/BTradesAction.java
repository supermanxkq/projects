package com.paySystem.ic.web.action.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.report.BTradesService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.report.BTradesDTO;

/**
 * @ProjectName: omproject
 * @ClassName: BTradesAction
 * @Description: 业务交易Action
 * @date: 2014-11-7 上午11:22:12
 * @author: 王少辉
 * @version: V1.0
 */
@Controller("/report/btrades")
@Scope("prototype")
public class BTradesAction extends BaseAction {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 3289594428448738667L;

	/**
	 * 业务交易Service
	 */
	@Resource
	private BTradesService bTradesService;

	/**
	 * 业务交易DTO
	 */
	private BTradesDTO btradesDTO = new BTradesDTO();

	/**
	 * @return BTradesDTO 业务交易DTO
	 */
	public BTradesDTO getBtradesDTO() {
		return btradesDTO;
	}

	/**
	 * @param btradesDTO
	 *            业务交易DTO
	 */
	public void setBtradesDTO(BTradesDTO btradesDTO) {
		this.btradesDTO = btradesDTO;
	}

	/**
	 *@Title: list
	 *@Description: 业务交易成功列表
	 *@Return: String 返回列表视图
	 *@author: 王少辉
	 *@Date: 2014-11-7 下午05:19:08
	 */
	public String list() {
		// 起始时间默认为前七天时间
		btradesDTO.setBeginDate(DateTimeTool.dateFormat("yyyy-MM-dd",
				DateTimeTool.nDaysAgo(7, new Date())));
		btradesDTO
		.setEndDate(DateTimeTool.dateFormat("yyyy-MM-dd", new Date()));
		
		return "successList";
	}
	
	/**
	 *@Title: list
	 *@Description: 业务交易异常列表
	 *@Return: String 返回列表视图
	 *@author: 王少辉
	 *@Date: 2014-11-7 下午05:19:08
	 */
	public String listException() {
		// 起始时间默认为前七天时间
		btradesDTO.setBeginDate(DateTimeTool.dateFormat("yyyy-MM-dd",
				DateTimeTool.nDaysAgo(7, new Date())));
		btradesDTO
				.setEndDate(DateTimeTool.dateFormat("yyyy-MM-dd", new Date()));

		return "failList";
	}

	/**
	 *@Title: jsonPageList
	 *@Description: 动态获取业务交易列表
	 *@Return: String 返回json结果
	 *@Params: @throws Exception
	 *@author: 王少辉
	 *@Date: 2014-11-7 下午05:20:13
	 */
	public String jsonPageList() throws Exception {
		// 分布查询数据
		QueryResult<BTradesDTO> terResult = queryResult(
				(btradesDTO.getPage() - 1) * pageNum, pageNum);

		// 封装页面显示数据
		List<BTradesDTO> list = terResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		lists = getListString(list);

		// 设置分页信息
		PageView<BTradesDTO> pageView = new PageView<BTradesDTO>(btradesDTO
				.getPage(), terResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));

		return Utils.printInfo(listInfoDTO);
	}

	/**
	 *@Title: findById
	 *@Description: 根据id查询业务交易信息
	 *@Params: id 主键ID
	 *@Return: BTradesDTO 返回业务交易信息
	 *@throws: Exception 抛出异常
	 *@author: 王少辉
	 *@Date: 2014-11-7 下午07:39:12
	 */
	public String findById() {
		try {
			// bTradesService.findById(btradesDTO.getTradesId());
		} catch (Exception e) {
			log.error("BTradesAction.findById() encounted an error!");
		}

		return INPUT;
	}

	/**
	 *@Title: queryResult
	 *@Description: 查询业务交易列表
	 *@Params: @param page 当前页数
	 *@Params: @param pageNum 每页记录数
	 *@Return: QueryResult<BTradesDTO> 返回查询结果
	 *@author: 王少辉
	 *@Date: 2014-11-7 下午05:18:48
	 */
	private QueryResult<BTradesDTO> queryResult(int page, int pageNum) {

		// 设置排序，默认交易时间降序
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderBy.put("tradesTime", "desc");
		}

		// 查询数据
		QueryResult<BTradesDTO> results = new QueryResult<BTradesDTO>();
		try {
			results = bTradesService.queryAll(page, pageNum, btradesDTO,
					orderBy);
		} catch (Exception e) {
			log.error("BTradesAction.queryResult() encounted an error!");
		}

		return results;
	}

	/**
	 *@Title: getListString
	 *@Description: 封装业务交易报表界面显示信息
	 *@Params: list 封装前的业务交易报表列表信息
	 *@Return: List<List<String>> 封装后的业务交易报表列表信息
	 *@author: 王少辉
	 *@Date: 2014-11-7 下午05:18:53
	 */
	private List<List<String>> getListString(List<BTradesDTO> list) {

		List<List<String>> lists = new ArrayList<List<String>>();
		if (CollectionUtils.isEmpty(list)) {
			return lists;
		}

		List<String> strings = null;
		for (int i = 0; i < list.size(); i++) {
			BTradesDTO dto = list.get(i);

			// 开始封装
			strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			strings.add(Utils.getString(dto.getTradesId()));
			strings.add(Utils.getString(dto.getOmemId()));
			strings.add(Utils.getString(dto.getPayOrderId()));
			strings.add(Utils.getString(dto.getPayAccNo()));
			strings.add(Utils.getString(dto.getPayAccName()));
			strings.add(NumberUtil.numberFormat("#0.00##", dto.getPayAmt()));
			strings.add(Utils.getString(dto.getMerAccNo()));
			strings.add(Utils.getString(dto.getMerAccName()));
			strings.add(DateTimeTool.dateFormat("", dto.getTradesTime()));

			lists.add(strings);
		}

		return lists;
	}

}
