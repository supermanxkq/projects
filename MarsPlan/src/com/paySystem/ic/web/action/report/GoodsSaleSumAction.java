package com.paySystem.ic.web.action.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.report.GoodsSaleSumService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.ExportUtil;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.report.GoodsSaleSumDTO;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:GoodsSaleSumAction
 * @Description:商品销量汇总排名的Action
 * @date: 2014-9-9
 * @author: 王楠
 * @version: V1.0
 */
@Controller("/report/goodSaleSum")
@Scope("prototype")
public class GoodsSaleSumAction extends BaseAction{

	private static final long serialVersionUID = -5988850084617201983L;

	@Resource
	GoodsSaleSumService goodsSaleSumService;
	@Resource
	FunctionsService functionsService;
	
	private GoodsSaleSumDTO goodsSaleSumDTO=new GoodsSaleSumDTO();
	
	public GoodsSaleSumDTO getGoodsSaleSumDTO() {
		return goodsSaleSumDTO;
	}

	public void setGoodsSaleSumDTO(GoodsSaleSumDTO goodsSaleSumDTO) {
		this.goodsSaleSumDTO = goodsSaleSumDTO;
	}

	/**
	*@Title:list
	*@Description:页面跳转的list方法
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-9-9上午11:45:25
	*/
	public String list(){
		goodsSaleSumDTO.setBeginDate(DateTimeTool.dateFormat("yyyy-MM-dd",
				DateTimeTool.nDaysAgo(7, new Date())));
		goodsSaleSumDTO.setEndDate(DateTimeTool.dateFormat("yyyy-MM-dd",
			  new Date()));
		return "list";
	}
	
	/**
	*@Title:jsonPageList
	*@Description:异步获取数据的方法
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-9-10下午03:09:36
	*/
	@SuppressWarnings("unchecked")
	public String jsonPageList()throws Exception{
		/**查询结果排序参数设定*/
		LinkedHashMap<String,String> orderBy=new LinkedHashMap<String,String>();
		/**判断排序参数是否有值*/
		if(StringUtils.isNotBlank(this.getOrderProperty())
				&&StringUtils.isNotBlank(this.getOrderDirection())){
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		}else{
			orderBy.put("qty", "asc");
		}
		/**获取queryResult中的集合*/
		QueryResult<GoodsSaleSumDTO> result=goodsSaleSumService.queryAll(
				(goodsSaleSumDTO.getPage()-1)*pageNum, pageNum, 
				goodsSaleSumDTO, orderBy);
		/**获取queryResult中的集合*/
		List<GoodsSaleSumDTO> goodsList=result.getResultlist();
		List<List<String>> lists=new ArrayList<List<String>>();
		/** QueryResult对象的getResultlist方法获取GoodsSaleSumDTO集合 */
		for(int i=0;i<goodsList.size();i++){
			/**向页面赋值*/
			GoodsSaleSumDTO goodsSaleSumDTO=goodsList.get(i);
			List<String> strings = new ArrayList<String>();
			/**添加到字符串集合中去*/
			strings.add(String.valueOf(i+1));
			/**调用下面私有方法，封装的String方法*/
			strings=findGoodsStringList(strings,goodsSaleSumDTO);
			lists.add(strings);
		}
		/***实例化PageView对象，获取分页的参数、总页数、总记录数*/
		PageView pageView=new PageView(goodsSaleSumDTO.getPage(),
				result.getTotalrecord());
		/**实例化ListInfoDTO*/
		ListInfoDTO listInfoDTO=new ListInfoDTO(lists,getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}
	
	/**
	*@Title:export
	*@Description:导出
	*@Params:@return
	*@Return:String
	*@author:王楠
	 * @throws Exception 
	*@Date:2014-9-10下午04:08:41
	*/
	public String export(){
		String title="商品销量汇总排名";
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		/** 判断排序参数是否有值 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderProperty());
		} else {
			orderBy.put("qty", "asc");
		}
		try {
			setFileName(this.getRequest(),this.getResponse(),title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String str="";
		ExportUtil util=new ExportUtil();
		List<String> headers=new ArrayList<String>();
		headers.add("商品名称");
		headers.add("商品货号");
		headers.add("销售量");
		headers.add("销售额");
		headers.add("均价");
		/**获取要导出的集合*/
		QueryResult<GoodsSaleSumDTO> goodsList;
		try {
			goodsList = goodsSaleSumService.exportGoodsSale
			                               (goodsSaleSumDTO, orderBy);
			List<List<String>> lists = new ArrayList<List<String>>();
			List<GoodsSaleSumDTO> dto=goodsList.getResultlist();
			for(int i=0;i<dto.size();i++){
				GoodsSaleSumDTO goodsSaleSumDTO=dto.get(i);
				List<String> list=new ArrayList<String>();
				list=findGoodsStringList(list,goodsSaleSumDTO);
				lists.add(list);
			}
			str=util.createXls(headers, lists, title, this.getResponse());
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	/**
	*@Title:findGoodsStringList
	*@Description:获取数据的字符串集合
	*@Params:@param strings
	*@Params:@param goodsSaleSumDTO 商品销量汇总实体的DTO
	*@Params:@return
	*@Return:List<String>
	*@author:王楠
	*@Date:2014-9-10下午03:36:10
	*/
	private List<String> findGoodsStringList(List<String> strings,
			GoodsSaleSumDTO goodsSaleSumDTO) {
		strings.add(Utils.getString(goodsSaleSumDTO.getGoodsName()));
		strings.add(Utils.getString(goodsSaleSumDTO.getGoodsItem()));
		strings.add(Utils.getString(goodsSaleSumDTO.getQty()));
		strings.add(NumberUtil
				.numberFormat("#,##0.0000", goodsSaleSumDTO.getPrice()));
		strings.add(NumberUtil
				.numberFormat("#,##0.0000", goodsSaleSumDTO.getAvgPrice()));
		return strings;
	}
}
