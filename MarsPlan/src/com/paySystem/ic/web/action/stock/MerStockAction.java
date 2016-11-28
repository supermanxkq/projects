package com.paySystem.ic.web.action.stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.service.base.MerchantsService;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.stock.MerStockService;
import com.paySystem.ic.service.stock.OrganStockService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:MerStockAction
 * @Description: 商户领卡
 * @date: 2014-5-26上午09:52:12
 * @author: 谢洪飞
 * @version: V1.0
 */
@Controller("/stock/mercard")
@Scope("prototype")
public class MerStockAction extends BaseAction {

	@Resource OrgansService organsService;
	@Resource OrganStockService organStockService;
	@Resource MerStockService merStockService;
	@Resource FunctionsService functionsService;
	@Resource MerchantsService merchantsService;
	public static Logger stockLogger = Logger.getLogger(MerStockAction.class);
	
	private static final long serialVersionUID = 1L;
	
	private ModStockDTO modStockDTO = new ModStockDTO();
	
	public ModStockDTO getModStockDTO() {
		return modStockDTO;
	}

	public void setModStockDTO(ModStockDTO modStockDTO) {
		this.modStockDTO = modStockDTO;
	}
	

	public String list(){
		
		this.getRequest().setAttribute("invChangeValues",OptionsValue.INVENTORYCHANGE_STATUS );
		return "list";
	}
	
	
	/**
	 *@Title:
	 *             josnPageList
	 *@Description:
	 *             异步获取商户领卡信息
	 *@param:
	 *             @return
	 *@return:
	 *             String
	 *@author:
	 *             谢洪飞
	 */
	public String jsonPageList(){
		/**
		 * 查询结果排序参数设定 
		 */
		LinkedHashMap<String,String> orderBy = new LinkedHashMap<String,String>();
		UserSession us = Utils.getUserSession();
		//判断排序参数是否有值
		if(StringUtils.isNotBlank(this.getOrderProperty())
				&&StringUtils.isNotBlank(this.getOrderDirection()))
		{orderBy.put(this.getOrderProperty(),this.getOrderProperty());}
		else{orderBy.put("id","desc");}
	
		QueryResult<ModStock> terResult = new QueryResult<ModStock>();
		try {
			terResult=
				merStockService.queryStockByCond((modStockDTO.getPage() - 1) * pageNum,
						                            pageNum,modStockDTO,orderBy);
		} catch (Exception e) {
			this.getRequest().setAttribute("result",this.getText("获取商户库存变动信息出错!"));
			this.getRequest().setAttribute("url","stock/mercard!list");
			return ERROR;	
		}
		List<ModStock> list = terResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for(int i = 0;i <list.size();i++){
			//向页面赋值
			ModStock modStock = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i+1));
			strings.add(Utils.getString(modStock.getId()));
			strings.add(Utils.getString(organsService.find(modStock.getOutId()).getName()));
			strings.add(Utils.getString(merchantsService.find(modStock.getInId()).getMerName()));
			strings.add(Utils.getString(modStock.getCardCount()));
			strings.add(Utils.getOptionsIntegerName(OptionsValue.INVENTORYCHANGE_STATUS, modStock.getStatus()));
			strings.add(DateTimeTool.dateFormat("", modStock.getCreateTime()));
			strings.add(DateTimeTool.dateFormat("", modStock.getAuditTime()));
			String operation ="";
			
				if(Utils.checkPermission("sy-2203-01"))
				{
					operation += "<a href=javascript:sureInStock('"+modStock.getId()+"','"
					             +Utils.getOptionsIntegerName(OptionsValue.INVENTORYCHANGE_STATUS,
					              modStock.getStatus())+"','"+1+"') title='查看'>"
					              +Globals.IMG_VIEW+"</a>&nbsp;";
				}
				//限定：1.只有商户级别操作员才能确认入库。
				//      2.变动表中状态为未入库的才能进行确认入库。
				//      3.只有入库商户为本商户的才可以进行入库操作
				if(modStock.getStatus()==0&&us.getUserLevel()==2 
						&&(modStock.getInId()==us.getMerId()||modStock.getInId().equals(us.getMerId()))){
					if(Utils.checkPermission("sy-2203-05")){
						operation += "<a href=javascript:sureInStock('"+modStock.getId()+"','"
						              +Utils.getOptionsIntegerName(OptionsValue.INVENTORYCHANGE_STATUS,
						               modStock.getStatus())+"') title='确认入库'>"
						               +Globals.IMG_AUDIT+"</a>&nbsp;";
					}
				}
			strings.add(operation);
			lists.add(strings);
		   
		}
		
		PageView pageView = new PageView(modStockDTO.getPage(),terResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists,getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}
	
	/**
	 *@Title:
	 *        addUI（商户领卡界面）
	 *        
	 *@Description:
	 *              添加商户领卡界面        
	 *@param:
	 *         @return
	 *       
	 *@return:
	 *        String
	 *        
	 *@author:
	 *        谢洪飞
	 *        
	 */
	public String addUI(){
		
		this.setMethod("addSave");
		this.getRequest().setAttribute("organsValues",organsService.getOption() );//机构
		this.getRequest().setAttribute("binsList", new ArrayList());
		return INPUT;
	}
	
	/**
	 *@Title:
	 *       loadBeginCardNo
	 *@Description:
	 *       添加商户领卡信息-加载启示卡号
	 *@param:@return
	 *@return:String
	 *@author:
	 *       谢洪飞
	 */
	public void loadBeginCardNo(){
		
		String cardNo = organStockService.loadBeginCardNo(modStockDTO.getCardBinNo(), modStockDTO.getOrganId(),0);
		HttpServletResponse response =
	         (HttpServletResponse)ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
	Gson gson = new Gson();			
	cardNo = gson.toJson(cardNo).toString();
	response.setHeader("Cache-Control", "no-cache");
	  response.setCharacterEncoding("UTF-8");
	  try
	  {
		response.getWriter().write(cardNo);
		response.getWriter().flush();
		response.getWriter().close();
	  }
	  catch (IOException e)
	  {
		  stockLogger.info("加载初始卡号失败!");
		e.printStackTrace();
	  }
	  
	}
	
	/**
	 *@Title:
	 *             addSave
	 *@Description:
	 *             保存商户领卡信息
	 *@param:@return 
	 *@return:String
	 *@author: 
	 *              谢洪飞
	 */
	public String addSave(){
		
		try {
			merStockService.saveMerStockIn(modStockDTO);
		} catch (Exception e) {
			stockLogger.info("保存商户领卡信息失败!");
			this.getRequest().setAttribute("result",this.getText("商户领卡信息保存失败"));
			this.getRequest().setAttribute("url","stock/mercard!list");
			return ERROR;	
		}
		functionsService.saveFunction("机构领卡", 1, "增加机构领卡：");
		this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url","stock/mercard!list");
		return SUCCESS;
	}
	
	
	
	/**
	 *@Title:
	 *      sureStockIn
	 *@Description:
	 *      商户领卡：确认入库操作
	 *@param:
	 *      @return
	 *@return:
	 *      String
	 *@author:
	 *      谢洪飞
	 */
	public String sureStockIn(){
		
		ReturnDTO dto = new ReturnDTO();
		ModStock modStock = new ModStock();
		try
		{
			dto = merStockService.sureStockIn(modStockDTO);
			modStock = (ModStock)dto.getObj();
			dto.setMsg("入库操作完成。\n本次入库流水号："+modStockDTO.getId()+"\n本次入库卡BIN："+modStock.getCardBin().getBinName()+"\n本次入库卡数量："+modStock.getCardCount());
		}
		catch (Exception e)
		{
			stockLogger.info("确认领卡失败!");
			dto.setMsg("批次号为:"+modStockDTO.getId()+" 的卡入库失败!");
			e.printStackTrace();
		}
		dto.setObj(null);
		return Utils.printInfo(dto);
	}

}
