package com.paySystem.ic.web.action.stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.CardBIN;
import com.paySystem.ic.bean.card.CardNo;
import com.paySystem.ic.bean.card.Cards;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.card.CardBINService;
import com.paySystem.ic.service.card.CardNoService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.stock.HeadQuinService;
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
import com.paySystem.ic.web.ui.OptionsString;

/**
 * @ClassName:OrgansStockAction
 * @Description:机构入库
 * @date: 2014-1-20上午11:38:29
 * @author: 谢洪飞
 * @version: V1.0
 */
@Controller("/stock/orgcard")
@Scope("prototype")
public class OrgansStockAction extends BaseAction {

	@Resource
	HeadQuinService heService;
	@Resource
	OrgansService organsService;
	@Resource 
	CardBINService cardBinService;
	@Resource
	CardNoService cardNoService;
	@Resource
	FunctionsService functionsService;
	@Resource
	OrganStockService organStockService;
	
	private static final long serialVersionUID = 665922725449076577L;
	
	private ModStockDTO modStockDTO = new ModStockDTO();
	
	public ModStockDTO getModStockDTO() {
		return modStockDTO;
	}

	public void setModStockDTO(ModStockDTO modStockDTO) {
		this.modStockDTO = modStockDTO;
	}

	/**
	 *@Title:list
	 *@Description:[机构领卡]菜单请求方法
	 *@param:@return
	 *@return:String
	 *@author:
	 */
	public String list(){
		/*modStockDTO.setBeginDate(DateTimeTool.dateFormat("yyyy-MM-dd",DateTimeTool.nDaysAgo(7, new Date())));
		modStockDTO.setEndDate(DateTimeTool.dateFormat("yyyy-MM-dd", new Date()));*/
		this.getRequest().setAttribute("invChangeValues",OptionsValue.INVENTORYCHANGE_STATUS );//状态
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
		case 0:
			return "list";
		case 1:
			return "list";
		case 2:
			return "intercepthtml";
		}
		return ERROR;
	}
	
	/**
	 *@Title:josnPageList
	 *@Description:异步请求查询方法
	 *@param:@return
	 *@return:String
	 *@author:謝
	 * @throws Exception 
	 */
	public String jsonPageList() throws Exception{
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
	
		QueryResult<ModStock> terResult = organStockService.queryStockByCond((modStockDTO.getPage() - 1) * pageNum,pageNum,modStockDTO,orderBy);
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
			
				if(Utils.checkPermission("sy-2202-01")){
					operation += "<a href=javascript:sureInStock('"+modStock.getId()+"','"+Utils.getOptionsIntegerName(OptionsValue.INVENTORYCHANGE_STATUS, modStock.getStatus())+"','"+1+"') title='查看'>"+Globals.IMG_VIEW+"</a>&nbsp;";
				}
				if(modStock.getStatus()==0&&us.getUserLevel()==1 //限定：1.只有机构级别操作员才能确认入库。2.变动表中状态为未入库的才能进行确认入库。3.只有入库机构为本机构的才可以进行入库操作
						&&(modStock.getInId()==us.getOrganId()||modStock.getInId().equals(us.getOrganId()))){
					if(Utils.checkPermission("sy-2202-05")){
						operation += "<a href=javascript:sureInStock('"+modStock.getId()+"','"+Utils.getOptionsIntegerName(OptionsValue.INVENTORYCHANGE_STATUS, modStock.getStatus())+"') title='确认入库'>"+Globals.IMG_AUDIT+"</a>&nbsp;";
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
		this.getRequest().setAttribute("organsValues",organsService.getOption() );//机构
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
	 *@Description:保存机构领卡信息
	 *@param:@return
	 *@return:String
	 *@author: 謝
	 *@throws Exception 
	 *@thorws:
	 */
	public String addSave() throws Exception{
		ReturnDTO dto = organStockService.addModStockInfo(modStockDTO);
		if(!dto.getFlag()){
			this.getRequest().setAttribute("result",this.getText("exist.no.notice"));
			this.getRequest().setAttribute("url","stock/orgcard!list");
			return ERROR;	
		}
		functionsService.saveFunction("机构领卡", 1, "增加机构领卡：");
		this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url","stock/orgcard!list");
		return SUCCESS;
	}
	
	/**
	 *@Title:loadBeginCardNo
	 *@Description:机构领卡-加载机构领卡起始卡号
	 *@param:@return
	 *@return:String
	 *@author:謝
	 * @throws IOException 
	 *@thorws:
	 */
	public void loadBeginCardNo() throws IOException{
		String cardBinNo = organStockService.loadBeginCardNo(modStockDTO.getCardBinNo(),modStockDTO.getOrganId(),0);
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
	 *@Title:loadCardBin
	 *@Description:根据机构号，加载Bin号
	 *@param:@return
	 *@return:String
	 *@author:謝
	 *@thorws:
	 */
	public String loadCardBin(){
		List<OptionsString> cardBinList = new ArrayList<OptionsString>();
		Query query = heService.queryCardBinList(modStockDTO.getOrganId());
		List<CardBIN> binList = query.getResultList();
		for(CardBIN bin : binList){
			cardBinList.add(new OptionsString(bin.getBinId(),bin.getBinName()));
		}
		return Utils.printInfo(cardBinList);
	}
	
	/**
	 *@Title:appendCardNo
	 *@Description:添加要出库的卡
	 *@param:@return
	 *@return:String
	 *@author:謝
	 *@thorws:
	 */
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
		Query query = organStockService.appendCardNo(modStockDTO);
		List<Cards> cardsList = query.getResultList();
		if(cardsList.size()<modStockDTO.getCardCount()){
			dto.setMsg("要添加出库的卡数量： "+modStockDTO.getCardCount()
					  +" 张\n实际可添加的卡数量： "+cardsList.size()+" 张\n请确认信息后进行入库添加!");
			Utils.printInfo(dto);
			return null;
		}
		else{
			CardBIN cardBIN = cardBinService.find(modStockDTO.getCardBinNo());
			ModStockDTO modDto = new ModStockDTO();
			modDto.setCardBinNo(cardBIN.getBinId());
			modDto.setBinName(cardBIN.getBinName());
			modDto.setBeginCardNo(cardsList.get(0).getCardNo());
			modDto.setEndNo(cardsList.get(cardsList.size()-1).getCardNo());
			modDto.setCardCount(cardsList.size());
			dto.setObj(modDto);
			dto.setFlag(true);
		}
		return Utils.printInfo(dto);
	}
	
	/**
	 *@Title:sureStockIn
	 *@Description:机构领卡确认入库 
	 *@Param:@return
	 *@Return:String
	 *@Throws:
	 */
	public String sureStockIn(){
		ReturnDTO dto = organStockService.sureStockIn(modStockDTO);
		ReturnDTO infoDto = new ReturnDTO();
		ModStock modStock = (ModStock) dto.getObj();
		if(dto.getFlag()){
			infoDto.setMsg("入库操作完成。\n本次入库流水号："+modStockDTO.getId()+"\n本次入库卡BIN："+modStock.getCardBin().getBinName()+"\n本次入库卡数量："+modStock.getCardCount());
		}
		else{
			infoDto.setMsg("批次号为:"+modStockDTO.getId()+" 的卡入库失败!");
		}
		/*dto.setObj(null);*/
		return Utils.printInfo(infoDto);
	}

}
