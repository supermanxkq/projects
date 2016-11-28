package com.paySystem.ic.web.action.member;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.member.AccDisPnt;
import com.paySystem.ic.bean.member.CardLevel;
import com.paySystem.ic.service.base.MerchantsService;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.member.AccDisPntService;
import com.paySystem.ic.service.member.CardLevelService;
import com.paySystem.ic.service.member.MerDisPntService;
import com.paySystem.ic.service.member.UpgradeService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.CardLevelDTO;
@Controller("/cardLevel/cardLevel")
@Scope("prototype")
public class CardLevelAction  extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Resource CardLevelService cardLevelServiceImpl;
	@Resource FunctionsService functionsService;
	@Resource OrgansService organsServiceImpl;
	@Resource MerchantsService merchantsServiceImpl;
	@Resource AccDisPntService accDisPntServiceImpl;
	@Resource UpgradeService upgradeServiceImpl;
	@Resource MerDisPntService merDisPntServiceImpl;
	private CardLevelDTO cardLevelDTO = new CardLevelDTO();
	private Merchants merchants=new Merchants();
	public CardLevelDTO getCardLevelDTO() {
		return cardLevelDTO;
	}
	public void setCardLevelDTO(CardLevelDTO cardLevelDTO) {
		this.cardLevelDTO = cardLevelDTO;
	}
	
	public Merchants getMerchants() {
		return merchants;
	}
	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}
	/**
	 * 列表页面
	 * @version 2011-9-8 下午08:50:23
	 * @return
	 * @throws Exception 
	 */
	public String list() {
		this.getRequest().setAttribute("statusValues",OptionsValue.STATE_STATUS );
			return "list";
	}
	/**
	 * 异步获取列表数据
	 * 	ps:分页查询所有卡等级信息
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {
		
		/**
		 * 查询结果排序参数设定 
		 */
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		//判断排序参数是否有值
		if(StringUtils.isNotBlank(this.getOrderProperty())
				&&StringUtils.isNotBlank(this.getOrderDirection()))
		{
			orderby.put(this.getOrderProperty(),this.getOrderDirection() );
		}else{
			//如果页面没有要求排序方式，则设置按照卡等级编号排序
			orderby.put("levelId", "desc");
		}
		//返回结果
		QueryResult<CardLevel> queryResult = cardLevelServiceImpl.queryResult((cardLevelDTO.getPage() - 1) * pageNum,pageNum,cardLevelDTO,orderby);
		List<CardLevel> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for(int i = 0;i <list.size();i++){
			//向页面赋值
			CardLevel cardLevel = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i+1));
			strings.add(Utils.getString(cardLevel.getLevelName()));
			if(cardLevel.getLevelSign()==1&&cardLevel.getOrgans()!=null){
			strings.add(Utils.getString(cardLevel.getOrgans().getName()));
			strings.add(Utils.getString(cardLevel.getOrgans().getOrganId()));
			}else if(cardLevel.getLevelSign()==2&&cardLevel.getMerchants()!=null){
				strings.add(Utils.getString(cardLevel.getMerchants().getMerName()));
				strings.add(Utils.getString(cardLevel.getMerchants().getMerId()));
			}
			//卡等级状态
			strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS,cardLevel.getStatus()));
			//卡等级加入时间
			strings.add(DateTimeTool.dateFormat("yyyy-MM-dd", cardLevel.getUpdateTime()));
			//相关操作赋值
			String operation = "";
			if(Utils.checkPermission("sy-1302-03")&&Utils.getUserSession().getUserLevel()==0){
				operation += "<a href=cardLevel/cardLevel!editUI?cardLevelDTO.levelId="+cardLevel.getLevelId()+" title='修改'>"+Globals.IMG_EDIT+"</a>&nbsp;";
			}
			if(Utils.checkPermission("sy-1302-03")&&Utils.getUserSession().getUserLevel()==1){
				operation += "<a href=cardLevel/cardLevel!checkUI?cardLevelDTO.levelId="+cardLevel.getLevelId()+" title='查看'>"+Globals.IMG_VIEW+"</a>&nbsp;";
			}
			strings.add(operation);
			lists.add(strings);
		}
		PageView pageView = new PageView(cardLevelDTO.getPage(),queryResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists,getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}
	/**
	 * 进入修改卡等级信息
	 * @return
	 */
	public String editUI(){
		//设置form中表单的方法
		this.setMethod("editSave");
		CardLevel cardLevel = cardLevelServiceImpl.find(cardLevelDTO.getLevelId());
		List<AccDisPnt> accDisPntList = accDisPntServiceImpl.query(cardLevelDTO.getLevelId());
	//	Upgrade upgrade =  upgradeServiceImpl.search(cardLevelDTO.getLevelId());
		String flag = pubMethod(cardLevel,accDisPntList);
		if(flag.equals("input")){
			return INPUT;
		}else{
			return ERROR;
		}
	}
	/**编辑保存*/
	public String editSave(){
		try {
			if(cardLevelDTO!=null){
				ReturnDTO dto = cardLevelServiceImpl.update(cardLevelDTO);
				accDisPntServiceImpl.update(cardLevelDTO);
			//	merDisPntServiceImpl.update(cardLevelDTO);
//				upgradeServiceImpl.update(cardLevelDTO);
				this.getSession().removeAttribute("cardSignValues");
				this.getSession().removeAttribute("statusValues");
				if(dto.getFlag()){
					functionsService.saveFunction("卡等级管理", 2, "修改卡等级："+cardLevelDTO.getLevelId());
					this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
					this.getRequest().setAttribute("url","cardLevel/cardLevel!list");
					return SUCCESS;
				}else{
					this.getRequest().setAttribute("result",dto.getMsg());
					this.getRequest().setAttribute("url","cardLevel/cardLevel!list");
					return ERROR;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getSession().removeAttribute("cardSignValues");
		this.getSession().removeAttribute("statusValues");
		this.getSession().removeAttribute("levelUpValues");
		return ERROR;
	}
	
	/**添加页面信息*/
	public String addUI(){
		this.setMethod("addSave");
		cardLevelDTO.setUpdateTime(new Date());
		cardLevelDTO.setCpayDiscount(new BigDecimal(1));
		cardLevelDTO.setCppointRate(new BigDecimal(0));
		cardLevelDTO.setCrpointRate(new BigDecimal(0));
		cardLevelDTO.setSpayDiscount(new BigDecimal(1));
		cardLevelDTO.setSppointRate(new BigDecimal(0));
		cardLevelDTO.setSrpointRate(new BigDecimal(0));
		cardLevelDTO.setOrganId("");
		cardLevelDTO.setName("");
	//	cardLevelDTO.setUpper(0);
		HttpSession session =  this.getSession();
		session.setAttribute("cardSignValues", OptionsValue.USER_LEVEL1);
		session.setAttribute("statusValues", OptionsValue.STATE_STATUS);
		session.setAttribute("levelUpValues",OptionsValue.LEVELUPDOWN_WAY);//升降级规则设定
		
		return INPUT;
	}
	
	/**
	 * 添加卡等级信息
	 */
	public String  addSave(){
		cardLevelDTO.setLevelId(cardLevelServiceImpl.getCardLevelId());
		cardLevelDTO.setUpdateTime(new Date());
		if(cardLevelDTO!=null){
			if(cardLevelDTO.getLevelSign()==1){
				cardLevelDTO.setMerId("");
			}else{
				cardLevelDTO.setOrganId("");
			}
		cardLevelServiceImpl.save(cardLevelDTO);
		this.getSession().removeAttribute("cardSignValues");
		this.getSession().removeAttribute("statusValues");
		}
		functionsService.saveFunction("卡等级管理",1, "增加卡等级："+cardLevelDTO.getLevelId());
		this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url","cardLevel/cardLevel!list");
		this.getSession().removeAttribute("cardSignValues");
		this.getSession().removeAttribute("statusValues");
		this.getSession().removeAttribute("levelUpValues");
		return SUCCESS;
	}
	
	public String checkLevelName(){
		ReturnDTO dto = new ReturnDTO();
		boolean revFlag = false;
		String method = this.getMethod();
		String levelId = cardLevelDTO.getLevelId();
		String levelName = cardLevelDTO.getLevelName();
		revFlag = cardLevelServiceImpl.getCardLevelName(levelName,levelId,method);
		dto.setFlag(revFlag);
		return  Utils.printInfo(dto);
	}
	/***
	 * 
	 *@Title:checkUI
	 *@Description:查看卡等级
	 *@param:@return
	 *@return:String
	 *@author:井建国
	 *@thorws:
	 */
	public String checkUI(){
		ReturnDTO dto = new ReturnDTO();
		this.setMethod("checkUI");
		CardLevel cardLevel = cardLevelServiceImpl.find(cardLevelDTO.getLevelId());
		List<AccDisPnt> accDisPntList = accDisPntServiceImpl.query(cardLevelDTO.getLevelId());
		String flag = pubMethod(cardLevel,accDisPntList);
		if(flag.equals("input")){
			return INPUT;
		}else{
			return ERROR;
		}
	}
	/****
	 * 
	 *@Title:pubMethod
	 *@Description:查看UI和修改UI时调用
	 *@param:@param cardLevel
	 *@param:@param accDisPntList
	 *@param:@return
	 *@return:String
	 *@author:井建国
	 *@thorws:
	 */
	private String pubMethod(CardLevel cardLevel,List<AccDisPnt> accDisPntList){
		if(cardLevel!=null){
			cardLevelDTO.setLevelId(Utils.getString(cardLevel.getLevelId()));
			cardLevelDTO.setLevelName(Utils.getString(cardLevel.getLevelName()));
			if(cardLevel.getMerchants()==null){
				cardLevelDTO.setLevelSign(1);
				cardLevelDTO.setOrganId(Utils.getString(cardLevel.getOrgans().getOrganId()));
				cardLevelDTO.setName(Utils.getString(cardLevel.getOrgans().getName()));
			}else {
				cardLevelDTO.setLevelSign(2);
				cardLevelDTO.setMerId(Utils.getString(cardLevel.getMerchants().getMerId()));
				cardLevelDTO.setMerName(Utils.getString(cardLevel.getMerchants().getMerName()));
			}
				cardLevelDTO.setNewPoint(cardLevel.getNewPoint());
				cardLevelDTO.setStatus(cardLevel.getStatus());
				cardLevelDTO.setUpdateTime(new Date());
				cardLevelDTO.setDescr(Utils.getString(cardLevel.getDescr()));
				for (int i = 0; i < accDisPntList.size(); i++) {
					AccDisPnt accDisPnt = accDisPntList.get(i);
					if(accDisPnt.getCaccTypeId()==1){
						cardLevelDTO.setCaccDisPntId(Utils.getString(accDisPnt.getAccDisPntId()));
						cardLevelDTO.setCaccTypeId(accDisPnt.getCaccTypeId());
						cardLevelDTO.setCpayDiscount(accDisPnt.getCpayDiscount());
						cardLevelDTO.setCppointRate(accDisPnt.getCppointRate());
						cardLevelDTO.setCrpointRate(accDisPnt.getCrpointRate());
					}
					if(accDisPnt.getCaccTypeId()==2){
						cardLevelDTO.setSaccDisPntId(Utils.getString(accDisPnt.getAccDisPntId()));
						cardLevelDTO.setSaccTypeId(accDisPnt.getCaccTypeId());
						cardLevelDTO.setSpayDiscount(accDisPnt.getCpayDiscount());
						cardLevelDTO.setSppointRate(accDisPnt.getCppointRate());
						cardLevelDTO.setSrpointRate(accDisPnt.getCrpointRate());
					}
				}
//				cardLevelDTO.setUpgId(upgrade.getUpgId());
//				cardLevelDTO.setUpgType(upgrade.getUpgType());
//				cardLevelDTO.setUpper(upgrade.getUpper());

			HttpSession session =  this.getSession();
			session.setAttribute("cardSignValues", OptionsValue.USER_LEVEL1);
			session.setAttribute("statusValues", OptionsValue.STATE_STATUS);
			session.setAttribute("levelUpValues",OptionsValue.LEVELUPDOWN_WAY);//升降级规则设定
			
			return INPUT;
		}
		return ERROR;
	}
}
