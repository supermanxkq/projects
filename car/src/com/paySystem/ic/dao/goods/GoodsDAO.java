package com.paySystem.ic.dao.goods;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.goods.GoodsDTO;

/**
 * 
 * @ProjectName:car 
 * @ClassName:GoodsDAO  
 * @Description:商品数据库操作Dao
 * @date: 2016年2月7日下午1:45:53
 * @author: 徐凯强
 * @version: V1.0
 * @date:2016年2月7日下午1:45:53
 */
public interface GoodsDAO {
	
	public static final String GOODSDAO = "goodsDao";
	/**
	 *@MethodName:queryAll 
	 *@Description:查询所有商品信息
	 *@param firstindex
	 *@param pageNum
	 *@param goodsDTO
	 *@param orderBy
	 *@author:徐凯强
	 *@return QueryResult<GoodsDTO>
	 *@date:2016年2月7日下午2:17:09
	 */
	public QueryResult<GoodsDTO> queryAll(int firstindex, int pageNum,		GoodsDTO goodsDTO, LinkedHashMap<String, String> orderBy);
}
