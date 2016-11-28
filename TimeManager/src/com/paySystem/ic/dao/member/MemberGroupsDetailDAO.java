package com.paySystem.ic.dao.member;

import java.util.List;

import com.paySystem.ic.bean.member.MemberGroupsDetail;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemGroupsDTO;
import com.paySystem.ic.web.dto.member.MemberGroupsDetailDTO;

public interface MemberGroupsDetailDAO extends DAO<MemberGroupsDetail> {

	/***
	 * 添加群组会员
	 * 
	 * @Title:save
	 *@param:@param memberGroupsDetailDTO
	 *@return:void
	 *@author:解文侠
	 *@thorws:
	 */

	public void save(MemberGroupsDetailDTO memberGroupsDetailDTO);

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

	public ReturnDTO update(MemberGroupsDetailDTO memberGroupsDetailDTO);

	/***
	 * 更新群组会员
	 *@Title:updateSave
	 *@param:@param memGroupsDTO
	 *@param:@param right
	 *@return:void
	 *@author:解文侠
	 *@thorws:
	 */
	public void updateSave(MemGroupsDTO memGroupsDTO,List<String> right);
}
