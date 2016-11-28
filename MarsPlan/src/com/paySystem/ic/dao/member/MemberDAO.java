package com.paySystem.ic.dao.member;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.DriverDTO;
import com.paySystem.ic.web.dto.member.AmemberDTO;
import com.paySystem.ic.web.dto.member.MemberDTO;
import com.paySystem.ic.web.ui.OptionsString;

public interface MemberDAO extends DAO<Member>{


	/**
	 * 会员服务层接口
	 * 
	 * 
	 * @author 井建国
	 *
	 */

		
		
		//设置static final 类型的依赖注入常量
		public static final String MEMBERSERVICE = "memberService";
		/**
		 * 保存实体
		 * 
		 * @param entity
		 *            实体id
		 */
		public void save(MemberDTO memberDTO,AmemberDTO amemberDTO,DriverDTO driverDTO);
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
	public void delete(String [] ids);
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
	public List<Member> findByName(String memRealName);
	public QueryResult<Member> queryResult(int fristindex,int pageNum, MemberDTO memberDTO,LinkedHashMap<String, String> orderBy) throws Exception;
	/**
	
	* @ClassName: MemberDAO.java
	
	* @Description: 单张售卡调用，返回的是会员DTO，主表和附表的内容都要获得的方法
	
	* @author  赵巧鹤
	
	* @date 2014-6-6 上午09:17:12
	
	*/
	public QueryResult<MemberDTO> queryAll(int firstindex,int pageNum,MemberDTO memberDTO,LinkedHashMap<String,String> orderBy)throws Exception;

	/** 
	* @Title: saveMember
	* @Description: 将MemberDTO保存到数据库
	* @param @param setMemberDTO 
	* @return void
	* @throws
	* @author lily
	* @date 2013-12-24 下午04:22:32
	 */
	public void saveMember(MemberDTO MemberDTO);  
	
	/***
	 * 
	 *@Title:getMemberByPersonId
	 *@Description:通过会员的身份证查询
	 *@param:@return
	 *@return:Member
	 *@author:井建国
	 *@thorws:
	 */
	public List<Member> getMemberByPersonId(String personId);
	/**
	 * 
	 *@Title:findByOrgId
	 *@TODO:根据orgId查询出list集合
	 *@data:2014-3-24
	 *@param:@param orgId
	 *@param:@return
	 *@return:List<MemberDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<MemberDTO> findByOrgId(String orgId);
	/**
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
	 *@Title:findManByOrgId
	 *@TODO:根据orgId查出男性会员
	 *@data:2014-3-25
	 *@param:@param orgId
	 *@param:@return
	 *@return:List<MemberDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<MemberDTO> findManByOrgId(String orgId);
	/***
	 * 
	 *@Title:findWoManByOrgId
	 *@TODO:根据orgId查出女性会员
	 *@data:2014-3-25
	 *@param:@param orgId
	 *@param:@return
	 *@return:List<MemberDTO>
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<MemberDTO> findWoManByOrgId(String orgId);
	
	/***
	 * 解文侠
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
	/**
	 *@Title:findMemOptionString
	 *@TODO:获取不属于群组的会员
	 *@data:2014-4-26
	 *@return:List<OptionsString>
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<OptionsString> findMemOptionString(MemberDTO memDto,
			Integer groupId);
	
}
