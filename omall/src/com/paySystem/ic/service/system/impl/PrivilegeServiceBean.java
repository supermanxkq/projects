package com.paySystem.ic.service.system.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.system.Module;
import com.paySystem.ic.bean.system.Privilege;
import com.paySystem.ic.bean.system.Role;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.system.ModuleService;
import com.paySystem.ic.service.system.PrivilegeService;
import com.paySystem.ic.service.system.RoleService;
import com.paySystem.ic.web.dto.system.RolePrivilegeDTO;

@Service
public class PrivilegeServiceBean extends DaoSupport<Privilege> implements PrivilegeService {

	@Resource ModuleService moduleService;
	@Resource RoleService groupService;

	public void batchSave(List<Privilege> privileges) {
		/**
		 * 先判断是否存该模块和权限，若有只作更新操作
		 */
		for (Privilege privilege : privileges) {
			Module module = privilege.getModule();
			// System.out.println("模块:"+module.getId());
			if (moduleService.getModule(module.getId()) != null) {
				// System.out.println("更新:" + module.getId());
				Module mod = moduleService.getModule(module.getId());
				mod.setName(module.getName());
				mod.setLinkAddr(module.getLinkAddr());
				moduleService.update(mod);

				Privilege privi = find(privilege.getId());
				if (privi != null) {
					privi.setModule(module);
					privi.setName(privilege.getName());
					this.update(privilege);
				} else {
					this.save(privilege);
				}
			} else {
				// System.out.println("保存:" + module.getId());
				this.save(module);// 先保存Module
				this.save(privilege);// 再保存权限
			}
		}
	}

	/**
	 * 删除模块、权限
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@SuppressWarnings("unused")
	public void removeAllPrivilege() throws Exception{
		List<Privilege> privileges = this.getScrollData().getResultlist();
		// System.out.println("权限：" + privileges);
		for (Privilege sp : privileges) {
			sp.setModule(null);
			em.merge(sp);
		}
		List<Module> modules = moduleService.getScrollData().getResultlist();
		for (Module m : modules) {
			m.setParent(null);
			m.setChildModules(null);
			em.merge(m);
		}
		
		int i = em.createQuery("delete from Module").executeUpdate();
		// System.out.println("删除模块：" + i);
		List<Role> groups = groupService.getScrollData().getResultlist();
		for (Role pg : groups) {
			/** 删除角色与权限之间的关系 */
			/*i = em.createNativeQuery("delete from " + "s_role_privilege o where o.roleid=?1").setParameter(1, pg.getId()).executeUpdate();*/
			i = em.createNativeQuery("delete from " + "s_role_privilege  where roleid=?1").setParameter(1, pg.getId()).executeUpdate();
			// System.out.println("关系表：" + i);
		}
		i = em.createQuery("delete from Privilege").executeUpdate();
		// System.out.println("删除权限：" + i);
		this.clear();
	}


	/**
	 * 获取用户与角色关系表数据
	 */
	@SuppressWarnings("unchecked")
	public List<RolePrivilegeDTO> getRolePrivilege() {
		List<Object[]> employee_roles = em.createNativeQuery("select * from " + "s_role_privilege ").getResultList();
		List<RolePrivilegeDTO> empRoles = new ArrayList<RolePrivilegeDTO>();
		for (Object[] o : employee_roles) {
			//log.debug(((BigDecimal) o[0]).intValue());
			empRoles.add(new RolePrivilegeDTO((Integer) o[0],(String) o[1]));
		}
		return empRoles;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void batchSaveRolePrivilege(List<RolePrivilegeDTO> empRoleList) {
		for (RolePrivilegeDTO rolePrivilege : empRoleList) {
			if (this.find(rolePrivilege.getPrivilegeid()) != null)
				this.saveRolePrivilege(rolePrivilege.getRoleid(),rolePrivilege.getPrivilegeid());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void saveRolePrivilege(Integer groupid, String privilegeid) {
		em.createNativeQuery("insert into "+ "s_role_privilege(roleid, privilegeid) values(?1, ?2)")
			.setParameter(1, groupid).setParameter(2, privilegeid).executeUpdate();
	}
}
