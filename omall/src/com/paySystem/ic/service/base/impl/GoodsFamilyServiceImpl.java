package com.paySystem.ic.service.base.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsFamily;
import com.paySystem.ic.bean.base.GoodsFamilyAdvertRelation;
import com.paySystem.ic.bean.base.GoodsFamilyAttrRela;
import com.paySystem.ic.bean.base.GoodsFamilyRateRel;
import com.paySystem.ic.dao.base.GoodsFamilyAdvertRelationDao;
import com.paySystem.ic.dao.base.GoodsFamilyAttrRelaDao;
import com.paySystem.ic.dao.base.GoodsFamilyDao;
import com.paySystem.ic.dao.base.GoodsFamilyRateRelDao;
import com.paySystem.ic.dao.base.impl.GoodsFamilyRateRelDaoImpl;
import com.paySystem.ic.service.base.GoodsFamilyRateRelService;
import com.paySystem.ic.service.base.GoodsFamilyService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
import com.paySystem.ic.web.dto.goods.GoodsTypeDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ClassName:GoodsFamilyServiceImpl
 * @Description:商品分类Service实现
 * @date: 2014-6-26下午03:36:32
 * @author: 张亚运
 * @version: V1.0
 */
@Service(GoodsFamilyService.GOODSFAMILYSERVICE)
public class GoodsFamilyServiceImpl implements GoodsFamilyService {

	@Resource
	GoodsFamilyDao goodsFamilyDao;
	@Resource
	GoodsFamilyAdvertRelationDao goRelationDao;
	@Resource
	GoodsFamilyAttrRelaDao goodsFamilyAttrRelaDao;
	@Resource
	private GoodsFamilyRateRelDao goodsFamilyRateRelDao = new GoodsFamilyRateRelDaoImpl();

	public GoodsFamilyRateRelDao getGoodsFamilyRateRelDao() {
		return goodsFamilyRateRelDao;
	}

	public void setGoodsFamilyRateRelDao(
			GoodsFamilyRateRelDao goodsFamilyRateRelDao) {
		this.goodsFamilyRateRelDao = goodsFamilyRateRelDao;
	}

	public GoodsFamilyDao getGoodsFamilyDao() {
		return goodsFamilyDao;
	}

	public void setGoodsFamilyDao(GoodsFamilyDao goodsFamilyDao) {
		this.goodsFamilyDao = goodsFamilyDao;
	}

	public GoodsFamilyAdvertRelationDao getGoRelationDao() {
		return goRelationDao;
	}

	public void setGoRelationDao(GoodsFamilyAdvertRelationDao goRelationDao) {
		this.goRelationDao = goRelationDao;
	}

	public GoodsFamilyAttrRelaDao getGoodsFamilyAttrRelaDao() {
		return goodsFamilyAttrRelaDao;
	}

	public void setGoodsFamilyAttrRelaDao(
			GoodsFamilyAttrRelaDao goodsFamilyAttrRelaDao) {
		this.goodsFamilyAttrRelaDao = goodsFamilyAttrRelaDao;
	}

	/**
	 *@Description:查询商品分类信息
	 *@param:@param page
	 *@param:@param pageNum
	 *@param:@param goodsFamilyDto
	 *@param:@param orderBy
	 *@param:@return
	 *@param:@throws Exception
	 *@author: 张亚运
	 *@throws:
	 */
	public QueryResult<GoodsFamilyDTO> queryAll(int page, int pageNum,
			GoodsFamilyDTO goodsFamilyDto, LinkedHashMap<String, String> orderBy)
			throws Exception {
		return goodsFamilyDao.queryAll(page, pageNum, goodsFamilyDto, orderBy);
	}

	/**
	 *@Description:添加保存商品分类信息
	 *@param:@param goodsFamilyDto
	 *@param:@return
	 *@author: 张亚运
	 * @throws Exception
	 *@throws:
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public GoodsFamily saveGoodsFamily(GoodsFamilyDTO goodsFamilyDto)
			throws Exception {
		// 1.保存分类信息
		GoodsFamily goodsfamily = goodsFamilyDao
				.saveGoodsFamily(goodsFamilyDto);
		if (goodsfamily.getOrderSort() != null) {
			goodsFamilyDao.updateOrderSort(goodsfamily.getFamilyId(),
					goodsfamily.getParentId(), goodsfamily.getOrderSort());
		}
		goodsFamilyDto.setFamilyId(goodsfamily.getFamilyId());

		List<GoodsFamily> gfs = goodsFamilyDao.findByFamilyName(goodsFamilyDto
				.getFamilyName());
		if (gfs.size() > 0) {
			goodsfamily = gfs.get(0);
		}
		if (goodsFamilyDto.getCreateFloorSign() == 1) {
			// 2.保存分类广告关联信息
			GoodsFamilyAdvertRelation g = goRelationDao
					.saveRelation(goodsFamilyDto);
			GoodsFamilyAttrRela rel = new GoodsFamilyAttrRela();
			rel.setFamilyId(goodsfamily.getFamilyId());
			rel.setAttrId(g.getRelId());
			goodsFamilyAttrRelaDao.save(rel);
		}
		/** 如果父级等级为2级保存商品分类 & 手续费率 **/
		if (goodsFamilyDto.getParentLevel() == 2) {
			List<GoodsFamilyRateRel> rates = getRate(goodsfamily.getFamilyId(),
					goodsFamilyDto);
			// 批量保存
			goodsFamilyRateRelDao.saves(rates);
		}

		return goodsfamily;
	}

	/**
	 * 
	 *@Title:getRate
	 *@Description:获取实体集合
	 *@Params:@param familyId
	 *@Params:@param goodsFamilyDto
	 *@Params:@return
	 *@Return:List<GoodsFamilyRateRel>
	 *@author:孟凡岭
	 *@Date:2014-12-10上午09:45:52
	 */
	private List<GoodsFamilyRateRel> getRate(Integer familyId,
			GoodsFamilyDTO goodsFamilyDto) {
		// TODO Auto-generated method stub
		String userName = Utils.getUserSession().getUserName();
		Date updateTime=goodsFamilyRateRelDao.getSysTime();
		List<GoodsFamilyRateRel> list = new ArrayList<GoodsFamilyRateRel>();
		GoodsFamilyRateRel g = new GoodsFamilyRateRel();
		// 融芯宝
		g.setPayMentType(goodsFamilyDto.getRswinPay());
		g.setChargeRate(goodsFamilyDto.getRswinPayValue());
		g.setOperatorId(userName);
		g.setFamilyId(familyId);
		g.setUpdateTime(updateTime);
		list.add(g);
		g = new GoodsFamilyRateRel();
		// 快捷支付
		g.setPayMentType(goodsFamilyDto.getFastPay());
		g.setChargeRate(goodsFamilyDto.getFastPayValue());
		g.setOperatorId(userName);
		g.setFamilyId(familyId);
		g.setUpdateTime(updateTime);
		list.add(g);
		// 网银支付
		g = new GoodsFamilyRateRel();
		g.setPayMentType(goodsFamilyDto.getWyPay());
		g.setChargeRate(goodsFamilyDto.getWyPayValue());
		g.setOperatorId(userName);
		g.setUpdateTime(updateTime);
		g.setFamilyId(familyId);
		list.add(g);
		// 支付宝
		g = new GoodsFamilyRateRel();
		g.setPayMentType(goodsFamilyDto.getAliPay());
		g.setChargeRate(goodsFamilyDto.getAliPayValue());
		g.setOperatorId(userName);
		g.setFamilyId(familyId);
		g.setUpdateTime(updateTime);
		list.add(g);
		// 第三方支付
		g = new GoodsFamilyRateRel();
		g = new GoodsFamilyRateRel();
		g.setPayMentType(goodsFamilyDto.getOtherPay());
		g.setChargeRate(goodsFamilyDto.getOtherPayValue());
		g.setOperatorId(userName);
		g.setFamilyId(familyId);
		g.setUpdateTime(updateTime);
		list.add(g);
		return list;
	}

	/**
	 *@Description:删除商品分类信息（更新状态）
	 *@return:void
	 *@author: 张亚运
	 *@throws:
	 */
	public void deleteGoodsFamily(Integer familyId) {
		GoodsFamily goodsfamily = goodsFamilyDao.find(familyId);
		goodsfamily.setStatus(9);
		goodsFamilyDao.update(goodsfamily);
	}

	/**
	 *@Description:根据编号查询该条记录
	 *@param:@param familyId
	 *@param:@return
	 *@author: 张亚运
	 *@throws:
	 */
	public GoodsFamilyDTO find(Integer familyId) {

		GoodsFamily gf = new GoodsFamily();
		gf = goodsFamilyDao.find(familyId);
		GoodsFamilyDTO dto = getDto(gf);
		if (dto.getNodeLevel() == 3) {
			// 返回map集合，支付类型为key，用于取值
			Map<Integer, GoodsFamilyRateRel> map = goodsFamilyRateRelDao
					.findByFamilyId(dto.getFamilyId());
			if(map.size()>0){
				dto.setRswinPayValue(map.get(dto.getRswinPay()).getChargeRate());
				dto.setWyPayValue(map.get(dto.getWyPay()).getChargeRate());
				dto.setFastPayValue(map.get(dto.getFastPay()).getChargeRate());
				dto.setAliPayValue(map.get(dto.getAliPay()).getChargeRate());
				dto.setOtherPayValue(map.get(dto.getOtherPay()).getChargeRate());
			}
		}
		return dto;
	}

	/**
	 *@Description:将实体转换Dto
	 *@return:GoodsFamilyDTO
	 *@author: 张亚运
	 *@throws:
	 */
	public GoodsFamilyDTO getDto(GoodsFamily goodsFamily) {
		GoodsFamilyDTO dto = new GoodsFamilyDTO();
		dto.setFamilyId(goodsFamily.getFamilyId());
		dto.setFamilyName(goodsFamily.getFamilyName());
		dto.setFamilyWay(goodsFamily.getFamilyWay());
		dto.setCreateTime(goodsFamily.getCreateTime());
		dto.setDefaultShow(goodsFamily.getDefaultShow());
		dto.setPicPath(goodsFamily.getPicPath());
		dto.setKeyWords(goodsFamily.getKeyWords());
		dto.setNodeLevel(goodsFamily.getNodeLevel());
		dto.setOperator(goodsFamily.getOperator());
		dto.setParentId(goodsFamily.getParentId());
		dto.setStatus(goodsFamily.getStatus());
		dto.setPreFlag(goodsFamily.getPreFlag());
		dto.setOrderSort(goodsFamily.getOrderSort());
		dto.setShowAdvertSign(goodsFamily.getShowAdvertSign());
		dto.setCreateFloorSign(goodsFamily.getCreateFloorSign());
		dto.setChargeRate(goodsFamily.getChargeRate());
		dto.setChargeRateType(goodsFamily.getChargeRateType());
		return dto;
	}

	/**
	 *@Description:修改商品分类信息
	 *@param:@param goodsfamilyDto
	 *@param:@return
	 *@author: 张亚运
	 *@throws:
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ReturnDTO updateGoodsFamily(GoodsFamilyDTO goodsfamilyDto)
			throws Exception {
		ReturnDTO returnDto = new ReturnDTO();
		returnDto = goodsFamilyDao.updateGoodsFamily(goodsfamilyDto);
		/** 当生成商城首页数据为否时将相关楼层信息删除 **/
		if (goodsfamilyDto.getCreateFloorSign() == 0
				|| goodsfamilyDto.getShowAdvertSign() == 0) {
			/** 商品分类 & 楼层广告关联 **/
			goRelationDao.deleteByFamilyId(goodsfamilyDto.getFamilyId());
			goodsFamilyAttrRelaDao.deleteByFamilyId(goodsfamilyDto
					.getFamilyId());

		} else {
			GoodsFamilyAdvertRelation g = goRelationDao
					.saveRelation(goodsfamilyDto);
			GoodsFamilyAttrRela rel = new GoodsFamilyAttrRela();
			rel.setFamilyId(goodsfamilyDto.getFamilyId());
			rel.setAttrId(g.getRelId());
			goodsFamilyAttrRelaDao.saveRel(rel);
		}
		//如果父等级不为二，删除关联，否则更新关联
		if(goodsfamilyDto.getParentLevel()!=2){
			goodsFamilyRateRelDao.deleteByFamilyId(goodsfamilyDto.getFamilyId());
		}else{
			Map<Integer, GoodsFamilyRateRel> map = goodsFamilyRateRelDao
			.findByFamilyId(goodsfamilyDto.getFamilyId());
			//如果size大于0表示有数据，进行更新，否则进行保存
			if(map.size()>0){
				goodsFamilyRateRelDao.updateByFamilyId(goodsfamilyDto,map);
				Date updateTime=goodsFamilyRateRelDao.getSysTime();
				String userName=Utils.getUserSession().getUserName();
				//融芯卡
				GoodsFamilyRateRel g=map.get(goodsfamilyDto.getRswinPay());
				g.setChargeRate(goodsfamilyDto.getRswinPayValue());
				g.setOperatorId(userName);
				g.setUpdateTime(updateTime);
				goodsFamilyRateRelDao.update(g);
				//网银支付
				g=map.get(goodsfamilyDto.getWyPay());
				g.setChargeRate(goodsfamilyDto.getWyPayValue());
				g.setOperatorId(userName);
				g.setUpdateTime(updateTime);
				goodsFamilyRateRelDao.update(g);
				//快捷支付
				g=map.get(goodsfamilyDto.getFastPay());
				g.setChargeRate(goodsfamilyDto.getFastPayValue());
				g.setOperatorId(userName);
				g.setUpdateTime(updateTime);
				goodsFamilyRateRelDao.update(g);
				//支付宝
				g=map.get(goodsfamilyDto.getAliPay());
				g.setChargeRate(goodsfamilyDto.getAliPayValue());
				g.setOperatorId(userName);
				g.setUpdateTime(updateTime);
				goodsFamilyRateRelDao.update(g);
				//第三方支付
				g=map.get(goodsfamilyDto.getOtherPay());
				g.setChargeRate(goodsfamilyDto.getOtherPayValue());
				g.setOperatorId(userName);
				g.setUpdateTime(updateTime);
				goodsFamilyRateRelDao.update(g);
			}else{
				List<GoodsFamilyRateRel> list=getRate(goodsfamilyDto.getFamilyId(), goodsfamilyDto);
				goodsFamilyRateRelDao.saves(list);
			}
		}
		return returnDto;
	}
	
	/**
	 *@Description:检验商品分类名称是否存在
	 *@param:@param familyName
	 *@param:@param familyId
	 *@param:@return
	 *@author: 张亚运
	 *@throws:
	 */
	public boolean validateName(String familyName, Integer familyId) {
		return goodsFamilyDao.validateName(familyName, familyId);
	}

	/**
	 *@Description:获取页面上的所属分类
	 *@param:@return
	 *@author: 张亚运
	 *@throws:
	 */
	public List<OptionsInteger> getFamilyOption() {
		List<OptionsInteger> list = goodsFamilyDao.getFamilyOption();
		return list;
	}

	/**
	 *@Description:获取商品分类包含父子关系的列表
	 *@return:List<OptionsString>
	 *@author: Jacky
	 *@throws:
	 */
	public List<GoodsTypeDTO> getFamilyCategoryList() {
		List<GoodsFamily> goodsFamilyList = goodsFamilyDao
				.getPureFamilyOption();
		List<GoodsTypeDTO> familyList = new ArrayList<GoodsTypeDTO>(
				goodsFamilyList.size());
		if (CollectionUtils.isNotEmpty(goodsFamilyList)) {
			Map<Integer, List<GoodsTypeDTO>> childFamilyMap = new HashMap<Integer, List<GoodsTypeDTO>>();
			for (GoodsFamily family : goodsFamilyList) {
				GoodsTypeDTO goodsTypeDTO = new GoodsTypeDTO();
				goodsTypeDTO.setId(family.getFamilyId());
				goodsTypeDTO.setText(family.getFamilyName());
				if (null == family.getDefaultShow()
						|| family.getDefaultShow() == 1) {
					goodsTypeDTO.setState("");
				} else {
					goodsTypeDTO.setState("closed");
				}
				/** 如果是根级分类，先加入list **/
				if (family.getParentId() == 0) {
					familyList.add(goodsTypeDTO);
				} else {
					/** 非根级分类先入map **/
					List<GoodsTypeDTO> goodDTOList = childFamilyMap.get(family
							.getParentId());
					if (goodDTOList == null) {
						goodDTOList = new ArrayList<GoodsTypeDTO>();
						childFamilyMap.put(family.getParentId(), goodDTOList);
					}
					goodDTOList.add(goodsTypeDTO);
				}
			}
			/** 找孩子 **/
			for (GoodsTypeDTO goodsTypeDTO : familyList) {
				findAndSetChild(goodsTypeDTO, childFamilyMap);
			}
		}
		return familyList;
	}

	/**
	 *@Description: 批量获取分类对应的类型名称
	 *@param typeIds
	 *            批量类型id
	 *@return:List<OptionsString>
	 *@author: Jacky
	 *@throws:
	 */
	public Map<String, String> batchQueryFamilyCategoryMap(Set<Integer> typeIds) {
		List<GoodsFamily> familyList = goodsFamilyDao
				.batchQueryGoodsFamily(typeIds);
		Map<String, String> categoryMap = new HashMap<String, String>();
		if (CollectionUtils.isNotEmpty(familyList)) {
			for (GoodsFamily goodsFamily : familyList) {
				categoryMap.put(String.valueOf(goodsFamily.getFamilyId()),
						goodsFamily.getFamilyName());
			}
		}
		return categoryMap;
	}

	/**
	 *@Description:递归方式获取商品分类包含父子关系的列表
	 *@return:List<GoodsTypeDTO>
	 *@author: Jacky
	 *@throws:
	 */
	private List<GoodsTypeDTO> findAndSetChild(GoodsTypeDTO goodsTypeDTO,
			Map<Integer, List<GoodsTypeDTO>> childFamilyMap) {
		List<GoodsTypeDTO> childList = childFamilyMap.get(goodsTypeDTO.getId());
		if (!CollectionUtils.isEmpty(childList)) {
			if (goodsTypeDTO.getChildren() == null) {
				goodsTypeDTO.setChildren(new ArrayList<GoodsTypeDTO>());
			}
			goodsTypeDTO.getChildren().addAll(childList);
			/** 查找孩子 **/
			for (GoodsTypeDTO child : goodsTypeDTO.getChildren()) {
				child.setChildren(findAndSetChild(child, childFamilyMap));
			}
			return goodsTypeDTO.getChildren();
		} else {
			/** 没有孩子的人的状态不设置 **/
			goodsTypeDTO.setState("");
		}
		return new ArrayList<GoodsTypeDTO>();
	}

	public Integer getNodeLevel(Integer parentId) {
		return goodsFamilyDao.getNodeLevel(parentId);
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.service.base.GoodsFamilyService#getFamilyByParent
	 *                        (int)
	 *@MethodName:getFamilyByParent
	 *@Description:根据父id得到下面的子分类
	 *@param parentId
	 *@return
	 *@throws Exception
	 *@Author:毛智东
	 *@Date:2014-11-7上午09:51:14
	 */
	public List<GoodsFamilyDTO> getFamilyByParent(int parentId)
			throws Exception {
		return goodsFamilyDao.getFamilyByParent(parentId);
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.service.base.GoodsFamilyService#saveExGoodsFamily
	 *                        (com.paySystem.ic.web.dto.base.GoodsFamilyDTO)
	 *@MethodName:saveExGoodsFamily
	 *@Description:保存商品分类信息并返回保存后DTO对象
	 *@param goodsFamilyDTO
	 *@return
	 *@throws Exception
	 *@Author:张军磊
	 *@Date:2014-11-20下午05:18:21
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public GoodsFamilyDTO saveExGoodsFamily(GoodsFamilyDTO goodsFamilyDTO)
			throws Exception {
		return goodsFamilyDao.saveExGoodsFamily(goodsFamilyDTO);
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.service.base.GoodsFamilyService#findGoodsFamilyByName
	 *                        (java.lang.String)
	 *@MethodName:findGoodsFamilyByName
	 *@Description:根据分类名称查询商品分类信息
	 *@param familyName
	 *@return
	 *@throws Exception
	 *@Author:张军磊
	 *@Date:2014-11-20下午05:38:33
	 */
	@Override
	public List<GoodsFamilyDTO> findGoodsFamilyByName(String familyName)
			throws Exception {
		return goodsFamilyDao.findGoodsFamilyByName(familyName);
	}

	/**
	 *@MethodName:ajaxObject
	 *@Description:验证广告对象
	 *@param id
	 *@Author:孟凡岭
	 *@Date:2014-12-8上午11:25:29
	 */
	public boolean ajaxObject(String id) {
		return goodsFamilyDao.ajaxObject(id);

	}
}
