package com.paySystem.ic.service.goods.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.goods.Goods;
import com.paySystem.ic.dao.goods.GoodsDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.goods.GoodsService;
import com.paySystem.ic.web.dto.goods.GoodsDTO;

/**
 * @ProjectName:omall
 * @ClassName:AttrEntityServiceImpl
 * @Description:属性值服务实现类
 * @date: 2014-10-10下午04:49:42
 * @author: 徐凯强
 * @version: V1.0
 */
@Service(GoodsService.GOODSSERVICE)
public class GoodsServiceImpl extends DaoSupport<Goods> implements
		GoodsService {

	/** 注入GoodsDao */
	@Resource
	private GoodsDAO goodsDAO;
	/**
	 * 
	 *@MethodName:addSave
	 *@Description:添加商品
	 *@param goods 
	 *@author:徐凯强
	 *@date:2016年2月7日下午1:43:03
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSave(Goods goods){
		this.save(goods);
	}
	/**
	 * 查询所有商品
	 *@MethodName:queryAll
	 *@Description:查询所有的商品信息
	 *@param goodsDTO
	 *@return 
	 *@author:徐凯强
	 *@date:2016年2月7日下午2:14:29
	 */
	public QueryResult<GoodsDTO> queryAll(int firstindex, int pageNum,
			GoodsDTO goodsDTO, LinkedHashMap<String, String> orderBy){
		return	goodsDAO.queryAll(firstindex, pageNum,goodsDTO, orderBy);
	}
}
