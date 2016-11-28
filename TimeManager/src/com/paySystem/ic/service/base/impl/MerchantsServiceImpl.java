package com.paySystem.ic.service.base.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Bail;
import com.paySystem.ic.bean.base.MerOilRel;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.dao.base.BailDAO;
import com.paySystem.ic.dao.base.MerBussDao;
import com.paySystem.ic.dao.base.MerOrgFunDao;
import com.paySystem.ic.dao.base.MerchantsDao;
import com.paySystem.ic.dao.base.SingleRelationDao;
import com.paySystem.ic.service.base.MerchantsService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.MerchantsDTO;
import com.paySystem.ic.web.ui.OptionsString;
@Service(MerchantsService.MERCHANTSSERVICE)
public class MerchantsServiceImpl implements MerchantsService {
	
	@Resource MerchantsDao merchantsDao;

    @Resource MerOrgFunDao merFunDao;

	@Resource SingleRelationDao sinRelationDao;

	@Resource BailDAO bailDAO;
	
	@Resource MerBussDao merBussDao;

	
	public boolean validate(String merId) {
	  boolean flag =  merchantsDao.validate(merId);
	  return flag;
	}
	
	public boolean validateName(String nameChinese,String merId) {
		boolean flag = merchantsDao.validateName(nameChinese,merId);
		return flag;
	}
	
	 
	public List<OptionsString> getOption() {
		
		List<OptionsString> list = merchantsDao.getOption();
		return list;
	}
	
	/**
	 * 通过机构号获取商户
	 * @param organId
	 * @param selectId
	 * @param js
	 * @return
	 */
	public List<OptionsString> getOptionByOrganId(String organId){
		List<OptionsString> list = merchantsDao.getOptionByOrganId(organId);	
		return list;
	}
	/*******************************
	 * 保存商户信息--新增
	 * 
	 * 1.保存商户信息
	 * 2.保存机构商户功能设置信息
	 * 3.保存商户充值限额信息
	 * 4.保存机构充值累计信息-初始
	 * 5.保存与主发卡机构的收单关系
	 * 6.保存商户卡BIN关联关系信息
	 * ******************************
	 * 7.保存商户缴纳保证金信息
	 * 8.保存油价信息维护表信息
	 * 9.保存商户油品关联表
	 * ******************************
	 * 
	 * 10.保存电商商户业务参数
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Merchants saveMerchant(MerchantsDTO merchantsDTO) throws Exception{
		/** 1.保存商户信息*/
		Merchants merchants = merchantsDao.saveMerchant(merchantsDTO);
/*		*//** 2.保存商户限额信息 *//*
		merRechLimitDao.saveMerRechLimit(merchantsDTO,merchants);
		*//** 3.保存商户充值累计表 *//*
		merRechTotalDao.saveMerRechTotal(merchants);*/
		/** 4.保存机构商户功能设置信息  */
		merFunDao.saveMerOrgFuncSwitch(merchants, null, 1);
		/** 5.保存收单关系 */
		sinRelationDao.initMainOrgSingRel(merchantsDTO, merchants);
		/** 6.保存商户卡BIN关联关系表 */
		//merSinRelationDao.saveMerBinFromMerchantsDTO(merchantsDTO, merchants);
		/** 7.保存商户缴纳保证金信息 */
		/*Bail bail = this.getBail(merchantsDTO);
		bailDAO.save(bail);*/
/*		*//** 8.保存油价信息维护表  *//*
		List<OilPrice> oilPrices = this.getOilPrice(merchantsDTO);
        oilPriceDao.batchSaveOilPrice(oilPrices);		
		*//** 9.保存商户油品关联表*//*
		List<MerOilRel> merOilRelList = this.getMerOilRelList(merchantsDTO);
		merOilRelDao.batchSaveMerOilRel(merOilRelList);*/
		
		//10.保存商户业务参数
		merBussDao.saveMerBuss(merchantsDTO);
		
		return merchants;
	}
	/**
	 * ****************************************
	 *@Title:updateMerchant
	 *@Description:更新商户信息
	 *
	 *             1.更新商户充值限额信息
	 *             2.更新商户信息
	 *             3.更新收单关系信息
	 *             4.更新商户卡BIN关联关系信息
	 *             
	 ****************石油平台改造***************
	 *
	 *             5.删除 商户/油品 关联表中信息
	 *             6.保存 商户/油品 关联表信息
	 *             7.删除油价信息
	 *             8.保存油价表信息表数据
	 *             
	 *@param:@param merchantsDTO  商户DTO类
	 *@param:@return
	 *@param:@throws Exception 
	 *@return:ReturnDTO
	 *@author: 谢
	 *@thorws:
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO updateMerchant(MerchantsDTO merchantsDTO) throws Exception{
		ReturnDTO dto = new ReturnDTO();
		//1.更改商户充值限额
		/*merRechLimitDao.updateMerRechLimit(merchantsDTO);*/
		//2.更改商户信息
		dto = merchantsDao.updateMerchant(merchantsDTO);
		//3.更改收单关系信息
		/*sinRelationDao.updateSingleRelation(merchantsDTO);*/
		//4.更改商户卡BIN关联关系信息
		/*merSinRelationDao.updateMerSin(merchantsDTO);*/
		/*******************************************************/
		//5.删除 商户/油品 关联表信息
		/*merOilRelDao.delMerOilRelByMerId(merchantsDTO.getMerId());*/
		//6.保存 商户/油品 关联表信息
		List<MerOilRel> merOilRels = this.getMerOilRelList(merchantsDTO);
		/*merOilRelDao.batchSaveMerOilRel(merOilRels);*/		
		//7.删除油价表信息
		/*oilPriceDao.delOilPriceByMerId(merchantsDTO.getMerId());*/
		//8.保存油价表信息数据
		/*List<OilPrice> oilPrices = this.getOilPrice(merchantsDTO);*/
		/*oilPriceDao.batchSaveOilPrice(oilPrices);*/
		//10.修改商户业务参数信息
		merBussDao.updateMerBuss(merchantsDTO);
		
		return dto;
		
	}

	public Merchants find(String merId) {
		Merchants merchants = merchantsDao.find(merId);
		return merchants;
	}

	@SuppressWarnings("unchecked")
	public QueryResult<Merchants> queryMerByCond(int firstindex, int maxresult,
			MerchantsDTO merchantsDTO, LinkedHashMap<String, String> orderby) throws Exception {
		QueryResult list = merchantsDao.queryMerByCond(firstindex, maxresult, merchantsDTO, orderby);
		return list;
	}

	public String getMerId() {
		String merId = merchantsDao.getMerId();
		return merId;
	}

	public void update(Merchants merchants) {
		
		merchantsDao.update(merchants);
		
	}

	/**
	 *@Title:getMerByOrgan
	 *@Description:根据 机构号获取商户集合
	 *@param:@param organId
	 *@param:@return
	 *@return:List<Merchants> 商户集合 
	 *@author:谢
	 *@thorws:
	 */
	public List<Merchants> getMerByOrgan(String organId) {
		
		return merchantsDao.getMerByOrganId(organId);
	}

	/**
	 * 
	 *@Title:getPreMerchants
	 *@Description:获取上级经销商
	 *@param:@return
	 *@return:List<OptionsString>
	 *@author:谢洪飞
	 *@thorws:
	 */
	public List<OptionsString> getPreMerchants() {
		return merchantsDao.getPreMerchants();
	}

	/**
	 *@Title:preMerHelpList
	 *@Description:获取经销商帮助信息集合
	 *@param:@param firstindex
	 *@param:@param pageNum
	 *@param:@param merchantsDTO
	 *@param:@param orderby
	 *@param:@return
	 *@param:@throws Exception
	 *@return:List<Merchants>
	 *@author:
	 *@thorws:
	 */
	public QueryResult<Merchants> preMerHelpList(int firstindex, int pageNum,
			MerchantsDTO merchantsDTO, LinkedHashMap<String, String> orderby)
			throws Exception {
		QueryResult<Merchants> merList = merchantsDao.preMerHelpList(firstindex, pageNum, merchantsDTO, orderby);
		return merList;
	}
	
	/**
	 * 获取保证金信息DTO
	 *@Title:getBailDTO
	 *@Description:获取保证金信息DTO
	 *@param:@param merchantsDTO
	 *@param:@return
	 *@return:BailDTO
	 *@author:
	 *@thorws:
	 */
	private Bail getBail(MerchantsDTO merchantsDTO){
		Bail bail = new Bail();
		bail.setMerOrgId(merchantsDTO.getMerId());
		bail.setBailAmt(merchantsDTO.getBailAmt());
		bail.setBuyOilRate(merchantsDTO.getBuyLimitAmt());
		bail.setContractNo(merchantsDTO.getConNo());
		bail.setCoopStatus(1);
		//交保时间
		bail.setPayTime(merchantsDao.getSysTime());
		bail.setTypeSign(1);
		return bail;
	}
	
	/**
	 *@Title:
	 *       getOilPrice
	 *@Description:
	 *        获取油价信息
	 *@param:
	 *        @param merchantsDTO
	 *@param:
	 *        @return
	 *@return:
	 *        List<OilPrice>
	 *@author:
	 *        谢洪飞
	 *@thorws:
	 */
	/*private List<OilPrice> getOilPrice(MerchantsDTO merchantsDTO){
		List<OilPrice> oilPrices =new ArrayList<OilPrice>();
		if(merchantsDTO.getOilTypeStatuss()!=null){
			for(int i = 0 ; i < merchantsDTO.getOilTypeStatuss().size(); i++ ){
				 OilPrice oilPrice = new OilPrice();
				String typeStatus = merchantsDTO.getOilTypeStatuss().get(i);
				for(int j = 0 ; j<merchantsDTO.getOilTypes().size();j++){
					if(typeStatus.equals(merchantsDTO.getOilTypes().get(j).toString())){
						if(merchantsDTO.getOilTypeStatuss()){}
						 
						  oilPrice.setOilTypeId(merchantsDTO.getOilTypes().get(j));
						  oilPrice.setDescr("维护油价信息!");
				          oilPrice.setMerOrgId(merchantsDTO.getMerId());
				          oilPrice.setMerOrgSign(1);
				          oilPrice.setStatus(1);
				          oilPrice.setCreateTime(oilPriceDao.getSysTime());
				          oilPrice.setSalePrice(merchantsDTO.getSaleAmts().get(j));
				          oilPrice.setOperId(Utils.getUserSession().getUserName());
					}
				}
	          oilPrices.add(oilPrice);	          
			}
		}

		return oilPrices;
	}*/
	
	/**
	 *@Title:getMerOilRelList
	 *@Description:获取商户油品关联表
	 *@param:@param merchantsDTO
	 *@param:@return
	 *@return:List<MerOilRel>
	 *@author:谢洪飞
	 *@thorws:
	 */

	private List<MerOilRel> getMerOilRelList(MerchantsDTO merchantsDTO){
		
		List<MerOilRel> merOilRels = new ArrayList<MerOilRel>();
		if(merchantsDTO.getOilTypeStatuss()!=null){
			for(int i = 0 ; i<merchantsDTO.getOilTypeStatuss().size(); i++){
				
				MerOilRel mor = new MerOilRel();
				String oilTypeStatus = merchantsDTO.getOilTypeStatuss().get(i);
			  for(int j = 0 ; j<merchantsDTO.getOilTypes().size() ; j++ ){
				  if(oilTypeStatus.equals(merchantsDTO.getOilTypes().get(j).toString())){
					//机构商户号
						mor.setMerOrgId(merchantsDTO.getMerId());
						//油品编号
						mor.setOilTypeId(merchantsDTO.getOilTypes().get(j));
						//机构商户标志
						mor.setMerOrgSign(1);
						//储备量
						mor.setOilStorage(merchantsDTO.getReserves().get(j));
						//售油价
						mor.setSaleOil(merchantsDTO.getSaleAmts().get(j));
						merOilRels.add(mor);
				  }
			  }
			}
		}

		return merOilRels;
	}

	public List<MerOilRel> queryOilRels(MerchantsDTO merchantsDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public MerchantsDTO findMerFromBussParam(String merId) {
		MerchantsDTO merDto = merBussDao.findMerBuss(merId);
		return merDto;
	}

	/**
	 *@Title:getOilPriceList
	 *@Description：根据商户获取油品价格信息
	 *@param:@param merchantsDTO
	 *@param:@return
	 *@return:List<OilPrice>
	 *@author:谢洪飞
	 *@thorws:
	 */
	/*public List<OilPrice> queryOilPriceList(MerchantsDTO merchantsDTO) {
		
		List<OilPrice> oilPrices = oilPriceDao.queryOpByMerId(merchantsDTO);
		
		return oilPrices;
	}*/

	/**
	 *@Title:queryOilRels
	 *@Description:根据商户获取商户油品关联关系信息
	 *@param:@param merchantsDTO
	 *@param:@return
	 *@return:List<MerOilRel>
	 *@author:谢洪飞
	 *@thorws:
	 */
	/*public List<MerOilRel> queryOilRels(MerchantsDTO merchantsDTO) {
		List<MerOilRel> merOilRels = merOilRelDao.queryMorByMerId(merchantsDTO);
		return merOilRels;
	}*/
	
	/**
	 *@Title:queryOilTypeById
	 *@Description:根据Id获取油品类型
	 *@param:@param oilTypeId
	 *@param:@return
	 *@return:OilType
	 *@author:
	 *@thorws:
	 */
/*	public OilType queryOilTypeById(Integer oilTypeId){
	   return oilTypeDao.queryOilById(oilTypeId);	
	}*/
	
	
}
