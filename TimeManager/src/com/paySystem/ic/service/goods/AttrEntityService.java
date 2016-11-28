package com.paySystem.ic.service.goods;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.base.GoodsAttributeDTO;
import com.paySystem.ic.web.dto.goods.AttrEntityDTO;

/**
 * @ProjectName:omall
 * @ClassName:AttrEntityService
 * @Description:商品属性值服务类
 * @date: 2014-10-10下午04:38:15
 * @author: 徐凯强
 * @version: V1.0
 */
public interface AttrEntityService {

	/** 常量 */
	public static final String ATTRENTITYSERVICE = "AttrEntityService";

	/**
	 *@Title:findAll
	 *@Description:查询数据库中所有的属性名称，用于显示在下拉框中
	 *@param firstindex分页的首个参数
	 *@param pageNum每页有多少条数据
	 *@param goodsAttributeDTO商品属性数据传输对象
	 *@param orderBy排序参数
	 *@Return:QueryResult<GoodsAttributeDTO>商品属性记录和总条数集合
	 *@author:徐凯强
	 *@Date:2014-10-12下午04:15:49
	 */
	public QueryResult<GoodsAttributeDTO> findAll(int firstindex, int pageNum,
			GoodsAttributeDTO goodsAttributeDTO,
			LinkedHashMap<String, String> orderBy);

	/**
	 *@Title:showAttrEntities
	 *@Description:查询数据库中所有的属性值，用于显示在下拉框中
	 *@param firstindex分页的首个参数
	 *@param pageNum每页有多少条数据
	 *@param attrEntityDTO商品属性值数据传输对象
	 *@param goodsAttributeDTO商品属性数据传输对象
	 *@param orderBy排序参数
	 *@Return:QueryResult<AttrEntityDTO>商品属性值记录和总条数集合
	 *@author:徐凯强
	 *@Date:2014-10-12下午04:15:49
	 */
	public QueryResult<AttrEntityDTO> showAttrEntities(int firstindex,
			int pageNum, AttrEntityDTO attrEntityDTO,
			GoodsAttributeDTO goodsAttributeDTO,
			LinkedHashMap<String, String> orderBy);

	/**
	 *@Title:addSave
	 *@Description:保存属性值记录
	 *@param attrEntityDTO数据传输对象
	 *@Return:void返回值
	 *@author:徐凯强
	 *@Date:2014-10-12下午04:15:49
	 */
	public void addSave(AttrEntityDTO attrEntityDTO);

}