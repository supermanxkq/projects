package com.paySystem.ic.dao.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.paySystem.ic.bean.member.MemGroups;
import com.paySystem.ic.bean.member.MemberGroupsDetail;
import com.paySystem.ic.bean.member.Members;
import com.paySystem.ic.dao.member.MemGroupsDAO;
import com.paySystem.ic.dao.member.MemberDAO;
import com.paySystem.ic.dao.member.MemberGroupsDetailDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemGroupsDTO;
import com.paySystem.ic.web.dto.member.MemberGroupsDetailDTO;

/***
 * MemberGroupsDetailDAO实现类
 * 
 * @author 解文侠
 * 
 */
@Component
public class MemberGroupsDetailDAOImpl extends DaoSupport<MemberGroupsDetail>
		implements MemberGroupsDetailDAO {
	@Resource
	MemGroupsDAO memGroupsDAO;
	@Resource
	MemberDAO memberDAO;
	/***
	 * 添加群组会员
	 * 
	 * @Title:save
	 *@param:@param memberGroupsDetailDTO
	 *@return:void
	 *@author:解文侠
	 *@thorws:
	 */
	public void save(MemberGroupsDetailDTO memberGroupsDetailDTO) {

	}

	/***
	 * 修改群组会员
	 * 
	 * @Title:update
	 *@param:@param memberGroupsDetailDTO
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:解文侠
	 *@thorws:
	 */
	public ReturnDTO update(MemberGroupsDetailDTO memberGroupsDetailDTO) {
		return null;
	}

	/***
	 * 更新群组会员
	 * 
	 * @see com.paySystem.ic.dao.member.MemberGroupsDetailDAO#updateSave(com.paySystem.ic.web.dto.member.MemGroupsDTO,
	 *      java.util.List)
	 * @Description:TODO
	 * @date: 2014-4-4下午02:36:03
	 * @author: 解文侠
	 * @version: V1.0
	 * @param memGroupsDTO
	 * @param rights
	 */
	public void updateSave(MemGroupsDTO memGroupsDTO, List<String> rights) {
		// TODO Auto-generated method stub
		/**将老数据删除**/
		em.createQuery(
				"delete from MemberGroupsDetail m where m.memGroups.groupId='"
						+ memGroupsDTO.getGroupId() + "'").executeUpdate();
		MemGroups group=memGroupsDAO.find(memGroupsDTO.getGroupId());
		for (int i = 0; i < rights.size(); i++) {
			if(!rights.get(i).equals("")){
				MemberGroupsDetail m=new MemberGroupsDetail();
				Members member=memberDAO.find(Integer.valueOf(rights.get(i).trim()));
				m.setMembers(member);
				m.setMemGroups(group);
				this.save(m);
			}
		}
		
	}
}
