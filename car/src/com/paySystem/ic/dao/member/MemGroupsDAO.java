package com.paySystem.ic.dao.member;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.MemGroups;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemGroupsDTO;
import com.paySystem.ic.web.ui.OptionsString;

public interface MemGroupsDAO extends DAO<MemGroups> {

	public static final String MEMGROUPSSERVICE = "MemGroupsService";

	/***
	 * 查询所有会员群组信息
	 * 
	 * @Title:queryResult
	 *@param:@param firstindex
	 *@param:@param maxresult
	 *@param:@param memGroupsDTO
	 *@param:@param orderby
	 *@param:@return
	 *@param:@throws Exception
	 *@return:QueryResult<MemGroups>
	 *@author:解文侠
	 *@thorws:
	 */
	public QueryResult<MemGroups> queryResult(int firstindex, int maxresult,
			MemGroupsDTO memGroupsDTO, LinkedHashMap<String, String> orderby)
			throws Exception;

	/***
	 * 添加群组会员
	 * 
	 * @Title:save
	 *@param:@param memGroupsDTO
	 *@return:void
	 *@author:解文侠
	 *@thorws:
	 */

	public void save(MemGroupsDTO memGroupsDTO);

	/***
	 * 修改群组会员
	 * 
	 * @Title:update
	 *@param:@param memGroupsDTO
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:解文侠
	 *@thorws:
	 */

	public ReturnDTO update(MemGroupsDTO memGroupsDTO);

	/***
	 * 删除群组会员
	 * 
	 * @Title:delete
	 *@param:@param ids
	 *@return:void
	 *@author:解文侠
	 *@thorws:
	 */

	public void delete(String[] ids);

	/***
	 * 查询群组会员id
	 * 
	 * @Title:queryAll
	 *@param:@param orgId
	 *@param:@return
	 *@return:List<MemGroupsDTO>
	 *@author:解文侠
	 *@thorws:
	 */

	public List<MemGroupsDTO> queryAll(String orgId);

	/***
	 * 返回会员表与会员群组表不重复的数据
	 * 
	 * @Title:getMemberByGroupId
	 *@param:@param groupId
	 *@param:@return
	 *@return:List<Member>
	 *@author:解文侠
	 *@thorws:
	 */
	public List<OptionsString> getMemberByGroupId(Integer memId);

}
