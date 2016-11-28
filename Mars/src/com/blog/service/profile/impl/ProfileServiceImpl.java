package com.blog.service.profile.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.blog.bean.QueryResult;
import com.blog.dao.profile.ProfileDao;
import com.blog.dto.profile.ProfileDTO;
import com.blog.service.profile.ProfileService;

//package com.blog.service.profile.impl;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.blog.bean.profile.Profile;
//import com.blog.dao.profile.ProfileDao;
//import com.blog.dto.profile.ProfileDTO;
//import com.blog.service.profile.ProfileService;
//import com.blog.util.UploadUtil;
//
@Repository(ProfileService.PROFILESERVICE)
public class ProfileServiceImpl implements ProfileService {

	@Resource
	private ProfileDao profileDao;
	public void addSave(ProfileDTO profileDTO) {
		
	}

	public ProfileDTO findById(String Id) {
		return null;
	}

	public QueryResult<ProfileDTO> findProfiles() {
		return profileDao.findProfiles();
	}

	public void updateData(ProfileDTO profileDTO) {
		
	}
	
//
	
//	/** 注入Dao */
//	@Resource
//	private ProfileDao profileDao;
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	public void addSave(ProfileDTO profileDTO) {
//		if (storeInfoDTO != null) {
//			if (storeInfoDTO.getImageFile() != null
//					&& StringUtils.isNotBlank(storeInfoDTO
//							.getImageFileFileName())) {
//				/** 上传文件 **/
//				String fileName = null;
//				try {
//					fileName = UploadUtil.uploadImg(
//							storeInfoDTO.getImageFile(), storeInfoDTO
//									.getImageFileFileName());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				/** 设置文件名 **/
//				storeInfoDTO.setImageFileFileName(fileName);
//			}
//			Profile p = profileDao.addSave(profileDTO);
//			Merchants m = merchantsDao.find(s.getStoreId());
//			m.setIsBankCard(storeInfoDTO.getIsBankCard());
//			merchantsDao.update(m);
//		}
//
//	}
//
//	public ProfileDTO findById(String id) {
//		StoreInfoDTO s=changeEntityTODTO(storeInfoDAO.find(storeId));
//		Merchants m=merchantsDao.find(s.getStoreId());
//		s.setIsBankCard(m.getIsBankCard());
//		return s;
//	}
//
//
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	public void updateDatas(ProfileDTO profileDTO) {
//		if (storeInfoDTO != null) {
//			if (storeInfoDTO.getImageFile() != null
//					&& StringUtils.isNotBlank(storeInfoDTO
//							.getImageFileFileName())) {
//				/** 上传文件 **/
//				String fileName = null;
//				try {
//					fileName = UploadUtil.uploadImg(
//							storeInfoDTO.getImageFile(), storeInfoDTO
//									.getImageFileFileName());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				/** 设置文件名 **/
//				storeInfoDTO.setImageFileFileName(fileName);
//			}
//			StoreInfo s = storeInfoDAO.updateStoreInfo(storeInfoDTO);
//			Merchants m=merchantsDao.find(s.getStoreId());
//			m.setIsBankCard(storeInfoDTO.getIsBankCard());
//			merchantsDao.update(m);
//		}
//	}
//
//
//
//	/**
//	 *@Title:deleteById
//	 *@Description:删除根据编号
//	 *@param storeGtypeRelDTO数据传输对象
//	 *@Return:void返回空
//	 *@author:徐凯强
//	 *@Date:2014-11-10下午04:18:26
//	 */
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	public void deleteById(ProfileDTO profileDTO) {
//		profileDao.deleteById(storeGtypeRelDTO);
//	}
}