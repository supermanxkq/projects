package com.paySystem.ic.dao.goods;

import java.util.List;

import com.paySystem.ic.web.dto.goods.FormatInfoDTO;
/**
 * 
 * @ProjectName:omall
 * @ClassName:FormatInfoDAO
 * @Description:规格信息dao
 * @date: 2014-8-26
 * @author:Jacky
 * @version: V1.0
 */
public interface FormatInfoDAO {
	public static final String FORMATINFODAO = "formatInfoDAO";
	

	/**
	 * 
	 *@Title:saveBeans
	 *@Description:保存规格信息
	 *@Params:@param formatList 规格集合
	 *@Params:@throws Exception
	 *@Return:void
	 *@author:Jacky
	 *@Date:2014-8-26下午08:56:12
	 */
	public void saveBeans(List<FormatInfoDTO> formatList) throws Exception;
	
	/**
	 * 
	 *@Title:deleteBeans
	 *@Description:删除规格对象
	 *@Params:@param goodsId商品ID
	 *@Params:@throws Exception
	 *@Return:void
	 *@author:Jacky
	 *@Date:2014-8-26下午08:56:44
	 */
	public void deleteBeans(Long goodsId) throws Exception;
	
	/**
	 * 
	 *@Title:findFormatInfoDTOByGoodsId
	 *@Description:根据商品ID查找规格信息
	 *@Params:@param goodsId 商品ID
	 *@Params:@return
	 *@Params:@throws Exception
	 *@Return:List<FormatInfoDTO>
	 *@author:Jacky
	 *@Date:2014-8-26下午08:58:21
	 */
	public List<FormatInfoDTO> findFormatInfoDTOByGoodsId(Long goodsId) throws Exception;
	
}
