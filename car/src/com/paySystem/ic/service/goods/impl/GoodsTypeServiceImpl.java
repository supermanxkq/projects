package com.paySystem.ic.service.goods.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.goods.GoodsType;
import com.paySystem.ic.dao.goods.GoodsTypeDAO;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.goods.GoodsTypeService;
import com.paySystem.ic.web.dto.goods.GoodsTypeDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ProjectName:omall
 * @ClassName:AttrEntityServiceImpl
 * @Description:属性值服务实现类
 * @date: 2014-10-10下午04:49:42
 * @author: 徐凯强
 * @version: V1.0
 */
@Service(GoodsTypeService.GOODSTYPESERVICE)
public class GoodsTypeServiceImpl extends DaoSupport<GoodsType> implements
		GoodsTypeService {

	@Resource
	private GoodsTypeDAO goodsTypeDAO;
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSave(GoodsType goodsType){
		this.save(goodsType);
	}
	public QueryResult<GoodsTypeDTO> queryAll(int firstindex, int pageNum,
			GoodsTypeDTO goodsTypeDTO, LinkedHashMap<String, String> orderBy){
		return	goodsTypeDAO.queryAll(firstindex, pageNum,goodsTypeDTO, orderBy);
	}
	@Override
	public List<OptionsInteger> queryTypes() {
		List<OptionsInteger> list=new ArrayList<>();
		try{
		StringBuffer wherejpql=new StringBuffer();
		List<Object> queryParams=new ArrayList<>();
		wherejpql.append(" and o.status!=9");
		QueryResult<GoodsType> queryResult=this.getScrollData(-1, -1, wherejpql.toString(), queryParams.toArray());
		for (GoodsType goodsType : queryResult.getResultlist()) {
			list.add(new OptionsInteger(goodsType.getId(), goodsType.getName()));
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return  list;
	}
}
