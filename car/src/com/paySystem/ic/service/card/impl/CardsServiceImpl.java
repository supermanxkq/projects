package com.paySystem.ic.service.card.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.Cards;
import com.paySystem.ic.dao.account.AccountsDAO;
import com.paySystem.ic.dao.card.CardsDAO;
import com.paySystem.ic.service.card.CardsService;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.account.AccRecordDTO;
import com.paySystem.ic.web.dto.account.AccountsDTO;
import com.paySystem.ic.web.dto.card.CardsDTO;
import com.paySystem.ic.web.dto.system.UserSession;

@Service(CardsService.CARDSSERVICE)
public class CardsServiceImpl extends DaoSupport<Cards> implements CardsService {

	
	@Resource
	CardsDAO cardsDetailDao;
	@Resource CardsDAO cardsDAOImpl;
  
    @Resource AccountsDAO accountDAOImpl;
	public QueryResult<CardsDTO> queryAll(int firstindex, int maxresult,
			CardsDTO cardsDTO, 
			LinkedHashMap<String, String> orderby) {
		QueryResult<CardsDTO> qr=null;
		/*try {
			qr = cardsDao.queryAll(firstindex, maxresult, cardsDTO, orderby);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return qr;
	}

	public CardsDTO findByCardNo(String cardsNo) {
		CardsDTO cardsDetailDTO = cardsDetailDao.findCards(cardsNo);
		return cardsDetailDTO;
	}

	public ReturnDTO update(CardsDTO cardsDTO) {
		cardsDetailDao.update(cardsDTO);
		return null;
	}

	//@Override
	//public Cards find(String cardNo) {
		
	//	return cardsDetailDao.find(cardNo);
	//}
	/****
	 * 验证卡号状态为7、卡号存在、卡号属于本机构
	 *赵巧鹤
	 */
	public boolean checkCardId(String cardNo) {
		UserSession us = Utils.getUserSession();
		boolean flag=false;
		CardsDTO cardsDTO = cardsDAOImpl.queryByCardNoShow(cardNo);
		List<AccountsDTO> accountsDTOList = accountDAOImpl.findAccountsByCardNo(cardsDTO.getCardNo());
		if(accountsDTOList.size()>0){
			for (int i = 0; i < accountsDTOList.size(); i++) {
				AccountsDTO accountsDTO = accountsDTOList.get(i);
				if(us.getUserLevel()==0){
					if(accountsDTO.getStatus()!=0||cardsDTO == null||cardsDTO.getStatus()!=7){
						flag= false;
					}else{
						flag= true;
					}
				}else{
					if((accountsDTO.getStatus()!=0||cardsDTO == null||cardsDTO.getStatus()!=7||!cardsDTO.getOrganId().equals(us.getOrganId()))){
						flag=false;
					}else{
						flag= true ;
					}
				}
			}
		}else{
			flag= false;
		}
		return flag;
			
	}

	public Cards checkRechAmt(String cardNo) {
		Cards cards = cardsDAOImpl.find(cardNo);
		return cards;
	}
	
	/**
	 * @Title:queryByCardsNoShow
	 * @Descrition:TODO 挂失专用
	 * @param: @param cardsNoShow
	 * @param: @return
	 * @return: CardsDTO
	 * @author: 赵巧鹤
	 * @throws:
	 */
	public CardsDTO queryByCardsNoLock(String cardsNoShow) {
		
		return cardsDAOImpl.queryByCardNoShow(cardsNoShow);
	}
   /**
 * @Title:queryByCardsNo
 * @Descrition:TODO
 * @param: @param cardsNoShow
 * @param: @return
 * @return: CardsDTO
 * @author: 赵巧鹤
 * @throws:
 */
public CardsDTO queryByCardsNoShow(String cardsNoShow) {
		
		return cardsDAOImpl.queryByCardNoShow(cardsNoShow);
	}
	/**
	 * 赵巧鹤
	 * 判断该卡号是否属于该卡BIN 
	 * */
	public boolean checkCardNo(AccRecordDTO accRecordDTO){
		UserSession us =Utils.getUserSession();
		if(accRecordDTO.getCardNoView().length()!=6){
			return false;
		}
		CardsDTO cardsDTO =cardsDAOImpl.queryByCardNoShow(accRecordDTO.getCardNoView());
		
         if(cardsDTO==null){
        	 return false;
          }else{
        	 String cardBinId = cardsDTO.getCardNo().substring(0,6);
     		 if(cardBinId.equals(accRecordDTO.getBinId())&&cardsDTO.getOrganId().equals(us.getOrganId())&&cardsDTO.getStatus()==7){
     			return true;
     		}
     		return false; 
         }
		
	}
 
}











