package com.paySystem.ic.dao.goods;

import java.util.List;

import com.paySystem.ic.web.dto.goods.GoodsAttrValueDTO;

/**
 * 
 * @ProjectName:omall
 * @ClassName:GoodsAttrValueDAO
 * @Description:商品属性值dao
 * @date: 2014-8-26
 * @author:Jacky
 * @version: V1.0
 */
public interface GoodsAttrValueDAO {
	public static final String GOODATTRVALUE = "goodsAttrValueDAO";
	
	/**
	 * 
	 *@Title:saveBean
	 *@Description:保存商品属性值
	 *@Params:@param goodsAttrValueDTO
	 *@Params:@throws Exception
	 *@Return:void
	 *@author:Jacky
	 *@Date:2014-8-26下午09:00:01
	 */
	public void saveBean(GoodsAttrValueDTO goodsAttrValueDTO) throws Exception;
	
	/**
	 * 
	 *@Title:deleteBean
	 *@Description:删除商品属性
	 *@Params:@param goodsId
	 *@Params:@throws Exception
	 *@Return:void
	 *@author:Jacky
	 *@Date:2014-8-26下午09:00:24
	 */
	public void deleteBean(Long goodsId) throws Exception;
	
	/**
	 * 
	 *@Title:saveBeans
	 *@Description:批量保存
	 *@Params:@param goodsAttrValueDTOList
	 *@Params:@throws Exception
	 *@Return:void
	 *@author:Jacky
	 *@Date:2014-8-26下午09:00:43
	 */
	public void saveBeans(List<GoodsAttrValueDTO> goodsAttrValueDTOList) throws Exception;
	
	/**
	 * 
	 *@Title:findAttrValueByGoodsId
	 *@Description:根据商品编号查找属性值
	 *@Params:@param goodsId
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:List<GoodsAttrValueDTO>
	 *@author:Jacky
	 *@Date:2014-8-26下午09:00:59
	 */
	public List<GoodsAttrValueDTO> findAttrValueByGoodsId(Long goodsId) throws Exception;
}
