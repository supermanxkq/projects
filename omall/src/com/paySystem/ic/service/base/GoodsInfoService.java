package com.paySystem.ic.service.base;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.goods.VioRegul;
import com.paySystem.ic.web.dto.buss.MerPromotionDTO;
import com.paySystem.ic.web.dto.buss.PromotionDTO;
import com.paySystem.ic.web.dto.goods.GoodsDTO;
import com.paySystem.ic.web.dto.goods.GoodsFormatNameDTO;
import com.paySystem.ic.web.dto.goods.MerUnruleDTO;
import com.paySystem.ic.web.dto.goods.PlatHandleRecordDTO;
import com.paySystem.ic.web.dto.goods.TypeAttrDTO;
import com.paySystem.ic.web.dto.goods.UnruleTypeDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**  
* @Title: GoodsInfoService.java
* @Package: com.paySystem.ic.service.base
* @Description: 商品信息服务接口
* @Author: Jacky
* @Date: 2014-08-06
* @Version: V1.0  
*/
public interface GoodsInfoService {
	
public static final String GOODINFOSERVICE = "goodsInfoService";
	
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
	QueryResult<GoodsDTO> queryGoodsByQueryInfo(int firstPage,int pageNum,
			GoodsDTO goodsDTO,LinkedHashMap<String,String> orderBy) throws Exception;
	
	/**
	 * @Title: findGoodsDTOById
	 * @Description:根据ID查询商品信息
	 * @return GoodsDTO 对象
	 * @author:  Jacky
	 * @throws Exception
	 */
	GoodsDTO findGoodsDTOById(long goodId) throws Exception ;
	
	/**
	 * @Title: uploadPic
	 * @Description:上传商品图片
	 * @param goodsDTO 商品信息DTO对象
	 * @return String 上传图片url
	 * @author:  Jacky
	 * @throws Exception
	 */
	String uploadPic(GoodsDTO goodsDTO) throws Exception;
	 
	/**
	 * @Title: batchOfflineGoods
	 * @Description: 批量下架商品
	 * @param goodsId 商品信息id
	 * @return List<Long> 下架失败的商品
	 * @author:  Jacky
	 * @throws Exception
	 */
	List<Long> batchOfflineGoods(List<String> goodsId) throws Exception;
	
	/**
	 * @Title: batchOnlieGoods
	 * @Description: 批量上架商品
	 * @param goodsId 商品信息id列表
	 * @return List<Long> 下架失败的商品
	 * @author:  Jacky
	 * @throws Exception
	 */
	List<Long> batchOnlieGoods(List<String> goodsId) throws Exception;
	
	/**
	 * @Title: findLatestUnRule
	 * @Description: 查找该商品最近处理记录
	 * @param goodsId 商品信息id列表
	 * @return MerUnruleDTO 违规处理记录
	 * @author:  Jacky
	 * @throws Exception
	 */
	MerUnruleDTO findLatestUnRule(Long goodsId) throws Exception;
	
	/**
	 * @Title: saveGoodInfo
	 * @Description: 保存商品信息
	 * @param goodInfo 商品信息
	 * @return String 错误信息
	 * @author:  Jacky
	 * @throws Exception
	 */
	String saveGoodInfo(GoodsDTO goodsDto) throws Exception;
	
	/**
	 * @Title: updateGoodInfo
	 * @Description: 更新商品信息
	 * @param goodsDto 商品信息
	 * @return String 错误信息
	 * @author:  Jacky
	 * @throws Exception
	 */
	String updateGoodInfo(GoodsDTO goodsDto) throws Exception;
	
	/**
	 * @Title: findAttrByTypeId
	 * @Description: 根据分类查询动态属性list
	 * @param typeId == familyId
	 * @return List<TypeAttrDTO> 属性列表
	 * @author:  Jacky
	 * @throws Exception
	 */
	List<TypeAttrDTO> findAttrByTypeId(int typeId) throws Exception;
	
	/**
	 * @Title: findGoodsFormatGroup
	 * @Description: 根据分类查询动态的规格列表
	 * @param typeId == familyId
	 * @return List<GoodsFormatNameDTO> 商品规格列表
	 * @author:  Jacky
	 * @throws Exception
	 */
	List<GoodsFormatNameDTO> findGoodsFormatGroup(int typeId) throws Exception;
	
	/**
	 * @Title: dealGoodUnRule
	 * @Description: 处理违规商品
	 * @param typeId == familyId
	 * @return String 错误消息
	 * @author:  Jacky
	 * @throws Exception
	 */
	String dealGoodUnRule(PlatHandleRecordDTO platHandleRecord) throws Exception;
	
	/**
	 * @Title: queryLastedRecord
	 * @Description: 查询最近一条违规处理记录
	 * @param goodsId 商品id
	 * @return PlatHandleRecordDTO 处理记录
	 * @author:  Jacky
	 * @throws Exception
	 */
	PlatHandleRecordDTO queryLastedRecord(Long goodsId) throws Exception;
	
	/**
	 * @Title: findPromotionList
	 * @Description: 查询促销信息
	 * @param us 会员
	 * @return List<PromotionDTO> 活动列表
	 * @author:  Jacky
	 * @throws Exception
	 */
	List<PromotionDTO> findPromotionList(UserSession us) throws Exception;
	
	/**
	 * @Title: deleteGoodInfoById
	 * @Description: 删除商品信息
	 * @param merId 会员userName
	 * @param goodsId 商品id
	 * @return String 错误消息
	 * @author:  Jacky
	 * @throws Exception
	 */
	String deleteGoodInfoById(String merId,Long goodsId) throws Exception;
	
	/**
	 * @Title: findMerPromotionDTOByProId
	 * @Description: 根据活动id来查询活动信息
	 * @param proId 活动id
	 * @return MerPromotionDTO 活动dto
	 * @author:  Jacky
	 * @throws Exception
	 */
	MerPromotionDTO findMerPromotionDTOByProId(Integer proId) throws Exception;
	
	/**
	 * @Title: findGoodsDetail
	 * @Description: 查询商品详细信息
	 * @param goodsId 商品id
	 * @return GoodsDTO 商品dto
	 * @author:  Jacky
	 * @throws Exception
	 */
	GoodsDTO findGoodsDetail(Long goodsId) throws Exception;
	
	/**
	 * 
	 *@Title:getAllUnruleTypes
	 *@Description:获取所有的违规类型
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:QueryResult<UnruleTypeDTO>
	 *@author:yanwuyang
	 *@Date:2014-8-27下午4:52:50
	 */
	public QueryResult<UnruleTypeDTO> getAllUnruleTypes() throws Exception;
	
	/**
	 * 
	 *@Title:getVioRegulByTypeId
	 *@Description:根据违规类型获取违规案例
	 *@Params:@param typeId
	 *@Params:@return
	 *@Return:List<VioRegul>
	 *@author:yanwuyang
	 *@Date:2014-8-27下午5:11:47
	 */
	public List<VioRegul> getVioRegulByTypeId(Integer typeId) ;
}
