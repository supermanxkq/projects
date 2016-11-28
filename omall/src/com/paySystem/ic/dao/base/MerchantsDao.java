package com.paySystem.ic.dao.base;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.MerchantsDTO;
import com.paySystem.ic.web.dto.base.StoreInfoDTO;
import com.paySystem.ic.web.ui.OptionsString;

/**
 * @ClassName:MerchantsDao
 * @Description:商户Dao接口
 * @date: 2013-12-7上午10:08:16
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface MerchantsDao extends DAO<Merchants> {
	

	 /**
	 *@Title:validate
	 *@Description:检查商户编号是否已经存在
	 *@param:@param merId
	 *@param:@return
	 *@return:boolean
	 *@thorws:
	 */
	boolean validate(String merId);
	
	/**
	 *@Title:validateName
	 *@Description:检查商户名称是否已经存在
	 *@param:@param nameChinese
	 *@param:@return
	 *@return:boolean
	 *@thorws:
	 */
	boolean validateName(String nameChinese,String merId);
	/**
	 *@Title:getMerId
	 *@Description:获取商户编号
	 *@param:@return
	 *@return:String
	 *@thorws:
	 */
	String getMerId();
	
	/**
	 *@Title:getOption
	 *@Description:从数据库获取选项信息
	 *@param:@return
	 *@return:List<OptionsString>
	 *@thorws:
	 */
	List<OptionsString> getOption();
	
	/**
	 *@Title:getOptionByOrganId
	 *@Description:通过机构编号获取商户
	 *@param:@param organId
	 *@param:@return
	 *@return:List<OptionsString>
	 *@thorws:
	 */
	List<OptionsString> getOptionByOrganId(String organId);
	
	/**
	 *@Title:saveMerchant
	 *@Description:保存商户信息
	 *@param:@param merchantsDTO
	 *@param:@return
	 *@return:Merchants
	 *@thorws:
	 */
	Merchants saveMerchant(MerchantsDTO merchantsDTO);
	
	/**
	 *@Title:updateMerchant
	 *@Description:更新商户信息
	 *@param:@param merchantsDTO
	 *@param:@return
	 *@return:ReturnDTO
	 * @throws Exception 
	 *@thorws:
	 */
	ReturnDTO updateMerchant(MerchantsDTO merchantsDTO) throws Exception;
	
	
	/**
	 *@Title:queryMerByCond
	 *@Description:根据条件查询商户信息
	 *@param:@param firstindex
	 *@param:@param maxresult
	 *@param:@param wherejpql
	 *@param:@param queryParams
	 *@param:@param orderby
	 *@param:@return
	 *@param:@throws Exception
	 *@return:QueryResult
	 *@thorws:
	 */
	@SuppressWarnings("unchecked")
	QueryResult queryMerByCond(int firstindex, int maxresult,
			MerchantsDTO merchantsDTO,
			LinkedHashMap<String, String> orderby) throws Exception;
	public static final String MERCHANTSDAO = "merchantsDao";
	
	/**
	 *@Title:getAllMerchants
	 *@Description:获取所有商户信息
	 *@param:@return
	 *@return:List<Merchants>
	 *@author:
	 *@thorws:
	 */
	List<Merchants> getAllMerchants();
	
	/**
	 *@Title:getMerchantsNeedToSett
	 *@Description:获取需要结算的商户信息
	 *@param:@return
	 *@return:List<Merchants> 需要结算的商户集合
	 *@author:谢
	 *@thorws:
	 */
	List<Merchants> getMerchantsNeedToSett();
	
	/**
	 *@Title:batchUpdateMerchants
	 *@Description:批量更新
	 *@param:@param merchantsList 商户集合
	 *@return:void
	 *@author: 谢
	 *@thorws:
	 */
	void batchUpdateMersLastSettDate(List<Merchants> merchantsList);
	
	/**
	 *@Title:getMerByOrganId
	 *@Description:根据机构查询所有下属商户信息
	 *@param:@return
	 *@return:List<Merchants> 下属商户信息集合
	 *@author:  谢
	 *@thorws:
	 */
	List<Merchants> getMerByOrganId(String organId);
	
	
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
	
	/**
	 *@Title:preMerHelpList
	 *@Description:获取上级经销商信息集合
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
			throws Exception;
	
	/**
	 * 
	 *@Title:queryByName
	 *@Description:根据名字查询运出一个集合
	 *@param:@param name
	 *@param:@return
	 *@return:List<Merchants>
	 *@author:井建国
	 *@thorws:
	 */
	public List<Merchants> queryByName(String name);

	/**
	*@Title:updateIsBankCard
	*@Description:更新商户是否支持银行卡
	*@Params:@param storeInfoDTO
	*@Return:void
	*@author:孟凡岭
	*@Date:2014-12-5上午10:44:39
	*/
	void updateIsBankCard(StoreInfoDTO storeInfoDTO);
}
