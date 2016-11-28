package com.paySystem.ic.service.member;
import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.DriverDTO;
import com.paySystem.ic.web.dto.member.AmemberDTO;
import com.paySystem.ic.web.dto.member.MemberDTO;
import com.paySystem.ic.web.ui.OptionsString;
/**
 * 会员服务层接口
 * 
 * 
 * @author 井建国
 *
 */
public interface MemberService extends DAO<Member>{
	
	
	//设置static final 类型的依赖注入常量
	public static final String MEMBERSERVICE = "memberService";
	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            实体id
	 */
	public void save(MemberDTO memberDTO,AmemberDTO amemberDTO,DriverDTO driverDTO );
	/**
	 * 更新实体
	 * 
	 * @param entity
	 *            实体id
	 */
	public ReturnDTO update(MemberDTO memberDTO,AmemberDTO amemberDTO,DriverDTO driverDTO);

	/**
	 * 删除实体 ...任意个
	 * 
	 * @param entityClass
	 *            实体类
	 * @param entityids
	 *            实体id数组
	 */
	public void delete(String [] ids,String [] id);

	/**
	 * 获取会员实体
	 * 
	 * @param <T>
	 * @param entityClass
	 *            实体类
	 * @param entityId
	 *            实体id
	 * @return
	 */
	public Member find(String [] memids);

/**
 * 自动生成会员编号
 * @return
 */
	public String getMemberId();

	/***
	 * 条件查询
	 * @throws Exception 
	 */
	public QueryResult<Member> queryResult(int fristindex,int pageNum, MemberDTO memberDTO,LinkedHashMap<String, String> orderBy) throws Exception;   
	
	public boolean getMemberByPersonId(String personId);
	/**
	 * 
	 *@Title:findByOrgId
	 *@TODO:根据OrgId查出list集合
	 *@data:2014-3-24
	 *@param:@param orgId
	 *@param:@return
	 *@return:List<MemberDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<MemberDTO> findByOrgId(String orgId);
	/***
	 * 
	 *@Title:findAll
	 *@TODO:查询所有会员信息
	 *@data:2014-3-24
	 *@param:@return
	 *@return:List<MemberDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<MemberDTO> findAll();
	/***
	 * 
	 *@Title:findManByOrdId
	 *@TODO:根据orgId查出男性会员
	 *@data:2014-3-25
	 *@param:@param orgId
	 *@param:@return
	 *@return:List<MemberDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<MemberDTO> findManByOrdId(String orgId);
	/**
	 * 
	 *@Title:findWoManByOrdId
	 *@TODO:根据orgId查出女性会员
	 *@data:2014-3-25
	 *@param:@param orgId
	 *@param:@return
	 *@return:List<MemberDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<MemberDTO> findWoManByOrdId(String orgId);
	/***
	 * 会员信息条件查询
	 */
	public List<Member> findByMems(MemberDTO memDto,Integer groupId);
	/***
	 * 解文侠
	 * 会员信息模糊查询
	 */
	public List<Member> LikeMember();
	/***
	 * 
	 * @Title:findDTO
	 * @TODO:获取一个会员DTO
	 * @date:2014-4-8
	 * @param:@param memId
	 * @param:@return
	 * @return:MemberDTO
	 * @author:孟凡岭
	 */
	public MemberDTO findDTO(String memId);
	/****
	 * 获取不属于群组的会员
	 *@Title:findMemOptionString
	 *@TODO:TODO
	 *@data:2014-4-26
	 *@return:List<OptionsString>
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<OptionsString> findMemOptionString(MemberDTO memDto,
			Integer groupId);
	
	/**
	 *@Title:find
	 *@Description:获取司机辅助表实体
	 *@param:@param memId
	 *@param:@return
	 *@return:Drivers
	 *@author:王月
	 *@thorws:
	 */
	public Member find(String memId);
	
	/**
	 *@Title:query
	 *@Description:根据条件查询司机信息辅表
	 *@param:@param memId
	 *@param:@return
	 *@param:@throws Exception
	 *@return:Drivers
	 *@author:王月
	 *@thorws:
	 */
	public Member query(String memId) throws Exception;
	/**
	
	* @ClassName: MemberService.java
	
	* @Description: 单张售卡的帮助窗口而写
	
	* @author  赵巧鹤
	
	* @date 2014-6-3 下午04:27:27
	
	*/
	public QueryResult<MemberDTO> queryMemberDTO(int fristindex, int pageNum,
			MemberDTO memberDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;
}
