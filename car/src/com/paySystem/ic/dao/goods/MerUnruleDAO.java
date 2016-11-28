package com.paySystem.ic.dao.goods;

import java.util.List;

import com.paySystem.ic.web.dto.goods.MerUnruleDTO;

/**
 * 
 * @ProjectName:omall
 * @ClassName:MerUnruleDAO
 * @Description:违规类型dao
 * @date: 2014-8-26
 * @author:Jacky
 * @version: V1.0
 */
public interface MerUnruleDAO {
	public static final String MERUNRULE = "merUnruleDAO";
	/**
	 * 
	 *@Title:save
	 *@Description:保存
	 *@Params:@param merUnrule
	 *@Params:@throws Exception
	 *@Return:void
	 *@author:Jacky
	 *@Date:2014-8-26下午09:02:40
	 */
	void save(MerUnruleDTO merUnrule) throws Exception;
	
	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.MerUnruleDAO#findMerUnruleDTOListByGoodsId(Long)
	 *@MethodName: findMerUnruleDTOListByGoodsId
	 *@Description: 根据商品id查询违规记录
	 *@param Long 商品id
	 *@throws Exception
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:17:35
	 */
	public List<MerUnruleDTO> findMerUnruleDTOListByGoodsId(Long goodsId) throws Exception;
	
}
