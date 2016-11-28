package com.paySystem.ic.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.system.Module;
import com.paySystem.ic.bean.system.Privilege;
import com.paySystem.ic.bean.system.Role;
import com.paySystem.ic.bean.system.User;
import com.paySystem.ic.service.account.AccKindsService;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.system.ModuleService;
import com.paySystem.ic.service.system.PrivilegeService;
import com.paySystem.ic.service.system.RoleService;
import com.paySystem.ic.service.system.UserService;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.MD5;
import com.paySystem.ic.web.dto.account.AccKindsDTO;
import com.paySystem.ic.web.dto.system.RolePrivilegeDTO;

@Controller("/system/init")
@Scope("prototype")
public class InitAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;
	public static Logger log = Logger.getLogger(InitAction.class);
	@Resource PrivilegeService privilegeService;
	@Resource RoleService roleService;
	@Resource ModuleService moduleService;
	@Resource UserService userService;
	@Resource OrgansService organsService;
	@Resource AccKindsService accKindsService;

	List<RolePrivilegeDTO> empRoleList = new ArrayList<RolePrivilegeDTO>();
	private String sign;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String init() {
		try {
			/** 初始化状态 */
			if (!StringUtils.isBlank(this.getSign())&& this.getSign().equals(Globals.ISINIT_SIGN)) {
				if (Globals.ISINIT_MODULE_PRIVILEGE) {
					log.info("初始化开始...");
					/**
					 * 用户权限初始化时，需先保住用户与角色之间的关系表 只要保存用户名与角色ID即可
					 */
					empRoleList = privilegeService.getRolePrivilege();
					
					// 判断是否要先清除模块和权限
					if (Globals.ISREMOVE_MODULE_PRIVILEGE) {
						privilegeService.removeAllPrivilege();
					}
					
					List<Module> modules = initSystemPrivilegeXML(this.getRealyPath("")+ "/WEB-INF/config/sy-config.xml");
					moduleService.batchSave(modules);
					privilegeService.batchSaveRolePrivilege(empRoleList);
					// 创建系统管理员角色
					if (Globals.ISCREATE_ADMIN_ROLE_USER) {
						//创建发卡机构
						Organs organs = organsService.find(Globals.MAIN_ORGANID);
						if(organs==null){
							organs = new Organs();
							organs.setOrganId(Globals.MAIN_ORGANID);
							organs.setName(Globals.MAIN_ORGANNAME);
							organs.setStatus(1);
							organs.setSysType(0);
							organs.setParentId("0");
							organs.setYearServSign(1);
							organs.setTrustFundSign(0);
							organs.setSettPeriod(1);
							organs.setLastSettTime(new Date());
							organs.setUpdateTime(new Date());
							organs.setCreateTime(new Date());
						}
						organsService.saveOrgan(organs);
						
						
						Organs organsFactory = organsService.find(Globals.FACTORY_ORGANID);
						if(organsFactory==null){
							organsFactory = new Organs();
							organsFactory.setOrganId(Globals.FACTORY_ORGANID);
							organsFactory.setName(Globals.FACTORY_ORGANNAME);
							organsFactory.setStatus(1);
							organsFactory.setSysType(0);
							organsFactory.setParentId("0");
							organsFactory.setYearServSign(1);
							organsFactory.setTrustFundSign(0);
							organsFactory.setSettPeriod(1);
							organsFactory.setLastSettTime(new Date());
							organsFactory.setUpdateTime(new Date());
							organsFactory.setCreateTime(new Date());
						}
						organsService.saveOrgan(organsFactory);
						
						//创建角色
						Role role = roleService.findBycode(Globals.ROLE_CODE);
						if (role == null) {
							role = new Role();
						}
						role.setCode(Globals.ROLE_CODE);
						role.setName(Globals.ROLE_NAME);
						role.setOrganId(Globals.MAIN_ORGANID);
						role.setStatus(1);
						List<Privilege> privilegelist = privilegeService.findByJpl("from Privilege");
						for (Privilege privilege : privilegelist) {
							role.getPrivileges().add(privilege);
						}
						roleService.save(role);
						//创建用户
						User user = userService.find(Globals.USER_NAME);
						if (user == null) {
							user = new User();
							user.setPassWord(MD5.MD5Encode(Globals.PASSWORD));
						}
						user.setUserName(Globals.USER_NAME);
						user.setRealName(Globals.USER_NAME);
						user.setUserLevel(0);
						user.setStatus(1);
						user.setEmail("994028591@qq.com");
						user.setTeleNo("15001164424");
						user.setCreateTime(new Date());
						user.setOrganId(organs.getOrganId());
						user.setKeyID(Globals.USER_KEYID);
						user.getRoles().add(role);
						userService.save(user);
						
						//创建索引
//						List<Article> articles = new Indexer().queryArticle();
//						new Indexer().index(Globals.INDEX_DIR, articles);
//						
						
						//油币账户
						AccKindsDTO accKindCash = accKindsService.findAccKinds(Globals.CASH_ACCKINDID);
						if(accKindCash==null){
							accKindCash = new AccKindsDTO();
							accKindCash.setKindId(Globals.CASH_ACCKINDID);
							accKindCash.setKindName(Globals.CASH_ACCKINDNAME);
							accKindCash.setAccSign(0);
							accKindCash.setSaleTimesLimit(0);
							accKindCash.setStatus(0);
							accKindsService.saveAccKinds(accKindCash);
						}
						
						//一般账户
						AccKindsDTO accKindSingles = accKindsService.findAccKinds(Globals.SINGLE_ACCKINDID);
						if (accKindSingles == null) {
							accKindSingles = new AccKindsDTO();
							accKindSingles.setKindId(Globals.SINGLE_ACCKINDID);
							accKindSingles.setKindName(Globals.SINGLE_ACCKINDNAME);
							accKindSingles.setAccSign(0);
							accKindSingles.setSaleTimesLimit(0);
							accKindSingles.setStatus(0);
							accKindsService.saveAccKinds(accKindSingles);
						}
						
						//积分账户
						AccKindsDTO accKindPoints = accKindsService.findAccKinds(Globals.POINTS_ACCKINDID);
						if(accKindPoints==null){
							accKindPoints = new AccKindsDTO();
							accKindPoints.setKindId(Globals.POINTS_ACCKINDID);
							accKindPoints.setKindName(Globals.POINTS_ACCKINDNAME);
							accKindPoints.setAccSign(0);
							accKindPoints.setSaleTimesLimit(0);
							accKindPoints.setStatus(0);
							accKindsService.saveAccKinds(accKindPoints);
						}
						
						//保证金账户
						AccKindsDTO accKindBails = accKindsService.findAccKinds(Globals.BAILS_ACCKINDID);
						if(accKindBails == null ){
							accKindBails = new AccKindsDTO();
							accKindBails.setKindId(Globals.BAILS_ACCKINDID);
							accKindBails.setKindName(Globals.BAILS_ACCKINDNAME);
							accKindBails.setAccSign(0);
							accKindBails.setSaleTimesLimit(0);
							accKindBails.setStatus(0);
							accKindsService.saveAccKinds(accKindBails);
						}
						
					}
					this.getRequest().setAttribute("result",this.getText("system.init.success"));
					log.info("初始化成功！");
				} else {
					this.getRequest().setAttribute("result",this.getText("system.init.not.open"));
				}
			} else {
				this.getRequest().setAttribute("result",this.getText("system.init.sign.error"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().setAttribute("result",this.getText("system.init.fail"));
		}
		this.getRequest().setAttribute("url","system/login!loginPage");
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public List<Module> initSystemPrivilegeXML(String fileName) throws DocumentException{
		List<Module> moluleList = new ArrayList<Module>();
		
		// 创建阅读器
		SAXReader saxReader = new SAXReader();
		// 创建document 对象
		Document document = saxReader.read(new File(fileName));
		// 取得根元素
		Element element = document.getRootElement();
		
		List<Element> mainmoduleElements = element.elements();
		for (Element mainmoduleElement : mainmoduleElements) {
			//父级模块mainModule
			Module mainModule = new Module(mainmoduleElement);
			moluleList.add(mainModule);
			List<Element> midmoduleElements = mainmoduleElement.elements();
			for (Element midmoduleElement : midmoduleElements) {
				//中级模块midModule
				Module midModule = new Module(midmoduleElement);
				midModule.setParent(mainModule);
				mainModule.getChildModules().add(midModule);
				List<Element> moduleElements = midmoduleElement.elements();
				for (Element moduleElement : moduleElements) {
					//模块module
					Module module = new Module(moduleElement);
					module.setParent(midModule);
					midModule.getChildModules().add(module);
					//权限privilege
					List<Element> privilegeElements = moduleElement.elements();
					for (Element privilegeElement : privilegeElements) {
						Privilege privilege = new Privilege(privilegeElement);
						privilege.setModule(module);
						module.getPrivileges().add(privilege);
					}
				}
			}
		}
		
/*		for (Module module : moluleList) {
			Set<Module> modules = module.getChildmodules();
			for (Module module2 : modules) {
				Set<Module> modules2 = module2.getChildmodules();
				for (Module module3 : modules2) {
					System.out.println(module3.getName());
					Set<Privilege> privileges = module3.getPrivileges();
					for (Privilege privilege : privileges) {
						System.out.println(privilege.getName()+privilege.getModule().getId());
					}
				}
			}
		}*/
		return moluleList;
	}
	
	public String addTestData(){
		return SUCCESS;
	}
	
	public static void main(String[] args) {
		InitAction initAction = new InitAction();
		try {
			initAction.initSystemPrivilegeXML("d://sy-config.xml");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
