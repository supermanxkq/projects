package com.paySystem.ic.dao.goods;

import java.util.List;

import com.paySystem.ic.web.dto.goods.GoodsPhotoDTO;

/**  
* @Title: GoodsPhotoDAO.java
* @Package: com.paySystem.ic.dao.goods
* @Description: 商品图片DAO接口
* @Author: Jacky
* @Date: 2014-08-06
* @Version: V1.0  
*/
public interface GoodsPhotoDAO {
	
	public static final String GOODSPHOTO = "goodsPhotoDAO";
	
	/**
	 * @Title: saveGoodsPhotos
	 * @Description: 保存商品图片信息
	 * @param photoList 商品图片信息
	 * @author:  Jacky
	 * @throws Exception
	 */
	public void saveGoodsPhotos(List<GoodsPhotoDTO> photoList) throws Exception ;
	
	/**
	 * 
	 *@Title:deleteGoodsPhtos
	 *@Description:删除商品照片
	 *@Params:@param goodsId
	 *@Params:@throws Exception
	 *@Return:void
	 *@author:Jacky
	 *@Date:2014-8-26下午09:01:31
	 */
	public void deleteGoodsPhtos(Long goodsId) throws Exception;
	
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
	public List<GoodsPhotoDTO> findGoodsPhotoByGoodId(Long goodsId) throws Exception;
}
