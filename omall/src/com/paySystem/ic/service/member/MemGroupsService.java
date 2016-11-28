package com.paySystem.ic.service.member;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.MemGroups;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemGroupsDTO;
import com.paySystem.ic.web.ui.OptionsString;

public interface MemGroupsService extends DAO<MemGroups> {
	/***
	 * 设置static final 类型的依赖注入常量
	 */

	public static final String MEMGROUPSSERVICE = "memGroupsService";

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
	 * @param right
	 *@thorws:
	 */

	public ReturnDTO update(MemGroupsDTO memGroupsDTO, List<String> right);

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
	 * 查询群组id
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
	 * 查詢会员信息
	 * 
	 * @Title:getMemberByGroupId
	 *@param:@param groupId
	 *@param:@return
	 *@return:List<Member>
	 *@author:解文侠
	 *@thorws:
	 */
	public List<OptionsString> getMemberByGroupId(Integer memId);

	/**
	 * 保存群組会员
	 * 
	 * @Title:save
	 *@param:@param memGroupsDTO
	 *@param:@param rights
	 *@return:void
	 *@author:解文侠
	 *@thorws:
	 */
	public void save(MemGroupsDTO memGroupsDTO, List<String> rights);

	/**
	 *@Title:checkGroupsNameExsis
	 *@Description:检测群组名称是否存在
	 *@param memGroupsDTO会员群组数据传输对象
	 *@param method请求的方法名称
	 *@Return:boolean返回布尔类型值，存在返回true,不存在返回false
	 *@author:徐凯强
	 *@Date:2014-8-26下午08:00:12
	 */
	public boolean checkGroupsNameExsis(MemGroupsDTO memGroupsDTO, String method);

}
