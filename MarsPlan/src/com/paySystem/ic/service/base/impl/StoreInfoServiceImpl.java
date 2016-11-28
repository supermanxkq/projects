package com.paySystem.ic.service.base.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.base.StoreInfo;
import com.paySystem.ic.dao.base.StoreInfoDAO;
import com.paySystem.ic.service.base.StoreInfoService;
import com.paySystem.ic.utils.UploadUtil;
import com.paySystem.ic.web.dto.base.StoreInfoDTO;

/**
 * @ProjectName:omall
 * @ClassName:StoreInfoServiceImpl
 * @Description:店铺基本设置服务接口实现类
 * @date: 2014-9-23下午05:32:33
 * @author: 徐凯强
 * @version: V1.0
 */
@Repository(StoreInfoService.STOREINFOSERVICE)
public class StoreInfoServiceImpl implements StoreInfoService {

	/** 注入Dao */
	@Resource
	private StoreInfoDAO storeInfoDAO;

	/**
	 *@Title:addSave
	 *@Description:保存店铺基本设置信息
	 *@param storeInfoDTO店铺基本设置数据传输对象
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-9-23下午05:30:17
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSave(StoreInfoDTO storeInfoDTO) {
		if (storeInfoDTO != null) {
			if (storeInfoDTO.getImageFile() != null
					&& StringUtils.isNotBlank(storeInfoDTO
							.getImageFileFileName())) {
				/** 上传文件 **/
				String fileName = null;
				try {
					fileName = UploadUtil.uploadImg(
							storeInfoDTO.getImageFile(), storeInfoDTO
									.getImageFileFileName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				/** 设置文件名 **/
				storeInfoDTO.setImageFileFileName(fileName);
			}
			storeInfoDAO.addSave(storeInfoDTO);
		}

	}

	/**
	 *@Title:findById
	 *@Description:根据店铺编号查找数据库中的记录
	 *@param storeId店铺编号
	 *@Return:StoreInfoDTO店铺数据传输对象
	 *@author:徐凯强
	 *@Date:2014-9-23下午06:13:37
	 */
	public StoreInfoDTO findById(Integer storeId) {
		return changeEntityTODTO(storeInfoDAO.find(storeId));
	}

	/**
	 *@Title:changeEntityTODTO
	 *@Description:将实体转化为DTO
	 *@param storeInfo店铺设置数据传输对象
	 *@Return:StoreInfoDTO店铺设置数据对象
	 *@author:徐凯强
	 *@Date:2014-9-23下午06:17:01
	 */
	public StoreInfoDTO changeEntityTODTO(StoreInfo storeInfo) {
		StoreInfoDTO storeInfoDTO = new StoreInfoDTO();
		if (storeInfo != null) {
			storeInfoDTO.setStoreId(storeInfo.getStoreId());
			storeInfoDTO.setStoreName(storeInfo.getStoreName());
			storeInfoDTO.setBank(storeInfo.getBank());
			storeInfoDTO.setBankAccNo(storeInfo.getBankAccNo());
			storeInfoDTO.setBusinType(storeInfo.getBusinType());
			storeInfoDTO.setBankName(storeInfo.getBankName());
			storeInfoDTO.setContPerson(storeInfo.getContPerson());
			storeInfoDTO.setIsStoreOrNot(storeInfo.getIsStoreOrNot());
			storeInfoDTO.setDomainAddress(storeInfo.getDomainAddress());
			storeInfoDTO.setIsFactOrNot(storeInfo.getIsFactOrNot());
			storeInfoDTO.setMainProduct(storeInfo.getMainProduct());
			storeInfoDTO.setSettPeriod(storeInfo.getSettPeriod());
			storeInfoDTO.setSettWay(storeInfo.getSettWay());
			storeInfoDTO.setStoreAddress(storeInfo.getStoreAddress());
			storeInfoDTO.setStoreDesc(storeInfo.getStoreDesc());
			storeInfoDTO.setStoreIntroduct(storeInfo.getStoreIntroduct());
			storeInfoDTO.setTeleNo(storeInfo.getTeleNo());
			storeInfoDTO.setImageFileFileName(storeInfo.getStoreSign());
			storeInfoDTO.setLicense(storeInfo.getLicense());
		}
		return storeInfoDTO;
	}

	/**
	 *@Title:updateStoreInfo
	 *@Description:更新
	 *@param storeInfoDTO店铺设置数据传输对象
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-9-23下午06:53:02
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateStoreInfo(StoreInfoDTO storeInfoDTO) {
		if (storeInfoDTO != null) {
			if (storeInfoDTO.getImageFile() != null
					&& StringUtils.isNotBlank(storeInfoDTO
							.getImageFileFileName())) {
				/** 上传文件 **/
				String fileName = null;
				try {
					fileName = UploadUtil.uploadImg(
							storeInfoDTO.getImageFile(), storeInfoDTO
									.getImageFileFileName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				/** 设置文件名 **/
				storeInfoDTO.setImageFileFileName(fileName);
			}
			storeInfoDAO.updateStoreInfo(storeInfoDTO);
		}
	}
}