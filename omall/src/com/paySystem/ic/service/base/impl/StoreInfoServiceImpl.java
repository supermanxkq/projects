package com.paySystem.ic.service.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.StoreInfo;
import com.paySystem.ic.dao.base.MerchantsDao;
import com.paySystem.ic.dao.base.StoreInfoDAO;
import com.paySystem.ic.service.base.StoreInfoService;
import com.paySystem.ic.utils.UploadUtil;
import com.paySystem.ic.web.dto.base.AreaDicDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
import com.paySystem.ic.web.dto.base.StoreInfoDTO;
import com.paySystem.ic.web.dto.goods.StoreGtypeRelDTO;

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
	@Resource
	private MerchantsDao merchantsDao;

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
			StoreInfo s = storeInfoDAO.addSave(storeInfoDTO);
			Merchants m = merchantsDao.find(s.getStoreId());
			m.setIsBankCard(storeInfoDTO.getIsBankCard());
			merchantsDao.update(m);
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
	public StoreInfoDTO findById(String storeId) {
		StoreInfoDTO s=changeEntityTODTO(storeInfoDAO.find(storeId));
		Merchants m=merchantsDao.find(s.getStoreId());
		s.setIsBankCard(m.getIsBankCard());
		return s;
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
			// storeInfoDTO.setBank(storeInfo.getBank());
			// storeInfoDTO.setBankAccNo(storeInfo.getBankAccNo());
			storeInfoDTO.setBusinType(storeInfo.getBusinType());
			// storeInfoDTO.setBankName(storeInfo.getBankName());
			storeInfoDTO.setContPerson(storeInfo.getContPerson());
			storeInfoDTO.setIsStoreOrNot(storeInfo.getIsStoreOrNot());
			storeInfoDTO.setDomainAddress(storeInfo.getDomainAddress());
			storeInfoDTO.setIsFactOrNot(storeInfo.getIsFactOrNot());
			storeInfoDTO.setMainProduct(storeInfo.getMainProduct());
			// storeInfoDTO.setSettPeriod(storeInfo.getSettPeriod());
			// storeInfoDTO.setSettWay(storeInfo.getSettWay());
			storeInfoDTO.setStoreAddress(storeInfo.getStoreAddress());
			storeInfoDTO.setStoreDesc(storeInfo.getStoreDesc());
			storeInfoDTO.setStoreIntroduct(storeInfo.getStoreIntroduct());
			storeInfoDTO.setTeleNo(storeInfo.getTeleNo());
			storeInfoDTO.setImageFileFileName(storeInfo.getStoreSign());
			storeInfoDTO.setLicense(storeInfo.getLicense());
			storeInfoDTO.setNickName(storeInfo.getNickName());
			storeInfoDTO.setProvinceId(storeInfo.getProvinceId());
			storeInfoDTO.setCityId(storeInfo.getCityId());
			storeInfoDTO.setImageFileFileName(storeInfo.getStoreSign());
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
			StoreInfo s = storeInfoDAO.updateStoreInfo(storeInfoDTO);
			Merchants m=merchantsDao.find(s.getStoreId());
			m.setIsBankCard(storeInfoDTO.getIsBankCard());
			merchantsDao.update(m);
		}
	}

	/**
	 *@Title:findProvinces
	 *@Description:查询所有
	 *@Return:List<AreaDicDTO>数据传输对象
	 *@author:徐凯强
	 *@Date:2014-11-3下午06:33:07
	 */
	@Override
	public List<AreaDicDTO> findProvinces() {
		return storeInfoDAO.findProvinces();
	}

	/**
	 *@Title:findCityByProvinceId
	 *@Description:根据省查找省下面的城市
	 *@param areaDicDTO数据传输对象
	 *@Return:List<AreaDicDTO>数据传输对象集合
	 *@author:徐凯强
	 *@Date:2014-11-4上午11:18:38
	 */
	public List<AreaDicDTO> findCityByProvinceId(AreaDicDTO areaDicDTO) {
		return storeInfoDAO.findCityByProvinceId(areaDicDTO);
	}

	/**
	 *@Title:chooseTypes
	 *@Description:选择商品分类
	 *@Return:List<GoodsFamilyDTO>商品分类集合
	 *@author:徐凯强
	 *@Date:2014-11-4下午04:23:44
	 */
	public List<GoodsFamilyDTO> chooseTypes() {
		return storeInfoDAO.chooseTypes();
	}

	/**
	 *@Title:insertRel
	 *@Description:插入商品类型和店铺关系id
	 *@param storeGtypeRelDTO
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-11-6上午11:11:22
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insertRel(StoreGtypeRelDTO storeGtypeRelDTO) {
		storeInfoDAO.insertRel(storeGtypeRelDTO);
	}

	/**
	 *@Title:deleteAll
	 *@Description:删除所有已选分类
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-11-6下午12:52:47
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteAll() {
		storeInfoDAO.deleteAll();
	}

	/**
	 *@Title:findCheckedTypes
	 *@Description:查找所有已选商品分类
	 *@Return:List<GoodsFamilyDTO>数据传输对象集合
	 *@author:徐凯强
	 *@Date:2014-11-6下午01:20:41
	 */
	public List<GoodsFamilyDTO> findCheckedTypes() {
		return storeInfoDAO.findCheckedTypes();
	}

	/**
	 *@Title:deleteById
	 *@Description:删除根据编号
	 *@param storeGtypeRelDTO数据传输对象
	 *@Return:void返回空
	 *@author:徐凯强
	 *@Date:2014-11-10下午04:18:26
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteById(StoreGtypeRelDTO storeGtypeRelDTO) {
		storeInfoDAO.deleteById(storeGtypeRelDTO);
	}

	public StoreInfoDAO getStoreInfoDAO() {
		return storeInfoDAO;
	}

	public void setStoreInfoDAO(StoreInfoDAO storeInfoDAO) {
		this.storeInfoDAO = storeInfoDAO;
	}

	public MerchantsDao getMerchantsDao() {
		return merchantsDao;
	}

	public void setMerchantsDao(MerchantsDao merchantsDao) {
		this.merchantsDao = merchantsDao;
	}

	/**
	 *@MethodName:ajaxObject
	 *@Description:验证广告对象
	 *@param id
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-12-8上午11:19:10
	 */
	public boolean ajaxObject(String id) {
		// TODO Auto-generated method stub
		return storeInfoDAO.ajaxObject(id);
	}

}