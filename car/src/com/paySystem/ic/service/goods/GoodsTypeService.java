package com.paySystem.ic.service.goods;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.goods.GoodsType;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.goods.GoodsTypeDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * 
 * @ProjectName:car 
 * @ClassName:GoodsTypeService  
 * @Description:商品服务类
 * @date: 2016年2月7日下午1:36:58
 * @author: 徐凯强
 * @version: V1.0
 * @date:2016年2月7日下午1:36:58
 */
public interface GoodsTypeService extends DAO<GoodsType>{

	/** 常量 */
	public static final String GOODSTYPESERVICE = "GoodsTypeService";
	public void addSave(GoodsType goodsType);
	public QueryResult<GoodsTypeDTO> queryAll(int firstindex, int pageNum,		GoodsTypeDTO goodsTypeDTO, LinkedHashMap<String, String> orderBy);
	public List<OptionsInteger> queryTypes();
}