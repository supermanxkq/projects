package com.paySystem.ic.dao.goods.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.goods.GoodsPhoto;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.goods.GoodsPhotoDAO;
import com.paySystem.ic.web.dto.goods.GoodsPhotoDTO;


/**  
* @Title: GoodsPhotoDAO.java
* @Package: com.paySystem.ic.dao.goods
* @Description: 商品图片DAO接口
* @Author: Jacky
* @Date: 2014-08-06
* @Version: V1.0  
*/
@Repository(GoodsPhotoDAO.GOODSPHOTO)
public class GoodsPhotoDAOImpl extends DaoSupport<GoodsPhoto> implements GoodsPhotoDAO {

	/**
	 * @Title: saveGoodsPhotos
	 * @Description: 保存商品图片信息
	 * @param photoList 商品图片信息
	 * @author:  Jacky
	 * @throws Exception
	 */
	public void saveGoodsPhotos(List<GoodsPhotoDTO> photoList) throws Exception {
		List<GoodsPhoto> photoLists = batchCopy(photoList);
		if(CollectionUtils.isNotEmpty(photoLists))
			this.saves(photoLists);
	}
	
	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.GoodsPhotoDAO#deleteGoodsPhtos(java.lang.Long)
	 *@MethodName:deleteGoodsPhtos
	 *@Description:删除商品图片
	 *@param goodsId
	 *@throws Exception
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:16:59
	 */
	public void deleteGoodsPhtos(Long goodsId) throws Exception {
		em.createQuery("delete from GoodsPhoto o where o.goodsId=?").setParameter(1, goodsId).executeUpdate();
	}

	/**
	 * 
	 *@Title:findGoodsPhotoByGoodId
	 *@Description:根据商品编号查找商品图片
	 *@Params:@param goodsId
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:List<GoodsPhotoDTO>
	 *@author:Jacky
	 *@Date:2014-8-26下午09:01:44
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsPhotoDTO> findGoodsPhotoByGoodId(Long goodsId)
			throws Exception {
		List<GoodsPhoto> resultList = em.createQuery("from GoodsPhoto o where o.goodsId=? order by o.photoId asc").setParameter(1, goodsId).getResultList();
		List<GoodsPhotoDTO> goodsPhotoList = new ArrayList<GoodsPhotoDTO>();
		for(GoodsPhoto goodsPhoto : resultList) {
			GoodsPhotoDTO gpt = new GoodsPhotoDTO();
			BeanUtils.copyProperties(goodsPhoto, gpt);
			goodsPhotoList.add(gpt);
		}
		return goodsPhotoList;
	}

	/**
	 * 
	 *@Title:batchCopy
	 *@Description:批量复制
	 *@Params:@param photoList 商品图片集合
	 *@Params:@return
	 *@Return:List<GoodsPhoto>
	 *@author:Jacky
	 *@Date:2014-8-26下午09:16:38
	 */
	private List<GoodsPhoto> batchCopy(List<GoodsPhotoDTO> photoList) {
		List<GoodsPhoto> goodsPhotoList = new ArrayList<GoodsPhoto>();
		if(CollectionUtils.isNotEmpty(photoList)) {
			for(GoodsPhotoDTO goodsPhotoDTO : photoList) {
				GoodsPhoto gp = new GoodsPhoto();
				BeanUtils.copyProperties(goodsPhotoDTO, gp);
				goodsPhotoList.add(gp);
			}
		}
		return goodsPhotoList;
	}
	
}
