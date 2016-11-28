package com.paySystem.ic.web.action.stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.CardBIN;
import com.paySystem.ic.bean.card.CardNo;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.card.CardBINService;
import com.paySystem.ic.service.card.CardNoService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.stock.HeadQuinService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.ModStockDTO;
import com.paySystem.ic.web.dto.stock.ModStockDetailDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsString;

/**
 * @ClassName:HeadQuinAction
 * @Description:总部入库Action类
 * @date: 2013-12-11下午04:03:07
 * @author: 谢洪飞
 * @version: V1.0
 */
@Controller("/stock/headquin")
@Scope("prototype")
public class HeadQuinAction extends BaseAction {

	private static final long serialVersionUID = -8405618181001797831L;
	
	
	private ModStockDTO modStockDTO = new ModStockDTO();
	
	@Resource
	HeadQuinService heService;
	@Resource
	CardNoService cardNoService;
	@Resource
	CardBINService cardBinService;
	@Resource
	FunctionsService functionsService;
	@Resource
	OrgansService organsService;
	
	
	public ModStockDTO getModStockDTO() {
		return modStockDTO;
	}
	public void setModStockDTO(ModStockDTO modStockDTO) {
		this.modStockDTO = modStockDTO;
	}




	/***
	 *@Title:list
	 *@Description:左侧菜单访问方法
	 *@param:@return
	 *@return:String
	 *@thorws:
	 */
	public String list(){
		 
		/*modStockDTO.setBeginDate(DateTimeTool.dateFormat("yyyy-MM-dd",DateTimeTool.nDaysAgo(7, new Date())));
		modStockDTO.setEndDate(DateTimeTool.dateFormat("yyyy-MM-dd", new Date()));*/
		this.getRequest().setAttribute("invChangeValues",OptionsValue.INVENTORYCHANGE_STATUS );//状态
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
		case 0:
			break;
		case 1:
			return "intercepthtml";
		case 2:
			return "intercepthtml";
		}
		return "list";
	}
	
	/**
	 *@Title:jsonPageList
	 *@Description:根据前台页面条件，异步ajax调用查询方法
	 *@param:@return
	 *@param:@throws Exception
	 *@return:String
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList()  throws Exception{
		
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
	
		QueryResult<ModStock> terResult = heService.queryStockByCond((modStockDTO.getPage() - 1) * pageNum,pageNum,modStockDTO,orderBy);
		List<ModStock> list = terResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for(int i = 0;i <list.size();i++){
			//向页面赋值
			ModStock modStock = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i+1));
			strings.add(Utils.getString(modStock.getId()));
			strings.add(Utils.getString(organsService.find(modStock.getOutId()).getName()));
			strings.add(Utils.getString(organsService.find(modStock.getInId()).getName()));
			strings.add(Utils.getString(modStock.getCardCount()));
			strings.add(Utils.getOptionsIntegerName(OptionsValue.INVENTORYCHANGE_STATUS, modStock.getStatus()));
			strings.add(DateTimeTool.dateFormat("", modStock.getCreateTime()));
			strings.add(DateTimeTool.dateFormat("", modStock.getAuditTime()));
			String operation ="";
			if(us.getUserLevel()==0){
				if(Utils.checkPermission("sy-2201-01")){
					operation += "<a href=javascript:sureInStock('"+modStock.getId()+"','"+Utils.getOptionsIntegerName(OptionsValue.INVENTORYCHANGE_STATUS, modStock.getStatus())+"','"+1+"') title='查看'>"+Globals.IMG_VIEW+"</a>&nbsp;";
				}
				if(modStock.getStatus()==0&&modStock.getFlag()==0){
					if(Utils.checkPermission("sy-2201-05")){
						operation += "<a href=javascript:sureInStock('"+modStock.getId()+"','"+Utils.getOptionsIntegerName(OptionsValue.INVENTORYCHANGE_STATUS, modStock.getStatus())+"') title='确认入库'>"+Globals.IMG_AUDIT+"</a>&nbsp;";
					}
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
	 *@Title:addUI
	 *@Description:[添加]按钮访问方法--跳转到新增入库信息界面
	 *@param:@return
	 *@return:String
	 * @throws Exception 
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public String addUI() throws Exception{
		this.setMethod("addSave");
		Query query = heService.queryCardBinList(null);
		List<CardBIN> binList = query.getResultList();
		List<OptionsString> binsList = new ArrayList<OptionsString>();
		for(CardBIN cardBin:binList){
			OptionsString cardBinOptions = new OptionsString();
			cardBinOptions.setKey(cardBin.getBinId());
			cardBinOptions.setValue(cardBin.getBinId()+"("+cardBin.getBinName()+")");
			binsList.add(cardBinOptions);
		}
		this.getRequest().setAttribute("binsList", binsList);
		return "input";
	}
	/**
	 *@Title:addSave
	 *@Description:保存总部入库信息 --添加入库信息操作
	 *             1.插入库存变动表信息
	 *             2.插入变动明细表信息
	 *             3.更改卡号表状态为：2:待确认
	 *@param:@return
	 *@return:String
	 *@author:謝
	 * @throws Exception 
	 *@thorws:
	 */
	public String addSave() throws Exception{
		
		ReturnDTO dto = heService.addModStockInfo(modStockDTO,1);
		functionsService.saveFunction("总部入库", 1, "增加总部入库：");
		this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url","stock/headquin!list");
		return SUCCESS;
	}
	/**
	 *@Title:loadBeginCardNo
	 *@Description:加载起始卡号--添加入库信息[刷新]按钮触发
	 *@param:
	 *@return:void
	 * @throws IOException 
	 *@thorws:
	 */
	public void loadBeginCardNo() throws IOException{
		String cardBinNo = heService.loadBeginCardNo(modStockDTO.getCardBinNo(),null);
		HttpServletResponse response =
		         (HttpServletResponse)ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		Gson gson = new Gson();			
		cardBinNo = gson.toJson(cardBinNo).toString();
		response.setHeader("Cache-Control", "no-cache");
		  response.setCharacterEncoding("UTF-8");
		  response.getWriter().write(cardBinNo);
		  response.getWriter().flush();
		  response.getWriter().close();
	}
	
	/**
	 *@Title:appendCardNo
	 *@Description:获取要添加的卡号信息
	 *@param:
	 *@return:void
	 *@author:谢洪飞
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public String appendCardNo(){
		ReturnDTO dto = new ReturnDTO();
		String[] endNos = modStockDTO.getEndNoStr().split(";");
		List<String> strList = new ArrayList<String>();
		for(int i=0;i<endNos.length;i++){//遍历所有结束卡号
			if(StringUtils.isNotBlank(endNos[i])){
				CardNo cardNo = cardNoService.find(endNos[i]);//根据结束卡号，查询卡号信息
				if(cardNo.getCardBIN().getBinId().equals(modStockDTO.getCardBinNo())){
			   	   //如果此结束卡号的卡BIN与页面所要添加的卡BIN相同，则将卡号添加到集合中
				   strList.add(endNos[i]);
			     }
			  }
		 }
		//判断起始卡号是否存在于已添加的卡号列表中
		if(strList.size()>0){
			String max = Collections.max(strList);//获取页面上的最大卡号
			if(modStockDTO.getBeginCardNo().compareTo(max)<=0){
				//如果起始卡号小于等于该卡BIN类型的结束卡号最大号
				dto.setMsg("该批要添加的卡号已存在或部分存在于以添加的列表中!");
				Utils.printInfo(dto);
				return null;
			}
		}
		
		//查询要添加的卡号信息
		Query query = heService.appendCardNo(modStockDTO.getBeginCardNo(),
				                             modStockDTO.getCardCount(), modStockDTO.getCardBinNo(),0);
		List<CardNo> cardNoList = query.getResultList();
		if(cardNoList.size()<modStockDTO.getCardCount()){
			dto.setMsg("要添加卡的数量为： "+modStockDTO.getCardCount()+" 张\n实际可添加的卡数量： "+cardNoList.size()+" 张\n请确认信息后进行入库添加!");
			Utils.printInfo(dto);
			return null;
		}
		else{
			CardBIN cardBIN = cardBinService.find(modStockDTO.getCardBinNo());
			ModStockDTO modDto = new ModStockDTO();
			modDto.setCardBinNo(cardBIN.getBinId());
			modDto.setBinName(cardBIN.getBinName());
			modDto.setBeginCardNo(cardNoList.get(0).getCardNo());
			modDto.setEndNo(cardNoList.get(cardNoList.size()-1).getCardNo());
			modDto.setCardCount(cardNoList.size());
			dto.setObj(modDto);
			dto.setFlag(true);
		}
		return Utils.printInfo(dto);
	}
	
	/**
	 *@Title:stockDetial
	 *@Description:获取要[审核入库/查询]的卡号表信息，审核/查询窗口[查询]按钮触发
	 *@param:@return
	 *@return:String
	 *@author:謝
	 *@thorws:
	 */
	public String stockDetial(){
		List<String> cardnoList = new ArrayList<String>();
		ReturnDTO printDto = new ReturnDTO();
		ReturnDTO dto = heService.stockDetial(modStockDTO);
		
        if(modStockDTO.getQueryType()==1){//查询全部
			cardnoList = (List<String>) dto.getObj();
			printDto.setObj(cardnoList);
		}
		else{//单卡查询
			ModStockDetailDTO modStoDetDTO = (ModStockDetailDTO) dto.getObj();
			/*Utils.getOptionsIntegerName(OptionsValue.INVENTORYCHANGE_STATUS, modStock.getStatus());*/
			modStoDetDTO.setStockStatusDesc(Utils.getOptionsIntegerName(OptionsValue.INVENTORYCHANGE_STATUS, modStoDetDTO.getStockStatus()));
			modStoDetDTO.setStockFlagDesc(Utils.getOptionsIntegerName(OptionsValue.INVENTORYCHANGE_FLAG, modStoDetDTO.getStockFlag()));
			printDto.setObj(modStoDetDTO);
		}
        dto.setFlag(true);
		return Utils.printInfo(printDto);
	}
	
	/**
	 *@throws Exception 
	 * @Title:sureStockIn
	 *@Description:[确认入库]操作触发，将该批卡入库
	 *              1.更改入库变动信息表中的入库状态为：从0:未入库 → 1:已入库
	 *              2.更改卡号表中的入库状态为：从2:待确认 → 3:已入库
	 *              3.插入库存信息表
	 *              4.新增卡表信息
	 *              5.新增账户表信息
	 *@Param:@return
	 *@Return:String
	 *@Throws:
	 */
	public String sureStockIn() throws Exception{
		ReturnDTO dto = heService.sureStockIn(modStockDTO);
		ModStock modStock = (ModStock) dto.getObj();
		if(dto.getFlag()){
			
			dto.setMsg("入库操作完成。\n本次入库流水号："+modStockDTO.getId()+"\n本次入库卡BIN："+modStock.getCardBin().getBinName()+"\n本次入库卡数量："+modStock.getCardCount());
		}
		else{
			dto.setMsg("批次号为:"+modStockDTO.getId()+" 的卡入库失败!");
		}
		dto.setObj(null);
		return Utils.printInfo(dto);
	}

}
