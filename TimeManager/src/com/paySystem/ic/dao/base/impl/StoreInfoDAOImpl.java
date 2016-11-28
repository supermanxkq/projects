package com.paySystem.ic.dao.base.impl;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.base.StoreInfo;
import com.paySystem.ic.dao.base.StoreInfoDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.web.dto.base.StoreInfoDTO;

/**
 * @ProjectName:omall
 * @ClassName:StoreInfoDAOImpl
 * @Description:店铺基本设置信息action
 * @date: 2014-9-23下午05:39:06
 * @author: 徐凯强
 * @version: V1.0
 */
@Repository(StoreInfoDAO.STOREINFODAO)
public class StoreInfoDAOImpl extends DaoSupport<StoreInfo> implements
		StoreInfoDAO {
	/**
	 *@Title:addSave
	 *@Description:保存店铺设置信息
	 *@param storeInfoDTO店铺设置信息数据传输
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-9-23下午05:36:37
	 */
	public void addSave(StoreInfoDTO storeInfoDTO) {
		this.save(changeDTOTOEntity(storeInfoDTO));
	}

	/**
	 *@Title:changeDTOTOEntity
	 *@Description:将DTO转换为实体
	 *@param storeInfoDTO店铺基本设置数据传输对象
	 *@Return:StoreInfo店铺基本设置实体
	 *@author:徐凯强
	 *@Date:2014-9-23下午05:42:24
	 */
	public StoreInfo changeDTOTOEntity(StoreInfoDTO storeInfoDTO) {
		StoreInfo storeInfo = new StoreInfo();
		storeInfo.setStoreId(1);
		storeInfo.setStoreName(storeInfoDTO.getStoreName());
		storeInfo.setBank(storeInfoDTO.getBank());
		storeInfo.setBankAccNo(storeInfoDTO.getBankAccNo());
		storeInfo.setBusinType(storeInfoDTO.getBusinType());
		storeInfo.setBankName(storeInfoDTO.getBankName());
		storeInfo.setContPerson(storeInfoDTO.getContPerson());
		storeInfo.setIsStoreOrNot(storeInfoDTO.getIsStoreOrNot());
		storeInfo.setDomainAddress(storeInfoDTO.getDomainAddress());
		storeInfo.setIsFactOrNot(storeInfoDTO.getIsFactOrNot());
		storeInfo.setMainProduct(storeInfoDTO.getMainProduct());
		storeInfo.setSettPeriod(storeInfoDTO.getSettPeriod());
		storeInfo.setSettWay(storeInfoDTO.getSettWay());
		storeInfo.setStoreAddress(storeInfoDTO.getStoreAddress());
		storeInfo.setStoreDesc(storeInfoDTO.getStoreDesc());
		storeInfo.setStoreIntroduct(storeInfoDTO.getStoreIntroduct());
		storeInfo.setTeleNo(storeInfoDTO.getTeleNo());
		storeInfo.setStoreSign(storeInfoDTO.getImageFileFileName());
		storeInfo.setLicense(storeInfoDTO.getLicense());
		return storeInfo;
	}

	/**
	 *@Title:updateStoreInfo
	 *@Description:更新
	 *@param storeInfoDTO店铺设置数据传输对象
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-9-23下午06:53:02
	 */
	public void updateStoreInfo(StoreInfoDTO storeInfoDTO) {
		this.update(changeDTOTOEntity(storeInfoDTO));
	}

}
