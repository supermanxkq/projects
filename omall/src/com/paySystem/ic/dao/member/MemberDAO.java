package com.paySystem.ic.dao.member;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.Members;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.member.MembersDTO;
import com.paySystem.ic.web.ui.OptionsString;

/**
 * @ProjectName:omall
 * @ClassName:MemberDAO
 * @Description:会员DAO
 * @date: 2014-11-27上午09:38:52
 * @author: 徐凯强
 * @version: V1.0
 */
public interface MemberDAO extends DAO<Members> {
	
	/** 设置static final 类型的依赖注入常量 **/
	public static final String MEMBERDAO = "MemberDAO";
	/**
	 *@MethodName:queryResult
	 *@Description:分页查询数据库中所有的会员信息
	 *@param fristindex首个分页参数
	 *@param pageNum每页含有多少个数据
	 *@param membersDTO会员数据传输对象
	 *@param orderBy排序参数
	 *@throws Exception抛出异常信息
	 *@Author:徐凯强
	 *@Date:2014-11-27上午09:29:11
	 */
	public QueryResult<MembersDTO> queryResult(int fristindex, int pageNum,
			MembersDTO membersDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;

	/**
	 *@Title:updateMembers
	 *@Description:禁用会员
	 *@param membersDTO会员数据传输对象
	 *@Return:void空
	 *@author:徐凯强
	 *@Date:2014-11-26下午03:18:31
	 */
	public void updateMembers(MembersDTO membersDTO);

	/**
	 *@Title:findMemOptionString
	 *@Description:获取不属于群组的会员和已经被选中的会员
	 *@Params:@param memDto
	 *@Params:@param groupId
	 *@Params:@param rightSelect
	 *@Params:@return
	 *@Return:List<OptionsString>
	 *@author:张军磊
	 *@Date:2014-11-19下午06:57:54
	 */
	public List<OptionsString> findMemOptionString(MembersDTO memDto,
			Integer groupId, String rightSelect);

	/**
	*@Title:findByOrgId
	*@Description:根据机构号查询
	*@Params:@param orgId
	*@Params:@return
	*@Return:List<MembersDTO>
	*@author:孟凡岭
	*@Date:2014-11-27上午11:51:10
	*/
	public List<MembersDTO> findByOrgId(String orgId) throws Exception;

}
