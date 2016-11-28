package com.paySystem.ic.dao.stock.impl;
import org.springframework.stereotype.Repository;
import com.paySystem.ic.bean.member.MemGrowValue;
import com.paySystem.ic.bean.stock.BussParamsConfig;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.stock.BussParamsConfigDAO;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.stock.BussParamsConfigDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ProjectName:omall
 * @ClassName:BussParamsConfigDAOImpl
 * @Description:商城业务参数配置DAO接口的实现类
 * @date: 2014-10-14
 * @author: 王楠
 * @version: V1.0
 */
@Repository(BussParamsConfigDAO.BUSSPARAMSCONFIGDAO)
public class BussParamsConfigDAOImpl extends DaoSupport<BussParamsConfig> 
                                            implements BussParamsConfigDAO{
	
	/**
	*@Title:getPayInterConfDTO
	*@Description:实体转DTO
	*@Params:@param bussParamsConfig 商城业务参数实体
	*@Params:@param memGrowValue 会员成长值实体
	*@Params:@return
	*@Params:@throws Exception
	*@Return:BussParamsConfigDTO 商城业务参数DTO
	*@author:王楠
	*@Date:2014-10-14下午03:32:44
	*/
	@SuppressWarnings("unused")
	private BussParamsConfigDTO getBussParamsConfigDTO(BussParamsConfig bussParamsConfig,MemGrowValue memGrowValue)throws Exception{
		BussParamsConfigDTO bussParamsConfigDTO=new BussParamsConfigDTO();
		bussParamsConfigDTO=(BussParamsConfigDTO)EntityDtoConverter.bean2Dto(bussParamsConfig, bussParamsConfigDTO);
		bussParamsConfigDTO=(BussParamsConfigDTO)EntityDtoConverter.bean2Dto(memGrowValue, bussParamsConfigDTO);
		return bussParamsConfigDTO;
	}

	/**
	*@Title:getBussParamsConfig
	*@Description:将DTO转换成实体
	*@Params:@param bussParamsConfigDTO 业务参数实体DTO
	*@Params:@return
	*@Params:@throws Exception
	*@Return:BussParamsConfig 业务参数实体
	*@author:王楠
	*@Date:2014-10-14下午03:39:26
	*/
	@SuppressWarnings("unused")
	private BussParamsConfig getBussParamsConfig(BussParamsConfigDTO bussParamsConfigDTO)throws Exception{
		BussParamsConfig bussParamsConfig=new BussParamsConfig();
		bussParamsConfig=(BussParamsConfig)EntityDtoConverter.bean2Dto(bussParamsConfigDTO, bussParamsConfig);
		return bussParamsConfig;
	}
	/**
	*@Title:getMemGrowValue
	*@Description:将会员成长值实体转换成DTO
	*@Params:@param bussParamsConfigDTO 业务参数实体DTO
	*@Params:@return
	*@Params:@throws Exception
	*@Return:MemGrowValue 会员成长值实体
	*@author:王楠
	*@Date:2014-10-14下午03:40:22
	*/
	@SuppressWarnings("unused")
	private MemGrowValue getMemGrowValue(BussParamsConfigDTO bussParamsConfigDTO)throws Exception{
		MemGrowValue memGrowValue=new MemGrowValue();
		memGrowValue=(MemGrowValue)EntityDtoConverter.bean2Dto(bussParamsConfigDTO, memGrowValue);
		return memGrowValue;
	}
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.stock.BussParamsConfigDAO#addBussParamsConfig(com.paySystem.ic.web.dto.stock.BussParamsConfigDTO)
	 *@MethodName:addBussParamsConfig
	 *@Description:保存业务参数配置信息
	 *@param bussParamsConfigDTO 商城业务参数实体DTO
	 * @throws Exception 
	 * @throws Exception 
	 *@Author:王楠
	 *@Date:2014-10-14下午02:56:29
	 */
	public void addBussParamsConfig(BussParamsConfigDTO bussParamsConfigDTO) throws Exception {
		UserSession us=Utils.getUserSession();
		bussParamsConfigDTO.setOperMan(us.getUserName());
		bussParamsConfigDTO.setOperTime(getSysTime());
		
		BussParamsConfig bussParamsConfig=this.getBussParamsConfig(bussParamsConfigDTO);
		MemGrowValue memGrowValue=this.getMemGrowValue(bussParamsConfigDTO);
		this.save(bussParamsConfig);
		this.save(memGrowValue);
	}
	
	/**
	*@Title:setZeroMethod
	*@Description:封装的私有方法
	*@Params:@param bussParamsConfigDTO
	*@Params:@return
	*@Return:BussParamsConfigDTO
	*@author:王楠
	*@Date:2014-10-16上午11:35:31
	*/
	public BussParamsConfigDTO setZeroMethod(BussParamsConfigDTO bussParamsConfigDTO){
		if(bussParamsConfigDTO.getLoginValue()==null||
			      bussParamsConfigDTO.getLoginValue().equals("")){
		bussParamsConfigDTO.setLoginValue(0);
		}
		if(bussParamsConfigDTO.getBaskPhotosValue()==null ||
				 bussParamsConfigDTO.getBaskPhotosValue().equals("")){
			bussParamsConfigDTO.setBaskPhotosValue(0);
		}
		if(bussParamsConfigDTO.getBaskValue()==null ||
				bussParamsConfigDTO.getBaskValue().equals("")){
			bussParamsConfigDTO.setBaskValue(0);
		}
		if(bussParamsConfigDTO.getBaskVideoValue()==null ||
				bussParamsConfigDTO.getBaskVideoValue().equals("")){
			bussParamsConfigDTO.setBaskVideoValue(0);
		}
		if(bussParamsConfigDTO.getEmailAuthValue()==null ||
				bussParamsConfigDTO.getEmailAuthValue().equals("")){
			bussParamsConfigDTO.setEmailAuthValue(0);
		}
		if(bussParamsConfigDTO.getGoodsEvaluValue()==null ||
				bussParamsConfigDTO.getGoodsEvaluValue().equals("")){
			bussParamsConfigDTO.setGoodsEvaluValue(0);
		}
		if(bussParamsConfigDTO.getPhoneAuthValue()==null ||
				bussParamsConfigDTO.getPhoneAuthValue().equals("")){
			bussParamsConfigDTO.setPhoneAuthValue(0);
		}
		if(bussParamsConfigDTO.getRealNameAuthValue()==null ||
				bussParamsConfigDTO.getRealNameAuthValue().equals(0)){
			bussParamsConfigDTO.setRealNameAuthValue(0);
		}
		if(bussParamsConfigDTO.getRecomClientValue()==null ||
				bussParamsConfigDTO.getRecomClientValue().equals("")){
			bussParamsConfigDTO.setRecomClientValue(0);
		}
		if(bussParamsConfigDTO.getServiceEvaluValue()==null ||
				bussParamsConfigDTO.getServiceEvaluValue().equals("")){
			bussParamsConfigDTO.setServiceEvaluValue(0);
		}
		return bussParamsConfigDTO;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.stock.BussParamsConfigDAO#findList()
	 *@MethodName:findList
	 *@Description:查找数据的方法
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-10-15下午03:37:38
	 */
	
	public BussParamsConfigDTO findList() throws Exception {
		BussParamsConfigDTO bussParamsConfigDto = new BussParamsConfigDTO();
		BussParamsConfig bussParamsConfig= (BussParamsConfig) em.createQuery("from BussParamsConfig").getResultList().get(0);
		MemGrowValue memGrowValue= (MemGrowValue) em.createQuery("from MemGrowValue").getResultList().get(0);
		bussParamsConfigDto = getBussParamsConfigDTO(bussParamsConfig, memGrowValue);
		
		bussParamsConfigDto = setZeroMethod(bussParamsConfigDto);
		return bussParamsConfigDto;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.stock.BussParamsConfigDAO#updateBussParamsConfig(com.paySystem.ic.web.dto.stock.BussParamsConfigDTO)
	 *@MethodName:updateBussParamsConfig
	 *@Description:修改业务参数配置信息
	 *@param bussParamsConfigDTO 业务参数实体的DTO 
	 *@return
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-10-15下午03:42:48
	 */
	public ReturnDTO updateBussParamsConfig(BussParamsConfigDTO bussParamsConfigDTO) throws Exception {
		ReturnDTO returnDTO=new ReturnDTO();
		BussParamsConfig bussParamsConfig=this.getBussParamsConfig(bussParamsConfigDTO);
		
		bussParamsConfigDTO = setZeroMethod(bussParamsConfigDTO);
		MemGrowValue memGrowValue=this.getMemGrowValue(bussParamsConfigDTO);
		UserSession us=Utils.getUserSession();
		bussParamsConfig.setOperMan(us.getUserName());
		bussParamsConfig.setOperTime(getSysTime());
		this.update(bussParamsConfig);
		this.update(memGrowValue);
		returnDTO.setFlag(true);
		return returnDTO;
	}
	
}
