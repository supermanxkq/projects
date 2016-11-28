package com.paySystem.ic.web.action.stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.stock.StockInfo;
import com.paySystem.ic.service.stock.StockInfoService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.stock.StockInfoDTO;

/**
 * @ClassName:CardStockAction
 * @Description:卡库存管理
 * @date: 2014-5-28下午03:20:36
 * @author: 谢洪飞
 * @version: V1.0
 */
@Controller("/stock/cardStock")
@Scope("prototype")
public class CardStockAction extends BaseAction {

	
	private static final long serialVersionUID = 1L;
	
	@Resource StockInfoService stockInfoService;

	private StockInfoDTO stockInfoDTO = new StockInfoDTO();
	
	public StockInfoDTO getStockInfoDTO() {
		return stockInfoDTO;
	}

	public void setStockInfoDTO(StockInfoDTO stockInfoDTO) {
		this.stockInfoDTO = stockInfoDTO;
	}




	/**
	 *@Title:list
	 *@Description:库存信息管理 list方法
	 *@param:@return
	 *@return:String
	 *@author: 谢洪飞
	 */
	public String list() {
		
		return "list";
		
	}
	
	
	/**
	 *@Title:jsonPageList
	 *@Description:异步获取库存信息
	 *@param:@return
	 *@return:String
	 *@author: 谢洪飞
	 * @throws Exception 
	 */
	public String jsonPageList() throws Exception{
		/**
		 * 查询结果排序参数设定 
		 */
		LinkedHashMap<String,String> orderBy = new LinkedHashMap<String,String>();
		
		//判断排序参数是否有值
		if(StringUtils.isNotBlank(this.getOrderProperty())
				&&StringUtils.isNotBlank(this.getOrderDirection()))
		{orderBy.put(this.getOrderProperty(),this.getOrderProperty());}
		else{orderBy.put("id","desc");}
	
		QueryResult<StockInfo> terResult = stockInfoService.queryStockByCond((stockInfoDTO.getPage() - 1) * pageNum,pageNum,stockInfoDTO,orderBy);
		List<StockInfo> list = terResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for(int i = 0;i <list.size();i++){
			//向页面赋值
			StockInfo stockInfo = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i+1));
			strings.add(Utils.getString(
					stockInfo.getMerchants()==null?stockInfo.getOrgans().getName()
							                      :stockInfo.getMerchants().getMerName()));
			strings.add(stockInfo.getMerchants()==null?"商户":"平台机构");
			strings.add(stockInfo.getCardBin().getBinName());
			
			Integer stockCount =
				  (stockInfo.getInTotal()==null?0:stockInfo.getInTotal())
			    - (stockInfo.getOutTotal()==null?0:stockInfo.getOutTotal());
			
			strings.add(stockCount.toString());
			strings.add(DateTimeTool.dateFormat("", stockInfo.getUpdateTime()));
			
			String operation ="";
			
			if(Utils.checkPermission("sy-2205-01")){
					operation += "<a href=javascript:checkStockInfo('"+stockInfo.getId()+"',"+1+") title='查看'>"+Globals.IMG_VIEW+"</a>&nbsp;";
			}
			/*if(Utils.checkPermission("sy-2205-02")){
					operation += "<a href=javascript:editStockInfo('"+stockInfo.getId()+"',"+2+") title='调整库存'>"+Globals.IMG_EDIT+"</a>&nbsp;";
			}*/
				
			strings.add(operation);
			lists.add(strings);
		   
		}
		
		PageView pageView = new PageView(stockInfoDTO.getPage(),terResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists,getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}
	
	/**
	 *@Title:checkStockInfo
	 *@Description:查看库存信息
	 *@param:
	 *       @return
	 *@return:
	 *       String
	 *@author:
	 *       谢洪飞
	 *       
	 * @throws IOException 
	 */
	public String checkStockInfo() throws IOException{
		
		stockInfoDTO = stockInfoService.queryStockInfoById(stockInfoDTO.getId());
		
		return Utils.printInfo(stockInfoDTO);
	}

}
