package com.paySystem.ic.service.base;

import java.util.LinkedHashMap;
import java.util.List;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.MerOilRel;
import com.paySystem.ic.bean.base.Merchants;
/*import com.paySystem.ic.bean.base.OilPrice;
import com.paySystem.ic.bean.base.OilType;*/
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.MerchantsDTO;
import com.paySystem.ic.web.ui.OptionsString;

public interface MerchantsService {
	
	public boolean validate(String merId);
	
	public boolean validateName(String nameChinese,String merId);
	
	public Merchants find(String merId);
	
	public MerchantsDTO findMerFromBussParam(String merId);
	 
	public List<OptionsString> getOption();
	
	/**
	 * 获取上级经销商
	 *@Title:getPreMerchants
	 *@Description:TODO
	 *@param:@return
	 *@return:List<OptionsString>
	 *@author:谢洪飞
	 *@thorws:
	 */
	public List<OptionsString> getPreMerchants();
	
	public List<OptionsString> getOptionByOrganId(String organId);
	
	public Merchants saveMerchant(MerchantsDTO merchantsDTO) throws Exception;
	
	/**
	 *@Title:updateMerchant
	 *@Description:更新商户信息
	 *@param:@param merchantsDTO  商户DTO类
	 *@param:@return
	 *@param:@throws Exception 
	 *@return:ReturnDTO
	 *@author: 谢
	 *@thorws:
	 */
	public ReturnDTO updateMerchant(MerchantsDTO merchantsDTO) throws Exception;

	public QueryResult<Merchants> queryMerByCond(int firstindex, int pageNum, MerchantsDTO merchantsDTO,
			LinkedHashMap<String, String> orderby) throws Exception;

	public String getMerId();

	public void update(Merchants merchants);
	
	/**
	 *@Title:getMerByOrgan
	 *@Description:根据 机构号获取商户集合
	 *@param:@param organId
	 *@param:@return
	 *@return:List<Merchants> 商户集合 
	 *@author:谢
	 *@thorws:
	 */
	List<Merchants> getMerByOrgan(String organId);
	
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
	public QueryResult<Merchants> preMerHelpList(int firstindex, int pageNum, MerchantsDTO merchantsDTO,
			LinkedHashMap<String, String> orderby) throws Exception;
	
	
	
	
	/**
	 *@Title:getOilPriceList
	 *@Description：根据商户获取油品价格信息
	 *@param:@param merchantsDTO
	 *@param:@return
	 *@return:List<OilPrice>
	 *@author:谢洪飞
	 *@thorws:
	 */
	/*public List<OilPrice> queryOilPriceList(MerchantsDTO merchantsDTO);*/
	
	/**
	 *@Title:queryOilRels
	 *@Description:根据商户获取商户油品关联关系信息
	 *@param:@param merchantsDTO
	 *@param:@return
	 *@return:List<MerOilRel>
	 *@author:谢洪飞
	 *@thorws:
	 */
	public List<MerOilRel> queryOilRels(MerchantsDTO merchantsDTO);
	
	/**
	 *@Title:queryOilTypeById
	 *@Description:根据Id获取油品类型
	 *@param:@param oilTypeId
	 *@param:@return
	 *@return:OilType
	 *@author:
	 *@thorws:
	 */
	/*public OilType queryOilTypeById(Integer oilTypeId);*/
	
	public static final String MERCHANTSSERVICE ="merchantsService";
	
}
