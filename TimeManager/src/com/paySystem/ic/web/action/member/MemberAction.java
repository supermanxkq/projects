package com.paySystem.ic.web.action.member;

import java.math.BigDecimal;
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
import com.paySystem.ic.bean.base.ReceivingInfo;
import com.paySystem.ic.bean.member.Amember;
import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.service.base.MemIntegralService;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.base.ReceivingInfoService;
import com.paySystem.ic.service.member.AmemberService;
import com.paySystem.ic.service.member.MemCreditRuleService;
import com.paySystem.ic.service.member.MemberService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.DriverDTO;
import com.paySystem.ic.web.dto.base.MemIntegral;
import com.paySystem.ic.web.dto.member.AmemberDTO;
import com.paySystem.ic.web.dto.member.MemberDTO;
import com.paySystem.ic.web.dto.system.UserDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsInteger;

@Controller("/member/member")
@Scope("prototype")
public class MemberAction extends BaseAction {
	private static final long serialVersionUID = -3051940652987671139L;
	@Resource
	MemberService memberServiceImpl;
	@Resource
	AmemberService amemberServiceImpl;
    @Resource
    MemIntegralService memIntegralServiceImpl;
    @Resource
    ReceivingInfoService receivingInfoService;
    @Resource
    MemCreditRuleService memCreditService;
	@Resource
	FunctionsService functionsService;
	@Resource
	OrgansService organsService;
	private MemberDTO memberDTO = new MemberDTO();
	private AmemberDTO amemberDTO = new AmemberDTO();
	private DriverDTO driverDTO = new DriverDTO();
	private UserDTO userDTO = new UserDTO();

	public AmemberDTO getAmemberDTO() {
		return amemberDTO;
	}

	public void setAmemberDTO(AmemberDTO amemberDTO) {
		this.amemberDTO = amemberDTO;
	}

	public DriverDTO getDriverDTO() {
		return driverDTO;
	}

	public void setDriverDTO(DriverDTO driverDTO) {
		this.driverDTO = driverDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public MemberDTO getMemberDTO() {
		return memberDTO;
	}

	public void setMemberDTO(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}

	/**
	 * 列表页面
	 * 
	 * @version 2011-9-8 下午08:50:23
	 * @return
	 * @throws Exception
	 */
	public String list() {
		UserSession us=Utils.getUserSession();
		if(us.getUserLevel()==0){
		this.getRequest().setAttribute("statusValues",
				OptionsValue.STATE_STATUS);
		List<OptionsInteger> memGradeValues = new ArrayList<OptionsInteger>();
        memGradeValues.addAll(memCreditService.getMemGradeOption());
        this.getRequest().setAttribute("memGradeValues", memGradeValues);
		memberDTO.setStatus(1);
		return "list";
		}else{
			return "intercepthtml";
		}
	}

	/**
	 * 异步获取列表数据 ps:分页查询所有会员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {
		/**
		 * 页面参数
		 */
		/**
		 * 查询结果排序参数设定
		 */
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		// 判断排序参数是否有值
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {// 如果页面没有要求排序方式，则设置按照会员编号排序
			orderby.put("memId", "desc");
		}
		UserSession us = Utils.getUserSession();
		// 返回结果
		QueryResult<Member> queryResult = memberServiceImpl.queryResult(
				(memberDTO.getPage() - 1) * pageNum, pageNum, memberDTO,
				orderby);
		List<Member> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			// 向页面赋值
			Member member = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));
			strings.add(Utils.getString(member.getMemId()));
			strings.add(Utils.getString(member.getMemRealName()));
			strings.add(Utils.getString(member.getTeleNo()));
			strings.add(Utils.getString(member.getMemGrade().getGradeName()));
			// 会员状态
			strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUSD,
					member.getStatus()));
			// 会员加入时间
			strings.add(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", member
					.getCreateTime()));
			// 相关操作赋值
				String operation = "";
				
					if (Utils.checkPermission("sy-1301-01")) {
						operation += "<a href=member/member!checkUI?memberDTO.memId="
							+ member.getMemId()
							+ " title='查看'>"
							+ Globals.IMG_VIEW + "</a>&nbsp;";
					}
					
					if (Utils.checkPermission("sy-1301-03")) {
						operation += "<a href=member/member!editUI?memberDTO.memId="
								+ member.getMemId()
								+ " title='修改'>"
								+ Globals.IMG_EDIT + "</a>&nbsp;";
					}
				
				strings.add(operation);

			lists.add(strings);
		}

		PageView pageView = new PageView(memberDTO.getPage(), queryResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * 进入修改会员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception {
		// 设置form中表单的方法
		this.setMethod("editSave");
		if (memberDTO != null) {
			String memId = memberDTO.getMemId();
			Member member = memberServiceImpl.find(memId);
			//MemIntegral memIntegral=memIntegralServiceImpl.queryByMemId(memId);
			if (memberDTO != null) {
				memberDTO.setMemRealName(Utils.getString(member
						.getMemRealName()));
				memberDTO.setTeleNo(Utils.getString(member.getTeleNo()));
				memberDTO.setGradeName(Utils.getString(member.getMemGrade().getGradeName()));
				memberDTO.setStatus(member.getStatus());
				memberDTO.setSex(member.getSex());
				memberDTO.setPersonId(Utils.getString(member.getPersonId()));
				memberDTO.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",
						new Date()));
				memberDTO.setEmail(Utils.getString(member.getEmail()));
//				if(memIntegral==null||("").equals(memIntegral.getAvailableAmt())
//						||null==memIntegral.getAvailableAmt()){
//					memberDTO.setAvailableAmt(new BigDecimal(0.00));
//				}else{
//					memberDTO.setAvailableAmt(memIntegral.getAvailableAmt());
//				}
				
				//memberDTO.setAvailableAmt(memIntegral.getAvailableAmt());
				// memberDTO.setMemId(Utils.getString(member.getMemId()));
				/*memberDTO.setAreaId(Utils.getString(member.getAreaId()));
				memberDTO.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd",
						new Date()));
				memberDTO.setEmail(Utils.getString(member.getEmail()));
				memberDTO.setGroupId(Utils.getString(member.getGroupId()));
				memberDTO.setMemRealName(Utils.getString(member
						.getMemRealName()));
				memberDTO.setOrganId(member.getOrgans().getOrganId());
				memberDTO.setName(organsService.find(
						member.getOrgans().getOrganId()).getName());
				memberDTO.setPersonId(Utils.getString(member.getPersonId()));
				memberDTO.setSex(member.getSex());
				memberDTO.setStatus(member.getStatus());
				// memberDTO.setCardType(member.getCardType());
				memberDTO.setCarType(member.getCarType());
				memberDTO.setDriverNo(member.getDriverNo());
				memberDTO.setCardType(member.getCardType());
				memberDTO.setTeleNo(Utils.getString(member.getTeleNo()));
				memberDTO.setUpdateTime(DateTimeTool.dateFormat("yyyy-MM-dd",
						new Date()));*/
			}
			Amember amember = amemberServiceImpl.query(memId);
			if (amember != null) {
				amemberDTO.setAmemId(Utils.getString(memId));
				amemberDTO.setCultDegree(Utils.getString(amember
						.getCultDegree()));
				amemberDTO.setMemName(Utils.getString(amember.getMemName()));
				amemberDTO.setPwd(Utils.getString(amember.getPwd()));
				amemberDTO.setAddress(Utils.getString(amember.getAddress()));
				amemberDTO.setResidZip(Utils.getString(amember.getResidZip()));
				amemberDTO.setBirthday(DateTimeTool.dateFormat("yyyy-MM-dd",
						amember.getBirthday()));
				amemberDTO.setCareer(Utils.getString(amember.getCareer()));
				amemberDTO.setCertExTime(amember.getCertExTime());
			}
/*			Drivers drivers = driverServiceImpl.query(memId);
			if (drivers != null) {
				driverDTO.setMemId(Utils.getString(memId));
				// driverDTO.setDriverId(getId());
				driverDTO.setBank(Utils.getString(drivers.getBank()));
				driverDTO.setBankAccount(Utils.getString(drivers
						.getBankAccount()));
				driverDTO.setCarNumber(Utils.getString(drivers.getCarNumber()));
				driverDTO.setContacts(Utils.getString(drivers.getContacts()));
				driverDTO.setContractNo(Utils
						.getString(drivers.getContractNo()));

				// driverDTO.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd",
				// drivers.getCreateTime()));
				driverDTO.setDriverNo(Utils.getString(drivers.getDriverNo()));
				driverDTO.setOperId(Utils.getString(drivers.getOperId()));
				driverDTO.setStatus((driverDTO.getStatus()));
				driverDTO.setSettlement(Utils
						.getString(drivers.getSettlement()));
				driverDTO.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd",
						memberDTO.getCreateTime()));
			}*/

			this.getRequest().setAttribute("statusValues",
					OptionsValue.STATE_STATUSD);// 状态
			this.getRequest()
					.setAttribute("sexValues", OptionsValue.SEX_STATUS);
			return INPUT;
		}
		return ERROR;
	}

	/** 编辑保存 */
	public String editSave() {
		try {
			ReturnDTO dto = memberServiceImpl.update(memberDTO, amemberDTO,
					driverDTO);
			if (dto.getFlag()) {
				functionsService.saveFunction("会员管理", 2, "修改会员："
						+ memberDTO.getMemId());
				this.getRequest().setAttribute("result",
						this.getText("operation.success.notice"));
				this.getRequest().setAttribute("url", "member/member!list");
				return SUCCESS;
			} else {
				this.getRequest().setAttribute("result", dto.getMsg());
				this.getRequest().setAttribute("url", "member/member!list");
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	/**
	 * 删除会员
	 * 
	 * @version 2011-9-10 下午04:23:43
	 */
	public String delete() {
		try {
			// ID 值得是
			Member member = memberServiceImpl.find(this.getId());
			member.setStatus(9);
			memberServiceImpl.update(member);
			// merchantsService.delete(this.getId());
			functionsService.saveFunction("会员管理", 3, "删除会员："
					+ member.getMemId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

	/** 添加页面信息 */
	public String addUI() {
		this.setMethod("addSave");
		UserSession us = Utils.getUserSession();
		// String memId = memberServiceImpl.getMemberId();
		memberDTO.setOrganId(us.getOrganId());
		memberDTO.setName(us.getOrganName());
		// memberDTO.setMemId(memId);
		this.getRequest().setAttribute("personTypeValues",
				OptionsValue.PERSONID_TYPE);// 证件类型
		this.getRequest().setAttribute("statusValues",
				OptionsValue.STATE_STATUS);// 状态
		this.getRequest().setAttribute("sexValues", OptionsValue.SEX_STATUS);
		this.getRequest().setAttribute("carType", OptionsValue.CAR_TYPE);// 车型
		this.getRequest().setAttribute("areaIdValues", OptionsValue.AREA_VALUE);// 地区类型
		this.getRequest().setAttribute("recomType", OptionsValue.RECOM_TYPE);// 推荐人
		this.getRequest().setAttribute("cardType", OptionsValue.CARD_TYPE);// 会员卡类型
		return INPUT;
	}

	/**
	 * 添加会员信息
	 */
	public String addSave() {
		memberDTO.setUpdateTime(DateTimeTool.dateFormat("yyyy-MM-dd",
				new Date()));
		memberDTO.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd",
				new Date()));
		memberServiceImpl.save(memberDTO, amemberDTO, driverDTO);
		functionsService
				.saveFunction("会员管理", 1, "增加会员：" + memberDTO.getMemId());
		this.getRequest().setAttribute("result",
				this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url", "member/member!list");
		return SUCCESS;
	}

	/***
	 * 
	 *@Title:checkUI
	 *@Description:删除禁用的会员信息查看
	 *@param:@return
	 *@return:String
	 *@author:井建国
	 * @throws Exception
	 *@thorws:
	 */
	public String checkUI() throws Exception {
		this.setMethod("checkUI");
		//ReceivingInfoDTO receivingInfoDTO=new ReceivingInfoDTO();
		//receivingInfoDTO=
        List<ReceivingInfo> receivingInfoList=
        	            receivingInfoService.queryByMemId(memberDTO.getMemId());
		if (memberDTO != null) {
			String memId = memberDTO.getMemId();
			Member member = memberServiceImpl.find(memId);
			MemIntegral memIntegral=memIntegralServiceImpl.queryByMemId(memId);
			if (memberDTO != null) {
				memberDTO.setMemRealName(Utils.getString(member
						.getMemRealName()));
				memberDTO.setTeleNo(Utils.getString(member.getTeleNo()));
				memberDTO.setGradeName(Utils.getString(member.getMemGrade().getGradeName()));
				memberDTO.setStatus(member.getStatus());
				memberDTO.setSex(member.getSex());
				memberDTO.setPersonId(Utils.getString(member.getPersonId()));
				memberDTO.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",
						new Date()));
				memberDTO.setEmail(Utils.getString(member.getEmail()));
				if(memIntegral==null||("").equals(memIntegral.getAvailableAmt())
						||null==memIntegral.getAvailableAmt()){
					memberDTO.setAvailableAmt(new BigDecimal(0.00));
				}else{
					memberDTO.setAvailableAmt(memIntegral.getAvailableAmt());
				}
				memberDTO.setReceivingInfo(receivingInfoList);
			}
			this.getRequest().setAttribute("statusValues",
					OptionsValue.STATE_STATUSD);// 状态
			this.getRequest()
					.setAttribute("sexValues", OptionsValue.SEX_STATUS);
			return INPUT;
		}
		
		
		return INPUT;
	}

	/***
	 * 
	 *@Title:checkPersonId
	 *@Description:验证会员身份证是否重复
	 *@param:@return
	 *@return:String
	 *@author:井建国
	 *@thorws:
	 */
	public String checkPersonId() {
		ReturnDTO dto = new ReturnDTO();
		boolean revFlag = false;
		String personId = memberDTO.getPersonId();
		revFlag = memberServiceImpl.getMemberByPersonId(personId);
		dto.setFlag(revFlag);
		return Utils.printInfo(dto);
	}

	/***
	 * 
	 *@Title:memJsonPageList
	 *@Description:会员帮助页面
	 *@param:@return
	 *@return:String
	 *@author:井建国
	 * @throws Exception
	 *@thorws:
	 */
	public String memJsonPageList() throws Exception {
		/**
		 * 页面参数
		 */
		/**
		 * 查询结果排序参数设定
		 */
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		// 判断排序参数是否有值
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {// 如果页面没有要求排序方式，则设置按照会员编号排序
			orderby.put("memId", "desc");
		}

		// 返回结果
		QueryResult<Member> queryResult = memberServiceImpl.queryResult(
				(memberDTO.getPage() - 1) * pageNum, pageNum, memberDTO,
				orderby);
		List<Member> list = queryResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			// 向页面赋值
			Member member = list.get(i);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));

			strings.add(Utils.getString(member.getMemId()));
			strings.add(Utils.getString(member.getMemRealName()));
			// 会员状态
			strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS,
					member.getStatus()));
			// 相关操作赋值
			String operation = "";
			if (member.getStatus() == 1) {
				operation += "<a href=javascript:secMem('" + member.getMemId()
						+ "','" + member.getMemRealName() + "','"
						+ member.getTeleNo() + "') title='选择'>[选择]</a>&nbsp;";
			}
			strings.add(operation);
			lists.add(strings);
		}
		PageView pageView = new PageView(memberDTO.getPage(), queryResult
				.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView,
				"memQuery"));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * 
	 * @ClassName: MemberAction.java
	 * 
	 * @Description: 单张售卡调用的帮助窗口所写的方法
	 * 
	 * @author 赵巧鹤
	 * @param
	 * 
	 * @date 2014-6-3 下午03:12:36
	 */
	public String memSaleJsonPageList() throws Exception {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		// 判断排序参数是否有值
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {// 如果页面没有要求排序方式，则设置按照会员编号排序
			orderby.put("memId", "desc");
		}

		// 返回结果

		QueryResult<MemberDTO> queryResult = memberServiceImpl.queryMemberDTO(
				(memberDTO.getPage() - 1) * pageNum, pageNum, memberDTO,
				orderby);
		List<List<String>> lists = new ArrayList<List<String>>();
		
		List<MemberDTO> memberDTOList =queryResult.getResultlist();
		
		if(memberDTOList!=null){
		for (int i = 0; i < memberDTOList.size(); i++) {
			// 向页面赋值
			MemberDTO memberDTO = memberDTOList.get(i);
			// memberDTO.setStatus(1);
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(i + 1));

			strings.add(Utils.getString(memberDTO.getMemId()));
			strings.add(Utils.getString(memberDTO.getMemRealName()));
			// 会员状态
			strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS,
					memberDTO.getStatus()));
			// 相关操作赋值
			String operation = "";
			if (memberDTO.getStatus() == 1) {
				operation += "<a href=javascript:secSaleMem('"
						+ memberDTO.getMemId() + "','"
						+ memberDTO.getMemRealName() + "','"
						+ memberDTO.getPersonId() + "','"
						+ memberDTO.getEmail() + "','" + memberDTO.getTeleNo()
						+ "','" + memberDTO.getBirthday() + "','"
						+ memberDTO.getCareer() + "','"
						+ memberDTO.getCultDegree() + "','"
						+ memberDTO.getAddress() + "','"
						+ memberDTO.getResidZip() + "','"
						+ memberDTO.getAreaId() + "','" + memberDTO.getSex()
						+ "') title='选择'>[选择]</a>&nbsp;";
			}
			strings.add(operation);
			lists.add(strings);
		}
		}
		PageView pageView = new PageView(memberDTO.getPage(), memberDTOList
				.size());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView,
				"memQuery"));
		return Utils.printInfo(listInfoDTO);
	}

}
