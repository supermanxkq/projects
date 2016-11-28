package com.paySystem.ic.dao.goods;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.goods.GoodsDTO;

/**  
* @Title: GoodsDAO.java
* @Package: com.paySystem.ic.dao.goods
* @Description: 商品信息DAO接口
* @Author: Jacky
* @Date: 2014-08-01
* @Version: V1.0  
*/
public interface GoodsDAO {
	
	public static final String GOODSDAO = "goodsDao";
	
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
			GoodsDTO goodsDTO, LinkedHashMap<String, String> orderBy)  throws Exception ;
	
	/**
	 *@Title: queryGoodsDTOById
	 *@Description: 根据参数查询商品信息
	 *@param:@param goodId 商品信息
	 *@Return: GoodsDTO 商品信息DTO对象
	 *@author: Jacky
	 *@Thorws:
	 */
	public GoodsDTO queryGoodsDTOById(long goodId) throws Exception ;
	
	/**
	 *@Title: updateGoodsInfo
	 *@Description: 下架ids的商品
	 *@param:@param ids 商品信息id
	 *@param:@param type表示更新操作类型
	 *@return ReturnDTO 失败的商品
	 *@author: Jacky
	 *@Thorws:
	 */
	public ReturnDTO updateGoodsInfo(List<Long> ids,int type) throws Exception;
	
	
	/**
	 * @Title: saveGoodsInfo
	 * @Description: 保存商品信息对象
	 * @param goodsDTO 商品信息dto
	 * @author:  Jacky
	 * @throws Exception
	 * @Date:2014-8-22下午6:05:34
	 */
	 void saveGoodsInfo(GoodsDTO goodsDTO) throws Exception;
	 
	 /**
	 * @Title: updateGoodsInfo
	 * @Description: 更新商品信息对象
	 * @param goodsDTO 商品信息dto
	 * @author:  Jacky
	 * @throws Exception
	 * @Date:2014-8-22下午6:05:34
	 */
	 void updateGoodsInfo(GoodsDTO goodsDTO) throws Exception;
	 
	 /**
	 * @Title: deleteGoodsInfo
	 * @Description: 删除商品信息对象
	 * @param goodsId 商品信息id
	 * @author:  Jacky
	 * @throws Exception
	 * @Date:2014-8-22下午6:05:34
	 */
	 void deleteGoodsInfo(Long goodsId) throws Exception;
	 
	 /**
	 * @Title: dealGoodsInfo
	 * @Description: 处理商品信息对象
	 * @param goodsId 商品信息id
	 * @author:  Jacky
	 * @throws Exception
	 * @Date:2014-8-22下午6:05:34
	 */
	 void dealGoodsInfo(Long goodsId) throws Exception;
	 
	 /**
		 * @Title: dealGoodsInfo
		 * @Description: 下架商户所有商品
		 * @param merId 商户id
		 * @author:  Jacky
		 * @throws Exception
		 * @Date:2014-8-22下午6:05:34
		 */
	 void offlineAllGoods(String merId) throws Exception;
	 
	 /**
	 * @Title: dealGoodsInfo
	 * @Description: 处理商品信息对象
	 * @param goodsId 商品信息id
	 * @param merId 会员名称
	 * @author:  Jacky
	 * @throws Exception
	 * @Date:2014-8-22下午6:05:34
	 */
	 void deleteGoodsInfo(long goodsId,String merId) throws Exception;
	
}
