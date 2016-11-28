package com.paySystem.ic.service.member.impl;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.Amember;
import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.dao.member.AmemberDAO;
import com.paySystem.ic.dao.member.MemberDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.member.AmemberService;
import com.paySystem.ic.service.member.MemberService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.DriverDTO;
import com.paySystem.ic.web.dto.member.AmemberDTO;
import com.paySystem.ic.web.dto.member.MemberDTO;
import com.paySystem.ic.web.ui.OptionsString;
/**
 * 会员服务层接口的实现
 * 
 * @author 井建国
 *
 */

//依赖注入
@Service(MemberService.MEMBERSERVICE)
public class MemberServiceImpl extends DaoSupport<Member> implements MemberService{
	@Resource AmemberDAO amemberDaoImpl;
	@Resource MemberDAO memberDaoImpl;
//	@Resource DriverDAO driverDaoImpl;
	@Resource
	AmemberService amemberServiceImpl;

	public void delete(String[] ids, String[] id) {
		memberDaoImpl.delete(ids);
	}

	public Member find(String[] memIds) {
		return memberDaoImpl.find(memIds);
	}	

	public String getMemberId() {
		String memberId = memberDaoImpl.getMemberId();
		return memberId;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(MemberDTO memberDTO,AmemberDTO amemberDTO,DriverDTO driverDTO) {
		memberDaoImpl.save(memberDTO,amemberDTO,driverDTO);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO update(MemberDTO memberDTO,AmemberDTO amemberDTO,DriverDTO driverDTO){
		ReturnDTO dto = new ReturnDTO();
		try{
			memberDaoImpl.update(memberDTO,amemberDTO,driverDTO);
			dto.setFlag(true);
		}catch(Exception e){
			e.printStackTrace();
			dto.setFlag(false);
		}
		return dto;
	}

	public QueryResult<Member> queryResult(int fristindex, int pageNum,
			MemberDTO memberDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		return memberDaoImpl.queryResult(fristindex, pageNum, memberDTO, orderBy);
	}
	
	/**
	
	* @ClassName: MemberServiceImpl.java
	
	* @Description: 单张售卡的帮助窗口调用，为了就是获得会员的DTO
	
	* @author  赵巧鹤
	
	* @date 2014-6-3 下午03:45:55
	
	*/
	public QueryResult<MemberDTO> queryMemberDTO(int fristindex, int pageNum,
			MemberDTO memberDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		/**
		 * 1、查询出一个装会员实体的QueryResult
		 * 2、new一个装会员DTO 的集合
		 * 3、得到一个装会员实体的List集合
		 * 4、得到dto集合
		 * 5、遍历这个集合得到每一个DTO把他给add到new好的集合里返回哦
		 * */
		QueryResult<Member> memberResult = memberDaoImpl.queryResult(fristindex, pageNum, memberDTO, orderBy);
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		List<Member> memberList = memberResult.getResultlist();
		QueryResult<MemberDTO> query = new QueryResult<MemberDTO>();
		//封装的私有方法实体集合转换为DTO集合
		list =getMemberDTOList(memberList);
	    query.setTotalrecord(memberList.size());
	    query.setResultlist(list);
		
			
		
		return query;
	}
	
	/**
	
	* @ClassName: MemberServiceImpl.java
	
	* @Description: BeanList 转换为DTOList
	
	* @author  赵巧鹤
	
	* @date 2014-6-3 下午04:09:26
	
	*/
	private List<MemberDTO> getMemberDTOList(List<Member> list) {
		// TODO Auto-generated method stub
		List<MemberDTO> listt = new ArrayList<MemberDTO>();
		for (int i = 0; i < list.size(); i++) {
			Member m = new Member();
			m = list.get(i);
		
			MemberDTO memDTO = new MemberDTO();
			
			if (m == null) {
				return null;
			}
			//区域ID
			if (m.getMemId() != null) {
				memDTO.setAreaId(m.getAreaId());
			}
			if (m.getCreateTime() != null) {
				memDTO.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd", m
						.getCreateTime()));
			}
			if (m.getEmail() != null) {
				memDTO.setEmail(m.getEmail());
			}
			if (m.getGroupId() != null) {
				memDTO.setGroupId(m.getGroupId());
			}
			if (m.getMemId() != null) {
				memDTO.setMemId(m.getMemId());
			}
			if (m.getMemRealName() != null) {
				memDTO.setMemRealName(m.getMemRealName());
			}
			if (m.getOrgans() != null) {
				memDTO.setOrganId(m.getOrgans().getOrganId());
			}
			if (m.getPersonId() != null) {
				memDTO.setPersonId(m.getPersonId());
			}
			if (m.getPerType() != null) {
				memDTO.setPerType(m.getPerType());
			}
			if (m.getSex() != null) {
				memDTO.setSex(m.getSex());
			}
			if (m.getStatus() != null) {
				memDTO.setStatus(m.getStatus());
			}
			if (m.getTeleNo() != null) {
				memDTO.setTeleNo(m.getTeleNo());
			}
			if (m.getUpdateTime() != null) {
				memDTO.setUpdateTime(DateTimeTool.dateFormat("yyyy-MM-dd", m
						.getUpdateTime()));
			}
			if (m.getCarNumber() != null) {
				memDTO.setCarNumber(m.getCarNumber());
			}
			if (m.getCarType() != null) {
				memDTO.setCarType(m.getCarType());
			}
			if (m.getRecomId() != null) {
				memDTO.setRecomId(m.getRecomId());
			}
			if (m.getRecomType() != null) {
				memDTO.setRecomType(m.getRecomType());
			}
			if (m.getCardType() != null) {
				memDTO.setCardType(m.getCardType());
			}
			//通过会员实体得到ID查到附表ID
			Amember am = amemberServiceImpl.find(m.getMemId());
			if (am.getBirthday() != null) {
				memDTO.setBirthday(DateTimeTool.dateFormat("yyyy-MM-dd", am
						.getBirthday()));
			}
			//职业
			if(am.getCareer()!=null){
				memDTO.setCareer(am.getCareer());
			}
			//地址
			if(am.getAddress()!=null){
				memDTO.setAddress(am.getAddress());
			}
			//学历
			if(am.getCultDegree()!=null){
				memDTO.setCultDegree(am.getCultDegree());
			}
			//邮编
			if(am.getResidZip()!=null){
				memDTO.setResidZip(am.getResidZip());
			}
		
			listt.add(memDTO);
		}
		return listt;
	}
	 
	public boolean getMemberByPersonId(String personId) {
		boolean flag = true;
		List<Member> list = memberDaoImpl.getMemberByPersonId(personId);
		if(list.size()!=0){
			flag = false;
		}
		return flag;
	}
	/***
	 * 
	 *@Title:findByOrgId
	 *@TODO:根据OrgId查出list集合
	 *@param:@param orgId
	 *@param:@return
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<MemberDTO> findByOrgId(String orgId) {
		
		return memberDaoImpl.findByOrgId(orgId);
	}
	/***
	 * 
	 *@Title:findAll
	 *@TODO:查询所有会员信息
	 *@param:@return
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<MemberDTO> findAll() {
		// TODO Auto-generated method stub
		return memberDaoImpl.findAll();
	}
	/***
	 * 
	 *@Title:findManByOrdId
	 *@TODO:根据orgId查出男性会员
	 *@param:@param orgId
	 *@param:@return
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<MemberDTO> findManByOrdId(String orgId) {
		// TODO Auto-generated method stub
		return memberDaoImpl.findManByOrgId(orgId);
	}
	/**
	 * 
	 *@Title:findWoManByOrdId
	 *@TODO:根据orgId查出男性会员
	 *@param:@param orgId
	 *@param:@return
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<MemberDTO> findWoManByOrdId(String orgId) {
		// TODO Auto-generated method stub
		return memberDaoImpl.findWoManByOrgId(orgId);
	}

	public List<Member> findByMems(MemberDTO memDto,Integer groupId) {
		return memberDaoImpl.findByMems(memDto,groupId);
	}

	public List<Member> LikeMember() {
		return memberDaoImpl.LikeMember();
	}
	/**
	 * 
	 * @Title:findDTO
	 * @TODO:获取一个会员DTO
	 * @date:2014-4-8
	 * @param:@param memId
	 * @param:@return
	 * @author:孟凡岭
	 */
	public MemberDTO findDTO(String memId) {
		// TODO Auto-generated method stub
		return memberDaoImpl.findDTO(memId);
	}
	/**
	 * 
	 *@Title:findMemOptionString
	 *@TODO:获取不属于群组的会员
	 *@param:@param memDto
	 *@param:@param groupId
	 *@param:@return
	 *@author:孟凡岭
	 *@thorws:
	 */
	public List<OptionsString> findMemOptionString(MemberDTO memDto,
			Integer groupId) {
		// TODO Auto-generated method stub
		return memberDaoImpl.findMemOptionString(memDto,groupId);
	}
	/**
	 *@Title:find
	 *@Description:获取司机辅表实体
	 *@param:@param memId
	 *@param:@return
	 *@author:王月
	 *@thorws:
	 *2014-5-26
	 *
	 */
	public Member find(String memId) {
		return memberDaoImpl.find(memId);
	}
	/**
	 *@Title:query
	 *@Description:根据条件查询司机信息辅助表
	 *@param:@param memId
	 *@param:@return
	 *@param:@throws Exception
	 *@author:王月
	 *@thorws:
	 *2014-5-26
	 *
	 */
	public Member query(String memId) throws Exception {
		return memberDaoImpl.find(memId);
	}
	
	/**@Override
	public Drivers query(String memId) throws Exception {
		return driverDaoImpl.query(memId);
	}


	@Override
	public Drivers find(String memId) {
		return driverDaoImpl.find(memId);
	}
	*/
}
