package com.paySystem.ic.dao.goods.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.StoreInfo;
import com.paySystem.ic.bean.goods.GoodInfo;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.goods.GoodsDAO;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.goods.GoodsDTO;

/**  
* @Title: GoodsDAOImpl.java
* @Package: com.paySystem.ic.dao.goods.impl
* @Description: 商品品牌DAO接口实现类
* @Author: Jacky
* @Date: 2014-08-06
* @Version: V1.0  
*/
@Repository(GoodsDAO.GOODSDAO)
public class GoodsDAOImpl extends DaoSupport<GoodInfo> implements GoodsDAO {

	/**
	 *@Title: findByParam
	 *@Description: 根据参数查询商品信息
	 *@param:@param firstPage 开始条数
	 *@param:@param pageNum   每页显示调试
	 *@param:@param goodsDTO GoodsDTO对象
	 *@param:@param orderBy   排序方式
	 *@Return:GoodBrandsDTO 商品信息对象
	 *@author: Jacky
	 *@Thorws:
	 */
	public QueryResult<GoodsDTO> findByParam(int firstPage, int pageNum,
			GoodsDTO goodsDTO, LinkedHashMap<String, String> orderBy)
			throws Exception {
		QueryResult<GoodsDTO> dtoResult
			= new QueryResult<GoodsDTO>();
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>(); 
		
		/** 多条件like **/
		if(StringUtils.isNotBlank(goodsDTO.getGoodsName())){
			sb.append(" and o.goodsName like ?").append(params.size()+1);
			params.add("%"+goodsDTO.getGoodsName().trim()+"%");
		}
		if(StringUtils.isNotBlank(goodsDTO.getGoodsItem())) {
			sb.append(" and o.goodsItem like ?").append(params.size()+1);
			params.add("%"+goodsDTO.getGoodsItem().trim()+"%");
		}
		if(StringUtils.isNotBlank(goodsDTO.getMerId())) {
			sb.append(" and o.merId like ?").append(params.size()+1);
			params.add("%"+goodsDTO.getMerId().trim()+"%");
		}
		if(null != goodsDTO.getGoodSaleSta()&&  goodsDTO.getGoodSaleSta() > -1 ) {
			sb.append(" and o.goodSaleSta = ?").append(params.size()+1);
			params.add(goodsDTO.getGoodSaleSta());
		}
		if(null != goodsDTO.getGoodsSta() && goodsDTO.getGoodsSta() > -1) {
			sb.append(" and o.goodsSta = ?").append(params.size()+1);
			params.add(goodsDTO.getGoodsSta());
		}
		if(null != goodsDTO.getTypeId() && goodsDTO.getTypeId() > -1) {
			sb.append(" and o.typeId = ?").append(params.size()+1);
			params.add(goodsDTO.getTypeId());
		}
		if(StringUtils.isNotBlank(goodsDTO.getCurrentMerId())) {
			sb.append(" and o.merId = ?").append(params.size()+1);
			params.add(goodsDTO.getCurrentMerId());
		}
		if(null != goodsDTO.getUnRuleMaSta() && goodsDTO.getUnRuleMaSta() > -1) {
			sb.append(" and o.unRuleMaSta = ?").append(params.size()+1);
			params.add(goodsDTO.getUnRuleMaSta());
		}
		
		QueryResult<GoodInfo> result =
				this.getScrollData(firstPage, pageNum, sb.toString(), params.toArray(),orderBy);
		dtoResult = this.dtoResult2beanResult(result);
		
		return dtoResult;
	}
	
	/**
	 *@Title: queryGoodsDTOById
	 *@Description: 根据参数查询商品信息
	 *@param:@param goodId 商品信息
	 *@Return: GoodsDTO 商品信息DTO对象
	 *@author: Jacky
	 *@Thorws:
	 */
	public GoodsDTO queryGoodsDTOById(long goodId) throws Exception {
		GoodInfo goodInfo = this.find(goodId);
		return bean2DTO(goodInfo);
	}

	/**
	 * @Title: saveGoodsInfo
	 * @Description: 保存商品信息对象
	 * @param goodsDTO 商品信息dto
	 * @author:  Jacky
	 * @throws Exception
	 */
	public void saveGoodsInfo(GoodsDTO goodsDTO) throws Exception {
		GoodInfo goodInfo = dto2Bean(goodsDTO);
		this.save(goodInfo);
		goodsDTO.setGoodsId(goodInfo.getGoodsId());
	}
	
	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.GoodsDAO#updateGoodsInfo(com.paySystem.ic.web.dto.goods.GoodsDTO)
	 *@MethodName:updateGoodsInfo
	 *@Description:修改商品信息
	 *@param goodsDTO
	 *@throws Exception
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:15:48
	 */
	public void updateGoodsInfo(GoodsDTO goodsDTO) throws Exception {
		GoodInfo goodInfo = dto2Bean(goodsDTO);
		GoodInfo goodInfos = this.find(goodsDTO.getGoodsId());
		if(!goodInfos.getMerId().equals(goodsDTO.getCurrentMerId())) {
			throw new IllegalAccessException("你无权修改此商品!");
		}
		this.update(goodInfo);
	}

	/**
	 * @Title: dto2Bean
	 * @Description: dto转bean
	 * @param goodsDTO
	 * @return GoodInfo
	 */
	private static GoodInfo dto2Bean(GoodsDTO goodsDTO) {
		GoodInfo goodInfo = new GoodInfo();
		if(null != goodsDTO) {
			/** 这里没有使用spring beanutil 因为很多属性都要ignore**/
			goodInfo.setGoodDescr(goodsDTO.getDealDesc());
			if(null != goodsDTO.getGoodsId() && goodsDTO.getGoodsId() > 0) {
				goodInfo.setGoodsId(goodsDTO.getGoodsId());
			}
			StoreInfo storeInfo = new StoreInfo();
			storeInfo.setStoreId(goodsDTO.getCurrentMerId());
			
			goodInfo.setGoodSaleSta(goodsDTO.getGoodsSaleStas()==null?1:0);
			goodInfo.setGoodsItem(goodsDTO.getGoodsItem());
			goodInfo.setGoodsName(goodsDTO.getGoodsName());
			goodInfo.setGoodsSta(goodsDTO.getGoodsSta()==null?0:goodsDTO.getGoodsSta());
			goodInfo.setMerId(goodsDTO.getCurrentMerId());
			goodInfo.setStoreInfo(storeInfo);
			goodInfo.setUserName(goodsDTO.getUserName());
			goodInfo.setOffSaleDate(null);
			goodInfo.setGoodDescr(goodsDTO.getGoodDescr());
			goodInfo.setIsFreeTran(goodsDTO.getIsFreeTran()==null?0:1);
			goodInfo.setRate(goodsDTO.getRate());
			/*  设置商户编号 */
			//goodInfo.setStoreId(Utils.getUserSession().getMerId());
			
			goodInfo.setProId(goodsDTO.getProId());
			Date now = new Date();
			if(goodInfo.getGoodSaleSta() == 0) {
				goodInfo.setSaleDate(now);
			}
			/** 排序不要的的 **/
			goodInfo.setSortId("0");
			goodInfo.setTypeId(goodsDTO.getTypeId());
			goodInfo.setUnRuleMaSta(0);
			goodInfo.setUpdateDate(now);
		}
		return goodInfo;
	}
	
	
	/**
	 * @Title: dealGoodsInfo
	 * @Description: 下架商户所有商品
	 * @param merId 商户id
	 * @author:  Jacky
	 * @throws Exception
	 * @Date:2014-8-22下午6:05:34
	 */
	public void offlineAllGoods(String merId) throws Exception {
		em.createQuery("update GoodInfo o set o.goodSaleSta = 1 ,o.unRuleMaSta=2 where o.goodsSta=0 and merId= ?").setParameter(1, merId).executeUpdate();
	}

	/**
	 * BeanResult转 实体DTOResult
	 * 
	 *@Title:dtoResult2beanResult
	 *@Description:BeanResult转 实体DTOResult
	 *@param:@param result
	 *               实体BeanResult
	 *@param:@return
	 *@return:QueryResult<GoodBrandsDTO>
	 *              DTO类型Result信息
	 *@author: Jacky
	 *@Thorws:
	 */
	private QueryResult<GoodsDTO> dtoResult2beanResult (QueryResult<GoodInfo> result) {
		QueryResult<GoodsDTO> dtoResult 
        	= new QueryResult<GoodsDTO>();
		List<GoodsDTO> dtoList
		        = new ArrayList<GoodsDTO>();
		
		if(result != null){
			/** 遍历实体Result信息，获取支付参数实体对象 **/
			for (GoodInfo goodsInfo : result.getResultlist()){
				/**将商品拍拍实体对象转为商品品牌DTO对象**/
				GoodsDTO goodsDto = bean2DTO(goodsInfo); 
				/**添加到DTO对象集合中**/
				dtoList.add(goodsDto);
			}
		
		}
		/**组装DtoResult信息  DTO对象集合及总条数**/
		dtoResult.setResultlist(dtoList);
		dtoResult.setTotalrecord(result.getTotalrecord());
		return dtoResult;
	}
	
	/**
	 *@Title: bean2DTO
	 *@Description: bean转DTO
	 *@param:@param goodsInfo  商品信息实体
	 *@Return: GoodsDTO 商品信息对象
	 *@author: Jacky
	 *@Thorws:
	 */
	private GoodsDTO bean2DTO(GoodInfo goodsInfo) {
		GoodsDTO goodsDTO = new GoodsDTO();
		if(null != goodsInfo) {
			BeanUtils.copyProperties(goodsInfo, goodsDTO, new String[]{"typeName","goodsPhotoList"});
		}
		return goodsDTO;
	}
	
	 /**
	 * @Title: deleteGoodsInfo
	 * @Description: 删除商品信息对象
	 * @param goodsId 商品信息id
	 * @author:  Jacky
	 * @throws Exception
	 * @Date:2014-8-22下午6:05:34
	 */
	public void deleteGoodsInfo(Long goodsId) throws Exception {
		GoodInfo goodInfo = this.find(goodsId);
		if(null != goodInfo) {
			/** 删除 **/
			goodInfo.setGoodsSta(9);
			/** 下架**/
			goodInfo.setGoodSaleSta(1);
			this.update(goodInfo);
		}
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.GoodsDAO#deleteGoodsInfo(long, java.lang.String)
	 *@MethodName:deleteGoodsInfo
	 *@Description:删除商品信息
	 *@param goodsId
	 *@param merId
	 *@throws Exception
	 *@Author:yanwuyang
	 *@Date:2014-8-26下午09:16:06
	 */
	public void deleteGoodsInfo(long goodsId, String merId) throws Exception {
		GoodInfo goodInfo = this.find(goodsId);
		if(null != goodInfo) {
			if(goodInfo.getMerId().equals(merId) && goodInfo.getGoodsSta() == 0) {
				/** 删除 **/
				goodInfo.setGoodsSta(9);
				/** 下架**/
				goodInfo.setGoodSaleSta(1);
				this.update(goodInfo);
			}
		}
	}

	/**
	 * @Title: dealGoodsInfo
	 * @Description: 处理商品信息对象
	 * @param goodsId 商品信息id
	 * @author:  Jacky
	 * @throws Exception
	 * @Date:2014-8-22下午6:05:34
	 */
	public void dealGoodsInfo(Long goodsId) throws Exception {
		GoodInfo goodInfo = this.find(goodsId);
		if(null != goodInfo) {
			if(goodInfo.getGoodsSta() == 0) {
				/** 删除 **/
				goodInfo.setGoodsSta(9);
				/** 下架**/
				goodInfo.setGoodSaleSta(1);
				/** 未处理**/
				goodInfo.setUnRuleMaSta(2);
				this.update(goodInfo);
			}
		}
	}

	/**
	 *@Title: updateGoodsInfo
	 *@Description: 下架ids的商品
	 *@param:@param ids 商品信息id
	 *@author: Jacky
	 *@Thorws:
	 */
	public ReturnDTO updateGoodsInfo(List<Long> ids,int type) throws Exception {
		ReturnDTO returnDTO = new ReturnDTO();
		Set<Long> goodIdSet = new HashSet<Long>(ids);
		List<Long> failedList = new ArrayList<Long>();
		for(Long goodsId : goodIdSet) {
			GoodInfo goodInfo = this.find(goodsId);
			if(goodInfo!=null) {
				/** 1下架商品  0上架**/
				goodInfo.setGoodSaleSta(type);
				this.update(goodInfo);
			} else {
				failedList.add(goodsId);
			}
		}
		returnDTO.setFlag(true);
		returnDTO.setObj(failedList);
		return returnDTO;
	}

}
