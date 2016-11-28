package com.paySystem.ic.web.action.member;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.Cards;
import com.paySystem.ic.bean.member.Amember;
import com.paySystem.ic.bean.member.CardLevel;
import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.service.account.AccountsService;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.card.CardBINService;
import com.paySystem.ic.service.card.CardsService;
import com.paySystem.ic.service.member.AmemberService;
import com.paySystem.ic.service.member.CardLevelService;
import com.paySystem.ic.service.member.MemCardService;
import com.paySystem.ic.service.member.MemberService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.account.AccountsDTO;
import com.paySystem.ic.web.dto.card.CardsDTO;
import com.paySystem.ic.web.dto.member.MemCardDTO;
import com.paySystem.ic.web.dto.member.MemberDTO;
import com.paySystem.ic.web.dto.system.UserSession;
@Controller("/memCard/memCard")
@Scope("prototype")
public class MemCardAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private MemCardDTO memCardDTO = new MemCardDTO();
	private MemberDTO memberDTO = new MemberDTO();;
	@Resource MemCardService memCardServiceImpl;
	@Resource CardsService cardsServiceImpl;
	@Resource FunctionsService functionsService;
	@Resource MemberService memberServiceImpl;
	@Resource AmemberService amemberServiceImpl;
	@Resource CardBINService cardBINServiceImpl;
	@Resource CardLevelService cardLeveServiceImpl;
	@Resource AccountsService accountsSerieImpl;
	@Resource OrgansService organsServiceImpl;
	public MemCardDTO getMemCardDTO() {
		return memCardDTO;
	}
	public void setMemCardDTO(MemCardDTO memCardDTO) {
		this.memCardDTO = memCardDTO;
	}
	
	public MemberDTO getMemberDTO() {
		return memberDTO;
	}
	public void setMemberDTO(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}
	/**
	 * 列表页面
	 * @version 2011-9-8 下午08:50:23
	 * @return
	 * @throws Exception 
	 */
	public String list() {
		this.getRequest().setAttribute("cstatusValues",OptionsValue.CARD_STATUS );
		return "list";
	}
	/**
	 * 异步获取列表数据
	 * 	ps:分页查询所有会员信息
	 * @return
	 * @throws Exception
	 */

	public String jsonPageList() throws Exception {
		/**
		 * 查询结果排序参数设定 
		 */
		/*LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		//判断排序参数是否有值
		if(StringUtils.isNotBlank(this.getOrderProperty())
				&&StringUtils.isNotBlank(this.getOrderDirection()))
		{
			orderby.put(this.getOrderProperty(),this.getOrderDirection() );
		}else{//如果页面没有要求排序方式，则设置按照会员编号排序
			orderby.put("cardNo", "desc");
		}
		//返回结果
		QueryResult<Cards> queryResult = memCardServiceImpl.queryResult((memberDTO.getPage() - 1) * pageNum,pageNum,memCardDTO,orderby);
		List<Cards> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		
		for(int i = 0;i <list.size();i++){
			Cards cards = list.get(i);
			if(cards.getMember()!=null){
			List<AccountsDTO> listAcc = accountsSerieImpl.findByCardNo(cards.getCardNo());
			//向页面赋值
			List<String> strings = new ArrayList<String>();
			//序号赋值
			strings.add(String.valueOf(i+1));
			//卡号赋值
			strings.add(Utils.getString(cards.getCardNo()));
			//会员姓名
			strings.add(Utils.getString(cards.getMember().getMemRealName()));
			
			for (int j = 0; j < listAcc.size(); j++) {
				if(listAcc.get(j).getAccTypeID()==0){
					BigDecimal balanceAnt = listAcc.get(j).getInAmt().add(listAcc.get(j).getUpAmt()).subtract(listAcc.get(j).getOutAmt()).subtract(listAcc.get(j).getDownAmt());
					//card余额
					strings.add(Utils.getString(balanceAnt));
					}
					if(listAcc.get(j).getAccTypeID()==1){
						BigDecimal balanceAnt = listAcc.get(j).getInAmt().add(listAcc.get(j).getUpAmt()).subtract(listAcc.get(j).getOutAmt()).subtract(listAcc.get(j).getDownAmt());
					//card积分
					strings.add(Utils.getString(balanceAnt));
					}
			}
			//卡等级
			strings.add(Utils.getString(cards.getCardLevel().getLevelName()));
			//卡状态
			strings.add(Utils.getOptionsIntegerName(OptionsValue.CARD_STATUS,cards.getStatus()));
			//开卡日期
			strings.add(DateTimeTool.dateFormat("yyyy-MM-dd", cards.getCreateTime()));
			//相关操作赋值
			String operation = "";
			if(Utils.checkPermission("sy-1304-03")){
				operation += "<a href=memCard/memCard!editUI?memCardDTO.cardNo="+cards.getCardNo()+" title='修改'>"+Globals.IMG_EDIT+"</a>&nbsp;";
			}
			strings.add(operation);
			lists.add(strings);
		}
	}
		PageView pageView = new PageView(memberDTO.getPage(),queryResult.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists,getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);*/
		return null;
	}

	
	/**添加页面信息   */
	public String addUI(){
		this.setMethod("addSave");
		UserSession us = Utils.getUserSession();
		String memId = "";// memberServiceImpl.getMemberId();
		memCardDTO.setMemId(memId);
		memCardDTO.setAmemId(memId);
		memCardDTO.setOrganId(us.getOrganId());
		memCardDTO.setOrgName(us.getOrganName());
		HttpSession session =  this.getSession();
		session.setAttribute("personTypeValues",OptionsValue.PERSONID_TYPE );//证件类型
		session.setAttribute("mstatusValues",OptionsValue.STATE_STATUS );//会员状态
		session.setAttribute("cstatusValues",OptionsValue.CARD_STATUS );//卡状态
		session.setAttribute("sexValues", OptionsValue.SEX_STATUS);//性别类型
		session.setAttribute("areaIdValues", OptionsValue.AREA_VALUE);//地区类型
		return INPUT;
	}
	/**
	 * 添加会员信息
	 */
	public String addSave() throws Exception{
		memCardDTO.setCstatus(7);
		memCardServiceImpl.save(memCardDTO);
		functionsService.saveFunction("会员卡信息管理",1, "增加会员卡信息："+memCardDTO.getCardNo());
		this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url","memCard/memCard!list");
		this.getSession().removeAttribute("personTypeValues");
		this.getSession().removeAttribute("mstatusValues");
		this.getSession().removeAttribute("cstatusValues");
		this.getSession().removeAttribute("sexValues");
		this.getSession().removeAttribute("areaIdValues");
		return SUCCESS;
	}
	/**
	 * 进入修改会员信息
	 * @return
	 * @throws Exception 
	 */
	public String editUI() {
		//设置form中表单的方法
		/*this.setMethod("editSave");
		if(memCardDTO!=null){
			Member member = memberServiceImpl.find(cardsServiceImpl.findByCardNo(memCardDTO.getCardNo()).getHoldmemId());
			CardsDTO cardsDTO = cardsServiceImpl.findByCardNo(memCardDTO.getCardNo());
			CardLevel cardLevel = cardLeveServiceImpl.find(Utils.getString(cardsDTO.getLevelId()));
			try {
				Amember amember = amemberServiceImpl.query(member.getMemId());
				List<AccountsDTO> accountsList = accountsSerieImpl.findByCardNo(memCardDTO.getCardNo());
				if(memCardDTO!=null){
					memCardDTO.setLevelId(Utils.getString(cardLevel.getLevelId()));
					memCardDTO.setLevelName(cardLevel.getLevelName());
					for (int i = 0; i < accountsList.size(); i++) {
							AccountsDTO accounts = accountsList.get(i);
						if(accounts.getAccTypeID()==0){
							BigDecimal balanceAnt = accounts.getInAmt().add(accounts.getUpAmt()).subtract(accounts.getOutAmt()).subtract(accounts.getDownAmt());
						//card余额
						memCardDTO.setBalanceAnt(balanceAnt);
						}
						if(accounts.getAccTypeID()==1){
							BigDecimal balancePnt = accounts.getInAmt().add(accounts.getUpAmt()).subtract(accounts.getOutAmt()).subtract(accounts.getDownAmt());      
						//card积分
						memCardDTO.setBalancePnt(balancePnt);
						}
					}
					String memId =member.getMemId();
					
					memCardDTO.setAreaId(member.getAreaId());
					memCardDTO.setMemRealName(Utils.getString(member.getMemRealName()));
					memCardDTO.setTelNo(Utils.getString(member.getTeleNo()));
					memCardDTO.setMemId(memId);
					memCardDTO.setMstatus(member.getStatus());
					memCardDTO.setEmail(Utils.getString(member.getEmail()));
					memCardDTO.setGroupId(Utils.getString(member.getGroupId()));
					//memCardDTO.setPerType(member.getPerType());
					memCardDTO.setPersonId(Utils.getString(member.getPersonId()));
					memCardDTO.setCardNo(cardsDTO.getCardNo());
					memCardDTO.setOrgName(cardsDTO.getOrganName());
					memCardDTO.setOrganId(cardsDTO.getOrganId());
					memCardDTO.setCstatus(cardsDTO.getStatus());
					memCardDTO.setBirthday(amember.getBirthday());
					memCardDTO.setCareer(amember.getCareer());
					memCardDTO.setCultDegree(amember.getCultDegree());
					memCardDTO.setAddress(amember.getAddress());
					memCardDTO.setResidZip(amember.getResidZip());
					memCardDTO.setCreateTime(member.getCreateTime());
					memCardDTO.setSex(member.getSex());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			HttpSession session =  this.getSession();
			session.setAttribute("personTypeValues",OptionsValue.PERSONID_TYPE );//证件类型
			session.setAttribute("mstatusValues",OptionsValue.STATE_STATUS );//会员状态
			session.setAttribute("cstatusValues",OptionsValue.CARD_STATUS );//卡状态
			session.setAttribute("sexValues", OptionsValue.SEX_STATUS);//性别类型
			session.setAttribute("areaIdValues", OptionsValue.AREA_VALUE);//地区类型
			return INPUT;
		}*/
		return ERROR;
	}
	
	
	
	/**编辑保存*///没有修改的数据还保持原来的状态
	public String editSave(){
		try {
			ReturnDTO dto = memCardServiceImpl.update(memCardDTO);
			if(dto.getFlag()){
				functionsService.saveFunction("会员卡管理", 2, "修改会员卡绑定："+memCardDTO.getMemId());
				this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
				this.getRequest().setAttribute("url","memCard/memCard!list");
				this.getSession().removeAttribute("personTypeValues");
				this.getSession().removeAttribute("mstatusValues");
				this.getSession().removeAttribute("cstatusValues");
				this.getSession().removeAttribute("sexValues");
				this.getSession().removeAttribute("areaIdValues");
				return SUCCESS;
			}else{
				this.getRequest().setAttribute("result",dto.getMsg());
				this.getRequest().setAttribute("url","memCard/memCard!list");
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}
	public String delete(){
		try { 
			//ID 值得是
			CardsDTO cardsDTO = cardsServiceImpl.findByCardNo(memCardDTO.getCardNo());
			cardsDTO.setHoldmemId("");
			cardsServiceImpl.update(cardsDTO);
			//merchantsService.delete(this.getId());
			functionsService.saveFunction("会员卡管理", 3, "解除会员绑定："+cardsDTO.getHoldmemId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
		
	}
	/***
	 * 前台获取卡余额、卡积分 、卡等级
	 * @throws Exception 
	 */
	
	public String setParams() throws Exception{
		ReturnDTO dto = new ReturnDTO();
		/*boolean flag = false;
		CardsDTO cardsDTO = cardsServiceImpl.queryByCardsNoShow(memCardDTO.getCardNoShow());
		String organId = Utils.getUserSession().getOrganId();
		if(cardsDTO.getOrganId().equals(organId)&&organId!=null){
			if(cardsDTO!=null&&cardsDTO.getMerId()==null&&cardsDTO.getStatus()==7){
				List<AccountsDTO> listAcc = accountsSerieImpl.findByCardNo(cardsDTO.getCardNo());
					//向页面赋值
					for (int j = 0; j < listAcc.size(); j++) {
						if(listAcc.get(j).getAccTypeID()==0){
							//card余额
							memCardDTO.setBalanceAnt(listAcc.get(j).getBalPoint());
							}
							if(listAcc.get(j).getAccTypeID()==1){
							//card积分
							memCardDTO.setBalancePnt(listAcc.get(j).getBalPoint());
							}
					}
					memCardDTO.setCstatus(cardsDTO.getStatus());
					if(cardsDTO.getLevelId()!=null){
					memCardDTO.setLevelName(cardLeveServiceImpl.find(cardsDTO.getLevelId()).getLevelName());
					memCardDTO.setLevelId(cardsDTO.getLevelId());
					dto.setObj(memCardDTO);
					flag=true;
					dto.setFlag(flag);
					}
			}else{
					dto.setFlag(flag);
			}
		}*/
		return Utils.printInfo(dto);
	}
}
