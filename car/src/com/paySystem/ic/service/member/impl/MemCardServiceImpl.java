package com.paySystem.ic.service.member.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.Cards;
import com.paySystem.ic.bean.member.Amember;
import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.dao.base.OrgansDao;
import com.paySystem.ic.dao.card.CardsDAO;
import com.paySystem.ic.dao.member.AmemberDAO;
import com.paySystem.ic.dao.member.MemberDAO;
import com.paySystem.ic.service.member.MemCardService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.DriverDTO;
import com.paySystem.ic.web.dto.card.CardsDTO;
import com.paySystem.ic.web.dto.member.AmemberDTO;
import com.paySystem.ic.web.dto.member.MemCardDTO;
import com.paySystem.ic.web.dto.member.MemberDTO;
@Service(MemCardService.MEMCARDSERVICE)
public class MemCardServiceImpl implements MemCardService {
	@Resource MemberDAO memberDAOImpl;
	@Resource AmemberDAO amemberDAOImpl;
	/*@Resource DriverDAO driverDAOImpl;*/
	@Resource CardsDAO cardsDAOImpl;
	@Resource OrgansDao organsDAOImpl;
	private MemberDTO memberDTO = new MemberDTO();
	private AmemberDTO amemberDTO = new AmemberDTO();
	private DriverDTO driverDTO = new DriverDTO();


	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(MemCardDTO memCardDTO) {
		try {
			
			/***将MemCardDTO转换成MemberDTO***/
	  		memberDTO.setMemId(memCardDTO.getMemId());
			memberDTO.setAreaId(memCardDTO.getAreaId());
			memberDTO.setSex(memCardDTO.getSex());
			memberDTO.setStatus(memCardDTO.getMstatus());
			memberDTO.setStatus(memCardDTO.getCstatus());
			memberDTO.setTeleNo(memCardDTO.getTelNo());
			memberDTO.setUpdateTime(DateTimeTool.dateFormat("yyyy-MM-dd", new Date()));
			memberDTO.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd", new Date()));
			memberDTO.setPersonId(memCardDTO.getPersonId());
			memberDTO.setEmail(memCardDTO.getEmail());
			memberDTO.setGroupId(memCardDTO.getGroupId());
			memberDTO.setPerType(memCardDTO.getPerType());
			memberDTO.setMemRealName(memCardDTO.getMemRealName());
			memberDTO.setDriverNo(memCardDTO.getDriverNo());
			memberDTO.setRecomId(memCardDTO.getRecomId());
			memberDTO.setRecomType(memCardDTO.getRecomType());
			memberDTO.setCardType(memCardDTO.getCardType());
			memberDTO.setCarType(memCardDTO.getCarType());
			
			memberDTO.setOrganId(memCardDTO.getOrganId());
			/***将MemCardDTO转换成AmemberDTO***/
			amemberDTO.setAmemId(memCardDTO.getMemId());
		    //amemberDTO.setPwd(memCardDTO.getPwd()));  暂时不用该功能 
			amemberDTO.setAddress(memCardDTO.getAddress());
			amemberDTO.setResidZip(memCardDTO.getResidZip());
			amemberDTO.setBirthday(DateTimeTool.dateFormat("yyyy-MM-dd", memCardDTO.getBirthday()));
			amemberDTO.setCareer(memCardDTO.getCareer());
			amemberDTO.setCertExTime(memCardDTO.getCertExTime());
			amemberDTO.setCultDegree(memCardDTO.getCultDegree());
			amemberDTO.setMemName(memCardDTO.getMemName());
			
			/**将MemCardDTO转换成为DriverDTO*/
			driverDTO.setBank(memCardDTO.getBank());
			driverDTO.setBankAccount(memCardDTO.getBankAccount());
			driverDTO.setContacts(memCardDTO.getContacts());
			driverDTO.setContactsTel(memCardDTO.getContactsTel());
			driverDTO.setContractNo(memCardDTO.getContractNo());
			driverDTO.setSettlement(memCardDTO.getSettleMent());
			driverDTO.setMemId(memCardDTO.getMemId());
			//driverDTO.setDriverId(memCardDTO.);
			driverDTO.setCarNumber(memberDTO.getCarNumber());
			driverDTO.setCarType(memberDTO.getCarType());
			driverDTO.setDriverNo(memberDTO.getDriverNo());
			driverDTO.setOperId(memberDTO.getOperId());
			//driverDTO.setCreateTime(this.getSysTime());
			driverDTO.setStatus(memberDTO.getStatus());
			/***将MemCardDTO转换成CardsDTO**/
			CardsDTO cardsDTO  = cardsDAOImpl.queryByCardNoShow(memCardDTO.getCardNo());
			cardsDTO.setHoldmemId(memCardDTO.getMemId());
			cardsDTO.setMemsign(1);
			memberDAOImpl.save(memberDTO,amemberDTO,driverDTO);
			cardsDAOImpl.updateCards(cardsDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO update(MemCardDTO memCardDTO){
		ReturnDTO dto = new ReturnDTO();
		try{
			CardsDTO cardsDTO = cardsDAOImpl.queryByCardNoShow(memCardDTO.getCardNo().substring(9,15));
			Member member =memberDAOImpl.find(cardsDTO.getHoldmemId());
			Amember amember = amemberDAOImpl.find(cardsDTO.getHoldmemId());
			member.setMemRealName(memCardDTO.getMemRealName());
			member.setSex(memCardDTO.getSex());
			member.setPersonId(memCardDTO.getPersonId());
			member.setPerType(memCardDTO.getPerType());
			member.setTeleNo(memCardDTO.getTelNo());
			member.setAreaId(memCardDTO.getAreaId());
			member.setEmail(memCardDTO.getEmail());
			amember.setBirthday(memCardDTO.getBirthday());
			amember.setAddress(memCardDTO.getAddress());
			amember.setResidZip(memCardDTO.getResidZip());
			amember.setCareer(memCardDTO.getCareer());
			amember.setCultDegree(memCardDTO.getCultDegree());
			cardsDTO.setStatus(memCardDTO.getCstatus());
			cardsDTO.setHoldmemId(memCardDTO.getMemId());
			cardsDTO.setMemsign(1);
			memCardDTO.setOrgName(cardsDTO.getOrganName());
			memCardDTO.setOrganId(cardsDTO.getOrganId());
			memberDAOImpl.update(member);
			amemberDAOImpl.update(amember);
			cardsDAOImpl.updateCards(cardsDTO);
			dto.setFlag(true);
		}catch(Exception e){
			e.printStackTrace();
			dto.setFlag(false);
		}
		return dto;
	}

	public QueryResult<Cards> queryResult(int fristindex, int pageNum,
			MemCardDTO memCardDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		CardsDTO cardsDTO = new CardsDTO();
		cardsDTO.setHoldName(memCardDTO.getMemRealName());
		cardsDTO.setCardNo(memCardDTO.getCardNoShow());
		cardsDTO.setLevelId(memCardDTO.getLevelId());
		cardsDTO.setLevelName(memCardDTO.getLevelName());
		cardsDTO.setStatus(memCardDTO.getCstatus());
		return cardsDAOImpl.queryResult(fristindex, pageNum, cardsDTO, orderBy);
	}
	public MemberDTO getMemberDTO() {
		return memberDTO;
	}
	public void setMemberDTO(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}

	public AmemberDTO getAmemberDTO() {
		return amemberDTO;
	}

	public void setAmemberDTO(AmemberDTO amemberDTO) {
		this.amemberDTO = amemberDTO;
	}

}
