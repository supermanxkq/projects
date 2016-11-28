package com.paySystem.ic.service.member.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.Members;
import com.paySystem.ic.dao.member.MemberDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.member.MemberService;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.member.MembersDTO;
import com.paySystem.ic.web.ui.OptionsString;

/**
 * @ProjectName:omall
 * @ClassName:MemberServiceImpl
 * @Description:会员操作Service实现类
 * @date: 2014-11-27上午09:30:25
 * @author: 徐凯强
 * @version: V1.0
 */
@Service(MemberService.MEMBERSERVICE)
public class MemberServiceImpl extends DaoSupport<Members> implements
		MemberService {
	/** 注入memberDao */
	@Resource
	MemberDAO memberDao;

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
			throws Exception {
		return memberDao.queryResult(fristindex, pageNum, membersDTO, orderBy);
	}

	/**
	 *@MethodName:findMembersById
	 *@Description:根据会员的编号进行获取会员信息
	 *@param memId会员编号
	 *@Author:徐凯强
	 *@Date:2014-11-27上午09:28:07
	 */
	public MembersDTO findMembersById(Integer memId) {
		MembersDTO membersDTO = new MembersDTO();
		try {
			Members members = this.find(memId);
			membersDTO = (MembersDTO) EntityDtoConverter.bean2Dto(members,
					membersDTO);
		} catch (Exception e) {
		}
		return membersDTO;
	}

	/**
	 *@Title:updateMembers
	 *@Description:禁用会员
	 *@param membersDTO会员数据传输对象
	 *@Return:void空
	 *@author:徐凯强
	 *@Date:2014-11-26下午03:18:31
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateMembers(MembersDTO membersDTO) {
		memberDao.updateMembers(membersDTO);
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.member.MemberService#findMemOptionString(com.paySystem.ic.web.dto.member.MemberDTO, java.lang.Integer)
	 *@MethodName:findMemOptionString
	 *@Description:获取不属于群组的会员和已经被选中的会员
	 *@param memDto
	 *@param groupId
	 *@Author:张军磊
	 *@Date:2014-11-19下午06:56:22
	 */
	public List<OptionsString> findMemOptionString(MembersDTO memDto,
			Integer groupId,String rightSelect) {
		return memberDao.findMemOptionString(memDto,groupId,rightSelect);
	}

	/**
	 *@MethodName:findByOrgId
	 *@Description:TODO
	 *@param orgId
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-11-27上午11:49:33
	 */
	public List<MembersDTO> findByOrgId(String orgId) throws Exception{
		// TODO Auto-generated method stub
		 return memberDao.findByOrgId(orgId);
	}
}
