package com.paySystem.ic.service.goods;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.goods.Goods;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.goods.GoodsDTO;

/**
 * 
 * @ProjectName:car 
 * @ClassName:GoodsService  
 * @Description:商品服务类
 * @date: 2016年2月7日下午1:36:58
 * @author: 徐凯强
 * @version: V1.0
 * @date:2016年2月7日下午1:36:58
 */
public interface GoodsService extends DAO<Goods>{

	/** 常量 */
	public static final String GOODSSERVICE = "GoodsService";

	/**
	 * 
	 *@MethodName:addSave 
	 *@Description:添加商品
	 *@param goods 
	 *@author:徐凯强
	 *@return void
	 *@date:2016年2月7日下午1:41:07
	 */
	public void addSave(Goods goods);
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