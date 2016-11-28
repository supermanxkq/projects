package com.paySystem.ic.dao.member.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Drivers;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.member.Amember;
import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.member.MemberDAO;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.member.AmemberService;
import com.paySystem.ic.service.member.MemberService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.DriverDTO;
import com.paySystem.ic.web.dto.member.AmemberDTO;
import com.paySystem.ic.web.dto.member.MemberDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsString;

@Component
public class MemberDaoImpl extends DaoSupport<Member> implements MemberDAO {
	/** 创建会员实体类 */
	Member member = new Member();
	Amember amember = new Amember();
	/** 创建会员 数据传送对象 */
	MemberDTO memberDTO = new MemberDTO();
	/** 创建返回值数据传送对象 */
	ReturnDTO dot = new ReturnDTO();
	Drivers drivers = new Drivers();
	/** 注入 OrganService 服务 */
	@Resource
	OrgansService organsServiceImpl;
	@Resource
	MemberService memberServiceImpl;
	@Resource
	AmemberService amemberServiceImpl;
/*	@Resource
	DriverService driverServiceImpl;
	@Resource
	DriverDAO driverDAO;*/

	/**
	 *@Title:delete
	 *@Description:删除
	 *@param:@param ids
	 *@author:王月
	 *@thorws:
	 *2014-5-26
	 *
	 */
	public void delete(String[] ids) {
		try {
			for (int i = 0; i < ids.length; i++) {
				ids[i] = Utils.getString(memberDTO.getMemId());
			}
			super.delete(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Member find(String[] memids) {
		try {
			for (int i = 0; i < memids.length; i++) {
				memids[i] = Utils.getString(memberDTO.getMemId());
			}
			super.find(memids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
    /**
     *@Title:getMemberId
     *@Description:对会员id进行改造
     *@param:@return
     *@author:王月
     *@thorws:
     *2014-5-26
     *
     */
    @SuppressWarnings("unchecked")
    public String getMemberId(){
		Integer memberId = 0;
		String sql = "select m.memid from M_Member m order by memId desc";
		//Query query = em.createNativeQuery(sql);
		List<Object[]> objlist = new ArrayList<Object[]>();
		objlist = em.createNativeQuery(sql.toString()).getResultList();
		if(objlist.size() != 0){
				Object obj = objlist.get(0);				
					String memId = obj.toString();
					memberId = Integer.parseInt(memId);
					memberId = memberId + 1 ;
				
		}
		else{
			memberId = 10000001;
		}
		return memberId.toString();		
	}
    
	/**
	 *@Title:save
	 *@Description:保存方法
	 *@param:@param memberDTO
	 *@param:@param amemberDTO
	 *@param:@param driverDTO
	 *@author:王月
	 *@thorws:
	 *2014-5-26
	 *
	 */
	public synchronized void  save(MemberDTO memberDTO, AmemberDTO amemberDTO,DriverDTO driverDTO) {
		try {
			//Member member=new Member();
			member.setMemId(getMemberId());
			member.setAreaId(Utils.getString(memberDTO.getAreaId()));
			member.setSex(memberDTO.getSex());
			member.setStatus(memberDTO.getStatus());
			member.setTeleNo(Utils.getString(memberDTO.getTeleNo()));
			member.setUpdateTime(DateTimeTool.dateFormat("yyyy-MM-dd",
					memberDTO.getUpdateTime()));
			member.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd",
					memberDTO.getCreateTime()));
			member.setPersonId(Utils.getString(memberDTO.getPersonId()));
			member.setEmail(Utils.getString(memberDTO.getEmail()));
			member.setGroupId(Utils.getString(memberDTO.getGroupId()));
			member.setPerType(1);
			member.setMemRealName(Utils.getString(memberDTO.getMemRealName()));
			member.setOrgans(organsServiceImpl.find(memberDTO.getOrganId()));
			member.setCarNumber(Utils.getString(memberDTO.getCarNumber()));
			member.setCarType(memberDTO.getCarType());
			member.setDriverNo(Utils.getString(memberDTO.getDriverNo()));
			member.setCardType(memberDTO.getCardType());
			member.setOperId(Utils.getString(memberDTO.getOperId()));
			member.setRecomId(Utils.getString(memberDTO.getRecomId()));
			member.setRecomType(memberDTO.getRecomType());
			memberDTO.setMemId(member.getMemId());
			super.save(member);
			amember.setAmemId(Utils.getString(memberDTO.getMemId()));
			amember.setPwd(Utils.getString(amemberDTO.getPwd()));
			amember.setAddress(Utils.getString(amemberDTO.getAddress()));
			amember.setResidZip(Utils.getString(amemberDTO.getResidZip()));
			amember.setBirthday(DateTimeTool.dateFormat("yyyy-MM-dd",
					amemberDTO.getBirthday()));
			amember.setCareer(Utils.getString(amemberDTO.getCareer()));
			amember.setCertExTime(amemberDTO.getCertExTime());
			amember.setCultDegree(Utils.getString(amemberDTO.getCultDegree()));
			amember.setMemName(Utils.getString(amemberDTO.getMemName()));
			super.save(amember);
			
			/*drivers.setDriverId(driverDAO.getId());*/
			drivers.setMemId(Utils.getString(memberDTO.getMemId()));
			drivers.setBank(Utils.getString(driverDTO.getBank()));
			drivers.setBankAccount(Utils.getString(driverDTO.getBankAccount()));
			//drivers.setBank(Utils.getString(driverDTO.getBank()));
			drivers.setCarNumber(Utils.getString(memberDTO.getCarNumber()));
			drivers.setContacts(Utils.getString(driverDTO.getContacts()));
			drivers.setCarType(memberDTO.getCarType());
			drivers.setContactsTel(Utils.getString(driverDTO.getContactsTel()));
			drivers.setContractNo(Utils.getString(driverDTO.getContractNo()));
			drivers.setDriverNo(Utils.getString(memberDTO.getDriverNo()));
			drivers.setOperId(Utils.getString(memberDTO.getOperId()));
			drivers.setSettlement(Utils.getString(driverDTO.getSettlement()));
			drivers.setCreateTime(this.getSysTime());
			drivers.setStatus(memberDTO.getStatus());
			super.save(drivers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *@Title:update
	 *@Description:更新方法  
	 *@param:@param memberDTO
	 *@param:@param amemberDTO
	 *@param:@param driverDTO
	 *@param:@return
	 *@author:王月
	 *@thorws:
	 *2014-5-26
	 *
	 */
	
	public ReturnDTO update(MemberDTO memberDTO, AmemberDTO amemberDTO,DriverDTO driverDTO) {
		ReturnDTO dto = new ReturnDTO();
		if (memberDTO != null) {
			member=this.find(memberDTO.getMemId());
			member.setMemId(Utils.getString(memberDTO.getMemId()));
			member.setEmail(Utils.getString(memberDTO.getEmail()));
			member.setStatus(memberDTO.getStatus());
			member.setTeleNo(Utils.getString(memberDTO.getTeleNo()));
//			member.setMemId(Utils.getString(memberDTO.getMemId()));
//			member.setAreaId(Utils.getString(memberDTO.getAreaId()));
//			member.setCreateTime(this.getSysTime());
//			member.setEmail(Utils.getString(memberDTO.getEmail()));
//			member.setGroupId(Utils.getString(memberDTO.getGroupId()));
//			member.setMemRealName(Utils.getString(memberDTO.getMemRealName()));
//			member.setPerType(memberDTO.getPerType());
//			member.setOrgans(organsServiceImpl.find(memberDTO.getOrganId()));
//			member.setPersonId(Utils.getString(memberDTO.getPersonId()));
//			member.setSex(memberDTO.getSex());
//			member.setStatus(memberDTO.getStatus());
//			member.setTeleNo(Utils.getString(memberDTO.getTeleNo()));
//			member.setCardType(memberDTO.getCardType());
//			member.setOperId(Utils.getString(memberDTO.getOperId()));
//			member.setUpdateTime(this.getSysTime());
			super.update(member);
			
//			amember.setAmemId(Utils.getString(memberDTO.getMemId()));
//			amember.setCultDegree(Utils.getString(amemberDTO.getCultDegree()));
//			amember.setMemName(Utils.getString(amemberDTO.getMemName()));
//			amember.setPwd(Utils.getString(amemberDTO.getPwd()));
//			amember.setAddress(Utils.getString(amemberDTO.getAddress()));
//			amember.setResidZip(Utils.getString(amemberDTO.getResidZip()));
//			amember.setBirthday(DateTimeTool.dateFormat("yyyy-MM-dd",
//					amemberDTO.getBirthday()));
//			amember.setCareer(Utils.getString(amemberDTO.getCareer()));
//			amember.setCertExTime(amemberDTO.getCertExTime());
//			super.update(amember);
//			
//			drivers.setMemId(Utils.getString(memberDTO.getMemId()));
//			drivers.setDriverId(driverDTO.getDriverId());
//			drivers.setBankAccount(Utils.getString(driverDTO.getBankAccount()));
//			drivers.setBank(Utils.getString(driverDTO.getBank()));
//			drivers.setCarNumber(Utils.getString(driverDTO.getCarNumber()));
//			drivers.setContacts(Utils.getString(driverDTO.getContacts()));
//			drivers.setCarType(driverDTO.getCarType());
//			drivers.setContactsTel(Utils.getString(driverDTO.getContactsTel()));
//			drivers.setContractNo(Utils.getString(driverDTO.getContractNo()));
//			drivers.setDriverNo(Utils.getString(driverDTO.getDriverNo()));
//			drivers.setOperId(Utils.getString(memberDTO.getOperId()));
//			drivers.setSettlement(Utils.getString(driverDTO.getSettlement()));
//			drivers.setCreateTime(this.getSysTime());
//			drivers.setStatus(memberDTO.getStatus());
//			super.update(drivers);
			dto.setFlag(true);
		}
		return dto;
	}

	/**
	 *@Title:queryResult
	 *@Description:查询数据
	 *@param:@param fristindex
	 *@param:@param pageNum
	 *@param:@param memberDTO
	 *@param:@param orderBy
	 *@param:@return
	 *@param:@throws Exception
	 *@author:王月
	 *@thorws:
	 *2014-5-26
	 *
	 */
	public QueryResult<Member> queryResult(int fristindex,int pageNum, MemberDTO memberDTO,LinkedHashMap<String, String> orderBy) throws Exception{
		StringBuilder sql = new StringBuilder();// 封装查询where条件
		List<Object> params = new ArrayList<Object>();// 参数设置
		/** 判断过滤条件，如果无过滤条查询全部数据 */
		if (StringUtils.isNotBlank(memberDTO.getMemId())) {
			sql.append(" and o.memId like ?").append(params.size() + 1);
			// 设置会员编号
			params.add("%" + memberDTO.getMemId().trim() + "%");
		}
		if (StringUtils.isNotBlank(memberDTO.getMemRealName())) {
			sql.append(" and o.memRealName like ?").append(params.size() + 1);
			// 设置会员名称
			params.add("%" + memberDTO.getMemRealName().trim() + "%");
		}
		if (StringUtils.isNotBlank(memberDTO.getTeleNo())) {
			sql.append(" and o.teleNo like ?").append(params.size() + 1);
			// 设置手机号码
			params.add("%" + memberDTO.getTeleNo().trim() + "%");
		}
		    // 设会员等级
		if(!memberDTO.getGradeId().equals("-1")){
			sql.append(" and o.memGrade.gradeId = '"+memberDTO.getGradeId()+"'");
		}
		if (memberDTO.getStatus() != null && memberDTO.getStatus() != -1) {
			sql.append(" and o.status = ?").append(params.size() + 1);
			// 设置会员状态
			params.add(memberDTO.getStatus());
		}
		/***
		 * 邮件发送条件判断 孟凡岭添加
		 */
		if (memberDTO.getCreateTime() != null
				&& memberDTO.getCreateTime().length() > 0) {
			sql.append(" and o.createTime=?").append(params.size() + 1);
			params.add(DateTimeTool.dateFormat("yyyy-MM-dd", memberDTO
					.getCreateTime()));
		}
		System.out.println(sql.toString());
		QueryResult<Member> queryResult = getScrollData(fristindex, pageNum,
				sql.toString(), params.toArray(), orderBy);
		
		return queryResult;
	}
	/**
	
	* @ClassName: MemberDAO.java
	
	* @Description: 单张售卡调用，返回的是会员DTO，主表和附表的内容都要获得的方法
	
	* @author  赵巧鹤
	
	* @date 2014-6-6 上午09:17:12
	
	*/
public QueryResult<MemberDTO> queryAll(int firstindex,int pageNum,MemberDTO memberDTO,LinkedHashMap<String,String> orderBy)throws Exception{
	return null;
}
	/**
	 *@Title:findByName
	 *@Description:通过名字查找
	 *@param:@param memRealName
	 *@param:@return
	 *@author:王月
	 *@thorws:
	 *2014-5-26
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<Member> findByName(String memRealName) {
		String jpl = "select o from Member o where o.memRealName like ?";
		Query query = em.createQuery(jpl);
		query.setParameter(1, "%" + memRealName + "%");
		List<Member> list = query.getResultList();
		return list;
	}

	/*
	 * <p>Title: saveMember</p> <p>Description: 将MemberDTO保存到数据库</p>
	 * 
	 * @param MemberDTO
	 * 
	 * @see
	 * com.paySystem.ic.dao.member.MemberDAO#saveMember(com.paySystem.ic.web
	 * .dto.member.MemberDTO)
	 * 
	 * @author lily
	 * 
	 * @date 2013-12-24 下午04:23:38
	 */
	public void saveMember(MemberDTO memberDTO) {
		this.save(getMember(memberDTO));
	}

	/**
	 * @Title: getMember
	 * @Description: 将memberDTO转换为Member
	 * @param @param memberDTO
	 * @param @return
	 * @return Member
	 * @throws
	 * @author lily
	 * @date 2013-12-24 下午04:25:06
	 */
	private Member getMember(MemberDTO memberDTO) {
		if (memberDTO == null) {
			return null;
		}
		Member member = new Member();
		if (memberDTO.getMemId() != null) {
			member.setMemId(memberDTO.getMemId());
		}
		if (memberDTO.getMemRealName() != null) {
			member.setMemRealName(memberDTO.getMemRealName());
		}
		if (memberDTO.getPerType() != null) {
			member.setPerType(memberDTO.getPerType());
		}
		if (memberDTO.getPersonId() != null) {
			member.setPersonId(memberDTO.getPersonId());
		}
		if (memberDTO.getTeleNo() != null) {
			member.setTeleNo(memberDTO.getTeleNo());
		}
		if (memberDTO.getSex() != null) {
			member.setSex(memberDTO.getSex());
		}
		if (memberDTO.getAreaId() != null) {
			member.setAreaId(memberDTO.getAreaId());
		}
		if (memberDTO.getEmail() != null) {
			member.setEmail(memberDTO.getEmail());
		}
		if (memberDTO.getGroupId() != null) {
			member.setGroupId(memberDTO.getGroupId());
		}
		if(memberDTO.getMemGrade() !=null){
			member.setMemGrade(memberDTO.getMemGrade());
		}
		if (memberDTO.getUpdateTime() != null) {
			member.setUpdateTime(DateTimeTool.dateFormat("yyyy-MM-dd",
					memberDTO.getUpdateTime()));
		}
		if (memberDTO.getCreateTime() != null) {
			member.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd",
					memberDTO.getCreateTime()));
		}
		if (memberDTO.getOrganId() != null) {
			member.setOrgans(new Organs(memberDTO.getOrganId()));
		}
		if (memberDTO.getStatus() != null) {
			member.setStatus(memberDTO.getStatus());
		}
		if (memberDTO.getCarNumber() != null) {
			member.setCarNumber(memberDTO.getCarNumber());
		}
		if (memberDTO.getCarType() != null) {
			member.setCarType(memberDTO.getCarType());
		}
		if (memberDTO.getRecomId() != null) {
			member.setRecomId(memberDTO.getRecomId());
		}
		if (memberDTO.getRecomType() != null) {
			member.setRecomType(memberDTO.getRecomType());
		}
		if (memberDTO.getCardType() != null) {
			member.setCardType(memberDTO.getCardType());
		}
		return member;
	}

	/**
	 *@Title:getMemberByPersonId
	 *@Description:通过身份证查找会员
	 *@param:@param personId
	 *@param:@return
	 *@author:王月
	 *@thorws:
	 *2014-5-26
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<Member> getMemberByPersonId(String personId) {
		String jpl = "select o from Member o where o.personId = ?1";
		Query query = em.createQuery(jpl);
		query.setParameter(1, personId);
		List<Member> list = query.getResultList();
		return list;
	}

	/**
	 *@Title:findByOrgId
	 *@Description:通过机构id查找
	 *@param:@param orgId
	 *@param:@return
	 *@author:王月
	 *@thorws:
	 *2014-5-26
	 *
	 */
	public List<MemberDTO> findByOrgId(String orgId) {
		String sql = "from Member m where m.organs.organId='" + orgId + "'";
		List<Member> list = em.createQuery(sql).getResultList();
		List<MemberDTO> listt = getMemberDTOList(list);
		return listt;
	}

	/**
	 *@Title:getMemberDTOList
	 *@Description:实体转dto
	 *@param:@param list
	 *@param:@return
	 *@return:List<MemberDTO>
	 *@author:王月
	 *@thorws:
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
			if (m.getAmember() != null) {
				memDTO.setAreaId(m.getAmember().getAmemId());
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
			if(m.getMemGrade() !=null){
				memDTO.setMemGrade(m.getMemGrade());
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
				memDTO.setUpdateTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", m
						.getUpdateTime()));
			}
			Amember am = amemberServiceImpl.find(m.getMemId());
			if (am.getBirthday() != null) {
				memDTO.setBirthday(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", am
						.getBirthday()));
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
			listt.add(memDTO);
		}
		return listt;
	}
	
	

	/**
	 * 
	 *@Title:findAll
	 *@TODO:查询所有会员信息
	 *@param:@return
	 *@author:孟凡岭
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public List<MemberDTO> findAll() {
		// TODO Auto-generated method stub
		String sql = "from Member m where 1=1";
		List<Member> list = em.createQuery(sql).getResultList();

		return getMemberDTOList(list);
	}

	/***
	 * 
	 *@Title:findManByOrgId
	 *@TODO:根据orgId查出男性会员
	 *@param:@param orgId
	 *@param:@return
	 *@author:孟凡岭
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public List<MemberDTO> findManByOrgId(String orgId) {
		// TODO Auto-generated method stub
		String sql = "from Member m where 1=1 and m.sex=1";
		List<Member> list = em.createQuery(sql).getResultList();

		return getMemberDTOList(list);
	}

	/***
	 * 
	 *@Title:findWoManByOrgId
	 *@TODO:根据orgId查出男性会员
	 *@param:@param orgId
	 *@param:@return
	 *@author:孟凡岭
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	public List<MemberDTO> findWoManByOrgId(String orgId) {
		String sql = "from Member m where 1=1 and m.sex=2";
		List<Member> list = em.createQuery(sql).getResultList();
		return getMemberDTOList(list);
	}

	/****
	 * 进行会员模糊查询
	 * 
	 * @see com.paySystem.ic.dao.member.MemberDAO#findByMems(com.paySystem.ic.web.dto.member.MemberDTO)
	 * @Description:TODO
	 * @date: 2014-4-3下午06:29:41
	 * @author: 解文侠
	 * @version: V1.0
	 * @param memberDTO
	 * @return
	 */
	public List<Member> findByMems(MemberDTO memberDTO, Integer groupId) {

		StringBuilder sql = new StringBuilder(" from Member o where 1=1 ");// 封装查询where条件
		List<Object> params = new ArrayList<Object>();// 参数设置
		if (StringUtils.isNotBlank(memberDTO.getAreaId())
				&& !"-1".equals(memberDTO.getAreaId())) {
			sql.append(" and o.areaId like ?").append(params.size() + 1);
			// 所属区域设置
			params.add("%" + memberDTO.getAreaId().trim() + "%");
		}
		if (memberDTO.getPerType() != null && memberDTO.getPerType() != -1) {
			sql.append(" and o.perType = ?").append(params.size() + 1);
			// 设置会员证件类型
			params.add(memberDTO.getPerType());
		}
		if (memberDTO.getSex() != null && memberDTO.getSex() != -1) {
			sql.append(" and o.sex = ?").append(params.size() + 1);
			// 设置会员性别
			params.add(memberDTO.getSex());
		}
		if (StringUtils.isNotBlank(memberDTO.getBirthday())) {
			sql.append(" and o.amember.birthday = ?").append(params.size() + 1);
			// 设置会员生日
			params.add(memberDTO.getBirthday());
		}
		if (StringUtils.isNotBlank(memberDTO.getCareer())) {
			sql.append(" and o.amember.career = ?").append(params.size() + 1);
			// 设置会员职业
			params.add(memberDTO.getCareer());
		}
		if (StringUtils.isNotBlank(memberDTO.getCultDegree())) {
			sql.append(" and o.amember.cultDegree = ?").append(
					params.size() + 1);
			// 设置会员学历
			params.add(memberDTO.getCultDegree());
		}

		Query query = em.createQuery(sql.toString());
		DaoSupport.setQueryParams(query, params.toArray());
		List<Member> list = query.getResultList();
		return list;
	}

	/**
	 *@Title:LikeMember
	 *@Description:模糊查询
	 *@param:@return
	 *@author:王月
	 *@thorws:
	 *2014-5-26
	 *
	 */
	public List<Member> LikeMember() {
		String jpl = "select o from Member o where o.perType like ? and o.sex like ? and o.areaId like ?";
		Query query = em.createQuery(jpl);
		List<Member> list = query.getResultList();
		return list;
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
		Member member = this.find(memId);
		return getMemberDTO(member);
	}

	/**
	 * 
	 *@Title:getMemberDTO
	 *@TODO:获取一个MEMBERDTO
	 *@data:2014-4-9
	 *@return:MemberDTO
	 *@author:孟凡岭
	 *@thorws:
	 */
	private MemberDTO getMemberDTO(Member m) {
		// TODO Auto-generated method stub
		MemberDTO dto = new MemberDTO();
		dto.setAreaId(m.getAreaId());
		if (m.getAmember() != null) {
			dto.setBirthday(DateTimeTool.dateFormat("yyyy-MM-dd", m
					.getAmember().getBirthday()));
			dto.setCareer(m.getAmember().getCareer());
			dto.setCultDegree(m.getAmember().getCultDegree());
		}
		if (m.getCreateTime() != null) {
			dto.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd", m
					.getCreateTime()));
		}
		if (m.getEmail() != null) {
			dto.setEmail(m.getEmail());
		}
		if (m.getGroupId() != null) {
			dto.setGroupId(m.getGroupId());
		}
		if (m.getMemId() != null) {
			dto.setMemId(m.getMemId());
		}
		if (m.getMemRealName() != null) {
			dto.setMemRealName(m.getMemRealName());
		}
		if (m.getOrgans() != null) {
			dto.setOrganId(m.getOrgans().getOrganId());
			dto.setOrganName(m.getOrgans().getName());
		}
		if (m.getPersonId() != null) {
			dto.setPersonId(m.getPersonId());
		}
		if (m.getPerType() != null) {
			dto.setPerType(m.getPerType());
		}
		if (m.getSex() != null) {
			dto.setSex(m.getSex());
		}
		if (m.getStatus() != null) {
			dto.setStatus(m.getStatus());
		}
		if (m.getTeleNo() != null) {
			dto.setTeleNo(m.getTeleNo());
		}
		if (m.getUpdateTime() != null) {
			dto.setUpdateTime(DateTimeTool.dateFormat("yyyy-MM-dd", m
					.getUpdateTime()));
		}
		return dto;
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
		UserSession us = Utils.getUserSession();
		List<Object> params = new ArrayList<Object>();// 参数设置
		StringBuilder sql = new StringBuilder(
				"select o.memid,o.memrealname,o.sex from m_member o where 1=1 ");
		switch (us.getUserLevel()) {
		case 1:
			sql.append(" and o.organId='" + us.getOrganId() + "'");
			break;
		}
		if (StringUtils.isNotBlank(memDto.getAreaId())
				&& !"-1".equals(memDto.getAreaId())) {
			sql.append(" and o.areaId = ?").append(params.size() + 1);
			params.add(memDto.getAreaId());
		}
		if (memDto.getSex() != null && memDto.getSex() != -1) {
			sql.append(" and o.sex = ?").append(params.size() + 1); // 设置会员性别
			params.add(memDto.getSex());
		}
		if (StringUtils.isNotBlank(memDto.getMemId())) {
			sql.append(" and o.memId like ?").append(params.size() + 1);
			params.add("%" + memDto.getMemId() + "%");
		}
		if (groupId!=null) {
			sql
					.append(" and o.memid not in (select m.memId from m_member_memgroups m where m.groupid="
							+ groupId + ")");
		}

		Query query = em.createNativeQuery(sql.toString());
		DaoSupport.setQueryParams(query, params.toArray());
		List<Object[]> list = query.getResultList();
		List<OptionsString> listt = new ArrayList<OptionsString>();
		for (int i = 0; i < list.size(); i++) {
			Object[] o = list.get(i);
			listt.add(new OptionsString(o[0].toString(), o[1].toString()));
		}
		return listt;
	}
	
	/**
	 *@Title:getId
	 *@Description:定义一个主键使其避免有重复的主键
	 *@param:@return
	 *@return:String
	 *@author:王月
	 *@thorws:
	 */
	private String getId(){
		Date d=new Date();
		SimpleDateFormat s=new SimpleDateFormat("yyyyMMddHHmmss");
		String id=s.format(d);
		return id;
	}

}
