package com.blog.dao.profile.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.blog.bean.QueryResult;
import com.blog.bean.profile.Profile;
import com.blog.dao.common.DaoSupport;
import com.blog.dao.profile.ProfileDao;
import com.blog.dto.profile.ProfileDTO;
import com.blog.util.EntityDtoConverter;

//package com.blog.dao.profile.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.Query;
//
//import org.springframework.stereotype.Repository;
//
//import com.paySystem.ic.bean.base.AreaDic;
//import com.paySystem.ic.bean.base.GoodsFamily;
//import com.paySystem.ic.bean.base.Merchants;
//import com.paySystem.ic.bean.base.StoreInfo;
//import com.paySystem.ic.bean.goods.StoreGtypeRel;
//import com.paySystem.ic.dao.base.StoreInfoDAO;
//import com.paySystem.ic.dao.common.DaoSupport;
//import com.paySystem.ic.utils.EntityDtoConverter;
//import com.paySystem.ic.utils.Utils;
//import com.paySystem.ic.web.dto.base.AreaDicDTO;
//import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
//import com.paySystem.ic.web.dto.base.StoreInfoDTO;
//import com.paySystem.ic.web.dto.goods.StoreGtypeRelDTO;
//import com.paySystem.ic.web.dto.system.UserSession;
//
///**
// * @ProjectName:omall
// * @ClassName:StoreInfoDAOImpl
// * @Description:店铺基本设置信息action
// * @date: 2014-9-23下午05:39:06
// * @author: 徐凯强
// * @version: V1.0
// */
@Repository(ProfileDao.PROFILEDAO)
public class ProfileDAOImpl extends DaoSupport<Profile> implements ProfileDao {
	public QueryResult<ProfileDTO> findProfiles() {
		QueryResult<ProfileDTO> profQueryResult = new QueryResult<ProfileDTO>();
		List<ProfileDTO> profileDTOs = new ArrayList<ProfileDTO>();
		QueryResult<Profile> pQueryResult;
		try {
			pQueryResult = this.getScrollData();
			for (Profile profile : pQueryResult.getResultlist()) {
				ProfileDTO profileDTO = new ProfileDTO();
				try {
					profileDTO = (ProfileDTO) EntityDtoConverter.bean2Dto(
							profile, profileDTO);
				} catch (Exception e) {
					e.printStackTrace();
				}
				profileDTOs.add(profileDTO);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		profQueryResult.setResultlist(profileDTOs);
		return profQueryResult;
	}
	// /**
	// *@Title:addSave
	// *@Description:保存店铺设置信息
	// *@param storeInfoDTO店铺设置信息数据传输
	// *@Return:void返回空
	// *@author:徐凯强
	// *@Date:2014-9-23下午05:36:37
	// */
	// public StoreInfo addSave(StoreInfoDTO storeInfoDTO) {
	// StoreInfo s=changeDTOTOEntity(storeInfoDTO);
	// this.save(s);
	// return s;
	// }
	//
	// /**
	// *@Title:changeDTOTOEntity
	// *@Description:将DTO转换为实体
	// *@param storeInfoDTO店铺基本设置数据传输对象
	// *@Return:StoreInfo店铺基本设置实体
	// *@author:徐凯强
	// *@Date:2014-9-23下午05:42:24
	// */
	// public StoreInfo changeDTOTOEntity(StoreInfoDTO storeInfoDTO) {
	// StoreInfo storeInfo = new StoreInfo();
	// UserSession userSession = Utils.getUserSession();
	// storeInfo.setStoreId(userSession.getMerId());
	// storeInfo.setStoreName(storeInfoDTO.getStoreName());
	// // storeInfo.setBank(storeInfoDTO.getBank());
	// // storeInfo.setBankAccNo(storeInfoDTO.getBankAccNo());
	// storeInfo.setBusinType(storeInfoDTO.getBusinType());
	// // storeInfo.setBankName(storeInfoDTO.getBankName());
	// storeInfo.setContPerson(storeInfoDTO.getContPerson());
	// storeInfo.setIsStoreOrNot(storeInfoDTO.getIsStoreOrNot());
	// storeInfo.setDomainAddress(storeInfoDTO.getDomainAddress());
	// storeInfo.setIsFactOrNot(storeInfoDTO.getIsFactOrNot());
	// storeInfo.setMainProduct(storeInfoDTO.getMainProduct());
	// // storeInfo.setSettPeriod(storeInfoDTO.getSettPeriod());
	// // storeInfo.setSettWay(storeInfoDTO.getSettWay());
	// storeInfo.setStoreAddress(storeInfoDTO.getStoreAddress());
	// storeInfo.setStoreDesc(storeInfoDTO.getStoreDesc());
	// storeInfo.setStoreIntroduct(storeInfoDTO.getStoreIntroduct());
	// storeInfo.setTeleNo(storeInfoDTO.getTeleNo());
	// storeInfo.setStoreSign(storeInfoDTO.getImageFileFileName());
	// storeInfo.setLicense(storeInfoDTO.getLicense());
	// storeInfo.setMerId(userSession.getMerId());
	// storeInfo.setProvinceId(storeInfoDTO.getProvinceId());
	// storeInfo.setCityId(storeInfoDTO.getCityId());
	// storeInfo.setNickName(storeInfoDTO.getNickName());
	// return storeInfo;
	// }
	//
	// /**
	// *@Title:updateStoreInfo
	// *@Description:更新
	// *@param storeInfoDTO店铺设置数据传输对象
	// *@Return:void返回空
	// *@author:徐凯强
	// * @return
	// *@Date:2014-9-23下午06:53:02
	// */
	// public StoreInfo updateStoreInfo(StoreInfoDTO storeInfoDTO) {
	// StoreInfo s=changeDTOTOEntity(storeInfoDTO);
	// this.update(s);
	// return s;
	// }
	//
	// /**
	// *@Title:findProvinces
	// *@Description:查询所有
	// *@Return:List<AreaDicDTO>数据传输对象
	// *@author:徐凯强
	// *@Date:2014-11-3下午06:33:07
	// */
	// @SuppressWarnings("unchecked")
	// public List<AreaDicDTO> findProvinces() {
	// String jpql = "from AreaDic o where o.fType=1";
	// Query query = em.createQuery(jpql);
	// List<AreaDic> areaDics = query.getResultList();
	// List<AreaDicDTO> areaDicDTOs = new ArrayList<AreaDicDTO>();
	// areaDicDTOs = (List<AreaDicDTO>) changeBeanToDTO(areaDics);
	// return areaDicDTOs;
	// }
	//
	// /**
	// *@Title:changeBeanToDTO
	// *@Description:实体转DTO
	// *@param areaDics实体集合
	// *@Return:List<AreaDicDTO>
	// *@author:徐凯强
	// *@Date:2014-11-4上午10:41:20
	// */
	// public List<AreaDicDTO> changeBeanToDTO(List<AreaDic> areaDics) {
	// List<AreaDicDTO> areaDicDTOs = new ArrayList<AreaDicDTO>();
	// for (AreaDic areaDic : areaDics) {
	// AreaDicDTO areaDicDTO = new AreaDicDTO();
	// areaDicDTO.setFcode(areaDic.getfCode());
	// areaDicDTO.setFname(areaDic.getfName());
	// areaDicDTO.setFtype(areaDic.getfType());
	// areaDicDTOs.add(areaDicDTO);
	// }
	// return areaDicDTOs;
	// }
	//
	// /**
	// *@Title:findCityByProvinceId
	// *@Description:根据省查找省下面的城市
	// *@param areaDicDTO数据传输对象
	// *@Return:List<AreaDicDTO>数据传输对象集合
	// *@author:徐凯强
	// *@Date:2014-11-4上午11:18:38
	// */
	// @SuppressWarnings("unchecked")
	// public List<AreaDicDTO> findCityByProvinceId(AreaDicDTO areaDicDTO) {
	// String fcode = areaDicDTO.getFcode().toString().substring(0, 2);
	// String jpql = "select o from AreaDic o where substring(o.fCode,1,2)="
	// + fcode + " and o.fType=2";
	// System.out.println(jpql);
	// Query query = em.createQuery(jpql);
	// List<AreaDic> areaDics = query.getResultList();
	// List<AreaDicDTO> areaDicDTOs = new ArrayList<AreaDicDTO>();
	// areaDicDTOs = (List<AreaDicDTO>) changeBeanToDTO(areaDics);
	// return areaDicDTOs;
	// }
	//
	// /**
	// *@Title:chooseTypes
	// *@Description:选择商品分类
	// *@Return:List<GoodsFamilyDTO>商品分类集合
	// *@author:徐凯强
	// *@Date:2014-11-4下午04:23:44
	// */
	// @SuppressWarnings("unchecked")
	// public List<GoodsFamilyDTO> chooseTypes() {
	// String jpql = "select o from GoodsFamily o where o.nodeLevel=1";
	// Query query = em.createQuery(jpql);
	// List<GoodsFamily> goodsFamilies = query.getResultList();
	// List<GoodsFamilyDTO> goodsFamilyDTOs = new ArrayList<GoodsFamilyDTO>();
	// goodsFamilyDTOs = changeGoodsFamilyTODTO(goodsFamilies);
	// return goodsFamilyDTOs;
	// }
	//
	// /**
	// *@Title:changeGoodsFamilyTODTO
	// *@Description:实体转换DTO
	// *@param goodsFamilies商品分类集合
	// *@Return:List<GoodsFamilyDTO>商品分类集合
	// *@author:徐凯强
	// *@Date:2014-11-4下午04:35:44
	// */
	// public List<GoodsFamilyDTO> changeGoodsFamilyTODTO(
	// List<GoodsFamily> goodsFamilies) {
	// List<GoodsFamilyDTO> goodsFamilyDTOs = new ArrayList<GoodsFamilyDTO>();
	// for (int i = 0; i < goodsFamilies.size(); i++) {
	// GoodsFamilyDTO goodsFamilyDTO = new GoodsFamilyDTO();
	// goodsFamilyDTO.setFamilyId(goodsFamilies.get(i).getFamilyId());
	// goodsFamilyDTO.setFamilyName(goodsFamilies.get(i).getFamilyName());
	// goodsFamilyDTOs.add(goodsFamilyDTO);
	// }
	// return goodsFamilyDTOs;
	// }
	//
	// /**
	// *@Title:insertRel
	// *@Description:插入商品类型和店铺关系id
	// *@param storeGtypeRelDTO店铺基本设置表和商品分类表关联实体数据传输对象
	// *@Return:void返回空
	// *@author:徐凯强
	// *@Date:2014-11-6上午11:11:22
	// */
	// public void insertRel(StoreGtypeRelDTO storeGtypeRelDTO) {
	// /** 将字符串split为数组 */
	// String[] ids = storeGtypeRelDTO.getFamiliesIds().split(",");
	// /** 迭代循环取出数组中的元素 */
	// for (int i = 0; i < ids.length; i++) {
	// /** 实例化每一个实体对象 */
	// StoreGtypeRel storeGtypeRel = new StoreGtypeRel();
	// storeGtypeRel.setFamilyId(Integer.parseInt(ids[i]));
	// storeGtypeRel.setStoreId(Utils.getUserSession().getMerId());
	// /** 批量添加到数据库中 */
	// em.persist(storeGtypeRel);
	// }
	// }
	//
	// /**
	// *@Title:deleteAll
	// *@Description:删除所有已选分类
	// *@Return:void返回空
	// *@author:徐凯强
	// *@Date:2014-11-6下午12:52:47
	// */
	// public void deleteAll() {
	// String jpql = "delete from StoreGtypeRel o where o.storeId="
	// + Utils.getUserSession().getMerId();
	// em.createQuery(jpql).executeUpdate();
	// }
	//
	// /**
	// *@Title:findCheckedTypes
	// *@Description:查找所有已选商品分类
	// *@Return:List<GoodsFamilyDTO>数据传输对象集合
	// *@author:徐凯强
	// *@Date:2014-11-6下午01:20:41
	// */
	// @SuppressWarnings("unchecked")
	// public List<GoodsFamilyDTO> findCheckedTypes() {
	// List<GoodsFamilyDTO> goodsFamilyDTOs = new ArrayList<GoodsFamilyDTO>();
	// String jpql =
	// "select o from GoodsFamily o where o.familyId in(select p.familyId from StoreGtypeRel p where p.storeId='"
	// + Utils.getUserSession().getMerId() + "')";
	// System.out.println(jpql);
	// List<GoodsFamily> goodsFamilies = em.createQuery(jpql).getResultList();
	// for (GoodsFamily goodsFamily : goodsFamilies) {
	// GoodsFamilyDTO goodsFamilyDTO = new GoodsFamilyDTO();
	// goodsFamilyDTO.setFamilyId(goodsFamily.getFamilyId());
	// goodsFamilyDTO.setFamilyName(goodsFamily.getFamilyName());
	// goodsFamilyDTOs.add(goodsFamilyDTO);
	// }
	// return goodsFamilyDTOs;
	// }
	//
	// /**
	// *@Title:deleteById
	// *@Description:删除根据编号
	// *@param storeGtypeRelDTO数据传输对象
	// *@Return:void返回空
	// *@author:徐凯强
	// *@Date:2014-11-10下午04:18:26
	// */
	// public void deleteById(StoreGtypeRelDTO storeGtypeRelDTO) {
	// String jpql = "delete  from StoreGtypeRel o where o.familyId="
	// + storeGtypeRelDTO.getFamilyId();
	// em.createQuery(jpql).executeUpdate();
	// }
	//
	// /**
	// *
	// *@MethodName: saveStore
	// *@Description: 批量增加商铺
	// *@param storeInfoDTO
	// *@throws Exception
	// *@Author: 郭营
	// *@Date: 2014-11-19下午02:34:39
	// */
	// @Override
	// public void saveStore(StoreInfoDTO storeInfoDTO) throws Exception {
	// this.save(EntityDtoConverter.bean2Dto(storeInfoDTO, new StoreInfo()));
	// }
	//
	// /**
	// *@MethodName:findByMerId
	// *@Description:根据merId查询店铺信息
	// *@param merId
	// *@return
	// *@Author:孟凡岭
	// *@Date:2014-11-24下午05:31:51
	// */
	// public StoreInfo findByMerId(String merId) {
	// // TODO Auto-generated method stub
	// String sql = "from StoreInfo o where o.merId='" + merId + "'";
	// List<StoreInfo> list = this.findByJpl(sql);
	// if (list.size() > 0) {
	// return list.get(0);
	// }
	// return null;
	// }
	//
	// /**
	// *@MethodName:ajaxObject
	// *@Description:验证广告对象
	// *@param id
	// *@return
	// *@Author:孟凡岭
	// *@Date:2014-12-8上午11:19:48
	// */
	// public boolean ajaxObject(String id) {
	// // TODO Auto-generated method stub
	// StoreInfo s=this.find(id);
	// if(s!=null){
	// return true;
	// }else{
	// return false;
	// }
	// }
	//
}
