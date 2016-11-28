package com.paySystem.ic.service.goods.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsAttribute;
import com.paySystem.ic.dao.goods.AttrEntityDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.goods.AttrEntityService;
import com.paySystem.ic.web.dto.base.GoodsAttributeDTO;
import com.paySystem.ic.web.dto.goods.AttrEntityDTO;

/**
 * @ProjectName:omall
 * @ClassName:AttrEntityServiceImpl
 * @Description:属性值服务实现类
 * @date: 2014-10-10下午04:49:42
 * @author: 徐凯强
 * @version: V1.0
 */
@Service(AttrEntityService.ATTRENTITYSERVICE)
public class AttrEntityServiceImpl extends DaoSupport<GoodsAttribute> implements
		AttrEntityService {

	/** 注入AttrEntityDAO */
	@Resource
	private AttrEntityDAO attrEntityDAO;

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
			LinkedHashMap<String, String> orderBy) {
		/** 要返回的集合 */
		QueryResult<GoodsAttributeDTO> aQueryResultDTO = new QueryResult<GoodsAttributeDTO>();
		/** 接收返回的数据库值 */
		QueryResult<GoodsAttribute> aQueryResult = attrEntityDAO.findAll(
				firstindex, pageNum, goodsAttributeDTO, orderBy);
		/** 将实体转换为DTO */
		List<GoodsAttributeDTO> goodsAttributeDTOs = changeEntityTODTO(aQueryResult
				.getResultlist());
		aQueryResultDTO.setResultlist(goodsAttributeDTOs);
		aQueryResultDTO.setTotalrecord(aQueryResult.getTotalrecord());
		return aQueryResultDTO;
	}

	/**
	 *@Title:changeEntityTODTO
	 *@Description:将实体集合转换为DTO集合
	 *@param attrEntities
	 *@Return:List<GoodsAttributeDTO>
	 *@author:徐凯强
	 *@Date:2014-10-10下午04:50:59
	 */
	public List<GoodsAttributeDTO> changeEntityTODTO(
			List<GoodsAttribute> goodsAttributes) {
		/** 实例化DTO集合 */
		List<GoodsAttributeDTO> goodsAttributeDTOs = new ArrayList<GoodsAttributeDTO>();
		/** 遍历转换 */
		for (GoodsAttribute goodsAttribute : goodsAttributes) {
			/** 实例化DTO对象 */
			GoodsAttributeDTO goodsAttributeDTO = new GoodsAttributeDTO();
			/** 转换DTO对象的属性 */
			goodsAttributeDTO.setAttrId(goodsAttribute.getAttrId());
			goodsAttributeDTO.setAttrName(goodsAttribute.getAttrName());
			goodsAttributeDTO.setIsEn(goodsAttribute.getIsEn());
			/** 放入的DTO集合中 */
			goodsAttributeDTOs.add(goodsAttributeDTO);
		}
		/** 返回DTO集合 */
		return goodsAttributeDTOs;
	}

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
			LinkedHashMap<String, String> orderBy) {
		return attrEntityDAO.showAttrEntities(firstindex, pageNum,
				attrEntityDTO, goodsAttributeDTO, orderBy);
	}
	/**
	 *@Title:addSave
	 *@Description:保存属性值记录
	 *@param attrEntityDTO数据传输对象
	 *@Return:void返回值
	 *@author:徐凯强
	 *@Date:2014-10-12下午04:15:49
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSave(AttrEntityDTO attrEntityDTO){
		attrEntityDAO.addSave(attrEntityDTO);
	}
}
