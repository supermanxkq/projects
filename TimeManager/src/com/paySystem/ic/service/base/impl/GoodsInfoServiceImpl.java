package com.paySystem.ic.service.base.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsFamilyGroupRela;
import com.paySystem.ic.bean.goods.AttrEntity;
import com.paySystem.ic.bean.goods.VioRegul;
import com.paySystem.ic.dao.base.GoodsFamilyAttrRelaDao;
import com.paySystem.ic.dao.base.GoodsFamilyGroupRelaDao;
import com.paySystem.ic.dao.base.GoodsFormatGroupRelaDao;
import com.paySystem.ic.dao.buss.MerPromotionDao;
import com.paySystem.ic.dao.buss.PromotionDao;
import com.paySystem.ic.dao.goods.FormatInfoDAO;
import com.paySystem.ic.dao.goods.GoodsAttrValueDAO;
import com.paySystem.ic.dao.goods.GoodsDAO;
import com.paySystem.ic.dao.goods.GoodsPhotoDAO;
import com.paySystem.ic.dao.goods.MerUnruleDAO;
import com.paySystem.ic.dao.goods.PlatHandleRecordDAO;
import com.paySystem.ic.dao.goods.StockPriMaDAO;
import com.paySystem.ic.dao.goods.UnruleTypeDao;
import com.paySystem.ic.dao.goods.VioRegulDao;
import com.paySystem.ic.service.base.GoodsInfoService;
import com.paySystem.ic.utils.UploadUtil;
import com.paySystem.ic.web.dto.base.GoodsFormatDTO;
import com.paySystem.ic.web.dto.base.GoodsFormatGroupInfoDTO;
import com.paySystem.ic.web.dto.buss.MerPromotionDTO;
import com.paySystem.ic.web.dto.buss.PromotionDTO;
import com.paySystem.ic.web.dto.goods.DynamicAttrDTO;
import com.paySystem.ic.web.dto.goods.FormatInfoDTO;
import com.paySystem.ic.web.dto.goods.GoodsAttrValueDTO;
import com.paySystem.ic.web.dto.goods.GoodsDTO;
import com.paySystem.ic.web.dto.goods.GoodsFormatNameDTO;
import com.paySystem.ic.web.dto.goods.GoodsPhotoDTO;
import com.paySystem.ic.web.dto.goods.MerUnruleDTO;
import com.paySystem.ic.web.dto.goods.PlatHandleRecordDTO;
import com.paySystem.ic.web.dto.goods.StockPriMaDTO;
import com.paySystem.ic.web.dto.goods.TypeAttrDTO;
import com.paySystem.ic.web.dto.goods.UnruleTypeDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**  
* @Title: GoodsInfoServiceImpl.java
* @Package: com.paySystem.ic.service.base.impl
* @Description: 商品信息服务接口实现类
* @Author: Jacky
* @Date: 2014-08-06
* @Version: V1.0  
*/
@Service(GoodsInfoService.GOODINFOSERVICE)
public class GoodsInfoServiceImpl implements GoodsInfoService {

	@Resource
	private GoodsDAO goodsDao;
	
	@Resource
	private GoodsPhotoDAO goodsPhotoDAO;
	
	@Resource
	private GoodsFamilyAttrRelaDao goodsFamilyAttrRelaDao;
	
	@Resource
	private PlatHandleRecordDAO platHandleRecordDAO;
	
	@Resource
	private GoodsFamilyGroupRelaDao goodsFamilyGroupRelaDao;
	
	@Resource
	private GoodsFormatGroupRelaDao goodsFormatGroupRelaDao;
	
	@Resource
	private MerUnruleDAO merUnruleDAO;
	
	@Resource
	private PromotionDao promotionDao;
	
	@Resource
	private GoodsAttrValueDAO goodsAttrValueDAO;
	
	@Resource
	private StockPriMaDAO stockPriMaDAO;
	
	@Resource
	private MerPromotionDao merPromotionDao;
	
	@Resource
	private FormatInfoDAO formatInfoDAO;
	
	@Resource
	private UnruleTypeDao unruleTypeDao;
	
	@Resource
	private VioRegulDao vioRegulDao;
	
	/**
	 * @Title: queryGoodsByQueryInfo
	 * @Description:根据条件查询商品信息
	 * @param firstPage 开始条数
	 * @param pageNum 每页显示条数
	 * @param goodsDTO GoodsDTO对象
	 * @param orderBy 排序方式
	 * @return List<GoodsDTO> 返回DTO集合
	 * @author:  Jacky
	 * @throws Exception
	 */
	public QueryResult<GoodsDTO> queryGoodsByQueryInfo(int firstPage,
			int pageNum, GoodsDTO goodsDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return goodsDao.findByParam(firstPage, pageNum, goodsDTO, orderBy);
	}

	/**
	 * @Title: findGoodsDTOById
	 * @Description:根据ID查询商品信息
	 * @return GoodsDTO 对象
	 * @author:  Jacky
	 * @throws Exception
	 */
	public GoodsDTO findGoodsDTOById(long goodId) throws Exception {
		if(goodId < 1L) return null;
		return goodsDao.queryGoodsDTOById(goodId);
	}
	
	/**
	 * @Title: findGoodsDetail
	 * @Description:根据ID查询商品最详细的信息
	 * @return GoodsDTO 对象
	 * @param goodsId 商品id
	 * @author:  Jacky
	 * @throws Exception
	 */
	public GoodsDTO findGoodsDetail(Long goodsId) throws Exception {
		if(goodsId < 1L) return null;
		GoodsDTO goodsDTO = goodsDao.queryGoodsDTOById(goodsId);
		if(null != goodsDTO) {
			/** 商品图片查询**/
			List<GoodsPhotoDTO> photoList = goodsPhotoDAO.findGoodsPhotoByGoodId(goodsId);
			/** 获取主图、商品图、描述图片 **/
			if(CollectionUtils.isNotEmpty(photoList)) {
				List<String> mainPicList = new ArrayList<String>();
				List<String> itemPicList = new ArrayList<String>();
				List<String> descPicList = new ArrayList<String>();
				for(GoodsPhotoDTO goodsPhoto : photoList) {
					switch(goodsPhoto.getGoodsStyle()) {
						case 1 : mainPicList.add(goodsPhoto.getGoodsLittPho()) ;break;
						case 2 : itemPicList.add(goodsPhoto.getGoodsLittPho());break;
						case 3 : descPicList.add(goodsPhoto.getGoodsLittPho());break;
						default : break;
					}
				}
				String [] mainV = new String [mainPicList.size()];
				mainPicList.toArray(mainV);
				goodsDTO.setMainResultHidden(mainV);
				String [] itemV = new String [itemPicList.size()];
				itemPicList.toArray(itemV);
				goodsDTO.setMainResultHidden(itemV);
				String [] descV = new String [descPicList.size()];
				descPicList.toArray(descV);
				goodsDTO.setMainResultHidden(mainV);
				goodsDTO.setItemResultHidden(itemV);
				goodsDTO.setDescResultHidden(descV);
			}
			
			/** 动态属性信息查询 **/
			List<GoodsAttrValueDTO> goodsAttrValueList = goodsAttrValueDAO.findAttrValueByGoodsId(goodsId);
			Map<Long,GoodsAttrValueDTO> mapGroup = new HashMap<Long,GoodsAttrValueDTO>();
			/** 最多增加属性行数**/
			int maxRow = 1;
			for(GoodsAttrValueDTO goodsAttrValue : goodsAttrValueList) {
				mapGroup.put(goodsAttrValue.getAttrvalId(), goodsAttrValue);
			}
			
			if(CollectionUtils.isNotEmpty(goodsAttrValueList)) {
				
				/** 动态属性的列 **/
				int counter = 0;
				/** 下面一段代码是在没办法，因为涉及的时候既有五个属性 
				 *  如果属性是下拉条，那么提交时候前端校验时就得进行笛卡尔积校验
				 *  比如有2个下条，每个有三个选项，那么属性哪一块最左增加8行+自己本身1行就是3x3=9
				 *  但是如果是editable的下拉条，那么就是可以增加无限行**/
				GoodsAttrValueDTO goodsAttrValue = goodsAttrValueList.get(0);
				if(null != goodsAttrValue.getIsEn1() && null != goodsAttrValue.getAttrEnList1() && goodsAttrValue.getAttrEnList1().size() > 0) {
					if(goodsAttrValue.getIsEn1()!= 2) {
						maxRow *= goodsAttrValue.getAttrEnList1().size();
					} else {
						maxRow = Integer.MAX_VALUE;
					}
				}
				if(null != goodsAttrValue.getAttrId1()) {
					counter ++;
				}
				if(null != goodsAttrValue.getIsEn2() &&   null != goodsAttrValue.getAttrEnList2() && goodsAttrValue.getAttrEnList2().size() > 0) {
					if(maxRow < Integer.MAX_VALUE && goodsAttrValue.getIsEn2() != 2) {
						maxRow *= goodsAttrValue.getAttrEnList2().size();
					}else {
						maxRow = Integer.MAX_VALUE;
					}
				}
				if(null != goodsAttrValue.getAttrId2()) {
					counter ++;
				}
				if(null != goodsAttrValue.getIsEn3() && null != goodsAttrValue.getAttrEnList3() && goodsAttrValue.getAttrEnList3().size() > 0) {
					if(maxRow < Integer.MAX_VALUE && goodsAttrValue.getIsEn3() != 2) {
						maxRow *= goodsAttrValue.getAttrEnList3().size();
					}else {
						maxRow = Integer.MAX_VALUE;
					}
				}
				if(null != goodsAttrValue.getAttrId3()) {
					counter ++;
				}
				if(null != goodsAttrValue.getIsEn4() &&  null != goodsAttrValue.getAttrEnList4() && goodsAttrValue.getAttrEnList4().size() > 0) {
					if(maxRow < Integer.MAX_VALUE && goodsAttrValue.getIsEn4() != 2) {
						maxRow *= goodsAttrValue.getAttrEnList4().size();
					}else {
						maxRow = Integer.MAX_VALUE;
					}
				}
				if(null != goodsAttrValue.getAttrId4()) {
					counter ++;
				}
				if(null != goodsAttrValue.getIsEn5() &&  null != goodsAttrValue.getAttrEnList5() && goodsAttrValue.getAttrEnList5().size() > 0) {
					if(maxRow < Integer.MAX_VALUE && goodsAttrValue.getIsEn5() != 2) {
						maxRow *= goodsAttrValue.getAttrEnList5().size();
					}else {
						maxRow = Integer.MAX_VALUE;
					}
				}
				if(null != goodsAttrValue.getAttrId5()) {
					counter ++;
				}
				goodsDTO.setAttCount(counter);
				goodsDTO.setMaxRow(maxRow);
			}
			/** 商品库存等信息查询 **/
			List<StockPriMaDTO> stockPriList = stockPriMaDAO.findStockPriMaByGoodsId(goodsId);
			Long totalInventory = 0L;
			for(StockPriMaDTO stockPri : stockPriList) {
				stockPri.setGoodsAttrValueDTO(mapGroup.get(stockPri.getAttrvalId()));
				totalInventory += Long.valueOf(stockPri.getStockNo());
			}
			goodsDTO.setTotalInventory(totalInventory);
			goodsDTO.setStockPriList(stockPriList);
			/** 商品规格信息查询**/
			List<GoodsFormatNameDTO> goodsGroupFormatList = new ArrayList<GoodsFormatNameDTO>();
			List<FormatInfoDTO> formatInfoList = formatInfoDAO.findFormatInfoDTOByGoodsId(goodsId);
			if(CollectionUtils.isNotEmpty(formatInfoList)) {
				Map<String ,List<FormatInfoDTO>> groupMap = new HashMap<String ,List<FormatInfoDTO>>();
				for(FormatInfoDTO formatInfo : formatInfoList) {
					if(groupMap.get(formatInfo.getGroupName()) == null) {
						groupMap.put(formatInfo.getGroupName(), new ArrayList<FormatInfoDTO>());
					}
					groupMap.get(formatInfo.getGroupName()).add(formatInfo);
				}
				for(Entry<String, List<FormatInfoDTO>> entry : groupMap.entrySet()) {
					goodsGroupFormatList.add(new GoodsFormatNameDTO(entry.getKey(),null,entry.getValue()));
				}
				goodsDTO.setFormatGroupList(goodsGroupFormatList);
			}
			
			/** 查询活动 **/
			if(goodsDTO.getProId() > 0) {
				/** 正常逻辑list.size == 1 **/
				List<MerPromotionDTO> proList = merPromotionDao.findOnlinePromotionPlatformById(goodsDTO.getProId());
				if(CollectionUtils.isNotEmpty(proList)) {
					goodsDTO.setPromotion(proList.get(0));
				}
			}
			
		}
		return goodsDTO;
	}
	
	/**
	 * @Title: dealGoodUnRule
	 * @Description: 处理违规商品
	 * @param typeId == familyId
	 * @return String 错误消息
	 * @author:  Jacky
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String dealGoodUnRule(PlatHandleRecordDTO platHandleRecord)
			throws Exception {
		
		MerUnruleDTO mu = new MerUnruleDTO();
		mu.setDeductScore(platHandleRecord.getDeductScore());
		mu.setGoodsId(platHandleRecord.getGoodsId());
		mu.setMerId(platHandleRecord.getOperatorId());
		mu.setPhType(platHandleRecord.getPhType());
		mu.setPunishTime(new Date());
		mu.setUnRuleBehald(platHandleRecord.getGoodPunishType());
		mu.setUnruleWay(Integer.valueOf(platHandleRecord.getVioRugleId()));
		merUnruleDAO.save(mu);
		
		/** 这里需要对商品进行处理 **/
		platHandleRecordDAO.save(platHandleRecord);
		/** 删除商品 **/ /** 暂时只处理被删除的商品，其他操作还不知道要干嘛**/
		if(null != platHandleRecord.getGoodPunishType() && platHandleRecord.getPhType() == 1) {
			goodsDao.dealGoodsInfo(platHandleRecord.getGoodsId());
		}
		return null;
	}
	
	/**
	 * @Title: queryLastedRecord
	 * @Description: 查询最近一条违规处理记录
	 * @param goodsId 商品id
	 * @return PlatHandleRecordDTO 处理记录
	 * @author:  Jacky
	 * @throws Exception
	 */
	public PlatHandleRecordDTO queryLastedRecord(Long goodsId) throws Exception {
		return platHandleRecordDAO.findPlatHandleRecordDTO(goodsId);
	}

	/**
	 * @Title: findLatestUnRule
	 * @Description: 查找该商品最近处理记录
	 * @param goodsId 商品信息id列表
	 * @return MerUnruleDTO 违规处理记录
	 * @author:  Jacky
	 * @throws Exception
	 */
	public MerUnruleDTO findLatestUnRule(Long goodsId) throws Exception {
		List<MerUnruleDTO> merUnRuleList = merUnruleDAO.findMerUnruleDTOListByGoodsId(goodsId);
		if(CollectionUtils.isNotEmpty(merUnRuleList)) {
			return merUnRuleList.get(0);
		}
		return null;
	}

	/**
	 * @Title: uploadPic
	 * @Description: 上传商品信息图片
	 * @return String 图片url
	 * @author:  Jacky
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String uploadPic(GoodsDTO goodsDTO) throws Exception {
		if(null != goodsDTO.getUploadFile() && StringUtils.isNotBlank(goodsDTO.getUploadFileFileName())) {
			String fileName = UploadUtil.uploadImg(goodsDTO.getUploadFile(), goodsDTO.getUploadFileFileName());
			return fileName;
		}
		return null;
	}

	/**
	 * @Title: findMerPromotionDTOByProId
	 * @Description: 根据活动id来查询活动信息
	 * @param proId 活动id
	 * @return MerPromotionDTO 活动dto
	 * @author:  Jacky
	 * @throws Exception
	 */
	public MerPromotionDTO findMerPromotionDTOByProId(Integer proId)
			throws Exception {
		List<MerPromotionDTO>  list = merPromotionDao.findOnlinePromotionPlatformById(proId);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * @Title: batchOfflineGoods
	 * @Description: 批量下架商品
	 * @param goodsId 商品信息id
	 * @return List<Long> 下架失败的商品
	 * @author:  Jacky
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Long> batchOfflineGoods(List<String> goodsId) throws Exception {
		if(!CollectionUtils.isEmpty(goodsId)) {
			List<Long> ids = new ArrayList<Long>();
			for(String gId : goodsId) {
				ids.add(Long.valueOf(gId));
			}
			goodsDao.updateGoodsInfo(ids,1);
		}
		return null;
	}

	/**
	 * @Title: batchOnlieGoods
	 * @Description: 批量上架商品
	 * @param goodsId 商品信息id列表
	 * @return List<Long> 下架失败的商品
	 * @author:  Jacky
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Long> batchOnlieGoods(List<String> goodsId) throws Exception {
		if(!CollectionUtils.isEmpty(goodsId)) {
			List<Long> ids = new ArrayList<Long>();
			for(String gId : goodsId) {
				ids.add(Long.valueOf(gId));
			}
			goodsDao.updateGoodsInfo(ids,0);
		}
		return null;
	}

	/**
	 * @Title: saveGoodInfo
	 * @Description: 保存商品信息
	 * @param goodInfo 商品信息
	 * @return String 错误信息
	 * @author:  Jacky
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveGoodInfo(GoodsDTO goodDto) throws Exception {
		MerPromotionDTO promotion = null;
		if(goodDto.getProId() > 0) {
			List<MerPromotionDTO> proList = merPromotionDao.findOnlinePromotionPlatformById(goodDto.getProId());
			if(null != proList && proList.size()> 0) {
				promotion = proList.get(0);
			} else {
				return "活动不存在!";
			}
			if(promotion.getRateSign().equals("0")) {
				goodDto.setRate(null);
			}
		}
		goodsDao.saveGoodsInfo(goodDto);
		if(goodDto.getGoodsId() < 1L) return "保存商品信息失败!";
		List<DynamicAttrDTO> dynamicAttrList = goodDto.getDynamicAttr();
		if(CollectionUtils.isNotEmpty(dynamicAttrList)) {
			List<StockPriMaDTO> spdtoList = new ArrayList<StockPriMaDTO>();
			for(DynamicAttrDTO dynamic : dynamicAttrList) {
				GoodsAttrValueDTO dav = new GoodsAttrValueDTO();
				if(dynamic.getAttrNameValueList().size() > 0) {
					dav.setAttrId1(dynamic.getAttrNameValueList().get(0).getKey());
					dav.setAttrValue1(dynamic.getAttrNameValueList().get(0).getValue());
				}
				if(dynamic.getAttrNameValueList().size() > 1) {
					dav.setAttrId2(dynamic.getAttrNameValueList().get(1).getKey());
					dav.setAttrValue2(dynamic.getAttrNameValueList().get(1).getValue());
				}
				if(dynamic.getAttrNameValueList().size() > 2) {
					dav.setAttrId3(dynamic.getAttrNameValueList().get(2).getKey());
					dav.setAttrValue3(dynamic.getAttrNameValueList().get(2).getValue());
				}
				if(dynamic.getAttrNameValueList().size() > 3) {
					dav.setAttrId4(dynamic.getAttrNameValueList().get(3).getKey());
					dav.setAttrValue4(dynamic.getAttrNameValueList().get(3).getValue());
				}
				if(dynamic.getAttrNameValueList().size() > 4) {
					dav.setAttrId5(dynamic.getAttrNameValueList().get(4).getKey());
					dav.setAttrValue5(dynamic.getAttrNameValueList().get(4).getValue());
				}
				dav.setGoodsId(goodDto.getGoodsId());
				dav.setPicture(dynamic.getImgUrl());
				goodsAttrValueDAO.saveBean(dav);
				StockPriMaDTO sp = new StockPriMaDTO();
				sp.setAttrvalId(dav.getAttrvalId());
				sp.setGoodsId(String.valueOf(goodDto.getGoodsId()));
				sp.setGoodsPrice(dynamic.getGoodsPrice());
				sp.setMarketGoodsPrice(dynamic.getMarketGoodsPrice());
				Double rate = 0.0d;
				/** 统一折扣 **/
				if(null != promotion && promotion.getRateSign().equals("0")) {
					rate = promotion.getSuggRate();
					goodDto.setRate(null);
				} else {
					rate = goodDto.getRate();
				}
				
				/** 折扣设置有问题的话就当做不打折 **/
				if(null != rate && rate < 1.0 && rate > 0.0) {
					sp.setProGoodsPrice(sp.getGoodsPrice().multiply(new BigDecimal(rate)));
				} else {
					sp.setProGoodsPrice(sp.getGoodsPrice());
				}
				sp.setStockNo(dynamic.getStockNo());
				spdtoList.add(sp);
			}
			stockPriMaDAO.saveBeans(spdtoList);
		}
		if(CollectionUtils.isNotEmpty(goodDto.getFormatDynamic())) {
			for(FormatInfoDTO format : goodDto.getFormatDynamic()) {
				format.setGoodsId(goodDto.getGoodsId());
			}
			formatInfoDAO.saveBeans(goodDto.getFormatDynamic());
		}
		List<GoodsPhotoDTO> photoList = getJoinPic(goodDto);
		if(CollectionUtils.isNotEmpty(photoList)) {
			goodsPhotoDAO.saveGoodsPhotos(photoList);
		}
		return null;
	}

	/**
	 * @Title: updateGoodInfo
	 * @Description: 更新商品信息
	 * @param goodsDto 商品信息
	 * @return String 错误信息
	 * @author:  Jacky
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String updateGoodInfo(GoodsDTO goodDto) throws Exception {
		MerPromotionDTO promotion = null;
		/** 查询该活动，用于后面计算折扣 **/
		if(goodDto.getProId() > 0) {
			List<MerPromotionDTO> proList = merPromotionDao.findOnlinePromotionPlatformById(goodDto.getProId());
			if(null != proList && proList.size()> 0) {
				promotion = proList.get(0);
			} else {
				return "活动不存在!";
			}
			/** 如果是统一折扣，那么商品就不保存折扣率 **/
			if(promotion.getRateSign().equals("0")) {
				goodDto.setRate(null);
			}
		}
		
		GoodsDTO goods = goodsDao.queryGoodsDTOById(goodDto.getGoodsId());
		if(null == goods) return "商品不存在，无法更新";
		/** 如果商品是未处理状态，并且用户改了状态，就证明商品已经被处理 **/
		if(goods.getUnRuleMaSta()!=null && goods.getUnRuleMaSta()==2 && goodDto.getGoodsSta() != null && (goodDto.getGoodsSta() == 0 || goodDto.getGoodsSta() == 1 || goodDto.getGoodsSta() == 9)) {
			goodDto.setUnRuleMaSta(1);
		}
		/** 编辑商品就把商品状态改成正常 **/
		goodDto.setGoodsSta(0);
		goodsDao.updateGoodsInfo(goodDto);
		
		List<DynamicAttrDTO> dynamicAttrList = goodDto.getDynamicAttr();
		if(CollectionUtils.isNotEmpty(dynamicAttrList)) {
			List<StockPriMaDTO> spdtoList = new ArrayList<StockPriMaDTO>();
			/** 删除掉之前的属性值 **/
			goodsAttrValueDAO.deleteBean(goodDto.getGoodsId());
			/** 删除之前的库存信息 **/
			stockPriMaDAO.deleteBeans(goodDto.getGoodsId());
			
			/** 不得不说这设计师是多么的2，一张表五个属性值一一赋值，顺序保存 **/
			for(DynamicAttrDTO dynamic : dynamicAttrList) {
				GoodsAttrValueDTO dav = new GoodsAttrValueDTO();
				if(dynamic.getAttrNameValueList().size() > 0) {
					dav.setAttrId1(dynamic.getAttrNameValueList().get(0).getKey());
					dav.setAttrValue1(dynamic.getAttrNameValueList().get(0).getValue());
				}
				if(dynamic.getAttrNameValueList().size() > 1) {
					dav.setAttrId2(dynamic.getAttrNameValueList().get(1).getKey());
					dav.setAttrValue2(dynamic.getAttrNameValueList().get(1).getValue());
				}
				if(dynamic.getAttrNameValueList().size() > 2) {
					dav.setAttrId3(dynamic.getAttrNameValueList().get(2).getKey());
					dav.setAttrValue3(dynamic.getAttrNameValueList().get(2).getValue());
				}
				if(dynamic.getAttrNameValueList().size() > 3) {
					dav.setAttrId4(dynamic.getAttrNameValueList().get(3).getKey());
					dav.setAttrValue4(dynamic.getAttrNameValueList().get(3).getValue());
				}
				if(dynamic.getAttrNameValueList().size() > 4) {
					dav.setAttrId5(dynamic.getAttrNameValueList().get(4).getKey());
					dav.setAttrValue5(dynamic.getAttrNameValueList().get(4).getValue());
				}
				dav.setGoodsId(goodDto.getGoodsId());
				dav.setPicture(dynamic.getImgUrl());
				/** 保存动态属性 **/
				goodsAttrValueDAO.saveBean(dav);
				
				StockPriMaDTO sp = new StockPriMaDTO();
				sp.setAttrvalId(dav.getAttrvalId());
				sp.setGoodsId(String.valueOf(goodDto.getGoodsId()));
				sp.setGoodsPrice(dynamic.getGoodsPrice());
				sp.setMarketGoodsPrice(dynamic.getMarketGoodsPrice());
				Double rate = 0.0d;
				/** 统一折扣 **/
				if(null != promotion && promotion.getRateSign().equals("0")) {
					rate = promotion.getSuggRate();
					goodDto.setRate(null);
				} else {
					rate = goodDto.getRate();
				}
				
				/** 折扣设置有问题的话就当做不打折 **/
				if(null != rate && rate < 1.0 && rate > 0.0) {
					sp.setProGoodsPrice(sp.getGoodsPrice().multiply(new BigDecimal(rate)));
				} else {
					sp.setProGoodsPrice(sp.getGoodsPrice());
				}
				sp.setStockNo(dynamic.getStockNo());
				spdtoList.add(sp);
			}
			stockPriMaDAO.saveBeans(spdtoList);
		}
		
		/** 清理掉规格关联表 **/
		formatInfoDAO.deleteBeans(goodDto.getGoodsId());
		
		if(CollectionUtils.isNotEmpty(goodDto.getFormatDynamic())) {
			for(FormatInfoDTO format : goodDto.getFormatDynamic()) {
				format.setGoodsId(goodDto.getGoodsId());
			}
			/** 保存新的规格 **/
			formatInfoDAO.saveBeans(goodDto.getFormatDynamic());
		}
		
		/** 删除商品图片**/
		goodsPhotoDAO.deleteGoodsPhtos(goodDto.getGoodsId());
		List<GoodsPhotoDTO> photoList = getJoinPic(goodDto);
		if(CollectionUtils.isNotEmpty(photoList)) {
			goodsPhotoDAO.saveGoodsPhotos(photoList);
		}
		
		return null;
	}

	/**
	 * @Title: savenJoinPic
	 * @Description: 获得商品图片
	 * @param goodsDTO dto
	 * @return 
	 */
	private List<GoodsPhotoDTO> getJoinPic(GoodsDTO goodsDTO) {
		if(goodsDTO.getGoodsId() < 1L) return null;
		/** 1主图片 2商品图片 3描述图片**/
		String[] itemPic = goodsDTO.getItemResultHidden();
		String[] mainPic = goodsDTO.getMainResultHidden();
		String[] descPic = goodsDTO.getDescResultHidden();
		List<GoodsPhotoDTO> photos = new ArrayList<GoodsPhotoDTO>();
		if(itemPic != null && itemPic.length > 0) {
			for(String photo : itemPic) {
				if(StringUtils.isNotBlank(photo)) {
					GoodsPhotoDTO gp = new GoodsPhotoDTO();
					gp.setGoodsId(goodsDTO.getGoodsId());
					gp.setGoodsStyle(2);
					gp.setGoodsLittPho(photo);
					photos.add(gp);
				}
			}
		}
		if(mainPic != null && mainPic.length > 0) {
			for(String photo : mainPic) {
				if(StringUtils.isNotBlank(photo)) {
					GoodsPhotoDTO gp = new GoodsPhotoDTO();
					gp.setGoodsId(goodsDTO.getGoodsId());
					gp.setGoodsStyle(1);
					gp.setGoodsLittPho(photo);
					photos.add(gp);
				}
			}
		}
		if(descPic != null && descPic.length > 0) {
			for(String photo : descPic) {
				if(StringUtils.isNotBlank(photo)) {
					GoodsPhotoDTO gp = new GoodsPhotoDTO();
					gp.setGoodsId(goodsDTO.getGoodsId());
					gp.setGoodsStyle(3);
					gp.setGoodsLittPho(photo);
					photos.add(gp);
				}
			}
		}
		return photos;
	}

	/**
	 * @Title: findAttrByTypeId
	 * @Description: 根据分类查询动态属性list
	 * @param typeId == familyId
	 * @return List<TypeAttrDTO> 属性列表
	 * @author:  Jacky
	 * @throws Exception
	 */
	public List<TypeAttrDTO> findAttrByTypeId(int typeId) throws Exception {
		List<TypeAttrDTO> typeAttList =  goodsFamilyAttrRelaDao.findTypeAttrRelaList(typeId);
		if(CollectionUtils.isNotEmpty(typeAttList)) {
			for(TypeAttrDTO typeAttr : typeAttList) {
				if(null != typeAttr.getIsEn() && (typeAttr.getIsEn() == 0 || typeAttr.getIsEn() == 2)) {
					List<AttrEntity> entityList = goodsFamilyAttrRelaDao.queryAttrEntityList(typeAttr.getAttrId());
					typeAttr.setAttValueList(entityList);
				}
			}
		}
		return typeAttList;
	}

	/**
	 * @Title: findGoodsFormatGroup
	 * @Description: 根据分类查询动态的规格列表
	 * @param typeId == familyId
	 * @return List<GoodsFormatNameDTO> 商品规格列表
	 * @author:  Jacky
	 * @throws Exception
	 */
	public List<GoodsFormatNameDTO> findGoodsFormatGroup(int typeId)
			throws Exception {
		List<GoodsFamilyGroupRela> familyFormatList = goodsFamilyGroupRelaDao.findGoodsFormatListByFamilyId(typeId);
		Set<Integer> formatSet = getFormatIdSet(familyFormatList);
		List<GoodsFormatGroupInfoDTO> resultFormatInfo = goodsFormatGroupRelaDao.findGoodsFormatGroupRelaByFormatId(formatSet);
		List<GoodsFormatNameDTO> formatList = new ArrayList<GoodsFormatNameDTO>();
		if(CollectionUtils.isNotEmpty(resultFormatInfo)) {
			Map<String,List<GoodsFormatDTO>> groupMap = new HashMap<String,List<GoodsFormatDTO>>();
			for(GoodsFormatGroupInfoDTO goodsFormatGroupInfo : resultFormatInfo) {
				/** 先归组 **/
				if(groupMap.get(goodsFormatGroupInfo.getfGroupName()) == null) {
					groupMap.put(goodsFormatGroupInfo.getfGroupName(), new ArrayList<GoodsFormatDTO>());
				}
				GoodsFormatDTO goodFormat = new GoodsFormatDTO();
				goodFormat.setFormatId(goodsFormatGroupInfo.getFormatId());
				goodFormat.setFormatName(goodsFormatGroupInfo.getFormatName());
				groupMap.get(goodsFormatGroupInfo.getfGroupName()).add(goodFormat);
			}
			
			/** 归组完毕之后，转list**/
			for(Entry<String, List<GoodsFormatDTO>>  entry : groupMap.entrySet()) {
				formatList.add(new GoodsFormatNameDTO(entry.getKey(),entry.getValue(),null));
			}
		}
		return formatList;
	}
	
	/**
	 * @Title: findPromotionList
	 * @Description: 查询促销信息
	 * @param merId 会员userName,因为需要带出平台上线活动和自己申请通过的活动
	 * @return List<PromotionDTO> 活动列表
	 * @author:  Jacky
	 * @throws Exception
	 */
	public List<PromotionDTO> findPromotionList(UserSession us) throws Exception {
		return promotionDao.findOnlinePromotionPlatform(us.getMerId(),us.getUserName());
	}
	
	/**
	 * @Title: deleteGoodInfoById
	 * @Description: 删除商品信息
	 * @param merId 会员userName
	 * @param goodsId 商品id
	 * @return String 错误消息
	 * @author:  Jacky
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String deleteGoodInfoById(String merId, Long goodsId)
			throws Exception {
		goodsDao.deleteGoodsInfo(goodsId, merId);
		return null;
	}

	/**
	 * @Title: getFormatIdSet
	 * @Description: 获取属性id的hashset，为了性能，你懂得
	 * @param familyFormatList 分类列表
	 * @return Set<Integer> formatId set
	 * @author:  Jacky
	 * @throws Exception
	 */
	private Set<Integer> getFormatIdSet(List<GoodsFamilyGroupRela> familyFormatList) {
		Set<Integer> formatSet = new HashSet<Integer>();
		if(CollectionUtils.isNotEmpty(familyFormatList)) {
			for(GoodsFamilyGroupRela goodsFamilyGroupRela : familyFormatList) {
				formatSet.add(goodsFamilyGroupRela.getFormatId());
			}
		}
		return formatSet;
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.service.base.GoodsInfoService#getAllUnruleTypes()
	 *@MethodName:getAllUnruleTypes
	 *@Description:获取所有的违规类型
	 *@return
	 *@throws Exception
	 *@Author:yanwuyang
	 *@Date:2014-8-27下午5:12:09
	 */
	public QueryResult<UnruleTypeDTO> getAllUnruleTypes() throws Exception {
		return unruleTypeDao.getAllUnruleTypes();
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.service.base.GoodsInfoService#getVioRegulByTypeId(java.lang.Integer)
	 *@MethodName:getVioRegulByTypeId
	 *@Description:根据违规类型Id获取违规案例
	 *@param typeId
	 *@return
	 *@Author:yanwuyang
	 *@Date:2014-8-27下午5:15:17
	 */
	public List<VioRegul> getVioRegulByTypeId(Integer typeId) {
		return vioRegulDao.getVioRegulByTypeId(typeId);
	}
	
	
}
