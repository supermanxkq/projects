package com.paySystem.ic.dao.card.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.card.Acards;
import com.paySystem.ic.bean.card.CardNo;
import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.dao.card.CardsDetailDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.web.dto.card.CardsDetailDTO;
@Repository("CardsDetailDaoImpl")
public class CardsDetailDaoImpl extends DaoSupport<Acards> implements CardsDetailDAO{

	
	public CardsDetailDTO findByCardNo(String cardNo) {
		return getCardsDetailDTO(this.find(cardNo));
	}

	
	public CardsDetailDTO findCardsDetail(String beginCardNo) {
		return getCardsDetailDTO(this.find(beginCardNo));
	}
	
	public void updateCardsDetail(CardsDetailDTO cardsDetailDTO) {
		this.update(getCardDetail(cardsDetailDTO));
	}
	
	private Acards getCardDetail(CardsDetailDTO cardsDetailDTO){
		if(cardsDetailDTO==null){
			return null;
		}
		Acards acards = new Acards();
		if(cardsDetailDTO.getCardNo()!=null){
			acards.setCardNo(cardsDetailDTO.getCardNo());
		}
		if(cardsDetailDTO.getSaleOrgId()!=null){
			acards.setOrgans(new Organs(cardsDetailDTO.getSaleOrgId()));
		}
		if(cardsDetailDTO.getSaleMerId()!=null){
			acards.setMerchants(new Merchants(cardsDetailDTO.getSaleMerId()));
		}
		if(cardsDetailDTO.getAmount()!=null){
			acards.setAmount(cardsDetailDTO.getAmount());
		}
		if(cardsDetailDTO.getBuyMemId()!=null){
			acards.setMember(new Member(cardsDetailDTO.getBuyMemId()));
		}
		if(cardsDetailDTO.getMakeTime()!=null){
			acards.setMakeTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", cardsDetailDTO.getMakeTime()));
		}
		if(cardsDetailDTO.getStartTime()!=null){
			acards.setStartTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", cardsDetailDTO.getStartTime()));
		}
		
		return acards;
	}
	
	private CardsDetailDTO getCardsDetailDTO(Acards acards){
		if(acards==null){
			return null;
		}
		CardsDetailDTO cardsDetailDTO = new CardsDetailDTO();
		if(acards.getCardNo()!=null){
			cardsDetailDTO.setCardNo(acards.getCardNo());
		}
		if (acards.getOrgans() != null) {
			cardsDetailDTO.setSaleOrgId(acards.getOrgans().getOrganId());
			cardsDetailDTO.setSaleOrgName(acards.getOrgans().getName());
		}
		if (acards.getMerchants() != null) {
			cardsDetailDTO.setSaleMerId(acards.getMerchants().getMerId());
			cardsDetailDTO.setSaleMerName(acards.getMerchants().getMerName());
		}
		if(acards.getAmount()!=null){
			cardsDetailDTO.setAmount(acards.getAmount());
		}
		if (acards.getMember() != null) {
			cardsDetailDTO.setBuyMemId(acards.getMember().getMemId());
			cardsDetailDTO.setBuyMemName(acards.getMember().getMemRealName());
		}			
		if (acards.getMakeTime() != null) {
			cardsDetailDTO.setMakeTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", acards.getMakeTime()));
		}
		if (acards.getStartTime() != null) {
			cardsDetailDTO.setStartTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", acards.getStartTime()));
		}
		return cardsDetailDTO;
	}
	
	/**
	 *@Title:saveCardsDetail
	 *@Description:保存卡明细信息
	 *@param:@param cardNos 卡号集合
	 *@return:void
	 *@author: 谢
	 *@thorws:
	 */
	
	public void saveCardsDetail(List<CardNo> cardNos) throws Exception {
		for(int i=0;i<cardNos.size();i++){
			Acards acards = new Acards();
			acards.setCardNo(cardNos.get(i).getCardNo());
			acards.setOrgans(cardNos.get(i).getOrgans());
			acards.setMakeTime(getSysTime());
		   this.save(acards);
		}
	}
}
